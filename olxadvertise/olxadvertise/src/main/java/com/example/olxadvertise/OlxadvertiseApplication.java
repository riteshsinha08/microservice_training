package com.example.olxadvertise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OlxadvertiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxadvertiseApplication.class, args);
	}

}
