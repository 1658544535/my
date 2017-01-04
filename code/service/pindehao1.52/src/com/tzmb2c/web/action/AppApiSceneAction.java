package com.tzmb2c.web.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.ConstParam;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.business.service.WalletService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityGoodsPojo;
import com.tzmb2c.web.pojo.AgencyPojo;
import com.tzmb2c.web.pojo.BrandDicPojo;
import com.tzmb2c.web.pojo.HistoryPojo;
import com.tzmb2c.web.pojo.HomePageIconPojo;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.ProductCommentPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.ScenePojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SpecialShowPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
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

public class AppApiSceneAction extends SuperAction {
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

    String url = ConstParam.URL + "/upfiles/scene/";
    if (scenePojos != null && scenePojos.size() > 0) {
      for (ScenePojo u : scenePojos) {
        detail = new HashMap<String, Object>();
        detail.put("sceneId", u.getId() == null ? 0 : u.getId());
        detail.put("activityId", u.getActivityId() == null ? 0 : u.getActivityId());
        detail.put("name", u.getName());
        detail.put("image", url + u.getImage());
        detail.put("beginTime", u.getBeginTimeStr());
        detail.put("endTime", u.getEndTimeStr());
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
   * 
   * 订单评价
   * 
   * @throws SQLException
   * */
  public String comment() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();

    if (uid == null || uid.equals("")) {
      map.put("error_msg", "会员Id不能为空！");
      map.put("success", false);
      map.put("result", null);
    } else if (oid == null || oid.equals("")) {
      map.put("error_msg", "订单id不能为空！");
      map.put("success", false);
      map.put("result", null);
    } else if (orderIds == null || orderIds.equals("")) {
      map.put("error_msg", "详情id与评价内容/分数不能为空！");
      map.put("success", false);
      map.put("result", null);
    } else if (scoreService == null || scoreService.equals("")) {
      map.put("error_msg", "卖家服务态度分数不能为空！");
      map.put("success", false);
      map.put("result", null);
    } else if (scoreSspeed == null || scoreSspeed.equals("")) {
      map.put("error_msg", "卖家发货速度分数不能为空！");
      map.put("success", false);
      map.put("result", null);
    } else {

      // 根据用户Id查询到当前用户所有信息
      SysLoginPojo loginPojo = sysLoginService.findSysLoginById(uid);
      // 根据订单ID得到当前订单详情
      OrderPojo order = orderService.findOrderById((long) oid);
      if (order.getOrderStatus() == 5) {
        map.put("error_msg", "该订单已经评价！");
        map.put("success", false);
        map.put("result", 2);
      } else {
        try {

          String[] sourceStrArray = orderIds[0].split(",");
          for (int i = 0; i < sourceStrArray.length; i++) {
            // 分解字符串分别得到订单ID和评论
            String[] strings = sourceStrArray[i].split(":");
            long ids = Integer.parseInt(strings[0]);
            String content = strings[1];

            // 分解字符串分别得到分数
            int score = Integer.parseInt(strings[2]);

            // 评论为空时
            if ("".equals(content)) {
              int s = sellerService.returnValueByScore(score);
              if (s == 1) {
                content = "差评！ 还要继续努力！";
              } else if (s == 2) {
                content = "中评！还不错！";
              } else {
                content = "好评！很不错！";
              }
            }

            ProductCommentPojo productCommentPojo = new ProductCommentPojo();
            productCommentPojo.setUserId(loginPojo.getId());// 设置用户id
            productCommentPojo.setUserName(loginPojo.getName());// 设置用户昵称user_name
            productCommentPojo.setCreateDate(new Date());// 设置评论时间
            productCommentPojo.setCreateBy(loginPojo.getId());// 设置评论者
            // productCommentPojo.setOrderId(orderId);// 设置订单ID
            // productCommentPojo.setProductId(productId);// 设置产品ID
            // productCommentPojo.setComment(comment);// 设置评价内容
            productCommentPojo.setComment(content);
            productCommentPojo.setOrderId((long) oid);
            OrderDetailPojo p = orderDetailService.findOrderDetailById(ids);
            productCommentPojo.setProductId(p.getProductId());// 设置产品ID
            productCommentPojo.setOrderNo(order.getOrderNo());
            // 插入时为1，可审核
            productCommentPojo.setStatus(1);

            productCommentPojo.setScoreProduct(score);
            productCommentPojo.setScoreService(Integer.parseInt(scoreService));
            productCommentPojo.setScoreSspeed(Integer.parseInt(scoreSspeed));
            productCommentPojo.setScore(sellerService.returnValueByScore(score));
            // 添加sku_link_id
            if (p != null && p.getSkuLinkId() != null) {
              productCommentPojo.setSkuLinkId(p.getSkuLinkId());
            }
            // productCommentPojo.setSkuLinkId(p.getSkuLinkId() ==
            // null ? 0 : p.getSkuLinkId());
            productCommentService.addUserComment(productCommentPojo);
            OrderPojo orderPojo = new OrderPojo();
            orderPojo.setOrderStatus(5);
            orderPojo.setId((long) oid);
            orderService.updateOrderStatus(orderPojo);// 修改订单状态为已评价

            // product添加product_commt
            ProductPojo userBrandId = null;
            String productCommt = sellerService.returnproductCommtByPid(p.getProductId());
            if (productCommt != "") {
              ProductPojo productPojo = new ProductPojo();
              productPojo.setId(p.getProductId());
              productPojo.setProductCommt(productCommt);
              productService.productUpdate(productPojo);

              userBrandId = productService.findProduct(productPojo);
            }

            // user_brand添加product_commt,seller_commt/deliver_commt,logistics_commt
            if (userBrandId != null) {
              UserBrandPojo userBrand =
                  sellerService.returnproductCommtByBid(userBrandId.getUserBrandId());
              if (userBrand != null) {
                UserBrandPojo userBrandPojo = new UserBrandPojo();
                userBrandPojo.setId(userBrandId.getUserBrandId());
                userBrandPojo.setProductCommt(userBrand.getProductCommt() + "");
                userBrandPojo.setDeliverCommt(userBrand.getDeliverCommt() + "");
                userBrandPojo.setLogisticsCommt(userBrand.getLogisticsCommt() + "");
                userBrandService.updateUserBrand(userBrandPojo);
              }
            }
          }
          map.put("result", 1);
          map.put("success", true);
          map.put("error_msg", "评价成功");

        } catch (Exception e) {
          e.printStackTrace();
          map.put("result", 0);
          map.put("success", false);
          map.put("error_msg", "评价失败");
        }
      }
    }
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
   * 产品详情评价
   * 
   * @throws Exception
   * */
  public String productDetailComment() throws Exception {
    boolean b = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> listComment = new ArrayList<Object>();
    if (id == null || id.equals("")) {
      msg = "产品Id不能为空！";
    } else {
      // 根据产品Id查询商品
      ProductPojo productPojo = new ProductPojo();
      productPojo.setId(id);
      productPojo = productService.findProduct(productPojo);
      if (productPojo != null && productPojo.getStatus() == 1) {
        if (pageNo == null || pageNo.equals("")) {
          page = new Pager();
          page.setPageNo(1);
        } else {
          page = new Pager();
          page.setPageNo(pageNo);
        }
        page.setPageSize(10);
        // 根据产品ID查询评价
        ProductCommentPojo productCommentPojo = new ProductCommentPojo();
        productCommentPojo.setProductId(productPojo.getId());
        if (uid != null && !uid.equals("")) {
          productCommentPojo.setUserId(uid);
        }
        if (score != null && !score.equals("")) {
          productCommentPojo.setScore(score);
        }
        productCommentPojo.setStatus(1);
        List<ProductCommentPojo> pclist =
            productCommentService.productCommentAllListWeb(productCommentPojo, page);
        if (pclist.size() == 0) {
          msg = "该产品没有评价！";
        }
        Map<String, Object> mapComment = null;
        for (ProductCommentPojo productCommentPojo2 : pclist) {
          mapComment = new HashMap<String, Object>();
          mapComment.put("id", productCommentPojo2.getId());// 编号
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          String date = simpleDateFormat.format(productCommentPojo2.getCreateDate());
          mapComment.put("time", date);// 评价时间
          mapComment.put("comment", productCommentPojo2.getComment());// 评价内容
          // mapComment.put("userName",
          // productCommentPojo2.getUserName());// 评价人
          mapComment.put("userName", productCommentPojo2.getLoginName() == null ? ""
              : WalletService.enCodeString(productCommentPojo2.getLoginName(), 4, 6));// 评价人

          mapComment.put("productScore", productCommentPojo2.getScoreProduct() == null ? "5"
              : productCommentPojo2.getScoreProduct());

          // //productCommt
          // ProductPojo p = new ProductPojo();
          // p.setId(productCommentPojo2.getProductId());
          // p.setStatus(1);
          // ProductPojo product = productService.findProduct(p);
          // if (product != null) {
          // mapComment.put("productCommt",
          // product.getProductCommt());
          // }else{
          // mapComment.put("productCommt", "5");
          // }

          // 商品sku显示
          Integer skuid = productCommentPojo2.getSkuLinkId();
          ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
          ProductSkuLinkPojo productSkuLinkPojo = null;
          if (skuid != null) {
            productSkuLink.setId(Long.valueOf(skuid));
            productSkuLinkPojo = productSkuLinkService.findProductSkuLink(productSkuLink);
            if (productSkuLinkPojo != null) {
              mapComment.put("skuValue", "颜色:" + productSkuLinkPojo.getColorValue() + ";尺码:"
                  + productSkuLinkPojo.getFormatValue());
            } else {
              mapComment.put("skuValue", "");
            }
          } else {
            mapComment.put("skuValue", "");
          }

          listComment.add(mapComment);
        }
      } else {
        msg = "没有该商品信息，或者已经下架！";
      }
      b = true;
    }
    map.put("result", listComment);
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
   * 首页图标接口
   * 
   * @return
   * @throws Exception
   */
  public String homePageIcon() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 1);
    List<HomePageIconPojo> homePageIconPojo = homePageIconService.findHomePageIconList(option);
    List<Object> list = new ArrayList<Object>();
    Map<String, Object> result = null;
    if (homePageIconPojo.size() > 0) {
      int count = 0;
      for (HomePageIconPojo h : homePageIconPojo) {
        if (count == 4) {
          break;
        }
        result = new HashMap<String, Object>();
        result.put("image", ConstParam.URL + "/upfiles/icon/" + h.getImage());
        result.put("title", h.getTitle() == null ? "" : h.getTitle());
        list.add(result);
        count++;
      }
      b = true;
    } else {
      msg = "未找到设置信息!";
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

        Date isbeginTime = null, isendTime = null;
        isbeginTime =
            activityGoodsPojo.getBeginTime() == null ? null : StringUtil
                .stringDate(activityGoodsPojo.getBeginTime());
        isendTime =
            activityGoodsPojo.getEndTime() == null ? null : StringUtil.stringDate(activityGoodsPojo
                .getEndTime());
        int isActive = sellerService.isActivityStart(isbeginTime, isendTime);
        map1.put("isActive", isActive);

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
   * 产品详情
   * 
   * @throws SQLException
   * */
  public String productDetailApi() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> mapresult = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    String msg = "";
    boolean success = false;
    if (id == null || id.equals("")) {
      msg = "产品ID不能为空！";
    } else {
      // 设置点击次数+1
      productService.updateHits(id);
      // String loginPojotype = "-1";
      if (uid == null) {
        uid = 0l;
      }
      // 根据用户Id查询到当前用户所有信息
      int loginPojo = sysLoginService.getfindCountByIdSysLogin(uid);
      // 根据产品Id查询商品
      ProductPojo productPojo = new ProductPojo();
      productPojo.setId(id);
      productPojo = productService.findProduct(productPojo);
      // 判断商品是否下架
      if (productPojo == null || productPojo.getStatus() == 0) {
        msg = "商品已经下架！";
      } else {
        UserBrandPojo ub = userBrandService.findUserBrandById(productPojo.getUserBrandId());

        if (activityId == null) {
          activityId = 0L;
        }
        if (loginPojo != 0) {
          // 用户存在，添加浏览记录
          sellerService.historyed(id, uid, activityId);
        }
        // 根据产品用户ID查询所属店铺
        ShopPojo shopPojo = new ShopPojo();
        shopPojo.setUserId(productPojo.getUserId());
        shopPojo = shopService.findShop(shopPojo);
        if (shopPojo == null) {
          shopPojo = new ShopPojo();
        }
        mapresult.put("name", productPojo.getProductName());// 产品名称
        mapresult.put("description", productPojo.getContent());// 产品详情
        mapresult.put("sid", shopPojo.getId());// 店铺ID
        // mapresult.put("shopName", shopPojo.getName());// 店铺名
        // mapresult.put("shopImage", ConstParam.URL + "/upfiles/shop" + File.separator +
        // shopPojo.getImages());// 店铺Logo

        mapresult.put("shopName", ub.getBrandName());// 品牌名
        mapresult.put("shopImage", ConstParam.URL + "/upfiles/businessCenter/brandDic"
            + File.separator + ub.getLogo());// 品牌Logo

        mapresult.put("sellNumber", productPojo.getSellNumber() + productPojo.getBaseNumber());// 销售数量
        mapresult.put("image",
            ConstParam.URL + "/upfiles/product" + File.separator + productPojo.getImage());// 产品主图片
        mapresult.put("isPower", productPojo.getIsPowerName());// 是否电动
        mapresult.put("pack", productPojo.getPackName());// 包装方式
        // mapresult.put("age", productPojo.getAgeName());// 适用年龄
        mapresult.put("ageDesc", productPojo.getAgeDesc() == null ? "" : productPojo.getAgeDesc());// 年龄描述
        mapresult.put("tag", productPojo.getTag() == null ? "" : productPojo.getTag());// 标签描述
        mapresult.put("weight", productPojo.getWeight());// 产品重量
        mapresult.put("location", productPojo.getLocation());// 产品产地
        mapresult.put("texture", productPojo.getTextureName());// 产品材质
        mapresult.put("model", productPojo.getProductNum());// 产品型号
        mapresult.put("status", productPojo.getStatus());// 产品状态0为未审核，1为审核
        mapresult.put("sellingPrice", df.format(productPojo.getSellingPrice()));
        mapresult.put("lowest_price",
            productPojo.getLowestPrice() == null ? "" : df.format(productPojo.getLowestPrice()));
        mapresult.put("brand", productPojo.getBrandName());
        // 店铺评分
        // mapresult.put("productCmmt", shopPojo.getProductCommt() == null ? "" :
        // shopPojo.getProductCommt());
        // mapresult.put("deliverCmmt", shopPojo.getDeliverCommt() == null ? "" :
        // shopPojo.getDeliverCommt());
        // mapresult.put("logisticsCmmt", shopPojo.getLogisticsCommt() == null ? "" :
        // shopPojo.getLogisticsCommt());
        // 品牌评分
        mapresult.put("productCmmt", ub.getProductCommt() == null ? "" : ub.getProductCommt());
        mapresult.put("deliverCmmt", ub.getDeliverCommt() == null ? "" : ub.getDeliverCommt());
        mapresult
            .put("logisticsCmmt", ub.getLogisticsCommt() == null ? "" : ub.getLogisticsCommt());
        mapresult.put("pCmmt",
            productPojo.getProductCommt() == null ? "" : productPojo.getProductCommt());

        // 是否包邮
        if (productPojo.getPostageType() == 1) {
          productPojo.setPostageTypeName("是");
        } else if (productPojo.getPostageType() == 0) {
          productPojo.setPostageTypeName("否");
        }
        mapresult.put("postageType", productPojo.getPostageTypeName());
        // 根据产品ID查询评价
        ProductCommentPojo productCommentPojo = new ProductCommentPojo();
        productCommentPojo.setProductId(productPojo.getId());
        // 只返回状态为1-审核通过
        productCommentPojo.setStatus(1);
        List<ProductCommentPojo> pclist =
            productCommentService.productCommentAllListWeb(productCommentPojo, page);
        mapresult.put("comCount", pclist == null ? 0 : pclist.size());// 商品评价数量

        // 是否有SKU
        int hasSku = sellerService.queryProductSkuCount(id, activityId);
        mapresult.put("skuFlag", hasSku > 0 ? 1 : 0);

        // 活动价格判断
        Double factPrice = productPojo.getDistributionPrice();
        // 活动类型
        int acType = 0;
        // 活动标题
        String acTitle = "";
        // 活动进行标志 0-未开始，1-进行中，2-已结束
        int isActive = 2;
        // 活动结束时间
        String endTime = "";
        // 活动产品判断
        if (activityId > 0) {
          ActivityGoodsPojo activityGoods =
              sellerService.isProductActivity(productPojo.getId(), activityId, null, null);
          if (activityGoods != null) {
            // 根据时间判断活动进行状态
            isActive = sellerService.checkActivityGoodStatus(activityGoods);
            acType =
                activityGoods.getActivityType() == null ? 0 : activityGoods.getActivityType() + 1;
            acTitle =
                activityGoods.getActivityTitle() == null ? "" : activityGoods.getActivityTitle();
            if (1 == acType) {
              acTitle = "限量购";
            }
            if (activityGoods.getActivePrice() != null
                && activityGoods.getActivePrice().doubleValue() != 0) {
              factPrice = activityGoods.getActivePrice();
            }
            if (isActive == 0 || isActive == 1) {
              endTime = activityGoods.getEndTime();
              // 活动无设置sku
              if (isActive == 1
                  && hasSku == 0
                  && !(activityGoods.getActivityStock() != null && activityGoods.getActivityStock() > 0)) {
                isActive = 2;
              }
            }
            if (activityGoods.getActivityType() == 3) {
              // 专场
              mapresult.put("sid", activityGoods.getTimeId());// 专场ID
              mapresult.put("shopName", activityGoods.getActivityTitle());// 专场名

              SpecialShowPojo specialShow =
                  specialShowService.findSpecialShowByActivityId(activityGoods.getTimeId());
              mapresult.put("shopImage", ConstParam.URL + "/upfiles/specialShow" + File.separator
                  + specialShow == null ? "" : specialShow.getBanner());// 专场Logo
              if (specialShow != null) {
                UserBrandPojo userBrand =
                    userBrandService.findUserBrandById(specialShow.getUserBrandId());
                if (userBrand != null) {
                  mapresult.put("shopName", userBrand.getBrandName());// 专场名
                  BrandDicPojo brand = brandDicService.findBrandDicById(userBrand.getBrandId());
                  if (brand != null) {
                    mapresult.put("shopImage", ConstParam.URL + "/upfiles/businessCenter/brandDic"
                        + File.separator + brand.getLogo());// 品牌Logo
                  }
                }
              }
            }
          }
        }
        mapresult.put("acType", acType);
        mapresult.put("acTitle", acTitle);
        mapresult.put("isActive", isActive);
        mapresult.put("endTime", endTime);

        mapresult.put("ladderPrice", df.format(factPrice));
        mapresult.put("discount",
            SellerService.calcDiscount(factPrice, productPojo.getSellingPrice()));

        Map<String, Object> mapHotProduct = null;
        List<Object> listHot = new ArrayList<Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("timeId", activityId);
        param.put("status", 1);
        param.put("proStatus", 1);
        List<ActivityGoodsPojo> activityGoods = activityGoodsService.findActivityGoodsList(param);
        if (activityGoods == null || activityGoods.size() == 0) {
          msg = "没有推荐商品！";
        }
        int count = 0;
        for (ActivityGoodsPojo activityGoodsPojo : activityGoods) {
          if (count >= 4) {
            break;
          }
          mapHotProduct = new HashMap<String, Object>();
          // 活动价格判断
          mapHotProduct.put("price", df.format(activityGoodsPojo.getActivePrice()));
          mapHotProduct.put("sellingPrice", df.format(activityGoodsPojo.getSellPrice()));
          mapHotProduct.put(
              "discount",
              SellerService.calcDiscount(activityGoodsPojo.getActivePrice(),
                  activityGoodsPojo.getSellPrice()));
          // 推荐商品价格
          mapHotProduct.put("image", ConstParam.URL + "/upfiles/product/small" + File.separator
              + activityGoodsPojo.getProductImage());// 推荐商品图片
          mapHotProduct.put("name", activityGoodsPojo.getProductName());// 推荐商品名
          mapHotProduct.put("productId", activityGoodsPojo.getProductId());// 推荐商品ID
          mapHotProduct.put("activityId", activityGoodsPojo.getTimeId());// 推荐活动ID
          listHot.add(mapHotProduct);
          count++;
        }
        mapresult.put("attribute", listHot);
        success = true;
      }
    }
    map.put("result", mapresult);
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
}
