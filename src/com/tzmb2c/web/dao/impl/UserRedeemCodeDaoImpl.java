/*
 * 文 件 名:  UserRedeemCodeDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-12-27
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserRedeemCodeDao;
import com.tzmb2c.web.pojo.UserRedeemCodePojo;
import com.tzmb2c.web.mapper.UserRedeemCodeMapper;

/**
 * UserRedeemCode Dao层
 */
public class UserRedeemCodeDaoImpl implements UserRedeemCodeDao {
	
    @Autowired
    private UserRedeemCodeMapper userRedeemCodeMapper;
    
	public int add(UserRedeemCodePojo userRedeemCode) throws SQLException{
		if(null == userRedeemCode){
			return 0;
		}
        int rows = userRedeemCodeMapper.insert(userRedeemCode);
        return rows;
	}

    public int update(UserRedeemCodePojo userRedeemCode) throws SQLException{
		if(null == userRedeemCode){
			return 0;
		}
        int rows = userRedeemCodeMapper.update(userRedeemCode);
        return rows;
    }
    
    public int delete(String code) throws SQLException{
		if(null == code){
			return 0;
		}
        int rows = userRedeemCodeMapper.deleteByCode(code);
        return rows;
    }
    
    public UserRedeemCodePojo getByCode(String code) throws SQLException{
		if(null == code){
			return null;
		}
		UserRedeemCodePojo userRedeemCode = userRedeemCodeMapper.getByCode(code);
        return userRedeemCode;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = userRedeemCodeMapper.countBy(params);
		return rows;
	}
	
	public List<UserRedeemCodePojo> listPage(Map<String, Object> params) throws SQLException{
		List<UserRedeemCodePojo> lists = userRedeemCodeMapper.listPage(params);		
		return lists;
	}
}
