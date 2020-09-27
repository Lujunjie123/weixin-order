package com.example.sell.utils;

import com.example.sell.eumus.CodeEnum;

public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code,Class<T> enumClass){
        for (T enumConstant : enumClass.getEnumConstants()) {
            if(enumConstant.getCode().equals(code)){
                return enumConstant;
            }
        }
        return null;
    }
}
