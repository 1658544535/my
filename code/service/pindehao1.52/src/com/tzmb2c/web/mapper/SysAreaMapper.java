package com.tzmb2c.web.mapper;

import java.util.List;

import com.tzmb2c.web.pojo.SysAreaPojo;

/**
 * 
 * @author EricChen
 * 
 */
public interface SysAreaMapper {

  public List<SysAreaPojo> getSysAreaByPid(SysAreaPojo sysAreaPojo);

  public SysAreaPojo getNameById(Integer id);

}
