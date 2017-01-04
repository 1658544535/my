package com.tzmb2c.web.dao;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SysLoginLogPojo;

public interface SysLoginLogDao {

  int sysLoginLogAllCount(Map<String, Object> map);

  List<SysLoginLogPojo> sysLoginLogAllList(Map<String, Object> map);

  List<SysLoginLogPojo> sysLoginLogAllListHot(Map<String, Object> map);

}
