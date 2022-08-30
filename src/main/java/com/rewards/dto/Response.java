package com.rewards.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Response {
	
	private Object object;
	private HttpStatus status;
	private String message;


	public Response(Object object, HttpStatus status, String message) {
		super();
		this.object = object;
		this.status = status;
		this.message = message;
	}
	@Override
	public String toString() {
		return "Response [object=" + object + ", status=" + status + ", message=" + message
				+ "]";
	}
	public Response() {
		super();
	}

	
	
}
