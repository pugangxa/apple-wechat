import { get } from "@/utils/request";

export default {
  listLabor: query => get(`/api/normal/list/labor`, query),
  listSupply: query => get(`/api/normal/list/supply`, query)
};
