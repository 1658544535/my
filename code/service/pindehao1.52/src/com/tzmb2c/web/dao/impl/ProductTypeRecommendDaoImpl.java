/*
 * 文 件 名:  ProductTypeRecommendDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-30
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ProductTypeRecommendDao;
import com.tzmb2c.web.pojo.ProductTypeRecommendPojo;
import com.tzmb2c.web.mapper.ProductTypeRecommendMapper;

/**
 * ProductTypeRecommend Dao层
 */
public class ProductTypeRecommendDaoImpl implements ProductTypeRecommendDao {
	
    @Autowired
    private ProductTypeRecommendMapper productTypeRecommendMapper;
    
	public int add(ProductTypeRecommendPojo productTypeRecommend) {
		if(null == productTypeRecommend){
			return 0;
		}
        int rows = productTypeRecommendMapper.insert(productTypeRecommend);
        return rows;
	}

    public int update(ProductTypeRecommendPojo productTypeRecommend) {
		if(null == productTypeRecommend){
			return 0;
		}
        int rows = productTypeRecommendMapper.update(productTypeRecommend);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = productTypeRecommendMapper.deleteById(id);
        return rows;
    }
    
    public ProductTypeRecommendPojo getById(Long id) {
		if(null == id){
			return null;
		}
		ProductTypeRecommendPojo productTypeRecommend = productTypeRecommendMapper.getById(id);
        return productTypeRecommend;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = productTypeRecommendMapper.countBy(params);
		return rows;
	}
	
	public List<ProductTypeRecommendPojo> listPage(Map<String, Object> params){
		List<ProductTypeRecommendPojo> lists = productTypeRecommendMapper.listPage(params);		
		return lists;
	}
}
