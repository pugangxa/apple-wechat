<template>
  <div>
    <van-nav-bar
      title="关于"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
    <div id="about">
      西安丝路昌晟软件服务有限公司
      <p>提供技术支持</p>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    onClickLeft() {
      this.$router.push({ path: "/user/index" });
    }
  }
};
</script>

<style>
#about {
  padding: 40% 0;
}
</style>
