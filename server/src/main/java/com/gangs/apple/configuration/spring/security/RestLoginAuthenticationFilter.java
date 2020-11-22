package com.gangs.apple.configuration.spring.security;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.gangs.apple.configuration.property.CookieConfig;
import com.gangs.apple.configuration.property.SystemConfig;
import com.gangs.apple.utility.JsonUtil;

/**
 * 登录参数序列化
 */

public class RestLoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(RestLoginAuthenticationFilter.class);
    
    private final SystemConfig systemConfig;
    
    static private RestTemplate restTemplate = new RestTemplate();

    public RestLoginAuthenticationFilter(SystemConfig systemConfig) {
    	super(new AntPathRequestMatcher("/api/user/login", "GET"));
    	this.systemConfig = systemConfig;       
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    	AppleUserAuthenticationToken authRequest;
	    try {
	    	String wxOpenId = request.getParameter("wxOpenId");
	    	String username = request.getParameter("userName");
	    	String password = request.getParameter("password");
		    request.setAttribute(TokenBasedRememberMeServices.DEFAULT_PARAMETER, false);
	        //The login request is from wx then there's code https://blog.csdn.net/hongmin118/article/details/89305861
	        String code = request.getParameter("code");
			if(StringUtils.isEmpty(wxOpenId) && !StringUtils.isEmpty(code)) {
				String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=#APPID&secret=#SECRET&code=#code&grant_type=authorization_code";
				url = url.replace("#APPID", systemConfig.getWx().getAppId())
						.replace("#SECRET", systemConfig.getWx().getAppSecret())
						.replace("#code", code);
				
				//获取到openid
				ResponseEntity<String> wxResponse = restTemplate.getForEntity(url, String.class);
				if(wxResponse.getStatusCode() == HttpStatus.OK) {
					wxOpenId = JSONObject.parseObject(wxResponse.getBody()).getString("openid");
				}
			}
	        authRequest = new AppleUserAuthenticationToken(username, password, wxOpenId);
	    } catch (Exception e) {
	        logger.error(e.getMessage(), e);
	        authRequest = new AppleUserAuthenticationToken("", "", "");
	    }			

        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    void setUserDetailsService(UserDetailsService userDetailsService) {
        RestTokenBasedRememberMeServices tokenBasedRememberMeServices = new RestTokenBasedRememberMeServices(CookieConfig.getName(), userDetailsService);
        tokenBasedRememberMeServices.setTokenValiditySeconds(CookieConfig.getInterval());
        setRememberMeServices(tokenBasedRememberMeServices);
    }

    private void setDetails(HttpServletRequest request, AppleUserAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
