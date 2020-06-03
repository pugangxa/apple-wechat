<template>
  <div>
    <van-cell :title="value" size="large" />
    <div class="apple-main">
      <router-view />
    </div>
    <van-tabbar v-model="active">
      <van-tabbar-item name="home" icon="home-o">{{ value }}</van-tabbar-item>
      <van-tabbar-item name="search" icon="search">标签</van-tabbar-item>
      <van-tabbar-item name="friends" icon="friends-o">标签</van-tabbar-item>
      <van-tabbar-item name="setting" icon="setting-o">标签</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
export default {
  name: "Layout",
  data() {
    return {
      active: "home",
      value: "我的"
    };
  },
  methods: {}
};
</script>
