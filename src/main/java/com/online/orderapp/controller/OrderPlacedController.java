package com.online.orderapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.orderapp.dto.ResponseStructure;
import com.online.orderapp.dto.orderDto.OrderPlacedDto;
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

	@PostMapping("/place/{cartId}")
	public ResponseEntity<ResponseStructure<OrderPlacedDto>> placeOrder(@PathVariable Integer cartId){
		OrderPlaced orderPlaced=orderPlacedService.placeOrder(cartId);
		OrderPlacedDto response = orderMapper.toDto(orderPlaced);
		ResponseStructure<OrderPlacedDto> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Order placed successfully");
		apiResponse.setStatusCode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
		
		
	}
}
