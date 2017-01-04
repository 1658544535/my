/*
 * 文 件 名:  HotBrandRecommendDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-30
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.HotBrandRecommendDao;
import com.tzmb2c.web.pojo.HotBrandRecommendPojo;
import com.tzmb2c.web.mapper.HotBrandRecommendMapper;

/**
 * HotBrandRecommend Dao层
 */
public class HotBrandRecommendDaoImpl implements HotBrandRecommendDao {
	
    @Autowired
    private HotBrandRecommendMapper hotBrandRecommendMapper;
    
	public int add(HotBrandRecommendPojo hotBrandRecommend) {
		if(null == hotBrandRecommend){
			return 0;
		}
        int rows = hotBrandRecommendMapper.insert(hotBrandRecommend);
        return rows;
	}

    public int update(HotBrandRecommendPojo hotBrandRecommend) {
		if(null == hotBrandRecommend){
			return 0;
		}
        int rows = hotBrandRecommendMapper.update(hotBrandRecommend);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = hotBrandRecommendMapper.deleteById(id);
        return rows;
    }
    
    public HotBrandRecommendPojo getById(Long id) {
		if(null == id){
			return null;
		}
		HotBrandRecommendPojo hotBrandRecommend = hotBrandRecommendMapper.getById(id);
        return hotBrandRecommend;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = hotBrandRecommendMapper.countBy(params);
		return rows;
	}
	
	public List<HotBrandRecommendPojo> listPage(Map<String, Object> params){
		List<HotBrandRecommendPojo> lists = hotBrandRecommendMapper.listPage(params);		
		return lists;
	}
}
