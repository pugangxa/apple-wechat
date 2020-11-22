package com.gangs.apple.configuration.spring.security;

import com.gangs.apple.base.SystemCode;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 账号验证异常
 */
@Component
public class RestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(exception instanceof UserNotBindException) {
        	String openid = ((UserNotBindException)exception).getOpenId();
        	if(StringUtils.isEmpty(openid)) {
        		response.sendRedirect("/index.html#/wxerror");
        	}else {
            	response.sendRedirect("/index.html#/login?openid=" + openid);
        	}

        	//RestUtil.response(response, SystemCode.UNBIND.getCode(), exception.getMessage(), ((UserNotBindException)exception).getOpenId());
        }else {
        	RestUtil.response(response, SystemCode.AuthError.getCode(), exception.getMessage());
        }
    }
}
