package com.sis.footballteams.util;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class AbstractResponseDTO {

	private boolean success;
	
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
