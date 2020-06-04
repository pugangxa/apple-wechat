package com.gangs.apple.controller.normal;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gangs.apple.base.BaseApiController;
import com.gangs.apple.service.AuthenticationService;
import com.gangs.apple.service.UserService;

import lombok.AllArgsConstructor;

@RestController("PublishController")
@RequestMapping(value = "/api/normal/publish")
@AllArgsConstructor
public class PublishControll extends BaseApiController {

}
