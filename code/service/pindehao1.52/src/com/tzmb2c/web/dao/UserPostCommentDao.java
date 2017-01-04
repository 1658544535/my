/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.dao;

import java.util.List;
import java.util.Map;


import com.tzmb2c.web.pojo.UserPostCommentPojo;

/**
 * @version 1.0
 * @author 
 */
public interface UserPostCommentDao {
	
	public int add(UserPostCommentPojo userPostComment);

	public int update(UserPostCommentPojo userPostComment);
    
	public int delete(Long id);

	public UserPostCommentPojo getById(Long id);

	public Integer countBy(Map<String, Object> params);

	public List<UserPostCommentPojo> listPage(Map<String, Object> params);

}
