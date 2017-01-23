/*
 * 文 件 名:  SellerBankDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2017-01-17
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SellerBankDao;
import com.tzmb2c.web.pojo.SellerBankPojo;
import com.tzmb2c.web.mapper.SellerBankMapper;

/**
 * SellerBank Dao层
 */
public class SellerBankDaoImpl implements SellerBankDao {
	
    @Autowired
    private SellerBankMapper sellerBankMapper;
    
	public int add(SellerBankPojo sellerBank) throws SQLException{
		if(null == sellerBank){
			return 0;
		}
        int rows = sellerBankMapper.insert(sellerBank);
        return rows;
	}

    public int update(SellerBankPojo sellerBank) throws SQLException{
		if(null == sellerBank){
			return 0;
		}
        int rows = sellerBankMapper.update(sellerBank);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = sellerBankMapper.deleteById(id);
        return rows;
    }
    
    public SellerBankPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		SellerBankPojo sellerBank = sellerBankMapper.getById(id);
        return sellerBank;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = sellerBankMapper.countBy(params);
		return rows;
	}
	
	public List<SellerBankPojo> listPage(Map<String, Object> params) throws SQLException{
		List<SellerBankPojo> lists = sellerBankMapper.listPage(params);		
		return lists;
	}
}
