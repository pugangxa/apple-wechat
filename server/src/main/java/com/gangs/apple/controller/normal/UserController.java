package com.gangs.apple.controller.normal;

import java.util.Date;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gangs.apple.viewmodel.normal.user.UserResponseVM;
import com.gangs.apple.viewmodel.normal.user.UserUpdateVM;
import com.gangs.apple.base.BaseApiController;
import com.gangs.apple.base.RestResponse;
import com.gangs.apple.domain.User;
import com.gangs.apple.domain.enums.RoleEnum;
import com.gangs.apple.domain.enums.UserStatusEnum;
import com.gangs.apple.domain.other.UserEventLog;
import com.gangs.apple.event.UserEvent;
import com.gangs.apple.service.AuthenticationService;
import com.gangs.apple.service.UserService;
import com.gangs.apple.viewmodel.normal.user.UserRegisterVM;

import lombok.AllArgsConstructor;

@RestController("UserController")
@RequestMapping(value = "/api/normal/user")
@AllArgsConstructor
public class UserController extends BaseApiController {
	private final UserService userService;
	private final AuthenticationService authenticationService;
	private final ApplicationEventPublisher eventPublisher;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public RestResponse register(@RequestBody @Valid UserRegisterVM model) {
		User existUser = userService.getUserByUserName(model.getUserName());
		if (null != existUser) {
			return new RestResponse<>(2, "用户已存在");
		}
		User user = modelMapper.map(model, User.class);
		String encodePwd = authenticationService.pwdEncode(model.getPassword());
		user.setUserUuid(UUID.randomUUID().toString());
		user.setPassword(encodePwd);
		user.setRole(RoleEnum.USER.getCode());
		user.setStatus(UserStatusEnum.Enable.getCode());
		user.setCreateTime(new Date());
		userService.insertByFilter(user);
		UserEventLog userEventLog = new UserEventLog(user.getId(), user.getUserName(), user.getRealName(), new Date());
		userEventLog.setContent("欢迎 " + user.getUserName() + " 注册果业平台系统");
		eventPublisher.publishEvent(new UserEvent(userEventLog));
		return RestResponse.ok();
	}

	@RequestMapping(value = "/current", method = RequestMethod.POST)
	public RestResponse<UserResponseVM> current() {
		User user = getCurrentUser();
		UserResponseVM userVm = UserResponseVM.from(user);
		return RestResponse.ok(userVm);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	//public RestResponse update(@RequestBody @Valid UserUpdateVM model) {
	public RestResponse update(@RequestBody @Valid UserUpdateVM model) {
		User user = userService.selectById(getCurrentUser().getId());
		if(null != model.getPassword()) {
			String encodePwd = authenticationService.pwdEncode(model.getPassword());
			model.setPassword(encodePwd);
		}
		modelMapper.map(model, user);
		user.setModifyTime(new Date());
		userService.updateByIdFilter(user);
		UserEventLog userEventLog = new UserEventLog(user.getId(), user.getUserName(), user.getRealName(), new Date());
		userEventLog.setContent(user.getUserName() + " 更新了个人资料");
		eventPublisher.publishEvent(new UserEvent(userEventLog));
		return RestResponse.ok();
	}
}
