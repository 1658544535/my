/*
 * 文 件 名:  GrouponPushServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-11-14
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.GrouponPushService;
import com.tzmb2c.web.pojo.GrouponPushPojo;
import com.tzmb2c.web.dao.GrouponPushDao;

/**
 * GrouponPush Service层
 */
public class GrouponPushServiceImpl implements GrouponPushService {
	
    @Autowired
    private GrouponPushDao grouponPushdao;
    
	public int add(GrouponPushPojo grouponPush) throws SQLException{
		if(null == grouponPush){
			return 0;
		}
        int rows = grouponPushdao.add(grouponPush);
        return rows;
	}

    public int update(GrouponPushPojo grouponPush) throws SQLException{
		if(null == grouponPush){
			return 0;
		}
        int rows = grouponPushdao.update(grouponPush);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = grouponPushdao.delete(id);
        return rows;
    }
    
    public GrouponPushPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		GrouponPushPojo grouponPush = grouponPushdao.getById(id);
        return grouponPush;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = grouponPushdao.countBy(params);
		return rows;
	}
	
	public List<GrouponPushPojo> listPage(Map<String, Object> params) throws SQLException{
		List<GrouponPushPojo> lists = grouponPushdao.listPage(params);
		return lists;
	}
}
