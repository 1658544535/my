package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserDistributionInfoDao;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserDistributionInfoPojo;
import com.tzmb2c.web.service.UserDistributionInfoService;

public class UserDistributionInfoServiceImpl implements UserDistributionInfoService {

  @Autowired
  private UserDistributionInfoDao userDistributionInfoDao;

  @Override
  public List<UserDistributionInfoPojo> userDistributionInfoAll(Map<String, Object> map)
      throws Exception {
    // TODO Auto-generated method stub
    return userDistributionInfoDao.userDistributionInfoAll(map);
  }

  @Override
  public void userDistributionInfoUpdate(UserDistributionInfoPojo userDistributionInfoPojo)
      throws Exception {
    // TODO Auto-generated method stub
    userDistributionInfoDao.userDistributionInfoUpdate(userDistributionInfoPojo);
  }

  @Override
  public void userDistributionInfoCheckById(Long id) throws Exception {
    // TODO Auto-generated method stub
    userDistributionInfoDao.userDistributionInfoCheckById(id);
  }

  @Override
  public List<SysLoginPojo> userDistributionInfoManage(Map<String, Object> map) throws Exception {
    // TODO Auto-generated method stub
    return userDistributionInfoDao.userDistributionInfoManage(map);
  }


}
