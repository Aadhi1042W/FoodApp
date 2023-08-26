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

import edu.qsp.food_app.entity.Item;
import edu.qsp.food_app.service.FoodItemService;
import edu.qsp.food_app.util.ResponseStructure;

@RestController
public class FoodItemController {

	@Autowired
	FoodItemService service;

	@PostMapping("saveFoodItem")
	public ResponseEntity<ResponseStructure<Item>> saveItem(@RequestBody Item item, @RequestParam long userId) {

		return service.saveItem(item, userId);
	}

	@GetMapping("getItemById")
	public ResponseEntity<ResponseStructure<Item>> findItemById(@RequestParam long itemId) {

		return service.findItemById(itemId);
	}

	@GetMapping("getAllItem")
	public ResponseEntity<ResponseStructure<List<Item>>> findAllItem() {

		return service.findAllItem();
	}

	@PutMapping("updateItem/{itemId}/{userId}")
	public ResponseEntity<ResponseStructure<Item>> updateItem(@RequestBody Item item, @PathVariable long itemId,
			@PathVariable long userId) {

		return service.updateItem(item, itemId, userId);
	}

	@DeleteMapping("deleteItem/{itemId}/{userId}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable long itemId, @PathVariable long userId) {

		return service.deleteById(itemId, userId);
	}
}
