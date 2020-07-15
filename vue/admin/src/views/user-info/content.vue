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
        <div
          v-for="(item, index) in list"
          :key="item.id"
          style="text-align: left"
        >
          <van-row>
            <van-col span="4" offset="1"
              ><van-icon name="smile-comment" size="38" color="#e6b3ff"
            /></van-col>
            <van-col span="11"
              ><van-row>{{ item.createUserName }}</van-row
              ><van-row
                ><span style="font-size: 12px; color: #8585ad">{{
                  item.createTime
                }}</span></van-row
              ></van-col
            >
            <van-col span="3"
              ><van-tag :type="item.status | toTagType">{{
                item.status | toTagTitle
              }}</van-tag>
            </van-col>
            <van-col span="3" offset="2"
              ><van-icon
                name="cross"
                size="20"
                color="red"
                @click="onDelete(item, index)"/>
              <van-dialog
                v-model="audit.showDelete"
                show-cancel-button
                title="确认删除?"
                :before-close="beforeDelete"
              >
              </van-dialog
            ></van-col>
          </van-row>
          <div
            style="margin: auto 12px; word-wrap: break-word; word-break: break-all;"
          >
            <p style="margin: 3px auto;">
              <span style="font-size: 14px;" v-if="theme == 0">
                <span v-if="item.type == 1">招工需求</span>
                <span v-else>工作需求</span> 人数: {{ item.number }} 日薪:
                {{ item.pay }}元
              </span>
              <span style="font-size: 14px;" v-if="theme == 1">
                <span v-if="item.type == 1">收购需求</span>
                <span v-else>销售需求</span> 数量: {{ item.quantity }} 价格:
                {{ item.price }}元
              </span>
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
            <p style="padding-top: 3px; margin: 3px auto; font-size: 12px;">
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
import deleteAPI from "@/api/publish";
import { formatTime } from "@/utils";

export default {
  name: "contentShow",
  props: { theme: Number },
  data() {
    return {
      queryParam: {
        pageIndex: 1,
        pageSize: 10,
        createUserName: "myself"
      },
      audit: {
        showDelete: false,
        showAudit: false
      },

      list: [],
      loading: false,
      finished: false,
      refreshing: false,
      error: false
    };
  },
  watch: {
    theme() {
      // 重新加载数据
      this.list = [];
      this.queryParam.pageIndex = 1;
      if (this.theme == 2 || this.theme == 3 || this.theme == 4) {
        this.finished = true;
      } else {
        this.onRefresh();
      }
    }
  },
  filters: {
    toTagType(value) {
      let status = { blocked: 1, audited: 2 };
      if (value == status.audited) {
        return "success";
      } else {
        return "danger";
      }
    },
    toTagTitle(value) {
      let status = { blocked: 1, audited: 2 };
      if (value == status.audited) {
        return "已发布";
      } else {
        return "待审核";
      }
    }
  },
  methods: {
    onLoad() {
      this.loading = true;
      let request;
      if (this.theme == 0) {
        request = listAPI.pagelistLabor(this.queryParam);
      } else if (this.theme == 1) {
        request = listAPI.pagelistSupply(this.queryParam);
      }
      request
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
    onDelete(item, index) {
      let _this = this;
      _this.audit.showDelete = true;
      _this.beforeDelete = function(action, done) {
        if (action === "confirm") {
          let request;
          if (_this.theme == 0) {
            request = deleteAPI.deleteLabor(item.id);
          } else if (_this.theme == 1) {
            request = deleteAPI.deleteSupply(item.id);
          }
          request
            .then(() => _this.list.splice(index, 1))
            .catch(e => {
              _this.$notify(e.message);
            });
        }
        done();
      };
    },
    beforeDelete() {},
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
        let createTime = formatTime(retArray[i].createTime);
        this.list.push({
          id: retArray[i].id,
          type: retArray[i].type,
          number: retArray[i].number,
          quantity: retArray[i].quantity,
          pay: retArray[i].pay,
          price: retArray[i].price,
          phone: retArray[i].phone,
          status: retArray[i].status,
          createTime: createTime,
          createUserName: retArray[i].createUserName,
          description: retArray[i].description
        });
      }
    }
  }
};
</script>
