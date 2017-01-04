package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.InfoPojo;

public interface InfoMapper {

  // ------后台调用方法------//
  public List<InfoPojo> infoAllList(Map<String, Object> map);

  public int infoAllCount(Map<String, Object> map);

  public void addInfo(InfoPojo infoPojo) throws SQLException;

  public void delInfo(InfoPojo infoPojo) throws SQLException;

  public void delAllInfoById(String id) throws SQLException;

  public InfoPojo findInfoById(Long id);

  public void updateInfo(InfoPojo infoPojo);

  public void checkAllInfoById(String id) throws SQLException;

  public void verifyInfo(InfoPojo infoPojo) throws SQLException;

  public void uncheckAllInfoById(String id) throws SQLException;

  public void uncheckinfo(InfoPojo infoPojo) throws SQLException;

  // ------前端调用方法------//
  public List<InfoPojo> infoPageList(Map<String, Object> map) throws SQLException;

  public List<InfoPojo> getRandomList() throws SQLException;

  public int infoTypeCount(Map<String, Object> map);

}
