package com.example.sell.eumus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  ResultEnum {
    PORM_ERROR(1,"参数不正确"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不足"),
    CATECORY_NOT_EXIST(12,"类目不存在"),
    ORDER_NOT_EXIST(20,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(21,"订单详情不存在"),
    ORDER_STATUS_ERROR(22,"订单状态不正确"),
    ORDER_UPDATE_FAIL(23,"订单更新失败"),
    ORDER_DETAIL_EMPTY(24,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(30,"订单支付状态不正确"),
    CART_EMPTY(40,"购物车为空"),
    ORDER_OWNER_ERROR(25,"该订单不属于当前用户"),
    STOCK_ERROR(50,"没有库存了")
    ;

    private int code;
    private String message;
}
