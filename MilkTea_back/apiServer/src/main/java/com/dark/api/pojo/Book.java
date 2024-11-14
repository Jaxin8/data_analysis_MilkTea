package com.dark.api.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.time.LocalDateTime;
@Data
public class Book {

    private Integer id;
    private String phone;
    private String brand;
    private int total;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private String  area;
}
