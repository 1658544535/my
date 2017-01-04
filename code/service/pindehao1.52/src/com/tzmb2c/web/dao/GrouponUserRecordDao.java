/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.GrouponExcelPojo;
import com.tzmb2c.web.pojo.GrouponUserRecordPojo;

/**
 * @version 1.0
 * @author
 */
public interface GrouponUserRecordDao {

  public int add(GrouponUserRecordPojo grouponUserRecord) throws SQLException;

  public int update(GrouponUserRecordPojo grouponUserRecord) throws SQLException;

  public int openWinHandle(GrouponUserRecordPojo grouponUserRecord) throws SQLException;

  public int delete(Long id) throws SQLException;

  public GrouponUserRecordPojo getById(Long id) throws SQLException;

  public Integer countBy(Map<String, Object> params) throws SQLException;

  public List<GrouponUserRecordPojo> listPage(Map<String, Object> params) throws SQLException;

  public Integer countBy2(Map<String, Object> params) throws SQLException;

  public List<GrouponUserRecordPojo> listPage2(Map<String, Object> params) throws SQLException;

  public Integer countBy3(Map<String, Object> params) throws SQLException;

  public List<GrouponUserRecordPojo> listPage3(Map<String, Object> params) throws SQLException;

  public GrouponUserRecordPojo findByParams(Map<String, Object> params) throws SQLException;

  public int updateIsRecCoupon(Map<String, Object> params) throws SQLException;

  public void updateIsPrize(Map<String, Object> params) throws SQLException;

  List<GrouponExcelPojo> listPage4(Map<String, Object> params) throws SQLException;

  public List<GrouponUserRecordPojo> findAttendOrders(Map<String, Object> params)
      throws SQLException;

}
