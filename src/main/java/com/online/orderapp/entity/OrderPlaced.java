package com.online.orderapp.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class OrderPlaced {
	
	@Id
	private String id;
	
	@CreationTimestamp
	private LocalDateTime orderPlacedAt;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	

	@ManyToMany
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	private List<Restaurant> restaurant;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private OrderItemNew orderItem;
	
	private Double totalPrice;
	
	private LocalDateTime deliveryDateAndTime;
	
	
}
