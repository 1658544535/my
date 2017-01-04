package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.GameFactoryToyDao;
import com.tzmb2c.web.pojo.GameFactoryToyPojo;
import com.tzmb2c.web.service.GameFactoryToyService;

public class GameFactoryToyServiceImpl implements GameFactoryToyService {
  @Autowired
  private GameFactoryToyDao gameFactoryToyDao;

  @Override
  public List<GameFactoryToyPojo> findGameFactoryToyList(Map<String, Object> map)
      throws SQLException {
    return gameFactoryToyDao.findGameFactoryToyList(map);
  }

  @Override
  public int findGameFactoryToyCount(Map<String, Object> map) throws SQLException {
    return gameFactoryToyDao.findGameFactoryToyCount(map);
  }

  @Override
  public GameFactoryToyPojo findGameFactoryToyById(Long id) throws SQLException {
    return gameFactoryToyDao.findGameFactoryToyById(id);
  }

  @Override
  public Long insertGameFactoryToy(GameFactoryToyPojo gameFactoryToy) throws SQLException {
    return gameFactoryToyDao.insertGameFactoryToy(gameFactoryToy);
  }

  @Override
  public void delGameFactoryToy(Long id) throws SQLException {
    gameFactoryToyDao.delGameFactoryToy(id);
  }

  @Override
  public void updateGameFactoryToy(GameFactoryToyPojo gameFactoryToy) throws SQLException {
    gameFactoryToyDao.updateGameFactoryToy(gameFactoryToy);
  }

  @Override
  public void checkGameFactoryToy(Long id) throws SQLException {
    gameFactoryToyDao.checkGameFactoryToy(id);
  }

  @Override
  public void uncheckGameFactoryToy(Long id) throws SQLException {
    gameFactoryToyDao.uncheckGameFactoryToy(id);
  }

}
