package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserDeductionScoreDao;
import com.tzmb2c.web.mapper.UserDeductionScoreMapper;
import com.tzmb2c.web.pojo.UserDeductionScorePojo;

public class UserDeductionScoreDaoImpl implements UserDeductionScoreDao {

  @Autowired
  private UserDeductionScoreMapper userDeductionScoreMapper;

  @Override
  public void insertUserDeductionScore(UserDeductionScorePojo userDeductionScorePojo)
      throws SQLException {
    // TODO Auto-generated method stub
    userDeductionScoreMapper.insertUserDeductionScore(userDeductionScorePojo);
  }

  @Override
  public List<UserDeductionScorePojo> findUserDeductionScoreList(Map<String, Object> map)
      throws SQLException {
    // TODO Auto-generated method stub
    return userDeductionScoreMapper.findUserDeductionScoreList(map);
  }

  @Override
  public void delUserDeductionScoreById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    userDeductionScoreMapper.delUserDeductionScoreById(id);
  }

  @Override
  public void checkUserDeductionScore(Long id) throws SQLException {
    // TODO Auto-generated method stub
    userDeductionScoreMapper.checkUserDeductionScore(id);
  }

  @Override
  public void uncheckUserDeductionScore(Long id) throws SQLException {
    // TODO Auto-generated method stub
    userDeductionScoreMapper.uncheckUserDeductionScore(id);
  }

  @Override
  public void updateUserDeductionScore(UserDeductionScorePojo userDeductionScorePojo)
      throws SQLException {
    // TODO Auto-generated method stub
    userDeductionScoreMapper.updateUserDeductionScore(userDeductionScorePojo);
  }

  @Override
  public int findUserDeductionScoreCount(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return userDeductionScoreMapper.findUserDeductionScoreCount(map);
  }

  @Override
  public double findUserDeductionScoreAll(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return userDeductionScoreMapper.findUserDeductionScoreAll(map);
  }


}
