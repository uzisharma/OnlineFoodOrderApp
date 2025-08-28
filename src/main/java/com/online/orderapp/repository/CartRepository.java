package com.online.orderapp.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.orderapp.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	Optional<Cart> findByUserId(Integer id);


}
