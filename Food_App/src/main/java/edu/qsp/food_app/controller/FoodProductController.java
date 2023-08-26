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

import edu.qsp.food_app.entity.FoodProduct;
import edu.qsp.food_app.service.FoodProductService;
import edu.qsp.food_app.util.ResponseStructure;

@RestController
public class FoodProductController {

	@Autowired
	FoodProductService service;
	
	@PostMapping("savefoodproduct")
	public ResponseEntity<ResponseStructure<FoodProduct>> saveFoodProduct(@RequestBody FoodProduct foodProduct,@RequestParam long userId) {
		
		return service.saveFoodProduct(foodProduct, userId);
	}
	
	@GetMapping("getfoodproductbyid")
	public ResponseEntity<ResponseStructure<FoodProduct>> findFoodProductById(@RequestParam  int productId) {
		
		return service.findFoodProductById(productId);
	}
	
	
	@GetMapping("getallfoodproduct")
	public ResponseEntity<ResponseStructure<List<FoodProduct>>> findAllFoodProduct() {
		
		return service.findAllFoodProduct();
	}
	
	@PutMapping("updatefoodproduct/{productId}/{userId}")
	public ResponseEntity<ResponseStructure<FoodProduct>> updateFoodProduct(@RequestBody FoodProduct foodProduct,@PathVariable int productId,@PathVariable long userId) {
		
		return service.updateFoodProduct(foodProduct, productId, userId);
	}
	
	@DeleteMapping("deletefoodproduct/{productId}/{userId}")
	public ResponseEntity<ResponseStructure<String>> deleteFoodproductById(@PathVariable int productId,@PathVariable long userId) {
		
		return service.deleteFoodproductById(productId, userId);
	}
}
