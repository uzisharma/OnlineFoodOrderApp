package com.online.orderapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.online.orderapp.dto.adminDto.AdminLoginResponseDto;
import com.online.orderapp.dto.adminDto.AdminRequestDto;
import com.online.orderapp.dto.adminDto.AdminResponseDto;
import com.online.orderapp.entity.Admin;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminMapper {

	AdminLoginResponseDto toLoginResponse(Admin admin);
	
	Admin toEntity(AdminRequestDto adminRequestDto);
	
	AdminResponseDto toDto(Admin admin);
}
