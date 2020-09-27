package com.example.sell.service.impl;

import com.example.sell.entity.ProductInfo;
import com.example.sell.service.ProductInfoService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    void findOne() {
        ProductInfo one = productInfoService.findOne("1234650");
        System.out.println(one);
        Assert.assertEquals("123465",one.getProductId());
    }

    @Test
    void findUpAll() {
        List<ProductInfo> upAll = productInfoService.findUpAll();
        System.out.println(upAll);
        Assert.assertNotEquals(0,upAll.size());
    }

    @Test
    void findAll() {
        PageRequest pageRequest = PageRequest.of(1, 1);
        Page<ProductInfo> all = productInfoService.findAll(pageRequest);
        System.out.println(all.getContent());
        Assert.assertNotEquals(0,all.getTotalElements());
    }

    @Test
    void save() {
//        ProductInfo productInfo = new ProductInfo();
//        productInfo.setCategoryType(1);
//        productInfo.setProductId("123467");
//        productInfo.setProductName("歌帝梵冰淇淋");
//        productInfo.setProductPrice(new BigDecimal("50.46"));
//        productInfo.setProductStatus(1);
//        productInfo.setProductStock(45);
//        productInfo.setProductDescription("很丝滑很凉爽");
//        productInfo.setProductIcon("www.xxx.png");
//        ProductInfo save = productInfoService.save(productInfo);
//        Assert.assertNotNull(save);
    }
}





















