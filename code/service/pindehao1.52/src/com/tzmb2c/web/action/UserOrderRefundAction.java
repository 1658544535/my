package com.tzmb2c.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.alipay.util.UtilDate;
import com.tencent.WXPay;
import com.tencent.common.Configure;
import com.tencent.common.Util;
import com.tencent.common.XMLUtil;
import com.tencent.protocol.refund_protocol.RefundReqData;
import com.tencent.protocol.refund_protocol.RefundResData;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.business.service.WalletService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.AlipayOrderInfoPojo;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserOrderRefundPojo;
import com.tzmb2c.web.pojo.UserWalletLogPojo;
import com.tzmb2c.web.pojo.UserWalletPojo;
import com.tzmb2c.web.pojo.WxpayOrderInfoPojo;
import com.tzmb2c.web.service.AlipayOrderInfoService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderRefundService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserOrderRefundService;
import com.tzmb2c.web.service.UserWalletLogService;
import com.tzmb2c.web.service.UserWalletService;
import com.tzmb2c.web.service.WxpayOrderInfoService;

/**
 * 退货记录
 * 
 * @author creazylee
 * 
 */
public class UserOrderRefundAction extends SuperAction {

  // private StringUtil stringUtil;
  @Autowired
  private SysLoginService sysLoginService;
  private String[] tids;
  private String refundStatus;
  private String result;
  private String skipStatus;
  private String is;
  private String logtype;
  private String lognum;
  private String message;
  private List<Integer> testUsers;
  private Long a;
  private String testcount;
  private String isAll;
  private Long orderId;
  private Integer payMethod;
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

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Integer getPayMethod() {
    return payMethod;
  }

  public void setPayMethod(Integer payMethod) {
    this.payMethod = payMethod;
  }

  public String getIsAll() {
    return isAll;
  }

  public void setIsAll(String isAll) {
    this.isAll = isAll;
  }

  public String getTestcount() {
    return testcount;
  }

  public void setTestcount(String testcount) {
    this.testcount = testcount;
  }

  public Long getA() {
    return a;
  }

  public void setA(Long a) {
    this.a = a;
  }

  public List<Integer> getTestUsers() {
    return testUsers;
  }

  public void setTestUsers(List<Integer> testUsers) {
    this.testUsers = testUsers;
  }

  @Autowired
  private OrderRefundService orderRefundService;
  @Autowired
  private UserOrderRefundService userOrderRefundService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private OrderDetailService orderDetailService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private ProductSkuLinkService productSkuLinkService;
  @Autowired
  private UserWalletService userWalletService;
  @Autowired
  private UserWalletLogService userWalletLogService;
  @Autowired
  private WalletService walletService;
  @Autowired
  private AlipayOrderInfoService alipayOrderInfoService;
  @Autowired
  private WxpayOrderInfoService wxpayOrderInfoService;
  @Autowired
  private SellerService sellerService;

  private UserOrderRefundPojo userOrderRefundPojo;
  private OrderPojo orderPojo;
  private OrderDetailPojo orderDetailPojo;
  private UserWalletPojo userWalletPojo;
  private UserWalletLogPojo userWalletLogPojo;
  private List<UserOrderRefundPojo> userOrderRefundList = null;
  private List<SysDictPojo> refundStatusList = null;


  public UserWalletPojo getUserWalletPojo() {
    return userWalletPojo;
  }

  public void setUserWalletPojo(UserWalletPojo userWalletPojo) {
    this.userWalletPojo = userWalletPojo;
  }

  public UserWalletLogPojo getUserWalletLogPojo() {
    return userWalletLogPojo;
  }

  public void setUserWalletLogPojo(UserWalletLogPojo userWalletLogPojo) {
    this.userWalletLogPojo = userWalletLogPojo;
  }

  public String getLogtype() {
    return logtype;
  }

  public void setLogtype(String logtype) {
    this.logtype = logtype;
  }

  public String getLognum() {
    return lognum;
  }

  public void setLognum(String lognum) {
    this.lognum = lognum;
  }

  public OrderDetailPojo getOrderDetailPojo() {
    return orderDetailPojo;
  }

  public void setOrderDetailPojo(OrderDetailPojo orderDetailPojo) {
    this.orderDetailPojo = orderDetailPojo;
  }

  public List<SysDictPojo> getRefundStatusList() {
    return refundStatusList;
  }

  public void setRefundStatusList(List<SysDictPojo> refundStatusList) {
    this.refundStatusList = refundStatusList;
  }

  public UserOrderRefundService getUserOrderRefundService() {
    return userOrderRefundService;
  }

  public void setUserOrderRefundService(UserOrderRefundService userOrderRefundService) {
    this.userOrderRefundService = userOrderRefundService;
  }

  public UserOrderRefundPojo getUserOrderRefundPojo() {
    return userOrderRefundPojo;
  }

  public void setUserOrderRefundPojo(UserOrderRefundPojo userOrderRefundPojo) {
    this.userOrderRefundPojo = userOrderRefundPojo;
  }

  public List<UserOrderRefundPojo> getUserOrderRefundList() {
    return userOrderRefundList;
  }

  public void setUserOrderRefundList(List<UserOrderRefundPojo> userOrderRefundList) {
    this.userOrderRefundList = userOrderRefundList;
  }

  public String getSkipStatus() {
    return skipStatus;
  }

  public void setSkipStatus(String skipStatus) {
    this.skipStatus = skipStatus;
  }

