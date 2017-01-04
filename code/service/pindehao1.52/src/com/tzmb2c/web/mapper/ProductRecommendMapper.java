/*
 * 文 件 名:  ProductRecommendMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-30
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductRecommendPojo;

public interface ProductRecommendMapper {
    
    ProductRecommendPojo getById(Long id);
    
    int countBy(Map<String, Object> params);

    List<ProductRecommendPojo> listPage(Map<String, Object> params);
    
    int insert(ProductRecommendPojo productRecommend);
    
    int update(ProductRecommendPojo productRecommend);
    
    int deleteById(Long id);
}