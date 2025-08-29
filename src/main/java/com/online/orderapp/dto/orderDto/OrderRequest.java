package com.online.orderapp.dto.orderDto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {
	@NotNull
	private List<OrderItemRequest> orderItems;
	@NotNull(message="Restaurant id cannot be null")
	private Integer restaurantId;
}
