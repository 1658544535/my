package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.PopupDao;
import com.tzmb2c.web.mapper.PopupMapper;
import com.tzmb2c.web.pojo.PopupPojo;

public class PopupDaoImpl implements PopupDao {
  @Autowired
  private PopupMapper popupMapper;

  @Override
  public List<PopupPojo> findPopupList(Map<String, Object> map) throws SQLException {
    return popupMapper.findPopupList(map);
  }

  @Override
  public int findPopupCount(Map<String, Object> map) throws SQLException {
    return popupMapper.findPopupCount(map);
  }

  @Override
  public void insertPopup(PopupPojo popupPojo) throws SQLException {
    popupMapper.insertPopup(popupPojo);
  }

  @Override
  public void delPopup(PopupPojo popupPojo) throws SQLException {
    popupMapper.delPopup(popupPojo);
  }

  @Override
  public PopupPojo findPopupById(Long id) throws SQLException {
    return popupMapper.findPopupById(id);
  }

  @Override
  public void updatePopup(PopupPojo popupPojo) throws SQLException {
    popupMapper.updatePopup(popupPojo);
  }

  @Override
  public void checkPopup(Long id) throws SQLException {
    popupMapper.checkPopup(id);
  }

  @Override
  public void uncheckPopup(Long id) throws SQLException {
    popupMapper.uncheckPopup(id);
  }
}
