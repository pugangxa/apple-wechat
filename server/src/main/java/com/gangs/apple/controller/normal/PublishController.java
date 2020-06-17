package com.gangs.apple.controller.normal;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gangs.apple.base.BaseApiController;
import com.gangs.apple.base.RestResponse;
import com.gangs.apple.domain.AppleLabor;
import com.gangs.apple.service.AppleLaborService;
import com.gangs.apple.utility.DateTimeUtil;
import com.gangs.apple.utility.PageInfoHelper;
import com.gangs.apple.viewmodel.normal.publish.AppleLaborPageRequestVM;
import com.gangs.apple.viewmodel.normal.publish.AppleLaborPageResponseVM;
import com.gangs.apple.viewmodel.normal.publish.AppleLaborRequestVM;
import com.github.pagehelper.PageInfo;

import lombok.AllArgsConstructor;

@RestController("PublishController")
@RequestMapping(value = "/api/normal/publish")
@AllArgsConstructor
public class PublishController extends BaseApiController {
	private final AppleLaborService laborService;
	
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
    
    @RequestMapping(value = "/labor/edit", method = RequestMethod.POST)
    public RestResponse editLabor(@RequestBody @Valid AppleLaborRequestVM model) {
    	laborService.edit(model, getCurrentUser());
        return RestResponse.ok();
    }
}
