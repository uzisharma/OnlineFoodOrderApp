package com.online.orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.orderapp.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

}
