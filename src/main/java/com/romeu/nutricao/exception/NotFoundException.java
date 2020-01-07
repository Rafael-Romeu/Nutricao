package com.romeu.nutricao.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class NotFoundException extends Exception implements ExceptionStatus{
	
	private static final long serialVersionUID = 1L;
	
	private final String message;
	@Getter private final HttpStatus status = HttpStatus.NOT_FOUND;

    public NotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
