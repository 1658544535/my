/*
 * 文 件 名:  UserOrderNoticeDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-11-30
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserOrderNoticeDao;
import com.tzmb2c.web.pojo.UserOrderNoticePojo;
import com.tzmb2c.web.mapper.UserOrderNoticeMapper;

/**
 * UserOrderNotice Dao层
 */
public class UserOrderNoticeDaoImpl implements UserOrderNoticeDao {
	
    @Autowired
    private UserOrderNoticeMapper userOrderNoticeMapper;
    
	public int add(UserOrderNoticePojo userOrderNotice) throws SQLException{
		if(null == userOrderNotice){
			return 0;
		}
        int rows = userOrderNoticeMapper.insert(userOrderNotice);
        return rows;
	}

    public int update(UserOrderNoticePojo userOrderNotice) throws SQLException{
		if(null == userOrderNotice){
			return 0;
		}
        int rows = userOrderNoticeMapper.update(userOrderNotice);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = userOrderNoticeMapper.deleteById(id);
        return rows;
    }
    
    public UserOrderNoticePojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		UserOrderNoticePojo userOrderNotice = userOrderNoticeMapper.getById(id);
        return userOrderNotice;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = userOrderNoticeMapper.countBy(params);
		return rows;
	}
	
	public List<UserOrderNoticePojo> listPage(Map<String, Object> params) throws SQLException{
		List<UserOrderNoticePojo> lists = userOrderNoticeMapper.listPage(params);		
		return lists;
	}
}
