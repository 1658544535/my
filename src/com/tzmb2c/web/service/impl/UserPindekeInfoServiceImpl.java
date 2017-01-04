/*
 * 文 件 名: UserPindekeInfoServiceImpl.java 创 建 人: admin 创建时间: 2016-10-15
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserPindekeInfoDao;
import com.tzmb2c.web.pojo.UserPindekeInfoPojo;
import com.tzmb2c.web.service.UserPindekeInfoService;

/**
 * UserPindekeInfo Service层
 */
public class UserPindekeInfoServiceImpl implements UserPindekeInfoService {

  @Autowired
  private UserPindekeInfoDao userPindekeInfodao;

  @Override
  public int add(UserPindekeInfoPojo userPindekeInfo) throws SQLException {
    if (null == userPindekeInfo) {
      return 0;
    }
    int rows = userPindekeInfodao.add(userPindekeInfo);
    return rows;
  }

  @Override
  public synchronized int update(UserPindekeInfoPojo userPindekeInfo) throws SQLException {
    if (null == userPindekeInfo) {
      return 0;
    }
    int rows = userPindekeInfodao.update(userPindekeInfo);
    return rows;
  }

  @Override
  public int delete(Map<String, Object> params) throws SQLException {
    if (null == params) {
      return 0;
    }
    int rows = userPindekeInfodao.delete(params);
    return rows;
  }

  @Override
  public UserPindekeInfoPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    UserPindekeInfoPojo userPindekeInfo = userPindekeInfodao.getById(id);
    return userPindekeInfo;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = userPindekeInfodao.countBy(params);
    return rows;
  }

  @Override
  public List<UserPindekeInfoPojo> listPage(Map<String, Object> params) throws SQLException {
    List<UserPindekeInfoPojo> lists = userPindekeInfodao.listPage(params);
    return lists;
  }

  @Override
  public UserPindekeInfoPojo findByUserId(Long userId) throws SQLException {
    return userPindekeInfodao.findByUserId(userId);
  }

  @Override
  public int updateInvitationCode(Map<String, Object> map) throws SQLException {
    return userPindekeInfodao.updateInvitationCode(map);
  }
}
