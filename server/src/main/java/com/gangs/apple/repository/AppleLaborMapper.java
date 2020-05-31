package com.gangs.apple.repository;

import com.gangs.apple.domain.AppleLabor;

public interface AppleLaborMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppleLabor record);

    int insertSelective(AppleLabor record);

    AppleLabor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppleLabor record);

    int updateByPrimaryKey(AppleLabor record);
}