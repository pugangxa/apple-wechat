import { get, post } from "@/utils/request";

export default {
  login: query => get(`/api/user/login`, query),
  logout: query => post(`/api/user/logout`, query)
};
