package com.online.orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.orderapp.entity.OrderItemNew;

public interface OrderItemNewRepository extends JpaRepository<OrderItemNew, Integer>{

}
