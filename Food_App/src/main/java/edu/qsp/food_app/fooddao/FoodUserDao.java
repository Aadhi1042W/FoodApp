package edu.qsp.food_app.fooddao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.qsp.food_app.entity.User;
import edu.qsp.food_app.repository.FoodUserRepo;

@Repository
public class FoodUserDao {

	@Autowired
	private FoodUserRepo repo;

	public User saveUser(User user) {

		return repo.save(user);

	}

	public User getById(long userId) {

		Optional<User> u = repo.findById(userId);

		if (u.isPresent()) {
			return u.get();
		} else {
			return null;

		}
	}

	public List<User> getAll() {

		return repo.findAll();
	}

	public User updateUser(User user) {

		return repo.save(user);
	}

	public String deleteById(long userId) {

		repo.deleteById(userId);

		return "details deleted successfully";
	}
}
