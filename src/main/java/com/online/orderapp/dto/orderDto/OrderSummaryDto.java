package com.online.orderapp.dto.orderDto;

import lombok.Data;

@Data
public class OrderSummaryDto {
	
	private String foodName;
	private Integer quantity;
	private Double quantityPrice;
}
