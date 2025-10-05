package com.online.orderapp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.online.orderapp.util.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
	private Integer id;
	
	
	@NotNull
	@NotBlank
	private String userName;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull
	@NotBlank
	private String password;
	@NotNull
//	@Pattern(
//			regexp = "^[A-Za-z0-9._+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
//			flags = Pattern.Flag.CASE_INSENSITIVE,
//			message = "Invalid email format"
//			)
	@Email
	private String email;
	@NotNull
	private long contactNumber;
	@NotNull
	private String address;
	private String gender;
	
	@Lob
	private byte[] image;

	
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
	private Cart userCart; //inversion side but parent also, deleting user should delete the associated cart
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderPlaced> orderPlaced;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
}
