package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.MessageReportPojo;

public interface MessageReportService {

  public List<MessageReportPojo> messageReportAllService() throws SQLException;

  public void insertMessageReport(MessageReportPojo messageReportPojo) throws SQLException;

  public void updateMessageReport(MessageReportPojo messageReportPojo) throws SQLException;

  public MessageReportPojo getfindByIdMessageReport(Long id) throws SQLException;

  public void deleteMessageReport(Long messageReportId) throws SQLException;

  public int messageReportAllCount(MessageReportPojo messageReportDaoPojo);

  List<MessageReportPojo> messageReportAllList(MessageReportPojo ticketRulePojo, Pager page);

  void messageReportDeleteId(String[] tids);

  void delMessageReport(Long id) throws SQLException;

  MessageReportPojo findMessageReportById(Long merId) throws SQLException;

  MessageReportPojo getfindByMessageId(Long id) throws SQLException;
}
