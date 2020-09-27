package com.example.sell.service.impl;

import com.example.sell.dto.CartDto;
import com.example.sell.entity.ProductInfo;
import com.example.sell.eumus.ProductStatusEnum;
import com.example.sell.eumus.ResultEnum;
import com.example.sell.exception.SellException;
import com.example.sell.repository.ProductInfoRepository;
import com.example.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        Optional<ProductInfo> optional = productInfoRepository.findById(productId);
        if(!optional.isPresent()){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        return optional.get();
    }

    @Override
    public List<ProductInfo> findUpAll() {

        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

//    @CachePut(cacheNames = "product", key = "123")
    @Override
    public ProductInfo save(ProductInfo productInfo) {

        return productInfoRepository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            Optional<ProductInfo> optional = productInfoRepository.findById(cartDto.getProductId());
            if(!optional.isPresent()){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = optional.get();
            Integer num = productInfo.getProductStock() + cartDto.getProductQuantity();
            productInfo.setProductStock(num);
            productInfoRepository.save(productInfo);
        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtoList) {
        //判断购物车中的每个商品是否在数据库中能找到，如果能找到，判断减完库存是否小于0
        for (CartDto cartDto : cartDtoList) {
            Optional<ProductInfo> optional = productInfoRepository.findById(cartDto.getProductId());
            if(!optional.isPresent()){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = optional.get();
            Integer num = productInfo.getProductStock() - cartDto.getProductQuantity();
            if(num<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }else{
                productInfo.setProductStock(num);
                productInfoRepository.save(productInfo);
            }
        }
    }

    @Override
    public void delete(String productId) {
        productInfoRepository.deleteById(productId);
    }
}




















