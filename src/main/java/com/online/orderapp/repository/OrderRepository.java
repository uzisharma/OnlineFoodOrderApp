package com.online.orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.orderapp.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
