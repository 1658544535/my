package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.VideoPojo;

public interface VideoService {
  List<VideoPojo> findVideoList(Map<String, Object> map) throws SQLException;

  int findVideoCount(Map<String, Object> map) throws SQLException;

  VideoPojo findVideoById(Long id) throws SQLException;

  Long insertVideo(VideoPojo video) throws SQLException;

  void delVideo(Long id) throws SQLException;

  void updateVideo(VideoPojo video) throws SQLException;

  void checkVideo(Long id) throws SQLException;

  void uncheckVideo(Long id) throws SQLException;
  
  void editVideo(Long id) throws SQLException;

}
