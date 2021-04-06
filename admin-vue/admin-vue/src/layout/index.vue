<template>
  <div :class="classObj" class="app-wrapper">
    <div @click="handleClickOutside" class="drawer-bg" v-if="device==='mobile'&&sidebar.opened"/>
    <div class="sidebar-container">
      <logo/>
      <el-menu
        :collapse="!sidebar.opened"
        @close="handleClose"
        @open="handleOpen"
        active-text-color="#409eff"
        background-color="#304156"
        class=""
        default-active="2"
        router
        text-color="#bfcbd9"

      >
        <li :key="index" v-for="(entity,index) in this.resList">
          <el-submenu :index="index+''">
            <template slot="title">{{entity.name}}</template>
        <li :key="index" v-for="(item,index) in  entity.children">
          <el-menu-item :index="item.url">
            <template slot="title">
              <span>{{item.name}}</span>
            </template>
          </el-menu-item>
        </li>
        </el-submenu>
        </li>
      </el-menu>
    </div>
    <div class="main-container">
      <div :class="{'fixed-header':fixedHeader}">
        <navbar/>
      </div>
      <app-main/>
    </div>
  </div>
</template>

<script>
  import {AppMain, Navbar} from './components';
  import ResizeMixin from './mixin/ResizeHandler';
  import Logo from '@/layout/components/Sidebar/Logo';
  import Cookies from 'js-cookie'

  export default {
    name: 'Layout',
    components: {
      Navbar,
      AppMain,
      Logo
    },
    data() {
      return {
        resList: []
      }
    },
    mixins: [ResizeMixin],
    computed: {
      sidebar() {
        return this.$store.state.app.sidebar;
      },
      device() {
        return this.$store.state.app.device;
      },
      fixedHeader() {
        return this.$store.state.settings.fixedHeader;
      },
      classObj() {
        return {
          hideSidebar: !this.sidebar.opened,
          openSidebar: this.sidebar.opened,
          withoutAnimation: this.sidebar.withoutAnimation,
          mobile: this.device === 'mobile'
        };
      }
    },
    mounted() {
      this.resList = JSON.parse(localStorage.getItem("RESLIST"));
    },
    methods: {
      handleClickOutside() {
        this.$store.dispatch('app/closeSideBar', {withoutAnimation: false});
      },
      handleOpen(key, keyPath) {
      },
      handleClose(key, keyPath) {

      }
    }
  };
</script>

<style lang="scss" scoped>
  @import "~@/styles/mixin.scss";
  @import "~@/styles/variables.scss";


  .app-wrapper {
    @include clearfix;
    position: relative;
    height: 100%;
    width: 100%;

    &.mobile.openSidebar {
      position: fixed;
      top: 0;
    }
  }

  .drawer-bg {
    background: #000;
    opacity: 0.3;
    width: 100%;
    top: 0;
    height: 100%;
    position: absolute;
    z-index: 999;
  }

  .fixed-header {
    position: fixed;
    top: 0;
    right: 0;
    z-index: 9;
    width: calc(100% - #{$sideBarWidth});
    transition: width 0.28s;
  }

  .hideSidebar .fixed-header {
    width: calc(100% - 54px)
  }

  .mobile .fixed-header {
    width: 100%;
  }
</style>
