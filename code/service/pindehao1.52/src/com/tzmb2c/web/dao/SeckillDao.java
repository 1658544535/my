/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import com.tzmb2c.web.pojo.SeckillPojo;

/**
 * @version 1.0
 * @author 
 */
public interface SeckillDao {
	
	public int add(SeckillPojo seckill) throws SQLException;

	public int update(SeckillPojo seckill) throws SQLException;
    
	public int delete(Long id) throws SQLException;

	public SeckillPojo getById(Long id) throws SQLException;

	public Integer countBy(Map<String, Object> params) throws SQLException;

	public List<SeckillPojo> listPage(Map<String, Object> params) throws SQLException;

}
