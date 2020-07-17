package com.gangs.apple.service;

import com.gangs.apple.domain.AppleMerchant;
import com.gangs.apple.domain.User;
import com.gangs.apple.viewmodel.normal.publish.AppleMerchantPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleMerchantRequestVM;
import com.github.pagehelper.PageInfo;

public interface AppleMerchantService extends BaseService<AppleMerchant> {
	PageInfo<AppleMerchant> page(AppleMerchantPageRequestVM requestVM);
	void edit(AppleMerchantRequestVM model, User user);
	void audit(Integer id);
}
