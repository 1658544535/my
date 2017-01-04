/*
 * 文 件 名:  HotBrandRecommendServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-30
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.HotBrandRecommendService;
import com.tzmb2c.web.pojo.HotBrandRecommendPojo;
import com.tzmb2c.web.dao.HotBrandRecommendDao;

/**
 * HotBrandRecommend Service层
 */
public class HotBrandRecommendServiceImpl implements HotBrandRecommendService {
	
    @Autowired
    private HotBrandRecommendDao hotBrandRecommenddao;
    
	public int add(HotBrandRecommendPojo hotBrandRecommend) {
		if(null == hotBrandRecommend){
			return 0;
		}
        int rows = hotBrandRecommenddao.add(hotBrandRecommend);
        return rows;
	}

    public int update(HotBrandRecommendPojo hotBrandRecommend) {
		if(null == hotBrandRecommend){
			return 0;
		}
        int rows = hotBrandRecommenddao.update(hotBrandRecommend);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = hotBrandRecommenddao.delete(id);
        return rows;
    }
    
    public HotBrandRecommendPojo getById(Long id) {
		if(null == id){
			return null;
		}
		HotBrandRecommendPojo hotBrandRecommend = hotBrandRecommenddao.getById(id);
        return hotBrandRecommend;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = hotBrandRecommenddao.countBy(params);
		return rows;
	}
	
	public List<HotBrandRecommendPojo> listPage(Map<String, Object> params){
		List<HotBrandRecommendPojo> lists = hotBrandRecommenddao.listPage(params);
		return lists;
	}
}
