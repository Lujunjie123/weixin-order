package com.example.sell.utils;

import com.example.sell.vo.ResultVo;

public class ResultUtil {

    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo<>();
        resultVo.setData(object);
        resultVo.setCode(0);
        resultVo.setMessage("成功");
        return resultVo;
    }
    public static ResultVo success(){
        ResultVo resultVo = new ResultVo<>();
        resultVo.setCode(0);
        resultVo.setMessage("成功");
        return resultVo;
    }
    public static ResultVo error(Integer code,String msg){
        ResultVo resultVo = new ResultVo<>();
        resultVo.setCode(code);
        resultVo.setMessage(msg);
        return resultVo;
    }
}
























