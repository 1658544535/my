/*
 * 文 件 名:  HotBrandRecommendMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-30
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.HotBrandRecommendPojo;

public interface HotBrandRecommendMapper {
    
    HotBrandRecommendPojo getById(Long id);
    
    int countBy(Map<String, Object> params);

    List<HotBrandRecommendPojo> listPage(Map<String, Object> params);
    
    int insert(HotBrandRecommendPojo hotBrandRecommend);
    
    int update(HotBrandRecommendPojo hotBrandRecommend);
    
    int deleteById(Long id);
}