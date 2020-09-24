package com.example.sell.entity;

import com.example.sell.eumus.OrderStatusEnum;
import com.example.sell.eumus.PayStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class OrderMaster {

    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE)
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    private Integer payStatus = PayStatusEnum.WAIT.getCode();

}


















