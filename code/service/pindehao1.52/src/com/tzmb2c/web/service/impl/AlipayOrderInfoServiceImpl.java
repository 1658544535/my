package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.AlipayOrderInfoDao;
import com.tzmb2c.web.pojo.AlipayOrderInfoPojo;
import com.tzmb2c.web.service.AlipayOrderInfoService;

public class AlipayOrderInfoServiceImpl implements AlipayOrderInfoService {

  @Autowired
  private AlipayOrderInfoDao alipayOrderInfoDao;


  @Override
  public void insertOne(AlipayOrderInfoPojo alipayOrderInfoPojo) throws SQLException {
    alipayOrderInfoDao.insertOne(alipayOrderInfoPojo);

  }


  @Override
  public void updateOrder(AlipayOrderInfoPojo alipayOrderInfoPojo) throws SQLException {
    alipayOrderInfoDao.updateOrder(alipayOrderInfoPojo);

  }


  @Override
  public void updatePayType(AlipayOrderInfoPojo alipayOrderInfoPojo) throws SQLException {
    alipayOrderInfoDao.updatePayType(alipayOrderInfoPojo);

  }


  @Override
  public AlipayOrderInfoPojo findPayInfoByTradeNo(String outTradeNo) throws SQLException {
    return alipayOrderInfoDao.findPayInfoByTradeNo(outTradeNo);
  }


  @Override
  public int updatePayRefund(Map<String, Object> param) throws SQLException {
    return alipayOrderInfoDao.updatePayRefund(param);
  }


  @Override
  public AlipayOrderInfoPojo findOrderInfoByBatchNo(Map<String, Object> params) throws SQLException {
    return alipayOrderInfoDao.findOrderInfoByBatchNo(params);
  }

}
