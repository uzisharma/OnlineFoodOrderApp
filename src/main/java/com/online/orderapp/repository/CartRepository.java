package com.online.orderapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.online.orderapp.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	@Query
	Optional<Cart> findByUserId(Integer userId);

	void deleteByUserId(Integer id);
}
