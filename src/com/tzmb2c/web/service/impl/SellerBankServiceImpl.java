/*
 * 文 件 名:  SellerBankServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2017-01-17
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.SellerBankService;
import com.tzmb2c.web.pojo.SellerBankPojo;
import com.tzmb2c.web.dao.SellerBankDao;

/**
 * SellerBank Service层
 */
public class SellerBankServiceImpl implements SellerBankService {
	
    @Autowired
    private SellerBankDao sellerBankdao;
    
	public int add(SellerBankPojo sellerBank) throws SQLException{
		if(null == sellerBank){
			return 0;
		}
        int rows = sellerBankdao.add(sellerBank);
        return rows;
	}

    public int update(SellerBankPojo sellerBank) throws SQLException{
		if(null == sellerBank){
			return 0;
		}
        int rows = sellerBankdao.update(sellerBank);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = sellerBankdao.delete(id);
        return rows;
    }
    
    public SellerBankPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		SellerBankPojo sellerBank = sellerBankdao.getById(id);
        return sellerBank;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = sellerBankdao.countBy(params);
		return rows;
	}
	
	public List<SellerBankPojo> listPage(Map<String, Object> params) throws SQLException{
		List<SellerBankPojo> lists = sellerBankdao.listPage(params);
		return lists;
	}
}
