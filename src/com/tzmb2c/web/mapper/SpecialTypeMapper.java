/*
 * 文 件 名:  SpecialTypeMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-14
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SpecialTypePojo;

public interface SpecialTypeMapper {
    
    SpecialTypePojo getById(Long id) throws SQLException;
    
    int countBy(Map<String, Object> params) throws SQLException;

    List<SpecialTypePojo> listPage(Map<String, Object> params) throws SQLException;
    
    int insert(SpecialTypePojo specialType) throws SQLException;
    
    int update(SpecialTypePojo specialType) throws SQLException;
    
    int deleteById(Long id) throws SQLException;
}