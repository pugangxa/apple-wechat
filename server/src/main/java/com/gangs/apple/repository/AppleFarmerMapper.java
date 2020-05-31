package com.gangs.apple.repository;

import com.gangs.apple.domain.AppleFarmer;

public interface AppleFarmerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppleFarmer record);

    int insertSelective(AppleFarmer record);

    AppleFarmer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppleFarmer record);

    int updateByPrimaryKey(AppleFarmer record);
}