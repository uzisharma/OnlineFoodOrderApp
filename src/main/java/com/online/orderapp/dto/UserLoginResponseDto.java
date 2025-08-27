package com.online.orderapp.dto;

import com.online.orderapp.entity.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginResponseDto {
	private Integer id;
	private String userName;
	private String email;	
	private Cart userCart;
}
