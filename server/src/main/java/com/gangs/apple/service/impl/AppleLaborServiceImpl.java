package com.gangs.apple.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gangs.apple.domain.AppleLabor;
import com.gangs.apple.repository.AppleLaborMapper;
import com.gangs.apple.service.AppleLaborService;
import com.gangs.apple.viewmodel.normal.publish.AppleLaborRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AppleLaborServiceImpl extends BaseServiceImpl<AppleLabor> implements AppleLaborService {

	private final AppleLaborMapper laborMapper;

	@Autowired
	public AppleLaborServiceImpl(AppleLaborMapper laborMapper) {
		super(laborMapper);
		this.laborMapper = laborMapper;
	}

	@Override
	public PageInfo<AppleLaborService> page(AppleLaborRequestVM requestVM) {
		return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc")
				.doSelectPageInfo(() -> laborMapper.appleLaborPage(requestVM));
	}

}
