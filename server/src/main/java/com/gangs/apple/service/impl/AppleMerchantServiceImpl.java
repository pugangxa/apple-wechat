package com.gangs.apple.service.impl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gangs.apple.domain.AppleMerchant;
import com.gangs.apple.domain.User;
import com.gangs.apple.domain.enums.AuditStatusEnum;
import com.gangs.apple.repository.AppleMerchantMapper;
import com.gangs.apple.service.AppleMerchantService;
import com.gangs.apple.utility.ModelMapperSingle;
import com.gangs.apple.viewmodel.normal.publish.AppleMerchantPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleMerchantRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AppleMerchantServiceImpl extends BaseServiceImpl<AppleMerchant> implements AppleMerchantService {
    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();

	private final AppleMerchantMapper merchantMapper;

	@Autowired
	public AppleMerchantServiceImpl(AppleMerchantMapper merchantMapper) {
		super(merchantMapper);
		this.merchantMapper = merchantMapper;
	}

	@Override
	public PageInfo<AppleMerchant> page(AppleMerchantPageRequestVM requestVM) {
		return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc")
				.doSelectPageInfo(() -> merchantMapper.appleMerchantPage(requestVM));
	}

	@Override
	@Transactional
	public void edit(AppleMerchantRequestVM model, User user) {
    	AppleMerchant appleMerchant = null;
    	Date now = new Date();
    	appleMerchant = modelMapper.map(model, AppleMerchant.class);
    	appleMerchant.setCreateUser(user.getId());
    	appleMerchant.setCreateUserName(user.getUserName());
    	appleMerchant.setCreateTime(now);
    	appleMerchant.setStatus(1);
    	merchantMapper.insertSelective(appleMerchant);
	}

	@Override
	public void audit(Integer id) {
		AppleMerchant appleLable = merchantMapper.selectByPrimaryKey(id);
		appleLable.setStatus(AuditStatusEnum.Passed.getCode());
		merchantMapper.updateByPrimaryKeySelective(appleLable);
	}

}
