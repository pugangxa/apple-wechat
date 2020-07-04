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
          <van-radio-group v-model="radioType" direction="horizontal">
            <van-radio name="1">我要招工</van-radio>
            <van-radio name="2">我要工作</van-radio>
          </van-radio-group>
        </div>
        <van-field
          v-model.number="labor.number"
          name="number"
          label="人数"
          type="digit"
          placeholder="多少人"
          :rules="[{ required: true, message: '请填写人数' }]"
        />
        <van-field
          v-model.number="labor.pay"
          name="pay"
          label="日薪(元)"
          type="digit"
          placeholder="工资"
          :rules="[{ required: true, message: '请填写工资' }]"
        />
        <van-field
          v-model="labor.phone"
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
        <van-field
          v-model="labor.description"
          rows="3"
          autosize
          label="详细描述"
          type="textarea"
          maxlength="80"
          show-word-limit
          :rules="[{ required: true, message: '请填写详细描述' }]"
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
      labor: { type: 1, number: "", pay: "", phone: "", description: "" }
    };
  },
  computed: {
    radioType: {
      get() {
        return String(this.labor.type);
      },
      set(value) {
        this.labor.type = value === "1" ? 1 : 2;
      }
    }
  },
  methods: {
    onClickLeft() {
      this.$router.push({ path: "/action/index" });
    },
    onSubmit() {
      publishAPI.publishLabor(this.labor).then(re => {
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
