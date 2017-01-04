package com.tzmb2c.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SysAreaDao;
import com.tzmb2c.web.pojo.SysAreaPojo;
import com.tzmb2c.web.service.SysAreaService;

public class SysAreaServiceImpl implements SysAreaService {
  @Autowired
  private SysAreaDao sysAreaDao;

  @Override
  public List<SysAreaPojo> getSysAreaByPid(SysAreaPojo sysAreaPojo) {
    return sysAreaDao.getSysAreaByPid(sysAreaPojo);
  }

  @Override
  public SysAreaPojo getNameById(Integer id) {
    return sysAreaDao.getNameById(id);
  }
}
