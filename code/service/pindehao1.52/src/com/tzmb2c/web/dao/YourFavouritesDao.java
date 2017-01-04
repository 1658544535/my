/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.dao;

import java.util.List;
import java.util.Map;


import com.tzmb2c.web.pojo.YourFavouritesPojo;

/**
 * @version 1.0
 * @author 
 */
public interface YourFavouritesDao {
	
	public int add(YourFavouritesPojo yourFavourites);

	public int update(YourFavouritesPojo yourFavourites);
    
	public int delete(Long id);

	public YourFavouritesPojo getById(Long id);

	public Integer countBy(Map<String, Object> params);

	public List<YourFavouritesPojo> listPage(Map<String, Object> params);

}
