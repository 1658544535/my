/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.service;

import java.util.List;
import java.util.Map;


import com.tzmb2c.web.pojo.CategorySettingPojo;

/**
 * @version 1.0
 * @author 
 */
public interface CategorySettingService {
	
	public int add(CategorySettingPojo categorySetting);

	public int update(CategorySettingPojo categorySetting);
    
	public int delete(Long id);

	public CategorySettingPojo getById(Long id);

	public Integer countBy(Map<String, Object> params);

	public List<CategorySettingPojo> listPage(Map<String, Object> params);

}
