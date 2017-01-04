package com.tzmb2c.web.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.ConstParam;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.business.service.WalletService;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityGoodsPojo;
import com.tzmb2c.web.pojo.ActivityTimePojo;
import com.tzmb2c.web.pojo.ActivityTitlePojo;
import com.tzmb2c.web.pojo.AgencyPojo;
import com.tzmb2c.web.pojo.BrandDicPojo;
import com.tzmb2c.web.pojo.HistoryPojo;
import com.tzmb2c.web.pojo.InfoPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.PagePushPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.PushHomePagePojo;
import com.tzmb2c.web.pojo.ScenePojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SpecialShowPojo;
import com.tzmb2c.web.pojo.UserBrandPojo;
import com.tzmb2c.web.pojo.UserCollectPojo;
import com.tzmb2c.web.pojo.UserConsumerCollectPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.ActivityProductService;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.ActivityTitleService;
import com.tzmb2c.web.service.AgencyService;
import com.tzmb2c.web.service.AlipayOrderInfoService;
import com.tzmb2c.web.service.BaiduLoginService;
import com.tzmb2c.web.service.BrandDicService;
import com.tzmb2c.web.service.CartService;
import com.tzmb2c.web.service.ConsumerService;
import com.tzmb2c.web.service.CouponService;
import com.tzmb2c.web.service.DeliveryAddressService;
import com.tzmb2c.web.service.HistoryService;
import com.tzmb2c.web.service.HomePageIconService;
import com.tzmb2c.web.service.InfoService;
import com.tzmb2c.web.service.LoginRecService;
import com.tzmb2c.web.service.LoginService;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.NavigationService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderRefundService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.OrderShipService;
import com.tzmb2c.web.service.PagePushService;
import com.tzmb2c.web.service.ProductCommentService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.PushHomePageService;
import com.tzmb2c.web.service.SceneProductService;
import com.tzmb2c.web.service.SceneService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SkuAttributeService;
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

public class AppApiHomePageAction extends SuperAction {
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
  private SceneService sceneService;
  @Autowired
  private SceneProductService sceneProductService;
  @Autowired
  private SkuAttributeService skuAttributeService;
  @Autowired
  private HomePageIconService homePageIconService;
  @Autowired
  private BrandDicService brandDicService;
  @Autowired
  private PagePushService pagePushService;
  @Autowired
  private PushHomePageService pushHomePageService;
  @Autowired
  private InfoService infoService;

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
  private String[] orderIds;
  private String scoreService;// 卖家服务态度分数
  private String scoreSspeed;// 卖家发货速度分数
  private Integer score;// 评价状态
  private ProductSkuLinkPojo productSkuLinkPojo;
  private ActivityTimePojo activityTimePojo;

  private Long timeId;// 秒杀时间id

  public ActivityTimePojo getActivityTimePojo() {
    return activityTimePojo;
  }

  public void setActivityTimePojo(ActivityTimePojo activityTimePojo) {
    this.activityTimePojo = activityTimePojo;
  }

  public ProductSkuLinkPojo getProductSkuLinkPojo() {
    return productSkuLinkPojo;
  }

  public void setProductSkuLinkPojo(ProductSkuLinkPojo productSkuLinkPojo) {
    this.productSkuLinkPojo = productSkuLinkPojo;
  }

  public String[] getOrderIds() {
    return orderIds;
  }

