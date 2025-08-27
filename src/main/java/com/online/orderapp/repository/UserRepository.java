package com.online.orderapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.online.orderapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
//	
//	@Query("SELECT u FROM User u WHERE u.userName=:userName AND u.password=:password")
//	Optional<User> loginAuth(@Param("userName") String userName, @Param("password") String password);
//	
//	
	
//	@Query("SELECT new com.online.orderapp.dto.UserLoginResponseDto(u.id, u.userName, u.email)" +
//			"FROM User u WHERE u.userName=:userName AND u.password=:password"
//			)
//	Optional<UserLoginResponseDto> loginAuth(
//				@Param("userName") String userName,
//				@Param("password") String password
//			);
	
	
//	Optional<User> findByUserName(String userName);
	
	@Query("SELECT u FROM User u LEFT JOIN FETCH u.userCart WHERE u.userName = :userName")
	Optional<User> findByUserNameWithCart(
			@Param("userName") String userName);

}
