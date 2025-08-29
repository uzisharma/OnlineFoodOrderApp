package com.online.orderapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	@PostMapping("/add")
	public ResponseEntity<ResponseStructure<CheckoutResponseDto>> addToCheckout(@RequestBody CheckoutRequestDto checkoutRequestDto){
		Checkout checkout =checkoutService.addToCheckout(checkoutRequestDto);
		CheckoutResponseDto response = checkoutMapper.toDto(checkout);
		ResponseStructure<CheckoutResponseDto> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Added to checkout");
		apiResponse.setStatusCode(HttpStatus.ACCEPTED.value());
		
		return new ResponseEntity<>(apiResponse,HttpStatus.ACCEPTED);
		
	}
}
