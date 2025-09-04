package com.online.orderapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.online.orderapp.entity.OrderPlaced;

public interface OrderPlacedRepository extends JpaRepository<OrderPlaced, String>{

	@Query
	Page<OrderPlaced> findByUserId(Integer userId, Pageable pageable);

	Optional<OrderPlaced> findByCheckoutId(Integer id);

	@Query
	List<OrderPlaced> findAllByUserId(Integer userId);

}
