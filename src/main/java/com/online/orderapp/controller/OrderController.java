package com.online.orderapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.orderapp.dto.BillResponse;
import com.online.orderapp.dto.OrderRequest;
import com.online.orderapp.dto.ResponseStructure;
import com.online.orderapp.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


	@Autowired
	private OrderService orderService;

	@PostMapping("/bill-generate")
	public ResponseEntity<ResponseStructure<BillResponse>> gererateBill(@RequestBody OrderRequest orderRequest){
		BillResponse response = orderService.generatedBill(orderRequest);
		ResponseStructure<BillResponse> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Bill generated Successfully");
		apiResponse.setStatusCode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<ResponseStructure<BillResponse>>(apiResponse, HttpStatus.CREATED);
	}
}
