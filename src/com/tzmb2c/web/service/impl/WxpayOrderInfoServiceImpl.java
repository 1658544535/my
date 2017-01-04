package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.WxpayOrderInfoDao;
import com.tzmb2c.web.pojo.WxpayOrderInfoPojo;
import com.tzmb2c.web.service.WxpayOrderInfoService;

public class WxpayOrderInfoServiceImpl implements WxpayOrderInfoService {

  @Autowired
  private WxpayOrderInfoDao wxpayOrderInfoDao;

  @Override
  public void insertPay(WxpayOrderInfoPojo wxpayOrderInfoPojo) throws SQLException {
    wxpayOrderInfoDao.insertPay(wxpayOrderInfoPojo);

  }

  @Override
  public void updatePay(WxpayOrderInfoPojo wxpayOrderInfoPojo) throws SQLException {
    wxpayOrderInfoDao.updatePay(wxpayOrderInfoPojo);

  }

  @Override
  public WxpayOrderInfoPojo findPayInfoByTradeNo(String outTradeNo) throws SQLException {
    return wxpayOrderInfoDao.findPayInfoByTradeNo(outTradeNo);
  }

  @Override
  public int updatePayRefund(Map<String, Object> param) throws SQLException {
    return wxpayOrderInfoDao.updatePayRefund(param);
  }

  @Override
  public int countBy(Map<String, Object> params) throws SQLException {
    return wxpayOrderInfoDao.countBy(params);
  }

  @Override
  public List<WxpayOrderInfoPojo> listPage(Map<String, Object> params) throws SQLException {
    return wxpayOrderInfoDao.listPage(params);
  }

}
