/*
 * 文 件 名:  UserPostHistoryDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-06-03
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserPostHistoryDao;
import com.tzmb2c.web.pojo.UserPostHistoryPojo;
import com.tzmb2c.web.mapper.UserPostHistoryMapper;

/**
 * UserPostHistory Dao层
 */
public class UserPostHistoryDaoImpl implements UserPostHistoryDao {
	
    @Autowired
    private UserPostHistoryMapper userPostHistoryMapper;
    
	public int add(UserPostHistoryPojo userPostHistory) {
		if(null == userPostHistory){
			return 0;
		}
        int rows = userPostHistoryMapper.insert(userPostHistory);
        return rows;
	}

    public int update(UserPostHistoryPojo userPostHistory) {
		if(null == userPostHistory){
			return 0;
		}
        int rows = userPostHistoryMapper.update(userPostHistory);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = userPostHistoryMapper.deleteById(id);
        return rows;
    }
    
    public UserPostHistoryPojo getById(Long id) {
		if(null == id){
			return null;
		}
		UserPostHistoryPojo userPostHistory = userPostHistoryMapper.getById(id);
        return userPostHistory;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = userPostHistoryMapper.countBy(params);
		return rows;
	}
	
	public List<UserPostHistoryPojo> listPage(Map<String, Object> params){
		List<UserPostHistoryPojo> lists = userPostHistoryMapper.listPage(params);		
		return lists;
	}

  @Override
  public Integer addLookNum(Map<String, Object> params) {
    return userPostHistoryMapper.addLookNum(params);
  }
}
