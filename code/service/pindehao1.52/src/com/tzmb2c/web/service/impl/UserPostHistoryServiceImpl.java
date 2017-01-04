/*
 * 文 件 名:  UserPostHistoryServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-06-03
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.UserPostHistoryService;
import com.tzmb2c.web.pojo.UserPostHistoryPojo;
import com.tzmb2c.web.dao.UserPostHistoryDao;

/**
 * UserPostHistory Service层
 */
public class UserPostHistoryServiceImpl implements UserPostHistoryService {
	
    @Autowired
    private UserPostHistoryDao userPostHistorydao;
    
	public int add(UserPostHistoryPojo userPostHistory) {
		if(null == userPostHistory){
			return 0;
		}
        int rows = userPostHistorydao.add(userPostHistory);
        return rows;
	}

    public int update(UserPostHistoryPojo userPostHistory) {
		if(null == userPostHistory){
			return 0;
		}
        int rows = userPostHistorydao.update(userPostHistory);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = userPostHistorydao.delete(id);
        return rows;
    }
    
    public UserPostHistoryPojo getById(Long id) {
		if(null == id){
			return null;
		}
		UserPostHistoryPojo userPostHistory = userPostHistorydao.getById(id);
        return userPostHistory;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = userPostHistorydao.countBy(params);
		return rows;
	}
	
	public List<UserPostHistoryPojo> listPage(Map<String, Object> params){
		List<UserPostHistoryPojo> lists = userPostHistorydao.listPage(params);
		return lists;
	}

  @Override
  public Integer addLookNum(Map<String, Object> params) {
    return userPostHistorydao.addLookNum(params);
  }
}
