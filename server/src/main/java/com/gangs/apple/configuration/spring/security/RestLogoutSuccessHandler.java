package com.gangs.apple.configuration.spring.security;

import com.gangs.apple.base.SystemCode;
import com.gangs.apple.context.WebContext;
import com.gangs.apple.domain.User;
import com.gangs.apple.domain.other.UserEventLog;
import com.gangs.apple.event.UserEvent;
import com.gangs.apple.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 用户登出
 */
@Component
@AllArgsConstructor
public class RestLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    private final ApplicationEventPublisher eventPublisher;
    private final UserService userService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        if (null != springUser) {
            com.gangs.apple.domain.User user = userService.getUserByUserName(springUser.getUsername());
            UserEventLog userEventLog = new UserEventLog(user.getId(), user.getUserName(), user.getRealName(), new Date());
            userEventLog.setContent(user.getUserName() + " 登出了果业信息发布平台");
            eventPublisher.publishEvent(new UserEvent(userEventLog));
        }
        RestUtil.response(response, SystemCode.OK);
    }
}
