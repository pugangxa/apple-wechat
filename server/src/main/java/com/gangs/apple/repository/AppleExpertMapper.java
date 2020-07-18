package com.gangs.apple.repository;

import java.util.List;

import com.gangs.apple.domain.AppleExpert;
import com.gangs.apple.viewmodel.normal.publish.AppleExpertPageRequestVM;

public interface AppleExpertMapper extends BaseMapper<AppleExpert> {
	int deleteByPrimaryKey(Integer id);

	int insert(AppleExpert record);

	int insertSelective(AppleExpert record);

	AppleExpert selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(AppleExpert record);

	int updateByPrimaryKey(AppleExpert record);
	
    List<AppleExpert> appleExpertPage(AppleExpertPageRequestVM requestVM);
}