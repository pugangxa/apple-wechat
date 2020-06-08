package com.gangs.apple.service.impl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gangs.apple.utility.ModelMapperSingle;
import com.gangs.apple.domain.AppleLabor;
import com.gangs.apple.domain.User;
import com.gangs.apple.repository.AppleLaborMapper;
import com.gangs.apple.service.AppleLaborService;
import com.gangs.apple.viewmodel.normal.publish.AppleLaborPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleLaborRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AppleLaborServiceImpl extends BaseServiceImpl<AppleLabor> implements AppleLaborService {
    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();

	private final AppleLaborMapper laborMapper;

	@Autowired
	public AppleLaborServiceImpl(AppleLaborMapper laborMapper) {
		super(laborMapper);
		this.laborMapper = laborMapper;
	}

	@Override
	public PageInfo<AppleLabor> page(AppleLaborPageRequestVM requestVM) {
		return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc")
				.doSelectPageInfo(() -> laborMapper.appleLaborPage(requestVM));
	}

	@Override
	@Transactional
	public void edit(AppleLaborRequestVM model, User user) {
    	AppleLabor appleLabor = null;
    	Date now = new Date();
    	appleLabor = modelMapper.map(model, AppleLabor.class);
    	appleLabor.setCreateUser(user.getId());
    	appleLabor.setCreateUserName(user.getUserName());
    	appleLabor.setCreateTime(now);
    	laborMapper.insertSelective(appleLabor);
	}

}
