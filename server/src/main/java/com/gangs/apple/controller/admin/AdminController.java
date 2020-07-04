package com.gangs.apple.controller.admin;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gangs.apple.base.BaseApiController;
import com.gangs.apple.base.RestResponse;
import com.gangs.apple.domain.AppleLabor;
import com.gangs.apple.domain.AppleSupply;
import com.gangs.apple.domain.User;
import com.gangs.apple.service.AppleLaborService;
import com.gangs.apple.service.AppleSupplyService;
import com.gangs.apple.service.UserService;
import com.gangs.apple.utility.DateTimeUtil;
import com.gangs.apple.utility.PageInfoHelper;
import com.gangs.apple.viewmodel.admin.user.UserPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleLaborPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleLaborPageResponseVM;
import com.gangs.apple.viewmodel.normal.publish.AppleSupplyPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleSupplyPageResponseVM;
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
	
    @RequestMapping(value = "/labor/pagelist", method = RequestMethod.POST)
    public RestResponse<PageInfo<AppleLaborPageResponseVM>> pageListLabor(@RequestBody @Valid AppleLaborPageRequestVM model) {
        PageInfo<AppleLabor> pageInfo = laborService.page(model);
        PageInfo<AppleLaborPageResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
        	AppleLaborPageResponseVM vm = modelMapper.map(e, AppleLaborPageResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }
    
    @RequestMapping(value = "/supply/pagelist", method = RequestMethod.POST)
    public RestResponse<PageInfo<AppleSupplyPageResponseVM>> pageListSupply(@RequestBody @Valid AppleSupplyPageRequestVM model) {
        PageInfo<AppleSupply> pageInfo = supplyService.page(model);
        PageInfo<AppleSupplyPageResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
        	AppleSupplyPageResponseVM vm = modelMapper.map(e, AppleSupplyPageResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }
	
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
