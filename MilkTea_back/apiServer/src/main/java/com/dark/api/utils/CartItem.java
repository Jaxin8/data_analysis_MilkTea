package com.dark.api.utils;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItem {
    private int foodId;
    private String name;
    private String photo;
    private String description;
    private int count = 1;
    private BigDecimal price;

    private String flavorItem;
    private String flavorValue;
}
