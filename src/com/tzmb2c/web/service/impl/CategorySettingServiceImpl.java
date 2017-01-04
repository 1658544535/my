/*
 * 文 件 名:  CategorySettingServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-03
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.CategorySettingService;
import com.tzmb2c.web.pojo.CategorySettingPojo;
import com.tzmb2c.web.dao.CategorySettingDao;

/**
 * CategorySetting Service层
 */
public class CategorySettingServiceImpl implements CategorySettingService {
	
    @Autowired
    private CategorySettingDao categorySettingdao;
    
	public int add(CategorySettingPojo categorySetting) {
		if(null == categorySetting){
			return 0;
		}
        int rows = categorySettingdao.add(categorySetting);
        return rows;
	}

    public int update(CategorySettingPojo categorySetting) {
		if(null == categorySetting){
			return 0;
		}
        int rows = categorySettingdao.update(categorySetting);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = categorySettingdao.delete(id);
        return rows;
    }
    
    public CategorySettingPojo getById(Long id) {
		if(null == id){
			return null;
		}
		CategorySettingPojo categorySetting = categorySettingdao.getById(id);
        return categorySetting;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = categorySettingdao.countBy(params);
		return rows;
	}
	
	public List<CategorySettingPojo> listPage(Map<String, Object> params){
		List<CategorySettingPojo> lists = categorySettingdao.listPage(params);
		return lists;
	}
}
