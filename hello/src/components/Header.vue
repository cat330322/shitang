<template>
  <div>
      <h1>欢迎进入首页</h1>
      <h2> <span v-show="isLogin">欢迎:{{admin.username}}</span></h2>
      <router-link v-show="!isLogin" :to="{name:'Index'}">退出登录</router-link>
      <router-link v-show="!isLogin" :to="{name:'Login'}">立即登录</router-link>
    <br>
      <a href="#/index/caigous">采购清单表</a>
       <a href="#/index/caigous02">采购清单表02</a>
      <a href="#/index/thedays">今日菜单</a>
      <a href="#/index/emps">员工管理</a>
      <a href="#/index/abouts">关于网站</a>
    <a href="#/index/vxe-tables">vxe</a>
  </div>
</template>

<script>
  import instance from "../utils/request";
    export default {
        name: "Header",
        data(){
            return{
                admin:{}
            }
        },
        methods:{
            logout(){
                let token = localStorage.getItem("token");
                instance.delete("/token?token="+token).then(res=>{
                    /*this.$router.push({name:'/'});//切换都主页*/
                    this.admin = {};
                });
            }
        },
        computed:{
            isLogin(){
                return this.admin.username;
            }
        },
        created() {
            //从localStorage获取token
            let token = localStorage.getItem("token");
            //发送axios请求，根据token获取用户信息
            instance.get("/token?token="+token).then(res=>{this.admin = res.data;});
        }
    }

</script>

<style scoped>

</style>
