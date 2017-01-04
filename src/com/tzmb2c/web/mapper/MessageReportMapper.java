package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.MessageReportPojo;

public interface MessageReportMapper {


  public List<MessageReportPojo> getMessageReportAll() throws SQLException;

  public void insertMessageReport(MessageReportPojo messageReportPojo) throws SQLException;

  public void updateMessageReport(MessageReportPojo messageReportPojo) throws SQLException;

  public MessageReportPojo getfindByIdMessageReport(Long id) throws SQLException;

  public MessageReportPojo getfindByMessageId(Long id) throws SQLException;

  public void deleteMessageReport(Long id) throws SQLException;

  public int messageReportAllCount(Map<String, Object> map);

  public List<MessageReportPojo> messageReportAllList(Map<String, Object> map);

  void delMessageReport(Long id) throws SQLException;

}
