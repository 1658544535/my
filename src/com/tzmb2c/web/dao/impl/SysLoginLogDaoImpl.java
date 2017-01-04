package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SysLoginLogDao;
import com.tzmb2c.web.mapper.SysLoginLogMapper;
import com.tzmb2c.web.pojo.SysLoginLogPojo;

public class SysLoginLogDaoImpl implements SysLoginLogDao {

  @Autowired
  private SysLoginLogMapper sysLoginLogMapper;

  @Override
  public int sysLoginLogAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return sysLoginLogMapper.sysLoginLogAllCount(map);
  }

  @Override
  public List<SysLoginLogPojo> sysLoginLogAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return sysLoginLogMapper.sysLoginLogAllList(map);
  }

  @Override
  public List<SysLoginLogPojo> sysLoginLogAllListHot(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return sysLoginLogMapper.sysLoginLogAllListHot(map);
  }
}
