package com.example.sell.service.impl;

import com.example.sell.entity.ProductCategory;
import com.example.sell.repository.ProductCategoryRepository;
import com.example.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        Optional<ProductCategory> optional = productCategoryRepository.findById(categoryId);
        //如果有值返回该值，否则返回一个新对象
        return optional.orElseGet(ProductCategory::new);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCateTypeIn(List<Integer> cateTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(cateTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
