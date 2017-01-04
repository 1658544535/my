package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ConsumerPojo;

public interface ConsumerMapper {


  public List<ConsumerPojo> getConsumerAll() throws SQLException;

  public void updateConsumer(ConsumerPojo consumerPojo) throws SQLException;

  public ConsumerPojo getfindByIdConsumer(Long id) throws SQLException;

  public ConsumerPojo getfindByUserIdConsumer(Long id) throws SQLException;

  public int consumerAllCount(Map<String, Object> map);

  public List<ConsumerPojo> consumerAllList(Map<String, Object> map);

  void checkConsumer(Long id) throws SQLException;

  void delCart(Long id) throws SQLException;

  void deleteConsumer(Long id) throws SQLException;

  public void insertConsumer(ConsumerPojo consumerPojo) throws SQLException;

  // 前台
  public void updateConsumerWeb(ConsumerPojo consumerPojo) throws SQLException;

}
