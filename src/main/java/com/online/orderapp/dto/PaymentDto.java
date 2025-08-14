package com.online.orderapp.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentDto {
	@NotNull
	private List<OrderItemRequest> orderItems;
	@NotNull
	private boolean PaymentSuccessful;
	@NotNull
	private Integer restaurantId;
	@NotNull
	private Integer userId;
}
