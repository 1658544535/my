package com.tzmb2c.web.action;

import java.util.List;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.pojo.SysAreaPojo;
import com.tzmb2c.web.service.SysAreaService;

/**
 * 
 * @author EricChen
 * 
 */
public class SysAreaAction extends SuperAction {
  private static final long serialVersionUID = 1L;

  @Autowired
  private SysAreaService sysAreaService;

  private SysAreaPojo sysAreaPojo;
  private String result;
  private String[] tids;
  private String jsonStr;

  public SysAreaPojo getSysAreaPojo() {
    return sysAreaPojo;
  }

  public void setSysAreaPojo(SysAreaPojo sysAreaPojo) {
    this.sysAreaPojo = sysAreaPojo;
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

  public String getJsonStr() {
    return jsonStr;
  }

  public void setJsonStr(String jsonStr) {
    this.jsonStr = jsonStr;
  }

  public String getSysAreaByPid() {
    List<SysAreaPojo> sysAreaList = sysAreaService.getSysAreaByPid(sysAreaPojo);
    JSONArray json = JSONArray.fromObject(sysAreaList);
    jsonStr = json.toString();
    return SUCCESS;
  }

}
