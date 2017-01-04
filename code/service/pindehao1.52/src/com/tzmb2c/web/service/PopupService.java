package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.PopupPojo;

public interface PopupService {
  List<PopupPojo> findPopupList(Map<String, Object> map) throws SQLException;

  int findPopupCount(Map<String, Object> map) throws SQLException;

  void insertPopup(PopupPojo popupPojo) throws SQLException;

  void delPopup(PopupPojo popupPojo) throws SQLException;

  PopupPojo findPopupById(Long id) throws SQLException;

  void updatePopup(PopupPojo popupPojo) throws SQLException;

  void checkPopup(Long id) throws SQLException;

  void uncheckPopup(Long id) throws SQLException;
}
