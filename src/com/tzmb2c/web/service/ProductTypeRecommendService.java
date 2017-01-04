/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.service;

import java.util.List;
import java.util.Map;


import com.tzmb2c.web.pojo.ProductTypeRecommendPojo;

/**
 * @version 1.0
 * @author 
 */
public interface ProductTypeRecommendService {
	
	public int add(ProductTypeRecommendPojo productTypeRecommend);

	public int update(ProductTypeRecommendPojo productTypeRecommend);
    
	public int delete(Long id);

	public ProductTypeRecommendPojo getById(Long id);

	public Integer countBy(Map<String, Object> params);

	public List<ProductTypeRecommendPojo> listPage(Map<String, Object> params);

}
