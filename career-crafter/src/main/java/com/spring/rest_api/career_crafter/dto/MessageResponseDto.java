package com.spring.rest_api.career_crafter.dto;

import org.springframework.stereotype.Component;

@Component
public class MessageResponseDto {
	
	private String message;
	private int status;
	
	
	public MessageResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
