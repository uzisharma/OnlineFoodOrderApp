package com.online.orderapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.orderapp.dto.ResponseStructure;
import com.online.orderapp.entity.Food;
import com.online.orderapp.entity.Restaurant;
import com.online.orderapp.service.FoodService;

@RestController
@RequestMapping("api/food")
public class FoodController {


	@Autowired
	private FoodService foodService;

	
	@PostMapping("/addFood")
	public ResponseEntity<ResponseStructure<Food>> createFood(@RequestBody Food food){
		Food response = foodService.createFood(food);
		ResponseStructure<Food> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Food Object Added");
		apiResponse.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseStructure<Food>> getById(@PathVariable Integer id ){
		Food responseFood = foodService.getFoodById(id);
		ResponseStructure<Food> apiResponse = new ResponseStructure<>();
		apiResponse.setData(responseFood);
		apiResponse.setMessage("Food Object Found");
		apiResponse.setStatusCode(HttpStatus.FOUND.value());
		
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<Page<Food>>> getAllFood(
				@RequestParam(defaultValue="0", required= false) int pageNum, 
				@RequestParam(defaultValue = "4", required=false) int PageSize
				){
		Page<Food> response = foodService.getAllFood(pageNum, PageSize);
		ResponseStructure<Page<Food>> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Data Fetched Acoording to page");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.ok(apiResponse);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Food>> updateFood(@RequestParam Integer id, @RequestBody Food food){
		Food response = foodService.updateFood(food, id);
		ResponseStructure<Food> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Updated Successfully");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.ok(apiResponse);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable Integer id){
		 foodService.deleteFood(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	
	}
	
	
	
}
