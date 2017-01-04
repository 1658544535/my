package com.tzmb2c.business.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Charge;
import com.tzmb2c.utils.PropertiesHelper;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.AdSpreadPojo;
import com.tzmb2c.web.pojo.BaiduLoginPojo;
import com.tzmb2c.web.pojo.CmbUserAgreePojo;
import com.tzmb2c.web.pojo.CouponOrderPojo;
import com.tzmb2c.web.pojo.CouponPojo;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserCouponPojo;
import com.tzmb2c.web.pojo.UserWalletLogPojo;
import com.tzmb2c.web.pojo.UserWalletPojo;
import com.tzmb2c.web.service.AdSpreadService;
import com.tzmb2c.web.service.BaiduLoginService;
import com.tzmb2c.web.service.CouponService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserWalletLogService;
import com.tzmb2c.web.service.UserWalletService;


/**
 * 钱包业务处理类.
 */
public class WalletService {
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private UserWalletService userWalletService;
  @Autowired
  private UserWalletLogService userWalletLogService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private CouponService couponService;
  @Autowired
  private OrderDetailService orderDetailService;
  @Autowired
  private ProductService productService;
  @Autowired
  private BaiduLoginService baiduLoginService;
  @Autowired
  private AdSpreadService adSpreadService;

  private static List<Integer> blackUsers;
  private static List<String> blackWords;

  public static List<Integer> getBlackUsers() {
    if (blackUsers == null) {
      getBlackUserId();
    }
    return blackUsers;
  }

  public static List<String> getBlackWords() {
    if (blackWords == null) {
      getBlackWord();
    }
    return blackWords;
  }

  /**
   * 获取分享奖金系数,默认为1.
   * 
   * @return
   * @throws Exception
   */
  public Double getShareRatio() throws Exception {
    String ratio = "1";
    List<SysDictPojo> ratioDic = sysDictService.getSysDictListByType("share_ratio");
    if (ratioDic != null && ratioDic.size() > 0) {
      ratio = ratioDic.get(0).getValue();
    }
    return Double.valueOf(ratio);
  }

  /**
   * 计算分享金额.
   * 
   * @param n 金额,默认传3.
   * @return
   * @throws Exception
   */
  public Double getShareAmount(int n) throws Exception {
    double ratio =
        new BigDecimal(String.valueOf(getShareRatio() * n)).setScale(1, RoundingMode.HALF_UP)
            .doubleValue();
    return ratio;
  }

  /**
   * 判断是否新用户.
   * 
   * @param uid 用户id
   * @param checkDate 在这个日期之后为新用户
   * @return
   * @throws SQLException
   */
  public int isNewWalletUser(Long uid, Date checkDate) throws SQLException {
    int type = 2;
    SysLoginPojo sysLoing = sysLoginService.findSysLoginById(uid);
    if (sysLoing != null) {
      Date createDate = sysLoing.getCreateDate();
      if (createDate != null && checkDate != null && checkDate.compareTo(createDate) <= 0
          && (sysLoing.getInviterId() == null || sysLoing.getInviterId() == 0)) {
        // 可被邀请
        type = 1;
      } else if (createDate != null && checkDate != null && checkDate.compareTo(createDate) > 0) {
        // 老用户
        type = 2;
      } else if (sysLoing.getInviterId() != null && sysLoing.getInviterId() > 0) {
        // 已被邀请
        type = 3;
      }
    }
    return type;
  }

