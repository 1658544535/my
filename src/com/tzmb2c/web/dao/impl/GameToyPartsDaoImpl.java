package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.GameToyPartsDao;
import com.tzmb2c.web.mapper.GameToyPartsMapper;
import com.tzmb2c.web.pojo.GameToyPartsPojo;

public class GameToyPartsDaoImpl implements GameToyPartsDao {
  @Autowired
  private GameToyPartsMapper gameToyPartsMapper;

  @Override
  public List<GameToyPartsPojo> findGameToyPartsList(Map<String, Object> map) throws SQLException {
    return gameToyPartsMapper.findGameToyPartsList(map);
  }

  @Override
  public int findGameToyPartsCount(Map<String, Object> map) throws SQLException {
    return gameToyPartsMapper.findGameToyPartsCount(map);
  }

  @Override
  public GameToyPartsPojo findGameToyPartsById(Long id) throws SQLException {
    return gameToyPartsMapper.findGameToyPartsById(id);
  }

  @Override
  public Long insertGameToyParts(GameToyPartsPojo gameToyParts) throws SQLException {
    return gameToyPartsMapper.insertGameToyParts(gameToyParts);
  }

  @Override
  public void delGameToyParts(Long id) throws SQLException {
    gameToyPartsMapper.delGameToyParts(id);
  }

  @Override
  public void updateGameToyParts(GameToyPartsPojo gameToyParts) throws SQLException {
    gameToyPartsMapper.updateGameToyParts(gameToyParts);
  }

  @Override
  public void checkGameToyParts(Long id) throws SQLException {
    gameToyPartsMapper.checkGameToyParts(id);
  }

  @Override
  public void uncheckGameToyParts(Long id) throws SQLException {
    gameToyPartsMapper.uncheckGameToyParts(id);
  }

}
