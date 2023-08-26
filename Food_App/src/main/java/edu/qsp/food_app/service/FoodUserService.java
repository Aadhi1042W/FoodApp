  package edu.qsp.food_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.qsp.food_app.entity.User;
import edu.qsp.food_app.exception.IdNotFoundToDeleteException;
import edu.qsp.food_app.exception.NoSuchIdFoundException;
import edu.qsp.food_app.exception.UnableToUpdateException;
import edu.qsp.food_app.fooddao.FoodUserDao;
import edu.qsp.food_app.util.ResponseStructure;

@Service
public class FoodUserService {

	@Autowired
	private FoodUserDao dao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {

		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("data saved successfully");
		responseStructure.setData(dao.saveUser(user));

		ResponseEntity<ResponseStructure<User>> responseEntity = new ResponseEntity<>(responseStructure,
				HttpStatus.CREATED);

		return responseEntity;

	}

	public ResponseEntity<ResponseStructure<User>> getById(long userId) {

		User user = dao.getById(userId);

		if (user != null) {

			ResponseStructure<User> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("data recived successfully");
			responseStructure.setData(user);

			ResponseEntity<ResponseStructure<User>> responseEntity = new ResponseEntity<>(responseStructure,
					HttpStatus.OK);

			return responseEntity;
		} else {

			throw new NoSuchIdFoundException();
		}

	}

	public ResponseEntity<ResponseStructure<List<User>>> getAll() {

		ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("datas recieved successfully");
		responseStructure.setData(dao.getAll());

		ResponseEntity<ResponseStructure<List<User>>> responseEntity = new ResponseEntity<>(responseStructure,
				HttpStatus.OK);

		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(long userId, User user) {

		User u = dao.getById(userId);

		if (u != null) {
			
			user.setUserid(userId);

			ResponseStructure<User> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("details updated successfully");
			responseStructure.setData(dao.updateUser(user));

			ResponseEntity<ResponseStructure<User>> responseEntity = new ResponseEntity<>(responseStructure,HttpStatus.OK);

			return responseEntity;
		} else {
			throw new UnableToUpdateException();

		}
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteById(long userId) {
		
	User user=	dao.getById(userId);
	
		if (user!=null) {
			
			ResponseStructure<String> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("details deleted successfully");
			responseStructure.setData(dao.deleteById(userId));
			
			
			ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<>(responseStructure,HttpStatus.OK);
			
			return responseEntity;
			
		} else {

			throw new IdNotFoundToDeleteException();
		}
		
		
	}
}
