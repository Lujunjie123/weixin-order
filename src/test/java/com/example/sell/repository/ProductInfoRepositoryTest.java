package com.example.sell.repository;

import com.example.sell.entity.ProductInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductInfoRepositoryTest {

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Test
    void findByProductStatusTest(){
        List<ProductInfo> productStatus = productInfoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0,productStatus.size());
    }

    @Test
    void save(){
//        ProductInfo productInfo = new ProductInfo();
//        productInfo.setCategoryType(1);
//        productInfo.setProductId("123465");
//        productInfo.setProductName("黄金榴莲披萨");
//        productInfo.setProductPrice(new BigDecimal(231.12));
//        productInfo.setProductStatus(0);
//        productInfo.setProductStock(55);
//        productInfo.setProductDescription("美味的披萨");
//        productInfo.setProductIcon("www.xxx.png");
//        ProductInfo save = productInfoRepository.save(productInfo);
//        Assert.assertNotNull(save);
    }
}