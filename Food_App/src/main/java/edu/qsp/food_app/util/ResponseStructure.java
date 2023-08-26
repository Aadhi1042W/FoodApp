package edu.qsp.food_app.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseStructure <T> {

	private int status;
	private String message;
	private T data;
	
	
}