  public String getRefundStatus() {
    return refundStatus;
  }

  public void setRefundStatus(String refundStatus) {
    this.refundStatus = refundStatus;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public OrderDetailService getOrderDetailService() {
    return orderDetailService;
  }

  public void setOrderDetailService(OrderDetailService orderDetailService) {
    this.orderDetailService = orderDetailService;
  }

  public String getIs() {
    return is;
  }

  public void setIs(String is) {
    this.is = is;
  }

  /***
   * 获取业务字典
   */
  private void getList() {
    try {
      refundStatusList = sysDictService.getSysDictListByType("refund_status");
    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  /***
   * 查找所有退货记录
   * 
   * @return
   */
  public String userOrderRefundAllList() {
    // getList();
    try {
      userOrderRefundList =
          userOrderRefundService.userOrderRefundAllList(userOrderRefundPojo, page, refundStatus);
      // 查询商品sku
      if (userOrderRefundList != null && userOrderRefundList.size() > 0) {
        ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
        ProductSkuLinkPojo productSkuLinkPojo = null;
        for (UserOrderRefundPojo refund : userOrderRefundList) {
          if (refund.getSkuLinkId() != null && refund.getSkuLinkId() > 0) {
            productSkuLink.setId(Long.valueOf(refund.getSkuLinkId()));
            productSkuLinkPojo = productSkuLinkService.findProductSkuLink(productSkuLink);
            if (productSkuLinkPojo != null
                && (StringUtils.isNotBlank(productSkuLinkPojo.getColorValue()) || StringUtils
                    .isNotBlank(productSkuLinkPojo.getFormatValue()))) {
              refund.setProductName(refund.getProductName().concat("（颜色：")
                  .concat(productSkuLinkPojo.getColorValue()).concat("，尺寸：")
                  .concat(productSkuLinkPojo.getFormatValue()).concat("）"));
            }
          }
        }
      }
      JSONArray json = JSONArray.fromObject(userOrderRefundList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /***
   * 查找退货记录数目
   * 
   * @return
   * @throws Exception
   */
  public String getUserOrderRefundCount() throws Exception {
    // getList();
    try {
      if (page == null) {
        page = new Pager();
      }
      page.setRowCount(userOrderRefundService.userOrderRefundAllCount(userOrderRefundPojo,
          refundStatus));
      if ("1".equals(refundStatus)) {
        // 退货申请
        return "Apply";
      }
      if ("2".equals(refundStatus)) {
        // 退货进度
        return "Progress";
      }
      if ("4".equals(refundStatus)) {
        // 退钱到红包
        return "Money";
      }
      if ("5".equals(refundStatus)) {
        // 退货退款仲裁
        return "Arbitration";
      }
      if ("0".equals(refundStatus)) {
        // 退货退款仲裁
        return "Deal";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /***
   * 
   * @throws Exception
   */
  public String getUserOrderRefundRowCount() throws Exception {
    // getList();
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(userOrderRefundService.userOrderRefundAllCount(userOrderRefundPojo,
        refundStatus));
    return SUCCESS;
  }

  /***
   * 删除单条
   * 
   * @return
   * @throws SQLException
   */
  public String delUserOrderRefund() throws SQLException {
    try {
      userOrderRefundService.delUserOrderRefund(userOrderRefundPojo);
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  /***
   * 删除全部
   * 
   * @return
   */
  public String delAllUserOrderRefundById() {
    userOrderRefundService.delAllUserOrderRefundById(tids);
    FileUtil.alertMessageBySkip("删除成功！", "userOrderRefundManage.do");
    return null;
  }

  /***
   * 跳转到退货记录审核页面
   * 
   * @return
   * @throws Exception
   */
  public String goCheckUserOrderRefund() throws Exception {
    getList();
    userOrderRefundPojo =
        userOrderRefundService.findUserOrderRefundById(userOrderRefundPojo.getId());
    return SUCCESS;
  }

  /***
   * 跳转到退货申请审核页面
   * 
   * @return
   * @throws Exception
   */
  public String goCheckUserOrderRefundApply() throws Exception {
    getList();
    userOrderRefundPojo =
        userOrderRefundService.findUserOrderRefundById(userOrderRefundPojo.getId());
    return SUCCESS;
  }

  /***
   * 后台退货审核页面
   * 
   * @return
   * @throws Exception
   */
  public String checkOrderRefund() throws Exception {
    System.out.println(is);
    // 填写退货信息
    if (is.equals("3")) {
      userOrderRefundPojo.setStatus(3);
      userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
      orderDetailService.updateReStatus(userOrderRefundPojo.getDetailId(), 3);
      FileUtil.alertMessageBySkip("操作成功！", "userOrderRefundManage.do?refundStatus=3");
    }
    // 审核申请成功
    else if (is.equals("2")) {
      userOrderRefundPojo.setStatus(2);
      userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("id", userOrderRefundPojo.getDetailId());
      map.put("reStatus", 2);
      map.put("status", 0);
      orderDetailService.updateReStatusmap(map);
      // 判断订单re_status是否都有 没有修改订单为取消状态is_cancel_order=1
      /*
       * orderDetailPojo =
       * orderDetailService.findOrderDetailById(userOrderRefundPojo.getDetailId()); Map<String,
       * Object> map2=new HashMap<String,Object>();
       * map2.put("orderId",orderDetailPojo.getOrderId()); map2.put("userId",
       * orderDetailPojo.getUserId()); int d=orderRefundService.getNOrefundDetail(map2); if(d==0){
       * OrderPojo o=new OrderPojo(); o.setIsCancelOrder(1); o.setId(orderDetailPojo.getOrderId());
       * orderService.updateIsCancelOrder(o); }
       */
      // orderDetailService.updateReStatus(userOrderRefundPojo.getDetailId(),2);
      FileUtil.alertMessageBySkip("操作成功！", "userOrderRefundManage.do?refundStatus=3");
    }
    // 退货成功
    else if (is.equals("4")) {
      // 微信/支付宝退款
      refundByAliOrWxPay();

      // userOrderRefundPojo.setStatus(4);
      // userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
      // Map<String, Object> map = new HashMap<String, Object>();
      // map.put("id", userOrderRefundPojo.getDetailId());
      // map.put("reStatus", 4);
      // map.put("status", "0");
      // orderDetailService.updateReStatusmap(map);
      // orderDetailService.updateReStatus(userOrderRefundPojo.getDetailId(),4);



      FileUtil.alertMessageBySkip("操作成功！", "userOrderRefundManage.do?refundStatus=3");
    }
    // 退货失败
    else if (is.equals("5")) {
      userOrderRefundPojo.setStatus(5);
      userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
      orderDetailService.updateReStatus(userOrderRefundPojo.getDetailId(), 5);
      FileUtil.alertMessageBySkip("操作成功！", "userOrderRefundManage.do?refundStatus=3");
    }
    // 审核失败
    else if (is.equals("6")) {
      userOrderRefundPojo.setStatus(6);
      userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
      orderDetailService.updateReStatus(userOrderRefundPojo.getDetailId(), 6);
      FileUtil.alertMessageBySkip("操作成功！", "userOrderRefundManage.do?refundStatus=3");
    }
    // 退款成功
    else if (is.equals("7")) {
      // 微信/支付宝退款
      refundByAliOrWxPay();

      // userOrderRefundPojo.setStatus(7);
      // userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
      // // orderDetailService.updateReStatus(userOrderRefundPojo.getDetailId(),7);
      // Map<String, Object> map = new HashMap<String, Object>();
      // map.put("id", userOrderRefundPojo.getDetailId());
      // map.put("reStatus", 7);
      // map.put("status", "0");
      // orderDetailService.updateReStatusmap(map);
      // 判断订单re_status是否都有 没有修改订单为取消状态is_cancel_order=1
      /*
       * orderDetailPojo =
       * orderDetailService.findOrderDetailById(userOrderRefundPojo.getDetailId()); Map<String,
       * Object> map2=new HashMap<String,Object>();
       * map2.put("orderId",orderDetailPojo.getOrderId()); map2.put("userId",
       * orderDetailPojo.getUserId()); int d=orderRefundService.getNOrefundDetail(map2); if(d==0){
       * OrderPojo o=new OrderPojo(); o.setIsCancelOrder(1); o.setId(orderDetailPojo.getOrderId());
       * orderService.updateIsCancelOrder(o); }
       */



      FileUtil.alertMessageBySkip("操作成功！", "userOrderRefundManage.do?refundStatus=3");
    }
    return null;
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void refundByAliOrWxPay() throws SQLException {
    SysLoginPojo user = UserUtil.getUser();
    if (user == null) {
      FileUtil.alertMessageBySkip("请先登录！", "loginin.do");
    }

    if (orderId != null && orderId > 0) {
      OrderPojo orderPojo = null;
      try {
        // 根据订单ID查找订单是否存在
        orderPojo = orderService.getfindByIdOrder(orderId);
      } catch (Exception e) {
        e.printStackTrace();
      }
      if (orderPojo != null) {
        if (orderPojo.getPayMethod() != null && orderPojo.getPayMethod() == 1) {
          Util.log("售后退款-支付宝退款");
          AlipayOrderInfoPojo alipayOrderInfoPojo =
              alipayOrderInfoService.findPayInfoByTradeNo(orderPojo.getOutTradeNo());
          if (alipayOrderInfoPojo != null
              && ("TRADE_FINISHED".equals(alipayOrderInfoPojo.getTradeStatus()) || "TRADE_SUCCESS"
                  .equals(alipayOrderInfoPojo.getTradeStatus()))) {
            Util.log(alipayOrderInfoPojo.getTradeNo());
            Util.log("拼装退款详细数据！");

            // 退款详情
            if (StringUtils.isNotBlank(alipayOrderInfoPojo.getTradeNo())) {
              String detailData =
                  alipayOrderInfoPojo.getTradeNo() + "^" + alipayOrderInfoPojo.getTotalFee()
                      + "^售后退款";
              Util.log("拼装好的详细数据：" + detailData);

              // 退款单号
              String batchNo = "";
              if (StringUtils.isNotEmpty(alipayOrderInfoPojo.getOutRefundNo())
                  && !"SUCCESS".equals(alipayOrderInfoPojo.getRefundStatus())) {
                // 使用上次退款失败所生成的流水号
                batchNo = alipayOrderInfoPojo.getOutRefundNo();
              } else {
                // 第一册发起退款，需新生成流水号
                batchNo = UtilDate.getDate() + UtilDate.getThree() + StringUtil.getSerialNum();
              }

              try {
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
                System.out.println(sHtmlText);
                Map<String, Object> reqMap = XMLUtil.xml2Map(sHtmlText);
                Util.log("支付宝无密退款返回：" + reqMap.get("is_success"));
                // 操作者
                Long operator = user.getId();
                // 交易号
                String tradeNo = "";
                // 支付宝订单号
                String outTradeNo = "";
                // 退款金额(实付金额)
                String refundFee = "0";
                String errMsg = "";
                if ("T".equals(reqMap.get("is_success"))) {
                  errMsg += orderPojo.getOrderNo() + "-申请退款成功\n";
                  // 交易号
                  tradeNo = alipayOrderInfoPojo.getTradeNo();
                  // 微信订单号
                  outTradeNo = alipayOrderInfoPojo.getOutTradeNo();
                  // 退款金额(实付金额)
                  refundFee = alipayOrderInfoPojo.getTotalFee();

                  refundApplyForAlipayOrder("SUCCESS", tradeNo, outTradeNo, batchNo, refundFee,
                      "售后退款", null, operator);

                } else if ("F".equals(reqMap.get("is_success"))) {
                  errMsg = orderPojo.getOrderNo() + "-申请退款失败！";
                  FileUtil.alertMessageBySkip(errMsg, "userOrderRefundManage.do?refundStatus=3");
                } else if ("P".equals(reqMap.get("is_success"))) {
                  errMsg = orderPojo.getOrderNo() + "-退款处理中！";
                  FileUtil.alertMessageBySkip(errMsg, "userOrderRefundManage.do?refundStatus=3");
                }
                Util.log(errMsg);
              } catch (Exception e) {
                e.printStackTrace();
                FileUtil.alertMessageBySkip(orderPojo.getOrderNo() + "支付宝发起退款请求出现异常！",
                    "userOrderRefundManage.do?refundStatus=3");
              }
            } else {
              Util.log("支付宝交易号为空或实付金额为空！");
              FileUtil.alertMessageBySkip(orderPojo.getOrderNo() + "-支付宝交易号为空或实付金额为空！",
                  "userOrderRefundManage.do?refundStatus=3");
            }
          } else {
            Util.log(orderPojo.getOrderNo() + "-查询不到支付宝交易记录！");
            FileUtil.alertMessageBySkip(orderPojo.getOrderNo() + "-查询不到支付宝交易记录！",
                "userOrderRefundManage.do?refundStatus=3");
          }
        } else if (orderPojo.getPayMethod() != null
            && (orderPojo.getPayMethod() == 2 || orderPojo.getPayMethod() == 8)) {
          Util.log("售后退款-微信退款");
          WxpayOrderInfoPojo wxpay =
              wxpayOrderInfoService.findPayInfoByTradeNo(orderPojo.getOutTradeNo());
          if (wxpay != null && "TRADE_SUCCESS".equals(wxpay.getTradeStatus())) {
            if (wxpay.getTotalFee() != null && wxpay.getTransactionId() != null) {
              // 退款单号
              String outRefundNo = "";
              if (StringUtils.isNotEmpty(wxpay.getOutRefundNo())
                  && !"SUCCESS".equals(wxpay.getRefundStatus())) {
                // 使用上次退款失败所生成的流水号
                outRefundNo = wxpay.getOutRefundNo();
              } else {
                // 第一次发起退款，需新生成流水号
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
              String opUserID = "";
              if (Configure.JSAPI.equals(wxpay.getTradeType())) {
                opUserID = Configure.getJsmchID();
              } else {
                opUserID = Configure.getMchid();
              }
              RefundReqData wxdata = null;
              RefundResData refundResData = null;
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
                  Long operator = user.getId();
                  if ("SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode)) {
                    refundApplyForWxOrder("SUCCESS", transactionId, outTradeNo, outRefundNo,
                        String.valueOf(refundFee), "售后退款", null, refundResData.getRefund_id(),
                        operator);

                  } else {
                    String errMsg = "";
                    if ("FAIL".equals(returnCode)) {
                      errMsg = refundResData.getReturn_msg();
                      FileUtil.alertMessageBySkip(errMsg + "-退款失败！",
                          "userOrderRefundManage.do?refundStatus=3");
                    } else if ("FAIL".equals(resultCode)) {
                      errMsg = refundResData.getErr_code() + ":" + refundResData.getErr_code_des();
                      FileUtil.alertMessageBySkip(errMsg + "-退款失败！",
                          "userOrderRefundManage.do?refundStatus=3");
                    }
                    refundApplyForWxOrder("FAIL", transactionId, outTradeNo, outRefundNo,
                        String.valueOf(refundFee), "售后退款", errMsg, null, operator);
                  }
                }
              } catch (Exception e) {
                e.printStackTrace();
                FileUtil.alertMessageBySkip(orderPojo.getOrderNo() + "-微信发起退款请求出现异常！",
                    "userOrderRefundManage.do?refundStatus=3");
              }
            } else {
              Util.log("微信交易号为空或实付金额为空！");
              FileUtil.alertMessageBySkip(orderPojo.getOrderNo() + "-微信交易号为空或实付金额为空！",
                  "userOrderRefundManage.do?refundStatus=3");
            }
          } else {
            Util.log(orderPojo.getOrderNo() + "-查询不到微信交易记录！");
            FileUtil.alertMessageBySkip(orderPojo.getOrderNo() + "-查询不到微信交易记录！",
                "userOrderRefundManage.do?refundStatus=3");
          }
        } else if (orderPojo.getPayMethod() != null && orderPojo.getPayMethod() == 4) {
          Util.log("售后退款-钱包退款");
          UserOrderRefundPojo userOrderRefundPojo = new UserOrderRefundPojo();
          userOrderRefundPojo.setOrderId(orderPojo.getId());
          List<UserOrderRefundPojo> userOrderRefundlist =
              userOrderRefundService.findUserOrderRefundByorderId(userOrderRefundPojo);
          if (userOrderRefundlist != null && userOrderRefundlist.size() > 0) {
            UserOrderRefundPojo userOrderRefund = userOrderRefundlist.get(0);
            if (userOrderRefund.getRefundMoney() != null
                && userOrderRefund.getRefundMoney() == 0.00
                && userOrderRefund.getIsRefund() != null && userOrderRefund.getIsRefund() == 0
                && userOrderRefund.getStatus() != null && userOrderRefund.getStatus() != 7) {
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
                    userWalletLogPojo.setRemarks("售后退款-钱包退款");
                    userWalletLogService.insertUserWalletLog(userWalletLogPojo);

                    // 修改订单的退款状态
                    UserOrderRefundPojo userOrderRefundPojo2 = new UserOrderRefundPojo();
                    userOrderRefundPojo2.setOldRefund(2);
                    userOrderRefundPojo2.setIsRefund(2);
                    userOrderRefundPojo2.setRefundDate(new Date());

                    userOrderRefundPojo2.setStatus(7);
                    userOrderRefundPojo2.setId(userOrderRefund.getId());
                    userOrderRefundPojo2.setRefundMoney(orderPojo.getWalletPrice());
                    userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo2);
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("id", userOrderRefund.getDetailId());
                    map.put("reStatus", 7);
                    map.put("status", "0");
                    orderDetailService.updateReStatusmap(map);
                  } else {
                    FileUtil.alertMessageBySkip(orderPojo.getOrderNo() + "售后退款-钱包退款失败！",
                        "userOrderRefundManage.do?refundStatus=3");
                  }
                } catch (Exception e) {
                  e.printStackTrace();
                  FileUtil.alertMessageBySkip(orderPojo.getOrderNo() + "售后退款-钱包退款失败！",
                      "userOrderRefundManage.do?refundStatus=3");
                }
              }
            } else {
              FileUtil.alertMessageBySkip(orderPojo.getOrderNo() + "-该退单已经退过款了，无法重复退款！",
                  "userOrderRefundManage.do?refundStatus=3");
            }
          }
        } else {
          Util.log(orderPojo.getOrderNo() + "-支付方式有误！");
          FileUtil.alertMessageBySkip(orderPojo.getOrderNo() + "-支付方式有误！",
              "userOrderRefundManage.do?refundStatus=3");
        }
      } else {
        FileUtil.alertMessageBySkip("订单不存在！", "userOrderRefundManage.do?refundStatus=3");
      }
    } else {
      FileUtil.alertMessageBySkip("订单ID不能为空！", "userOrderRefundManage.do?refundStatus=3");
    }
  }

  /**
   * 微信退款申请处理.
   * 
   * @param applyStatus 申请返回状态 SUCCESS-成功 FAIL-失败
   * @param transcationId 支付宝支付流水号
   * @param outTradeNo 商户订单号
   * @param outRefundNo 商户退款单号
   * @param refundFee 退款金额
   * @param refundReason 退款原因
   * @param errMsg 失败原因
   * @param refundId 微信退款单号
   * @param operator 操作者
   * @throws Exception
   * @throw
   * @return void
   */
  public void refundApplyForAlipayOrder(String applyStatus, String tradeNo, String outTradeNo,
      String outRefundNo, String refundFee, String refundReason, String errMsg, Long operator)
      throws Exception {
    // 支付信息修改
    Map<String, Object> param = new HashMap<String, Object>();
    if ("SUCCESS".equals(applyStatus)) {
      param.put("outRefundNo", outRefundNo);
      param.put("refundFee", refundFee);
      param.put("refundReason", refundReason);
      param.put("refundStatus", "PROCESSING");
      param.put("updateBy", operator);
      param.put("tradeNo", tradeNo);
      param.put("outTradeNo", outTradeNo);
      param.put("source", 1);
      int flag = alipayOrderInfoService.updatePayRefund(param);
      if (flag > 0) {
        // // 订单修改
        // param.clear();
        // param.put("isRefund", 1);
        // param.put("outRefundNo", outRefundNo);
        // param.put("oldRefund", 2);
        // param.put("outTradeNo", outTradeNo);
        // orderService.updateOrderRefund(param);

        userOrderRefundPojo.setOldRefund(2);
        userOrderRefundPojo.setIsRefund(1);
        userOrderRefundPojo.setOutRefundNo(outRefundNo);
        userOrderRefundPojo.setRefundDate(new Date());

        // userOrderRefundPojo.setStatus(7);
        userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
        // Map<String, Object> map = new HashMap<String, Object>();
        // map.put("id", userOrderRefundPojo.getDetailId());
        // map.put("reStatus", 7);
        // map.put("status", "0");
        // orderDetailService.updateReStatusmap(map);
      }
    } else if ("FAIL".equals(applyStatus)) {
      param.put("outRefundNo", outRefundNo);
      param.put("refundFee", refundFee);
      param.put("refundReason", refundReason);
      param.put("refundStatus", "FAIL");
      param.put("remarks", errMsg);
      param.put("updateBy", operator);
      param.put("tradeNo", tradeNo);
      param.put("outTradeNo", outTradeNo);
      param.put("source", 1);
      int flag = alipayOrderInfoService.updatePayRefund(param);
      if (flag > 0) {
        // // 订单修改
        // param.clear();
        // param.put("outRefundNo", outRefundNo);
        // param.put("oldRefund", 2);
        // param.put("outTradeNo", outTradeNo);
        // orderService.updateOrderRefund(param);

        userOrderRefundPojo.setOldRefund(2);
        userOrderRefundPojo.setIsRefund(3);
        userOrderRefundPojo.setOutRefundNo(outRefundNo);
        userOrderRefundPojo.setRefundDate(new Date());

        userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
      }
    }
  }

  /**
   * 微信退款申请处理.
   * 
   * @param applyStatus 申请返回状态 SUCCESS-成功 FAIL-失败
   * @param transcationId 微信支付流水号
   * @param outTradeNo 商户订单号
   * @param outRefundNo 商户退款单号
   * @param refundFee 退款金额
   * @param refundReason 退款原因
   * @param errMsg 失败原因
   * @param refundId 微信退款单号
   * @param operator 操作者
   * @throws Exception
   * @throw
   * @return void
   */
  public void refundApplyForWxOrder(String applyStatus, String transcationId, String outTradeNo,
      String outRefundNo, String refundFee, String refundReason, String errMsg, String refundId,
      Long operator) throws Exception {
    // 支付信息修改
    Map<String, Object> param = new HashMap<String, Object>();
    if ("SUCCESS".equals(applyStatus)) {
      param.put("outRefundNo", outRefundNo);
      param.put("refundFee", refundFee);
      param.put("refundReason", refundReason);
      param.put("refundId", refundId);
      param.put("refundStatus", "PROCESSING");
      param.put("updateBy", operator);
      param.put("tradeNo", transcationId);
      param.put("outTradeNo", outTradeNo);
      param.put("source", 1);
      int flag = wxpayOrderInfoService.updatePayRefund(param);
      if (flag > 0) {
        // // 订单修改
        // param.clear();
        // param.put("isRefund", 1);
        // param.put("outRefundNo", outRefundNo);
        // param.put("oldRefund", 2);
        // param.put("outTradeNo", outTradeNo);
        // orderService.updateOrderRefund(param);

        userOrderRefundPojo.setOldRefund(2);
        userOrderRefundPojo.setIsRefund(1);
        userOrderRefundPojo.setOutRefundNo(outRefundNo);
        userOrderRefundPojo.setRefundDate(new Date());

        // userOrderRefundPojo.setStatus(7);
        userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
        // Map<String, Object> map = new HashMap<String, Object>();
        // map.put("id", userOrderRefundPojo.getDetailId());
        // map.put("reStatus", 7);
        // map.put("status", "0");
        // orderDetailService.updateReStatusmap(map);
      }
    } else if ("FAIL".equals(applyStatus)) {
      param.put("outRefundNo", outRefundNo);
      param.put("refundFee", refundFee);
      param.put("refundReason", refundReason);
      param.put("refundStatus", "FAIL");
      param.put("remarks", errMsg);
      param.put("updateBy", operator);
      param.put("tradeNo", transcationId);
      param.put("outTradeNo", outTradeNo);
      param.put("source", 1);
      int flag = wxpayOrderInfoService.updatePayRefund(param);
      if (flag > 0) {
        // // 订单修改
        // param.clear();
        // param.put("outRefundNo", outRefundNo);
        // param.put("oldRefund", 2);
        // param.put("outTradeNo", outTradeNo);
        // orderService.updateOrderRefund(param);

        userOrderRefundPojo.setOldRefund(2);
        userOrderRefundPojo.setIsRefund(3);
        userOrderRefundPojo.setOutRefundNo(outRefundNo);
        userOrderRefundPojo.setRefundDate(new Date());

        userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
      }
    }
  }

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

  /***
   * 跳转到退货进度审核页面
   * 
   * @return
   * @throws Exception
   */
  public String goCheckUserOrderRefundProgress() throws Exception {
    getList();
    userOrderRefundPojo =
        userOrderRefundService.findUserOrderRefundById(userOrderRefundPojo.getId());
    return SUCCESS;
  }

  /***
   * 审核单条，同时修改订单的状态
   * 
   * @return
   */
  public String checkUserOrderRefund() {
    try {
      userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
    } catch (SQLException e) {

      e.printStackTrace();
    }
    int orderIsCancel = userOrderRefundPojo.getStatus();
    orderPojo = new OrderPojo();
    orderPojo.setId(userOrderRefundPojo.getOrderId());
    if (orderIsCancel == 1 || orderIsCancel == 2 || orderIsCancel == 4) {
      try {
        orderPojo.setIsCancelOrder(3);// 审核通过
        orderService.updateIsCancelOrder(orderPojo);// 修改订单状态
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
    if (orderIsCancel == 3) {
      try {
        orderPojo.setIsCancelOrder(4);// 审核失败
        orderService.updateIsCancelOrder(orderPojo);// 修改订单状态
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
    FileUtil.alertMessageBySkip("操作成功！", "userOrderRefundManage.do?refundStatus=" + skipStatus);
    return null;
  }

  /***
   * 
   * @return
   */
  public String updateUserOrderRefund() {
    try {
      userOrderRefundService.checkOrder(userOrderRefundPojo);
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  /***
   * 
   * @return
   */
  public String queryDeliverInfo() {
    return SUCCESS;
  }

  public String checkAllUserOrderRefundById() {
    try {
      if (tids != null) {
        userOrderRefundService.checkAllUserOrderRefundById(tids, userOrderRefundPojo);
      }
      // FileUtil.alertMessageBySkip("审核成功！", "userOrderRefundManage.do?refundStatus=1");
      if (refundStatus.equals("1")) {
        FileUtil.alertMessageBySkip("操作成功！", "userOrderRefundManage.do?refundStatus=1");
      }
      if (refundStatus.equals("2")) {
        FileUtil.alertMessageBySkip("操作成功！", "userOrderRefundManage.do?refundStatus=2");
      }
      return null;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /***
   * 退货详情
   * 
   * @return
   */
  public String goFindRefundOrder() {
    // getList();
    userOrderRefundList =
        userOrderRefundService.userOrderRefundAllList(userOrderRefundPojo, page, refundStatus);
    if (userOrderRefundList.size() >= 1) {
      userOrderRefundPojo = userOrderRefundList.get(0);
    }
    if (refundStatus != null && refundStatus.equals("1")) {
      return "Apply";
    }
    return SUCCESS;
  }

  /***
   * 修改订单
   * 
   * @return
   * @throws Exception
   */
  public String updateOrderRefund() throws Exception {

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      userOrderRefundPojo.setUpdateBy(loginPojo.getId());
      userOrderRefundPojo.setUpdateDate(new Date());
    }
    userOrderRefundService.updateOrderRefundRemarks(userOrderRefundPojo);
    if (refundStatus != null && refundStatus.equals("1")) {
      FileUtil.alertMessageBySkip("修改成功！", "userOrderRefundManage.do?refundStatus=1");
    }
    FileUtil.alertMessageBySkip("修改成功！", "userOrderRefundManage.do?refundStatus=2");
    return null;
  }

  /***
   * 退钱进钱包
   * 
   * @return
   * @throws Exception
   */
  public String refundMoney() throws Exception {
    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    }
    message = "成功退款进钱包!";
    UserOrderRefundPojo userOrderRefund =
        userOrderRefundService.findUserOrderRefundById(userOrderRefundPojo.getId());
    if (userOrderRefund != null) {
      Double sum =
          userOrderRefund.getStockPrice() * userOrderRefund.getRefundNum()
              - userOrderRefund.getCouponPrice();
      if (userOrderRefund.getStatus() == 4 || userOrderRefund.getStatus() == 7) {
        if (userOrderRefund.getRefundMoney() == null || userOrderRefund.getRefundMoney() == 0.00) {
          if (userOrderRefundPojo.getRefundMoney() <= sum) {
            UserWalletPojo userWalletPojo =
                userWalletService.findUserWalletByUserId(userOrderRefund.getUserId());
            if (userWalletPojo == null) {
              userWalletPojo = new UserWalletPojo();
              userWalletPojo.setUserId(userOrderRefund.getUserId());
              userWalletPojo.setCreateBy(loginPojo.getId());
              userWalletPojo.setUpdateBy(loginPojo.getId());
              userWalletPojo.setCreateDate(new Date());
              userWalletPojo.setUpdateDate(new Date());
              userWalletPojo.setBalance(0.00);
              userWalletPojo.setTotalBalance(0.00);
              userWalletService.insertUserWallet(userWalletPojo);// 插入用户钱包
            }
            userWalletLogPojo = new UserWalletLogPojo();
            userWalletLogPojo.setCurBal(userWalletPojo.getBalance());
            /*
             * userWalletPojo.setBalance(userWalletPojo.getBalance()+userOrderRefundPojo.getRefundMoney
             * ()); userWalletPojo.setUpdateDate(new Date());
             * userWalletPojo.setUpdateBy(loginPojo.getId());
             * userWalletService.updateUserWallet(userWalletPojo);//更新用户钱包
             */
            walletService.addWalletBalance(userOrderRefundPojo.getRefundMoney(),
                userOrderRefund.getUserId());// 更新用户钱包
            userWalletLogPojo.setUserId(userOrderRefund.getUserId());
            userWalletLogPojo.setCreateDate(new Date());
            userWalletLogPojo.setUpdateDate(new Date());
            userWalletLogPojo.setCreateBy(loginPojo.getId());
            userWalletLogPojo.setUpdateBy(loginPojo.getId());
            userWalletLogPojo.setType(0L);
            userWalletLogPojo.setTradeAmt(userOrderRefundPojo.getRefundMoney());
            userWalletLogPojo.setOrderId(userOrderRefund.getOrderId());
            userWalletLogPojo.setOutTradeNo("cancel");
            userWalletLogPojo.setRemarks("申请了订单退款");
            userWalletLogPojo.setSource(userOrderRefund.getUserId());
            userWalletLogService.insertUserWalletLog(userWalletLogPojo);// 插入用户钱包记录
            userOrderRefundService.updateOrderRefundRemarks(userOrderRefundPojo);// 更新退货订单
          } else {
            message = "实际退款金额不能超出建议退款金额，退款失败";
          }
        } else {
          message = "该退单已经退过款了，无法重复退款";
        }
      } else {
        message = "该退单还在进行中，无法退款";
      }
    }
    // FileUtil.alertMessageBySkip( message , "userOrderRefundManage.do?refundStatus=4");
    return SUCCESS;
  }

  /***
   * 
   * 根据type更改status状态
   */
  public String updateStatusOfUserOrderRefundById() {
    try {
      if (userOrderRefundPojo != null) {
        if (userOrderRefundPojo.getType() == 1) {
          if (is.equals("1")) {
            userOrderRefundPojo.setStatus(6);
          } else {
            userOrderRefundPojo.setStatus(7);
          }
        } else {
          if (is.equals("3")) {
            userOrderRefundPojo.setStatus(4);
          } else {
            userOrderRefundPojo.setStatus(5);
          }

        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", userOrderRefundPojo.getDetailId());
        map.put("reStatus", userOrderRefundPojo.getStatus());
        map.put("status", "0");
        orderDetailService.updateReStatusmap(map);
        userOrderRefundService.updateStatusOfUserOrderRefundById(userOrderRefundPojo);
        FileUtil.alertMessageBySkip("修改成功！", "userOrderRefundManage.do?refundStatus=5");
        return null;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public String userOrderRefundDeal() {
    if (page == null) {
      page = new Pager();
    }
    return SUCCESS;
  }

  /**
   * 后台退货订单导出Excel表格
   * 
   * @return
   * @throws Exception
   */
  public void getOrderRefundExcel() throws Exception {
    new HashMap<String, Object>();
    /*
     * if (tids != null && !tids.equals("")) { map.put("tids", tids); } testUsers =
     * SellerService.getTestUsers(); if (testUsers != null && testUsers.size() > 0) { if (testcount
     * != null && !testcount.equals("")) { map.put("userIds", testUsers); } else {
     * map.put("notuserIds", testUsers); } }
     */
    if (isAll != null && isAll.equals("1")) {
      if (userOrderRefundPojo != null) {
        userOrderRefundList =
            userOrderRefundService.userOrderRefundAllList(userOrderRefundPojo, null, refundStatus);
      }
    } else {
      userOrderRefundList = userOrderRefundService.userOrderRefundAllList(null, null, refundStatus);
    }
    downloadFileName = "退货记录.xls";
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
      sheet.addCell(new Label(0, 0, "退款类型"));
      sheet.addCell(new Label(1, 0, "申请退款日期"));
      sheet.addCell(new Label(2, 0, "后台订单号"));
      sheet.addCell(new Label(3, 0, "支付方式"));
      sheet.addCell(new Label(4, 0, "交易号（支付宝或微信的交易号）"));
      sheet.addCell(new Label(5, 0, "订单金额"));
      sheet.addCell(new Label(6, 0, "实际退款金额"));
      sheet.addCell(new Label(7, 0, "退款类别"));
      sheet.addCell(new Label(8, 0, "商品ID"));
      sheet.addCell(new Label(9, 0, "数量"));
      sheet.addCell(new Label(10, 0, "备注"));
      sheet.addCell(new Label(11, 0, "扣款备注"));
      for (int j = 1, i = 1; j <= userOrderRefundList.size(); j++) {
        if (userOrderRefundList.get(j - 1).getType() == 1) {
          sheet.addCell(new Label(0, i, "退款"));
        } else if (userOrderRefundList.get(j - 1).getType() == 2) {
          sheet.addCell(new Label(0, i, "退货"));
        } else if (userOrderRefundList.get(j - 1).getType() == 3) {
          sheet.addCell(new Label(0, i, "售后退货"));
        } else {
          sheet.addCell(new Label(0, i, ""));
        }
        sheet.addCell(new Label(1, i, userOrderRefundList.get(j - 1).getCreatDateString()));
        sheet.addCell(new Label(2, i, userOrderRefundList.get(j - 1).getOrderNo()));
        if (userOrderRefundList.get(j - 1).getPayMethod() == 1) {
          sheet.addCell(new Label(3, i, "支付宝"));
        } else if (userOrderRefundList.get(j - 1).getPayMethod() == 2
            || userOrderRefundList.get(j - 1).getPayMethod() == 8) {
          sheet.addCell(new Label(3, i, "微信支付"));
        } else if (userOrderRefundList.get(j - 1).getPayMethod() == 3) {
          sheet.addCell(new Label(3, i, "货到付款"));
        } else if (userOrderRefundList.get(j - 1).getPayMethod() == 4) {
          sheet.addCell(new Label(3, i, "钱包支付"));
        } else if (userOrderRefundList.get(j - 1).getPayMethod() == 5) {
          sheet.addCell(new Label(3, i, "银联支付"));
        } else if (userOrderRefundList.get(j - 1).getPayMethod() == 6) {
          sheet.addCell(new Label(3, i, "苹果支付"));
        } else {
          sheet.addCell(new Label(3, i, ""));
        }
        sheet.addCell(new Label(4, i, userOrderRefundList.get(j - 1).getTradeNo()));
        sheet.addCell(new Label(5, i, userOrderRefundList.get(j - 1).getFactPrice() + ""));
        sheet.addCell(new Label(6, i, userOrderRefundList.get(j - 1).getFactPrice() + ""));
        sheet.addCell(new Label(7, i, ""));
        sheet.addCell(new Label(8, i, userOrderRefundList.get(j - 1).getProductId() + ""));
        sheet.addCell(new Label(9, i, userOrderRefundList.get(j - 1).getRefundNum() + ""));
        sheet.addCell(new Label(10, i, userOrderRefundList.get(j - 1).getRefundReason()));
        sheet.addCell(new Label(11, i, ""));
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

}
