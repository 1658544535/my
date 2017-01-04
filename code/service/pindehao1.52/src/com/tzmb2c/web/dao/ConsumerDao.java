package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ConsumerPojo;

public interface ConsumerDao {

  List<ConsumerPojo> getConsumerAll() throws SQLException;

  void updateConsumer(ConsumerPojo consumerPojo) throws SQLException;

  ConsumerPojo getfindByIdConsumer(Long id) throws SQLException;

  int consumerAllCount(Map<String, Object> map);

  List<ConsumerPojo> consumerAllList(Map<String, Object> map);

  void checkConsumer(Long id) throws SQLException;

  void delConsumer(Long id) throws SQLException;

  void deleteConsumer(Long id) throws SQLException;

  ConsumerPojo findConsumerById(Long id) throws SQLException;

  void insertConsumer(ConsumerPojo consumerPojo) throws SQLException;

  ConsumerPojo findConsumerByUserId(Long id) throws SQLException;

  // 前台
  public void updateConsumerWeb(ConsumerPojo consumerPojo) throws SQLException;

}
