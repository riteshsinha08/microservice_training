package com.example.olxadvertise.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	 @Bean
	    public Docket api() { 
		 return new Docket(DocumentationType.SWAGGER_2)
				 .select()
				 .apis(RequestHandlerSelectors.basePackage("com.example.olxadvertise.controller"))  
				 .paths(PathSelectors.any())
				 .build()
				 .apiInfo(apiInfo());
	    }
	 
	 private ApiInfo apiInfo() {
		    return new ApiInfo(
		      "OLX Advertise Service", 
		      "APIs to post and fetch Advertise details", 
		      "API TOS", 
		      "Terms of service", 
		      new Contact("John Doe", "www.example.com", "myeaddress@company.com"), 
		      "License of API", "API license URL", Collections.emptyList());
		}
}
