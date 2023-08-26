package edu.qsp.food_app.userexceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.qsp.food_app.exception.NotEligibleToAccessException;
import edu.qsp.food_app.util.ResponseStructure;

@RestControllerAdvice
public class FoodMenuExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(NotEligibleToAccessException.class)
	public ResponseEntity<ResponseStructure<String>> notEligibleToAccessException(NotEligibleToAccessException notEligibleToAccessException) {
		
		ResponseStructure<String> responseStructure =new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("this user does not have the access to alter food app");
		responseStructure.setData(notEligibleToAccessException.getMessage());
		
		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
}
