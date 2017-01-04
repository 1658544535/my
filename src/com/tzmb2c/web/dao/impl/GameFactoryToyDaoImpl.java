package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.GameFactoryToyDao;
import com.tzmb2c.web.mapper.GameFactoryToyMapper;
import com.tzmb2c.web.pojo.GameFactoryToyPojo;

public class GameFactoryToyDaoImpl implements GameFactoryToyDao {
  @Autowired
  private GameFactoryToyMapper gameFactoryToyMapper;

  @Override
  public List<GameFactoryToyPojo> findGameFactoryToyList(Map<String, Object> map)
      throws SQLException {
    return gameFactoryToyMapper.findGameFactoryToyList(map);
  }

  @Override
  public int findGameFactoryToyCount(Map<String, Object> map) throws SQLException {
    return gameFactoryToyMapper.findGameFactoryToyCount(map);
  }

  @Override
  public GameFactoryToyPojo findGameFactoryToyById(Long id) throws SQLException {
    return gameFactoryToyMapper.findGameFactoryToyById(id);
  }

  @Override
  public Long insertGameFactoryToy(GameFactoryToyPojo gameFactoryToy) throws SQLException {
    return gameFactoryToyMapper.insertGameFactoryToy(gameFactoryToy);
  }

  @Override
  public void delGameFactoryToy(Long id) throws SQLException {
    gameFactoryToyMapper.delGameFactoryToy(id);
  }

  @Override
  public void updateGameFactoryToy(GameFactoryToyPojo gameFactoryToy) throws SQLException {
    gameFactoryToyMapper.updateGameFactoryToy(gameFactoryToy);
  }

  @Override
  public void checkGameFactoryToy(Long id) throws SQLException {
    gameFactoryToyMapper.checkGameFactoryToy(id);
  }

  @Override
  public void uncheckGameFactoryToy(Long id) throws SQLException {
    gameFactoryToyMapper.uncheckGameFactoryToy(id);
  }

}
