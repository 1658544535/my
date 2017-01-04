package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.PopupDao;
import com.tzmb2c.web.pojo.PopupPojo;
import com.tzmb2c.web.service.PopupService;

public class PopupServiceImpl implements PopupService {
  @Autowired
  private PopupDao popupDao;

  @Override
  public List<PopupPojo> findPopupList(Map<String, Object> map) throws SQLException {
    return popupDao.findPopupList(map);
  }

  @Override
  public int findPopupCount(Map<String, Object> map) throws SQLException {
    return popupDao.findPopupCount(map);
  }

  @Override
  public void insertPopup(PopupPojo popupPojo) throws SQLException {
    popupDao.insertPopup(popupPojo);
  }

  @Override
  public void delPopup(PopupPojo popupPojo) throws SQLException {
    popupDao.delPopup(popupPojo);
  }

  @Override
  public PopupPojo findPopupById(Long id) throws SQLException {
    return popupDao.findPopupById(id);
  }

  @Override
  public void updatePopup(PopupPojo popupPojo) throws SQLException {
    popupDao.updatePopup(popupPojo);
  }

  @Override
  public void checkPopup(Long id) throws SQLException {
    popupDao.checkPopup(id);
  }

  @Override
  public void uncheckPopup(Long id) throws SQLException {
    popupDao.uncheckPopup(id);
  }
}
