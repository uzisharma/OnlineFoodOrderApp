package com.online.orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.orderapp.entity.OrderItem;


public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

}
