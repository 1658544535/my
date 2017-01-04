package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserWalletPojo;

public interface UserWalletMapper {
  List<UserWalletPojo> findUserWalletList(Map<String, Object> map) throws SQLException;

  int findUserWalletCount(Map<String, Object> map) throws SQLException;

  Long insertUserWallet(UserWalletPojo userWalletPojo) throws SQLException;

  void delUserWallet(Long id) throws SQLException;

  UserWalletPojo findUserWalletById(Long id) throws SQLException;

  int updateUserWallet(UserWalletPojo userWalletPojo) throws SQLException;

  UserWalletPojo findUserWalletByUserId(Long userId) throws SQLException;
}
