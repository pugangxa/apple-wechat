package com.gangs.apple.event;

import org.springframework.context.ApplicationEvent;

import com.gangs.apple.domain.other.UserEventLog;

public class UserEvent extends ApplicationEvent {

    private final UserEventLog userEventLog;

    public UserEvent(final UserEventLog userEventLog) {
        super(userEventLog);
        this.userEventLog = userEventLog;
    }

    public UserEventLog getUserEventLog() {
        return userEventLog;
    }
}
