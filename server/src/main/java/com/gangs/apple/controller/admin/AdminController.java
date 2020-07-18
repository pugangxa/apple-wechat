package com.gangs.apple.controller.admin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gangs.apple.base.BaseApiController;
import com.gangs.apple.base.RestResponse;
import com.gangs.apple.domain.User;
import com.gangs.apple.service.AppleExpertService;
import com.gangs.apple.service.AppleFarmerService;
import com.gangs.apple.service.AppleLaborService;
import com.gangs.apple.service.AppleMerchantService;
import com.gangs.apple.service.AppleSupplyService;
import com.gangs.apple.service.UserService;
import com.gangs.apple.utility.PageInfoHelper;
import com.gangs.apple.viewmodel.admin.user.UserPageRequestVM;
import com.gangs.apple.viewmodel.normal.user.UserResponseVM;
import com.github.pagehelper.PageInfo;

import lombok.AllArgsConstructor;

@RestController("AdminController")
@RequestMapping(value = "/api/admin")
@AllArgsConstructor
public class AdminController  extends BaseApiController {
	private final UserService userService;
	private final AppleLaborService laborService;
	private final AppleSupplyService supplyService;
	
	private final AppleFarmerService farmerService;
	private final AppleMerchantService merchantService;
	
	private final AppleExpertService expertService;
	
    @RequestMapping(value = "/labor/audit/{id}", method = RequestMethod.POST)
    public RestResponse auditLabor(@PathVariable Integer id) {
    	laborService.audit(id);
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/supply/audit/{id}", method = RequestMethod.POST)
    public RestResponse auditSupply(@PathVariable Integer id) {
    	supplyService.audit(id);
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/farmer/audit/{id}", method = RequestMethod.POST)
    public RestResponse auditFarmer(@PathVariable Integer id) {
    	farmerService.audit(id);
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/merchant/audit/{id}", method = RequestMethod.POST)
    public RestResponse auditMerchant(@PathVariable Integer id) {
    	merchantService.audit(id);
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/expert/audit/{id}", method = RequestMethod.POST)
    public RestResponse auditExpert(@PathVariable Integer id) {
    	expertService.audit(id);
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/labor/{id}", method = RequestMethod.DELETE)
    public RestResponse deleteLabor(@PathVariable Integer id) {
    	laborService.deleteById(id);
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/supply/{id}", method = RequestMethod.DELETE)
    public RestResponse deleteSupply(@PathVariable Integer id) {
    	supplyService.deleteById(id);
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/farmer/{id}", method = RequestMethod.DELETE)
    public RestResponse deleteFarmer(@PathVariable Integer id) {
    	farmerService.deleteById(id);
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/merchant/{id}", method = RequestMethod.DELETE)
    public RestResponse deleteMerchant(@PathVariable Integer id) {
    	merchantService.deleteById(id);
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/expert/{id}", method = RequestMethod.DELETE)
    public RestResponse deleteExpert(@PathVariable Integer id) {
    	expertService.deleteById(id);
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/user/pagelist", method = RequestMethod.POST)
    public RestResponse<PageInfo<UserResponseVM>> pageListUser(@RequestBody UserPageRequestVM model) {
        PageInfo<User> pageInfo = userService.userPage(model);
        PageInfo<UserResponseVM> page = PageInfoHelper.copyMap(pageInfo, d -> UserResponseVM.from(d));
        return RestResponse.ok(page);
    }
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public RestResponse deleteUser(@PathVariable Integer id) {
    	userService.deleteById(id);
        return RestResponse.ok();
    }
    
    @RequestMapping(value = "/user/reset/{id}", method = RequestMethod.POST)
    public RestResponse resetUserPassword(@PathVariable Integer id) {
    	userService.resetPassById(id);
        return RestResponse.ok();
    }
}
