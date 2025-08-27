package com.online.orderapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginResponseDto {
	private Integer id;
	private String userName;
	private String email;
	private String gender;
	private Integer totalCartItem;
}
