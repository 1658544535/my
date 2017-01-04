/*
 * 文 件 名:  GrouponPushDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-11-14
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.GrouponPushDao;
import com.tzmb2c.web.pojo.GrouponPushPojo;
import com.tzmb2c.web.mapper.GrouponPushMapper;

/**
 * GrouponPush Dao层
 */
public class GrouponPushDaoImpl implements GrouponPushDao {
	
    @Autowired
    private GrouponPushMapper grouponPushMapper;
    
	public int add(GrouponPushPojo grouponPush) throws SQLException{
		if(null == grouponPush){
			return 0;
		}
        int rows = grouponPushMapper.insert(grouponPush);
        return rows;
	}

    public int update(GrouponPushPojo grouponPush) throws SQLException{
		if(null == grouponPush){
			return 0;
		}
        int rows = grouponPushMapper.update(grouponPush);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = grouponPushMapper.deleteById(id);
        return rows;
    }
    
    public GrouponPushPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		GrouponPushPojo grouponPush = grouponPushMapper.getById(id);
        return grouponPush;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = grouponPushMapper.countBy(params);
		return rows;
	}
	
	public List<GrouponPushPojo> listPage(Map<String, Object> params) throws SQLException{
		List<GrouponPushPojo> lists = grouponPushMapper.listPage(params);		
		return lists;
	}
}
