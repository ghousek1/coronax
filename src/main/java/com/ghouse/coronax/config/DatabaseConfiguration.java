package com.ghouse.coronax.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Configuration
@ConfigurationProperties("spring.datasource")
@Slf4j
@Data
public class DatabaseConfiguration {

	private String driverClassName;
	private String url;
	private String username;
	private String password;


	@Profile("dev")
	@Bean
	public String devDatabaseConnection() {
		log.info("DB connection for DEV DataSource");
		log.info(driverClassName);
		log.info(url);
		return "DB connection for DEV DataSource";
	}

	@Profile("test")
	@Bean
	public String testDatabaseConnection() {
		log.info("DB Connection to TEST DataSource");
		log.info(driverClassName);
		log.info(url);
		return "DB Connection to Test DataSource";
	}

	@Profile("prod")
	@Bean
	public String prodDatabaseConnection() {
		log.info("DB Connection to PROD DataSource");
		log.info(driverClassName);
		log.info(url);
		return "DB Connection to PROD DataSource";
	}
}
