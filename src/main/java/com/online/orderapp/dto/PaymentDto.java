package com.online.orderapp.dto;

import java.util.List;

import lombok.Data;

@Data
public class PaymentDto {
	private List<OrderItemRequest> orderItems;
	private boolean PaymentSuccessful;

	private Integer restaurantId;
	
	private Integer userId;
}
