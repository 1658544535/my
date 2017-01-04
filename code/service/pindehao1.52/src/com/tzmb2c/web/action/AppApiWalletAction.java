package com.tzmb2c.web.action;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.util.UtilDate;
import com.geetest.sdk.GeetestUtil;
import com.tencent.common.MD5;
import com.tzmb2c.business.service.ConstParam;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.business.service.WalletService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.RandomUtils;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityGoodsPojo;
import com.tzmb2c.web.pojo.AgencyPojo;
import com.tzmb2c.web.pojo.AlipayOrderInfoPojo;
import com.tzmb2c.web.pojo.BaiduLoginPojo;
import com.tzmb2c.web.pojo.CartPojo;
import com.tzmb2c.web.pojo.CouponOrderPojo;
import com.tzmb2c.web.pojo.DeliveryAddressPojo;
import com.tzmb2c.web.pojo.HistoryPojo;
import com.tzmb2c.web.pojo.LoginRecPojo;
import com.tzmb2c.web.pojo.ManufacturerPojo;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.SpecialShowPojo;
import com.tzmb2c.web.pojo.SplashScreenPojo;
import com.tzmb2c.web.pojo.SysAreaPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserCollectPojo;
import com.tzmb2c.web.pojo.UserConsumerCollectPojo;
import com.tzmb2c.web.pojo.UserCouponPojo;
import com.tzmb2c.web.pojo.UserInfoPojo;
import com.tzmb2c.web.pojo.UserOrderRefundPojo;
import com.tzmb2c.web.pojo.UserVerifyPojo;
import com.tzmb2c.web.pojo.UserWalletLogPojo;
import com.tzmb2c.web.pojo.UserWalletPojo;
import com.tzmb2c.web.pojo.WxpayOrderInfoPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.ActivityProductService;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.ActivityTitleService;
import com.tzmb2c.web.service.AgencyService;
import com.tzmb2c.web.service.AlipayOrderInfoService;
import com.tzmb2c.web.service.BaiduLoginService;
import com.tzmb2c.web.service.CartService;
import com.tzmb2c.web.service.ConsumerService;
import com.tzmb2c.web.service.CouponService;
import com.tzmb2c.web.service.DeliveryAddressService;
import com.tzmb2c.web.service.HistoryService;
import com.tzmb2c.web.service.LoginRecService;
import com.tzmb2c.web.service.LoginService;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.NavigationService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderRefundService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.OrderShipService;
import com.tzmb2c.web.service.ProductCommentService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SpecialProductService;
import com.tzmb2c.web.service.SpecialPushService;
import com.tzmb2c.web.service.SpecialShowService;
import com.tzmb2c.web.service.SplashScreenService;
import com.tzmb2c.web.service.SysAreaService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserBrandService;
import com.tzmb2c.web.service.UserCollectService;
import com.tzmb2c.web.service.UserConsumerCollectService;
import com.tzmb2c.web.service.UserInfoService;
import com.tzmb2c.web.service.UserOrderRefundService;
import com.tzmb2c.web.service.UserScoreService;
import com.tzmb2c.web.service.UserShopCollectService;
import com.tzmb2c.web.service.UserSpecialCollectService;
import com.tzmb2c.web.service.UserVerifyService;
import com.tzmb2c.web.service.UserWalletLogService;
import com.tzmb2c.web.service.UserWalletService;
import com.tzmb2c.web.service.WxpayOrderInfoService;

public class AppApiWalletAction extends SuperAction {
  private static final long serialVersionUID = 1L;

  @Autowired
  private ProductService productService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private ShopService shopService;
  @Autowired
  private CartService cartService;
  @Autowired
  private ActivityTimeService activityTimeService;
  @Autowired
  private ProductSkuLinkService productSkuLinkService;
  @Autowired
  private SpecialShowService specialShowService;
  @Autowired
  private SpecialPushService specialPushService;
  @Autowired
  private SpecialProductService specialProductService;
  @Autowired
  private SellerService sellerService;
  @Autowired
  private NavigationService navigationService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  @Autowired
  private ActivityTitleService activityTitleService;
  @Autowired
  private ActivityProductService activityProductService;
  @Autowired
  private UserCollectService userCollectService;
  @Autowired
  private UserShopCollectService userShopCollectService;
  @Autowired
  private UserConsumerCollectService userConsumerCollectService;
  @Autowired
  private HistoryService historyService;
  @Autowired
  private UserSpecialCollectService userSpecialCollectService;
  @Autowired
  private ProductCommentService productCommentService;
  @Autowired
  private ProductTypeService productTypeService;
  @Autowired
  private DeliveryAddressService addressService;
  @Autowired
  private SysAreaService sysAreaService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private OrderDetailService orderDetailService;
  @Autowired
  private UserOrderRefundService userOrderRefundService;
  @Autowired
  private OrderShipService orderShipService;
  @Autowired
  private CouponService couponService;
  @Autowired
  private AgencyService agencyService;
  @Autowired
  private AlipayOrderInfoService alipayOrderInfoService;
  @Autowired
  private WxpayOrderInfoService wxpayOrderInfoService;
  @Autowired
  private UserBrandService userBrandService;
  @Autowired
  private OrderRefundService orderRefundService;
  @Autowired
  private UserVerifyService userVerifyService;
  @Autowired
  private LoginService loginService;
  @Autowired
  private LoginRecService loginRecService;
  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private UserScoreService userScoreService;
  @Autowired
  private ManufacturerService manufacturerService;
  @Autowired
  private ConsumerService consumerService;
  @Autowired
  private WalletService walletService;
  @Autowired
  private UserWalletService userWalletService;
  @Autowired
  private UserWalletLogService userWalletLogService;
  @Autowired
  private BaiduLoginService baiduLoginService;
  @Autowired
  private SplashScreenService splashScreenService;
  @Autowired
  private DeliveryAddressService deliveryAddressService;


  private UserCollectPojo userCollect;
  private UserConsumerCollectPojo userConsumerCollectPojo;
  private HistoryPojo historyPojo;
  private Integer pageNo;

  private OrderPojo order, orderPojo;
  private Integer orderStatus;
  private Integer oid;

  private String buyer_message;
  private Integer consigneeType;// 送货方式
  private Long addressId;
  /**
   * 1-支付宝 2-微信 3-货到付款 4-钱包（全额）
   */
  private Integer payMethod;
  private String messages;// 店铺留言
  private String couponNo;// 优惠券码
  private String outTradeNo;
  private AgencyPojo agencyPojo;
  private Integer couponNum;// 未使用的代金券数量
  /**
   * 商品id
   */
  private Long pid;
  /**
   * 商品id
   */
  private Long productId;
  /**
   * 会员id
   */
  private Long uid;
  /**
   * 数量
   */
  private Integer num;
  /**
   * sku关联id
   */
  private Integer skuLinkId;
  /**
   * 活动类型
   */
  private Long activityId;
  /**
   * 类目(1/一级类目;2/二级类目)
   */
  private String type;
  private Long id;// 级别ID
  /**
   * 购物车ID串
   */
  private String[] cids;
  private String sorting;// (1为热门，2为最新，3为价格从高到低，4为价格从低到高,7为销量)
  /**
   * 产品/店铺/专场ID
   */
  private Integer favSenId;
  /**
   * 产品/店铺/分销产品库/专场
   */
  private Integer favType;
  private String productNo;// 产品货号
  private String name;// 搜索内容
  private String brindId;// 品牌id
  private String key;// 搜索关键字（产品名称或产品型号）
  private String display;// 是否新品：0=》否；1=》是
  private String subTypeId;// （0为所有；1为遥控/电动玩具；2为早教/y音乐玩具
  // ；3为过家家玩具；4为童车玩具；5为益智玩具；6为其他玩具）
  private String typeId;// 小分类
  private Integer source;// 设置排序1销量降序、11销量升序、2价格降序、22价格升序、3点击率降序、33点击率升序
  private Long sid;
  private File file, file1, file2;
  private Integer refundType;
  private String refundReason;

  private String phone;// 手机号码
  private String openid;// 微信
  private String sinaid;// 新浪
  private String captcha;// 验证码
  private String pass;// 密码
  private String baidu_uid;// 百度id
  private String regChannel;
  private String inviteCode;// 邀请码
  /**
   * 0-不使用 1-使用
   */
  private Integer wallet;
  private String orderNo;// 订单号
  private String imei;// 设备标识

