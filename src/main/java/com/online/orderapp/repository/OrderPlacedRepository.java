package com.online.orderapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.online.orderapp.entity.OrderPlaced;

public interface OrderPlacedRepository extends JpaRepository<OrderPlaced, String>{

	@Query
	List<OrderPlaced> findByUserId(Integer userId);

}
