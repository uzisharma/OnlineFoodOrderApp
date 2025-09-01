package com.online.orderapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.online.orderapp.dto.userDto.UserLoginResponseDto;
import com.online.orderapp.dto.userDto.UserRequestDto;
import com.online.orderapp.dto.userDto.UserResponseDto;
import com.online.orderapp.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
	
	//login
	@Mapping(target="totalCartItem" , expression = "java(getTotalCartItem(user))")
	UserLoginResponseDto toLoginResponse(User user);

	default Integer getTotalCartItem(User user) {
		if(user.getUserCart() !=null && user.getUserCart().getUserCartItem() != null) {
			return user.getUserCart().getUserCartItem().getTotalCartItem();
		}
		return 0;
	}
	
	//Retrieve user
	UserResponseDto toUserResponse(User user);
	
	User toEntity(UserRequestDto userRequestDto);
	
	@Mapping(target="password", ignore=true)
	void updateUserFromDto(UserRequestDto dto, @MappingTarget User entity);

}
