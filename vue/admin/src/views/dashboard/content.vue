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
        <div v-for="item in list" :key="item.id" style="text-align: left">
          <van-row>
            <van-col span="4" offset="1"
              ><van-icon name="smile-comment" size="38" color="#e6b3ff"
            /></van-col>
            <van-col span="15"
              ><van-row>{{ item.createUserName }}</van-row
              ><van-row
                ><span style="font-size: 12px; color: #8585ad">{{
                  item.createTime
                }}</span></van-row
              ></van-col
            >
          </van-row>
          <div
            style="margin: auto 12px; word-wrap: break-word; word-break: break-all;"
          >
            <p style="margin: 3px auto;">
              {{ item.type }} 人数: {{ item.number }} 日薪: {{ item.pay }}元
            </p>
            <van-divider
              dashed
              :style="{
                color: '#1989fa',
                borderColor: '#1989fa',
                padding: '0 16px'
              }"
            ></van-divider>
            {{ item.description }}
            <p style="padding-top: 3px; margin: 3px auto; font-size: 13px;">
              联系方式 {{ item.phone }}
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
import listAPI from "@/api/list";
import { formatTime } from "@/utils";

export default {
  name: "contentShow",
  props: { theme: Number },
  data() {
    return {
      queryParam: {
        pageIndex: 1,
        pageSize: 10
      },
      list: [],
      loading: false,
      finished: false,
      refreshing: false,
      error: false,
      pageIndex: 1,
      pageSize: 10
    };
  },
  watch: {
    theme() {
      // 重新加载数据
      this.list = [];
      this.queryParam.pageIndex = 1;
      if (
        this.theme == 1 ||
        this.theme == 2 ||
        this.theme == 3 ||
        this.theme == 4
      ) {
        this.finished = true;
      } else {
        this.onLoad();
      }
    }
  },
  methods: {
    onLoad() {
      this.loading = true;
      listAPI
        .pagelistLabor(this.queryParam)
        .then(data => {
          const re = data.response;
          this.transferList(re.list);
          this.queryParam.pageIndex = re.pageNum + 1;
          this.loading = false;
          if (re.isLastPage) {
            this.finished = true;
          }
        })
        .catch(() => {
          this.error = true;
          this.loading = false;
        });
    },
    onRefresh() {
      // 目前暴力清空列表数据
      this.list = [];
      this.finished = false;

      // 重新加载数据
      this.queryParam.pageIndex = 1;

      // 将 loading 设置为 true，表示处于加载状态
      this.loading = true;
      this.onLoad();
    },
    transferList(retArray) {
      for (let i = 0, len = retArray.length; i < len; i++) {
        if (this.theme == 0) {
          let type = "";
          switch (retArray[i].type) {
            case 1:
              type = "招工需求";
              break;
            case 2:
              type = "工作需求";
              break;
          }
          let createTime = formatTime(retArray[i].createTime);
          this.list.push({
            id: retArray[i].id,
            type: type,
            number: retArray[i].number,
            pay: retArray[i].pay,
            phone: retArray[i].phone,
            createTime: createTime,
            createUserName: retArray[i].createUserName,
            description: retArray[i].description
          });
        }
      }
    }
  }
};
</script>
