<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--用户数据-->
      <el-col :span="20" :xs="24">
        <el-form ref="queryForm" :inline="true" :model="queryParams" label-width="68px">
          <el-form-item label="用户名称" prop="roleName">
            <el-input
              v-model="queryParams.adminName"
              clearable
              placeholder="请输入用户名称"
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

        <el-table v-loading="loading" :data="adminList" stripe style="width: 100%" @selection-change="handleSelectionChange">
          <el-table-column align="center" type="selection" width="50"/>
          <el-table-column :show-overflow-tooltip="true" align="center" label="用户名称" prop="name"/>
          <el-table-column :show-overflow-tooltip="true" align="center" label="手机号码" prop="phone"/>
          <el-table-column :show-overflow-tooltip="true" align="center" label="用户密码" prop="password"/>
          <el-table-column :show-overflow-tooltip="true" align="center" label="登录方式" prop="login"/>
          <el-table-column
            align="center"
            class-name="small-padding fixed-width"
            label="操作"
            width="180"
          >
            <template slot-scope="scope">
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
            <el-form-item  label="用户名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入用户名称" type="text"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item  label="用户密码" prop="password">
              <el-input v-model="form.password" placeholder="请输入用户密码" type="password"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item  label="用户角色" prop="rid">
              <el-select v-model="form.rid"  placeholder="请选择">
                <el-option
                  v-for="item in roleList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
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
  import Pagination from '@/components/Pagination';
import { addAdmin, listAdmin,getAdmin,updateAdmin,delAdmin} from '@/api/admin';
  import {findAllRole} from "@/api/role";

export default {
  name: 'Admin',
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
      // 用户表格数据
      adminList: null,
      roleList: [{id:1, name: '管理员'}],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {},
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: undefined,
        phonenumber: undefined,
        status: undefined,
        deptId: undefined
      },
      // 表单校验

    };
  },
  created() {
    this.getList();
    this.getRoleList();
  },
  methods: {
    /** 查询用户列表 */
    getList() {
      this.loading = true;
      listAdmin(this.queryParams)
        .then(response => {
            this.adminList = response.data.list;
            this.total = response.data.totalCount;
            this.loading = false;
          }
        );
    },
    getRoleList() {
      findAllRole().then(response => {
        this.roleList = response.data;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
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

    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      getAdmin()
        .then(response => {
          this.postOptions = response.posts;
          this.open = true;
          this.title = '添加角色';
          this.form.password = this.initPassword;
        });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const adminId = row.id || this.ids
      getAdmin(adminId)
        .then(response => {
          this.form = response.data
          this.open = true
          this.title = '修改用户'
        })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const adminIds = row.id || this.ids
      this.$confirm('是否确认删除用户编号为"' + adminIds + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(function () {
          return delAdmin(adminIds)
        })
        .then(() => {
          this.getList()
          this.msgSuccess('删除成功')
        })
        .catch(function () {

        })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            this.loading = true;
            updateAdmin(this.form)
              .then(response => {
                if (response.code === 20000) {
                  this.msgSuccess('修改成功');
                  this.loading = false;
                  this.open = false;
                  this.getList();
                }
              });
          } else {
            this.loading = true;
            addAdmin(this.form)
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
