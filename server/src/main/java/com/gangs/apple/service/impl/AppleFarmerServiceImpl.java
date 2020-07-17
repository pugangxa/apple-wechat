package com.gangs.apple.service.impl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gangs.apple.domain.AppleFarmer;
import com.gangs.apple.domain.User;
import com.gangs.apple.domain.enums.AuditStatusEnum;
import com.gangs.apple.repository.AppleFarmerMapper;
import com.gangs.apple.service.AppleFarmerService;
import com.gangs.apple.utility.ModelMapperSingle;
import com.gangs.apple.viewmodel.normal.publish.AppleFarmerPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleFarmerRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AppleFarmerServiceImpl extends BaseServiceImpl<AppleFarmer> implements AppleFarmerService {
    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();

	private final AppleFarmerMapper farmerMapper;

	@Autowired
	public AppleFarmerServiceImpl(AppleFarmerMapper farmerMapper) {
		super(farmerMapper);
		this.farmerMapper = farmerMapper;
	}

	@Override
	public PageInfo<AppleFarmer> page(AppleFarmerPageRequestVM requestVM) {
		return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc")
				.doSelectPageInfo(() -> farmerMapper.appleFarmerPage(requestVM));
	}

	@Override
	@Transactional
	public void edit(AppleFarmerRequestVM model, User user) {
    	AppleFarmer appleFarmer = null;
    	Date now = new Date();
    	appleFarmer = modelMapper.map(model, AppleFarmer.class);
    	appleFarmer.setCreateUser(user.getId());
    	appleFarmer.setCreateUserName(user.getUserName());
    	appleFarmer.setCreateTime(now);
    	appleFarmer.setStatus(1);
    	farmerMapper.insertSelective(appleFarmer);
	}

	@Override
	public void audit(Integer id) {
		AppleFarmer appleFarmer = farmerMapper.selectByPrimaryKey(id);
		appleFarmer.setStatus(AuditStatusEnum.Passed.getCode());
		farmerMapper.updateByPrimaryKeySelective(appleFarmer);
	}

}
