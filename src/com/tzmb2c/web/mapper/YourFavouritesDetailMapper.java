/*
 * 文 件 名: YourFavouritesDetailMapper.java 创 建 人: admin 创建时间: 2016-08-28
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.UserCirclePostPojo;
import com.tzmb2c.web.pojo.YourFavouritesDetailPojo;

public interface YourFavouritesDetailMapper {

  YourFavouritesDetailPojo getById(Long id);

  int countBy(Map<String, Object> params);

  List<YourFavouritesDetailPojo> listPage(Map<String, Object> params);

  int insert(YourFavouritesDetailPojo yourFavouritesDetail);

  int update(YourFavouritesDetailPojo yourFavouritesDetail);

  int deleteById(Long id);

  int getUserCirclePostFavouritesCount(Map<String, Object> params);

  List<UserCirclePostPojo> getUserCirclePostFavouritesList(Map<String, Object> params);

  int getProductFavouritesCount(Map<String, Object> params);

  List<ProductPojo> getProductFavouritesList(Map<String, Object> params);

  int getProductTypeFavouritesCount(Map<String, Object> params);

  List<ProductPojo> getProductTypeFavouritesList(Map<String, Object> params);
}
