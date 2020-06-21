<template>
  <div>
    <van-cell :title="activeTitle" size="large" />
    <div class="apple-main">
      <router-view />
    </div>
    <van-tabbar v-model="active" active-color="#07c160">
      <van-tabbar-item name="manage" to="/admin/manage/index" icon="edit"
        >用户管理</van-tabbar-item
      >
      <van-tabbar-item name="home" to="/admin/audit/index" icon="home-o"
        >信息审核</van-tabbar-item
      >
      <van-tabbar-item name="my" to="/admin/user/index" icon="user-o"
        >个人中心</van-tabbar-item
      >
    </van-tabbar>
  </div>
</template>

<script>
export default {
  name: "Layout",
  data() {
    return {
      active: "home",
      activeTitle: "信息审核"
    };
  },
  watch: {
    $route() {
      // 对路由变化作出响应
      this.tabbarActive();
    }
  },
  mounted() {
    this.tabbarActive();
  },
  methods: {
    tabbarActive() {
      const href = window.location.href.split("#/")[1].split("/")[1];
      switch (href) {
        case "audit":
          this.activeTitle = "信息动态";
          this.active = "home";
          break;
        case "manage":
          this.activeTitle = "用户管理";
          this.active = "manage";
          break;
        case "user":
          this.activeTitle = "个人中心";
          this.active = "my";
          break;
      }
    }
  }
};
</script>
