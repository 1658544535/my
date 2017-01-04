package com.tzmb2c.web.action;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.alipay.util.UtilDate;
import com.tencent.WXPay;
import com.tencent.common.MD5;
import com.tencent.common.Signature;
import com.tencent.common.Util;
import com.tencent.protocol.pre_pay_protocol.PrePayReqData;
import com.tencent.protocol.pre_pay_protocol.PrePayResData;
import com.tzmb2c.business.service.ConstParam;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.business.service.WalletService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.PropertiesHelper;
import com.tzmb2c.utils.RandomUtils;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityGoodsPojo;
import com.tzmb2c.web.pojo.ActivityTimePojo;
import com.tzmb2c.web.pojo.AgencyPojo;
import com.tzmb2c.web.pojo.AlipayOrderInfoPojo;
import com.tzmb2c.web.pojo.BrandDicPojo;
import com.tzmb2c.web.pojo.CartPojo;
import com.tzmb2c.web.pojo.CouponOrderPojo;
import com.tzmb2c.web.pojo.DeliveryAddressPojo;
import com.tzmb2c.web.pojo.HistoryPojo;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.OrderRefundPojo;
import com.tzmb2c.web.pojo.ProductCommentPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SpecialShowPojo;
import com.tzmb2c.web.pojo.SysAreaPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserBrandPojo;
import com.tzmb2c.web.pojo.UserCollectPojo;
import com.tzmb2c.web.pojo.UserConsumerCollectPojo;
import com.tzmb2c.web.pojo.UserCouponPojo;
import com.tzmb2c.web.pojo.UserOrderRefundPojo;
import com.tzmb2c.web.pojo.UserWalletPojo;
import com.tzmb2c.web.pojo.WxpayOrderInfoPojo;
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

