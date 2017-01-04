package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.MessagePojo;

public interface MessageMapper {

  public void updateMessage(MessagePojo messagePojo) throws SQLException;

  public MessagePojo getfindByIdMessage(Long id) throws SQLException;

  public int messageAllCount(Map<String, Object> map);

  public List<MessagePojo> messageAllList(Map<String, Object> map);

  void delMessage(Long id) throws SQLException;

  void checkMessage(Long id) throws SQLException;

}
