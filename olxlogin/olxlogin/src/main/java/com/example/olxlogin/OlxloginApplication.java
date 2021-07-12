package com.example.olxlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OlxloginApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxloginApplication.class, args);
	}

}
