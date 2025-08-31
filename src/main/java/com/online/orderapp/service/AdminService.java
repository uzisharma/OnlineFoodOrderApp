package com.online.orderapp.service;

import com.online.orderapp.dto.adminDto.AdminLoginResponseDto;
import com.online.orderapp.dto.adminDto.AdminRequestDto;
import com.online.orderapp.dto.adminDto.AdminResponseDto;

public interface AdminService {

	AdminLoginResponseDto login(String userName, String password);

	AdminResponseDto saveAdmin(AdminRequestDto request);

}
