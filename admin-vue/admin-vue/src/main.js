import Vue from 'vue';
import 'normalize.css/normalize.css';
import App from './App.vue';
import router from './router';
import store from './store';
import './plugins/element';
import '@/styles/index.scss';


import { parseTime, resetForm, addDateRange, selectDictLabel, download, handleTree } from '@/utils/common'
import Pagination from '@/components/Pagination'
// 全局方法挂载
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.download = download
Vue.prototype.handleTree = handleTree

Vue.prototype.msgSuccess = function (msg) {
  this.$message({ showClose: true, message: msg, type: "success" });
}

Vue.prototype.msgError = function (msg) {
  this.$message({ showClose: true, message: msg, type: "error" });
}

Vue.prototype.msgInfo = function (msg) {
  this.$message.info(msg);
}

// 全局组件挂载
Vue.component('Pagination', Pagination)



new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
