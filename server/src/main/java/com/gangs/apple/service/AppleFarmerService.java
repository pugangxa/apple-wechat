package com.gangs.apple.service;

import com.gangs.apple.domain.AppleFarmer;
import com.gangs.apple.domain.User;
import com.gangs.apple.viewmodel.normal.publish.AppleFarmerPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleFarmerRequestVM;
import com.github.pagehelper.PageInfo;

public interface AppleFarmerService extends BaseService<AppleFarmer> {
	PageInfo<AppleFarmer> page(AppleFarmerPageRequestVM requestVM);
	void edit(AppleFarmerRequestVM model, User user);
	void audit(Integer id);
}
