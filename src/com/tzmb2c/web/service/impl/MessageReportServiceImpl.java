package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.MessageReportDao;
import com.tzmb2c.web.pojo.MessageReportPojo;
import com.tzmb2c.web.service.MessageReportService;

public class MessageReportServiceImpl implements MessageReportService {
  @Autowired
  private MessageReportDao messageReportDao;

  public void setMessageReportDao(MessageReportDao messageReportDao) {
    this.messageReportDao = messageReportDao;
  }


  @Override
  public List<MessageReportPojo> messageReportAllService() throws SQLException {
    return messageReportDao.getMessageReportAll();
  }

  @Override
  public void insertMessageReport(MessageReportPojo messageReportPojo) throws SQLException {

    messageReportDao.insertMessageReport(messageReportPojo);
  }

  @Override
  public void updateMessageReport(MessageReportPojo messageReportPojo) throws SQLException {
    messageReportDao.updateMessageReport(messageReportPojo);

  }

  @Override
  public MessageReportPojo getfindByIdMessageReport(Long id) throws SQLException {
    return messageReportDao.getfindByIdMessageReport(id);

  }

  @Override
  public MessageReportPojo getfindByMessageId(Long id) throws SQLException {
    return messageReportDao.getfindByMessageId(id);

  }

  @Override
  public void deleteMessageReport(Long id) throws SQLException {

    messageReportDao.deleteMessageReport(id);
  }

  @Override
  public int messageReportAllCount(MessageReportPojo messageReportDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    // if(messageReportDaoPojo!=null){
    // map.put("productName", messageReportDaoPojo.getProductName());
    // /* map.put("ticketType", ticketRulePojo.getTicketType());*/
    // }
    return messageReportDao.messageReportAllCount(map);
  }

  @Override
  public List<MessageReportPojo> messageReportAllList(MessageReportPojo ticketRulePojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (ticketRulePojo.getType() > -1) {
      map.put("type", ticketRulePojo.getType());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<MessageReportPojo> list = messageReportDao.messageReportAllList(map);

    return list;
  }

  @Override
  public void messageReportDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        messageReportDao.delMessageReport(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void delMessageReport(Long id) throws SQLException {
    messageReportDao.delMessageReport(id);
  }

  @Override
  public MessageReportPojo findMessageReportById(Long id) throws SQLException {

    return messageReportDao.findMessageReportById(id);

  }
}
