package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.MessagePojo;

public interface MessageDao {

  void updateMessage(MessagePojo messagePojo) throws SQLException;

  MessagePojo getfindByIdMessage(Long id) throws SQLException;

  int messageAllCount(Map<String, Object> map);

  List<MessagePojo> messageAllList(Map<String, Object> map);

  void delMessage(Long id) throws SQLException;

  void checkMessage(Long id) throws SQLException;

  MessagePojo findMessageById(Long id) throws SQLException;

}
