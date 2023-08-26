package edu.qsp.food_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.qsp.food_app.entity.FoodMenu;

public interface FoodMenuRepo extends JpaRepository<FoodMenu, Long>{

	
}
