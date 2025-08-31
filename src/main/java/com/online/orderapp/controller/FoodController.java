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
import com.online.orderapp.dto.foodDto.FoodRequestDto;
import com.online.orderapp.dto.foodDto.FoodResponseDto;
import com.online.orderapp.service.FoodService;

@RestController
@RequestMapping("/api/food")
public class FoodController {


	@Autowired
	private FoodService foodService;

	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<FoodResponseDto>> createFood(@RequestBody FoodRequestDto request){
		FoodResponseDto response = foodService.createFood(request);
		ResponseStructure<FoodResponseDto> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Food Object Added");
		apiResponse.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseStructure<FoodResponseDto>> getById(@PathVariable Integer id ){
		FoodResponseDto responseFood = foodService.getFoodById(id);
		ResponseStructure<FoodResponseDto> apiResponse = new ResponseStructure<>();
		apiResponse.setData(responseFood);
		apiResponse.setMessage("Food Object Found");
		apiResponse.setStatusCode(HttpStatus.FOUND.value());
		
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<Page<FoodResponseDto>>> getAllFood(
				@RequestParam(defaultValue="0", required= false) int pageNum, 
				@RequestParam(defaultValue = "10", required=false) int PageSize
				){
		Page<FoodResponseDto> response = foodService.getAllFood(pageNum, PageSize);
		ResponseStructure<Page<FoodResponseDto>> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Data Fetched Acoording to page");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.ok(apiResponse);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<FoodResponseDto>> updateFood(@RequestParam Integer id, @RequestBody FoodRequestDto request){
		FoodResponseDto response = foodService.updateFood(request, id);
		ResponseStructure<FoodResponseDto> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Updated Successfully");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.ok(apiResponse);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<FoodResponseDto> deleteFood(@PathVariable Integer id){
		 foodService.deleteFood(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	
	}
	
	
	
}
