package edu.qsp.food_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.qsp.food_app.entity.User;
import edu.qsp.food_app.service.FoodUserService;
import edu.qsp.food_app.util.ResponseStructure;

@RestController
public class FoodUserController {

	@Autowired
	private FoodUserService service;
	
	@PostMapping("save")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		
		return service.saveUser(user);
	}
	
	
	@GetMapping("getById")
	public ResponseEntity<ResponseStructure<User>> getById(@RequestParam long userId) {
		
		return service.getById(userId);
		
	}
	
	@GetMapping("getAll")
	public ResponseEntity<ResponseStructure<List<User>>> getAll() {
		
		return service.getAll();
	}
	
	@PutMapping("updateUser")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestParam long userId,@RequestBody User user) {
		
		return service.updateUser(userId, user);
	}
	
	@DeleteMapping("deleteById")
	public ResponseEntity<ResponseStructure<String>> deleteById(@RequestParam long userId) {
		
		return service.deleteById(userId);
	}
}
