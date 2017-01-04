/*
 * 文 件 名:  CategorySettingDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-03
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.CategorySettingDao;
import com.tzmb2c.web.pojo.CategorySettingPojo;
import com.tzmb2c.web.mapper.CategorySettingMapper;

/**
 * CategorySetting Dao层
 */
public class CategorySettingDaoImpl implements CategorySettingDao {
	
    @Autowired
    private CategorySettingMapper categorySettingMapper;
    
	public int add(CategorySettingPojo categorySetting) {
		if(null == categorySetting){
			return 0;
		}
        int rows = categorySettingMapper.insert(categorySetting);
        return rows;
	}

    public int update(CategorySettingPojo categorySetting) {
		if(null == categorySetting){
			return 0;
		}
        int rows = categorySettingMapper.update(categorySetting);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = categorySettingMapper.deleteById(id);
        return rows;
    }
    
    public CategorySettingPojo getById(Long id) {
		if(null == id){
			return null;
		}
		CategorySettingPojo categorySetting = categorySettingMapper.getById(id);
        return categorySetting;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = categorySettingMapper.countBy(params);
		return rows;
	}
	
	public List<CategorySettingPojo> listPage(Map<String, Object> params){
		List<CategorySettingPojo> lists = categorySettingMapper.listPage(params);		
		return lists;
	}
}
