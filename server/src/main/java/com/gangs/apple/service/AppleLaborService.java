package com.gangs.apple.service;

import com.gangs.apple.domain.AppleLabor;
import com.gangs.apple.domain.User;
import com.gangs.apple.viewmodel.normal.publish.AppleLaborPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleLaborRequestVM;
import com.github.pagehelper.PageInfo;

public interface AppleLaborService extends BaseService<AppleLabor> {
	PageInfo<AppleLabor> page(AppleLaborPageRequestVM requestVM);
	void edit(AppleLaborRequestVM model, User user);
	void audit(Integer id);
}
