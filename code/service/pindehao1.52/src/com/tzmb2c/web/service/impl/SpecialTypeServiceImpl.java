/*
 * 文 件 名:  SpecialTypeServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-14
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.SpecialTypeService;
import com.tzmb2c.web.pojo.SpecialTypePojo;
import com.tzmb2c.web.dao.SpecialTypeDao;

/**
 * SpecialType Service层
 */
public class SpecialTypeServiceImpl implements SpecialTypeService {
	
    @Autowired
    private SpecialTypeDao specialTypedao;
    
	public int add(SpecialTypePojo specialType) throws SQLException{
		if(null == specialType){
			return 0;
		}
        int rows = specialTypedao.add(specialType);
        return rows;
	}

    public int update(SpecialTypePojo specialType) throws SQLException{
		if(null == specialType){
			return 0;
		}
        int rows = specialTypedao.update(specialType);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = specialTypedao.delete(id);
        return rows;
    }
    
    public SpecialTypePojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		SpecialTypePojo specialType = specialTypedao.getById(id);
        return specialType;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = specialTypedao.countBy(params);
		return rows;
	}
	
	public List<SpecialTypePojo> listPage(Map<String, Object> params) throws SQLException{
		List<SpecialTypePojo> lists = specialTypedao.listPage(params);
		return lists;
	}
}
