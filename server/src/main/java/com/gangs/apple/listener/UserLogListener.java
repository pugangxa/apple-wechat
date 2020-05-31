package com.gangs.apple.listener;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.gangs.apple.configuration.spring.security.RestLoginAuthenticationFilter;
import com.gangs.apple.event.UserEvent;

@Component
public class UserLogListener implements ApplicationListener<UserEvent> {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(RestLoginAuthenticationFilter.class);

    @Override
    public void onApplicationEvent(UserEvent userEvent) {
        logger.info(userEvent.getUserEventLog().toString());
    }

}
