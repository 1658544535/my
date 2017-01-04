/*
 * 文 件 名:  ExternalLinksMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-30
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ExternalLinksPojo;

public interface ExternalLinksMapper {
    
    ExternalLinksPojo getById(Long id);
    
    int countBy(Map<String, Object> params);

    List<ExternalLinksPojo> listPage(Map<String, Object> params);
    
    int insert(ExternalLinksPojo externalLinks);
    
    int update(ExternalLinksPojo externalLinks);
    
    int deleteById(Long id);
}