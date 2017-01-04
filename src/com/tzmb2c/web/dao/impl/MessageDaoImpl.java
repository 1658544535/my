package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.MessageDao;
import com.tzmb2c.web.mapper.MessageMapper;
import com.tzmb2c.web.pojo.MessagePojo;

public class MessageDaoImpl implements MessageDao {

  @Autowired
  private MessageMapper messageMapper;


  @Override
  public void updateMessage(MessagePojo messagePojo) throws SQLException {

    messageMapper.updateMessage(messagePojo);
  }

  @Override
  public MessagePojo getfindByIdMessage(Long id) throws SQLException {
    return messageMapper.getfindByIdMessage(id);

  }

  @Override
  public void checkMessage(Long id) throws SQLException {

    messageMapper.checkMessage(id);
  }

  @Override
  public int messageAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return messageMapper.messageAllCount(map);
  }

  @Override
  public List<MessagePojo> messageAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return messageMapper.messageAllList(map);
  }

  @Override
  public void delMessage(Long id) throws SQLException {

    messageMapper.delMessage(id);
  }

  @Override
  public MessagePojo findMessageById(Long id) throws SQLException {
    return messageMapper.getfindByIdMessage(id);
  }
}
