package com.wibmo.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaServer
public class CrsGroupdWibmoEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsGroupdWibmoEurekaApplication.class, args);
	}

}
