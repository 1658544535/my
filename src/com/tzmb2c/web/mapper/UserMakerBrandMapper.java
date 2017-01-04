package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserMakerBrandPojo;

public interface UserMakerBrandMapper {

  UserMakerBrandPojo getUserMakerBrandById(Long id);

  int userMakerBrandCount(Map<String, Object> params);

  List<UserMakerBrandPojo> userMakerBrandList(Map<String, Object> params);

  int addUserMakerBrand(UserMakerBrandPojo userMakerBrand);

  int updateUserMakerBrand(UserMakerBrandPojo userMakerBrand);

  int deleteUserMakerBrandById(Long id);

  void checkUserMakerBrand(Long id);

  void uncheckUserMakerBrand(Long id);

  void changeTypeUserMakerBrand1(Long id);

  void changeTypeUserMakerBrand2(Long id);
  
  UserMakerBrandPojo findMakerBrandByParams(Map<String, Object> params);
}