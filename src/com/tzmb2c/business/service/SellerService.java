package com.tzmb2c.business.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.jiguang.JPushUtils;
import com.tencent.WXPay;
import com.tencent.common.Configure;
import com.tencent.common.MD5;
import com.tencent.common.Signature;
import com.tencent.common.Util;
import com.tencent.common.XMLParser;
import com.tencent.protocol.pre_pay_protocol.PrePayReqData;
import com.tencent.protocol.pre_pay_protocol.PrePayResData;
import com.tencent.protocol.refund_query_protocol.RefundOrderData;
import com.tencent.protocol.refund_query_protocol.RefundQueryReqData;
import com.tencent.protocol.refund_query_protocol.RefundQueryResData;
import com.tzmb2c.utils.PropertiesHelper;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityGoodsPojo;
import com.tzmb2c.web.pojo.CartPojo;
import com.tzmb2c.web.pojo.GrouponActivityPojo;
import com.tzmb2c.web.pojo.GrouponPushPojo;
import com.tzmb2c.web.pojo.ManufacturerSettlePojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.ProductCommentPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.SocialCirclePojo;
import com.tzmb2c.web.pojo.SpecialDiscountPojo;
import com.tzmb2c.web.pojo.SpecialShowPojo;
import com.tzmb2c.web.pojo.UserBrandPojo;
import com.tzmb2c.web.pojo.UserOrderRefundPojo;
import com.tzmb2c.web.pojo.WxpayOrderInfoPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.AlipayOrderInfoService;
import com.tzmb2c.web.service.GrouponActivityService;
import com.tzmb2c.web.service.GrouponPushService;
import com.tzmb2c.web.service.HistoryService;
import com.tzmb2c.web.service.ManufacturerSettleService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.ProductCommentService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.SpecialShowService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserCircleFollowService;
import com.tzmb2c.web.service.UserDeductionScoreService;
import com.tzmb2c.web.service.UserOrderRefundService;
import com.tzmb2c.web.service.UserPostCollectService;
import com.tzmb2c.web.service.WxpayOrderInfoService;

/**
 * 商家中心业务处理类.
 */
public class SellerService {
  @Autowired
  private ActivityGoodsService activityGoodsService;
  @Autowired
  private ProductSkuLinkService productSkuLinkService;
  @Autowired
  private HistoryService historyService;
  @Autowired
  private SpecialShowService specialShowService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private ManufacturerSettleService manufacturerSettleService;
  @Autowired
  private ProductCommentService productCommentService;
  @Autowired
  private ProductService productService;
  @Autowired
  private UserDeductionScoreService userDeductionScoreService;
  @Autowired
  private UserPostCollectService userPostCollectService;
  @Autowired
  private UserCircleFollowService userCircleFollowService;
  @Autowired
  private WxpayOrderInfoService wxpayOrderInfoService;
  @Autowired
  private AlipayOrderInfoService alipayOrderInfoService;
  @Autowired
  private GrouponService grouponService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private GrouponActivityService grouponActivityService;
  @Autowired
  private UserOrderRefundService userOrderRefundService;
  @Autowired
  private OrderDetailService orderDetailService;
  @Autowired
  private GrouponPushService grouponPushService;

  private static List<Integer> testUsers;

  private static Integer openGeekValid = 0;

  private static Integer openDrawValid = 1;

  public static Integer getOpenDrawValid() {
    return openDrawValid;
  }

  public static void setOpenDrawValid(Integer openDrawValid) {
    SellerService.openDrawValid = openDrawValid;
  }

  public static Integer getOpenGeekValid() {
    return openGeekValid;
  }

  public static void setOpenGeekValid(Integer openGeekValid) {
    SellerService.openGeekValid = openGeekValid;
  }

  public static List<Integer> getTestUsers() {
    if (testUsers == null) {
      getTestUserId();
    }
    return testUsers;
  }

  /**
   * 测试例子
   * 
   * @param a
   * @param b
   * @return
   */
  public int add(int a, int b) {
    return a + b;
  }

  /**
   * 根据产品id判断当前是否作活动中(无库存限制,活动时间判断)
   * 
   * @param productId
   * @return
   * @throws SQLException
   */
  public ActivityGoodsPojo isProductActivity(Long productId, Long activityId) throws SQLException {
    ActivityGoodsPojo activityGoodsPojo = isProductActivity(productId, activityId, null, 1);
    return activityGoodsPojo;
  }

  /**
   * 根据产品id判断当前是否作活动中(库存判断,活动时间判断)
   * 
   * @param productId
   * @return
   * @throws SQLException
   */
  public ActivityGoodsPojo isProductActivity(Long productId, Long activityId, Integer stockStatus)
      throws SQLException {
    ActivityGoodsPojo activityGoodsPojo = isProductActivity(productId, activityId, stockStatus, 1);
    return activityGoodsPojo;
  }

  /**
   * 根据产品id判断当前是否作活动中.
   * 
   * @param productId 产品ID
   * @param activityId 活动ID
   * @param stockStatus 库存判断,null不作判断
   * @param checkActive 活动进行判断,null不作判断
   * @return
   * @throws SQLException
   */
  public ActivityGoodsPojo isProductActivity(Long productId, Long activityId, Integer stockStatus,
      Integer checkActive) throws SQLException {
    ActivityGoodsPojo activityGoodsPojo =
        activityGoodsService
            .findActivityGoodsByPid(productId, activityId, stockStatus, checkActive);
    return activityGoodsPojo;
  }

  /**
   * 根据skuId判断产品sku信息
   * 
   * @param productId
   * @return
   * @throws SQLException
   */
  public ProductSkuLinkPojo getProducSku(Long productId, Integer skuLinkId) throws SQLException {
    ProductSkuLinkPojo productSkuLinkPojo = new ProductSkuLinkPojo();
    productSkuLinkPojo.setId(Long.valueOf(skuLinkId));
    productSkuLinkPojo.setProductId(productId);
    productSkuLinkPojo = productSkuLinkService.findProductSkuLink(productSkuLinkPojo);
    return productSkuLinkPojo;
  }

  // 计算商品折扣
  public static String calcDiscount(Double factPrice, Double sellPrice) {
    if (factPrice == null || factPrice == 0 || sellPrice == null || sellPrice == 0) {
      return "";
    }
    BigDecimal fact = new BigDecimal(String.valueOf(factPrice));
    BigDecimal sell = new BigDecimal(String.valueOf(sellPrice));
    BigDecimal discount =
        fact.divide(sell, 3, RoundingMode.HALF_UP).multiply(new BigDecimal(10))
            .setScale(1, RoundingMode.HALF_UP);
    if (discount.doubleValue() < 10) {
      return discount.toPlainString();
    } else {
      return "";
    }
  }

  /**
   * 计算折扣后金额差.
   * 
   * @param d1
   * @param d2 折扣（含折）
   * @return
   */
  public static double doubleDiscount(Double d1, Double d2) {
    if (d1 == null || d1 == 0 || d2 == null || d2 == 0) {
      return 0;
    }
    BigDecimal b1 = new BigDecimal(String.valueOf(d1));
    BigDecimal b2 = new BigDecimal(String.valueOf(d2));
    BigDecimal result = b1.multiply(b2).divide(new BigDecimal(10), 1, RoundingMode.HALF_UP);
    result = b1.subtract(result).setScale(1, RoundingMode.HALF_UP);
    return result.doubleValue();
  }

  /**
   * 判断商品是否活动商品.
   * 
   * @param pid 商品ID
   * @param type 活动类型：0秒杀1专题2场景3专场
   * @return
   * @throws SQLException
   */
  public ActivityGoodsPojo checkActivityGoodByPid(Long pid, Integer type) throws SQLException {
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("productId", pid);
    param.put("type", type);
    List<ActivityGoodsPojo> activityGoods = activityGoodsService.checkActivityGoodsByPid(param);
    return activityGoods != null && activityGoods.size() > 0 ? activityGoods.get(0) : null;
  }

