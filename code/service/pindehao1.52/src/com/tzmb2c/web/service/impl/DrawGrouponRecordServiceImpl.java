/*
 * 文 件 名: DramGrouponRecordServiceImpl.java 创 建 人: admin 创建时间: 2016-09-22
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.DrawGrouponRecordDao;
import com.tzmb2c.web.pojo.DrawGrouponRecordPojo;
import com.tzmb2c.web.service.DrawGrouponRecordService;

/**
 * DramGrouponRecord Service层
 */
public class DrawGrouponRecordServiceImpl implements DrawGrouponRecordService {

  @Autowired
  private DrawGrouponRecordDao dramGrouponRecorddao;

  @Override
  public int add(DrawGrouponRecordPojo dramGrouponRecord) throws SQLException {
    if (null == dramGrouponRecord) {
      return 0;
    }
    int rows = dramGrouponRecorddao.add(dramGrouponRecord);
    return rows;
  }

  @Override
  public int update(DrawGrouponRecordPojo dramGrouponRecord) throws SQLException {
    if (null == dramGrouponRecord) {
      return 0;
    }
    int rows = dramGrouponRecorddao.update(dramGrouponRecord);
    return rows;
  }

  @Override
  public int openWinHandle(DrawGrouponRecordPojo dramGrouponRecord) throws SQLException {
    if (null == dramGrouponRecord) {
      return 0;
    }
    int rows = dramGrouponRecorddao.openWinHandle(dramGrouponRecord);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = dramGrouponRecorddao.delete(id);
    return rows;
  }

  @Override
  public DrawGrouponRecordPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    DrawGrouponRecordPojo dramGrouponRecord = dramGrouponRecorddao.getById(id);
    return dramGrouponRecord;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = dramGrouponRecorddao.countBy(params);
    return rows;
  }

  @Override
  public List<DrawGrouponRecordPojo> listPage(Map<String, Object> params) throws SQLException {
    List<DrawGrouponRecordPojo> lists = dramGrouponRecorddao.listPage(params);
    return lists;
  }

  @Override
  public DrawGrouponRecordPojo findByParams(Map<String, Object> params) throws SQLException {
    return dramGrouponRecorddao.findByParams(params);
  }

  @Override
  public Integer joinNumByActivityId(Long activityId) throws SQLException {
    if (activityId == null || activityId < 1) {
      return 0;
    }
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("activityId", activityId);
    return countBy(params);
  }

  @Override
  public Integer joinNumByAttendId(Long attendId) throws SQLException {
    if (attendId == null || attendId < 1) {
      return 0;
    }
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("attendId", attendId);
    return countBy(params);
  }

  @Override
  public synchronized boolean isJoin(Long userId, Long activityId, Long attendId)
      throws SQLException {
    if (userId == null || userId < 1 || activityId == null || activityId < 1) {
      return false;
    }
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("userId", userId);
    params.put("activityId", activityId);
    params.put("attendId", attendId);
    int count = countBy(params);
    return count > 0 ? true : false;
  }

  @Override
  public int updateIsRecCoupon(Map<String, Object> params) throws SQLException {
    return dramGrouponRecorddao.updateIsRecCoupon(params);
  }

  @Override
  public Integer countBy2(Map<String, Object> params) throws SQLException {
    // TODO Auto-generated method stub
    return dramGrouponRecorddao.countBy2(params);
  }

  @Override
  public List<DrawGrouponRecordPojo> listPage2(Map<String, Object> params) throws SQLException {
    // TODO Auto-generated method stub
    return dramGrouponRecorddao.listPage2(params);
  }
  
  @Override
  public Integer countBy3(Map<String, Object> params) throws SQLException {
    // TODO Auto-generated method stub
    return dramGrouponRecorddao.countBy3(params);
  }

  @Override
  public List<DrawGrouponRecordPojo> listPage3(Map<String, Object> params) throws SQLException {
    // TODO Auto-generated method stub
    return dramGrouponRecorddao.listPage3(params);
  }

}
