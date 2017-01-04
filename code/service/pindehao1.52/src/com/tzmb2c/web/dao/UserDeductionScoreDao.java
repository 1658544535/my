package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserDeductionScorePojo;

public interface UserDeductionScoreDao {

  public void insertUserDeductionScore(UserDeductionScorePojo userDeductionScorePojo)
      throws SQLException;

  public List<UserDeductionScorePojo> findUserDeductionScoreList(Map<String, Object> map)
      throws SQLException;

  public int findUserDeductionScoreCount(Map<String, Object> map) throws SQLException;

  public void delUserDeductionScoreById(Long id) throws SQLException;

  public void checkUserDeductionScore(Long id) throws SQLException;

  public void uncheckUserDeductionScore(Long id) throws SQLException;

  public void updateUserDeductionScore(UserDeductionScorePojo userDeductionScorePojo)
      throws SQLException;

  public double findUserDeductionScoreAll(Map<String, Object> map) throws SQLException;


}
