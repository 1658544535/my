/*
 * 文 件 名:  UserDealLogDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-15
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserDealLogDao;
import com.tzmb2c.web.pojo.UserDealLogPojo;
import com.tzmb2c.web.mapper.UserDealLogMapper;

/**
 * UserDealLog Dao层
 */
public class UserDealLogDaoImpl implements UserDealLogDao {
	
    @Autowired
    private UserDealLogMapper userDealLogMapper;
    
	public int add(UserDealLogPojo userDealLog) throws SQLException{
		if(null == userDealLog){
			return 0;
		}
        int rows = userDealLogMapper.insert(userDealLog);
        return rows;
	}

    public int update(UserDealLogPojo userDealLog) throws SQLException{
		if(null == userDealLog){
			return 0;
		}
        int rows = userDealLogMapper.update(userDealLog);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = userDealLogMapper.deleteById(id);
        return rows;
    }
    
    public UserDealLogPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		UserDealLogPojo userDealLog = userDealLogMapper.getById(id);
        return userDealLog;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = userDealLogMapper.countBy(params);
		return rows;
	}
	
	public List<UserDealLogPojo> listPage(Map<String, Object> params) throws SQLException{
		List<UserDealLogPojo> lists = userDealLogMapper.listPage(params);		
		return lists;
	}
}
