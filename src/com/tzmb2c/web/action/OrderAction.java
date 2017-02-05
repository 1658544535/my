package com.tzmb2c.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;
import com.alipay.util.UtilDate;
import com.opensymphony.xwork2.ActionContext;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Event;
import com.pingplusplus.model.Webhooks;
import com.tencent.WXPay;
import com.tencent.common.Configure;
import com.tencent.common.Util;
import com.tencent.common.XMLUtil;
import com.tencent.protocol.pay_protocol.PayResData;
import com.tencent.protocol.refund_protocol.RefundReqData;
import com.tencent.protocol.refund_protocol.RefundResData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.tzmb2c.business.service.GrouponService;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.business.service.WalletService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.PropertiesHelper;
import com.tzmb2c.utils.RandomUtils;
import com.tzmb2c.utils.SmsSendUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.utils.export.excel.ExcelProcessor;
import com.tzmb2c.utils.pingxx.PingxxUtil;
import com.tzmb2c.web.dao.OrderDao;
import com.tzmb2c.web.pojo.AgencyPojo;
import com.tzmb2c.web.pojo.AlipayOrderInfoPojo;
import com.tzmb2c.web.pojo.AlipayRefundInfo;
import com.tzmb2c.web.pojo.CartPojo;
import com.tzmb2c.web.pojo.CouponOrderPojo;
import com.tzmb2c.web.pojo.CouponPojo;
import com.tzmb2c.web.pojo.DeliveryAddressPojo;
import com.tzmb2c.web.pojo.GrouponActivityPojo;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.OrderPayinfoPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.OrderShipPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductRestrictionPojo;
import com.tzmb2c.web.pojo.ProductSellPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.ResultDetailData;
import com.tzmb2c.web.pojo.SysAreaPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserCouponPojo;
import com.tzmb2c.web.pojo.UserDealLogPojo;
import com.tzmb2c.web.pojo.UserOrderRefundPojo;
import com.tzmb2c.web.pojo.UserPindekeInfoPojo;
import com.tzmb2c.web.pojo.UserWalletLogPojo;
import com.tzmb2c.web.pojo.WxpayOrderInfoPojo;
import com.tzmb2c.web.service.AlipayOrderInfoService;
import com.tzmb2c.web.service.CartService;
import com.tzmb2c.web.service.CouponService;
import com.tzmb2c.web.service.DeliveryAddressService;
import com.tzmb2c.web.service.GrouponActivityRecordService;
import com.tzmb2c.web.service.GrouponActivityService;
import com.tzmb2c.web.service.GrouponUserRecordService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderPayinfoService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.OrderShipService;
import com.tzmb2c.web.service.ProductRestrictionService;
import com.tzmb2c.web.service.ProductSellService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.SysAreaService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserDealLogService;
import com.tzmb2c.web.service.UserOrderRefundService;
import com.tzmb2c.web.service.UserPindekeInfoService;
import com.tzmb2c.web.service.UserWalletLogService;
import com.tzmb2c.web.service.WxpayOrderInfoService;

/*
 * 用户订单 action by lie
 */
public class OrderAction extends SuperAction {

  @Autowired
  private OrderService orderService, shipOrderServiceWeb;
  @Autowired
  private OrderDetailService orderDetailService, orderReturn;
  @Autowired
  private OrderShipService orderShipService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SysAreaService sysAreaService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private ProductService productService;
  @Autowired
  private OrderDao orderDao;
  @Autowired
  private CartService cartService;
  @Autowired
  private DeliveryAddressService addressService;
  @Autowired
  private AlipayOrderInfoService alipayOrderInfoService;
  @Autowired
  private WxpayOrderInfoService wxpayOrderInfoService;
  @Autowired
  private CouponService couponService;
  @Autowired
  private ProductSkuLinkService productSkuLinkService;
  @Autowired
  private WalletService walletService;
  @Autowired
  private OrderPayinfoService orderPayinfoService;
  @Autowired
  private UserOrderRefundService userOrderRefundService;
  @Autowired
  private GrouponService grouponService;
  @Autowired
  private SellerService sellerService;
  @Autowired
  private GrouponUserRecordService grouponUserRecordService;
  @Autowired
  private ProductSellService productSellService;
  @Autowired
  private ProductRestrictionService productRestrictionService;
  @Autowired
  private GrouponActivityService grouponActivityService;
  @Autowired
  private UserPindekeInfoService userPindekeInfoService;
  @Autowired
  private UserDealLogService userDealLogService;
  @Autowired
  private GrouponActivityRecordService grouponActivityRecordService;
  @Autowired
  private UserWalletLogService userWalletLogService;

  private UserOrderRefundPojo userOrderRefundPojo;
  private SysAreaPojo sysArea;
  private OrderPojo order, orderPojo;
  private ProductPojo productPojo;
  private DeliveryAddressPojo deliveryAddressPojo;
  private OrderDetailPojo orderDetail, orderReturnPj, orderReturnPjWeb;
  private OrderShipPojo orderShip;
  private String result;
  private String[] tids;
  private String isAll;
  private String os;
  private String testcount;
  private String beganday = null, endday = null;
  private String userName;
  private String jsonStr;
  private List<OrderPojo> webSupplyOrderList, shipOrderListWeb;
  private List<ProductPojo> hotProductListSupplyWeb, productSellCountSupplyWeb;
  private Long orderId;// 订单ID
  private String consignee;// 收货人
  private int orderStatusWeb;// 订单状态
  private String[] cids;
  private String cidsStr;
  private Long addressId;
  private int goodCount;
  private Integer consigneeType;// 送货方式
  private String consigneeTypeName;
  private String[] orderNoArr;// 存放订单号(订单号数组)
  private String totalfee;// 支付宝支付金钱
  private List<OrderDetailPojo> orderDetailConfirmList;
  private OrderPojo orderDetailConfirm;

  private String cbody, outTradeNo;
  private String oallCartPrice;
  private String bankcode;
  private String buyer_message;
  private String extra_common_param;
  private List<Integer> testUsers;
  private AgencyPojo agency;
  private int orderType;// 订单来源
  private Long a;
  private List<CouponOrderPojo> couponOrderPojos;
  private CouponOrderPojo couponOrderPojo;
  private int guide;// 用于判断是否为内容导购的
  /**
   * 批次号，必填，格式：当天日期[8位]+序列号[3至24位]，如：201603081000001
   */
  private String batchNo;
  /**
   * 退款笔数，必填，参数detail_data的值中，“#”字符出现的数量加1，最大支持1000笔（即“#”字符出现的数量999个）
   */
  private String batchNum;
  /**
   * 退款详细数据，必填，格式（支付宝交易号^退款金额^备注），多笔请用#隔开
   */
  private String detailData;
  private Integer payType;
  private String orderNoStr;
  private Integer isRefund;
  private String loginname;
  private Integer pageNoVal;
  private String formParam;
  private Integer b;

  public Integer getB() {
    return b;
  }

  public void setB(Integer b) {
    this.b = b;
  }

  public String getFormParam() {
    return formParam;
  }

  public void setFormParam(String formParam) {
    this.formParam = formParam;
  }

  public Integer getPageNoVal() {
    return pageNoVal;
  }

  public void setPageNoVal(Integer pageNoVal) {
    this.pageNoVal = pageNoVal;
  }

  public Integer getIsRefund() {
    return isRefund;
  }

  public void setIsRefund(Integer isRefund) {
    this.isRefund = isRefund;
  }

  public String getLoginname() {
    return loginname;
  }

  public void setLoginname(String loginname) {
    this.loginname = loginname;
  }

  public String getOrderNoStr() {
    return orderNoStr;
  }

  public void setOrderNoStr(String orderNoStr) {
    this.orderNoStr = orderNoStr;
  }

  public Integer getPayType() {
    return payType;
  }

  public void setPayType(Integer payType) {
    this.payType = payType;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getBatchNo() {
    return batchNo;
  }

  public void setBatchNo(String batchNo) {
    this.batchNo = batchNo;
  }

  public String getBatchNum() {
    return batchNum;
  }

  public void setBatchNum(String batchNum) {
    this.batchNum = batchNum;
  }

  public String getDetailData() {
    return detailData;
  }

  public void setDetailData(String detailData) {
    this.detailData = detailData;
  }

  public int getGuide() {
    return guide;
  }

  public void setGuide(int guide) {
    this.guide = guide;
  }

  public Long getA() {
    return a;
  }

  public void setA(Long a) {
    this.a = a;
  }

  public String getIsAll() {
    return isAll;
  }

  public void setIsAll(String isAll) {
    this.isAll = isAll;
  }

  public void setAgency(AgencyPojo agency) {
    this.agency = agency;
  }

  public AgencyPojo getAgency() {
    return agency;
  }

  public String getExtra_common_param() {
    return extra_common_param;
  }

  public void setExtra_common_param(String extra_common_param) {
    this.extra_common_param = extra_common_param;
  }

  public String getBuyer_message() {
    return buyer_message;
  }

  public void setBuyer_message(String buyer_message) {
    this.buyer_message = buyer_message;
  }

  public String getBankcode() {
    return bankcode;
  }

  public void setBankcode(String bankcode) {
    this.bankcode = bankcode;
  }

  public String getOallCartPrice() {
    return oallCartPrice;
  }

  public void setOallCartPrice(String oallCartPrice) {
    this.oallCartPrice = oallCartPrice;
  }

  public String getCbody() {
    return cbody;
  }

  public void setCbody(String cbody) {
    this.cbody = cbody;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public List<OrderDetailPojo> getOrderDetailConfirmList() {
    return orderDetailConfirmList;
  }

  public void setOrderDetailConfirmList(List<OrderDetailPojo> orderDetailConfirmList) {
    this.orderDetailConfirmList = orderDetailConfirmList;
  }

  public OrderPojo getOrderDetailConfirm() {
    return orderDetailConfirm;
  }

  public void setOrderDetailConfirm(OrderPojo orderDetailConfirm) {
    this.orderDetailConfirm = orderDetailConfirm;
  }

  public String getTotalfee() {
    return totalfee;
  }

  public void setTotalfee(String totalfee) {
    this.totalfee = totalfee;
  }

  public OrderDetailPojo getOrderReturnPjWeb() {
    return orderReturnPjWeb;
  }

  public void setOrderReturnPjWeb(OrderDetailPojo orderReturnPjWeb) {
    this.orderReturnPjWeb = orderReturnPjWeb;
  }

  public List<OrderPojo> getShipOrderListWeb() {
    return shipOrderListWeb;
  }

  public void setShipOrderListWeb(List<OrderPojo> shipOrderListWeb) {
    this.shipOrderListWeb = shipOrderListWeb;
  }

  public DeliveryAddressPojo getDeliveryAddressPojo() {
    return deliveryAddressPojo;
  }

  public void setDeliveryAddressPojo(DeliveryAddressPojo deliveryAddressPojo) {
    this.deliveryAddressPojo = deliveryAddressPojo;
  }

  public int getGoodCount() {
    return goodCount;
  }

  public void setGoodCount(int goodCount) {
    this.goodCount = goodCount;
  }

  public OrderDetailPojo getOrderReturnPj() {
    return orderReturnPj;
  }

  public void setOrderReturnPj(OrderDetailPojo orderReturnPj) {
    this.orderReturnPj = orderReturnPj;
  }

  public String getJsonStr() {
    return jsonStr;
  }

  public void setJsonStr(String jsonStr) {
    this.jsonStr = jsonStr;
  }

  public List<ProductPojo> getProductSellCountSupplyWeb() {
    return productSellCountSupplyWeb;
  }

  public void setProductSellCountSupplyWeb(List<ProductPojo> productSellCountSupplyWeb) {
    this.productSellCountSupplyWeb = productSellCountSupplyWeb;
  }

  public ProductPojo getProductPojo() {
    return productPojo;
  }

  public void setProductPojo(ProductPojo productPojo) {
    this.productPojo = productPojo;
  }

  public List<ProductPojo> getHotProductListSupplyWeb() {
    return hotProductListSupplyWeb;
  }

  public void setHotProductListSupplyWeb(List<ProductPojo> hotProductListSupplyWeb) {
    this.hotProductListSupplyWeb = hotProductListSupplyWeb;
  }

  public String getConsignee() {
    return consignee;
  }

  public void setConsignee(String consignee) {
    this.consignee = consignee;
  }

  public int getOrderStatusWeb() {
    return orderStatusWeb;
  }

  public void setOrderStatusWeb(int orderStatusWeb) {
    this.orderStatusWeb = orderStatusWeb;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getBeganday() {
    return beganday;
  }

  public void setBeganday(String beganday) {
    this.beganday = beganday;
  }

  public String getEndday() {
    return endday;
  }

  public void setEndday(String endday) {
    this.endday = endday;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }

  public OrderShipPojo getOrderShip() {
    return orderShip;
  }

  public void setOrderShip(OrderShipPojo orderShip) {
    this.orderShip = orderShip;
  }

  public OrderDetailPojo getOrderDetail() {
    return orderDetail;
  }

  public void setOrderDetail(OrderDetailPojo orderDetail) {
    this.orderDetail = orderDetail;
  }

  public OrderPojo getOrder() {
    return order;
  }

  public void setOrder(OrderPojo order) {
    this.order = order;
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

  public String getTestcount() {
    return testcount;
  }

  public void setTestcount(String testcount) {
    this.testcount = testcount;
  }

  public OrderPojo getOrderPojo() {
    return orderPojo;
  }

  public void setOrderPojo(OrderPojo orderPojo) {
    this.orderPojo = orderPojo;
  }

  public List<OrderPojo> getWebSupplyOrderList() {
    return webSupplyOrderList;
  }

  public void setWebSupplyOrderList(List<OrderPojo> webSupplyOrderList) {
    this.webSupplyOrderList = webSupplyOrderList;
  }

  public String[] getCids() {
    return cids;
  }

  public void setCids(String[] cids) {
    this.cids = cids;
  }

  public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }

  public DeliveryAddressService getAddressService() {
    return addressService;
  }

  public void setAddressService(DeliveryAddressService addressService) {
    this.addressService = addressService;
  }



  public SysAreaPojo getSysArea() {
    return sysArea;
  }

  public void setSysArea(SysAreaPojo sysArea) {
    this.sysArea = sysArea;
  }

  public Integer getConsigneeType() {
    return consigneeType;
  }

  public void setConsigneeType(Integer consigneeType) {
    this.consigneeType = consigneeType;
  }

  public String getConsigneeTypeName() {
    return consigneeTypeName;
  }

  public void setConsigneeTypeName(String consigneeTypeName) {
    this.consigneeTypeName = consigneeTypeName;
  }

  public List<CouponOrderPojo> getCouponOrderPojos() {
    return couponOrderPojos;
  }

  public void setCouponOrderPojos(List<CouponOrderPojo> couponOrderPojos) {
    this.couponOrderPojos = couponOrderPojos;
  }

  public CouponOrderPojo getCouponOrderPojo() {
    return couponOrderPojo;
  }

  public void setCouponOrderPojo(CouponOrderPojo couponOrderPojo) {
    this.couponOrderPojo = couponOrderPojo;
  }

  public int getOrderType() {
    return orderType;
  }

  public void setOrderType(int orderType) {
    this.orderType = orderType;
  }

  public UserOrderRefundPojo getUserOrderRefundPojo() {
    return userOrderRefundPojo;
  }

  public void setUserOrderRefundPojo(UserOrderRefundPojo userOrderRefundPojo) {
    this.userOrderRefundPojo = userOrderRefundPojo;
  }

  /**
   * 获取测试订单用户ID
   * 
   * @throws Exception
   */
  /*
   * public static void getTestUserId() throws Exception { if (testUsers == null) { testUsers = new
   * ArrayList<Integer>(); // 过滤记事本中的userId String filePath = ""; String fileName = "userId.txt";
   * filePath = ServletActionContext.getServletContext().getRealPath( "/temp") + File.separator +
   * fileName; InputStreamReader read = null; BufferedReader bufferedReader = null; String lineTxt =
   * null; try { File file = new File(filePath); if (file.isFile() && file.exists()) { // 判断文件是否存在
   * read = new InputStreamReader(new FileInputStream(file), "utf-8");// 编码格式 bufferedReader = new
   * BufferedReader(read); while ((lineTxt = bufferedReader.readLine()) != null) {
   * testUsers.add(Integer.parseInt(lineTxt)); } } } finally { if(bufferedReader!=null){
   * bufferedReader.close(); } if (read != null) { read.close(); } } } }
   */

  public String orderWeb() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    OrderPojo orderPojo3 = new OrderPojo();
    orderPojo3 = orderService.orderStatusNum(sysLogin.getId());
    if (order != null && order.getOrderStatus() != null) {
      if (orderPojo3 != null) {
        orderPojo3.setOrderStatus(order.getOrderStatus());
      }
    }
    ActionContext ac = ActionContext.getContext();
    ac.put("orderPojo", orderPojo3);
    return SUCCESS;
  }

  /***
   * 前台：退货
   * 
   * @return
   * @throws Exception
   */
  public String userOrderRefund() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    OrderPojo orderPojo3 = new OrderPojo();
    orderPojo3 = orderService.orderStatusNum(sysLogin.getId());
    if (order != null && order.getOrderStatus() != null) {
      orderPojo3.setOrderStatus(order.getOrderStatus());
    }
    ActionContext ac = ActionContext.getContext();
    ac.put("orderPojo", orderPojo3);
    return SUCCESS;
  }

  public String orderlist() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    OrderPojo orderPojo2 = new OrderPojo();
    orderPojo2.setUserId(sysLogin.getId());
    if (order != null && order.getOrderStatus() != null) {
      orderPojo2.setOrderStatus(order.getOrderStatus());
    }
    List<OrderPojo> orderPojos = orderService.userIdOrder(orderPojo2);
    JSONArray json = JSONArray.fromObject(orderPojos);
    jsonStr = json.toString();
    return SUCCESS;
  }

  public String orderDetaillist() throws Exception {
    OrderDetailPojo p1 = null;
    List<OrderDetailPojo> orderDetailPojos =
        orderDetailService.getfindByUserIdOrderDetail(orderDetail.getOrderId());
    for (int i = 0; i < orderDetailPojos.size(); i++) {
      p1 = orderDetailPojos.get(i);
      double s = p1.getSubtotal();
      DecimalFormat df = new DecimalFormat(".#");
      String ss = df.format(s);
      p1.setSumtotal(ss);
    }
    JSONArray json = JSONArray.fromObject(orderDetailPojos);
    jsonStr = json.toString();
    return SUCCESS;
  }

  public String getOrderCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    ActionContext ac = ActionContext.getContext();
    ac.put("orderStatus", sysDictService.getSysDictListByType("order_status"));
    ac.put("payStatus", sysDictService.getSysDictListByType("pay_status"));
    ac.put("refundStatus", sysDictService.getSysDictListByType("refund_status"));
    ac.put("consigneeType", sysDictService.getSysDictListByType("consignee_type"));
    ac.put("payMethod", sysDictService.getSysDictListByType("pay_method_type"));
    ac.put("pageNoVal", pageNoVal);
    Map<String, Object> map = new HashMap<String, Object>();
    if (order != null) {
      map.put("orderNo", order.getOrderNo());
      map.put("orderStatus", order.getOrderStatus());
      map.put("consignee", order.getConsignee());
      map.put("consigneePhone", order.getConsigneePhone());
      map.put("consigneeAddress", order.getConsigneeAddress());
      map.put("payStatus", order.getPayStatus());
      map.put("overdue", order.getOverdue());
      map.put("createDate", order.getCreateDateString());
      map.put("beganday", order.getBeganday());
      map.put("endday", order.getEndday());
      map.put("beganday1", order.getBeganday1());
      map.put("endday1", order.getEndday1());
      map.put("sendDate", order.getSendTimes());
      map.put("tids", order.getTids());
      map.put("userId", order.getUserId());
      map.put("userName", order.getUserName());
      map.put("pushName", order.getPushName());
      map.put("refundStatus", order.getRefundStatus());
      map.put("consigneeType", order.getConsigneeType());
      map.put("payMethod", order.getPayMethod());
      map.put("logisticsNo", order.getLogisticsNo());
      map.put("logisticsName", order.getLogisticsName());
      map.put("shopName", order.getShopName());
      map.put("productId", order.getProductId());
      map.put("beganSendDate", order.getBeganSendDate());
      map.put("endSendDate", order.getEndSendDate());
      map.put("source", order.getSource());
      map.put("loginname", order.getLoginname());
      map.put("attendId", order.getAttendId());
      // map.put("isRefund", order.getIsRefund());
      map.put("refundStatus", order.getRefundStatus());
      map.put("groupBeginDateStr", order.getGroupBeginDateStr());
      map.put("groupEndDateStr", order.getGroupEndDateStr());
      map.put("notShip", order.getNotShip());
      map.put("pdkLoginname", order.getPdkLoginname());
    }
    /*
     * if (agency!= null) { map.put("agencyId",agency.getAgencyId()); }
     */
    if (order != null) {
      map.put("channel", order.getChannel());
    }
    // 过滤记事本中的userId
    /*
     * String filePath=""; String fileName="userId.txt"; List<Integer> a=new ArrayList<>();
     * filePath=ServletActionContext.getServletContext().getRealPath("/temp"
     * )+File.separator+fileName; File file = new File(filePath); if(file.isFile() &&
     * file.exists()){ //判断文件是否存在 InputStreamReader read = new InputStreamReader( new
     * FileInputStream(file),"utf-8");//编码格式 BufferedReader bufferedReader = new
     * BufferedReader(read); String lineTxt = null; while((lineTxt = bufferedReader.readLine()) !=
     * null){ a.add(Integer.parseInt(lineTxt)); } read.close(); }
     */
    testUsers = SellerService.getTestUsers();
    if (testUsers != null && testUsers.size() > 0) {
      if (testcount != null && !testcount.equals("")) {
        map.put("userIds", testUsers);
      } else {
        map.put("notuserIds", testUsers);
      }
    }
    if (os != null && !os.equals("") && !os.equals("6") && !os.equals("7") && !os.equals("8")) {
      map.put("orderStatus", Integer.parseInt(os));
    } else if (os != null && os.equals("6") || os.equals("7") || os.equals("8")) {
      map.put("os", Integer.parseInt(os));
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    // page.setRowCount(orderService.orderAllCount(order, os));
    if (a == null) {
      if (orderType == 1) {
        map.put("orderType", 1);
      } else {
        map.put("orderTypes", 1);
      }
    } else {
      map.put("a", 1);
    }
    page.setRowCount(orderService.orderAllCount2(map));
    return SUCCESS;
  }

