package com.tzmb2c.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.SysLoginLogDao;
import com.tzmb2c.web.pojo.SysLoginLogPojo;
import com.tzmb2c.web.service.SysLoginLogService;

public class SysLoginLogServiceImpl implements SysLoginLogService {
  @Autowired
  private SysLoginLogDao sysLoginLogDao;

  public void setSysLoginLogDao(SysLoginLogDao sysLoginLogDao) {
    this.sysLoginLogDao = sysLoginLogDao;
  }

  @Override
  public int sysLoginLogAllCount(SysLoginLogPojo sysLoginLogDaoPojo, String beganday, String endday) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (sysLoginLogDaoPojo != null) {
      map.put("loginIp", sysLoginLogDaoPojo.getLoginIp());
    }
    if (beganday != null && !beganday.equals("")) {
      map.put("beganday", beganday);
    }
    if (endday != null && !endday.equals("")) {
      map.put("endday", endday);
    }

    return sysLoginLogDao.sysLoginLogAllCount(map);
  }

  @Override
  public List<SysLoginLogPojo> sysLoginLogAllList(SysLoginLogPojo ticketRulePojo, Pager page,
      String beganday, String endday) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (ticketRulePojo != null) {
      map.put("loginIp", ticketRulePojo.getLoginIp());
    }
    if (beganday != null && !beganday.equals("")) {
      map.put("beganday", beganday);
    }
    if (endday != null && !endday.equals("")) {
      map.put("endday", endday);
    }

    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<SysLoginLogPojo> list = sysLoginLogDao.sysLoginLogAllList(map);

    return list;
  }


  // /////////////////////////////////////分割线/////////////////////////////////////////
  //

  @Override
  public List<SysLoginLogPojo> sysLoginLogAllListHot(SysLoginLogPojo ticketRulePojo, Pager page,
      String os, String beganday, String endday) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (ticketRulePojo != null) {
      map.put("userId", ticketRulePojo.getUserId());
    }
    if (beganday != null && !beganday.equals("")) {
      map.put("beganday", beganday);
    }
    if (endday != null && !endday.equals("")) {
      map.put("endday", endday);
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    if (os != null & !os.equals("")) {
      map.put("os", Integer.parseInt(os));
    }
    List<SysLoginLogPojo> list = sysLoginLogDao.sysLoginLogAllListHot(map);

    return list;
  }
}
