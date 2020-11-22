package com.gangs.apple.configuration.spring.security;

import lombok.Data;

@Data
public class AuthenticationBean {
    private String userName;
    private String password;
    private String wxOpenId;
    private boolean remember;
}
