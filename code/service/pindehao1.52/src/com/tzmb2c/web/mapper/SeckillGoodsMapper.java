/*
 * 文 件 名:  SeckillGoodsMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-26
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SeckillGoodsPojo;

public interface SeckillGoodsMapper {
    
    SeckillGoodsPojo getById(Long id) throws SQLException;
    
    int countBy(Map<String, Object> params) throws SQLException;

    List<SeckillGoodsPojo> listPage(Map<String, Object> params) throws SQLException;
    
    int insert(SeckillGoodsPojo seckillGoods) throws SQLException;
    
    int update(SeckillGoodsPojo seckillGoods) throws SQLException;
    
    int deleteById(Long id) throws SQLException;
}