package com.gangs.apple.service;

import com.gangs.apple.domain.AppleExpert;
import com.gangs.apple.domain.User;
import com.gangs.apple.viewmodel.normal.publish.AppleExpertPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleExpertRequestVM;
import com.github.pagehelper.PageInfo;

public interface AppleExpertService extends BaseService<AppleExpert> {
	PageInfo<AppleExpert> page(AppleExpertPageRequestVM requestVM);
	void edit(AppleExpertRequestVM model, User user);
	void audit(Integer id);
}
