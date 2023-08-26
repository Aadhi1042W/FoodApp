package edu.qsp.food_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.qsp.food_app.entity.FoodMenu;
import edu.qsp.food_app.entity.Role;
import edu.qsp.food_app.entity.User;
import edu.qsp.food_app.exception.IdNotFoundToDeleteException;
import edu.qsp.food_app.exception.NoSuchIdFoundException;
import edu.qsp.food_app.exception.NotEligibleToAccessException;
import edu.qsp.food_app.fooddao.FoodMenuDao;
import edu.qsp.food_app.fooddao.FoodUserDao;
import edu.qsp.food_app.util.ResponseStructure;

@Service
public class FoodMenuService {

	@Autowired
	FoodMenuDao fdao;

	@Autowired
	FoodUserDao udao;

	public ResponseEntity<ResponseStructure<FoodMenu>> saveFoodMenu(FoodMenu foodMenu, long userId) {

		ResponseStructure<FoodMenu> responseStructure = new ResponseStructure<>();

		User user = udao.getById(userId);

		if (user != null) {
			Role role = user.getRole();

			if (role.equals(Role.ADMIN) || role.equals(Role.MANAGER)) {

				responseStructure.setStatus(HttpStatus.CREATED.value());
				responseStructure.setMessage("food menu added successfully");
				responseStructure.setData(fdao.SaveFoodMenu(foodMenu));

				return new ResponseEntity<ResponseStructure<FoodMenu>>(responseStructure, HttpStatus.CREATED);
			} else {
				throw new NotEligibleToAccessException();
			}
		} else {

			throw new NoSuchIdFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<FoodMenu>> findFoodMenuById(long menuId) {

		FoodMenu foodMenu = fdao.findFoodMenuById(menuId);

		if (foodMenu != null) {

			ResponseStructure<FoodMenu> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("food details recived successfully");
			responseStructure.setData(foodMenu);

			ResponseEntity<ResponseStructure<FoodMenu>> responseEntity = new ResponseEntity<>(responseStructure,
					HttpStatus.OK);

			return responseEntity;

		} else {

			throw new NoSuchIdFoundException();
		}

	}

	public ResponseEntity<ResponseStructure<List<FoodMenu>>> findAllFoodMenu() {

		ResponseStructure<List<FoodMenu>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("data's recived successfully");
		responseStructure.setData(fdao.findAllFoodMenu());

		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<FoodMenu>> updateFoodMenu(FoodMenu foodMenu, long menuId, long userId) {

		User u = udao.getById(userId);

		if (u != null) {
			Role r = u.getRole();

			if (r.equals(Role.ADMIN) || r.equals(Role.MANAGER)) {

				FoodMenu fm = fdao.findFoodMenuById(menuId);

				ResponseStructure<FoodMenu> responseStructure = new ResponseStructure<>();
				if (fm != null) {

					foodMenu.setMenuId(menuId);



					responseStructure.setStatus(HttpStatus.OK.value());
					responseStructure.setMessage("food menu updated successfully");
					responseStructure.setData(fdao.updateFoodMenu(foodMenu));

					ResponseEntity<ResponseStructure<FoodMenu>> responseEntity = new ResponseEntity<>(responseStructure,
							HttpStatus.OK);

					return responseEntity;
				} else
					throw new NoSuchIdFoundException();
			} else
				throw new NotEligibleToAccessException();
		} else
			throw new NoSuchIdFoundException();
	}

	public ResponseEntity<ResponseStructure<String>> deleteByMenuId(long menuId, long userId) {

		ResponseStructure<String> responseStructure = new ResponseStructure<>();

		User user = udao.getById(userId);

		if (user != null) {
			Role role = user.getRole();

			if (role.equals(Role.ADMIN) || role.equals(Role.MANAGER)) {

				FoodMenu fm = fdao.findFoodMenuById(menuId);

				if (fm != null) {

					responseStructure.setStatus(HttpStatus.OK.value());
					responseStructure.setMessage("food menu deleted successfully");
					responseStructure.setData(fdao.deleteByMenuId(menuId));

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
