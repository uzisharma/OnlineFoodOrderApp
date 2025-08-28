package com.online.orderapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.orderapp.dto.AddToCartRequestDto;
import com.online.orderapp.dto.CartResponseDto;
import com.online.orderapp.dto.ResponseStructure;
import com.online.orderapp.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;	
	
	@PostMapping("/add")
	public ResponseEntity<ResponseStructure<CartResponseDto>> addFoodToCart(
			@RequestBody AddToCartRequestDto request
			){
		CartResponseDto response = cartService.addFoodToCart(
				request.getUserId(),
				request.getRestaurantId(),
				request.getFoodId(),
				request.getQuantity()
				);
		ResponseStructure<CartResponseDto> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Added To User Cart");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseStructure<CartResponseDto>> getUserCart(@PathVariable Integer id){
		CartResponseDto response = cartService.findCartByUserId(id);
		
		ResponseStructure<CartResponseDto> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("User found with id: "+ id);
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.ok(apiResponse);
	}
	
	@DeleteMapping("/{userId}/delete")
	public ResponseEntity<ResponseStructure<CartResponseDto>> clearCartByUserId(@PathVariable Integer userId){
	    CartResponseDto response = cartService.deleteCartByUserId(userId);

	    ResponseStructure<CartResponseDto> apiResponse = new ResponseStructure<>();
	    apiResponse.setData(response);
	    apiResponse.setMessage("Cart cleared for userId: " + userId);
	    apiResponse.setStatusCode(HttpStatus.OK.value());

	    return ResponseEntity.ok(apiResponse);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteCartById(@PathVariable Integer id){
		String response = cartService.deleteCartById(id);
		
		ResponseStructure<String> apiResponse = new ResponseStructure<>();
	    apiResponse.setData(response);
	    apiResponse.setMessage("Cart deleted for Id: " + id);
	    apiResponse.setStatusCode(HttpStatus.OK.value());
	    
	    return ResponseEntity.ok(apiResponse);
	}
	
}
