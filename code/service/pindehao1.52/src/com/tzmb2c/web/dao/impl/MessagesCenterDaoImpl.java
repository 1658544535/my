package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.MessagesCenterDao;
import com.tzmb2c.web.mapper.MessagesCenterMapper;
import com.tzmb2c.web.pojo.MessagesCenterPojo;

public class MessagesCenterDaoImpl implements MessagesCenterDao {
  @Autowired
  private MessagesCenterMapper messagesCenterMapper;

  @Override
  public List<MessagesCenterPojo> findMessagesCenterList(Map<String, Object> map)
      throws SQLException {
    return messagesCenterMapper.findMessagesCenterList(map);
  }

  @Override
  public int findMessagesCenterCount(Map<String, Object> map) throws SQLException {
    return messagesCenterMapper.findMessagesCenterCount(map);
  }

  @Override
  public void insertMessagesCenter(MessagesCenterPojo messagesCenterPojo) throws SQLException {
    messagesCenterMapper.insertMessagesCenter(messagesCenterPojo);
  }

  @Override
  public void delMessagesCenter(Long id) throws SQLException {
    messagesCenterMapper.delMessagesCenter(id);
  }

  @Override
  public MessagesCenterPojo findMessagesCenterById(Long id) throws SQLException {
    return messagesCenterMapper.findMessagesCenterById(id);
  }

  @Override
  public void updateMessagesCenter(MessagesCenterPojo messagesCenterPojo) throws SQLException {
    messagesCenterMapper.updateMessagesCenter(messagesCenterPojo);
  }

  @Override
  public void checkMessagesCenter(Long id) throws SQLException {
    messagesCenterMapper.checkMessagesCenter(id);
  }

  @Override
  public void uncheckMessagesCenter(Long id) throws SQLException {
    messagesCenterMapper.uncheckMessagesCenter(id);
  }

  @Override
  public void delMessagesCenterWeb(Long id) throws SQLException {
    messagesCenterMapper.delMessagesCenterWeb(id);
  }

  @Override
  public void readMessagesCenterWeb(Long id) throws SQLException {
    messagesCenterMapper.readMessagesCenterWeb(id);
  }
}
