package com.wibmo.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan("com.wibmo.*")
@Configuration
@EnableAutoConfiguration
public class CrsGroupdWibmoApacheKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsGroupdWibmoApacheKafkaApplication.class, args);
	}

}
