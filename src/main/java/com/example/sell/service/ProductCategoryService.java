package com.example.sell.service;

import com.example.sell.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    //查询单个类目
    ProductCategory findOne(Integer categoryId);
    //查询所有类目
    List<ProductCategory> findAll();
    //通过指定的类目类型查找类目
    List<ProductCategory> findByCateTypeIn(List<Integer> cateTypeList);
    //保存类目
    ProductCategory save(ProductCategory productCategory);
}
