package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.MessageReportDao;
import com.tzmb2c.web.mapper.MessageReportMapper;
import com.tzmb2c.web.pojo.MessageReportPojo;

public class MessageReportDaoImpl implements MessageReportDao {

  @Autowired
  private MessageReportMapper messageReportMapper;

  @Override
  public List<MessageReportPojo> getMessageReportAll() throws SQLException {
    return messageReportMapper.getMessageReportAll();
  }

  @Override
  public void insertMessageReport(MessageReportPojo messageReportPojo) throws SQLException {
    try {
      messageReportMapper.insertMessageReport(messageReportPojo);
    } catch (Exception e) {
      e.printStackTrace();

    }

  }

  @Override
  public void updateMessageReport(MessageReportPojo messageReportPojo) throws SQLException {

    messageReportMapper.updateMessageReport(messageReportPojo);
  }

  @Override
  public MessageReportPojo getfindByIdMessageReport(Long id) throws SQLException {
    return messageReportMapper.getfindByIdMessageReport(id);

  }

  @Override
  public MessageReportPojo getfindByMessageId(Long id) throws SQLException {
    return messageReportMapper.getfindByMessageId(id);

  }

  @Override
  public void deleteMessageReport(Long id) throws SQLException {
    messageReportMapper.deleteMessageReport(id);
  }

  @Override
  public int messageReportAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return messageReportMapper.messageReportAllCount(map);
  }

  @Override
  public List<MessageReportPojo> messageReportAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return messageReportMapper.messageReportAllList(map);
  }

  @Override
  public void delMessageReport(Long id) throws SQLException {

    messageReportMapper.delMessageReport(id);
  }

  @Override
  public MessageReportPojo findMessageReportById(Long id) throws SQLException {
    return messageReportMapper.getfindByIdMessageReport(id);
  }
}
