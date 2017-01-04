package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.WxpayOrderInfoDao;
import com.tzmb2c.web.mapper.WxpayOrderInfoMapper;
import com.tzmb2c.web.pojo.WxpayOrderInfoPojo;

public class WxpayOrderInfoDaoImpl implements WxpayOrderInfoDao {

  @Autowired
  private WxpayOrderInfoMapper wxpayOrderInfoMapper;

  @Override
  public void insertPay(WxpayOrderInfoPojo wxpayOrderInfoPojo) throws SQLException {
    wxpayOrderInfoMapper.insertPay(wxpayOrderInfoPojo);

  }

  @Override
  public void updatePay(WxpayOrderInfoPojo wxpayOrderInfoPojo) throws SQLException {
    wxpayOrderInfoMapper.updatePay(wxpayOrderInfoPojo);

  }

  @Override
  public WxpayOrderInfoPojo findPayInfoByTradeNo(String outTradeNo) throws SQLException {
    return wxpayOrderInfoMapper.findPayInfoByTradeNo(outTradeNo);
  }

  @Override
  public int updatePayRefund(Map<String, Object> param) throws SQLException {
    return wxpayOrderInfoMapper.updatePayRefund(param);
  }

  @Override
  public int countBy(Map<String, Object> params) throws SQLException {
    return wxpayOrderInfoMapper.countBy(params);
  }

  @Override
  public List<WxpayOrderInfoPojo> listPage(Map<String, Object> params) throws SQLException {
    return wxpayOrderInfoMapper.listPage(params);
  }

}
