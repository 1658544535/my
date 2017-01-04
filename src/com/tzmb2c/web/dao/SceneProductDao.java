package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SceneProductPojo;

public interface SceneProductDao {
  List<SceneProductPojo> findSceneProductList(Map<String, Object> map) throws SQLException;

  int findSceneProductCount(Map<String, Object> map) throws SQLException;

  SceneProductPojo findSceneProductById(Long id) throws SQLException;

  void insertSceneProduct(SceneProductPojo sceneProduct) throws SQLException;

  void delSceneProductById(Long id) throws SQLException;

  void checkSceneProductById(Long id) throws SQLException;

  void uncheckSceneProductById(Long id) throws SQLException;

  void updateSceneProductById(SceneProductPojo sceneProduct) throws SQLException;

  void delSceneProductBySceneId(Long sceneId) throws SQLException;
}
