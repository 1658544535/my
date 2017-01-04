/*
 * 文 件 名:  ProductRestrictionServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-12-05
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.ProductRestrictionService;
import com.tzmb2c.web.pojo.ProductRestrictionPojo;
import com.tzmb2c.web.dao.ProductRestrictionDao;

/**
 * ProductRestriction Service层
 */
public class ProductRestrictionServiceImpl implements ProductRestrictionService {
	
    @Autowired
    private ProductRestrictionDao productRestrictiondao;
    
	public int add(ProductRestrictionPojo productRestriction) throws SQLException{
		if(null == productRestriction){
			return 0;
		}
        int rows = productRestrictiondao.add(productRestriction);
        return rows;
	}

    public int update(ProductRestrictionPojo productRestriction) throws SQLException{
		if(null == productRestriction){
			return 0;
		}
        int rows = productRestrictiondao.update(productRestriction);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = productRestrictiondao.delete(id);
        return rows;
    }
    
    public ProductRestrictionPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		ProductRestrictionPojo productRestriction = productRestrictiondao.getById(id);
        return productRestriction;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = productRestrictiondao.countBy(params);
		return rows;
	}
	
	public List<ProductRestrictionPojo> listPage(Map<String, Object> params) throws SQLException{
		List<ProductRestrictionPojo> lists = productRestrictiondao.listPage(params);
		return lists;
	}
}
