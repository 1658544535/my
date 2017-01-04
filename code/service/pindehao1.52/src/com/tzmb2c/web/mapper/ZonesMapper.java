/*
 * 文 件 名:  ZonesMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-18
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ZonesPojo;

public interface ZonesMapper {
    
    ZonesPojo getById(Long id) throws SQLException;
    
    int countBy(Map<String, Object> params) throws SQLException;

    List<ZonesPojo> listPage(Map<String, Object> params) throws SQLException;
    
    int insert(ZonesPojo zones) throws SQLException;
    
    int update(ZonesPojo zones) throws SQLException;
    
    int deleteById(Long id) throws SQLException;
}