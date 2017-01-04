/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.dao;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.HotPushPojo;

/**
 * @version 1.0
 * @author 
 */
public interface HotPushDao {
	
	public int add(HotPushPojo hotPush);

	public int update(HotPushPojo hotPush);
    
	public int delete(Long id);

	public HotPushPojo getById(Long id);

	public Integer countBy(Map<String, Object> params);

	public List<HotPushPojo> listPage(Map<String, Object> params);
	
	public List<HotPushPojo> topicListPage(Map<String, Object> params);

	public HotPushPojo findByParams(Map<String, Object> params);
}
