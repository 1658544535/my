/*
 * 文 件 名:  UserOrderNoticeServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-11-30
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.UserOrderNoticeService;
import com.tzmb2c.web.pojo.UserOrderNoticePojo;
import com.tzmb2c.web.dao.UserOrderNoticeDao;

/**
 * UserOrderNotice Service层
 */
public class UserOrderNoticeServiceImpl implements UserOrderNoticeService {
	
    @Autowired
    private UserOrderNoticeDao userOrderNoticedao;
    
	public int add(UserOrderNoticePojo userOrderNotice) throws SQLException{
		if(null == userOrderNotice){
			return 0;
		}
        int rows = userOrderNoticedao.add(userOrderNotice);
        return rows;
	}

    public int update(UserOrderNoticePojo userOrderNotice) throws SQLException{
		if(null == userOrderNotice){
			return 0;
		}
        int rows = userOrderNoticedao.update(userOrderNotice);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = userOrderNoticedao.delete(id);
        return rows;
    }
    
    public UserOrderNoticePojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		UserOrderNoticePojo userOrderNotice = userOrderNoticedao.getById(id);
        return userOrderNotice;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = userOrderNoticedao.countBy(params);
		return rows;
	}
	
	public List<UserOrderNoticePojo> listPage(Map<String, Object> params) throws SQLException{
		List<UserOrderNoticePojo> lists = userOrderNoticedao.listPage(params);
		return lists;
	}
}
