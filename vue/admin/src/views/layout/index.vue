<template>
  <div>
    <van-cell :title="activeTitle" size="large" />
    <div class="apple-main">
      <router-view />
    </div>
    <van-tabbar v-model="active" active-color="#07c160">
      <van-tabbar-item name="publish" replace to="/action/index" icon="edit"
        >我要发布</van-tabbar-item
      >
      <van-tabbar-item name="home" replace to="/dashboard/index" icon="home-o"
        >信息动态</van-tabbar-item
      >
      <van-tabbar-item name="my" replace to="/user/index" icon="user-o"
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
      activeTitle: "信息动态"
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
      const href = window.location.href.split("#/")[1].split("/")[0];
      switch (href) {
        case "dashboard":
          this.activeTitle = "信息动态";
          this.active = "home";
          break;
        case "action":
          this.activeTitle = "我要发布";
          this.active = "publish";
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
