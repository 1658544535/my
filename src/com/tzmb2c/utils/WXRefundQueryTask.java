package com.tzmb2c.utils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.web.pojo.WxpayOrderInfoPojo;
import com.tzmb2c.web.service.WxpayOrderInfoService;

public class WXRefundQueryTask {
  private static final Logger logger = Logger.getLogger(WXRefundQueryTask.class);
  @Autowired
  private WxpayOrderInfoService wxpayOrderInfoService;
  @Autowired
  private SellerService sellerService;

  public void query() {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("refundStatus", "PROCESSING");
    params.put("tradeStatus", "TRADE_SUCCESS");
    List<WxpayOrderInfoPojo> wxpays = null;
    int size = 0;
    try {
      wxpays = wxpayOrderInfoService.listPage(params);
    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e.getMessage());
    }
    size = wxpays == null ? 0 : wxpays.size();
    logger.info("待退款查询笔数：" + size);
    if (size > 0) {
      long costTimeStart = System.currentTimeMillis();
      for (WxpayOrderInfoPojo wxpay : wxpays) {
        try {
          sellerService.buildWxpayRefundQuery(null, null, wxpay.getOutRefundNo(), null,
              wxpay.getTradeType());
        } catch (Exception e) {
          e.printStackTrace();
          logger.error(e.getMessage());
        }
      }
      long costTimeEnd = System.currentTimeMillis();
      logger.info("总耗时：" + (costTimeEnd - costTimeStart) + "ms");
    }
  }
}
