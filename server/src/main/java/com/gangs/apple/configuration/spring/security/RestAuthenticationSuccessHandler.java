package com.gangs.apple.configuration.spring.security;

import com.gangs.apple.base.SystemCode;
import com.gangs.apple.context.WebContext;
import com.gangs.apple.domain.enums.RoleEnum;
import com.gangs.apple.domain.other.UserEventLog;
import com.gangs.apple.event.UserEvent;
import com.gangs.apple.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 登录成功返回
 *
 */
@Component
@AllArgsConstructor
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final ApplicationEventPublisher eventPublisher;
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User springUser = (User) authentication.getPrincipal();
        com.gangs.apple.domain.User user = userService.getUserByUserName(springUser.getUsername());
        UserEventLog userEventLog = new UserEventLog(user.getId(), user.getUserName(), user.getRealName(), new Date());
        userEventLog.setContent(user.getUserName() + " 登录了果业信息发布平台");
        eventPublisher.publishEvent(new UserEvent(userEventLog));
        com.gangs.apple.domain.User newUser = new com.gangs.apple.domain.User();
        newUser.setUserName(user.getUserName());
        if(RoleEnum.fromCode(user.getRole()) == RoleEnum.ADMIN) {
        	RestUtil.response(response, SystemCode.OK.getCode(), SystemCode.OK.getMessage(), newUser);
        }else {
        	response.sendRedirect("/index.html#/dashboard/index");
        }
        
        //RestUtil.response(response, SystemCode.OK.getCode(), SystemCode.OK.getMessage(), newUser);
    }
}
