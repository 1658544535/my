/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import com.tzmb2c.web.pojo.UserOrderNoticePojo;

/**
 * @version 1.0
 * @author 
 */
public interface UserOrderNoticeDao {
	
	public int add(UserOrderNoticePojo userOrderNotice) throws SQLException;

	public int update(UserOrderNoticePojo userOrderNotice) throws SQLException;
    
	public int delete(Long id) throws SQLException;

	public UserOrderNoticePojo getById(Long id) throws SQLException;

	public Integer countBy(Map<String, Object> params) throws SQLException;

	public List<UserOrderNoticePojo> listPage(Map<String, Object> params) throws SQLException;

}
