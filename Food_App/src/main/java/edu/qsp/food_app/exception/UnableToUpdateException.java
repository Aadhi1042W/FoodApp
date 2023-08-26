package edu.qsp.food_app.exception;

public class UnableToUpdateException extends RuntimeException{

	String message="no data present to update in this ID";

	@Override
	public String getMessage() {
		
		return message;
	}

	public UnableToUpdateException(String message) {
		this.message = message;
	}

	public UnableToUpdateException() {
	
	}
	
	
	
	
}
