package com.wibmo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@EnableJpaRepositories("com.wibmo.repository")

@EntityScan(basePackages = "com.wibmo.entity")


@EnableWebMvc

@EnableAutoConfiguration

@ComponentScan("com.wibmo.*")

@Configuration

@EnableCaching

@SpringBootApplication


public class DemoRedisProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRedisProjectApplication.class, args);
	}

}
