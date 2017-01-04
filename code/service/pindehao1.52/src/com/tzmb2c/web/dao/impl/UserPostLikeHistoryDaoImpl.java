package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserPostLikeHistoryDao;
import com.tzmb2c.web.pojo.UserPostLikeHistoryPojo;
import com.tzmb2c.web.mapper.UserPostLikeHistoryMapper;

//@Service
public class UserPostLikeHistoryDaoImpl implements UserPostLikeHistoryDao {
	
    @Autowired
    private UserPostLikeHistoryMapper userPostLikeHistoryMapper;
    
    @Transactional
	public int addUserPostLikeHistory(UserPostLikeHistoryPojo userPostLikeHistory) {
		if(null == userPostLikeHistory){
			return 0;
		}
        int rows = userPostLikeHistoryMapper.addUserPostLikeHistory(userPostLikeHistory);
        return rows;
	}

    @Transactional
    public int updateUserPostLikeHistory(UserPostLikeHistoryPojo userPostLikeHistory) {
		if(null == userPostLikeHistory){
			return 0;
		}
        int rows = userPostLikeHistoryMapper.updateUserPostLikeHistory(userPostLikeHistory);
        return rows;
    }
    
    @Transactional
    public int deleteUserPostLikeHistoryById(Long id) {
		if(null == id){
			return 0;
		}
        int rows = userPostLikeHistoryMapper.deleteUserPostLikeHistoryById(id);
        return rows;
    }
    
    
    public UserPostLikeHistoryPojo getUserPostLikeHistoryById(Long id) {
		if(null == id){
			return null;
		}
		UserPostLikeHistoryPojo userPostLikeHistory = userPostLikeHistoryMapper.getUserPostLikeHistoryById(id);
		//
        return userPostLikeHistory;
    }
	
	public Integer userPostLikeHistoryCount(Map<String, Object> params){
		Integer rows = userPostLikeHistoryMapper.userPostLikeHistoryCount(params);
		return rows;
	}
	
	public List<UserPostLikeHistoryPojo> userPostLikeHistoryList(Map<String, Object> params){
		List<UserPostLikeHistoryPojo> lists = userPostLikeHistoryMapper.userPostLikeHistoryList(params);
		
		return lists;
	}
}
