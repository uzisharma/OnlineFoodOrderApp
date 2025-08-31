package com.online.orderapp.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.orderapp.dto.ResponseStructure;
import com.online.orderapp.dto.orderDto.OrderPlacedDto;
import com.online.orderapp.dto.orderDto.PlaceOrderRequestDto;
import com.online.orderapp.entity.OrderPlaced;
import com.online.orderapp.mapper.OrderPlacedMapper;
import com.online.orderapp.service.OrderPlacedService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/place-order")
@AllArgsConstructor
public class OrderPlacedController {
	

	
	private final OrderPlacedService orderPlacedService;
	private final OrderPlacedMapper orderMapper;

	@PostMapping("/place")
	public ResponseEntity<ResponseStructure<OrderPlacedDto>> placeOrder(@RequestBody PlaceOrderRequestDto request) {
	    OrderPlaced orderPlaced = orderPlacedService.placeOrder(request.getCartId(), request.getPaymentStatus());
	    OrderPlacedDto response = orderMapper.toDto(orderPlaced);

	    ResponseStructure<OrderPlacedDto> apiResponse = new ResponseStructure<>();
	    apiResponse.setData(response);

	    // If the order already existed, return 200 instead of 201
	    boolean isExisting = orderPlaced.getOrderPlacedAt() != null 
	            && orderPlaced.getOrderPlacedAt().isBefore(LocalDateTime.now().minusSeconds(5));

	    if (isExisting) {
	        apiResponse.setMessage("Order already exists");
	        apiResponse.setStatusCode(HttpStatus.OK.value());
	        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	    } else {
	        apiResponse.setMessage("Order placed successfully");
	        apiResponse.setStatusCode(HttpStatus.CREATED.value());
	        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	    }
	}


}
