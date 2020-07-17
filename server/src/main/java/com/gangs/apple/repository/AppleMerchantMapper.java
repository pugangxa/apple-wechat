package com.gangs.apple.repository;

import java.util.List;

import com.gangs.apple.domain.AppleMerchant;
import com.gangs.apple.viewmodel.normal.publish.AppleMerchantPageRequestVM;

public interface AppleMerchantMapper extends BaseMapper<AppleMerchant>{
    int deleteByPrimaryKey(Integer id);

    int insert(AppleMerchant record);

    int insertSelective(AppleMerchant record);

    AppleMerchant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppleMerchant record);

    int updateByPrimaryKey(AppleMerchant record);
    
    List<AppleMerchant> appleMerchantPage(AppleMerchantPageRequestVM requestVM);

}