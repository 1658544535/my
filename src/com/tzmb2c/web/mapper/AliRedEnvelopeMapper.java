/*
 * 文 件 名: AliRedEnvelopeMapper.java 创 建 人: admin 创建时间: 2017-01-06
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.AliRedEnvelopePojo;

public interface AliRedEnvelopeMapper {

  AliRedEnvelopePojo getById(Long id) throws SQLException;

  int countBy(Map<String, Object> params) throws SQLException;

  List<AliRedEnvelopePojo> listPage(Map<String, Object> params) throws SQLException;

  int insert(AliRedEnvelopePojo aliRedEnvelope) throws SQLException;

  int update(AliRedEnvelopePojo aliRedEnvelope) throws SQLException;

  int update2(AliRedEnvelopePojo aliRedEnvelope) throws SQLException;

  int deleteById(Long id) throws SQLException;

  public AliRedEnvelopePojo getByInviteCode(String inviteCode) throws SQLException;
}
