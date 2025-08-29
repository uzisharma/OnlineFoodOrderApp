package com.online.orderapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.orderapp.dto.ResponseStructure;
import com.online.orderapp.dto.checkoutDto.CheckoutRequestDto;
import com.online.orderapp.dto.checkoutDto.CheckoutResponseDto;
import com.online.orderapp.entity.Checkout;
import com.online.orderapp.mapper.CheckoutMapper;
import com.online.orderapp.service.CheckoutService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/checkout")
public class CheckoutController {
	
	private final CheckoutService checkoutService;
	private final CheckoutMapper checkoutMapper;
	
	
	@PostMapping("/add/{cartId}")
	public ResponseEntity<ResponseStructure<CheckoutResponseDto>> addToCheckout(@PathVariable Integer cartId){
		CheckoutRequestDto checkoutRequestDto = new CheckoutRequestDto();
		checkoutRequestDto.setCartId(cartId);
		Checkout checkout =checkoutService.addToCheckout(checkoutRequestDto);
		CheckoutResponseDto response = checkoutMapper.toDto(checkout);
		ResponseStructure<CheckoutResponseDto> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Added to checkout");
		apiResponse.setStatusCode(HttpStatus.ACCEPTED.value());
		
		return new ResponseEntity<>(apiResponse,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/{userId}/delete")
	public ResponseEntity<ResponseStructure<String>> deleteCheckout(@PathVariable Integer userId){
		String response = checkoutService.deleteCheckout(userId);
		ResponseStructure<String> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Checkout Record Deleted");
		apiResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
		
		return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
	}
}
