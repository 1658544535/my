package com.tzmb2c.utils;


import org.apache.log4j.Logger;

import com.tzmb2c.business.service.WalletService;

public class OrderTask {
  private static final Logger logger = Logger.getLogger(OrderTask.class);
  private final String url =
      "http://weixinm2c.taozhuma.com/model/ClickFarm/admin/index.php?act=add";

  public void createOrderJob() {
    try {
      WalletService.visitUrl(url);
      System.out.println(">>>>" + System.currentTimeMillis());
    } catch (Exception e) {
      logger.error(e);
    }
  }
}
