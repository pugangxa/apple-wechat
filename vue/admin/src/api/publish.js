import { post, _delete } from "@/utils/request";

export default {
  publishLabor: query => post(`/api/normal/publish/labor/edit`, query),
  publishSupply: query => post(`/api/normal/publish/supply/edit`, query),
  deleteLabor: id => _delete(`/api/normal/labor/` + id),
  deleteSupply: id => _delete(`/api/normal/supply/` + id)
};
