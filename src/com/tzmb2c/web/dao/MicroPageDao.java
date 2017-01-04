/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.dao;

import java.util.List;
import java.util.Map;


import com.tzmb2c.web.pojo.MicroPagePojo;

/**
 * @version 1.0
 * @author 
 */
public interface MicroPageDao {
	
	public int add(MicroPagePojo microPage);

	public int update(MicroPagePojo microPage);
    
	public int delete(Long id);

	public MicroPagePojo getById(Long id);

	public Integer countBy(Map<String, Object> params);

	public List<MicroPagePojo> listPage(Map<String, Object> params);

}
