<template>
    <div>
      <el-card class="box-card">
        <div id="main" class="main_container"></div>
      </el-card>
    </div>
</template>

<script>
  import * as echarts from 'echarts'
  import { loginList } from '@/api/admin'
    export default {
      name: "logins",
      data () {
        return {
          loginLists: [],
          list: [],
          myChart: null,
        }
      },
      mounted () {
        this.initCharts()
      },
      created () {
      },
      methods: {
        async getLoginList () {
          const { data } = await loginList()
          this.loginLists = data
          console.log(this.loginLists)
          for (let i = 0; i < this.loginLists.length; i++) {
            console.log(this.loginLists[i])
            this.list.push({ value: this.loginLists[i].num, name: this.loginLists[i].login })
            console.log(this.list)
            this.initList();
          }
        },
        initList(){
          this.myChart.setOption({
            series: [
              {
                name: '登录方式',
                type: 'pie',
                radius: '50%',
                data: this.list,
              }]
          })
        },
        initCharts () {
          // 初始化echarts实例
          this.myChart = echarts.init(document.getElementById('main'))
          // 绘制图表
          this.myChart.setOption({
            title: {
              text: '用户登录方式',
              left: 'center'
            },
            tooltip: {
              trigger: 'item'
            },
            legend: {
              orient: 'vertical',
              left: 'left'
            },
            series: [
              {
                name: '登录方式',
                type: 'pie',
                radius: '50%',
                data: this.list,
                //   [
                //   { value: 5, name: '微信' },
                //   { value: 2, name: 'QQ' },
                //   { value: 3, name: 'GitHub' }
                // ],
                emphasis: {
                  itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                  }
                }
              }
            ]
          })
          this.getLoginList();
        }
      }
    }
</script>

<style scoped>
  .main_container {
    width: 100%;
    height: 390px;
    overflow: hidden;
  }
</style>
