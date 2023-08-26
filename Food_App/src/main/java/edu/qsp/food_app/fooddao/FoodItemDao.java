package edu.qsp.food_app.fooddao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.qsp.food_app.entity.Item;
import edu.qsp.food_app.exception.NoSuchIdFoundException;
import edu.qsp.food_app.repository.FoodItemRepo;

@Repository
public class FoodItemDao {

	@Autowired
	FoodItemRepo repo;

	public Item saveItem(Item item) {

		return repo.save(item);
	}

	public Item findItemById(long itemId) {

		Optional<Item> optional = repo.findById(itemId);

		if (optional.isPresent()) {

			return optional.get();

		} else {
			throw new NoSuchIdFoundException();

		}
	}
	
	public List<Item> findAllItem() {
		
		return repo.findAll();
	}
	
	public Item updateItem(Item item) {
		
		return repo.save(item);
	}
	
	
	public String deleteById(long itemId) {
		
		repo.deleteById(itemId);
		return "item deleted successfully";
	}
}
