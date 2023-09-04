package com.wibmo.apigatweay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.wibmo.configuration.SpringCloudConfig;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableAutoConfiguration
@EnableSwagger2
@Configuration
@Import({SpringCloudConfig.class})
@EnableEurekaClient
public class CrsWibmoApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsWibmoApiGatewayApplication.class, args);
	}

}
