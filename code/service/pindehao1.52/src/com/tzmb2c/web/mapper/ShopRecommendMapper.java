/*
 * 文 件 名:  ShopRecommendMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-30
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ShopRecommendPojo;

public interface ShopRecommendMapper {
    
    ShopRecommendPojo getById(Long id);
    
    int countBy(Map<String, Object> params);

    List<ShopRecommendPojo> listPage(Map<String, Object> params);
    
    int insert(ShopRecommendPojo shopRecommend);
    
    int update(ShopRecommendPojo shopRecommend);
    
    int deleteById(Long id);
}