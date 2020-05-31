package com.gangs.apple.repository;

import com.gangs.apple.domain.AppleMerchant;

public interface AppleMerchantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppleMerchant record);

    int insertSelective(AppleMerchant record);

    AppleMerchant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppleMerchant record);

    int updateByPrimaryKey(AppleMerchant record);
}