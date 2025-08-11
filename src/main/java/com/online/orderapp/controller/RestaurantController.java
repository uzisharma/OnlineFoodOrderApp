package com.online.orderapp.controller;

import java.util.List;
import java.util.Set;

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
import com.online.orderapp.service.RestaurantService;



@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
	
	@Autowired
//	@Qualifier() used to avoid the NoUniqueBeanDefinitionException
	private RestaurantService restaurantService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Restaurant>> createRestaurant(@RequestBody Restaurant restaurant){
		Restaurant response = restaurantService.createRestaurant(restaurant);
		ResponseStructure<Restaurant> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Restaurant Object is created");
		apiResponse.setStatusCode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseStructure<Restaurant>> fetchById(@PathVariable Integer id){
		Restaurant response = restaurantService.fetchById(id);
		ResponseStructure<Restaurant> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Restaurant Object Found");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
	
//	@GetMapping("/getAll")
//	public ResponseEntity<ResponseStructure<List<Restaurant>>> getAllRestaurant(){
//		List<Restaurant> response = restaurantService.getAllRestaurant();
//		ResponseStructure<List<Restaurant>> apiResponse = new ResponseStructure<>();
//		apiResponse.setData(response);
//		apiResponse.setMessage("Api ran Successfully");
//		apiResponse.setStatusCode(HttpStatus.OK.value());
//		
//		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
////		return ResponseEntity.ok(apiResponse); //another way to send ok response
//		
//	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<Page<?>>> getAllRestaurants(
			@RequestParam(defaultValue = "0", required = false) int pageNum,
			@RequestParam(defaultValue = "5", required = false) int pageSize,
			@RequestParam(defaultValue = "createdAt", required = false) String sortBy){
		Page<?> response = restaurantService.getAllRestaurants(pageNum, pageSize, sortBy);
		ResponseStructure<Page<?>> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Data Fetched Acoording to page");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.ok(apiResponse);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Restaurant>> updateRestaurant(@RequestParam int id, @RequestBody Restaurant restaurant){
		Restaurant response = restaurantService.updateRestaurant(id, restaurant);
		ResponseStructure<Restaurant> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Updated Successfully");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.ok(apiResponse);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable Integer id){
		 restaurantService.deleteRestaurant(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	
	}
	
	
	@PostMapping("/{restaurantId}/assign")
	public ResponseEntity<ResponseStructure<Restaurant>> assignFood(@PathVariable Integer restaurantId, @RequestBody Set<Integer> food){
		Restaurant restaurant = restaurantService.assignFood(restaurantId, food);
		ResponseStructure<Restaurant> apiResponse = new ResponseStructure<>();
		apiResponse.setData(restaurant);
		apiResponse.setMessage("Assigned");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	
	@GetMapping("/{id}/getAll")
	public ResponseEntity<ResponseStructure<List<Food>>> getFoodByRestaurantId(@PathVariable Integer id){
		ResponseStructure<List<Food>> apiResponse = new ResponseStructure<List<Food>>();
		apiResponse.setData(restaurantService.findFoodByRestaurantId(id));
		apiResponse.setMessage("Food item found");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
		
	}
	
}
