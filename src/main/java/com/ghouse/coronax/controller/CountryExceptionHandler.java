package com.ghouse.coronax.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ghouse.coronax.exceptions.ExceptionResponse;
import com.ghouse.coronax.exceptions.InvalidDataException;
import com.ghouse.coronax.exceptions.NoSuchCountryFound;

@ControllerAdvice
public class CountryExceptionHandler {
	@ExceptionHandler(value = InvalidDataException.class)

	public ResponseEntity<Object> exception(InvalidDataException exception) throws Exception {

		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage("Coronax API exception");
		ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		return entity;

	}
	
	@ExceptionHandler(value = NoSuchCountryFound.class)

	public ResponseEntity<Object> exception(NoSuchCountryFound exception) throws Exception {

		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage("Coronax API exception");
		ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		return entity;

	}

}
