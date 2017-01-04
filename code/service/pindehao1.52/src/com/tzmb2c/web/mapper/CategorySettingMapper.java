/*
 * 文 件 名:  CategorySettingMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-03
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.CategorySettingPojo;

public interface CategorySettingMapper {
    
    CategorySettingPojo getById(Long id);
    
    int countBy(Map<String, Object> params);

    List<CategorySettingPojo> listPage(Map<String, Object> params);
    
    int insert(CategorySettingPojo categorySetting);
    
    int update(CategorySettingPojo categorySetting);
    
    int deleteById(Long id);
}