/*
 * 文 件 名: YourFavouritesDetailServiceImpl.java 创 建 人: admin 创建时间: 2016-08-28
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.YourFavouritesDetailDao;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.UserCirclePostPojo;
import com.tzmb2c.web.pojo.YourFavouritesDetailPojo;
import com.tzmb2c.web.service.YourFavouritesDetailService;

/**
 * YourFavouritesDetail Service层
 */
public class YourFavouritesDetailServiceImpl implements YourFavouritesDetailService {

  @Autowired
  private YourFavouritesDetailDao yourFavouritesDetaildao;

  @Override
  public int add(YourFavouritesDetailPojo yourFavouritesDetail) {
    if (null == yourFavouritesDetail) {
      return 0;
    }
    int rows = yourFavouritesDetaildao.add(yourFavouritesDetail);
    return rows;
  }

  @Override
  public int update(YourFavouritesDetailPojo yourFavouritesDetail) {
    if (null == yourFavouritesDetail) {
      return 0;
    }
    int rows = yourFavouritesDetaildao.update(yourFavouritesDetail);
    return rows;
  }

  @Override
  public int delete(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = yourFavouritesDetaildao.delete(id);
    return rows;
  }

  @Override
  public YourFavouritesDetailPojo getById(Long id) {
    if (null == id) {
      return null;
    }
    YourFavouritesDetailPojo yourFavouritesDetail = yourFavouritesDetaildao.getById(id);
    return yourFavouritesDetail;
  }

  @Override
  public Integer countBy(Map<String, Object> params) {
    Integer rows = yourFavouritesDetaildao.countBy(params);
    return rows;
  }

  @Override
  public List<YourFavouritesDetailPojo> listPage(Map<String, Object> params) {
    List<YourFavouritesDetailPojo> lists = yourFavouritesDetaildao.listPage(params);
    return lists;
  }

  @Override
  public int getUserCirclePostFavouritesCount(Map<String, Object> params) {
    // TODO Auto-generated method stub
    return yourFavouritesDetaildao.getUserCirclePostFavouritesCount(params);
  }

  @Override
  public List<UserCirclePostPojo> getUserCirclePostFavouritesList(Map<String, Object> params) {
    // TODO Auto-generated method stub
    return yourFavouritesDetaildao.getUserCirclePostFavouritesList(params);
  }

  @Override
  public int getProductFavouritesCount(Map<String, Object> params) {
    // TODO Auto-generated method stub
    return yourFavouritesDetaildao.getProductFavouritesCount(params);
  }

  @Override
  public List<ProductPojo> getProductFavouritesList(Map<String, Object> params) {
    // TODO Auto-generated method stub
    return yourFavouritesDetaildao.getProductFavouritesList(params);
  }

  @Override
  public List<ProductPojo> getProductTypeFavouritesList(Map<String, Object> params) {
    // TODO Auto-generated method stub
    return yourFavouritesDetaildao.getProductTypeFavouritesList(params);
  }

  @Override
  public int getProductTypeFavouritesCount(Map<String, Object> params) {
    // TODO Auto-generated method stub
    return yourFavouritesDetaildao.getProductTypeFavouritesCount(params);
  }
}
