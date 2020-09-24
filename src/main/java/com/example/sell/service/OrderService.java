package com.example.sell.service;

import com.example.sell.dto.OrderDto;

import java.awt.print.Pageable;
import java.util.List;

public interface OrderService {

    //创建订单
    OrderDto create(OrderDto orderDto);

    //查询单个订单
    OrderDto findOne(String orderId);

    //查询订单列表
    List<OrderDto> findAll(String buyerOpenid, Pageable pageable);

    //取消订单
    OrderDto cancel(OrderDto orderDto);

    //完结订单
    OrderDto finish(OrderDto orderDto);

    //支付订单
    OrderDto paid(OrderDto orderDto);
}








































