  /**
   * 钱包使用.
   * 
   * @param uid 钱包用户id
   * @param factPrice 需要支付金额
   * @return
   * @throws Exception
   */
  public Double useWalletPay(Long uid, Double factPrice, List<Map<String, Object>> orders,
      String outTradeNo, int needFull) throws Exception {
    double surplus = factPrice;
    UserWalletPojo userWallet = userWalletService.findUserWalletByUserId(uid);
    if (userWallet != null && userWallet.getBalance() > 0 && surplus > 0) {
      UserWalletLogPojo userWalletLog = null;
      double balance = userWallet.getBalance();
      double curbal = userWallet.getBalance();
      Long versions = userWallet.getVersions();
      double consume = 0.00;
      Long oid = 0l;
      double price = 0.00;

      // 需要全额支付判断
      if (needFull == 1 && balance < surplus) {
        return surplus;
      }
      Map<String, Object> orderMap = new HashMap<String, Object>();
      if (balance - surplus >= 0) {
        // 全额支付
        balance = balance - surplus;
        surplus = 0.00;

        // 更新余额
        userWallet.setBalance(balance);
        userWallet.setUpdateBy(uid);
        userWallet.setVersions(versions);
        int status = userWalletService.updateUserWallet(userWallet);
        // 更新失败，返回未使用钱包的订单金额
        if (status == 0) {
          return factPrice;
        }

        for (Map<String, Object> map : orders) {
          oid = (Long) map.get("orderId");
          price = (Double) map.get("price");

          orderMap.put("id", oid);
          orderMap.put("walletPrice", price);
          // 钱包支付
          orderMap.put("payMethod", 4);
          orderMap.put("orderType", 2);
          orderService.updateorders(orderMap);

          // 插入钱包记录
          userWalletLog = new UserWalletLogPojo();
          userWalletLog.setUserId(uid);
          userWalletLog.setCurBal(curbal);
          userWalletLog.setType(1l);
          userWalletLog.setTradeAmt(price);
          userWalletLog.setSource(uid);
          userWalletLog.setOrderId(oid);
          userWalletLog.setOutTradeNo(outTradeNo);
          userWalletLog.setRemarks(ConstParam.BILL_ORDER);
          userWalletLog.setCreateBy(uid);
          userWalletLog.setUpdateBy(uid);
          userWalletLogService.insertUserWalletLog(userWalletLog);

          curbal = curbal - price;
        }
      } else {
        surplus = surplus - balance;
        // balance = 0.00;

        // 更新余额
        userWallet.setBalance(0.00);
        userWallet.setUpdateBy(uid);
        userWallet.setVersions(versions);
        int status = userWalletService.updateUserWallet(userWallet);

        // 更新失败，返回未使用钱包的订单金额
        if (status == 0) {
          return factPrice;
        }

        int count = orders.size();
        for (Map<String, Object> map : orders) {
          count--;
          oid = (Long) map.get("orderId");
          price = (Double) map.get("price");
          if (count == 0) {
            consume = curbal;
          } else {
            consume =
                new BigDecimal(String.valueOf(balance * (price / factPrice))).setScale(1,
                    RoundingMode.HALF_UP).doubleValue();
          }

          orderMap.put("id", oid);
          orderMap.put("walletPrice", consume);
          orderMap.put("orderType", 2);
          orderService.updateorders(orderMap);

          // 插入钱包记录
          userWalletLog = new UserWalletLogPojo();
          userWalletLog.setUserId(uid);
          userWalletLog.setCurBal(curbal);
          userWalletLog.setType(1l);
          userWalletLog.setTradeAmt(consume);
          userWalletLog.setSource(uid);
          userWalletLog.setOrderId(oid);
          userWalletLog.setOutTradeNo(outTradeNo);
          userWalletLog.setRemarks(ConstParam.BILL_ORDER);
          userWalletLog.setCreateBy(uid);
          userWalletLog.setUpdateBy(uid);
          userWalletLogService.insertUserWalletLog(userWalletLog);

          curbal = curbal - consume;
        }
      }

    }
    return surplus;
  }

  /**
   * 得到IP地址
   * */
  public String getIpAddr(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }

