package com.gangs.apple.service;

import com.gangs.apple.domain.AppleLabor;
import com.gangs.apple.viewmodel.normal.publish.AppleLaborRequestVM;
import com.github.pagehelper.PageInfo;

public interface AppleLaborService extends BaseService<AppleLabor> {
	PageInfo<AppleLaborService> page(AppleLaborRequestVM requestVM);

}
