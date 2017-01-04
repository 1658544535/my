/*
 * 文 件 名:  UserBabyServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-06-30
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.UserBabyService;
import com.tzmb2c.web.pojo.UserBabyPojo;
import com.tzmb2c.web.dao.UserBabyDao;

/**
 * UserBaby Service层
 */
public class UserBabyServiceImpl implements UserBabyService {
	
    @Autowired
    private UserBabyDao userBabydao;
    
	public int add(UserBabyPojo userBaby) {
		if(null == userBaby){
			return 0;
		}
        int rows = userBabydao.add(userBaby);
        return rows;
	}

    public int update(UserBabyPojo userBaby) {
		if(null == userBaby){
			return 0;
		}
        int rows = userBabydao.update(userBaby);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = userBabydao.delete(id);
        return rows;
    }
    
    public UserBabyPojo getById(Long id) {
		if(null == id){
			return null;
		}
		UserBabyPojo userBaby = userBabydao.getById(id);
        return userBaby;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = userBabydao.countBy(params);
		return rows;
	}
	
	public List<UserBabyPojo> listPage(Map<String, Object> params){
		List<UserBabyPojo> lists = userBabydao.listPage(params);
		return lists;
	}

  @Override
  public UserBabyPojo getByParams(Map<String, Object> params) {
    return userBabydao.getByParams(params);
  }
}
