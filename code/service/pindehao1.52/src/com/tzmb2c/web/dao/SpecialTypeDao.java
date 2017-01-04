/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import com.tzmb2c.web.pojo.SpecialTypePojo;

/**
 * @version 1.0
 * @author 
 */
public interface SpecialTypeDao {
	
	public int add(SpecialTypePojo specialType) throws SQLException;

	public int update(SpecialTypePojo specialType) throws SQLException;
    
	public int delete(Long id) throws SQLException;

	public SpecialTypePojo getById(Long id) throws SQLException;

	public Integer countBy(Map<String, Object> params) throws SQLException;

	public List<SpecialTypePojo> listPage(Map<String, Object> params) throws SQLException;

}
