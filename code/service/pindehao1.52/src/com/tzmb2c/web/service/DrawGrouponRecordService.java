/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.DrawGrouponRecordPojo;

/**
 * @version 1.0
 * @author
 */
public interface DrawGrouponRecordService {

  public int add(DrawGrouponRecordPojo dramGrouponRecord) throws SQLException;

  public int update(DrawGrouponRecordPojo dramGrouponRecord) throws SQLException;

  public int updateIsRecCoupon(Map<String, Object> params) throws SQLException;

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

}
