package com.online.orderapp.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartDto {
	
	private Integer id;	
	
	private Integer restuarantId;
	
	
	private List<CartItemDto> orderItem;
	
	private Integer userId;
}
