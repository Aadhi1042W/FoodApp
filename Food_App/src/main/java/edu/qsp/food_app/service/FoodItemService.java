package edu.qsp.food_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.qsp.food_app.entity.Item;
import edu.qsp.food_app.entity.Role;
import edu.qsp.food_app.entity.User;
import edu.qsp.food_app.exception.IdNotFoundToDeleteException;
import edu.qsp.food_app.exception.NoSuchIdFoundException;
import edu.qsp.food_app.exception.NotEligibleToAccessException;
import edu.qsp.food_app.fooddao.FoodItemDao;
import edu.qsp.food_app.fooddao.FoodUserDao;
import edu.qsp.food_app.util.ResponseStructure;

@Service
public class FoodItemService {

	@Autowired
	FoodItemDao idao;

	@Autowired
	FoodUserDao udao;

	public ResponseEntity<ResponseStructure<Item>> saveItem(Item item, long userId) {

		User user = udao.getById(userId);

		if (user != null) {

			Role role = user.getRole();
			if (role.equals(Role.ADMIN) || role.equals(Role.MANAGER)) {

				ResponseStructure<Item> responseStructure = new ResponseStructure<>();
				responseStructure.setStatus(HttpStatus.CREATED.value());
				responseStructure.setMessage("item added successfully");
				responseStructure.setData(idao.saveItem(item));

				ResponseEntity<ResponseStructure<Item>> responseEntity = new ResponseEntity<>(responseStructure,
						HttpStatus.CREATED);

				return responseEntity;

			} else
				throw new NotEligibleToAccessException();
		} else
			throw new NoSuchIdFoundException();
	}

	public ResponseEntity<ResponseStructure<Item>> findItemById(long itemId) {

		Item item = idao.findItemById(itemId);

		if (item != null) {

			ResponseStructure<Item> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("item details recieved successfully");
			responseStructure.setData(item);

			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new NoSuchIdFoundException();

		}
	}

	public ResponseEntity<ResponseStructure<List<Item>>> findAllItem() {

		ResponseStructure<List<Item>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("all item details recieved successfully");
		responseStructure.setData(idao.findAllItem());

		ResponseEntity<ResponseStructure<List<Item>>> responseEntity = new ResponseEntity<>(responseStructure,
				HttpStatus.OK);

		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Item>> updateItem(Item item, long itemId, long userId) {

		User user = udao.getById(userId);

		if (user != null) {

			Role role = user.getRole();

			if (role.equals(Role.ADMIN) || role.equals(Role.MANAGER)) {

				Item i = idao.findItemById(itemId);

				if (i != null) {

					item.setItemId(itemId);

					ResponseStructure<Item> responseStructure = new ResponseStructure<>();
					responseStructure.setStatus(HttpStatus.OK.value());
					responseStructure.setMessage("item details updated successfully");
					responseStructure.setData(item);

					ResponseEntity<ResponseStructure<Item>> responseEntity = new ResponseEntity<>(responseStructure,
							HttpStatus.OK);

					return responseEntity;
				} else
					throw new NoSuchIdFoundException();
			} else
				throw new NotEligibleToAccessException();
		} else
			throw new NoSuchIdFoundException();
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(long itemId, long userId) {

		User user = udao.getById(userId);

		if (user != null) {

			Role role = user.getRole();

			if (role.equals(Role.ADMIN) || role.equals(Role.MANAGER)) {

				Item item = idao.findItemById(itemId);

				if (item != null) {

					ResponseStructure<String> responseStructure = new ResponseStructure<>();
					responseStructure.setStatus(HttpStatus.OK.value());
					responseStructure.setMessage("item deleted successfully");
					responseStructure.setData(idao.deleteById(itemId));

					ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<>(responseStructure,
							HttpStatus.OK);

					return responseEntity;
				} else
					throw new IdNotFoundToDeleteException();
			} else
				throw new NotEligibleToAccessException();
		} else
			throw new NoSuchIdFoundException();
	}
}
