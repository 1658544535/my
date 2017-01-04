package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SceneDao;
import com.tzmb2c.web.pojo.ScenePojo;
import com.tzmb2c.web.service.SceneService;

public class SceneServiceImpl implements SceneService {
  @Autowired
  private SceneDao sceneDao;

  @Override
  public List<ScenePojo> findSceneList(Map<String, Object> map) throws SQLException {
    return sceneDao.findSceneList(map);
  }

  @Override
  public int findSceneCount(Map<String, Object> map) throws SQLException {
    return sceneDao.findSceneCount(map);
  }

  @Override
  public ScenePojo findSceneById(Long id) throws SQLException {
    return sceneDao.findSceneById(id);
  }

  @Override
  public void insertScene(ScenePojo scene) throws SQLException {
    sceneDao.insertScene(scene);
  }

  @Override
  public void delSceneById(Long id) throws SQLException {
    sceneDao.delSceneById(id);
  }

  @Override
  public void checkSceneById(Long id) throws SQLException {
    sceneDao.checkSceneById(id);
  }

  @Override
  public void uncheckSceneById(Long id) throws SQLException {
    sceneDao.uncheckSceneById(id);
  }

  @Override
  public void updateSceneById(ScenePojo scene) throws SQLException {
    sceneDao.updateSceneById(scene);
  }

  @Override
  public long lastInsertId() throws SQLException {
    // TODO Auto-generated method stub
    return sceneDao.lastInsertId();
  }


}