  /**
   * 赠送用户优惠券
   * 
   * @param uid 用户id
   * @param source 优惠券来源
   * @param type 优惠券类型
   * @param content 优惠信息
   * @param validSdate 有效起始
   * @param validDays 有效天数
   * @throws Exception
   */
  public void giveCoupon(Long uid, Integer source, Integer type, String content, Date validSdate,
      int validDays) throws Exception {
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
   * 付款成功处理.
   * 
   * @param outTradeNo 交易订单号
   * @return 用户id
   * @throws Exception
   */
  public Long processPaySuccess(String outTradeNo) throws Exception {
    // 改变订单支付状态
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("outTradeNo", outTradeNo);
    map.put("updateDate", new Date());
    orderService.orderPaySuccess(map);

    // 付款成功更新优惠券状态
    // 多张订单支付时
    List<OrderPojo> orders = orderService.getOrderByoutTradeNo(outTradeNo);
    long uid = 0;
    if (orders != null && orders.size() > 0) {
      uid = orders.get(0).getUserId();
    }
    if (orders != null && orders.size() > 1) {
      Map<String, Object> orderMap = new HashMap<String, Object>();
      List<CouponOrderPojo> couponOrders = null;
      CouponOrderPojo couponOrder = null;
      for (OrderPojo orderPojo : orders) {
        orderMap.put("orderId", orderPojo.getId());
        couponOrders = couponService.getcouponOrderList(orderMap);
        if (couponOrders != null && couponOrders.size() > 0 && couponOrders.get(0).getStatus() == 0) {
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
        }
      }
    }

    return uid;
  }

  /**
   * 付款失败处理.
   * 
   * @param outTradeNo 交易订单号
   * @throws Exception
   */
  public void processPayFailure(String outTradeNo) throws Exception {
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
        if (couponOrders != null && couponOrders.size() > 0 && 0 == couponOrders.get(0).getStatus()) {
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
  }

  /**
   * 取消订单，恢复钱包金额.
   * 
   * @param uid 用户ID
   * @param oid 订单ID
   * @param price 钱包金额
   * @throws Exception
   */
  public void recoverWalletPay(Long uid, Long oid, Double price) throws Exception {
    UserWalletPojo userWallet = userWalletService.findUserWalletByUserId(uid);
    double balance = userWallet.getBalance();
    Long versions = userWallet.getVersions();
    // 更新余额
    userWallet.setBalance(balance + price);
    userWallet.setUpdateBy(uid);
    userWallet.setVersions(versions);
    int status = userWalletService.updateUserWallet(userWallet);
    if (status == 0) {
      throw new RuntimeException("钱包余额更新失败！");
    }
    // 更新订单钱包使用金额
    Map<String, Object> orderMap = new HashMap<String, Object>();
    orderMap.put("id", oid);
    orderMap.put("walletPrice", 0.00);
    orderService.updateorders(orderMap);

    // 插入钱包记录
    UserWalletLogPojo userWalletLog = new UserWalletLogPojo();
    userWalletLog.setUserId(uid);
    userWalletLog.setCurBal(balance);
    userWalletLog.setType(0l);
    userWalletLog.setTradeAmt(price);
    userWalletLog.setSource(uid);
    userWalletLog.setOrderId(oid);
    userWalletLog.setOutTradeNo("cancel");
    userWalletLog.setRemarks(ConstParam.CANL_BILL_ORDER);
    userWalletLog.setCreateBy(uid);
    userWalletLog.setUpdateBy(uid);
    userWalletLogService.insertUserWalletLog(userWalletLog);
  }

  /**
   * 按条件向表baidu_login插入数据
   * 
   * @return
   * @throws SQLException
   */
  public void isInsertBaibuLogin(BaiduLoginPojo baiduLoginPojo) throws SQLException {
    if (baiduLoginPojo != null) {
      /*
       * Map<String, Object> map = new HashMap<String, Object>(); map.put("loginName",
       * baiduLoginPojo.getLoginName()); map.put("baiduId", baiduLoginPojo.getBaiduId());
       * map.put("loginDate", StringUtil.dateToString(new Date())); List<BaiduLoginPojo>
       * baiduLoginPojos = new ArrayList<BaiduLoginPojo>(); baiduLoginPojos =
       * baiduLoginService.findBaiduLoginList(map); if (baiduLoginPojos.size() == 0) {
       * baiduLoginPojo.setLoginTime(new Date());
       * baiduLoginService.insertBaiduLogin(baiduLoginPojo); }
       */
      baiduLoginPojo.setLoginTime(new Date());
      baiduLoginService.insertBaiduLogin(baiduLoginPojo);
    }
  }

  /**
   * 添加钱包余额/总金额 n 金额 uid 用户ID
   * 
   * @return
   * @throws SQLException
   */
  public boolean addWalletBalance1(Double n, Long uid) throws Exception {
    // 更新用户表
    // SysLoginPojo sysLogin = sysLoginService.findSysLoginById(uid);
    // if (sysLogin != null) {
    SysLoginPojo sysLoginpojo = new SysLoginPojo();
    sysLoginpojo.setBalanceAdd(n);
    sysLoginpojo.setTotalBalanceAdd(n);
    sysLoginpojo.setId(uid);
    // sysLoginpojo.setVersion(sysLogin.getVersion());
    int status = sysLoginService.updateSysLogin(sysLoginpojo);
    // if (status == 0) {
    // throw new RuntimeException("钱包余额更新失败！");
    // }
    // }
    return status == 0 ? false : true;
  }

  /**
   * 添加钱包余额/总金额 n 金额 uid 用户ID
   * 
   * @return
   * @throws SQLException
   */
  public void addWalletBalance(Double n, Long uid) throws Exception {
    UserWalletPojo userWalletPojo = userWalletService.findUserWalletByUserId(uid);
    if (userWalletPojo != null) {
      UserWalletPojo userWallet = new UserWalletPojo();
      userWallet.setBalance(userWalletPojo.getBalance() + n);
      userWallet.setTotalBalance(userWalletPojo.getTotalBalance() + n);
      userWallet.setId(userWalletPojo.getId());
      userWallet.setVersions(userWalletPojo.getVersions());
      int status = userWalletService.updateUserWallet(userWallet);
      if (status == 0) {
        throw new RuntimeException("钱包余额更新失败！");
      }
    }
  }

  /**
   * 字符隐藏.
   * 
   * @param str
   * @return
   */
  public static String enCodeString(String str) {
    if (str == null || "".equals(str)) {
      return str;
    }
    char[] aa = str.toCharArray();
    int len = aa.length;
    int secret = 2;
    if (len < 4) {
      secret = 0;
    }
    for (int i = 1; i < len - secret; i++) {
      aa[i] = '*';
    }
    return String.valueOf(aa);
  }

  /**
   * 字符隐藏.
   * 
   * @param str
   * @return
   */
  public static String enCodeString(String str, int startIndex, int nosecretLen) {
    if (str == null || "".equals(str)) {
      return str;
    }
    char[] aa = str.toCharArray();
    int len = aa.length;
    int secretLen = 0;
    if (nosecretLen > len) {
      nosecretLen = len;
    }
    if (startIndex > len) {
      startIndex = len;
    }
    if (nosecretLen - startIndex > 0) {
      secretLen = len - nosecretLen;
    } else {
      secretLen = len - startIndex;
    }
    for (int i = startIndex; i < startIndex + secretLen; i++) {
      aa[i] = '*';
    }
    return String.valueOf(aa);
  }

  /**
   * 生成分享邀请码
   * 
   * @return
   */
  public synchronized String genInviteCode() {
    String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Random random = new Random();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < 6; ++i) {
      int number = random.nextInt(36);
      sb.append(str.charAt(number));
    }
    return sb.toString();
  }

  /**
   * 获取测试订单用户ID
   * 
   * @throws Exception
   */
  public static void getBlackUserId() {
    if (blackUsers == null) {
      blackUsers = new ArrayList<Integer>();
    } else {
      blackUsers.clear();
    }
    // 过滤记事本中的userId
    String filePath = "";
    String fileName = "blacks.txt";
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
          blackUsers.add(Integer.parseInt(lineTxt));
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
   * 获取黑名单前缀.
   * 
   * @throws Exception
   */
  public static void getBlackWord() {
    if (blackWords == null) {
      blackWords = new ArrayList<String>();
    } else {
      blackWords.clear();
    }
    // 过滤记事本中的userId
    String filePath = "";
    String fileName = "blackwords.txt";
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
          blackWords.add(lineTxt);
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
   * 随机生成0或1.
   * 
   * @return
   */
  public int genRanCode(int n) {
    double ran =
        new BigDecimal(String.valueOf(Math.random())).setScale(2, RoundingMode.HALF_UP)
            .doubleValue();
    int flag = 0;
    // System.out.println(ran);
    if (ran * n <= 1) {
      flag = 1;
    } else {
      flag = 0;
    }
    return flag;
  }

  /**
   * 钱包检查规则
   * 
   * @param wallet 钱包总金额
   * @return
   */
  public int walletBlackRule(double wallet) {
    int flag = 1;
    if (wallet >= 500 && wallet < 800) {
      flag = genRanCode(3);
    } else if (wallet >= 800 && wallet < 1200) {
      flag = genRanCode(5);
    } else if (wallet >= 1200 && wallet < 5000) {
      flag = genRanCode(6);
    } else if (wallet >= 5000 && wallet < 10000) {
      flag = genRanCode(10);
    } else if (wallet >= 10000) {
      flag = 0;
    }
    return flag;
  }

  /**
   * 有米广告回调校验.
   * 
   * @param imei 设备标识
   * @param channel 渠道1-ios 2-android
   * @param uid 注册用户id
   */
  public void checkAdFromYoumi(String imei, Integer channel, Long uid) {
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("imei", imei);
    param.put("channel", channel);
    param.put("adType", 1);
    param.put("status", 0);
    AdSpreadPojo ad = null;

    String result = "";
    try {
      ad = adSpreadService.findAdSpreadByImei(param);
      if (ad != null && StringUtils.isNotBlank(ad.getCallbackUrl())) {
        result = visitUrl(ad.getCallbackUrl());
        if (StringUtils.isNotEmpty(result)) {
          JSONObject json = JSONObject.fromObject(result);
          if (channel == 1 && "0".equals(String.valueOf(json.get("c")))) {
            ad.setStatus(1);
          } else if (channel == 2 && "true".equals(String.valueOf(json.get("success")))) {
            ad.setStatus(1);
          }
          ad.setResult(json.toString());
        }

        if (uid != null && uid != 0) {
          ad.setUserId(uid);
        }

        adSpreadService.updateAdSpread(ad);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 访问某个URL.
   * 
   * @param urlvalue
   * @return
   */
  public static String visitUrl(String urlvalue) {
    String inputLine = "";
    BufferedReader in = null;
    try {
      URL url = new URL(urlvalue);
      HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
      in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
      inputLine = in.readLine().toString();
    } catch (Exception e) {
      e.printStackTrace();
      inputLine = "";
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    return inputLine;
  }

  /**
   * 订单支付金额满xx送xx
   * 
   * @param orderPirce 支付金额
   * @throws Exception
   */
  public void giveUserCouponByOrderPay(double orderPirce, Long uid) throws Exception {
    String startstr = "2016-04-18 00:00:00";
    String endstr = "2016-04-24 23:59:59";
    Date today = new Date();
    if (StringUtil.stringDate(startstr).compareTo(today) <= 0
        && StringUtil.stringDate(endstr).compareTo(today) >= 0) {
      // 满40元赠送
      if (orderPirce >= 40.0) {
        Map<String, Object> param = new HashMap<String, Object>();
        Date start = StringUtil.stringDate("2016-04-18 00:00:00");
        // 满169减20
        param.put("om", "169");
        param.put("m", "20");
        JSONObject json = JSONObject.fromObject(param);
        giveCoupon(uid, 3, 1, json.toString(), start, 7);
        // 满169减20
        param.put("om", "169");
        param.put("m", "20");
        json = JSONObject.fromObject(param);
        giveCoupon(uid, 3, 1, json.toString(), start, 7);
      }
    }
  }

  /**
   * 订单支付金额满xx送xx时间判断
   * 
   * @param tradeStatus 支付状态
   * @param uid 用户ID
   * @param orderPrice 支付金额
   * @throws Exception
   */
  public void giveUserCouponByOrderPayWithDate(String tradeStatus, long uid, Double orderPrice)
      throws Exception {
    // String start = "2016-03-07 00:00:00";
    // String end = "2016-03-13 23:59:59";
    // Date today = new Date();
    // if (StringUtil.stringDate(start).compareTo(today) <= 0 &&
    // StringUtil.stringDate(end).compareTo(today) >= 0) {
    if ("TRADE_FINISHED".equals(tradeStatus) || "TRADE_SUCCESS".equals(tradeStatus)) {
      giveUserCouponByOrderPay(orderPrice, uid);
    }
    // }
  }


  /**
   * ping++创建charge
   * 
   * @param out_trade_no 账单号
   * @param subject 主题
   * @param amount 总价
   * @param body 说明
   * @param ip ip地址
   * @param channel 支付方式
   * @return charge 凭证
   */

  public Charge pingPayReq(String out_trade_no, String subject, int amount, String body, String ip,
      String channel, CmbUserAgreePojo cmbUserAgreePojo) {

    // 把请求参数打包成数组
    Charge charge = null;
    Pingpp.apiKey = PropertiesHelper.getValue("Pingpp.apiKey");
    String appId = PropertiesHelper.getValue("appId");
    String result_url = PropertiesHelper.getValue("result_url");
    Map<String, Object> chargeParams = new HashMap<String, Object>();
    // 某些渠道需要添加extra参数，具体参数详见接口文档
    if (channel.equals("upacp_wap")) {
      Map<String, Object> upacpParams = new HashMap<String, Object>();
      upacpParams.put("result_url", result_url);
      chargeParams.put("extra", upacpParams);
    }
    if (channel.equals("cmb_wallet") && cmbUserAgreePojo != null) {
      Map<String, Object> upacpParams = new HashMap<String, Object>();
      upacpParams.put("result_url", result_url);
      upacpParams.put("p_no", cmbUserAgreePojo.getCustArgno());
      upacpParams.put("seq", cmbUserAgreePojo.getReqSerial());
      upacpParams.put("m_uid", cmbUserAgreePojo.getUserId());
      upacpParams.put("mobile", cmbUserAgreePojo.getMobile());
      chargeParams.put("extra", upacpParams);
    }
    chargeParams.put("amount", amount);
    chargeParams.put("currency", "cny");
    chargeParams.put("subject", subject);
    chargeParams.put("body", body);
    chargeParams.put("order_no", out_trade_no);
    chargeParams.put("channel", channel);
    chargeParams.put("client_ip", ip);
    Map<String, String> app = new HashMap<String, String>();
    app.put("id", appId);
    chargeParams.put("app", app);

    try {
      charge = Charge.create(chargeParams);
    } catch (AuthenticationException e) {
      e.printStackTrace();
    } catch (InvalidRequestException e) {
      e.printStackTrace();
    } catch (APIConnectionException e) {
      e.printStackTrace();
    } catch (APIException e) {
      e.printStackTrace();
    } catch (ChannelException e) {
      e.printStackTrace();
    }
    return charge;
  }



}
