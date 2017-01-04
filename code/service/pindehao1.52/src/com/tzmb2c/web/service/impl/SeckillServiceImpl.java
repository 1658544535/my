/*
 * 文 件 名:  SeckillServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-26
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.SeckillService;
import com.tzmb2c.web.pojo.SeckillPojo;
import com.tzmb2c.web.dao.SeckillDao;

/**
 * Seckill Service层
 */
public class SeckillServiceImpl implements SeckillService {
	
    @Autowired
    private SeckillDao seckilldao;
    
	public int add(SeckillPojo seckill) throws SQLException{
		if(null == seckill){
			return 0;
		}
        int rows = seckilldao.add(seckill);
        return rows;
	}

    public int update(SeckillPojo seckill) throws SQLException{
		if(null == seckill){
			return 0;
		}
        int rows = seckilldao.update(seckill);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = seckilldao.delete(id);
        return rows;
    }
    
    public SeckillPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		SeckillPojo seckill = seckilldao.getById(id);
        return seckill;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = seckilldao.countBy(params);
		return rows;
	}
	
	public List<SeckillPojo> listPage(Map<String, Object> params) throws SQLException{
		List<SeckillPojo> lists = seckilldao.listPage(params);
		return lists;
	}
}
