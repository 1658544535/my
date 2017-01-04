package com.tzmb2c.web.service;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserDistributionInfoPojo;

public interface UserDistributionInfoService {

  public List<UserDistributionInfoPojo> userDistributionInfoAll(Map<String, Object> map)
      throws Exception;

  public void userDistributionInfoUpdate(UserDistributionInfoPojo userDistributionInfoPojo)
      throws Exception;

  public void userDistributionInfoCheckById(Long id) throws Exception;

  public List<SysLoginPojo> userDistributionInfoManage(Map<String, Object> map) throws Exception;


}
