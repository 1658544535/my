/*
 * 文 件 名:  ZoneGoodsServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-18
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.ZoneGoodsService;
import com.tzmb2c.web.pojo.ZoneGoodsPojo;
import com.tzmb2c.web.dao.ZoneGoodsDao;

/**
 * ZoneGoods Service层
 */
public class ZoneGoodsServiceImpl implements ZoneGoodsService {
	
    @Autowired
    private ZoneGoodsDao zoneGoodsdao;
    
	public int add(ZoneGoodsPojo zoneGoods) throws SQLException{
		if(null == zoneGoods){
			return 0;
		}
        int rows = zoneGoodsdao.add(zoneGoods);
        return rows;
	}

    public int update(ZoneGoodsPojo zoneGoods) throws SQLException{
		if(null == zoneGoods){
			return 0;
		}
        int rows = zoneGoodsdao.update(zoneGoods);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = zoneGoodsdao.delete(id);
        return rows;
    }
    
    public ZoneGoodsPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		ZoneGoodsPojo zoneGoods = zoneGoodsdao.getById(id);
        return zoneGoods;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = zoneGoodsdao.countBy(params);
		return rows;
	}
	
	public List<ZoneGoodsPojo> listPage(Map<String, Object> params) throws SQLException{
		List<ZoneGoodsPojo> lists = zoneGoodsdao.listPage(params);
		return lists;
	}
}
