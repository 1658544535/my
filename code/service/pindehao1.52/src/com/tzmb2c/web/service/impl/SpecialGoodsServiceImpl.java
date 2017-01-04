/*
 * 文 件 名:  SpecialGoodsServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-14
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.SpecialGoodsService;
import com.tzmb2c.web.pojo.SpecialGoodsPojo;
import com.tzmb2c.web.dao.SpecialGoodsDao;

/**
 * SpecialGoods Service层
 */
public class SpecialGoodsServiceImpl implements SpecialGoodsService {
	
    @Autowired
    private SpecialGoodsDao specialGoodsdao;
    
	public int add(SpecialGoodsPojo specialGoods) throws SQLException{
		if(null == specialGoods){
			return 0;
		}
        int rows = specialGoodsdao.add(specialGoods);
        return rows;
	}

    public int update(SpecialGoodsPojo specialGoods) throws SQLException{
		if(null == specialGoods){
			return 0;
		}
        int rows = specialGoodsdao.update(specialGoods);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = specialGoodsdao.delete(id);
        return rows;
    }
    
    public SpecialGoodsPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		SpecialGoodsPojo specialGoods = specialGoodsdao.getById(id);
        return specialGoods;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = specialGoodsdao.countBy(params);
		return rows;
	}
	
	public List<SpecialGoodsPojo> listPage(Map<String, Object> params) throws SQLException{
		List<SpecialGoodsPojo> lists = specialGoodsdao.listPage(params);
		return lists;
	}
}
