/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.GrouponExcelPojo;
import com.tzmb2c.web.pojo.GrouponUserRecordPojo;

/**
 * @version 1.0
 * @author
 */
public interface GrouponUserRecordService {

  public int add(GrouponUserRecordPojo grouponUserRecord) throws SQLException;

  public int update(GrouponUserRecordPojo grouponUserRecord) throws SQLException;

  public int updateIsRecCoupon(Map<String, Object> params) throws SQLException;

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

  public void updateIsPrize(Map<String, Object> params) throws SQLException;

  public List<GrouponUserRecordPojo> findAttendOrders(Map<String, Object> params)
      throws SQLException;

  /**
   * 根据活动ID返回参团总人数
   * 
   * @param activityId
   * @throw
   * @return Integer
   */
  public Integer joinNumByActivityId(Long activityId) throws SQLException;

  /**
   * 根据参团ID返回参团人数
   * 
   * @param attendId
   * @throw
   * @return Integer
   */
  public Integer joinNumByAttendId(Long attendId) throws SQLException;

  /**
   * 判断用户是否参团
   * 
   * @param userId 用户ID
   * @param activityId 活动ID
   * @param attendId 普通拼团/团免 参团ID 猜价时传NULL
   * @throw
   * @return boolean
   */
  public boolean isJoin(Long userId, Long activityId, Long attendId) throws SQLException;

  public List<GrouponExcelPojo> listPage4(Map<String, Object> params) throws SQLException;


}
