/*
 * 文 件 名:  ProductTypeRecommendServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-30
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.ProductTypeRecommendService;
import com.tzmb2c.web.pojo.ProductTypeRecommendPojo;
import com.tzmb2c.web.dao.ProductTypeRecommendDao;

/**
 * ProductTypeRecommend Service层
 */
public class ProductTypeRecommendServiceImpl implements ProductTypeRecommendService {
	
    @Autowired
    private ProductTypeRecommendDao productTypeRecommenddao;
    
	public int add(ProductTypeRecommendPojo productTypeRecommend) {
		if(null == productTypeRecommend){
			return 0;
		}
        int rows = productTypeRecommenddao.add(productTypeRecommend);
        return rows;
	}

    public int update(ProductTypeRecommendPojo productTypeRecommend) {
		if(null == productTypeRecommend){
			return 0;
		}
        int rows = productTypeRecommenddao.update(productTypeRecommend);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = productTypeRecommenddao.delete(id);
        return rows;
    }
    
    public ProductTypeRecommendPojo getById(Long id) {
		if(null == id){
			return null;
		}
		ProductTypeRecommendPojo productTypeRecommend = productTypeRecommenddao.getById(id);
        return productTypeRecommend;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = productTypeRecommenddao.countBy(params);
		return rows;
	}
	
	public List<ProductTypeRecommendPojo> listPage(Map<String, Object> params){
		List<ProductTypeRecommendPojo> lists = productTypeRecommenddao.listPage(params);
		return lists;
	}
}
