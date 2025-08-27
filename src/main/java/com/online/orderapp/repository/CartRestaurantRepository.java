package com.online.orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.orderapp.entity.CartRestaurant;

public interface CartRestaurantRepository extends JpaRepository<CartRestaurant, Integer>{

}
