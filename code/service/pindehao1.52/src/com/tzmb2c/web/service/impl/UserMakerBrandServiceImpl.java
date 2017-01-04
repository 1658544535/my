package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tzmb2c.web.dao.UserMakerBrandDao;
import com.tzmb2c.web.pojo.UserMakerBrandPojo;
import com.tzmb2c.web.service.UserMakerBrandService;

// @Service
public class UserMakerBrandServiceImpl implements UserMakerBrandService {

  @Autowired
  private UserMakerBrandDao userMakerBrandDao;

  @Override
  @Transactional
  public int addUserMakerBrand(UserMakerBrandPojo userMakerBrand) {
    if (null == userMakerBrand) {
      return 0;
    }
    int rows = userMakerBrandDao.addUserMakerBrand(userMakerBrand);
    return rows;
  }

  @Override
  @Transactional
  public int updateUserMakerBrand(UserMakerBrandPojo userMakerBrand) {
    if (null == userMakerBrand) {
      return 0;
    }
    int rows = userMakerBrandDao.updateUserMakerBrand(userMakerBrand);
    return rows;
  }

  @Override
  @Transactional
  public int deleteUserMakerBrandById(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = userMakerBrandDao.deleteUserMakerBrandById(id);
    return rows;
  }


  @Override
  public UserMakerBrandPojo getUserMakerBrandById(Long id) {
    if (null == id) {
      return null;
    }
    UserMakerBrandPojo userMakerBrand = userMakerBrandDao.getUserMakerBrandById(id);
    //
    return userMakerBrand;
  }

  @Override
  public Integer userMakerBrandCount(Map<String, Object> params) {
    Integer rows = userMakerBrandDao.userMakerBrandCount(params);
    return rows;
  }

  @Override
  public List<UserMakerBrandPojo> userMakerBrandList(Map<String, Object> params) {
    List<UserMakerBrandPojo> lists = userMakerBrandDao.userMakerBrandList(params);

    return lists;
  }

  @Override
  public void checkUserMakerBrand(Long id) {
    userMakerBrandDao.checkUserMakerBrand(id);
  }

  @Override
  public void uncheckUserMakerBrand(Long id) {
    userMakerBrandDao.uncheckUserMakerBrand(id);
  }

  @Override
  public void changeTypeUserMakerBrand1(Long id) {
    userMakerBrandDao.changeTypeUserMakerBrand1(id);
  }

  @Override
  public void changeTypeUserMakerBrand2(Long id) {
    userMakerBrandDao.changeTypeUserMakerBrand2(id);
  }

@Override
public UserMakerBrandPojo findMakerBrandByParams(Map<String, Object> params) {
	return userMakerBrandDao.findMakerBrandByParams(params);
}
}
