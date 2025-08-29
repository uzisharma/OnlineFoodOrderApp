package com.online.orderapp.dto.userDto;

import lombok.Data;

@Data
public class UserLoginRequestDto {
	private String userName;
	private String password;
}
