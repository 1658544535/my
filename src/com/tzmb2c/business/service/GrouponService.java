package com.tzmb2c.business.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import maowu.framework.utils.datetime.DateTimeUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tencent.common.Util;
import com.tzmb2c.utils.IdWorker;
import com.tzmb2c.utils.SmsSendUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.AliRedEnvelopePojo;
import com.tzmb2c.web.pojo.CartPojo;
import com.tzmb2c.web.pojo.CouponOrderPojo;
import com.tzmb2c.web.pojo.CouponPojo;
import com.tzmb2c.web.pojo.GroupFreeCouponPojo;
import com.tzmb2c.web.pojo.GroupFreeCouponSettingPojo;
import com.tzmb2c.web.pojo.GrouponActivityPojo;
import com.tzmb2c.web.pojo.GrouponActivityRecordPojo;
import com.tzmb2c.web.pojo.GrouponUserRecordPojo;
import com.tzmb2c.web.pojo.NoticeTemplatePojo;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.ResultDetailData;
import com.tzmb2c.web.pojo.SysAreaPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserCouponPojo;
import com.tzmb2c.web.pojo.UserOrderNoticePojo;
import com.tzmb2c.web.pojo.UserPindekeInfoPojo;
import com.tzmb2c.web.service.AliRedEnvelopeService;
import com.tzmb2c.web.service.CouponService;
import com.tzmb2c.web.service.GroupFreeCouponService;
import com.tzmb2c.web.service.GroupFreeCouponSettingService;
import com.tzmb2c.web.service.GrouponActivityRecordService;
import com.tzmb2c.web.service.GrouponActivityService;
import com.tzmb2c.web.service.GrouponUserRecordService;
import com.tzmb2c.web.service.NoticeTemplateService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.SeckillGoodsService;
import com.tzmb2c.web.service.SeckillService;
import com.tzmb2c.web.service.SysAreaService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserOrderNoticeService;
import com.tzmb2c.web.service.UserOrderRefundService;
import com.tzmb2c.web.service.UserPindekeInfoService;


/**
 * 团购业务处理类.
 */
public class GrouponService {
  @Autowired
  private CouponService couponService;
  @Autowired
  private GrouponActivityService grouponActivityService;
  @Autowired
  private GrouponUserRecordService grouponUserRecordService;
  @Autowired
  private GroupFreeCouponService groupFreeCouponService;
  @Autowired
  private GrouponActivityRecordService grouponActivityRecordService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private GroupFreeCouponSettingService groupFreeCouponSettingService;
  @Autowired
  private SeckillService seckillService;
  @Autowired
  private SeckillGoodsService seckillGoodsService;
  @Autowired
  private OrderDetailService orderDetailService;
  @Autowired
  private SellerService sellerService;
  @Autowired
  private ProductSkuLinkService productSkuLinkService;
  @Autowired
  private SysAreaService sysAreaService;
  @Autowired
  private UserOrderNoticeService userOrderNoticeService;
  @Autowired
  private NoticeTemplateService noticeTemplateService;
  @Autowired
  private UserOrderRefundService userOrderRefundService;
  @Autowired
  private UserPindekeInfoService userPindekeInfoService;
  @Autowired
  private ProductService productService;
  @Autowired
  private AliRedEnvelopeService aliRedEnvelopeService;

  /**
   * 根据订单金额判断能否使用优惠券
   * 
   * @param id 优惠券id
   * @param price 订单金额
   * @return
   * @throws Exception
   */
  public boolean checkCouponUsePrice(Long id, Double price) throws Exception {
    boolean flag = false;

    // 查询优惠券金额
    CouponPojo coupon = couponService.getcouponById(id);
    if (coupon != null) {
      int type = coupon.getType();
      String contentStr = coupon.getContent();
      double m = 0.0;
      org.json.JSONObject json = new org.json.JSONObject(contentStr);
      if (1 == type) {
        // 满减
        m = json.getDouble("om");
        if (price.doubleValue() >= m) {
          flag = true;
        }

      } else if (2 == type) {
        // 直减
        m = json.getDouble("m");
        if (price.doubleValue() >= m) {
          flag = true;
        }
      }
    }

    return flag;
  }

