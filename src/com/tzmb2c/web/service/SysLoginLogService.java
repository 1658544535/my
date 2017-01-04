package com.tzmb2c.web.service;

import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.SysLoginLogPojo;

public interface SysLoginLogService {


  public int sysLoginLogAllCount(SysLoginLogPojo sysLoginLogDaoPojo, String beganday, String endday);

  List<SysLoginLogPojo> sysLoginLogAllList(SysLoginLogPojo ticketRulePojo, Pager page,
      String beganday, String endday);

  public Object sysLoginLogAllListHot(SysLoginLogPojo sysLoginLog, Pager page, String os,
      String beganday, String endday);



}
