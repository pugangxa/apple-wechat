import { post } from "@/utils/request";

export default {
  pagelistAuditLabor: query =>
    post(`/api/normal/publish/labor/pagelist`, query),
  pagelistAuditSupply: query =>
    post(`/api/normal/publish/supply/pagelist`, query)
};
