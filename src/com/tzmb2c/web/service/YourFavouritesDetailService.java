/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.service;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.UserCirclePostPojo;
import com.tzmb2c.web.pojo.YourFavouritesDetailPojo;

/**
 * @version 1.0
 * @author
 */
public interface YourFavouritesDetailService {

  public int add(YourFavouritesDetailPojo yourFavouritesDetail);

  public int update(YourFavouritesDetailPojo yourFavouritesDetail);

  public int delete(Long id);

  public YourFavouritesDetailPojo getById(Long id);

  public Integer countBy(Map<String, Object> params);

  public List<YourFavouritesDetailPojo> listPage(Map<String, Object> params);

  int getUserCirclePostFavouritesCount(Map<String, Object> params);

  List<UserCirclePostPojo> getUserCirclePostFavouritesList(Map<String, Object> params);

  int getProductFavouritesCount(Map<String, Object> params);

  List<ProductPojo> getProductFavouritesList(Map<String, Object> params);

  int getProductTypeFavouritesCount(Map<String, Object> params);

  List<ProductPojo> getProductTypeFavouritesList(Map<String, Object> params);
}
