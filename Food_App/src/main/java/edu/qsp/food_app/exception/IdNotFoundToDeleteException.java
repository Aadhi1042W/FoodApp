package edu.qsp.food_app.exception;

public class IdNotFoundToDeleteException extends RuntimeException{

	String message="no data found in this ID";

	@Override
	public String getMessage() {
		return message;
	}

	public IdNotFoundToDeleteException(String message) {
		this.message = message;
	}

	public IdNotFoundToDeleteException() {
		
	}
	
	
	
	
	
}
