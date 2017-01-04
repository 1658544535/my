/*
 * 文 件 名:  SpecialServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-14
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.SpecialService;
import com.tzmb2c.web.pojo.SpecialPojo;
import com.tzmb2c.web.dao.SpecialDao;

/**
 * Special Service层
 */
public class SpecialServiceImpl implements SpecialService {
	
    @Autowired
    private SpecialDao specialdao;
    
	public int add(SpecialPojo special) throws SQLException{
		if(null == special){
			return 0;
		}
        int rows = specialdao.add(special);
        return rows;
	}

    public int update(SpecialPojo special) throws SQLException{
		if(null == special){
			return 0;
		}
        int rows = specialdao.update(special);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = specialdao.delete(id);
        return rows;
    }
    
    public SpecialPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		SpecialPojo special = specialdao.getById(id);
        return special;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = specialdao.countBy(params);
		return rows;
	}
	
	public List<SpecialPojo> listPage(Map<String, Object> params) throws SQLException{
		List<SpecialPojo> lists = specialdao.listPage(params);
		return lists;
	}
}
