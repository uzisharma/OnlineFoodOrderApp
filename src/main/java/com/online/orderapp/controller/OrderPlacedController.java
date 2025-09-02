package com.online.orderapp.controller;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.orderapp.dto.ResponseStructure;
import com.online.orderapp.dto.orderDto.OrderPlacedResponseDto;
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
	public ResponseEntity<ResponseStructure<OrderPlacedResponseDto>> placeOrder(@RequestBody PlaceOrderRequestDto request) {
	    OrderPlaced orderPlaced = orderPlacedService.placeOrder(request.getCartId(), request.getPaymentStatus());
	    OrderPlacedResponseDto response = orderMapper.toDto(orderPlaced);

	    ResponseStructure<OrderPlacedResponseDto> apiResponse = new ResponseStructure<>();
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
	
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<Page<OrderPlacedResponseDto>>> getAllOrders(
			@RequestParam(defaultValue = "0", required = false) int pageNum,
			@RequestParam(defaultValue = "5", required = false) int pageSize,
			@RequestParam(defaultValue = "createdAt", required = false) String sortBy){
		Page<OrderPlacedResponseDto> response = orderPlacedService.getAllOrders(pageNum, pageSize, sortBy);
		ResponseStructure<Page<OrderPlacedResponseDto>> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Data Fetched Acoording to page");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.ok(apiResponse);
	}
	


}
