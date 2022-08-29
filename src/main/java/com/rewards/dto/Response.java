package com.rewards.dto;

import lombok.Data;

@Data
public class Response {
	
	private Object object;
	private boolean status;
	private String statusMessage;
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Response(Object object, boolean status, String statusMessage, String errorMessage) {
		super();
		this.object = object;
		this.status = status;
		this.statusMessage = statusMessage;
	}
	@Override
	public String toString() {
		return "Response [object=" + object + ", status=" + status + ", statusMessage=" + statusMessage
				+ "]";
	}
	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
