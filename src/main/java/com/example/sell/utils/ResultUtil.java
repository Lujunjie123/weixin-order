package com.example.sell.utils;

import com.example.sell.vo.ResultVo;

public class ResultUtil {

    public static ResultVo<Object> success(Object object){
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setData(object);
        resultVo.setCode(0);
        resultVo.setMessage("成功");
        return resultVo;
    }
}
























