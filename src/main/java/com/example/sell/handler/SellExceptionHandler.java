package com.example.sell.handler;

import com.example.sell.exception.SellException;
import com.example.sell.utils.ResultUtil;
import com.example.sell.vo.ResultVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SellExceptionHandler {

    @ExceptionHandler(SellException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultVo handlerSellException(SellException e){
        return ResultUtil.error(e.getCode(),e.getMessage());
    }
}
