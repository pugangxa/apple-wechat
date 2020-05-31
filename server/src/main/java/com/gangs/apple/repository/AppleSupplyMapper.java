package com.gangs.apple.repository;

import com.gangs.apple.domain.AppleSupply;

public interface AppleSupplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppleSupply record);

    int insertSelective(AppleSupply record);

    AppleSupply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppleSupply record);

    int updateByPrimaryKey(AppleSupply record);
}