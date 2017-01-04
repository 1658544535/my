package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserPostLikeHistoryPojo;

public interface UserPostLikeHistoryMapper {
    
    UserPostLikeHistoryPojo getUserPostLikeHistoryById(Long id);
    
    int userPostLikeHistoryCount(Map<String, Object> params);

    List<UserPostLikeHistoryPojo> userPostLikeHistoryList(Map<String, Object> params);
    
    int addUserPostLikeHistory(UserPostLikeHistoryPojo userPostLikeHistory);
    
    int updateUserPostLikeHistory(UserPostLikeHistoryPojo userPostLikeHistory);
    
    int deleteUserPostLikeHistoryById(Long id);
}