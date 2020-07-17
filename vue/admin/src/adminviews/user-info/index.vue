<template>
  <div>
    <div style="padding-top: 20px">
      <div><van-icon name="user-o" /> {{ userName }}</div>
    </div>
    <van-divider
      :style="{
        color: '#f0f5f5',
        borderColor: '#f0f5f5',
        borderWidth: '2px'
      }"
    />
    <div>
      <van-cell
        value="修改密码"
        is-link
        to="/admin/user/profile"
        icon="user-circle-o"
        size="large"
      />
      <van-cell
        value="关于"
        is-link
        to="/admin/user/about"
        icon="info-o"
        size="large"
      />
    </div>
    <van-divider
      :style="{
        color: '#f0f5f5',
        borderColor: '#f0f5f5',
        borderWidth: '2px'
      }"
    />
    <div>
      <van-cell value="退出登录" @click="onLogout" icon="close" size="large" />
    </div>
  </div>
</template>

<script>
import loginApi from "@/api/login";
import { mapMutations, mapState } from "vuex";

export default {
  methods: {
    onLogout() {
      let _this = this;
      loginApi.logout().then(function(result) {
        if (result && result.code === 1) {
          _this.clearLogin();
          _this.$router.push({ path: "/admin/login" });
        }
      });
    },

    ...mapMutations(["clearLogin"])
  },
  computed: {
    ...mapState({
      userName: state => state.userName
    })
  }
};
</script>
