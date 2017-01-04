package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ScenePojo;

public interface SceneMapper {
  List<ScenePojo> findSceneList(Map<String, Object> map) throws SQLException;

  int findSceneCount(Map<String, Object> map) throws SQLException;

  ScenePojo findSceneById(Long id) throws SQLException;

  void insertScene(ScenePojo scene) throws SQLException;

  void delSceneById(Long id) throws SQLException;

  void checkSceneById(Long id) throws SQLException;

  void uncheckSceneById(Long id) throws SQLException;

  void updateSceneById(ScenePojo scene) throws SQLException;

  long lastInsertId() throws SQLException;
}
