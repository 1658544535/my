package com.tzmb2c.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.GrouponService;
import com.tzmb2c.web.pojo.GrouponActivityPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.UserDealLogPojo;
import com.tzmb2c.web.pojo.UserPindekeInfoPojo;
import com.tzmb2c.web.service.GrouponActivityRecordService;
import com.tzmb2c.web.service.GrouponActivityService;
import com.tzmb2c.web.service.GrouponUserRecordService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.UserDealLogService;
import com.tzmb2c.web.service.UserPindekeInfoService;

/**
 * 自动收货定时任务
 * 
 * @author Administrator
 * 
 */
public class autoRecTask {
  private static final Logger logger = Logger.getLogger(autoRecTask.class);
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
  private UserPindekeInfoService userPindekeInfoService;
  @Autowired
  private UserDealLogService userDealLogService;

  public void autoRecJob() throws Exception {
    long starttime = System.currentTimeMillis();
    Date nowDate = new Date();
    // 查询已发货且当前时间大于等于自动确认收货时间的订单
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("isAutoRec", 1);
    params.put("nowDate", nowDate);
    params.put("orderStatus", 3);
    params.put("orderBy", "id asc");
    params.put("pageSize", 20);
    params.put("pageNo", 0);
    List<OrderPojo> orderList = orderService.onlyOrderTabList(params);
    if (orderList != null && orderList.size() > 0) {
      OrderPojo orderUp = null;
      for (OrderPojo orderPojo : orderList) {
        orderUp = new OrderPojo();
        orderUp.setId(orderPojo.getId());
        orderUp.setOrderStatus(4);
        orderUp.setConfirmDate(nowDate);
        // 更新订单状态为已确认
        orderService.updateOrder(orderUp);
        // 判断是否拼得客订单,是的话返佣处理
        if (orderPojo != null && orderPojo.getSource() == 8 && orderPojo.getActivityId() != null
            && orderPojo.getPdkUid() != null && orderPojo.getPdkUid() > 0
            && orderPojo.getIsRebate() != null && orderPojo.getIsRebate() == 0) {
          params = new HashMap<String, Object>();
          params.put("userId", orderPojo.getUserId());
          params.put("id", orderPojo.getSourceId());
          int gar = grouponActivityRecordService.countBy(params);
          if (gar > 0) {
            logger.info("团长不用返佣!");
          } else {
            logger.info("拼得客返佣!");
            try {
              GrouponActivityPojo grouponActivity =
                  grouponActivityService.getById(orderPojo.getActivityId());
              if (grouponActivity != null && grouponActivity.getRebateRatio() > 0.0) {
                logger.info("计算返佣金额!");
                Double price = orderPojo.getRebatePrice();
                UserPindekeInfoPojo userPindekeInfo =
                    userPindekeInfoService.findByUserId(orderPojo.getPdkUid());
                if (userPindekeInfo != null) {
                  logger.info("减去拼得客冻结金额且添加拼得客剩余金额!");
                  UserPindekeInfoPojo userPindeke = new UserPindekeInfoPojo();
                  userPindeke.setId(userPindekeInfo.getId());
                  userPindeke.setFreezingPriceMinus(price);// 冻结金额
                  userPindeke.setSurpluPriceAdd(price);// 余额
                  userPindeke.setRebatePriceAdd(price);// 总返佣金额
                  int upi = userPindekeInfoService.update(userPindeke);
                  if (upi > 0) {
                    logger.info("后台修改拼得客金额成功!");
                  }
                  logger.info("修改订单返佣信息!");
                  OrderPojo pdkOrderUp = new OrderPojo();
                  pdkOrderUp.setId(orderPojo.getId());
                  // pdkOrderUp.setRebatePrice(price);
                  pdkOrderUp.setRebateTime(nowDate);
                  pdkOrderUp.setIsRebate(1);
                  int uo = orderService.updateOrder(pdkOrderUp);
                  if (uo > 0) {
                    logger.info("修改订单返佣信息成功!");
                  }
                  logger.info("插入交易记录表!");
                  UserDealLogPojo userDealLog = new UserDealLogPojo();
                  userDealLog.setDealType(1);
                  userDealLog.setDealDate(nowDate);
                  userDealLog.setDealAmount(price);
                  userDealLog.setUserId(userPindekeInfo.getUserId());
                  userDealLog.setStatus(0);
                  userDealLog.setGroupId(orderPojo.getSourceId());
                  userDealLog.setRemark(1);
                  userDealLog.setSurplusPrice(userPindekeInfo.getSurpluPrice() == null ? 0.0
                      : userPindekeInfo.getSurpluPrice() + price);
                  userDealLog.setCreateBy(orderPojo.getUserId());
                  userDealLog.setCreateDate(nowDate);
                  userDealLog.setUpdateBy(orderPojo.getUserId());
                  userDealLog.setUpdateDate(nowDate);
                  int udl = userDealLogService.add(userDealLog);
                  if (udl > 0) {
                    logger.info("插入交易记录表成功!");
                  }
                }
              }
            } catch (Exception e) {
              logger.info("拼得客返佣出现异常!");
              e.printStackTrace();
            }
          }
        }
      }
    } else {

    }
    long spendtime = System.currentTimeMillis() - starttime;
    logger.info("自动确认订单花费时间：" + spendtime + "ms");
  }
}
