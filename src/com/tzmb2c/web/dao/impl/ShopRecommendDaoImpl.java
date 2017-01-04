/*
 * 文 件 名:  ShopRecommendDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-30
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ShopRecommendDao;
import com.tzmb2c.web.pojo.ShopRecommendPojo;
import com.tzmb2c.web.mapper.ShopRecommendMapper;

/**
 * ShopRecommend Dao层
 */
public class ShopRecommendDaoImpl implements ShopRecommendDao {
	
    @Autowired
    private ShopRecommendMapper shopRecommendMapper;
    
	public int add(ShopRecommendPojo shopRecommend) {
		if(null == shopRecommend){
			return 0;
		}
        int rows = shopRecommendMapper.insert(shopRecommend);
        return rows;
	}

    public int update(ShopRecommendPojo shopRecommend) {
		if(null == shopRecommend){
			return 0;
		}
        int rows = shopRecommendMapper.update(shopRecommend);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = shopRecommendMapper.deleteById(id);
        return rows;
    }
    
    public ShopRecommendPojo getById(Long id) {
		if(null == id){
			return null;
		}
		ShopRecommendPojo shopRecommend = shopRecommendMapper.getById(id);
        return shopRecommend;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = shopRecommendMapper.countBy(params);
		return rows;
	}
	
	public List<ShopRecommendPojo> listPage(Map<String, Object> params){
		List<ShopRecommendPojo> lists = shopRecommendMapper.listPage(params);		
		return lists;
	}
}
