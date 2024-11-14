package com.dark.api.utils;

import lombok.Data;

@Data
public class ResultModel<T> {
    private String msg;
    private int code = 200;
    private T data;
}
