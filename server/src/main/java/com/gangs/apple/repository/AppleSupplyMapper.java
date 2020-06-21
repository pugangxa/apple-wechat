package com.gangs.apple.repository;

import java.util.List;

import com.gangs.apple.domain.AppleSupply;
import com.gangs.apple.viewmodel.normal.publish.AppleSupplyPageRequestVM;

public interface AppleSupplyMapper extends BaseMapper<AppleSupply>{
    int deleteByPrimaryKey(Integer id);

    int insert(AppleSupply record);

    int insertSelective(AppleSupply record);

    AppleSupply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppleSupply record);

    int updateByPrimaryKey(AppleSupply record);

	List<AppleSupply> appleSupplyPage(AppleSupplyPageRequestVM requestVM);
}