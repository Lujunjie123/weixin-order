package com.example.sell.dto;

import com.example.sell.entity.OrderDetail;
import com.example.sell.eumus.OrderStatusEnum;
import com.example.sell.eumus.PayStatusEnum;
import com.example.sell.utils.EnumUtil;
import com.example.sell.utils.serializer.DateLongSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)  //如果为空不返回给前端
public class OrderDto {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    @JsonSerialize(using = DateLongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = DateLongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum orderStatusEnum(){
       return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum payStatusEnum(){
       return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
























