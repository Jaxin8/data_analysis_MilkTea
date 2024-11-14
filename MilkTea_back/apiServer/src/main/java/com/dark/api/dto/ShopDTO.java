package com.dark.api.dto;

import lombok.Data;

@Data
public class ShopDTO {
    private String shopName;

    private int saleNumber;

    private int amount;

    private String province;

    private String address;
}
