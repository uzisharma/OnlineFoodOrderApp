package com.online.orderapp.service;

import org.springframework.data.domain.Page;

import com.online.orderapp.dto.adminDto.AdminLoginResponseDto;
import com.online.orderapp.dto.adminDto.AdminRequestDto;
import com.online.orderapp.dto.adminDto.AdminResponseDto;

public interface AdminService {

	AdminLoginResponseDto login(String userName, String password);

	AdminResponseDto saveAdmin(AdminRequestDto request);

	Page<AdminResponseDto> getAllAdmin(int pageNum, int pageSize, String sortBy);

}
