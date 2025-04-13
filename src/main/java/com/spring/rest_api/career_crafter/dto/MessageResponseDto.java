package com.spring.rest_api.career_crafter.dto;

import org.springframework.stereotype.Component;
@Component
public class MessageResponseDto {
	private String Message;
	private int status;
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public MessageResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MessageResponseDto(String message, int status) {
		super();
		Message = message;
		this.status = status;
	}
	
	

	

}

