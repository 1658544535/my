package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.GameFactoryToyPojo;

public interface GameFactoryToyDao {
  List<GameFactoryToyPojo> findGameFactoryToyList(Map<String, Object> map) throws SQLException;

  int findGameFactoryToyCount(Map<String, Object> map) throws SQLException;

  GameFactoryToyPojo findGameFactoryToyById(Long id) throws SQLException;

  Long insertGameFactoryToy(GameFactoryToyPojo gameFactoryToy) throws SQLException;

  void delGameFactoryToy(Long id) throws SQLException;

  void updateGameFactoryToy(GameFactoryToyPojo gameFactoryToy) throws SQLException;

  void checkGameFactoryToy(Long id) throws SQLException;

  void uncheckGameFactoryToy(Long id) throws SQLException;

}
