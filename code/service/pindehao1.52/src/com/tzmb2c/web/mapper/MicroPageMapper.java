/*
 * 文 件 名:  MicroPageMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-28
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.MicroPagePojo;

public interface MicroPageMapper {
    
    MicroPagePojo getById(Long id);
    
    int countBy(Map<String, Object> params);

    List<MicroPagePojo> listPage(Map<String, Object> params);
    
    int insert(MicroPagePojo microPage);
    
    int update(MicroPagePojo microPage);
    
    int deleteById(Long id);
}