  public String orderAllList() throws Exception {
    // JSONArray json =
    // JSONArray.fromObject(orderService.orderAllList(order,
    // page, os));
    // page.setResult(json.toString());
    Map<String, Object> map = new HashMap<String, Object>();
    if (order != null) {
      map.put("orderNo", order.getOrderNo());
      map.put("orderStatus", order.getOrderStatus());
      map.put("consignee", order.getConsignee());
      map.put("consigneePhone", order.getConsigneePhone());
      map.put("consigneeAddress", order.getConsigneeAddress());
      map.put("payStatus", order.getPayStatus());
      map.put("overdue", order.getOverdue());
      map.put("createDate", order.getCreateDateString());
      map.put("beganday", order.getBeganday());
      map.put("endday", order.getEndday());
      map.put("sendDate", order.getSendTimes());
      map.put("beganday1", order.getBeganday1());
      map.put("endday1", order.getEndday1());
      map.put("tids", order.getTids());
      map.put("userId", order.getUserId());
      map.put("userName", order.getUserName());
      map.put("remarks", order.getRemarks());
      map.put("pushName", order.getPushName());
      map.put("consigneeType", order.getConsigneeType());
      map.put("refundStatus", order.getRefundStatus());
      map.put("payMethod", order.getPayMethod());
      map.put("logisticsNo", order.getLogisticsNo());
      map.put("logisticsName", order.getLogisticsName());
      map.put("shopName", order.getShopName());
      map.put("productId", order.getProductId());
      map.put("beganSendDate", order.getBeganSendDate());
      map.put("endSendDate", order.getEndSendDate());
      map.put("source", order.getSource());
      map.put("loginname", order.getLoginname());
      map.put("attendId", order.getAttendId());
      // map.put("isRefund", order.getIsRefund());
      map.put("refundStatus", order.getRefundStatus());
      map.put("groupBeginDateStr", order.getGroupBeginDateStr());
      map.put("groupEndDateStr", order.getGroupEndDateStr());
      map.put("notShip", order.getNotShip());
      map.put("pdkLoginname", order.getPdkLoginname());
    }
    /*
     * if (agency!= null) { map.put("agencyId",agency.getAgencyId()); }
     */
    if (order != null) {
      map.put("channel", order.getChannel());
    }
    if (os != null && !os.equals("") && !os.equals("6") && !os.equals("7") && !os.equals("8")) {
      map.put("orderStatus", Integer.parseInt(os));
    } else if (os != null && os.equals("6") || os.equals("7") || os.equals("8")) {
      map.put("os", Integer.parseInt(os));
    }
    // 过滤记事本中的userId
    /*
     * String filePath = ""; String fileName = "userId.txt"; List<Integer> a = new ArrayList<>();
     * filePath = ServletActionContext.getServletContext() .getRealPath("/temp") + File.separator +
     * fileName; File file = new File(filePath); if (file.isFile() && file.exists()) { // 判断文件是否存在
     * InputStreamReader read = new InputStreamReader(new FileInputStream( file), "utf-8");// 编码格式
     * BufferedReader bufferedReader = new BufferedReader(read); String lineTxt = null; while
     * ((lineTxt = bufferedReader.readLine()) != null) { a.add(Integer.parseInt(lineTxt)); }
     * read.close(); }
     */
    testUsers = SellerService.getTestUsers();
    if (testUsers != null && testUsers.size() > 0) {
      if (testcount != null && !testcount.equals("")) {
        map.put("userIds", testUsers);
      } else {
        map.put("notuserIds", testUsers);
      }
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    if (a == null) {
      if (orderType == 1) {
        map.put("orderType", 1);
      } else {
        map.put("orderTypes", 1);
      }
    } else {
      map.put("a", 1);
    }
    List<OrderPojo> orders = orderService.orderAllList2(map);

    JSONArray json = JSONArray.fromObject(orders);
    page.setResult(json.toString());
    page.setRowCount(orders == null ? 0 : orders.size());
    return SUCCESS;
  }

  public String orderDeleteId() {
    if (tids != null) {
      orderService.orderDeleteId(tids);
      // 同时删除订单详情
      for (String tid : tids) {
        try {
          List<OrderDetailPojo> list = orderDetailService.getOrderDetail(Long.parseLong(tid));
          for (int i = 0; i < list.size(); i++) {
            orderDetailService.delOrderDetail(list.get(i).getId());
          }
          // 同时删除发货信息
          orderShip = orderShipService.findByIdOrderShip(Long.parseLong(tid));
          if (orderShip != null) {
            orderShipService.delOrderShip(orderShip.getId());
          }

        } catch (SQLException e) {

          e.printStackTrace();
        }
      }
      FileUtil.alertMessageBySkip("删除成功！", "order.do?os=" + os);
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "order.do?os=" + os);
    }

    return null;
  }

