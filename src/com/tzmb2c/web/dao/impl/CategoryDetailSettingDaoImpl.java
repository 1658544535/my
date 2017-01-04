/*
 * 文 件 名:  CategoryDetailSettingDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-03
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.CategoryDetailSettingDao;
import com.tzmb2c.web.pojo.CategoryDetailSettingPojo;
import com.tzmb2c.web.mapper.CategoryDetailSettingMapper;

/**
 * CategoryDetailSetting Dao层
 */
public class CategoryDetailSettingDaoImpl implements CategoryDetailSettingDao {
	
    @Autowired
    private CategoryDetailSettingMapper categoryDetailSettingMapper;
    
	public int add(CategoryDetailSettingPojo categoryDetailSetting) {
		if(null == categoryDetailSetting){
			return 0;
		}
        int rows = categoryDetailSettingMapper.insert(categoryDetailSetting);
        return rows;
	}

    public int update(CategoryDetailSettingPojo categoryDetailSetting) {
		if(null == categoryDetailSetting){
			return 0;
		}
        int rows = categoryDetailSettingMapper.update(categoryDetailSetting);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = categoryDetailSettingMapper.deleteById(id);
        return rows;
    }
    
    public CategoryDetailSettingPojo getById(Long id) {
		if(null == id){
			return null;
		}
		CategoryDetailSettingPojo categoryDetailSetting = categoryDetailSettingMapper.getById(id);
        return categoryDetailSetting;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = categoryDetailSettingMapper.countBy(params);
		return rows;
	}
	
	public List<CategoryDetailSettingPojo> listPage(Map<String, Object> params){
		List<CategoryDetailSettingPojo> lists = categoryDetailSettingMapper.listPage(params);		
		return lists;
	}
}
