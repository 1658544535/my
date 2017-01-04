/*
 * 文 件 名:  MicroPageDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-28
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.MicroPageDao;
import com.tzmb2c.web.pojo.MicroPagePojo;
import com.tzmb2c.web.mapper.MicroPageMapper;

/**
 * MicroPage Dao层
 */
public class MicroPageDaoImpl implements MicroPageDao {
	
    @Autowired
    private MicroPageMapper microPageMapper;
    
	public int add(MicroPagePojo microPage) {
		if(null == microPage){
			return 0;
		}
        int rows = microPageMapper.insert(microPage);
        return rows;
	}

    public int update(MicroPagePojo microPage) {
		if(null == microPage){
			return 0;
		}
        int rows = microPageMapper.update(microPage);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = microPageMapper.deleteById(id);
        return rows;
    }
    
    public MicroPagePojo getById(Long id) {
		if(null == id){
			return null;
		}
		MicroPagePojo microPage = microPageMapper.getById(id);
        return microPage;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = microPageMapper.countBy(params);
		return rows;
	}
	
	public List<MicroPagePojo> listPage(Map<String, Object> params){
		List<MicroPagePojo> lists = microPageMapper.listPage(params);		
		return lists;
	}
}
