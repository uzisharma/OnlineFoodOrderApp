package com.online.orderapp.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartDto {
	
	private Integer id;	
	
	private Integer restuarantId;
	
	
	private List<CartItemResponseDto> orderItem;
	
	private Integer userId;
	
	private double totalPrice;
}
