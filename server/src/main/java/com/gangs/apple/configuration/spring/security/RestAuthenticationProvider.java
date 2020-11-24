package com.gangs.apple.configuration.spring.security;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.gangs.apple.context.WebContext;
import com.gangs.apple.domain.enums.RoleEnum;
import com.gangs.apple.domain.enums.UserStatusEnum;
import com.gangs.apple.service.AuthenticationService;
import com.gangs.apple.service.UserService;

/**
 * 登录用户名密码验证
 *
 */

@Component
public class RestAuthenticationProvider implements AuthenticationProvider {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final WebContext webContext;

    @Autowired
    public RestAuthenticationProvider(AuthenticationService authenticationService, UserService userService, WebContext webContext) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.webContext = webContext;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        String openid = (String)((AppleUserAuthenticationToken)authentication).getWxOpenId();
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        com.gangs.apple.domain.User userByName = userService.getUserByUserName(username);
        
		if(StringUtils.isEmpty(openid)) {
			if( StringUtils.isEmpty(username)) {
				throw new UserNotBindException("请先关注本公众号", openid);
			}else {
				//check whether it's admin
        		if(null == userByName) {
        			throw new BadCredentialsException("用户名或密码错误");
        		}
		        if(RoleEnum.fromCode(userByName.getRole()) == RoleEnum.ADMIN) {
		        	boolean result = authenticationService.authUser(userByName, username, password);
	                if (!result) {
	                    throw new BadCredentialsException("用户名或密码错误");
	                }
	                grantedAuthorities.add(new SimpleGrantedAuthority(RoleEnum.fromCode(userByName.getRole()).getRoleName()));
	                grantedAuthorities.add(new SimpleGrantedAuthority(RoleEnum.USER.getRoleName()));
	                User authUser = new User(userByName.getUserName(), userByName.getPassword(), grantedAuthorities);
	                return new UsernamePasswordAuthenticationToken(authUser, authUser.getPassword(), authUser.getAuthorities());
		        }
			}
		}
		
		//Now it's normal user
        com.gangs.apple.domain.User user = userService.getUserByOpenId(openid);
        
        if (user == null) {
        	if( StringUtils.isEmpty(username)) {
            	//用户未绑定，redirect 到login page
                throw new UserNotBindException("用户未绑定", openid);
        	}else {
        		if(null == userByName) {
        			throw new BadCredentialsException("用户名或密码错误");
        		}
        		//must be from login page, 验证并绑定用户
                boolean result = authenticationService.authUser(userByName, username, password);
                if (!result) {
                    throw new BadCredentialsException("用户名或密码错误");
                }
                UserStatusEnum userStatusEnum = UserStatusEnum.fromCode(userByName.getStatus());
                if (UserStatusEnum.Disable == userStatusEnum) {
                    throw new LockedException("用户已禁用");
                }
                userByName.setWxOpenId(openid);
                userService.updateById(userByName);
                //return AppleUserAuthenticationToken so that we know it's from login page
                grantedAuthorities.add(new SimpleGrantedAuthority(RoleEnum.fromCode(userByName.getRole()).getRoleName()));

                User authUser = new User(userByName.getUserName(), userByName.getPassword(), grantedAuthorities);
                return new AppleUserAuthenticationToken(authUser, authUser.getPassword(), openid, authUser.getAuthorities());
        	}
        }
        //openid already bind to a user, just login succeeded and return UsernamePasswordAuthenticationToken
        grantedAuthorities.add(new SimpleGrantedAuthority(RoleEnum.fromCode(user.getRole()).getRoleName()));

        User authUser = new User(user.getUserName(), user.getPassword(), grantedAuthorities);
        return new UsernamePasswordAuthenticationToken(authUser, authUser.getPassword(), authUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
