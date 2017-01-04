/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.dao;

import java.util.List;
import java.util.Map;


import com.tzmb2c.web.pojo.ShopRecommendPojo;

/**
 * @version 1.0
 * @author 
 */
public interface ShopRecommendDao {
	
	public int add(ShopRecommendPojo shopRecommend);

	public int update(ShopRecommendPojo shopRecommend);
    
	public int delete(Long id);

	public ShopRecommendPojo getById(Long id);

	public Integer countBy(Map<String, Object> params);

	public List<ShopRecommendPojo> listPage(Map<String, Object> params);

}
