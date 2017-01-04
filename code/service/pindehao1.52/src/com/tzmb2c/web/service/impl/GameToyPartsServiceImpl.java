package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.GameToyPartsDao;
import com.tzmb2c.web.pojo.GameToyPartsPojo;
import com.tzmb2c.web.service.GameToyPartsService;

public class GameToyPartsServiceImpl implements GameToyPartsService {
  @Autowired
  private GameToyPartsDao gameToyPartsDao;

  @Override
  public List<GameToyPartsPojo> findGameToyPartsList(Map<String, Object> map) throws SQLException {
    return gameToyPartsDao.findGameToyPartsList(map);
  }

  @Override
  public int findGameToyPartsCount(Map<String, Object> map) throws SQLException {
    return gameToyPartsDao.findGameToyPartsCount(map);
  }

  @Override
  public GameToyPartsPojo findGameToyPartsById(Long id) throws SQLException {
    return gameToyPartsDao.findGameToyPartsById(id);
  }

  @Override
  public Long insertGameToyParts(GameToyPartsPojo gameToyParts) throws SQLException {
    return gameToyPartsDao.insertGameToyParts(gameToyParts);
  }

  @Override
  public void delGameToyParts(Long id) throws SQLException {
    gameToyPartsDao.delGameToyParts(id);
  }

  @Override
  public void updateGameToyParts(GameToyPartsPojo gameToyParts) throws SQLException {
    gameToyPartsDao.updateGameToyParts(gameToyParts);
  }

  @Override
  public void checkGameToyParts(Long id) throws SQLException {
    gameToyPartsDao.checkGameToyParts(id);
  }

  @Override
  public void uncheckGameToyParts(Long id) throws SQLException {
    gameToyPartsDao.uncheckGameToyParts(id);
  }

}
