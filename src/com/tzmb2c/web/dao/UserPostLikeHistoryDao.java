package com.tzmb2c.web.dao;

import java.util.List;
import java.util.Map;


import com.tzmb2c.web.pojo.UserPostLikeHistoryPojo;

public interface UserPostLikeHistoryDao {
	
	public int addUserPostLikeHistory(UserPostLikeHistoryPojo userPostLikeHistory);

	public int updateUserPostLikeHistory(UserPostLikeHistoryPojo userPostLikeHistory);
    
	public int deleteUserPostLikeHistoryById(Long id);

	public UserPostLikeHistoryPojo getUserPostLikeHistoryById(Long id);

	public Integer userPostLikeHistoryCount(Map<String, Object> params);

	public List<UserPostLikeHistoryPojo> userPostLikeHistoryList(Map<String, Object> params);

}
