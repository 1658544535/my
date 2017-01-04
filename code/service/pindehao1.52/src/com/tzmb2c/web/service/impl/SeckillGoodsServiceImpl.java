/*
 * 文 件 名:  SeckillGoodsServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-26
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.SeckillGoodsService;
import com.tzmb2c.web.pojo.SeckillGoodsPojo;
import com.tzmb2c.web.dao.SeckillGoodsDao;

/**
 * SeckillGoods Service层
 */
public class SeckillGoodsServiceImpl implements SeckillGoodsService {
	
    @Autowired
    private SeckillGoodsDao seckillGoodsdao;
    
	public int add(SeckillGoodsPojo seckillGoods) throws SQLException{
		if(null == seckillGoods){
			return 0;
		}
        int rows = seckillGoodsdao.add(seckillGoods);
        return rows;
	}

    public int update(SeckillGoodsPojo seckillGoods) throws SQLException{
		if(null == seckillGoods){
			return 0;
		}
        int rows = seckillGoodsdao.update(seckillGoods);
        return rows;
    }
    
    public int delete(Long id) throws SQLException{
		if(null == id){
			return 0;
		}
        int rows = seckillGoodsdao.delete(id);
        return rows;
    }
    
    public SeckillGoodsPojo getById(Long id) throws SQLException{
		if(null == id){
			return null;
		}
		SeckillGoodsPojo seckillGoods = seckillGoodsdao.getById(id);
        return seckillGoods;
    }
	
	public Integer countBy(Map<String, Object> params) throws SQLException{
		Integer rows = seckillGoodsdao.countBy(params);
		return rows;
	}
	
	public List<SeckillGoodsPojo> listPage(Map<String, Object> params) throws SQLException{
		List<SeckillGoodsPojo> lists = seckillGoodsdao.listPage(params);
		return lists;
	}
}
