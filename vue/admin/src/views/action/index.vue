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
    <div class="middle">
      <van-grid :gutter="10" :column-num="3" :border="false" :icon-size="50">
        <van-grid-item
          icon="friends"
          text="劳务信息"
          replace
          to="/action/labor"
        />
        <van-grid-item
          icon="shopping-cart"
          text="供需信息"
          replace
          to="/action/supply"
        />
        <van-grid-item
          icon="shop"
          text="果商信息"
          replace
          to="/action/merchant"
        />
      </van-grid>
      <van-grid :gutter="10" :column-num="2" :border="false" :icon-size="45">
        <van-grid-item
          icon="manager"
          text="果农信息"
          replace
          to="/action/farmer"
        />
        <van-grid-item
          icon="good-job"
          text="专家信息"
          replace
          to="/action/expert"
        />
      </van-grid>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.middle {
  width: 300px;
  height: 200px;
  margin: auto;
  position: absolute;
  left: 50%;
  top: 50%;
  margin-left: -150px;
  margin-top: -60px;
  color: #a00728;
}
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
