package com.gangs.apple.configuration.spring.security;

import org.springframework.security.core.AuthenticationException;

public class UserNotBindException extends AuthenticationException {
	
	private String openId;

	public UserNotBindException(String msg, String openId) {
		super(msg);
		this.setOpenId(openId);
	}


	public UserNotBindException(String msg, String openId, Throwable t) {
		super(msg, t);
		this.setOpenId(openId);
	}


	public String getOpenId() {
		return openId;
	}


	public void setOpenId(String openId) {
		this.openId = openId;
	}
}