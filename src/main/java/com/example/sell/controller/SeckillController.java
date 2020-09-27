package com.example.sell.controller;

import com.example.sell.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @GetMapping("/query/{productId}")
    public String querySeckill(@PathVariable String productId) {
        return seckillService.getProductInfo(productId);
    }

    @GetMapping("/seckill/{productId}")
    public String seckill(@PathVariable String productId){
        seckillService.seckill(productId);

        return seckillService.getProductInfo(productId);
    }
}























