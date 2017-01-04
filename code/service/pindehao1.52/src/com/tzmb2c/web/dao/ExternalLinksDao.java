/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.dao;

import java.util.List;
import java.util.Map;


import com.tzmb2c.web.pojo.ExternalLinksPojo;

/**
 * @version 1.0
 * @author 
 */
public interface ExternalLinksDao {
	
	public int add(ExternalLinksPojo externalLinks);

	public int update(ExternalLinksPojo externalLinks);
    
	public int delete(Long id);

	public ExternalLinksPojo getById(Long id);

	public Integer countBy(Map<String, Object> params);

	public List<ExternalLinksPojo> listPage(Map<String, Object> params);

}
