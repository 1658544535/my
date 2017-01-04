package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.GameToyPartsPojo;

public interface GameToyPartsDao {
  List<GameToyPartsPojo> findGameToyPartsList(Map<String, Object> map) throws SQLException;

  int findGameToyPartsCount(Map<String, Object> map) throws SQLException;

  GameToyPartsPojo findGameToyPartsById(Long id) throws SQLException;

  Long insertGameToyParts(GameToyPartsPojo gameToyParts) throws SQLException;

  void delGameToyParts(Long id) throws SQLException;

  void updateGameToyParts(GameToyPartsPojo gameToyParts) throws SQLException;

  void checkGameToyParts(Long id) throws SQLException;

  void uncheckGameToyParts(Long id) throws SQLException;

}
