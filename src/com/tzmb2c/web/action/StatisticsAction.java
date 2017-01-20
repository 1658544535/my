package com.tzmb2c.web.action;

import java.util.HashMap;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.service.GrouponActivityRecordService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.SysLoginService;

public class StatisticsAction extends SuperAction {
  @Autowired
  private OrderService orderService;
  @Autowired
  private SysLoginService sysloginService;
  @Autowired
  private GrouponActivityRecordService grouponActivityRecordService;
  private String data;
  private String beginTime;
  private String endTime;


  public String getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(String beginTime) {
    this.beginTime = beginTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String orderCnt() throws Exception {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("beginTime", beginTime);
    params.put("endTime", endTime);
    int count = orderService.orderCnt(params);
    data = "" + count;
    return SUCCESS;
  }

  public String totalSale() throws Exception {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("beginTime", beginTime);
    params.put("endTime", endTime);
    data = orderService.totalSale(params);
    data = StringUtils.isEmpty(data) ? "0.00" : data;
    return SUCCESS;
  }

  public String userCnt() throws Exception {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("beginTime", beginTime);
    params.put("endTime", endTime);
    data = "" + sysloginService.userCnt(params);
    return SUCCESS;
  }

  public String openGroupCnt() throws Exception {
    Map<String, Object> params = new HashMap<String, Object>();
    if (beginTime != null && endTime != null) {
      params.put("beginTimeStr", beginTime);
      params.put("endTimeStr", endTime);
    }
    params.put("home", 1);
    data = "" + grouponActivityRecordService.countBy(params);
    return SUCCESS;
  }

  public String openSuccessGroupCnt() throws Exception {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("status", 1);
    params.put("home", 1);
    data = "" + grouponActivityRecordService.countBy(params);
    return SUCCESS;
  }

  public String oneOrderCnt() throws Exception {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("beginTime", beginTime);
    params.put("endTime", endTime);
    params.put("isOne", 1);
    int count = orderService.orderCnt(params);
    data = "" + count;
    return SUCCESS;
  }

  public String oneTotalSale() throws Exception {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("beginTime", beginTime);
    params.put("endTime", endTime);
    params.put("isOne", 1);
    data = orderService.totalSale(params);
    data = StringUtils.isEmpty(data) ? "0.00" : data;
    return SUCCESS;
  }

}
