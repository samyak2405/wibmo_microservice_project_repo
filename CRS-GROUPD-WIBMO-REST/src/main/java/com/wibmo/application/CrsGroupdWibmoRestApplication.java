package com.wibmo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@EnableAutoConfiguration
@ComponentScan("D:\\wibmo_microservice_project_repo\\CRS-GROUPD-WIBMO-REST\\src\\main")
@EnableWebMvc
@Configuration
@SpringBootApplication

public class CrsGroupdWibmoRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsGroupdWibmoRestApplication.class, args);
	}
	
}
