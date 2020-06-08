<template>
  <div style="padding-top: 10px">
    <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
      <van-list
        v-model="loading"
        :finished="finished"
        finished-text="没有更多了"
        :error.sync="error"
        error-text="请求失败，点击重新加载"
        @load="onLoad"
      >
        <div v-for="item in list" :key="item" style="text-align: left">
          <van-row>
            <van-col span="4" offset="1"
              ><van-icon name="smile-comment" size="38" color="#e6b3ff"
            /></van-col>
            <van-col span="15"
              ><van-row>姓名</van-row
              ><van-row
                ><span style="font-size: 12px; color: #8585ad"
                  >一天前</span
                ></van-row
              ></van-col
            >
          </van-row>
          <div
            style="margin: auto 12px; word-wrap: break-word; word-break: break-all;"
          >
            <p style="margin: 3px auto;">
              招工信息 人数 日薪
            </p>
            abcdaaaaaacccccccfdsagsdgasdgasdggbads dsgasdahbb dgasdhbgbdsb
            gasdghsdhbs gasdghahg招工信息abcdaaaaaacccccccfdsagsdgasdgasdggbads
            dsgasdahbb dgasdhbgbdsb gasdghsdhbs
            <p style="padding-top: 3px; margin: 3px auto; font-size: 13px;">
              地址
            </p>
            <p style="margin: 3px auto; font-size: 13px;">
              电话
            </p>
          </div>

          <van-divider
            :style="{
              color: '#f0f5f5',
              borderColor: '#f0f5f5',
              borderWidth: '5px'
            }"
          />
        </div>
      </van-list>
    </van-pull-refresh>
  </div>
</template>

<script>
export default {
  name: "contentShow",
  props: { theme: Number },
  data() {
    return {
      list: [],
      loading: false,
      finished: false,
      refreshing: false,
      error: false
    };
  },
  methods: {
    onLoad() {
      setTimeout(() => {
        if (this.refreshing) {
          this.list = [];
          this.refreshing = false;
        }

        for (let i = 0; i < 10; i++) {
          this.list.push(this.list.length + 1);
        }
        this.loading = false;

        if (this.list.length >= 40) {
          this.finished = true;
        }
      }, 1000);
    },
    onRefresh() {
      // 清空列表数据
      this.finished = false;

      // 重新加载数据
      // 将 loading 设置为 true，表示处于加载状态
      this.loading = true;
      this.onLoad();
    }
  }
};
</script>
