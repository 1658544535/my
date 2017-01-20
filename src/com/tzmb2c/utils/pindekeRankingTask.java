package com.tzmb2c.utils;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

public class pindekeRankingTask {
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

  public void pindekeRanking() throws SQLException {
    logger.info(">>>拼得客排行榜排名<<<");
    try {
      DecimalFormat df = new DecimalFormat("#.##");
      // 获取当前日期年月日
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMM");
      String date2 = sdf.format(new Date());
      // 获取当前日期减去一天的年月份
      Calendar c = Calendar.getInstance();
      c.add(Calendar.DATE, -1);
      String date1 =
          String.valueOf(c.get(Calendar.YEAR)) + "-" + String.valueOf(c.get(Calendar.MONTH) + 1)
              + "-01";
      Map<String, Object> param = new HashMap<String, Object>();
      param.put("paymentDateStart", date1);
      param.put("paymentDateEnd", date2);
      param.put("status", 1);
      param.put("isPindeke", 1);
      List<OrderPojo> orderPojos = orderService.getPindekeRanking(param);
      int i = 1;// 用来排名的
      if (orderPojos != null && orderPojos.size() > 0) {
        for (OrderPojo order : orderPojos) {
          // 把字符串转日期再把日期转为格式化字符串是为了保证月份为两位数
          String dateString =
              sdf1.format(sdf1.parse(String.valueOf(c.get(Calendar.YEAR))
                  + String.valueOf(c.get(Calendar.MONTH) + 1)));
          Map<String, Object> map = new HashMap<String, Object>();
          map.put("userId", order.getPdkUid());
          map.put("type", 1);
          map.put("sectionTime", dateString);
          List<PindekeMonthSalePojo> pindekeMonthSalePojos = pindekeMonthSaleService.listPage(map);
          PindekeMonthSalePojo pindekeMonthSalePojo = new PindekeMonthSalePojo();
          // 插入或修改拼得客销售排名表
          if (pindekeMonthSalePojos.size() > 0) {
            pindekeMonthSalePojo.setId(pindekeMonthSalePojos.get(0).getId());
            pindekeMonthSalePojo.setTotal(Double.parseDouble(df.format(order.getFactPriceAll())));
            pindekeMonthSalePojo.setRanking(i);
            pindekeMonthSaleService.update(pindekeMonthSalePojo);
            logger.info(">>>修改排名成功<<<");
          } else {
            pindekeMonthSalePojo.setUserId(order.getPdkUid());
            pindekeMonthSalePojo.setType(1);
            pindekeMonthSalePojo.setSectionTime(dateString);
            pindekeMonthSalePojo.setTotal(Double.parseDouble(df.format(order.getFactPriceAll())));
            pindekeMonthSalePojo.setIsSettle(0);
            pindekeMonthSalePojo.setRanking(i);
            pindekeMonthSalePojo.setInviterId(order.getInviterId());
            pindekeMonthSaleService.add(pindekeMonthSalePojo);
            logger.info(">>>添加排名成功<<<");
          }
          i++;
        }
      }
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      logger.info(">>>拼得客排行榜排名失败<<<");
    }
  }
}
