package com.online.orderapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
	private Integer id;
	private String userName;
	private String email;
	private long contactNumber;
	private String address;
}
