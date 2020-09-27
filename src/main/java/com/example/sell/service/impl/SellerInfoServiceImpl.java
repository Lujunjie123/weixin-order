package com.example.sell.service.impl;

import com.example.sell.entity.SellerInfo;
import com.example.sell.repository.SellerInfoRepository;
import com.example.sell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerInfoServiceImpl implements SellerInfoService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        String.format("","");

        return sellerInfoRepository.findByOpenid(openid);
    }
}
























