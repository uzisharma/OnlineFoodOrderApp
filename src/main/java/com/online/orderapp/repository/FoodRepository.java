
package com.online.orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.orderapp.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {

}
