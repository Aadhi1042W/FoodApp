package edu.qsp.food_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.qsp.food_app.entity.FoodProduct;
import edu.qsp.food_app.entity.Item;
import edu.qsp.food_app.entity.Role;
import edu.qsp.food_app.entity.User;
import edu.qsp.food_app.exception.IdNotFoundToDeleteException;
import edu.qsp.food_app.exception.NoSuchIdFoundException;
import edu.qsp.food_app.exception.NotEligibleToAccessException;
import edu.qsp.food_app.fooddao.FoodProductDao;
import edu.qsp.food_app.fooddao.FoodUserDao;
import edu.qsp.food_app.util.ResponseStructure;

@Service
public class FoodProductService {

	@Autowired
	FoodProductDao pdao;

	@Autowired
	FoodUserDao udao;

	public ResponseEntity<ResponseStructure<FoodProduct>> saveFoodProduct(FoodProduct foodProduct, long userId) {

		User user = udao.getById(userId);

		if (user != null) {

			Role role = user.getRole();

			if (role.equals(Role.ADMIN) || role.equals(Role.MANAGER)) {

				ResponseStructure<FoodProduct> responseStructure = new ResponseStructure<>();
				responseStructure.setStatus(HttpStatus.CREATED.value());
				responseStructure.setMessage("food product details saved successfully");
				responseStructure.setData(pdao.saveFoodProduct(foodProduct));

				ResponseEntity<ResponseStructure<FoodProduct>> responseEntity = new ResponseEntity<>(responseStructure,
						HttpStatus.CREATED);

				return responseEntity;
			} else
				throw new NotEligibleToAccessException();
		} else
			throw new NoSuchIdFoundException();
	}

	public ResponseEntity<ResponseStructure<FoodProduct>> findFoodProductById(int productId) {

		FoodProduct foodProduct = pdao.findFoodProductById(productId);

		if (foodProduct != null) {

			ResponseStructure<FoodProduct> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("food products details recieved");
			responseStructure.setData(foodProduct);

			ResponseEntity<ResponseStructure<FoodProduct>> responseEntity = new ResponseEntity<>(responseStructure,
					HttpStatus.OK);

			return responseEntity;
		} else
			throw new NoSuchIdFoundException();
	}

	public ResponseEntity<ResponseStructure<List<FoodProduct>>> findAllFoodProduct() {

		ResponseStructure<List<FoodProduct>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("all product details recieved duccessfully");
		responseStructure.setData(pdao.findAllFoodProduct());

		ResponseEntity<ResponseStructure<List<FoodProduct>>> responseEntity = new ResponseEntity<>(responseStructure,
				HttpStatus.OK);

		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<FoodProduct>> updateFoodProduct(FoodProduct foodProduct, int productId,
			long userId) {

		User user = udao.getById(userId);

		if (user != null) {

			Role role = user.getRole();

			if (role.equals(Role.ADMIN) || role.equals(Role.MANAGER)) {

				FoodProduct fp = pdao.findFoodProductById(productId);

				if (fp != null) {

					foodProduct.setProductId(productId);
					ResponseStructure<FoodProduct> responseStructure = new ResponseStructure<>();
					responseStructure.setStatus(HttpStatus.OK.value());
					responseStructure.setMessage("item details updated successfully");
					responseStructure.setData(foodProduct);

					ResponseEntity<ResponseStructure<FoodProduct>> responseEntity = new ResponseEntity<>(
							responseStructure, HttpStatus.OK);

					return responseEntity;
				} else
					throw new NoSuchIdFoundException();
			} else
				throw new NotEligibleToAccessException();
		} else
			throw new NoSuchIdFoundException();
	}

	public ResponseEntity<ResponseStructure<String>> deleteFoodproductById(int productId, long userId) {

		User user = udao.getById(userId);

		if (user != null) {

			Role role = user.getRole();

			if (role.equals(Role.ADMIN) || role.equals(Role.MANAGER)) {

				FoodProduct foodProduct = pdao.findFoodProductById(productId);

				if (foodProduct != null) {

					ResponseStructure<String> responseStructure = new ResponseStructure<>();
					responseStructure.setStatus(HttpStatus.OK.value());
					responseStructure.setMessage("item deleted successfully");
					responseStructure.setData(pdao.deleteFoodproductById(productId));

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
