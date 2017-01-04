/*
 * 文 件 名:  YourFavouritesMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-28
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.YourFavouritesPojo;

public interface YourFavouritesMapper {
    
    YourFavouritesPojo getById(Long id);
    
    int countBy(Map<String, Object> params);

    List<YourFavouritesPojo> listPage(Map<String, Object> params);
    
    int insert(YourFavouritesPojo yourFavourites);
    
    int update(YourFavouritesPojo yourFavourites);
    
    int deleteById(Long id);
}