package com.gangs.apple.service;

import com.gangs.apple.domain.AppleSupply;
import com.gangs.apple.domain.User;
import com.gangs.apple.viewmodel.normal.publish.AppleSupplyPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleSupplyRequestVM;
import com.github.pagehelper.PageInfo;

public interface AppleSupplyService extends BaseService<AppleSupply> {
	PageInfo<AppleSupply> page(AppleSupplyPageRequestVM requestVM);
	void edit(AppleSupplyRequestVM model, User user);
	void audit(Integer id);
}
