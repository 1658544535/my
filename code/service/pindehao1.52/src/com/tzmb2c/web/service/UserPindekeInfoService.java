/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserPindekeInfoPojo;

/**
 * @version 1.0
 * @author
 */
public interface UserPindekeInfoService {

  public int add(UserPindekeInfoPojo userPindekeInfo) throws SQLException;

  public int update(UserPindekeInfoPojo userPindekeInfo) throws SQLException;

  public int delete(Map<String, Object> params) throws SQLException;

  public UserPindekeInfoPojo getById(Long id) throws SQLException;

  public Integer countBy(Map<String, Object> params) throws SQLException;

  public List<UserPindekeInfoPojo> listPage(Map<String, Object> params) throws SQLException;

  public UserPindekeInfoPojo findByUserId(Long userId) throws SQLException;

  public int updateInvitationCode(Map<String, Object> map) throws SQLException;
}
