package com.tzmb2c.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.GrouponService;
import com.tzmb2c.web.pojo.GrouponActivityRecordPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.service.GrouponActivityRecordService;
import com.tzmb2c.web.service.GrouponActivityService;
import com.tzmb2c.web.service.GrouponUserRecordService;
import com.tzmb2c.web.service.OrderService;

public class GrouponTask {
  private static final Logger logger = Logger.getLogger(GrouponTask.class);
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

  private List<GrouponActivityRecordPojo> grouponActivityRecordList;
  private List<OrderPojo> orderList;

  public void overdueGrouponJob() throws Exception {
    long starttime = System.currentTimeMillis();
    Map<String, Object> params = new HashMap<String, Object>();
    // 查询超过结束时间的数据
    params.put("overTime", new Date());
    params.put("status", 0);
    grouponActivityRecordList = grouponActivityRecordService.listPage(params);
    if (grouponActivityRecordList != null && grouponActivityRecordList.size() > 0) {
      for (GrouponActivityRecordPojo grouponActivityRecordPojo : grouponActivityRecordList) {
        int joinNum = grouponUserRecordService.joinNumByAttendId(grouponActivityRecordPojo.getId());
        if (joinNum < grouponActivityRecordPojo.getNum()) {
          logger.info("拼团失败!");
          // 设置团记录为拼团失败,
          grouponActivityRecordPojo.setStatus(2);
          grouponActivityRecordService.update(grouponActivityRecordPojo);
          // 将订单参团id为改团记录id的拼团状态改为拼团失败.
          params.clear();
          params.put("sourceId", grouponActivityRecordPojo.getId());
          orderList = orderService.listPage(params);
          if (orderList != null && orderList.size() > 0) {
            for (OrderPojo orderPojo : orderList) {
              orderPojo.setIsSuccess(2);
              orderService.updateOrder(orderPojo);
            }
            // 拼团失败微信通知
            if (grouponActivityRecordPojo.getActivityType() == 6
                || grouponActivityRecordPojo.getActivityType() == 1) {
              grouponService.wxNotice(6, orderList.get(0).getId(), null, orderList.get(0)
                  .getUserId());
            } else {
              grouponService.addUserOrderNotice(8, orderList.get(0).getUserId(), orderList.get(0)
                  .getId());
            }
          } else {
            logger.info("查询不到团记录" + grouponActivityRecordPojo.getId() + "的订单!");
          }
        } else {
          logger.info("" + grouponActivityRecordPojo.getId() + "拼团已成功!");
        }
      }
    } else {
      logger.info("查询不到结束的团!");
    }
    long spendtime = System.currentTimeMillis() - starttime;
    logger.info("花费时间：" + spendtime + "ms");
  }
}
