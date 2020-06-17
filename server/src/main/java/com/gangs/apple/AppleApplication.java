package com.gangs.apple;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.gangs.apple.configuration.property.SystemConfig;

/**
 * @author gang
 */
@SpringBootApplication
@EnableConfigurationProperties(value = { SystemConfig.class})
@MapperScan("com.gangs.apple.repository")
@EnableTransactionManagement
public class AppleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppleApplication.class, args);
	}
}
