package edu.qsp.food_app.exception;

public class NotEligibleToAccessException extends RuntimeException {

	String message = "not eligible to access ";

	@Override
	public String getMessage() {
		return message;
	}

	public NotEligibleToAccessException(String message) {
		this.message = message;
	}

	public NotEligibleToAccessException() {

	}

}
