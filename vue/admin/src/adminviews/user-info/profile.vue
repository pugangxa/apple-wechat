<template>
  <div>
    <van-nav-bar
      title="个人资料修改"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
    <div class="form">
      <van-form @submit="onSubmit">
        <van-field
          v-model="modifyForm.password"
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
                if (value !== this.modifyForm.password) {
                  return false;
                }
              },
              required: true,
              message: '两次输入密码不一致'
            }
          ]"
        />

        <div style="margin: 16px;">
          <van-button round block type="info" native-type="submit">
            提交修改
          </van-button>
        </div>
      </van-form>
    </div>
  </div>
</template>

<script>
import updateUserApi from "@/api/register";
import { Toast } from "vant";

export default {
  data() {
    return {
      modifyForm: {
        password: ""
      },
      againPassword: ""
    };
  },
  methods: {
    onClickLeft() {
      this.$router.push({ path: "/admin/user/index" });
    },
    onSubmit() {
      let _this = this;
      updateUserApi.update(this.modifyForm).then(function(result) {
        if (result && result.code === 1) {
          Toast.success("修改成功");
          _this.$router.push({ path: "/admin/user/index" });
        } else {
          _this.$notify({
            message: result.message
          });
        }
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.form {
  padding-top: 16px;
}
</style>
