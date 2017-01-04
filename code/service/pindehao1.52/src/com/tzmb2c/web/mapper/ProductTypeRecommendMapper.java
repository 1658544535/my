/*
 * 文 件 名:  ProductTypeRecommendMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-30
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductTypeRecommendPojo;

public interface ProductTypeRecommendMapper {
    
    ProductTypeRecommendPojo getById(Long id);
    
    int countBy(Map<String, Object> params);

    List<ProductTypeRecommendPojo> listPage(Map<String, Object> params);
    
    int insert(ProductTypeRecommendPojo productTypeRecommend);
    
    int update(ProductTypeRecommendPojo productTypeRecommend);
    
    int deleteById(Long id);
}