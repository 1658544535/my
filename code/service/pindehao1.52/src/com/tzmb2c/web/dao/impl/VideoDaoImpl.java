package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.VideoDao;
import com.tzmb2c.web.mapper.VideoMapper;
import com.tzmb2c.web.pojo.VideoPojo;

public class VideoDaoImpl implements VideoDao {
  @Autowired
  private VideoMapper videoMapper;

  @Override
  public List<VideoPojo> findVideoList(Map<String, Object> map)
      throws SQLException {
    return videoMapper.findVideoList(map);
  }

  @Override
  public int findVideoCount(Map<String, Object> map) throws SQLException {
    return videoMapper.findVideoCount(map);
  }

  @Override
  public VideoPojo findVideoById(Long id) throws SQLException {
    return videoMapper.findVideoById(id);
  }

  @Override
  public Long insertVideo(VideoPojo video) throws SQLException {
    return videoMapper.insertVideo(video);
  }

  @Override
  public void delVideo(Long id) throws SQLException {
    videoMapper.delVideo(id);
  }

  @Override
  public void updateVideo(VideoPojo video) throws SQLException {
    videoMapper.updateVideo(video);
  }

  @Override
  public void checkVideo(Long id) throws SQLException {
    videoMapper.checkVideo(id);
  }

  @Override
  public void uncheckVideo(Long id) throws SQLException {
    videoMapper.uncheckVideo(id);
  }

  @Override
  public void editVideo(Long id) throws SQLException {
    videoMapper.editVideo(id);
    
  }

}