  public void setOrderIds(String[] orderIds) {
    this.orderIds = orderIds;
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

  public String getScoreService() {
    return scoreService;
  }

  public void setScoreService(String scoreService) {
    this.scoreService = scoreService;
  }

  public String getScoreSspeed() {
    return scoreSspeed;
  }

  public void setScoreSspeed(String scoreSspeed) {
    this.scoreSspeed = scoreSspeed;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Long getTimeId() {
    return timeId;
  }

  public void setTimeId(Long timeId) {
    this.timeId = timeId;
  }

  /**
   * 大牌驾到列表
   * 
   * @return
   * @throws SQLException
   */
  public String brandActivityList() throws SQLException {
    String msg = "";
    boolean success = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> all = new ArrayList<Map<String, Object>>();

    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 4);
    // 未删除
    option.put("isdelete", "0");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    option.put("currentTimeStr", sdf.format(new Date()));
    // 分页
    int pageSize = 21;
    if (pageNo == null || pageNo == 0) {
      option.put("pageNo", 0);
      option.put("pageSize", pageSize);
    } else {
      option.put("pageNo", (pageNo - 1) * pageSize);
      option.put("pageSize", pageSize);
    }


    List<PagePushPojo> brandbanner = pagePushService.findAdByType(0);
    if (brandbanner.size() != 0) {
      result.put("banner",
          brandbanner.get(0).getImages() == null || "".equals(brandbanner.get(0).getImages()) ? ""
              : ConstParam.URL + "/upfiles/notice" + File.separator
                  + brandbanner.get(0).getImages());
    } else {
      result.put("banner", "");
    }

    List<BrandDicPojo> brandDicPojos = brandDicService.findBrandActivityList(option);
    if (brandDicPojos.size() != 0) {
      Map<String, Object> item = null;
      for (BrandDicPojo b : brandDicPojos) {
        item = new HashMap<String, Object>();
        item.put("image", b.getLogo() == null || "".equals(b.getLogo()) ? "" : ConstParam.URL
            + "/upfiles/businessCenter/brandDic" + File.separator + b.getLogo());
        item.put("activityName", b.getTitle() == null ? "" : b.getTitle());
        item.put("activityId", b.getActivityId() == null ? "" : b.getActivityId());
        all.add(item);
      }
    } else {
      msg = "活动未开始，请耐心等待!";
    }
    result.put("brandlist", all);

    success = true;
    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", success);
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
   * 专场列表
   * 
   * @return
   * @throws SQLException
   * @throws ParseException
   */
  public String getSpecialShowListApi() throws SQLException, ParseException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> result = new ArrayList<Object>();

    if (id == null || id == 0) {
      msg = "ID不能为空哦~";
    } else if (type == null || "".equals(type)) {
      msg = "类目级别不能为空哦~";
    } else {
      Map<String, Object> option = new HashMap<String, Object>();
      if ("1".equals(type)) {
        option.put("mainCategory1", id);
      } else {
        option.put("mainCategory2", id);
      }
      option.put("status", 4);
      // 未删除
      option.put("isdelete", "0");
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      option.put("currentTimeStr", sdf.format(new Date()));
      // 分页
      int pageSize = 10;
      if (pageNo == null || pageNo == 0) {
        option.put("pageNo", 0);
        // option.put("pageSize", pageSize);
      } else {
        option.put("pageNo", (pageNo - 1) * pageSize);
        // option.put("pageSize", pageSize);
      }
      option.put("pageSize", pageSize);

      List<SpecialShowPojo> specialShowPojos = new ArrayList<SpecialShowPojo>();
      specialShowPojos = specialShowService.findSpecialShowList(option);
      Map<String, Object> item = null;
      Date end = null;
      Calendar cal = Calendar.getInstance();
      if (specialShowPojos.size() != 0) {
        for (SpecialShowPojo s : specialShowPojos) {
          item = new HashMap<String, Object>();
          // item.put("name", s.getName());
          item.put("name", s.getTitle());
          item.put("banner",
              ConstParam.URL + "/upfiles/specialShow" + File.separator + s.getBanner());
          item.put("activityId", s.getActivityId() == null ? "" : s.getActivityId());
          item.put("beginTime", s.getBeginTimeStr());
          // 特殊处理 + 8H
          cal.setTime(s.getEndTime());
          cal.add(Calendar.HOUR, 8);
          end = cal.getTime();
          item.put("endTime", sdf.format(end));
          item.put("endTime2", s.getEndTimeStr());
          int discountType = s.getDiscountType() == null ? 0 : s.getDiscountType();
          item.put("discountType", discountType);
          item.put("discountText",
              sellerService.transJsonToDiscountStr(discountType, s.getDiscountContext()));
          item.put("discount", s.getDiscount() == null || s.getDiscount() == 0
              || s.getDiscount() == 0.0 ? "" : s.getDiscount() + "折起");
          item.put("currentTime", StringUtil.dateString(new Date()));
          result.add(item);
        }
        b = true;
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
   * 专场产品列表
   * 
   * @return
   * @throws SQLException
   * @throws ParseException
   */
  public String getSpecialProductListApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (activityId == null || activityId == 0) {
      msg = "活动ID不能为空哦~";
    } else {
      Map<String, Object> option = new HashMap<String, Object>();
      // option.put("id", id);
      option.put("activityId", activityId);
      option.put("status", 4);
      // 未删除
      option.put("isdelete", "0");
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      option.put("currentTimeStr", sdf.format(new Date()));

      List<SpecialShowPojo> specialShowPojos = new ArrayList<SpecialShowPojo>();
      specialShowPojos = specialShowService.findSpecialShowList(option);
      Date end = null;
      Calendar cal = Calendar.getInstance();
      if (specialShowPojos.size() > 0) {
        SpecialShowPojo specialShowPojo = specialShowPojos.get(0);
        result.put("banner", ConstParam.URL + "/upfiles/specialShow" + File.separator
            + specialShowPojo.getBanner());
        result.put("introduction", specialShowPojo.getIntroduction());

        UserBrandPojo userBrand =
            userBrandService.findUserBrandById(specialShowPojo.getUserBrandId());
        if (userBrand != null) {
          result.put("introduction",
              userBrand.getBrandDisc() == null ? "" : userBrand.getBrandDisc());
        }
        int discountType =
            specialShowPojo.getDiscountType() == null ? 0 : specialShowPojo.getDiscountType();
        result.put("discountType", discountType);
        result.put("discountText", sellerService.transJsonToDiscountStr(discountType,
            specialShowPojo.getDiscountContext()));
        result.put("beginTime", specialShowPojo.getBeginTimeStr());
        // result.put("endTime", specialShowPojo.getEndTimeStr());
        // 特殊处理 + 8H
        cal.setTime(specialShowPojo.getEndTime());
        cal.add(Calendar.HOUR, 8);
        end = cal.getTime();
        result.put("endTime", sdf.format(end));
        result.put("endTime2", specialShowPojo.getEndTimeStr());
        result.put("currentTime", StringUtil.dateString(new Date()));

        option.clear();
        List<Object> list = new ArrayList<Object>();
        Map<String, Object> item = new HashMap<String, Object>();

        option.put("timeId", activityId);
        option.put("status", 1);
        option.put("proStatus", 1);
        if (source != null && source != 0) {
          option.put("sort", source);
        }
        // 分页
        int pageSize = 10;
        if (pageNo == null || pageNo == 0) {
          option.put("pageNo", 0);
          option.put("pageSize", pageSize);
        } else {
          option.put("pageNo", (pageNo - 1) * pageSize);
          option.put("pageSize", pageSize);
        }

        List<ActivityGoodsPojo> activityGoodsPojos =
            activityGoodsService.findActivityGoodsList(option);
        DecimalFormat df = new DecimalFormat("#.##");
        if (activityGoodsPojos.size() > 0) {
          for (ActivityGoodsPojo a : activityGoodsPojos) {
            item = new HashMap<String, Object>();
            item.put("productId", a.getProductId());
            item.put("image",
                ConstParam.URL + "/upfiles/product" + File.separator + a.getProductImage());
            item.put("banner",
                ConstParam.URL + "/upfiles/product" + File.separator + a.getProductImage());
            item.put("productName", a.getProductName());
            item.put("activePrice", df.format(a.getActivePrice()));
            item.put("activityId", a.getTimeId());
            item.put("sellPrice", df.format(a.getSellPrice()));
            list.add(item);
          }
        }
        result.put("productList", list);
        b = true;
      } else {
        msg = "不好意思，未找到活动信息!";
        b = false;
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
   * 场景活动列表
   * 
   * @return
   * @throws SQLException
   * @throws ParseException
   */
  public String getSceneList() throws SQLException, ParseException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> results = new ArrayList<Object>();
    Map<String, Object> detail = null;
    Map<String, Object> option = new HashMap<String, Object>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    option.put("type", 1);
    option.put("status", 1);
    // 未删除的
    option.put("isdelete", "0");
    option.put("currentTimeStr", sdf.format(new Date()));
    if (pageNo == null || pageNo <= 0) {
      pageNo = 1;
    }
    int pageSize = 8;
    option.put("pageNo", (pageNo - 1) * pageSize);
    option.put("pageSize", pageSize);
    List<ScenePojo> scenePojos = sceneService.findSceneList(option);

    String url = ConstParam.URL + "/upfiles/scene" + File.separator;
    if (scenePojos != null && scenePojos.size() > 0) {
      for (ScenePojo u : scenePojos) {
        detail = new HashMap<String, Object>();
        detail.put("sceneId", u.getId() == null ? 0 : u.getId());
        detail.put("activityId", u.getActivityId() == null ? 0 : u.getActivityId());
        detail.put("name", u.getName());
        detail.put("image", url + u.getImage());
        detail.put("beginTime", u.getBeginTimeStr());
        detail.put("endTime", u.getEndTimeStr());
        detail.put("currentTime", StringUtil.dateString(new Date()));
        results.add(detail);
      }

      b = true;
    } else {
      msg = "活动未开始，请耐心等待！";
    }
    map.put("result", results);
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
   * 首页专场列表
   * 
   * @return
   * @throws SQLException
   * @throws ParseException
   */
  public String getHomepgSpecialShowList() throws SQLException, ParseException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> result = new ArrayList<Object>();

    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 4);
    // 未删除
    option.put("isdelete", "0");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    option.put("currentTimeStr", sdf.format(new Date()));
    // 分页
    int pageSize = 10;
    if (pageNo == null || pageNo == 0) {
      option.put("pageNo", 0);
      // option.put("pageSize", pageSize);
    } else {
      option.put("pageNo", (pageNo - 1) * pageSize);
      // option.put("pageSize", pageSize);
    }
    option.put("pageSize", pageSize);

    List<SpecialShowPojo> specialShowPojos = new ArrayList<SpecialShowPojo>();
    specialShowPojos = specialShowService.findSpecialShowList(option);
    Map<String, Object> item = null;
    Date end = null;
    Calendar cal = Calendar.getInstance();
    if (specialShowPojos.size() != 0) {
      for (SpecialShowPojo s : specialShowPojos) {
        item = new HashMap<String, Object>();
        // item.put("name", s.getName());
        item.put("name", s.getTitle());
        item.put("banner", ConstParam.URL + "/upfiles/specialShow" + File.separator + s.getBanner());
        item.put("activityId", s.getActivityId() == null ? "" : s.getActivityId());
        item.put("beginTime", s.getBeginTimeStr());
        // 特殊处理 + 8H
        cal.setTime(s.getEndTime());
        cal.add(Calendar.HOUR, 8);
        end = cal.getTime();
        item.put("endTime", sdf.format(end));
        item.put("endTime2", s.getEndTimeStr());
        int discountType = s.getDiscountType() == null ? 0 : s.getDiscountType();
        item.put("discountType", discountType);
        item.put("discountText",
            sellerService.transJsonToDiscountStr(discountType, s.getDiscountContext()));
        item.put("discount", s.getDiscount() == null || s.getDiscount() == 0
            || s.getDiscount() == 0.0 ? "" : s.getDiscount() + "折起");
        item.put("currentTime", StringUtil.dateString(new Date()));
        result.add(item);
      }
    } else {
      msg = "活动未开始，请耐心等待!";
    }
    b = true;
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
   * 
   * 单个产品活动时间
   * 
   * @throws SQLException
   * */
  public String getProductActivityTime() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    if (productId == null) {
      msg = "产品id不能为空！";
    } else {
      if (activityId == null) {
        activityId = 0L;
      }
      ActivityGoodsPojo activityGoodsPojo =
          sellerService.isProductActivity(productId, activityId, null, null);
      if (activityGoodsPojo != null) {
        String beginTime = activityGoodsPojo.getBeginTime();
        String endTime = activityGoodsPojo.getEndTime();
        String activityDate = "";
        if (activityGoodsPojo.getActivityType() == 0 && activityGoodsPojo.getActivityDate() != null) {
          // 秒杀活动
          beginTime += ":00";
          endTime += ":00";
          activityDate = StringUtil.dateToString(activityGoodsPojo.getActivityDate());
        } else if (activityGoodsPojo.getActivityType() > 0) {
          beginTime =
              beginTime != null && beginTime.length() > 11 ? sdf.format(StringUtil
                  .stringDate(beginTime)) : "";
          endTime =
              endTime != null && endTime.length() > 11 ? sdf.format(StringUtil.stringDate(endTime))
                  : "";
          activityDate =
              StringUtil.dateToString(StringUtil.stringDate(activityGoodsPojo.getEndTime()));
        }
        map1.put("beginTime", beginTime);
        map1.put("endTime", endTime);
        map1.put("activityDate", activityDate);
        map1.put("currentTime", StringUtil.dateString(new Date()));
        b = true;
      } else {
        msg = "产品未参加活动！";
      }
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
   * 限时秒杀时间列表
   * 
   * @throws SQLException
   * */
  public String activityTimeList() throws SQLException {
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = null;
    List list1 = new ArrayList();
    Map<String, Object> param = new HashMap<String, Object>();
    // 只返回APP的活动
    param.put("channel", 1);
    // 查询秒杀活动
    param.put("op", 1);
    param.put("isdelete", "0");
    List<ActivityTimePojo> times = activityTimeService.findActivityTimeList(param);
    if (times != null && times.size() > 0) {
      int flag = 0;
      String url = ConstParam.URL + "/upfiles/activity" + File.separator;
      for (ActivityTimePojo time : times) {
        if (list1 != null && list1.size() >= 4) {
          break;
        }
        map1 = new HashMap<String, Object>();
        map1.put("title", time.getTitle());
        map1.put("activityDate", StringUtil.dateToString(time.getActivityDate()));
        map1.put("beginTime", time.getBeginTime());
        map1.put("endTime", time.getEndTime());
        map1.put("banner", time.getBanner() == null || "".equals(time.getBanner()) ? "" : url
            + time.getBanner());
        flag = isActivityStart(time.getBeginTime(), time.getEndTime(), time.getActivityDate());
        if (flag == 2) {
          continue;
        }
        // 0-未开始；1-进行中；2-已结束
        map1.put("status", flag);
        map1.put("timeId", time.getId());
        map1.put("currentTime", StringUtil.dateString(new Date()));

        list1.add(map1);
      }
    } else {
      msg = "无数据！";
    }

    map.put("result", list1);
    map.put("error_msg", msg);
    map.put("success", true);
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
   * 判断活动是否开始
   * 
   * @param beginTime 开始时间
   * @param endTime 结束时间
   * @param activityDate 活动日期
   * @return
   */
  public int isActivityStart(String beginTime, String endTime, Date activityDate) {
    // 0-未开始 ；1-进行中；2-已结束
    int flag = 0;
    if (activityDate == null) {
      return 2;
    }
    Date d1 = StringUtil.stringToDate(StringUtil.dateToString(new Date()));
    Date d2 = StringUtil.stringToDate(StringUtil.dateToString(activityDate));
    if (d1.compareTo(d2) < 0) {
      return 0;
    } else if (d1.compareTo(d2) > 0) {
      return 2;
    }
    String time = getCurrentTime();
    if (beginTime != null && time.compareTo(beginTime) < 0) {
      flag = 0;
    } else if (endTime != null && time.compareTo(endTime) >= 0) {
      flag = 2;
    } else {
      flag = 1;
    }
    return flag;
  }

  public String getCurrentTime() {
    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    String time = StringUtils.leftPad(String.valueOf(hour), 2, "0");
    time += ":" + StringUtils.leftPad(String.valueOf(minute), 2, "0");
    return time;
  }


  /**
   * 
   * 限时秒杀产品列表
   * 
   * @throws SQLException
   * */
  public String activityGoods() throws SQLException {
    boolean b = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();;
    Map<String, Object> map2 = null;
    List list1 = new ArrayList();
    DecimalFormat df = new DecimalFormat("#.##");
    List<ActivityGoodsPojo> goods = null;
    if (timeId == null) {
      msg = "timeId不能为空";
    } else {
      ActivityTimePojo activityTime = activityTimeService.findActivityTimeById(timeId);
      if (activityTime != null) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("status", 1);
        param.put("timeId", timeId);
        param.put("proStatus", 1);
        goods = activityGoodsService.findActivityGoodsList(param);

        ShopPojo shop = new ShopPojo();
        ShopPojo shopPojo = null;
        String url = ConstParam.URL + "/upfiles/product" + File.separator;
        String url2 = ConstParam.URL + "/upfiles/activity" + File.separator;
        if (goods != null && goods.size() > 0) {
          int flag = 0;
          map1.put(
              "activityDate",
              activityTime.getActivityDate() == null || "".equals(activityTime.getActivityDate()) ? ""
                  : StringUtil.dateToString(activityTime.getActivityDate()));
          map1.put("beginTime",
              activityTime.getBeginTime() == null || "".equals(activityTime.getBeginTime()) ? ""
                  : activityTime.getBeginTime());
          map1.put("endTime",
              activityTime.getEndTime() == null || "".equals(activityTime.getEndTime()) ? ""
                  : activityTime.getEndTime());
          map1.put("banner",
              activityTime.getBanner() == null || "".equals(activityTime.getBanner()) ? "" : url2
                  + activityTime.getBanner());
          flag =
              isActivityStart(activityTime.getBeginTime(), activityTime.getEndTime(),
                  activityTime.getActivityDate());
          // 0-未开始；1-进行中；2-已结束
          map1.put("status", flag);
          int[] stock = null;
          for (ActivityGoodsPojo good : goods) {
            map2 = new HashMap<String, Object>();
            shop.setUserId(good.getUserId());
            shopPojo = shopService.findShop(shop);
            if (shopPojo != null) {
              map2.put("shopId", shopPojo.getId());
              map2.put("shopName", shopPojo.getName());
            } else {
              map2.put("shopId", "");
              map2.put("shopName", "");
            }
            map2.put("productId", good.getProductId());
            map2.put("productName", good.getProductName());
            map2.put("productImage", url + good.getProductImage());
            map2.put("productPrice",
                good.getActivePrice() == null ? "" : df.format(good.getActivePrice()));
            map2.put("sellingPrice",
                good.getSellPrice() == null ? "" : df.format(good.getSellPrice()));

            // 判断是否有活动sku
            stock = getActivitySkuStock(timeId, good.getProductId());
            if (stock != null) {
              map2.put("activityNum", stock[0]);
              map2.put("activityStock", stock[1]);
            } else {
              map2.put("activityNum", good.getActivityNum() == null ? 0 : good.getActivityNum());
              map2.put("activityStock",
                  good.getActivityStock() == null ? 0 : good.getActivityStock());
            }

            map2.put("sorting", good.getSorting() == null ? "" : good.getSorting());
            map2.put("tips", good.getTips() == null ? "" : good.getTips());
            // sku返回
            // map2.put("skuLinkId", good.getSkuLinkId() == null ? "" : good.getSkuLinkId());
            // map2.put("skuValue", good.getSkuLinkStr() == null ? "" : good.getSkuLinkStr());
            map1.put("currentTime", StringUtil.dateString(new Date()));
            map2.put("currentTime", StringUtil.dateString(new Date()));
            list1.add(map2);
          }
          map1.put("products", list1);
          b = true;
        } else {
          msg = "无活动产品数据！";
        }
      } else {
        msg = "未找到该活动！";
      }

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
   * 统计sku库存信息.
   * 
   * @param activityId
   * @param productId
   * @return
   * @throws SQLException
   */
  public int[] getActivitySkuStock(Long activityId, Long productId) throws SQLException {
    int[] stock = null;
    ProductSkuLinkPojo skuLink = new ProductSkuLinkPojo();
    skuLink.setActivityId(activityId);
    skuLink.setProductId(productId);
    skuLink.setStatus(1);
    skuLink.setType(1);
    List<ProductSkuLinkPojo> skuLinks = productSkuLinkService.getProductSkuLinkAll(skuLink, null);
    if (skuLinks != null && skuLinks.size() > 0) {
      stock = new int[] {0, 0};
      for (ProductSkuLinkPojo productSkuLinkPojo : skuLinks) {
        stock[0] += productSkuLinkPojo.getStockNum();
        stock[1] += productSkuLinkPojo.getStock();
      }
    }

    return stock;
  }

  /**
   * @return 首页顶部推送图
   * @throws SQLException
   */
  public String homePageTop4List() throws SQLException {
    String msg = "";
    boolean b = false;
    List<Object> list = new ArrayList<Object>();
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> activitymap = new HashMap<String, Object>();
    activitymap.put("status", "1");
    activitymap.put("type", "0");
    List<PushHomePagePojo> pushHomePagePojoList = pushHomePageService.findAll(activitymap);
    if (pushHomePagePojoList == null || pushHomePagePojoList.size() == 0) {
      msg = "没有查询到活动数据！";
    } else {
      Map<String, Object> activiMap = null;
      String url = ConstParam.URL + "/upfiles/pushhomepg" + File.separator;
      for (PushHomePagePojo pushHomePagePojo : pushHomePagePojoList) {
        activiMap = new HashMap<String, Object>();
        activiMap.put("type", pushHomePagePojo.getType() == null ? "" : pushHomePagePojo.getType());
        activiMap.put("typeId",
            pushHomePagePojo.getActivityId() == null ? "" : pushHomePagePojo.getActivityId());
        activiMap.put("image",
            pushHomePagePojo.getImage() == null ? "" : url + pushHomePagePojo.getImage());
        activiMap.put("title",
            pushHomePagePojo.getTitle() == null ? "" : pushHomePagePojo.getTitle());
        activiMap.put("sorting",
            pushHomePagePojo.getSorting() == null ? "" : pushHomePagePojo.getSorting());
        list.add(activiMap);
      }
      b = true;
    }
    map.put("result", list);
    map.put("success", b);
    map.put("error_msg", msg);
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
   * 首页焦点图
   * 
   * @throws SQLException
   * */
  public String focus() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> list = new ArrayList<Object>();
    String msg = "";
    boolean b = false;
    // 根据类型查询首页焦点图
    List<PagePushPojo> list2 = pagePushService.findTopThreeDate(8);
    String type = "";
    Long id = 0L;
    for (PagePushPojo pagePushPojo : list2) {
      Map<String, Object> map1 = new HashMap<String, Object>();
      map1.put("image",
          ConstParam.URL + "/upfiles/notice" + File.separator + pagePushPojo.getImages());
      map1.put("id", pagePushPojo.getTitle());
      map1.put("subType", pagePushPojo.getUrl());
      type = pagePushPojo.getUrl();
      // 1-产品，2-店铺，3-列表，4-文字 5-web活动页
      if ("2".equals(type)) {
        id = Long.valueOf(pagePushPojo.getTitle());
        ActivityTimePojo activity = activityTimeService.findActivityTimeById(id);
        if (activity != null && StringUtils.isNotBlank(activity.getTitle())) {
          map1.put("title", activity.getTitle());
        }
      } else if ("3".equals(type) || "5".equals(type)) {
        id = Long.valueOf(pagePushPojo.getTitle());
        ActivityTitlePojo activity = activityTitleService.findActivityTitleById(id);
        if (activity != null && StringUtils.isNotBlank(activity.getTitle())) {
          map1.put("title", activity.getTitle());
        }
      } else if ("4".equals(type)) {
        id = Long.valueOf(pagePushPojo.getTitle());
        InfoPojo info = infoService.findInfoById(id);
        if (info != null && StringUtils.isNotBlank(info.getTitle())) {
          map1.put("title", info.getTitle());
        }
      }
      list.add(map1);
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


}
