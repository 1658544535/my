package com.tzmb2c.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.SysLoginLogDao;
import com.tzmb2c.web.pojo.SysLoginLogPojo;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginLogService;
import com.tzmb2c.web.service.SysLoginService;

public class SysLoginLogAction extends SuperAction {

  @Autowired
  private SysLoginLogService sysLoginLogService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SysLoginLogDao sysLoginLogDao;
  @Autowired
  private SysLoginService sysLoginService;

  private SysLoginLogPojo sysLoginLog;
  private String result;
  private String[] tids;
  private String os;
  private String beganday = null, endday = null;
  private Long nums;
  private Long activeUser;

  public Long getActiveUser() {
    return activeUser;
  }

  public void setActiveUser(Long activeUser) {
    this.activeUser = activeUser;
  }

  public Long getNums() {
    return nums;
  }

  public void setNums(Long nums) {
    this.nums = nums;
  }

  public String getBeganday() {
    return beganday;
  }

  public void setBeganday(String beganday) {
    this.beganday = beganday;
  }

  public String getEndday() {
    return endday;
  }

  public void setEndday(String endday) {
    this.endday = endday;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }

  public SysLoginLogPojo getSysLoginLog() {
    return sysLoginLog;
  }

  public void setSysLoginLog(SysLoginLogPojo sysLoginLog) {
    this.sysLoginLog = sysLoginLog;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getSysLoginLogCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(sysLoginLogService.sysLoginLogAllCount(sysLoginLog, beganday, endday));
    return SUCCESS;
  }

  public String sysLoginLogAllList() {
    JSONArray json =
        JSONArray.fromObject(sysLoginLogService.sysLoginLogAllList(sysLoginLog, page, beganday,
            endday));
    page.setResult(json.toString());

    return SUCCESS;
  }



  // //////////////////////////////////////////////分割线///////////////////////////////////////
  public String getSysLoginLogCountHot() throws Exception {
    if (page == null) {
      page = new Pager();
    }

    Map<String, Object> map = new HashMap<String, Object>();
    if (sysLoginLog != null) {
      map.put("userId", sysLoginLog.getUserId());
    }
    if (beganday != null && !beganday.equals("")) {
      map.put("beganday", beganday);
    }
    if (endday != null && !endday.equals("")) {
      map.put("endday", endday);
    }
    if (os != null && !os.equals("")) {
      map.put("os", Integer.parseInt(os));
    }
    List<SysLoginLogPojo> list = sysLoginLogDao.sysLoginLogAllListHot(map);
    page.setRowCount(list.size());
    return SUCCESS;
  }

  public String sysLoginLogAllListHot() {
    JSONArray json =
        JSONArray.fromObject(sysLoginLogService.sysLoginLogAllListHot(sysLoginLog, page, os,
            beganday, endday));
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String sysLoginLogAllActiveUser() {
    Map<String, Object> map = new HashMap<String, Object>();
    if (sysLoginLog != null) {
      map.put("userId", sysLoginLog.getUserId());
    }
    if (beganday != null && !beganday.equals("")) {
      map.put("beganday", beganday);
    }
    if (endday != null && !endday.equals("")) {
      map.put("endday", endday);
    }
    if (os != null && !os.equals("")) {
      map.put("os", Integer.parseInt(os));
    }
    map.put("nums", 2);
    result = sysLoginLogDao.sysLoginLogAllListHot(map).size() + "";
    return SUCCESS;
  }
}
