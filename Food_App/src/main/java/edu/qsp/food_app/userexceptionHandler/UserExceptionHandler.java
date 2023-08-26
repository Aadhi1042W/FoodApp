package edu.qsp.food_app.userexceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.qsp.food_app.entity.User;
import edu.qsp.food_app.exception.IdNotFoundToDeleteException;
import edu.qsp.food_app.exception.NoSuchIdFoundException;
import edu.qsp.food_app.exception.NotEligibleToAccessException;
import edu.qsp.food_app.exception.UnableToUpdateException;
import edu.qsp.food_app.util.ResponseStructure;

@RestControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoSuchIdFoundException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchIdFoundException(
			NoSuchIdFoundException noSuchIdFoundException) {

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("ID Not Found Enter Valid ID");
		responseStructure.setData(noSuchIdFoundException.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<>(responseStructure,
				HttpStatus.NOT_FOUND);

		return responseEntity;
	}

	@ExceptionHandler(UnableToUpdateException.class)
	public ResponseEntity<ResponseStructure<String>> unableToUpdateException(
			UnableToUpdateException unableToUpdateException) {

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("unable to update");
		responseStructure.setData(unableToUpdateException.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<>(responseStructure,
				HttpStatus.NOT_FOUND);

		return responseEntity;
	}

	@ExceptionHandler(IdNotFoundToDeleteException.class)
	public ResponseEntity<ResponseStructure<String>> idNotFoundToDeleteException(
			IdNotFoundToDeleteException idNotFoundToDeleteException) {

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("id not found to delete");
		responseStructure.setData(idNotFoundToDeleteException.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<>(responseStructure,
				HttpStatus.NOT_FOUND);

		return responseEntity;
	}
	
	
}
