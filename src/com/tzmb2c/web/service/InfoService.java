package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.InfoPojo;

public interface InfoService {

  // ------后台方法------//
  public List<InfoPojo> infoAllList(InfoPojo infoPojo, Pager page, Integer status);

  public int infoAllCount(InfoPojo infoPojo);

  public InfoPojo findInfoById(Long id) throws SQLException;

  public void addInfo(InfoPojo infoPojo) throws SQLException;

  public void delInfo(InfoPojo infoPojo) throws SQLException;

  public void delAllInfoById(String[] tids);

  public void updateInfo(InfoPojo infoPojo) throws SQLException;

  public void verifyInfo(InfoPojo infoPojo) throws SQLException;

  public void checkAllInfoById(String[] tids);

  public void uncheckAllInfoById(String[] tids) throws SQLException;

  public void uncheckinfo(InfoPojo infoPojo) throws SQLException;


  // ------前端方法------//
  // public List<InfoPojo> infoPageList(InfoPojo infoPojo, Pager page);
  public List<InfoPojo> infoPageList(Map<String, Object> map) throws SQLException;

  public List<InfoPojo> getRandomList() throws SQLException;

  int infoTypeCount(Map<String, Object> map) throws SQLException;

}
