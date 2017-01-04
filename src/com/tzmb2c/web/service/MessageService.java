package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.MessagePojo;

public interface MessageService {


  public void updateMessage(MessagePojo messagePojo) throws SQLException;

  public MessagePojo getfindByIdMessage(Long id) throws SQLException;

  public int messageAllCount(MessagePojo messageDaoPojo);

  List<MessagePojo> messageAllList(MessagePojo ticketRulePojo, Pager page);

  void messageDeleteId(String[] tids);

  void delMessage(Long id) throws SQLException;

  void checkMessage(Long id) throws SQLException;

  MessagePojo findMessageById(Long merId) throws SQLException;
}
