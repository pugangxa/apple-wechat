package com.gangs.apple.repository;

import java.util.List;

import com.gangs.apple.domain.AppleLabor;
import com.gangs.apple.viewmodel.normal.publish.AppleLaborRequestVM;

public interface AppleLaborMapper extends BaseMapper<AppleLabor>{
    int deleteByPrimaryKey(Integer id);

    int insert(AppleLabor record);

    int insertSelective(AppleLabor record);

    AppleLabor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppleLabor record);

    int updateByPrimaryKey(AppleLabor record);
    
    List<AppleLabor> appleLaborPage(AppleLaborRequestVM requestVM);
}