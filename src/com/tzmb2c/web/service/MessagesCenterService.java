package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.MessagesCenterPojo;

public interface MessagesCenterService {
  List<MessagesCenterPojo> findMessagesCenterList(Map<String, Object> map) throws SQLException;

  int findMessagesCenterCount(Map<String, Object> map) throws SQLException;

  void insertMessagesCenter(MessagesCenterPojo messagesCenterPojo) throws SQLException;

  void delMessagesCenter(Long id) throws SQLException;

  MessagesCenterPojo findMessagesCenterById(Long id) throws SQLException;

  void updateMessagesCenter(MessagesCenterPojo messagesCenterPojo) throws SQLException;

  void checkMessagesCenter(Long id) throws SQLException;

  void uncheckMessagesCenter(Long id) throws SQLException;

  void delMessagesCenterWeb(Long id) throws SQLException;

  void readMessagesCenterWeb(Long id) throws SQLException;
}
