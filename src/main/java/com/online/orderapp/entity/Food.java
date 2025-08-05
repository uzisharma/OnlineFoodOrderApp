package com.online.orderapp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String foodName;
	private String description;
	private float price;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "food")
	List<Restaurant> restaurants;
}
