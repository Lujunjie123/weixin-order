package com.example.sell.controller;

import com.example.sell.entity.ProductCategory;
import com.example.sell.entity.ProductInfo;
import com.example.sell.service.ProductCategoryService;
import com.example.sell.service.ProductInfoService;
import com.example.sell.utils.ResultUtil;
import com.example.sell.vo.ProductCategoryVo;
import com.example.sell.vo.ProductInfoVo;
import com.example.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("list")
    public ResultVo list(){
        //查询所有上架商品
        List<ProductInfo> list = productInfoService.findUpAll();
        System.out.println("所有商品:"+list);

        //收集所有类目id
        List<Integer> collect = list.stream()
                .map(ProductInfo::getCategoryType)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("所有商品中的类目id:"+collect);

        //根据类目id查找类目信息
        List<ProductCategory> productCategories = productCategoryService.findByCateTypeIn(collect);

        List<ProductCategoryVo> productCategoryVoList = new ArrayList<>();

        for (ProductCategory productCategory : productCategories) {
            ProductCategoryVo productCategoryVo = new ProductCategoryVo();
            productCategoryVo.setCategoryName(productCategory.getCategoryName());
            productCategoryVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : list) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productCategoryVo.setProductInfoList(productInfoVoList);
            productCategoryVoList.add(productCategoryVo);
        }
        return ResultUtil.success(productCategoryVoList);
    }
}


























