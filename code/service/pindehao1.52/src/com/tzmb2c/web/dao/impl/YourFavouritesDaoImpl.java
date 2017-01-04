/*
 * 文 件 名:  YourFavouritesDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-28
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.YourFavouritesDao;
import com.tzmb2c.web.pojo.YourFavouritesPojo;
import com.tzmb2c.web.mapper.YourFavouritesMapper;

/**
 * YourFavourites Dao层
 */
public class YourFavouritesDaoImpl implements YourFavouritesDao {
	
    @Autowired
    private YourFavouritesMapper yourFavouritesMapper;
    
	public int add(YourFavouritesPojo yourFavourites) {
		if(null == yourFavourites){
			return 0;
		}
        int rows = yourFavouritesMapper.insert(yourFavourites);
        return rows;
	}

    public int update(YourFavouritesPojo yourFavourites) {
		if(null == yourFavourites){
			return 0;
		}
        int rows = yourFavouritesMapper.update(yourFavourites);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = yourFavouritesMapper.deleteById(id);
        return rows;
    }
    
    public YourFavouritesPojo getById(Long id) {
		if(null == id){
			return null;
		}
		YourFavouritesPojo yourFavourites = yourFavouritesMapper.getById(id);
        return yourFavourites;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = yourFavouritesMapper.countBy(params);
		return rows;
	}
	
	public List<YourFavouritesPojo> listPage(Map<String, Object> params){
		List<YourFavouritesPojo> lists = yourFavouritesMapper.listPage(params);		
		return lists;
	}
}
