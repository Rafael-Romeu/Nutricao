package com.romeu.nutricao.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionStatus {
	
	public HttpStatus getStatus();

}
