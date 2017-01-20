/*
 * 文 件 名: AliRedEnvelopeServiceImpl.java 创 建 人: admin 创建时间: 2017-01-06
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.AliRedEnvelopeDao;
import com.tzmb2c.web.pojo.AliRedEnvelopePojo;
import com.tzmb2c.web.service.AliRedEnvelopeService;

/**
 * AliRedEnvelope Service层
 */
public class AliRedEnvelopeServiceImpl implements AliRedEnvelopeService {

  @Autowired
  private AliRedEnvelopeDao aliRedEnvelopedao;

  @Override
  public int add(AliRedEnvelopePojo aliRedEnvelope) throws SQLException {
    if (null == aliRedEnvelope) {
      return 0;
    }
    int rows = aliRedEnvelopedao.add(aliRedEnvelope);
    return rows;
  }

  @Override
  public int update(AliRedEnvelopePojo aliRedEnvelope) throws SQLException {
    if (null == aliRedEnvelope) {
      return 0;
    }
    int rows = aliRedEnvelopedao.update(aliRedEnvelope);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = aliRedEnvelopedao.delete(id);
    return rows;
  }

  @Override
  public AliRedEnvelopePojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    AliRedEnvelopePojo aliRedEnvelope = aliRedEnvelopedao.getById(id);
    return aliRedEnvelope;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = aliRedEnvelopedao.countBy(params);
    return rows;
  }

  @Override
  public List<AliRedEnvelopePojo> listPage(Map<String, Object> params) throws SQLException {
    List<AliRedEnvelopePojo> lists = aliRedEnvelopedao.listPage(params);
    return lists;
  }

  @Override
  public AliRedEnvelopePojo getByInviteCode(String inviteCode) throws SQLException {
    if (null == inviteCode || "".equals(inviteCode)) {
      return null;
    }
    AliRedEnvelopePojo aliRedEnvelope = aliRedEnvelopedao.getByInviteCode(inviteCode);
    return aliRedEnvelope;
  }

  @Override
  public int update2(AliRedEnvelopePojo aliRedEnvelope) throws SQLException {
    if (null == aliRedEnvelope) {
      return 0;
    }
    int rows = aliRedEnvelopedao.update2(aliRedEnvelope);
    return rows;
  }
}
