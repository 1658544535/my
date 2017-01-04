/*
 * 文 件 名:  ActivityProductCommentServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-26
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.ActivityProductCommentService;
import com.tzmb2c.web.pojo.ActivityProductCommentPojo;
import com.tzmb2c.web.dao.ActivityProductCommentDao;

/**
 * ActivityProductComment Service层
 */
public class ActivityProductCommentServiceImpl implements ActivityProductCommentService {
	
    @Autowired
    private ActivityProductCommentDao activityProductCommentdao;
    
	public int add(ActivityProductCommentPojo activityProductComment) throws SQLException{
		if(null == activityProductComment){
			return 0;
		}
        int rows = activityProductCommentdao.add(activityProductComment);
        return rows;
	}

    public int update(ActivityProductCommentPojo activityProductComment) throws SQLException{
		if(null == activityProductComment){
			return 0;
		}
        int rows = activityProductCommentdao.update(activityProductComment);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = activityProductCommentdao.delete(id);
        return rows;
    }
    
    public ActivityProductCommentPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		ActivityProductCommentPojo activityProductComment = activityProductCommentdao.getById(id);
        return activityProductComment;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = activityProductCommentdao.countBy(params);
		return rows;
	}
	
	public List<ActivityProductCommentPojo> listPage(Map<String, Object> params) throws SQLException{
		List<ActivityProductCommentPojo> lists = activityProductCommentdao.listPage(params);
		return lists;
	}
}