  public String getImei() {
    return imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public Integer getWallet() {
    return wallet;
  }

  public void setWallet(Integer wallet) {
    this.wallet = wallet;
  }

  public String getInviteCode() {
    return inviteCode;
  }

  public void setInviteCode(String inviteCode) {
    this.inviteCode = inviteCode;
  }

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public String getSinaid() {
    return sinaid;
  }

  public void setSinaid(String sinaid) {
    this.sinaid = sinaid;
  }

  public String getCaptcha() {
    return captcha;
  }

  public void setCaptcha(String captcha) {
    this.captcha = captcha;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public String getBaidu_uid() {
    return baidu_uid;
  }

  public void setBaidu_uid(String baidu_uid) {
    this.baidu_uid = baidu_uid;
  }

  public String getRegChannel() {
    return regChannel;
  }

  public void setRegChannel(String regChannel) {
    this.regChannel = regChannel;
  }

  public AgencyPojo getAgencyPojo() {
    return agencyPojo;
  }

  public void setAgencyPojo(AgencyPojo agencyPojo) {
    this.agencyPojo = agencyPojo;
  }

  public Integer getCouponNum() {
    return couponNum;
  }

  public void setCouponNum(Integer couponNum) {
    this.couponNum = couponNum;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getBuyer_message() {
    return buyer_message;
  }

  public void setBuyer_message(String buyer_message) {
    this.buyer_message = buyer_message;
  }

  public Integer getConsigneeType() {
    return consigneeType;
  }

  public void setConsigneeType(Integer consigneeType) {
    this.consigneeType = consigneeType;
  }

  public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }

  public Integer getPayMethod() {
    return payMethod;
  }

  public void setPayMethod(Integer payMethod) {
    this.payMethod = payMethod;
  }

  public String getMessages() {
    return messages;
  }

  public void setMessages(String messages) {
    this.messages = messages;
  }

  public String getCouponNo() {
    return couponNo;
  }

  public void setCouponNo(String couponNo) {
    this.couponNo = couponNo;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Integer getFavSenId() {
    return favSenId;
  }

  public void setFavSenId(Integer favSenId) {
    this.favSenId = favSenId;
  }

  public Long getUid() {
    return uid;
  }

  public String getTypeId() {
    return typeId;
  }

  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }

  public String getSubTypeId() {
    return subTypeId;
  }

  public void setSubTypeId(String subTypeId) {
    this.subTypeId = subTypeId;
  }

  public String getDisplay() {
    return display;
  }

  public void setDisplay(String display) {
    this.display = display;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getBrindId() {
    return brindId;
  }

  public void setBrindId(String brindId) {
    this.brindId = brindId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getProductNo() {
    return productNo;
  }

  public void setProductNo(String productNo) {
    this.productNo = productNo;
  }

  public String getSorting() {
    return sorting;
  }

  public void setSorting(String sorting) {
    this.sorting = sorting;
  }

  public Integer getPageNo() {
    return pageNo;
  }

  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
  }

  public Integer getFavType() {
    return favType;
  }

  public void setFavType(Integer favType) {
    this.favType = favType;
  }

  public HistoryPojo getHistoryPojo() {
    return historyPojo;
  }

  public void setHistoryPojo(HistoryPojo historyPojo) {
    this.historyPojo = historyPojo;
  }

  public UserConsumerCollectPojo getUserConsumerCollectPojo() {
    return userConsumerCollectPojo;
  }

  public void setUserConsumerCollectPojo(UserConsumerCollectPojo userConsumerCollectPojo) {
    this.userConsumerCollectPojo = userConsumerCollectPojo;
  }

  public UserCollectPojo getUserCollect() {
    return userCollect;
  }

  public void setUserCollect(UserCollectPojo userCollect) {
    this.userCollect = userCollect;
  }

  public String[] getCids() {
    return cids;
  }

  public void setCids(String[] cids) {
    this.cids = cids;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public Integer getSkuLinkId() {
    return skuLinkId;
  }

  public void setSkuLinkId(Integer skuLinkId) {
    this.skuLinkId = skuLinkId;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public Long getPid() {
    return pid;
  }

  public void setPid(Long pid) {
    this.pid = pid;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OrderPojo getOrder() {
    return order;
  }

  public void setOrder(OrderPojo order) {
    this.order = order;
  }

  public OrderPojo getOrderPojo() {
    return orderPojo;
  }

  public void setOrderPojo(OrderPojo orderPojo) {
    this.orderPojo = orderPojo;
  }

  public Integer getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(Integer orderStatus) {
    this.orderStatus = orderStatus;
  }

  public Integer getOid() {
    return oid;
  }

  public void setOid(Integer oid) {
    this.oid = oid;
  }

  public Integer getSource() {
    return source;
  }

  public void setSource(Integer source) {
    this.source = source;
  }

  public Long getSid() {
    return sid;
  }

  public void setSid(Long sid) {
    this.sid = sid;
  }

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public File getFile1() {
    return file1;
  }

  public void setFile1(File file1) {
    this.file1 = file1;
  }

  public File getFile2() {
    return file2;
  }

  public void setFile2(File file2) {
    this.file2 = file2;
  }

  public Integer getRefundType() {
    return refundType;
  }

  public void setRefundType(Integer refundType) {
    this.refundType = refundType;
  }

  public String getRefundReason() {
    return refundReason;
  }

  public void setRefundReason(String refundReason) {
    this.refundReason = refundReason;
  }



  /**
   * 用户钱包明细查询.
   * 
   * @return
   * @throws SQLException
   */
  public String userWelletDetail() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    HashMap<String, Object> detail = new HashMap<String, Object>();
    List<HashMap<String, Object>> details = new ArrayList<HashMap<String, Object>>();
    DecimalFormat df = new DecimalFormat("#.##");
    if (uid != null) {
      page = new Pager();
      if (pageNo == null || pageNo.equals("")) {
        // page = new Pager();
        // page.setPageNo(1);
        pageNo = 1;
      }
      page.setPageNo(pageNo);

      Map<String, Object> option = new HashMap<String, Object>();
      option.put("userId", uid);
      option.put("pageSize", 10);
      option.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      List<UserWalletLogPojo> userWalletLogs = userWalletLogService.findUserWalletLogList(option);

      // UserWalletPojo userWallet = userWalletService.findUserWalletByUserId(uid);
      // if (userWallet != null) {
      // map1.put("balance",
      // userWallet.getBalance() == null ? "0" : df.format(userWallet.getBalance()));
      // } else {
      // System.out.println(">>>> 未找到" + uid + "钱包记录.");
      // map1.put("balance", "0");
      // }

      SysLoginPojo sysLoginPojo = sysLoginService.getfindByIdSysLogin(uid);
      if (sysLoginPojo != null) {
        map1.put("balance",
            sysLoginPojo.getBalance() == null ? "0" : df.format(sysLoginPojo.getBalance()));
      } else {
        System.out.println(">>>> 未找到" + uid + "钱包记录.");
        map1.put("balance", "0");
      }

      if (userWalletLogs != null && userWalletLogs.size() > 0) {
        for (UserWalletLogPojo walletlog : userWalletLogs) {
          detail = new HashMap<String, Object>();
          detail.put("addTime", StringUtil.checkVal(walletlog.getCreateDateStr()));
          // if (walletlog.getSource() == null) {
          // detail.put("source", "");
          // } else if (walletlog.getSource() == uid) {
          // detail.put("source", "我");
          // } else {
          // SysLoginPojo sname = sysLoginService.findSysLoginById(walletlog.getSource());
          // if (sname != null) {
          // detail.put("source", StringUtil.checkVal(sname.getName()));
          // }
          // }
          detail.put("digest", StringUtil.checkVal(walletlog.getRemarks()));
          detail.put("type", StringUtil.checkVal(walletlog.getType()));
          detail.put("receive",
              walletlog.getTradeAmt() == null ? "0" : df.format(walletlog.getTradeAmt()));
          details.add(detail);
        }
      }
      b = true;
      map1.put("detailList", details);
    } else {
      msg = "会员ID是必须要填写哦！";
    }
    map.put("result", map1);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 用户注册api
   * */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String register() throws Exception {
    boolean flag = false;
    if (baidu_uid != null && StringUtils.isNotBlank(baidu_uid)) {
      // 相同机器码最多注册5个用户
      int count = baiduLoginService.checkMachineCodeRepeatByCode(baidu_uid);
      if (count >= 5) {
        flag = true;
        System.out.println(">>>>> baiduid2:" + baidu_uid);
      }
      if (!flag) {
        List<String> blackWords = WalletService.getBlackWords();
        int len = blackWords.size();

        for (int i = 0; i < len; i++) {
          if (baidu_uid.startsWith(blackWords.get(i))) {
            flag = true;
            break;
          }
        }
      }

    }/*
      * else { flag = true; }
      */
    if (flag) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("result", "");
      map.put("error_msg", "系统繁忙，请稍后再试！");
      map.put("success", false);
      JSONObject json = JSONObject.fromObject(map);
      ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      try {
        ServletActionContext.getResponse().getWriter().write(json.toString());
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }
    Boolean success = false;
    Boolean regFlag = false;
    String error_msg = "";
    // 用户注册呢称为tzm+手机号
    name = "tzm" + phone;
    // 根据手机号码判断是否注册过
    SysLoginPojo sysLoginPojobyloginname = null;
    if (phone == null || phone.equals("")) {
      regFlag = true;
      error_msg = "请输入手机号码！";
    } else if (captcha == null || captcha.equals("")) {
      regFlag = true;
      error_msg = "请输入验证码！";
    } else if (captcha.toString().length() != 6) {
      regFlag = true;
      error_msg = "请填写正确6位验证码！";
    } else if (pass == null || pass.equals("")) {
      regFlag = true;
      error_msg = "请输入密码！";
    } else if (pass.length() < 6) {
      regFlag = true;
      error_msg = "密码强度弱（至少6位），请换一个！";
    } else if (type == null || "".equals(type) || !"1".equals(type) && !"2".equals(type)
        && !"6".equals(type)) {
      regFlag = true;
      error_msg = "用户注册类型错误！";
    } else {
      sysLoginPojobyloginname = sysLoginService.getSysLoginByLoginName(phone);
      if (sysLoginPojobyloginname != null) {
        regFlag = true;
        error_msg = "手机号已注册过！";
      }
    }

    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    UserVerifyPojo verifyPojo = null;
    if (!regFlag) {
      verifyPojo = new UserVerifyPojo();
      verifyPojo.setLoginname(phone);
      // 得到系统生成的验证码
      verifyPojo = userVerifyService.findNewestByPhone(verifyPojo);
    }
    if (regFlag) {
      // error_msg = "手机号码已经存在！";
    } else if (verifyPojo == null) {
      error_msg = "验证码已经失效，请重新发送验证！";
    } else if (!captcha.equals(verifyPojo.getCaptcha())) {
      error_msg = "请填写正确验证码！";
    } else {
      SysLoginPojo sysPojo = new SysLoginPojo();
      sysPojo.setType(type);
      sysPojo.setStatus(1);
      sysPojo.setSorting(0);
      // sysPojo.setPassword(MD5Util.MD5(pass));
      sysPojo.setPassword(pass);
      sysPojo.setLoginname(phone);
      sysPojo.setName(name);
      if (baidu_uid != null && !"".equals(baidu_uid)) {
        sysPojo.setBaidu_uid(baidu_uid);
      }
      if (openid != null && !"".equals(openid)) {
        // sysPojo.setOpenid(openid);
        sysPojo.setUnionid(openid);
      }
      if (sinaid != null && !"".equals(sinaid)) {
        sysPojo.setSinaid(sinaid);
      }
      if (regChannel != null && !"".equals(regChannel)) {
        sysPojo.setRegChannel(regChannel);
      }
      if (imei != null && !"".equals(imei)) {
        sysPojo.setImei(imei);
      }
      sysPojo.setCreateDate(new Date());
      String externalSignCode = phone + new Date().getTime() / 1000 + StringUtil.genRandomStr(4);
      externalSignCode = MD5.MD5Encode(externalSignCode);
      sysPojo.setExternalSignCode(externalSignCode);
      sysPojo.setInvitationCode(walletService.genInviteCode());
      sysPojo.setCreateBy(0l);
      sysLoginService.addSysLoginService(sysPojo);

      // 得到添加成功的账户id
      if (sysPojo != null && sysPojo.getId() != null && sysPojo.getId() != 0) {
        // 根据用户注册时的手机查询用户
        // sysPojo = sysLoginService.findSysLoginByLoginname(sysPojo);

        // 向表baidu_login插入数据
        if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(baidu_uid)) {
          BaiduLoginPojo baiduLoginPojo = new BaiduLoginPojo();
          baiduLoginPojo.setUserId(sysPojo.getId());
          baiduLoginPojo.setLoginName(phone);
          baiduLoginPojo.setBaiduId(baidu_uid);
          baiduLoginPojo.setType(1);
          walletService.isInsertBaibuLogin(baiduLoginPojo);
        }
        // 有米积分墙回调
        if (StringUtils.isNotEmpty(imei)) {
          // 1-ios 2-android
          Integer channel = 0;
          if (StringUtils.isEmpty(regChannel) || "App Store".equals(regChannel)) {
            channel = 1;
          } else if ("Umeng".equals(regChannel) || "YouMi".equals(regChannel)) {
            channel = 2;
          }
          if (channel > 0) {
            walletService.checkAdFromYoumi(imei, channel, sysPojo.getId());
          }
        }

        // Date today = new Date();
        // if (StringUtil.stringDate("2015-11-20 00:00:00").compareTo(today) <= 0) {
        // // 首次注册送优惠券
        // long uid = sysPojo.getId();
        // Map<String, Object> param = new HashMap<String, Object>();
        // // 满50减25
        // /*
        // * param.put("om", "50"); param.put("m", "25"); json = JSONObject.fromObject(param);
        // * walletService.giveCoupon(uid, 1, 1, json.toString(), current, 30);
        // */
        // // 满10减5
        // param.put("om", "10");
        // param.put("m", "5");
        // Date current = new Date();
        // JSONObject json = JSONObject.fromObject(param);
        // walletService.giveCoupon(uid, 1, 1, json.toString(), current, 30);
        // // 满30减5
        // param.put("om", "30");
        // param.put("m", "5");
        // json = JSONObject.fromObject(param);
        // walletService.giveCoupon(uid, 1, 1, json.toString(), current, 30);
        // // 满99减10
        // param.put("om", "99");
        // param.put("m", "10");
        // json = JSONObject.fromObject(param);
        // walletService.giveCoupon(uid, 1, 1, json.toString(), current, 30);
        // // 满169减20=2张
        // param.put("om", "169");
        // param.put("m", "20");
        // json = JSONObject.fromObject(param);
        // walletService.giveCoupon(uid, 1, 1, json.toString(), current, 30);
        // walletService.giveCoupon(uid, 1, 1, json.toString(), current, 30);
        // // 满399减40
        // param.put("om", "399");
        // param.put("m", "40");
        // json = JSONObject.fromObject(param);
        // walletService.giveCoupon(uid, 1, 1, json.toString(), current, 30);
        // }

        // 融云token
        // 用户id
        /*
         * String userid = String.valueOf(sysPojo.getId()); // 用户名 String username =
         * sysPojo.getName(); // 头像 String logo = ""; // token String token =
         * sysLoginService.getRongyunToken(userid, username, logo); SysLoginPojo updsyspojo = new
         * SysLoginPojo(); updsyspojo.setToken(token); updsyspojo.setRemarks("");
         * updsyspojo.setLoginname(sysPojo.getLoginname());
         * loginService.updateLoginPojo(updsyspojo);
         */
        HttpServletRequest request = ServletActionContext.getRequest();

        LoginRecPojo loginRecPojo = new LoginRecPojo();
        loginRecPojo.setType(sysPojo.getType());
        loginRecPojo.setLoginDate(new Date());
        loginRecPojo.setLoginIp(walletService.getIpAddr(request));
        loginRecPojo.setUserId(sysPojo.getId());
        loginRecService.addLoginRec(loginRecPojo);
        // 注册同时userinfo表需插入一条数据
        // sysPojo = loginService
        // .getLoginPojoByLoginnameAndPassword(sysPojo);
        UserInfoPojo userInfoPojo = new UserInfoPojo();
        userInfoPojo.setUserId(sysPojo.getId());
        userInfoPojo.setCreateDate(new Date());
        userInfoPojo.setStatus(1);
        userInfoService.insertUserInfo(userInfoPojo);

        // 用户积分表插入记录
        /*
         * UserScorePojo userScorePojo = new UserScorePojo();
         * userScorePojo.setUserId(sysPojo.getId()); userScorePojo.setName(sysPojo.getName());
         * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         * userScorePojo.setShakeDateStr(sdf.format(new Date())); userScorePojo.setScore(0L);
         * userScorePojo.setShakeDate(StringUtil.stringToDate(sdf.format(new Date())));
         * userScorePojo.setShakeNum(3); userScorePojo.setBunding(0); userScorePojo.setUpload(0);
         * userScorePojo.setImprove(0); userScoreService.insertUserScore(userScorePojo);
         */

        // 用户钱包表插入记录
        UserWalletPojo userWalletPojo = new UserWalletPojo();
        userWalletPojo.setUserId(sysPojo.getId());
        userWalletPojo.setBalance(0.00);
        userWalletPojo.setTotalBalance(0.00);
        userWalletPojo.setCreateBy(sysPojo.getId());
        userWalletPojo.setUpdateBy(sysPojo.getId());
        userWalletService.insertUserWallet(userWalletPojo);
      }

      map1.put("phone", sysPojo.getLoginname());
      map1.put("name", sysPojo.getName());
      map1.put("type", sysPojo.getType());
      map1.put("uid", sysPojo.getId());
      map1.put("token", sysPojo.getToken() == null ? "" : sysPojo.getToken());
      map1.put("openid", sysPojo.getUnionid() == null ? "" : sysPojo.getUnionid());
      map1.put("sinaid", sysPojo.getSinaid() == null ? "" : sysPojo.getSinaid());
      if (sysPojo.getType() != null && "2".equals(sysPojo.getType())) {
        map1.put("judgeType", 1);
      } else {
        map1.put("judgeType", 0);
      }
      success = true;
      error_msg = "提交成功！";
    }
    map.put("success", success);
    map.put("result", map1);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 提交邀请码接口
   * 
   * @return
   * @throws Exception
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String submitInviteCode() throws Exception {
    String msg = "";
    boolean b = false;
    int i = 0;
    Map<String, Object> map = new HashMap<String, Object>();
    if ("6CL21B".equals(inviteCode) || "HY4VY1".equals(inviteCode)) {
      // 特殊处理
      msg = "验证成功！";
    } else if (uid == null || "".equals(uid)) {
      msg = "用户id不能为空！";
    } else if (inviteCode == null || "".equals(inviteCode)) {
      msg = "邀请码不能为空！";
    } else if (sysLoginService.getfindCountByIdSysLogin(uid) == 0) {
      msg = "该会员未找到！";
    } else {
      // 根据邀请码获取被邀请用户ID
      SysLoginPojo code = new SysLoginPojo();
      code.setInvitationCode(inviteCode);
      SysLoginPojo invitor = sysLoginService.getUserIdByInvitationCode(code);
      if (invitor != null && invitor.getId().longValue() != uid.longValue()) {
        int count = userWalletLogService.getUserIncomeCountToday(invitor.getId());
        i = walletService.isNewWalletUser(uid, StringUtil.stringDate("2015-12-23 00:00:00"));
        if (count >= 100) {
          b = true;
          msg = "验证成功！";
        } else if (i == 1) {
          // 修改inviterId
          SysLoginPojo sysLogin0 = new SysLoginPojo();
          sysLogin0.setId(uid);
          sysLogin0.setInviterId(invitor.getId());
          int check = baiduLoginService.checkMachineCodeRepeat(uid);
          int blackflag = 0;
          // 同一机器注册5个用户及以上，为嫌疑用户
          if (check >= 5) {
            blackflag = 1;
            sysLogin0.setBlackFlag(blackflag);
          }
          int isupdate = sysLoginService.updateSysLogin(sysLogin0);
          if (isupdate == 1) {
            //
            int flag = 1;
            /*
             * blackUsers = WalletService.getBlackUsers(); if (blackUsers != null &&
             * blackUsers.contains(invitor.getId().intValue())) { flag =
             * walletService.genRanCode(3); }
             */
            UserWalletPojo userWallet0 = userWalletService.findUserWalletByUserId(invitor.getId());
            if (userWallet0 != null) {
              if (blackflag == 1) {
                // 嫌疑用户钱包金额检查
                flag = walletService.walletBlackRule(userWallet0.getTotalBalance());
              }
              // 写入记录
              if (flag == 1) {
                UserWalletLogPojo userWalletLogPojo = new UserWalletLogPojo();
                userWalletLogPojo.setUserId(invitor.getId());
                userWalletLogPojo.setCurBal(userWallet0.getBalance());
                userWalletLogPojo.setType(0l);
                Double balance = walletService.getShareAmount(3);
                userWalletLogPojo.setTradeAmt(balance);
                userWalletLogPojo.setSource(uid);
                userWalletLogPojo.setRemarks(ConstParam.SHARE_FROM_TIP);
                userWalletLogService.insertUserWalletLog(userWalletLogPojo);

                walletService.addWalletBalance(balance, invitor.getId());
              }
            }
            b = true;
            msg = "验证成功！";
          } else {
            msg = "您被邀请过了！";
          }
        } else if (i == 2) {
          msg = "您是老用户，赶紧分享邀请码吧！";
        } else if (i == 3) {
          msg = "您被邀请过了！";
        }
      } else {
        msg = "邀请码无效！";
      }
    }
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 提交邀请码后
   * 
   * @return
   * @throws Exception
   */
  public String checkInviteCode() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    SysLoginPojo login = sysLoginService.findSysLoginById(uid);
    if (uid == null) {
      msg = "用户id不能为空！";
    } else if (login == null) {
      msg = "该会员未找到！";
    } else {
      String nullStr = "";
      int i = walletService.isNewWalletUser(uid, StringUtil.stringDate("2015-12-12 00:00:00"));
      if (i == 1) {
        // 新用户
        msg = "请输入邀请码！";
        map1.put("invitor", nullStr);
        map1.put("invitorImg", nullStr);
        map1.put("inviteCode", nullStr);
      } else if (i == 2) {
        // 老用户
        map1.put("invitor", nullStr);
        map1.put("invitorImg", nullStr);
        map1.put("inviteCode",
            login.getInvitationCode() == null ? nullStr : login.getInvitationCode());
      } else if (i == 3) {
        // 已被邀请
        SysLoginPojo invitor = sysLoginService.findSysLoginById(login.getInviterId());
        map1.put(
            "invitor",
            invitor == null || invitor.getName() == null ? nullStr : WalletService
                .enCodeString(invitor.getName()));
        map1.put("invitorImg", invitor == null || invitor.getImage() == null ? nullStr
            : ConstParam.URL + "/upfiles/userlogo/" + invitor.getImage());
        map1.put("inviteCode",
            login.getInvitationCode() == null ? nullStr : login.getInvitationCode());
      }
      b = true;
    }
    map.put("error_msg", msg);
    map.put("success", b);
    map.put("result", map1);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 用户登录API
   * 
   * @throws Exception
   * */
  public String myInfo() throws Exception {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    Map<String, Object> userSpecialCollectMaper = new HashMap<String, Object>();
    if (uid == null || uid.equals("")) {
      error_msg = "用户id不能为空！";
    } else {
      // 验证用户名和密码是否正确
      int status;
      SysLoginPojo logiPojo = sysLoginService.getfindByIdSysLogin(uid);
      try {
        agencyPojo = agencyService.findagencyStatusByUserId(uid);
      } catch (Exception e) {
        e.printStackTrace();
      }
      if (agencyPojo != null) {
        status = agencyPojo.getStatus();
      } else {
        status = 0;
      }

      if (logiPojo != null) {

        // 设置用户id会员名称
        Long uid = logiPojo.getId();

        map2.put("status", status);
        success = true;
        error_msg = "";
        map.put("success", success);
        map.put("error_msg", error_msg);
        map2.put("uid", uid);
        map2.put("userName", logiPojo.getLoginname());
        map2.put("name", logiPojo.getName());
        if (logiPojo.getImage() == null || "".equals(logiPojo.getImage())) {
          map2.put("image", "");
        } else {
          map2.put("image", ConstParam.URL + "/upfiles/userlogo/" + logiPojo.getImage());
        }

        int num = userCollectService.myProductCollectAllCount(uid);
        map2.put("proFavorites", num);
        // int num2 = userShopCollectService.myShopCollectAllCount(uid);
        // map2.put("shopFavorites", num2);
        userSpecialCollectMaper.put("userId", uid);
        int num2 =
            userSpecialCollectService.getUserSpecialCollectListCount(userSpecialCollectMaper);// 调用方法查找用户对应的专场收藏
        map2.put("shopFavorites", num2);

        HistoryPojo hPojo = new HistoryPojo();

        hPojo.setUserId(uid);
        hPojo.setType(1);
        int num3 = historyService.myFootprint(hPojo);
        map2.put("myFootprint", num3);

        List<OrderPojo> list = null;
        orderPojo = new OrderPojo();
        if (agencyPojo != null) {
          // 待接受
          // orderPojo.setPushAgencyUid(uid);
          // list = orderService.getPushByUidOrder(orderPojo, page);
          // int num4 = list == null ? 0 : list.size();
          // map2.put("waitReceive", num4 == 0 ? "" : num4);
          map2.put("waitReceive", "");
          map2.put("waitPay", "");
        } else {
          // 待付款
          orderPojo.setUserId(uid);
          orderPojo.setOrderStatus(1);
          list = orderService.userIdOrderByPage(orderPojo, page);
          int num4 = list == null ? 0 : list.size();
          map2.put("waitPay", num4 == 0 ? "" : num4);

          UserOrderRefundPojo userOrderRefundPojo = new UserOrderRefundPojo();
          userOrderRefundPojo.setUserId(uid);
          List<UserOrderRefundPojo> orderlist =
              userOrderRefundService.findOrderRefundDetailByUserId(userOrderRefundPojo, page);
          int num8 = 0;
          for (UserOrderRefundPojo refund : orderlist) {
            // 退货类型(4=>退货成功，5=>退货失败，6=>审核不成功，7=>退款成功)
            if (refund.getStatus() != 4 && refund.getStatus() != 7 && refund.getStatus() != 5
                && refund.getStatus() != 6) {
              num8++;
            }
          }
          map2.put("waitReceive", num8 == 0 ? "" : num8);
        }
        // 待付款
        orderPojo = new OrderPojo();
        orderPojo.setUserId(uid);
        // 待发货
        orderPojo.setOrderStatus(2);
        list = orderService.userIdOrderByPage(orderPojo, page);
        int num5 = list == null ? 0 : list.size();
        map2.put("waitDeliver", num5 == 0 ? "" : num5);
        // 待收货
        orderPojo.setOrderStatus(3);
        list = orderService.userIdOrderByPage(orderPojo, page);
        int num6 = list == null ? 0 : list.size();
        map2.put("waitReceipt", num6 == 0 ? "" : num6);
        // 待评价
        orderPojo.setOrderStatus(4);
        list = orderService.userIdOrderByPage(orderPojo, page);
        int num7 = 0;
        if (agencyPojo == null) {
          if (list != null && list.size() > 0) {
            UserOrderRefundPojo userOrderRefundPojo = null;
            for (OrderPojo p : list) {
              List<OrderDetailPojo> orderDetaillist =
                  orderDetailService.getfindByUserIdOrderDetail(p.getId());
              userOrderRefundPojo = new UserOrderRefundPojo();
              userOrderRefundPojo.setOrderId(p.getId());
              List<UserOrderRefundPojo> userOrderRefundlist =
                  userOrderRefundService.findUserOrderRefundByorderId(userOrderRefundPojo);
              if (orderDetaillist != null && userOrderRefundlist != null
                  && orderDetaillist.size() == userOrderRefundlist.size()) {
                // 4-待评价，如果为待评价查询，全部退货/退款订单不显示
                boolean flag = true;
                for (UserOrderRefundPojo refund : userOrderRefundlist) {
                  if (refund.getStatus() != 4 && refund.getStatus() != 7 && refund.getStatus() != 5
                      && refund.getStatus() != 6) {
                    flag = false;
                  }
                }
                if (flag) {
                  continue;
                }
              }
              num7++;
            }
          }
        } else {
          num7 = list == null ? 0 : list.size();
        }

        map2.put("waitComment", num7 == 0 ? "" : num7);
        // 融云token
        map2.put("token", logiPojo.getToken() == null ? "" : logiPojo.getToken());
        // 未使用的代金券数量
        Map<String, Object> c = new HashMap<String, Object>();
        c.put("userId", uid);
        c.put("status", 1);
        c.put("used", 0);
        long time = new Date().getTime() / 1000;
        c.put("validEtime", time);
        c.put("couponNum", "couponNum");
        couponNum = couponService.getuserCouponList(c).size();
        map2.put("couponNum", couponNum == 0 ? "" : couponNum);

        // 用户首单标志更新
        int hadFirstOrder = orderService.checkUserFirstOrder(uid);
        if (hadFirstOrder > 0) {
          map2.put("firstOrder", 1);
        } else {
          map2.put("firstOrder", 0);
        }

        // 年龄
        SysLoginPojo sysLoginPojo = sysLoginService.getUserInfoById(uid);
        if (sysLoginPojo != null && sysLoginPojo.getBabyBirthday() != null
            && !"".equals(sysLoginPojo.getBabyBirthday())) {
          String birth = sysLoginPojo.getBabyBirthday();
          birth = SellerService.getAgeString(StringUtil.stringToDate(birth));
          map2.put("age", birth);

        } else {
          map2.put("age", "");
        }

        // 加newUser
        int newUser =
            walletService.isNewWalletUser(uid, StringUtil.stringDate("2015-12-12 00:00:00"));
        map2.put("newUser", newUser);
        // 加balance
        UserWalletPojo userWallet0 = userWalletService.findUserWalletByUserId(uid);
        if (userWallet0 != null) {
          map2.put("balance", userWallet0.getBalance() == null || userWallet0.getBalance() == 0 ? 0
              : userWallet0.getBalance());
        } else {
          map2.put("balance", 0);
        }

        map.put("result", map2);

      } else {
        error_msg = "用户id不存在";
      }
    }
    map.put("success", success);
    map.put("result", map2);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 批发商用户登录API
   * 
   * @throws Exception
   * */
  public String agentlogin() throws Exception {
    boolean success = false;
    boolean ckFlag = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    Map<String, Object> map3 = new HashMap<String, Object>();
    SysLoginPojo loginck = null;
    if (openid != null && !"".equals(openid)) {
      // map3.put("openid", openid);
      map3.put("unionid", openid);
      loginck = sysLoginService.qrySysLoginByThirdId(map3);
      if (loginck == null) {
        error_msg = "微信号未注册！";
        ckFlag = true;
      }
    } else if (sinaid != null && !"".equals(sinaid)) {
      map3.put("sinaid", sinaid);
      loginck = sysLoginService.qrySysLoginByThirdId(map3);
      if (loginck == null) {
        error_msg = "新浪id未注册！";
        ckFlag = true;
      }
    } else if (phone == null || phone.equals("")) {
      error_msg = "用户登录名不能为空！";
      ckFlag = true;
    } else if (pass == null || pass.equals("")) {
      error_msg = "用户密码不能为空！";
      ckFlag = true;
    }
    if (ckFlag) {

    } else {
      // 验证用户名和密码是否正确
      SysLoginPojo loginPojo = new SysLoginPojo();
      if (loginck != null) {
        loginPojo.setLoginname(loginck.getLoginname());
        loginPojo.setPassword(loginck.getPassword());
        phone = loginck.getLoginname();
      } else {
        loginPojo.setLoginname(phone);
        // loginPojo.setPassword(MD5Util.MD5(pass));
        loginPojo.setPassword(pass);
      }

      if (loginService.loginCheckWeb(loginPojo)) {
        // 成功
        SysLoginPojo logiPojo = sysLoginService.getSysLoginByLoginName(phone);
        if (logiPojo != null
            && logiPojo.getStatus() == 1
            && (Long.valueOf(logiPojo.getType()) == 6 || Long.valueOf(logiPojo.getType()) == 1
                || Long.valueOf(logiPojo.getType()) == 11 || Long.valueOf(logiPojo.getType()) == 12)) {
          /*
           * HttpServletRequest request = ServletActionContext.getRequest(); // 向登录日志表中插入信息
           * LoginRecPojo loginRecPojo = new LoginRecPojo();
           * loginRecPojo.setType(logiPojo.getType()); loginRecPojo.setLoginDate(new Date());
           * loginRecPojo.setLoginIp(walletService.getIpAddr(request));
           * loginRecPojo.setUserId(logiPojo.getId()); loginRecService.addLoginRec(loginRecPojo);
           */

          // 接收baidu_id并保存进数据库表
          if (logiPojo.getBaidu_uid() != baidu_uid) {
            SysLoginPojo login = new SysLoginPojo();
            login.setBaidu_uid(baidu_uid);
            login.setLoginname(phone);
            loginService.updateBaiduUid(login);
          }

          // 向表baidu_login插入数据
          /*
           * if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(baidu_uid)) {
           * BaiduLoginPojo baiduLoginPojo = new BaiduLoginPojo();
           * baiduLoginPojo.setUserId(logiPojo.getId()); baiduLoginPojo.setLoginName(phone);
           * baiduLoginPojo.setBaiduId(baidu_uid); baiduLoginPojo.setType(2);
           * walletService.isInsertBaibuLogin(baiduLoginPojo); }
           */

          // 设置用户id会员名称
          Long uid = logiPojo.getId();
          String name = logiPojo.getName();
          // 查询全部订单数量
          // OrderPojo orderPojo = orderService.orderStatusNum(uid);
          success = true;
          error_msg = "登录成功！";
          map.put("success", success);
          map.put("error_msg", error_msg);
          map2.put("uid", uid);
          map2.put("name", name);
          map2.put("phone", phone);
          map2.put("type", logiPojo.getType());
          map2.put("token", logiPojo.getToken() == null ? "" : logiPojo.getToken());
          // map2.put("openid", logiPojo.getOpenid() == null ? "" : logiPojo.getOpenid());
          map2.put("openid", logiPojo.getUnionid() == null ? "" : logiPojo.getUnionid());
          map2.put("sinaid", logiPojo.getSinaid() == null ? "" : logiPojo.getSinaid());
          if (logiPojo.getImage() == null || "".equals(logiPojo.getImage())) {
            map2.put("image", "");
          } else {
            map2.put("image", ConstParam.URL + "/upfiles/userlogo/" + logiPojo.getImage());
          }
          AgencyPojo agencyPojo = agencyService.findagencyByUserId(uid);
          int status = 0;
          if (agencyPojo != null) {
            map2.put("judgeType", 3);
            status = agencyPojo.getStatus();
          }/*
            * else if (consumerService.findConsumerByUserId(uid) != null) { map2.put("judgeType",
            * 2); }
            */else if (manufacturerService.findManufacturerByUserId(uid) != null) {
            map2.put("judgeType", 1);
          } else {
            map2.put("judgeType", 0);
          }
          if (agencyPojo != null && agencyPojo.getAgencyId() != null) {
            map2.put("agencyId", agencyPojo.getAgencyId());
          } else {
            map2.put("agencyId", 0);
          }

          map2.put("status", status);

          // 加newUser
          int newUser =
              walletService.isNewWalletUser(uid, StringUtil.stringDate("2015-12-12 00:00:00"));
          map2.put("newUser", newUser);
          // 加balance
          UserWalletPojo userWallet0 = userWalletService.findUserWalletByUserId(uid);
          if (userWallet0 != null) {
            map2.put("balance", userWallet0.getBalance() == null ? 0 : userWallet0.getBalance());
          } else {
            map2.put("balance", 0);
          }

          map.put("result", map2);
          // JSONObject json = JSONObject.fromObject(map);
          // actionContext.put("result", json.toString());
          // return SUCCESS;
        } else {
          error_msg = "请用批发商账号登陆";
        }
      } else {
        error_msg = "用户名或密码错误";
      }
    }
    map.put("success", success);
    map.put("result", map2);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 活动闪屏接口
   * 
   * @return
   * @throws Exception
   */
  public String activitySplashScreen() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 1);
    List<SplashScreenPojo> splashScreenPojos = splashScreenService.findSplashScreenList(option);

    List<Object> list = new ArrayList<Object>();
    Map<String, Object> result = null;
    if (splashScreenPojos.size() != 0) {
      for (SplashScreenPojo s : splashScreenPojos) {
        result = new HashMap<String, Object>();
        result.put("image_ios4", ConstParam.URL + "/upfiles/splashScreen/" + s.getImage1());
        result.put("image_ios5", ConstParam.URL + "/upfiles/splashScreen/" + s.getImage2());
        result.put("image_android", ConstParam.URL + "/upfiles/splashScreen/" + s.getImage3());
        result.put("title", s.getTitle());
        result.put("webLink", s.getUrl());
        list.add(result);
      }
      b = true;
    }
    map.put("result", list);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 提交订单
   * 
   * @throws Exception
   * */
  public String addorder() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, String> alipayMap = new HashMap<String, String>();
    Map<String, Object> wxpayMap = new HashMap<String, Object>();
    String error_msg = "";
    Boolean success = false;
    DeliveryAddressPojo address = null;

    // 根据用户ID查询用户信息
    SysLoginPojo user = sysLoginService.findSysLoginById(uid);
    if (consigneeType == 1 && addressId != null && addressId > 0) {
      address = addressService.findDeliveryAddressById(addressId);// 获取收货地址信息
    }
    if (uid == null || uid.equals("")) {
      error_msg = "用户ID不能为空！";
    } else if (user == null) {
      error_msg = "没有该用户ID！";
    } else if (cids == null || cids.equals("")) {
      error_msg = "购物车ID不能为空！";
    } else if (payMethod == null || payMethod != 1 && payMethod != 2) {
      error_msg = "支付方式有误！";
    } else if (consigneeType == null || consigneeType == 0) {
      error_msg = "收货方式有误！";
    } else if (consigneeType == 1 && address == null) {
      error_msg = "收货地址不能为空！";
    } else if (messages == null || "".equals(messages)) {
      error_msg = "订单消息不能为空！";
    } else {
      // 单个订单产品总价格
      Double allCartPrice = 0.0;
      // 单个订单总价+运费
      Double allCartPrice0 = 0.0;
      // Double hCartPrice = 0.0;
      // 自提地址可以为空
      // DeliveryAddressPojo address = null;
      // 订单最大金额
      Double maxBill = 0.00;
      String maxOrderNo = "";

      // 分割消息
      String[] msgs = messages.split(",");
      // 分割cid
      String[] cartIds = cids[0].split(",");

      String remark = null;
      boolean choosedCart = false;
      List<CartPojo> cartList = cartService.submitCartsByIds(cartIds);// 获取商品信息
      for (CartPojo cart : cartList) {
        if (cart != null && 1 == cart.getStatus()) {
          // 购物车商品已选中
          choosedCart = true;
          break;
        }
      }
      if (cartList == null || cartList.size() == 0) {
        error_msg = "购物车中已没有商品了哦！";
        success = false;
      } else if (choosedCart) {
        error_msg = "订单商品已提交，请不要重复提交哦！";
        success = false;
      } else {
        // 修改选中的购物车商品的状态=1
        cartService.updateStatusCartWeb(cartIds);

        DecimalFormat df = new DecimalFormat("#.##");
        // 订单描述--支付宝
        String body = "";
        // 订单号
        String subject = "";
        // 商户订单号--支付宝
        String out_trade_no = UtilDate.getOrderNum() + UtilDate.getThree();
        long activeId = -1;
        String activityName = "";
        // 订单商品数
        int cartCnt = 0;
        // 订单数
        int allOrderCnt = 0;
        // 购买总数量
        int cartNum = 0;
        CartPojo cartParam = null;
        OrderPojo orderParam = null;
        List<Long> errCartIds = new ArrayList<Long>();
        for (CartPojo cart : cartList) {

          if (!(activeId == cart.getActivityId() && activityName.equals(cart.getActivityName()))) {
            cartCnt = 0;
            cartParam = new CartPojo();
            cartParam.setUserId(cart.getUserId());
            cartParam.setActivityId(cart.getActivityId());
            cartParam.setActivityName(cart.getActivityName());
            cartParam.setCheckStatus(1);
            List<CartPojo> cartListByShopId = cartService.findCartByUserIdAndBrand(cartParam);
            // 邮费
            Double farestatus = 0.0;// farestatus=1初始值
            // 重量
            Double pweight = 0.0;
            Double orderWeight = 0.0;
            // 订单总价
            allCartPrice = 0.0;
            cartNum = 0;
            // 活动产品判断
            ActivityGoodsPojo activityGoods = null;
            boolean stockFlag = false;
            errCartIds.clear();
            for (CartPojo cartByShopId : cartListByShopId) {
              // 初始化
              stockFlag = false;
              // 更新购物车活动价格
              Integer skuid = cartByShopId.getSkuLinkId();
              Long activityId = cartByShopId.getActivityId();
              if (activityId == null) {
                activityId = 0L;
              }
              // 活动是否结束
              if (activityId > 0) {
                activityGoods =
                    sellerService.isProductActivity(cartByShopId.getProductId(), activityId);
                if (activityGoods == null) {
                  errCartIds.add(cartByShopId.getId());
                  continue;
                }
              }
              // sku库存判断
              if (skuid != null && skuid > 0) {
                // 同步检查活动库存并更新
                stockFlag = sellerService.updateActivitySkuStock(cartByShopId);
                if (!stockFlag) {
                  errCartIds.add(cartByShopId.getId());
                  continue;
                }
              } else if (activityId > 0) {
                // 同步检查活动库存并更新
                stockFlag = sellerService.updateActivityStock(cartByShopId);
                if (!stockFlag) {
                  errCartIds.add(cartByShopId.getId());
                  continue;
                }
              }
              if (stockFlag) {
                // 更新库存成功
                // cartByShopId.setStockPrice(activityGoods.getActivePrice());
                cartService.updateCart(cartByShopId);
              }
              body = body + cartByShopId.getProductName() + ";";
              cartCnt++;
              // 计算同一张订单的所有商品的价格
              allCartPrice = allCartPrice + cartByShopId.getStockPrice() * cartByShopId.getNum();
              cartByShopId.setAllStockPrice(df.format(cartByShopId.getStockPrice()
                  * cartByShopId.getNum()));
              // 计算重量
              orderWeight += Double.valueOf(cartByShopId.getWeight()) * cartByShopId.getNum();
              cartNum += cartByShopId.getNum();
            }
            // 订单无商品
            if (cartCnt == 0) {
              activeId = cart.getActivityId();// 再次初始化ShopId的值
              activityName = cart.getActivityName();
              continue;
            }
            // 自提
            if (consigneeType == 2) {
              farestatus = 0.0;
            }
            // hCartPrice = hCartPrice + allCartPrice;
            // 方式为自提时该值为空
            SysAreaPojo sysArea = null;
            if (address != null) {
              sysArea = sysAreaService.getNameById(address.getProvince());// 查询这个省对应的运费；
            }
            if (farestatus == 1.0 && sysArea != null) {
              // 不包邮
              Double cweight = 0.0;
              for (CartPojo cartByShopId : cartListByShopId) {
                cweight = Double.valueOf(cartByShopId.getWeight());
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

            // 偏远地区：新疆、西藏、内蒙古、青海、甘肃、宁夏——加运费10元
            Double deliverFee = 0.00;
            String province = "";
            if (address != null && address.getProvinceName() != null
                && !"".equals(address.getProvinceName())) {
              province = address.getProvinceName();
              if (province.indexOf("新疆") != -1 || province.indexOf("西藏") != -1
                  || province.indexOf("内蒙古") != -1 || province.indexOf("青海") != -1
                  || province.indexOf("甘肃") != -1 || province.indexOf("宁夏") != -1) {
                // deliverFee = 10.00;
                deliverFee = SellerService.calcFare(orderWeight);
                // 订单价格
                allCartPrice0 += deliverFee;
              }
            }
            // 生成一张新的订单的订单号
            String orderNo = new Date().getTime() + RandomUtils.runVerifyCode(6);

            // 满减满折处理
            orderParam =
                sellerService.specialDiscountProcess(allCartPrice, cartNum, allCartPrice0,
                    cart.getActivityId());
            allCartPrice0 = orderParam == null ? allCartPrice0 : orderParam.getFactPrice();
            // 四舍五入保留一位小数
            allCartPrice0 =
                new BigDecimal(String.valueOf(allCartPrice0)).setScale(1, RoundingMode.HALF_UP)
                    .doubleValue();

            // 生成订单
            OrderPojo order = new OrderPojo();
            order.setUserId(user.getId());
            order.setSuserId(cart.getShopUserid());// 店铺id作为订单的suerid
            order.setAllNum("" + cartNum);
            order.setAllPrice(allCartPrice);
            order.setFactPrice(allCartPrice0);
            // 判断最大单
            if (maxBill < allCartPrice) {
              maxBill = allCartPrice;
              maxOrderNo = orderNo;
            }
            // 运费
            order.setEspressPrice(Double.valueOf(farestatus) + deliverFee);
            // 自提方式
            if (address != null) {
              order.setConsignee(address.getConsignee());
              order.setConsigneeAddress(address.getProvinceName() + address.getCityName()
                  + address.getAreaName() + address.getAddress());
              order.setConsigneePhone(address.getConsigneePhone());
            }

            order.setConsigneeType(consigneeType);
            if (3 == payMethod) {
              // 货到付款时状态为2-已付款
              order.setOrderStatus(2);
              order.setPayStatus(1);
            } else {
              order.setOrderStatus(1);
              order.setPayStatus(0);
            }
            order.setIsDeleteOrder(0);
            order.setIsCancelOrder(0);
            order.setCreateBy(user.getId());
            order.setCreateDate(new Date());
            order.setOrderNo(orderNo);
            order.setChannel(1);
            order.setPayMethod(payMethod);
            for (int i = 0; i < msgs.length; i++) {
              // 分解字符串分别得到店铺ID和评价
              String[] strings = msgs[i].split(":");
              if (strings.length >= 2) {
                for (int k = 0; k < strings.length; k++) {
                  String idsplit = strings[0];

                  if (cart.getShopId() == Long.parseLong(idsplit)) {
                    remark = strings[1];
                    buyer_message = remark;
                  }
                }
              }
            }

            if (buyer_message != null && !"".equals(buyer_message)) {
              if ("null".equals(buyer_message.trim())) {
                order.setBuyerMessage("");
              } else {
                order.setBuyerMessage(buyer_message);
              }
            } else {
              order.setBuyerMessage("");
            }
            // i++;
            // 支付宝订单号
            order.setOutTradeNo(out_trade_no);
            if (address != null) {
              order.setProvince(address.getProvince() != null ? address.getProvince().toString()
                  : "0");
              order.setCity(address.getCity() != null ? address.getCity().toString() : "0");
              order.setArea(address.getArea() != null ? address.getArea().toString() : "0");
            }

            order.setActivityId(cart.getActivityId());
            order.setActivityName(cart.getActivityName());
            order.setDiscountType(orderParam == null ? 0 : orderParam.getDiscountType());
            order.setDiscountContext(orderParam == null ? null : orderParam.getDiscountContext());
            order.setDiscountPrice(orderParam == null ? 0.00 : orderParam.getUsedPrice());
            orderService.insertOrder(order);
            subject += order.getOrderNo() + "\n";
            // 生成订单结束
            // 生成订单详情
            OrderPojo insert_order = orderService.findOrderByOrderNo(orderNo);
            for (CartPojo cartDetail : cartListByShopId) {
              if (!errCartIds.contains(cartDetail.getId())) {
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
                orderDetail.setChannel(1);
                orderDetail.setStatus(1);
                orderDetail.setSkuLinkId(cartDetail.getSkuLinkId());
                orderDetail.setCreateBy(user.getId());
                orderDetail.setCreateDate(new Date());
                orderDetail.setActivityId(cartDetail.getActivityId());
                orderDetail.setActivityName(cartDetail.getActivityName());
                // 商品活动类型
                orderDetail.setType(cartDetail.getType());
                orderDetailService.insertOrderDetail(orderDetail);
                cartService.deleteCart(cartDetail.getId());
              }
            }
            allOrderCnt++;
          }
          activeId = cart.getActivityId();// 再次初始化ShopId的值
          activityName = cart.getActivityName();
        }
        if (allOrderCnt == 0) {
          map.put("result", result);
          map.put("success", false);
          map.put("error_msg", "对不起，您订单中的商品库存已不足，请选择其他商品！");
          JSONObject json = JSONObject.fromObject(map);
          ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
          try {
            ServletActionContext.getResponse().getWriter().write(json.toString());
          } catch (IOException e) {
            e.printStackTrace();
          }
          return null;
        }

        setOutTradeNo(out_trade_no);
        // 用户首单标志更新
        int hadFirstOrder = orderService.checkUserFirstOrder(uid);
        if (hadFirstOrder == 0) {
          // 首单更新
          orderService.updateFirstOrder(out_trade_no);
        }

        // 使用优惠券金额
        double m = 0.0;
        double om = 0.0;
        int type = 0;
        UserCouponPojo userCoupon = null;
        long time = new Date().getTime() / 1000;
        if (couponNo != null && !"".equals(couponNo)) {
          Map<String, Object> paramMap = new HashMap<String, Object>();
          paramMap.put("couponNo", couponNo);
          paramMap.put("userId", uid);
          // 未使用
          paramMap.put("used", 0);
          userCoupon = couponService.getUserCouponByNo(paramMap);
          if (userCoupon != null && (1 == userCoupon.getType() || 2 == userCoupon.getType())) {
            type = userCoupon.getType();
            org.json.JSONObject json = new org.json.JSONObject(userCoupon.getContent());
            if (!(json.get("om") == null || "".equals(json.get("om")))) {
              om = json.getDouble("om");
            }
            if (!(json.get("m") == null || "".equals(json.get("m")))) {
              m = json.getDouble("m");
              // 更新优惠券使用
              userCoupon.setUsed(1);
              userCoupon.setUseTime(time);
              userCoupon.setStatus(1);
              userCoupon.setVersions(1);
              int isupdate = couponService.updateUserCoupon(userCoupon);
              if (isupdate == 0) {
                m = 0;
              }
            }
          }
        }

        Double allOrderPrice = 0.0;
        CouponOrderPojo couponOrder = null;
        List<OrderPojo> outOrderList = orderService.getOrderByoutTradeNo(out_trade_no);// 获取商品信息
        List<Map<String, Object>> orders = new ArrayList<Map<String, Object>>();
        Map<String, Object> ordermap = null;
        Double factPrice = 0.00;
        for (OrderPojo outOrder : outOrderList) {
          factPrice = outOrder.getFactPrice();
          allOrderPrice = allOrderPrice + factPrice;
          ordermap = new HashMap<String, Object>();
          ordermap.put("orderId", outOrder.getId());
          if (m > 0.0) {
            // 添加订单用券记录
            couponOrder = new CouponOrderPojo();
            if (maxOrderNo.equals(outOrder.getOrderNo())) {
              factPrice = factPrice - m;
              couponOrder.setUsedPrice(m);
              if (1 == type && om > 0.0 && outOrder.getAllPrice() >= om || type == 2) {
                // 初始有效，单张订单满足用券
                couponOrder.setStatus(1);
              } else {
                // 初始无效
                couponOrder.setStatus(0);
              }
            } else {
              couponOrder.setUsedPrice(0.00);
              // 初始无效，支付有效
              couponOrder.setStatus(0);
            }
            couponOrder.setCouponNo(couponNo);
            couponOrder.setOrderId(outOrder.getId());
            couponOrder.setRelTime(time);

            couponService.addCouponOrder(couponOrder);
          }
          ordermap.put("price", factPrice);
          orders.add(ordermap);
        }

        // 支付金额减去代金券金额
        allOrderPrice = allOrderPrice - m;

        // 判断钱包使用 并且用户未冻结
        if (wallet != null && wallet == 1 && user.getStatus() != null && user.getStatus() == 1) {
          allOrderPrice = walletService.useWalletPay(uid, allOrderPrice, orders, out_trade_no, 0);
        }
        int fullpay = 0;
        if (allOrderPrice > 0) {
          // 1-支付宝支付
          if (1 == payMethod) {
            // 生成一条未付款支付宝信息
            AlipayOrderInfoPojo alipayOrderInfoPojo = new AlipayOrderInfoPojo();
            alipayOrderInfoPojo.setOutTradeNo(out_trade_no);
            alipayOrderInfoPojo.setTotalFee(df.format(allOrderPrice));
            alipayOrderInfoPojo.setTradeStatus("WAIT_BUYER_PAY");
            alipayOrderInfoPojo.setCreateDate(new Date());
            alipayOrderInfoPojo.setVersion(0);
            alipayOrderInfoService.insertOne(alipayOrderInfoPojo);

            // 组装支付宝支付串
            if (subject.length() > 2) {
              // 去掉尾部‘\n’
              subject = subject.substring(0, subject.length() - 2);
            }
            subject = "拼得好-订单号:" + subject;
            // 总长128，超出120长度截掉
            if (subject.length() > 120) {
              subject = subject.substring(0, 120) + "...";
            }
            String show_url = "http://www.alipay.com/";
            // 超出500长度截掉
            if (body.length() > 250) {
              body = body.substring(0, 250) + "...";
            }
            alipayMap =
                sellerService.buildAlipayReq(alipayOrderInfoPojo.getOutTradeNo(), subject,
                    alipayOrderInfoPojo.getTotalFee(), body, show_url);

          } else if (2 == payMethod) {
            // 生成未付款微信支付信息
            WxpayOrderInfoPojo wxpay = new WxpayOrderInfoPojo();
            wxpay.setOutTradeNo(out_trade_no);
            // 单位分
            wxpay.setTotalFee(SellerService.doubleToFee(allOrderPrice));
            wxpay.setTradeStatus("WAIT_BUYER_PAY");
            wxpay.setVersion(0);
            wxpayOrderInfoService.insertPay(wxpay);

            // 微信支付
            subject = "拼得好-订单号:" + outTradeNo;
            // 总长32，超出29长度截掉
            if (subject.length() > 29) {
              subject = subject.substring(0, 29) + "...";
            }
            // 超出500长度截掉
            if (body.length() > 500) {
              body = body.substring(0, 500) + "...";
            }
            wxpayMap =
                sellerService.buildWxpayReq(null, wxpay.getOutTradeNo(), null, body, subject,
                    Integer.valueOf(wxpay.getTotalFee()), "");
            wxpayMap.put("out_trade_no", out_trade_no);
          }
        } else {
          // 钱包全额付款成功
          walletService.processPaySuccess(out_trade_no);

          fullpay = 1;
        }

        success = true;
        // map.put("result", 1);
        UserWalletPojo wallet = userWalletService.findUserWalletByUserId(uid);
        Double balance = 0.00;
        if (wallet != null && wallet.getBalance() != null) {
          balance = wallet.getBalance();
        }
        result.put("fullpay", fullpay);
        result.put("balance", df.format(balance));
        result.put("aplipay", alipayMap);
        result.put("wxpay", wxpayMap);
      }
    }
    map.put("result", result);
    map.put("success", success);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 同步检查是否有活动库存(有SKU)
   * 
   * @param id
   * @param num
   * @return
   * @throws SQLException
   */
  protected boolean updateActivitySkuStock(CartPojo cart) throws SQLException {
    if (cart.getSkuLinkId() == null || cart.getSkuLinkId() == 0) {
      return false;
    }
    synchronized (AppApiWalletAction.class) {
      ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
      productSkuLink.setId(Long.valueOf(cart.getSkuLinkId()));
      productSkuLink.setProductId(cart.getProductId());
      productSkuLink = productSkuLinkService.findProductSkuLink(productSkuLink);
      if (productSkuLink != null && productSkuLink.getPrice() != null
          && productSkuLink.getPrice().doubleValue() != 0 && productSkuLink.getStock() > 0) {
        // 活动数量<购买数
        if (cart.getNum() > productSkuLink.getStock()) {
          cart.setNum(productSkuLink.getStock());
          cart.setStockPrice(productSkuLink.getPrice());
        }
      } else {
        // 活动结束/无活动
        return false;
      }

      // 提交订单时更新库存
      productSkuLink.setStock(productSkuLink.getStock() - cart.getNum());
      productSkuLinkService.productSkuLinkUpdate(productSkuLink);
      return true;
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
  protected boolean updateActivityStock(CartPojo cart) throws SQLException {
    synchronized (AppApiWalletAction.class) {
      ActivityGoodsPojo activityGoods =
          sellerService.isProductActivity(cart.getProductId(), cart.getActivityId());
      // 活动信息齐全且有库存
      if (activityGoods != null && activityGoods.getActivePrice() != null
          && activityGoods.getActivePrice().doubleValue() != 0
          && activityGoods.getActivityStock() != null && activityGoods.getActivityStock() > 0) {
        // 活动数量<购买数
        if (cart.getNum() > activityGoods.getActivityStock().intValue()) {
          cart.setNum(activityGoods.getActivityStock().intValue());
          cart.setStockPrice(activityGoods.getActivePrice());
        }
      } else {
        // 活动结束/无活动
        return false;
      }

      // 提交订单时更新库存
      activityGoods.setActivityStock(activityGoods.getActivityStock() - cart.getNum());
      activityGoodsService.updateActivityGoods(activityGoods);
      return true;
    }
  }

  /**
   * @return 订单支付
   * @throws Exception
   */
  public String payOrder() throws Exception {
    String error_msg = "";
    boolean b = false;
    boolean isPay = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, String> alipayMap = new HashMap<String, String>();
    Map<String, Object> wxpayMap = new HashMap<String, Object>();
    SysLoginPojo loginPojo = sysLoginService.findSysLoginById(uid);
    DecimalFormat df = new DecimalFormat("#.##");
    int fullpay = 0;
    if (uid == null) {
      error_msg = "uid不能为空";
    } else if (loginPojo == null) {
      error_msg = "没有该会员";
    } else if (orderNo == null || "".equals(orderNo)) {
      error_msg = "订单号不能为空";
    } else if (payMethod != 1 && payMethod != 2 && payMethod != 4) {
      error_msg = "支付方式错误";
    } else {
      OrderPojo order = orderService.findOrderByOrderNo(orderNo);

      String subject = "";
      String body = "";
      Double price = 0.0;
      // 待支付状态
      if (order != null && 1 == order.getOrderStatus()) {
        // 查看原支付流水号是否返回结果
        String outTradeNoOld = order.getOutTradeNo();
        if (1 == order.getPayMethod()) {
          AlipayOrderInfoPojo alipay = alipayOrderInfoService.findPayInfoByTradeNo(outTradeNoOld);
          if (alipay != null && "WAIT_BUYER_PAY".equals(alipay.getTradeStatus())) {
            // error_msg = "等待支付结果返回中";
            // isPay = true;
            alipay.setTradeStatus("TRADE_FAIL");
            alipay.setRemarks("cancel:" + outTradeNoOld);
            alipayOrderInfoService.updateOrder(alipay);
          }
        } else if (2 == order.getPayMethod()) {
          WxpayOrderInfoPojo wxpay = wxpayOrderInfoService.findPayInfoByTradeNo(outTradeNoOld);
          if (wxpay != null && "WAIT_BUYER_PAY".equals(wxpay.getTradeStatus())) {
            // error_msg = "等待支付结果返回中";
            // isPay = true;
            wxpay.setTradeStatus("TRADE_FAIL");
            wxpay.setRemarks("cancel:" + outTradeNoOld);
            wxpayOrderInfoService.updatePay(wxpay);
          }
        } else {
          error_msg = "原支付方式不为在线支付";
          isPay = true;
        }
        if (!isPay) {
          // 更新支付流水号
          // 商户订单号--支付宝
          String out_trade_no = UtilDate.getOrderNum() + UtilDate.getThree();
          Map<String, Object> orderMap = new HashMap<String, Object>();
          orderMap.put("orderId", order.getId());
          orderMap.put("outTradeNo", out_trade_no);
          orderMap.put("payMethod", payMethod);
          orderService.updateOutTradeNo(orderMap);
          // 订单实收价格
          price = order.getFactPrice();
          // 判断优惠券使用
          double m = 0.0;
          orderMap.clear();
          orderMap.put("orderId", order.getId());
          orderMap.put("status", 1);
          List<CouponOrderPojo> couponOrders = couponService.getcouponOrderList(orderMap);
          if (couponOrders != null && couponOrders.size() > 0) {
            CouponOrderPojo couponOrder = couponOrders.get(0);
            orderMap.clear();
            orderMap.put("couponNo", couponOrder.getCouponNo());
            UserCouponPojo userCouponPojo = couponService.getUserCouponByNo(orderMap);
            if (userCouponPojo != null) {
              org.json.JSONObject json = new org.json.JSONObject(userCouponPojo.getContent());
              if (userCouponPojo.getType() == 1 && json.get("om") != null) {
                double orderPay = json.getDouble("om");
                if (json.get("m") != null && orderPay <= order.getFactPrice()) {
                  m = json.getDouble("m");
                }
              } else if (userCouponPojo.getType() == 2 && json.get("m") != null) {
                m = json.getDouble("m");
              }
            }
          }
          // 减去优惠券金额
          price = price - m;
          // 减去已用钱包支付的金额
          price = price - order.getWalletPrice();
          List<Map<String, Object>> orders = new ArrayList<Map<String, Object>>();
          Map<String, Object> ordermap = new HashMap<String, Object>();
          ordermap.put("orderId", order.getId());
          ordermap.put("price", price);
          orders.add(ordermap);
          // 判断钱包使用
          if (4 == payMethod) {
            price = walletService.useWalletPay(uid, price, orders, out_trade_no, 1);
          }

          if (4 == payMethod && price > 0.00) {
            b = false;
            error_msg = "对不起，钱包余额不足！";
          } else if (4 == payMethod && price == 0.00) {
            fullpay = 1;
            // 钱包全额付款成功
            walletService.processPaySuccess(out_trade_no);

            b = true;
          } else {
            // 订单号
            subject = "拼得好-订单号:" + order.getOrderNo();
            List<OrderDetailPojo> orderDetails = orderDetailService.getOrderDetail(order.getId());
            for (OrderDetailPojo orderDetailPojo : orderDetails) {
              // 订单明细
              body += orderDetailPojo.getProductName() + ";";
            }
            // 1-支付宝支付
            if (1 == payMethod) {
              // 生成一条未付款支付宝信息
              AlipayOrderInfoPojo alipayOrderInfoPojo = new AlipayOrderInfoPojo();
              alipayOrderInfoPojo.setOutTradeNo(out_trade_no);
              alipayOrderInfoPojo.setTotalFee(df.format(price));
              alipayOrderInfoPojo.setTradeStatus("WAIT_BUYER_PAY");
              alipayOrderInfoPojo.setCreateDate(new Date());
              alipayOrderInfoPojo.setVersion(0);
              alipayOrderInfoService.insertOne(alipayOrderInfoPojo);

              // 组装支付宝支付串
              // 总长128，超出120长度截掉
              if (subject.length() > 120) {
                subject = subject.substring(0, 120) + "...";
              }
              String show_url = "http://www.alipay.com/";
              // 超出500长度截掉
              if (body.length() > 250) {
                body = body.substring(0, 250) + "...";
              }
              alipayMap =
                  sellerService.buildAlipayReq(alipayOrderInfoPojo.getOutTradeNo(), subject,
                      alipayOrderInfoPojo.getTotalFee(), body, show_url);

            } else if (2 == payMethod) {
              // 生成未付款微信支付信息
              WxpayOrderInfoPojo wxpay = new WxpayOrderInfoPojo();
              wxpay.setOutTradeNo(out_trade_no);
              // 单位分
              wxpay.setTotalFee(SellerService.doubleToFee(price));
              wxpay.setTradeStatus("WAIT_BUYER_PAY");
              wxpay.setVersion(0);
              wxpayOrderInfoService.insertPay(wxpay);

              // 微信支付
              // 总长32，超出29长度截掉
              if (subject.length() > 29) {
                subject = subject.substring(0, 29) + "...";
              }
              // 超出500长度截掉
              if (body.length() > 500) {
                body = body.substring(0, 500) + "...";
              }
              wxpayMap =
                  sellerService.buildWxpayReq(null, wxpay.getOutTradeNo(), null, body, subject,
                      Integer.valueOf(wxpay.getTotalFee()), "");
              wxpayMap.put("out_trade_no", out_trade_no);
            }
            b = true;
          }
        }
      } else {
        error_msg = "订单号未找到或已付款";
      }
    }
    UserWalletPojo wallet = userWalletService.findUserWalletByUserId(uid);
    Double balance = 0.00;
    if (wallet != null && wallet.getBalance() != null) {
      balance = wallet.getBalance();
    }
    result.put("fullpay", fullpay);
    result.put("balance", df.format(balance));
    result.put("aplipay", alipayMap);
    result.put("wxpay", wxpayMap);
    map.put("result", result);
    map.put("success", b);
    map.put("error_msg", error_msg);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 
   * 取消订单
   * */
  public String cancelOrder() {
    String msg = "";
    boolean b = false;
    int result = 0;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    if (oid == null || oid.equals("")) {
      msg = "订单ID不能为空哒，(づ￣ 3￣)づ";
    } else {
      OrderPojo orderqry = null;
      OrderPojo orderPojo = new OrderPojo();
      CouponOrderPojo couponOrderPojo = new CouponOrderPojo();
      UserCouponPojo userCouponPojo = new UserCouponPojo();
      orderPojo.setId(oid.longValue());
      orderPojo.setIsCancelOrder(1);
      map1.put("orderId", oid.longValue());
      map1.put("status", 1);
      List<CouponOrderPojo> couponOrderPojos = null;
      couponOrderPojo.setOrderId(oid.longValue());
      couponOrderPojo.setStatus(0);
      try {
        orderService.updateIsCancelOrder(orderPojo);// 根据订单ID修改是否取消状态
        orderqry = orderService.getfindByIdOrder(oid.longValue());
        if (orderqry != null && orderqry.getOrderStatus() == 1) {
          // 待付款状态取消订单，恢复优惠券
          couponOrderPojos = couponService.getcouponOrderList(map1);
          if (couponOrderPojos != null && couponOrderPojos.size() > 0) {
            String couponNo = couponOrderPojos.get(0).getCouponNo();
            couponOrderPojo.setCouponNo(couponNo);
            couponService.updateCouponOrderStatus(couponOrderPojo);
            userCouponPojo.setCouponNo(couponNo);
            userCouponPojo.setUsed(0);
            userCouponPojo.setUseTime(0L);
            userCouponPojo.setStatus(1);
            couponService.updateUserCoupon(userCouponPojo);
          }

          // 取消订单恢复钱包金额
          if (orderqry.getWalletPrice() != null && orderqry.getWalletPrice() > 0) {
            walletService.recoverWalletPay(orderqry.getUserId(), orderqry.getId(),
                orderqry.getWalletPrice());
          }
        }
        msg = "取消订单成功！";
        b = true;
        result = 1;
      } catch (Exception e) {
        e.printStackTrace();
        msg = "取消失败哒，或者没有该条订单！";
      }
    }
    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json1 = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json1.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 根据orderId获取退货的商家信息
   * 
   * @return
   * @throws Exception
   */
  public String getRefSellerInfoByOrderId() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (oid == null || "".equals(oid)) {
      msg = "订单详情ID不能为空哦！";
    } else {
      OrderDetailPojo orderDetail = orderDetailService.findOrderDetailById((long) oid);
      if (orderDetail != null) {
        OrderPojo order = orderService.getfindByIdOrderWeb(orderDetail.getOrderId());
        if (order != null) {
          DeliveryAddressPojo delivery = null;
          SpecialShowPojo show =
              specialShowService.findSpecialShowByActivityId(order.getActivityId());
          if (show != null && show.getRefundAddrId() != null && show.getRefundAddrId() > 0) {
            // 查询专场退货地址
            delivery = deliveryAddressService.findDeliveryAddressById(show.getRefundAddrId());
          }
          if (delivery == null) {
            DeliveryAddressPojo param = new DeliveryAddressPojo();
            param.setUserId(order.getSuserId());
            List<DeliveryAddressPojo> deliverys =
                deliveryAddressService.deliveryAddressAllList(param, null);
            if (deliverys != null && deliverys.size() > 0) {
              delivery = deliverys.get(0);
            }
          }
          if (delivery != null) {
            // 查询退货地址
            result.put("name", delivery.getConsignee() == null ? "" : delivery.getConsignee());
            String addr =
                delivery.getProvinceName() == null ? "" : delivery.getProvinceName()
                    + delivery.getCityName() == null ? "" : delivery.getCityName()
                    + delivery.getAreaName() == null ? "" : delivery.getAreaName()
                    + delivery.getAddress() == null ? "" : delivery.getAddress();
            result.put("address", addr);
            result.put("phone",
                delivery.getConsigneePhone() == null ? "" : delivery.getConsigneePhone());
            b = true;
          } else {
            // 查询公司地址
            ManufacturerPojo manufacturer =
                manufacturerService.findManufacturerByUserId(order.getSuserId());
            if (manufacturer != null) {
              result
                  .put("name", manufacturer.getCompany() == null ? "" : manufacturer.getCompany());
              result.put("address",
                  manufacturer.getAddress() == null ? "" : manufacturer.getAddress());
              result.put("phone", manufacturer.getPhone() == null ? "" : manufacturer.getPhone());
              b = true;
            } else {
              msg = "查不到该用户信息哦！";
            }
          }
        } else {
          msg = "查不到该订单信息哦！";
        }
      } else {
        msg = "查不到该订单详情信息哦！";
      }
    }
    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 极验验证码.
   * 
   * @return
   * @throws Exception
   */
  public String getValidCode() throws Exception {
    HttpServletRequest request = ServletActionContext.getRequest();
    String resStr = GeetestUtil.getGeetestCaptcha(request);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(resStr);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 刷新黑名单用户数据.
   */
  public void reflushBlackUser() {
    WalletService.getBlackUserId();
  }

  /**
   * 刷新黑名单前缀.
   */
  public void reflushBlackWord() {
    WalletService.getBlackWord();
  }

  /**
   * 苹果判断.
   * 
   * @param request
   * @return
   */
  public boolean appleFlag() {
    HttpServletRequest request = ServletActionContext.getRequest();
    String agent = request.getHeader("User-Agent");
    if (agent.contains("MailWorldClient")) {
      return true;
    }
    return false;
  }

  /**
   * app接口异常处理
   * 
   * @return
   */
  public String appApiError() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("result", "");
    map.put("error_msg", "系统繁忙，请稍后再试！");
    map.put("success", false);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
