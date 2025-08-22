package com.online.orderapp.dto;

import lombok.Data;

@Data
public class CartItemResponseDto {
    private Integer foodId;
    private int quantity;
    private double price;   // ✅ computed from DB
}