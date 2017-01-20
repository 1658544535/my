package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tencent.common.Util;
import com.tzmb2c.business.service.GrouponService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.SmsSendUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.OrderShipPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.OrderShipService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;

public class OrderShipAction extends SuperAction {

  @Autowired
  private OrderShipService orderShipService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private GrouponService grouponService;
  private OrderShipPojo orderShip;
  private OrderPojo order;
  private String result;
  private String[] tids;
  private String os;
  private String remarks;// 客服留言
  private Integer pageNoVal;
  private String formParam;



  public Integer getPageNoVal() {
    return pageNoVal;
  }

  public void setPageNoVal(Integer pageNoVal) {
    this.pageNoVal = pageNoVal;
  }

  public String getFormParam() {
    return formParam;
  }

  public void setFormParam(String formParam) {
    this.formParam = formParam;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }

  public OrderPojo getOrder() {
    return order;
  }

  public void setOrder(OrderPojo order) {
    this.order = order;
  }

  public OrderShipPojo getOrderShip() {
    return orderShip;
  }

  public void setOrderShip(OrderShipPojo orderShip) {
    this.orderShip = orderShip;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getOrderShipCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(orderShipService.orderShipAllCount(orderShip));
    ActionContext ac = ActionContext.getContext();
    ac.put("orderStatus", sysDictService.getSysDictListByType("ship_order_status"));
    ac.put("consigneeType", sysDictService.getSysDictListByType("consignee_type"));

    return SUCCESS;
  }

  public String orderShipAllList() {
    JSONArray json = JSONArray.fromObject(orderShipService.orderShipAllList(orderShip, page));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String orderShipDeleteId() {
    if (tids != null) {
      orderShipService.orderShipDeleteId(tids);
      // FileUtil.alertMessageBySkip("删除成功", "gomanageTicketRule.do");
      FileUtil.alertMessageBySkip("删除成功！", "orderShip.do");
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "orderShip.do");
    }

    return null;
  }

  public String deleOrderShip() throws SQLException {
    try {
      orderShipService.delOrderShip(orderShip.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String goFindOrderShip() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("orderShipPojo", orderShipService.findOrderShipById(orderShip.getId()));
    return SUCCESS;
  }

  public String updateOrderShip() throws Exception {

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      orderShip.preUpdate(loginPojo);
    }
    orderShipService.updateOrderShip(orderShip);
    FileUtil.alertMessageBySkip("修改成功！", "orderShip.do");

    return null;
  }

  public String goOrderShipAdd() throws Exception {
    String ads = null;
    OrderPojo orderPojo = orderService.findOrderById(order.getId());
    ActionContext ac = ActionContext.getContext();
    List<SysDictPojo> sysDicPojoList = sysDictService.getSysDictListByType("logistics_type");
    ac.put("sysDicPojoList", sysDicPojoList);
    ac.put("orderPojo", orderPojo);
    ac.put("consigneeType", sysDictService.getSysDictListByType("consignee_type"));
    if (orderPojo.getOrderStatus() == 1) {
      FileUtil.alertMessageBySkip("未付款，不能给予发货！", "order.do?os=" + os);
    } else if (orderPojo.getOrderStatus() == 2) {
      ads = SUCCESS;
    } else {
      FileUtil.alertMessageBySkip("已发货，不能重复发货！", "order.do?os=" + os);
    }
    return ads;
  }

  public String addOrderShip() throws Exception {

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      orderShip.prePersist(loginPojo);
    }
    OrderShipPojo orderShipList = orderShipService.findByIdOrderShip(orderShip.getOrderId());
    if (orderShipList != null) {
      orderShipService.updateOrderShip(orderShip);
      order = new OrderPojo();
      order.setId(orderShip.getOrderId());
      order.setUpdateBy(loginPojo.getId());
      order.setOrderStatus(3);
      order.setSendDate(new Date());
      order.setRemarks(orderShip.getRemarks());
      order.setAutoRecTime(GrouponService.getTimeAddDay(new Date(), 15));
      orderService.updateOrderStatus(order);
      String content =
          "【拼得好】您购买的宝贝已由" + orderShipList.getLogisticsNameCN() + "快递发出，正在奔向您的途中，快递单号："
              + orderShipList.getLogisticsNo();
      // MxtongSMSCaptchaUtil.sendMxtongSMS(orderShip.getConsigneePhone(), content);
      SmsSendUtil.sendSMS(order.getConsigneePhone(), content);

      try {
        Util.log("addUserOrderNotice:orderShipList.getUserId()=" + orderShipList.getUserId()
            + "/orderShip.getOrderId()=" + orderShip.getOrderId());
        grouponService.addUserOrderNotice(6, orderShipList.getUserId(), orderShip.getOrderId());
      } catch (Exception e) {
        e.printStackTrace();
      }

      FileUtil.alertMessageBySkip("该发货信息修改成功！", "order.do?os=" + os + "&pageNoVal=" + pageNoVal
          + "&" + formParam);
      return null;
    } else {
      orderShipService.insertOrderShip(orderShip);
      order = new OrderPojo();
      order.setId(orderShip.getOrderId());
      order.setUpdateBy(loginPojo.getId());
      order.setOrderStatus(3);
      order.setSendDate(new Date());
      order.setRemarks(orderShip.getRemarks());
      order.setAutoRecTime(GrouponService.getTimeAddDay(new Date(), 15));
      orderService.updateOrderStatus(order);
      // 订单已发货自动发送“发货短信”
      if (orderShip != null && orderShip.getConsigneePhone() != null) {
        orderShipList = orderShipService.findByIdOrderShip(orderShip.getOrderId());
        String content =
            "【拼得好】您购买的宝贝已由" + orderShipList.getLogisticsNameCN() + "快递发出，正在奔向您的途中，快递单号："
                + orderShipList.getLogisticsNo();
        // MxtongSMSCaptchaUtil.sendMxtongSMS(orderShip.getConsigneePhone(), content);
        SmsSendUtil.sendSMS(orderShip.getConsigneePhone(), content);
        Util.log(content);
        // 微信发货通知
        order = orderService.findOrderById(orderShip.getOrderId());
        if (order != null) {
          // 查询用户信息
          SysLoginPojo user = sysLoginService.findSysLoginById(order.getUserId());
          if (user == null) {
            Util.log("查询不到用户!");
          } else {
            try {
              if (order.getPayMethod() == 2 || order.getPayMethod() == 8) {
                Util.log("微信支付的订单");
                grouponService.wxNotice(1, order.getId(), user.getOpenid(), user.getId());
              } else {
                Util.log("不是微信支付的订单");
                grouponService.addUserOrderNotice(6, user.getId(), order.getId());
              }
            } catch (Exception e) {
              System.out.println("发货通知失败！order.getPayMethod()=" + order.getPayMethod());
              e.printStackTrace();
            }
          }
        } else {
          System.out.println("查询不到订单!");
        }
      }
      FileUtil.alertMessageBySkip("发货成功,已短信通知该买家！", "order.do?os=" + os + "&pageNoVal=" + pageNoVal
          + "&" + formParam);
      return null;
    }
  }
}
