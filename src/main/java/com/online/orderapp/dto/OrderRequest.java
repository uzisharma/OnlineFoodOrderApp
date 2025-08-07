package com.online.orderapp.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {
	private List<OrderItemRequest> orderItems;
	private Integer restaurantId;
}
