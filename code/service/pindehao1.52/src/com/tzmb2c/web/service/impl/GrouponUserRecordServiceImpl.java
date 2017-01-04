/*
 * 文 件 名: GrouponUserRecordServiceImpl.java 创 建 人: admin 创建时间: 2016-09-22
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.GrouponUserRecordDao;
import com.tzmb2c.web.pojo.GrouponExcelPojo;
import com.tzmb2c.web.pojo.GrouponUserRecordPojo;
import com.tzmb2c.web.service.GrouponUserRecordService;

/**
 * GrouponUserRecord Service层
 */
public class GrouponUserRecordServiceImpl implements GrouponUserRecordService {

  @Autowired
  private GrouponUserRecordDao grouponUserRecorddao;

  @Override
  public int add(GrouponUserRecordPojo grouponUserRecord) throws SQLException {
    if (null == grouponUserRecord) {
      return 0;
    }
    int rows = grouponUserRecorddao.add(grouponUserRecord);
    return rows;
  }

  @Override
  public int update(GrouponUserRecordPojo grouponUserRecord) throws SQLException {
    if (null == grouponUserRecord) {
      return 0;
    }
    int rows = grouponUserRecorddao.update(grouponUserRecord);
    return rows;
  }

  @Override
  public int openWinHandle(GrouponUserRecordPojo grouponUserRecord) throws SQLException {
    if (null == grouponUserRecord) {
      return 0;
    }
    int rows = grouponUserRecorddao.openWinHandle(grouponUserRecord);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = grouponUserRecorddao.delete(id);
    return rows;
  }

  @Override
  public GrouponUserRecordPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    GrouponUserRecordPojo grouponUserRecord = grouponUserRecorddao.getById(id);
    return grouponUserRecord;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = grouponUserRecorddao.countBy(params);
    return rows;
  }

  @Override
  public List<GrouponUserRecordPojo> listPage(Map<String, Object> params) throws SQLException {
    List<GrouponUserRecordPojo> lists = grouponUserRecorddao.listPage(params);
    return lists;
  }

  @Override
  public GrouponUserRecordPojo findByParams(Map<String, Object> params) throws SQLException {
    return grouponUserRecorddao.findByParams(params);
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
    return grouponUserRecorddao.updateIsRecCoupon(params);
  }

  @Override
  public Integer countBy2(Map<String, Object> params) throws SQLException {
    return grouponUserRecorddao.countBy2(params);
  }

  @Override
  public List<GrouponUserRecordPojo> listPage2(Map<String, Object> params) throws SQLException {
    return grouponUserRecorddao.listPage2(params);
  }

  @Override
  public Integer countBy3(Map<String, Object> params) throws SQLException {
    return grouponUserRecorddao.countBy3(params);
  }

  @Override
  public List<GrouponUserRecordPojo> listPage3(Map<String, Object> params) throws SQLException {
    return grouponUserRecorddao.listPage3(params);
  }

  @Override
  public void updateIsPrize(Map<String, Object> params) throws SQLException {
    grouponUserRecorddao.updateIsPrize(params);
  }

  @Override
  public List<GrouponExcelPojo> listPage4(Map<String, Object> params) throws SQLException {
    return grouponUserRecorddao.listPage4(params);
  }

  @Override
  public List<GrouponUserRecordPojo> findAttendOrders(Map<String, Object> params)
      throws SQLException {
    return grouponUserRecorddao.findAttendOrders(params);
  }


}
