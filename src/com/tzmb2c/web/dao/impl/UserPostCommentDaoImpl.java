/*
 * 文 件 名:  UserPostCommentDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-06-04
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserPostCommentDao;
import com.tzmb2c.web.pojo.UserPostCommentPojo;
import com.tzmb2c.web.mapper.UserPostCommentMapper;

/**
 * UserPostComment Dao层
 */
public class UserPostCommentDaoImpl implements UserPostCommentDao {
	
    @Autowired
    private UserPostCommentMapper userPostCommentMapper;
    
	public int add(UserPostCommentPojo userPostComment) {
		if(null == userPostComment){
			return 0;
		}
        int rows = userPostCommentMapper.insert(userPostComment);
        return rows;
	}

    public int update(UserPostCommentPojo userPostComment) {
		if(null == userPostComment){
			return 0;
		}
        int rows = userPostCommentMapper.update(userPostComment);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = userPostCommentMapper.deleteById(id);
        return rows;
    }
    
    public UserPostCommentPojo getById(Long id) {
		if(null == id){
			return null;
		}
		UserPostCommentPojo userPostComment = userPostCommentMapper.getById(id);
        return userPostComment;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = userPostCommentMapper.countBy(params);
		return rows;
	}
	
	public List<UserPostCommentPojo> listPage(Map<String, Object> params){
		List<UserPostCommentPojo> lists = userPostCommentMapper.listPage(params);		
		return lists;
	}
}
