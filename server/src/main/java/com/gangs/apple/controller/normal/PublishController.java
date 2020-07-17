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
import com.gangs.apple.domain.AppleFarmer;
import com.gangs.apple.domain.AppleLabor;
import com.gangs.apple.domain.AppleMerchant;
import com.gangs.apple.domain.AppleSupply;
import com.gangs.apple.domain.enums.AuditStatusEnum;
import com.gangs.apple.service.AppleFarmerService;
import com.gangs.apple.service.AppleLaborService;
import com.gangs.apple.service.AppleMerchantService;
import com.gangs.apple.service.AppleSupplyService;
import com.gangs.apple.utility.DateTimeUtil;
import com.gangs.apple.utility.PageInfoHelper;
import com.gangs.apple.viewmodel.normal.publish.AppleFarmerPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleFarmerPageResponseVM;
import com.gangs.apple.viewmodel.normal.publish.AppleFarmerRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleLaborPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleLaborPageResponseVM;
import com.gangs.apple.viewmodel.normal.publish.AppleLaborRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleMerchantPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleMerchantPageResponseVM;
import com.gangs.apple.viewmodel.normal.publish.AppleMerchantRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleSupplyPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleSupplyPageResponseVM;
import com.gangs.apple.viewmodel.normal.publish.AppleSupplyRequestVM;
import com.github.pagehelper.PageInfo;

import lombok.AllArgsConstructor;

@RestController("PublishController")
@RequestMapping(value = "/api/normal")
@AllArgsConstructor
public class PublishController extends BaseApiController {
	private final AppleLaborService laborService;
	private final AppleSupplyService supplyService;
	
	private final AppleFarmerService farmerService;
	private final AppleMerchantService merchantService;
	
    @RequestMapping(value = "/publish/labor/pagelist", method = RequestMethod.POST)
    public RestResponse<PageInfo<AppleLaborPageResponseVM>> pageListLabor(@RequestBody @Valid AppleLaborPageRequestVM model) {
    	if(null != model.getCreateUserName() && model.getCreateUserName().equals("myself")) {
    		model.setCreateUserName(getCurrentUser().getUserName());
    	}
        PageInfo<AppleLabor> pageInfo = laborService.page(model);
        PageInfo<AppleLaborPageResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
        	AppleLaborPageResponseVM vm = modelMapper.map(e, AppleLaborPageResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }
    
    @RequestMapping(value = "/publish/labor/edit", method = RequestMethod.POST)
    public RestResponse editLabor(@RequestBody @Valid AppleLaborRequestVM model) {
    	laborService.edit(model, getCurrentUser());
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/publish/supply/pagelist", method = RequestMethod.POST)
    public RestResponse<PageInfo<AppleSupplyPageResponseVM>> pageListSupply(@RequestBody @Valid AppleSupplyPageRequestVM model) {
    	if(null != model.getCreateUserName() && model.getCreateUserName().equals("myself")) {
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
    
    @RequestMapping(value = "/publish/supply/edit", method = RequestMethod.POST)
    public RestResponse editSupply(@RequestBody @Valid AppleSupplyRequestVM model) {
    	supplyService.edit(model, getCurrentUser());
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/labor/{id}", method = RequestMethod.DELETE)
    public RestResponse deleteSelfLabor(@PathVariable Integer id) {
    	if(!laborService.selectById(id).getCreateUserName().equals(getCurrentUser().getUserName())) {
    		return RestResponse.fail(SystemCode.AccessDenied.getCode(), SystemCode.AccessDenied.getMessage());
    	}    	
    	laborService.deleteById(id);
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/supply/{id}", method = RequestMethod.DELETE)
    public RestResponse deleteSelfSupply(@PathVariable Integer id) {
    	if(!supplyService.selectById(id).getCreateUserName().equals(getCurrentUser().getUserName())) {
    		return RestResponse.fail(SystemCode.AccessDenied.getCode(), SystemCode.AccessDenied.getMessage());
    	}   
    	supplyService.deleteById(id);
        return RestResponse.ok();
    }
    
	
    @RequestMapping(value = "/publish/farmer/pagelist", method = RequestMethod.POST)
    public RestResponse<PageInfo<AppleFarmerPageResponseVM>> pageListFarmer(@RequestBody @Valid AppleFarmerPageRequestVM model) {
    	if(null != model.getCreateUserName() && model.getCreateUserName().equals("myself")) {
    		model.setCreateUserName(getCurrentUser().getUserName());
    	}
        PageInfo<AppleFarmer> pageInfo = farmerService.page(model);
        PageInfo<AppleFarmerPageResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
        	AppleFarmerPageResponseVM vm = modelMapper.map(e, AppleFarmerPageResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }
    
    @RequestMapping(value = "/publish/farmer/edit", method = RequestMethod.POST)
    public RestResponse editFarmer(@RequestBody @Valid AppleFarmerRequestVM model) {
    	farmerService.edit(model, getCurrentUser());
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/farmer/{id}", method = RequestMethod.DELETE)
    public RestResponse deleteSelfFarmer(@PathVariable Integer id) {
    	if(!farmerService.selectById(id).getCreateUserName().equals(getCurrentUser().getUserName())) {
    		return RestResponse.fail(SystemCode.AccessDenied.getCode(), SystemCode.AccessDenied.getMessage());
    	}    	
    	farmerService.deleteById(id);
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/publish/merchant/pagelist", method = RequestMethod.POST)
    public RestResponse<PageInfo<AppleMerchantPageResponseVM>> pageListMerchant(@RequestBody @Valid AppleMerchantPageRequestVM model) {
    	if(null != model.getCreateUserName() && model.getCreateUserName().equals("myself")) {
    		model.setCreateUserName(getCurrentUser().getUserName());
    	}
        PageInfo<AppleMerchant> pageInfo = merchantService.page(model);
        PageInfo<AppleMerchantPageResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
        	AppleMerchantPageResponseVM vm = modelMapper.map(e, AppleMerchantPageResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }
    
    @RequestMapping(value = "/publish/merchant/edit", method = RequestMethod.POST)
    public RestResponse editMerchant(@RequestBody @Valid AppleMerchantRequestVM model) {
    	merchantService.edit(model, getCurrentUser());
        return RestResponse.ok();
    }
    
    
    @RequestMapping(value = "/merchant/{id}", method = RequestMethod.DELETE)
    public RestResponse deleteSelfMerchant(@PathVariable Integer id) {
    	if(!merchantService.selectById(id).getCreateUserName().equals(getCurrentUser().getUserName())) {
    		return RestResponse.fail(SystemCode.AccessDenied.getCode(), SystemCode.AccessDenied.getMessage());
    	}   
    	merchantService.deleteById(id);
        return RestResponse.ok();
    }
}
