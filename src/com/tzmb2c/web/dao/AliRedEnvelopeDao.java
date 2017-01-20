/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.AliRedEnvelopePojo;

/**
 * @version 1.0
 * @author
 */
public interface AliRedEnvelopeDao {

  public int add(AliRedEnvelopePojo aliRedEnvelope) throws SQLException;

  public int update(AliRedEnvelopePojo aliRedEnvelope) throws SQLException;

  public int update2(AliRedEnvelopePojo aliRedEnvelope) throws SQLException;

  public int delete(Long id) throws SQLException;

  public AliRedEnvelopePojo getById(Long id) throws SQLException;

  public AliRedEnvelopePojo getByInviteCode(String inviteCode) throws SQLException;

  public Integer countBy(Map<String, Object> params) throws SQLException;

  public List<AliRedEnvelopePojo> listPage(Map<String, Object> params) throws SQLException;

}
