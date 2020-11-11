import Vue from "vue";
import Vuex from "vuex";
import Cookies from "js-cookie";

Vue.use(Vuex);

// initial state
const state = {
  userName: Cookies.get("userName"),
  userInfo: Cookies.get("userInfo")
};

// mutations
const mutations = {
  setUserName(state, userName) {
    state.userName = userName;
    Cookies.set("userName", userName, { expires: 300 });
  },
  setUserInfo: (state, userInfo) => {
    state.userInfo = userInfo;
    Cookies.set("userInfo", userInfo, { expires: 300 });
  },
  clearLogin() {
    Cookies.remove("userName");
    Cookies.remove("userInfo");
  }
};

export default new Vuex.Store({
  state,
  mutations
});
