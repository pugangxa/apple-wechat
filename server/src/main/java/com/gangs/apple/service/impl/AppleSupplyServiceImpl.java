package com.gangs.apple.service.impl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gangs.apple.domain.AppleSupply;
import com.gangs.apple.domain.User;
import com.gangs.apple.domain.enums.AuditStatusEnum;
import com.gangs.apple.repository.AppleSupplyMapper;
import com.gangs.apple.service.AppleSupplyService;
import com.gangs.apple.utility.ModelMapperSingle;
import com.gangs.apple.viewmodel.normal.publish.AppleSupplyPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleSupplyRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AppleSupplyServiceImpl extends BaseServiceImpl<AppleSupply> implements AppleSupplyService {
    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();

	private final AppleSupplyMapper supplyMapper;

	@Autowired
	public AppleSupplyServiceImpl(AppleSupplyMapper supplyMapper) {
		super(supplyMapper);
		this.supplyMapper = supplyMapper;
	}

	@Override
	public PageInfo<AppleSupply> page(AppleSupplyPageRequestVM requestVM) {
		return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc")
				.doSelectPageInfo(() -> supplyMapper.appleSupplyPage(requestVM));
	}

	@Override
	@Transactional
	public void edit(AppleSupplyRequestVM model, User user) {
    	AppleSupply appleSupply = null;
    	Date now = new Date();
    	appleSupply = modelMapper.map(model, AppleSupply.class);
    	appleSupply.setCreateUser(user.getId());
    	appleSupply.setCreateUserName(user.getUserName());
    	appleSupply.setCreateTime(now);
    	appleSupply.setStatus(1);
    	supplyMapper.insertSelective(appleSupply);
	}

	@Override
	public void audit(Integer id) {
		AppleSupply appleSupply = supplyMapper.selectByPrimaryKey(id);
		appleSupply.setStatus(AuditStatusEnum.Passed.getCode());
		supplyMapper.updateByPrimaryKeySelective(appleSupply);
		
	}

}
