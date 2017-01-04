/*
 * 文 件 名:  ProductRestrictionDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-12-05
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ProductRestrictionDao;
import com.tzmb2c.web.pojo.ProductRestrictionPojo;
import com.tzmb2c.web.mapper.ProductRestrictionMapper;

/**
 * ProductRestriction Dao层
 */
public class ProductRestrictionDaoImpl implements ProductRestrictionDao {
	
    @Autowired
    private ProductRestrictionMapper productRestrictionMapper;
    
	public int add(ProductRestrictionPojo productRestriction) throws SQLException{
		if(null == productRestriction){
			return 0;
		}
        int rows = productRestrictionMapper.insert(productRestriction);
        return rows;
	}

    public int update(ProductRestrictionPojo productRestriction) throws SQLException{
		if(null == productRestriction){
			return 0;
		}
        int rows = productRestrictionMapper.update(productRestriction);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = productRestrictionMapper.deleteById(id);
        return rows;
    }
    
    public ProductRestrictionPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		ProductRestrictionPojo productRestriction = productRestrictionMapper.getById(id);
        return productRestriction;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = productRestrictionMapper.countBy(params);
		return rows;
	}
	
	public List<ProductRestrictionPojo> listPage(Map<String, Object> params) throws SQLException{
		List<ProductRestrictionPojo> lists = productRestrictionMapper.listPage(params);		
		return lists;
	}
}
