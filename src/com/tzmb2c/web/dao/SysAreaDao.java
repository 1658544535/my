package com.tzmb2c.web.dao;

import java.util.List;

import com.tzmb2c.web.pojo.SysAreaPojo;

/**
 * 
 * @author EricChen
 * 
 */
public interface SysAreaDao {

  public List<SysAreaPojo> getSysAreaByPid(SysAreaPojo sysAreaPojo);

  public SysAreaPojo getNameById(Integer id);

}
