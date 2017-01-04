/*
 * 文 件 名:  SeckillDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-26
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SeckillDao;
import com.tzmb2c.web.pojo.SeckillPojo;
import com.tzmb2c.web.mapper.SeckillMapper;

/**
 * Seckill Dao层
 */
public class SeckillDaoImpl implements SeckillDao {
	
    @Autowired
    private SeckillMapper seckillMapper;
    
	public int add(SeckillPojo seckill) throws SQLException{
		if(null == seckill){
			return 0;
		}
        int rows = seckillMapper.insert(seckill);
        return rows;
	}

    public int update(SeckillPojo seckill) throws SQLException{
		if(null == seckill){
			return 0;
		}
        int rows = seckillMapper.update(seckill);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = seckillMapper.deleteById(id);
        return rows;
    }
    
    public SeckillPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		SeckillPojo seckill = seckillMapper.getById(id);
        return seckill;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = seckillMapper.countBy(params);
		return rows;
	}
	
	public List<SeckillPojo> listPage(Map<String, Object> params) throws SQLException{
		List<SeckillPojo> lists = seckillMapper.listPage(params);		
		return lists;
	}
}
