package com.wibmo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Student Entry Application
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
public class CrsGroupdWibmoStudentMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsGroupdWibmoStudentMicroserviceApplication.class, args);
	}

}
