package com.tzmb2c.web.service;

import java.util.List;

import com.tzmb2c.web.pojo.SysAreaPojo;

public interface SysAreaService {

  public List<SysAreaPojo> getSysAreaByPid(SysAreaPojo sysAreaPojo);

  public SysAreaPojo getNameById(Integer id);

}
