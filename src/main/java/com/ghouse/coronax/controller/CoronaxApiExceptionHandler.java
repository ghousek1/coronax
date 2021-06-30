package com.ghouse.coronax.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ghouse.coronax.exceptions.ExceptionResponse;
import com.ghouse.coronax.exceptions.NullCheckException;

@ControllerAdvice
public class CoronaxApiExceptionHandler {
	@ExceptionHandler(value = NullCheckException.class)
	public ResponseEntity<Object> exception(NullCheckException exception) throws Exception {
		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage("Coronax API - Null Check Exception");
		ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		return entity;
	}

}
