package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SysLoginLogPojo;

public interface SysLoginLogMapper {

  public int sysLoginLogAllCount(Map<String, Object> map);

  public List<SysLoginLogPojo> sysLoginLogAllList(Map<String, Object> map);

  public List<SysLoginLogPojo> sysLoginLogAllListHot(Map<String, Object> map);

}
