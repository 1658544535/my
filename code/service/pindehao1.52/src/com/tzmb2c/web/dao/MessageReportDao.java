package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.MessageReportPojo;

public interface MessageReportDao {

  List<MessageReportPojo> getMessageReportAll() throws SQLException;

  void insertMessageReport(MessageReportPojo messageReportPojo) throws SQLException;

  void updateMessageReport(MessageReportPojo messageReportPojo) throws SQLException;

  MessageReportPojo getfindByIdMessageReport(Long id) throws SQLException;

  void deleteMessageReport(Long id) throws SQLException;

  int messageReportAllCount(Map<String, Object> map);

  List<MessageReportPojo> messageReportAllList(Map<String, Object> map);

  void delMessageReport(Long id) throws SQLException;

  MessageReportPojo findMessageReportById(Long id) throws SQLException;

  MessageReportPojo getfindByMessageId(Long id) throws SQLException;

}
