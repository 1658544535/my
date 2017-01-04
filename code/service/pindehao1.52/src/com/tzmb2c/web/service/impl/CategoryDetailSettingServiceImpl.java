/*
 * 文 件 名:  CategoryDetailSettingServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-03
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.CategoryDetailSettingService;
import com.tzmb2c.web.pojo.CategoryDetailSettingPojo;
import com.tzmb2c.web.dao.CategoryDetailSettingDao;

/**
 * CategoryDetailSetting Service层
 */
public class CategoryDetailSettingServiceImpl implements CategoryDetailSettingService {
	
    @Autowired
    private CategoryDetailSettingDao categoryDetailSettingdao;
    
	public int add(CategoryDetailSettingPojo categoryDetailSetting) {
		if(null == categoryDetailSetting){
			return 0;
		}
        int rows = categoryDetailSettingdao.add(categoryDetailSetting);
        return rows;
	}

    public int update(CategoryDetailSettingPojo categoryDetailSetting) {
		if(null == categoryDetailSetting){
			return 0;
		}
        int rows = categoryDetailSettingdao.update(categoryDetailSetting);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = categoryDetailSettingdao.delete(id);
        return rows;
    }
    
    public CategoryDetailSettingPojo getById(Long id) {
		if(null == id){
			return null;
		}
		CategoryDetailSettingPojo categoryDetailSetting = categoryDetailSettingdao.getById(id);
        return categoryDetailSetting;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = categoryDetailSettingdao.countBy(params);
		return rows;
	}
	
	public List<CategoryDetailSettingPojo> listPage(Map<String, Object> params){
		List<CategoryDetailSettingPojo> lists = categoryDetailSettingdao.listPage(params);
		return lists;
	}
}
