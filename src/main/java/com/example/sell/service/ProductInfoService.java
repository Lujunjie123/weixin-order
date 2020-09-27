package com.example.sell.service;

import com.example.sell.dto.CartDto;
import com.example.sell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService{
    //查询单个商品
    ProductInfo findOne(String productId);
    //查询在架商品
    List<ProductInfo> findUpAll();
    //查询所有
    Page<ProductInfo> findAll(Pageable pageable);
    //保存
    ProductInfo save(ProductInfo productInfo);
    //加库存
    void increaseStock(List<CartDto> cartDtoList);
    //减库存
    void decreaseStock(List<CartDto> cartDtoList);

    //删除某个商品
    void delete(String productId);
}




















