package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.MessageDao;
import com.tzmb2c.web.pojo.MessagePojo;
import com.tzmb2c.web.service.MessageService;

public class MessageServiceImpl implements MessageService {
  @Autowired
  private MessageDao messageDao;

  public void setMessageDao(MessageDao messageDao) {
    this.messageDao = messageDao;
  }

  @Override
  public void updateMessage(MessagePojo messagePojo) throws SQLException {
    messageDao.updateMessage(messagePojo);

  }

  @Override
  public MessagePojo getfindByIdMessage(Long id) throws SQLException {
    return messageDao.getfindByIdMessage(id);

  }

  @Override
  public void checkMessage(Long id) throws SQLException {
    messageDao.checkMessage(id);
  }

  @Override
  public int messageAllCount(MessagePojo messageDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (messageDaoPojo != null) {
      map.put("title", messageDaoPojo.getTitle());
      map.put("status", messageDaoPojo.getStatus());
    }
    return messageDao.messageAllCount(map);
  }

  @Override
  public List<MessagePojo> messageAllList(MessagePojo ticketRulePojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (ticketRulePojo != null) {
      map.put("title", ticketRulePojo.getTitle());
      map.put("status", ticketRulePojo.getStatus());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<MessagePojo> list = messageDao.messageAllList(map);

    return list;
  }

  @Override
  public void messageDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        messageDao.delMessage(Long.parseLong(tid));
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void delMessage(Long id) throws SQLException {
    messageDao.delMessage(id);
  }

  @Override
  public MessagePojo findMessageById(Long id) throws SQLException {

    return messageDao.findMessageById(id);

  }
}
