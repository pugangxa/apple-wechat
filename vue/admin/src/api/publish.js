import { post, _delete } from "@/utils/request";

export default {
  publishLabor: query => post(`/api/normal/publish/labor/edit`, query),
  publishSupply: query => post(`/api/normal/publish/supply/edit`, query),
  publishFarmer: query => post(`/api/normal/publish/farmer/edit`, query),
  publishMerchant: query => post(`/api/normal/publish/merchant/edit`, query),
  deleteLabor: id => _delete(`/api/normal/labor/` + id),
  deleteSupply: id => _delete(`/api/normal/supply/` + id),
  deleteFarmer: id => _delete(`/api/normal/farmer/` + id),
  deleteMerchant: id => _delete(`/api/normal/merchant/` + id)
};
