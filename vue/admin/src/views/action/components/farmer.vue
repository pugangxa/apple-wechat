<template>
  <div>
    <van-nav-bar
      title="果农信息发布"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
    <div class="form">
      <van-form @submit="onSubmit">
        <van-field
          v-model="farmer.name"
          label="果农姓名"
          placeholder="姓名"
          :rules="[{ required: true, message: '请填写姓名' }]"
        />
        <van-field
          v-model="farmer.location"
          label="地址"
          placeholder="地址"
          :rules="[{ required: true, message: '请填写地址' }]"
        />
        <van-field
          v-model="farmer.category"
          label="种植品种"
          placeholder="品种"
          :rules="[{ required: true, message: '请填写品种' }]"
        />
        <van-field
          v-model.number="farmer.quantity"
          label="果品数量"
          type="digit"
          placeholder="数量"
          :rules="[{ required: true, message: '请填写数量' }]"
        />
        <van-field
          v-model="farmer.phone"
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
      farmer: {
        name: "",
        location: "",
        category: "",
        phone: "",
        quantity: ""
      }
    };
  },
  methods: {
    onClickLeft() {
      this.$router.push({ path: "/action/index" });
    },
    onSubmit() {
      publishAPI.publishFarmer(this.farmer).then(re => {
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
