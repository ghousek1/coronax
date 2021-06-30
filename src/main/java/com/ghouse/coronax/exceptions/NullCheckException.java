package com.ghouse.coronax.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NullCheckException extends RuntimeException {

	public NullCheckException() {
		super();
	}

	public NullCheckException(String exceptionMessage) {
		super(exceptionMessage);
		log.error("Null Check Exception ");

	}

}
