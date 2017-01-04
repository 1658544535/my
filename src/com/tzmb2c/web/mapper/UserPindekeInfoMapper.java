/*
 * 文 件 名: UserPindekeInfoMapper.java 创 建 人: admin 创建时间: 2016-10-15
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserPindekeInfoPojo;

public interface UserPindekeInfoMapper {

  UserPindekeInfoPojo getById(Long id) throws SQLException;

  int countBy(Map<String, Object> params) throws SQLException;

  List<UserPindekeInfoPojo> listPage(Map<String, Object> params) throws SQLException;

  int insert(UserPindekeInfoPojo userPindekeInfo) throws SQLException;

  int update(UserPindekeInfoPojo userPindekeInfo) throws SQLException;

  int deleteById(Map<String, Object> params) throws SQLException;

  public UserPindekeInfoPojo findByUserId(Long userId) throws SQLException;
  
  public int updateInvitationCode(Map<String, Object> map) throws SQLException;
}
