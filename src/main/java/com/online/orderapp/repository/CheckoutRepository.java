package com.online.orderapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.online.orderapp.entity.Checkout;

public interface CheckoutRepository extends JpaRepository<Checkout, Integer>{

	@Query
	Optional<Checkout> findByCartId(Integer cartId);
	
	@Query
	Optional<Checkout> findByUserId(Integer id);

}
