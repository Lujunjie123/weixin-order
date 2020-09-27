package com.example.sell.mapper;

import com.example.sell.entity.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.Map;

public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String,Object> map);

    @Insert("insert into product_category(category_name,category_type) values(#{categoryName},#{categoryType})")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type = #{categoryType}")
    @Results(
            {
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType")
         }
    )
    ProductCategory findByCategoryType(Integer categoryType);

    @Update("update product_category set category_name=#{categoryName} where category_type = #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,@Param("categoryType") Integer categoryType);

    @Delete("delete from product_category where category_type=#{categoryType} ")
    int deleteByCategory(Integer categoryType);

    ProductCategory selectByCategoryType(Integer categoryType);
}













































