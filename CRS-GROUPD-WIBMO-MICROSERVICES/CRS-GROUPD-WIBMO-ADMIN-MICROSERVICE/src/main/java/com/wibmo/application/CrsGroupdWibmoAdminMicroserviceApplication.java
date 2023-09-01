package com.wibmo.application;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableAutoConfiguration
@ComponentScan("com.wibmo.*")
@Configuration
@EnableJpaRepositories("com.wibmo.repository")
@EntityScan(basePackages = "com.wibmo.entity")
@EnableEurekaClient
@SpringBootApplication
public class CrsGroupdWibmoAdminMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsGroupdWibmoAdminMicroserviceApplication.class, args);
	}

}
