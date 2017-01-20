package com.tzmb2c.utils;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.GrouponService;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.PindekeMonthSalePojo;
import com.tzmb2c.web.service.GrouponActivityRecordService;
import com.tzmb2c.web.service.GrouponUserRecordService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.PindekeMonthSaleService;
import com.tzmb2c.web.service.SysLoginService;

public class pindekeMonthSaleTask {
  private static final Logger logger = Logger.getLogger("AutoGrouponTask");

  @Autowired
  private GrouponActivityRecordService grouponActivityRecordService;
  @Autowired
  private GrouponUserRecordService grouponUserRecordService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private OrderDetailService orderDetailService;
  @Autowired
  private GrouponService grouponService;
  @Autowired
  private PindekeMonthSaleService pindekeMonthSaleService;

  public void pindekeMonthSale() throws SQLException {
    logger.info(">>>拼得客注册30天销售额<<<");
    DecimalFormat df = new DecimalFormat("#.##");
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("status", 1);
    param.put("isPindeke", 1);
    List<OrderPojo> orderPojos = orderService.getPindekeMonthSale(param);
    if (orderPojos != null) {
      for (OrderPojo order : orderPojos) {
        PindekeMonthSalePojo pindekeMonthSalePojo = new PindekeMonthSalePojo();
        if (order.getFactPriceAll() != null) {
          Double priceAll = order.getFactPriceAll();
          pindekeMonthSalePojo.setTotal(Double.parseDouble(df.format(priceAll)));
        } else {
          pindekeMonthSalePojo.setTotal(0.0);
        }
        map.put("userId", order.getPdkUid());
        map.put("type", 2);
        List<PindekeMonthSalePojo> pindekeMonthSalePojos = pindekeMonthSaleService.listPage(map);
        // 插入或修改拼得客月销售表
        if (pindekeMonthSalePojos.size() > 0) {
          pindekeMonthSalePojo.setId(pindekeMonthSalePojos.get(0).getId());
          pindekeMonthSaleService.update(pindekeMonthSalePojo);
          logger.info(">>>修改月销售额成功<<<");
        } else {
          pindekeMonthSalePojo.setUserId(order.getPdkUid());
          pindekeMonthSalePojo.setType(2);
          // 若天数不为整数，则向上取整
          pindekeMonthSalePojo.setSectionTime("30");
          pindekeMonthSalePojo.setIsSettle(0);
          pindekeMonthSalePojo.setInviterId(order.getInviterId());
          pindekeMonthSaleService.add(pindekeMonthSalePojo);
          logger.info(">>>添加月销售额成功<<<");
        }
      }
    }
  }
}
