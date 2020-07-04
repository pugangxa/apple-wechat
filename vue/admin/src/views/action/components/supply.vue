<template>
  <div>
    <van-nav-bar
      title="供需信息发布"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
    <div class="form">
      <van-form @submit="onSubmit">
        <div class="radio-group">
          <van-radio-group v-model="radioType" direction="horizontal">
            <van-radio name="1">我要收购</van-radio>
            <van-radio name="2">我要销售</van-radio>
          </van-radio-group>
        </div>
        <van-field
          v-model.number="supply.quantity"
          name="quantity"
          label="数量(斤)"
          type="digit"
          placeholder="果品数量"
          :rules="[{ required: true, message: '请填写果品数量' }]"
        />
        <van-field
          v-model.number="supply.price"
          name="price"
          label="价格(元)"
          type="number"
          placeholder="价格"
          :rules="[{ required: true, message: '请填写价格' }]"
        />
        <van-field
          v-model="supply.phone"
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
          v-model="supply.description"
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
      supply: {
        type: 1,
        quantity: "",
        price: "",
        grade: "",
        phone: "",
        description: ""
      }
    };
  },
  computed: {
    radioType: {
      get() {
        return String(this.supply.type);
      },
      set(value) {
        this.supply.type = value === "1" ? 1 : 2;
      }
    }
  },
  methods: {
    onClickLeft() {
      this.$router.push({ path: "/action/index" });
    },
    onSubmit() {
      publishAPI.publishSupply(this.supply).then(re => {
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
