package com.example.sell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//购物车
@Data
@AllArgsConstructor
public class CartDto {

    private String productId;

    private Integer productQuantity;
}
