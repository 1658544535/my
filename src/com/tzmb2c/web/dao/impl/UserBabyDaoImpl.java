/*
 * 文 件 名:  UserBabyDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-06-30
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserBabyDao;
import com.tzmb2c.web.pojo.UserBabyPojo;
import com.tzmb2c.web.mapper.UserBabyMapper;

/**
 * UserBaby Dao层
 */
public class UserBabyDaoImpl implements UserBabyDao {
	
    @Autowired
    private UserBabyMapper userBabyMapper;
    
	public int add(UserBabyPojo userBaby) {
		if(null == userBaby){
			return 0;
		}
        int rows = userBabyMapper.insert(userBaby);
        return rows;
	}

    public int update(UserBabyPojo userBaby) {
		if(null == userBaby){
			return 0;
		}
        int rows = userBabyMapper.update(userBaby);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = userBabyMapper.deleteById(id);
        return rows;
    }
    
    public UserBabyPojo getById(Long id) {
		if(null == id){
			return null;
		}
		UserBabyPojo userBaby = userBabyMapper.getById(id);
        return userBaby;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = userBabyMapper.countBy(params);
		return rows;
	}
	
	public List<UserBabyPojo> listPage(Map<String, Object> params){
		List<UserBabyPojo> lists = userBabyMapper.listPage(params);		
		return lists;
	}

  @Override
  public UserBabyPojo getByParams(Map<String, Object> params) {
    return userBabyMapper.getByParams(params);
  }
}
