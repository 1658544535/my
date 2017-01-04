/*
 * 文 件 名: DrawGrouponRecordMapper.java 创 建 人: admin 创建时间: 2016-09-22
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.DrawGrouponRecordPojo;

public interface DrawGrouponRecordMapper {

  DrawGrouponRecordPojo getById(Long id) throws SQLException;

  int countBy(Map<String, Object> params) throws SQLException;

  int countBy2(Map<String, Object> params) throws SQLException;
  
  int countBy3(Map<String, Object> params) throws SQLException;

  List<DrawGrouponRecordPojo> listPage(Map<String, Object> params) throws SQLException;

  List<DrawGrouponRecordPojo> listPage2(Map<String, Object> params) throws SQLException;
  
  List<DrawGrouponRecordPojo> listPage3(Map<String, Object> params) throws SQLException;

  int insert(DrawGrouponRecordPojo dramGrouponRecord) throws SQLException;

  int update(DrawGrouponRecordPojo dramGrouponRecord) throws SQLException;

  int openWinHandle(DrawGrouponRecordPojo dramGrouponRecord) throws SQLException;

  int deleteById(Long id) throws SQLException;

  public DrawGrouponRecordPojo findByParams(Map<String, Object> params) throws SQLException;

  public int updateIsRecCoupon(Map<String, Object> params) throws SQLException;
}
