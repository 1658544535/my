package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SceneDao;
import com.tzmb2c.web.mapper.SceneMapper;
import com.tzmb2c.web.pojo.ScenePojo;

public class SceneDaoImpl implements SceneDao {
  @Autowired
  private SceneMapper sceneMapper;

  @Override
  public List<ScenePojo> findSceneList(Map<String, Object> map) throws SQLException {
    return sceneMapper.findSceneList(map);
  }

  @Override
  public int findSceneCount(Map<String, Object> map) throws SQLException {
    return sceneMapper.findSceneCount(map);
  }

  @Override
  public ScenePojo findSceneById(Long id) throws SQLException {
    return sceneMapper.findSceneById(id);
  }

  @Override
  public void insertScene(ScenePojo scene) throws SQLException {
    sceneMapper.insertScene(scene);
  }

  @Override
  public void delSceneById(Long id) throws SQLException {
    sceneMapper.delSceneById(id);
  }

  @Override
  public void checkSceneById(Long id) throws SQLException {
    sceneMapper.checkSceneById(id);
  }

  @Override
  public void uncheckSceneById(Long id) throws SQLException {
    sceneMapper.uncheckSceneById(id);
  }

  @Override
  public void updateSceneById(ScenePojo scene) throws SQLException {
    sceneMapper.updateSceneById(scene);
  }

  @Override
  public long lastInsertId() throws SQLException {
    // TODO Auto-generated method stub
    return sceneMapper.lastInsertId();
  }

}
