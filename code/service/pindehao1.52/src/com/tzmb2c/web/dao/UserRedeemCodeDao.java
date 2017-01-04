/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserRedeemCodePojo;

/**
 * @version 1.0
 * @author 
 */
public interface UserRedeemCodeDao {
	
	public int add(UserRedeemCodePojo userRedeemCode) throws SQLException;

	public int update(UserRedeemCodePojo userRedeemCode) throws SQLException;
    
	public int delete(String code) throws SQLException;

	public UserRedeemCodePojo getByCode(String code) throws SQLException;

	public Integer countBy(Map<String, Object> params) throws SQLException;

	public List<UserRedeemCodePojo> listPage(Map<String, Object> params) throws SQLException;

}
