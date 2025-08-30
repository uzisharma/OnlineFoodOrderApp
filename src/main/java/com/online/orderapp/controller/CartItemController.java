package com.online.orderapp.controller;

import org.springframework.http.HttpStatus;
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

	private final CartItemService cartItemService;

	@DeleteMapping("/{cartId}/delete")
	public ResponseEntity<String> deleteCartItem(@PathVariable Integer cartId){
		cartItemService.deleteByCartId(cartId);
		
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
}
