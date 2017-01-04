/*
 * 文 件 名: GrouponUserRecordDaoImpl.java 创 建 人: admin 创建时间: 2016-09-22
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.GrouponUserRecordDao;
import com.tzmb2c.web.mapper.GrouponUserRecordMapper;
import com.tzmb2c.web.pojo.GrouponExcelPojo;
import com.tzmb2c.web.pojo.GrouponUserRecordPojo;

/**
 * GrouponUserRecord Dao层
 */
public class GrouponUserRecordDaoImpl implements GrouponUserRecordDao {

  @Autowired
  private GrouponUserRecordMapper grouponUserRecordMapper;

  @Override
  public int add(GrouponUserRecordPojo grouponUserRecord) throws SQLException {
    if (null == grouponUserRecord) {
      return 0;
    }
    int rows = grouponUserRecordMapper.insert(grouponUserRecord);
    return rows;
  }

  @Override
  public int update(GrouponUserRecordPojo grouponUserRecord) throws SQLException {
    if (null == grouponUserRecord) {
      return 0;
    }
    int rows = grouponUserRecordMapper.update(grouponUserRecord);
    return rows;
  }

  @Override
  public int openWinHandle(GrouponUserRecordPojo grouponUserRecord) throws SQLException {
    if (null == grouponUserRecord) {
      return 0;
    }
    int rows = grouponUserRecordMapper.openWinHandle(grouponUserRecord);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = grouponUserRecordMapper.deleteById(id);
    return rows;
  }

  @Override
  public GrouponUserRecordPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    GrouponUserRecordPojo grouponUserRecord = grouponUserRecordMapper.getById(id);
    return grouponUserRecord;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = grouponUserRecordMapper.countBy(params);
    return rows;
  }

  @Override
  public List<GrouponUserRecordPojo> listPage(Map<String, Object> params) throws SQLException {
    List<GrouponUserRecordPojo> lists = grouponUserRecordMapper.listPage(params);
    return lists;
  }

  @Override
  public GrouponUserRecordPojo findByParams(Map<String, Object> params) throws SQLException {
    return grouponUserRecordMapper.findByParams(params);
  }

  @Override
  public int updateIsRecCoupon(Map<String, Object> params) throws SQLException {
    return grouponUserRecordMapper.updateIsRecCoupon(params);
  }

  @Override
  public Integer countBy2(Map<String, Object> params) throws SQLException {
    return grouponUserRecordMapper.countBy2(params);
  }

  @Override
  public List<GrouponUserRecordPojo> listPage2(Map<String, Object> params) throws SQLException {
    return grouponUserRecordMapper.listPage2(params);
  }

  @Override
  public Integer countBy3(Map<String, Object> params) throws SQLException {
    return grouponUserRecordMapper.countBy3(params);
  }

  @Override
  public List<GrouponUserRecordPojo> listPage3(Map<String, Object> params) throws SQLException {
    return grouponUserRecordMapper.listPage3(params);
  }

  @Override
  public void updateIsPrize(Map<String, Object> params) throws SQLException {
    grouponUserRecordMapper.updateIsPrize(params);
  }


  @Override
  public List<GrouponExcelPojo> listPage4(Map<String, Object> params) {
    return grouponUserRecordMapper.listPage4(params);
  }

  @Override
  public List<GrouponUserRecordPojo> findAttendOrders(Map<String, Object> params)
      throws SQLException {
    return grouponUserRecordMapper.findAttendOrders(params);
  }
}
