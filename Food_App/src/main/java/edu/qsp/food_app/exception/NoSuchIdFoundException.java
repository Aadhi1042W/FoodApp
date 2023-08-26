package edu.qsp.food_app.exception;

public class NoSuchIdFoundException extends RuntimeException{

	String message="entered ID is not present enter valid ID";
	
	@Override
	public String getMessage() {
	
		return message;
	}

	public NoSuchIdFoundException(String message) {
		this.message = message;
	}

	public NoSuchIdFoundException() {
		
	}
	
	

	
}
