/*
 * 文 件 名:  ZoneGoodsDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-18
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ZoneGoodsDao;
import com.tzmb2c.web.pojo.ZoneGoodsPojo;
import com.tzmb2c.web.mapper.ZoneGoodsMapper;

/**
 * ZoneGoods Dao层
 */
public class ZoneGoodsDaoImpl implements ZoneGoodsDao {
	
    @Autowired
    private ZoneGoodsMapper zoneGoodsMapper;
    
	public int add(ZoneGoodsPojo zoneGoods) throws SQLException{
		if(null == zoneGoods){
			return 0;
		}
        int rows = zoneGoodsMapper.insert(zoneGoods);
        return rows;
	}

    public int update(ZoneGoodsPojo zoneGoods) throws SQLException{
		if(null == zoneGoods){
			return 0;
		}
        int rows = zoneGoodsMapper.update(zoneGoods);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = zoneGoodsMapper.deleteById(id);
        return rows;
    }
    
    public ZoneGoodsPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		ZoneGoodsPojo zoneGoods = zoneGoodsMapper.getById(id);
        return zoneGoods;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = zoneGoodsMapper.countBy(params);
		return rows;
	}
	
	public List<ZoneGoodsPojo> listPage(Map<String, Object> params) throws SQLException{
		List<ZoneGoodsPojo> lists = zoneGoodsMapper.listPage(params);		
		return lists;
	}
}
