/*
 * 文 件 名:  SeckillMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-26
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SeckillPojo;

public interface SeckillMapper {
    
    SeckillPojo getById(Long id) throws SQLException;
    
    int countBy(Map<String, Object> params) throws SQLException;

    List<SeckillPojo> listPage(Map<String, Object> params) throws SQLException;
    
    int insert(SeckillPojo seckill) throws SQLException;
    
    int update(SeckillPojo seckill) throws SQLException;
    
    int deleteById(Long id) throws SQLException;
}