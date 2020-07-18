<template>
  <div>
    <van-nav-bar
      title="技术专家信息发布"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
    <div class="form">
      <van-form @submit="onSubmit">
        <van-field
          v-model="expert.name"
          label="专家姓名"
          placeholder="姓名"
          :rules="[{ required: true, message: '请填写姓名' }]"
        />
        <van-field
          v-model="expert.expert"
          label="技术专长"
          placeholder="技术专长"
          :rules="[{ required: true, message: '请填写技术专长' }]"
        />
        <van-field
          v-model="expert.email"
          label="邮箱"
          placeholder="邮箱"
          :rules="[
            {
              validator: validateEmail,
              required: true,
              message: '请填写正确邮箱信息'
            }
          ]"
        />
        <van-field
          v-model="expert.phone"
          type="tel"
          label="电话"
          placeholder="手机号"
          :rules="[
            {
              validator: value => /^(1\d{10})$/.test(value),
              required: true,
              message: '请填写正确电话号码'
            }
          ]"
        />
        <div style="margin: 16px;">
          <van-button round block type="info" native-type="submit">
            发布
          </van-button>
        </div>
      </van-form>
    </div>
  </div>
</template>

<script>
import publishAPI from "@/api/publish";

export default {
  data() {
    return {
      expert: {
        name: "",
        expert: "",
        phone: "",
        email: ""
      }
    };
  },
  methods: {
    validateEmail(value) {
      // eslint-disable-next-line no-useless-escape
      const reg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return reg.test(value);
    },
    onClickLeft() {
      this.$router.push({ path: "/action/index" });
    },
    onSubmit() {
      publishAPI.publishExpert(this.expert).then(re => {
        if (re.code === 1) {
          this.$notify({
            type: "success",
            message: "发布成功",
            onOpened: () => {
              this.$router.push({ path: "/action/index" });
            }
          });
        } else {
          this.$notify(re.message);
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
