/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.DrawGrouponRecordPojo;

/**
 * @version 1.0
 * @author
 */
public interface DrawGrouponRecordDao {

  public int add(DrawGrouponRecordPojo dramGrouponRecord) throws SQLException;

  public int update(DrawGrouponRecordPojo dramGrouponRecord) throws SQLException;

  public int openWinHandle(DrawGrouponRecordPojo dramGrouponRecord) throws SQLException;

  public int delete(Long id) throws SQLException;

  public DrawGrouponRecordPojo getById(Long id) throws SQLException;

  public Integer countBy(Map<String, Object> params) throws SQLException;

  public List<DrawGrouponRecordPojo> listPage(Map<String, Object> params) throws SQLException;

  public Integer countBy2(Map<String, Object> params) throws SQLException;

  public List<DrawGrouponRecordPojo> listPage2(Map<String, Object> params) throws SQLException;
  
  public Integer countBy3(Map<String, Object> params) throws SQLException;

  public List<DrawGrouponRecordPojo> listPage3(Map<String, Object> params) throws SQLException;

  public DrawGrouponRecordPojo findByParams(Map<String, Object> params) throws SQLException;

  public int updateIsRecCoupon(Map<String, Object> params) throws SQLException;
}
