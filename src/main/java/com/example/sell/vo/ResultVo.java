package com.example.sell.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = -4992883212468153324L;
    private Integer code;
    private String message;
    private T data;
}
