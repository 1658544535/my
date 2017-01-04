package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserWalletDao;
import com.tzmb2c.web.mapper.UserWalletMapper;
import com.tzmb2c.web.pojo.UserWalletPojo;

public class UserWalletDaoImpl implements UserWalletDao {
  @Autowired
  private UserWalletMapper userWalletMapper;

  @Override
  public List<UserWalletPojo> findUserWalletList(Map<String, Object> map) throws SQLException {

    return userWalletMapper.findUserWalletList(map);
  }

  @Override
  public int findUserWalletCount(Map<String, Object> map) throws SQLException {

    return userWalletMapper.findUserWalletCount(map);
  }

  @Override
  public Long insertUserWallet(UserWalletPojo userWalletPojo) throws SQLException {

    return userWalletMapper.insertUserWallet(userWalletPojo);
  }

  @Override
  public void delUserWallet(Long id) throws SQLException {

    userWalletMapper.delUserWallet(id);
  }

  @Override
  public UserWalletPojo findUserWalletById(Long id) throws SQLException {

    return userWalletMapper.findUserWalletById(id);
  }

  @Override
  public int updateUserWallet(UserWalletPojo userWalletPojo) throws SQLException {

    return userWalletMapper.updateUserWallet(userWalletPojo);
  }

  @Override
  public UserWalletPojo findUserWalletByUserId(Long userId) throws SQLException {

    return userWalletMapper.findUserWalletByUserId(userId);
  }
}
