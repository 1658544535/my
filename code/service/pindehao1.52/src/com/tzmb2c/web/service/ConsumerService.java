package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.ConsumerPojo;

public interface ConsumerService {

  public List<ConsumerPojo> consumerAllService() throws SQLException;

  public void updateConsumer(ConsumerPojo consumerPojo) throws SQLException;

  public ConsumerPojo getfindByIdConsumer(Long id) throws SQLException;

  public int consumerAllCount(ConsumerPojo consumerDaoPojo);

  List<ConsumerPojo> consumerAllList(ConsumerPojo ticketRulePojo, Pager page);

  void consumerChecks(String[] tids);

  void checkConsumer(Long id) throws SQLException;

  ConsumerPojo findConsumerById(Long merId) throws SQLException;

  void delConsumer(Long id) throws SQLException;

  void deleteConsumer(Long id) throws SQLException;

  void insertConsumer(ConsumerPojo consumerPojo) throws SQLException;

  ConsumerPojo findConsumerByUserId(Long id) throws SQLException;

  // 前台
  public void updateConsumerWeb(ConsumerPojo consumerPojo) throws SQLException;

}
