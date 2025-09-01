package com.online.orderapp.dto.userDto;

import lombok.Data;

@Data
public class UserRequestDto {
	private Integer id;
	private String userName;
	private String email;
	private long contactNumber;
	private String address;
	private String gender;
}
