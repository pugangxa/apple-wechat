import { post } from "@/utils/request";

export default {
  register: query => post(`/api/normal/user/register`, query)
};
