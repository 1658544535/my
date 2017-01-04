/*
 * 文 件 名:  SpecialGoodsMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-14
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SpecialGoodsPojo;

public interface SpecialGoodsMapper {
    
    SpecialGoodsPojo getById(Long id) throws SQLException;
    
    int countBy(Map<String, Object> params) throws SQLException;

    List<SpecialGoodsPojo> listPage(Map<String, Object> params) throws SQLException;
    
    int insert(SpecialGoodsPojo specialGoods) throws SQLException;
    
    int update(SpecialGoodsPojo specialGoods) throws SQLException;
    
    int deleteById(Long id) throws SQLException;
}