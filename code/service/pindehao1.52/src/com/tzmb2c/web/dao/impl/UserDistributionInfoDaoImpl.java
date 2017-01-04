package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserDistributionInfoDao;
import com.tzmb2c.web.mapper.UserDistributionInfoMapper;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserDistributionInfoPojo;

public class UserDistributionInfoDaoImpl implements UserDistributionInfoDao {

  @Autowired
  private UserDistributionInfoMapper userDistributionInfoMapper;

  @Override
  public List<UserDistributionInfoPojo> userDistributionInfoAll(Map<String, Object> map)
      throws Exception {
    // TODO Auto-generated method stub
    return userDistributionInfoMapper.userDistributionInfoAll(map);
  }

  @Override
  public void userDistributionInfoUpdate(UserDistributionInfoPojo userDistributionInfoPojo)
      throws Exception {
    // TODO Auto-generated method stub
    userDistributionInfoMapper.userDistributionInfoUpdate(userDistributionInfoPojo);
  }

  @Override
  public void userDistributionInfoCheckById(Long id) throws Exception {
    // TODO Auto-generated method stub
    userDistributionInfoMapper.userDistributionInfoCheckById(id);
  }

  @Override
  public List<SysLoginPojo> userDistributionInfoManage(Map<String, Object> map) throws Exception {
    // TODO Auto-generated method stub
    return userDistributionInfoMapper.userDistributionInfoManage(map);
  }


}
