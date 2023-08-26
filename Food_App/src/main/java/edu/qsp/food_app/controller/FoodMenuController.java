package edu.qsp.food_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.qsp.food_app.entity.FoodMenu;
import edu.qsp.food_app.service.FoodMenuService;
import edu.qsp.food_app.util.ResponseStructure;

@RestController 
public class FoodMenuController {
	
	@Autowired
	FoodMenuService service;

	@PostMapping("savefoodmenu")
	public ResponseEntity<ResponseStructure<FoodMenu>> saveFoodMenu(@RequestBody FoodMenu foodMenu,@RequestParam long userId) {
		
		return service.saveFoodMenu(foodMenu, userId);
	}
	
	@GetMapping("getFoodMenuById")
	public ResponseEntity<ResponseStructure<FoodMenu>> findFoodMenuById(@RequestParam long menuId) {
		
		return service.findFoodMenuById(menuId);
	}
	
	@GetMapping("getAllFoodMenu")
	public ResponseEntity<ResponseStructure<List<FoodMenu>>> findAllFoodMenu() {
		
		return service.findAllFoodMenu();
	}
	
	@PutMapping("updateFoodMenu/{menuId}/{userId}")
	public ResponseEntity<ResponseStructure<FoodMenu>> updateFoodMenu(@RequestBody FoodMenu foodMenu,@PathVariable long menuId,@PathVariable long userId) {
		
		return service.updateFoodMenu(foodMenu, menuId, userId);
	}
	
	@DeleteMapping("deleteByFoodId/{menuId}/{userId}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable long menuId,@PathVariable long userId) {
		
		return service.deleteByMenuId(menuId, userId);
	}
}
