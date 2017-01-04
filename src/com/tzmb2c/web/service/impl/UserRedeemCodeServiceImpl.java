/*
 * 文 件 名:  UserRedeemCodeServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-12-27
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.UserRedeemCodeService;
import com.tzmb2c.web.pojo.UserRedeemCodePojo;
import com.tzmb2c.web.dao.UserRedeemCodeDao;

/**
 * UserRedeemCode Service层
 */
public class UserRedeemCodeServiceImpl implements UserRedeemCodeService {
	
    @Autowired
    private UserRedeemCodeDao userRedeemCodedao;
    
	public int add(UserRedeemCodePojo userRedeemCode) throws SQLException{
		if(null == userRedeemCode){
			return 0;
		}
        int rows = userRedeemCodedao.add(userRedeemCode);
        return rows;
	}

    public int update(UserRedeemCodePojo userRedeemCode) throws SQLException{
		if(null == userRedeemCode){
			return 0;
		}
        int rows = userRedeemCodedao.update(userRedeemCode);
        return rows;
    }
    
    public int delete(String code) throws SQLException{
		if(null == code){
			return 0;
		}
        int rows = userRedeemCodedao.delete(code);
        return rows;
    }
    
    public UserRedeemCodePojo getByCode(String code) throws SQLException{
		if(null == code){
			return null;
		}
		UserRedeemCodePojo userRedeemCode = userRedeemCodedao.getByCode(code);
        return userRedeemCode;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = userRedeemCodedao.countBy(params);
		return rows;
	}
	
	public List<UserRedeemCodePojo> listPage(Map<String, Object> params) throws SQLException{
		List<UserRedeemCodePojo> lists = userRedeemCodedao.listPage(params);
		return lists;
	}
}
