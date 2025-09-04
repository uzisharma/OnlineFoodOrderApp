package com.online.orderapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.online.orderapp.entity.Food;
import com.online.orderapp.entity.OrderPlaced;
import com.online.orderapp.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	
	@Query("SELECT r.food FROM Restaurant r WHERE r.id = :restaurantId")
	List<Food> findFoodByRestaurantId(@Param(value = "restaurantId") int id);

//	@Query("SELECT r.order_placed FROM Restaurant r WHERE r.id = : id")
	@Query("SELECT o FROM OrderPlaced o WHERE o.restaurant.id = :id")
	List<OrderPlaced> findOrderPlacedByid(@Param(value = "id") Integer id);
	
}
