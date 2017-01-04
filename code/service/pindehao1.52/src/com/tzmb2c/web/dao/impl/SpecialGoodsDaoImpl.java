/*
 * 文 件 名:  SpecialGoodsDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-14
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SpecialGoodsDao;
import com.tzmb2c.web.pojo.SpecialGoodsPojo;
import com.tzmb2c.web.mapper.SpecialGoodsMapper;

/**
 * SpecialGoods Dao层
 */
public class SpecialGoodsDaoImpl implements SpecialGoodsDao {
	
    @Autowired
    private SpecialGoodsMapper specialGoodsMapper;
    
	public int add(SpecialGoodsPojo specialGoods) throws SQLException{
		if(null == specialGoods){
			return 0;
		}
        int rows = specialGoodsMapper.insert(specialGoods);
        return rows;
	}

    public int update(SpecialGoodsPojo specialGoods) throws SQLException{
		if(null == specialGoods){
			return 0;
		}
        int rows = specialGoodsMapper.update(specialGoods);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = specialGoodsMapper.deleteById(id);
        return rows;
    }
    
    public SpecialGoodsPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		SpecialGoodsPojo specialGoods = specialGoodsMapper.getById(id);
        return specialGoods;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = specialGoodsMapper.countBy(params);
		return rows;
	}
	
	public List<SpecialGoodsPojo> listPage(Map<String, Object> params) throws SQLException{
		List<SpecialGoodsPojo> lists = specialGoodsMapper.listPage(params);		
		return lists;
	}
}
