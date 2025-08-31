package com.online.orderapp.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Checkout {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer userId;
	
	@OneToOne
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
	private Cart cart;
	
	private Double gstPercent;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal gstAmount;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal originalAmount;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal totalAmount;
	
	private Integer itemQuantity;
	
}
