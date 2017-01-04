/*
 * 文 件 名:  ZonesServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-18
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.ZonesService;
import com.tzmb2c.web.pojo.ZonesPojo;
import com.tzmb2c.web.dao.ZonesDao;

/**
 * Zones Service层
 */
public class ZonesServiceImpl implements ZonesService {
	
    @Autowired
    private ZonesDao zonesdao;
    
	public int add(ZonesPojo zones) throws SQLException{
		if(null == zones){
			return 0;
		}
        int rows = zonesdao.add(zones);
        return rows;
	}

    public int update(ZonesPojo zones) throws SQLException{
		if(null == zones){
			return 0;
		}
        int rows = zonesdao.update(zones);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = zonesdao.delete(id);
        return rows;
    }
    
    public ZonesPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		ZonesPojo zones = zonesdao.getById(id);
        return zones;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = zonesdao.countBy(params);
		return rows;
	}
	
	public List<ZonesPojo> listPage(Map<String, Object> params) throws SQLException{
		List<ZonesPojo> lists = zonesdao.listPage(params);
		return lists;
	}
}
