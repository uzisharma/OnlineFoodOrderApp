package com.online.orderapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.online.orderapp.dto.UserLoginResponseDto;
import com.online.orderapp.dto.UserResponseDto;
import com.online.orderapp.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
	
	//login
	UserLoginResponseDto toLoginResponse(User user);
//	User toEntity(UserLoginRequestDto dto);

	
	//Retrieve user
	UserResponseDto toUserResponse(User user);

}
