package com.dark.api.vo;

import lombok.Data;

@Data
public class ShopVO {

    private Long id;

    private String shopName;

    private int saleNumber;

    private int amount;

    private String province;

    private String address;
}
