package edu.qsp.food_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.qsp.food_app.entity.FoodProduct;

public interface FoodProductRepo extends JpaRepository<FoodProduct, Integer>{

}
