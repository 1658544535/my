/*
 * 
 * 文 件 名: YourFavouritesDetailDaoImpl.java 创 建 人: admin 创建时间: 2016-08-28
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.YourFavouritesDetailDao;
import com.tzmb2c.web.mapper.YourFavouritesDetailMapper;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.UserCirclePostPojo;
import com.tzmb2c.web.pojo.YourFavouritesDetailPojo;

/**
 * YourFavouritesDetail Dao层
 */
public class YourFavouritesDetailDaoImpl implements YourFavouritesDetailDao {

  @Autowired
  private YourFavouritesDetailMapper yourFavouritesDetailMapper;

  @Override
  public int add(YourFavouritesDetailPojo yourFavouritesDetail) {
    if (null == yourFavouritesDetail) {
      return 0;
    }
    int rows = yourFavouritesDetailMapper.insert(yourFavouritesDetail);
    return rows;
  }

  @Override
  public int update(YourFavouritesDetailPojo yourFavouritesDetail) {
    if (null == yourFavouritesDetail) {
      return 0;
    }
    int rows = yourFavouritesDetailMapper.update(yourFavouritesDetail);
    return rows;
  }

  @Override
  public int delete(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = yourFavouritesDetailMapper.deleteById(id);
    return rows;
  }

  @Override
  public YourFavouritesDetailPojo getById(Long id) {
    if (null == id) {
      return null;
    }
    YourFavouritesDetailPojo yourFavouritesDetail = yourFavouritesDetailMapper.getById(id);
    return yourFavouritesDetail;
  }

  @Override
  public Integer countBy(Map<String, Object> params) {
    Integer rows = yourFavouritesDetailMapper.countBy(params);
    return rows;
  }

  @Override
  public List<YourFavouritesDetailPojo> listPage(Map<String, Object> params) {
    List<YourFavouritesDetailPojo> lists = yourFavouritesDetailMapper.listPage(params);
    return lists;
  }

  @Override
  public int getUserCirclePostFavouritesCount(Map<String, Object> params) {
    // TODO Auto-generated method stub
    return yourFavouritesDetailMapper.getUserCirclePostFavouritesCount(params);
  }

  @Override
  public List<UserCirclePostPojo> getUserCirclePostFavouritesList(Map<String, Object> params) {
    // TODO Auto-generated method stub
    return yourFavouritesDetailMapper.getUserCirclePostFavouritesList(params);
  }

  @Override
  public int getProductFavouritesCount(Map<String, Object> params) {
    // TODO Auto-generated method stub
    return yourFavouritesDetailMapper.getProductFavouritesCount(params);
  }

  @Override
  public List<ProductPojo> getProductFavouritesList(Map<String, Object> params) {
    // TODO Auto-generated method stub
    return yourFavouritesDetailMapper.getProductFavouritesList(params);
  }

  @Override
  public List<ProductPojo> getProductTypeFavouritesList(Map<String, Object> params) {
    // TODO Auto-generated method stub
    return yourFavouritesDetailMapper.getProductTypeFavouritesList(params);
  }

  @Override
  public int getProductTypeFavouritesCount(Map<String, Object> params) {
    // TODO Auto-generated method stub
    return yourFavouritesDetailMapper.getProductTypeFavouritesCount(params);
  }
}
