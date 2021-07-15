package com.ghouse.coronax.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket swaggerConfigDocket() {

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.ghouse.coronax")).build().apiInfo(swaggerApiInfo());
	}

	public ApiInfo swaggerApiInfo() {

		return new ApiInfo("Corona X API", "Corona X-Spring Boot Application developed by Ghouse", "v 1.0",
				"No Terms & Conditions",
				new springfox.documentation.service.Contact("Ghouse", "gouse369.github.io", "thegouse369@gmail.com"),
				"GPL-2.0 License", "https://opensource.org/licenses/GPL-2.0", Collections.emptyList());

	}

}
