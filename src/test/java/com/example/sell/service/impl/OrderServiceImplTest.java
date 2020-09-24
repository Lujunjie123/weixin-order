package com.example.sell.service.impl;

import com.example.sell.dto.OrderDto;
import com.example.sell.entity.OrderDetail;
import com.example.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    private final String openid = "1101110";

    @Test
    void create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerAddress("上海");
        orderDto.setBuyerOpenid(openid);
        orderDto.setBuyerName("zhangsan");
        orderDto.setBuyerPhone("12345678901");

        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123465");
        orderDetail.setProductQuantity(2);

        orderDetailList.add(orderDetail);
        orderDto.setOrderDetailList(orderDetailList);

        OrderDto dto = orderService.create(orderDto);
        log.info("订单信息{}",dto);
        Assert.assertNotNull(dto);
    }

    @Test
    void findOne() {
    }

    @Test
    void findAll() {
    }

    @Test
    void cancel() {
    }

    @Test
    void finish() {
    }

    @Test
    void paid() {
    }
}