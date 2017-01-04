package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.AlipayOrderInfoDao;
import com.tzmb2c.web.mapper.AlipayOrderInfoMapper;
import com.tzmb2c.web.pojo.AlipayOrderInfoPojo;

public class AlipayOrderInfoDaoImpl implements AlipayOrderInfoDao {

  @Autowired
  private AlipayOrderInfoMapper alipayOrderInfoMapper;

  @Override
  public void insertOne(AlipayOrderInfoPojo alipayOrderInfoPojo) throws SQLException {
    alipayOrderInfoMapper.insertOne(alipayOrderInfoPojo);

  }

  @Override
  public void updateOrder(AlipayOrderInfoPojo alipayOrderInfoPojo) throws SQLException {
    alipayOrderInfoMapper.updateOrder(alipayOrderInfoPojo);

  }

  @Override
  public void updatePayType(AlipayOrderInfoPojo alipayOrderInfoPojo) throws SQLException {
    alipayOrderInfoMapper.updatePayType(alipayOrderInfoPojo);

  }

  @Override
  public AlipayOrderInfoPojo findPayInfoByTradeNo(String outTradeNo) throws SQLException {
    return alipayOrderInfoMapper.findPayInfoByTradeNo(outTradeNo);
  }

  @Override
  public int updatePayRefund(Map<String, Object> param) throws SQLException {
    return alipayOrderInfoMapper.updatePayRefund(param);
  }

  @Override
  public AlipayOrderInfoPojo findOrderInfoByBatchNo(Map<String, Object> params) throws SQLException {
    return alipayOrderInfoMapper.findOrderInfoByBatchNo(params);
  }



}
