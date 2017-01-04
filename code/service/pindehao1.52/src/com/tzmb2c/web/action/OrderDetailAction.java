package com.tzmb2c.web.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.OrderShipPojo;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.OrderShipService;

public class OrderDetailAction extends SuperAction {

  @Autowired
  private OrderDetailService orderDetailService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private OrderShipService orderShipService;
  private OrderDetailPojo orderDetail, shopDetail;
  private OrderPojo orderWeb, orderUserComment, orderDetailConfirm, orderPojo;
  private OrderShipPojo orderShipPojo;
  private String result;
  private String[] tids;
  private Long orderIdWeb;
  private List<OrderDetailPojo> userComment, orderDetailConfirmList, orderDetailWeb;
  private String logisticsName;// 物流名称
  private String logisticsNo;// 物流单号
  private String jsonStr;
  private String id;
  private String remarks, sellerMessage;
  private String contentGuide;

  public String getContentGuide() {
    return contentGuide;
  }

  public void setContentGuide(String contentGuide) {
    this.contentGuide = contentGuide;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getSellerMessage() {
    return sellerMessage;
  }

  public void setSellerMessage(String sellerMessage) {
    this.sellerMessage = sellerMessage;
  }

  public String getJsonStr() {
    return jsonStr;
  }

  public void setJsonStr(String jsonStr) {
    this.jsonStr = jsonStr;
  }

  public String getLogisticsName() {
    return logisticsName;
  }

  public void setLogisticsName(String logisticsName) {
    this.logisticsName = logisticsName;
  }

  public String getLogisticsNo() {
    return logisticsNo;
  }

  public void setLogisticsNo(String logisticsNo) {
    this.logisticsNo = logisticsNo;
  }

  public OrderDetailPojo getShopDetail() {
    return shopDetail;
  }

  public void setShopDetail(OrderDetailPojo shopDetail) {
    this.shopDetail = shopDetail;
  }

  public List<OrderDetailPojo> getOrderDetailConfirmList() {
    return orderDetailConfirmList;
  }

  public void setOrderDetailConfirmList(List<OrderDetailPojo> orderDetailConfirmList) {
    this.orderDetailConfirmList = orderDetailConfirmList;
  }

  public OrderPojo getOrderPojo() {
    return orderPojo;
  }

  public void setOrderPojo(OrderPojo orderPojo) {
    this.orderPojo = orderPojo;
  }

  public OrderPojo getOrderDetailConfirm() {
    return orderDetailConfirm;
  }

  public void setOrderDetailConfirm(OrderPojo orderDetailConfirm) {
    this.orderDetailConfirm = orderDetailConfirm;
  }

  public OrderPojo getOrderUserComment() {
    return orderUserComment;
  }

  public void setOrderUserComment(OrderPojo orderUserComment) {
    this.orderUserComment = orderUserComment;
  }

  public List<OrderDetailPojo> getUserComment() {
    return userComment;
  }

  public void setUserComment(List<OrderDetailPojo> userComment) {
    this.userComment = userComment;
  }

  public OrderPojo getOrderWeb() {
    return orderWeb;
  }

  public void setOrderWeb(OrderPojo orderWeb) {
    this.orderWeb = orderWeb;
  }

  public Long getOrderIdWeb() {
    return orderIdWeb;
  }

  public void setOrderIdWeb(Long orderIdWeb) {
    this.orderIdWeb = orderIdWeb;
  }

  public List<OrderDetailPojo> getOrderDetailWeb() {
    return orderDetailWeb;
  }

  public void setOrderDetailWeb(List<OrderDetailPojo> orderDetailWeb) {
    this.orderDetailWeb = orderDetailWeb;
  }

  public OrderDetailPojo getOrderDetail() {
    return orderDetail;
  }

  public void setOrderDetail(OrderDetailPojo orderDetail) {
    this.orderDetail = orderDetail;
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

  public String getOrderDetailCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    if (contentGuide != null) {
      if (orderDetail != null) {
        orderDetail.setContentGuide(contentGuide);
        page.setRowCount(orderDetailService.orderDetailAllCount(orderDetail));
      } else {
        OrderDetailPojo orderDetail = new OrderDetailPojo();
        orderDetail.setContentGuide(contentGuide);
        page.setRowCount(orderDetailService.orderDetailAllCount(orderDetail));
      }
    }
    return SUCCESS;
  }

  public String orderDetailAllList() {
    if (page == null) {
      page = new Pager();
    }
    List<OrderDetailPojo> orderDetailPojos = new ArrayList<OrderDetailPojo>();
    if (orderDetail == null) {
      OrderDetailPojo orderDetail = new OrderDetailPojo();
      if (contentGuide != null) {
        orderDetail.setContentGuide(contentGuide);
        orderDetailPojos = orderDetailService.orderDetailAllList(orderDetail, page);
      }
    } else {
      if (contentGuide != null) {
        orderDetail.setContentGuide(contentGuide);
        orderDetailPojos = orderDetailService.orderDetailAllList(orderDetail, page);
      }
    }
    JSONArray json = JSONArray.fromObject(orderDetailPojos);
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String orderDetailDeleteId() {
    if (tids != null) {
      orderDetailService.orderDetailDeleteId(tids);
      // FileUtil.alertMessageBySkip("删除成功", "gomanageTicketRule.do");
      FileUtil.alertMessageBySkip("删除成功！", "orderDetail.do");
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "orderDetail.do");
    }

    return null;
  }

  public String deleOrderDetail() throws SQLException {
    try {
      orderDetailService.delOrderDetail(orderDetail.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  public String goFindOrderDetail() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("orderDetailPojo", orderDetailService.findOrderDetailById(orderDetail.getId()));
    return SUCCESS;
  }

  public String updateOrderDetail() throws Exception {

    orderDetailService.updateOrderDetail(orderDetail);
    FileUtil.alertMessageBySkip("修改成功！", "orderDetail.do");

    return null;
  }

  // 前台调用：采购商的订单详情
  public String getOrderDetailByIdWeb() throws Exception {
    // orderIdWeb = orderPojo.getId();
    orderDetailWeb = orderDetailService.getOrderDetailWeb(orderIdWeb);
    orderWeb = orderService.getfindByIdOrderWeb(orderIdWeb);
    return SUCCESS;
  }

  // 前台调用：供应商的订单详情
  public String getSupplyOrderDetailByIdWeb() throws Exception {
    orderDetailWeb = orderDetailService.getOrderDetailWeb(orderIdWeb);
    orderWeb = orderService.getfindByIdOrderWeb(orderIdWeb);
    return SUCCESS;
  }

  /**
   * 跳转到商品评价的页面 前台
   * 
   * @return
   * @throws Exception
   */
  public String goUserCommentWeb() throws Exception {
    Long orderId = orderPojo.getId();// 获取订单ID
    shopDetail = orderDetailService.getfindByOrderIdOrderDetail(orderId);// 获取店铺详情
    userComment = orderDetailService.getfindByUserIdOrderDetail(orderId);// 获取订单详情(一个订单可以对应多个详情)
    return SUCCESS;
  }

  /***
   * 跳转到订单详情页面 前台
   * 
   * @return
   * @throws Exception
   */
  public String goMyOrderDetailWeb() throws Exception {
    orderDetailConfirm = orderService.getfindByIdOrderWeb(orderPojo.getId());
    orderDetailConfirmList = orderDetailService.getfindByUserIdOrderDetail(orderPojo.getId());
    List<OrderDetailPojo> temp = new ArrayList<OrderDetailPojo>();
    for (OrderDetailPojo orderDetail : orderDetailConfirmList) {
      orderDetail.setAllStockPrice(orderDetail.getStockPrice() * orderDetail.getNum());
      temp.add(orderDetail);
    }
    ActionContext ac = ActionContext.getContext();
    ac.put("orderDetailConfirmList", temp);
    ac.put("orderDetailConfirm", orderDetailConfirm);
    return SUCCESS;
  }

  /***
   * 获取物流信息
   * 
   * @return
   */
  public String getLogisticsInformation() {
    // 获取物流详情信息
    try {
      Long orderId = orderPojo.getId();// 获取订单ID
      orderShipPojo = orderShipService.findByIdOrderShip(orderId);
    } catch (SQLException e) {

      e.printStackTrace();
    }
    if (orderShipPojo == null) {
      try {
        jsonStr = "0";// 未发货，没有获取到发货的物流信息
      } catch (Exception e) {

        e.printStackTrace();
      }
    } else {
      logisticsName = orderShipPojo.getLogisticsName();// 获取物流名称
      logisticsNo = orderShipPojo.getLogisticsNo();// 获取物流单号

      String url =
          "http://www.kuaidi100.com/query?type=" + logisticsName + "&postid=" + logisticsNo;

      try {

        String str = getJsonString(url);
        JSONObject jsonobject = JSONObject.fromObject(str);
        // 快递100返回的错误是重新封装json返回快递单号和快递类型
        if (jsonobject.get("status").equals("201") || jsonobject.get("status").equals("403")
            || jsonobject.get("status").equals("400")) {
          String str1 =
              "{'nu':'" + logisticsNo + "','com':'" + logisticsName
                  + "','data':[{'time':'','context':'暂时没有该物流信息'}]}";
          str1 = str1.replaceAll("\'", "\"");
          jsonStr = str1;
        } else {
          jsonStr = str;
        }
      } catch (Exception e) {

        e.printStackTrace();
      }
    }
    // System.out.println("jsonStr:"+jsonStr);
    return SUCCESS;
  }

  /**
   * 访问服务器地址，返回Json字符串
   * 
   * @param urlPath
   * @return
   * @throws Exception
   */
  protected String getJsonString(String urlPath) throws Exception {
    URL url = new URL(urlPath);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.connect();
    InputStream inputStream = connection.getInputStream();
    // 对应的字符编码转换
    Reader reader = new InputStreamReader(inputStream, "UTF-8");
    BufferedReader bufferedReader = new BufferedReader(reader);
    String str = null;
    StringBuffer sb = new StringBuffer();
    while ((str = bufferedReader.readLine()) != null) {
      sb.append(str);
    }
    reader.close();
    connection.disconnect();
    return sb.toString();
  }

  public String code() throws Exception {
    remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("id", id);
    map.put("remarks", remarks);
    orderService.updateorders(map);
    return SUCCESS;
  }

  public String codeWeb() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("id", id);
    map.put("sellerMessage", sellerMessage);
    orderService.updateorders(map);
    return SUCCESS;
  }
}
