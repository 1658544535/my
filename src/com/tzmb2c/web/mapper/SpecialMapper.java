/*
 * 文 件 名:  SpecialMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-14
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SpecialPojo;

public interface SpecialMapper {
    
    SpecialPojo getById(Long id) throws SQLException;
    
    int countBy(Map<String, Object> params) throws SQLException;

    List<SpecialPojo> listPage(Map<String, Object> params) throws SQLException;
    
    int insert(SpecialPojo special) throws SQLException;
    
    int update(SpecialPojo special) throws SQLException;
    
    int deleteById(Long id) throws SQLException;
}