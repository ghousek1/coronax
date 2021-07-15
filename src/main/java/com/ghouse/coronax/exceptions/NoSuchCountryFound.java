package com.ghouse.coronax.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoSuchCountryFound extends RuntimeException{
	
	
	public NoSuchCountryFound() {
		super();
	}
	public NoSuchCountryFound(String exceptionMessage) {
		super(exceptionMessage);
	    log.error("Country Not Found Exception");
		
	}

}
