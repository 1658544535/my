/*
 * 文 件 名:  TemplatePageDataDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-28
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.TemplatePageDataDao;
import com.tzmb2c.web.pojo.TemplatePageDataPojo;
import com.tzmb2c.web.mapper.TemplatePageDataMapper;

/**
 * TemplatePageData Dao层
 */
public class TemplatePageDataDaoImpl implements TemplatePageDataDao {
	
    @Autowired
    private TemplatePageDataMapper templatePageDataMapper;
    
	public int add(TemplatePageDataPojo templatePageData) {
		if(null == templatePageData){
			return 0;
		}
        int rows = templatePageDataMapper.insert(templatePageData);
        return rows;
	}

    public int update(TemplatePageDataPojo templatePageData) {
		if(null == templatePageData){
			return 0;
		}
        int rows = templatePageDataMapper.update(templatePageData);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = templatePageDataMapper.deleteById(id);
        return rows;
    }
    
    public TemplatePageDataPojo getById(Long id) {
		if(null == id){
			return null;
		}
		TemplatePageDataPojo templatePageData = templatePageDataMapper.getById(id);
        return templatePageData;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = templatePageDataMapper.countBy(params);
		return rows;
	}
	
	public List<TemplatePageDataPojo> listPage(Map<String, Object> params){
		List<TemplatePageDataPojo> lists = templatePageDataMapper.listPage(params);		
		return lists;
	}

  public TemplatePageDataPojo findByParams(Map<String, Object> params) {
    return templatePageDataMapper.findByParams(params);
  }
}
