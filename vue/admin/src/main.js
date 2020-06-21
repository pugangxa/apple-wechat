import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "@/styles/index.scss"; // global css

import {
  Cell,
  Col,
  Row,
  Tabbar,
  Form,
  Button,
  TabbarItem,
  Field,
  Popup,
  Picker,
  Swipe,
  SwipeItem,
  Notify,
  Tab,
  Tabs,
  Grid,
  GridItem,
  NavBar,
  RadioGroup,
  Radio,
  Sticky,
  List,
  PullRefresh,
  Divider,
  Icon,
  Toast,
  Dialog
} from "vant";

Vue.use(Cell)
  .use(Col)
  .use(Row)
  .use(Tabbar)
  .use(Form)
  .use(Button)
  .use(TabbarItem)
  .use(Field)
  .use(Popup)
  .use(Picker)
  .use(Swipe)
  .use(SwipeItem)
  .use(Notify)
  .use(Tab)
  .use(Tabs)
  .use(Grid)
  .use(GridItem)
  .use(NavBar)
  .use(RadioGroup)
  .use(Radio)
  .use(Sticky)
  .use(List)
  .use(PullRefresh)
  .use(Divider)
  .use(Icon)
  .use(Toast)
  .use(Dialog);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
