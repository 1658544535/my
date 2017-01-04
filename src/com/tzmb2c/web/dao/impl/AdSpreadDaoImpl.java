package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.AdSpreadDao;
import com.tzmb2c.web.mapper.AdSpreadMapper;
import com.tzmb2c.web.pojo.AdSpreadPojo;

public class AdSpreadDaoImpl implements AdSpreadDao {
  @Autowired
  AdSpreadMapper adSpreadMapper;

  @Override
  public List<AdSpreadPojo> findAdSpreadList(Map<String, Object> param) throws SQLException {

    return adSpreadMapper.findAdSpreadList(param);
  }

  @Override
  public int findAdSpreadCount(Map<String, Object> param) throws SQLException {

    return adSpreadMapper.findAdSpreadCount(param);
  }

  @Override
  public AdSpreadPojo findAdSpreadById(Long id) throws SQLException {

    return adSpreadMapper.findAdSpreadById(id);
  }

  @Override
  public AdSpreadPojo findAdSpreadByImei(Map<String, Object> param) throws SQLException {

    return adSpreadMapper.findAdSpreadByImei(param);
  }

  @Override
  public int insertAdSpread(AdSpreadPojo ad) throws SQLException {

    return adSpreadMapper.insertAdSpread(ad);
  }

  @Override
  public void delAdSpreadById(Long id) throws SQLException {
    adSpreadMapper.delAdSpreadById(id);
  }

  @Override
  public void delAdSpreadByImei(String imei) throws SQLException {
    adSpreadMapper.delAdSpreadByImei(imei);
  }

  @Override
  public int updateAdSpread(AdSpreadPojo ad) throws SQLException {
    return adSpreadMapper.updateAdSpread(ad);
  }

}
