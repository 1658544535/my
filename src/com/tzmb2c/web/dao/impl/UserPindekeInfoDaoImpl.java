/*
 * 文 件 名: UserPindekeInfoDaoImpl.java 创 建 人: admin 创建时间: 2016-10-15
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserPindekeInfoDao;
import com.tzmb2c.web.mapper.UserPindekeInfoMapper;
import com.tzmb2c.web.pojo.UserPindekeInfoPojo;

/**
 * UserPindekeInfo Dao层
 */
public class UserPindekeInfoDaoImpl implements UserPindekeInfoDao {

  @Autowired
  private UserPindekeInfoMapper userPindekeInfoMapper;

  @Override
  public int add(UserPindekeInfoPojo userPindekeInfo) throws SQLException {
    if (null == userPindekeInfo) {
      return 0;
    }
    int rows = userPindekeInfoMapper.insert(userPindekeInfo);
    return rows;
  }

  @Override
  public int update(UserPindekeInfoPojo userPindekeInfo) throws SQLException {
    if (null == userPindekeInfo) {
      return 0;
    }
    int rows = userPindekeInfoMapper.update(userPindekeInfo);
    return rows;
  }

  @Override
  public int delete(Map<String, Object> params) throws SQLException {
    if (null == params) {
      return 0;
    }
    int rows = userPindekeInfoMapper.deleteById(params);
    return rows;
  }

  @Override
  public UserPindekeInfoPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    UserPindekeInfoPojo userPindekeInfo = userPindekeInfoMapper.getById(id);
    return userPindekeInfo;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = userPindekeInfoMapper.countBy(params);
    return rows;
  }

  @Override
  public List<UserPindekeInfoPojo> listPage(Map<String, Object> params) throws SQLException {
    List<UserPindekeInfoPojo> lists = userPindekeInfoMapper.listPage(params);
    return lists;
  }

  @Override
  public UserPindekeInfoPojo findByUserId(Long userId) throws SQLException {
    return userPindekeInfoMapper.findByUserId(userId);
  }

  @Override
  public int updateInvitationCode(Map<String, Object> map) throws SQLException {
    return userPindekeInfoMapper.updateInvitationCode(map);
  }
}
