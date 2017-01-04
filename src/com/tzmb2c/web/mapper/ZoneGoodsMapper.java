/*
 * 文 件 名:  ZoneGoodsMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-18
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ZoneGoodsPojo;

public interface ZoneGoodsMapper {
    
    ZoneGoodsPojo getById(Long id) throws SQLException;
    
    int countBy(Map<String, Object> params) throws SQLException;

    List<ZoneGoodsPojo> listPage(Map<String, Object> params) throws SQLException;
    
    int insert(ZoneGoodsPojo zoneGoods) throws SQLException;
    
    int update(ZoneGoodsPojo zoneGoods) throws SQLException;
    
    int deleteById(Long id) throws SQLException;
}