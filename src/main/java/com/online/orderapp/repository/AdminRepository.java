package com.online.orderapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.online.orderapp.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	@Query
	Optional<Admin> findByUserName(String userName);

}
