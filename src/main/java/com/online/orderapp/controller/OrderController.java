package com.online.orderapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.orderapp.dto.BillResponse;
import com.online.orderapp.dto.OrderRequest;
import com.online.orderapp.dto.PaymentDto;
import com.online.orderapp.dto.ResponseStructure;
import com.online.orderapp.entity.Order;
import com.online.orderapp.service.OrderService;
import com.online.orderapp.util.OrderStatus;

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
	
	
	@PostMapping("/pay-and-place-order")
	public ResponseEntity<ResponseStructure<String>> payAndPlaceOrder(@RequestBody PaymentDto payment){
		String data = orderService.payAndPlaceOrder(payment);
		ResponseStructure<String> apiResponse = new ResponseStructure<>();
		apiResponse.setData(data);
		apiResponse.setMessage("Order placed");
		apiResponse.setStatusCode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
	}
	
	@PatchMapping("/{id}/updateStatus")
	public ResponseEntity<ResponseStructure<Order>> updateStatus(@PathVariable Integer id, @RequestParam OrderStatus status){
		ResponseStructure<Order> apiResponse = new ResponseStructure<>();
		Order order = orderService.updateStatusByAdmin(status, id);
		apiResponse.setMessage("Order status updated to "+ status);
		apiResponse.setStatusCode(HttpStatus.OK.value());
		apiResponse.setData(order);
		
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<?> deleteOrder(@PathVariable Integer id) {
		orderService.deleteOrder(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping("/{id}cancelOrder")
	public ResponseEntity<ResponseStructure<String>> canceOrder(@PathVariable Integer id){
		String response = orderService.cancelOrder(id);
		ResponseStructure<String> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Order Cancelled");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
}
