/*
 * 文 件 名:  CategoryDetailSettingMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-03
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.CategoryDetailSettingPojo;

public interface CategoryDetailSettingMapper {
    
    CategoryDetailSettingPojo getById(Long id);
    
    int countBy(Map<String, Object> params);

    List<CategoryDetailSettingPojo> listPage(Map<String, Object> params);
    
    int insert(CategoryDetailSettingPojo categoryDetailSetting);
    
    int update(CategoryDetailSettingPojo categoryDetailSetting);
    
    int deleteById(Long id);
}