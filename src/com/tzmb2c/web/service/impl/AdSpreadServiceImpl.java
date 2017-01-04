package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.AdSpreadDao;
import com.tzmb2c.web.pojo.AdSpreadPojo;
import com.tzmb2c.web.service.AdSpreadService;

public class AdSpreadServiceImpl implements AdSpreadService {
  @Autowired
  AdSpreadDao adSpreadDao;

  @Override
  public List<AdSpreadPojo> findAdSpreadList(Map<String, Object> param) throws SQLException {

    return adSpreadDao.findAdSpreadList(param);
  }

  @Override
  public int findAdSpreadCount(Map<String, Object> param) throws SQLException {

    return adSpreadDao.findAdSpreadCount(param);
  }

  @Override
  public AdSpreadPojo findAdSpreadById(Long id) throws SQLException {

    return adSpreadDao.findAdSpreadById(id);
  }

  @Override
  public AdSpreadPojo findAdSpreadByImei(Map<String, Object> param) throws SQLException {
    return adSpreadDao.findAdSpreadByImei(param);
  }

  @Override
  public int insertAdSpread(AdSpreadPojo ad) throws SQLException {
    return adSpreadDao.insertAdSpread(ad);
  }

  @Override
  public void delAdSpreadById(Long id) throws SQLException {
    adSpreadDao.delAdSpreadById(id);
  }

  @Override
  public void delAdSpreadByImei(String imei) throws SQLException {
    adSpreadDao.delAdSpreadByImei(imei);
  }

  @Override
  public int updateAdSpread(AdSpreadPojo ad) throws SQLException {
    return adSpreadDao.updateAdSpread(ad);
  }

}
