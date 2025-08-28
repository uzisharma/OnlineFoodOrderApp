package com.online.orderapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.orderapp.service.CartItemService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/cart-item")
@AllArgsConstructor
public class CartItemController {
	
//	private final CartItemRepository cartItemRepository;
	private final CartItemService cartItemService;
	
	@DeleteMapping("/delete/{cartItemId}")
	public ResponseEntity<String> deleteCartItem(@PathVariable Integer cartItemId){
		cartItemService.deleteCartItem(cartItemId);
		
		
		return ResponseEntity.ok("Deleted");
	}

}
