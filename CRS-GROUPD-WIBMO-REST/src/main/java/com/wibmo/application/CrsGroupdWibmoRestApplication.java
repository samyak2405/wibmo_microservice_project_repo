package com.wibmo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication

@EnableAutoConfiguration

@ComponentScan("com.wibmo.*")

@EnableWebMvc

@Configuration

public class CrsGroupdWibmoRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsGroupdWibmoRestApplication.class, args);
	}
	
}
