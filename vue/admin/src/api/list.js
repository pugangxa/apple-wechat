import { post } from "@/utils/request";

export default {
  pagelistLabor: query => post(`/api/normal/publish/labor/pagelist`, query),
  pagelistSupply: query => post(`/api/normal/publish/supply/pagelist`, query)
};
