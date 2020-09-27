package com.example.sell.service;

import com.example.sell.entity.SellerInfo;

public interface SellerInfoService {

    SellerInfo findSellerInfoByOpenid(String openid);
}
