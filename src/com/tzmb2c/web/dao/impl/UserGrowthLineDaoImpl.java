package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tzmb2c.web.dao.UserGrowthLineDao;
import com.tzmb2c.web.mapper.UserGrowthLineMapper;
import com.tzmb2c.web.pojo.UserGrowthLinePojo;

// @Service
public class UserGrowthLineDaoImpl implements UserGrowthLineDao {

  @Autowired
  private UserGrowthLineMapper userGrowthLineMapper;

  @Override
  @Transactional
  public int addUserGrowthLine(UserGrowthLinePojo userGrowthLine) {
    if (null == userGrowthLine) {
      return 0;
    }
    int rows = userGrowthLineMapper.addUserGrowthLine(userGrowthLine);
    return rows;
  }

  @Override
  @Transactional
  public int updateUserGrowthLine(UserGrowthLinePojo userGrowthLine) {
    if (null == userGrowthLine) {
      return 0;
    }
    int rows = userGrowthLineMapper.updateUserGrowthLine(userGrowthLine);
    return rows;
  }

  @Override
  @Transactional
  public int deleteUserGrowthLineById(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = userGrowthLineMapper.deleteUserGrowthLineById(id);
    return rows;
  }


  @Override
  public UserGrowthLinePojo getUserGrowthLineById(Long id) {
    if (null == id) {
      return null;
    }
    UserGrowthLinePojo userGrowthLine = userGrowthLineMapper.getUserGrowthLineById(id);
    //
    return userGrowthLine;
  }

  @Override
  public Integer userGrowthLineCount(Map<String, Object> params) {
    Integer rows = userGrowthLineMapper.userGrowthLineCount(params);
    return rows;
  }

  @Override
  public List<UserGrowthLinePojo> userGrowthLineList(Map<String, Object> params) {
    List<UserGrowthLinePojo> lists = userGrowthLineMapper.userGrowthLineList(params);

    return lists;
  }

  @Override
  public int addUserScore(Map<String, Object> params) {
    return userGrowthLineMapper.addUserScore(params);
  }

  @Override
  public UserGrowthLinePojo getUserGrowthLineByUid(Map<String, Object> params) {
    return userGrowthLineMapper.getUserGrowthLineByUid(params);
  }

  @Override
  public int batchAgeUpdGrowLine(int ageType) {
    return userGrowthLineMapper.batchAgeUpdGrowLine(ageType);
  }

  @Override
  public int batchMonthUpdGrowLine() {
    return userGrowthLineMapper.batchMonthUpdGrowLine();
  }
}
