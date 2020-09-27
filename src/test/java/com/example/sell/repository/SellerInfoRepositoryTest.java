package com.example.sell.repository;

import com.example.sell.entity.SellerInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void findByOpenid(){
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("abc");
        System.out.println(sellerInfo);
    }

    @Test
    public void test(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setId("1601091648270151457");
        sellerInfo.setUsername("lisi");
        sellerInfo.setPassword("123456");
        sellerInfo.setOpenid("abc");
        sellerInfoRepository.save(sellerInfo);
    }
}