package com.online.orderapp.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.online.orderapp.entity.User;
import com.online.orderapp.util.Role;

public interface UserRepository extends JpaRepository<User, Integer>{

	
	@Query("SELECT u FROM User u LEFT JOIN FETCH u.userCart WHERE u.userName = :userName")
	Optional<User> findByUserNameWithCart(
			@Param("userName") String userName);

	Optional<User> findByUserName(String username);

	Page<User> findByRole(Role role, Pageable pageable);

}
