package edu.qsp.food_app.fooddao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.qsp.food_app.entity.FoodProduct;
import edu.qsp.food_app.exception.NoSuchIdFoundException;
import edu.qsp.food_app.repository.FoodProductRepo;

@Repository
public class FoodProductDao {

	@Autowired
	FoodProductRepo repo;

	public FoodProduct saveFoodProduct(FoodProduct foodProduct) {

		return repo.save(foodProduct);
	}

	public FoodProduct findFoodProductById(int productId) {

		Optional<FoodProduct> optional = repo.findById(productId);

		if (optional.isPresent()) {

			return optional.get();
		} else
			throw new NoSuchIdFoundException();

	}

	public List<FoodProduct> findAllFoodProduct() {

		return repo.findAll();
	}

	public FoodProduct updateFoodProduct(FoodProduct foodProduct) {

		return repo.save(foodProduct);
	}

	public String deleteFoodproductById(int productId) {

		repo.deleteById(productId);
		return "food product deleted successfully";
	}
}
