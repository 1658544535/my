package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SceneProductDao;
import com.tzmb2c.web.mapper.SceneProductMapper;
import com.tzmb2c.web.pojo.SceneProductPojo;

public class SceneProductDaoImpl implements SceneProductDao {
  @Autowired
  private SceneProductMapper sceneProductMapper;

  @Override
  public List<SceneProductPojo> findSceneProductList(Map<String, Object> map) throws SQLException {
    return sceneProductMapper.findSceneProductList(map);
  }

  @Override
  public int findSceneProductCount(Map<String, Object> map) throws SQLException {
    return sceneProductMapper.findSceneProductCount(map);
  }

  @Override
  public SceneProductPojo findSceneProductById(Long id) throws SQLException {
    return sceneProductMapper.findSceneProductById(id);
  }

  @Override
  public void insertSceneProduct(SceneProductPojo sceneProduct) throws SQLException {
    sceneProductMapper.insertSceneProduct(sceneProduct);
  }

  @Override
  public void delSceneProductById(Long id) throws SQLException {
    sceneProductMapper.delSceneProductById(id);
  }

  @Override
  public void delSceneProductBySceneId(Long sceneId) throws SQLException {
    sceneProductMapper.delSceneProductBySceneId(sceneId);
  }

  @Override
  public void checkSceneProductById(Long id) throws SQLException {
    sceneProductMapper.checkSceneProductById(id);
  }

  @Override
  public void uncheckSceneProductById(Long id) throws SQLException {
    sceneProductMapper.uncheckSceneProductById(id);
  }

  @Override
  public void updateSceneProductById(SceneProductPojo sceneProduct) throws SQLException {
    sceneProductMapper.updateSceneProductById(sceneProduct);
  }


}
