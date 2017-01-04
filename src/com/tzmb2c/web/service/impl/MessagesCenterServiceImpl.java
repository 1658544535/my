package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.MessagesCenterDao;
import com.tzmb2c.web.pojo.MessagesCenterPojo;
import com.tzmb2c.web.service.MessagesCenterService;

public class MessagesCenterServiceImpl implements MessagesCenterService {
  @Autowired
  private MessagesCenterDao messagesCenterDao;

  @Override
  public List<MessagesCenterPojo> findMessagesCenterList(Map<String, Object> map)
      throws SQLException {
    return messagesCenterDao.findMessagesCenterList(map);
  }

  @Override
  public int findMessagesCenterCount(Map<String, Object> map) throws SQLException {
    return messagesCenterDao.findMessagesCenterCount(map);
  }

  @Override
  public void insertMessagesCenter(MessagesCenterPojo messagesCenterPojo) throws SQLException {
    messagesCenterDao.insertMessagesCenter(messagesCenterPojo);
  }

  @Override
  public void delMessagesCenter(Long id) throws SQLException {
    messagesCenterDao.delMessagesCenter(id);
  }

  @Override
  public MessagesCenterPojo findMessagesCenterById(Long id) throws SQLException {
    return messagesCenterDao.findMessagesCenterById(id);
  }

  @Override
  public void updateMessagesCenter(MessagesCenterPojo messagesCenterPojo) throws SQLException {
    messagesCenterDao.updateMessagesCenter(messagesCenterPojo);
  }

  @Override
  public void checkMessagesCenter(Long id) throws SQLException {
    messagesCenterDao.checkMessagesCenter(id);
  }

  @Override
  public void uncheckMessagesCenter(Long id) throws SQLException {
    messagesCenterDao.uncheckMessagesCenter(id);
  }

  @Override
  public void delMessagesCenterWeb(Long id) throws SQLException {
    messagesCenterDao.delMessagesCenterWeb(id);
  }

  @Override
  public void readMessagesCenterWeb(Long id) throws SQLException {
    messagesCenterDao.readMessagesCenterWeb(id);
  }
}
