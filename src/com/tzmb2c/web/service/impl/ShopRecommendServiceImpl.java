/*
 * 文 件 名:  ShopRecommendServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-30
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.ShopRecommendService;
import com.tzmb2c.web.pojo.ShopRecommendPojo;
import com.tzmb2c.web.dao.ShopRecommendDao;

/**
 * ShopRecommend Service层
 */
public class ShopRecommendServiceImpl implements ShopRecommendService {
	
    @Autowired
    private ShopRecommendDao shopRecommenddao;
    
	public int add(ShopRecommendPojo shopRecommend) {
		if(null == shopRecommend){
			return 0;
		}
        int rows = shopRecommenddao.add(shopRecommend);
        return rows;
	}

    public int update(ShopRecommendPojo shopRecommend) {
		if(null == shopRecommend){
			return 0;
		}
        int rows = shopRecommenddao.update(shopRecommend);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = shopRecommenddao.delete(id);
        return rows;
    }
    
    public ShopRecommendPojo getById(Long id) {
		if(null == id){
			return null;
		}
		ShopRecommendPojo shopRecommend = shopRecommenddao.getById(id);
        return shopRecommend;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = shopRecommenddao.countBy(params);
		return rows;
	}
	
	public List<ShopRecommendPojo> listPage(Map<String, Object> params){
		List<ShopRecommendPojo> lists = shopRecommenddao.listPage(params);
		return lists;
	}
}
