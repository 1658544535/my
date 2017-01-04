package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserDeductionScoreDao;
import com.tzmb2c.web.pojo.UserDeductionScorePojo;
import com.tzmb2c.web.service.UserDeductionScoreService;

public class UserDeductionScoreServiceImpl implements UserDeductionScoreService {

  @Autowired
  private UserDeductionScoreDao userDeductionScoreDao;

  @Override
  public void insertUserDeductionScore(UserDeductionScorePojo userDeductionScorePojo)
      throws SQLException {
    // TODO Auto-generated method stub
    userDeductionScoreDao.insertUserDeductionScore(userDeductionScorePojo);
  }

  @Override
  public List<UserDeductionScorePojo> findUserDeductionScoreList(Map<String, Object> map)
      throws SQLException {
    // TODO Auto-generated method stub
    return userDeductionScoreDao.findUserDeductionScoreList(map);
  }

  @Override
  public void delUserDeductionScoreById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    userDeductionScoreDao.delUserDeductionScoreById(id);
  }

  @Override
  public void checkUserDeductionScore(Long id) throws SQLException {
    // TODO Auto-generated method stub
    userDeductionScoreDao.checkUserDeductionScore(id);
  }

  @Override
  public void uncheckUserDeductionScore(Long id) throws SQLException {
    // TODO Auto-generated method stub
    userDeductionScoreDao.uncheckUserDeductionScore(id);
  }

  @Override
  public void updateUserDeductionScore(UserDeductionScorePojo userDeductionScorePojo)
      throws SQLException {
    // TODO Auto-generated method stub
    userDeductionScoreDao.updateUserDeductionScore(userDeductionScorePojo);
  }

  @Override
  public int findUserDeductionScoreCount(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return userDeductionScoreDao.findUserDeductionScoreCount(map);
  }

  @Override
  public double findUserDeductionScoreAll(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return userDeductionScoreDao.findUserDeductionScoreAll(map);
  }


}
