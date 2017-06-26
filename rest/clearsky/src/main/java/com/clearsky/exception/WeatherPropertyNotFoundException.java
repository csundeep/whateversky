package com.clearsky.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class WeatherPropertyNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public WeatherPropertyNotFoundException(String message) {
		super(message);
	}

	public WeatherPropertyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
