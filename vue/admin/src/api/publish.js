import { post } from "@/utils/request";

export default {
  publishLabor: query => post(`/api/normal/publish/labor/edit`, query),
  publishSupply: query => post(`/api/normal/publish/supply/edit`, query)
};