  /**
   * 
   * 支付成功参团/开团处理
   * 
   * @param activityId 活动id
   * @param attendId 团记录id
   * @param uid 用户id
   * @param source 来源(1-普通拼团2-团免3-猜价)
   * @param orderId 订单id
   * @throw Exception
   * @return boolean
   */
  public synchronized Boolean groupOrderHandle(Long activityId, Long attendId, Long uid,
      Integer source, Long orderId) throws Exception {
    Boolean flag = false;
    Map<String, Object> params = new HashMap<String, Object>();
    Util.log("判断是拼团还是猜价");
    if (source == 3 || source == 4) {

      try {
        // if (source == 4) {
        addUserOrderNotice(1, uid, orderId);
        // }
      } catch (Exception e) {
        e.printStackTrace();
      }

      OrderPojo orderPojo = new OrderPojo();
      orderPojo.setId(orderId);
      orderPojo.setIsSuccess(1);
      orderPojo.setGroupDate(new Date());
      orderPojo.setUpdateBy(uid);
      orderService.updateOrder(orderPojo);
      Util.log("修改一等奖为已获取奖励");
      if (source == 3) {
        if (uid != null && uid != 0 && activityId != null && activityId != 0) {
          params.clear();
          params.put("userId", uid);
          params.put("activityId", activityId);
          params.put("activityType", 3);
          params.put("prize", 1);
          List<GrouponUserRecordPojo> grouponUserRecordList =
              grouponUserRecordService.listPage(params);
          if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
            grouponUserRecordList.get(0).setIsRecCoupon(1);
            grouponUserRecordService.update(grouponUserRecordList.get(0));
          }
        }
      }
      return true;
    } else if (source == 1 || source == 2 || source == 5 || source == 6 || source == 7
        || source == 8) {
      GrouponActivityPojo grouponActivityPojo = grouponActivityService.getById(activityId);
      Util.log("判断是否存在团活动");
      if (grouponActivityPojo == null) {
        return false;
      }
      Util.log("判断开团还是参团");
      if (activityId != null && uid != null && (attendId == null || attendId == 0)) {
        Util.log("开团");
        Util.log("添加团记录表");
        GrouponActivityRecordPojo grouponActivityRecordPojo = new GrouponActivityRecordPojo();
        grouponActivityRecordPojo.setActivityId(activityId);
        grouponActivityRecordPojo.setActivityType(source);
        Date nowTime = new Date();
        if (source == 5 || source == 6 || source == 7 || source == 8) {
          if (source == 5) {
            Util.log("设置0.1抽奖活动时间");
            if (DateTimeUtil.getTimeChange(nowTime, grouponActivityPojo.getEndTime()) > 360L) {
              grouponActivityRecordPojo.setBeginTime(nowTime);
              grouponActivityRecordPojo.setEndTime(GrouponService.getTimeAddHour(nowTime, 6));
            } else {
              grouponActivityRecordPojo.setBeginTime(nowTime);
              grouponActivityRecordPojo.setEndTime(grouponActivityPojo.getEndTime());
            }
          } else {
            Util.log("设置限时秒杀活动时间");
            if (DateTimeUtil.getTimeChange(nowTime, grouponActivityPojo.getEndTime()) > 1440L) {
              grouponActivityRecordPojo.setBeginTime(nowTime);
              grouponActivityRecordPojo.setEndTime(GrouponService.getTimeAddHour(nowTime, 24));
            } else {
              grouponActivityRecordPojo.setBeginTime(nowTime);
              grouponActivityRecordPojo.setEndTime(grouponActivityPojo.getEndTime());
            }
          }
        } else {
          grouponActivityRecordPojo.setBeginTime(nowTime);
          grouponActivityRecordPojo.setEndTime(GrouponService.getTimeAdd24(new Date()));
        }
        grouponActivityRecordPojo.setUserId(uid);
        grouponActivityRecordPojo.setNum(grouponActivityPojo.getNum());
        grouponActivityRecordPojo.setCreateBy(uid);
        grouponActivityRecordPojo.setCreateDate(new Date());
        grouponActivityRecordPojo.setUpdateBy(uid);
        grouponActivityRecordPojo.setUpdateDate(new Date());
        if (source == 8) {
          Util.log("拼得客UserId");
          OrderPojo order = orderService.findOrderById(orderId);
          if (order != null && order.getPdkUid() != null && order.getPdkUid() > 0) {
            grouponActivityRecordPojo.setPdkUid(order.getPdkUid());
          }
        }
        grouponActivityRecordService.add(grouponActivityRecordPojo);
        Util.log("抽奖和秒杀库存判断");
        if (source == 5 || source == 6) {
          params = new HashMap<String, Object>();
          params.put("productId", grouponActivityPojo.getProductId());
          params.put("status", 1);
          int stock = productSkuLinkService.getSkuStock(params);
          if (stock < 1) {
            Util.log("库存不足!处理为拼团失败!");
            Util.log("根据sourceId订单处理为拼团失败!");
            params = new HashMap<String, Object>();
            if (grouponActivityRecordPojo.getId() != null && grouponActivityRecordPojo.getId() > 0) {
              params.put("sourceId", grouponActivityRecordPojo.getId());
              List<OrderPojo> orderList = orderService.listPage(params);
              if (orderList != null && orderList.size() > 0) {
                OrderPojo orderPojo = null;
                for (OrderPojo order : orderList) {
                  orderPojo = new OrderPojo();
                  orderPojo.setId(order.getId());
                  orderPojo.setIsSuccess(2);
                  orderService.updateOrder(orderPojo);
                }
              } else {
                Util.log("查询不到订单!");
              }
              Util.log("团记录设置为拼团失败!");
              GrouponActivityRecordPojo grouponActivityRecord = new GrouponActivityRecordPojo();
              grouponActivityRecord.setId(grouponActivityRecordPojo.getId());
              grouponActivityRecord.setStatus(2);
              grouponActivityRecordService.update(grouponActivityRecord);
              // Util.log("0.1抽奖拼团失败微信通知!");
              // oneWXNotice(6, orderId, uid);
            }
          }
          if (source == 6) {
            if (grouponActivityPojo.getSurplusNum() == null
                || grouponActivityPojo.getSurplusNum() < 1) {
              Util.log("根据sourceId订单处理为拼团失败!");
              params = new HashMap<String, Object>();
              if (grouponActivityRecordPojo.getId() != null
                  && grouponActivityRecordPojo.getId() > 0) {
                params.put("sourceId", grouponActivityRecordPojo.getId());
                List<OrderPojo> orderList = orderService.listPage(params);
                if (orderList != null && orderList.size() > 0) {
                  OrderPojo orderPojo = null;
                  for (OrderPojo order : orderList) {
                    orderPojo = new OrderPojo();
                    orderPojo.setId(order.getId());
                    orderPojo.setIsSuccess(2);
                    orderPojo.setGroupDate(new Date());
                    orderService.updateOrder(orderPojo);
                  }
                } else {
                  Util.log("查询不到订单!");
                }
                Util.log("团记录设置为拼团失败!");
                GrouponActivityRecordPojo grouponActivityRecord = new GrouponActivityRecordPojo();
                grouponActivityRecord.setId(grouponActivityRecordPojo.getId());
                grouponActivityRecord.setStatus(2);
                grouponActivityRecordService.update(grouponActivityRecord);
              }
              return false;
            }
          }
        }
        Util.log("添加用户团记录表");
        GrouponUserRecordPojo grouponUserRecordPojo = new GrouponUserRecordPojo();
        grouponUserRecordPojo.setActivityId(activityId);
        grouponUserRecordPojo.setAttendId(grouponActivityRecordPojo.getId());
        grouponUserRecordPojo.setActivityType(source);
        grouponUserRecordPojo.setUserId(uid);
        if (source == 1) {
          Util.log("普通拼团");
          grouponUserRecordPojo.setStatus(0);
        } else if (source == 2) {
          Util.log("团免");
          grouponUserRecordPojo.setStatus(1);
          Util.log("查找团免券");
          params.clear();
          params.put("userId", uid);
          List<GroupFreeCouponPojo> groupFreeCouponList = groupFreeCouponService.listPage(params);
          if (groupFreeCouponList != null && groupFreeCouponList.size() > 0) {
            GroupFreeCouponPojo groupFreeCoupon = groupFreeCouponList.get(0);
            if (groupFreeCoupon != null) {
              Util.log("插入团免券id");
              grouponUserRecordPojo.setCouponId(groupFreeCoupon.getId());
              Util.log("更新团记录的拼得客用户id");
              if (groupFreeCoupon.getInvitationUserId() != null
                  && groupFreeCoupon.getInvitationUserId() > 0) {
                grouponActivityRecordPojo
                    .setInvitationUserId(groupFreeCoupon.getInvitationUserId());
                grouponActivityRecordService.update(grouponActivityRecordPojo);
              }

            }
          } else {
            Util.log("查询不到团免券!");
          }
        } else if (source == 5) {
          Util.log("0.1抽奖!");
          grouponUserRecordPojo.setStatus(3);
        } else if (source == 6) {
          Util.log("限时秒杀!");
          grouponUserRecordPojo.setStatus(4);
        } else if (source == 7) {
          Util.log("免费抽奖!");
          grouponUserRecordPojo.setStatus(5);
        } else if (source == 8) {
          Util.log("拼得客开团!");
          grouponUserRecordPojo.setStatus(6);
        }
        grouponUserRecordPojo.setIsHead(1);
        grouponUserRecordPojo.setAttendTime(new Date());
        grouponUserRecordPojo.setCreateBy(uid);
        grouponUserRecordPojo.setCreateDate(new Date());
        grouponUserRecordPojo.setUpdateBy(uid);
        grouponUserRecordPojo.setUpdateDate(new Date());
        grouponUserRecordService.add(grouponUserRecordPojo);
        Util.log("订单记录开团记录id");
        OrderPojo orderPojo = new OrderPojo();
        orderPojo.setId(orderId);
        orderPojo.setSourceId(grouponActivityRecordPojo.getId());
        orderService.updateOrder(orderPojo);
        // 修改总开团数
        if (source == 1) {
          try {
            if (grouponActivityPojo.getProductId() != null
                && grouponActivityPojo.getProductId() > 0) {
              ProductPojo product = new ProductPojo();
              product.setId(grouponActivityPojo.getProductId());
              product.setSurplusNumMinus(1);
              productService.productUpdate(product);
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        orderPojo = orderService.getfindByIdOrder(orderId);
        if (orderPojo != null) {
          Util.log("0.1红包活动修改口令表");
          if (source == 5 && orderPojo.getInviteCode() != null
              && !"".equals(orderPojo.getInviteCode())) {
            AliRedEnvelopePojo aliRedEnvelope =
                aliRedEnvelopeService.getByInviteCode(orderPojo.getInviteCode());
            if (aliRedEnvelope != null) {
              AliRedEnvelopePojo aliRedEnvelopeUp = new AliRedEnvelopePojo();
              aliRedEnvelopeUp.setId(aliRedEnvelope.getId());
              aliRedEnvelopeUp.setAttendId(grouponActivityRecordPojo.getId());
              aliRedEnvelopeUp.setHeaderId(uid);
              aliRedEnvelopeUp.setVersions(aliRedEnvelope.getVersions());
              aliRedEnvelopeUp.setUpdateDate(new Date());
              int i = aliRedEnvelopeService.update(aliRedEnvelopeUp);
              if (i > 0) {
                Util.log("口令表修改成功!");
              }
            } else {
              Util.log("口令表查询不到数据!");
            }
          } else {
            Util.log("没有邀请码!");
          }
          // 微信通知
          SysLoginPojo user = sysLoginService.getfindByIdSysLogin(uid);
          if (user != null) {
            if (StringUtils.isNotBlank(user.getOpenid())) {
              Util.log("查询有openid的用户!");
              Util.log("微信支付成功通知!");
              wxNotice(3, orderId, user.getOpenid(), uid);
              if (orderPojo.getSource() == 5) {
                // ----------------------------------
                // Util.log("0.1抽奖开团微信通知!");
                // oneWXNotice(1, orderId, uid);
                // ----------------------------------
              } else {
                Util.log("微信开团成功通知!");
                wxNotice(4, orderId, user.getOpenid(), uid);
              }
            } else {
              Util.log("查询不到有openid的用户!");
              Util.log("微信支付成功通知!");
              addUserOrderNotice(1, uid, orderId);
              if (orderPojo.getSource() == 5) {
                // ----------------------------------
                // Util.log("0.1抽奖开团微信通知!");
                // addUserOrderNotice(2, uid, orderId);
                // ----------------------------------
              } else {
                Util.log("微信开团成功通知!");
                addUserOrderNotice(2, uid, orderId);
              }
            }
          } else {
            Util.log("查询不到用户!");
          }
          if (user != null && source == 1) {
            // 添加开团推送
            try {
              int pid =
                  Integer.parseInt(StringUtil.checkVal(orderPojo.getProvince() == null ? 0
                      : orderPojo.getProvince()));
              SysAreaPojo sysAreaPojo = sysAreaService.getNameById(pid);
              String productImage = StringUtil.checkVal(orderPojo.getProductImage());
              String address =
                  sysAreaPojo == null ? "" : StringUtil.checkVal(sysAreaPojo.getName());
              long orderIdTs = orderPojo.getId() == null ? 0 : orderPojo.getId();
              long attendIdTs =
                  grouponActivityRecordPojo.getId() == null ? 0 : grouponActivityRecordPojo.getId();
              String userName =
                  StringUtil.checkVal(user.getLoginname().substring(0, 3)) + "***"
                      + StringUtil.checkVal(user.getLoginname().substring(9));
              Util.log("添加开团推送：" + productImage + "-" + address + "-" + userName + "-orderId："
                  + orderIdTs + "-attendId：" + attendIdTs);
              sellerService.addGrouponPushApi(productImage, address, userName, orderIdTs,
                  attendIdTs);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        } else {
          Util.log("查询不到公众号支付的订单!");
        }
        if (source != 7) {
          Util.log("检查是否拼团人数是否已到");
          int againJoinNum =
              grouponUserRecordService.joinNumByAttendId(grouponActivityRecordPojo.getId());
          if (againJoinNum == grouponActivityRecordPojo.getNum()) {
            Util.log("更改订单状态和团状态");
            params.clear();
            params.put("isSuccess", 0);
            params.put("sourceId", grouponActivityRecordPojo.getId());
            List<OrderPojo> orderList = orderService.listPage(params);
            Util.log("团状态改为拼团成功且订单状态改为待发货,插入成团时间");
            if (orderList != null && orderList.size() > 0) {
              OrderPojo orderup = null;
              for (OrderPojo order : orderList) {
                orderup = new OrderPojo();
                orderup.setIsSuccess(1);
                orderup.setGroupDate(new Date());
                orderup.setId(order.getId());
                orderService.updateOrder(orderup);
              }
            } else {
              Util.log("查询不到对应拼团支付成功的数据!");
            }
            Util.log("修改团记录为组团成功");
            GrouponActivityRecordPojo grouponActivityRecord = new GrouponActivityRecordPojo();
            grouponActivityRecord.setStatus(1);
            grouponActivityRecord.setUpdateDate(new Date());
            grouponActivityRecord.setId(grouponActivityRecordPojo.getId());
            // grouponActivityRecordPojo.setActivityType(grouponActivityRecordPojo.getActivityType());
            // grouponActivityRecordPojo.setBeginTime(new Date());
            // grouponActivityRecordPojo.setBeginTime(getTimeAdd24(new Date()));
            int state = grouponActivityRecordService.update(grouponActivityRecord);
            if (state > 0) {
              // 普通拼团成团
              if (source == 1
                  && (activityId == 3643 || activityId == 3645 || activityId == 3646 || activityId == 3883)) {
                // 发放优惠券
                grantCoupon(activityId, grouponActivityRecordPojo.getId());
              }
            }
            if (source == 5 || source == 6) {
              Util.log("抽奖和秒杀减库存");
              Util.log("根据订单详情skuId减库存");
              List<OrderDetailPojo> orderDetailList = orderDetailService.getOrderDetailWeb(orderId);
              if (orderDetailList != null && orderDetailList.size() > 0) {
                CartPojo cart = new CartPojo();
                cart.setProductId(grouponActivityPojo.getProductId());
                cart.setNum(1);
                if (source == 6) {
                  cart.setActivityId(activityId);
                }
                if (orderDetailList.get(0).getSkuLinkId() != null
                    && orderDetailList.get(0).getSkuLinkId() > 0) {
                  cart.setSkuLinkId(orderDetailList.get(0).getSkuLinkId());
                  boolean stockFlag = sellerService.updateActivitySkuStock(cart);
                  if (stockFlag) {
                    Util.log("减sku库存成功!");
                  } else {
                    Util.log("减sku库存失败!");
                  }
                }
              } else {
                Util.log("查询不到订单详情!");
              }
              if (source == 5) {
                Util.log("0.1抽奖开团拼团成功直接设置为一等奖拼团成功");
                GrouponUserRecordPojo grouponUserRecord = new GrouponUserRecordPojo();
                grouponUserRecord.setId(grouponUserRecordPojo.getId());
                grouponUserRecord.setStatus(3);
                grouponUserRecord.setPrize(1);
                grouponUserRecordService.update(grouponUserRecord);
                // ---------------------------------------
                // Util.log("0.1抽奖拼团成功微信通知!");
                // oneWXNotice(3, orderId, uid);
                // Util.log("0.1抽奖拼团成功短信通知!");
                // smsOneOpenWin(activityId, grouponActivityRecordPojo.getId());
                // ---------------------------------------
              }
            }
            SysLoginPojo user = sysLoginService.getfindByIdSysLogin(uid);
            if (user != null) {
              if (StringUtils.isNotBlank(user.getOpenid())) {
                Util.log("查询有openid的用户!");
                wxNotice(5, orderId, user.getOpenid(), uid);
              } else {
                Util.log("查询不到有openid的用户!");
                // addUserOrderNotice(3, uid, orderId);
                wxNotice(5, orderId, null, uid);
              }
            } else {
              Util.log("查询不到用户!");
            }
            Util.log("激活团免券!");
            params.clear();
            params.put("attendId", grouponActivityRecordPojo.getId());
            List<GrouponUserRecordPojo> grouponUserRecordList =
                grouponUserRecordService.listPage(params);
            if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
              for (GrouponUserRecordPojo grouponUserRecord : grouponUserRecordList) {
                String f = grantFreeCoupon(grouponUserRecord.getUserId());
                Util.log("1".equals(f) == true ? "用户:" + grouponUserRecord.getUserId() + "激活团免券成功"
                    : "用户:" + grouponUserRecord.getUserId() + "激活团免券失败");
              }
            } else {
              Util.log("查询不到用户拼团记录");
            }
          } else {
            Util.log("开团检查:未成团!");
          }
        } else {

          Util.log("免费抽奖成团通知！~");

          Util.log("检查是否拼团人数是否已到");
          int againJoinNum =
              grouponUserRecordService.joinNumByAttendId(grouponActivityRecordPojo.getId());
          if (againJoinNum == grouponActivityRecordPojo.getNum() && source != 7) {

            Util.log("更改订单状态和团状态");
            params.clear();
            params.put("isSuccess", 0);
            params.put("sourceId", grouponActivityRecordPojo.getId());
            List<OrderPojo> orderList = orderService.listPage(params);
            Util.log("团状态改为拼团成功且订单状态改为待发货,插入成团时间");
            if (orderList != null && orderList.size() > 0) {
              OrderPojo orderup = null;
              for (OrderPojo order : orderList) {
                orderup = new OrderPojo();
                orderup.setIsSuccess(1);
                orderup.setGroupDate(new Date());
                orderup.setId(order.getId());
                orderService.updateOrder(orderup);
              }
            } else {
              Util.log("查询不到对应拼团支付成功的数据!");
            }

            Util.log("修改团记录为组团成功");
            GrouponActivityRecordPojo grouponActivityRecord = new GrouponActivityRecordPojo();
            grouponActivityRecord.setStatus(1);
            grouponActivityRecord.setUpdateDate(new Date());
            grouponActivityRecord.setId(grouponActivityRecordPojo.getId());
            grouponActivityRecordService.update(grouponActivityRecord);

            SysLoginPojo user = sysLoginService.getfindByIdSysLogin(uid);
            if (user != null) {
              wxNotice(5, orderId, null, uid);
            } else {
              Util.log("查询不到用户!");
            }
          } else {
            Util.log("开团检查:未成团!");
          }

        }
        return true;
      } else if (activityId != null && attendId != null && attendId != 0) {
        boolean activityFlag = true;
        Util.log("判断是否存在团记录");
        GrouponActivityRecordPojo grouponActivityRecordPojo =
            grouponActivityRecordService.getById(attendId);
        if (grouponActivityRecordPojo != null) {
          // 判断是否存在团记录->判断团是否已满->是否过期->是的话修改订单状态,
          Util.log("判断是否团活动是否到期");
          if (DateTimeUtil.compareDate(
              StringUtil.dateString(grouponActivityRecordPojo.getEndTime()),
              StringUtil.dateString(new Date()))
              && DateTimeUtil.compareDate(StringUtil.dateString(new Date()),
                  StringUtil.dateString(grouponActivityRecordPojo.getBeginTime()))) {
            Util.log("判断拼团人数是否已到");
            int joinNum = grouponUserRecordService.joinNumByAttendId(attendId);
            if (joinNum >= grouponActivityRecordPojo.getNum()) {
              System.out.println("该团已满!");
              activityFlag = false;
            } else {
              int buyNum = 1;// 成团数量
              if (source == 5) {
                buyNum = 2;
              } else {
                buyNum = grouponActivityRecordPojo.getNum();
              }
              if (source == 5 || source == 6) {
                List<OrderDetailPojo> orderDetailList =
                    orderDetailService.getOrderDetailWeb(orderId);
                if (orderDetailList == null || orderDetailList.size() <= 0) {
                  Util.log("查询不到订单详情!");
                  return false;
                } else {
                  params = new HashMap<String, Object>();
                  params.put("productId", orderDetailList.get(0).getProductId());
                  params.put("status", 1);
                  int stock = productSkuLinkService.getSkuStock(params);
                  if (stock < buyNum) {
                    Util.log("库存不足!处理为拼团失败!");
                    Util.log("根据sourceId订单处理为拼团失败!");
                    params = new HashMap<String, Object>();
                    if (grouponActivityRecordPojo.getId() != null
                        && grouponActivityRecordPojo.getId() > 0) {
                      params.put("sourceId", grouponActivityRecordPojo.getId());
                      List<OrderPojo> orderList = orderService.listPage(params);
                      if (orderList != null && orderList.size() > 0) {
                        OrderPojo orderPojo = null;
                        for (OrderPojo order : orderList) {
                          orderPojo = new OrderPojo();
                          orderPojo.setId(order.getId());
                          orderPojo.setIsSuccess(2);
                          orderService.updateOrder(orderPojo);
                        }
                      } else {
                        Util.log("查询不到订单!");
                      }
                      Util.log("团记录设置为拼团失败!");
                      GrouponActivityRecordPojo grouponActivityRecord =
                          new GrouponActivityRecordPojo();
                      grouponActivityRecord.setId(grouponActivityRecordPojo.getId());
                      grouponActivityRecord.setStatus(2);
                      grouponActivityRecordService.update(grouponActivityRecord);
                      // if (source == 5) {
                      // Util.log("0.1抽奖库存不足拼团失败微信通知!");
                      // oneWXNotice(6, orderId, uid);
                      // }
                      return false;
                    }
                  }
                }
                if (source == 6) {
                  Util.log("限时秒杀活动库存判断");
                  if (grouponActivityPojo.getSurplusNum() == null
                      || grouponActivityPojo.getSurplusNum() < buyNum) {
                    Util.log("根据sourceId订单处理为拼团失败!");
                    params = new HashMap<String, Object>();
                    if (grouponActivityRecordPojo.getId() != null
                        && grouponActivityRecordPojo.getId() > 0) {
                      params.put("sourceId", grouponActivityRecordPojo.getId());
                      List<OrderPojo> orderList = orderService.listPage(params);
                      if (orderList != null && orderList.size() > 0) {
                        OrderPojo orderPojo = null;
                        for (OrderPojo order : orderList) {
                          orderPojo = new OrderPojo();
                          orderPojo.setId(order.getId());
                          orderPojo.setIsSuccess(2);
                          orderService.updateOrder(orderPojo);
                        }
                      } else {
                        Util.log("查询不到订单!");
                      }
                    }
                    Util.log("设置活动为拼团失败!");
                    GrouponActivityRecordPojo gar = new GrouponActivityRecordPojo();
                    gar.setActivityId(grouponActivityRecordPojo.getId());
                    gar.setStatus(2);
                    grouponActivityRecordService.update(gar);
                    return false;
                  }
                }
              }
              Util.log("参团->添加用户团记录表");
              GrouponUserRecordPojo grouponUserRecordPojo = new GrouponUserRecordPojo();
              grouponUserRecordPojo.setActivityId(activityId);
              grouponUserRecordPojo.setAttendId(attendId);
              grouponUserRecordPojo.setActivityType(source);
              grouponUserRecordPojo.setUserId(uid);
              if (source == 1) {
                Util.log("普通拼团");
                grouponUserRecordPojo.setStatus(0);
              } else if (source == 2) {
                Util.log("团免");
                grouponUserRecordPojo.setStatus(1);
              } else if (source == 5) {
                Util.log("0.1抽奖!");
                grouponUserRecordPojo.setStatus(3);
              } else if (source == 6) {
                Util.log("限时秒杀!");
                grouponUserRecordPojo.setStatus(4);
              } else if (source == 7) {
                Util.log("免费抽奖!");
                grouponUserRecordPojo.setStatus(5);
              } else if (source == 8) {
                Util.log("参拼得客的团!");
                grouponUserRecordPojo.setStatus(6);
              }
              grouponUserRecordPojo.setIsHead(0);
              grouponUserRecordPojo.setAttendTime(new Date());
              grouponUserRecordPojo.setCreateBy(uid);
              grouponUserRecordPojo.setCreateDate(new Date());
              grouponUserRecordPojo.setUpdateBy(uid);
              grouponUserRecordPojo.setUpdateDate(new Date());
              grouponUserRecordService.add(grouponUserRecordPojo);
              if (source != 7) {
                Util.log("再次检查拼团人数是否已到");
                grouponActivityRecordPojo = grouponActivityRecordService.getById(attendId);
                if (grouponActivityRecordPojo != null) {
                  int againJoinNum = grouponUserRecordService.joinNumByAttendId(attendId);
                  if (againJoinNum == grouponActivityRecordPojo.getNum()) {
                    Util.log("更改订单状态和团状态");
                    if (source != 5) {
                      params.clear();
                      params.put("isSuccess", 0);
                      params.put("orderStatus", 2);
                      params.put("sourceId", grouponActivityRecordPojo.getId());
                      List<OrderPojo> orderList = orderService.listPage(params);
                      Util.log("团状态改为拼团成功且订单状态改为待发货,插入成团时间");
                      if (orderList != null && orderList.size() > 0) {
                        OrderPojo orderup = null;
                        for (OrderPojo order : orderList) {
                          orderup = new OrderPojo();
                          orderup.setIsSuccess(1);
                          orderup.setGroupDate(new Date());
                          orderup.setId(order.getId());
                          // 来源是拼得客的设置订单拼得客用户id
                          // if (source == 8) {
                          // orderup.setPdkUid(grouponActivityRecordPojo.getUserId());
                          // }
                          orderService.updateOrder(orderup);
                        }
                      } else {
                        Util.log("查询不到对应拼团支付成功的数据!");
                      }
                    }
                    Util.log("修改团记录为组团成功");
                    GrouponActivityRecordPojo grouponActivityRecord =
                        new GrouponActivityRecordPojo();
                    grouponActivityRecord.setStatus(1);
                    grouponActivityRecord.setUpdateDate(new Date());
                    grouponActivityRecord.setId(grouponActivityRecordPojo.getId());
                    int state = grouponActivityRecordService.update(grouponActivityRecord);
                    if (state > 0) {
                      // 普通拼团成团
                      if (source == 1
                          && (activityId == 3643 || activityId == 3645 || activityId == 3646 || activityId == 3883)) {
                        // 发放优惠券
                        grantCoupon(activityId, attendId);
                      }
                    }
                    Util.log("微信拼团成功通知!");
                    if (source != 5) {
                      Util.log("非0.1抽奖订单通知！~");
                      SysLoginPojo user = sysLoginService.getfindByIdSysLogin(uid);
                      if (user != null) {
                        if (StringUtils.isNotBlank(user.getOpenid())) {
                          Util.log("查询有openid的用户!");
                          Util.log("支付成功通知！~");
                          wxNotice(3, orderId, user.getOpenid(), uid);
                          Util.log("拼团成功通知！~");
                          wxNotice(5, orderId, user.getOpenid(), uid);
                        } else {
                          Util.log("查询不到有openid的用户!");
                          Util.log("支付成功通知！~");
                          wxNotice(3, orderId, null, uid);
                          // addUserOrderNotice(3, uid, orderId);
                          Util.log("拼团成功通知！~");
                          wxNotice(5, orderId, null, uid);
                        }
                      } else {
                        Util.log("查询不到用户!");
                      }
                    } else {
                      Util.log("0.1抽奖订单通知！~");
                      SysLoginPojo user = sysLoginService.getfindByIdSysLogin(uid);
                      if (user != null) {
                        if (StringUtils.isNotBlank(user.getOpenid())) {
                          Util.log("查询有openid的用户!");
                          Util.log("支付成功通知！~");
                          addUserOrderNotice(1, uid, orderId);
                          // Util.log("拼团成功通知！~");
                          // addUserOrderNotice(3, uid, orderId);
                        } else {
                          Util.log("查询不到有openid的用户!");
                          Util.log("支付成功通知！~");
                          addUserOrderNotice(1, uid, orderId);
                          // addUserOrderNotice(3, uid, orderId);
                          // Util.log("拼团成功通知！~");
                          // addUserOrderNotice(3, uid, orderId);
                        }
                      } else {
                        Util.log("查询不到用户!");
                      }
                    }
                    if (source == 5 || source == 6) {
                      Util.log("根据订单详情skuId减库存");
                      List<OrderDetailPojo> orderDetailList =
                          orderDetailService.getOrderDetailWeb(orderId);
                      if (orderDetailList != null && orderDetailList.size() > 0) {
                        CartPojo cart = new CartPojo();
                        cart.setProductId(grouponActivityPojo.getProductId());
                        cart.setNum(buyNum);
                        if (source == 6) {
                          cart.setActivityId(activityId);
                        }
                        if (orderDetailList.get(0).getSkuLinkId() != null
                            && orderDetailList.get(0).getSkuLinkId() > 0) {
                          cart.setSkuLinkId(orderDetailList.get(0).getSkuLinkId());
                          boolean stockFlag = sellerService.updateActivitySkuStock(cart);
                          if (stockFlag) {
                            Util.log("减sku库存成功!");
                          } else {
                            Util.log("减sku库存失败!");
                          }
                        }
                      } else {
                        Util.log("查询不到订单详情!");
                      }
                      // 0.1抽奖拼团成功,团长中一等奖,随机抽取一人
                      if (source == 5) {
                        // ----------------------------------
                        // Util.log("0.1抽奖成团微信通知!");
                        // oneWXNotice(3, orderId, uid);
                        // ----------------------------------
                        List<Long> userIds = new ArrayList<Long>();
                        Util.log("查询团长,设置中奖");
                        params.clear();
                        params.put("attendId", grouponActivityRecordPojo.getId());
                        params.put("isHead", 1);
                        List<GrouponUserRecordPojo> grouponUserRecordList =
                            grouponUserRecordService.listPage(params);
                        GrouponUserRecordPojo grouponUserRecord = null;
                        if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
                          grouponUserRecord = new GrouponUserRecordPojo();
                          grouponUserRecord.setId(grouponUserRecordList.get(0).getId());
                          grouponUserRecord.setStatus(3);
                          grouponUserRecord.setPrize(1);
                          grouponUserRecordService.update(grouponUserRecord);

                          params.clear();
                          params.put("userId", grouponUserRecordList.get(0).getUserId());
                          params.put("sourceId", grouponUserRecordList.get(0).getAttendId());
                          params.put("orderStatus", 2);
                          params.put("isSuccess", 0);
                          List<OrderPojo> orderprizes = orderService.listPage(params);
                          if (orderprizes != null && orderprizes.size() > 0) {
                            OrderPojo orderup = new OrderPojo();
                            orderup.setIsSuccess(1);
                            // order.setOrderStatus(2);
                            orderup.setGroupDate(new Date());
                            orderup.setId(orderprizes.get(0).getId());
                            orderService.updateOrder(orderup);
                          }
                          // 记录团长userId
                          userIds.add(grouponUserRecordList.get(0).getUserId());
                        }
                        Util.log("随机抽取一人设置中奖");
                        params.clear();
                        params.put("attendId", grouponActivityRecordPojo.getId());
                        params.put("isHead", 0);
                        grouponUserRecordList = grouponUserRecordService.listPage(params);
                        if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
                          Random rand = new Random();
                          int num = rand.nextInt(grouponUserRecordList.size());
                          GrouponUserRecordPojo gur = new GrouponUserRecordPojo();
                          gur = grouponUserRecordList.get(num);
                          grouponUserRecord.setId(gur.getId());
                          grouponUserRecord.setStatus(3);
                          grouponUserRecord.setPrize(1);
                          grouponUserRecordService.update(grouponUserRecord);

                          params.clear();
                          params.put("userId", gur.getUserId());
                          params.put("sourceId", gur.getAttendId());
                          params.put("orderStatus", 2);
                          params.put("isSuccess", 0);
                          List<OrderPojo> orderprizes = orderService.listPage(params);
                          if (orderprizes != null && orderprizes.size() > 0) {
                            OrderPojo orderup = new OrderPojo();
                            orderup.setIsSuccess(1);
                            // order.setOrderStatus(2);
                            orderup.setGroupDate(new Date());
                            orderup.setId(orderprizes.get(0).getId());
                            orderService.updateOrder(orderup);
                          }
                          // 记录中奖人userId
                          userIds.add(gur.getUserId());
                        }
                        Util.log("其余其他人订单设置拼团失败!");
                        params.clear();
                        params.put("userIds", userIds);
                        params.put("sourceId", grouponActivityRecordPojo.getId());
                        List<OrderPojo> orderList1 = orderService.listPage(params);
                        OrderPojo orderPojo = null;
                        if (orderList1 != null && orderList1.size() > 0) {
                          for (OrderPojo order : orderList1) {
                            orderPojo = new OrderPojo();
                            orderPojo.setId(order.getId());
                            orderPojo.setIsSuccess(2);
                            orderPojo.setGroupDate(new Date());
                            orderService.updateOrder(orderPojo);
                          }
                        }
                        // ----------------------------------
                        // Util.log("0.1抽奖未中奖微信通知!");
                        // try {
                        // oneWXNotice(5, orderId, uid);
                        // } catch (Exception ex) {
                        // Util.log("0.1抽奖未中奖微信通知失败!");
                        // ex.printStackTrace();
                        // }
                        // ----------------------------------
                        if (grouponActivityPojo != null
                            && (grouponActivityPojo.getId() == 8760
                                || grouponActivityPojo.getId() == 8858
                                || grouponActivityPojo.getId() == 9437 || grouponActivityPojo
                                .getId() == 9543)) {
                          Util.log("0.1抽奖中奖微信通知!");
                          try {
                            oneWXNotice(4, orderId, uid);
                          } catch (Exception ex) {
                            Util.log("0.1抽奖中奖微信通知失败!");
                            ex.printStackTrace();
                          }
                        }
                        // ----------------------------------
                        // Util.log("0.1抽奖拼团成功短信通知!");
                        // smsOneOpenWin(activityId, attendId);
                        // ----------------------------------
                      }
                    }
                    Util.log("激活团免券!");
                    params.clear();
                    params.put("attendId", grouponActivityRecordPojo.getId());
                    List<GrouponUserRecordPojo> grouponUserRecordList =
                        grouponUserRecordService.listPage(params);
                    if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
                      for (GrouponUserRecordPojo grouponUserRecord : grouponUserRecordList) {
                        String f = grantFreeCoupon(grouponUserRecord.getUserId());
                        Util.log("1".equals(f) == true ? "用户:" + grouponUserRecord.getUserId()
                            + "激活团免券成功" : "用户:" + grouponUserRecord.getUserId() + "激活团免券失败");
                      }
                    }
                    if (source == 8) {
                      Util.log("拼得客成团处理!");
                      Util.log("查询这个团的全部订单!");
                      params.clear();
                      params.put("sourceId", grouponActivityRecordPojo.getId());
                      params.put("isDeleteOrder", 0);
                      params.put("isCancelOrder", 0);
                      params.put("payStatus", 1);
                      List<OrderPojo> orderList = orderService.listPage(params);
                      if (orderList != null && orderList.size() > 0) {
                        Util.log("计算返佣金额!");
                        Double freezingPrice = 0.0;
                        for (OrderPojo orderPojo : orderList) {
                          freezingPrice +=
                              grouponActivityPojo.getRebateRatio() / 100 * orderPojo.getFactPrice();
                        }
                        Util.log("返佣金额添加拼得客的冻结金额!");
                        UserPindekeInfoPojo userPindekeInfo =
                            userPindekeInfoService.findByUserId(orderList.get(0).getPdkUid());
                        if (userPindekeInfo != null) {
                          UserPindekeInfoPojo userPindekeInfoUp = new UserPindekeInfoPojo();
                          userPindekeInfoUp.setId(userPindekeInfo.getId());
                          userPindekeInfoUp.setFreezingPriceAdd(freezingPrice);
                          userPindekeInfoService.update(userPindekeInfoUp);
                        }
                      }
                    }
                  } else {
                    OrderPojo orderPojo = orderService.getfindByIdOrder(orderId);
                    if (orderPojo != null) {
                      SysLoginPojo user = sysLoginService.getfindByIdSysLogin(uid);
                      if (user != null) {
                        Util.log("微信支付成功通知!");
                        if (StringUtils.isNotBlank(user.getOpenid())) {
                          Util.log("查询有openid的用户!");
                          if (orderPojo.getPayMethod() == 8) {
                            Util.log("公众号支付！~");
                            wxNotice(3, orderId, user.getOpenid(), uid);
                            Util.log("参团成功通知！~");
                            addUserOrderNotice(9, uid, orderId);
                          } else {
                            Util.log("非公众号支付！~");
                            addUserOrderNotice(1, uid, orderId);
                            Util.log("参团成功通知！~");
                            addUserOrderNotice(9, uid, orderId);
                          }
                        } else {
                          Util.log("查询不到有openid的用户!");
                          addUserOrderNotice(1, uid, orderId);
                          Util.log("参团成功通知！~");
                          addUserOrderNotice(9, uid, orderId);
                        }
                        // if (source == 5) {
                        // Util.log("0.1抽奖参团成功微信通知!");
                        // oneWXNotice(2, orderId, uid);
                        // }
                      } else {
                        Util.log("查询不到用户!");
                      }
                    } else {
                      Util.log("查询不到订单!");
                    }
                  }
                } else {
                  Util.log("再次检查拼团人数,查询不到该该团!");
                  activityFlag = false;
                }
              } else if (source == 7) {
                Util.log("免费抽奖参团通知！~");
                Util.log("再次检查拼团人数是否已到");
                grouponActivityRecordPojo = grouponActivityRecordService.getById(attendId);
                if (grouponActivityRecordPojo != null) {
                  int againJoinNum = grouponUserRecordService.joinNumByAttendId(attendId);
                  if (againJoinNum == grouponActivityRecordPojo.getNum()) {
                    SysLoginPojo user = sysLoginService.getfindByIdSysLogin(uid);
                    if (user != null) {
                      Util.log("支付成功通知！~");
                      addUserOrderNotice(1, uid, orderId);
                      Util.log("拼团成功通知！~");
                      wxNotice(5, orderId, null, uid);
                    } else {
                      Util.log("查询不到用户!");
                    }
                  }
                } else {
                  OrderPojo orderPojo = orderService.getfindByIdOrder(orderId);
                  if (orderPojo != null) {
                    SysLoginPojo user = sysLoginService.getfindByIdSysLogin(uid);
                    if (user != null) {
                      Util.log("支付成功通知!");
                      addUserOrderNotice(1, uid, orderId);
                      Util.log("参团成功通知！~");
                      addUserOrderNotice(9, uid, orderId);
                    } else {
                      Util.log("查询不到用户!");
                    }
                  } else {
                    Util.log("查询不到订单!");
                  }
                }
              }
            }
          } else {
            activityFlag = false;
          }
        } else {
          activityFlag = false;
        }
        if (!activityFlag) {
          Util.log("修改订单状态");
          OrderPojo orderPojo = new OrderPojo();
          orderPojo.setId(orderId);
          orderPojo.setIsSuccess(2);
          orderService.updateOrder(orderPojo);
        } else {
          flag = true;
        }
      }
    }
    return flag;
  }

