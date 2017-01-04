/*
 * 文 件 名:  ProductRecommendServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-30
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.ProductRecommendService;
import com.tzmb2c.web.pojo.ProductRecommendPojo;
import com.tzmb2c.web.dao.ProductRecommendDao;

/**
 * ProductRecommend Service层
 */
public class ProductRecommendServiceImpl implements ProductRecommendService {
	
    @Autowired
    private ProductRecommendDao productRecommenddao;
    
	public int add(ProductRecommendPojo productRecommend) {
		if(null == productRecommend){
			return 0;
		}
        int rows = productRecommenddao.add(productRecommend);
        return rows;
	}

    public int update(ProductRecommendPojo productRecommend) {
		if(null == productRecommend){
			return 0;
		}
        int rows = productRecommenddao.update(productRecommend);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = productRecommenddao.delete(id);
        return rows;
    }
    
    public ProductRecommendPojo getById(Long id) {
		if(null == id){
			return null;
		}
		ProductRecommendPojo productRecommend = productRecommenddao.getById(id);
        return productRecommend;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = productRecommenddao.countBy(params);
		return rows;
	}
	
	public List<ProductRecommendPojo> listPage(Map<String, Object> params){
		List<ProductRecommendPojo> lists = productRecommenddao.listPage(params);
		return lists;
	}
}
