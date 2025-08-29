package com.online.orderapp.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.online.orderapp.util.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class OrderPlaced {
	
//	@Id
//	@Column(nullable = false, updatable = false, unique = true)
//	private String id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@CreationTimestamp
	private LocalDateTime orderPlacedAt;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	

	@ManyToOne
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	private Restaurant restaurant; //single restaurant per order
	
	@OneToMany(mappedBy = "orderPlaced",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItemNew> orderItem;
	
	private Double totalPrice;
	
	private LocalDate deliveryDate;
	
	private LocalTime deliveryTime;
	
	private PaymentStatus paymentStatus;
}
