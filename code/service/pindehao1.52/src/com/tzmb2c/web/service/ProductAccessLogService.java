package com.tzmb2c.web.service;

import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.ProductAccessLogPojo;

public interface ProductAccessLogService {


  public int productAccessLogAllCount(ProductAccessLogPojo productAccessLogDaoPojo,
      String beganday, String endday);

  List<ProductAccessLogPojo> productAccessLogAllList(ProductAccessLogPojo ticketRulePojo,
      Pager page, String beganday, String endday);

  public Object productAccessLogAllListHot(ProductAccessLogPojo productAccessLog, Pager page,
      String os, String beganday, String endday);



}
