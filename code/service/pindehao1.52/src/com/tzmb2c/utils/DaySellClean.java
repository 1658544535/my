package com.tzmb2c.utils;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.GrouponService;
import com.tzmb2c.web.pojo.ProductSellPojo;
import com.tzmb2c.web.service.GrouponActivityRecordService;
import com.tzmb2c.web.service.GrouponActivityService;
import com.tzmb2c.web.service.GrouponUserRecordService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.ProductSellService;
import com.tzmb2c.web.service.UserDealLogService;
import com.tzmb2c.web.service.UserPindekeInfoService;

public class DaySellClean {
  private static final Logger logger = Logger.getLogger("DaySellClean");
  @Autowired
  private GrouponActivityService grouponActivityService;
  @Autowired
  private GrouponUserRecordService grouponUserRecordService;
  @Autowired
  private GrouponActivityRecordService grouponActivityRecordService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private GrouponService grouponService;
  @Autowired
  private UserDealLogService userDealLogService;
  @Autowired
  private UserPindekeInfoService userPindekeInfoService;
  @Autowired
  private ProductSellService productSellService;

  public void daySellClean() throws SQLException {
    try {
      ProductSellPojo productSellPojo = new ProductSellPojo();
      productSellService.updateDaySell(productSellPojo);
      logger.info("当日销售量清除成功!");
    } catch (Exception e) {
      e.printStackTrace();
      logger.info("当日销售量清除失败!");
    }
  }
}
