package com.gangs.apple.repository;

import java.util.List;

import com.gangs.apple.domain.AppleFarmer;
import com.gangs.apple.viewmodel.normal.publish.AppleFarmerPageRequestVM;

public interface AppleFarmerMapper extends BaseMapper<AppleFarmer>{
    int deleteByPrimaryKey(Integer id);

    int insert(AppleFarmer record);

    int insertSelective(AppleFarmer record);

    AppleFarmer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppleFarmer record);

    int updateByPrimaryKey(AppleFarmer record);
    
    List<AppleFarmer> appleFarmerPage(AppleFarmerPageRequestVM requestVM);

}