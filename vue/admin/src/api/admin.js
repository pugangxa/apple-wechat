import { post, _delete } from "@/utils/request";

export default {
  auditLabor: id => post(`/api/admin/labor/audit/` + id),
  auditSupply: id => post(`/api/admin/supply/audit/` + id),
  auditFarmer: id => post(`/api/admin/farmer/audit/` + id),
  auditMerchant: id => post(`/api/admin/merchant/audit/` + id),
  deleteLabor: id => _delete(`/api/admin/labor/` + id),
  deleteSupply: id => _delete(`/api/admin/supply/` + id),
  deleteFarmer: id => _delete(`/api/admin/farmer/` + id),
  deleteMerchant: id => _delete(`/api/admin/merchant/` + id),
  pagelistUser: query => post(`/api/admin/user/pagelist`, query),
  deleteUser: id => _delete(`/api/admin/user/` + id),
  resetUserPassword: id => post(`/api/admin/user/reset/` + id)
};
