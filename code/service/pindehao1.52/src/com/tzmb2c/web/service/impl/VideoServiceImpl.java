package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.VideoDao;
import com.tzmb2c.web.pojo.VideoPojo;
import com.tzmb2c.web.service.VideoService;

public class VideoServiceImpl implements VideoService {
  @Autowired
  private VideoDao videoDao;

  @Override
  public List<VideoPojo> findVideoList(Map<String, Object> map)
      throws SQLException {
    return videoDao.findVideoList(map);
  }

  @Override
  public int findVideoCount(Map<String, Object> map) throws SQLException {
    return videoDao.findVideoCount(map);
  }

  @Override
  public VideoPojo findVideoById(Long id) throws SQLException {
    return videoDao.findVideoById(id);
  }

  @Override
  public Long insertVideo(VideoPojo video) throws SQLException {
    return videoDao.insertVideo(video);
  }

  @Override
  public void delVideo(Long id) throws SQLException {
    videoDao.delVideo(id);
  }

  @Override
  public void updateVideo(VideoPojo video) throws SQLException {
    videoDao.updateVideo(video);
  }

  @Override
  public void checkVideo(Long id) throws SQLException {
    videoDao.checkVideo(id);
  }

  @Override
  public void uncheckVideo(Long id) throws SQLException {
    videoDao.uncheckVideo(id);
  }

  @Override
  public void editVideo(Long id) throws SQLException {
    videoDao.editVideo(id);
    
  }

}
