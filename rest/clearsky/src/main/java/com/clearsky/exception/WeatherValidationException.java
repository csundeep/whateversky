package com.clearsky.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class WeatherValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public WeatherValidationException(String message) {
		super(message);
	}

	public WeatherValidationException(String message, Throwable cause) {
		super(message, cause);
	}

}
