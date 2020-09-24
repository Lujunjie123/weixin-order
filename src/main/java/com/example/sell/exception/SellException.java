package com.example.sell.exception;

import com.example.sell.eumus.ResultEnum;

public class SellException extends RuntimeException{

    private int code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
