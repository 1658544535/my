package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.AdSpreadPojo;

public interface AdSpreadMapper {
  public List<AdSpreadPojo> findAdSpreadList(Map<String, Object> param) throws SQLException;

  public int findAdSpreadCount(Map<String, Object> param) throws SQLException;

  public AdSpreadPojo findAdSpreadById(Long id) throws SQLException;

  public AdSpreadPojo findAdSpreadByImei(Map<String, Object> param) throws SQLException;

  public int insertAdSpread(AdSpreadPojo ad) throws SQLException;

  public void delAdSpreadById(Long id) throws SQLException;

  public void delAdSpreadByImei(String imei) throws SQLException;

  public int updateAdSpread(AdSpreadPojo ad) throws SQLException;
}
