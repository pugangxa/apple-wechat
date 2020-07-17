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
              ><van-button
                type="primary"
                size="mini"
                :disabled="item.buttonDisabled"
                @click="onAudit(item)"
                >{{ item.buttonDisabled | toAuditTitle }}</van-button
              >
              <van-dialog
                v-model="audit.showAudit"
                show-cancel-button
                title="通过审核?"
                :before-close="beforeAudit"
              >
              </van-dialog
            ></van-col>
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
            <div style="margin: 3px auto;">
              <span style="font-size: 14px;" v-if="theme == 0">
                <span v-if="item.type == 1">招工需求</span>
                <span v-else>工作需求</span> 人数: {{ item.number }} 日薪(元):
                {{ item.pay }}
              </span>
              <span style="font-size: 14px;" v-if="theme == 1">
                <span v-if="item.type == 1">收购需求</span>
                <span v-else>销售需求</span> 数量(斤):
                {{ item.quantity }} 价格(元):
                {{ item.price }}
              </span>
              <div style="font-size: 14px;" v-if="theme == 2">
                <span>果农</span>姓名: {{ item.name }} 收购品种:
                {{ item.category }} 收购数量: {{ item.quantity }}
                <div>地址: {{ item.location }}</div>
              </div>
              <div style="font-size: 14px;" v-if="theme == 3">
                <span>果商</span>姓名: {{ item.name }} 收购品种:
                {{ item.category }} 收购数量: {{ item.quantity }}
                <div>地址: {{ item.location }}</div>
              </div>
            </div>
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
import adminAPI from "@/api/admin";
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
      this.onRefresh();
    }
  },
  filters: {
    toAuditTitle(buttonDisabled) {
      if (buttonDisabled) {
        return "已通过";
      } else {
        return "审核";
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
      } else if (this.theme == 2) {
        request = listAPI.pagelistFarmer(this.queryParam);
      } else if (this.theme == 3) {
        request = listAPI.pagelistMerchant(this.queryParam);
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
    onAudit(item) {
      let _this = this;
      _this.audit.showAudit = true;
      _this.beforeAudit = function(action, done) {
        if (action === "confirm") {
          let request;
          if (_this.theme == 0) {
            request = adminAPI.auditLabor(item.id);
          } else if (_this.theme == 1) {
            request = adminAPI.auditSupply(item.id);
          } else if (_this.theme == 2) {
            request = adminAPI.auditFarmer(item.id);
          } else if (_this.theme == 3) {
            request = adminAPI.auditMerchant(item.id);
          }
          request
            .then(() => {
              item.buttonDisabled = true;
            })
            .catch(e => {
              _this.$notify(e.message);
            });
        }
        done();
      };
    },
    beforeAudit() {},
    onDelete(item, index) {
      let _this = this;
      _this.audit.showDelete = true;
      _this.beforeDelete = function(action, done) {
        if (action === "confirm") {
          let request;
          if (_this.theme == 0) {
            request = adminAPI.deleteLabor(item.id);
          } else if (_this.theme == 1) {
            request = adminAPI.deleteSupply(item.id);
          } else if (_this.theme == 2) {
            request = adminAPI.deleteFarmer(item.id);
          } else if (_this.theme == 3) {
            request = adminAPI.deleteMerchant(item.id);
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
      let status = { blocked: 1, audited: 2 };
      for (let i = 0, len = retArray.length; i < len; i++) {
        let createTime = formatTime(retArray[i].createTime);
        let buttonDisabled = false;
        if (retArray[i].status == status.audited) {
          buttonDisabled = true;
        }
        this.list.push({
          id: retArray[i].id,
          type: retArray[i].type,
          name: retArray[i].name,
          number: retArray[i].number,
          location: retArray[i].location,
          category: retArray[i].category,
          quantity: retArray[i].quantity,
          pay: retArray[i].pay,
          price: retArray[i].price,
          phone: retArray[i].phone,
          createTime: createTime,
          createUserName: retArray[i].createUserName,
          description: retArray[i].description,
          buttonDisabled: buttonDisabled
        });
      }
    }
  }
};
</script>
