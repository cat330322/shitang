<template>
<div>
  <Header></Header>
  <!--员工组件 部门组件 用户组件 订单组件-->
  <router-view></router-view>
</div>
</template>

<script>
    import instance from "../utils/request";
    import Header from "../components/Header";

    export default {
        name: "Index",
        components: {Header},
    }
</script>

<style scoped>

</style>
