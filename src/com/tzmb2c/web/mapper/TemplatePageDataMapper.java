/*
 * 文 件 名:  TemplatePageDataMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-28
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.TemplatePageDataPojo;

public interface TemplatePageDataMapper {
    
    TemplatePageDataPojo getById(Long id);
    
    int countBy(Map<String, Object> params);

    List<TemplatePageDataPojo> listPage(Map<String, Object> params);
    
    int insert(TemplatePageDataPojo templatePageData);
    
    int update(TemplatePageDataPojo templatePageData);
    
    int deleteById(Long id);
    
    public TemplatePageDataPojo findByParams(Map<String, Object> params);
}