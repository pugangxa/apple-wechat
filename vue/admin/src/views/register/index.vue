<template>
  <div>
    <div class="lowin-brand">
      <img src="@/assets/logo.png" alt="logo" style="margin-top: 12px" />
    </div>
    <div class="lowin-box-inner">
      <van-form @submit="onSubmit">
        <div class="lowin-title">
          <p>北极镇果业信息发布平台</p>
        </div>
        <van-field
          v-model="userName"
          name="用户名"
          label="用户名"
          placeholder="用户名"
          :rules="[{ required: true, message: '请填写用户名' }]"
        />
        <van-field
          v-model="password"
          type="password"
          name="密码"
          label="密码"
          placeholder="密码"
          :rules="[{ required: true, message: '请填写密码' }]"
        />
        <van-field
          readonly
          clickable
          name="picker"
          :value="value"
          label="用户类型"
          placeholder="点击选择用户类型"
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
export default {
  name: "Register",
  data() {
    return {
      userName: "",
      password: "",
      value: "",
      columns: ["果农", "果商", "技术专家"],
      showPicker: false
    };
  },
  methods: {
    onSubmit(values) {
      console.log(values);
      this.$router.push({ path: "/layout" });
    },
    onConfirm(value) {
      this.value = value;
      this.showPicker = false;
    }
  }
};
</script>
<style lang="scss" scoped>
.lowin-brand {
  overflow: hidden;
  width: 100px;
  height: 100px;
  margin: 10px auto -50px auto;
  border-radius: 50%;
  -webkit-box-shadow: 0 4px 40px rgba(0, 0, 0, 0.07);
  box-shadow: 0 4px 40px rgba(0, 0, 0, 0.07);
  padding: 10px;
  background-color: #fff;
  z-index: 1;
  position: relative;
  img {
    width: 100%;
  }
}
.lowin-box-inner {
  padding: 60px 25px 25px 25px;
  text-align: left;
  border-radius: 3px;
}
.lowin-title {
  text-align: center;
  font-weight: bold;
  font-size: 18px;
  color: #2c3e50;
  padding: 20px;
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
