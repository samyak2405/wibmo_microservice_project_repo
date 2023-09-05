package com.wibmo.application;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Admin entry Applicaiton
 */


@EnableJpaRepositories("com.wibmo.repository")
@EntityScan(basePackages = "com.wibmo.entity")
@EnableSwagger2
@EnableWebMvc
@EnableAutoConfiguration
@ComponentScan("com.wibmo.*")
@Configuration
@EnableEurekaClient
@SpringBootApplication
public class CrsGroupdWibmoAdminMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsGroupdWibmoAdminMicroserviceApplication.class, args);
	}
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())

				.paths(PathSelectors.any()).build();

	}

	@Bean

	public InternalResourceViewResolver defaultViewResolver() {

		return new InternalResourceViewResolver();

	}

}
