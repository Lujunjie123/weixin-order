package com.example.sell.utils;

import java.util.Random;

public class KeyUtil {

    //生成当前时间戳+加随机6位数
    public static synchronized String generateUniqueKey(){
       return System.currentTimeMillis()+String.valueOf(new Random().nextInt(900_000)+100_000);
    }
}
