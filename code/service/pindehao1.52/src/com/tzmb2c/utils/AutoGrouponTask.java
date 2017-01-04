package com.tzmb2c.utils;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.util.UtilDate;
import com.tzmb2c.business.service.GrouponService;
import com.tzmb2c.web.pojo.GrouponActivityRecordPojo;
import com.tzmb2c.web.pojo.GrouponUserRecordPojo;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.GrouponActivityRecordService;
import com.tzmb2c.web.service.GrouponUserRecordService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.SysLoginService;

public class AutoGrouponTask {
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

  public void autoGroupon() throws SQLException {
    logger.info(">>>查询10分钟内结束的团<<<");
    long starttime = System.currentTimeMillis();

    Map<String, Object> params = new HashMap<String, Object>();
    params.put("status", 0);// 活动进行中
    params.put("activityType", 1);// 普通拼团
    // Date overTime = new Date(new Date().getTime() + 600000);
    // params.put("overTime", StringUtil.dateString(overTime));// 10分钟内结束
    params.put("second", 600);// 10分钟内结束
    Date overTime = new Date();
    params.put("overTime2", StringUtil.dateString(overTime));// 10分钟内结束
    List<GrouponActivityRecordPojo> grouponActivityRecordList =
        grouponActivityRecordService.listPage(params);
    if (grouponActivityRecordList != null && grouponActivityRecordList.size() > 0) {
      for (GrouponActivityRecordPojo grouponActivityRecordPojo : grouponActivityRecordList) {
        // 差1人成团
        int num = grouponUserRecordService.joinNumByAttendId(grouponActivityRecordPojo.getId());
        if (grouponActivityRecordPojo.getNum() - num == 1) {
          logger.info(">>>10分钟内结束的团：差1人成团，团ID-" + grouponActivityRecordPojo.getId() + "，自动拼团<<<");
          logger.info(">>>10分钟内结束的团：系统自动参团，添加用户团记录和订单记录<<<");
          try {
            params.clear();
            params.put("createBy", -1);// 导入数据
            params.put("orderBy", " rand() limit 1");
            List<SysLoginPojo> sysLoginPojos = sysLoginService.listPage(params);
            SysLoginPojo sysLoginPojo = null;
            if (sysLoginPojos != null && sysLoginPojos.size() > 0) {
              sysLoginPojo = sysLoginPojos.get(0);
            }
            if (sysLoginPojo != null) {
              // 添加用户团记录
              GrouponUserRecordPojo grouponUserRecordPojo = new GrouponUserRecordPojo();
              grouponUserRecordPojo.setActivityId(grouponActivityRecordPojo.getActivityId());
              grouponUserRecordPojo.setAttendId(grouponActivityRecordPojo.getId());
              grouponUserRecordPojo.setActivityType(1);
              grouponUserRecordPojo.setUserId(sysLoginPojo.getId());
              grouponUserRecordPojo.setStatus(0);
              grouponUserRecordPojo.setIsHead(0);
              grouponUserRecordPojo.setCreateBy(-1l);// -1
              grouponUserRecordPojo.setUpdateBy(-1l);// -1
              grouponUserRecordService.add(grouponUserRecordPojo);

              // 添加订单记录
              OrderPojo order = new OrderPojo();
              order.setUserId(sysLoginPojo.getId());
              order.setSuserId(0l);// 0?
              order.setAllNum("1");
              order.setAllPrice(grouponActivityRecordPojo.getGroupPrice());
              order.setFactPrice(grouponActivityRecordPojo.getGroupPrice());
              order.setEspressPrice(0.00);
              order.setConsignee(sysLoginPojo.getId().toString());
              order.setConsigneeAddress("广东省汕头市澄海区-测试用户数据");// 收货地址?
              order.setConsigneePhone(sysLoginPojo.getLoginname().toString());
              order.setConsigneeType(1);
              order.setOrderStatus(2);// 待发货
              order.setPayStatus(1);// 支付成功
              order.setIsDeleteOrder(0);
              order.setIsCancelOrder(0);
              order.setChannel(0);
              order.setCreateBy(-1l);// -1
              order.setCreateDate(new Date());
              String orderNo = new Date().getTime() + RandomUtils.runVerifyCode(6);
              order.setOrderNo(orderNo);
              order.setPayMethod(1);
              order.setIp("127.0.0.1");// 下单ip
              order.setBuyerMessage("");
              String out_trade_no = UtilDate.getOrderNum() + UtilDate.getThree();
              order.setOutTradeNo(out_trade_no);
              order.setProvince("20");// 省份?
              order.setCity("237");// 城市?
              order.setArea("2227");// 区域?
              order.setActivityId(grouponActivityRecordPojo.getActivityId());
              order.setSource(1);
              order.setSourceId(grouponActivityRecordPojo.getId());
              order.setGroupNum(grouponActivityRecordPojo.getNum());
              order.setOrderType(1);// 导入数据
              orderService.insertOrder(order);

              // 添加订单详情记录
              OrderDetailPojo orderDetail = new OrderDetailPojo();
              orderDetail.setOrderId(order.getId());
              orderDetail.setUserId(sysLoginPojo.getId());
              orderDetail.setLoginname(sysLoginPojo.getLoginname());
              orderDetail.setShopId(0l);// 0?
              orderDetail.setProductId(grouponActivityRecordPojo.getProductId());
              orderDetail.setProductName(grouponActivityRecordPojo.getProductName());
              orderDetail.setProductImage(grouponActivityRecordPojo.getProductImage());
              orderDetail.setWeight("");
              orderDetail.setStockId(0l);
              orderDetail.setStockPriceOld(grouponActivityRecordPojo.getGroupPrice());// 原价?
              orderDetail.setStockPrice(grouponActivityRecordPojo.getGroupPrice());
              orderDetail.setNum(1);
              orderDetail.setPostageType(1);
              orderDetail.setChannel(0);
              orderDetail.setStatus(1);
              // orderDetail.setSkuLinkId(0);// skuId?
              orderDetail.setCreateBy(-1l);// -1
              orderDetail.setCreateDate(new Date());
              orderDetail.setType(1);
              orderDetail.setActivityId(grouponActivityRecordPojo.getActivityId());
              orderDetail.setSource(1);
              orderDetail.setSourceId(grouponActivityRecordPojo.getId());
              orderDetail.setReStatus(0l);
              orderDetailService.insertOrderDetail(orderDetail);

              logger.info(">>>10分钟内结束的团：记录已插入，修改订单状态和团状态<<<");
              // 修改订单状态
              params.clear();
              params.put("isSuccess", 0);
              params.put("orderStatus", 2);
              params.put("sourceId", grouponActivityRecordPojo.getId());
              List<OrderPojo> orderList = orderService.listPage(params);
              if (orderList != null && orderList.size() > 0) {
                OrderPojo orderPojo = null;
                for (OrderPojo o : orderList) {
                  orderPojo = new OrderPojo();
                  orderPojo.setIsSuccess(1);
                  orderPojo.setGroupDate(new Date());
                  orderPojo.setId(o.getId());
                  orderService.updateOrder(orderPojo);
                }
              }

              // 修改订单状态和团状态
              GrouponActivityRecordPojo grouponActivityRecord = new GrouponActivityRecordPojo();
              grouponActivityRecord.setStatus(1);
              grouponActivityRecord.setUpdateBy(-1l);// -1
              grouponActivityRecord.setUpdateDate(new Date());
              grouponActivityRecord.setId(grouponActivityRecordPojo.getId());
              grouponActivityRecordService.update(grouponActivityRecord);

              // 微信拼团成功通知
              grouponService.wxNotice(5, order.getId(), null, null);
            } else {
              logger.info(">>>查询不到测试用户数据<<<");
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        } else {
          logger.info(">>>10分钟内结束的团：差" + (grouponActivityRecordPojo.getNum() - num) + "人成团，团ID-"
              + grouponActivityRecordPojo.getId() + "，人数大于1人，忽略<<<");
        }
      }
    } else {
      logger.info(">>>查询不到10分钟内结束的团<<<");
    }

    long spendtime = System.currentTimeMillis() - starttime;
    logger.info(">>>花费时间：" + spendtime + "ms<<<");
  }
}
