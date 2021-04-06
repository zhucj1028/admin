<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--角色数据-->
      <el-col :span="20" :xs="24">
        <el-form ref="queryForm" :inline="true" :model="queryParams" label-width="68px">
          <el-form-item label="角色名称" prop="roleName">
            <el-input
              v-model="queryParams.roleName"
              clearable
              placeholder="请输入角色名称"
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>

          <el-form-item>
            <el-button icon="el-icon-search" size="mini" type="primary" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              icon="el-icon-plus"
              size="mini"
              type="primary"
              @click="handleAdd"
            >新增
            </el-button>
          </el-col>

        </el-row>

        <el-table v-loading="loading" :data="roleList" stripe style="width: 100%" @selection-change="handleSelectionChange">
          <el-table-column align="center" type="selection" width="50"/>
          <el-table-column :show-overflow-tooltip="true" align="center" label="角色名称" prop="name"/>
          <el-table-column
            align="center"
            class-name="small-padding fixed-width"
            label="操作"
            width="180"
          >
            <template slot-scope="scope">
              <el-button
                icon="el-icon-plus"
                size="mini"
                type="text"
                @click="handleAuth(scope.row)"
              >授权
              </el-button>
              <el-button
                icon="el-icon-edit"
                size="mini"
                type="text"
                @click="handleUpdate(scope.row)"
              >修改
              </el-button>
              <el-button
                icon="el-icon-delete"
                size="mini"
                type="text"
                @click="handleDelete(scope.row)"
              >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <pagination
          v-show="total>0"
          :limit.sync="queryParams.pageSize"
          :page.sync="queryParams.pageNum"
          :total="total"
          @pagination="getList"
        />
      </el-col>
    </el-row>

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="600px">
      <el-form ref="form" :model="form" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item  label="角色名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入角色名称" type="text"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="loading" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 授权对话框 -->
    <el-dialog :title="title" :visible.sync="openAuth" append-to-body width="600px">
      <el-form ref="form" :model="form" label-width="80px">
<!--        <el-row>-->
<!--          <el-col :span="12">-->
<!--            <el-form-item  label="角色名称" prop="name">-->
<!--              <el-input v-model="form.name" placeholder="请输入角色名称" type="text"/>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->
        <el-row>
          <el-form-item label="菜单权限">
            <el-tree
              class="tree-border"
              :data="menuOptions"
              show-checkbox
              ref="menu"
              :check-strictly="menuCheckStrictly"
              node-key="id"
              empty-text="加载中，请稍后"
              :props="defaultProps"
            ></el-tree>
          </el-form-item>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="loading" type="primary" @click="submitAuth">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import Pagination from '@/components/Pagination';
import {addRole, listRole, getRole, updateRole, delRole, impower} from '@/api/role';
import {roleMenuTreeselect} from '@/api/role';
import {handleTree} from "@/utils/common";

export default {
  name: 'User',
  components: { Pagination },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 角色表格数据
      roleList: null,
      // 弹出层标题
      title: '',
      // 是否显示修改弹出层
      open: false,
      // 是否显示授权弹出层
      openAuth: false,
      // 日期范围
      dateRange: [],
      // 状态数据字典
      statusOptions: [],
      menuOptions: [],
      menuCheckStrictly:true,
      // 表单参数
      form: {},
      defaultProps: {
        children: "children",
        label: "name"
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      // 表单校验

    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询角色列表 */
    getList() {
      this.loading = true;
      listRole(this.queryParams)
        .then(response => {
            this.roleList = response.data.list;
            this.total = response.data.totalCount;
            this.loading = false;
          }
        );
    },

    getRoleMenuTreeselect(roleId) {
    return   roleMenuTreeselect(roleId).then(res=>{
      this.menuOptions = handleTree(res.data,"id");
        return res;
      });
    },
    // 所有菜单节点数据
    getMenuAllCheckedKeys() {
      // 目前被选中的菜单节点
      let checkedKeys = this.$refs.menu.getCheckedKeys();
      // 半选中的菜单节点
      let halfCheckedKeys = this.$refs.menu.getHalfCheckedKeys();
      checkedKeys.unshift.apply(checkedKeys, halfCheckedKeys);
      return checkedKeys;
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.openAuth = false;
      this.menuCheckStrictly = true;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        deptId: undefined,
        name: undefined,
        nickName: undefined,
        password: undefined,
        phonenumber: undefined,
        email: undefined,
        sex: undefined,
        status: '0',
        remark: undefined,
        postIds: [],
        roleIds: []
      };
    },
    //多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.page = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.handleQuery();
    },
    handleAuth(row) {
      this.reset()
      const roleId = row.id || this.ids
      const roleMenu = this.getRoleMenuTreeselect(roleId);
      getRole(roleId)
        .then(response => {
          this.form = response.data
          this.openAuth = true
          this.$nextTick(() => {
            roleMenu.then(res => {
              this.menuCheckStrictly = false;
              this.$refs.menu.setCheckedKeys(res.resIdListByRole);
            })
          });
          this.title = '角色授权';
        })
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const roleId = row.id || this.ids
      const roleMenu = this.getRoleMenuTreeselect(roleId);
      getRole(roleId)
        .then(response => {
          this.form = response.data
          this.open = true
          this.$nextTick(() => {
            roleMenu.then(res => {

              this.$refs.menu.setCheckedKeys(res.resIdListByRole);
            })
          });
          this.title = '修改角色'
        })
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      getRole()
        .then(response => {
          this.postOptions = response.posts;
          this.roleOptions = response.roles;
          this.open = true;
          this.title = '添加角色';
          this.form.password = this.initPassword;
        });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const roleIds = row.id || this.ids
      this.$confirm('是否确认删除角色编号为"' + roleIds + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(function () {
          return delRole(roleIds)
        })
        .then(() => {
          this.getList()
          this.msgSuccess('删除成功')
        })
        .catch(function () {

        })
    },
    submitAuth() {
      let resIdList = this.getMenuAllCheckedKeys();
      let temp = [];
      for (const resIdListElement of resIdList) {
        temp.push({resid:resIdListElement,rid:this.form.id})
      }
      this.loading = true;
      impower(temp).then(response => {
        if (response.code === 20000) {
          this.msgSuccess('修改成功');
          this.loading = false;
          this.openAuth = false;
          this.menuCheckStrictly = true;
          this.getList();
        }
      });
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            this.loading = true;
            updateRole(this.form)
              .then(response => {
                if (response.code === 20000) {
                  this.msgSuccess('修改成功');
                  this.loading = false;
                  this.openAuth = false;
                  this.getList();
                }
              });
          } else {
            this.loading = true;
            addRole(this.form)
              .then(response => {
                if (response.code === 20000) {
                  this.msgSuccess('新增成功');
                  this.loading = false;
                  this.open = false;
                  this.getList();
                }
              });
          }
        }
      });
    },
  }
};
</script>
