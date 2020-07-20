<template>
  <div>
    <div id="search">
      <van-sticky>
        <van-form>
          <van-field
            v-model="queryParam.userName"
            size="large"
            name="username"
            label="用户名: "
            placeholder="输入用户名查找"
            clearable
          >
            <template #button>
              <van-button size="small" type="primary" @click="submitForm"
                >查询</van-button
              >
            </template>
          </van-field>
        </van-form>
      </van-sticky>
    </div>
    <van-pull-refresh v-model="loading" @refresh="onRefresh">
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
            <van-col span="15" offset="1">
              <van-row> 用户名： {{ item.userName }}</van-row>
              <van-row> 用户类型: {{ item.userType | toUserType }} </van-row
              ><van-row
                >创建时间:
                {{ item.createTime | toDate }}
              </van-row></van-col
            >
            <van-col span="4" offset="1">
              <div id="operation">
                <van-dropdown-menu
                  :overlay="true"
                  :close-on-click-overlay="true"
                  :close-on-click-outside="true"
                >
                  <van-dropdown-item
                    title="操作"
                    ref="opItem"
                    @closed="onOpClosed"
                  >
                    <van-switch-cell
                      v-model="operation.resetPassword"
                      title="重置密码"
                    />
                    <van-switch-cell
                      v-model="operation.deleteUser"
                      title="删除用户"
                    />
                    <van-button
                      block
                      type="info"
                      @click="onOpConfirm(item, index)"
                      >确认</van-button
                    >
                  </van-dropdown-item>
                </van-dropdown-menu>
              </div>
            </van-col>
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
import { Toast } from "vant";

export default {
  data() {
    return {
      queryParam: {
        userName: null,
        role: 1,
        pageIndex: 1,
        pageSize: 10
      },
      operation: {
        deleteUser: false,
        resetPassword: false
      },

      list: [],
      loading: false,
      finished: false,
      error: false
    };
  },
  created() {
    this.onLoad();
  },
  methods: {
    onOpClosed() {
      this.operation.resetPassword = false;
      this.operation.deleteUser = false;
    },
    onOpConfirm(item, index) {
      if (this.operation.resetPassword && this.operation.deleteUser) {
        this.$notify("一次只能选择一项");
        return;
      }
      if (this.operation.resetPassword) {
        adminAPI
          .resetUserPassword(item.id)
          .then(() => Toast.success("重置成功"))
          .catch(e => {
            this.$notify(e.message);
          });
      } else if (this.operation.deleteUser) {
        adminAPI
          .deleteUser(item.id)
          .then(() => {
            Toast.success("删除成功");
            this.list.splice(index, 1);
          })
          .catch(e => {
            this.$notify(e.message);
          });
      }
      this.operation.resetPassword = false;
      this.operation.deleteUser = false;
      this.$refs.opItem[index].toggle();
      return;
    },
    onLoad() {
      this.loading = true;
      adminAPI
        .pagelistUser(this.queryParam)
        .then(data => {
          const re = data.response;
          this.list = re.list;
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
    submitForm() {
      this.queryParam.pageIndex = 1;
      this.onLoad();
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
    }
  },
  filters: {
    toUserType(value) {
      switch (value) {
        case 1:
          return "果农";
        case 2:
          return "果商";
        case 3:
          return "技术专家";
      }
    },
    toDate(value) {
      let date = new Date(value);
      let y = date.getFullYear();
      let m = date.getMonth() + 1;
      m = m < 10 ? "0" + m : m;
      let d = date.getDate();
      d = d < 10 ? "0" + d : d;
      return y + "-" + m + "-" + d;
    }
  }
};
</script>

<style lang="scss" scoped>
/deep/ #search {
  padding: 2px;
  padding-bottom: 15px;
  .van-cell {
    background-color: #f2f2f2;
  }
}
/deep/ #operation {
  .van-dropdown-menu__bar {
    background-color: hsl(0, 3%, 79%);
  }
}
</style>
