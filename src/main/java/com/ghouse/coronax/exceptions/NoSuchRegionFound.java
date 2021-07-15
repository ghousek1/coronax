package com.ghouse.coronax.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoSuchRegionFound extends RuntimeException{
	
	
	public NoSuchRegionFound() {
		super();
	}
	public NoSuchRegionFound(String exceptionMessage) {
		super(exceptionMessage);
	    log.error("Region Not Found Exception");
		
	}

}
