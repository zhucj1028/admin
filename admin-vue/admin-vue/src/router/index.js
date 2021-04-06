import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

export const routes = [
  {
    path: '/',
    name: 'login',
    component: () => import(/* webpackChunkName: "login" */ '@/views/login/index'),
  },
  {
    path: '/index',
    name: 'index',
    component: () => import(/* webpackChunkName: "index" */ '@/layout/index'),
    children: [

      {
        path: '/role',
        component: (resolve) => require(['@/views/permission/role/index'], resolve)
      },
      {
        path: '/admin',
        component: (resolve) => require(['@/views/permission/admin/index'], resolve)
      },
      {
        path: '/res',
        component: (resolve) => require(['@/views/permission/res/index'], resolve)
      },

      {
        path: '/log',
        component: (resolve) => require(['@/views/system/log/index'], resolve)
      },
      {
        path: '/logins',
        component: (resolve) => require(['@/views/system/logins/index'], resolve)
      },

    ]
  },
];

const router = new VueRouter({
  mode: 'history',
  routes,
});

export default router;
