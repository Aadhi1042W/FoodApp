package edu.qsp.food_app.fooddao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.qsp.food_app.entity.FoodMenu;
import edu.qsp.food_app.repository.FoodMenuRepo;

@Repository
public class FoodMenuDao {

	@Autowired
	FoodMenuRepo repo;

	public FoodMenu SaveFoodMenu(FoodMenu foodMenu) {

		return repo.save(foodMenu);
	}

	public FoodMenu findFoodMenuById(long menuId) {

		Optional<FoodMenu> optional = repo.findById(menuId);
		
		return optional.isPresent() ? optional.get() : null;

	}
	
	
	public  List<FoodMenu> findAllFoodMenu() {
		
		return repo.findAll();
	}
	
	
	public FoodMenu updateFoodMenu(FoodMenu foodMenu) {
		
		return repo.save(foodMenu);
	}
	
	public String deleteByMenuId(long menuId) {
		
		repo.deleteById(menuId);
		
		return "food menu deleted successfully";
	}
}
