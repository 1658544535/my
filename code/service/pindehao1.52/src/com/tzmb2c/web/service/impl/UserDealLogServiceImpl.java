/*
 * 文 件 名:  UserDealLogServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-15
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.UserDealLogService;
import com.tzmb2c.web.pojo.UserDealLogPojo;
import com.tzmb2c.web.dao.UserDealLogDao;

/**
 * UserDealLog Service层
 */
public class UserDealLogServiceImpl implements UserDealLogService {
	
    @Autowired
    private UserDealLogDao userDealLogdao;
    
	public int add(UserDealLogPojo userDealLog) throws SQLException{
		if(null == userDealLog){
			return 0;
		}
        int rows = userDealLogdao.add(userDealLog);
        return rows;
	}

    public int update(UserDealLogPojo userDealLog) throws SQLException{
		if(null == userDealLog){
			return 0;
		}
        int rows = userDealLogdao.update(userDealLog);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = userDealLogdao.delete(id);
        return rows;
    }
    
    public UserDealLogPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		UserDealLogPojo userDealLog = userDealLogdao.getById(id);
        return userDealLog;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = userDealLogdao.countBy(params);
		return rows;
	}
	
	public List<UserDealLogPojo> listPage(Map<String, Object> params) throws SQLException{
		List<UserDealLogPojo> lists = userDealLogdao.listPage(params);
		return lists;
	}
}
