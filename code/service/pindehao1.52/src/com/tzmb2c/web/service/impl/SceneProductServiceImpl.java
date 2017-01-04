package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SceneProductDao;
import com.tzmb2c.web.pojo.SceneProductPojo;
import com.tzmb2c.web.service.SceneProductService;

public class SceneProductServiceImpl implements SceneProductService {
  @Autowired
  private SceneProductDao sceneProductDao;

  @Override
  public List<SceneProductPojo> findSceneProductList(Map<String, Object> map) throws SQLException {
    return sceneProductDao.findSceneProductList(map);
  }

  @Override
  public int findSceneProductCount(Map<String, Object> map) throws SQLException {
    return sceneProductDao.findSceneProductCount(map);
  }

  @Override
  public SceneProductPojo findSceneProductById(Long id) throws SQLException {
    return sceneProductDao.findSceneProductById(id);
  }

  @Override
  public void insertSceneProduct(SceneProductPojo sceneProduct) throws SQLException {
    sceneProductDao.insertSceneProduct(sceneProduct);
  }

  @Override
  public void delSceneProductById(Long id) throws SQLException {
    sceneProductDao.delSceneProductById(id);
  }

  @Override
  public void delSceneProductBySceneId(Long sceneId) throws SQLException {
    sceneProductDao.delSceneProductBySceneId(sceneId);
  }

  @Override
  public void checkSceneProductById(Long id) throws SQLException {
    sceneProductDao.checkSceneProductById(id);
  }

  @Override
  public void uncheckSceneProductById(Long id) throws SQLException {
    sceneProductDao.uncheckSceneProductById(id);
  }

  @Override
  public void updateSceneProductById(SceneProductPojo sceneProduct) throws SQLException {
    sceneProductDao.updateSceneProductById(sceneProduct);
  }
}
