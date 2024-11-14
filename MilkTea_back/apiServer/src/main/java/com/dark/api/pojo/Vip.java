package com.dark.api.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Vip {
    private int id;

    private String name;

    private LocalDateTime createTime;

    private Integer sex;
}
