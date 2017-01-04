/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.service;

import java.util.List;
import java.util.Map;


import com.tzmb2c.web.pojo.TemplatePageDataPojo;

/**
 * @version 1.0
 * @author 
 */
public interface TemplatePageDataService {
	
	public int add(TemplatePageDataPojo templatePageData);

	public int update(TemplatePageDataPojo templatePageData);
    
	public int delete(Long id);

	public TemplatePageDataPojo getById(Long id);

	public Integer countBy(Map<String, Object> params);

	public List<TemplatePageDataPojo> listPage(Map<String, Object> params);
	
	public TemplatePageDataPojo findByParams(Map<String, Object> params);

}
