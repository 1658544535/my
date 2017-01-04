/*
 * 文 件 名:  YourFavouritesServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-28
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.YourFavouritesService;
import com.tzmb2c.web.pojo.YourFavouritesPojo;
import com.tzmb2c.web.dao.YourFavouritesDao;

/**
 * YourFavourites Service层
 */
public class YourFavouritesServiceImpl implements YourFavouritesService {
	
    @Autowired
    private YourFavouritesDao yourFavouritesdao;
    
	public int add(YourFavouritesPojo yourFavourites) {
		if(null == yourFavourites){
			return 0;
		}
        int rows = yourFavouritesdao.add(yourFavourites);
        return rows;
	}

    public int update(YourFavouritesPojo yourFavourites) {
		if(null == yourFavourites){
			return 0;
		}
        int rows = yourFavouritesdao.update(yourFavourites);
        return rows;
    }
    
    public int delete(Long id) {
		if(null == id){
			return 0;
		}
        int rows = yourFavouritesdao.delete(id);
        return rows;
    }
    
    public YourFavouritesPojo getById(Long id) {
		if(null == id){
			return null;
		}
		YourFavouritesPojo yourFavourites = yourFavouritesdao.getById(id);
        return yourFavourites;
    }
	
	public Integer countBy(Map<String, Object> params){
		Integer rows = yourFavouritesdao.countBy(params);
		return rows;
	}
	
	public List<YourFavouritesPojo> listPage(Map<String, Object> params){
		List<YourFavouritesPojo> lists = yourFavouritesdao.listPage(params);
		return lists;
	}
}
