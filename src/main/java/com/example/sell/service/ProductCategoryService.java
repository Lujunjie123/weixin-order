package com.example.sell.service;

import com.example.sell.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCateTypeIn(List<Integer> cateTypeList);

    ProductCategory save(ProductCategory productCategory);
}
