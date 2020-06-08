<template>
  <div>
    <div class="swiper">
      <van-swipe class="my-swipe" :autoplay="4000" indicator-color="#fff">
        <van-swipe-item
          ><img src="@/assets/carousel/1.jpeg" class="carousel-img"
        /></van-swipe-item>
        <van-swipe-item
          ><img src="@/assets/carousel/2.jpeg" class="carousel-img"
        /></van-swipe-item>
        <van-swipe-item
          ><img src="@/assets/carousel/3.jpeg" class="carousel-img"
        /></van-swipe-item>
        <van-swipe-item
          ><img src="@/assets/carousel/4.jpeg" class="carousel-img"
        /></van-swipe-item>
      </van-swipe>
    </div>
    <van-tabs v-model="active" animated sticky @click="onClick">
      <van-tab
        v-for="(item, index) in tabItems"
        :title="item.title"
        :key="index"
      >
      </van-tab>
    </van-tabs>
    <content-show :theme="active"></content-show>
  </div>
</template>

<script>
import contentShow from "./content";
export default {
  components: { contentShow },
  data() {
    return {
      active: 0,
      tabItems: [
        { title: "劳务信息", id: 1 },
        { title: "供需信息", id: 2 },
        { title: "果农信息", id: 3 },
        { title: "果商信息", id: 4 },
        { title: "专家信息", id: 5 }
      ]
    };
  },
  methods: {
    onClick() {
      if (this.active == 2 || this.active == 3 || this.active == 4) {
        this.$notify("暂未实现");
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.swiper {
  width: 100%;
  height: 7rem;
  .van-swipe {
    width: 100%;
    height: 100%;
  }
  img {
    width: 100%;
    height: 100%;
    pointer-events: none;
  }
  .custom-indicator {
    position: absolute;
    right: 5px;
    bottom: 5px;
    padding: 2px 5px;
    font-size: 12px;
    color: #fff;
    background: #fff;
  }
}
</style>
