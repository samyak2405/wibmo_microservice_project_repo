package com.wibmo.apigatweay;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.wibmo.configuration.SpringCloudConfig;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
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
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
		.paths(PathSelectors.any()).build().securitySchemes(securitySchemes()).securityContexts(List.of(securityContext()));
	}
	private List<SecurityScheme> securitySchemes() {
        return List.of(new BasicAuth("basicAuth"), new ApiKey("Bearer", "Authorization", "header"));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(List.of(basicAuthReference(), bearerAuthReference())).forPaths(PathSelectors.ant("/admin/**")).build();
    }
    private SecurityReference basicAuthReference() {
        return new SecurityReference("basicAuth", new AuthorizationScope[0]);
    }

    private SecurityReference bearerAuthReference() {
        return new SecurityReference("Bearer", new AuthorizationScope[0]);
    }
	@Bean
	public InternalResourceViewResolver defaultViewResolver() {
	   return new InternalResourceViewResolver();
	}

}
