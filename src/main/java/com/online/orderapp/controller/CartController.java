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

import com.online.orderapp.dto.CartDto;
import com.online.orderapp.dto.ResponseStructure;
import com.online.orderapp.service.CartService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {

	private final CartService cartService;
	
	@PostMapping("/add")
	public CartDto addToCart(@RequestBody CartDto cartDto) {
		return cartService.addToCart(cartDto);
	}
	
    
    @GetMapping("/{userId}")
    public ResponseEntity<ResponseStructure<CartDto>> getCart(@PathVariable Integer userId){
    	CartDto response = cartService.getCartByUser(userId);
    	ResponseStructure<CartDto> apiResponse = new ResponseStructure<>();
    	apiResponse.setData(response);
    	apiResponse.setMessage("Cart of the User Id : "+userId );
    	apiResponse.setStatusCode(HttpStatus.FOUND.value());
 
    	return new ResponseEntity<>(apiResponse, HttpStatus.FOUND);
    	
    }
    

    @DeleteMapping("/{userId}")
    public void clearCart(@PathVariable Integer userId) {
        cartService.clearCart(userId);
    }
}
