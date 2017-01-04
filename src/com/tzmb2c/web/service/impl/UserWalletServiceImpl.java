package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserWalletDao;
import com.tzmb2c.web.pojo.UserWalletPojo;
import com.tzmb2c.web.service.UserWalletService;

public class UserWalletServiceImpl implements UserWalletService {
  @Autowired
  private UserWalletDao userWalletDao;

  @Override
  public List<UserWalletPojo> findUserWalletList(Map<String, Object> map) throws SQLException {

    return userWalletDao.findUserWalletList(map);
  }

  @Override
  public int findUserWalletCount(Map<String, Object> map) throws SQLException {

    return userWalletDao.findUserWalletCount(map);
  }

  @Override
  public Long insertUserWallet(UserWalletPojo userWalletPojo) throws SQLException {

    return userWalletDao.insertUserWallet(userWalletPojo);
  }

  @Override
  public void delUserWallet(Long id) throws SQLException {

    userWalletDao.delUserWallet(id);
  }

  @Override
  public UserWalletPojo findUserWalletById(Long id) throws SQLException {

    return userWalletDao.findUserWalletById(id);
  }

  @Override
  public int updateUserWallet(UserWalletPojo userWalletPojo) throws SQLException {

    return userWalletDao.updateUserWallet(userWalletPojo);
  }

  @Override
  public UserWalletPojo findUserWalletByUserId(Long userId) throws SQLException {

    return userWalletDao.findUserWalletByUserId(userId);
  }
}
