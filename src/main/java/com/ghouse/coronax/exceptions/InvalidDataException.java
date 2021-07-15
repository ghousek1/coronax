package com.ghouse.coronax.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidDataException extends RuntimeException{
	
	
	public InvalidDataException() {
		super();
	}
	public InvalidDataException(String exceptionMessage) {
		super(exceptionMessage);
	    log.error("Invalid Data Exception");
		
	}

}
