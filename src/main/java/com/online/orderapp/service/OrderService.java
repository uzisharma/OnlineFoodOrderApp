package com.online.orderapp.service;

import com.online.orderapp.dto.BillResponse;
import com.online.orderapp.dto.OrderRequest;
import com.online.orderapp.dto.PaymentDto;

public interface OrderService {

	BillResponse generatedBill(OrderRequest orderRequest);
	
	String payAndPlaceOrder(PaymentDto payment);
	

}
