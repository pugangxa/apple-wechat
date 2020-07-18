package com.gangs.apple.service.impl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gangs.apple.domain.AppleExpert;
import com.gangs.apple.domain.User;
import com.gangs.apple.domain.enums.AuditStatusEnum;
import com.gangs.apple.repository.AppleExpertMapper;
import com.gangs.apple.service.AppleExpertService;
import com.gangs.apple.utility.ModelMapperSingle;
import com.gangs.apple.viewmodel.normal.publish.AppleExpertPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleExpertRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AppleExpertServiceImpl extends BaseServiceImpl<AppleExpert> implements AppleExpertService {
    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();

	private final AppleExpertMapper expertMapper;

	@Autowired
	public AppleExpertServiceImpl(AppleExpertMapper expertMapper) {
		super(expertMapper);
		this.expertMapper = expertMapper;
	}

	@Override
	public PageInfo<AppleExpert> page(AppleExpertPageRequestVM requestVM) {
		return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc")
				.doSelectPageInfo(() -> expertMapper.appleExpertPage(requestVM));
	}

	@Override
	@Transactional
	public void edit(AppleExpertRequestVM model, User user) {
    	AppleExpert appleExpert = null;
    	Date now = new Date();
    	appleExpert = modelMapper.map(model, AppleExpert.class);
    	appleExpert.setCreateUser(user.getId());
    	appleExpert.setCreateUserName(user.getUserName());
    	appleExpert.setCreateTime(now);
    	appleExpert.setStatus(1);
    	expertMapper.insertSelective(appleExpert);
	}

	@Override
	public void audit(Integer id) {
		AppleExpert appleExpert = expertMapper.selectByPrimaryKey(id);
		appleExpert.setStatus(AuditStatusEnum.Passed.getCode());
		expertMapper.updateByPrimaryKeySelective(appleExpert);
	}

}
