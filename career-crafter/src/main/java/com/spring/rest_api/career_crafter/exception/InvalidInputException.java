package com.spring.rest_api.career_crafter.exception;


public class InvalidInputException extends Exception{

	private static final long serialVersionUID = 1L;

	private String message; 
	
	
	public InvalidInputException(String message) {
		super();
		this.message = message;
	}


	@Override
	public String getMessage() {
		return message;
	}
}