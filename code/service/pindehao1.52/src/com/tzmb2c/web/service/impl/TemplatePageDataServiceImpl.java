/*
 * 文 件 名:  TemplatePageDataServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-28
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.TemplatePageDataService;
import com.tzmb2c.web.pojo.TemplatePageDataPojo;
import com.tzmb2c.web.dao.TemplatePageDataDao;

/**
 * TemplatePageData Service层
 */
public class TemplatePageDataServiceImpl implements TemplatePageDataService {
	
    @Autowired
    private TemplatePageDataDao templatePageDatadao;
    
	public int add(TemplatePageDataPojo templatePageData) {
		if(null == templatePageData){
			return 0;
		}
        int rows = templatePageDatadao.add(templatePageData);
        return rows;
	}

    public int update(TemplatePageDataPojo templatePageData) {
		if(null == templatePageData){
			return 0;
		}
        int rows = templatePageDatadao.update(templatePageData);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = templatePageDatadao.delete(id);
        return rows;
    }
    
    public TemplatePageDataPojo getById(Long id) {
		if(null == id){
			return null;
		}
		TemplatePageDataPojo templatePageData = templatePageDatadao.getById(id);
        return templatePageData;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = templatePageDatadao.countBy(params);
		return rows;
	}
	
	public List<TemplatePageDataPojo> listPage(Map<String, Object> params){
		List<TemplatePageDataPojo> lists = templatePageDatadao.listPage(params);
		return lists;
	}

  public TemplatePageDataPojo findByParams(Map<String, Object> params) {
    return templatePageDatadao.findByParams(params);
  }
}