  public String deleOrder() throws SQLException {
    try {
      orderService.delOrder(order.getId());
      // 同时删除订单详情
      List<OrderDetailPojo> list = orderDetailService.getOrderDetail(order.getId());
      for (int i = 0; i < list.size(); i++) {
        orderDetailService.delOrderDetail(list.get(i).getId());
      }
      // 同时删除发货信息
      orderShip = orderShipService.findByIdOrderShip(order.getId());
      if (orderShip != null) {
        orderShipService.delOrderShip(orderShip.getId());
      }

      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  /***
   * 跳转到订单详情页面
   * 
   * @return
   * @throws Exception
   */
  public String goFindOrder() throws Exception {
    ActionContext ac = ActionContext.getContext();
    UserOrderRefundPojo userOrderRefund = new UserOrderRefundPojo();
    userOrderRefund.setOrderId(order.getId());
    List<UserOrderRefundPojo> userOrderRefundPojos =
        userOrderRefundService.findUserOrderRefundByorderId(userOrderRefund);
    orderPojo = orderService.findOrderById(order.getId());
    // ac.put("orderPojo",p);
    // ac.put("orderDetailPojo",
    // orderDetailService.getfindByOrderIdOrderDetail(order.getId()));
    // 查询商品sku
    List<OrderDetailPojo> orderDetails = orderDetailService.getOrderDetail(order.getId());
    if (orderDetails != null && orderDetails.size() > 0) {
      ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
      ProductSkuLinkPojo productSkuLinkPojo = null;
      for (OrderDetailPojo orderDetailPojo : orderDetails) {
        if (orderDetailPojo.getSkuLinkId() != null && orderDetailPojo.getSkuLinkId() > 0) {
          productSkuLink.setId(Long.valueOf(orderDetailPojo.getSkuLinkId()));
          productSkuLinkPojo = productSkuLinkService.findProductSkuLink(productSkuLink);
          if (productSkuLinkPojo != null
              && (StringUtils.isNotBlank(productSkuLinkPojo.getColorValue()) || StringUtils
                  .isNotBlank(productSkuLinkPojo.getFormatValue()))) {
            orderDetailPojo.setProductName(orderDetailPojo.getProductName().concat("（颜色：")
                .concat(productSkuLinkPojo.getSkuColor()).concat("，尺寸：")
                .concat(productSkuLinkPojo.getSkuFormat()).concat("）"));
          }
        }
      }
    }
    if (userOrderRefundPojos.size() > 0) {
      ac.put("userOrderRefundPojo", userOrderRefundPojos.get(0));
    }
    ac.put("orderDetails", orderDetails);
    ac.put("orderStatus", sysDictService.getSysDictListByType("order_status"));
    ac.put("payStatus", sysDictService.getSysDictListByType("pay_status"));
    ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("consigneeType", sysDictService.getSysDictListByType("consignee_type"));
    ac.put("sysLoginPojo", sysLoginService.findSysLoginById(order.getUserId()));
    ac.put("guide", guide);
    ac.put("pageNoVal", page == null ? 1 : page.getPageNo());
    ac.put("formParam", formParam);
    // HttpServletRequest request = ServletActionContext.getRequest();
    // System.out.println(request.getQueryString());
    // ac.put("formParam", request.getQueryString());
    return SUCCESS;
  }

  /**
   * 退货：跳到订单的产品详情页
   * 
   * @return
   * @throws Exception
   */
  public String goFindOrderWeb() throws Exception {
    String orderNo = order.getOrderNo();
    Long orderID = order.getId();
    ActionContext ac = ActionContext.getContext();
    ac.put("orderDetailsWeb", orderDetailService.getOrderDetail(order.getId()));
    ac.put("orderNoWeb", orderNo);
    ac.put("orderIDWeb", orderID);
    return SUCCESS;
  }

  /***
   * 修改订单
   * 
   * @return
   * @throws Exception
   */
  public String updateOrder() throws Exception {
    try {
      SysLoginPojo loginPojo = UserUtil.getUser();
      if (UserUtil.getUser() == null) {
        return "loginpage";
      } else {
        order.preUpdate(loginPojo);
      }
      order.setUpdateBy(loginPojo.getId());
      if (order.getUsedPrice() != null) {
        order.setFactPrice(order.getFactPrice() + order.getUsedPrice());
      }
      // 判断是否已经支付,是的话不执行下面拼团操作
      orderPojo = orderService.getfindByIdOrder(order.getId());
      if (orderPojo == null) {
        FileUtil.alertMessageBySkip("查询不到该订单！", "order.do?os=" + os + "&pageNoVal=" + pageNoVal
            + "&" + formParam);
        return null;
      }
      // 自动确认订单时间为15天后
      if (order.getOrderStatus() != null && order.getOrderStatus() == 3) {
        order.setAutoRecTime(GrouponService.getTimeAddDay(new Date(), 15));
      }
      // 如果状态改为代发货则插入付款时间
      if (order.getOrderStatus() != null && order.getOrderStatus() == 2) {
        order.setPaymentDate(new Date());
      }
      int i = orderService.updateOrder(order);
      if (i > 0) {
        if (orderPojo != null && orderPojo.getOrderStatus() < 2 && order.getOrderStatus() != null
            && order.getOrderStatus() == 2) {
          // 判断是否参加过该团
          Map<String, Object> params = new HashMap<String, Object>();
          if (orderPojo.getSourceId() != null && orderPojo.getSourceId() > 0
              && orderPojo.getUserId() != null && orderPojo.getUserId() > 0) {
            params.put("attendId", orderPojo.getSourceId());
            params.put("userId", orderPojo.getUserId());
            int gur = grouponUserRecordService.countBy(params);
            if (gur > 0) {
              FileUtil.alertMessageBySkip("该用户已经参加过该团!", "order.do?os=" + os + "&pageNoVal="
                  + pageNoVal + "&" + formParam);
              return null;
            }
          }
          // 支付成功处理
          grouponService.groupOrderHandle(order.getActivityId(), order.getSourceId(),
              order.getUserId(), order.getSource(), order.getId());
          try {
            // 添加商品限购
            OrderPojo order = orderService.findOrderByOrderNo(orderPojo.getOrderNo());
            if (order != null && order.getSource() != null && order.getSource() == 1
                && order.getMaxNum() != null && order.getMaxNum() > 0) {
              Map<String, Object> params2 = new HashMap<String, Object>();
              params2.put("userId", orderPojo.getUserId());
              params2.put("activityId", orderPojo.getActivityId());
              params2.put("productId", Long.valueOf(order.getProductId()));
              List<ProductRestrictionPojo> productRestrictionPojos =
                  productRestrictionService.listPage(params2);
              if (productRestrictionPojos != null && productRestrictionPojos.size() > 0) {
                ProductRestrictionPojo productRestriction1 = productRestrictionPojos.get(0);
                ProductRestrictionPojo productRestriction = new ProductRestrictionPojo();
                productRestriction.setBuyNum(productRestriction1.getBuyNum() + order.getNum());
                productRestriction.setMaxNum(order.getMaxNum());// 为了适应限购数量有改变
                productRestriction.setId(productRestriction1.getId());
                int j = productRestrictionService.update(productRestriction);
                if (j > 0) {
                  Util.log("修改商品限购成功：id-" + productRestriction1.getId());
                } else {
                  Util.log("修改商品限购失败：id-" + productRestriction1.getId());
                }
              } else {
                ProductRestrictionPojo productRestriction = new ProductRestrictionPojo();
                productRestriction.setUserId(orderPojo.getUserId());
                productRestriction.setActivityId(orderPojo.getActivityId());
                productRestriction.setProductId(Long.valueOf(order.getProductId()));
                productRestriction.setBuyNum(order.getNum());
                productRestriction.setMaxNum(order.getMaxNum());
                int j = productRestrictionService.add(productRestriction);
                if (j > 0) {
                  Util.log("添加商品限购成功：userId-" + orderPojo.getUserId() + "/activityId-"
                      + orderPojo.getActivityId() + "/productId-" + order.getProductId());
                } else {
                  Util.log("添加商品限购失败：userId-" + orderPojo.getUserId() + "/activityId-"
                      + orderPojo.getActivityId() + "/productId-" + order.getProductId());
                }
              }
            } else {
              Util.log("添加商品限购，订单不存在了：orderId-" + orderPojo.getId());
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        } else if (orderPojo != null && order.getOrderStatus() != null
            && order.getOrderStatus() == 4 && orderPojo.getSource() == 8
            && orderPojo.getActivityId() != null && orderPojo.getPdkUid() != null
            && orderPojo.getPdkUid() > 0 && orderPojo.getIsRebate() != null
            && orderPojo.getIsRebate() == 0) {
          Map<String, Object> params = new HashMap<String, Object>();
          params.put("userId", orderPojo.getUserId());
          params.put("id", orderPojo.getSourceId());
          int gar = grouponActivityRecordService.countBy(params);
          if (gar > 0) {
            Util.log("团长不用返佣!");
          } else {
            Util.log("拼得客返佣!");
            try {
              GrouponActivityPojo grouponActivity =
                  grouponActivityService.getById(orderPojo.getActivityId());
              if (grouponActivity != null && grouponActivity.getRebateRatio() > 0.0) {
                Util.log("计算返佣金额!");
                Double price = orderPojo.getRebatePrice();
                UserPindekeInfoPojo userPindekeInfo =
                    userPindekeInfoService.findByUserId(orderPojo.getPdkUid());
                if (userPindekeInfo != null) {
                  Util.log("减去拼得客冻结金额且添加拼得客剩余金额!");
                  UserPindekeInfoPojo userPindeke = new UserPindekeInfoPojo();
                  userPindeke.setId(userPindekeInfo.getId());
                  userPindeke.setFreezingPriceMinus(price);// 冻结金额
                  userPindeke.setSurpluPriceAdd(price);// 余额
                  userPindeke.setRebatePriceAdd(price);// 总返佣金额
                  int upi = userPindekeInfoService.update(userPindeke);
                  if (upi > 0) {
                    Util.log("后台修改拼得客金额成功!");
                  }
                  Util.log("修改订单返佣信息!");
                  Date nowDate = new Date();
                  OrderPojo orderUp = new OrderPojo();
                  orderUp.setId(orderPojo.getId());
                  // orderUp.setRebatePrice(price);
                  // orderUp.setRebateRatio(grouponActivity.getRebateRatio());
                  orderUp.setRebateTime(nowDate);
                  orderUp.setIsRebate(1);
                  int uo = orderService.updateOrder(orderUp);
                  if (uo > 0) {
                    Util.log("修改订单返佣信息成功!");
                  }
                  Util.log("插入交易记录表!");
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
                    Util.log("插入交易记录表成功!");
                  }
                }
              }
            } catch (Exception e) {
              Util.log("拼得客返佣出现异常!");
              e.printStackTrace();
            }
          }
        }
      }
      // 订单已发货自动发送“发货短信”
      if (order != null) {
        if (order.getOrderStatus() != null && order.getOrderStatus() == 3
            && order.getConsigneePhone() != null) {
          OrderShipPojo orderShipList = orderShipService.findByIdOrderShip(order.getId());
          String content =
              "【拼得好】您购买的宝贝已由" + orderShipList.getLogisticsNameCN() + "快递发出，正在奔向您的途中，快递单号："
                  + orderShipList.getLogisticsNo();
          // MxtongSMSCaptchaUtil.sendMxtongSMS(order.getConsigneePhone(), content);
          SmsSendUtil.sendSMS(order.getConsigneePhone(), content);
          // 微信发货通知
          if (orderPojo != null && (orderPojo.getPayMethod() == 2 || orderPojo.getPayMethod() == 8)) {
            // 查询用户信息
            SysLoginPojo user = sysLoginService.findSysLoginById(orderPojo.getUserId());
            if (user == null) {
              Util.log("查询不到用户!");
            } else {
              try {
                grouponService.wxNotice(1, orderPojo.getId(), user.getOpenid(), user.getId());
              } catch (Exception e) {
                System.out.println("微信发货通知失败！");
                e.printStackTrace();
              }
            }
          } else {
            System.out.println("查询不到微信支付的订单!");
          }
        }
      }
      if (guide == 1) {
        FileUtil.alertMessageBySkip("修改成功！", "goContentGuide.do?contentGuide=1&pageNoVal="
            + pageNoVal + "&" + formParam);
      } else {
        FileUtil.alertMessageBySkip("修改成功！", "order.do?os=" + os + "&pageNoVal=" + pageNoVal + "&"
            + formParam);
      }
      ActionContext ac = ActionContext.getContext();
      OrderPojo p = orderService.findOrderById(order.getId());
      ac.put("orderPojo", p);
      if (p.getConsigneeType() == 2) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderId", p.getOrderId());
        orderService.deleteOrdership(map);
      }
    } catch (Exception e) {

      e.printStackTrace();
    }
    return null;
  }

  /***
   * 后台修改订单状态
   * 
   * // * @return
   * 
   * @throws Exception
   */
  public String updatesendOrderStatus() throws Exception {
    SysLoginPojo loginPojo = UserUtil.getUser();
    order.setUpdateBy(loginPojo.getId());
    if (order.getOrderStatus() == 1) {
      order.setOrderStatus(2);
      order.setPaymentDate(new Date());
    } else if (order.getOrderStatus() == 2) {
      order.setOrderStatus(3);
      order.setSendDate(new Date());
    } else if (order.getOrderStatus() == 3) {
      order.setOrderStatus(4);
      order.setConfirmDate(new Date());
      // 添加销售量
      // List<OrderDetailPojo>
      // orderDetaillist=orderDetailService.getOrderDetail(order.getId());
      // if(orderDetaillist!=null&&orderDetaillist.size()>0){
      // for(OrderDetailPojo p:orderDetaillist){
      // Map<String, Object> map1 = new HashMap<String, Object>();
      // ProductPojo productPojo=new ProductPojo();
      // productPojo.setId(p.getProductId());
      // productPojo=productService.findProduct(productPojo);
      // if(productPojo!=null){
      // map1.put("id",productPojo.getId());
      // map1.put("sellNumber",productPojo.getSellNumber()+p.getNum());
      // productService.updateProductsellNumber(map1);
      // }
      // }
      // }
    } else if (order.getOrderStatus() == 4) {
      order.setOrderStatus(5);
    }
    orderService.updateOrderStatus(order);
    FileUtil.alertMessageBySkip("修改成功！", "order.do?os=" + os);
    return null;
  }

  /***
   * 删除单条
   * 
   * @return
   */
  public String isDelete() {
    try {
      order.setIsDeleteOrder(1);// 将订单设置成删除状态
      orderService.isDelete(order);
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  /**
   * 批量删除
   * 
   * @return
   */
  public String checkAllOrderById() {
    orderService.checkAllOrderById(tids);
    FileUtil.alertMessageBySkip("删除成功！", "order.do?os=" + os);
    return null;
  }

  /**
   * 取消单条
   * 
   * @return
   */
  public String isCancel() {
    try {
      order.setIsCancelOrder(1);// 设置订单状态为取消
      orderService.isCancel(order);
      if (!os.equals("1")) {
        FileUtil.alertMessageBySkip("取消成功！", "order.do");
      } else {
        FileUtil.alertMessageBySkip("取消成功！", "orderWeb.do");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /***
   * 跳到退货申请页面
   * 
   * @return
   * @throws Exception
   */
  public String goReturnGoods() throws Exception {
    // 查询订单详情，给页面展示
    orderReturnPj = orderReturn.getfindByOrderIdOrderDetail(order.getId());
    return SUCCESS;
  }

  /***
   * 前台：跳到退货申请页面
   * 
   * @return
   * @throws Exception
   */
  public String goReturnGoodsWeb() throws Exception {
    // 查询订单详情，给页面展示
    orderReturnPjWeb = orderReturn.getfindByOrderIdOrderDetailWeb(orderDetail.getId());
    return SUCCESS;
  }

  //

  // /////////////////////////////////////分割线/////////////////////////////////////
  // 订单结算
  public String getOrderCountSettle() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    // if(order!=null){
    // map.put("userId", order.getUserId());
    // map.put("orderStatus", orderDaoPojo.getOrderStatus());
    // map.put("consignee", orderDaoPojo.getConsignee());
    // map.put("payStatus", orderDaoPojo.getPayStatus());
    // }
    if (os != null && !os.equals("")) {
      map.put("os", Integer.parseInt(os));
    }
    if (beganday != null && !beganday.equals("")) {
      map.put("beganday", beganday);
    }
    if (endday != null && !endday.equals("")) {
      map.put("endday", endday);
    }
    if (userName != null && !userName.equals("")) {
      map.put("userName", userName);
    }

    List<OrderPojo> list = orderDao.orderAllListSettle(map);
    page.setRowCount(list.size());

    return SUCCESS;
  }

  public String orderAllListSettle() {
    JSONArray json =
        JSONArray.fromObject(orderService.orderAllListSettle(order, page, os, beganday, endday,
            userName));
    page.setResult(json.toString());

    return SUCCESS;
  }

  // 供应商订单结算
  public String getOrderCountSettleMF() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (beganday != null && !beganday.equals("")) {
      map.put("beganday", beganday);
    }
    if (endday != null && !endday.equals("")) {
      map.put("endday", endday);
    }
    if (userName != null && !userName.equals("")) {
      map.put("userName", userName);
    }

    List<OrderPojo> list = orderDao.orderAllListSettleMF(map);
    page.setRowCount(list.size());

    return SUCCESS;
  }

  public String orderAllListSettleMF() {
    JSONArray json =
        JSONArray.fromObject(orderService.orderAllListSettleMF(order, page, beganday, endday,
            userName));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String updateOrderStatus() throws SQLException {
    if (orderPojo.getOrderStatus() == 1) {
      orderPojo.setOrderStatus(2);
      orderPojo.setPaymentDate(new Date());
    } else if (orderPojo.getOrderStatus() == 2) {
      orderPojo.setOrderStatus(3);
      orderPojo.setSendDate(new Date());
    } else if (orderPojo.getOrderStatus() == 3) {
      orderPojo.setOrderStatus(4);
      orderPojo.setConfirmDate(new Date());
    } else if (orderPojo.getOrderStatus() == 4) {
      orderPojo.setOrderStatus(5);
    }
    orderService.updateOrderStatus(orderPojo);
    return SUCCESS;
  }

  // 前端调用
  public String goSelectAddress() throws Exception {
    // SysLoginPojo user = UserUtil.getWebUser();
    boolean s = true;
    String productName = "";
    ActionContext actionContext = ActionContext.getContext();
    SysLoginPojo user = (SysLoginPojo) actionContext.getSession().get("wuser");
    if (cids == null && cidsStr == null) {
      FileUtil.alertMessageBySkip("请重新选择您要提交的购物车商品！", "/cartWeb.do");
      return null;
    }
    if (cids == null && cidsStr != null) {
      cids = cidsStr.split(",");
    }
    List<CartPojo> cartList = cartService.getCartsByIds(cids);

    for (CartPojo cart : cartList) {// 查询购物车里面是否存在下架商品
      productPojo = new ProductPojo();
      productPojo.setId(cart.getProductId());
      productPojo = productService.findProduct(productPojo);
      if (productPojo.getStatus() == 0) {
        s = false;
        productName = productName + productPojo.getProductName();
      }
    }
    if (s) {
      String sb = "";
      for (int i = 0; i < cids.length; i++) {
        if (i < cids.length - 1) {
          sb = sb + cids[i] + ",";
        } else {
          sb = sb + cids[i];
        }
      }
      DecimalFormat df = new DecimalFormat(".#");
      cidsStr = sb;

      goodCount = cartList.size();
      List<DeliveryAddressPojo> addressList = addressService.getAddressByUser(user.getId());
      Double allCartPrice = 0.0;
      for (CartPojo cart : cartList) {
        allCartPrice = allCartPrice + cart.getStockPrice() * cart.getNum();
        cart.setAllStockPrice(df.format(cart.getStockPrice() * cart.getNum()));
      }

      String allCartPrice0 = df.format(allCartPrice);
      ActionContext ac = ActionContext.getContext();
      ac.put("cartList", cartList);
      ac.put("addressList", addressList);
      ac.put("allCartPrice", allCartPrice0);
      ac.put("goodCount", goodCount);
      ac.put("cidsStr", cidsStr);
      ServletActionContext.getRequest().setAttribute("cids", cids);
      return SUCCESS;
    } else {
      FileUtil.alertMessageBySkip("你的购物车中" + productName + "已经下架！", "/cartWeb.do");
      return null;
    }
  }

  /***
   * 前端页面：确认收货地址：使用新地址
   * 
   * @return
   * @throws Exception
   */
  public String goAddAddress() {
    ActionContext ac = ActionContext.getContext();
    ac.put("cidsStr", cidsStr);
    return SUCCESS;
  }

  /***
   * 添加单条我的收货地址信息
   * 
   * @return
   * @throws IOException
   */
  public String addDeliveryAddress() throws IOException {
    String cids = cidsStr;
    try {
      deliveryAddressPojo.setCreateBy(8L);
      deliveryAddressPojo.setIsDefault(0);
      SysLoginPojo user = UserUtil.getWebUser();
      deliveryAddressPojo.setUserId(user.getId());
      deliveryAddressPojo.setStatus(0);
      addressService.addDeliveryAddress(deliveryAddressPojo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (cids.equals("")) {
      FileUtil.alertMessageBySkip("添加收货地址成功！", "deliveryAddressListWeb.do");
    } else {
      FileUtil.alertMessageBySkip("添加收货地址成功！", "goSelectAddress.do?cidsStr=" + cids);
    }
    // FileUtil.alertMessageBySkip("添加收货地址成功！",
    // "goSelectAddress.do?cidsStr="+cids);
    return null;
  }

  /***
   * 我的：发货管理，查询订单数目
   * 
   * @return
   * @throws Exception
   */
  public String shipOrderCountWeb() throws Exception {
    // if (page == null)
    // page = new Pager();
    // page.setPageSize(5);
    // page.setRowCount(shipOrderServiceWeb.shipOrderCountWeb(orderPojo));
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(12);
    page.setRowCount(shipOrderServiceWeb.shipOrderCountWeb(orderPojo));
    shipOrderListWeb = shipOrderServiceWeb.getShipOrderAllListWeb(orderPojo, page);
    return SUCCESS;
  }

  /***
   * 我的：发货管理，查询所有记录
   * 
   * @return
   * @throws Exception
   */
  public String getShipOrderAllListWeb() throws Exception {
    page.setPageSize(5);
    shipOrderListWeb = shipOrderServiceWeb.getShipOrderAllListWeb(orderPojo, page);
    JSONArray json = JSONArray.fromObject(shipOrderListWeb);
    jsonStr = json.toString();
    return SUCCESS;
  }

  public String getCidsStr() {
    return cidsStr;
  }

  public void setCidsStr(String cidsStr) {
    this.cidsStr = cidsStr;
  }

  public String[] getOrderNoArr() {
    return orderNoArr;
  }

  public void setOrderNoArr(String[] orderNoArr) {
    this.orderNoArr = orderNoArr;
  }

  /***
   * 提交订单
   * 
   * @return
   * @throws Exception
   */
  public String confirmOrder() throws Exception {
    SysLoginPojo user = UserUtil.getWebUser();
    Double allCartPrice = 0.0;
    Double allCartPrice0 = 0.0;
    Double hCartPrice = 0.0;
    // 订单留言处理
    // String s_message[]=null;
    // int i=0;
    // buyer_message=buyer_message.substring(0, buyer_message.length()-3);
    // s_message=buyer_message.split(", #, ");
    // String orderNo = new
    // Date().getTime()+RandomUtils.runVerifyCode(6);//生成订单号
    // String oo = totalfee;
    DeliveryAddressPojo address = null;// 自提地址可以为空

    // 快递方式
    if (addressId != null && consigneeType == 1) {
      address = addressService.findDeliveryAddressById(addressId);// 获取收货地址信息
    }

    // List<CartPojo> cartList = cartService.getCartsByIds(cids);//获取商品信息
    List<CartPojo> cartList = cartService.getCartsByIdsSummit(cids);// 获取商品信息
    cartService.updateStatusCartWeb(cids);// 修改选中的购物车商品的状态=1
    DecimalFormat df = new DecimalFormat(".#");
    // 订单描述--支付宝
    String body = "";
    // 商户订单号--支付宝
    String out_trade_no = UtilDate.getOrderNum() + UtilDate.getThree();
    Long shopId = 0L;
    for (CartPojo cart : cartList) {
      // cart.setPricess(cart.getStockPrice()*cart.getNum());
      body = body + cart.getProductName() + ";";
      if (shopId != cart.getShopId()) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", user.getId());
        map.put("shopId", cart.getShopId());
        List<CartPojo> cartListByShopId = cartService.getCartAllByShopId(map);
        Double farestatus = 1.0;// farestatus=1初始值
        Double pweight = 0.0;
        allCartPrice = 0.0;
        for (CartPojo cartByShopId : cartListByShopId) {
          // 计算同一张订单的所有商品的价格
          allCartPrice = allCartPrice + cartByShopId.getStockPrice() * cartByShopId.getNum();
          cartByShopId.setAllStockPrice(df.format(cartByShopId.getStockPrice()
              * cartByShopId.getNum()));
          if (cartByShopId.getPostageType() == 1) {
            farestatus = 0.0;
          }
        } // for

        if (consigneeType == 2) {

          farestatus = 0.0;
        }
        hCartPrice = hCartPrice + allCartPrice;
        // 方式为自提时该值为空
        SysAreaPojo sysArea = null;
        if (address != null) {
          sysArea = sysAreaService.getNameById(address.getProvince());// 查询这个省对应的运费；
        }
        if (farestatus == 1.0 && sysArea != null) {
          // 不包邮，
          for (CartPojo cartByShopId : cartListByShopId) {
            Double cweight = Double.valueOf(cartByShopId.getWeight());
            pweight = pweight + cweight * Double.valueOf(cartByShopId.getNum());
          }
          int intPweight = (int) Math.ceil(pweight);
          if (intPweight <= 3) {
            Double posttage = sysArea.getPostage();
            Double addPostage = sysArea.getAddPostage();

            farestatus = posttage + addPostage * (intPweight - 1);
          } else {
            Double posttage = sysArea.getPostage2();
            Double addPostage = sysArea.getAddPostage2();

            farestatus = posttage + addPostage * (intPweight - 1);
          }

          allCartPrice0 = allCartPrice + farestatus;
        } else {
          // 包邮//自提
          allCartPrice0 = allCartPrice;
        }
        // 生成一张新的订单的订单号
        String orderNo = new Date().getTime() + RandomUtils.runVerifyCode(6);

        // 生成订单
        OrderPojo order = new OrderPojo();
        order.setUserId(user.getId());
        // order.setSuserId(user.getId());//用户id作为订单的suerid
        order.setSuserId(cart.getShopUserid());// 店铺id作为订单的suerid
        order.setAllPrice(allCartPrice);
        order.setFactPrice(allCartPrice0);
        order.setEspressPrice(Double.valueOf(farestatus));
        // 自提方式
        if (address != null) {
          order.setConsignee(address.getConsignee());
          order.setConsigneeAddress(address.getProvinceName() + address.getCityName()
              + address.getAreaName() + address.getAddress());
          order.setConsigneePhone(address.getConsigneePhone());
        }

        order.setConsigneeType(consigneeType);
        order.setOrderStatus(1);
        // order.setStatus(1);
        order.setIsDeleteOrder(0);
        order.setIsCancelOrder(0);
        order.setPayStatus(0);
        order.setCreateBy(user.getId());
        order.setCreateDate(new Date());
        order.setOrderNo(orderNo);
        // System.out.println(">>>>>>>>>>>"+buyer_message);
        // String message="";
        // if (s_message.length>0 && s_message[i] != null &&
        // !s_message[i].equals("")){
        // message=s_message[i];
        // }
        if (buyer_message != null && buyer_message != "") {
          order.setBuyerMessage(buyer_message);
        }
        if (buyer_message == null && buyer_message == "") {
          order.setBuyerMessage(" ");
        }
        // i++;
        // 支付宝订单号
        order.setOutTradeNo(out_trade_no);
        if (addressId != null) {
          int s = Integer.parseInt(String.valueOf(addressId));
          order.setUserAddressId(s);
        }
        if (null != address) {
          order.setProvince(address.getProvince() + "");
          order.setCity(address.getCity() + "");
          order.setArea(address.getArea() + "");
        }
        orderService.insertOrder(order);
        // 生成订单结束
        // 生成订单详情
        OrderPojo insert_order = orderService.findOrderByOrderNo(orderNo);
        for (CartPojo cartDetail : cartListByShopId) {
          cart.setAllStockPrice(df.format(cartDetail.getStockPrice() * cartDetail.getNum()));
          OrderDetailPojo orderDetail = new OrderDetailPojo();
          orderDetail.setOrderId(insert_order.getId());
          orderDetail.setUserId(user.getId());
          orderDetail.setLoginname(user.getLoginname());
          orderDetail.setShopId(cartDetail.getShopId());
          orderDetail.setProductId(cartDetail.getProductId());
          orderDetail.setProductName(cartDetail.getProductName());
          orderDetail.setProductImage(cartDetail.getProductImage());
          orderDetail.setProductModel(cartDetail.getProductModel());
          orderDetail.setWeight(cartDetail.getWeight());
          orderDetail.setStockId(cartDetail.getStockId());
          orderDetail.setStockPriceOld(cartDetail.getStockPriceOld());
          orderDetail.setStockPrice(cartDetail.getStockPrice());
          orderDetail.setNum(cartDetail.getNum());
          orderDetail.setPostageType(cartDetail.getPostageType());
          orderDetail.setChannel(0);
          orderDetail.setStatus(1);
          orderDetail.setCreateBy(user.getId());
          orderDetail.setCreateDate(new Date());
          orderDetailService.insertOrderDetail(orderDetail);
          cartService.deleteCart(cartDetail.getId());
        } // for
      } // if
      shopId = cart.getShopId();// 再次初始化ShopId的值
    } // for

    // 生成订单
    // OrderPojo order = new OrderPojo();
    // order.setUserId(user.getId());
    // order.setSuserId(suserid);
    // order.setAllPrice(allCartPrice);
    // order.setFactPrice(allCartPrice);
    // order.setConsignee(address.getConsignee());
    // order.setConsigneeAddress(address.getProvinceName()+address.getCityName()+address.getAreaName()+address.getAddress());
    // order.setConsigneePhone(address.getConsigneePhone());
    // order.setConsigneeType(1);
    // order.setOrderStatus(2);
    // order.setStatus(1);
    // order.setPayStatus(1);
    // order.setCreateBy(user.getId());
    // order.setCreateDate(new Date());
    // order.setOrderNo(orderNo);
    // orderService.insertOrder(order);
    // 生成订单详情
    // OrderPojo insert_order =
    // orderService.findOrderByOrderNo(orderNo);
    // for(CartPojo cart:cartList){
    // cart.setAllStockPrice(cart.getStockPrice()*cart.getNum());
    // OrderDetailPojo orderDetail = new OrderDetailPojo();
    // orderDetail.setOrderId(insert_order.getId());
    // orderDetail.setUserId(user.getId());
    // orderDetail.setLoginname(user.getLoginname());
    // orderDetail.setShopId(cart.getShopId());
    // orderDetail.setProductId(cart.getProductId());
    // orderDetail.setProductName(cart.getProductName());
    // orderDetail.setProductModel(cart.getProductModel());
    // orderDetail.setStockId(cart.getStockId());
    // orderDetail.setStockPriceOld(cart.getStockPriceOld());
    // orderDetail.setStockPrice(cart.getStockPrice());
    // orderDetail.setNum(cart.getNum());
    // orderDetail.setChannel(0);
    // orderDetail.setStatus(1);
    // orderDetail.setCreateBy(user.getId());
    // orderDetail.setCreateDate(new Date());
    // orderDetailService.insertOrderDetail(orderDetail);
    // cartService.delCart(cart.getId());
    // }

    // 生成一条未付款支付宝信息
    // AlipayOrderInfoPojo alipayOrderInfoPojo = new AlipayOrderInfoPojo();
    // alipayOrderInfoPojo.setOutTradeNo(out_trade_no);
    // alipayOrderInfoPojo.setTotalFee(totalfee);
    // alipayOrderInfoPojo.setTradeStatus("WAIT_BUYER_PAY");
    // alipayOrderInfoPojo.setCreateDate(new Date());
    // alipayOrderInfoPojo.setVersion(0);
    // alipayOrderInfoService.insertOne(alipayOrderInfoPojo);
    //
    // ActionContext ac = ActionContext.getContext();
    // ac.put("orderDetailConfirmList", cartList);
    // ac.put("orderDetailConfirm", orderDetailConfirm);
    // ac.put("hCartPrice", totalfee);
    // ac.put("consignee", address.getConsignee());
    // ac.put("address", address.getProvinceName() + address.getCityName()
    // + address.getAreaName() + address.getAddress());
    // ac.put("phone", address.getConsigneePhone());
    // ac.put("out_trade_no", out_trade_no);
    // ac.put("body", body);

    // 这个地方执行后我要调整到mergeOrder。do?orderNo=45656565656565656565656;
    setOutTradeNo(out_trade_no);

    Double allOrderPrice = 0.0;
    List<OrderPojo> outOrderList = orderService.getOrderByoutTradeNo(out_trade_no);// 获取商品信息
    for (OrderPojo outOrder : outOrderList) {
      allOrderPrice = allOrderPrice + outOrder.getFactPrice();
    }
    // 生成一条未付款支付宝信息
    AlipayOrderInfoPojo alipayOrderInfoPojo = new AlipayOrderInfoPojo();
    alipayOrderInfoPojo.setOutTradeNo(out_trade_no);
    alipayOrderInfoPojo.setTotalFee(df.format(allOrderPrice));
    alipayOrderInfoPojo.setTradeStatus("WAIT_BUYER_PAY");
    alipayOrderInfoPojo.setCreateDate(new Date());
    alipayOrderInfoPojo.setVersion(0);
    alipayOrderInfoService.insertOne(alipayOrderInfoPojo);
    return SUCCESS;
    // return new
    // ActionForward("/mergeOrder.do?out_trade_no="+out_trade_no);
  }

  /**
   * 后台订单导出Excel表格
   * 
   * @return
   * @throws Exception
   */
  public void getOrderExcel2() throws Exception {
    List<OrderPojo> orderList = null;
    String oos = order.getOs();
    // order.setTids(tids);
    // System.out.println("--->"+tids.length);

    Map<String, Object> map = new HashMap<String, Object>();
    if (tids != null && !tids.equals("")) {
      map.put("tids", tids);
    }
    // 过滤记事本中的userId
    /*
     * String filePath1 = ""; String fileName1 = "userId.txt"; List<Integer> a = new ArrayList<>();
     * filePath1 = ServletActionContext.getServletContext().getRealPath( "/temp") + File.separator +
     * fileName1; File file1 = new File(filePath1); if (file1.isFile() && file1.exists()) { //
     * 判断文件是否存在 InputStreamReader read = new InputStreamReader(new FileInputStream( file1),
     * "utf-8");// 编码格式 BufferedReader bufferedReader = new BufferedReader(read); String lineTxt =
     * null; while ((lineTxt = bufferedReader.readLine()) != null) {
     * a.add(Integer.parseInt(lineTxt)); } read.close(); }
     */
    testUsers = SellerService.getTestUsers();
    if (testUsers != null && testUsers.size() > 0) {
      if (testcount != null && !testcount.equals("")) {
        map.put("userIds", testUsers);
      } else {
        map.put("notuserIds", testUsers);
      }
    }
    if (oos != null && !oos.equals("") && !oos.equals("6") & !oos.equals("7")) {
      map.put("orderStatus", Integer.parseInt(oos));
    } else if (oos.equals("6") || oos.equals("7")) {
      map.put("os", Integer.parseInt(oos));
    }
    if (a == null) {
      if (orderType == 1) {
        map.put("orderType", 1);
      } else {
        map.put("orderTypes", 1);
      }
    } else {
      map.put("a", 1);
    }
    if (isAll != null && isAll.equals("1")) {
      if (order != null) {
        map.put("orderNo", order.getOrderNo());
        map.put("consignee", order.getConsignee());
        map.put("consigneePhone", order.getConsigneePhone());
        map.put("consigneeAddress", order.getConsigneeAddress());
        map.put("payStatus", order.getPayStatus());

        map.put("refundStatus", order.getRefundStatus());
        map.put("overdue", order.getOverdue());
        // map.put("shopName", order.getShopName());
        map.put("createDate", order.getCreateDateString());
        map.put("sendDate", order.getSendTimes());

        map.put("tids", order.getTids());
        map.put("userName", order.getUserName());
        map.put("remarks", order.getRemarks());
        map.put("logisticsNo", order.getLogisticsNo());
        map.put("logisticsName", order.getLogisticsName());

        map.put("consigneeType", order.getConsigneeType());
        map.put("outTradeNo", order.getOutTradeNo());
        map.put("channel", order.getChannel());
        map.put("loginname", order.getLoginname());
        map.put("attendId", order.getAttendId());
        map.put("isRefund", order.getIsRefund());
        map.put("groupBeginDateStr", order.getGroupBeginDateStr());
        if (oos == null || "".equals(oos)) {
          map.put("orderStatus", order.getOrderStatus());
        }
        map.put("payMethod", order.getPayMethod());
        // map.put("pushName", order.getPushName());
        if (order.getBeganday() != null) {
          map.put("beganday", order.getBeganday());
        }
        if (order.getEndday() != null) {
          map.put("endday", order.getEndday());
        }
      }
    } else {
      if (tids != null && !tids.equals("")) {
        map.put("tids", tids);
      }
    }
    orderList = orderService.orderAllListExcel(map);
    switch (oos) {
      case "1":
        downloadFileName = "待付款订单.xls";
        break;
      case "2":
        downloadFileName = "待发货单.xls";
        break;
      case "3":
        downloadFileName = "已发货订单.xls";
        break;
      case "4":
        downloadFileName = "已确认订单.xls";
        break;
      case "5":
        downloadFileName = "已评论订单.xls";
        break;
      default:
        downloadFileName = "全部订单.xls";
        break;
    }
    WritableWorkbook wwb = null;
    OutputStream ots = null;
    String filePath = null;
    String fileName = null;
    fileName = downloadFileName;

    // filePath = "/home"+fileName;
    // 这里直接找到项目的路径，liunx和window路径不同，不能混淆在一起!!!
    filePath =
        ServletActionContext.getServletContext().getRealPath("/temp") + File.separator + fileName;
    File file = new File(filePath);
    if (!file.isFile()) {
      file.createNewFile();
    }
    try {
      ots = new FileOutputStream(file);
      wwb = Workbook.createWorkbook(ots);
      WritableSheet sheet = wwb.createSheet("sheet1", 0);
      sheet.addCell(new Label(0, 0, "商品订单号"));
      sheet.addCell(new Label(1, 0, "订单状态"));
      sheet.addCell(new Label(2, 0, "商品总价"));
      sheet.addCell(new Label(3, 0, "支付金额"));
      sheet.addCell(new Label(4, 0, "数量"));
      sheet.addCell(new Label(5, 0, "收货人"));
      sheet.addCell(new Label(6, 0, "收货手机"));
      sheet.addCell(new Label(7, 0, "省份"));
      sheet.addCell(new Label(8, 0, "市"));
      sheet.addCell(new Label(9, 0, "区"));
      sheet.addCell(new Label(10, 0, "收货地址"));
      sheet.addCell(new Label(11, 0, "创建时间"));
      sheet.addCell(new Label(12, 0, "成团时间"));
      sheet.addCell(new Label(13, 0, "订单来源"));
      sheet.addCell(new Label(14, 0, "商品id"));
      sheet.addCell(new Label(15, 0, "商品名称"));
      sheet.addCell(new Label(16, 0, "商品规格"));
      sheet.addCell(new Label(17, 0, "商品套餐类型"));
      sheet.addCell(new Label(18, 0, "商品编码"));
      sheet.addCell(new Label(19, 0, "快递公司"));
      sheet.addCell(new Label(20, 0, "物流单号"));
      sheet.addCell(new Label(21, 0, "团状态"));
      sheet.addCell(new Label(22, 0, "支付状态"));
      sheet.addCell(new Label(23, 0, "支付方式"));
      sheet.addCell(new Label(24, 0, "支付流水号"));
      sheet.addCell(new Label(25, 0, "客服留言"));
      sheet.addCell(new Label(26, 0, "售后状态"));
      sheet.addCell(new Label(27, 0, "商品货号"));


      ProductSkuLinkPojo productSkuLink = null;
      // String sku = "";
      Integer payMethod = 0;
      for (int j = 1, i = 1; j <= orderList.size(); j++) {
        OrderPojo orderPojo = orderList.get(j - 1);
        List<OrderDetailPojo> list2 =
            orderDetailService.getfindByUserIdOrderDetailExcel(orderPojo.getId());
        for (int s = 1; s <= list2.size(); s++) {
          OrderDetailPojo p = list2.get(s - 1);
          if (p.getSkuLinkId() != null && p.getSkuLinkId() > 0) {
            productSkuLink = new ProductSkuLinkPojo();
            productSkuLink.setId(Long.valueOf(p.getSkuLinkId()));
            productSkuLink = productSkuLinkService.findProductSkuLink(productSkuLink);
            // if (productSkuLink != null) {
            // sku =
            // "(" + "颜色：" + productSkuLink.getColorValue() + ",尺寸："
            // + productSkuLink.getFormatValue() + ")";
            // }
          }
          sheet.addCell(new Label(0, i, orderPojo.getOrderNo()));
          sheet.addCell(new Label(1, i, orderPojo.getOrderStatusName()));
          sheet.addCell(new Label(2, i, p.getAllPrice() + ""));
          sheet.addCell(new Label(3, i, orderPojo.getFactPrice() + ""));
          sheet.addCell(new Label(4, i, p.getNum() + ""));
          sheet.addCell(new Label(5, i, orderPojo.getConsignee()));
          sheet.addCell(new Label(6, i, orderPojo.getConsigneePhone()));

          sheet.addCell(new Label(7, i, StringUtil.checkVal(orderPojo.getProvince())));
          sheet.addCell(new Label(8, i, StringUtil.checkVal(orderPojo.getCity())));
          sheet.addCell(new Label(9, i, StringUtil.checkVal(orderPojo.getArea())));

          sheet.addCell(new Label(10, i, orderPojo.getConsigneeAddress()));
          sheet.addCell(new Label(11, i, orderPojo.getCreatDateString()));
          sheet.addCell(new Label(12, i, orderPojo.getGroupDateStr()));
          sheet.addCell(new Label(13, i, orderPojo.getSourceName()));
          sheet.addCell(new Label(14, i, p.getProductId() + ""));
          sheet.addCell(new Label(15, i, p.getProductName()));
          sheet.addCell(new Label(16, i, p.getSkuColor()));
          sheet.addCell(new Label(17, i, p.getSkuFormat()));
          sheet.addCell(new Label(18, i, p.getBusinessCode()));
          sheet.addCell(new Label(19, i, orderPojo.getLogisticsName()));
          sheet.addCell(new Label(20, i, orderPojo.getLogisticsNo()));
          if (orderPojo.getIsSuccess() == 1) {
            sheet.addCell(new Label(21, i, "拼团成功"));
          } else if (orderPojo.getIsSuccess() == 2) {
            sheet.addCell(new Label(21, i, "拼团失败"));
          } else if (orderPojo.getIsSuccess() == 0
              && (orderPojo.getSource() == 1 || orderPojo.getSource() == 2)) {
            sheet.addCell(new Label(21, i, "拼团中"));
          }
          sheet.addCell(new Label(22, i, orderPojo.getPayStatusName()));
          sheet.addCell(new Label(23, i, orderPojo.getPayMethodName()));
          payMethod = orderPojo.getPayMethod() == null ? 0 : orderPojo.getPayMethod();
          if (payMethod == 8 || payMethod == 2) {
            sheet.addCell(new Label(24, i, p.getTradeNos()));
          } else if (payMethod == 1) {
            sheet.addCell(new Label(24, i, p.getTradeNo()));
          } else {
            sheet.addCell(new Label(24, i, ""));
          }
          sheet.addCell(new Label(25, i, orderPojo.getRemarks()));
          sheet.addCell(new Label(26, i, orderPojo.getIsCancelOrderName()));
          sheet.addCell(new Label(27, i, p.getProductNum()));

          i++;
        }
      }
      wwb.write();
      ots.flush();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (wwb != null) {
          wwb.close();
        }
        if (ots != null) {
          ots.close();
        }
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }

    // FileUtil.copyFile(filePath,
    // ServletActionContext.getServletContext().getRealPath("/temp/"+fileName));
    // File delfile = new File(filePath);
    // if (delfile.isFile() && delfile.exists()) {
    // delfile.delete();
    // }
    HttpServletResponse response = ServletActionContext.getResponse();
    // 设置文件ContentType类型，这样设置，会自动判断下载文件类型
    response.setContentType("application/vnd.ms-excel");
    String fileNames = new String(fileName.getBytes("GB2312"), "ISO_8859_1");
    response.setHeader("Content-Disposition", "attachment;fileName=" + fileNames);
    ServletOutputStream out;
    try {
      FileInputStream fileInputStream = new FileInputStream(filePath);
      out = response.getOutputStream();
      int i = 0;
      while ((i = fileInputStream.read()) != -1) {
        out.write(i);
      }
      fileInputStream.close();
      out.close();

      File delfile = new File(filePath);
      // 路径为文件且不为空则进行删除
      if (delfile.isFile() && delfile.exists()) {
        delfile.delete();
      }

    } catch (IOException e) {

      e.printStackTrace();
    }
  }

  public void getOrderExcelExport() throws Exception {
    List<OrderPojo> orderList = null;
    String oos = order == null ? "" : order.getOs();
    switch (oos) {
      case "1":
        downloadFileName = "待付款订单.xls";
        break;
      case "2":
        downloadFileName = "待发货单.xls";
        break;
      case "3":
        downloadFileName = "已发货订单.xls";
        break;
      case "4":
        downloadFileName = "已确认订单.xls";
        break;
      case "5":
        downloadFileName = "已评论订单.xls";
        break;
      case "6":
        downloadFileName = "拼团中订单.xls";
        break;
      case "7":
        downloadFileName = "拼团失败订单.xls";
        break;
      default:
        downloadFileName = "全部订单.xls";
        break;
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (tids != null && !tids.equals("")) {
      map.put("tids", tids);
    } else {
      // 过滤记事本中的测试用户userId
      testUsers = SellerService.getTestUsers();
      if (testUsers != null && testUsers.size() > 0) {
        if (testcount != null && !testcount.equals("")) {
          map.put("userIds", testUsers);
        } else {
          map.put("notuserIds", testUsers);
        }
      }
      // 是否导入订单
      // a=1 :非导入订单+ 导入订单已付款部分
      // orderType : 1 导入订单 <>1 非导入订单
      if (a == null) {
        if (orderType == 1) {
          map.put("orderType", 1);
        } else {
          map.put("orderTypes", 1);
        }
      } else {
        map.put("a", 1);
      }
      // os 订单状态菜单标识
      if (oos != null && !oos.equals("") && !oos.equals("6") & !oos.equals("7")) {
        map.put("orderStatus", Integer.parseInt(oos));
      } else if (oos.equals("6") || oos.equals("7")) {
        map.put("os", Integer.parseInt(oos));
      }
      // isAll ： 1 导全部 <>1 导查询结果
      if (isAll == null || !"1".equals(isAll)) {
        if (order != null) {
          map.put("orderNo", order.getOrderNo());
          map.put("productId", order.getProductId());
          map.put("consignee", order.getConsignee());
          map.put("consigneePhone", order.getConsigneePhone());
          map.put("consigneeAddress", order.getConsigneeAddress());
          map.put("payStatus", order.getPayStatus());

          map.put("refundStatus", order.getRefundStatus());
          map.put("overdue", order.getOverdue());
          // map.put("shopName", order.getShopName());
          map.put("createDate", order.getCreateDateString());
          map.put("sendDate", order.getSendTimes());

          map.put("userName", order.getUserName());
          map.put("remarks", order.getRemarks());
          map.put("logisticsNo", order.getLogisticsNo());
          map.put("logisticsName", order.getLogisticsName());

          map.put("consigneeType", order.getConsigneeType());
          map.put("outTradeNo", order.getOutTradeNo());
          map.put("channel", order.getChannel());
          map.put("loginname", order.getLoginname());
          if (oos == null || "".equals(oos)) {
            map.put("orderStatus", order.getOrderStatus());
          }
          map.put("payMethod", order.getPayMethod());
          // map.put("pushName", order.getPushName());
          if (order.getBeganday() != null) {
            map.put("beganday", order.getBeganday());
          }
          if (order.getEndday() != null) {
            map.put("endday", order.getEndday());
          }
          if (order.getBeganday1() != null) {
            map.put("beganday1", order.getBeganday1());
          }
          if (order.getEndday1() != null) {
            map.put("endday1", order.getEndday1());
          }
          map.put("attendId", order.getAttendId());
          map.put("isRefund", order.getIsRefund());
          map.put("source", order.getSource());
          map.put("groupBeginDateStr", order.getGroupBeginDateStr());
          map.put("groupEndDateStr", order.getGroupEndDateStr());
          map.put("beginSendDateStr", order.getBeganSendDate());
          map.put("EndSendDateStr", order.getEndSendDate());
          map.put("pdkLoginname", order.getPdkLoginname());
        }
      }

    }

    orderList = orderService.exportOrderExcel(map);

    WritableWorkbook wwb = null;
    OutputStream ots = null;
    String filePath = null;
    String fileName = downloadFileName;

    filePath =
        ServletActionContext.getServletContext().getRealPath("/temp") + File.separator + fileName;
    File file = new File(filePath);
    if (!file.isFile()) {
      file.createNewFile();
    }
    try {
      ots = new FileOutputStream(file);
      wwb = Workbook.createWorkbook(ots);
      WritableSheet sheet = wwb.createSheet("sheet1", 0);
      sheet.addCell(new Label(0, 0, "用户账号"));
      sheet.addCell(new Label(1, 0, "商品订单号"));
      sheet.addCell(new Label(2, 0, "订单状态"));
      sheet.addCell(new Label(3, 0, "商品总价"));
      sheet.addCell(new Label(4, 0, "支付金额"));
      sheet.addCell(new Label(5, 0, "数量"));
      sheet.addCell(new Label(6, 0, "收货人"));
      sheet.addCell(new Label(7, 0, "收货手机"));
      sheet.addCell(new Label(8, 0, "省份"));
      sheet.addCell(new Label(9, 0, "市"));
      sheet.addCell(new Label(10, 0, "区"));
      sheet.addCell(new Label(11, 0, "收货地址"));
      sheet.addCell(new Label(12, 0, "创建时间"));
      sheet.addCell(new Label(13, 0, "成团时间"));
      sheet.addCell(new Label(14, 0, "订单来源"));
      sheet.addCell(new Label(15, 0, "商品id"));
      sheet.addCell(new Label(16, 0, "商品名称"));
      sheet.addCell(new Label(17, 0, "商品规格"));
      sheet.addCell(new Label(18, 0, "商品套餐类型"));
      sheet.addCell(new Label(19, 0, "商品编码"));
      sheet.addCell(new Label(20, 0, "快递公司"));
      sheet.addCell(new Label(21, 0, "物流单号"));
      sheet.addCell(new Label(22, 0, "团状态"));
      sheet.addCell(new Label(23, 0, "支付状态"));
      sheet.addCell(new Label(24, 0, "支付方式"));
      sheet.addCell(new Label(25, 0, "支付流水号"));
      sheet.addCell(new Label(26, 0, "买家留言"));
      sheet.addCell(new Label(27, 0, "客服留言"));
      sheet.addCell(new Label(28, 0, "售后状态"));
      sheet.addCell(new Label(29, 0, "商品货号"));
      sheet.addCell(new Label(30, 0, "拼得客账号"));
      sheet.addCell(new Label(31, 0, "是否团长"));
      sheet.addCell(new Label(32, 0, "返佣金额"));
      sheet.addCell(new Label(33, 0, "返佣比例"));

      Integer payMethod = 0;
      Integer orderStatus = 0;
      Integer source = 0;
      Integer isRefund = 0;
      for (int j = 1, i = 1; j <= orderList.size(); j++) {
        OrderPojo orderPojo = orderList.get(j - 1);
        sheet.addCell(new Label(0, i, orderPojo.getLoginname()));
        sheet.addCell(new Label(1, i, orderPojo.getOrderNo()));
        orderStatus = orderPojo.getOrderStatus() == null ? 0 : orderPojo.getOrderStatus();
        switch (orderStatus) {
          case 1:
            sheet.addCell(new Label(2, i, "待付款"));
            break;
          case 2:
            if (orderPojo.getIsSuccess() == 1) {
              sheet.addCell(new Label(2, i, "待发货"));
            } else if (orderPojo.getIsSuccess() == 2) {
              sheet.addCell(new Label(2, i, "拼团失败"));
            } else {
              sheet.addCell(new Label(2, i, "拼团中"));
            }
            break;
          case 3:
            sheet.addCell(new Label(2, i, "已发货"));
            break;
          case 4:
            sheet.addCell(new Label(2, i, "已确认"));
            break;
          case 5:
            sheet.addCell(new Label(2, i, "已评论"));
            break;
          default:
            sheet.addCell(new Label(2, i, ""));
            break;
        }

        sheet.addCell(new Label(3, i, orderPojo.getAllPrice() + ""));
        sheet.addCell(new Label(4, i, orderPojo.getFactPrice() + ""));
        sheet.addCell(new Label(5, i, orderPojo.getNum() + ""));
        sheet.addCell(new Label(6, i, orderPojo.getConsignee()));
        sheet.addCell(new Label(7, i, orderPojo.getConsigneePhone()));

        sheet.addCell(new Label(8, i, StringUtil.checkVal(orderPojo.getProvince())));
        sheet.addCell(new Label(9, i, StringUtil.checkVal(orderPojo.getCity())));
        sheet.addCell(new Label(10, i, StringUtil.checkVal(orderPojo.getArea())));

        sheet.addCell(new Label(11, i, orderPojo.getConsigneeAddress()));
        sheet.addCell(new Label(12, i, orderPojo.getCreatDateString()));
        sheet.addCell(new Label(13, i, orderPojo.getGroupDateStr()));
        source = orderPojo.getSource() == null ? 0 : orderPojo.getSource();
        switch (source) {
          case 1:
            sheet.addCell(new Label(14, i, "普通拼团"));
            break;
          case 2:
            sheet.addCell(new Label(14, i, "团免"));
            break;
          case 3:
            sheet.addCell(new Label(14, i, "猜价"));
            break;
          case 4:
            sheet.addCell(new Label(14, i, "单独购"));
            break;
          case 5:
            sheet.addCell(new Label(14, i, "0.1夺宝"));
            break;
          case 6:
            sheet.addCell(new Label(14, i, "掌上秒杀"));
            break;
          case 7:
            sheet.addCell(new Label(14, i, "免费抽奖"));
            break;
          case 8:
            sheet.addCell(new Label(14, i, "拼得客"));
            break;
          default:
            sheet.addCell(new Label(14, i, ""));
            break;
        }
        sheet.addCell(new Label(15, i, orderPojo.getProductId()));
        sheet.addCell(new Label(16, i, orderPojo.getProductName()));
        sheet.addCell(new Label(17, i, orderPojo.getSkuColor()));
        sheet.addCell(new Label(18, i, orderPojo.getSkuFormat()));
        sheet.addCell(new Label(19, i, orderPojo.getBusinessCode()));
        sheet.addCell(new Label(20, i, orderPojo.getLogisticsName()));
        sheet.addCell(new Label(21, i, orderPojo.getLogisticsNo()));
        if (orderPojo.getIsSuccess() == 1) {
          sheet.addCell(new Label(22, i, "拼团成功"));
        } else if (orderPojo.getIsSuccess() == 2) {
          sheet.addCell(new Label(22, i, "拼团失败"));
        } else if (orderPojo.getIsSuccess() == 0) {
          sheet.addCell(new Label(22, i, "拼团中"));
        }
        if (orderPojo.getPayStatus() != null && orderPojo.getPayStatus() == 1) {
          sheet.addCell(new Label(23, i, "已付款"));
        } else {
          sheet.addCell(new Label(23, i, "未付款"));
        }

        payMethod = orderPojo.getPayMethod() == null ? 0 : orderPojo.getPayMethod();
        if (payMethod == 8 || payMethod == 2) {
          sheet.addCell(new Label(24, i, "微信"));
          sheet.addCell(new Label(25, i, orderPojo.getTradeNos()));
        } else if (payMethod == 1) {
          sheet.addCell(new Label(24, i, "支付宝"));
          sheet.addCell(new Label(25, i, orderPojo.getTradeNo()));
        } else if (payMethod == 4) {
          sheet.addCell(new Label(24, i, "钱包"));
          sheet.addCell(new Label(25, i, ""));
        } else {
          sheet.addCell(new Label(24, i, ""));
          sheet.addCell(new Label(25, i, ""));
        }
        sheet.addCell(new Label(26, i, orderPojo.getBuyerMessage()));
        sheet.addCell(new Label(27, i, orderPojo.getRemarks()));
        isRefund = orderPojo.getIsRefund() == null ? 4 : orderPojo.getIsRefund();
        switch (isRefund) {
          case 0:
            sheet.addCell(new Label(28, i, "未退款"));
            break;
          case 1:
            sheet.addCell(new Label(28, i, "退款中"));
            break;
          case 2:
            sheet.addCell(new Label(28, i, "退款成功"));
            break;
          case 3:
            sheet.addCell(new Label(28, i, "退款失败"));
            break;

          default:
            sheet.addCell(new Label(28, i, ""));
            break;
        }
        sheet.addCell(new Label(29, i, orderPojo.getProductNum()));
        sheet.addCell(new Label(30, i, orderPojo.getPdkLoginname()));
        if (orderPojo.getIsHead() != null && orderPojo.getIsHead() == 1) {
          sheet.addCell(new Label(31, i, "是"));
        } else {
          sheet.addCell(new Label(31, i, "否"));
        }
        sheet.addCell(new Label(32, i, StringUtil.checkVal(orderPojo.getRebatePrice())));
        sheet.addCell(new Label(33, i, StringUtil.checkVal(orderPojo.getRebateRatio())));
        i++;
      }
      wwb.write();
      ots.flush();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (wwb != null) {
          wwb.close();
        }
        if (ots != null) {
          ots.close();
        }
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }

    HttpServletResponse response = ServletActionContext.getResponse();
    // 设置文件ContentType类型，这样设置，会自动判断下载文件类型
    response.setContentType("application/vnd.ms-excel");
    String fileNames = new String(fileName.getBytes("GB2312"), "ISO_8859_1");
    response.setHeader("Content-Disposition", "attachment;fileName=" + fileNames);
    ServletOutputStream out;
    byte[] buff = null;
    try {
      FileInputStream fileInputStream = new FileInputStream(filePath);
      out = response.getOutputStream();
      buff = new byte[1024];
      while (fileInputStream.read(buff) != -1) {
        out.write(buff);
        out.flush();
      }
      fileInputStream.close();
      out.close();

      File delfile = new File(filePath);
      // 路径为文件且不为空则进行删除
      if (delfile.isFile() && delfile.exists()) {
        delfile.delete();
      }

    } catch (IOException e) {

      e.printStackTrace();
    }
  }

  public String getOrderExcel() throws Exception {
    List<OrderPojo> orderList;
    String oos = order.getOs();
    // System.out.println("--->"+tids.length);
    order.setTids(tids);

    orderList = orderService.orderAllList(order, null, oos);
    switch (oos) {
      case "1":
        downloadFileName = "待付款订单.xls";
        break;
      case "2":
        downloadFileName = "待发货单.xls";
        break;
      case "3":
        downloadFileName = "已发货订单.xls";
        break;
      case "4":
        downloadFileName = "已确认订单.xls";
        break;

      default:
        downloadFileName = "全部订单.xls";
        break;
    }
    ExcelProcessor<OrderPojo> e = new ExcelProcessor<OrderPojo>(orderList, OrderPojo.class, "订单");
    inputStream = e.generateExcelSteam();
    return SUCCESS;
  }

  public String mergeOrder() throws Exception {
    UserUtil.getWebUser();
    Double allOrderPrice = 0.0;
    String consignee = "";
    String address = "";
    String phone = "";
    ActionContext ac = ActionContext.getContext();
    List<OrderPojo> outOrderList = orderService.getOrderByoutTradeNo(outTradeNo);// 获取商品信息
    ac.put("outOrderList", outOrderList);
    // 订单描述--支付宝
    String body = "";
    DecimalFormat df = new DecimalFormat(".#");
    for (OrderPojo outOrder : outOrderList) {
      allOrderPrice = allOrderPrice + outOrder.getFactPrice();
      consignee = outOrder.getConsignee();
      address = outOrder.getConsigneeAddress();
      phone = outOrder.getConsigneePhone();
      consigneeTypeName = outOrder.getConsigneeTypeName();
      List<OrderDetailPojo> cartList = orderDetailService.getOrderDetail(outOrder.getId());// 获取对应商品信息
      // ac.put("cartList"+outOrder.getId(), cartList);
      outOrder.setCartList(cartList);

      for (OrderDetailPojo dcart : cartList) {
        body = body + dcart.getProductName() + ";";
        dcart.setAllStockPrice(Double.valueOf(df.format(dcart.getStockPrice() * dcart.getNum())));
      }
    }

    ac.put("consignee", consignee);
    ac.put("address", address);
    ac.put("phone", phone);
    ac.put("consigneeTypeName", consigneeTypeName);
    ac.put("body", body);
    ac.put("out_trade_no", outTradeNo);
    ac.put("hCartPrice", df.format(allOrderPrice));
    // ac.put("orderDetailConfirmList", cartList);
    // ac.put("orderDetailConfirm", orderDetailConfirm);
    // ac.put("hCartPrice", allOrderPrice);
    // ac.put("consignee", address.getConsignee());
    // ac.put("address", address.getProvinceName() + address.getCityName()
    // + address.getAreaName() + address.getAddress());
    // ac.put("phone", address.getConsigneePhone());
    // ac.put("out_trade_no", out_trade_no);
    // ac.put("body", body);
    return SUCCESS;

  }

  // 订单支付页面
  public String goOrderPay() throws Exception {

    // 生成淘宝订单
    // 支付类型
    String payment_type = "1";
    // 必填，不能修改
    // 服务器异步通知页面路径
    // String notify_url = "http://www.fenxiao0.com:8080/getnotifyUrl.do";
    String notify_url = "http://www.taozhuma.com/getnotifyUrl.do";
    // "http://商户网关地址/create_direct_pay_by_user-JAVA-UTF-8/notify_url.jsp";
    // 需http://格式的完整路径，不能加?id=123这类自定义参数 //页面跳转同步通知页面路径
    // String return_url = "http://www.fenxiao0.com:8080/getreturnUrl.do";
    String return_url = "";
    // "http://商户网关地址/create_direct_pay_by_user-JAVA-UTF-8/return_url.jsp";
    // 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/ //卖家支付宝帐户
    String seller_email = "taozhuma@5315.cn";
    // 必填 //付款金额
    String total_fee = totalfee;
    // 商品展示地址
    String show_url = "";
    // 需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html //防钓鱼时间戳
    String anti_phishing_key = "";
    // 若要使用请调用类文件submit中的query_timestamp函数 //客户端的IP地址
    String exter_invoke_ip = "";
    // 非局域网的外网IP地址，如：221.0.0.1

    // 默认支付方式
    String paymethod = "bankPay";
    // 必填
    // 默认网银
    String defaultbank = bankcode;

    // 把请求参数打包成数组
    Map<String, String> sParaTemp = new HashMap<String, String>();
    sParaTemp.put("service", "create_direct_pay_by_user");
    sParaTemp.put("partner", AlipayConfig.partner);
    sParaTemp.put("_input_charset", AlipayConfig.input_charset);
    sParaTemp.put("payment_type", payment_type);
    sParaTemp.put("notify_url", notify_url);
    sParaTemp.put("return_url", return_url);
    sParaTemp.put("seller_email", seller_email);
    sParaTemp.put("total_fee", total_fee);
    // 超出1000长度截掉
    if (cbody.length() > 500) {
      cbody = cbody.substring(0, 495) + "...";
    }
    sParaTemp.put("body", cbody);
    sParaTemp.put("show_url", show_url);
    sParaTemp.put("anti_phishing_key", anti_phishing_key);
    sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
    sParaTemp.put("extra_common_param", extra_common_param);

    // 单订重新付款重新生成out_trade_no
    if (extra_common_param != null && !extra_common_param.equals("")
        && extra_common_param.length() != 0) {
      // 商户订单号--支付宝
      outTradeNo = UtilDate.getOrderNum() + UtilDate.getThree();
      // 更新该订单的out_trade_no
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("outTradeNo", outTradeNo);
      map.put("orderId", Long.parseLong(extra_common_param));
      orderService.updateOutTradeNo(map);
      // 生成一条新未付款支付宝信息
      AlipayOrderInfoPojo alipayOrderInfoPojo = new AlipayOrderInfoPojo();
      alipayOrderInfoPojo.setOutTradeNo(outTradeNo);
      alipayOrderInfoPojo.setTotalFee(total_fee);
      alipayOrderInfoPojo.setTradeStatus("WAIT_BUYER_PAY");
      alipayOrderInfoPojo.setCreateDate(new Date());
      alipayOrderInfoPojo.setVersion(0);
      if (!bankcode.equals("alipay")) {
        alipayOrderInfoPojo.setPayType(2);
      } else {
        alipayOrderInfoPojo.setPayType(1);
      }
      alipayOrderInfoService.insertOne(alipayOrderInfoPojo);

    } else {
      AlipayOrderInfoPojo alipayOrderInfoPojo = new AlipayOrderInfoPojo();
      alipayOrderInfoPojo.setOutTradeNo(outTradeNo);
      if (!bankcode.equals("alipay")) {
        alipayOrderInfoPojo.setPayType(2);
      } else {
        alipayOrderInfoPojo.setPayType(1);
      }
      alipayOrderInfoService.updatePayType(alipayOrderInfoPojo);
    }
    if (!bankcode.equals("alipay")) {
      sParaTemp.put("paymethod", paymethod);
      sParaTemp.put("defaultbank", defaultbank);
    }
    // 商户网站订单系统中唯一订单号，必填 //订单名称
    String subject = "拼得好-订单号：" + outTradeNo;
    sParaTemp.put("subject", subject);
    sParaTemp.put("out_trade_no", outTradeNo);

    // 建立请求
    String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
    // System.out.println(sHtmlText);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    ServletActionContext.getResponse().getWriter().write(sHtmlText);

    // orderDetailConfirm = orderService
    // .getfindByIdOrderWeb(orderPojo.getId());
    // orderDetailConfirmList = orderDetailService
    // .getfindByUserIdOrderDetail(orderPojo.getId());
    // List<OrderDetailPojo> temp = new ArrayList<OrderDetailPojo>();
    // for (OrderDetailPojo orderDetail : orderDetailConfirmList) {
    // orderDetail.setAllStockPrice(orderDetail.getStockPrice()
    // * orderDetail.getNum());
    // temp.add(orderDetail);
    // }
    // ActionContext ac = ActionContext.getContext();
    // ac.put("orderDetailConfirmList", temp);
    // ac.put("orderDetailConfirm", orderDetailConfirm);

    return null;

  }

  // 订单支付页面
  public String goOrderDetailPay() throws Exception {
    String dy = "";
    orderDetailConfirm = orderService.getfindByIdOrderWeb(orderPojo.getId());
    orderDetailConfirmList = orderDetailService.getfindByUserIdOrderDetail(orderPojo.getId());
    List<OrderDetailPojo> temp = new ArrayList<OrderDetailPojo>();
    for (OrderDetailPojo orderDetail : orderDetailConfirmList) {
      orderDetail.setAllStockPrice(orderDetail.getStockPrice() * orderDetail.getNum());
      temp.add(orderDetail);
      dy = dy + orderDetail.getProductName() + ";";
    }
    ActionContext ac = ActionContext.getContext();
    ac.put("orderDetailConfirmList", temp);
    ac.put("orderDetailConfirm", orderDetailConfirm);
    ac.put("body", dy);
    // ac.put("orderPojo", orderPojo);

    return SUCCESS;

  }

  /**
   * 支付宝异步跳转
   */
  public void getnotifyUrl() throws Exception {
    // 获取支付宝GET过来反馈信息
    Map<String, String> params = new HashMap<String, String>();

    Map requestParams = ServletActionContext.getRequest().getParameterMap();
    for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
      String name = (String) iter.next();
      String[] values = (String[]) requestParams.get(name);
      String valueStr = "";
      for (int i = 0; i < values.length; i++) {
        valueStr = i == values.length - 1 ? valueStr + values[i] : valueStr + values[i] + ",";
      }
      // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
      // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
      params.put(name, valueStr);
    }
    System.out.println(">>>>>支付宝异步支付返回:" + params);
    // params.get("out_trade_no");
    // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
    // 商户订单号

    /*
     * String out_trade_no = new
     * String(ServletActionContext.getRequest().getParameter("out_trade_no")
     * .getBytes("ISO-8859-1"), "UTF-8");
     * 
     * // 支付宝交易号
     * 
     * String trade_no = new String(ServletActionContext.getRequest().getParameter("trade_no").
     * getBytes("ISO-8859-1"), "UTF-8");
     */

    // 交易状态
    String trade_status =
        new String(ServletActionContext.getRequest().getParameter("trade_status")
            .getBytes("ISO-8859-1"), "UTF-8");

    // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

    // 计算得出通知验证结果
    boolean verify_result = AlipayNotify.verify(params);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");

    if (verify_result) {// 验证成功
      // ////////////////////////////////////////////////////////////////////////////////////////
      // 请在这里加上商户的业务逻辑程序代码

      // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
      if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
        // 判断该笔订单是否在商户网站中已经做过处理
        // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
        // 如果有做过处理，不执行商户的业务程序
        // ServletActionContext.getResponse().getWriter()
        // .write("交易成功<br />");
        // ServletActionContext.getResponse().getWriter()
        // .write(params.toString());
        String outTradeNo = params.get("out_trade_no");
        System.out.println(">>>>>>支付宝支付流水号:" + outTradeNo);
        AlipayOrderInfoPojo alipayOrderInfoPojo =
            alipayOrderInfoService.findPayInfoByTradeNo(outTradeNo);

        if (alipayOrderInfoPojo != null
            && "WAIT_BUYER_PAY".equals(alipayOrderInfoPojo.getTradeStatus())) {
          // 验证价格
          Double totalFee = Double.valueOf(params.get("total_fee"));
          Double aliTotalFee = Double.valueOf(alipayOrderInfoPojo.getTotalFee());
          if (totalFee.doubleValue() == aliTotalFee.doubleValue()) {
            // 更新支付宝订单状态
            alipayOrderInfoPojo.setTradeNo(params.get("trade_no"));
            alipayOrderInfoPojo.setBuyerEmail(params.get("buyer_email"));
            alipayOrderInfoPojo.setBuyerId(params.get("buyer_id"));
            alipayOrderInfoPojo.setTradeStatus(params.get("trade_status"));
            alipayOrderInfoPojo.setOutTradeNo(params.get("out_trade_no"));
            alipayOrderInfoPojo.setUpdateDate(new Date());
            // 异步标志
            alipayOrderInfoPojo.setUpdateBy(998L);
            alipayOrderInfoService.updateOrder(alipayOrderInfoPojo);
            // 单订单标志
            String extra_common_param = params.get("extra_common_param");
            // 改变支付状态
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("outTradeNo", params.get("out_trade_no"));
            map.put("updateDate", new Date());
            if (extra_common_param != null && !extra_common_param.equals("")
                && extra_common_param.length() != 0) {
              map.put("orderId", Long.parseLong(extra_common_param));
            }
            orderService.orderPaySuccess(map);
            // if (extra_common_param != null && !extra_common_param.equals("") &&
            // extra_common_param.length() != 0) {
            // 付款成功更新优惠券状态
            // 多张订单支付时
            List<OrderPojo> orders = orderService.getOrderByoutTradeNo(outTradeNo);
            if (orders != null && orders.size() > 0) {
              orderPojo = orders.get(0);
              // 支付成功参团/开团处理
              grouponService.groupOrderHandle(orderPojo.getActivityId(), orderPojo.getSourceId(),
                  orderPojo.getUserId(), orderPojo.getSource(), orderPojo.getId());

              try {
                // 添加商品限购
                OrderPojo order = orderService.findOrderByOrderNo(orderPojo.getOrderNo());
                if (order != null && order.getSource() != null && order.getSource() == 1
                    && order.getMaxNum() != null && order.getMaxNum() > 0) {
                  Map<String, Object> params2 = new HashMap<String, Object>();
                  params2.put("userId", orderPojo.getUserId());
                  params2.put("activityId", orderPojo.getActivityId());
                  params2.put("productId", Long.valueOf(order.getProductId()));
                  List<ProductRestrictionPojo> productRestrictionPojos =
                      productRestrictionService.listPage(params2);
                  if (productRestrictionPojos != null && productRestrictionPojos.size() > 0) {
                    ProductRestrictionPojo productRestriction1 = productRestrictionPojos.get(0);
                    ProductRestrictionPojo productRestriction = new ProductRestrictionPojo();
                    productRestriction.setBuyNum(productRestriction1.getBuyNum() + order.getNum());
                    productRestriction.setMaxNum(order.getMaxNum());// 为了适应限购数量有改变
                    productRestriction.setId(productRestriction1.getId());
                    int i = productRestrictionService.update(productRestriction);
                    if (i > 0) {
                      Util.log("修改商品限购成功：id-" + productRestriction1.getId());
                    } else {
                      Util.log("修改商品限购失败：id-" + productRestriction1.getId());
                    }
                  } else {
                    ProductRestrictionPojo productRestriction = new ProductRestrictionPojo();
                    productRestriction.setUserId(orderPojo.getUserId());
                    productRestriction.setActivityId(orderPojo.getActivityId());
                    productRestriction.setProductId(Long.valueOf(order.getProductId()));
                    productRestriction.setBuyNum(order.getNum());
                    productRestriction.setMaxNum(order.getMaxNum());
                    int i = productRestrictionService.add(productRestriction);
                    if (i > 0) {
                      Util.log("添加商品限购成功：userId-" + orderPojo.getUserId() + "/activityId-"
                          + orderPojo.getActivityId() + "/productId-" + order.getProductId());
                    } else {
                      Util.log("添加商品限购失败：userId-" + orderPojo.getUserId() + "/activityId-"
                          + orderPojo.getActivityId() + "/productId-" + order.getProductId());
                    }
                  }
                } else {
                  Util.log("添加商品限购，订单不存在了：orderId-" + orderPojo.getId());
                }
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
            if (orders != null && orders.size() > 1) {
              Map<String, Object> orderMap = new HashMap<String, Object>();
              List<CouponOrderPojo> couponOrders = null;
              CouponOrderPojo couponOrder = null;
              for (OrderPojo orderPojo : orders) {
                orderMap.put("orderId", orderPojo.getId());
                couponOrders = couponService.getcouponOrderList(orderMap);
                if (couponOrders != null && couponOrders.size() > 0
                    && couponOrders.get(0).getStatus() == 0) {
                  couponOrder = new CouponOrderPojo();
                  couponOrder.setStatus(1);
                  couponOrder.setCouponNo(couponOrders.get(0).getCouponNo());
                  couponOrder.setOrderId(couponOrders.get(0).getOrderId());
                  // 更新合单支付所有订单的用券状态
                  couponService.updateCouponOrderStatus(couponOrder);
                }
              }
            }

            // 添加销售量
            List<OrderDetailPojo> orderDetaillist =
                orderDetailService.getOrderDetailByOutTradeNo(params.get("out_trade_no"));
            if (orderDetaillist != null && orderDetaillist.size() > 0) {
              for (OrderDetailPojo p : orderDetaillist) {
                Map<String, Object> map1 = new HashMap<String, Object>();
                ProductPojo productPojo = new ProductPojo();
                productPojo.setId(p.getProductId());
                productPojo = productService.findProduct(productPojo);
                if (productPojo != null) {
                  map1.put("id", productPojo.getId());
                  map1.put("sellNumber", productPojo.getSellNumber() + p.getNum());
                  productService.updateProductsellNumber(map1);
                  ProductSellPojo productSellPojo = new ProductSellPojo();
                  productSellPojo.setProductId(productPojo.getId());
                  productSellPojo.setSellNumber(productPojo.getSellNumber() + p.getNum());
                  // 根据商品id查找对应商品当日销售量
                  ProductSellPojo productSell = productSellService.getById(productPojo.getId());
                  productSellPojo.setDaySell(productSell.getDaySell() + p.getNum());
                  productSellPojo.setUpdateDate(new Date());
                  productSellService.update(productSellPojo);

                }
              }
            }

            alipayOrderInfoService.findPayInfoByTradeNo(outTradeNo);
            /*
             * // 活动期间满60送60 Date today = new Date(); if (payQry != null &&
             * ("TRADE_FINISHED".equals(payQry.getTradeStatus()) ||
             * "TRADE_SUCCESS".equals(payQry.getTradeStatus())) && StringUtil.stringDate(
             * "2015-12-07 00:00:00").compareTo(today) <= 0 && StringUtil.stringDate(
             * "2015-12-14 00:00:00").compareTo(today) > 0) {
             * giveUserCouponByOrderPay(Double.valueOf(payQry.getTotalFee()), uid); }
             */
            // 满40元赠送
            /*
             * if (payQry != null && payQry.getTradeStatus() != null) {
             * walletService.giveUserCouponByOrderPayWithDate(payQry.getTradeStatus(), uid,
             * Double.valueOf(payQry.getTotalFee())); }
             */
            System.out.println("支付成功！");
          } else {
            System.out.println("支付价格与订单价格不一致!");
          }
        } else {
          System.out.println("未找到支付记录或已支付过！");
        }

      } else {
        String outTradeNo = params.get("out_trade_no");
        System.out.println(">>>>>>支付宝支付流水号:" + outTradeNo);
        AlipayOrderInfoPojo alipayOrderInfoPojo =
            alipayOrderInfoService.findPayInfoByTradeNo(outTradeNo);
        if (!trade_status.equals("WAIT_BUYER_PAY")) {
          if (alipayOrderInfoPojo != null
              && "WAIT_BUYER_PAY".equals(alipayOrderInfoPojo.getTradeStatus())) {
            alipayOrderInfoPojo.setBuyerEmail(params.get("buyer_email"));
            alipayOrderInfoPojo.setBuyerId(params.get("buyer_id"));
            // alipayOrderInfoPojo.setTradeStatus("TRADE_FAIL");
            alipayOrderInfoPojo.setTradeStatus(trade_status);
            alipayOrderInfoPojo.setOutTradeNo(params.get("out_trade_no"));
            alipayOrderInfoPojo.setUpdateDate(new Date());
            // 异步标志
            alipayOrderInfoPojo.setUpdateBy(998L);
            alipayOrderInfoService.updateOrder(alipayOrderInfoPojo);

            // 优惠券恢复：合单支付失败，单张订单未满使用条件，恢复优惠券为未使用
            // 多张订单支付时
            List<OrderPojo> orders = orderService.getOrderByoutTradeNo(outTradeNo);
            if (orders != null && orders.size() > 1) {
              int count = orders.size();
              String couponNo = "";
              Map<String, Object> orderMap = new HashMap<String, Object>();
              List<CouponOrderPojo> couponOrders = null;
              for (OrderPojo orderPojo : orders) {
                orderMap.put("orderId", orderPojo.getId());
                couponOrders = couponService.getcouponOrderList(orderMap);
                if (couponOrders != null && couponOrders.size() > 0
                    && 0 == couponOrders.get(0).getStatus()) {
                  couponNo = couponOrders.get(0).getCouponNo();
                  count--;
                }
              }

              if (count == 0) {
                // 所有订单都未满足使用条件
                UserCouponPojo userCouponPojo = new UserCouponPojo();
                userCouponPojo.setCouponNo(couponNo);
                userCouponPojo.setUsed(0);
                userCouponPojo.setUseTime(0L);
                userCouponPojo.setStatus(1);
                couponService.updateUserCoupon(userCouponPojo);
              }
            }

          } else {
            System.out.println("未找到支付记录或已支付过！");
          }
        }
      }


      System.out.println(">>>>>支付宝验证成功[sign success]");
      try {
        ServletActionContext.getResponse().getWriter().write("success");
      } catch (Exception e) {
        e.printStackTrace();
      }

      // 该页面可做页面美工编辑
      // System.out.println("验证成功<br />");

      // ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
      // ServletActionContext.getResponse().getWriter().write("验证成功<br />");
      // ServletActionContext.getResponse().getWriter()
      // .write(params.toString());
      // ////////////////////////////////////////////////////////////////////////////////////////
    } else {
      // 该页面可做页面美工编辑
      System.out.println(">>>>>支付宝验证失败[sign fail]");
      try {
        ServletActionContext.getResponse().getWriter().write("fail");
      } catch (Exception e) {
        e.printStackTrace();
      }
      // ServletActionContext.getResponse().getWriter().write("验证失败<br />");
      // ServletActionContext.getResponse().getWriter()
      // .write(params.toString());
    }

    return;
  }

  /**
   * 微信支付异步通知
   */
  public void getwxnotifyUrl() throws Exception {
    // 获取微信GET过来反馈信息
    InputStream reqInputStream = ServletActionContext.getRequest().getInputStream();

    int count = 0;
    byte[] buffer = new byte[1024];
    StringBuilder builder = new StringBuilder();
    try {
      while ((count = reqInputStream.read(buffer, 0, 1024)) > 0) {
        builder.append(new String(buffer, 0, count, "UTF-8"));
      }
    } finally {
      if (reqInputStream != null) {
        reqInputStream.close();
      }
    }
    String notifyString = builder.toString();
    System.out.println(">>>>>> 微信通知返回:" + notifyString);
    PayResData payres = new PayResData();

    Map<String, Object> reqMap = XMLUtil.xml2Map(notifyString);
    if (reqMap != null && "SUCCESS".equals(reqMap.get("return_code"))) {
      String outTradeNo = (String) reqMap.get("out_trade_no");
      System.out.println(">>>>>>微信支付流水号:" + outTradeNo);
      WxpayOrderInfoPojo wxpay = wxpayOrderInfoService.findPayInfoByTradeNo(outTradeNo);
      // 验证签名
      if (wxpay != null
          && com.tencent.common.Signature.checkIsSignValidFromResponseStringWithTrade(notifyString,
              wxpay.getTradeType())) {
        // Map<String, Object> reqMap = XMLParser.getMapFromXML(notifyString);
        // Map<String, Object> reqMap = XMLUtil.xml2Map(notifyString);
        if (reqMap != null && "SUCCESS".equals(reqMap.get("result_code"))) {
          // String outTradeNo = (String) reqMap.get("out_trade_no");
          // System.out.println(">>>>>>微信支付流水号:" + outTradeNo);
          // WxpayOrderInfoPojo wxpay = wxpayOrderInfoService.findPayInfoByTradeNo(outTradeNo);
          if (wxpay != null && "WAIT_BUYER_PAY".equals(wxpay.getTradeStatus())) {
            // 验证价格
            Double totalFee = Double.valueOf(reqMap.get("total_fee").toString());
            Double wxTotalFee = Double.valueOf(wxpay.getTotalFee());
            if (totalFee.doubleValue() == wxTotalFee.doubleValue()) {
              wxpay.setBankType((String) reqMap.get("bank_type"));
              wxpay.setTimeEnd((String) reqMap.get("time_end"));
              wxpay.setTradeStatus("TRADE_SUCCESS");
              wxpay.setTransactionId((String) reqMap.get("transaction_id"));
              wxpay.setUpdateBy(998L);
              wxpayOrderInfoService.updatePay(wxpay);

              // 改变支付状态
              Map<String, Object> map = new HashMap<String, Object>();
              map.put("outTradeNo", outTradeNo);
              map.put("updateDate", new Date());
              orderService.orderPaySuccess(map);

              // 付款成功更新优惠券状态
              // 多张订单支付时
              List<OrderPojo> orders = orderService.getOrderByoutTradeNo(outTradeNo);
              if (orders != null && orders.size() > 0) {
                orderPojo = orders.get(0);
                Util.log(orderPojo.getTradeNo() + ">>>>支付成功参团/开团处理");
                grouponService.groupOrderHandle(orderPojo.getActivityId(), orderPojo.getSourceId(),
                    orderPojo.getUserId(), orderPojo.getSource(), orderPojo.getId());

                try {
                  // 添加商品限购
                  OrderPojo order = orderService.findOrderByOrderNo(orderPojo.getOrderNo());
                  if (order != null && order.getSource() != null && order.getSource() == 1
                      && order.getMaxNum() != null && order.getMaxNum() > 0) {
                    Map<String, Object> params2 = new HashMap<String, Object>();
                    params2.put("userId", orderPojo.getUserId());
                    params2.put("activityId", orderPojo.getActivityId());
                    params2.put("productId", Long.valueOf(order.getProductId()));
                    List<ProductRestrictionPojo> productRestrictionPojos =
                        productRestrictionService.listPage(params2);
                    if (productRestrictionPojos != null && productRestrictionPojos.size() > 0) {
                      ProductRestrictionPojo productRestriction1 = productRestrictionPojos.get(0);
                      ProductRestrictionPojo productRestriction = new ProductRestrictionPojo();
                      productRestriction
                          .setBuyNum(productRestriction1.getBuyNum() + order.getNum());
                      productRestriction.setMaxNum(order.getMaxNum());// 为了适应限购数量有改变
                      productRestriction.setId(productRestriction1.getId());
                      int i = productRestrictionService.update(productRestriction);
                      if (i > 0) {
                        Util.log("修改商品限购成功：id-" + productRestriction1.getId());
                      } else {
                        Util.log("修改商品限购失败：id-" + productRestriction1.getId());
                      }
                    } else {
                      ProductRestrictionPojo productRestriction = new ProductRestrictionPojo();
                      productRestriction.setUserId(orderPojo.getUserId());
                      productRestriction.setActivityId(orderPojo.getActivityId());
                      productRestriction.setProductId(Long.valueOf(order.getProductId()));
                      productRestriction.setBuyNum(order.getNum());
                      productRestriction.setMaxNum(order.getMaxNum());
                      int i = productRestrictionService.add(productRestriction);
                      if (i > 0) {
                        Util.log("添加商品限购成功：userId-" + orderPojo.getUserId() + "/activityId-"
                            + orderPojo.getActivityId() + "/productId-" + order.getProductId());
                      } else {
                        Util.log("添加商品限购失败：userId-" + orderPojo.getUserId() + "/activityId-"
                            + orderPojo.getActivityId() + "/productId-" + order.getProductId());
                      }
                    }
                  } else {
                    Util.log("添加商品限购，订单不存在了：orderId-" + orderPojo.getId());
                  }
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
              if (orders != null && orders.size() > 1) {
                Map<String, Object> orderMap = new HashMap<String, Object>();
                List<CouponOrderPojo> couponOrders = null;
                CouponOrderPojo couponOrder = null;
                for (OrderPojo orderPojo : orders) {
                  orderMap.put("orderId", orderPojo.getId());
                  couponOrders = couponService.getcouponOrderList(orderMap);
                  if (couponOrders != null && couponOrders.size() > 0
                      && couponOrders.get(0).getStatus() == 0) {
                    couponOrder = new CouponOrderPojo();
                    couponOrder.setStatus(1);
                    couponOrder.setCouponNo(couponOrders.get(0).getCouponNo());
                    couponOrder.setOrderId(couponOrders.get(0).getOrderId());
                    // 更新合单支付所有订单的用券状态
                    couponService.updateCouponOrderStatus(couponOrder);
                  }
                }
              }

              // 添加销售量
              List<OrderDetailPojo> orderDetaillist =
                  orderDetailService.getOrderDetailByOutTradeNo(outTradeNo);
              if (orderDetaillist != null && orderDetaillist.size() > 0) {
                for (OrderDetailPojo p : orderDetaillist) {
                  Map<String, Object> map1 = new HashMap<String, Object>();
                  ProductPojo productPojo = new ProductPojo();
                  productPojo.setId(p.getProductId());
                  productPojo = productService.findProduct(productPojo);
                  if (productPojo != null) {
                    map1.put("id", productPojo.getId());
                    map1.put("sellNumber", productPojo.getSellNumber() + p.getNum());
                    productService.updateProductsellNumber(map1);
                    ProductSellPojo productSellPojo = new ProductSellPojo();
                    productSellPojo.setProductId(productPojo.getId());
                    productSellPojo.setSellNumber(productPojo.getSellNumber() + p.getNum());
                    productSellPojo.setUpdateDate(new Date());
                    productSellService.update(productSellPojo);
                  }
                }
              }

              // WxpayOrderInfoPojo payQry = wxpayOrderInfoService.findPayInfoByTradeNo(outTradeNo);
              /*
               * // 活动期间付款成功满60送60 Date today = new Date(); if (payQry != null &&
               * "TRADE_SUCCESS".equals(payQry.getTradeStatus()) && StringUtil.stringDate(
               * "2015-12-07 00:00:00").compareTo(today) <= 0 && StringUtil.stringDate(
               * "2015-12-14 00:00:00").compareTo(today) > 0) {
               * giveUserCouponByOrderPay(Double.valueOf(payQry.getTotalFee())/100, uid); }
               */
              // 满40元赠送
              /*
               * if (payQry != null && payQry.getTradeStatus() != null) {
               * walletService.giveUserCouponByOrderPayWithDate(payQry.getTradeStatus(), uid,
               * Double.valueOf(payQry.getTotalFee()) / 100); }
               */

              System.out.println(">>>>>微信支付成功！");
              payres.setReturn_code("SUCCESS");
              payres.setReturn_msg("OK");
            } else {
              System.out.println("支付金额与订单金额不一致!");
            }

          } else {
            System.out.println(">>>>>微信支付未找到信息或已支付过！");
            payres.setReturn_code("SUCCESS");
            payres.setReturn_msg("未找到信息或已支付过");
          }
        } else if (reqMap != null && "FAIL".equals(reqMap.get("result_code"))) {
          // String outTradeNo = (String) reqMap.get("out_trade_no");
          // System.out.println(">>>>>>微信支付流水号:" + outTradeNo);
          // WxpayOrderInfoPojo wxpay = wxpayOrderInfoService.findPayInfoByTradeNo(outTradeNo);
          if (wxpay != null && "WAIT_BUYER_PAY".equals(wxpay.getTradeStatus())) {
            wxpay.setTradeStatus("TRADE_FAIL");
            wxpay.setRemarks((String) reqMap.get("err_code") + (String) reqMap.get("err_code_des"));
            wxpay.setUpdateBy(998L);
            wxpayOrderInfoService.updatePay(wxpay);

            // 优惠券恢复：合单支付失败，单张订单未满使用条件，恢复优惠券为未使用
            // 多张订单支付时
            List<OrderPojo> orders = orderService.getOrderByoutTradeNo(outTradeNo);
            if (orders != null && orders.size() > 1) {
              int ocount = orders.size();
              String couponNo = "";
              Map<String, Object> orderMap = new HashMap<String, Object>();
              List<CouponOrderPojo> couponOrders = null;
              for (OrderPojo orderPojo : orders) {
                orderMap.put("orderId", orderPojo.getId());
                couponOrders = couponService.getcouponOrderList(orderMap);
                if (couponOrders != null && couponOrders.size() > 0
                    && 0 == couponOrders.get(0).getStatus()) {
                  couponNo = couponOrders.get(0).getCouponNo();
                  ocount--;
                }
              }

              if (ocount == 0) {
                // 所有订单都未满足使用条件
                UserCouponPojo userCouponPojo = new UserCouponPojo();
                userCouponPojo.setCouponNo(couponNo);
                userCouponPojo.setUsed(0);
                userCouponPojo.setUseTime(0L);
                userCouponPojo.setStatus(1);
                couponService.updateUserCoupon(userCouponPojo);
              }
            }

            System.out.println(">>>>>微信支付失败！");
            payres.setReturn_code("SUCCESS");
            payres.setReturn_msg("OK");
          } else {
            System.out.println(">>>>>微信支付未找到信息或已支付过！");
            payres.setReturn_code("SUCCESS");
            payres.setReturn_msg("未找到信息或已支付过");
          }
        }
      } else {
        System.out.println(">>>>>微信支付未成功，签名校验失败！");
        payres.setReturn_code("FAIL");
        payres.setReturn_msg("签名失败");
      }
    } else {
      System.out.println(">>>>>> 微信通知返回状态失败！");
      payres.setReturn_code("FAIL");
      payres.setReturn_msg("返回业务失败");
    }

    // 解决XStream对出现双下划线的bug
    XStream xStreamForRequestPostData =
        new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
    try {
      // 将要提交给API的数据对象转换成XML格式数据Post给API
      String postDataXML = xStreamForRequestPostData.toXML(payres);
      ServletActionContext.getResponse().getWriter().write(postDataXML);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 订单支付金额满xx送xx
   * 
   * @param orderPirce 支付金额
   * @throws Exception
   */
  public void giveUserCouponByOrderPay(double orderPirce, Long uid) throws Exception {
    // 满60元赠送
    if (orderPirce >= 60.0) {
      Map<String, Object> param = new HashMap<String, Object>();
      param.put("om", "99");
      param.put("m", "20");
      JSONObject json = JSONObject.fromObject(param);
      Date start = StringUtil.stringDate("2015-12-07 00:00:00");
      // 满99减20
      giveCoupon(uid, 2, 1, json.toString(), start, 7);
      // 满199减40
      param.put("om", "159");
      param.put("m", "40");
      json = JSONObject.fromObject(param);
      giveCoupon(uid, 2, 1, json.toString(), start, 7);
    }
  }

  /**
   * 赠送用户优惠券
   * 
   * @param uid 用户id
   * @param type 优惠券类型
   * @param content 优惠信息
   * @param validSdate 有效起始
   * @param validDays 有效天数
   * @throws Exception
   */
  protected void giveCoupon(Long uid, Integer source, Integer type, String content,
      Date validSdate, int validDays) throws Exception {
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("type", type);
    paramMap.put("content", content);
    paramMap.put("status", 1);
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
      String name = "";
      org.json.JSONObject json = new org.json.JSONObject(content);
      if (type == 0) {
        name = "兑换产品";
      } else if (type == 1) {
        name = "满" + json.get("om").toString() + "元减" + json.get("m").toString() + "元";
      } else if (type == 2) {
        name = "直减" + json.get("m").toString() + "元";
      }
      coupon.setName(name);
      coupon.setType(type);
      coupon.setStatus(1);
      coupon.setValidStime(stime / 1000);
      coupon.setValidEtime(etime / 1000);
      coupon.setCreateTime(gentime / 1000);
      coupon.setContent(content);
      couponService.addcoupon(coupon);
    }

    UserCouponPojo userCoupon = new UserCouponPojo();
    // 优惠券码规则：时间秒+5位数字随机
    userCoupon.setCouponNo(String.valueOf(gentime / 1000) + StringUtil.genRandomStr(5));
    userCoupon.setCouponId(coupon.getCouponId());
    userCoupon.setUserId(uid);
    userCoupon.setGenTime(gentime / 1000);
    userCoupon.setStatus(1);
    userCoupon.setUsed(0);
    userCoupon.setUseTime(0L);
    userCoupon.setValidstime(stime / 1000);
    userCoupon.setValidetime(etime / 1000);
    userCoupon.setSource(source);
    couponService.addUserCoupon(userCoupon);
  }

  /**
   * 发货审核
   * 
   * @return
   * @throws SQLException
   */
  public String checkOrderType() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("id", order.getId());
    map.put("orderTypeOld", 2);
    map.put("orderType", 0);
    try {
      orderService.updateorders(map);
      result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  /**
   * 发货批量审核
   * 
   * @return
   * @throws SQLException
   */
  public String checkOrderTypeAll() throws SQLException {
    if (tids != null && tids.length > 0) {
      Map<String, Object> map = new HashMap<String, Object>();
      boolean flag = true;
      for (String tid : tids) {
        map.put("id", Long.valueOf(tid));
        map.put("orderTypeOld", 2);
        map.put("orderType", 0);
        try {
          orderService.updateOrderType(map);
        } catch (Exception e) {
          flag = false;
          e.printStackTrace();
        }
      }
      if (!flag) {
        FileUtil.alertMessageBySkip("批量审核出错！", "order.do?os=" + os + "&testcount=" + testcount);
      } else {
        FileUtil.alertMessageBySkip("批量审核成功！", "order.do?os=" + os + "&testcount=" + testcount);
      }
    } else {
      FileUtil.alertMessageBySkip("请先勾选！", "order.do?os=" + os);
    }
    return null;
  }

  // --------------------------------------ping++start-----------------------------------------------------
  /**
   * ping++异步跳转
   * 
   * @throws Exception
   */
  // 订单id
  String orderNo;
  // 实收价格
  Double facrPrice;
  // charge总价
  double amount;

  public void getPingnotifyUrl() throws Exception {
    new DecimalFormat("#.##");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    HttpServletRequest request = ServletActionContext.getRequest();
    new HashMap<String, Object>();
    // event里面的对象
    // Map<String, Object> dataObject = new HashMap<String, Object>();
    // 获取签名
    String sign = request.getHeader("x-pingplusplus-signature");
    // System.out.println("这是sign："+sign);
    // 公钥
    String pubKey = PropertiesHelper.getValue("pingxx.pubKey");
    // String pubKey =
    // "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApLPQo9v6P/MQW9K6VDWimsn7L9Hf6X80XesVMwvFx54gPxnG5xnVVie8clQ03iTwpmxwpnYwf6DbnQ3r3HkIedEpyYD8ozlkUrvQJFFdWgOIjqE1hcWPtCUC9yhLUOmptSBYzrniERIFxdtqiGBqJkK4V2RHX/sYHInhlntTEE8uKqiflBlq2dqb/TE/qETRCBDvZvvIg2ARDcXqVurRVXIOBSzjbIsKCeq/7SxRvrOqyGI4c9FUILCucUoVERE2WtVAGabrH8RhT2MbxR2d26Ec0rjP41ebbjDMx3edhPlupKfpyvSc4Ptrn6eHPj79MIY9oCoyHi0rZE0ZGnSn/QIDAQAB";

    request.setCharacterEncoding("UTF8");
    // 获取头部所有信息
    Enumeration headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String key = (String) headerNames.nextElement();
      String value = request.getHeader(key);
      System.out.println(key + " " + value);
    }
    // 获得 http body 内容
    BufferedReader reader = request.getReader();
    StringBuffer buffer = new StringBuffer();
    String string;
    while ((string = reader.readLine()) != null) {
      buffer.append(string);
    }
    reader.close();
    Object obj = Webhooks.getObject(buffer.toString());
    Event event = Webhooks.eventParse(buffer.toString());
    try {
      if (obj instanceof Charge) {
        OrderPayinfoPojo orderPayinfoPojo = null;
        Charge charge = (Charge) obj;
        System.out.println("这是charge:" + charge);
        // webhooks发过来的
        orderNo = charge.getOrderNo();
        amount = charge.getAmount();// Double.valueOf(dataObject.get("amount").toString());
        if (charge.getChannel().equals("cmb_wallet")) {
          orderPayinfoPojo = orderPayinfoService.findOrderPayInfoByCmbTradeNo(orderNo);
        } else {
          orderPayinfoPojo = orderPayinfoService.findOrderPayInfoByOrderNo(orderNo);
        }
        if (orderPayinfoPojo != null) {
          System.out.println("orderPayinfoPojo不为空");
          // 数据库查询的charge金额
          double amount2 = Double.valueOf(orderPayinfoPojo.getAmount());
          System.out.println("webhook传过来的charge金额：" + amount);
          System.out.println("数据库charge金额：" + amount2);
          // 签名验证
          // System.out.println("这是eventStr:" + PingxxUtil.getByteFromFile(buffer.toString(),
          // false));
          // System.out.println("这是sign:" + PingxxUtil.getByteFromFile(sign, true));
          // System.out.println("这是PubKey:" + PingxxUtil.getPubKey(pubKey));
          boolean verifyResult =
              PingxxUtil.verifyData(PingxxUtil.getByteFromFile(buffer.toString(), false),
                  PingxxUtil.getByteFromFile(sign, true), PingxxUtil.getPubKey(pubKey));
          System.out.println("验签结果：" + verifyResult);
          orderPayinfoPojo.getAmount();
          // 解析异步通知数据
          if (verifyResult) {
            System.out.println("验证成功");
            if (amount == amount2) {
              System.out.println("价格验证成功");
              if ("charge.succeeded".equals(event.getType())) {
                // 修改订单支付信息
                orderPayinfoPojo.setChannel(charge.getChannel());
                orderPayinfoPojo.setTimePaid(sdf.format(charge.getTimePaid() * 1000L));
                if (charge.getChannel().equals("cmb_wallet")) {
                  orderPayinfoPojo.setCmbTradeNo(orderNo);
                } else {
                  orderPayinfoPojo.setOutTradeNo(orderNo);
                }
                orderPayinfoPojo.setAmount(charge.getAmount().toString());
                orderPayinfoPojo.setChargeId(charge.getId().toString());
                orderPayinfoPojo.setTradeStatus("TRADE_SUCCESS");
                orderPayinfoPojo.setFailureCode(charge.getFailureCode() != null ? charge
                    .getFailureCode().toString() : null);
                orderPayinfoPojo.setFailureMsg(charge.getFailureMsg() != null ? charge
                    .getFailureMsg().toString() : null);
                orderPayinfoPojo.setCreateBy(orderPayinfoPojo.getCreateBy());
                orderPayinfoPojo.setCreateDate(orderPayinfoPojo.getCreateDate());
                orderPayinfoPojo.setUpdateBy(orderPayinfoPojo.getUpdateBy());// 修改人
                orderPayinfoPojo.setUpdateDate(new Date());
                orderPayinfoPojo.setRemarks(charge.getSubject().toString());
                orderPayinfoPojo.setTransactionNo(charge.getTransactionNo().toString());
                orderPayinfoService.updateOrderPayInfo(orderPayinfoPojo);
                System.out.println("支付成功");
                ServletActionContext.getResponse().setStatus(200);
              } else {
                orderPayinfoPojo.setChannel(charge.getChannel().toString());
                orderPayinfoPojo.setTimePaid(sdf.format(charge.getTimePaid() * 1000L));
                if (charge.getChannel().equals("cmb_wallet")) {
                  orderPayinfoPojo.setCmbTradeNo(orderNo);
                } else {
                  orderPayinfoPojo.setOutTradeNo(orderNo);
                }
                orderPayinfoPojo.setAmount(charge.getAmount().toString());
                orderPayinfoPojo.setChargeId(charge.getId().toString());
                orderPayinfoPojo.setTradeStatus("TRADE_FAIL");
                orderPayinfoPojo.setFailureCode(charge.getFailureCode() != null ? charge
                    .getFailureCode().toString() : null);
                orderPayinfoPojo.setFailureMsg(charge.getFailureMsg() != null ? charge
                    .getFailureMsg().toString() : null);
                orderPayinfoPojo.setCreateBy(orderPayinfoPojo.getCreateBy());
                orderPayinfoPojo.setCreateDate(orderPayinfoPojo.getCreateDate());
                orderPayinfoPojo.setUpdateBy(orderPayinfoPojo.getUpdateBy());// 修改人
                orderPayinfoPojo.setUpdateDate(new Date());
                orderPayinfoPojo.setRemarks(charge.getSubject().toString());
                orderPayinfoPojo.setTransactionNo(charge.getTransactionNo().toString());
                orderPayinfoService.updateOrderPayInfo(orderPayinfoPojo);
                // 优惠券恢复：合单支付失败，单张订单未满使用条件，恢复优惠券为未使用
                // 多张订单支付时
                List<OrderPojo> orders =
                    orderService.getOrderByoutTradeNo(orderPayinfoPojo.getOutTradeNo());
                if (orders != null && orders.size() > 1) {
                  int count = orders.size();
                  String couponNo = "";
                  Map<String, Object> orderMap = new HashMap<String, Object>();
                  List<CouponOrderPojo> couponOrders = null;
                  for (OrderPojo orderPojo : orders) {
                    orderMap.put("orderId", orderPojo.getId());
                    couponOrders = couponService.getcouponOrderList(orderMap);
                    if (couponOrders != null && couponOrders.size() > 0
                        && 0 == couponOrders.get(0).getStatus()) {
                      couponNo = couponOrders.get(0).getCouponNo();
                      count--;
                    }
                  }

                  if (count == 0) {
                    // 所有订单都未满足使用条件
                    UserCouponPojo userCouponPojo = new UserCouponPojo();
                    userCouponPojo.setCouponNo(couponNo);
                    userCouponPojo.setUsed(0);
                    userCouponPojo.setUseTime(0L);
                    userCouponPojo.setStatus(1);
                    couponService.updateUserCoupon(userCouponPojo);
                  }
                }
                System.out.println("支付失败");
                ServletActionContext.getResponse().setStatus(500);
              }
            } else {
              System.out.println("价格验证失败");
              ServletActionContext.getResponse().setStatus(500);
            }
          } else {
            System.out.println("签名验证失败");
            ServletActionContext.getResponse().setStatus(500);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }


  }

  // ----------------------------------------ping++end---------------------------------------------------------------------


  // ----------------------------------------支付宝退款开始---------------------------------------------------------------------
  /**
   * 拼团退款处理
   */
  public void groupBatchRefundHandle() {
    if (orderId != null && orderId != 0) {
      List<Long> orderIds = new ArrayList<Long>();
      orderIds.add(orderId);
      try {
        // 生成退款所需数据
        Integer flag = aliPayBatchRefund(orderIds);
        if (flag == 1) {
          FileUtil.alertMessageBySkip("订单id不能为空!", "goRefundUserOrder.do");
        } else if (detailData.equals("") || Integer.valueOf(batchNum) <= 0) {
          FileUtil.alertMessageBySkip("查询不到已支付订单!", "goRefundUserOrder.do");
        } else {
          // 去掉最后一个#号
          detailData = detailData.substring(0, detailData.length() - 1);
          // 退款批次号
          batchNo = UtilDate.getOrderNum() + UtilDate.getThree() + StringUtil.getSerialNum();
          // 把请求参数打包成数组
          Map<String, String> sParaTemp = new HashMap<String, String>();
          sParaTemp.put("service", AlipayConfig.service);
          sParaTemp.put("partner", AlipayConfig.partner);
          sParaTemp.put("_input_charset", AlipayConfig.input_charset);
          sParaTemp.put("notify_url", AlipayConfig.batch_refund_notify_url);
          sParaTemp.put("seller_user_id", AlipayConfig.seller_id);
          sParaTemp.put("refund_date", UtilDate.getDateFormatter());
          sParaTemp.put("batch_no", batchNo);
          sParaTemp.put("batch_num", "1");
          sParaTemp.put("detail_data", detailData);
          // 建立请求
          String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
          ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
          try {
            ServletActionContext.getResponse().getWriter().write(sHtmlText);
          } catch (IOException e) {
            FileUtil.alertMessageBySkip("调用支付宝退款页面出错!", "goRefundUserOrder.do");
            e.printStackTrace();
          }
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } else {
      System.out.println("订单id不能为空!");
    }
  }

  /**
   * 拼团失败支付宝批量退款
   * 
   * @param orderIds 订单id
   * @throws SQLException
   */
  public Integer aliPayBatchRefund(List<Long> orderIds) throws SQLException {
    detailData = "";
    if (orderIds != null && orderIds.size() == 1) {
      for (Long orderId : orderIds) {
        // 查询是否存在已支付的订单
        System.out.println("查询订单!");
        orderPojo = orderService.findOrderById(orderId);
        System.out.println("判断订单是否已支付!");
        if (orderPojo != null && orderPojo.getPayStatus() == 1 && orderPojo.getOutTradeNo() != null) {
          // 查询是否存在已支付的支付宝订单记录
          System.out.println("查询是否存在已支付的支付宝订单记录!");
          AlipayOrderInfoPojo alipayOrderInfoPojo =
              alipayOrderInfoService.findPayInfoByTradeNo(orderPojo.getOutTradeNo());
          if (alipayOrderInfoPojo != null
              && (alipayOrderInfoPojo.getTradeStatus().equals("TRADE_FINISHED") || alipayOrderInfoPojo
                  .getTradeStatus().equals("TRADE_SUCCESS"))) {
            // 拼装退款详细数据
            System.out.println(alipayOrderInfoPojo.getTradeNo());
            System.out.println("拼装退款详细数据");
            if (StringUtils.isNotBlank(alipayOrderInfoPojo.getTradeNo())) {
              detailData +=
                  alipayOrderInfoPojo.getTradeNo() + "^" + orderPojo.getFactPrice() + "^拼团失败退款#";
              // batchNum = String.valueOf(Integer.valueOf(batchNum) + 1);
              System.out.println("拼装好的数据:" + detailData);
            } else {
              System.out.println("支付宝交易号为空或实付金额为空!");
            }
          } else {
            System.out.println("查询不到支付宝记录!");
          }
        } else {
          System.out.println(orderId + "查询不到已支付订单!");
        }
      }
    } else {
      System.out.println("订单id不能为空!");
      return 1;
    }
    return 0;
  }


  /**
   * 支付宝服务器异步通知
   * 
   * @throws Exception
   */
  public void aliPayBatchRefundNotify() throws Exception {
    // 获取支付宝POST过来反馈信息
    Map<String, String> params = new HashMap<String, String>();
    Map requestParams = ServletActionContext.getRequest().getParameterMap();
    for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
      String name = (String) iter.next();
      String[] values = (String[]) requestParams.get(name);
      String valueStr = "";
      for (int i = 0; i < values.length; i++) {
        valueStr = i == values.length - 1 ? valueStr + values[i] : valueStr + values[i] + ",";
      }
      // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
      // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
      params.put(name, valueStr);
    }
    // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
    // 批次号
    String batchNo =
        new String(ServletActionContext.getRequest().getParameter("batch_no")
            .getBytes("ISO-8859-1"), "UTF-8");
    // 批量退款数据中转账成功的笔数
    String successNum =
        new String(ServletActionContext.getRequest().getParameter("success_num")
            .getBytes("ISO-8859-1"), "UTF-8");
    String resultDetails =
        new String(ServletActionContext.getRequest().getParameter("result_details")
            .getBytes("ISO-8859-1"), "UTF-8");
    boolean verify_result = AlipayNotify.verify(params);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
    if (verify_result) {// 验证成功
      if (StringUtils.isNotBlank(batchNo) && StringUtils.isNotBlank(successNum)
          && StringUtils.isNotBlank(resultDetails)) {
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> orderParam = new HashMap<String, Object>();
        param.put("outRefundNo", batchNo);
        AlipayOrderInfoPojo alipayOrderInfo = alipayOrderInfoService.findOrderInfoByBatchNo(param);
        if (alipayOrderInfo == null) {
          Util.log("查询不到订单退款申请!");
        } else if ("SUCCESS".equals(alipayOrderInfo.getRefundStatus())) {
          Util.log("已退过款!");
          try {
            ServletActionContext.getResponse().getWriter().write("success");// 请不要修改或删除
          } catch (Exception e) {
            e.printStackTrace();
          }
        } else {
          // 退货详情封装成对象
          List<ResultDetailData> resultDetailDataList =
              GrouponService.refundDataHandle(resultDetails);
          if (resultDetailDataList != null && resultDetailDataList.size() > 0) {
            OrderPojo orderPojo = null;
            List<OrderPojo> orderPojos = null;
            for (ResultDetailData resultDetailData : resultDetailDataList) {
              if (resultDetailData.getTradeNo() != null
                  && resultDetailData.getTradeNo().equals(alipayOrderInfo.getTradeNo())) {
                orderPojos = orderService.getOrderByoutTradeNo(alipayOrderInfo.getOutTradeNo());
                if (orderPojos != null && orderPojos.size() > 0) {
                  orderPojo = orderPojos.get(0);
                }
                if (orderPojo != null && orderPojo.getIsRefund() != 2) {
                  if ("SUCCESS".equals(resultDetailData.getResult())) {
                    if (alipayOrderInfo.getSource() == 1) {
                      // 售后退款
                      UserOrderRefundPojo userOrderRefund =
                          userOrderRefundService.getByOutRefundNo(batchNo);
                      if (userOrderRefund != null) {
                        UserOrderRefundPojo userOrderRefundPojo = new UserOrderRefundPojo();
                        userOrderRefundPojo.setOldRefund(2);
                        userOrderRefundPojo.setIsRefund(2);
                        // userOrderRefundPojo.setOutRefundNo(outRefundNo);
                        userOrderRefundPojo.setRefundDate(new Date());

                        userOrderRefundPojo.setStatus(7);
                        userOrderRefundPojo.setId(userOrderRefund.getId());
                        userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("id", userOrderRefund.getDetailId());
                        map.put("reStatus", 7);
                        map.put("status", "0");
                        orderDetailService.updateReStatusmap(map);

                        // 修改支付订单退款状态
                        orderParam.clear();
                        orderParam.put("refundFee", resultDetailData.getRefundPrice());
                        orderParam.put("refundStatus", resultDetailData.getResult());
                        orderParam.put("tradeNo", resultDetailData.getTradeNo());
                        alipayOrderInfoService.updatePayRefund(orderParam);
                      }
                    } else {
                      // 修改订单的退款状态
                      orderParam.clear();
                      orderParam.put("isRefund", 2);
                      orderParam.put("oldRefund", 2);
                      orderParam.put("refundDate", new Date());
                      orderParam.put("orderNo", orderPojo.getOrderNo());
                      int cnt = orderService.updateOrderRefund(orderParam);
                      if (cnt > 0) {
                        // 修改支付订单退款状态
                        orderParam.clear();
                        orderParam.put("refundFee", resultDetailData.getRefundPrice());
                        orderParam.put("refundStatus", resultDetailData.getResult());
                        orderParam.put("tradeNo", resultDetailData.getTradeNo());
                        alipayOrderInfoService.updatePayRefund(orderParam);

                        try {
                          grouponService.addUserOrderNotice(7, orderPojo.getUserId(),
                              orderPojo.getId());
                        } catch (Exception e) {
                          e.printStackTrace();
                        }
                      }
                    }
                  } else {
                    // 退款失败
                    if (alipayOrderInfo != null && alipayOrderInfo.getSource() == 1) {
                      // 售后退款失败
                      UserOrderRefundPojo userOrderRefund =
                          userOrderRefundService.getByOutRefundNo(batchNo);
                      if (userOrderRefund != null) {
                        UserOrderRefundPojo userOrderRefundPojo = new UserOrderRefundPojo();
                        userOrderRefundPojo.setOldRefund(2);
                        userOrderRefundPojo.setIsRefund(3);
                        // userOrderRefundPojo.setOutRefundNo(outRefundNo);
                        userOrderRefundPojo.setRefundDate(new Date());

                        // userOrderRefundPojo.setStatus(7);
                        userOrderRefundPojo.setId(userOrderRefund.getId());
                        userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
                        // Map<String, Object> map = new HashMap<String, Object>();
                        // map.put("id", userOrderRefund.getDetailId());
                        // map.put("reStatus", 7);
                        // map.put("status", "0");
                        // orderDetailService.updateReStatusmap(map);
                        // 修改支付订单退款状态
                        orderParam.clear();
                        orderParam.put("refundFee", resultDetailData.getRefundPrice());
                        orderParam.put("refundStatus", resultDetailData.getResult());
                        orderParam.put("refundReason", "拼团失败退款!");
                        orderParam.put("tradeNo", resultDetailData.getTradeNo());
                        alipayOrderInfoService.updatePayRefund(orderParam);
                      }
                    } else {
                      // 修改订单的退款状态
                      orderParam.clear();
                      orderParam.put("isRefund", 3);
                      orderParam.put("oldRefund", 2);
                      orderParam.put("refundDate", new Date());
                      orderParam.put("orderNo", orderPojo.getOrderNo());
                      int cnt = orderService.updateOrderRefund(orderParam);
                      if (cnt > 0) {
                        // 修改支付订单退款状态
                        orderParam.clear();
                        orderParam.put("refundFee", resultDetailData.getRefundPrice());
                        orderParam.put("refundStatus", resultDetailData.getResult());
                        orderParam.put("refundReason", "拼团失败退款!");
                        orderParam.put("tradeNo", resultDetailData.getTradeNo());
                        alipayOrderInfoService.updatePayRefund(orderParam);
                      }
                    }
                  }
                } else {
                  System.out.println("查询不到订单或订单退款已处理!");
                }
              } else {
                System.out.println("获取不到异步发送的交易号!");
              }
            }
            try {
              ServletActionContext.getResponse().getWriter().write("success");// 请不要修改或删除
            } catch (Exception e) {
              e.printStackTrace();
            }
          } else {
            System.out.println("退款详情封装后得不到数据!");
          }
        }
      } else {
        System.out.println("获取不到异步通知信息数据!");
      }
    } else {
      // 验证失败
      System.out.print("支付宝异步验证失败!");
    }
  }

  /**
   * @throws SQLException
   * @方法名称:alipayRefundRequest
   * @内容摘要: ＜支付宝单笔退款请求＞
   * @param orderId 订单id
   * @return String
   * @exception
   */
  public String alipaySingleRefundRequest(Long orderId) throws AlipayApiException, SQLException {
    if (orderId != null) {
      // 查询是否存在已支付的订单
      orderPojo = orderService.getfindByIdOrder(orderId);
      if (orderPojo != null && orderPojo.getPayStatus() == 1 && orderPojo.getOutTradeNo() != null) {
        // 查询是否存在已支付的支付宝订单记录
        AlipayOrderInfoPojo alipayOrderInfoPojo =
            alipayOrderInfoService.findPayInfoByTradeNo(orderPojo.getOutTradeNo());
        if (alipayOrderInfoPojo != null
            && (alipayOrderInfoPojo.getTradeStatus().equals("TRADE_FINISHED") || alipayOrderInfoPojo
                .getTradeStatus().equals("TRADE_SUCCESS"))) {
          // 查询是否存在交易号和交易金额
          if (alipayOrderInfoPojo.getTradeNo() != null && orderPojo.getFactPrice() != null) {
            try {
              // 发起退款请求
              AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
              AlipayRefundInfo alidata = new AlipayRefundInfo();
              alidata.setOutTradeNo(orderPojo.getOutTradeNo());
              alidata.setRefundAmount(Double.valueOf(alipayOrderInfoPojo.getTotalFee()));
              alidata.setTradeNo(alipayOrderInfoPojo.getTradeNo());
              alidata.setRefundReason("拼团失败退款!");
              JSONObject alidataJson = JSONObject.fromObject(alidata);
              System.out.println("请求退款数据:" + alidataJson.toString());
              request.setBizContent(alidataJson.toString());
              AlipayClient alipayClient =
                  new DefaultAlipayClient(AlipayConfig.refund_api, AlipayConfig.seller_id,
                      AlipayConfig.private_key, "json", AlipayConfig.input_charset,
                      AlipayConfig.ali_public_key);
              AlipayTradeRefundResponse response = alipayClient.execute(request);
              result = response.getCode();
              if ("10000".equals(response.getCode())) {
                // 修改订单退款信息
                String outRefundNo = UtilDate.getOrderNum() + UtilDate.getThree();
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("id", orderId);
                params.put("orderNo", response.getTradeNo());
                params.put("outTradeNo", response.getOutTradeNo());
                params.put("outRefundNo", outRefundNo);
                int i = orderService.updateOrderRefund(params);
                if (i <= 0) {
                  // 修改订单失败
                  result = "5";
                } else {
                  // 退款成功
                  result = "1";
                }
              } else {
                // 异常
                result = response.getSubMsg();
              }
            } catch (Exception e) {
              Logger.getLogger(getClass()).error("alipayRefundRequest", e);
            }
          } else {
            result = "4";
            System.out.println("支付宝交易号为空或实付金额为空!");
          }
        } else {
          result = "3";
          System.out.println("查询不到支付宝记录!");
        }
      } else {
        result = "2";
        System.out.println("查询不到已支付订单!");
      }
    } else {
      result = "6";
      System.out.println("订单id不能为空!");
    }
    return result;
  }

  /**
   * 支付宝退款
   * 
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void aliGroupRefundHandle() {
    SysLoginPojo user = UserUtil.getUser();
    if (user == null) {
      FileUtil.alertMessageBySkip("请先登录!", "loginin.do");
    } else {
      if (orderId != null && orderId != 0 && payType != null && payType != 0) {
        try {
          if (payType == 1) {
            List<Long> orderIds = new ArrayList<Long>();
            orderIds.add(orderId);
            try {
              // 生成退款所需数据
              Integer flag = aliPayBatchRefund(orderIds);
              System.out.println(detailData);
              if (flag == 1) {
                FileUtil.alertMessageBySkip("订单id不能为空!", "goRefundUserOrder.do");
              } else if (StringUtils.isBlank(detailData)) {
                FileUtil.alertMessageBySkip("退款详情不能为空!", "goRefundUserOrder.do");
              } else {
                // 退款详情
                detailData = detailData.substring(0, detailData.length() - 1);
                // 退款批号
                batchNo = UtilDate.getDate() + UtilDate.getThree() + StringUtil.getSerialNum();
                // 修改订单退款状态
                orderPojo = new OrderPojo();
                orderPojo.setId(orderId);
                orderPojo.setIsRefund(1);
                orderPojo.setOutRefundNo(batchNo);
                // orderPojo.setRefundDate(new Date());
                orderService.updateOrder(orderPojo);
                // 修改支付订单退款状态
                Map<String, Object> params = new HashMap<String, Object>();
                orderPojo = orderService.findOrderById(orderId);
                if (orderPojo == null) {
                  FileUtil.alertMessageBySkip("查询不到订单!", "goRefundUserOrder.do");
                } else {
                  params.put("outTradeNo", orderPojo.getOutTradeNo());
                  params.put("outRefundNo", batchNo);
                  params.put("refundReason", "拼团失败退款");
                  params.put("updateBy", user.getId());
                  params.put("refundFee", orderPojo.getFactPrice());
                  params.put("refundStatus", "PROCESSING");
                  alipayOrderInfoService.updatePayRefund(params);
                }
                // 把请求参数打包成数组
                Map<String, String> sParaTemp = new HashMap<String, String>();
                sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
                sParaTemp.put("partner", AlipayConfig.partner);
                sParaTemp.put("_input_charset", AlipayConfig.input_charset);
                sParaTemp.put("notify_url", AlipayConfig.batch_refund_notify_url);
                sParaTemp.put("seller_email", AlipayConfig.seller_email);
                sParaTemp.put("refund_date", UtilDate.getDateFormatter());
                sParaTemp.put("batch_no", batchNo);
                sParaTemp.put("batch_num", "1");
                sParaTemp.put("detail_data", detailData);
                // 建立请求
                String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
                System.out.println(sHtmlText);
                ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
                try {
                  ServletActionContext.getResponse().getWriter().write(sHtmlText);
                } catch (IOException e) {
                  FileUtil.alertMessageBySkip("调用支付宝退款页面出错!", "goRefundUserOrder.do");
                  e.printStackTrace();
                }
              }
            } catch (SQLException e) {
              FileUtil.alertMessageBySkip("出现异常!", "goRefundUserOrder.do");
              e.printStackTrace();
            }
          } else {
            FileUtil.alertMessageBySkip("支付类型不能错误!", "goRefundUserOrder.do");
          }
        } catch (Exception e) {
          FileUtil.alertMessageBySkip("退款出现异常!", "goRefundUserOrder.do");
          e.printStackTrace();
        }
      } else {
        FileUtil.alertMessageBySkip("订单id和支付方式不能为空!", "goRefundUserOrder.do");
      }
    }
  }

  // ----------------------------------------支付宝退款结束---------------------------------------------------------------------

  // -----------------------------------------微信退款开始-----------------------------------------------------------------------
  /**
   * @throws SQLException
   * 
   * @方法名称:wxPayRefundRequest
   * @内容摘要: ＜退款＞
   * @param orderId 订单id
   * @return String
   * @exception
   */
  public String wxPayRefundRequest() throws SQLException {
    SysLoginPojo user = UserUtil.getUser();
    if (user != null) {
      String opUserID = "";
      Long operator = user.getId();
      if (orderId != null && orderId != 0) {
        // 查询是否存在已支付的订单
        orderPojo = orderService.getfindByIdOrder(orderId);
        if (orderPojo != null && orderPojo.getPayStatus() == 1 && orderPojo.getOutTradeNo() != null) {
          WxpayOrderInfoPojo wxpay =
              wxpayOrderInfoService.findPayInfoByTradeNo(orderPojo.getOutTradeNo());
          if (wxpay != null && "TRADE_SUCCESS".equals(wxpay.getTradeStatus())) {
            if (wxpay.getTotalFee() != null && wxpay.getTransactionId() != null) {
              // 退款单号
              String outRefundNo = "";
              if (StringUtils.isNotEmpty(wxpay.getOutRefundNo())
                  && !"SUCCESS".equals(wxpay.getRefundStatus())) {
                // 重用失败流水号
                outRefundNo = wxpay.getOutRefundNo();
              } else {
                // 新生成
                outRefundNo = UtilDate.getOrderNum() + RandomUtils.runVerifyCode(3);
              }
              // 交易号
              String transactionId = wxpay.getTransactionId();
              // 微信订单号
              String outTradeNo = wxpay.getOutTradeNo();
              // 订单总额
              int totalFee = Integer.valueOf(wxpay.getTotalFee());
              // 退款金额(实付金额)
              int refundFee = Integer.valueOf(wxpay.getTotalFee());
              RefundReqData wxdata = null;
              RefundResData refundResData = null;
              if (Configure.JSAPI.equals(wxpay.getTradeType())) {
                opUserID = Configure.getJsmchID();
              } else {
                opUserID = Configure.getMchid();
              }
              try {
                wxdata =
                    new RefundReqData(transactionId, outTradeNo, "WEB", outRefundNo, totalFee,
                        refundFee, opUserID, "", wxpay.getTradeType());
                long costTimeStart = System.currentTimeMillis();
                String rsp = WXPay.requestRefundService(wxdata);

                long costTimeEnd = System.currentTimeMillis();
                long totalTimeCost = costTimeEnd - costTimeStart;
                System.out.println(rsp);
                System.out.println("api请求总耗时：" + totalTimeCost + "ms");
                // 将从API返回的XML数据映射到Java对象
                refundResData = (RefundResData) Util.getObjectFromXML(rsp, RefundResData.class);
                if (refundResData != null) {
                  String returnCode = refundResData.getReturn_code();
                  String resultCode = refundResData.getResult_code();
                  if ("SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode)) {
                    sellerService.refundApplyForWx("SUCCESS", transactionId, outTradeNo,
                        outRefundNo, String.valueOf(refundFee), "拼团失败退款", null,
                        refundResData.getRefund_id(), operator);
                    FileUtil.alertMessageBySkip("申请退款成功!", "goRefundUserOrder.do");
                  } else {
                    String errMsg = "";
                    if ("FAIL".equals(returnCode)) {
                      errMsg = refundResData.getReturn_msg();
                      FileUtil.alertMessageBySkip("申请退款失败!" + errMsg, "goRefundUserOrder.do");
                    } else if ("FAIL".equals(resultCode)) {
                      errMsg = refundResData.getErr_code() + ":" + refundResData.getErr_code_des();
                      FileUtil.alertMessageBySkip("申请退款失败!" + errMsg, "goRefundUserOrder.do");
                    }
                    sellerService.refundApplyForWx("FAIL", transactionId, outTradeNo, outRefundNo,
                        String.valueOf(refundFee), "拼团失败退款", errMsg, null, operator);
                  }
                }

              } catch (Exception e) {
                e.printStackTrace();
                FileUtil.alertMessageBySkip("申请退款出现异常!", "goRefundUserOrder.do");
              }
            } else {
              FileUtil.alertMessageBySkip("微信交易号为空或实付金额为空!", "goRefundUserOrder.do");
            }
          } else {
            FileUtil.alertMessageBySkip("查询不到微信交易记录!", "goRefundUserOrder.do");
          }
        } else {
          FileUtil.alertMessageBySkip("查询不到已支付订单!", "goRefundUserOrder.do");
        }
      } else {
        FileUtil.alertMessageBySkip("订单id不能为空!", "goRefundUserOrder.do");
      }
    } else {
      FileUtil.alertMessageBySkip("请先登录!", "goRefundUserOrder.do");
    }
    return null;
  }

  // -----------------------------------------微信退款结束-----------------------------------------------------------------------


  // -------------------------拼团失败退款开始------------------
  /**
   * 退款页面查询全部条数
   */
  public String userOrderRefundRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("isSuccess", 2);
    map.put("payStatus", 1);
    map.put("isNotZero", 1);
    if (payType != null && payType != 0) {
      map.put("payMethod", payType);
    }
    if (orderNoStr != null && StringUtils.isNotBlank(orderNoStr)) {
      map.put("orderNo", orderNoStr);
    }
    if (isRefund != null) {
      map.put("isRefund", isRefund);
    }
    if (order != null) {
      map.put("loginname", order.getLoginname());
      map.put("source", order.getSource());
    }
    try {
      int i = orderService.groupRefundCountBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 退款页面查询全部记录
   */
  public String userOrderRefundList() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (payType != null && payType != 0) {
      map.put("payMethod", payType);
    }
    if (isRefund != null) {
      map.put("isRefund", isRefund);
    }
    if (order != null) {
      map.put("loginname", order.getLoginname());
      map.put("source", order.getSource());
    }
    map.put("isSuccess", 2);
    map.put("payStatus", 1);
    map.put("isNotZero", 1);
    if (StringUtils.isNotBlank(orderNoStr)) {
      map.put("orderNo", orderNoStr);
    }
    map.put("orderBy", "t.is_refund asc,t.id asc");
    List<OrderPojo> userOrderList = null;
    try {
      userOrderList = orderService.groupRefundListPage(map);
      JSONArray json = JSONArray.fromObject(userOrderList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  // -------------------------拼团失败退款结束------------------

  // -----------------------------------------批量退款开始-----------------------------------------------------------------------
  /**
   * 支付宝微信批量退款
   * 
   * @return
   * @throws Exception
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String aliWxRefund() throws Exception {
    String errMsg = "";
    SysLoginPojo user = UserUtil.getUser();
    Map<String, Object> map = new HashMap<String, Object>();
    result = "0";
    if (user != null) {
      user.getId();
      if (tids != null && tids.length > 0) {
        OrderPojo orderPojo = null;
        for (String tid : tids) {
          try {
            // 根据ID查找订单是否存在
            orderPojo = orderService.getfindByIdOrder(Long.valueOf(tid));
          } catch (Exception e) {
            e.printStackTrace();
          }
          if (orderPojo != null) {
            if (orderPojo.getPayMethod() != null && orderPojo.getPayMethod() == 1
                && orderPojo.getIsRefund() != null && orderPojo.getIsRefund() == 0) {
              Util.log("拼团失败-支付宝退款");
              String batchNo = "";// 退款单号
              String detailData = "";// 退款详情
              Integer flag = 0;
              Util.log("查询是否存在已支付的支付宝订单记录!");
              // 交易号
              String tradeNo = "";
              // 支付宝订单号
              String outTradeNo = "";
              // 退款金额(实付金额)
              String refundFee = "0";
              // 操作者
              Long operator = user.getId();
              AlipayOrderInfoPojo alipayOrderInfoPojo =
                  alipayOrderInfoService.findPayInfoByTradeNo(orderPojo.getOutTradeNo());
              if (alipayOrderInfoPojo != null
                  && ("TRADE_FINISHED".equals(alipayOrderInfoPojo.getTradeStatus()) || "TRADE_SUCCESS"
                      .equals(alipayOrderInfoPojo.getTradeStatus()))) {
                Util.log(alipayOrderInfoPojo.getTradeNo());
                Util.log("拼装退款详细数据");
                if (StringUtils.isNotBlank(alipayOrderInfoPojo.getTradeNo())) {
                  detailData +=
                      alipayOrderInfoPojo.getTradeNo() + "^" + alipayOrderInfoPojo.getTotalFee()
                          + "^拼团失败退款";
                  Util.log("拼装好的数据:" + detailData);
                  flag = 1;
                } else {
                  Util.log("支付宝交易号为空或实付金额为空!");
                }
                // 交易号
                tradeNo = alipayOrderInfoPojo.getTradeNo();
                // 微信订单号
                outTradeNo = alipayOrderInfoPojo.getOutTradeNo();
                // 退款金额(实付金额)
                refundFee = alipayOrderInfoPojo.getTotalFee();
              } else {
                Util.log("查询不到已支付支付宝记录!");
              }
              if (StringUtils.isNotEmpty(alipayOrderInfoPojo.getOutRefundNo())
                  && !"SUCCESS".equals(alipayOrderInfoPojo.getRefundStatus())) {
                // 重用失败流水号
                batchNo = alipayOrderInfoPojo.getOutRefundNo();
              } else {
                // 新生成
                batchNo = UtilDate.getDate() + UtilDate.getThree() + StringUtil.getSerialNum();
              }
              try {
                // 生成退款所需数据
                if (flag != 1 || StringUtils.isBlank(detailData) || StringUtils.isBlank(batchNo)) {
                  errMsg += orderPojo.getOrderNo() + "-支付宝退款参数有误!\n";
                } else {
                  // 退款批号
                  // batchNo = UtilDate.getDate() + UtilDate.getThree() + StringUtil.getSerialNum();
                  // 把请求参数打包成数组
                  Map<String, String> sParaTemp = new HashMap<String, String>();
                  sParaTemp.put("service", "refund_fastpay_by_platform_nopwd");
                  sParaTemp.put("partner", AlipayConfig.partner);
                  sParaTemp.put("_input_charset", AlipayConfig.input_charset);
                  sParaTemp.put("notify_url", AlipayConfig.batch_refund_notify_url);
                  sParaTemp.put("seller_email", AlipayConfig.seller_email);
                  sParaTemp.put("refund_date", UtilDate.getDateFormatter());
                  sParaTemp.put("batch_no", batchNo);
                  sParaTemp.put("batch_num", "1");
                  sParaTemp.put("detail_data", detailData);
                  // 建立请求
                  String sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp);
                  Util.log(sHtmlText);
                  Map<String, Object> reqMap = XMLUtil.xml2Map(sHtmlText);
                  Util.log("支付宝无密退款返回：" + reqMap.get("is_success"));
                  if ("T".equals(reqMap.get("is_success"))) {
                    errMsg += orderPojo.getOrderNo() + "-申请退款成功\n";
                    sellerService.refundApplyForAlipay("SUCCESS", tradeNo, outTradeNo, batchNo,
                        refundFee, "拼团失败退款", null, operator);
                  } else if ("F".equals(reqMap.get("is_success"))) {
                    errMsg += orderPojo.getOrderNo() + "-申请退款失败\n";
                  } else if ("P".equals(reqMap.get("is_success"))) {
                    errMsg += orderPojo.getOrderNo() + "-退款处理中\n";
                  }
                }
              } catch (Exception e) {
                e.printStackTrace();
                Util.log(orderPojo.getOrderNo() + "支付宝发起退款请求出现异常!");
                errMsg += "\n" + orderPojo.getOrderNo();
              }
            } else if (orderPojo.getPayMethod() != null
                && (orderPojo.getPayMethod() == 2 || orderPojo.getPayMethod() == 8)) {
              Util.log("拼团失败-微信退款");
              String opUserID = "";
              Long operator = user.getId();
              WxpayOrderInfoPojo wxpay =
                  wxpayOrderInfoService.findPayInfoByTradeNo(orderPojo.getOutTradeNo());
              if (wxpay != null && "TRADE_SUCCESS".equals(wxpay.getTradeStatus())) {
                if (wxpay.getTotalFee() != null && wxpay.getTransactionId() != null) {
                  // 退款单号
                  String outRefundNo = "";
                  if (StringUtils.isNotEmpty(wxpay.getOutRefundNo())
                      && !"SUCCESS".equals(wxpay.getRefundStatus())) {
                    // 重用失败流水号
                    outRefundNo = wxpay.getOutRefundNo();
                  } else {
                    // 新生成
                    outRefundNo = UtilDate.getOrderNum() + UtilDate.getThree();
                  }
                  // 交易号
                  String transactionId = wxpay.getTransactionId();
                  // 微信订单号
                  String outTradeNo = wxpay.getOutTradeNo();
                  // 订单总额
                  int totalFee = Integer.valueOf(wxpay.getTotalFee());
                  // 退款金额(实付金额)
                  int refundFee = Integer.valueOf(wxpay.getTotalFee());
                  RefundReqData wxdata = null;
                  RefundResData refundResData = null;
                  if (Configure.JSAPI.equals(wxpay.getTradeType())) {
                    opUserID = Configure.getJsmchID();
                  } else {
                    opUserID = Configure.getMchid();
                  }
                  try {
                    wxdata =
                        new RefundReqData(transactionId, outTradeNo, "WEB", outRefundNo, totalFee,
                            refundFee, opUserID, "", wxpay.getTradeType());
                    long costTimeStart = System.currentTimeMillis();
                    String rsp = WXPay.requestRefundService(wxdata);

                    long costTimeEnd = System.currentTimeMillis();
                    long totalTimeCost = costTimeEnd - costTimeStart;
                    System.out.println(rsp);
                    System.out.println("api请求总耗时：" + totalTimeCost + "ms");
                    // 将从API返回的XML数据映射到Java对象
                    refundResData = (RefundResData) Util.getObjectFromXML(rsp, RefundResData.class);
                    if (refundResData != null) {
                      String returnCode = refundResData.getReturn_code();
                      String resultCode = refundResData.getResult_code();
                      if ("SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode)) {
                        sellerService.refundApplyForWx("SUCCESS", transactionId, outTradeNo,
                            outRefundNo, String.valueOf(refundFee), "拼团失败退款", null,
                            refundResData.getRefund_id(), operator);
                      } else {
                        if ("FAIL".equals(returnCode)) {
                          errMsg = refundResData.getReturn_msg();
                        } else if ("FAIL".equals(resultCode)) {
                          errMsg =
                              refundResData.getErr_code() + ":" + refundResData.getErr_code_des();
                        }
                        sellerService.refundApplyForWx("FAIL", transactionId, outTradeNo,
                            outRefundNo, String.valueOf(refundFee), "拼团失败退款", errMsg, null,
                            operator);
                      }
                    }

                  } catch (Exception e) {
                    e.printStackTrace();
                    errMsg += orderPojo.getOrderNo() + "-微信发起退款请求出现异常!\n";
                  }
                } else {
                  errMsg += orderPojo.getOrderNo() + "-微信交易号为空或实付金额为空!\n";
                }
              } else {
                errMsg += orderPojo.getOrderNo() + "-查询不到微信交易记录!\n";
              }
            } else if (orderPojo.getPayMethod() != null && orderPojo.getPayMethod() == 4
                && orderPojo.getIsRefund() != null && orderPojo.getIsRefund() == 0) {
              Util.log("拼团失败-钱包退款");
              SysLoginPojo sysLogin = sysLoginService.findSysLoginById(orderPojo.getUserId());
              if (sysLogin != null) {
                try {
                  // 更新用户的钱包
                  boolean b =
                      walletService.addWalletBalance1(orderPojo.getWalletPrice(),
                          orderPojo.getUserId());
                  if (b) {
                    // 插入用户钱包记录
                    UserWalletLogPojo userWalletLogPojo = new UserWalletLogPojo();
                    userWalletLogPojo.setUserId(orderPojo.getUserId());
                    userWalletLogPojo.setCurBal(sysLogin.getTotalBalance());
                    userWalletLogPojo.setType(0l);
                    userWalletLogPojo.setTradeAmt(orderPojo.getWalletPrice());
                    userWalletLogPojo.setSource((long) orderPojo.getSourceId());
                    userWalletLogPojo.setOrderId(orderPojo.getId());
                    userWalletLogPojo.setCreateBy(user.getId());
                    userWalletLogPojo.setOutTradeNo(orderPojo.getOutTradeNo());
                    userWalletLogPojo.setRemarks("拼团失败-钱包退款");
                    userWalletLogService.insertUserWalletLog(userWalletLogPojo);

                    // 修改订单的退款状态
                    Map<String, Object> orderParam = new HashMap<String, Object>();
                    orderParam.clear();
                    orderParam.put("isRefund", 2);
                    orderParam.put("oldRefund", 2);
                    orderParam.put("refundDate", new Date());
                    orderParam.put("orderNo", orderPojo.getOrderNo());
                    int cnt = orderService.updateOrderRefund(orderParam);
                    if (cnt > 0) {
                      try {
                        grouponService.addUserOrderNotice(7, orderPojo.getUserId(),
                            orderPojo.getId());
                      } catch (Exception e) {
                        e.printStackTrace();
                      }
                    }
                  } else {
                    errMsg += orderPojo.getOrderNo() + "拼团失败-钱包退款失败\n";
                  }
                } catch (Exception e) {
                  e.printStackTrace();
                  errMsg += orderPojo.getOrderNo() + "拼团失败-钱包退款失败\n";
                }
              } else {
                errMsg += orderPojo.getOrderNo() + "该用户不存在\n";
              }
            } else {
              errMsg += orderPojo.getOrderNo() + "支付参数有误\n";
            }
          } else {
            errMsg += "查询不到订单!" + tid + "\n";
          }
        }// for循环结束
        if (!errMsg.equals("")) {
          result = errMsg;
        }
      } else {
        result = "3";
      }
    } else {
      result = "4";
    }
    map.put("result", result);
    if (page != null) {
      map.put("pageNo", page.getPageNo());
    }
    JSONObject json = JSONObject.fromObject(map);
    result = json.toString();
    return SUCCESS;
  }


  // -----------------------------------------批量退款结束-----------------------------------------------------------------------

  /**
   * 自动退款条数
   */
  public String countDownRefundRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("isSuccess", 2);
    map.put("payStatus", 1);
    map.put("isNotZero", 1);
    map.put("pageNo", 0);
    map.put("pageSize", 10);
    if (payType != null && payType != 0) {
      map.put("payMethod", payType);
    }
    if (orderNoStr != null && StringUtils.isNotBlank(orderNoStr)) {
      map.put("orderNo", orderNoStr);
    }
    if (isRefund != null) {
      map.put("isRefund", isRefund);
    }
    if (order != null) {
      map.put("loginname", order.getLoginname());
    }
    try {
      int i = orderService.groupRefundCountBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 自动退款列表
   */
  public String countDownRefundList() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    map.put("pageNo", 0);
    map.put("pageSize", 10);
    if (payType != null && payType != 0) {
      map.put("payMethod", payType);
    }
    if (isRefund != null) {
      map.put("isRefund", isRefund);
    }
    if (order != null) {
      map.put("loginname", order.getLoginname());
    }
    map.put("isSuccess", 2);
    map.put("payStatus", 1);
    map.put("isNotZero", 1);
    if (StringUtils.isNotBlank(orderNoStr)) {
      map.put("orderNo", orderNoStr);
    }
    map.put("orderBy", "t.is_refund asc,t.id asc");
    List<OrderPojo> userOrderList = null;
    try {
      userOrderList = orderService.groupRefundListPage(map);
      JSONArray json = JSONArray.fromObject(userOrderList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  public String goQueryOrder() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    if (b != null) {
      page.setRowCount(0);
    } else {
      ActionContext ac = ActionContext.getContext();
      ac.put("orderStatus", sysDictService.getSysDictListByType("order_status"));
      ac.put("payStatus", sysDictService.getSysDictListByType("pay_status"));
      ac.put("refundStatus", sysDictService.getSysDictListByType("refund_status"));
      ac.put("consigneeType", sysDictService.getSysDictListByType("consignee_type"));
      ac.put("payMethod", sysDictService.getSysDictListByType("pay_method_type"));
      ac.put("pageNoVal", pageNoVal);
      Map<String, Object> map = new HashMap<String, Object>();
      if (order != null) {
        map.put("orderNo", order.getOrderNo());
        map.put("orderStatus", order.getOrderStatus());
        map.put("consignee", order.getConsignee());
        map.put("consigneePhone", order.getConsigneePhone());
        map.put("consigneeAddress", order.getConsigneeAddress());
        map.put("payStatus", order.getPayStatus());
        map.put("overdue", order.getOverdue());
        map.put("createDate", order.getCreateDateString());
        map.put("beganday", order.getBeganday());
        map.put("endday", order.getEndday());
        map.put("beganday1", order.getBeganday1());
        map.put("endday1", order.getEndday1());
        map.put("sendDate", order.getSendTimes());
        map.put("tids", order.getTids());
        map.put("userId", order.getUserId());
        map.put("userName", order.getUserName());
        map.put("pushName", order.getPushName());
        map.put("refundStatus", order.getRefundStatus());
        map.put("consigneeType", order.getConsigneeType());
        map.put("payMethod", order.getPayMethod());
        map.put("logisticsNo", order.getLogisticsNo());
        map.put("logisticsName", order.getLogisticsName());
        map.put("shopName", order.getShopName());
        map.put("productId", order.getProductId());
        map.put("beganSendDate", order.getBeganSendDate());
        map.put("endSendDate", order.getEndSendDate());
        map.put("source", order.getSource());
        map.put("loginname", order.getLoginname());
        map.put("attendId", order.getAttendId());
        // map.put("isRefund", order.getIsRefund());
        map.put("refundStatus", order.getRefundStatus());
        map.put("groupBeginDateStr", order.getGroupBeginDateStr());
        map.put("groupEndDateStr", order.getGroupEndDateStr());
        map.put("notShip", order.getNotShip());
        map.put("pdkLoginname", order.getPdkLoginname());
      }
      /*
       * if (agency!= null) { map.put("agencyId",agency.getAgencyId()); }
       */
      if (order != null) {
        map.put("channel", order.getChannel());
      }
      // 过滤记事本中的userId
      /*
       * String filePath=""; String fileName="userId.txt"; List<Integer> a=new ArrayList<>();
       * filePath=ServletActionContext.getServletContext().getRealPath("/temp"
       * )+File.separator+fileName; File file = new File(filePath); if(file.isFile() &&
       * file.exists()){ //判断文件是否存在 InputStreamReader read = new InputStreamReader( new
       * FileInputStream(file),"utf-8");//编码格式 BufferedReader bufferedReader = new
       * BufferedReader(read); String lineTxt = null; while((lineTxt = bufferedReader.readLine()) !=
       * null){ a.add(Integer.parseInt(lineTxt)); } read.close(); }
       */
      testUsers = SellerService.getTestUsers();
      if (testUsers != null && testUsers.size() > 0) {
        if (testcount != null && !testcount.equals("")) {
          map.put("userIds", testUsers);
        } else {
          map.put("notuserIds", testUsers);
        }
      }
      if (page != null) {
        map.put("pageSize", page.getPageSize());
        map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      }
      // page.setRowCount(orderService.orderAllCount(order, os));
      if (a == null) {
        if (orderType == 1) {
          map.put("orderType", 1);
        } else {
          map.put("orderTypes", 1);
        }
      } else {
        map.put("a", 1);
      }
      page.setRowCount(orderService.orderAllCount2(map));
    }
    return SUCCESS;
  }

  public String getQueryOrderList() throws Exception {
    if (b != null) {
      page.setRowCount(1);
    } else {
      Map<String, Object> map = new HashMap<String, Object>();
      if (order != null) {
        map.put("orderNo", order.getOrderNo());
        map.put("orderStatus", order.getOrderStatus());
        map.put("consignee", order.getConsignee());
        map.put("consigneePhone", order.getConsigneePhone());
        map.put("consigneeAddress", order.getConsigneeAddress());
        map.put("payStatus", order.getPayStatus());
        map.put("overdue", order.getOverdue());
        map.put("createDate", order.getCreateDateString());
        map.put("beganday", order.getBeganday());
        map.put("endday", order.getEndday());
        map.put("sendDate", order.getSendTimes());
        map.put("beganday1", order.getBeganday1());
        map.put("endday1", order.getEndday1());
        map.put("tids", order.getTids());
        map.put("userId", order.getUserId());
        map.put("userName", order.getUserName());
        map.put("remarks", order.getRemarks());
        map.put("pushName", order.getPushName());
        map.put("consigneeType", order.getConsigneeType());
        map.put("refundStatus", order.getRefundStatus());
        map.put("payMethod", order.getPayMethod());
        map.put("logisticsNo", order.getLogisticsNo());
        map.put("logisticsName", order.getLogisticsName());
        map.put("shopName", order.getShopName());
        map.put("productId", order.getProductId());
        map.put("beganSendDate", order.getBeganSendDate());
        map.put("endSendDate", order.getEndSendDate());
        map.put("source", order.getSource());
        map.put("loginname", order.getLoginname());
        map.put("attendId", order.getAttendId());
        // map.put("isRefund", order.getIsRefund());
        map.put("refundStatus", order.getRefundStatus());
        map.put("groupBeginDateStr", order.getGroupBeginDateStr());
        map.put("groupEndDateStr", order.getGroupEndDateStr());
        map.put("notShip", order.getNotShip());
        map.put("pdkLoginname", order.getPdkLoginname());
      }
      /*
       * if (agency!= null) { map.put("agencyId",agency.getAgencyId()); }
       */
      if (order != null) {
        map.put("channel", order.getChannel());
      }
      testUsers = SellerService.getTestUsers();
      if (testUsers != null && testUsers.size() > 0) {
        if (testcount != null && !testcount.equals("")) {
          map.put("userIds", testUsers);
        } else {
          map.put("notuserIds", testUsers);
        }
      }
      if (page != null) {
        map.put("pageSize", page.getPageSize());
        map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      }
      if (a == null) {
        if (orderType == 1) {
          map.put("orderType", 1);
        } else {
          map.put("orderTypes", 1);
        }
      } else {
        map.put("a", 1);
      }
      List<OrderPojo> orders = orderService.orderAllList2(map);

      JSONArray json = JSONArray.fromObject(orders);
      page.setResult(json.toString());
      page.setRowCount(orders == null ? 0 : orders.size());
    }
    return SUCCESS;
  }

  /***
   * 后台修改订单处理状态
   * 
   * // * @return
   * 
   * @throws Exception
   */
  public String updateOrderhandleStatus() throws Exception {
    SysLoginPojo loginPojo = UserUtil.getUser();
    order.setUpdateBy(loginPojo.getId());
    if (order.getIsHandle() == 0) {
      order.setIsHandle(1);
    } else {
      order.setIsHandle(0);
    }
    orderService.updateOrder(order);
    FileUtil.alertMessageBySkip("修改成功！", "order.do?os=" + os);
    return null;
  }


  /**
   * 批量确认收货
   * 
   * @return
   * @throws SQLException
   */
  public String updatesendOrder() throws SQLException {
    if (tids != null && tids.length > 0) {
      boolean flag = true;
      OrderPojo order = new OrderPojo();
      SysLoginPojo loginPojo = UserUtil.getUser();
      order.setUpdateBy(loginPojo.getId());
      for (String tid : tids) {
        order.setId(Long.valueOf(tid));
        order.setOrderStatus(4);
        order.setConfirmDate(new Date());
        try {
          int i = orderService.updateOrderStatus2(order);
          if (i > 0) {
            try {
              Util.log("判断订单类型是不是拼得客!");
              OrderPojo orderPojo = orderService.getfindByIdOrder(Long.valueOf(tid));
              if (orderPojo != null && orderPojo.getActivityId() != null
                  && orderPojo.getSource() == 8 && orderPojo.getPdkUid() != null
                  && orderPojo.getPdkUid() > 0 && orderPojo.getIsRebate() != null
                  && orderPojo.getIsRebate() == 0) {
                Util.log("判断订单是否是0元");
                if (orderPojo.getFactPrice().doubleValue() == 0.0) {
                  Util.log("0元不用返佣!");
                } else {
                  GrouponActivityPojo grouponActivity =
                      grouponActivityService.getById(orderPojo.getActivityId());
                  if (grouponActivity != null && grouponActivity.getRebateRatio() > 0.0) {
                    Util.log("计算返佣金额!");
                    Double price = orderPojo.getRebatePrice();
                    UserPindekeInfoPojo userPindekeInfo =
                        userPindekeInfoService.findByUserId(orderPojo.getPdkUid());
                    if (userPindekeInfo != null) {
                      Util.log("减去拼得客冻结金额且添加拼得客剩余金额!");
                      UserPindekeInfoPojo userPindeke = new UserPindekeInfoPojo();
                      userPindeke.setId(userPindekeInfo.getId());
                      userPindeke.setFreezingPriceMinus(price);// 冻结金额
                      userPindeke.setSurpluPriceAdd(price);// 余额
                      userPindeke.setRebatePriceAdd(price);// 总返佣金额
                      int upi = userPindekeInfoService.update(userPindeke);
                      if (upi > 0) {
                        Util.log("修改拼得客金额成功!");
                      }
                      Util.log("修改订单返佣信息!");
                      Date nowDate = new Date();
                      OrderPojo orderUp = new OrderPojo();
                      orderUp.setId(orderPojo.getId());
                      // orderUp.setRebatePrice(price);
                      orderUp.setRebateTime(nowDate);
                      orderUp.setIsRebate(1);
                      // 记录返佣比例
                      // orderUp.setRebateRatio(grouponActivity.getRebateRatio());
                      int uo = orderService.updateOrder(orderUp);
                      if (uo > 0) {
                        Util.log("修改订单返佣信息成功!");
                      }
                      Util.log("插入交易记录表!");
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
                        Util.log("插入交易记录表成功!");
                      }
                    }
                  }
                }
              }
            } catch (Exception e) {
              Util.log("拼得客返佣出现异常!");
              e.printStackTrace();
            }
          }
        } catch (Exception e) {
          flag = false;
          e.printStackTrace();
        }
      }
      if (!flag) {
        FileUtil.alertMessageBySkip("确认出错！", "order.do?os=" + os);
      } else {
        FileUtil.alertMessageBySkip("确认成功！", "order.do?os=" + os);
      }
    } else {
      FileUtil.alertMessageBySkip("请先勾选！", "order.do?os=" + os);
    }
    return null;
  }

}
