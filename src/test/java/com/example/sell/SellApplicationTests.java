package com.example.sell;

import com.example.sell.entity.ProductCategory;
import com.example.sell.repository.ProductCategoryRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SellApplicationTests {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Test
    void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(1);
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(3);
        ProductCategory category = productCategoryRepository.save(productCategory);
        Assert.assertNotNull(category);
//        Optional<ProductCategory> optional = productCategoryRepository.findById(1);
//        ProductCategory productCategory = optional.get();
//        productCategory.setCategoryType(10);
//        productCategoryRepository.save(productCategory);

    }

    @Test
    void findOne(){
        System.out.println(productCategoryRepository.findById(1));
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<ProductCategory> categories = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(1, 2, 10));
        Assert.assertNotEquals(0,categories.size());
    }
}
