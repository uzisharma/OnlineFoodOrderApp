package com.online.orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.orderapp.entity.OrderPlaced;

public interface OrderPlacedRepository extends JpaRepository<OrderPlaced, String>{

}