public class AppApiWalletActivityAction extends SuperAction {
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
  private ProductSkuLinkPojo productSkuLinkPojo;
  private ActivityTimePojo activityTimePojo;

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
        mapresult.put("isSelf", shopPojo.getSelfSupport() == null ? 0 : shopPojo.getSelfSupport());// 是否自营
                                                                                                   // 0否1是
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
            } else if (3 == acType) {
              acTitle = "钱包专区";
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

  public void historyed(Long id, Long uid) {
    // SysLoginPojo loginPojo = UserUtil.getWebUser();
    if (uid != null) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("userId", uid);
      map.put("type", 1);
      map.put("businessId", id);
      map.put("createBy", uid);
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

  /**
   * 根据产品id判断当前是否作活动中(无库存限制)
   * 
   * @param productId
   * @return
   * @throws SQLException
   */
  public ActivityGoodsPojo isProductActivity(Long productId, Long activityId) throws SQLException {
    ActivityGoodsPojo activityGoodsPojo =
        activityGoodsService.findActivityGoodsByPid(productId, activityId, null, 1);
    return activityGoodsPojo;
  }

  // 计算商品折扣
  protected String calcDiscount(Double factPrice, Double sellPrice) {
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
   * 
   * 直接购买
   * 
   * @throws SQLException
   * */
  public String addPurchase() throws SQLException {
    int isWallet = 0;// 0-为普通商品 或者 普通商品与钱包商品2者混合；1-为钱包商品
    Map<String, Object> resultAll = new HashMap<String, Object>();
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    List result = new ArrayList();

    // 根据商品Id得到商品信息
    ProductPojo proPojo = null;
    if (pid != null && pid > 0) {
      proPojo = new ProductPojo();
      proPojo.setId(pid);
      proPojo = productService.findProduct(proPojo);
    }
    SysLoginPojo sysLogin = null;
    if (uid != null && uid > 0) {
      sysLogin = sysLoginService.findSysLoginById(uid);
    }
    String msg = "";
    boolean b = false;
    if (uid == null || uid.equals("")) {
      msg = "会员ID是必须要填写哦！";
    } else if (sysLogin == null || sysLogin.getStatus() == 0) {
      msg = "该会员ID不存在哦！";
    } else if (pid == null || pid.equals("")) {
      msg = "商品ID是必须要填写哦！";
    } else if (num == null || num.equals("")) {
      msg = "购买数量是必须要填写哦！";
    } else if (proPojo == null || proPojo.getStatus() == 0) {
      msg = "商品已经下架！";
    } else {
      ActivityTimePojo activityTimePojo = null;
      // 根据产品信息中的用户ID查询到店铺
      ShopPojo shop = new ShopPojo();
      shop.setUserId(proPojo.getUserId());
      shop = shopService.findShop(shop);
      if (shop != null) {
        DecimalFormat df = new DecimalFormat("#.##");
        Double price = proPojo.getDistributionPrice();
        Double weight = 0.0;
        String allprice = "";
        boolean stockFlag = true;
        // 活动价格判断
        if (activityId == null) {
          activityId = 0L;
        }
        ActivityGoodsPojo activityGoods = null;
        ProductSkuLinkPojo productSkuLinkCheck = null;
        if (activityId > 0) {
          activityGoods = isProductActivity(proPojo.getId(), activityId);
          if (activityGoods == null) {
            stockFlag = false;
          }
        }
        if (stockFlag) {
          // sku 价格
          if (skuLinkId != null && skuLinkId > 0) {
            productSkuLinkCheck = getProducSku(proPojo.getId(), skuLinkId);
            if (productSkuLinkCheck != null) {
              if (productSkuLinkCheck.getPrice() != null && productSkuLinkCheck.getPrice() != 0) {
                price = productSkuLinkCheck.getPrice();
              }
              if (num > productSkuLinkCheck.getStock()) {
                stockFlag = false;
              }
            } else {
              stockFlag = false;
            }
          } else if (activityId > 0) {
            if (activityGoods != null && activityGoods.getActivePrice() != null
                && activityGoods.getActivePrice().doubleValue() != 0
                && activityGoods.getActivityStock() != null
                && num <= activityGoods.getActivityStock()) {
              price = activityGoods.getActivePrice();
            } else {
              stockFlag = false;
            }
          }
        }

        if (stockFlag) {
          allprice = df.format(price * num);
          Map<String, Object> map2 = new HashMap<String, Object>();
          List list = new ArrayList();
          map2.put("productId", proPojo.getId());
          map2.put("sellingPrice", df.format(proPojo.getSellingPrice()));
          map2.put("price", df.format(price));
          map2.put("discount", calcDiscount(price, proPojo.getSellingPrice()));
          map2.put("productName", proPojo.getProductName());
          map2.put("productNumber", num);
          map2.put("productImage", ConstParam.URL + "/upfiles/product/small/" + proPojo.getImage());
          map2.put("allPrice", allprice);
          map2.put("skuLinkId", skuLinkId == null ? "" : skuLinkId);
          if (skuLinkId != null) {
            ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
            productSkuLink.setId(Long.valueOf(skuLinkId));
            productSkuLink = productSkuLinkService.findProductSkuLink(productSkuLink);
            if (productSkuLink != null) {
              map2.put("skuValue", "颜色:" + productSkuLinkPojo.getColorValue() + ";尺码:"
                  + productSkuLinkPojo.getFormatValue());
            } else {
              map2.put("skuValue", "");
            }
          } else {
            map2.put("skuValue", "");
          }
          map2.put("activityId", activityId);
          // 商品活动类型
          if (activityId == 0) {
            // 普通商品
            map2.put("type", 0);
          } else {
            activityTimePojo = activityTimeService.findActivityTimeById(activityId);
            if (activityTimePojo != null && activityTimePojo.getType() == 0) {
              // 秒杀商品
              map2.put("type", 2);
            } else if (activityTimePojo != null && activityTimePojo.getType() == 1) {
              // 专题商品
              map2.put("type", 3);
              isWallet = 1;
            } else if (activityTimePojo != null && activityTimePojo.getType() == 2) {
              // 场景商品
              map2.put("type", 4);
            } else if (activityTimePojo != null && activityTimePojo.getType() == 3) {
              // 专场商品
              map2.put("type", 5);
            } else {
              // 活动商品
              map2.put("type", 1);
            }
          }
          list.add(map2);

          map1.put("shopLogo", ConstParam.URL + "/upfiles/shop/" + shop.getImages());
          map1.put("allCount", num);
          map1.put("sumPrice", allprice);
          weight += Double.valueOf(proPojo.getWeight()) * num;
          map1.put("espressPrice", df.format(calcFare(weight)));
          map1.put("shopId", shop.getId());
          map1.put("shopName", shop.getName());
          map1.put("products", list);
          result.add(map1);
          b = true;
        } else {
          msg = "对不起，您购买的数量大于该商品剩余库存！";
        }

      } else {
        msg = "未找到该产品店铺信息！";
      }
    }
    resultAll.put("result", result);
    resultAll.put("isWallet", isWallet);
    map.put("result", resultAll);
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

  /**
   * 计算偏远地区运费(>1kg,+15/kg,不足1kg按1kg算).
   * 
   * @param weight 重量
   * @return
   */
  public double calcFare(double weight) {
    double fare = 0.0;
    if (weight > 1.0) {
      weight = Math.ceil(weight - 1.0);
      fare = weight * 15.0;
    }
    return fare;
  }

  /**
   * 
   * 选中提交购物车
   * 
   * @throws SQLException
   * */
  public String orderProduct() throws SQLException {
    Map<String, Object> resultAll = new HashMap<String, Object>();
    int isWallet = 1;// 0-为普通商品 或者 普通商品与钱包商品2者混合；1-为钱包商品
    SysLoginPojo sysLogin = sysLoginService.findSysLoginById(uid);
    List<Object> list = new ArrayList<Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    String msg = "";
    boolean b = false;
    // 活动标志
    int acFlag = 0;
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid == null || uid == 0) {
      map.put("error_msg", "用户ID不能为空！");
      map.put("result", list);
      map.put("success", b);
    } else if (sysLogin == null || sysLogin.getStatus() == 0) {
      map.put("error_msg", "没有该用户！");
      map.put("success", b);
      map.put("result", list);
    } else {
      CartPojo cPojo = new CartPojo();
      cPojo.setUserId(sysLogin.getId());
      List<CartPojo> cartList = cartService.findCartByUserIdAndBrand(cPojo);
      List<CartPojo> cartListNew = new ArrayList<CartPojo>();
      List<CartPojo> cartListShow = new ArrayList<CartPojo>();
      if (cartList == null || cartList.size() == 0) {
        msg = "亲，您的购物车里没有东西哦，赶紧去选购吧！";
      } else {
        String[] strings = cids[0].split(",");
        // 判断是否有下架产品标志
        boolean tip = false;
        for (int j = 0; j < strings.length; j++) {
          for (CartPojo p : cartList) {
            if (p.getId() == Long.parseLong(strings[j])) {
              if (p.getProStatus() != 1) {
                tip = true;
                // 删除购物车中已下架的商品
                // cartService.delCart(p.getId());
              } else {
                cartListNew.add(p);
              }
            }
          }
        }
        if (tip || cartListNew.size() == 0) {
          msg = "存在下架产品，请您重新选择！！！";
        } else {
          List<Object> clist = null;
          List<CartPojo> shopCartList = null;
          // 活动产品
          ActivityGoodsPojo activityGoods = null;
          ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
          ProductSkuLinkPojo productSkuLinkPojo = null;
          // 按活动划分
          long activeId = -1;
          String activityName = "";

          SpecialShowPojo specialShowPojo = null;
          OrderPojo orderParam = null;
          for (CartPojo cartPojo : cartListNew) {
            if (!(activeId == cartPojo.getActivityId() && activityName.equals(cartPojo
                .getActivityName()))) {
              clist = new ArrayList<Object>();

              cPojo.setUserId(uid);
              cPojo.setActivityId(cartPojo.getActivityId());
              cPojo.setActivityName(cartPojo.getActivityName());
              shopCartList = cartService.findCartByUserIdAndBrand(cPojo);

              for (int i = 0; i < shopCartList.size(); i++) {
                for (int j = 0; j < strings.length; j++) {
                  Long cidLong = Long.parseLong(strings[j]);
                  if (shopCartList.get(i).getId().equals(cidLong)) {
                    cartListShow.add(shopCartList.get(i));
                  }
                }
              }

              Map<String, Object> mapShop = new HashMap<String, Object>();
              String name = null;
              Long sId = null;
              Double sum = 0.0;
              Double weight = 0.0;
              int count = 0;
              ProductSkuLinkPojo productSkuLinkCheck = null;

              for (CartPojo sCartPojo : cartListShow) {
                // 判断是否为钱包商品
                if (sCartPojo.getType() != null && !sCartPojo.getType().equals(3)) {
                  isWallet = 0;
                }
                // 活动价格判断
                Integer skuid = sCartPojo.getSkuLinkId();
                Long activityId = sCartPojo.getActivityId();
                // 更新购物车活动价格
                if (activityId == null) {
                  activityId = 0L;
                }
                if (activityId > 0) {
                  activityGoods =
                      sellerService.isProductActivity(sCartPojo.getProductId(), activityId);
                }
                // 有sku
                if (skuid != null && skuid > 0) {
                  productSkuLinkCheck = sellerService.getProducSku(sCartPojo.getProductId(), skuid);
                  if (productSkuLinkCheck != null) {
                    // 购买数量大于库存
                    if (sCartPojo.getNum().intValue() > productSkuLinkCheck.getStock()) {
                      acFlag = 1;
                      continue;
                    } else if (activityId > 0 && activityGoods == null) {
                      // 活动已过期
                      acFlag = 2;
                      continue;
                    }
                  } else {
                    acFlag = 1;
                    continue;
                  }
                } else if (activityId > 0) {
                  // 有活动，活动无库存或设置不全
                  if (!(activityGoods != null && activityGoods.getActivePrice() != null
                      && activityGoods.getActivePrice().doubleValue() != 0
                      && activityGoods.getActivityStock() != null && sCartPojo.getNum().intValue() <= activityGoods
                      .getActivityStock().intValue())) {
                    acFlag = 2;
                    // 删除活动已过期商品
                    // cartService.delCart(sCartPojo.getId());
                    continue;
                  }
                }

                name =
                    StringUtils.isEmpty(sCartPojo.getActivityName()) ? sCartPojo.getShopName()
                        : sCartPojo.getActivityName();
                sId = sCartPojo.getActivityId();
                Map<String, Object> mapCart = new HashMap<String, Object>();
                mapCart.put("cid", sCartPojo.getId());
                mapCart.put("productId", sCartPojo.getProductId());
                mapCart.put("sellingPrice", df.format(sCartPojo.getStockPriceOld()));
                mapCart.put("price", df.format(sCartPojo.getStockPrice()));
                mapCart.put(
                    "discount",
                    SellerService.calcDiscount(sCartPojo.getStockPrice(),
                        sCartPojo.getStockPriceOld()));
                mapCart.put("productName", sCartPojo.getProductName());
                mapCart.put("productNumber", sCartPojo.getNum());
                mapCart.put("productImage",
                    ConstParam.URL + "/upfiles/product/small/" + sCartPojo.getProductImage());
                mapCart.put("allPrice", df.format(sCartPojo.getNum() * sCartPojo.getStockPrice()));
                // 商品sku
                mapCart.put("skuLinkId", skuid == null ? "" : skuid);
                if (skuid != null) {
                  productSkuLink.setId(Long.valueOf(skuid));
                  productSkuLinkPojo = productSkuLinkService.findProductSkuLink(productSkuLink);
                  if (productSkuLinkPojo != null) {
                    mapCart.put("skuValue", "颜色:" + productSkuLinkPojo.getColorValue() + ";尺码:"
                        + productSkuLinkPojo.getFormatValue());
                  } else {
                    mapCart.put("skuValue", "");
                  }
                } else {
                  mapCart.put("skuValue", "");
                }

                mapCart.put("activityId", sCartPojo.getActivityId());
                // 商品活动类型
                mapCart.put("type", sCartPojo.getType());
                clist.add(mapCart);
                sum += sCartPojo.getNum() * sCartPojo.getStockPrice();
                count += sCartPojo.getNum();
                weight += Double.valueOf(sCartPojo.getWeight()) * sCartPojo.getNum();
              }

              cartListShow.clear();
              // 满减满折处理
              orderParam =
                  sellerService.specialDiscountProcess(sum, count, sum, cartPojo.getActivityId());
              sum = orderParam == null ? sum : orderParam.getFactPrice();

              specialShowPojo = specialShowService.findSpecialShowByActivityId(sId);
              String banner = "";
              if (specialShowPojo != null && specialShowPojo.getBanner() != null) {
                banner = specialShowPojo.getBanner();
              }
              mapShop.put("shopLogo", ConstParam.URL + "/upfiles/specialShow/" + banner);
              mapShop.put("allCount", count);
              mapShop.put("sumPrice", df.format(sum));
              mapShop.put("shopId", sId == null ? 0 : sId);
              mapShop.put("shopName", name == null ? "" : name);
              mapShop.put("espressPrice", df.format(SellerService.calcFare(weight)));
              mapShop.put("discountType", orderParam == null ? 0 : orderParam.getDiscountType());
              mapShop.put("discountText",
                  orderParam == null ? "" : df.format(orderParam.getUsedPrice()));

              mapShop.put("products", clist);

              list.add(mapShop);

            }
            activeId = cartPojo.getActivityId();
            activityName = cartPojo.getActivityName();
          }
        }
      }

      b = true;
      if (acFlag == 1 || acFlag == 2) {
        msg = "对不起，您购买的商品数量大于剩余库存！";
      }
      resultAll.put("result", list);
      resultAll.put("isWallte", isWallet);
      map.put("result", resultAll);
      if (list.size() == 0) {
        map.put("error_msg", msg);
      } else {
        map.put("error_msg", "");
      }

      map.put("success", b);
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
   * 钱包专区列表
   * 
   * @return
   * @throws Exception
   */
  public String walletActivity() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> result = new ArrayList<Object>();
    Map<String, Object> item = new HashMap<String, Object>();

    if (pageNo == null || "".equals(pageNo)) {
      page = new Pager();
      page.setPageNo(1);
    } else {
      page = new Pager();
      page.setPageNo(pageNo);
    }
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("isdelete", "0");
    option.put("sort", 1);
    option.put("type", 1);
    String date = StringUtil.dateString(new Date());
    option.put("beginTimeApi", date);
    option.put("endTimeApi", date);
    List<ActivityTimePojo> list = activityTimeService.findActivityTimeList(option);
    if (list.size() != 0) {
      option = new HashMap<String, Object>();
      option.put("pageSize", 15);
      option.put("pageNo", (page.getPageNo() - 1) * 15);
      option.put("status", 1);
      option.put("timeId", list.get(0).getId());
      List<ActivityGoodsPojo> list2 = activityGoodsService.findActivityGoodsList(option);
      if (list.size() != 0) {
        for (ActivityGoodsPojo a : list2) {
          item = new HashMap<String, Object>();
          item.put("id", a.getProductId() == null ? "" : a.getProductId());
          item.put("activityId", a.getTimeId() == null ? "" : a.getTimeId());
          item.put("image", a.getProductImage() == null ? "" : ConstParam.URL + "/upfiles/product"
              + File.separator + a.getProductImage());
          item.put("name", a.getProductName() == null ? "" : a.getProductName());
          item.put("price", a.getActivePrice() == null ? "" : a.getActivePrice());
          result.add(item);
        }
        b = true;
      } else {
        msg = "钱包专区暂时没有商品哦！~";
      }
    } else {
      msg = "钱包专区暂时没有商品哦！~";
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
   * 
   * 直接购买提交订单
   * 
   * @throws Exception
   * */
  public String addOrderByPurchase() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, String> alipayMap = new HashMap<String, String>();
    Map<String, Object> wxpayMap = new HashMap<String, Object>();
    String msg = "";
    boolean b = false;
    // 根据用户ID查询用户信息
    SysLoginPojo user = null;
    if (uid != null && uid > 0) {
      user = sysLoginService.findSysLoginById(uid);
    }
    // 获取商品信息
    ProductPojo productPojo = null;
    if (pid != null && pid > 0) {
      productPojo = new ProductPojo();
      productPojo.setId(pid);
      productPojo = productService.findProduct(productPojo);
    }
    if (activityId != null && activityId != 0) {
      activityTimePojo = activityTimeService.findActivityTimeById(activityId);
    }
    if (uid == null || uid.equals("")) {
      msg = "会员Id不能为空哦！";
    } else if (activityId == null || activityId == 0) {
      msg = "活动ID不能为空哦！";
    } else if (user == null || user.getStatus() == 0) {
      msg = "该会员ID不存在哦！";
    } else if (pid == null || pid == 0) {
      msg = "产品Id不能为空哦！";
    } else if (productPojo == null || productPojo.getStatus() == 0) {
      msg = "商品已下架！";
    } else if (num == null || num == 0) {
      msg = "购买数量不能为空哦！";
    } else if (consigneeType == null || consigneeType == 0) {
      msg = "收货方式有误！";
    } else if (consigneeType == 1 && (addressId == null || addressId == 0)) {
      msg = "收货地址不能为空！";
    } else if (payMethod == null || payMethod != 1 && payMethod != 2 && payMethod != 4) {
      msg = "支付方式有误哦！";
    } else if (activityTimePojo == null) {
      msg = "活动不存在哦！";
    } else if (payMethod == 4
        && (activityTimePojo.getType() == null || activityTimePojo.getType() != 1)) {
      msg = "支付方式有误哦！";
    } else {
      // 分割消息
      String[] msgs = buyer_message.split(",");
      DeliveryAddressPojo address = null;// 自提地址可以为空
      // 快递方式
      if (addressId != null && consigneeType == 1) {
        address = addressService.findDeliveryAddressById(addressId);// 获取收货地址信息
      }

      DecimalFormat df = new DecimalFormat("#.##");
      // 订单描述--支付宝
      String body = productPojo.getProductName();
      // 商户订单号--支付宝
      String out_trade_no = UtilDate.getOrderNum() + UtilDate.getThree();
      // 是否算运费,默认否
      boolean isFee = false;
      double price = productPojo.getDistributionPrice();// 单价
      double postPrice = 0.0;// 邮费
      double allCartPrice = 0.0;// 订单价格
      double allCartPrice0 = 0.0;// 总价格
      boolean stockFlag = true;
      // 活动价格判断
      if (activityId == null) {
        activityId = 0L;
      }
      ActivityGoodsPojo activityGoods = null;
      if (activityId > 0) {
        activityGoods = isProductActivity(productPojo.getId(), activityId);
        if (activityGoods == null) {
          stockFlag = false;
        }
      }
      // sku价格判断
      if (stockFlag) {
        CartPojo cart = new CartPojo();
        cart.setProductId(productPojo.getId());
        cart.setNum(num);
        if (skuLinkId != null && skuLinkId > 0) {
          cart.setSkuLinkId(skuLinkId);
          stockFlag = sellerService.updateActivitySkuStock(cart);
          if (stockFlag) {
            price = cart.getStockPrice();
            // 更新购买数量
            num = cart.getNum();
          }
        } else if (activityId > 0) {
          cart.setActivityId(activityId);
          stockFlag = sellerService.updateActivityStock(cart);
          if (stockFlag) {
            price = cart.getStockPrice();
            // 更新购买数量
            num = cart.getNum();
          }
        }
      }

      if (!stockFlag) {
        map.put("result", result);
        map.put("error_msg", "对不起，您购买的商品可能剩余库存不足或者活动已过期！");
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

      // 获取商品库存信息
      /*
       * ProductStockPojo productStockPojo = new ProductStockPojo();
       * productStockPojo.setProductId(pid); productStockPojo =
       * productStockService.findByProductStock(productStockPojo);
       */
      // 根据产品信息中的用户ID查询到店铺
      ShopPojo shop = new ShopPojo();
      shop.setUserId(productPojo.getUserId());
      shop = shopService.findShop(shop);

      // 方式为自提时该值为空
      SysAreaPojo sysArea = null;
      if (address != null) {
        sysArea = sysAreaService.getNameById(address.getProvince());// 查询这个省对应的运费；
      }
      allCartPrice = Double.valueOf(num * price);
      // 快递并且该商品不包邮
      if (isFee && consigneeType == 1 && productPojo.getPostageType() == 0) {
        // 总重量
        double allWeight = num * Double.valueOf(productPojo.getWeight());
        // 四舍五入重量
        int intPweight = (int) Math.ceil(allWeight);
        // 根据重量计算邮费
        if (intPweight <= 3) {
          Double posttage = sysArea.getPostage();
          Double addPostage = sysArea.getAddPostage();

          postPrice = posttage + addPostage * (intPweight - 1);
        } else {
          Double posttage = sysArea.getPostage2();
          Double addPostage = sysArea.getAddPostage2();

          postPrice = posttage + addPostage * (intPweight - 1);
        }
        // 不包邮购物车总价格
        allCartPrice0 = allCartPrice + postPrice;
      } else {
        // 自提和包邮总价格
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
          // 总重量
          double allWeight = num * Double.valueOf(productPojo.getWeight());
          deliverFee = calcFare(allWeight);
          // 订单价格
          allCartPrice0 += deliverFee;
        }
      }

      // 生成一张新的订单的订单号
      String orderNo = new Date().getTime() + RandomUtils.runVerifyCode(6);

      // 生成订单
      OrderPojo order = new OrderPojo();
      order.setUserId(user.getId());
      order.setSuserId(shop.getUserId());// 店铺userid作为订单的suerid
      order.setAllNum("" + num);
      order.setAllPrice(allCartPrice);
      order.setFactPrice(allCartPrice0);
      order.setEspressPrice(postPrice + deliverFee);
      // 快递方式
      if (address != null) {
        order.setConsignee(address.getConsignee());
        order.setConsigneeAddress(address.getProvinceName() + address.getCityName()
            + address.getAreaName() + address.getAddress());
        order.setConsigneePhone(address.getConsigneePhone());
      }
      order.setConsigneeType(consigneeType);
      order.setOrderStatus(1);
      order.setPayStatus(0);
      order.setIsDeleteOrder(0);
      order.setIsCancelOrder(0);
      order.setChannel(1);
      order.setCreateBy(user.getId());
      order.setCreateDate(new Date());
      order.setOrderNo(orderNo);
      order.setPayMethod(payMethod);

      if (msgs != null && msgs.length > 0) {
        // 分解字符串分别得到店铺ID和评价
        String[] strings = msgs[0].split(":");
        if (strings.length >= 2) {
          buyer_message = strings[1];
        }
      }
      // 买家留言
      if (buyer_message != null && !"".equals(buyer_message)) {
        order.setBuyerMessage(buyer_message);
      }
      // 支付宝订单号
      order.setOutTradeNo(out_trade_no);
      /*
       * if (addressId != null) { int s = Integer.parseInt(String.valueOf(addressId));
       * order.setUserAddressId(s); }
       */
      if (address != null) {
        order.setProvince(address.getProvince() != null ? address.getProvince().toString() : "0");
        order.setCity(address.getCity() != null ? address.getCity().toString() : "0");
        order.setArea(address.getArea() != null ? address.getArea().toString() : "0");
      }
      orderService.insertOrder(order);

      String subject = order.getOrderNo();
      // 用户首单标志更新
      int hadFirstOrder = orderService.checkUserFirstOrder(uid);
      if (hadFirstOrder == 0) {
        // 首单更新
        orderService.updateFirstOrder(out_trade_no);
      }

      // 生成订单详情
      // OrderPojo insert_order = orderService.findOrderByOrderNo(orderNo);
      OrderDetailPojo orderDetail = new OrderDetailPojo();
      orderDetail.setOrderId(order.getId());
      orderDetail.setUserId(user.getId());
      orderDetail.setLoginname(user.getLoginname());
      orderDetail.setShopId(shop.getId());
      orderDetail.setProductId(productPojo.getId());
      orderDetail.setProductName(productPojo.getProductName());
      orderDetail.setProductImage(productPojo.getImage());
      orderDetail.setWeight(productPojo.getWeight());
      orderDetail.setStockId(0l);
      orderDetail.setStockPriceOld(productPojo.getSellingPrice());
      orderDetail.setStockPrice(price);
      orderDetail.setNum(num);
      orderDetail.setPostageType(productPojo.getPostageType());
      orderDetail.setChannel(1);
      orderDetail.setStatus(1);
      orderDetail.setSkuLinkId(skuLinkId);
      orderDetail.setCreateBy(user.getId());
      orderDetail.setCreateDate(new Date());

      // 商品活动类型
      if (activityId == 0) {
        // 普通商品
        orderDetail.setType(0);
      } else {
        if (activityTimePojo != null && activityTimePojo.getType() == 0) {
          // 秒杀商品
          orderDetail.setType(2);
        } else if (activityTimePojo != null && activityTimePojo.getType() == 1) {
          // 专题商品
          orderDetail.setType(3);
        } else if (activityTimePojo != null && activityTimePojo.getType() == 2) {
          // 场景商品
          orderDetail.setType(4);
        } else if (activityTimePojo != null && activityTimePojo.getType() == 3) {
          // 专场商品
          orderDetail.setType(5);
        } else {
          // 活动商品
          orderDetail.setType(1);
        }

      }
      orderDetailService.insertOrderDetail(orderDetail);

      // 使用优惠券金额
      double m = 0.0;
      UserCouponPojo userCoupon = null;
      long time = new Date().getTime() / 1000;
      if (couponNo != null && !"".equals(couponNo)) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("couponNo", couponNo);
        paramMap.put("userId", uid);
        paramMap.put("used", 0);
        userCoupon = couponService.getUserCouponByNo(paramMap);
        if (userCoupon != null && (1 == userCoupon.getType() || 2 == userCoupon.getType())) {
          org.json.JSONObject json = new org.json.JSONObject(userCoupon.getContent());
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

      if (m > 0.0) {
        // 添加订单用券记录
        CouponOrderPojo couponOrder = new CouponOrderPojo();
        couponOrder.setUsedPrice(m);
        // 初始有效，单张订单满足用券
        couponOrder.setStatus(1);
        couponOrder.setCouponNo(couponNo);
        couponOrder.setOrderId(order.getId());
        couponOrder.setRelTime(time);
        couponService.addCouponOrder(couponOrder);
      }

      // 支付金额减去代金券金额
      allCartPrice0 = allCartPrice0 - m;
      // 1-支付宝支付
      if (1 == payMethod) {
        // 生成一条未付款支付宝信息
        AlipayOrderInfoPojo alipayOrderInfoPojo = new AlipayOrderInfoPojo();
        alipayOrderInfoPojo.setOutTradeNo(out_trade_no);
        alipayOrderInfoPojo.setTotalFee(df.format(allCartPrice0));
        alipayOrderInfoPojo.setTradeStatus("WAIT_BUYER_PAY");
        alipayOrderInfoPojo.setCreateDate(new Date());
        alipayOrderInfoPojo.setVersion(0);
        alipayOrderInfoService.insertOne(alipayOrderInfoPojo);

        // 组装支付宝支付串
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
            buildAlipayReq(alipayOrderInfoPojo.getOutTradeNo(), subject,
                alipayOrderInfoPojo.getTotalFee(), body, show_url);

      } else if (2 == payMethod) {
        // 生成未付款微信支付信息
        WxpayOrderInfoPojo wxpay = new WxpayOrderInfoPojo();
        wxpay.setOutTradeNo(out_trade_no);
        // 单位分
        wxpay.setTotalFee(doubleToFee(allCartPrice0));
        wxpay.setTradeStatus("WAIT_BUYER_PAY");
        wxpay.setVersion(0);
        wxpayOrderInfoService.insertPay(wxpay);

        // 微信支付
        subject = "拼得好-订单号:" + subject;
        // 总长32，超出29长度截掉
        if (subject.length() > 29) {
          subject = subject.substring(0, 29) + "...";
        }
        // 超出500长度截掉
        if (body.length() > 500) {
          body = body.substring(0, 500) + "...";
        }
        wxpayMap =
            buildWxpayReq(wxpay.getOutTradeNo(), null, body, subject,
                Integer.valueOf(wxpay.getTotalFee()));
        wxpayMap.put("out_trade_no", out_trade_no);
      }
      b = true;
      result.put("aplipay", alipayMap);
      result.put("wxpay", wxpayMap);
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
   * 同步检查是否有活动库存（无SKU）
   * 
   * @param id
   * @param num
   * @return
   * @throws SQLException
   */
  protected boolean updateActivityStock(CartPojo cart) throws SQLException {
    synchronized (AppApiAction.class) {
      ActivityGoodsPojo activityGoods =
          isProductActivity(cart.getProductId(), cart.getActivityId());
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
    synchronized (AppApiAction.class) {
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

    // 防钓鱼时间戳
    String anti_phishing_key = "";
    try {
      anti_phishing_key = AlipaySubmit.query_timestamp();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (DocumentException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // 若要使用请调用类文件submit中的query_timestamp函数

    // 客户端的IP地址
    // HttpServletRequest request = ServletActionContext.getRequest();
    // String exter_invoke_ip = getIpAddr(request);
    String exter_invoke_ip = "";
    // 非局域网的外网IP地址，如：221.0.0.1

    // 把请求参数打包成数组
    Map<String, String> sParaTemp = new HashMap<String, String>();
    sParaTemp.put("service", "create_direct_pay_by_user");
    sParaTemp.put("partner", AlipayConfig.partner);
    sParaTemp.put("seller_email", AlipayConfig.seller_email);
    sParaTemp.put("_input_charset", AlipayConfig.input_charset);
    sParaTemp.put("payment_type", payment_type);
    sParaTemp.put("notify_url", AlipayConfig.notify_url);
    sParaTemp.put("return_url", AlipayConfig.return_url);
    sParaTemp.put("out_trade_no", out_trade_no);
    sParaTemp.put("subject", subject);
    sParaTemp.put("total_fee", total_fee);
    sParaTemp.put("body", body);
    sParaTemp.put("show_url", show_url);
    sParaTemp.put("anti_phishing_key", anti_phishing_key);
    sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

    String sign = AlipaySubmit.buildRequestMysignMobile(sParaTemp);
    // 签名结果与签名方式加入请求提交参数组中
    sParaTemp.put("sign", sign);
    sParaTemp.put("sign_type", AlipayConfig.sign_type);
    return sParaTemp;
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

  public Map<String, Object> buildWxpayReq(String out_trade_no, String product_id, String detail,
      String body, int total_fee) {
    // key 签名算法需要用到的秘钥
    // appID 公众账号ID
    String appID = PropertiesHelper.getValue("wx_app_id");
    // mchID 商户ID
    String mchID = PropertiesHelper.getValue("wx_mch_id");
    // sdbMchID 子商户ID，受理模式必填
    String sdbMchID = null;
    // certLocalPath HTTP证书在服务器中的路径，用来加载证书用
    String certLocalPath = PropertiesHelper.getValue("wx_cert_path");
    // certPassword HTTP证书的密码，默认等于MCHID
    String certPassword = mchID;

    // authCode 这个是扫码终端设备从用户手机上扫取到的支付授权号，这个号是跟用户用来支付的银行卡绑定的，有效期是1分钟
    // body 要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
    // attach 支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回
    // outTradeNo 商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
    // totalFee 订单总金额，单位为“分”，只能整数
    // int totalFee = new Double(price*100l).intValue();
    // deviceInfo 商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
    // spBillCreateIP 订单生成的机器IP
    String spBillCreateIP = "";
    // goodsTag 商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
    String notifyUrl = PropertiesHelper.getValue("wx_notify_url");
    String key = PropertiesHelper.getValue("wx_key");
    WXPay.initSDKConfiguration(key, appID, mchID, sdbMchID, certLocalPath, certPassword);
    PrePayReqData prePayReqData =
        new PrePayReqData(null, "WEB", product_id, detail, body, out_trade_no, total_fee,
            notifyUrl, spBillCreateIP, "APP", "");
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
        sParaTemp.put("appid", appID);
        sParaTemp.put("noncestr", MD5.MD5Encode(String.valueOf(time)).toUpperCase());
        sParaTemp.put("package", "Sign=WXPay");
        sParaTemp.put("partnerid", mchID);
        sParaTemp.put("prepayid", prePayResData.getPrepay_id());
        sParaTemp.put("timestamp", String.valueOf(time));
        String sign = Signature.getSign(sParaTemp);
        sParaTemp.put("sign", sign);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return sParaTemp;
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
    SysLoginPojo user = null;
    if (uid != null && uid > 0) {
      user = sysLoginService.findSysLoginById(uid);
    }
    if (consigneeType == 1 && addressId != null && addressId > 0) {
      address = addressService.findDeliveryAddressById(addressId);// 获取收货地址信息
    }
    if (uid == null || uid.equals("")) {
      error_msg = "用户ID不能为空！";
    } else if (user == null) {
      error_msg = "没有该用户ID！";
    } else if (cids == null || cids.equals("")) {
      error_msg = "购物车ID不能为空！";
    } else if (payMethod == null || payMethod != 1 && payMethod != 2 && payMethod != 4) {
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

      boolean choosedCart = false;
      List<CartPojo> cartList = cartService.submitCartsByIds(cartIds);// 获取商品信息
      int isWallet = 1;// 0-为普通商品 或者 普通商品与钱包商品2者混合；1-为钱包商品
      for (CartPojo cart : cartList) {
        if (cart != null && 1 == cart.getStatus()) {
          // 购物车商品已选中
          choosedCart = true;
          break;
        }
        if (cart.getType() != null && !cart.getType().equals(3)) {
          isWallet = 0;
        }
      }
      if (cartList == null || cartList.size() == 0) {
        error_msg = "购物车中已没有商品了哦！";
        success = false;
      } else if (choosedCart) {
        error_msg = "订单商品已提交，请不要重复提交哦！";
        success = false;
      } else if (payMethod == 4 && isWallet == 0) {
        error_msg = "支付方式有误！";
        success = false;
      } else {
        // 修改选中的购物车商品的状态=1
        int flag = cartService.updateStatusCartWeb(cartIds);
        if (flag > 0) {
          // 留言处理
          Map<String, String> buyMsg = new HashMap<String, String>();
          String[] strings = null;
          for (int i = 0; i < msgs.length; i++) {
            // 分解字符串分别得到店铺ID和评价
            strings = msgs[i].split(":");
            if (strings != null && strings.length >= 2) {
              buyMsg.put(strings[0], strings[1]);
            }
          }

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
          List<CartPojo> cartListByShopId = null;
          // 活动产品判断
          ActivityGoodsPojo activityGoods = null;
          boolean stockFlag = false;
          for (CartPojo cart : cartList) {

            if (!(activeId == cart.getActivityId() && activityName.equals(cart.getActivityName()))) {
              cartCnt = 0;
              cartParam = new CartPojo();
              cartParam.setUserId(cart.getUserId());
              cartParam.setActivityId(cart.getActivityId());
              cartParam.setActivityName(cart.getActivityName());
              cartParam.setCheckStatus(1);
              cartListByShopId = cartService.findCartByUserIdAndBrand(cartParam);
              // 邮费
              Double farestatus = 0.0;// farestatus=1初始值
              // 重量
              Double pweight = 0.0;
              Double orderWeight = 0.0;
              // 订单总价
              allCartPrice = 0.0;
              cartNum = 0;
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
                // 不再更新商品购买数量，注掉
                /*
                 * if (stockFlag) { // 更新库存成功 //
                 * cartByShopId.setStockPrice(activityGoods.getActivePrice());
                 * cartService.updateCart(cartByShopId); }
                 */
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
              if (farestatus == 1.0 && address != null) {
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

              buyer_message = buyMsg.get(cart.getActivityId() + "-" + cart.getActivityName());
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
              Long orderflag = orderService.insertOrder(order);
              subject += order.getOrderNo() + "\n";
              // 生成订单结束
              // 生成订单详情
              // OrderPojo insert_order = orderService.findOrderByOrderNo(orderNo);
              if (orderflag > 0) {
                for (CartPojo cartDetail : cartListByShopId) {
                  if (!errCartIds.contains(cartDetail.getId())) {
                    cart.setAllStockPrice(df.format(cartDetail.getStockPrice()
                        * cartDetail.getNum()));
                    OrderDetailPojo orderDetail = new OrderDetailPojo();
                    orderDetail.setOrderId(order.getId());
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
                    orderDetail.setSource(cartDetail.getSource());
                    orderDetail.setSourceId(cartDetail.getSourceId());
                    orderDetailService.insertOrderDetail(orderDetail);
                    cartService.deleteCart(cartDetail.getId());
                  }
                }
                allOrderCnt++;
              }
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
        } else {
          error_msg = "订单商品已提交，请不要重复提交哦！";
          success = false;
        }
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
    OrderPojo order = null;
    int isWallet = 1;
    if (orderNo != null && !"".equals(orderNo)) {
      order = orderService.findOrderByOrderNo(orderNo);
      if (order != null) {
        List<OrderDetailPojo> orderDetail =
            orderDetailService.getfindByUserIdOrderDetail(order.getId());
        if (orderDetail.size() != 0) {
          for (OrderDetailPojo o : orderDetail) {
            if (o.getType() != 3) {
              isWallet = 0;
              break;
            }
          }
        } else {
          isWallet = 0;
        }
      } else {
        isWallet = 0;
      }
    }
    int fullpay = 0;
    if (uid == null) {
      error_msg = "uid不能为空";
    } else if (loginPojo == null) {
      error_msg = "没有该会员";
    } else if (orderNo == null || "".equals(orderNo)) {
      error_msg = "订单号不能为空";
    } else if (order == null) {
      error_msg = "订单号未找到或已付款";
    } else if (payMethod != 1 && payMethod != 2 && payMethod != 4) {
      error_msg = "支付方式错误";
    } else if (payMethod == 4 && isWallet == 0) {
      error_msg = "支付方式错误";
    } else {

      String subject = "";
      String body = "";
      Double price = 0.0;
      // 待支付状态
      if (order != null && 1 == order.getOrderStatus()) {
        // 新商户订单号
        String out_trade_no = UtilDate.getOrderNum() + UtilDate.getThree();
        // 查看原支付流水号是否返回结果
        String outTradeNoOld = order.getOutTradeNo();
        if (1 == order.getPayMethod()) {
          AlipayOrderInfoPojo alipay = alipayOrderInfoService.findPayInfoByTradeNo(outTradeNoOld);
          if (alipay != null && "WAIT_BUYER_PAY".equals(alipay.getTradeStatus())) {
            // error_msg = "等待支付结果返回中";
            // isPay = true;
            alipay.setTradeStatus("TRADE_FAIL");
            alipay.setRemarks("cancelBy:" + out_trade_no);
            alipayOrderInfoService.updateOrder(alipay);
          } else if (alipay != null && "TRADE_SUCCESS".equals(alipay.getTradeStatus())) {
            isPay = true;
          }
        } else if (2 == order.getPayMethod()) {
          WxpayOrderInfoPojo wxpay = wxpayOrderInfoService.findPayInfoByTradeNo(outTradeNoOld);
          if (wxpay != null && "WAIT_BUYER_PAY".equals(wxpay.getTradeStatus())) {
            // error_msg = "等待支付结果返回中";
            // isPay = true;
            wxpay.setTradeStatus("TRADE_FAIL");
            wxpay.setRemarks("cancelBy:" + out_trade_no);
            wxpayOrderInfoService.updatePay(wxpay);
          } else if (wxpay != null && "TRADE_SUCCESS".equals(wxpay.getTradeStatus())) {
            isPay = true;
          }
        } else {
          error_msg = "原支付方式不为在线支付";
          isPay = true;
        }
        if (!isPay) {
          // 更新支付流水号
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
   * 我的订单
   * */
  public String myorder() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = null;
    Map<String, Object> map2 = null;
    List list3 = new ArrayList();
    DecimalFormat df = new DecimalFormat("#.##");
    if (uid == null || uid.equals("")) {
      map.put("error_msg", "用户ID不能为空！");
      map.put("success", false);
    } else {
      long s = uid;
      String error_msg = "";
      try {
        orderPojo = new OrderPojo();
        orderPojo.setUserId(s);
        if (orderStatus != null && orderStatus != 0) {
          orderPojo.setOrderStatus(orderStatus);
        }
        if (pageNo == null || pageNo.equals("")) {
          page = new Pager();
          page.setPageNo(1);
        } else {
          page = new Pager();
          page.setPageNo(pageNo);
        }
        page.setPageSize(10);
        List<OrderPojo> list1 = orderService.userIdOrderByPage(orderPojo, page);
        List<OrderDetailPojo> orderDetaillist = new ArrayList<OrderDetailPojo>();
        List<UserOrderRefundPojo> userOrderRefundlist = new ArrayList<UserOrderRefundPojo>();
        if (list1 != null && list1.size() > 0) {
          for (OrderPojo p : list1) {
            List list = new ArrayList();
            int num = 0;
            map1 = new HashMap<String, Object>();
            orderDetaillist = orderDetailService.getfindByUserIdOrderDetail(p.getId());
            UserOrderRefundPojo userOrderRefundPojo = new UserOrderRefundPojo();
            for (int i = 0; i < orderDetaillist.size(); i++) {
              num += orderDetaillist.get(i).getNum();
            }
            map1.put("num", num);
            userOrderRefundPojo.setOrderId(p.getId());
            userOrderRefundlist =
                userOrderRefundService.findUserOrderRefundByorderId(userOrderRefundPojo);
            if (orderDetaillist.size() == userOrderRefundlist.size()) {
              // map1.put("refundId",1);

              int num1 = 0;// 全部退货中
              int num2 = 0;// 全部成功
              int num3 = 0;// 全部失败
              int size = userOrderRefundlist.size();
              for (UserOrderRefundPojo refund : userOrderRefundlist) {
                if (refund.getStatus() == 2 || refund.getStatus() == 3) {
                  num1++;
                } else if (refund.getStatus() == 4 || refund.getStatus() == 7) {
                  num2++;
                } else if (refund.getStatus() == 5 || refund.getStatus() == 6) {
                  num3++;
                }
              }
              if (num1 == size) {
                // 全部退货中
                map1.put("refundId", 2);
              } else if (num2 == size) {
                // 全部成功
                map1.put("refundId", 3);
              } else if (num3 == size) {
                // 全部失败
                map1.put("refundId", 4);
              } else {
                // 审核中
                map1.put("refundId", 1);
              }

              // 4-待评价，如果为待评价查询，全部退货/退款订单成功的不显示
              if (orderStatus != null && 4 == orderStatus) {
                if (num2 == size || num3 == size) {
                  continue;
                }
              }
            } else {
              map1.put("refundId", 0);
            }
            map1.put("orderId", p.getId());
            map1.put("orderNumber", p.getOrderNo());
            if (p.getAllPrice() == null) {
              p.setAllPrice(0d);
            }
            if (p.getEspressPrice() == null) {
              p.setEspressPrice(0d);
            }
            /*
             * if (p.getUsedPrice() != null) { map1.put("orderPrice", df.format(p.getAllPrice() +
             * p.getEspressPrice() - p.getUsedPrice())); } else { map1.put("orderPrice",
             * df.format(p.getAllPrice() + p.getEspressPrice())); }
             */
            if (p.getUsedPrice() != null) {
              map1.put("orderPrice", df.format(p.getFactPrice() - p.getUsedPrice()));
            } else {
              map1.put("orderPrice", df.format(p.getFactPrice()));
            }
            map1.put("espressPrice", df.format(p.getEspressPrice()));
            // 判断订单物流是否在45天前
            int isExpired = orderShipService.isOrderShipExpiredByOrderId(p.getId());
            map1.put("isExpired", isExpired);
            // map1.put("num", p.getAllNum());
            map1.put("orderStatus", p.getOrderStatus());

            map1.put("shopLogo",
                ConstParam.URL + "/upfiles/shop/"
                    + shopService.findShopById(p.getShopId()).getImages());

            // 判断活动名称是否为空(为空时店铺id跟店铺名称照常显示，不为空时店铺id跟店铺名称显示为活动名称跟活动id)
            map1.put("shopId", p.getActivityId());
            if (StringUtils.isEmpty(p.getActivityName())) {
              map1.put("shopName", p.getShopName());
            } else {
              map1.put("shopName", p.getActivityName());
            }

            // map1.put("consigneeType", p.getConsigneeTypeName());
            // 3-货到付款
            if (p.getPayMethod() != null && 3 == p.getPayMethod()) {
              map1.put("consigneeType", "货到付款");
            } else {
              map1.put("consigneeType", p.getConsigneeTypeName());
            }
            List<OrderDetailPojo> orderDetailList = orderDetailService.getOrderDetail(p.getId());
            if (orderDetailList != null) {
              ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
              ProductSkuLinkPojo productSkuLinkPojo = null;
              int isWallet = 1;// 0-为普通商品 或者 普通商品与钱包商品2者混合；1-为钱包商品
              for (OrderDetailPojo orderDetailPojo2 : orderDetailList) {
                map2 = new HashMap<String, Object>();
                map2.put("productId", orderDetailPojo2.getProductId());
                map2.put("productName", orderDetailPojo2.getProductName());
                map2.put("productNumber", orderDetailPojo2.getNum());
                map2.put("productImage", ConstParam.URL + "/upfiles/product/small" + File.separator
                    + orderDetailPojo2.getProductImages());
                map2.put("price", df.format(orderDetailPojo2.getStockPrice()));
                map2.put("reStatus", orderDetailPojo2.getReStatus());
                map2.put("productStatus", orderDetailPojo2.getProductStatus() == null ? 0
                    : orderDetailPojo2.getProductStatus());
                // 商品sku显示
                Integer skuid = orderDetailPojo2.getSkuLinkId();
                map2.put("skuLinkId", skuid == null ? "" : skuid);
                if (skuid != null) {
                  productSkuLink.setId(Long.valueOf(skuid));
                  productSkuLinkPojo = productSkuLinkService.findProductSkuLink(productSkuLink);
                  if (productSkuLinkPojo != null) {
                    map2.put("skuValue", "颜色:" + productSkuLinkPojo.getColorValue() + ";尺码:"
                        + productSkuLinkPojo.getFormatValue());
                  } else {
                    map2.put("skuValue", "");
                  }
                } else {
                  map2.put("skuValue", "");
                }
                map2.put("activityId", orderDetailPojo2.getActivityId());
                map2.put("activityName", orderDetailPojo2.getActivityName() == null ? ""
                    : orderDetailPojo2.getActivityName());
                // 商品活动类型 0-普通商品 1-活动商品 2-秒杀商品 3-专题商品 4-场景 5-专场
                Object type = orderDetailPojo2.getType() == null ? "" : orderDetailPojo2.getType();
                map2.put("type", type);
                if (!type.equals(3)) {
                  isWallet = 0;
                }
                list.add(map2);
              }
              map1.put("isWallet", isWallet);

            } else {
              error_msg = "cars没有数据";
            }
            if (list.size() == 0) {
              map1.put("carts", "");
            } else {
              map1.put("carts", list);
            }
            list3.add(map1);
          }
          if (list3 == null || list3.size() == 0) {
            error_msg = "没有数据";
          }
        } else {
          error_msg = "没有数据";
        }
        map.put("error_msg", error_msg);
        map.put("success", true);
      } catch (Exception e) {
        e.printStackTrace();
        map.put("success", false);
      }
    }
    map.put("result", list3);
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
   * 申请退货、退款 、售后
   * 
   * @throws SQLException
   * */
  public String applyRefund() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    OrderRefundPojo orderRefundPojo = new OrderRefundPojo();
    String msg = "";
    int dresult = 0;
    boolean b = false;
    if (uid == null) {
      msg = "会员ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else if (sid == null) {
      msg = "店铺ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else if (type == null) {
      msg = "退货退款类型是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      try {
        long s = oid;
        String ddd = "";
        if (file != null) {
          ddd = sellerService.uploadFile(file, "upfiles" + File.separator + "orderRefund");
          orderRefundPojo.setImages(ddd);
        }
        if (file1 != null) {
          ddd = sellerService.uploadFile(file1, "upfiles" + File.separator + "orderRefund");
          orderRefundPojo.setLogistics(ddd);
        }
        if (file2 != null) {
          ddd = sellerService.uploadFile(file2, "upfiles" + File.separator + "orderRefund");
          orderRefundPojo.setLogType(ddd);
        }
        OrderRefundPojo isnull = new OrderRefundPojo();
        isnull.setUserId(uid);
        isnull.setDetailId(s);
        if (orderRefundService.getorderRefundDetail(isnull) == null) {
          orderRefundPojo.setRefundNum(num);
          orderRefundPojo.setDetailId(s);
          // if(sid != null && !"".equals(sid)){
          // ShopPojo shopPojo = shopService.findShopById(sid);
          // if (shopPojo != null) {
          // long suserId = shopPojo.getUserId();
          // orderRefundPojo.setUserId(suserId);
          // }
          // }
          orderRefundPojo.setUserId(uid);
          if (uid != null && !"".equals(uid)) {
            SysLoginPojo sysLoginPojo = sysLoginService.getfindByIdSysLogin(uid);
            if (sysLoginPojo != null) {
              String loginname = sysLoginPojo.getName();
              orderRefundPojo.setLoginname(loginname);
            }
          }
          orderRefundPojo.setShopId(sid);
          orderRefundPojo.setType(Long.valueOf(type));
          orderRefundPojo.setRefundType(refundType);
          orderRefundPojo.setRefundReason(refundReason);
          if ("2".equals(type) || "3".equals(type)) {
            orderRefundPojo.setStatus(2);
          } else {
            orderRefundPojo.setStatus(1);
          }
          OrderDetailPojo orderDetailPojo = orderDetailService.getfindByIdOrderDetail(s);
          orderRefundPojo.setOrderId(orderDetailPojo.getOrderId());
          orderRefundPojo.setProductId(orderDetailPojo.getProductId());
          orderRefundPojo.setProductName(orderDetailPojo.getProductName());
          orderRefundPojo.setStockPrice(orderDetailPojo.getStockPrice());
          orderRefundPojo.setSkuLinkId(orderDetailPojo.getSkuLinkId());
          // orderRefundPojo.setServiceInvolved(1);
          orderRefundService.addOrderRefundService(orderRefundPojo);
          Map<String, Object> map1 = new HashMap<String, Object>();
          if ("2".equals(type) || "3".equals(type)) {
            map1.put("reStatus", 2);
          } else {
            map1.put("reStatus", 1);
          }
          map1.put("status", 0);
          map1.put("id", s);
          orderDetailService.updateReStatusmap(map1);
        }

        // 优惠金额
        OrderRefundPojo orderRefundPojo2 = new OrderRefundPojo();
        OrderRefundPojo orderRefundPojo3 = new OrderRefundPojo();
        orderRefundPojo2.setUserId(uid);
        orderRefundPojo2.setDetailId(s);
        orderRefundPojo3 = orderRefundService.getorderRefundDetail(orderRefundPojo2);
        if (orderRefundPojo3 != null) {
          double allPrice = 0, couponPrice = 0, discountPrice = 0;
          Map<String, Object> option = new HashMap<String, Object>();
          option.put("orderId", orderRefundPojo3.getOrderId());
          option.put("status", 1);
          List<CouponOrderPojo> list2 = couponService.getcouponOrderList(option);
          if (list2.size() != 0) {
            option = new HashMap<String, Object>();
            option.put("couponNo", list2.get(0).getCouponNo());
            option.put("status", 1);
            List<CouponOrderPojo> list3 = couponService.getcouponOrderList(option);
            if (list3.size() != 0) {
              for (CouponOrderPojo c : list3) {
                if (c.getUsedPrice() != 0) {
                  couponPrice = c.getUsedPrice();
                }

                OrderDetailPojo orderDetailPojo2 = new OrderDetailPojo();
                orderDetailPojo2.setOrderId(c.getOrderId());
                List<OrderDetailPojo> list =
                    orderDetailService.orderDetailAllList(orderDetailPojo2, null);
                if (list.size() != 0) {
                  for (OrderDetailPojo o : list) {
                    allPrice += o.getStockPrice() * o.getNum();
                  }
                }
              }
            }
          }
          if (couponPrice != 0 && allPrice != 0) {
            couponPrice =
                couponPrice * orderRefundPojo3.getStockPrice() * orderRefundPojo3.getRefundNum()
                    / allPrice;
          }
          // 专场退款金额
          OrderPojo order = orderService.findOrderById(orderRefundPojo3.getOrderId());
          if (order != null && order.getDiscountPrice() > 0 && order.getAllPrice() > 0) {
            discountPrice =
                order.getDiscountPrice() * orderRefundPojo3.getStockPrice()
                    * orderRefundPojo3.getRefundNum() / order.getAllPrice();
          }
          // 优惠券优惠金额、专场优惠金额累计
          couponPrice += discountPrice;
          if (couponPrice > 0) {
            // 修改优惠金额
            OrderRefundPojo orderRefund = new OrderRefundPojo();
            orderRefund.setCouponPrice(couponPrice);
            orderRefund.setId(orderRefundPojo3.getId());
            orderRefundService.updateOrderRefund(orderRefund);
          }

        }

        msg = "申请成功！";
        dresult = 1;
        b = true;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        msg = "申请失败！";
      }
    }
    map.put("error_msg", msg);
    map.put("result", dresult);
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
   * 
   * 产品分类
   * 
   * @throws SQLException
   * */
  public String productType() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    ProductTypePojo pojo = new ProductTypePojo();
    Long idLong = (long) 0;
    pojo.setPid(idLong);
    pojo.setStatus(1);
    pojo.setVisable(1);
    List<ProductTypePojo> fatherList = productTypeService.getProductTypeByPid(pojo);
    List<ProductTypePojo> childList = productTypeService.getProductTypeSecondLevel();
    Map<String, Object> map1 = null;
    Map<String, Object> map2 = null, tempmap1 = null, tempmap2 = null;
    List<Object> list = null, templist1 = null, templist2 = null;
    List<Object> falist1 = new ArrayList<Object>();
    List<Object> falist2 = new ArrayList<Object>();
    String url = ConstParam.URL + "/upfiles/productType/";
    for (ProductTypePojo fatherPojo : fatherList) {
      map2 = new HashMap<String, Object>();
      list = new ArrayList<Object>();
      templist1 = new ArrayList<Object>();
      templist2 = new ArrayList<Object>();
      map2.put("pid", fatherPojo.getId());
      map2.put("name", fatherPojo.getName());
      map2.put("image", url + fatherPojo.getImage());
      for (ProductTypePojo productTypePojo : childList) {
        map1 = new HashMap<String, Object>();
        if ("0-3岁婴幼儿玩具".equals(fatherPojo.getName()) && "1".equals(productTypePojo.getAgeType())
            || "3-6岁儿童玩具".equals(fatherPojo.getName()) && "2".equals(productTypePojo.getAgeType())
            || "6岁以上玩具".equals(fatherPojo.getName()) && "3".equals(productTypePojo.getAgeType())
            || productTypePojo.getPid() == fatherPojo.getId()) {
          if ("0-3岁婴幼儿玩具".equals(fatherPojo.getName())) {
            if ("床铃".equals(productTypePojo.getName()) || "爬行垫".equals(productTypePojo.getName())
                || "健身毯".equals(productTypePojo.getName())
                || "摇铃牙胶".equals(productTypePojo.getName())) {
              // 0-1岁婴幼儿玩具
              map1.put("id", productTypePojo.getId());
              map1.put("name", productTypePojo.getName());
              map1.put("image", StringUtils.isEmpty(productTypePojo.getImage()) ? "" : url
                  + productTypePojo.getImage());
              templist1.add(map1);
            } else {
              // 1-2岁婴幼儿玩具
              map1.put("id", productTypePojo.getId());
              map1.put("name", productTypePojo.getName());
              map1.put("image", StringUtils.isEmpty(productTypePojo.getImage()) ? "" : url
                  + productTypePojo.getImage());
              templist2.add(map1);
            }
            // 0-3岁婴幼儿玩具
            // list.add(map1);
          } else {
            map1.put("id", productTypePojo.getId());
            map1.put("name", productTypePojo.getName());
            map1.put("image", StringUtils.isEmpty(productTypePojo.getImage()) ? "" : url
                + productTypePojo.getImage());
            list.add(map1);
          }
        }
      }
      map2.put("type", list);
      if ("0-3岁婴幼儿玩具".equals(fatherPojo.getName())) {
        String pid = map2.get("pid").toString();
        String image = map2.get("image").toString();
        // 0-1岁婴幼儿玩具
        tempmap1 = new HashMap<String, Object>();
        tempmap1.put("pid", pid);
        tempmap1.put("name", "0-1岁婴幼儿玩具");
        tempmap1.put("image", image);
        tempmap1.put("type", templist1);
        falist2.add(tempmap1);
        // 1-2岁婴幼儿玩具
        tempmap2 = new HashMap<String, Object>();
        tempmap2.put("pid", pid);
        tempmap2.put("name", "1-2岁婴幼儿玩具");
        tempmap2.put("image", image);
        tempmap2.put("type", templist2);
        falist2.add(tempmap2);
        // 0-3岁婴幼儿玩具
        map2.clear();
        // falist2.add(map2);
      } else if ("3-6岁儿童玩具".equals(fatherPojo.getName()) || "6岁以上玩具".equals(fatherPojo.getName())) {
        falist2.add(map2);
      } else {
        falist1.add(map2);
      }
    }
    falist2.addAll(falist1);
    map.put("result", falist2);
    map.put("error_msg", "");
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
}
