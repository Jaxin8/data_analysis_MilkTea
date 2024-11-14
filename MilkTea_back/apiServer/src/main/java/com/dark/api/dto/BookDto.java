package com.dark.api.dto;

import lombok.Data;

@Data
public class BookDto {
    private Integer month;
    private int totalPrice; // 每月vip总额
}
