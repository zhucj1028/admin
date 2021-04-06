<template>
  <div>
    <template>
      <el-card>
        <el-table
          ref="multipleTable"
          :data="logLists"
          height="500"
          tooltip-effect="dark"
          style="width: 100%">
          <el-table-column
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            prop="id"
            label="ID"
            width="120">
          </el-table-column>
          <el-table-column
            prop="createTime"
            label="日期"
            width="180">
          </el-table-column>
          <el-table-column
            prop="name"
            label="姓名"
            width="180">
          </el-table-column>
          <el-table-column
            prop="operation"
            label="操作记录">
          </el-table-column>
        </el-table>
      </el-card>
    </template>
  </div>
</template>

<script>
  import { logList } from '@/api/admin'
  export default {
    name: "log",
    data() {
      return {
        tableData: [{
          id: 101,
          createTime: '2016-05-03',
          name: '王小虎',
          operation: '上海市普陀区金沙江路 1518 弄'
        }, {
          id: 102,
          createTime: '2016-05-02',
          name: '王小虎',
          operation: '上海市普陀区金沙江路 1518 弄'
        }, {
          id: 103,
          createTime: '2016-05-04',
          name: '王小虎',
          operation: '上海市普陀区金沙江路 1518 弄'
        }, {
          id: 104,
          createTime: '2016-05-01',
          name: '王小虎',
          operation: '上海市普陀区金沙江路 1518 弄'
        }, {
          id: 105,
          createTime: '2016-05-08',
          name: '王小虎',
          operation: '上海市普陀区金沙江路 1518 弄'
        }, {
          id: 106,
          createTime: '2016-05-06',
          name: '王小虎',
          operation: '上海市普陀区金沙江路 1518 弄'
        }, {
          id: 107,
          createTime: '2016-05-07',
          name: '王小虎',
          operation: '上海市普陀区金沙江路 1518 弄'
        }],
        logLists: []
      }
    },
    created () {
      this.getLogs()
    },
    methods: {
      async getLogs() {
        const {data} = await logList()
        this.logLists = data
        console.log(this.logLists)
      }
    }
  }
</script>

<style scoped>

</style>
