/*
 * 文 件 名:  ActivityProductCommentDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-26
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ActivityProductCommentDao;
import com.tzmb2c.web.pojo.ActivityProductCommentPojo;
import com.tzmb2c.web.mapper.ActivityProductCommentMapper;

/**
 * ActivityProductComment Dao层
 */
public class ActivityProductCommentDaoImpl implements ActivityProductCommentDao {
	
    @Autowired
    private ActivityProductCommentMapper activityProductCommentMapper;
    
	public int add(ActivityProductCommentPojo activityProductComment) throws SQLException{
		if(null == activityProductComment){
			return 0;
		}
        int rows = activityProductCommentMapper.insert(activityProductComment);
        return rows;
	}

    public int update(ActivityProductCommentPojo activityProductComment) throws SQLException{
		if(null == activityProductComment){
			return 0;
		}
        int rows = activityProductCommentMapper.update(activityProductComment);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = activityProductCommentMapper.deleteById(id);
        return rows;
    }
    
    public ActivityProductCommentPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		ActivityProductCommentPojo activityProductComment = activityProductCommentMapper.getById(id);
        return activityProductComment;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = activityProductCommentMapper.countBy(params);
		return rows;
	}
	
	public List<ActivityProductCommentPojo> listPage(Map<String, Object> params) throws SQLException{
		List<ActivityProductCommentPojo> lists = activityProductCommentMapper.listPage(params);		
		return lists;
	}
}
