package com.tzmb2c.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SysAreaDao;
import com.tzmb2c.web.mapper.SysAreaMapper;
import com.tzmb2c.web.pojo.SysAreaPojo;

/**
 * 
 * @author EricChen
 * 
 */
public class SysAreaDaoImpl implements SysAreaDao {

  @Autowired
  private SysAreaMapper sysAreaMapper;

  @Override
  public List<SysAreaPojo> getSysAreaByPid(SysAreaPojo sysAreaPojo) {
    return sysAreaMapper.getSysAreaByPid(sysAreaPojo);
  }

  @Override
  public SysAreaPojo getNameById(Integer id) {
    return sysAreaMapper.getNameById(id);
  }
}
