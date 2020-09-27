package com.example.sell.service;

import com.example.sell.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    //创建订单
    OrderDto create(OrderDto orderDto);
    //查询单个订单
    OrderDto findOne(String orderId);
    //根据openid查询订单列表
    Page<OrderDto> findList(String buyerOpenid, Pageable pageable);
    //取消订单
    OrderDto cancel(OrderDto orderDto);
    //完结订单  改状态
    OrderDto finish(OrderDto orderDto);
    //支付订单  改状态
    OrderDto paid(OrderDto orderDto);
    //查询所有订单列表
    Page<OrderDto> findList(Pageable pageable);
}








































































