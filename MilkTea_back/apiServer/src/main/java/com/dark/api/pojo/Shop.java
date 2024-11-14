package com.dark.api.pojo;

import lombok.Data;

@Data
public class Shop {
    private Long id;

    private String shopName;

    private int saleNumber;

    private int amount;

    private String province;

    private String address;
}
