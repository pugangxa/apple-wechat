<template>
  <div>
    <div id="title">
      <p>北极镇果业信息发布平台</p>
    </div>
    <div>
      <img
        src="@/assets/fruit.png"
        alt="logo"
        style="margin-bottom: 30px; margin-top: 10px"
      />
    </div>
    <div>
      <van-form @submit="onSubmit">
        <van-field
          v-model="loginForm.userName"
          name="userName"
          label="用户名"
          placeholder="用户名"
          :rules="[{ required: true, message: '请填写用户名' }]"
        />
        <van-field
          v-model="loginForm.password"
          type="password"
          name="password"
          label="密码"
          placeholder="密码"
          :rules="[{ required: true, message: '请填写密码' }]"
        />
        <div style="margin: 16px;">
          <van-button round block type="info" native-type="submit">
            登录
          </van-button>
        </div>
      </van-form>
    </div>
    <div class="text-foot">
      还没有账号?
      <router-link to="/register" class="register">
        注册
      </router-link>
    </div>
  </div>
</template>
<script>
import { Toast } from "vant";
import { mapMutations } from "vuex";
import loginApi from "@/api/login";

export default {
  data() {
    return {
      loginForm: {
        userName: "",
        password: "",
        remember: false
      }
    };
  },
  methods: {
    onSubmit() {
      let _this = this;
      loginApi.login(this.loginForm).then(function(result) {
        if (result && result.code === 1) {
          _this.setUserName(_this.loginForm.userName);
          Toast.success("登录成功");
          _this.$router.push({ path: "/" });
        } else {
          _this.$notify(result.message);
        }
      });
    },
    ...mapMutations(["setUserName"])
  }
};
</script>

<style lang="scss" scoped>
.text-foot {
  text-align: center;
  padding: 10px;
  font-weight: 700;
  margin-top: 20px;
  color: var(--color-semidark);
}
#title {
  p {
    font-weight: bold;
    color: #2c3e50;
  }
  padding: 20px;
}
</style>
