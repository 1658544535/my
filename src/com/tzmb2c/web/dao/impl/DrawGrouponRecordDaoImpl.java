/*
 * 文 件 名: DrawGrouponRecordDaoImpl.java 创 建 人: admin 创建时间: 2016-09-22
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.DrawGrouponRecordDao;
import com.tzmb2c.web.mapper.DrawGrouponRecordMapper;
import com.tzmb2c.web.pojo.DrawGrouponRecordPojo;

/**
 * DrawGrouponRecord Dao层
 */
public class DrawGrouponRecordDaoImpl implements DrawGrouponRecordDao {

  @Autowired
  private DrawGrouponRecordMapper dramGrouponRecordMapper;

  @Override
  public int add(DrawGrouponRecordPojo dramGrouponRecord) throws SQLException {
    if (null == dramGrouponRecord) {
      return 0;
    }
    int rows = dramGrouponRecordMapper.insert(dramGrouponRecord);
    return rows;
  }

  @Override
  public int update(DrawGrouponRecordPojo dramGrouponRecord) throws SQLException {
    if (null == dramGrouponRecord) {
      return 0;
    }
    int rows = dramGrouponRecordMapper.update(dramGrouponRecord);
    return rows;
  }

  @Override
  public int openWinHandle(DrawGrouponRecordPojo dramGrouponRecord) throws SQLException {
    if (null == dramGrouponRecord) {
      return 0;
    }
    int rows = dramGrouponRecordMapper.openWinHandle(dramGrouponRecord);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = dramGrouponRecordMapper.deleteById(id);
    return rows;
  }

  @Override
  public DrawGrouponRecordPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    DrawGrouponRecordPojo dramGrouponRecord = dramGrouponRecordMapper.getById(id);
    return dramGrouponRecord;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = dramGrouponRecordMapper.countBy(params);
    return rows;
  }

  @Override
  public List<DrawGrouponRecordPojo> listPage(Map<String, Object> params) throws SQLException {
    List<DrawGrouponRecordPojo> lists = dramGrouponRecordMapper.listPage(params);
    return lists;
  }

  @Override
  public DrawGrouponRecordPojo findByParams(Map<String, Object> params) throws SQLException {
    return dramGrouponRecordMapper.findByParams(params);
  }

  @Override
  public int updateIsRecCoupon(Map<String, Object> params) throws SQLException {
    return dramGrouponRecordMapper.updateIsRecCoupon(params);
  }

  @Override
  public Integer countBy2(Map<String, Object> params) throws SQLException {
    // TODO Auto-generated method stub
    return dramGrouponRecordMapper.countBy2(params);
  }

  @Override
  public List<DrawGrouponRecordPojo> listPage2(Map<String, Object> params) throws SQLException {
    // TODO Auto-generated method stub
    return dramGrouponRecordMapper.listPage2(params);
  }
  
  @Override
  public Integer countBy3(Map<String, Object> params) throws SQLException {
    // TODO Auto-generated method stub
    return dramGrouponRecordMapper.countBy3(params);
  }

  @Override
  public List<DrawGrouponRecordPojo> listPage3(Map<String, Object> params) throws SQLException {
    // TODO Auto-generated method stub
    return dramGrouponRecordMapper.listPage3(params);
  }
}
