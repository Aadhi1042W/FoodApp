package edu.qsp.food_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.qsp.food_app.entity.Item;

public interface FoodItemRepo extends JpaRepository<Item, Long>{

}
