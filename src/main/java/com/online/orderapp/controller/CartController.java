package com.online.orderapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.orderapp.dto.AddToCartRequestDto;
import com.online.orderapp.dto.ResponseStructure;
import com.online.orderapp.entity.Cart;
import com.online.orderapp.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;	
	
	@PostMapping("/add")
	public ResponseEntity<ResponseStructure<Cart>> addFoodToCart(
			@RequestBody AddToCartRequestDto request
			){
		Cart response = cartService.addFoodToCart(
				request.getUserId(),
				request.getFoodId(),
				request.getQuantity());
		ResponseStructure<Cart> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Added To User Cart");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.ok(apiResponse);
	}
}
