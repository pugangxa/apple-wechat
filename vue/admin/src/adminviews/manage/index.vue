<template>
  <div>
    <van-form>
      <van-field
        v-model="username"
        size="large"
        name="username"
        label="用户名"
        placeholder="请输入"
        clearable
      >
        <template #button>
          <van-button size="small" type="primary" @click="submitForm"
            >查询</van-button
          >
        </template>
      </van-field>
    </van-form>

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
            <van-col span="4" offset="1">name</van-col>
            <van-col span="11">operation</van-col>
          </van-row>
          <van-row>
            用户类型/创建时间
            <van-icon
              name="cross"
              size="20"
              color="red"
              @click="onDelete(item, index)"
            />
            <van-dialog
              v-model="audit.showDelete"
              show-cancel-button
              title="确认删除?"
              :before-close="beforeDelete"
            >
            </van-dialog>
          </van-row>
          <van-divider
            :style="{
              color: '#f0f5f5',
              borderColor: '#f0f5f5',
              borderWidth: '2px'
            }"
          />
        </div>
      </van-list>
    </van-pull-refresh>
  </div>
</template>

<script>
import adminAPI from "@/api/admin";

export default {
  data() {
    return {
      queryParam: {
        userName: "",
        role: 3,
        pageIndex: 1,
        pageSize: 10
      },
      audit: {
        showDelete: false,
        showResetPassword: false
      },

      list: [],
      loading: false,
      finished: false,
      refreshing: false,
      error: false
    };
  },
  created() {
    this.onLoad();
  },
  methods: {
    onLoad() {
      this.loading = true;
      adminAPI.pagelistUser
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
          //listAPI.confirm(item);
          console.log(item);
          item.buttonDisabled = true;
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
          //listAPI.delete(item);
          console.log(item);
          _this.list.splice(index, 1);
        }
        done();
      };
    },
    beforeDelete() {},
    submitForm() {
      this.queryParam.pageIndex = 1;
      this.search();
    }
  }
};
</script>
