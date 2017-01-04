package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tzmb2c.web.dao.UserMakerBrandDao;
import com.tzmb2c.web.mapper.UserMakerBrandMapper;
import com.tzmb2c.web.pojo.UserMakerBrandPojo;

// @Service
public class UserMakerBrandDaoImpl implements UserMakerBrandDao {

  @Autowired
  private UserMakerBrandMapper userMakerBrandMapper;

  @Override
  @Transactional
  public int addUserMakerBrand(UserMakerBrandPojo userMakerBrand) {
    if (null == userMakerBrand) {
      return 0;
    }
    int rows = userMakerBrandMapper.addUserMakerBrand(userMakerBrand);
    return rows;
  }

  @Override
  @Transactional
  public int updateUserMakerBrand(UserMakerBrandPojo userMakerBrand) {
    if (null == userMakerBrand) {
      return 0;
    }
    int rows = userMakerBrandMapper.updateUserMakerBrand(userMakerBrand);
    return rows;
  }

  @Override
  @Transactional
  public int deleteUserMakerBrandById(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = userMakerBrandMapper.deleteUserMakerBrandById(id);
    return rows;
  }


  @Override
  public UserMakerBrandPojo getUserMakerBrandById(Long id) {
    if (null == id) {
      return null;
    }
    UserMakerBrandPojo userMakerBrand = userMakerBrandMapper.getUserMakerBrandById(id);
    //
    return userMakerBrand;
  }

  @Override
  public Integer userMakerBrandCount(Map<String, Object> params) {
    Integer rows = userMakerBrandMapper.userMakerBrandCount(params);
    return rows;
  }

  @Override
  public List<UserMakerBrandPojo> userMakerBrandList(Map<String, Object> params) {
    List<UserMakerBrandPojo> lists = userMakerBrandMapper.userMakerBrandList(params);

    return lists;
  }

  @Override
  public void checkUserMakerBrand(Long id) {
    userMakerBrandMapper.checkUserMakerBrand(id);
  }

  @Override
  public void uncheckUserMakerBrand(Long id) {
    userMakerBrandMapper.uncheckUserMakerBrand(id);
  }

  @Override
  public void changeTypeUserMakerBrand1(Long id) {
    userMakerBrandMapper.changeTypeUserMakerBrand1(id);
  }

  @Override
  public void changeTypeUserMakerBrand2(Long id) {
    userMakerBrandMapper.changeTypeUserMakerBrand2(id);
  }

@Override
public UserMakerBrandPojo findMakerBrandByParams(Map<String, Object> params) {
	return userMakerBrandMapper.findMakerBrandByParams(params);
}
}
