package com.example.sell.service.impl;

import com.example.sell.eumus.ResultEnum;
import com.example.sell.exception.SellException;
import com.example.sell.service.SeckillService;
import com.example.sell.utils.KeyUtil;
import com.example.sell.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SeckillServiceImpl implements SeckillService {

    private static final int TIMEOUT = 10*1000; //10s超时

    private static Map<String,String> orders;
    private static Map<String,Integer> products;
    private static Map<String,Integer> stocks;

    static{
        products = new HashMap<>();
        orders =  new HashMap<>();
        stocks = new HashMap<>();
        products.put("1234",100000);       //秒杀10万份商品
        stocks.put("1234",100000);         //库存10万份
    }

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String getProductInfo(String productId) {
        log.info("【秒杀】,一共秒杀{}份商品,库存剩余:{},已卖订单数:{}",products.get(productId),stocks.get(productId),orders.size());
        return "一共秒杀"+products.get(productId)+"份商品,库存剩余:"+stocks.get(productId)+",已卖订单数:"+orders.size();
    }

    //synchronized实现
//    @Override
//    public synchronized void seckill(String productId) {
//        try {
//            Integer stockNum = stocks.get(productId);
//            if(stockNum == null){
//                throw new SellException(ResultEnum.STOCK_ERROR);
//            }
//            //创建订单
//            orders.put(KeyUtil.generateUniqueKey(),productId);
//            //减库存
//            stocks.put(productId,stockNum - 1);
//        } catch (SellException e) {
//            e.printStackTrace();
//        }
//    }

    //redis分布式锁实现
    @Override
    public void seckill(String productId) {
        long expiretime = TIMEOUT + System.currentTimeMillis();
        try {
            //加锁
            if(!redisUtil.lock(productId,String.valueOf(expiretime))){
                throw new SellException(100,"没抢到，哈哈~~");
            }
            Integer stockNum = stocks.get(productId);
            if(stockNum == null){
                throw new SellException(ResultEnum.STOCK_ERROR);
            }
            //创建订单
            orders.put(KeyUtil.generateUniqueKey(),productId);
            //减库存
            stocks.put(productId,stockNum - 1);
        } catch (SellException e) {
            e.printStackTrace();
        }
//        }finally {//解锁
            redisUtil.unlock(productId,String.valueOf(expiretime));
//        }
    }
}

































