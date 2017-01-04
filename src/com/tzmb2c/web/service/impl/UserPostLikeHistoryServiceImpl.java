package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.UserPostLikeHistoryService;
import com.tzmb2c.web.pojo.UserPostLikeHistoryPojo;
import com.tzmb2c.web.dao.UserPostLikeHistoryDao;

//@Service
public class UserPostLikeHistoryServiceImpl implements UserPostLikeHistoryService {
	
    @Autowired
    private UserPostLikeHistoryDao userPostLikeHistoryDao;
    
    @Transactional
	public int addUserPostLikeHistory(UserPostLikeHistoryPojo userPostLikeHistory) {
		if(null == userPostLikeHistory){
			return 0;
		}
        int rows = userPostLikeHistoryDao.addUserPostLikeHistory(userPostLikeHistory);
        return rows;
	}

    @Transactional
    public int updateUserPostLikeHistory(UserPostLikeHistoryPojo userPostLikeHistory) {
		if(null == userPostLikeHistory){
			return 0;
		}
        int rows = userPostLikeHistoryDao.updateUserPostLikeHistory(userPostLikeHistory);
        return rows;
    }
    
    @Transactional
    public int deleteUserPostLikeHistoryById(Long id) {
		if(null == id){
			return 0;
		}
        int rows = userPostLikeHistoryDao.deleteUserPostLikeHistoryById(id);
        return rows;
    }
    
    
    public UserPostLikeHistoryPojo getUserPostLikeHistoryById(Long id) {
		if(null == id){
			return null;
		}
		UserPostLikeHistoryPojo userPostLikeHistory = userPostLikeHistoryDao.getUserPostLikeHistoryById(id);
		//
        return userPostLikeHistory;
    }
	
	public Integer userPostLikeHistoryCount(Map<String, Object> params){
		Integer rows = userPostLikeHistoryDao.userPostLikeHistoryCount(params);
		return rows;
	}
	
	public List<UserPostLikeHistoryPojo> userPostLikeHistoryList(Map<String, Object> params){
		List<UserPostLikeHistoryPojo> lists = userPostLikeHistoryDao.userPostLikeHistoryList(params);
		
		return lists;
	}
}
