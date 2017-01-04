/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.service;

import java.util.List;
import java.util.Map;


import com.tzmb2c.web.pojo.HotBrandRecommendPojo;

/**
 * @version 1.0
 * @author 
 */
public interface HotBrandRecommendService {
	
	public int add(HotBrandRecommendPojo hotBrandRecommend);

	public int update(HotBrandRecommendPojo hotBrandRecommend);
    
	public int delete(Long id);

	public HotBrandRecommendPojo getById(Long id);

	public Integer countBy(Map<String, Object> params);

	public List<HotBrandRecommendPojo> listPage(Map<String, Object> params);

}
