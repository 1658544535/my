package com.tzmb2c.web.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.OrderRefundPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderRefundService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.SysDictService;

public class OrderRefundAction extends SuperAction {

  @Autowired
  private OrderRefundService orderRefundService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private OrderDetailService orderDetailService;
  private OrderRefundPojo orderRefund, orderReturnPj, orderReturnPjWeb;
  private OrderPojo order, orderPojo;
  private OrderDetailPojo orderDetailPojo, orderDetail;
  private String result;
  private String[] tids;
  private int refundStatus;
  private Long orderDetailId;
  private String data;
  private String beginTime;
  private String endTime;



  public String getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(String beginTime) {
    this.beginTime = beginTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public OrderDetailPojo getOrderDetail() {
    return orderDetail;
  }

  public void setOrderDetail(OrderDetailPojo orderDetail) {
    this.orderDetail = orderDetail;
  }

  public OrderDetailPojo getOrderDetailPojo() {
    return orderDetailPojo;
  }

  public void setOrderDetailPojo(OrderDetailPojo orderDetailPojo) {
    this.orderDetailPojo = orderDetailPojo;
  }

  public int getRefundStatus() {
    return refundStatus;
  }

  public void setRefundStatus(int refundStatus) {
    this.refundStatus = refundStatus;
  }

  public Long getOrderDetailId() {
    return orderDetailId;
  }

  public void setOrderDetailId(Long orderDetailId) {
    this.orderDetailId = orderDetailId;
  }

  public OrderRefundPojo getOrderReturnPjWeb() {
    return orderReturnPjWeb;
  }

  public void setOrderReturnPjWeb(OrderRefundPojo orderReturnPjWeb) {
    this.orderReturnPjWeb = orderReturnPjWeb;
  }

  public OrderPojo getOrder() {
    return order;
  }

  public void setOrder(OrderPojo order) {
    this.order = order;
  }

  public OrderRefundPojo getOrderReturnPj() {
    return orderReturnPj;
  }

  public void setOrderReturnPj(OrderRefundPojo orderReturnPj) {
    this.orderReturnPj = orderReturnPj;
  }

  public OrderRefundPojo getOrderRefund() {
    return orderRefund;
  }

  public void setOrderRefund(OrderRefundPojo orderRefund) {
    this.orderRefund = orderRefund;
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

  public String getOrderRefundCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(orderRefundService.orderRefundAllCount(orderRefund));
    return SUCCESS;
  }

  public String orderRefundAllList() {
    JSONArray json = JSONArray.fromObject(orderRefundService.orderRefundAllList(orderRefund, page));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String orderRefundDeleteId() {
    if (tids != null) {
      orderRefundService.orderRefundDeleteId(tids);
      // FileUtil.alertMessageBySkip("删除成功", "gomanageTicketRule.do");
      FileUtil.alertMessageBySkip("删除成功！", "orderRefund.do");
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "orderRefund.do");
    }

    return null;
  }

  /***
   * 后台：添加一条退货申请
   * 
   * @return
   * @throws IOException
   */
  public String addOrderRefund() throws IOException {
    try {
      // orderReturnPj = new OrderRefundPojo();
      SysLoginPojo loginPojo = UserUtil.getUser();
      orderReturnPj.setUserId(loginPojo.getId());
      orderReturnPj.setStatus(0);
      orderRefundService.insertOrderRefund(orderReturnPj);
      orderPojo = new OrderPojo();
      orderPojo.setId(orderReturnPj.getOrderId());
      orderPojo.setIsCancelOrder(2);// 等待审核
      orderService.updateIsCancelOrder(orderPojo);// 修改订单状态:等待审核
      // orderService.isCancel(order);//将订单状态设为取消
    } catch (SQLException e) {

      e.printStackTrace();
    }
    FileUtil.alertMessageBySkip("操作成功！", "userOrderRefundManage.do?refundStatus=1");
    return null;
  }

  /***
   * 前台：添加一条退货申请
   * 
   * @return
   * @throws Throwable
   */
  public String addOrderRefundWeb() throws Throwable {
    try {
      // Long i = orderDetailPojo.getId();
      // orderDetail = orderDetailService.getfindByIdOrderDetail(i);
      // orderReturnPjWeb = new OrderRefundPojo();
      // orderReturnPjWeb.setOrderId(orderDetail.getOrderId());
      // orderReturnPjWeb.setProductId(orderDetail.getProductId());
      // orderReturnPj = orderRefundService.getorderRefundDetail(orderReturnPjWeb);
      // Long orid = orderReturnPj.getId();

      Long oid = orderDetailPojo.getId();// 获取用户订单详情id
      orderDetailPojo = orderDetailService.findOrderDetailById(oid);// 通过orderDetailId查找订单详情
      SysLoginPojo loginPojo = UserUtil.getWebUser();
      orderReturnPjWeb = new OrderRefundPojo();
      orderReturnPjWeb.setUserId(loginPojo.getId());// 用户id
      orderReturnPjWeb.setOrderId(orderDetailPojo.getOrderId());// 订单id
      orderReturnPjWeb.setLoginname(orderDetailPojo.getLoginname());// 用户昵称
      orderReturnPjWeb.setShopId(orderDetailPojo.getShopId());// 商铺id
      orderReturnPjWeb.setProductId(orderDetailPojo.getProductId());// 商品id
      orderReturnPjWeb.setProductName(orderDetailPojo.getProductName());// 商品名称
      orderReturnPjWeb.setStockPriceOld(orderDetailPojo.getStockPriceOld());//
      orderReturnPjWeb.setStockPrice(orderDetailPojo.getStockPrice());//
      orderReturnPjWeb.setStatus(1);// 状态
      orderReturnPjWeb.setRefundNum(orderRefund.getRefundNum());// 退货数量
      orderReturnPjWeb.setRefundReason(orderRefund.getRefundReason());// 退货原因
      orderReturnPjWeb.setRefundType(orderRefund.getRefundType());// 退货类型
      orderReturnPjWeb.setDetailId(orderDetail.getId());
      orderReturnPjWeb.setType(orderRefund.getType());
      if (orderRefund.getImages() != null) {
        File fin = new File(orderRefund.getImages());
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/orderRefund")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/orderRefund/", fin);
        orderReturnPjWeb.setImages(file_name);
      } else {
        orderReturnPjWeb.setImages("");
      }
      orderRefundService.insertOrderRefund(orderReturnPjWeb);// 插入数据库
      orderDetailPojo = orderDetailService.findOrderDetailById(oid);
      orderDetailPojo.setReStatus(1L);
      orderDetailService.updateOrderDetail(orderDetailPojo);// 更新订单详情状态
      // 判断订单re_status是否都有
      // Map<String, Object> map2=new HashMap<String,Object>();
      // map2.put("orderId",orderDetailPojo.getOrderId());
      // map2.put("userId", orderDetailPojo.getUserId());
      // int d=orderRefundService.getNOrefundDetail(map2);
      // if(d==0){
      // OrderPojo o=new OrderPojo();
      // o.setIsCancelOrder(1);
      // o.setId(orderDetailPojo.getOrderId());
      // orderService.updateIsCancelOrder(o);
      // }


    } catch (SQLException e) {

      e.printStackTrace();
    }

    return SUCCESS;
  }

