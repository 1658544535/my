/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.service;

import java.util.List;
import java.util.Map;


import com.tzmb2c.web.pojo.UserPostHistoryPojo;

/**
 * @version 1.0
 * @author 
 */
public interface UserPostHistoryService {
	
	public int add(UserPostHistoryPojo userPostHistory);

	public int update(UserPostHistoryPojo userPostHistory);
    
	public int delete(Long id);

	public UserPostHistoryPojo getById(Long id);

	public Integer countBy(Map<String, Object> params);

	public List<UserPostHistoryPojo> listPage(Map<String, Object> params);
	
	public Integer addLookNum(Map<String, Object> params);

}
