package com.online.orderapp.service;

import com.online.orderapp.dto.BillResponse;
import com.online.orderapp.dto.PaymentDto;
import com.online.orderapp.dto.orderDto.OrderRequest;
import com.online.orderapp.entity.Order;
import com.online.orderapp.util.OrderStatus;

public interface OrderService {

	BillResponse generatedBill(OrderRequest orderRequest);
	
	String payAndPlaceOrder(PaymentDto payment);
	
	void deleteOrder(Integer id);
	
	Order getOrder(Integer id);
	
	Order updateStatusByAdmin(OrderStatus status, Integer id);
	
	String cancelOrder(Integer id);
	

}
