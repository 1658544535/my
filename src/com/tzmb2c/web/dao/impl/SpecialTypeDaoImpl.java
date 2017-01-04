/*
 * 文 件 名:  SpecialTypeDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-14
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SpecialTypeDao;
import com.tzmb2c.web.pojo.SpecialTypePojo;
import com.tzmb2c.web.mapper.SpecialTypeMapper;

/**
 * SpecialType Dao层
 */
public class SpecialTypeDaoImpl implements SpecialTypeDao {
	
    @Autowired
    private SpecialTypeMapper specialTypeMapper;
    
	public int add(SpecialTypePojo specialType) throws SQLException{
		if(null == specialType){
			return 0;
		}
        int rows = specialTypeMapper.insert(specialType);
        return rows;
	}

    public int update(SpecialTypePojo specialType) throws SQLException{
		if(null == specialType){
			return 0;
		}
        int rows = specialTypeMapper.update(specialType);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = specialTypeMapper.deleteById(id);
        return rows;
    }
    
    public SpecialTypePojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		SpecialTypePojo specialType = specialTypeMapper.getById(id);
        return specialType;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = specialTypeMapper.countBy(params);
		return rows;
	}
	
	public List<SpecialTypePojo> listPage(Map<String, Object> params) throws SQLException{
		List<SpecialTypePojo> lists = specialTypeMapper.listPage(params);		
		return lists;
	}
}
