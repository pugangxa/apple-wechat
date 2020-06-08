import { post } from "@/utils/request";

export default {
  publishLabor: query => post(`/api/normal/publish/labor`, query),
  publishSupply: query => post(`/api/normal/publish/supply`, query)
};
