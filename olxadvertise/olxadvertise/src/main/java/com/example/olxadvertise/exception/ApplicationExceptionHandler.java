package com.example.olxadvertise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(InvalidAuthorizationTokenException.class)
	public ResponseEntity<ErrorResponse> invalidAuthorizationTokenExceptionHandler(final InvalidAuthorizationTokenException e){
		ErrorResponse response=new ErrorResponse();
		response.setMessage("Invalid Auth token = "+e.getMessage());
		return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(HttpClientException.class)
	public ResponseEntity<ErrorResponse> httpClientExceptionHandler(final HttpClientException e){
		ErrorResponse response=new ErrorResponse();
		response.setMessage(e.getMessage());
		return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
	}
}
