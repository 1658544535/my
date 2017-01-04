package com.tzmb2c.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.ProductAccessLogDao;
import com.tzmb2c.web.pojo.ProductAccessLogPojo;
import com.tzmb2c.web.service.ProductAccessLogService;

public class ProductAccessLogServiceImpl implements ProductAccessLogService {
  @Autowired
  private ProductAccessLogDao productAccessLogDao;

  public void setProductAccessLogDao(ProductAccessLogDao productAccessLogDao) {
    this.productAccessLogDao = productAccessLogDao;
  }

  @Override
  public int productAccessLogAllCount(ProductAccessLogPojo productAccessLogDaoPojo,
      String beganday, String endday) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (productAccessLogDaoPojo != null) {
      map.put("productName", productAccessLogDaoPojo.getProductName());
      map.put("userName", productAccessLogDaoPojo.getUserName());
    }
    if (beganday != null && !beganday.equals("")) {
      map.put("beganday", beganday);
    }
    if (endday != null && !endday.equals("")) {
      map.put("endday", endday);
    }

    return productAccessLogDao.productAccessLogAllCount(map);
  }

  @Override
  public List<ProductAccessLogPojo> productAccessLogAllList(ProductAccessLogPojo ticketRulePojo,
      Pager page, String beganday, String endday) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (ticketRulePojo != null) {
      map.put("productName", ticketRulePojo.getProductName());
      map.put("userName", ticketRulePojo.getUserName());
    }
    if (beganday != null && !beganday.equals("")) {
      map.put("beganday", beganday);
    }
    if (endday != null && !endday.equals("")) {
      map.put("endday", endday);
    }

    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<ProductAccessLogPojo> list = productAccessLogDao.productAccessLogAllList(map);

    return list;
  }


  // /////////////////////////////////////分割线/////////////////////////////////////////
  //

  @Override
  public List<ProductAccessLogPojo> productAccessLogAllListHot(ProductAccessLogPojo ticketRulePojo,
      Pager page, String os, String beganday, String endday) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (ticketRulePojo != null) {
      map.put("productName", ticketRulePojo.getProductName());
      map.put("userName", ticketRulePojo.getUserName());
    }
    if (beganday != null && !beganday.equals("")) {
      map.put("beganday", beganday);
    }
    if (endday != null && !endday.equals("")) {
      map.put("endday", endday);
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    if (os != null & !os.equals("")) {
      map.put("os", Integer.parseInt(os));
    }

    List<ProductAccessLogPojo> list = productAccessLogDao.productAccessLogAllListHot(map);

    return list;
  }
}
