/*
 * 文 件 名:  ProductRecommendDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-30
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ProductRecommendDao;
import com.tzmb2c.web.pojo.ProductRecommendPojo;
import com.tzmb2c.web.mapper.ProductRecommendMapper;

/**
 * ProductRecommend Dao层
 */
public class ProductRecommendDaoImpl implements ProductRecommendDao {
	
    @Autowired
    private ProductRecommendMapper productRecommendMapper;
    
	public int add(ProductRecommendPojo productRecommend) {
		if(null == productRecommend){
			return 0;
		}
        int rows = productRecommendMapper.insert(productRecommend);
        return rows;
	}

    public int update(ProductRecommendPojo productRecommend) {
		if(null == productRecommend){
			return 0;
		}
        int rows = productRecommendMapper.update(productRecommend);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = productRecommendMapper.deleteById(id);
        return rows;
    }
    
    public ProductRecommendPojo getById(Long id) {
		if(null == id){
			return null;
		}
		ProductRecommendPojo productRecommend = productRecommendMapper.getById(id);
        return productRecommend;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = productRecommendMapper.countBy(params);
		return rows;
	}
	
	public List<ProductRecommendPojo> listPage(Map<String, Object> params){
		List<ProductRecommendPojo> lists = productRecommendMapper.listPage(params);		
		return lists;
	}
}
