package com.example.sell.mapper;

import com.example.sell.entity.ProductCategory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    void insertByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("categoryName","销量榜");
        map.put("category_type",1234);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1,result);
    }
    @Test
    void insertByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("巅峰榜");
        productCategory.setCategoryType(12345);
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    void findByCategoryType() {
        ProductCategory productCategory = mapper.findByCategoryType(1234);
        System.out.println(productCategory);
        Assert.assertNotNull(productCategory);
    }

    @Test
    void updateByCategoryType(){
        int update = mapper.updateByCategoryType("巅峰", 12345);
        Assert.assertEquals(1,update);
    }

    @Test
    void deleteByCategoryType(){
        int delete = mapper.deleteByCategory(12345);
        Assert.assertEquals(1,delete);
    }

    @Test
    void select(){
        ProductCategory productCategory = mapper.selectByCategoryType(1234);
        System.out.println(productCategory);
        Assert.assertNotNull(productCategory);
    }

}



























