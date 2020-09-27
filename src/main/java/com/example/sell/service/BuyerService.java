package com.example.sell.service;

import com.example.sell.dto.OrderDto;

public interface BuyerService {

    OrderDto findOneOrder(String openid,String orderId);

    void cancelOrder(String openid,String orderId);
}
