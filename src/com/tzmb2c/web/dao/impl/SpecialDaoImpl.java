/*
 * 文 件 名:  SpecialDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-14
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SpecialDao;
import com.tzmb2c.web.pojo.SpecialPojo;
import com.tzmb2c.web.mapper.SpecialMapper;

/**
 * Special Dao层
 */
public class SpecialDaoImpl implements SpecialDao {
	
    @Autowired
    private SpecialMapper specialMapper;
    
	public int add(SpecialPojo special) throws SQLException{
		if(null == special){
			return 0;
		}
        int rows = specialMapper.insert(special);
        return rows;
	}

    public int update(SpecialPojo special) throws SQLException{
		if(null == special){
			return 0;
		}
        int rows = specialMapper.update(special);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = specialMapper.deleteById(id);
        return rows;
    }
    
    public SpecialPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		SpecialPojo special = specialMapper.getById(id);
        return special;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = specialMapper.countBy(params);
		return rows;
	}
	
	public List<SpecialPojo> listPage(Map<String, Object> params) throws SQLException{
		List<SpecialPojo> lists = specialMapper.listPage(params);		
		return lists;
	}
}
