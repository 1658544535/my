/*
 * 文 件 名:  ZonesDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-18
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ZonesDao;
import com.tzmb2c.web.pojo.ZonesPojo;
import com.tzmb2c.web.mapper.ZonesMapper;

/**
 * Zones Dao层
 */
public class ZonesDaoImpl implements ZonesDao {
	
    @Autowired
    private ZonesMapper zonesMapper;
    
	public int add(ZonesPojo zones) throws SQLException{
		if(null == zones){
			return 0;
		}
        int rows = zonesMapper.insert(zones);
        return rows;
	}

    public int update(ZonesPojo zones) throws SQLException{
		if(null == zones){
			return 0;
		}
        int rows = zonesMapper.update(zones);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = zonesMapper.deleteById(id);
        return rows;
    }
    
    public ZonesPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		ZonesPojo zones = zonesMapper.getById(id);
        return zones;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = zonesMapper.countBy(params);
		return rows;
	}
	
	public List<ZonesPojo> listPage(Map<String, Object> params) throws SQLException{
		List<ZonesPojo> lists = zonesMapper.listPage(params);		
		return lists;
	}
}
