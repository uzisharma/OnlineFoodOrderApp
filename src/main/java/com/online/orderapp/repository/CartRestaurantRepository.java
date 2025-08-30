package com.online.orderapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.orderapp.entity.CartRestaurant;

public interface CartRestaurantRepository extends JpaRepository<CartRestaurant, Integer>{

	List<CartRestaurant> findByCartItemsId(Integer id);

}