  public String deleOrderRefund() throws SQLException {
    try {
      orderRefundService.delOrderRefund(orderRefund.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String goFindOrderRefund() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("orderRefundPojo", orderRefundService.findOrderRefundById(orderRefund.getId()));
    ac.put("status", sysDictService.getSysDictListByType("status"));
    return SUCCESS;
  }

  public String updateOrderRefund() throws Exception {

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      orderRefund.preUpdate(loginPojo);
    }
    orderRefundService.updateOrderRefund(orderRefund);
    FileUtil.alertMessageBySkip("修改成功！", "orderRefund.do");

    return null;
  }

  public String refundListByorderId() throws Exception {
    // long s=orderRefund.getId();
    // long d=orderDetailPojo.getOrderId();
    // ActionContext ac = ActionContext.getContext();
    // ac.put("orderRefundList", orderRefundService.getfindByIdOrderRefund(s));
    // ac.put("orderDetailList", orderDetailService.getfindByOrderIdOrderDetail(d));
    // ac.put("orderList", orderService.getfindByIdOrder(d));
    // Long oid = orderDetailPojo.getId();//获取用户订单详情id
    // System.out.println(oid);
    // orderDetailPojo = orderDetailService.findOrderDetailById(oid);//通过orderDetailId查找订单详情
    // orderReturnPjWeb = new OrderRefundPojo();
    // orderReturnPjWeb.setOrderId(orderDetailPojo.getOrderId());//订单id
    // orderReturnPjWeb.setLoginname(orderDetailPojo.getLoginname());//用户昵称
    // orderReturnPjWeb.setShopId(orderDetailPojo.getShopId());//商铺id
    // orderReturnPjWeb.setProductId(orderDetailPojo.getProductId());//商品id
    // orderReturnPjWeb.setProductName(orderDetailPojo.getProductName());//商品名称
    // orderReturnPjWeb.setStockPriceOld(orderDetailPojo.getStockPriceOld());//
    // orderReturnPjWeb.setStockPrice(orderDetailPojo.getStockPrice());//
    // orderReturnPjWeb.setStatus(0);//状态
    // //orderReturnPjWeb.setRefundNum(orderDetailPojo.getNum());//退货数量
    // orderReturnPjWeb.setRefundReason(orderRefund.getRefundReason());//退货原因
    // orderReturnPjWeb.setRefundType(orderRefund.getRefundType());//退货类型
    return SUCCESS;
  }

  // 点击“已审核”状态跳转方法
  public String returnsSeller() throws Exception {
    return SUCCESS;
  }

  // 买家发货方法
  public String returnsSellerSuccess() throws Exception {
    UserUtil.getWebUser();
    // orderDetail.getOrderId();//获取订单id
    Long oids = orderDetail.getOrderId();
    Long pids = orderDetail.getProductId();// 获取产品id
    orderReturnPj = new OrderRefundPojo();

    orderReturnPj.setOrderId(oids);
    orderReturnPj.setProductId(pids);
    orderReturnPjWeb = orderRefundService.getorderRefundDetail(orderReturnPj);
    System.out.println(orderReturnPjWeb.getId());
    // orderReturnPjWeb.setUserId(loginPojo.getId());
    orderReturnPjWeb.setStatus(3);
    orderReturnPjWeb.setLogistics(orderRefund.getLogistics());
    orderReturnPjWeb.setLogType(orderRefund.getLogType());
    orderRefundService.updateOrderRefund(orderReturnPjWeb);
    Long oid = orderDetail.getId();// 获取用户订单详情id
    // Long oid = 526L;
    orderDetailPojo = orderDetailService.findOrderDetailById(oid);
    orderDetailPojo.setReStatus(3L);
    orderDetailService.updateOrderDetail(orderDetailPojo);// 更新订单详情状态

    return SUCCESS;
  }

  public String refundcont() throws Exception {
    OrderRefundPojo orderRefundPojo1 = new OrderRefundPojo();
    orderRefundPojo1.setStatus(1);
    orderRefundPojo1.setBeginTime(beginTime);
    orderRefundPojo1.setEndTime(endTime);
    data = "";
    data = data + orderRefundService.orderRefundAllCount(orderRefundPojo1) + ":";
    orderRefundPojo1.setStatus(3);
    data = data + orderRefundService.orderRefundAllCount(orderRefundPojo1);
    return SUCCESS;
  }

}
