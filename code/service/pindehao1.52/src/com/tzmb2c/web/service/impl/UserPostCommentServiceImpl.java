/*
 * 文 件 名:  UserPostCommentServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-06-04
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.UserPostCommentService;
import com.tzmb2c.web.pojo.UserPostCommentPojo;
import com.tzmb2c.web.dao.UserPostCommentDao;

/**
 * UserPostComment Service层
 */
public class UserPostCommentServiceImpl implements UserPostCommentService {
	
    @Autowired
    private UserPostCommentDao userPostCommentdao;
    
	public int add(UserPostCommentPojo userPostComment) {
		if(null == userPostComment){
			return 0;
		}
        int rows = userPostCommentdao.add(userPostComment);
        return rows;
	}

    public int update(UserPostCommentPojo userPostComment) {
		if(null == userPostComment){
			return 0;
		}
        int rows = userPostCommentdao.update(userPostComment);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = userPostCommentdao.delete(id);
        return rows;
    }
    
    public UserPostCommentPojo getById(Long id) {
		if(null == id){
			return null;
		}
		UserPostCommentPojo userPostComment = userPostCommentdao.getById(id);
        return userPostComment;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = userPostCommentdao.countBy(params);
		return rows;
	}
	
	public List<UserPostCommentPojo> listPage(Map<String, Object> params){
		List<UserPostCommentPojo> lists = userPostCommentdao.listPage(params);
		return lists;
	}
}
