package com.tzmb2c.web.service;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserMakerBrandPojo;

public interface UserMakerBrandService {

  public int addUserMakerBrand(UserMakerBrandPojo userMakerBrand);

  public int updateUserMakerBrand(UserMakerBrandPojo userMakerBrand);

  public int deleteUserMakerBrandById(Long id);

  public UserMakerBrandPojo getUserMakerBrandById(Long id);

  public Integer userMakerBrandCount(Map<String, Object> params);

  public List<UserMakerBrandPojo> userMakerBrandList(Map<String, Object> params);

  void checkUserMakerBrand(Long id);

  void uncheckUserMakerBrand(Long id);

  void changeTypeUserMakerBrand1(Long id);

  void changeTypeUserMakerBrand2(Long id);
  
  UserMakerBrandPojo findMakerBrandByParams(Map<String, Object> params);
}
