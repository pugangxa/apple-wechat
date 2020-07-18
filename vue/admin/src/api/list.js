import { post } from "@/utils/request";

export default {
  pagelistLabor: query => post(`/api/normal/publish/labor/pagelist`, query),
  pagelistSupply: query => post(`/api/normal/publish/supply/pagelist`, query),
  pagelistFarmer: query => post(`/api/normal/publish/farmer/pagelist`, query),
  pagelistMerchant: query =>
    post(`/api/normal/publish/merchant/pagelist`, query),
  pagelistExpert: query => post(`/api/normal/publish/expert/pagelist`, query)
};
