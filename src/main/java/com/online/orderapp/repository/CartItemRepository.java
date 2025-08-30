package com.online.orderapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.online.orderapp.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

	@Query
	List<CartItem> findByCartId(Integer cartId);

}
