package com.sis.footballteams.util;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public class ErrorResponseDTO extends AbstractResponseDTO {

	public ErrorResponseDTO(final Exception e){
		setSuccess(false);
		setMessage(e.getMessage());
	}

	public ErrorResponseDTO() {
		setSuccess(false);
	}
}
