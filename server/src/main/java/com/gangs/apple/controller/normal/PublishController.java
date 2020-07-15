package com.gangs.apple.controller.normal;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gangs.apple.base.BaseApiController;
import com.gangs.apple.base.RestResponse;
import com.gangs.apple.base.SystemCode;
import com.gangs.apple.domain.AppleLabor;
import com.gangs.apple.domain.AppleSupply;
import com.gangs.apple.domain.enums.AuditStatusEnum;
import com.gangs.apple.service.AppleLaborService;
import com.gangs.apple.service.AppleSupplyService;
import com.gangs.apple.utility.DateTimeUtil;
import com.gangs.apple.utility.PageInfoHelper;
import com.gangs.apple.viewmodel.normal.publish.AppleLaborPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleLaborPageResponseVM;
import com.gangs.apple.viewmodel.normal.publish.AppleLaborRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleSupplyPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleSupplyPageResponseVM;
import com.gangs.apple.viewmodel.normal.publish.AppleSupplyRequestVM;
import com.github.pagehelper.PageInfo;

import lombok.AllArgsConstructor;

@RestController("PublishController")
@RequestMapping(value = "/api/normal/publish")
@AllArgsConstructor
public class PublishController extends BaseApiController {
	private final AppleLaborService laborService;
	private final AppleSupplyService supplyService;
	
    @RequestMapping(value = "/labor/pagelist", method = RequestMethod.POST)
    public RestResponse<PageInfo<AppleLaborPageResponseVM>> pageListLabor(@RequestBody @Valid AppleLaborPageRequestVM model) {
    	model.setStatus(AuditStatusEnum.Passed.getCode());
        PageInfo<AppleLabor> pageInfo = laborService.page(model);
        PageInfo<AppleLaborPageResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
        	AppleLaborPageResponseVM vm = modelMapper.map(e, AppleLaborPageResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }
    
    @RequestMapping(value = "/labor/edit", method = RequestMethod.POST)
    public RestResponse editLabor(@RequestBody @Valid AppleLaborRequestVM model) {
    	laborService.edit(model, getCurrentUser());
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/supply/pagelist", method = RequestMethod.POST)
    public RestResponse<PageInfo<AppleSupplyPageResponseVM>> pageListSupply(@RequestBody @Valid AppleSupplyPageRequestVM model) {
    	if(model.getCreateUserName().equals("myself")) {
    		model.setCreateUserName(getCurrentUser().getUserName());
    	}
        PageInfo<AppleSupply> pageInfo = supplyService.page(model);
        PageInfo<AppleSupplyPageResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
        	AppleSupplyPageResponseVM vm = modelMapper.map(e, AppleSupplyPageResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }
    
    @RequestMapping(value = "/supply/edit", method = RequestMethod.POST)
    public RestResponse editSupply(@RequestBody @Valid AppleSupplyRequestVM model) {
    	supplyService.edit(model, getCurrentUser());
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/labor/{id}", method = RequestMethod.DELETE)
    public RestResponse deleteSelfLabor(@PathVariable Integer id) {
    	if(laborService.selectById(id).getCreateUserName().equals(getCurrentUser().getUserName())) {
    		return RestResponse.fail(SystemCode.AccessDenied.getCode(), SystemCode.AccessDenied.getMessage());
    	}    	
    	laborService.deleteById(id);
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/supply/{id}", method = RequestMethod.DELETE)
    public RestResponse deleteSelfSupply(@PathVariable Integer id) {
    	if(supplyService.selectById(id).getCreateUserName().equals(getCurrentUser().getUserName())) {
    		return RestResponse.fail(SystemCode.AccessDenied.getCode(), SystemCode.AccessDenied.getMessage());
    	}   
    	supplyService.deleteById(id);
        return RestResponse.ok();
    }
}
