<template>
  <div>
    <div>
      <img
        src="@/assets/fruitBasket.png"
        alt="logo"
        style="margin-bottom: 5px;  margin-top: 30px; height: 100px;"
      />
    </div>
    <div class="lowin-box-inner">
      <van-form @submit="onSubmit">
        <div class="lowin-title">
          <p>北极镇果业信息发布平台</p>
        </div>
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
        <van-field
          v-model="againPassword"
          type="password"
          name="againPassword"
          label="确认密码"
          placeholder="请再次输入密码"
          :rules="[
            {
              validator: value => {
                if (value !== this.loginForm.password) {
                  return false;
                }
              },
              required: true,
              message: '两次输入密码不一致'
            }
          ]"
        />
        <van-field
          readonly
          clickable
          name="userType"
          :value="value"
          label="用户类型"
          placeholder="点击选择用户类型"
          :rules="[{ required: true, message: '请选择用户类型' }]"
          @click="showPicker = true"
        />
        <van-popup v-model="showPicker" position="bottom">
          <van-picker
            show-toolbar
            :columns="columns"
            @confirm="onConfirm"
            @cancel="showPicker = false"
          />
        </van-popup>
        <div style="margin: 16px;">
          <van-button round block type="info" native-type="submit">
            注册
          </van-button>
        </div>
        <div class="text-foot">
          已有账号?
          <router-link to="/login" class="login-link">
            登录
          </router-link>
        </div>
      </van-form>
    </div>
  </div>
</template>

<script>
import { Toast } from "vant";
import registerApi from "@/api/register";

export default {
  name: "Register",
  data() {
    return {
      loginForm: {
        userName: "",
        password: "",
        userType: 1
      },
      againPassword: "",
      value: "",
      columns: ["果农", "果商", "技术专家"],
      showPicker: false
    };
  },
  methods: {
    onSubmit() {
      let _this = this;
      registerApi.register(this.loginForm).then(function(result) {
        if (result && result.code === 1) {
          Toast.success("注册成功");
          _this.$router.push({ path: "/login" });
        } else {
          _this.$notify({
            message: result.message
          });
        }
      });
    },
    onConfirm(value) {
      switch (value) {
        case "果农":
          this.loginForm.userType = 1;
          this.value = "果农";
          break;
        case "果商":
          this.loginForm.userType = 2;
          this.value = "果商";
          break;
        case "技术专家":
          this.loginForm.userType = 3;
          this.value = "技术专家";
          break;
        default:
          //默认是果农
          this.loginForm.userType = 1;
          this.value = "果农";
      }
      this.showPicker = false;
    }
  }
};
</script>
<style lang="scss" scoped>
.lowin-box-inner {
  padding: 1px 25px 25px 25px;
  text-align: left;
}
.lowin-title {
  text-align: center;
  font-weight: bold;
  font-size: 18px;
  color: #2c3e50;
  padding: 10px;
}
.text-foot {
  text-align: center;
  padding: 10px;
  font-weight: 300;
  font-size: 14px;
  margin-top: 20px;
  color: var(--color-semidark);
}
</style>