  /**
   * 获取商品活动ID.
   * 
   * @param pid 商品ID
   * @param type 活动类型：0秒杀1专题2场景3专场
   * @return 返回当前进行中活动ID
   * @throws SQLException
   */
  public Long getProductActivityId(Long pid, Integer type) throws SQLException {
    ActivityGoodsPojo activityGood = checkActivityGoodByPid(pid, type);
    return activityGood != null ? activityGood.getTimeId() : 0;
  }

  /**
   * 计算偏远地区运费(>1kg,+15/kg,不足1kg按1kg算).
   * 
   * @param weight 重量
   * @return
   */
  public static double calcFare(double weight) {
    double fare = 0.0;
    if (weight > 1.0) {
      weight = Math.ceil(weight - 1.0);
      fare = weight * 15.0;
    }
    return fare;
  }

  /**
   * 专场满减设置转换成json.
   * 
   * @param om 满xx
   * @param m 减xx
   * @return
   */
  public String transDiscountToJson(String[] om, String[] m) {
    if (om == null || m == null || om.length == 0 || m.length == 0 || om.length != m.length) {
      return "";
    }
    int len = om.length;
    List<SpecialDiscountPojo> discounts = new ArrayList<SpecialDiscountPojo>();
    SpecialDiscountPojo discount = null;
    for (int i = 0; i < len; i++) {
      if (StringUtils.isEmpty(om[i]) || StringUtils.isEmpty(m[i])) {
        continue;
      }
      discount = new SpecialDiscountPojo();
      discount.setOm(StringUtils.trimToEmpty(om[i]));
      discount.setM(StringUtils.trimToEmpty(m[i]));
      discounts.add(discount);
    }
    JSONArray json = JSONArray.fromObject(discounts);
    return json.toString();
  }

  /**
   * 由json转换成满减对象.
   * 
   * @param discount json格式
   * @return
   */
  public List<SpecialDiscountPojo> transJsonToDiscount(String discount) {
    if (StringUtils.isEmpty(discount) || !(discount.startsWith("[{") && discount.endsWith("}]"))) {
      return null;
    }
    JSONArray json = JSONArray.fromObject(discount);
    List<SpecialDiscountPojo> discounts =
        JSONArray.toList(json, new SpecialDiscountPojo(), new JsonConfig());
    return discounts;
  }

  /**
   * 按满xx从大到小排序.
   * 
   * @param list
   */
  public void sortSpecialDiscounts(List<SpecialDiscountPojo> list) {
    if (list != null && list.size() > 1) {
      Collections.sort(list);
    }
  }

  /**
   * 由json转换成满减对象.
   * 
   * @param discount json格式
   * @return
   */
  public String transJsonToDiscountStr(Integer type, String discount) {
    List<SpecialDiscountPojo> discounts = transJsonToDiscount(discount);
    String discountStr = "";
    int count = discounts == null ? 0 : discounts.size();
    if (count > 0 && type > 0) {
      for (SpecialDiscountPojo specialDiscountPojo : discounts) {
        count--;
        if (type == 1) {
          discountStr +=
              "满" + specialDiscountPojo.getOm() + "元减" + specialDiscountPojo.getM() + "元";
        } else if (type == 2) {
          discountStr +=
              "满" + specialDiscountPojo.getOm() + "件享受" + specialDiscountPojo.getM() + "折";
        }
        if (count > 0) {
          discountStr += ",";
        }
      }
    }
    return discountStr;
  }

  /**
   * 判断活动是否开始
   * 
   * @param beginTime 开始时间
   * @param endTime 结束时间
   * @return
   */
  public int isActivityStart(Date beginTime, Date endTime) {
    // 0-未开始 ；1-进行中；2-已结束
    int flag = 0;
    if (beginTime == null || endTime == null) {
      return 2;
    }

    Date d1 = StringUtil.stringDate(StringUtil.dateString(new Date()));
    if (d1.compareTo(beginTime) < 0) {
      flag = 0;
    } else if (d1.compareTo(endTime) >= 0) {
      flag = 2;
    } else {
      flag = 1;
    }
    return flag;
  }

  /**
   * 查询商品是否在該時間段活动中
   * 
   * @param productId 产品ID
   * @param timeId 活动ID
   * @return
   * @throws SQLException
   */
  public ActivityGoodsPojo findActivityGoodsByProductId(Long productId, Long timeId)
      throws SQLException {
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("productId", productId);
    map.put("timeId", timeId);
    List<ActivityGoodsPojo> list = activityGoodsService.findActivityGoodsByProductId(map);
    return list.size() == 0 ? null : list.get(0);
  }

  /**
   * 判断活动产品状态.
   * 
   * @param activityGood
   * @return
   */
  public int checkActivityGoodStatus(ActivityGoodsPojo activityGood) {
    // 0-未开始，1-进行中，2-已结束
    int status = 2;
    // 活动产品状态为1并且产品上架
    if (activityGood != null && activityGood.getStatus() == 1 && activityGood.getProStatus() == 1) {
      String beginTime = activityGood.getBeginTime();
      String endTime = activityGood.getEndTime();
      if (StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(endTime)) {
        if (activityGood.getActivityType() != null && activityGood.getActivityType() == 0) {
          // 秒杀
          beginTime = activityGood.getActivityDateStr() + " " + beginTime + ":00";
          endTime = activityGood.getActivityDateStr() + " " + endTime + ":00";
        }
        Date beginDate = StringUtil.stringDate(beginTime);
        Date endDate = StringUtil.stringDate(endTime);
        Date current = new Date();
        if (current.compareTo(beginDate) < 0) {
          status = 0;
        } else if (current.compareTo(endDate) >= 0) {
          status = 2;
        } else {
          status = 1;
        }
      }
    }

    return status;
  }

