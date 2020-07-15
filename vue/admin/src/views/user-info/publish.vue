<template>
  <div>
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
        this.$notify("实现中");
      }
    }
  }
};
</script>
