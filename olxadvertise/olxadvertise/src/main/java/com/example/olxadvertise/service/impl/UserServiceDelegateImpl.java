package com.example.olxadvertise.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.olxadvertise.exception.HttpClientException;
import com.example.olxadvertise.service.UserServiceDelegate;

@Service
public class UserServiceDelegateImpl implements UserServiceDelegate {
	
	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	private RestTemplate restTemplate;
	
	private CircuitBreaker circuitBreaker;

	@PostConstruct
	public void setUp() {
		circuitBreaker=circuitBreakerFactory.create("AUTH_TOKEN_VALIDATION");
		restTemplate = restTemplateBuilder.build();
	}

	@Override
	public boolean isLoggedInUser(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Boolean> response=circuitBreaker.run(()-> restTemplate.exchange("http://auth-service/token/validate", HttpMethod.GET, entity,
				Boolean.class),throwable->fallBackForisLoggedInUser(token,throwable)
				);
		return response.getBody();
		
	}

	private ResponseEntity<Boolean> fallBackForisLoggedInUser(String token, Throwable throwable) {
		System.out.println("Login service failed"+throwable);
		return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public List<Map> findByUserNames(String usernames) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map findByUserName(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Map> response = null;
		try {
			response = restTemplate.exchange("http://localhost:7000/user", HttpMethod.GET, entity, Map.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			throw new HttpClientException(e.getResponseBodyAsString());
		}

	}
}