  public void historyed(Long id, Long uid, Long activityId) {
    if (uid != null) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("userId", uid);
      map.put("type", 1);
      map.put("businessId", id);
      map.put("createBy", uid);
      map.put("activityId", activityId);
      map.put("createDate", new Date());
      int s = historyService.checkhistory(map);
      if (s == 0) {
        // 初始点击数为1
        map.put("hid", 1);
        historyService.inserthistory(map);
      } else {
        historyService.updatehistory(map);
      }
    }
  }

  /**
   * 根据产品id和活动id查询sku数量(判断是否有sku).
   * 
   * @param pid
   * @param activityId
   * @return
   * @throws SQLException
   */
  public int queryProductSkuCount(Long pid, Long activityId) throws SQLException {
    int type = 0;
    ProductSkuLinkPojo skuLinkQry = new ProductSkuLinkPojo();
    skuLinkQry.setProductId(pid);
    skuLinkQry.setStatus(1);
    skuLinkQry.setActivityId(activityId);
    if (activityId > 0) {
      type = 1;
    }
    skuLinkQry.setType(type);
    int skuCount = productSkuLinkService.getProductSkuLinkCount(skuLinkQry);

    return skuCount;
  }

  public Map<String, String> buildAlipayReq(String out_trade_no, String subject, String total_fee,
      String body, String show_url) {
    // 支付类型
    String payment_type = "1";
    // 必填，不能修改
    // 商户订单号 out_trade_no
    // 商户网站订单系统中唯一订单号，必填

    // 订单名称 subject
    // 必填

    // 付款金额 total_fee
    // 必填

    // 订单描述 body

    // 商品展示地址 show_url
    // 需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

    /*
     * try { AlipaySubmit.query_timestamp(); } catch (MalformedURLException e) {
     * e.printStackTrace(); } catch (DocumentException e) { e.printStackTrace(); } catch
     * (IOException e) { e.printStackTrace(); }
     */
    // 若要使用请调用类文件submit中的query_timestamp函数

    // 把请求参数打包成数组
    Map<String, String> sParaTemp = new HashMap<String, String>();
    sParaTemp.put("service", "mobile.securitypay.pay");
    sParaTemp.put("partner", AlipayConfig.partner);
    sParaTemp.put("seller_id", AlipayConfig.seller_email);
    sParaTemp.put("_input_charset", AlipayConfig.input_charset);
    sParaTemp.put("payment_type", payment_type);
    sParaTemp.put("notify_url", AlipayConfig.notify_url);
    // sParaTemp.put("return_url", AlipayConfig.return_url);
    sParaTemp.put("out_trade_no", out_trade_no);
    sParaTemp.put("subject", subject);
    sParaTemp.put("total_fee", total_fee);
    sParaTemp.put("body", body);
    sParaTemp.put("it_b_pay", "30m");
    sParaTemp.put("show_url", "m.alipay.com");
    // sParaTemp.put("show_url", show_url);
    // sParaTemp.put("anti_phishing_key", anti_phishing_key);
    // sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

    String sign = AlipaySubmit.buildRequestMysignMobile(sParaTemp);
    // 签名结果与签名方式加入请求提交参数组中
    sParaTemp.put("sign", sign);
    sParaTemp.put("sign_type", AlipayConfig.sign_type);
    return sParaTemp;
  }

  public Map<String, String> buildAlipayWapReq(String out_trade_no, String subject,
      String total_fee, String body, String show_url) {
    // 支付类型
    String payment_type = "1";
    // 必填，不能修改
    // 商户订单号 out_trade_no
    // 商户网站订单系统中唯一订单号，必填

    // 订单名称 subject
    // 必填

    // 付款金额 total_fee
    // 必填

    // 订单描述 body

    // 商品展示地址 show_url
    // 需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

    /*
     * try { AlipaySubmit.query_timestamp(); } catch (MalformedURLException e) {
     * e.printStackTrace(); } catch (DocumentException e) { e.printStackTrace(); } catch
     * (IOException e) { e.printStackTrace(); }
     */
    // 若要使用请调用类文件submit中的query_timestamp函数

    // 把请求参数打包成数组
    Map<String, String> sParaTemp = new HashMap<String, String>();
    sParaTemp.put("service", "alipay.wap.create.direct.pay.by.user");
    sParaTemp.put("partner", AlipayConfig.partner);
    sParaTemp.put("seller_id", AlipayConfig.seller_email);
    sParaTemp.put("_input_charset", AlipayConfig.input_charset);
    sParaTemp.put("payment_type", payment_type);
    sParaTemp.put("notify_url", AlipayConfig.notify_url);
    // sParaTemp.put("return_url", AlipayConfig.return_url);
    sParaTemp.put("out_trade_no", out_trade_no);
    sParaTemp.put("subject", subject);
    sParaTemp.put("total_fee", total_fee);
    sParaTemp.put("body", body);
    sParaTemp.put("it_b_pay", "30m");
    sParaTemp.put("show_url", "m.alipay.com");
    // sParaTemp.put("show_url", show_url);
    // sParaTemp.put("anti_phishing_key", anti_phishing_key);
    // sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

    String sign = AlipaySubmit.buildRequestMysign(sParaTemp);
    // 签名结果与签名方式加入请求提交参数组中
    sParaTemp.put("sign", sign);
    sParaTemp.put("sign_type", AlipayConfig.sign_type);
    return sParaTemp;
  }

  public Map<String, Object> buildWxpayReq(String openid, String out_trade_no, String product_id,
      String detail, String body, int total_fee, String tradeType) {
    // spBillCreateIP 订单生成的机器IP
    String spBillCreateIP = "";
    String notifyUrl = PropertiesHelper.getValue("wx_notify_url");;
    String appID = "";
    String mchID = "";
    if (Configure.JSAPI.equals(tradeType)) {
      // 公众号支付
      appID = Configure.getJsappID();
      mchID = Configure.getJsmchID();
    } else {
      appID = Configure.getAppid();
      mchID = Configure.getMchid();
    }
    PrePayReqData prePayReqData =
        new PrePayReqData(openid, "WEB", product_id, detail, body, out_trade_no, total_fee,
            notifyUrl, spBillCreateIP, tradeType, tradeType);
    prePayReqData.setOtherNull();
    String rsp = "";
    long costTimeStart = System.currentTimeMillis();
    Map<String, Object> sParaTemp = new HashMap<String, Object>();
    try {
      rsp = WXPay.requestPrePayService(prePayReqData);
      long costTimeEnd = System.currentTimeMillis();
      long totalTimeCost = costTimeEnd - costTimeStart;
      System.out.println(rsp);
      System.out.println("api请求总耗时：" + totalTimeCost + "ms");
      // 将从API返回的XML数据映射到Java对象
      PrePayResData prePayResData = (PrePayResData) Util.getObjectFromXML(rsp, PrePayResData.class);
      if ("SUCCESS".equals(prePayResData.getResult_code())) {
        long time = new java.sql.Timestamp(new Date().getTime()).getTime() / 1000;
        if (Configure.JSAPI.equals(tradeType)) {
          // 公众号支付
          sParaTemp.put("appId", appID);
          sParaTemp.put("nonceStr", MD5.MD5Encode(String.valueOf(time)).toUpperCase());
          sParaTemp.put("timeStamp", String.valueOf(time));
          sParaTemp.put("wxtrade", tradeType);
          sParaTemp.put("package", "prepay_id=" + prePayResData.getPrepay_id());
          sParaTemp.put("signType", "MD5");
          String sign = Signature.getSign(sParaTemp);
          // 清除交易方式
          sParaTemp.remove("wxtrade");
          sParaTemp.put("paySign", sign);
        } else {
          sParaTemp.put("appid", appID);
          sParaTemp.put("noncestr", MD5.MD5Encode(String.valueOf(time)).toUpperCase());
          sParaTemp.put("timestamp", String.valueOf(time));
          sParaTemp.put("wxtrade", tradeType);
          sParaTemp.put("partnerid", mchID);
          sParaTemp.put("package", "Sign=WXPay");
          sParaTemp.put("prepayid", prePayResData.getPrepay_id());

          String sign = Signature.getSign(sParaTemp);
          // 清除交易方式
          sParaTemp.remove("wxtrade");
          sParaTemp.put("sign", sign);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return sParaTemp;
  }

  /**
   * 微信退款查询接口(参数4选1，其他传null).
   * 
   * @param transactionID 交易ID
   * @param outTradeNo 商户订单号
   * @param outRefundNo 商户退款请求号
   * @param refundID 微信退款号
   * @return
   * @throw
   * @return RefundQueryResData
   */
  public void buildWxpayRefundQuery(String transactionID, String outTradeNo, String outRefundNo,
      String refundID, String tradeType) throws Exception {
    String rsp = "";
    String remark = "";
    RefundQueryReqData refundQueryReqData =
        new RefundQueryReqData(transactionID, outTradeNo, "WEB", outRefundNo, refundID, tradeType);
    long costTimeStart = System.currentTimeMillis();
    rsp = WXPay.requestRefundQueryService(refundQueryReqData);
    long costTimeEnd = System.currentTimeMillis();
    long totalTimeCost = costTimeEnd - costTimeStart;
    Util.log(rsp);
    Util.log("api请求总耗时：" + totalTimeCost + "ms");

    if (StringUtils.isNotEmpty(rsp)) {
      // 将从API返回的XML数据映射到Java对象
      RefundQueryResData refundQueryResData =
          (RefundQueryResData) Util.getObjectFromXML(rsp, RefundQueryResData.class);
      if (refundQueryResData == null || refundQueryResData.getReturn_code() == null) {
        Util.log("Case1:退款查询API请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问");
      } else if ("FAIL".equals(refundQueryResData.getReturn_code())) {
        Util.log("Case2:退款查询API系统返回失败，请检测Post给API的数据是否规范合法");
      } else if ("SUCCESS".equals(refundQueryResData.getReturn_code())
          && Signature.checkIsSignValidFromResponseStringWithTrade(rsp, tradeType)) {
        if ("SUCCESS".equals(refundQueryResData.getResult_code())) {
          // 退款查询成功
          List<RefundOrderData> refundOrderList = XMLParser.getRefundOrderList(rsp);
          if (refundOrderList != null && refundOrderList.size() > 0) {
            RefundOrderData refund = null;
            for (int i = 0; i < refundOrderList.size(); i++) {
              refund = refundOrderList.get(i);
              refundProcessForWX(refund.getRefundStatus(), refundQueryResData.getOut_trade_no(),
                  refundQueryResData.getTransaction_id(), refund.getOutRefundNo(),
                  String.valueOf(refund.getRefundFee()), "");
            }
          }
        } else {
          // 退款查询失败
          remark =
              "退款查询失败，错误码：" + refundQueryResData.getErr_code() + ";错误信息："
                  + refundQueryResData.getErr_code_des();
          refundProcessForWX("FAIL", outTradeNo, transactionID, outRefundNo, "", remark);
          Util.log(remark);
        }
      } else {
        Util.log("Case3:退款查询API返回的数据签名验证失败，有可能数据被篡改了");
      }
    }

  }

  /**
   * 退款结果处理.
   * 
   * @param refundStatus (SUCCESS/FAIL)
   * @param outTradeNo 商户订单号
   * @param transactioId 微信支付单号
   * @param outRefundNo 商户退款号
   * @param refundFee 退款实际金额
   * @param remark 错误备注
   * @throw
   * @return void
   * @throws Exception
   */
  public void refundProcessForWX(String refundStatus, String outTradeNo, String transactioId,
      String outRefundNo, String refundFee, String remark) throws Exception {
    WxpayOrderInfoPojo wxpay = wxpayOrderInfoService.findPayInfoByTradeNo(outTradeNo);
    // 已处理成功的不重复处理,CHANGE状态线下处理
    if (wxpay != null
        && ("FAIL".equals(wxpay.getRefundStatus()) || "PROCESSING".equals(wxpay.getRefundStatus()))) {
      Map<String, Object> param = new HashMap<String, Object>();
      int succ = 0;
      if ("SUCCESS".equals(refundStatus)) {
        // 退款成功处理
        param.put("refundStatus", refundStatus);
        param.put("refundFee", refundFee);
        param.put("tradeNo", transactioId);
        param.put("outRefundNumber", outRefundNo);
        param.put("outTradeNo", outTradeNo);
        // 1-支付信息处理
        succ = wxpayOrderInfoService.updatePayRefund(param);
        if (succ == 1) {
          if (wxpay.getSource() != null && wxpay.getSource() == 1) {
            UserOrderRefundPojo userOrderRefund =
                userOrderRefundService.getByOutRefundNo(outRefundNo);
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
            }
          } else {
            param.clear();
            param.put("isRefund", 2);
            param.put("oldRefund", 2);
            param.put("refundDate", StringUtil.dateString(new Date()));
            param.put("outTradeNo", outTradeNo);
            // 2-订单处理
            orderService.updateOrderRefund(param);
            // 退货成功微信通知
            /*
             * if (StringUtils.isNotBlank(outTradeNo)) { List<OrderPojo> orderList =
             * orderService.getOrderByoutTradeNo(outTradeNo); if (orderList != null &&
             * orderList.size() > 0) { if (orderList.get(0).getPayMethod() != null &&
             * (orderList.get(0).getPayMethod() == 2 || orderList.get(0).getPayMethod() == 8)) {
             * SysLoginPojo user = sysLoginService.findSysLoginById(orderList.get(0).getUserId());
             * if (user != null && StringUtils.isNotBlank(user.getOpenid())) {
             * grouponService.wxNotice(2, orderList.get(0).getId(), user.getOpenid(), orderList
             * .get(0).getUserId()); } else { Util.log("查询不到用户!"); } } else {
             * Util.log("查询不到微信支付的订单!"); } } else { Util.log("查询不到订单!"); } } else {
             * Util.log("支付宝订单号不能为空!"); }
             */

            try {
              Util.log("拼团失败退款！~");
              List<OrderPojo> orders = orderService.getOrderByoutTradeNo(outTradeNo);
              if (orders != null && orders.size() > 0) {
                grouponService.addUserOrderNotice(7, orders.get(0).getUserId(), orders.get(0)
                    .getId());
              } else {
                Util.log("查询不到商户订单号=" + outTradeNo + "的订单！~");
              }
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
      } else if ("FAIL".equals(refundStatus) || "CHANGE".equals(refundStatus)) {
        // 退款失败处理
        param.put("refundStatus", refundStatus);
        param.put("remarks", remark);
        param.put("tradeNo", transactioId);
        param.put("outRefundNumber", outRefundNo);
        param.put("outTradeNo", outTradeNo);
        // 1-支付信息处理
        succ = wxpayOrderInfoService.updatePayRefund(param);
        if (succ == 1) {
          if (wxpay.getSource() != null && wxpay.getSource() == 1) {
            UserOrderRefundPojo userOrderRefund =
                userOrderRefundService.getByOutRefundNo(outRefundNo);
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
            }
          } else {
            param.clear();
            param.put("isRefund", 3);
            param.put("oldRefund", 2);
            param.put("refundDate", StringUtil.dateString(new Date()));
            param.put("outTradeNo", outTradeNo);
            // 2-订单处理
            orderService.updateOrderRefund(param);
          }
        }
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
  public void refundApplyForWx(String applyStatus, String transcationId, String outTradeNo,
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
      int flag = wxpayOrderInfoService.updatePayRefund(param);
      if (flag > 0) {
        // 订单修改
        param.clear();
        param.put("isRefund", 1);
        param.put("outRefundNo", outRefundNo);
        param.put("oldRefund", 2);
        param.put("outTradeNo", outTradeNo);
        orderService.updateOrderRefund(param);
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
      int flag = wxpayOrderInfoService.updatePayRefund(param);
      if (flag > 0) {
        // 订单修改
        param.clear();
        param.put("isRefund", 3);
        param.put("outRefundNo", outRefundNo);
        param.put("oldRefund", 2);
        param.put("outTradeNo", outTradeNo);
        orderService.updateOrderRefund(param);
      }
    }
  }

  /**
   * 支付宝退款申请处理.
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
  public void refundApplyForAlipay(String applyStatus, String tradeNo, String outTradeNo,
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
      int flag = alipayOrderInfoService.updatePayRefund(param);
      if (flag > 0) {
        // 订单修改
        param.clear();
        param.put("isRefund", 1);
        param.put("outRefundNo", outRefundNo);
        param.put("oldRefund", 2);
        param.put("outTradeNo", outTradeNo);
        orderService.updateOrderRefund(param);
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
      int flag = alipayOrderInfoService.updatePayRefund(param);
      if (flag > 0) {
        // 订单修改
        param.clear();
        param.put("isRefund", 3);
        param.put("outRefundNo", outRefundNo);
        param.put("oldRefund", 2);
        param.put("outTradeNo", outTradeNo);
        orderService.updateOrderRefund(param);
      }
    }
  }

  /**
   * 转换金额为分单位
   * 
   * @param price
   * @return
   */
  public static String doubleToFee(Double price) {
    return new BigDecimal(String.valueOf(price)).multiply(new BigDecimal(100))
        .setScale(0, RoundingMode.HALF_UP).toPlainString();
  }

  /**
   * 计算年龄
   * 
   * @param birth
   * @return
   */
  public static String getAgeString(Date birth) {
    if (birth == null) {
      return "";
    }
    Calendar birthday = Calendar.getInstance();
    birthday.setTime(birth);
    Calendar now = Calendar.getInstance();
    int day = now.get(Calendar.DAY_OF_MONTH) - birthday.get(Calendar.DAY_OF_MONTH);
    int month = now.get(Calendar.MONTH) - birthday.get(Calendar.MONTH);
    int year = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
    // 按照减法原理，先day相减，不够向month借；然后month相减，不够向year借；最后year相减。
    if (birthday.compareTo(now) >= 0) {
      return "";
    }
    if (day < 0) {
      month--;
      now.add(Calendar.MONTH, -1);// 得到上一个月，用来得到上个月的天数。
      day = day + now.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    if (month < 0) {
      month = (month + 12) % 12;
      year--;
    }
    String age = "";
    if (year > 0) {
      age = year + "岁";
    } else if (month > 0) {
      age = month + "个月";
      if (day > 0) {
        age += day + "天";
      }
    } else if (day > 0) {
      age = day + "天";
    }
    return age;
  }

  /**
   * 专场满减满折处理.
   * 
   * @param allprice 订单商品价格
   * @param factprice 订单实际价格
   * @param cartNum 订单总购买数量
   * @param activityId 专题活动ID
   * @return
   * @throws SQLException
   */
  public OrderPojo specialDiscountProcess(double allprice, int cartNum, double factprice,
      Long activityId) throws SQLException {
    OrderPojo order = new OrderPojo();
    order.setFactPrice(factprice);
    order.setDiscountType(0);
    order.setDiscountContext("");
    order.setUsedPrice(0.00);
    if (activityId == null || activityId == 0) {
      return order;
    }
    SpecialShowPojo specialShow = specialShowService.findSpecialShowByActivityId(activityId);
    double discountPrice = factprice;
    if (specialShow != null && specialShow.getDiscountType() != null
        && specialShow.getDiscountType() != 0) {
      Integer type = specialShow.getDiscountType();
      String discount = specialShow.getDiscountContext();
      List<SpecialDiscountPojo> specialDiscountLt = transJsonToDiscount(discount);
      // 按从大到小排序
      sortSpecialDiscounts(specialDiscountLt);

      if (type != null && type == 1) {
        // 满减处理
        if (specialDiscountLt != null && specialDiscountLt.size() > 0) {
          for (SpecialDiscountPojo specialDiscountPojo : specialDiscountLt) {
            if (allprice >= Double.valueOf(specialDiscountPojo.getOm()).doubleValue()) {
              factprice = factprice - Double.valueOf(specialDiscountPojo.getM());
              order.setFactPrice(factprice);
              order.setDiscountType(type);
              order.setDiscountContext("满" + specialDiscountPojo.getOm() + "元减"
                  + specialDiscountPojo.getM() + "元");
              order.setUsedPrice(Double.valueOf(specialDiscountPojo.getM()));
              break;
            }
          }
        }

      } else if (type != null && type == 2) {
        // 满折处理
        if (specialDiscountLt != null && specialDiscountLt.size() > 0) {
          for (SpecialDiscountPojo specialDiscountPojo : specialDiscountLt) {
            if (cartNum >= Double.valueOf(specialDiscountPojo.getOm()).doubleValue()) {
              discountPrice = doubleDiscount(allprice, Double.valueOf(specialDiscountPojo.getM()));
              order.setFactPrice(factprice - discountPrice);
              order.setDiscountType(type);
              order.setDiscountContext("满" + specialDiscountPojo.getOm() + "件享受"
                  + specialDiscountPojo.getM() + "折");
              order.setUsedPrice(discountPrice);
              break;
            }
          }
        }
      }
    }
    return order;
  }

  /**
   * 上传文件.
   * 
   * @param file
   * @param s
   * @return
   * @throws IOException
   */
  public String uploadFile(File file, String s) throws IOException {
    String fileName = "";
    FileInputStream fis = null;
    FileOutputStream fos = null;
    File fs = null;
    Random random = new Random();
    int i = random.nextInt(1000);
    try {
      fis = new FileInputStream(file);
      String path = ServletActionContext.getRequest().getRealPath(s);
      System.out.println(">>>>>>" + path);
      fileName = StringUtil.getCurrentDateStrByfu() + i + ".jpg";
      fs = new File(path, fileName);
      fos = new FileOutputStream(fs);
      int len = 0;
      byte[] buffer = new byte[1024];

      while ((len = fis.read(buffer)) != -1) {
        fos.write(buffer, 0, len);
      }
      fos.flush();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fos != null) {
        fos.close();
      }
      if (fis != null) {
        fis.close();
      }
    }

    return fileName;
  }

  /**
   * 批量生成商家结算数据.
   * 
   * @throws Exception
   */
  public synchronized void batchGenSettleInfo() throws Exception {
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("isSettle", 0);
    param.put("orderType", 0);
    testUsers = getTestUsers();
    if (testUsers != null && testUsers.size() > 0) {
      param.put("notuserIds", testUsers);
    }
    List<OrderPojo> settleOrders = orderService.groupOrderSettleBySuserId(param);
    if (settleOrders != null && settleOrders.size() > 0) {
      Long suid = 0l;
      Long settleId = 0l;
      Double totalSettleAmt = 0.00;
      OrderPojo order = null;

      ManufacturerSettlePojo manufacturerSettlePojo = null;
      Date settleDate = new Date();

      for (OrderPojo orderPojo : settleOrders) {
        suid = orderPojo.getSuserId();
        totalSettleAmt = orderPojo.getSettleAmount();
        manufacturerSettlePojo = new ManufacturerSettlePojo();
        manufacturerSettlePojo.setUserId(suid);
        manufacturerSettlePojo.setSettleDate(settleDate);
        manufacturerSettlePojo.setSettleAmount(totalSettleAmt);
        manufacturerSettlePojo.setStatus(0);
        manufacturerSettlePojo.setCreateBy(1l);
        manufacturerSettlePojo.setUpdateBy(1l);
        manufacturerSettleService.insertManufacturerSettle(manufacturerSettlePojo);
        settleId = manufacturerSettlePojo.getId();
        order = new OrderPojo();
        order.setSuserId(suid);
        order.setSettleDate(settleDate);
        order.setSettleId(settleId);
        order.setNotuserIds(testUsers);
        // 按商家结算
        orderService.settleOrderByUserId(order);
      }
    }
  }

  /**
   * 对账单-导出订单结算详情
   * 
   * @return
   * @throws IOException
   */
  public void getOrderSetterExcel(ManufacturerSettlePojo manufacturerSettlePojo) throws IOException {
    if (manufacturerSettlePojo != null) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("settleId", manufacturerSettlePojo.getId());
      List<OrderPojo> orderPojos = orderService.orderAllList2(map);

      WritableWorkbook wwb = null;
      OutputStream ots = null;
      String filePath = null;
      String fileName = "订单结算详情.xls";

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
        sheet.addCell(new Label(0, 0, "订单编号"));
        sheet.addCell(new Label(1, 0, "订单日期"));
        sheet.addCell(new Label(2, 0, "用户呢称"));
        sheet.addCell(new Label(3, 0, "买家备注"));
        sheet.addCell(new Label(4, 0, "货到付款"));
        sheet.addCell(new Label(5, 0, "实付金额"));
        sheet.addCell(new Label(6, 0, "结算金额"));
        sheet.addCell(new Label(7, 0, "省份"));
        sheet.addCell(new Label(8, 0, "市"));
        sheet.addCell(new Label(9, 0, "区"));
        sheet.addCell(new Label(10, 0, "收货人"));
        sheet.addCell(new Label(11, 0, "收货手机"));
        sheet.addCell(new Label(12, 0, "收货地址"));
        sheet.addCell(new Label(13, 0, "买家运费"));
        sheet.addCell(new Label(14, 0, "快递公司"));
        sheet.addCell(new Label(15, 0, "物流单号"));
        sheet.addCell(new Label(16, 0, "收货人方式"));
        sheet.addCell(new Label(17, 0, "来源"));
        sheet.addCell(new Label(18, 0, "结算时间"));
        sheet.addCell(new Label(19, 0, "结算总额"));
        sheet.addCell(new Label(20, 0, "结算状态"));

        NumberFormat format = new NumberFormat("0.00");
        WritableCellFormat cellFormat = new WritableCellFormat(format);

        for (int i = 1, j = 0; i <= orderPojos.size(); i++, j++) {
          sheet.addCell(new Label(0, i, orderPojos.get(j).getOrderNo()));
          sheet.addCell(new Label(1, i, orderPojos.get(j).getCreateDateString()));
          sheet.addCell(new Label(2, i, orderPojos.get(j).getUserName()));
          sheet.addCell(new Label(3, i, orderPojos.get(j).getBuyerMessage()));
          sheet.addCell(new Label(4, i, ""));
          sheet.addCell(new Number(5, i, orderPojos.get(j).getFactPrice() == null ? 0.00
              : orderPojos.get(j).getFactPrice(), cellFormat));
          sheet.addCell(new Number(6, i, orderPojos.get(j).getPrice() == null ? 0.00 : orderPojos
              .get(j).getPrice(), cellFormat));
          sheet.addCell(new Label(7, i, orderPojos.get(j).getProvince()));
          sheet.addCell(new Label(8, i, orderPojos.get(j).getCity()));
          sheet.addCell(new Label(9, i, orderPojos.get(j).getArea()));
          sheet.addCell(new Label(10, i, orderPojos.get(j).getConsignee()));
          sheet.addCell(new Label(11, i, orderPojos.get(j).getConsigneePhone()));
          sheet.addCell(new Label(12, i, orderPojos.get(j).getConsigneeAddress()));
          sheet.addCell(new Label(13, i, orderPojos.get(j).getEspressPrice() + ""));
          sheet.addCell(new Label(14, i, orderPojos.get(j).getLogisticsName() == null ? ""
              : orderPojos.get(j).getLogisticsName()));
          sheet.addCell(new Label(15, i, orderPojos.get(j).getLogisticsNo() == null ? ""
              : orderPojos.get(j).getLogisticsNo() + ""));
          sheet.addCell(new Label(16, i, orderPojos.get(j).getConsigneeTypeName() == null ? ""
              : orderPojos.get(j).getConsigneeTypeName() + ""));
          sheet.addCell(new Label(17, i, orderPojos.get(j).getChannelName()));
          sheet.addCell(new Label(18, i, orderPojos.get(j).getSettleDateStr()));
          sheet.addCell(new Number(19, i, orderPojos.get(j).getSettleAmount() == null ? 0.00
              : orderPojos.get(j).getSettleAmount(), cellFormat));
          sheet.addCell(new Label(20, i, orderPojos.get(j).getSettleStatusName()));
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

  /**
   * 快递字典转化.
   * 
   * @param logisticsnm 快递名称
   * @return
   */
  public static String logisticsNameTrans(String logisticsnm) {
    String nm = "";
    if (StringUtils.isBlank(logisticsnm)) {
      return nm;
    }

    if (logisticsnm.contains("中通")) {
      nm = "zhongtong";
    } else if (logisticsnm.contains("顺丰")) {
      nm = "shunfeng";
    } else if (logisticsnm.contains("申通")) {
      nm = "shentong";
    } else if (logisticsnm.contains("圆通")) {
      nm = "yuantong";
    } else if (logisticsnm.contains("汇通") || logisticsnm.equals("百世快递")) {
      nm = "huitongkuaidi";
    } else if (logisticsnm.contains("天天")) {
      nm = "tiantian";
    } else if (logisticsnm.contains("韵达")) {
      nm = "yunda";
    } else if ("DHL".equalsIgnoreCase(logisticsnm)) {
      nm = "dhl";
    } else if (logisticsnm.contains("宅急送")) {
      nm = "zhaijisong";
    } else if (logisticsnm.contains("德邦")) {
      nm = "debangwuliu";
    } else if ("EMS国内".equalsIgnoreCase(logisticsnm) || logisticsnm.contains("ems")
        || logisticsnm.contains("EMS") || logisticsnm.equals("邮政")) {
      nm = "ems";
    } else if ("E邮宝".equalsIgnoreCase(logisticsnm)) {
      nm = "eyoubao";
    } else if (logisticsnm.contains("国通")) {
      nm = "guotongkuaidi";
    } else if (logisticsnm.contains("龙邦")) {
      nm = "longbangwuliu";
    } else if (logisticsnm.contains("联邦")) {
      nm = "lianbangkuaidi";
    } else if ("TNT".equalsIgnoreCase(logisticsnm)) {
      nm = "tnt";
    } else if (logisticsnm.contains("新邦")) {
      nm = "xinbangwuliu";
    } else if (logisticsnm.contains("中铁")) {
      nm = "zhongtiewuliu";
    } else if (logisticsnm.contains("中邮")) {
      nm = "zhongyouwuliu";
    } else if (logisticsnm.contains("优速")) {
      nm = "youshuwuliu";
    } else if (logisticsnm.contains("快捷")) {
      nm = "kuaijiesudi";
    } else if ("国内小包".equals(logisticsnm) || "邮政国内".equals(logisticsnm)
        || "邮政国内小包".equals(logisticsnm) || "邮政小包".equals(logisticsnm)
        || "邮政商务小包".equals(logisticsnm) || "邮政快递包裹".equals(logisticsnm)) {
      nm = "youzhengguonei";
    } else if (logisticsnm.contains("全峰")) {
      nm = "quanfengkuaidi";
    } else if (logisticsnm.contains("京东")) {
      nm = "jd";
    } else if (logisticsnm.contains("万象")) {
      nm = "wanxiangwuliu";
    } else {
      nm = logisticsnm;
    }
    return nm;
  }

  /**
   * 同步检查是否有活动库存(有SKU)
   * 
   * @param id
   * @param num
   * @return
   * @throws SQLException
   */
  public boolean updateActivitySkuStock(CartPojo cart) throws SQLException {
    if (cart.getSkuLinkId() == null || cart.getSkuLinkId() == 0) {
      return false;
    }
    ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
    productSkuLink.setId(Long.valueOf(cart.getSkuLinkId()));
    productSkuLink.setProductId(cart.getProductId());
    productSkuLink = productSkuLinkService.findProductSkuLink(productSkuLink);
    if (cart.getActivityId() == null || cart.getActivityId() <= 0) {
      if (productSkuLink != null && productSkuLink.getStock() > 0
          && cart.getNum() <= productSkuLink.getStock()) {
        // 提交订单时更新库存
        productSkuLink.setMinusStock(cart.getNum());
        int status = productSkuLinkService.updateProductSkuStock(productSkuLink);
        return status > 0 ? true : false;
      } else {
        // 活动结束/无活动
        return false;
      }
    } else {
      // 限时秒杀双重库存控制
      GrouponActivityPojo activity = grouponActivityService.getById(cart.getActivityId());
      if (activity != null && activity.getSurplusNum() > 0
          && cart.getNum() <= activity.getSurplusNum() && productSkuLink != null
          && productSkuLink.getStock() > 0 && cart.getNum() <= productSkuLink.getStock()) {
        // 提交订单时更新库存
        productSkuLink.setMinusStock(cart.getNum());
        productSkuLink.setActivityId(cart.getActivityId());
        int status = productSkuLinkService.updateActivityProductSkuStock(productSkuLink);
        return status > 0 ? true : false;
      } else {
        // 活动结束/无活动
        return false;
      }
    }
  }

  /**
   * 同步检查是否有活动库存（无SKU）
   * 
   * @param id
   * @param num
   * @return
   * @throws SQLException
   */
  public boolean updateActivityStock(CartPojo cart) throws SQLException {
    ActivityGoodsPojo activityGoods =
        this.isProductActivity(cart.getProductId(), cart.getActivityId());
    // 活动信息齐全且有库存
    if (activityGoods != null && activityGoods.getActivePrice() != null
        && activityGoods.getActivePrice().doubleValue() != 0
        && activityGoods.getActivityStock() != null && activityGoods.getActivityStock() > 0
        && cart.getNum() <= activityGoods.getActivityStock().intValue()) {
      // 提交订单时更新库存
      activityGoods.setMinusStock(cart.getNum());
      int stauts = activityGoodsService.updateActivityGoodsStock(activityGoods);
      return stauts > 0 ? true : false;
    } else {
      // 活动结束/无活动
      return false;
    }
  }

  /**
   * 获取测试订单用户ID
   * 
   * @throws Exception
   */
  public static void getTestUserId() {
    if (testUsers == null) {
      testUsers = new ArrayList<Integer>();
    } else {
      testUsers.clear();
    }
    // 过滤记事本中的userId
    String filePath = "";
    String fileName = "userId.txt";
    filePath =
        ServletActionContext.getServletContext().getRealPath("/temp") + File.separator + fileName;
    InputStreamReader read = null;
    BufferedReader bufferedReader = null;
    String lineTxt = null;
    try {
      File file = new File(filePath);
      if (file.isFile() && file.exists()) { // 判断文件是否存在
        read = new InputStreamReader(new FileInputStream(file), "utf-8");// 编码格式
        bufferedReader = new BufferedReader(read);
        while ((lineTxt = bufferedReader.readLine()) != null) {
          testUsers.add(Integer.parseInt(lineTxt));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (bufferedReader != null) {
          bufferedReader.close();
        }
        if (read != null) {
          read.close();
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * 根据评价分数返回评价状态
   * 
   * @return
   */
  public Integer returnValueByScore(int score) {
    int num = 1;
    if (score >= 0 && score <= 1) {
      num = 1;
    }
    if (score >= 2 && score <= 4) {
      num = 2;
    }
    if (score >= 4 && score <= 5) {
      num = 3;
    }
    return num;
  }

  /**
   * 根据productId返回productCommt
   */
  public String returnproductCommtByPid(long productId) {
    String productCommt = "";
    ProductCommentPojo productComment = new ProductCommentPojo();
    productComment.setProductId(productId);
    productComment.setStatus(1);
    List<ProductCommentPojo> productComments =
        productCommentService.productCommentAllListWeb(productComment, null);
    if (productComments.size() > 0) {
      double productCommts = 0;
      int count = 0;
      for (ProductCommentPojo pc : productComments) {
        if (pc.getScoreProduct() != null) {
          productCommts += pc.getScoreProduct();
          count++;
        }
      }
      if (count > 0) {
        BigDecimal bd = new BigDecimal(String.valueOf(productCommts / count));
        productCommt = bd.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString();
      }
    }
    return productCommt;
  }

  // /**
  // * 根据userBrandId返回productCommt
  // * @throws SQLException
  // */
  // public String returnproductCommtByBid(long userBrandId) throws SQLException{
  // String productCommt = "";
  // ProductPojo userBrandId2 = new ProductPojo();
  // userBrandId2.setUserBrandId(userBrandId);
  // userBrandId2.setStatus(1);
  // List<ProductPojo> products = productService.getProductAll(userBrandId2, null);
  //
  // if (products.size() != 0) {
  // double productCommts = 0;
  // for (ProductPojo pro : products) {
  // productCommts += Integer.valueOf(pro.getProductCommt());
  // }
  // BigDecimal bd = new BigDecimal(productCommts/products.size());
  // productCommt = String.valueOf(bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
  // }
  // return productCommt;
  // }

  /**
   * 根据userBrandId返回userBrandPojo(productCommt,deliverCommt,logisticsCommt)
   * 
   * @throws SQLException
   */
  public UserBrandPojo returnproductCommtByBid(long userBrandId) throws SQLException {
    UserBrandPojo userBrandPojo = productCommentService.returnproductCommtByBid(userBrandId);
    if (userBrandPojo != null) {
      BigDecimal bd = new BigDecimal(userBrandPojo.getProductCommt());
      userBrandPojo.setProductCommt(String.valueOf(bd.setScale(1, BigDecimal.ROUND_HALF_UP)
          .doubleValue()));
      bd = new BigDecimal(userBrandPojo.getDeliverCommt());
      userBrandPojo.setDeliverCommt(String.valueOf(bd.setScale(1, BigDecimal.ROUND_HALF_UP)
          .doubleValue()));
      bd = new BigDecimal(userBrandPojo.getLogisticsCommt());
      userBrandPojo.setLogisticsCommt(String.valueOf(bd.setScale(1, BigDecimal.ROUND_HALF_UP)
          .doubleValue()));
    }
    return userBrandPojo;
  }

  /**
   * 根据userId返回deductScore
   * 
   * @throws SQLException
   */
  public double returnDeductScoreAllByUid(long userId) throws SQLException {
    // BigDecimal bd = new BigDecimal();
    // bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

    double deductScoreAll = 0;
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("status", 1);
    map.put("suserId", userId);
    /*
     * userDeductionScorePojos = userDeductionScoreService.findUserDeductionScoreList(map); if
     * (userDeductionScorePojos.size() != 0) { for (UserDeductionScorePojo u :
     * userDeductionScorePojos) { deductScoreAll += u.getDeductScore(); } }
     */
    deductScoreAll = userDeductionScoreService.findUserDeductionScoreAll(map);
    return deductScoreAll;
  }

  /**
   * 计算真实年龄
   * 
   * @throws
   */
  public static double getAge(Date birthDay) throws Exception {
    // 获取当前系统时间
    Calendar cal = Calendar.getInstance();
    // 如果出生日期大于当前时间，则抛出异常
    if (cal.getTime().before(birthDay)) {
      return 0.00;
    }
    // 取出系统当前时间的年、月、日部分
    int yearNow = cal.get(Calendar.YEAR);
    int monthNow = cal.get(Calendar.MONTH);
    int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

    // 将日期设置为出生日期
    cal.setTime(birthDay);
    // 取出出生日期的年、月、日部分
    int yearBirth = cal.get(Calendar.YEAR);
    int monthBirth = cal.get(Calendar.MONTH);
    int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
    // 当前年份与出生年份相减，初步计算年龄
    int year = yearNow - yearBirth;
    int month = 0;
    // 当前月份与出生日期的月份相比，如果月份小于出生月份，则年龄上减1，表示不满多少周岁
    if (monthNow <= monthBirth) {
      // 如果月份相等，在比较日期，如果当前日，小于出生日，也减1，表示不满多少周岁
      if (monthNow == monthBirth) {
        if (dayOfMonthNow < dayOfMonthBirth) {
          year--;
          month = 11;
        }
      } else {
        year--;
        monthNow += 12;
        if (dayOfMonthNow < dayOfMonthBirth) {
          monthNow--;
        }
        month = monthNow - monthBirth;
      }
    } else {
      month = monthNow - monthBirth;
      if (dayOfMonthNow < dayOfMonthBirth) {
        month--;
      }
    }
    String age =
        String.valueOf(year).concat(".").concat(StringUtils.leftPad(String.valueOf(month), 2, '0'));
    return Double.valueOf(age).doubleValue();
  }

  /**
   * 计算成长线分数.
   * 
   * @param skillType 能力value
   * @param score 加的分数
   * @param userGrow 要更新的map
   * @throw
   * @return void
   */
  public static void calcGrowLine(int skillType, int score, Map<String, Object> userGrow) {
    if (skillType == 1) {
      userGrow.put("languageGrow", score);
    } else if (skillType == 2) {
      userGrow.put("actionSkill", score);
    } else if (skillType == 3) {
      userGrow.put("emotionSkill", score);
    } else if (skillType == 5) {
      userGrow.put("specialSkill", score);
    } else if (skillType == 6) {
      userGrow.put("cognitiveSkill", score);
    } else if (skillType == 7) {
      userGrow.put("sportSkill", score);
    } else if (skillType == 8) {
      userGrow.put("thinkSkill", score);
    } else if (skillType == 9) {
      userGrow.put("moveSkill", score);
    } else if (skillType == 10) {
      userGrow.put("learnSkill", score);
    } else if (skillType == 11) {
      userGrow.put("visionSkill", score);
    } else if (skillType == 12) {
      userGrow.put("listenSkill", score);
    } else if (skillType == 13) {
      userGrow.put("touchSkill", score);
    } else if (skillType == 14) {
      userGrow.put("coordinationSkill", score);
    } else if (skillType == 15) {
      userGrow.put("brainGrow", score);
    } else if (skillType == 16) {
      userGrow.put("exploringSkill", score);
    } else if (skillType == 17) {
      userGrow.put("languageSkill", score);
    }
  }


  /**
   * 验证网址Url
   * 
   * @param 待验证的字符串
   * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
   */
  public static boolean IsUrl(String str) {
    String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    Pattern patt = Pattern.compile(regex);
    Matcher matcher = patt.matcher(str);
    boolean isMatch = matcher.matches();
    if (!isMatch) {
      System.out.println("您输入的URL地址不正确");
      return false;
    } else {
      System.out.println("URL地址验证成功");
      return true;
    }
  }

  /**
   * 判断成长阶段.
   * 
   * @param babyBirthday 宝宝生日
   * @throws Exception
   * @return int 成长阶段对应值
   */
  public static int getBabyAgeType(String babyBirthday) {
    int ageType = 1;
    if (StringUtils.isNotBlank(babyBirthday)) {
      double age = 0.0;
      try {
        age = getAge(StringUtil.stringToDate(babyBirthday));
      } catch (Exception e) {
        e.printStackTrace();
      }
      // 查询年龄对应专题
      if (age >= 0.0 && age <= 0.06) {
        ageType = 1;
      } else if (age > 0.06 && age <= 1.0) {
        ageType = 2;
      } else if (age > 1.0 && age <= 3.0) {
        ageType = 3;
      } else if (age > 3.0 && age <= 6.0) {
        ageType = 4;
      } else if (age > 6.0 && age <= 12.0) {
        ageType = 5;
      } else if (age > 12.0 && age <= 16.0) {
        ageType = 6;
      } else if (age > 16.0) {
        ageType = 6;
      }
    }
    return ageType;
  }

  /**
   * 获取Image的宽高，默认返回1*1(算比例)
   * 
   * @param imgPath 图片地址（如：/upfiles/product/xxx.jpg）
   * @throw
   * @return Map<String,String> 返回宽高Map
   */
  public static Map<String, String> getImgWH(String imgPath) {
    Image img = null;
    Map<String, String> wh = new HashMap<String, String>();
    wh.put("width", "1");
    wh.put("height", "1");
    if (StringUtils.isBlank(imgPath)) {
      return wh;
    }
    try {
      // imgPath = ConstParam.URL + imgPath;
      img = Image.getInstance(new URL(imgPath));
      wh.put("width", String.valueOf(img.getWidth()));
      wh.put("height", String.valueOf(img.getHeight()));
    } catch (BadElementException e) {
      e.printStackTrace();
      wh.put("width", "1");
      wh.put("height", "1");
    } catch (MalformedURLException e) {
      e.printStackTrace();
      wh.put("width", "1");
      wh.put("height", "1");
    } catch (IOException e) {
      e.printStackTrace();
      wh.put("width", "1");
      wh.put("height", "1");
    }
    return wh;
  }

  /**
   * 获取笔记：是否收藏（0-未收藏；1-已收藏）
   * 
   * @param tid 帖子id
   * @param aid 作者id
   * @param type 1-主题；2-帖子
   * @param uid 用户id
   * @return
   * @throw
   * @return int
   */
  public int getUserCollectInfoCount(Long tid, Long aid, int type, Long uid) {
    int num = 0;
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("typeId", tid);
    option.put("authorId", aid);
    option.put("type", type);
    option.put("userId", uid);
    option.put("isCollect", 1);
    num = userPostCollectService.userPostCollectCount(option);
    return num;
  }

  /**
   * 获取用户：关注人数；粉丝人数；是否关注（0-未关注；1-已关注）
   * 
   * @param uid 查询用户关注人数
   * @param tid 查询用户粉丝人数
   * @param uid&tid 查uid是否关注tid
   * @param type 1-用户；2-圈子
   * @return
   * @throw
   * @return int
   */
  public int getUserFollowInfoCount(Long uid, Long tid, int type) {
    int num = 0;
    Map<String, Object> option = new HashMap<String, Object>();
    if (uid != null && uid > 0) {
      option.put("userId", uid);
    }
    if (tid != null && tid > 0) {
      option.put("typeId", tid);
    }
    option.put("type", type);
    option.put("isFollow", 1);
    num = userCircleFollowService.userCircleFollowCount(option);
    return num;
  }

  /**
   * 
   * 从List中随机出count个对象
   * 
   * @param socialCircleList
   * @param count
   * @return
   * @throw
   * @return List<SocialCirclePojo>
   */
  public List<SocialCirclePojo> randomList(List<SocialCirclePojo> socialCircleList, int count) {
    // 创建一个长度为count(count<=list)的数组,用于存随机数
    int[] a = new int[count];
    // 利于此数组产生随机数
    int[] b = new int[socialCircleList.size()];
    int size = socialCircleList.size();

    // 取样填充至数组a满
    for (int i = 0; i < count; i++) {
      int num = (int) (Math.random() * size); // [0,size)
      int where = -1;
      for (int j = 0; j < b.length; j++) {
        if (b[j] != -1) {
          where++;
          if (where == num) {
            b[j] = -1;
            a[i] = j;
          }
        }
      }
      size = size - 1;
    }
    // a填满后 将数据加载到rslist
    List<SocialCirclePojo> rslist = new ArrayList<SocialCirclePojo>();
    for (int i = 0; i < count; i++) {
      SocialCirclePojo df = socialCircleList.get(a[i]);
      rslist.add(df);
    }
    return rslist;
  }

  /**
   * 
   * 添加开团推送
   * 
   * @param productImage 商品图片
   * @param address 地址（省份）
   * @param userName 用户呢称
   * @param orderId 订单ID
   * @param attendId 参与ID
   * @throws SQLException
   * @throw
   * @return void
   */
  public void addGrouponPushApi(String productImage, String address, String userName, long orderId,
      long attendId) {
    GrouponPushPojo grouponPush = new GrouponPushPojo();
    grouponPush.setProductImage(productImage);
    grouponPush.setAddress(address);
    grouponPush.setName(userName);
    grouponPush.setOrderId(orderId);
    grouponPush.setAttendId(attendId);
    try {
      grouponPushService.add(grouponPush);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    // 极光推送
    messageJPush(grouponPush);
  }

  /**
   * 开团成功，极光推送.
   * 
   * @param push
   * @throw
   * @return void
   */
  public void messageJPush(GrouponPushPojo push) {
    Map<String, String> extras = new HashMap<String, String>();
    extras.put("groupID", StringUtil.checkVal(push.getAttendId()));
    extras.put("image", StringUtils.isBlank(push.getProductImage()) ? "" : ConstParam.URL
        + "/upfiles/product" + File.separator + push.getProductImage());
    extras.put("title", "新订单来自" + StringUtil.checkVal(push.getAddress()).replace("省", "") + "的【"
        + StringUtil.checkVal(push.getName()) + "】一起拼！");
    JPushUtils.customerMsgJPush("", extras);
  }

  // public static void main(String[] args) throws Exception {
  // String str =
  // "{\"title\":\"组团失败退款通知\",\"img\":\"/upfiles/product/20160929110349688601.jpg\",\"name\":\"任选一本 阳光宝贝安全知识我知道儿童校园安全故事书籍\",\"info\":\"您好，您有一笔退款\",\"price\":\"商品价格：10.00\",\"refundItem\":\"退款项目：拼团失败\",\"refundReason\":\"退款原因：拼团失败\",\"refundDate\":\"退款时间：2016-11-30 10:00:00\",\"orderNo\":\"订单号：1480490774185040042\",\"remark\":\"如有疑问，请联系客服！\",\"lname\":\"订单详情\",\"type\":\"1\",\"param\":\"\"}";
  // JSONObject o = JSONObject.fromObject(str);
  // System.out.println(noticeJsonToStr(o));
  // }

  /**
   * 将消息字段合并重组.
   * 
   * @param o 消息json
   * @return
   * @throw
   * @return String
   */
  public static String noticeJsonToStr(JSONObject o) {
    if (o == null) {
      return "{}";
    }
    String key = "";
    String cnt = "";
    StringBuffer content = new StringBuffer();
    Map<String, String> map = new HashMap<String, String>();
    JSONObject oo = null;
    try {
      for (Iterator iter = o.keys(); iter.hasNext();) {
        key = (String) iter.next();
        if ("title".equals(key) || "img".equals(key) || "name".equals(key) || "lname".equals(key)
            || "type".equals(key) || "param".equals(key)) {
          map.put(key, o.getString(key));
        } else {
          content.append(o.getString(key) + "\n");
        }
      }
      cnt = content.toString();
      if (cnt != null && cnt.length() > 1) {
        cnt = cnt.substring(0, cnt.length() - 1);
      }
      map.put("content", cnt);
      oo = JSONObject.fromObject(map);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return oo == null ? "{}" : oo.toString();
  }
}