  /**
   * 检查团是否过期和人数是否已满
   * 
   * @param activityId 活动id
   * @param source 来源，1-普通拼团2-团免3-猜价
   * @param attendId 团记录id
   * @param uid 用户id
   * @param skuLinkId skuId
   * @param num 购买数量
   * @return
   * @throws SQLException
   */
  public Integer purchaseActivityCheck(Long activityId, int source, Long attendId, Long uid,
      Long pid, Integer skuLinkId, Integer num) throws SQLException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 查询是否参加过活动
    Map<String, Object> params = new HashMap<String, Object>();
    if (attendId != null && uid != null) {
      params.put("userId", uid);
      params.put("attendId", attendId);
      int isJoin = grouponUserRecordService.countBy(params);
      if (isJoin > 0) {
        return 8;
      }
    }
    // 拼团或团免或0.1抽奖或限时秒杀
    if (source == 1 || source == 2 || source == 5 || source == 6) {
      // 根据activityId查询活动
      GrouponActivityPojo grouponActivityPojo = grouponActivityService.getById(activityId);
      if (grouponActivityPojo != null && grouponActivityPojo.getStatus() == 1) {
        // 判断传过来的商品id是否是该活动的商品id
        System.out.println(grouponActivityPojo.getProductId() + ";" + pid);
        if (grouponActivityPojo.getProductId().longValue() != pid.longValue()) {
          System.out.println("商品验证不通过!");
          return 1;
        }
        // 抽奖和秒杀库存判断
        if (source == 5 || source == 6) {
          if (source == 6) {
            if (grouponActivityPojo.getSurplusNum() == 0) {
              return 10;
            }
          }
          if (skuLinkId != null && skuLinkId > 0) {
            ProductSkuLinkPojo productSkuLinkPojo =
                productSkuLinkService.getById(Long.valueOf(skuLinkId));
            if (productSkuLinkPojo == null) {
              Util.log("查询不到sku!");
              return 11;
            } else {
              if (productSkuLinkPojo.getStock() <= 0) {
                Util.log("库存不足!");
                return 10;
              }
            }
          } else {
            Util.log("skuLinkId为空!");
            return 11;
          }
          if (source == 5) {
            if (attendId != null && attendId != 0) {
              // 判断是否参过团
              params.clear();
              params.put("sourceId", attendId);
              params.put("userId", uid);
              int i = orderService.countBy(params);
              if (i > 0) {
                Util.log("参过团!");
                return 12;
              }
            } else {
              // 判断是否开过团
              params.clear();
              params.put("sourceId", attendId);
              params.put("userId", uid);
              int i = orderService.countBy(params);
              if (i > 0) {
                // 判断是否是团长
                params.clear();
                params.put("isHead", 1);
                params.put("userId", uid);
                params.put("activityId", activityId);
                int j = grouponActivityRecordService.countBy(params);
                if (j > 0) {
                  Util.log("开过团!");
                  return 13;
                }
              }
            }
          }

        }
        // 判断是开团还是参团
        if (attendId != null && attendId != 0) {
          // 参团,判断是否存在团记录
          GrouponActivityRecordPojo grouponActivityRecordPojo =
              grouponActivityRecordService.getById(attendId);
          if (grouponActivityRecordPojo != null) {
            // 判断是否团活动是否到期
            if (DateTimeUtil.compareDate(
                StringUtil.dateString(grouponActivityRecordPojo.getEndTime()),
                sdf.format(new Date()))
                && DateTimeUtil.compareDate(sdf.format(new Date()),
                    StringUtil.dateString(grouponActivityRecordPojo.getBeginTime()))) {
              // 参团,判断团人数是否已满
              synchronized (this) {
                int joinNum = grouponUserRecordService.joinNumByAttendId(attendId);
                if (joinNum >= grouponActivityRecordPojo.getNum()) {
                  System.out.println("该团已满!");
                  return 2;
                }
              }
            } else {
              System.out.println("活动已结束!");
              return 3;
            }
          } else {
            System.out.println("不存在该团记录!");
            return 4;
          }
        } else {
          if (source == 2) {
            // 判断团免券是否有效
            Map<String, Object> params3 = new HashMap<String, Object>();
            params3.put("userId", uid);
            params3.put("status", 1);
            params3.put("used", 0);
            // 有效时间
            params3.put("validTime", StringUtil.dateString(new Date()));
            int isGroupFree = groupFreeCouponService.countBy(params3) > 0 ? 1 : 0;
            // 开团,判断是否团活动是否到期
            if (isGroupFree == 1
                && DateTimeUtil
                    .compareDate(StringUtil.dateString(grouponActivityPojo.getEndTime()),
                        sdf.format(new Date()))
                && DateTimeUtil.compareDate(sdf.format(new Date()),
                    StringUtil.dateString(grouponActivityPojo.getBeginTime()))) {
              // 活动进行中
            } else {
              System.out.println("活动已结束!");
              return 3;
            }
          }
        }
      } else {
        System.out.println("查询不到该活动!");
        return 5;
      }
    } else if (source == 3) {// 猜价
      params.clear();
      params.put("userId", uid);
      params.put("activityId", activityId);
      params.put("activityType", 3);
      params.put("prize", 1);
      GrouponUserRecordPojo grouponUserRecordPojo = grouponUserRecordService.findByParams(params);
      // 查找用户是否有中奖记录
      if (grouponUserRecordPojo == null) {
        System.out.println("用户在该活动并未等奖!");
        return 6;
      } else if (grouponUserRecordPojo.getIsRecCoupon() == 1) {
        System.out.println("您已领取过一等奖奖品!");
        return 9;
      }
    } else if (source == 8) {
      // 判断活动状态
      if (activityId != null) {
        GrouponActivityPojo grouponActivityPojo = grouponActivityService.getById(activityId);
        if (grouponActivityPojo != null) {
          Date nowDate = new Date();
          if (nowDate.getTime() < grouponActivityPojo.getBeginTime().getTime()
              && nowDate.getTime() > grouponActivityPojo.getEndTime().getTime()) {
            System.out.println("活动已结束!");
            return 3;
          }
        }
      }
      // 拼得客
      // if (attendId == null || attendId == 0) {
      // Util.log("拼得客开团");
      // if (activityId != null && activityId != 0) {
      // GrouponActivityPojo grouponActivityPojo = grouponActivityService.getById(activityId);
      // if (grouponActivityPojo != null && grouponActivityPojo.getRestrictionNum() > 0) {
      // params = new HashMap<String, Object>();
      // params.put("userId", uid);
      // params.put("activityId", activityId);
      // Integer grouponActivityRecordPojo = grouponActivityRecordService.countBy(params);
      // grouponActivityRecordPojo += num;
      // if (grouponActivityRecordPojo > grouponActivityPojo.getRestrictionNum()) {
      // Util.log("购买数量超过了限购数量!");
      // return 14;
      // }
      // }
      // } else {
      // Util.log("活动id为空!");
      // }
      // }
    } else {
      Util.log("来源类型不正确!");
      return 7;
    }
    return 0;
  }

  public String uploadFile(File file, String path) {
    String fileName = "";
    try {
      FileInputStream fis = new FileInputStream(file);
      Random random = new Random();
      int i = random.nextInt(1000);
      fileName = StringUtil.getCurrentDateStrByfu() + i + ".jpg";
      File fs = new File(path, fileName);
      FileOutputStream fos = new FileOutputStream(fs);
      int len = 0;
      byte[] buffer = new byte[1024];

      while ((len = fis.read(buffer)) != -1) {
        fos.write(buffer, 0, len);
      }
      fos.flush();
      fos.close();
      fis.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return fileName;
  }

  /**
   * 返回当前时间加上24H
   * 
   * @return
   */
  public static Date getTimeAdd24(Date current) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(current);
    cal.add(Calendar.HOUR, 24);
    return cal.getTime();
  }

  /**
   * 返回当前时间加上N小时
   * 
   * @param current 当前时间
   * @param H 加上小时数
   * @return
   */
  public static Date getTimeAddHour(Date current, int H) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(current);
    cal.add(Calendar.HOUR, H);
    return cal.getTime();
  }

  /**
   * 返回当前时间加上N天
   * 
   * @param current 当前时间
   * @param H 天数
   * @return
   */
  public static Date getTimeAddDay(Date current, int D) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(current);
    cal.add(Calendar.DATE, D);
    return cal.getTime();
  }


  /**
   * 领取团免券
   * 
   * @param userId 用户id
   * @param linkId 分享id
   * @throws SQLException
   */
  public synchronized int getFreeCoupon(Long userId, Long linkId) throws SQLException {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("userId", userId);
    params.put("used", 0);
    params.put("status", 1);
    params.put("nowTime", new Date());
    int i = groupFreeCouponService.countBy(params);
    if (i == 0) {
      // 根据用户id判断是否存在正在进行中的团免团
      params.clear();
      params.put("userId", userId);
      params.put("activityType", 2);
      params.put("status", 0);
      int count = grouponActivityRecordService.countBy(params);
      if (count > 0) {
        return 6;
      }
      GroupFreeCouponSettingPojo groupFreeCouponSettingPojo =
          groupFreeCouponSettingService.getById(linkId);
      if (groupFreeCouponSettingPojo != null) {
        // 判断有效期时间是否大于当前时间
        if (DateTimeUtil.compareDate(
            StringUtil.dateString(groupFreeCouponSettingPojo.getInvalidTime()),
            StringUtil.dateString(new Date()))) {
          if (groupFreeCouponSettingPojo.getSurplusNum() > 0) {
            // 激活用户团免券
            GroupFreeCouponPojo groupFreeCouponPojo = new GroupFreeCouponPojo();
            groupFreeCouponPojo.setUserId(userId);
            groupFreeCouponPojo.setUsed(0);
            groupFreeCouponPojo.setStatus(1);
            groupFreeCouponPojo.setActiveTime(new Date());
            groupFreeCouponPojo.setInvalidTime(GrouponService.getTimeAdd24(groupFreeCouponPojo
                .getActiveTime()));
            groupFreeCouponPojo.setInvitationUserId(0l);
            int updateNum = groupFreeCouponService.update(groupFreeCouponPojo);
            if (updateNum > 0) {
              // 可领取人数减一
              groupFreeCouponSettingPojo.setSurplusNumM(1);
              groupFreeCouponSettingService.update(groupFreeCouponSettingPojo);
            } else {
              return 1;
              // msg = "优惠券激活失败!";
            }
          } else {
            return 5;
            // msg = "人数已满!";
          }
        } else {
          return 2;
          // msg = "领取时间已过!";
        }
      } else {
        return 3;
        // msg = "查询不到!";
      }
    } else {
      return 4;
      // msg = "您已经有团免券了,不能重复领取!";
    }
    return 0;
  }

  /**
   * 领取优惠券
   * 
   * @param userId 用户id
   * @param linkId 分享id
   * @throws Exception
   */
  public synchronized int getCoupon(Long userId, Long linkId) throws Exception {
    if (userId == null || userId == 0) {
      Util.log("用户id不能为空!");
      return 2;
    } else if (linkId == null || linkId == 0) {
      Util.log("查询该不到优惠券!");
      return 3;
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      // 判断是否存在优惠券
      CouponPojo couponPojo = couponService.getcouponById(linkId);
      if (couponPojo != null) {
        // 判断用户是否领取过优惠券
        params.put("userId", userId);
        params.put("couponId", linkId);
        int i = couponService.getuserCouponCount(params);
        if (i > 0) {
          Util.log("您已经领取过优惠券!");
          return 5;
        } else {
          // 判断优惠券是否过期
          if (DateTimeUtil.compareDate(StringUtil.dateString(new Date()),
              DateTimeUtil.currToDateStr(couponPojo.getValidStime()))
              && DateTimeUtil.compareDate(DateTimeUtil.currToDateStr(couponPojo.getValidEtime()),
                  StringUtil.dateString(new Date()))) {
            // 判断是否有可领取优惠券
            if (couponPojo.getSurplusNum() > 0) {
              // 插入user_coupon(判断是否指定商品)
              UserCouponPojo userCoupon = new UserCouponPojo();
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              new Date();
              // 创建时间
              long gentime = sdf.parse(StringUtil.dateString(new Date())).getTime();
              // 有效期开始时间
              // long stime = sdf.parse(StringUtil.dateString(validSdate)).getTime();
              // Calendar calendar = Calendar.getInstance();
              // calendar.setTime(validSdate);
              // calendar.add(Calendar.HOUR, 24);
              // 有效期结束时间
              // long etime = sdf.parse(StringUtil.dateString(calendar.getTime())).getTime();
              IdWorker idGen = new IdWorker(0, 0);
              userCoupon.setCouponNo(String.valueOf(idGen.nextId()));
              userCoupon.setCouponId(linkId);
              userCoupon.setUserId(userId);
              userCoupon.setSource(0);
              userCoupon.setGenTime(gentime / 1000);
              userCoupon.setStatus(1);
              userCoupon.setUsed(0);
              userCoupon.setUseTime(0L);
              // userCoupon.setValidstime(stime / 1000);
              // userCoupon.setValidetime(etime / 1000);
              userCoupon.setValidstime(couponPojo.getValidStime());
              userCoupon.setValidetime(couponPojo.getValidEtime());
              userCoupon.setProductId(couponPojo.getProductId() == null ? 0L : couponPojo
                  .getProductId());
              int a = couponService.addUserCoupon(userCoupon);
              CouponPojo coupon = new CouponPojo();
              if (a > 0) {
                // 可领取数减一
                coupon.setCouponId(linkId);
                coupon.setSurplusNumM(1);
                couponService.updatecouponById(coupon);
              } else {
                Util.log("优惠券领取失败,请重新领取!");
                return 8;
              }
            } else {
              Util.log("优惠券领取完了!");
              return 7;
            }
          } else {
            Util.log("优惠券已过期!");
            return 6;
          }
        }
      } else {
        Util.log("查询不到优惠券!");
        return 4;
      }
    }
    return 1;
  }

  /**
   * 激活团免券
   * 
   * @param userId 用户id
   * @param invitorUserId 邀请用户ID
   * @throws SQLException
   */
  public synchronized int activeFreeCoupon(Long userId, Long invitorUserId) throws SQLException {
    int result = 0;
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("userId", userId);
    List<GroupFreeCouponPojo> coupons = groupFreeCouponService.listPage(params);
    if (coupons != null && coupons.size() > 0) {
      GroupFreeCouponPojo coupon = coupons.get(0);
      if (coupon.getInvitationUserId() == null || coupon.getInvitationUserId() == 0) {
        coupon.setUsed(0);
        coupon.setStatus(1);
        coupon.setActiveTime(new Date());
        coupon.setInvalidTime(GrouponService.getTimeAdd24(coupon.getActiveTime()));
        // 邀请用户ID
        coupon.setInvitationUserId(invitorUserId);
        // 0-激活失败 1-激活成功
        result = groupFreeCouponService.update(coupon);
      } else {
        // 2-已被邀请
        result = 2;
      }
    }
    return result;
  }

  /**
   * 批量赠送用户优惠券
   * 
   * @param uids 用户id集
   * @param source 优惠券来源：1-猜价赠送
   * @param type 优惠券类型
   * @param couponName 优惠券名
   * @param content 优惠信息
   * @param validSdate 有效起始
   * @param validDays 有效天数
   * @param productId 指定商品ID,为空不指定
   * @param caption 优惠券说明
   * @param sourceId 优惠券来源活动ID
   * @throws Exception
   * @throw
   * @return void
   */
  public void grantUserCoupon(List<Long> uids, Integer source, Integer type, String couponName,
      String content, Date validSdate, int validDays, Long productId, String caption, Long sourceId)
      throws Exception {
    // 判断是否有用户
    if (uids != null && uids.size() > 0) {
      // 判断优惠券模板是否存在
      Map<String, Object> paramMap = new HashMap<String, Object>();
      paramMap.put("type", type);
      paramMap.put("content", content);
      paramMap.put("couponName", couponName);
      paramMap.put("status", 1);
      paramMap.put("channel", source);
      if (productId != null && productId > 0) {
        paramMap.put("isProduct", 1);
        paramMap.put("productId", productId);
      } else {
        paramMap.put("isProduct", 0);
      }
      List<CouponPojo> coupons = couponService.getcouponList(paramMap);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      long gentime = sdf.parse(StringUtil.dateString(new Date())).getTime();
      long stime = sdf.parse(StringUtil.dateString(validSdate)).getTime();
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(validSdate);
      calendar.add(Calendar.DAY_OF_MONTH, validDays);
      long etime = sdf.parse(StringUtil.dateString(calendar.getTime())).getTime();
      CouponPojo coupon = null;
      if (coupons != null && coupons.size() > 0) {
        coupon = coupons.get(0);
      } else {
        // 优惠券未找到，添加新优惠券
        coupon = new CouponPojo();
        coupon.setName(couponName);
        coupon.setType(type);
        coupon.setStatus(1);
        coupon.setValidStime(stime / 1000);
        coupon.setValidEtime(etime / 1000);
        coupon.setCreateTime(gentime / 1000);
        coupon.setContent(content);
        coupon.setSheetNum(0);
        coupon.setSurplusNum(0);
        if (productId != null && productId > 0) {
          coupon.setIsProduct(1);
          coupon.setProductId(productId);
        } else {
          coupon.setIsProduct(0);
        }
        coupon.setIsDelete(0);
        coupon.setCaption(caption);
        // 1-猜价优惠券
        coupon.setChannel(source);
        couponService.addcoupon(coupon);
      }

      IdWorker idGen = new IdWorker(0, 0);
      List<UserCouponPojo> userCoupons = new ArrayList<UserCouponPojo>();
      UserCouponPojo userCoupon = null;
      for (int i = 0; i < uids.size(); i++) {
        userCoupon = new UserCouponPojo();
        // 优惠券码
        userCoupon.setCouponNo(String.valueOf(idGen.nextId()));
        userCoupon.setCouponId(coupon.getCouponId());
        userCoupon.setUserId(uids.get(i));
        userCoupon.setGenTime(gentime / 1000);
        userCoupon.setStatus(1);
        userCoupon.setUsed(0);
        userCoupon.setUseTime(0L);
        userCoupon.setValidstime(stime / 1000);
        userCoupon.setValidetime(etime / 1000);
        userCoupon.setSource(source);
        userCoupon.setProductId(productId);
        userCoupon.setSourceId(sourceId);
        userCoupons.add(userCoupon);
      }
      // 批量添加优惠券
      if (userCoupons.size() > 0) {
        couponService.addUserCouponBatch(userCoupons);
      }
    }
  }

  /**
   * 赠送猜价用户优惠券（二等奖5元直减 三等奖3元直减）
   * 
   * @param uids 得奖用户集
   * @param prize 奖项 ：2-二等奖 3-三等奖
   * @param productId 指定猜价商品ID
   * @throw
   * @return void
   * @throws Exception
   */
  public void gussesPriceGrantCoupon(List<Long> uids, int prize, Long productId, Long sourceId)
      throws Exception {
    if ((prize == 2 || prize == 3) && uids != null && uids.size() > 0) {
      // 二三等奖进入
      String couponName = "";
      String content = "";
      Map<String, Object> param = new HashMap<String, Object>();
      JSONObject json = null;
      if (prize == 2) {
        couponName = "直减5元（猜价赠送）";
        param.put("m", "5");
      } else if (prize == 3) {
        couponName = "直减3元（猜价赠送）";
        param.put("m", "3");
      }
      json = JSONObject.fromObject(param);
      content = json.toString();
      // source=1-猜价赠送 type=2-直减 有效期1天
      grantUserCoupon(uids, 1, 2, couponName, content, new Date(), 1, productId, "猜价活动赠送", sourceId);
    }

  }

  /**
   * 支付宝批量退款异步返回数据处理
   * 
   * @return
   */
  public static List<ResultDetailData> refundDataHandle(String resultDetails) {
    String[] dataArr = null;
    ResultDetailData resultDetailData = null;
    List<ResultDetailData> resultDetailDataList = null;
    if (StringUtils.isNotBlank(resultDetails)) {
      resultDetailDataList = new ArrayList<ResultDetailData>();
      String[] detailArr = resultDetails.split("#");
      for (String resultDetail : detailArr) {
        dataArr = resultDetail.split("\\^");
        resultDetailData = new ResultDetailData();
        if (dataArr != null) {
          resultDetailData.setTradeNo(dataArr[0]);
          resultDetailData.setRefundPrice(Double.valueOf(dataArr[1]));
          resultDetailData.setResult(dataArr[2]);
        }
        resultDetailDataList.add(resultDetailData);
      }
    } else {
      System.out.println("没有退款详情数据!");
    }
    return resultDetailDataList;
  }

  /**
   * 微信通知
   * 
   * @param type(1-发货2-退款3-支付成功4-开团成功5-拼团成功6-拼团失败)
   * @param oid 订单id
   * @param openid
   * @param uid 用户id
   * @return
   * @throws SQLException
   */
  public Integer wxNotice(Integer type, Long oid, String openid, Long uid) throws SQLException {
    try {
      // 添加订单消息
      Util.log("wxNotice()添加订单消息：type=" + type + "/oid=" + oid + "/uid=" + uid);
      if (type != null && type == 1 && oid != null && uid != null) {
        addUserOrderNotice(6, uid, oid);
      } else if (type != null && type == 2 && oid != null && uid != null) {
        addUserOrderNotice(7, uid, oid);
      } else if (type != null && type == 3 && oid != null && uid != null) {
        addUserOrderNotice(1, uid, oid);
      } else if (type != null && type == 4 && oid != null && uid != null) {
        addUserOrderNotice(2, uid, oid);
        // } else if (type != null && type == 5 && oid != null && uid != null) {
        // addUserOrderNotice(3, uid, oid);
        // } else if (type != null && type == 6 && oid != null && uid != null) {
        // addUserOrderNotice(8, uid, oid);
        // } else {
        // Util.log("参数错误!");
      }

      if (type != null && type == 1 && oid != null && StringUtils.isNotBlank(openid)) {
        // 查询订单
        OrderPojo orderPojo = orderService.getfindByIdOrder(oid);
        if (orderPojo != null) {
          // 发货通知
          String url = ConstParam.WX_URL + "/wx_msgtpl.php";
          String params =
              "act=delivery&oid=" + oid + "&openid=" + openid + "&logisticsName="
                  + orderPojo.getLogisticsName() + "&logisticsNo=" + orderPojo.getLogisticsNo()
                  + "&productName=" + orderPojo.getProductName() + "&buyNum=" + orderPojo.getNum()
                  + "&orderNo=" + orderPojo.getOrderNo();
          StringUtil.loadJson(url, params, "post");
        } else {
          Util.log("查询不到订单!");
        }
      } else if (type != null && type == 2 && oid != null && StringUtils.isNotBlank(openid)
          && uid != null) {
        // 退款通知
        String url =
            ConstParam.WX_URL + "/wx_msgtpl.php?act=refund&oid=" + oid + "&openid=" + openid
                + "&uid=" + uid;
        StringUtil.loadJson(url);
      } else if (type != null && type == 3 && oid != null && StringUtils.isNotBlank(openid)) {
        // 查询订单
        OrderPojo orderPojo = orderService.getfindByIdOrder(oid);
        if (orderPojo != null) {
          // 支付成功通知
          String url = ConstParam.WX_URL + "/wx_msgtpl.php";
          double factPrice = orderPojo.getFactPrice();
          // if (orderPojo.getUsedPrice() != null && orderPojo.getUsedPrice() > 0) {
          // factPrice = factPrice - orderPojo.getUsedPrice();
          // }
          String params =
              "act=pay&openid=" + openid + "&factPrice=" + factPrice + "&productName="
                  + orderPojo.getProductName();
          StringUtil.loadJson(url, params, "post");
        } else {
          Util.log("查询不到订单!");
        }
      } else if (type != null && type == 4 && oid != null && StringUtils.isNotBlank(openid)) {
        // 查询订单
        OrderPojo orderPojo = orderService.getfindByIdOrder(oid);
        if (orderPojo != null) {
          // 开团成功通知
          String url = ConstParam.WX_URL + "/wx_msgtpl.php";
          double factPrice = orderPojo.getFactPrice();
          // if (orderPojo.getUsedPrice() != null && orderPojo.getUsedPrice() > 0) {
          // factPrice = factPrice - orderPojo.getUsedPrice();
          // }
          String params =
              "act=open&openid=" + openid + "&factPrice=" + factPrice + "&productName="
                  + orderPojo.getProductName() + "&consignee=" + orderPojo.getConsignee()
                  + "&consigneePhone=" + orderPojo.getConsigneePhone() + "&consigneeAddress="
                  + orderPojo.getConsigneeAddress() + "&orderNo=" + orderPojo.getOrderNo();
          StringUtil.loadJson(url, params, "post");
        } else {
          Util.log("查询不到订单!");
        }
      } else if (type != null && type == 5) {
        // 拼团成功通知
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> item = new HashMap<String, Object>();
        OrderPojo orderPojo = orderService.getfindByIdOrder(oid);
        if (orderPojo != null) {
          String url = ConstParam.WX_URL + "/wx_msgtpl.php";
          param.put("sourceId", orderPojo.getSourceId());
          param.put("payStatus", 1);
          param.put("isSuccess", 1);
          param.put("orderType", 0);
          List<OrderPojo> orderList = orderService.listPage(param);
          List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
          if (orderList != null && orderList.size() > 0) {
            for (OrderPojo order : orderList) {
              if (order.getPayMethod() == 8) {
                item = new HashMap<String, Object>();
                SysLoginPojo user = sysLoginService.getfindByIdSysLogin(order.getUserId());
                if (user != null && StringUtils.isNotBlank(user.getOpenid())) {
                  item.put("openid", user.getOpenid());
                } else {
                  item.put("openid", "");
                }
                item.put("orderNo", StringUtil.checkVal(order.getOrderNo()));
                double factPrice = order.getFactPrice();
                if (order.getUsedPrice() != null && order.getUsedPrice() > 0) {
                  factPrice = factPrice - order.getUsedPrice();
                }
                item.put("factPrice", StringUtil.checkVal(factPrice));
                data.add(item);
              }

              if (order.getUserId() != null && order.getId() != null) {
                addUserOrderNotice(3, order.getUserId(), order.getId());
              }

              Util.log("addUserOrderNotice:order.getUserId()=" + order.getUserId()
                  + "/order.getId()=" + order.getId());
            }
          }
          JSONArray json = JSONArray.fromObject(data);
          StringUtil.loadJson(url, "act=join&data=" + json.toString(), "post");
        } else {
          Util.log("查询不到订单!");
        }
      } else if (type != null && type == 6) {
        Util.log("拼团失败微信通知!");
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> item = new HashMap<String, Object>();
        OrderPojo orderPojo = orderService.getfindByIdOrder(oid);
        if (orderPojo != null) {
          String url = ConstParam.WX_URL + "/wx_msgtpl.php";
          param.put("sourceId", orderPojo.getSourceId());
          param.put("payStatus", 1);
          param.put("isSuccess", 2);
          param.put("orderType", 0);
          List<OrderPojo> orderList = orderService.listPage(param);
          List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
          if (orderList != null && orderList.size() > 0) {
            for (OrderPojo order : orderList) {
              if (order.getPayMethod() == 8) {
                item = new HashMap<String, Object>();
                SysLoginPojo user = sysLoginService.getfindByIdSysLogin(order.getUserId());
                if (user != null && StringUtils.isNotBlank(user.getOpenid())) {
                  item.put("openid", user.getOpenid());
                } else {
                  item.put("openid", "");
                }
                item.put("orderNo", StringUtil.checkVal(order.getOrderNo()));
                item.put("productName", StringUtil.checkVal(order.getProductName()));
                try {
                  double productPrice = order.getFactPrice() + order.getUsedPrice();
                  item.put("productPrice", StringUtil.checkVal(productPrice));
                } catch (Exception e) {
                  item.put("productPrice", "");
                  e.printStackTrace();
                }
                item.put("factPrice", StringUtil.checkVal(order.getFactPrice()));
                data.add(item);
              }

              if (order.getUserId() != null && order.getId() != null) {
                addUserOrderNotice(8, order.getUserId(), order.getId());
              }
            }
          }
          JSONArray json = JSONArray.fromObject(data);
          StringUtil.loadJson(url, "act=join_fail&data=" + json.toString(), "post");
        } else {
          Util.log("查询不到订单!");
        }
      } else {
        Util.log("参数错误!");
      }
    } catch (Exception e) {
      Util.log("微信通知出现异常!");
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 0.1抽奖微信通知
   * 
   * @param type 1开团 2参团 3成团 4中奖 5未中奖 6参团失败
   * @param oid 订单id
   * @param uid 用户id
   * @return
   * @throws SQLException
   */
  public void oneWXNotice(Integer type, Long oid, Long uid) throws SQLException {
    try {
      if (type != null && type > 0 && oid != null && oid > 0 && uid != null && uid > 0) {
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> item = new HashMap<String, Object>();
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        OrderPojo orderPojo = orderService.getfindByIdOrder(oid);
        if (orderPojo != null && orderPojo.getSourceId() != null) {
          Util.log("oneWXNotice()添加订单消息：type=" + type + "/oid=" + oid + "/uid=" + uid);
          String url = ConstParam.WX_URL + "/wx_msgtpl.php";
          if (type == 3 || type == 4 || type == 5 || type == 6) {
            // 拼团成功/失败通知
            param.put("sourceId", orderPojo.getSourceId());
            param.put("payStatus", 1);
            if (type == 6 || type == 5) {
              param.put("isSuccess", 2);
            } else if (type == 3) {
              param.put("isSuccess", 0);
            } else {
              param.put("isSuccess", 1);
            }
            List<OrderPojo> orderList = orderService.listPage(param);
            if (orderList != null && orderList.size() > 0) {
              for (OrderPojo order : orderList) {
                // if (order.getPayMethod() == 8) {
                item = new HashMap<String, Object>();
                SysLoginPojo user = sysLoginService.getfindByIdSysLogin(order.getUserId());
                if (user != null && StringUtils.isNotBlank(user.getOpenid())) {
                  item.put("openid", user.getOpenid());
                } else {
                  item.put("openid", "");
                }
                item.put("orderNo", StringUtil.checkVal(order.getOrderNo()));
                item.put("factPrice", StringUtil.checkVal(order.getFactPrice()));
                item.put("productName", StringUtil.checkVal(order.getProductName()));
                item.put("consignee", StringUtil.checkVal(order.getConsignee()));
                item.put("consigneePhone", StringUtil.checkVal(order.getConsigneePhone()));
                item.put("consigneeAddress", StringUtil.checkVal(order.getConsigneeAddress()));
                item.put("attendId", StringUtil.checkVal(order.getSourceId()));
                item.put(
                    "groupDate",
                    order.getGroupDate() == null ? StringUtil.checkVal(new Date()) : StringUtil
                        .checkVal(StringUtil.dateString(order.getGroupDate())));
                if (type != null && type == 4 && order.getSourceId() != null
                    && order.getSourceId() > 0) {
                  Map<String, Object> params = new HashMap<String, Object>();
                  params.put("attendId", order.getSourceId());
                  List<AliRedEnvelopePojo> aliRedEnvelopes = aliRedEnvelopeService.listPage(params);
                  if (aliRedEnvelopes != null && aliRedEnvelopes.size() > 0) {
                    item.put("invCode", StringUtil.checkVal(aliRedEnvelopes.get(0).getInviteCode()));
                  } else {
                    item.put("invCode", "");
                  }
                } else {
                  item.put("invCode", "");
                }
                data.add(item);
                // }

                if (type == 3) {
                  // 添加订单消息
                  addUserOrderNotice(3, order.getUserId(), order.getId());
                } else if (type == 4) {
                  // 添加订单消息
                  addUserOrderNotice(5, order.getUserId(), order.getId());
                } else if (type == 5) {
                  // 添加订单消息
                  addUserOrderNotice(4, order.getUserId(), order.getId());
                } else if (type == 6) {
                  addUserOrderNotice(8, order.getUserId(), order.getId());
                }
              }
            }
            Util.log("0.1微信通知!");
            JSONArray json = JSONArray.fromObject(data);
            StringUtil.loadJson(url, "act=raffle01&type=" + type + "&data=" + json.toString(),
                "post");
          } else {
            if (type == 1) {
              // 添加订单消息
              addUserOrderNotice(2, uid, oid);
            } else if (type == 2) {

            }

            // if (orderPojo.getPayMethod() == 8) {
            item = new HashMap<String, Object>();
            SysLoginPojo user = sysLoginService.getfindByIdSysLogin(orderPojo.getUserId());
            if (user != null) {
              item.put("openid", StringUtil.checkVal(user.getOpenid()));
            } else {
              item.put("openid", "");
            }
            item.put("orderNo", StringUtil.checkVal(orderPojo.getOrderNo()));
            item.put("factPrice", StringUtil.checkVal(orderPojo.getFactPrice()));
            item.put("productName", StringUtil.checkVal(orderPojo.getProductName()));
            item.put("consignee", StringUtil.checkVal(orderPojo.getConsignee()));
            item.put("consigneePhone", StringUtil.checkVal(orderPojo.getConsigneePhone()));
            item.put("consigneeAddress", StringUtil.checkVal(orderPojo.getConsigneeAddress()));
            item.put("attendId", StringUtil.checkVal(orderPojo.getSourceId()));
            data.add(item);
            Util.log("0.1微信通知!");
            JSONArray json = JSONArray.fromObject(data);
            StringUtil.loadJson(url, "act=raffle01&type=" + type + "&data=" + json.toString(),
                "post");
            // } else {
            // Util.log("不是微信公众号支付!");
            // }
          }
        } else {
          Util.log("查询不到订单!");
        }
      } else {
        Util.log("参数错误!");
      }
    } catch (Exception e) {
      Util.log("微信0.1抽奖通知出现异常!");
      e.printStackTrace();
    }
  }

  /**
   * 猜价微信通知
   * 
   * @param type 1 一等奖 2 二等奖 3 三等奖
   * @param activityId 活动id
   * @throws SQLException
   */
  public void guessWXNotice(Integer type, Long activityId) throws SQLException {
    try {
      List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
      Map<String, Object> item = new HashMap<String, Object>();
      Map<String, Object> params = new HashMap<String, Object>();
      String url = ConstParam.WX_URL + "/wx_msgtpl.php";
      if (type == 1) {
        if (activityId != null && activityId > 0) {
          params.put("activityId", activityId);
          params.put("prize", 1);
          Util.log("猜价格活动！~source：3");
          // params.put("source", 3);
          List<GrouponUserRecordPojo> grouponUserRecordList =
              grouponUserRecordService.listPage(params);
          Util.log("猜价格活动！~source：3/grouponUserRecordList=" + grouponUserRecordList.size());
          if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
            Util.log("guessWXNotice()添加订单消息：type=" + type + "/activityId=" + activityId);
            for (GrouponUserRecordPojo grouponUserRecordPojo : grouponUserRecordList) {
              item = new HashMap<String, Object>();
              item.put("activityId", StringUtil.checkVal(grouponUserRecordPojo.getActivityId()));
              item.put("productId", StringUtil.checkVal(grouponUserRecordPojo.getProductId()));
              item.put("productName", StringUtil.checkVal(grouponUserRecordPojo.getProductName()));
              item.put("prize", StringUtil.checkVal(grouponUserRecordPojo.getPrize()));
              item.put("price", StringUtil.checkVal(grouponUserRecordPojo.getPrice()));
              item.put("prizeTime", StringUtil.checkVal(StringUtil.dateString(new Date())));
              SysLoginPojo user =
                  sysLoginService.getfindByIdSysLogin(grouponUserRecordPojo.getUserId());
              if (user != null) {
                item.put("openid", StringUtil.checkVal(user.getOpenid()));
              } else {
                item.put("openid", "");
              }
              data.add(item);

              try {
                Util.log("addUserOrderNotice:grouponUserRecordPojo.getUserId()="
                    + grouponUserRecordPojo.getUserId() + "/grouponUserRecordPojo.getId()="
                    + grouponUserRecordPojo.getId());
                addUserOrderNotice(10, grouponUserRecordPojo.getUserId(),
                    grouponUserRecordPojo.getId());
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
            Util.log("猜价一等奖微信通知!");
            JSONArray json = JSONArray.fromObject(data);
            Util.log(json.toString());
            StringUtil.loadJson(url, "act=guess&type=" + type + "&data=" + json.toString(), "post");
          }
        } else {
          Util.log("活动id为空!");
        }
      } else if (type == 2 || type == 3) {
        if (activityId != null && activityId > 0) {
          params.put("activityId", activityId);
          params.put("activityType", 3);
          if (type == 2) {
            params.put("prize", 2);
          } else {
            params.put("prize", 3);
          }
          // params.put("source", 3);
          List<GrouponUserRecordPojo> grouponUserRecordList =
              grouponUserRecordService.listPage(params);
          if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
            for (GrouponUserRecordPojo grouponUserRecord : grouponUserRecordList) {
              item = new HashMap<String, Object>();
              SysLoginPojo user =
                  sysLoginService.getfindByIdSysLogin(grouponUserRecord.getUserId());
              if (user != null) {
                item.put("openid", StringUtil.checkVal(user.getOpenid()));
              } else {
                item.put("openid", "");
              }
              item.put("productName", StringUtil.checkVal(grouponUserRecord.getProductName()));
              item.put("price", StringUtil.checkVal(grouponUserRecord.getPrice()));
              item.put("prize", StringUtil.checkVal(grouponUserRecord.getPrize()));
              item.put("activityId", StringUtil.checkVal(activityId));
              item.put("productId", StringUtil.checkVal(grouponUserRecord.getProductId()));
              item.put("prizeTime", StringUtil.checkVal(StringUtil.dateString(new Date())));
              data.add(item);

              try {
                Util.log("addUserOrderNotice:grouponUserRecord.getUserId()="
                    + grouponUserRecord.getUserId() + "/grouponUserRecord.getId()="
                    + grouponUserRecord.getId());
                if (type == 2) {
                  addUserOrderNotice(11, grouponUserRecord.getUserId(), grouponUserRecord.getId());
                } else {
                  addUserOrderNotice(12, grouponUserRecord.getUserId(), grouponUserRecord.getId());
                }
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
            Util.log("猜价二/三等奖微信通知!");
            JSONArray json = JSONArray.fromObject(data);
            StringUtil.loadJson(url, "act=guess&type=" + type + "&data=" + json.toString(), "post");
          } else {
            Util.log("查询不到获奖的人!");
          }
        } else {

        }
      } else {
        Util.log("参数错误!");
      }
    } catch (Exception e) {
      Util.log("猜价通知出现异常!");
      e.printStackTrace();
    }
  }

  // #################################################支付成功处理##########################################################
  /**
   * 
   * 支付成功参团/开团处理
   * 
   * @param activityId 活动id
   * @param attendId 团记录id
   * @param uid 用户id
   * @param source 来源(1-普通拼团2-团免3-猜价)
   * @param orderId 订单id
   * @throw Exception
   * @return boolean
   */
  public synchronized Boolean groupOrderHandle2(Long activityId, Long attendId, Long uid,
      Integer source, Long orderId) throws Exception {
    Boolean flag = false;
    Map<String, Object> params = new HashMap<String, Object>();
    Util.log("判断是拼团还是猜价");
    if (source == 3 || source == 4) {
      OrderPojo orderPojo = new OrderPojo();
      orderPojo.setId(orderId);
      orderPojo.setIsSuccess(1);
      orderPojo.setGroupDate(new Date());
      orderPojo.setUpdateBy(uid);
      orderService.updateOrder(orderPojo);
      Util.log("修改一等奖为已获取奖励");
      if (source == 3) {
        if (uid != null && uid != 0 && activityId != null && activityId != 0) {
          params.clear();
          params.put("userId", uid);
          params.put("activityId", activityId);
          params.put("activityType", 3);
          params.put("prize", 1);
          List<GrouponUserRecordPojo> grouponUserRecordList =
              grouponUserRecordService.listPage(params);
          if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
            grouponUserRecordList.get(0).setIsRecCoupon(1);
            grouponUserRecordService.update(grouponUserRecordList.get(0));
          }
        }
      }
      return true;
    } else if (source == 1 || source == 2 || source == 5 || source == 6) {
      GrouponActivityPojo grouponActivityPojo = grouponActivityService.getById(activityId);
      OrderPojo osOrder = orderService.getfindByIdOrder(orderId);
      SysLoginPojo osUser = sysLoginService.getfindByIdSysLogin(uid);
      Util.log("判断是否存在团活动");
      if (grouponActivityPojo == null || osOrder == null || osUser == null) {
        return false;
      }
      Util.log("判断开团还是参团");
      if (activityId != null && uid != null && (attendId == null || attendId == 0)) {
        Util.log("开团");
        Util.log("添加团记录表");
        GrouponActivityRecordPojo grouponActivityRecordPojo = new GrouponActivityRecordPojo();
        grouponActivityRecordPojo.setActivityId(activityId);
        grouponActivityRecordPojo.setActivityType(source);
        Date nowTime = new Date();
        if (source == 5 || source == 6) {
          if (source == 5 || source == 7) {
            Util.log("设置0.1抽奖活动时间");
            if (DateTimeUtil.getTimeChange(nowTime, grouponActivityPojo.getEndTime()) > 360L) {
              grouponActivityRecordPojo.setBeginTime(nowTime);
              grouponActivityRecordPojo.setEndTime(GrouponService.getTimeAddHour(nowTime, 6));
            } else {
              grouponActivityRecordPojo.setBeginTime(nowTime);
              grouponActivityRecordPojo.setEndTime(grouponActivityPojo.getEndTime());
            }
          } else {
            Util.log("设置限时秒杀活动时间");
            if (DateTimeUtil.getTimeChange(nowTime, grouponActivityPojo.getEndTime()) > 1440L) {
              grouponActivityRecordPojo.setBeginTime(nowTime);
              grouponActivityRecordPojo.setEndTime(GrouponService.getTimeAddHour(nowTime, 24));
            } else {
              grouponActivityRecordPojo.setBeginTime(nowTime);
              grouponActivityRecordPojo.setEndTime(grouponActivityPojo.getEndTime());
            }
          }
        } else {
          grouponActivityRecordPojo.setBeginTime(nowTime);
          grouponActivityRecordPojo.setEndTime(GrouponService.getTimeAdd24(new Date()));
        }
        grouponActivityRecordPojo.setUserId(uid);
        grouponActivityRecordPojo.setNum(grouponActivityPojo.getNum());
        grouponActivityRecordPojo.setCreateBy(uid);
        grouponActivityRecordPojo.setCreateDate(new Date());
        grouponActivityRecordPojo.setUpdateBy(uid);
        grouponActivityRecordPojo.setUpdateDate(new Date());
        grouponActivityRecordService.add(grouponActivityRecordPojo);
        Util.log("抽奖和秒杀库存判断");
        Boolean stockF =
            oneStockCheck(source, grouponActivityPojo, grouponActivityRecordPojo.getId(), 1);
        if (!stockF) {
          Util.log("库存不足或出现异常!");
          return false;
        }
        Util.log("添加用户团记录表");
        GrouponUserRecordPojo grouponUserRecordPojo = new GrouponUserRecordPojo();
        grouponUserRecordPojo.setActivityId(activityId);
        grouponUserRecordPojo.setAttendId(grouponActivityRecordPojo.getId());
        grouponUserRecordPojo.setActivityType(source);
        grouponUserRecordPojo.setUserId(uid);
        if (source == 1) {
          Util.log("普通拼团");
          grouponUserRecordPojo.setStatus(0);
        } else if (source == 2) {
          Util.log("团免");
          grouponUserRecordPojo.setStatus(1);
          Util.log("查找团免券");
          params.clear();
          params.put("userId", uid);
          List<GroupFreeCouponPojo> groupFreeCouponList = groupFreeCouponService.listPage(params);
          if (groupFreeCouponList != null && groupFreeCouponList.size() > 0) {
            GroupFreeCouponPojo groupFreeCoupon = groupFreeCouponList.get(0);
            if (groupFreeCoupon != null) {
              Util.log("插入团免券id");
              grouponUserRecordPojo.setCouponId(groupFreeCoupon.getId());
              Util.log("更新团记录的拼得客用户id");
              if (groupFreeCoupon.getInvitationUserId() != null
                  && groupFreeCoupon.getInvitationUserId() > 0) {
                grouponActivityRecordPojo
                    .setInvitationUserId(groupFreeCoupon.getInvitationUserId());
                grouponActivityRecordService.update(grouponActivityRecordPojo);
              }
            }
          } else {
            Util.log("查询不到团免券!");
          }
        } else if (source == 5) {
          Util.log("0.1抽奖!");
          grouponUserRecordPojo.setStatus(3);
        } else if (source == 6) {
          Util.log("限时秒杀!");
          grouponUserRecordPojo.setStatus(4);
        } else if (source == 7) {
          Util.log("免费抽奖!");
          grouponUserRecordPojo.setStatus(5);
        }
        grouponUserRecordPojo.setIsHead(1);
        grouponUserRecordPojo.setAttendTime(new Date());
        grouponUserRecordPojo.setCreateBy(uid);
        grouponUserRecordPojo.setCreateDate(new Date());
        grouponUserRecordPojo.setUpdateBy(uid);
        grouponUserRecordPojo.setUpdateDate(new Date());
        grouponUserRecordService.add(grouponUserRecordPojo);
        Util.log("修改订单的团记录id为添加的");
        OrderPojo orderPojo = new OrderPojo();
        orderPojo.setId(orderId);
        orderPojo.setSourceId(grouponActivityRecordPojo.getId());
        orderService.updateOrder(orderPojo);
        // 微信通知
        Util.log("微信支付成功通知!");
        wxNotice2(3, osOrder, osUser);
        if (orderPojo.getSource() == 5) {
          Util.log("0.1抽奖开团微信通知!");
          oneWXNotice(1, orderId, uid);
        } else {
          Util.log("微信开团成功通知!");
          wxNotice2(4, osOrder, osUser);
        }
        // 添加开团推送
        if (source == 1) {
          openGroupPush(osOrder, osUser, grouponUserRecordPojo.getId());
        }
        Util.log("检查是否拼团人数是否已到");
        int againJoinNum =
            grouponUserRecordService.joinNumByAttendId(grouponActivityRecordPojo.getId());
        if (againJoinNum == grouponActivityRecordPojo.getNum() && source != 7) {
          Util.log("更改订单状态和团状态");
          params.clear();
          params.put("isSuccess", 0);
          params.put("sourceId", grouponActivityRecordPojo.getId());
          List<OrderPojo> orderList = orderService.listPage(params);
          Util.log("团状态改为拼团成功且订单状态改为待发货,插入成团时间");
          if (orderList != null && orderList.size() > 0) {
            OrderPojo orderup = null;
            for (OrderPojo order : orderList) {
              orderup = new OrderPojo();
              orderup.setIsSuccess(1);
              orderup.setGroupDate(new Date());
              orderup.setId(order.getId());
              orderService.updateOrder(orderup);
            }
          } else {
            Util.log("查询不到对应拼团支付成功的数据!");
          }
          Util.log("修改团记录为组团成功");
          GrouponActivityRecordPojo grouponActivityRecord = new GrouponActivityRecordPojo();
          grouponActivityRecord.setStatus(1);
          grouponActivityRecord.setUpdateDate(new Date());
          grouponActivityRecord.setId(grouponActivityRecordPojo.getId());
          grouponActivityRecordService.update(grouponActivityRecord);
          Boolean reduceStockF =
              oneReduceStock(source, grouponActivityPojo, grouponActivityRecordPojo.getId(), 1,
                  orderId);
          if (!reduceStockF) {
            Util.log("减库存失败或出现异常!");
            return false;
          }
          if (source == 5) {
            Util.log("0.1抽奖开团拼团成功直接设置为一等奖拼团成功");
            GrouponUserRecordPojo grouponUserRecord = new GrouponUserRecordPojo();
            grouponUserRecord.setId(grouponUserRecordPojo.getId());
            grouponUserRecord.setStatus(3);
            grouponUserRecord.setPrize(1);
            grouponUserRecordService.update(grouponUserRecord);
            Util.log("0.1抽奖拼团成功微信通知!");
            oneWXNotice(3, orderId, uid);
          }
          Util.log("开团成团通知!");
          wxNotice2(5, osOrder, osUser);
          Util.log("激活团免券!");
          String f = grantFreeCoupon(uid);
          Util.log("1".equals(f) == true ? "激活团免券成功" : "激活团免券失败");
        } else {
          Util.log("开团检查:未成团!");
        }
        return true;
      } else if (activityId != null && attendId != null && attendId != 0) {
        boolean activityFlag = true;
        Util.log("判断是否存在团记录");
        GrouponActivityRecordPojo grouponActivityRecordPojo =
            grouponActivityRecordService.getById(attendId);
        if (grouponActivityRecordPojo != null) {
          // 判断是否存在团记录->判断团是否已满->是否过期->是的话修改订单状态,
          Util.log("判断是否团活动是否到期");
          if (isRangeTime(new Date(), grouponActivityRecordPojo.getBeginTime(),
              grouponActivityRecordPojo.getEndTime())) {
            Util.log("判断拼团人数是否已到");
            int joinNum = grouponUserRecordService.joinNumByAttendId(attendId);
            if (joinNum >= grouponActivityRecordPojo.getNum()) {
              System.out.println("该团已满!");
              activityFlag = false;
            } else {
              int buyNum = 1;// 成团数量
              if (source == 5) {
                buyNum = 2;
              } else {
                buyNum = grouponActivityRecordPojo.getNum();
              }
              Util.log("限时秒杀和抽奖库存判断");
              Boolean stockF =
                  oneStockCheck(source, grouponActivityPojo, grouponActivityRecordPojo.getId(),
                      buyNum);
              if (!stockF) {
                return false;
              }
              Util.log("参团->添加用户团记录表");
              GrouponUserRecordPojo grouponUserRecordPojo = new GrouponUserRecordPojo();
              grouponUserRecordPojo.setActivityId(activityId);
              grouponUserRecordPojo.setAttendId(attendId);
              grouponUserRecordPojo.setActivityType(source);
              grouponUserRecordPojo.setUserId(uid);
              if (source == 1) {
                Util.log("普通拼团");
                grouponUserRecordPojo.setStatus(0);
              } else if (source == 2) {
                Util.log("团免");
                grouponUserRecordPojo.setStatus(1);
              } else if (source == 5) {
                Util.log("0.1抽奖!");
                grouponUserRecordPojo.setStatus(3);
              } else if (source == 6) {
                Util.log("限时秒杀!");
                grouponUserRecordPojo.setStatus(4);
              } else if (source == 7) {
                Util.log("免费抽奖!");
                grouponUserRecordPojo.setStatus(5);
              }
              grouponUserRecordPojo.setIsHead(0);
              grouponUserRecordPojo.setAttendTime(new Date());
              grouponUserRecordPojo.setCreateBy(uid);
              grouponUserRecordPojo.setCreateDate(new Date());
              grouponUserRecordPojo.setUpdateBy(uid);
              grouponUserRecordPojo.setUpdateDate(new Date());
              grouponUserRecordService.add(grouponUserRecordPojo);
              Util.log("再次检查拼团人数是否已到");
              grouponActivityRecordPojo = grouponActivityRecordService.getById(attendId);
              if (grouponActivityRecordPojo != null && source != 7) {
                int againJoinNum = grouponUserRecordService.joinNumByAttendId(attendId);
                if (againJoinNum == grouponActivityRecordPojo.getNum()) {
                  Util.log("更改订单状态和团状态");
                  if (source != 5) {
                    params.clear();
                    params.put("isSuccess", 0);
                    params.put("orderStatus", 2);
                    params.put("sourceId", grouponActivityRecordPojo.getId());
                    List<OrderPojo> orderList = orderService.listPage(params);
                    Util.log("团状态改为拼团成功且订单状态改为待发货,插入成团时间");
                    if (orderList != null && orderList.size() > 0) {
                      OrderPojo orderup = null;
                      for (OrderPojo order : orderList) {
                        orderup = new OrderPojo();
                        orderup.setIsSuccess(1);
                        // order.setOrderStatus(2);
                        orderup.setGroupDate(new Date());
                        orderup.setId(order.getId());
                        orderService.updateOrder(orderup);
                      }
                    } else {
                      Util.log("查询不到对应拼团支付成功的数据!");
                    }
                  }
                  Util.log("修改团记录为组团成功");
                  GrouponActivityRecordPojo grouponActivityRecord = new GrouponActivityRecordPojo();
                  grouponActivityRecord.setStatus(1);
                  grouponActivityRecord.setUpdateDate(new Date());
                  grouponActivityRecord.setId(grouponActivityRecordPojo.getId());
                  grouponActivityRecordService.update(grouponActivityRecord);
                  Util.log("微信拼团成功通知!");
                  if (source != 5) {
                    wxNotice2(5, osOrder, osUser);
                  }
                  if (source == 5 || source == 6) {
                    Util.log("根据订单详情skuId减库存");
                    Boolean reduceStockF =
                        oneReduceStock(source, grouponActivityPojo,
                            grouponActivityRecordPojo.getId(), 1, orderId);
                    if (!reduceStockF) {
                      Util.log("减库存失败或出现异常!");
                      return false;
                    }
                    // 0.1抽奖拼团成功,团长中一等奖,随机抽取一人
                    if (source == 5) {
                      oneGroupWinHandle(orderId, uid, attendId);
                    }
                    Util.log("激活团免券!");
                    String f = grantFreeCoupon(grouponActivityRecordPojo.getUserId());
                    Util.log("1".equals(f) == true ? "激活团免券成功" : "激活团免券失败");
                  }
                }
              } else {
                Util.log("参团支付成功通知!");
                wxNotice2(3, osOrder, osUser);
                activityFlag = false;
              }
            }
          } else {
            activityFlag = false;
          }
        } else {
          activityFlag = false;
        }
        if (!activityFlag) {
          Util.log("修改订单状态");
          OrderPojo orderPojo = new OrderPojo();
          orderPojo.setId(orderId);
          orderPojo.setIsSuccess(2);
          orderService.updateOrder(orderPojo);
        } else {
          flag = true;
        }
      }
    }
    return flag;
  }

  /**
   * 拼团业务处理.
   * 
   * @param activityId
   * @param attendId
   * @param uid
   * @param source
   * @param orderId
   * @return
   * @throws Exception
   * @throw
   * @return Boolean
   */
  public synchronized Boolean groupOrderHandle3(Long activityId, Long attendId, Long uid,
      Integer source, Long orderId) throws Exception {
    Map<String, Object> params = new HashMap<String, Object>();
    Util.log("判断是拼团还是猜价");
    if (source == 3 || source == 4) {
      OrderPojo orderPojo = new OrderPojo();
      orderPojo.setId(orderId);
      orderPojo.setIsSuccess(1);
      orderPojo.setGroupDate(new Date());
      orderPojo.setUpdateBy(uid);
      orderService.updateOrder(orderPojo);
      Util.log("修改一等奖为已获取奖励");
      if (source == 3) {
        if (uid != null && uid != 0 && activityId != null && activityId != 0) {
          params.clear();
          params.put("userId", uid);
          params.put("activityId", activityId);
          params.put("activityType", 3);
          params.put("prize", 1);
          List<GrouponUserRecordPojo> grouponUserRecordList =
              grouponUserRecordService.listPage(params);
          if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
            grouponUserRecordList.get(0).setIsRecCoupon(1);
            grouponUserRecordService.update(grouponUserRecordList.get(0));
          }
        }
      }
      return true;
    } else if (source == 1 || source == 2 || source == 5 || source == 6) {
      GrouponActivityPojo grouponActivityPojo = grouponActivityService.getById(activityId);
      OrderPojo osOrder = orderService.getfindByIdOrder(orderId);
      SysLoginPojo osUser = sysLoginService.getfindByIdSysLogin(uid);
      GrouponActivityRecordPojo grouponActivityRecordPojo = null;
      if (grouponActivityPojo == null || osOrder == null || osUser == null) {
        Util.log("不存在团活动");
        return false;
      }
      // 拼团数
      int buyNum = grouponActivityPojo.getNum();
      // 判断是否活动时间内
      Date actStartTime = grouponActivityPojo.getBeginTime();
      Date actEndTime = grouponActivityPojo.getEndTime();

      // attendID不为空时 判断参团信息
      if (attendId != null && attendId > 0) {
        Util.log("判断是否存在开团记录");
        grouponActivityRecordPojo = grouponActivityRecordService.getById(attendId);
        if (grouponActivityRecordPojo == null) {
          Util.log("开团记录不存在");
          return false;
        }
        buyNum = grouponActivityRecordPojo.getNum();
        if (buyNum > 1 && source == 5) {
          // 0.1抽奖只中2个人
          buyNum = 2;
        }
        actStartTime = grouponActivityRecordPojo.getBeginTime();
        actEndTime = grouponActivityRecordPojo.getEndTime();
      }

      Util.log("微信支付成功通知!");
      wxNotice2(3, osOrder, osUser);

      // 判断是否0.1夺宝和限时秒杀判断库存
      if (source == 5 || source == 6) {
        // 判断库存
        params.clear();
        params.put("productId", grouponActivityPojo.getProductId());
        params.put("status", 1);
        int stock = productSkuLinkService.getSkuStock(params);
        if (source == 6) {
          // 限时秒杀活动库存控制
          int actstock =
              grouponActivityPojo.getSurplusNum() == null ? 0 : grouponActivityPojo.getSurplusNum();
          stock = stock > actstock ? actstock : stock;
        }
        if (stock < buyNum) {
          // sku库存不足，订单拼团失败
          groupOrderFail(attendId, 0l, orderId);
          return false;
        }
      }

      // 判断时间是否有效
      Util.log("判断是否团活动是否到期");
      Date nowTime = new Date();
      if (!(nowTime.compareTo(actStartTime) >= 0 && nowTime.compareTo(actEndTime) <= 0)) {
        // 不在有效时间内，订单拼团失败
        Util.log("不在团活动有效期内");
        groupOrderFail(attendId, 0l, orderId);
        return false;
      }
      Util.log("判断开团还是参团");
      if (grouponActivityRecordPojo == null) {
        // 开团
        Util.log("添加开团记录表");
        grouponActivityRecordPojo = new GrouponActivityRecordPojo();
        grouponActivityRecordPojo.setActivityId(activityId);
        grouponActivityRecordPojo.setActivityType(source);

        if (source == 5) {
          Util.log("设置0.1抽奖活动时间");
          if (DateTimeUtil.getTimeChange(nowTime, actEndTime) > 360L) {
            grouponActivityRecordPojo.setBeginTime(nowTime);
            grouponActivityRecordPojo.setEndTime(GrouponService.getTimeAddHour(nowTime, 6));
          } else {
            grouponActivityRecordPojo.setBeginTime(nowTime);
            grouponActivityRecordPojo.setEndTime(actEndTime);
          }
        } else if (source == 6) {
          Util.log("设置限时秒杀活动时间");
          if (DateTimeUtil.getTimeChange(nowTime, actEndTime) > 1440L) {
            grouponActivityRecordPojo.setBeginTime(nowTime);
            grouponActivityRecordPojo.setEndTime(GrouponService.getTimeAddHour(nowTime, 24));
          } else {
            grouponActivityRecordPojo.setBeginTime(nowTime);
            grouponActivityRecordPojo.setEndTime(actEndTime);
          }
        } else {
          grouponActivityRecordPojo.setBeginTime(nowTime);
          grouponActivityRecordPojo.setEndTime(GrouponService.getTimeAddHour(nowTime, 24));
        }
        grouponActivityRecordPojo.setUserId(uid);
        grouponActivityRecordPojo.setNum(grouponActivityPojo.getNum());
        grouponActivityRecordPojo.setCreateBy(uid);
        grouponActivityRecordPojo.setCreateDate(nowTime);
        grouponActivityRecordPojo.setUpdateBy(uid);
        grouponActivityRecordPojo.setUpdateDate(nowTime);
        grouponActivityRecordService.add(grouponActivityRecordPojo);
        // 开团记录ID
        attendId = grouponActivityRecordPojo.getId();

        Util.log("添加用户开团记录表");
        GrouponUserRecordPojo grouponUserRecordPojo = new GrouponUserRecordPojo();
        grouponUserRecordPojo.setActivityId(activityId);
        grouponUserRecordPojo.setAttendId(attendId);
        grouponUserRecordPojo.setActivityType(source);
        grouponUserRecordPojo.setUserId(uid);
        if (source == 1) {
          grouponUserRecordPojo.setStatus(0);
        } else if (source == 2) {
          grouponUserRecordPojo.setStatus(1);
          Util.log("查找团免券");
          params.clear();
          params.put("userId", uid);
          List<GroupFreeCouponPojo> groupFreeCouponList = groupFreeCouponService.listPage(params);
          if (groupFreeCouponList != null && groupFreeCouponList.size() > 0) {
            GroupFreeCouponPojo groupFreeCoupon = groupFreeCouponList.get(0);
            if (groupFreeCoupon != null) {
              Util.log("插入团免券id");
              grouponUserRecordPojo.setCouponId(groupFreeCoupon.getId());
              Util.log("更新团记录的拼得客用户id");
              if (groupFreeCoupon.getInvitationUserId() != null
                  && groupFreeCoupon.getInvitationUserId() > 0) {
                GrouponActivityRecordPojo activityRecord = new GrouponActivityRecordPojo();
                activityRecord.setId(attendId);
                activityRecord.setInvitationUserId(groupFreeCoupon.getInvitationUserId());
                grouponActivityRecordService.update(activityRecord);
              }
            }
          } else {
            Util.log("查询不到团免券!");
          }
        } else if (source == 5) {
          grouponUserRecordPojo.setStatus(3);
        } else if (source == 6) {
          grouponUserRecordPojo.setStatus(4);
        }
        grouponUserRecordPojo.setIsHead(1);
        grouponUserRecordPojo.setAttendTime(nowTime);
        grouponUserRecordPojo.setCreateBy(uid);
        grouponUserRecordPojo.setCreateDate(nowTime);
        grouponUserRecordPojo.setUpdateBy(uid);
        grouponUserRecordPojo.setUpdateDate(nowTime);
        grouponUserRecordService.add(grouponUserRecordPojo);
        Util.log("更新订单的开团记录id");
        OrderPojo orderPojo = new OrderPojo();
        orderPojo.setId(orderId);
        orderPojo.setSourceId(attendId);
        orderService.updateOrder(orderPojo);
        // 微信通知
        if (source == 5) {
          Util.log("0.1抽奖开团微信通知!");
          oneWXNotice(1, orderId, uid);
        } else {
          Util.log("微信开团成功通知!");
          wxNotice2(4, osOrder, osUser);
          if (source == 1) {
            // 添加普通开团推送
            openGroupPush(osOrder, osUser, attendId);
          }
        }
      } else {
        // 参团
        // 判断参团人数是否已满
        int joinNum = grouponUserRecordService.joinNumByAttendId(attendId);
        if (joinNum >= grouponActivityRecordPojo.getNum()) {
          Util.log("该团已满,拼团失败！");
          // 订单失败处理
          groupOrderFail(attendId, 0l, orderId);
          return false;
        } else {
          Util.log("参团->添加用户团记录表");
          GrouponUserRecordPojo grouponUserRecordPojo = new GrouponUserRecordPojo();
          grouponUserRecordPojo.setActivityId(activityId);
          grouponUserRecordPojo.setAttendId(attendId);
          grouponUserRecordPojo.setActivityType(source);
          grouponUserRecordPojo.setUserId(uid);
          if (source == 1) {
            grouponUserRecordPojo.setStatus(0);
          } else if (source == 2) {
            grouponUserRecordPojo.setStatus(1);
          } else if (source == 5) {
            grouponUserRecordPojo.setStatus(3);
          } else if (source == 6) {
            grouponUserRecordPojo.setStatus(4);
          }
          grouponUserRecordPojo.setIsHead(0);
          grouponUserRecordPojo.setAttendTime(nowTime);
          grouponUserRecordPojo.setCreateBy(uid);
          grouponUserRecordPojo.setCreateDate(nowTime);
          grouponUserRecordPojo.setUpdateBy(uid);
          grouponUserRecordPojo.setUpdateDate(nowTime);
          grouponUserRecordService.add(grouponUserRecordPojo);
        }
      }

      // 检查是否成团
      Util.log("再次检查参团人数");
      int againJoinNum = grouponUserRecordService.joinNumByAttendId(attendId);
      if (againJoinNum == grouponActivityRecordPojo.getNum()) {
        Util.log("人数达到，成团处理");
        if (source == 5 || source == 6) {
          // 库存扣除
          Util.log("0.1夺宝/限时秒杀库存扣除！");
          ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
          productSkuLink.setId(osOrder.getSkuLinkId());
          productSkuLink.setMinusStock(buyNum);
          int status = 0;
          if (source == 5) {
            status = productSkuLinkService.updateProductSkuStock(productSkuLink);
          } else if (source == 6) {
            productSkuLink.setActivityId(activityId);
            status = productSkuLinkService.updateActivityProductSkuStock(productSkuLink);
          }
          if (status < 1) {
            // 库存扣除失败，订单失败处理
            groupOrderFail(attendId, 0l, orderId);
            return false;
          }
        }

        Util.log("修改团记录为组团成功");
        GrouponActivityRecordPojo grouponActivityRecord = new GrouponActivityRecordPojo();
        grouponActivityRecord.setStatus(1);
        grouponActivityRecord.setUpdateDate(new Date());
        grouponActivityRecord.setId(attendId);
        grouponActivityRecordService.update(grouponActivityRecord);

        if (source == 5) {
          // 0.1抽奖 开奖
          Util.log("查询团长,设置中奖");
          params.clear();
          params.put("attendId", attendId);
          params.put("isHead", 1);
          List<GrouponUserRecordPojo> grouponUserRecordList =
              grouponUserRecordService.listPage(params);
          GrouponUserRecordPojo gur = null;
          GrouponUserRecordPojo grouponUserRecord = null;
          if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
            gur = grouponUserRecordList.get(0);
            grouponUserRecord = new GrouponUserRecordPojo();
            grouponUserRecord.setId(gur.getId());
            grouponUserRecord.setStatus(3);
            grouponUserRecord.setPrize(1);
            grouponUserRecordService.update(grouponUserRecord);
            // 抽奖成功处理
            groupOrderSucc(gur.getAttendId(), gur.getUserId());
          }
          params.clear();
          params.put("attendId", attendId);
          params.put("isHead", 0);
          grouponUserRecordList = grouponUserRecordService.listPage(params);
          if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
            Util.log("随机抽取一人设置中奖");
            Random rand = new Random();
            int num = rand.nextInt(grouponUserRecordList.size());
            gur = grouponUserRecordList.get(num);
            grouponUserRecord = new GrouponUserRecordPojo();
            grouponUserRecord.setId(gur.getId());
            grouponUserRecord.setStatus(3);
            grouponUserRecord.setPrize(1);
            grouponUserRecordService.update(grouponUserRecord);
            // 抽奖成功处理
            groupOrderSucc(gur.getAttendId(), gur.getUserId());
          }
          Util.log("其余其他人订单设置拼团失败!");
          groupOrderFail(attendId, 0l, orderId);
        } else {
          // 拼团成功处理
          groupOrderSucc(attendId, 0l);
        }
        // 0.1抽奖拼团成功,团长中一等奖,随机抽取一人
        if (source == 5) {
          Util.log("0.1抽奖未中奖微信通知!");
          oneWXNotice(5, orderId, uid);
          Util.log("0.1抽奖中奖微信通知!");
          oneWXNotice(4, orderId, uid);
        } else {
          Util.log("微信拼团成功通知!");
          wxNotice2(5, osOrder, osUser);
        }
      }
    }
    return true;
  }

  /**
   * 拼团成功订单处理.
   * 
   * @param sourceId
   * @param uid
   * @throws Exception
   * @throw
   * @return void
   */
  public void groupOrderSucc(Long sourceId, Long uid) throws Exception {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("isSuccess", 0);
    params.put("orderStatus", 2);
    params.put("sourceId", sourceId);
    if (uid != null && uid != 0) {
      Util.log("单独用户处理为拼团成功");
      params.put("userId", uid);
    }
    List<OrderPojo> orderList = orderService.listPage(params);
    Util.log("订单状态处理为拼团成功,插入成团时间");
    if (orderList != null && orderList.size() > 0) {
      OrderPojo orderup = null;
      for (OrderPojo order : orderList) {
        orderup = new OrderPojo();
        orderup.setIsSuccess(1);
        orderup.setGroupDate(new Date());
        orderup.setId(order.getId());
        orderService.updateOrder(orderup);
      }
    } else {
      Util.log("查询不到对应拼团支付成功的数据!");
    }
  }

  /**
   * 拼团失败订单处理.
   * 
   * @param sourceId
   * @param uid
   * @throws Exception
   * @throw
   * @return void
   */
  public void groupOrderFail(Long sourceId, Long uid, Long orderId) throws Exception {
    OrderPojo orderup = null;
    if (sourceId != null && sourceId > 0) {
      // 拼团失败处理
      grouponFail(sourceId);
      // 订单失败处理
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("isSuccess", 0);
      params.put("sourceId", sourceId);
      if (uid != null && uid > 0) {
        params.put("userId", uid);
      }
      List<OrderPojo> orderList = orderService.listPage(params);
      Util.log("订单状态处理为拼团失败,插入成团时间");
      if (orderList != null && orderList.size() > 0) {
        for (OrderPojo order : orderList) {
          orderup = new OrderPojo();
          orderup.setIsSuccess(2);
          orderup.setGroupDate(new Date());
          orderup.setId(order.getId());
          orderService.updateOrder(orderup);
        }
      } else {
        Util.log("查询不到对应拼团支付成功的数据!");
      }
    } else if (orderId != null && orderId > 0) {
      // 单订单失败处理
      Util.log("单一订单状态处理为拼团失败,插入成团时间");
      orderup = new OrderPojo();
      orderup.setIsSuccess(2);
      orderup.setGroupDate(new Date());
      orderup.setId(orderId);
      orderService.updateOrder(orderup);
    }
  }

  /**
   * 拼团记录失败处理.
   * 
   * @param attendId
   * @throws Exception
   * @throw
   * @return void
   */
  public void grouponFail(Long attendId) throws Exception {
    Util.log("修改团记录为组团失败");
    GrouponActivityRecordPojo grouponActivityRecordPojo =
        grouponActivityRecordService.getById(attendId);
    if (grouponActivityRecordPojo != null && grouponActivityRecordPojo.getStatus() == 0) {
      GrouponActivityRecordPojo grouponActivityRecord = new GrouponActivityRecordPojo();
      grouponActivityRecord.setStatus(2);
      grouponActivityRecord.setUpdateDate(new Date());
      grouponActivityRecord.setId(attendId);
      grouponActivityRecordService.update(grouponActivityRecord);
    }
  }

  /**
   * 限时秒杀和0.1抽奖库存判断
   * 
   * @param source 来源
   * @param grouponActivityPojo 活动
   * @param attendId 团记录id
   * @param buyNum 购买数量
   * @return
   */
  public Boolean oneStockCheck(Integer source, GrouponActivityPojo grouponActivityPojo,
      Long attendId, Integer buyNum) {
    Boolean flag = true;
    try {
      Map<String, Object> params = new HashMap<String, Object>();
      if (source == 5 || source == 6) {
        params = new HashMap<String, Object>();
        params.put("productId", grouponActivityPojo.getProductId());
        params.put("status", 1);
        int stock = productSkuLinkService.getSkuStock(params);
        if (stock < buyNum) {
          Util.log("库存不足!处理为拼团失败!");
          params = new HashMap<String, Object>();
          if (attendId != null && attendId > 0) {
            params.put("sourceId", attendId);
            Util.log("根据sourceId订单处理为拼团失败!");
            List<OrderPojo> orderList = orderService.listPage(params);
            if (orderList != null && orderList.size() > 0) {
              OrderPojo orderPojo = null;
              for (OrderPojo order : orderList) {
                orderPojo = new OrderPojo();
                orderPojo.setId(order.getId());
                orderPojo.setIsSuccess(2);
                orderService.updateOrder(orderPojo);
              }
            } else {
              Util.log("查询不到订单!");
            }
            Util.log("团记录设置为拼团失败!");
            GrouponActivityRecordPojo grouponActivityRecord = new GrouponActivityRecordPojo();
            grouponActivityRecord.setId(attendId);
            grouponActivityRecord.setStatus(2);
            grouponActivityRecordService.update(grouponActivityRecord);
          }
          flag = false;
        }
        if (source == 6) {
          Util.log("限时秒杀判断活动库存!");
          if (grouponActivityPojo.getSurplusNum() == null
              || grouponActivityPojo.getSurplusNum() < buyNum) {
            params = new HashMap<String, Object>();
            if (attendId != null && attendId > 0) {
              params.put("sourceId", attendId);
              Util.log("根据sourceId订单处理为拼团失败!");
              List<OrderPojo> orderList = orderService.listPage(params);
              if (orderList != null && orderList.size() > 0) {
                OrderPojo orderPojo = null;
                for (OrderPojo order : orderList) {
                  orderPojo = new OrderPojo();
                  orderPojo.setId(order.getId());
                  orderPojo.setIsSuccess(2);
                  orderPojo.setGroupDate(new Date());
                  orderService.updateOrder(orderPojo);
                }
              } else {
                Util.log("查询不到订单!");
              }
              Util.log("团记录设置为拼团失败!");
              GrouponActivityRecordPojo grouponActivityRecord = new GrouponActivityRecordPojo();
              grouponActivityRecord.setId(attendId);
              grouponActivityRecord.setStatus(2);
              grouponActivityRecordService.update(grouponActivityRecord);
            }
            flag = false;
          }
        }
      }
    } catch (Exception e) {
      Util.log("检查库存出现异常!");
      flag = false;
      e.printStackTrace();
    }
    return flag;
  }

  /**
   * 限时秒杀和0.1抽奖减库存
   * 
   * @param source 来源
   * @param grouponActivityPojo 活动
   * @param attendId 团记录id
   * @param buyNum 购买数量
   * @param orderId 订单id
   * @param gurId 用户参团记录id
   * @param uid 用户id
   * @return
   */
  public Boolean oneReduceStock(Integer source, GrouponActivityPojo grouponActivityPojo,
      Long attendId, Integer buyNum, Long orderId) {
    Boolean flag = true;
    try {
      Util.log("抽奖和秒杀减库存");
      List<OrderDetailPojo> orderDetailList = orderDetailService.getOrderDetailWeb(orderId);
      if (orderDetailList != null && orderDetailList.size() > 0) {
        CartPojo cart = new CartPojo();
        cart.setProductId(grouponActivityPojo.getProductId());
        cart.setNum(1);
        if (source == 6) {
          cart.setActivityId(grouponActivityPojo.getId());
        }
        if (orderDetailList.get(0).getSkuLinkId() != null
            && orderDetailList.get(0).getSkuLinkId() > 0) {
          cart.setSkuLinkId(orderDetailList.get(0).getSkuLinkId());
          Util.log("根据订单详情skuId减库存");
          boolean stockFlag = sellerService.updateActivitySkuStock(cart);
          if (stockFlag) {
            Util.log("减sku库存成功!");
          } else {
            Util.log("减sku库存失败!");
            flag = false;
          }
        }
      } else {
        Util.log("查询不到订单详情!");
        flag = false;
      }
    } catch (Exception e) {
      Util.log("减库存出现异常!");
      flag = false;
      e.printStackTrace();
    }
    return flag;
  }

  /**
   * 0.1抽奖拼团成功处理
   * 
   * @return
   */
  public Boolean oneGroupWinHandle(Long orderId, Long uid, Long attendId) {
    Boolean flag = true;
    try {
      Map<String, Object> params = new HashMap<String, Object>();
      Util.log("0.1抽奖成团微信通知!");
      oneWXNotice(3, orderId, uid);
      List<Long> userIds = new ArrayList<Long>();
      Util.log("查询团长,设置中奖");
      params.clear();
      params.put("attendId", attendId);
      params.put("isHead", 1);
      List<GrouponUserRecordPojo> grouponUserRecordList = grouponUserRecordService.listPage(params);
      GrouponUserRecordPojo grouponUserRecord = null;
      if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
        grouponUserRecord = new GrouponUserRecordPojo();
        grouponUserRecord.setId(grouponUserRecordList.get(0).getId());
        grouponUserRecord.setStatus(3);
        grouponUserRecord.setPrize(1);
        grouponUserRecordService.update(grouponUserRecord);
        params.clear();
        params.put("userId", grouponUserRecordList.get(0).getUserId());
        params.put("sourceId", grouponUserRecordList.get(0).getAttendId());
        params.put("orderStatus", 2);
        params.put("isSuccess", 0);
        List<OrderPojo> orderprizes = orderService.listPage(params);
        if (orderprizes != null && orderprizes.size() > 0) {
          OrderPojo orderup = new OrderPojo();
          orderup.setIsSuccess(1);
          orderup.setGroupDate(new Date());
          orderup.setId(orderprizes.get(0).getId());
          orderService.updateOrder(orderup);
        }
        // 记录团长userId
        userIds.add(grouponUserRecordList.get(0).getUserId());
      }
      Util.log("随机抽取一人设置中奖");
      params.clear();
      params.put("attendId", attendId);
      params.put("isHead", 0);
      grouponUserRecordList = grouponUserRecordService.listPage(params);
      if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
        Random rand = new Random();
        int num = rand.nextInt(grouponUserRecordList.size());
        GrouponUserRecordPojo gur = new GrouponUserRecordPojo();
        gur = grouponUserRecordList.get(num);
        grouponUserRecord.setId(gur.getId());
        grouponUserRecord.setStatus(3);
        grouponUserRecord.setPrize(1);
        grouponUserRecordService.update(grouponUserRecord);
        Util.log("随机抽取一人设置中奖");
        params.clear();
        params.put("userId", gur.getUserId());
        params.put("sourceId", gur.getAttendId());
        params.put("orderStatus", 2);
        params.put("isSuccess", 0);
        List<OrderPojo> orderprizes = orderService.listPage(params);
        if (orderprizes != null && orderprizes.size() > 0) {
          OrderPojo orderup = new OrderPojo();
          orderup.setIsSuccess(1);
          orderup.setGroupDate(new Date());
          orderup.setId(orderprizes.get(0).getId());
          orderService.updateOrder(orderup);
        }
        // 记录中奖人userId
        userIds.add(gur.getUserId());
      }
      Util.log("其余其他人订单设置拼团失败!");
      params.clear();
      params.put("userIds", userIds);
      params.put("sourceId", attendId);
      List<OrderPojo> orderList1 = orderService.listPage(params);
      OrderPojo orderPojo = null;
      if (orderList1 != null && orderList1.size() > 0) {
        for (OrderPojo order : orderList1) {
          orderPojo = new OrderPojo();
          orderPojo.setId(order.getId());
          orderPojo.setIsSuccess(2);
          orderPojo.setGroupDate(new Date());
          orderService.updateOrder(orderPojo);
        }
      }
      Util.log("0.1抽奖未中奖微信通知!");
      oneWXNotice(5, orderId, uid);
      Util.log("0.1抽奖中奖微信通知!");
      oneWXNotice(4, orderId, uid);
    } catch (SQLException e) {
      flag = false;
      Util.log("0.1抽奖拼团成功处理出现异常!");
      e.printStackTrace();
    }
    return flag;
  }


  /**
   * 微信通知
   * 
   * @param type(1-发货2-退款3-支付成功4-开团成功5-拼团成功)
   * @param orderPojo 订单
   * @param user 用户
   * @return
   * @throws SQLException
   */
  public Integer wxNotice2(Integer type, OrderPojo orderPojo, SysLoginPojo user)
      throws SQLException {
    try {
      if (orderPojo == null || user == null) {
        Util.log("查询不到订单或用户!");
      } else {
        // 添加订单消息
        Util.log("wxNotice2()添加订单消息：type=" + type + "/oid=" + orderPojo.getId() + "/uid="
            + user.getId());
        if (type != null && type == 1 && orderPojo != null && user != null) {
          addUserOrderNotice(6, user.getId(), orderPojo.getId());
        } else if (type != null && type == 2 && orderPojo != null && user != null) {
          addUserOrderNotice(7, user.getId(), orderPojo.getId());
        } else if (type != null && type == 3 && orderPojo != null && user != null) {
          addUserOrderNotice(1, user.getId(), orderPojo.getId());
        } else if (type != null && type == 4 && orderPojo != null && user != null) {
          addUserOrderNotice(2, user.getId(), orderPojo.getId());
          // } else if (type != null && type == 5 && orderPojo != null && user != null) {
          // addUserOrderNotice(3, user.getId(), orderPojo.getId());
        } else if (type != null && type == 6 && orderPojo != null && user != null) {
          addUserOrderNotice(8, user.getId(), orderPojo.getId());
          // } else {
          // Util.log("参数错误!");
        }

        if (type != null && type == 1 && StringUtils.isNotBlank(user.getOpenid())) {
          // 发货通知
          String url = ConstParam.WX_URL + "/wx_msgtpl.php";
          String params =
              "act=delivery&oid=" + orderPojo.getId() + "&openid=" + user.getOpenid()
                  + "&logisticsName=" + orderPojo.getLogisticsName() + "&logisticsNo="
                  + orderPojo.getLogisticsNo() + "&productName=" + orderPojo.getProductName()
                  + "&buyNum=" + orderPojo.getNum() + "&orderNo=" + orderPojo.getOrderNo();
          StringUtil.loadJson(url, params, "post");
        } else if (type != null && type == 2 && StringUtils.isNotBlank(user.getOpenid())) {
          // 退款通知
          String url =
              ConstParam.WX_URL + "/wx_msgtpl.php?act=refund&oid=" + orderPojo.getId() + "&openid="
                  + user.getOpenid() + "&uid=" + user.getId();
          StringUtil.loadJson(url);
        } else if (type != null && type == 3 && orderPojo.getId() != null
            && StringUtils.isNotBlank(user.getOpenid())) {
          // 支付成功通知
          String url = ConstParam.WX_URL + "/wx_msgtpl.php";
          double factPrice = orderPojo.getFactPrice();
          // if (orderPojo.getUsedPrice() != null && orderPojo.getUsedPrice() > 0) {
          // factPrice = factPrice - orderPojo.getUsedPrice();
          // }
          String params =
              "act=pay&openid=" + user.getOpenid() + "&factPrice=" + factPrice + "&productName="
                  + orderPojo.getProductName();
          StringUtil.loadJson(url, params, "post");
        } else if (type != null && type == 4 && orderPojo.getId() != null
            && StringUtils.isNotBlank(user.getOpenid())) {
          // 开团成功通知
          String url = ConstParam.WX_URL + "/wx_msgtpl.php";
          double factPrice = orderPojo.getFactPrice();
          // if (orderPojo.getUsedPrice() != null && orderPojo.getUsedPrice() > 0) {
          // factPrice = factPrice - orderPojo.getUsedPrice();
          // }
          String params =
              "act=open&openid=" + user.getOpenid() + "&factPrice=" + factPrice + "&productName="
                  + orderPojo.getProductName() + "&consignee=" + orderPojo.getConsignee()
                  + "&consigneePhone=" + orderPojo.getConsigneePhone() + "&consigneeAddress="
                  + orderPojo.getConsigneeAddress() + "&orderNo=" + orderPojo.getOrderNo();
          StringUtil.loadJson(url, params, "post");
        } else if (type != null && type == 5) {
          // 拼团成功通知
          Map<String, Object> param = new HashMap<String, Object>();
          Map<String, Object> item = new HashMap<String, Object>();
          try {
            String url = ConstParam.WX_URL + "/wx_msgtpl.php";
            param.put("sourceId", orderPojo.getSourceId());
            param.put("payStatus", 1);
            param.put("isSuccess", 1);
            List<OrderPojo> orderList = orderService.listPage(param);
            List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
            if (orderList != null && orderList.size() > 0) {
              for (OrderPojo order : orderList) {
                if (order.getPayMethod() == 8) {
                  item = new HashMap<String, Object>();
                  if (user != null && StringUtils.isNotBlank(user.getOpenid())) {
                    item.put("openid", user.getOpenid());
                  } else {
                    item.put("openid", "");
                  }
                  item.put("orderNo", StringUtil.checkVal(order.getOrderNo()));
                  double factPrice = order.getFactPrice();
                  if (order.getUsedPrice() != null && order.getUsedPrice() > 0) {
                    factPrice = factPrice - order.getUsedPrice();
                  }
                  item.put("factPrice", StringUtil.checkVal(factPrice));
                  data.add(item);
                }

                if (order.getUserId() != null && order.getId() != null) {
                  addUserOrderNotice(3, order.getUserId(), order.getId());
                }

                Util.log("addUserOrderNotice:order.getUserId()=" + order.getUserId()
                    + "/order.getId()=" + order.getId());
              }
            }
            JSONArray json = JSONArray.fromObject(data);
            StringUtil.loadJson(url, "act=join&data=" + json.toString(), "post");
          } catch (Exception e) {
            e.printStackTrace();
          }
        } else {
          Util.log("参数错误!");
        }
      }
    } catch (Exception e) {
      Util.log("微信通知出现异常!");
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 开团推送
   * 
   * @param orderPojo 订单
   * @param user 用户
   */
  public void openGroupPush(OrderPojo orderPojo, SysLoginPojo user, Long attendId) {
    try {
      int pid =
          Integer.parseInt(StringUtil.checkVal(orderPojo.getProvince() == null ? 0 : orderPojo
              .getProvince()));
      SysAreaPojo sysAreaPojo = sysAreaService.getNameById(pid);
      sellerService.addGrouponPushApi(StringUtil.checkVal(orderPojo.getProductImage()),
          sysAreaPojo == null ? "" : StringUtil.checkVal(sysAreaPojo.getName()),
          StringUtil.checkVal(user.getName()), orderPojo.getId(), attendId);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 获取团免券
   * 
   * @return
   * @throws SQLException
   */
  public String grantFreeCoupon(Long userId) throws SQLException {
    String result = "0";
    try {
      if (userId != null && userId > 0) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("used", 0);
        params.put("status", 1);
        params.put("nowTime", new Date());
        int i = groupFreeCouponService.countBy(params);
        if (i == 0) {
          Util.log("激活用户团免券");
          GroupFreeCouponPojo groupFreeCouponPojo = new GroupFreeCouponPojo();
          groupFreeCouponPojo.setUserId(userId);
          groupFreeCouponPojo.setUsed(0);
          groupFreeCouponPojo.setStatus(1);
          groupFreeCouponPojo.setActiveTime(new Date());
          groupFreeCouponPojo.setInvalidTime(GrouponService.getTimeAdd24(groupFreeCouponPojo
              .getActiveTime()));
          groupFreeCouponPojo.setInvitationUserId(0l);
          groupFreeCouponService.update(groupFreeCouponPojo);
          result = "1";
        } else {
          Util.log("用户:" + userId + " 已有团免券!");
        }
      } else {
        Util.log("用户id为空!");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * 判断一个时间是否在某个时间范围内
   * 
   * @param nowTime 当前时间
   * @param beginTime 开始时间
   * @param endTime 结束时间
   * @return
   */
  public Boolean isRangeTime(Date nowTime, Date beginTiem, Date endTime) {
    Boolean flag = false;
    if (nowTime.getTime() >= beginTiem.getTime() && nowTime.getTime() <= endTime.getTime()) {
      flag = true;
    }
    return flag;
  }

  /**
   * 0.1拼团成功发送短信
   * 
   * @param activityId
   */
  public void smsOneOpenWin(Long activityId, Long attendId) {
    try {
      if (activityId != null && activityId > 0 && attendId != null && attendId > 0) {
        String text = "恭喜您中 奖啦！您参与的猜价格/0.1夺宝活动开奖啦！商品在1-3天内打包发出，更多关注http://dwz.cn/4JhfzF";
        Map<String, Object> params = new HashMap<String, Object>();
        params.clear();
        params.put("activityId", activityId);
        params.put("status", 3);
        params.put("activityType", 5);
        params.put("attendId", attendId);
        params.put("prize", 1);
        List<GrouponUserRecordPojo> grouponUserRecordList =
            grouponUserRecordService.listPage(params);
        if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
          for (GrouponUserRecordPojo grouponUserRecord1 : grouponUserRecordList) {
            if (grouponUserRecord1.getLoginName() != null) {
              SmsSendUtil.sendSMS(grouponUserRecord1.getLoginName(), text);
            }
          }
        }
      } else {
        Util.log("活动id、团id不能为空!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // #################################################支付成功处理##########################################################

  /**
   * 
   * 添加订单消息
   * 
   * @param type 类型1-支付成功2-开团成功3-拼团成功4-抽奖未中奖5-抽奖已中奖6-发货7-退款8-拼团失败9-参团成功10-一等奖11-二等奖12-三等奖
   * @param userId 用户ID
   * @param orderId 订单ID（type=10、11、12时为团购用户记录ID）
   * @throws Exception
   * @throw
   * @return void
   */
  public void addUserOrderNotice(Integer type, Long userId, Long orderId) throws Exception {
    Map<String, Object> params = new HashMap<String, Object>();
    if (type == 10 || type == 11 || type == 12) {// 猜价格一、二、三等奖
      params.put("type", 5);// 使用抽奖模板
    } else {
      params.put("type", type);
    }
    params.put("isDelete", 0);
    params.put("status", 1);
    params.put("orderBy", " create_by desc");
    params.put("pageNo", 0);
    params.put("pageSize", 1);
    List<NoticeTemplatePojo> noticeTemplatePojos = noticeTemplateService.listPage(params);

    if (noticeTemplatePojos != null && noticeTemplatePojos.size() > 0) {
      NoticeTemplatePojo noticeTemplatePojo = noticeTemplatePojos.get(0);
      if (noticeTemplatePojo.getTemplate() != null && !"".equals(noticeTemplatePojo.getTemplate())
          && noticeTemplatePojo.getTemplate().length() > 2) {

        JSONObject jsonObject = JSONObject.fromObject(noticeTemplatePojo.getTemplate());
        OrderPojo orderPojo = null;
        if (type == 10 || type == 11 || type == 12) {// 猜价格一、二、三等奖
          params.clear();
          params.put("id", orderId);
          params.put("pageNo", 0);
          params.put("pageSize", 1);
          List<GrouponUserRecordPojo> grouponUserRecordPojos =
              grouponUserRecordService.listPage(params);
          if (grouponUserRecordPojos != null && grouponUserRecordPojos.size() > 0) {
            orderPojo = new OrderPojo();
            orderPojo.setFactPrice(0.0);
            orderPojo.setProductName(grouponUserRecordPojos.get(0).getProductName());
            orderPojo.setCreateDate(grouponUserRecordPojos.get(0).getCreateDate());
          }
        } else {
          orderPojo = orderService.getfindByIdOrder(orderId);
        }

        if (orderPojo != null) {
          try {
            UserOrderNoticePojo userOrderNoticePojo = new UserOrderNoticePojo();
            // if (type == 10 || type == 11 || type == 12) {// 猜价格一、二、三等奖
            // userOrderNoticePojo.setType(5);// 使用抽奖模板
            // } else {
            userOrderNoticePojo.setType(type);
            // }
            userOrderNoticePojo.setUserId(userId);
            userOrderNoticePojo.setTitle(noticeTemplatePojo.getTitle());

            jsonObject.put("img",
                "/upfiles/product/" + StringUtil.checkVal(orderPojo.getProductImage()));
            jsonObject.put("name", StringUtil.checkVal(orderPojo.getProductName()));

            double factPrice = orderPojo.getFactPrice();
            // if (orderPojo.getUsedPrice() != null && orderPojo.getUsedPrice() > 0) {
            // factPrice = factPrice - orderPojo.getUsedPrice();
            // }

            if (type == 1) {// 支付成功
              jsonObject.put("price", "支付金额：" + StringUtil.checkVal(factPrice) + "元");
              jsonObject.put("pname", "商品信息：" + StringUtil.checkVal(orderPojo.getProductName()));


            } else if (type == 2) {// 开团成功
              jsonObject.put("price", "订单金额：" + StringUtil.checkVal(factPrice) + "元");
              jsonObject.put("pname", "商品详情：" + StringUtil.checkVal(orderPojo.getProductName()));

              jsonObject.put("address",
                  "收货信息：" + StringUtil.checkVal(orderPojo.getConsigneeAddress()));
              jsonObject.put("orderNo", "订单编号：" + StringUtil.checkVal(orderPojo.getOrderNo()));
            } else if (type == 3) {// 拼团成功
              jsonObject.put("price", "订单金额：" + StringUtil.checkVal(factPrice) + "元");

              jsonObject.put("orderNo", "订单号：" + StringUtil.checkVal(orderPojo.getOrderNo()));
            } else if (type == 4) {// 抽奖未中奖
              jsonObject.put("price", "支付金额：" + StringUtil.checkVal(factPrice) + "元");
              jsonObject.put("pname", "商品名称：" + StringUtil.checkVal(orderPojo.getProductName()));

              jsonObject.put("drawDate", "抽奖时间：" + StringUtil.checkVal(orderPojo.getCreateDate()));
              jsonObject.put("drawStatus",
                  "抽奖结果：" + StringUtil.checkVal(jsonObject.get("drawStatus")));
            } else if (type == 5) {// 抽奖已中奖
              jsonObject.put("price", "支付金额：" + StringUtil.checkVal(factPrice) + "元");
              jsonObject.put("pname", "商品名称：" + StringUtil.checkVal(orderPojo.getProductName()));

              jsonObject.put("drawDate", "抽奖时间：" + StringUtil.checkVal(orderPojo.getCreateDate()));
              jsonObject.put("drawStatus",
                  "抽奖结果：" + StringUtil.checkVal(jsonObject.get("drawStatus")));
            } else if (type == 6) {// 发货
              jsonObject.put("pname", "商品信息：" + StringUtil.checkVal(orderPojo.getProductName()));

              jsonObject
                  .put("express", "快递公司：" + StringUtil.checkVal(orderPojo.getLogisticsName()));
              jsonObject
                  .put("expressNo", "快递单号：" + StringUtil.checkVal(orderPojo.getLogisticsNo()));
              jsonObject.put("num", "商品数量：" + StringUtil.checkVal(orderPojo.getCount()));
            } else if (type == 7) {// 退款
              jsonObject.put("price", "退款金额：" + StringUtil.checkVal(factPrice) + "元");
              // UserOrderRefundPojo userOrderRefundPojo =
              // userOrderRefundService.findUserOrderRefundByOid(orderPojo.getOrderDid());
              // if (userOrderRefundPojo != null) {
              //
              // } else {
              // Util.log(">>>添加订单模板，查询不到退款信息>>>");
              // }

              jsonObject
                  .put("refundDate", "退款时间：" + StringUtil.checkVal(orderPojo.getRefundDate()));
              jsonObject.put("orderNo", "订单号：" + StringUtil.checkVal(orderPojo.getOrderNo()));
              jsonObject.put("refundItem",
                  "退款项目：" + StringUtil.checkVal(jsonObject.get("refundItem")));
              jsonObject.put("refundReason",
                  "退款原因：" + StringUtil.checkVal(jsonObject.get("refundReason")));
            } else if (type == 8) {// 拼团失败
              // jsonObject.put("factPrice", "商品金额：" + StringUtil.checkVal(orderPojo.getFactPrice())
              // + "元");
              jsonObject.put("price", "商品金额：" + StringUtil.checkVal(factPrice) + "元");
              jsonObject.put("refundPrice", "退款金额：" + StringUtil.checkVal(factPrice) + "元");
              jsonObject.put("pname", "拼团商品：" + StringUtil.checkVal(orderPojo.getProductName()));

              // jsonObject
              // .put("refundDate", "失败时间：" + StringUtil.checkVal(orderPojo.getRefundDate()));
              jsonObject.put("orderNo", "订单号：" + StringUtil.checkVal(orderPojo.getOrderNo()));
              // jsonObject.put("refundItem",
              // "退款项目：" + StringUtil.checkVal(jsonObject.get("refundItem")));
              // jsonObject.put("refundReason",
              // "失败原因：" + StringUtil.checkVal(jsonObject.get("refundReason")));
            } else if (type == 9) {// 参团成功
              jsonObject.put("price", "订单金额：" + StringUtil.checkVal(factPrice) + "元");
              jsonObject.put("pname", "商品详情：" + StringUtil.checkVal(orderPojo.getProductName()));
              jsonObject.put("address",
                  "收货信息：" + StringUtil.checkVal(orderPojo.getConsigneeAddress()));

              jsonObject.put("orderNo", "订单编号：" + StringUtil.checkVal(orderPojo.getOrderNo()));
            } else if (type == 10) {// 猜价格一等奖
              jsonObject.put("info", "一等奖，获得该商品");
              jsonObject.put("price", "支付金额：" + StringUtil.checkVal(factPrice) + "元");
              jsonObject.put("pname", "商品名称：" + StringUtil.checkVal(orderPojo.getProductName()));

              jsonObject.put("drawDate", "猜价时间：" + StringUtil.checkVal(orderPojo.getCreateDate()));
              jsonObject.put("drawStatus", "猜价结果：一等奖，获得该商品");
            } else if (type == 11) {// 猜价格二等奖
              jsonObject.put("info", "二等奖，获得5元代金券");
              jsonObject.put("price", "支付金额：" + StringUtil.checkVal(factPrice) + "元");
              jsonObject.put("pname", "商品名称：" + StringUtil.checkVal(orderPojo.getProductName()));

              jsonObject.put("drawDate", "猜价时间：" + StringUtil.checkVal(orderPojo.getCreateDate()));
              jsonObject.put("drawStatus", "猜价结果：二等奖，获得5元代金券");
            } else if (type == 12) {// 猜价格三等奖
              jsonObject.put("info", "三等奖，获得3元代金券");
              jsonObject.put("price", "支付金额：" + StringUtil.checkVal(factPrice) + "元");
              jsonObject.put("pname", "商品名称：" + StringUtil.checkVal(orderPojo.getProductName()));

              jsonObject.put("drawDate", "猜价时间：" + StringUtil.checkVal(orderPojo.getCreateDate()));
              jsonObject.put("drawStatus", "猜价结果：三等奖，获得3元代金券");
            }

            if (jsonObject.get("type") != null) {
              // 信息内容（
              // type：1-商品详情页，2-订单详情页，3-团详情页，4-首页，5-普通拼团，6-猜价格，7-免费抽奖，8-专题，9-专题分类，10-77专区，11-0.1夺宝）
              if ("1".equals(jsonObject.get("type"))) {// 商品详情页
                // 判断是不是单独购订单
                if (orderPojo.getSource() == 4) {
                  params = new HashMap<String, Object>();
                  params.put("productId", orderPojo.getProductId());
                  params.put("type", 1);
                  params.put("isDefault", 1);
                  params.put("pageNo", 0);
                  params.put("pageSize", 1);
                  List<GrouponActivityPojo> grouponActivityPojos =
                      grouponActivityService.listPage(params);
                  if (grouponActivityPojos != null && grouponActivityPojos.size() > 0) {
                    jsonObject.put("param",
                        StringUtil.checkVal(grouponActivityPojos.get(0).getId()));
                  } else {
                    jsonObject.put("param", "0");
                  }
                } else {
                  jsonObject.put("param", StringUtil.checkVal(orderPojo.getActivityId()));
                }
              } else if ("2".equals(jsonObject.get("type"))) {// 订单详情页
                jsonObject.put("param", StringUtil.checkVal(orderPojo.getId()));
              } else if ("3".equals(jsonObject.get("type"))) {// 团详情页
                jsonObject.put("param", StringUtil.checkVal(orderPojo.getSourceId()));
              }
            } else {
              Util.log(">>>消息模板模板信息type字段为空>>>");
            }

            userOrderNoticePojo.setContent(SellerService.noticeJsonToStr(jsonObject));

            int i = userOrderNoticeService.add(userOrderNoticePojo);
            if (i > 0) {
              Util.log(">>>添加订单消息成功：type-" + type + "/userId-" + userId + "/orderId-" + orderId
                  + ">>>");
            } else {
              Util.log(">>>添加订单消息失败：type-" + type + "/userId-" + userId + "/orderId-" + orderId
                  + ">>>");
            }
          } catch (Exception e) {
            e.printStackTrace();
            Util.log(">>>添加订单消息失败：type-" + type + "/userId-" + userId + "/orderId-" + orderId
                + ">>>");
          }
        } else {
          Util.log(">>>查询不到要添加消息模板的订单>>>");
        }
      } else {
        Util.log(">>>消息模板模板信息为空>>>");
      }
    } else {
      Util.log(">>>查询不到消息模板>>>");
    }
  }

  /**
   * 
   * 根据orderId获取订单使用的优惠金额
   * 
   * @param orderId
   * @return
   * @throw
   * @return double
   * @throws Exception
   */
  public double getCouponPriceByOrderId(Long orderId) throws Exception {
    // 判断优惠券使用
    Map<String, Object> orderMap = new HashMap<String, Object>();
    double m = 0.0;
    orderMap.put("orderId", orderId);
    orderMap.put("status", 1);
    List<CouponOrderPojo> couponOrders = couponService.getcouponOrderList(orderMap);
    if (couponOrders != null && couponOrders.size() > 0) {
      CouponOrderPojo couponOrder = couponOrders.get(0);
      // orderMap.clear();
      // orderMap.put("couponNo", couponOrder.getCouponNo());
      // UserCouponPojo userCouponPojo = couponService.getUserCouponByNo(orderMap);
      // if (userCouponPojo != null) {
      // org.json.JSONObject json = new org.json.JSONObject(userCouponPojo.getContent());
      // if (userCouponPojo.getType() == 1 && json.get("om") != null) {
      // double orderPay = json.getDouble("om");
      // if (json.get("m") != null && orderPay <= order.getFactPrice()) {
      // m = json.getDouble("m");
      // }
      // } else if (userCouponPojo.getType() == 2 && json.get("m") != null) {
      // m = json.getDouble("m");
      // }
      // }
      m = couponOrder.getUsedPrice();
    }
    return m;
  }

  /**
   * 特定活动发送优惠券.
   * 
   * @param activityId 团活动ID
   * @param attendId 参团ID
   * @throws Exception
   * @throw
   * @return void
   */
  public void grantCoupon(Long activityId, Long attendId) throws Exception {
    if (attendId == null || attendId <= 0) {
      return;
    }
    Map<String, Object> params = new HashMap<String, Object>();
    // 团编号
    params.put("attendId", attendId);
    // 活动ID
    params.put("activityId", activityId);
    // 普通拼团类型
    params.put("activityType", 1);
    // 拼团成功
    params.put("isSuccess", 1);
    // 拼团成功用户
    List<GrouponUserRecordPojo> users = grouponUserRecordService.findAttendOrders(params);
    Long userId = 0L;
    Long orderId = 0L;
    Long couponId = 0L;
    int count = 0;
    GrouponUserRecordPojo user = null;
    UserCouponPojo coupon = null;
    UserCouponPojo updcoupon = null;
    if (users != null && users.size() > 0) {
      count = users.size();
      // TODO:红包商品数据
      if (activityId == 3643) {
        couponId = 102L;
      } else if (activityId == 3645) {
        couponId = 103L;
      } else if (activityId == 3646) {
        couponId = 104L;
      } else if (activityId == 3883) {
        couponId = 106L;
      }
      if (couponId > 0) {
        params.clear();
        params.put("couponId", couponId);
        params.put("status", 1);
        params.put("used", 0);
        params.put("userId", 0);
        params.put("pageNo", 0);
        params.put("pageSize", count);
        List<UserCouponPojo> coupons = couponService.getuserCouponList(params);
        if (coupons != null && coupons.size() > 0) {
          if (count > coupons.size()) {
            count = coupons.size();
          }
        } else {
          count = 0;
        }
        int flag = 0;
        Util.log(">>>>>> 参团ID:" + attendId + " 券数量：" + count + "!");
        for (int i = 0; i < count; i++) {
          user = users.get(i);
          coupon = coupons.get(i);
          userId = user.getUserId();
          orderId = user.getOrderId();
          updcoupon = new UserCouponPojo();
          updcoupon.setUserId(userId);
          updcoupon.setStatus(1);
          updcoupon.setCouponNo(coupon.getCouponNo());
          updcoupon.setVersions(coupon.getVersion());
          flag = couponService.updateUserCoupon(updcoupon);
          if (flag > 0) {
            // 修改订单状态
            if (orderId > 0) {

            }
          } else {
            Util.log(">>>>>> 用户ID:" + userId + " 发券[" + coupon.getCouponNo() + "]失败!");
          }
        }
      }
    }
  }
}
