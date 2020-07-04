import { post, _delete } from "@/utils/request";

export default {
  pagelistLabor: query => post(`/api/admin/labor/pagelist`, query),
  pagelistSupply: query => post(`/api/admin/supply/pagelist`, query),
  auditLabor: id => post(`/api/admin/labor/audit/` + id),
  auditSupply: id => post(`/api/admin/supply/audit/` + id),
  deleteLabor: id => _delete(`/api/admin/labor/` + id),
  deleteSupply: id => _delete(`/api/admin/supply/` + id),
  pagelistUser: query => post(`/api/admin/user/pagelist`, query),
  deleteUser: id => _delete(`/api/admin/user/` + id),
  resetUserPassword: id => post(`/api/admin/user/reset/` + id)
};
