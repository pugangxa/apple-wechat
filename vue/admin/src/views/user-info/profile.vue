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
        <van-field name="switch" label="修改用户类型">
          <template #input>
            <van-switch v-model="userTypeChecked" size="20" />
          </template>
        </van-field>
        <div style="padding-top: 20px">
          <div class="radio-group">
            <van-radio-group
              v-model="radioType"
              direction="horizontal"
              :disabled="userTypeDisabled"
            >
              <van-radio name="1">果农</van-radio>
              <van-radio name="2">果商</van-radio>
            </van-radio-group>
          </div>
        </div>
        <van-divider
          :style="{
            color: '#f0f5f5',
            borderColor: '#f0f5f5',
            borderWidth: '2px'
          }"
        />
        <van-field name="switch" label="修改密码">
          <template #input>
            <van-switch v-model="passwordChecked" size="20" />
          </template>
        </van-field>
        <van-field
          v-model="modifyForm.password"
          type="password"
          name="password"
          label="密码"
          placeholder="密码"
          :disabled="passwordDisabled"
          :required="passwordChecked"
        />
        <van-field
          v-model="againPassword"
          type="password"
          name="againPassword"
          label="确认密码"
          placeholder="请再次输入密码"
          :disabled="passwordDisabled"
          :required="passwordChecked"
          :rules="[
            {
              validator: value => {
                if (value !== this.modifyForm.password) {
                  return false;
                }
              },
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
        password: "",
        userType: 1
      },
      againPassword: "",
      userTypeChecked: false,
      passwordChecked: false
    };
  },
  computed: {
    userTypeDisabled() {
      return !this.userTypeChecked;
    },
    passwordDisabled() {
      return !this.passwordChecked;
    },
    radioType: {
      get() {
        return String(this.modifyForm.userType);
      },
      set(value) {
        this.modifyForm.userType = Number(value);
      }
    }
  },
  methods: {
    onClickLeft() {
      this.$router.push({ path: "/user/index" });
    },
    onSubmit() {
      if (!this.userTypeChecked && !this.passwordChecked) {
        this.$notify("没有任何修改");
        return;
      }
      if (!this.userTypeChecked) {
        this.modifyForm.userType = null;
      }
      if (!this.passwordChecked) {
        this.modifyForm.password = null;
      } else {
        if (
          this.modifyForm.password == "" ||
          this.modifyForm.password == null
        ) {
          this.$notify("密码不能为空");
          return;
        }
      }
      let _this = this;
      updateUserApi.update(this.modifyForm).then(function(result) {
        if (result && result.code === 1) {
          Toast.success("修改成功");
          _this.$router.push({ path: "/user/index" });
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
  .radio-group {
    padding-left: 35px;
    padding-bottom: 15px;
  }
}
</style>
