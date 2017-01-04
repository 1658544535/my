package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.ConsumerDao;
import com.tzmb2c.web.pojo.ConsumerPojo;
import com.tzmb2c.web.service.ConsumerService;

public class ConsumerServiceImpl implements ConsumerService {
  @Autowired
  private ConsumerDao consumerDao;

  public void setConsumerDao(ConsumerDao consumerDao) {
    this.consumerDao = consumerDao;
  }

  @Override
  public List<ConsumerPojo> consumerAllService() throws SQLException {
    return consumerDao.getConsumerAll();
  }

  @Override
  public void insertConsumer(ConsumerPojo consumerPojo) throws SQLException {
    consumerDao.insertConsumer(consumerPojo);

  }

  @Override
  public void updateConsumer(ConsumerPojo consumerPojo) throws SQLException {
    consumerDao.updateConsumer(consumerPojo);

  }

  @Override
  public ConsumerPojo getfindByIdConsumer(Long id) throws SQLException {
    return consumerDao.getfindByIdConsumer(id);

  }

  @Override
  public int consumerAllCount(ConsumerPojo consumerDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (consumerDaoPojo != null) {
      map.put("company", consumerDaoPojo.getCompany());
      map.put("address", consumerDaoPojo.getAddress());
      map.put("platform", consumerDaoPojo.getPlatform());
      map.put("mainCategory", consumerDaoPojo.getMainCategory());
      map.put("salesArea", consumerDaoPojo.getSalesArea());
      map.put("phone", consumerDaoPojo.getPhone());
      map.put("QQ", consumerDaoPojo.getQQ());
      map.put("loginname", consumerDaoPojo.getLoginname());
    }
    return consumerDao.consumerAllCount(map);
  }

  @Override
  public List<ConsumerPojo> consumerAllList(ConsumerPojo consumerDaoPojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (consumerDaoPojo != null) {
      map.put("company", consumerDaoPojo.getCompany());
      map.put("address", consumerDaoPojo.getAddress());
      map.put("platform", consumerDaoPojo.getPlatform());
      map.put("mainCategory", consumerDaoPojo.getMainCategory());
      map.put("salesArea", consumerDaoPojo.getSalesArea());
      map.put("phone", consumerDaoPojo.getPhone());
      map.put("QQ", consumerDaoPojo.getQQ());
      map.put("userId", consumerDaoPojo.getUserId());
      map.put("loginname", consumerDaoPojo.getLoginname());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<ConsumerPojo> list = consumerDao.consumerAllList(map);

    return list;
  }

  @Override
  public void consumerChecks(String[] tids) {
    for (String tid : tids) {
      try {
        consumerDao.checkConsumer(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void checkConsumer(Long id) throws SQLException {
    consumerDao.checkConsumer(id);
  }

  @Override
  public void delConsumer(Long id) throws SQLException {
    consumerDao.delConsumer(id);
  }

  @Override
  public void deleteConsumer(Long id) throws SQLException {
    consumerDao.deleteConsumer(id);
  }

  @Override
  public ConsumerPojo findConsumerById(Long id) throws SQLException {

    return consumerDao.findConsumerById(id);

  }

  @Override
  public ConsumerPojo findConsumerByUserId(Long id) throws SQLException {

    return consumerDao.findConsumerByUserId(id);

  }

  @Override
  public void updateConsumerWeb(ConsumerPojo consumerPojo) throws SQLException {
    // TODO Auto-generated method stub
    consumerDao.updateConsumerWeb(consumerPojo);
  }
}
