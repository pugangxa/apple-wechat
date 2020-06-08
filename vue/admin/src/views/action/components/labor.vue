<template>
  <div>
    <van-nav-bar
      title="劳务信息发布"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
    <div class="form">
      <van-form @submit="onSubmit">
        <div class="radio-group">
          <van-radio-group v-model="radio" direction="horizontal">
            <van-radio name="1">我要招工</van-radio>
            <van-radio name="2">我要工作</van-radio>
          </van-radio-group>
        </div>
        <van-field
          v-model="number"
          name="number"
          label="人数"
          type="digit"
          placeholder="多少人"
          :rules="[{ required: true, message: '请填写人数' }]"
        />
        <van-field
          v-model="pay"
          name="pay"
          label="日薪/元"
          type="digit"
          placeholder="工资"
          :rules="[{ required: true, message: '请填写工资' }]"
        />
        <van-field
          v-model="location"
          name="location"
          label="地址"
          placeholder="工作地点"
          :rules="[{ required: true, message: '请填写地址' }]"
        />
        <van-field
          v-model="phone"
          type="tel"
          label="电话"
          placeholder="手机号"
          :rules="[{ required: true, message: '请填写电话' }]"
        />
        <van-field
          v-model="description"
          rows="3"
          autosize
          label="详细描述"
          type="textarea"
          maxlength="80"
          show-word-limit
          :rules="[{ required: true, message: '请填写详细描述' }]"
        />
        <van-sticky>
          <div style="margin: 16px;">
            <van-button round block type="info" native-type="submit">
              发布
            </van-button>
          </div>
        </van-sticky>
      </van-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      radio: "1",
      number: "",
      pay: "",
      location: "",
      phone: "",
      description: ""
    };
  },
  methods: {
    onClickLeft() {
      this.$router.push({ path: "/action/index" });
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
