<template>
  <div class="app-container">
    <el-form :inline="true">
      <el-form-item label="资源名称">
        <el-input
          v-model="queryParams.resName"
          placeholder="请输入资源名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button  type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
      </el-form-item>
    </el-form>

    <el-table
      v-loading="loading"
      :data="resList"
      row-key="id"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column prop="name" label="资源名称" :show-overflow-tooltip="true" width="160" />

      <el-table-column prop="url" label="资源路径" :show-overflow-tooltip="true" />


      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
          >新增</el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改菜单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form"  label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="上级资源">
              <treeselect
                v-model="form.parentId"
                :options="menuOptions"
                :normalizer="normalizer"
                :show-count="true"
                placeholder="选择上级资源"
              />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="资源名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入资源名称" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="资源地址" prop="path">
              <el-input v-model="form.url" placeholder="请输入资源地址" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="loading" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRes,addRes,delRes,getRes,updateRes} from '@/api/res'
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
import { handleTree } from '@/utils/common'


export default {
  name: 'Menu',
  components: {Treeselect},
  data() {
    return {
      // 遮罩层
      loading: true,
      // 菜单表格树数据
      resList: [],
      // 菜单树选项
      menuOptions: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 显示状态数据字典
      visibleOptions: [],
      // 菜单状态数据字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        resName: undefined,
        visible: undefined
      },
      // 表单参数
      form: {},
    }
  },
  created() {
    this.getList()
  },
  methods: {

    /** 查询菜单列表 */
    getList() {
      this.loading = true
      listRes(this.queryParams).then(response => {
        this.resList = handleTree(response.data, 'id')
        this.loading = false
      })
    },
    /** 转换菜单数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children
      }
      return {
        id: node.id,
        label: node.name,
        children: node.children
      }
    },
    /** 查询菜单下拉树结构 */
    getTreeselect() {
      listRes().then(response => {
        this.menuOptions = []
        const menu = { id: 0, name: '顶层资源', children: [] }
        menu.children = handleTree(response.data, 'id')
        this.menuOptions.push(menu)
      })
    },

    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        menuId: undefined,
        parentId: 0,
        menuName: undefined,
        icon: undefined,
        menuType: 'M',

      }
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList()
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset()
      this.getTreeselect()
      if (row != null) {
        this.form.parentId = row.id
      }
      this.open = true
      this.title = '添加资源'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      this.getTreeselect()
      getRes(row.id).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改菜单'
      })
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            this.loading = true;
            updateRes(this.form).then(response => {
              if (response.code === 20000) {
                this.msgSuccess('修改成功');
                this.loading = false;
                this.open = false
                this.getList()
              }
            });
          } else {
            this.loading = true;
            addRes(this.form).then(response => {
              if (response.code === 20000) {
                this.msgSuccess('新增成功');
                this.loading = false;
                this.open = false
                this.getList()
              }
            });
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$confirm('是否确认删除名称为"' + row.name + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return delRes(row.id)
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function() {})
    }
  }
}
</script>
