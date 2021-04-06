<template>
  <div class="login-container">
    <el-form :model="loginForm" :rules="loginRules" auto-complete="on" class="login-form"
             label-position="left"
             ref="loginForm">

      <div class="title-container">
        <h3 class="title">登录</h3>
      </div>

      <el-form-item prop="username">
        <el-input
          auto-complete="on"
          ref="username"
          tabindex="1"
          type="text"
          v-model="loginForm.name"
        />
      </el-form-item>

      <el-form-item prop="password">
        <el-input
          :key="passwordType"
          :type="passwordType"
          @keyup.enter.native="handleLogin"
          auto-complete="on"
          ref="password"
          tabindex="2"
          v-model="loginForm.password"
        />
      </el-form-item>

      <el-button :loading="loading" @click.native.prevent="handleLogin" style="width:100%;margin-bottom:30px;"
                 type="primary">Login
      </el-button>


    </el-form>
  </div>
</template>

<script>
  // es6解构变量

  import {login} from "@/api/admin";
  import Cookies from 'js-cookie'


  export default {
    name: 'Login',
    data() {
      return {
        loginForm: {
          name: '',
          password: ''
        },
        loading: false,
        passwordType: 'password',
        redirect: undefined,
        // 表单校验
        loginRules: {
          username: [{required: true, message: '名称不能为空', trigger: 'blur'}],
          password: [{required: true, message: '密码不能为空', trigger: 'blur'}]
        },
      };
    },
    methods: {
      //  处理登录方法
      handleLogin() {
        this.loading = true;
        login(this.loginForm).then(res => {
          if (res.code = 20000) {
            Cookies.set("ROLE", res.data)
            localStorage.setItem("RESLIST", JSON.stringify(this.handleTree(res.resList)));
            this.$router.push({path: '/index'});
            this.loading = false;
          } else {
            this.msgError(res.msg)
          }
        });

      }
    }
  };
</script>

<style lang="scss">
  $bg: #283443;
  $light_gray: #fff;
  $cursor: #fff;

  @supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
    .login-container .el-input input {
      color: $cursor;
    }
  }

  /* reset element-ui css */
  .login-container {
    .el-input {
      display: inline-block;
      height: 47px;
      width: 85%;

      input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 5px 12px 15px;
        color: $light_gray;
        height: 47px;
        caret-color: $cursor;

        &:-webkit-autofill {
          box-shadow: 0 0 0px 1000px $bg inset !important;
          -webkit-text-fill-color: $cursor !important;
        }
      }
    }

    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }
  }
</style>

<style lang="scss" scoped>
  $bg: #2d3a4b;
  $dark_gray: #889aa4;
  $light_gray: #eee;

  .login-container {
    min-height: 100%;
    width: 100%;
    background-color: $bg;
    overflow: hidden;

    .login-form {
      position: relative;
      width: 520px;
      max-width: 100%;
      padding: 160px 35px 0;
      margin: 0 auto;
      overflow: hidden;
    }

    .tips {
      font-size: 14px;
      color: #fff;
      margin-bottom: 10px;

      span {
        &:first-of-type {
          margin-right: 16px;
        }
      }
    }

    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
    }

    .title-container {
      position: relative;

      .title {
        font-size: 26px;
        color: $light_gray;
        margin: 0px auto 40px auto;
        text-align: center;
        font-weight: bold;
      }
    }

    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select: none;
    }
  }
</style>
