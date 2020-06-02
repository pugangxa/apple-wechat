import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "@/styles/index.scss"; // global css

import {
  Cell,
  Tabbar,
  Form,
  Button,
  TabbarItem,
  Field,
  Popup,
  Picker
} from "vant";

Vue.use(Cell)
  .use(Tabbar)
  .use(Form)
  .use(Button)
  .use(TabbarItem)
  .use(Field)
  .use(Popup)
  .use(Picker);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
