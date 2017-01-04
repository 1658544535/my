/*
 * 文 件 名: GrouponUserRecordMapper.java 创 建 人: admin 创建时间: 2016-09-22
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.GrouponExcelPojo;
import com.tzmb2c.web.pojo.GrouponUserRecordPojo;

public interface GrouponUserRecordMapper {

  GrouponUserRecordPojo getById(Long id) throws SQLException;

  int countBy(Map<String, Object> params) throws SQLException;

  int countBy2(Map<String, Object> params) throws SQLException;

  int countBy3(Map<String, Object> params) throws SQLException;

  List<GrouponUserRecordPojo> listPage(Map<String, Object> params) throws SQLException;

  List<GrouponUserRecordPojo> listPage2(Map<String, Object> params) throws SQLException;

  List<GrouponUserRecordPojo> listPage3(Map<String, Object> params) throws SQLException;

  int insert(GrouponUserRecordPojo grouponUserRecord) throws SQLException;

  int update(GrouponUserRecordPojo grouponUserRecord) throws SQLException;

  int openWinHandle(GrouponUserRecordPojo grouponUserRecord) throws SQLException;

  int deleteById(Long id) throws SQLException;

  public GrouponUserRecordPojo findByParams(Map<String, Object> params) throws SQLException;

  public int updateIsRecCoupon(Map<String, Object> params) throws SQLException;

  public void updateIsPrize(Map<String, Object> params) throws SQLException;

  List<GrouponExcelPojo> listPage4(Map<String, Object> params);

  List<GrouponUserRecordPojo> findAttendOrders(Map<String, Object> params);

}
