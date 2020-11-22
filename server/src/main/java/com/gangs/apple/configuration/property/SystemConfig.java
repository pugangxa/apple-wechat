package com.gangs.apple.configuration.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


@ConfigurationProperties(prefix = "system")
@Data
public class SystemConfig {
	private WxConfig wx;
    private PasswordKeyConfig pwdKey;
    private List<String> securityIgnoreUrls;
}
