package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ConsumerDao;
import com.tzmb2c.web.mapper.ConsumerMapper;
import com.tzmb2c.web.pojo.ConsumerPojo;

public class ConsumerDaoImpl implements ConsumerDao {

  @Autowired
  private ConsumerMapper consumerMapper;

  @Override
  public void insertConsumer(ConsumerPojo consumerPojo) throws SQLException {
    try {
      consumerMapper.insertConsumer(consumerPojo);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public List<ConsumerPojo> getConsumerAll() throws SQLException {
    return consumerMapper.getConsumerAll();
  }

  @Override
  public void updateConsumer(ConsumerPojo consumerPojo) throws SQLException {

    consumerMapper.updateConsumer(consumerPojo);
  }

  @Override
  public ConsumerPojo getfindByIdConsumer(Long id) throws SQLException {
    return consumerMapper.getfindByIdConsumer(id);

  }

  @Override
  public int consumerAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return consumerMapper.consumerAllCount(map);
  }

  @Override
  public List<ConsumerPojo> consumerAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return consumerMapper.consumerAllList(map);
  }

  @Override
  public void checkConsumer(Long id) throws SQLException {

    consumerMapper.checkConsumer(id);
  }

  @Override
  public void delConsumer(Long id) throws SQLException {

    consumerMapper.delCart(id);
  }

  @Override
  public void deleteConsumer(Long id) throws SQLException {

    consumerMapper.deleteConsumer(id);
  }

  @Override
  public ConsumerPojo findConsumerById(Long id) throws SQLException {
    return consumerMapper.getfindByIdConsumer(id);
  }

  @Override
  public ConsumerPojo findConsumerByUserId(Long id) throws SQLException {
    return consumerMapper.getfindByUserIdConsumer(id);
  }

  @Override
  public void updateConsumerWeb(ConsumerPojo consumerPojo) throws SQLException {
    // TODO Auto-generated method stub
    consumerMapper.updateConsumerWeb(consumerPojo);
  }
}
