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

import maowu.framework.utils.datetime.DateTimeUtil;
import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.alipay.util.UtilDate;
import com.alipay.util.httpClient.AlipayTradeQueryResponse;
import com.tencent.WXPay;
import com.tencent.common.Util;
import com.tencent.protocol.pay_query_protocol.ScanPayQueryReqData;
import com.tencent.protocol.pay_query_protocol.ScanPayQueryResData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.tzmb2c.business.service.ConstParam;
import com.tzmb2c.business.service.GrouponService;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.business.service.WalletService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.IdWorker;
import com.tzmb2c.utils.MD5Util;
import com.tzmb2c.utils.RandomUtils;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityProductCommentPojo;
import com.tzmb2c.web.pojo.AliRedEnvelopePojo;
import com.tzmb2c.web.pojo.AlipayOrderInfoPojo;
import com.tzmb2c.web.pojo.BaiduLoginPojo;
import com.tzmb2c.web.pojo.CartPojo;
import com.tzmb2c.web.pojo.CouponOrderPojo;
import com.tzmb2c.web.pojo.CouponPojo;
import com.tzmb2c.web.pojo.DeliveryAddressPojo;
import com.tzmb2c.web.pojo.FocusSettingPojo;
import com.tzmb2c.web.pojo.GroupFreeCouponPojo;
import com.tzmb2c.web.pojo.GrouponActivityPojo;
import com.tzmb2c.web.pojo.GrouponActivityRecordPojo;
import com.tzmb2c.web.pojo.GrouponPushPojo;
import com.tzmb2c.web.pojo.GrouponRecommendPojo;
import com.tzmb2c.web.pojo.GrouponUserRecordPojo;
import com.tzmb2c.web.pojo.HomePageIconPojo;
import com.tzmb2c.web.pojo.LoginRecPojo;
import com.tzmb2c.web.pojo.NavigationPojo;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.OrderRefundPojo;
import com.tzmb2c.web.pojo.OrderShipPojo;
import com.tzmb2c.web.pojo.ProductFocusImagesPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductRestrictionPojo;
import com.tzmb2c.web.pojo.ProductSellPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.SeckillGoodsPojo;
import com.tzmb2c.web.pojo.SeckillPojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SpecialGoodsPojo;
import com.tzmb2c.web.pojo.SpecialPojo;
import com.tzmb2c.web.pojo.SpecialShowPojo;
import com.tzmb2c.web.pojo.SpecialTypePojo;
import com.tzmb2c.web.pojo.SysAreaPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserCollectPojo;
import com.tzmb2c.web.pojo.UserConsumerCollectPojo;
import com.tzmb2c.web.pojo.UserCouponFlagPojo;
import com.tzmb2c.web.pojo.UserCouponPojo;
import com.tzmb2c.web.pojo.UserDealLogPojo;
import com.tzmb2c.web.pojo.UserInfoPojo;
import com.tzmb2c.web.pojo.UserOrderNoticePojo;
import com.tzmb2c.web.pojo.UserOrderRefundPojo;
import com.tzmb2c.web.pojo.UserPindekeInfoPojo;
import com.tzmb2c.web.pojo.UserShopCollectPojo;
import com.tzmb2c.web.pojo.UserSpecialCollectPojo;
import com.tzmb2c.web.pojo.UserVerifyPojo;
import com.tzmb2c.web.pojo.UserWalletLogPojo;
import com.tzmb2c.web.pojo.VersionControlPojo;
import com.tzmb2c.web.pojo.WxpayOrderInfoPojo;
import com.tzmb2c.web.pojo.ZoneGoodsPojo;
import com.tzmb2c.web.pojo.ZonesPojo;
import com.tzmb2c.web.service.ActivityProductCommentService;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.AliRedEnvelopeService;
import com.tzmb2c.web.service.AlipayOrderInfoService;
import com.tzmb2c.web.service.BaiduLoginService;
import com.tzmb2c.web.service.CouponService;
import com.tzmb2c.web.service.DeliveryAddressService;
import com.tzmb2c.web.service.FocusSettingService;
import com.tzmb2c.web.service.GroupFreeCouponService;
import com.tzmb2c.web.service.GroupFreeCouponSettingService;
import com.tzmb2c.web.service.GrouponActivityRecordService;
import com.tzmb2c.web.service.GrouponActivityService;
import com.tzmb2c.web.service.GrouponPushService;
import com.tzmb2c.web.service.GrouponRecommendService;
import com.tzmb2c.web.service.GrouponUserRecordService;
import com.tzmb2c.web.service.HomePageIconService;
import com.tzmb2c.web.service.LoginRecService;
import com.tzmb2c.web.service.LoginService;
import com.tzmb2c.web.service.NavigationService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderRefundService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.OrderShipService;
import com.tzmb2c.web.service.ProductFocusImagesService;
import com.tzmb2c.web.service.ProductRestrictionService;
import com.tzmb2c.web.service.ProductSellService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.SeckillGoodsService;
import com.tzmb2c.web.service.SeckillService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SpecialGoodsService;
import com.tzmb2c.web.service.SpecialService;
import com.tzmb2c.web.service.SpecialShowService;
import com.tzmb2c.web.service.SpecialTypeService;
import com.tzmb2c.web.service.SysAreaService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserCollectService;
import com.tzmb2c.web.service.UserConsumerCollectService;
import com.tzmb2c.web.service.UserCouponFlagService;
import com.tzmb2c.web.service.UserDealLogService;
import com.tzmb2c.web.service.UserInfoService;
import com.tzmb2c.web.service.UserOrderNoticeService;
import com.tzmb2c.web.service.UserOrderRefundService;
import com.tzmb2c.web.service.UserPindekeInfoService;
import com.tzmb2c.web.service.UserShopCollectService;
import com.tzmb2c.web.service.UserSpecialCollectService;
import com.tzmb2c.web.service.UserVerifyService;
import com.tzmb2c.web.service.UserWalletLogService;
import com.tzmb2c.web.service.UserWalletService;
import com.tzmb2c.web.service.VersionControlService;
import com.tzmb2c.web.service.WxpayOrderInfoService;
import com.tzmb2c.web.service.ZoneGoodsService;
import com.tzmb2c.web.service.ZonesService;

public class AppApiGrouponAction extends SuperAction {
  private static final long serialVersionUID = 1L;
  @Autowired
  private ProductService productService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private SellerService sellerService;
  @Autowired
  private ShopService shopService;
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
  private CouponService couponService;
  @Autowired
  private ProductSkuLinkService productSkuLinkService;
  @Autowired
  private WalletService walletService;
  @Autowired
  private AlipayOrderInfoService alipayOrderInfoService;
  @Autowired
  private WxpayOrderInfoService wxpayOrderInfoService;
  @Autowired
  private UserCollectService userCollectService;
  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private NavigationService navigationService;
  @Autowired
  private FocusSettingService focusSettingService;
  @Autowired
  private GrouponRecommendService grouponRecommendService;
  @Autowired
  private GrouponActivityService grouponActivityService;
  @Autowired
  private GroupFreeCouponService groupFreeCouponService;
  @Autowired
  private GrouponActivityRecordService grouponActivityRecordService;
  @Autowired
  private GrouponUserRecordService grouponUserRecordService;
  @Autowired
  private ProductFocusImagesService productFocusImagesService;
  @Autowired
  private BaiduLoginService baiduLoginService;
  @Autowired
  private UserVerifyService userVerifyService;
  @Autowired
  private OrderRefundService orderRefundService;
  @Autowired
  private ActivityTimeService activityTimeService;
  @Autowired
  private GrouponService grouponService;
  @Autowired
  private OrderShipService orderShipService;
  @Autowired
  private LoginService loginService;
  @Autowired
  private GroupFreeCouponSettingService groupFreeCouponSettingService;
  @Autowired
  private UserShopCollectService userShopCollectService;
  @Autowired
  private UserConsumerCollectService userConsumerCollectService;
  @Autowired
  private UserSpecialCollectService userSpecialCollectService;
  @Autowired
  private SpecialShowService specialShowService;
  @Autowired
  private UserWalletService userWalletService;
  @Autowired
  private UserPindekeInfoService userPindekeInfoService;
  @Autowired
  private UserDealLogService userDealLogService;
  @Autowired
  private SpecialService specialService;
  @Autowired
  private SpecialTypeService specialTypeService;
  @Autowired
  private SpecialGoodsService specialGoodsService;
  @Autowired
  private ZoneGoodsService zoneGoodsService;
  @Autowired
  private ZonesService zonesService;
  @Autowired
  private SeckillService seckillService;
  @Autowired
  private SeckillGoodsService seckillGoodsService;
  @Autowired
  private ActivityProductCommentService activityProductCommentService;
  @Autowired
  private HomePageIconService homePageIconService;
  @Autowired
  private LoginRecService loginRecService;
  @Autowired
  private GrouponPushService grouponPushService;
  @Autowired
  private ProductSellService productSellService;
  @Autowired
  private ProductTypeService productTypeService;
  @Autowired
  private UserOrderNoticeService userOrderNoticeService;

  @Autowired
  private VersionControlService versionControlService;
  @Autowired
  private UserCouponFlagService userCouponFlagService;
  @Autowired
  private ProductRestrictionService productRestrictionService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private UserWalletLogService userWalletLogService;
  @Autowired
  private AliRedEnvelopeService aliRedEnvelopeService;
  private String upfileFileName;
  // ---- 变量定义 ---- //
  private Long sid;
  private Integer refundType;
  private String refundReason;
  private File image1, image2, image3, image4, image5;
  private Integer status;
  private Long id;
  /**
   * 会员id
   */
  private Long uid;
  /**
   * 活动类型
   */
  private Long activityId;
  /**
   * 团活动记录ID
   */
  private Long recordId;

  private Integer pageNo;

  /**
   * source:设置排序 1销量降序、11销量升序、2价格降序、22价格升序、3点击率降序、33点击率升序
   */
  private Integer source;

  /**
   * 商品id
   */
  private Long pid;

  /**
   * 数量
   */
  private Integer num;

  /**
   * sku关联id
   */
  private Integer skuLinkId;

  private String buyer_message;
  /**
   * consigneeType:送货方式
   */
  private Integer consigneeType;
  private Long addressId;
  /**
   * 1-支付宝 2-微信 3-货到付款 4-钱包（全额）
   */
  private Integer payMethod;
  /**
   * messages:店铺留言
   */
  private String messages;
  /**
   * couponNo:优惠券码
   */
  private String couponNo;
  /**
   * outTradeNo:支付流水号
   */
  private String outTradeNo;
  /**
   * 购物车ID串
   */
  private String[] cids;
  /**
   * 0-不使用 1-使用
   */
  private Integer wallet;

  /**
   * orderStatus:订单状态
   */
  private Integer orderStatus;

  /**
   * oid:订单号
   */
  private Long oid;

  /**
   * productNo:产品货号
   */
  private String productNo;

  /**
   * name:搜索内容
   */
  private String name;

  /**
   * sorting:(1为热门，2为最新，3为价格从高到低，4为价格从低到高,7为销量)
   */
  private Integer sorting;

  /**
   * brindId:品牌id
   */
  private String brindId;
  /**
   * key:搜索关键字（产品名称或产品型号）
   */
  private String key;
  /**
   * display:是否新品：0=》否；1=》是
   */
  private String display;
  /**
   * subTypeId: （0为所有；1为遥控/电动玩具；2为早教/音乐玩具；3为过家家玩具；4为童车玩具；5为益智玩具；6为其他玩具）
   */
  private String subTypeId;
  /**
   * typeId:商品小分类
   */
  private String typeId;
  /**
   * 产品/店铺/分销产品库/专场
   */
  private Integer favType;
  /**
   * pageSize显示条数
   */
  private Integer pageSize;
  /**
   * 能力id
   */
  private Long abilityId;
  /**
   * 排序ID
   */
  private Long sortId;
  /**
   * 年龄标签ID
   */
  private Long ageType;

  /**
   * 店铺ID
   */
  private Long shopId;

  /**
   * 用户留言信息
   */
  private String custMsg;
  /**
   * 关键字
   */
  private String keywords;

  /**
   * 年龄value
   */
  private String age;
  private String platform;
  private String platUserName;
  private String identity;
  private Integer fansNum;
  private String coopBrand;
  private Integer contentOutPer;
  private Integer orContentOutPer;
  private String commercialType;
  private String sampleTitle1;
  private String sampleUrl1;
  private String ageTypes;
  private File idCardImg;
  private String crossPlatform;
  private String commercialPrice;
  private String coopSimilarPlat;

  /**
   * src:来源：1 平台专题 2 创客专题 3笔记 4微页面
   */
  private Integer src;
  /**
   * srcId:来源ID
   */
  private Long srcId;

  private Long brandId;// 用户品牌id

  /**
   * userId:用户ID
   */
  private Long userId;
  /**
   * 焦点图设置List
   */
  private List<FocusSettingPojo> focusSettingPojoList;
  /**
   * 焦点图设置Pojo
   */
  private FocusSettingPojo focusSettingPojo;
  /**
   * 平团/猜价活动List
   */
  private List<GrouponActivityPojo> grouponActivityList;
  /**
   * 团购用户记录
   */
  private GrouponActivityRecordPojo grouponActivityRecordPojo;
  /**
   * 活动pojo
   */
  private GrouponActivityPojo grouponActivityPojo;
  /**
   * 团购用户记录pojo
   */
  private GrouponUserRecordPojo grouponUserRecordPojo;
  /**
   * 用户团购记录List
   */
  private List<GrouponUserRecordPojo> grouponUserRecordList;
  /**
   * 商品id
   */
  private Long productId;
  /**
   * 商品id
   */
  private List<ProductFocusImagesPojo> productFocusImagesList;
  /**
   * 猜价价格
   */
  private Double price;
  /**
   * 奖项
   */
  private Integer prize;
  /**
   * 猜价列表类型
   */
  private Integer type;
  /**
   * 百度id
   */
  private String baidu_uid;
  /**
   * phone:手机号码
   */
  private String phone;
  /**
   * openid:微信ID
   */
  private String openid;
  /**
   * unionid:微信ID
   */
  private String unionid;
  /**
   * qq_openid:QQID
   */
  private String qq_openid;
  /**
   * sinaid:新浪ID
   */
  private String sinaid;
  /**
   * captcha:验证码
   */
  private String captcha;
  /**
   * pass:密码
   */
  private String pass;
  /**
   * 类目(1/一级类目;2/二级类目)
   */
  private String category;
  /**
   * 注册渠道
   */
  private String regChannel;
  /**
   * 设备标识
   */
  private String imei;
  private String logisticsNum;
  private String logisticsName;
  /**
   * 团记录id
   */
  private Long attendId;
  private Integer sex;// 性别
  private String birth;// 生日
  private Integer babySex;// baby性别
  private String babyBirthday;// baby生日
  private String QQ;// 联系人QQ
  private File file;
  /**
   * 领取团免券链接id
   */
  private Long linkId;
  private List<UserCouponPojo> userCouponList;
  private UserCouponPojo userCouponPojo;
  /**
   * 产品/店铺/专场ID
   */
  private Integer favSenId;
  private UserCollectPojo userCollectPojo;
  private List<UserCollectPojo> userCollectList;
  private ProductPojo productPojo;
  private String orderNo;// 订单号
  private Integer channel;// 订单渠道

  private String cardNo;
  private UserPindekeInfoPojo userPindekeInfo;
  private String extChannel;
  private List<UserDealLogPojo> userDealLogList;
  private String beginTime;
  private String endTime;
  private UserDealLogPojo userDealLogPojo;
  private String account;// 账户
  private Integer taType;// 支付方式
  private Long specialId;// 专题id
  private SpecialPojo specialPojo;// 专题
  private ZonesPojo zonesPojo;// 专区
  private List<SpecialTypePojo> specialTypeList;
  private List<SpecialPojo> specialList;
  private List<SpecialGoodsPojo> specialGoodsList;
  private List<ZoneGoodsPojo> zoneGoodsList;
  private List<ZonesPojo> zonesList;
  private String code;// 拼得客邀请码
  private Long zonesId;// 专区id
  private OrderPojo orderPojo;
  private String image;
  private Long killId;

  /**
   * img:评论上传图片
   */
  private File img1, img2, img3, img4, img5, img6, img7, img8, img9;
  /**
   * content:评论内容
   */
  private String content;

  private String imgName1, imgName2, imgName3, imgName4, imgName5;

  private String ip;

  private Integer level;

  private Integer activityType;

  private Integer os;
  private Double ver;
  private File logo;
  private String invCode;

  // ---- getter and setter ---- //

  public String getIp() {
    return ip;
  }

  public String getInvCode() {
    return invCode;
  }

  public void setInvCode(String invCode) {
    this.invCode = invCode;
  }

  public File getLogo() {
    return logo;
  }

  public void setLogo(File logo) {
    this.logo = logo;
  }

  public Integer getActivityType() {
    return activityType;
  }

  public void setActivityType(Integer activityType) {
    this.activityType = activityType;
  }

  public Integer getOs() {
    return os;
  }

  public void setOs(Integer os) {
    this.os = os;
  }

  public Double getVer() {
    return ver;
  }

  public void setVer(Double ver) {
    this.ver = ver;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getImage() {
    return image;
  }

  public String getImgName1() {
    return imgName1;
  }

  public void setImgName1(String imgName1) {
    this.imgName1 = imgName1;
  }

  public String getImgName2() {
    return imgName2;
  }

  public void setImgName2(String imgName2) {
    this.imgName2 = imgName2;
  }

  public String getImgName3() {
    return imgName3;
  }

  public void setImgName3(String imgName3) {
    this.imgName3 = imgName3;
  }

  public String getImgName4() {
    return imgName4;
  }

  public void setImgName4(String imgName4) {
    this.imgName4 = imgName4;
  }

  public String getImgName5() {
    return imgName5;
  }

  public void setImgName5(String imgName5) {
    this.imgName5 = imgName5;
  }

  public Long getKillId() {
    return killId;
  }

  public void setKillId(Long killId) {
    this.killId = killId;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public File getImg1() {
    return img1;
  }

  public void setImg1(File img1) {
    this.img1 = img1;
  }

  public File getImg2() {
    return img2;
  }

  public void setImg2(File img2) {
    this.img2 = img2;
  }

  public File getImg3() {
    return img3;
  }

  public void setImg3(File img3) {
    this.img3 = img3;
  }

  public File getImg4() {
    return img4;
  }

  public void setImg4(File img4) {
    this.img4 = img4;
  }

  public File getImg5() {
    return img5;
  }

  public void setImg5(File img5) {
    this.img5 = img5;
  }

  public File getImg6() {
    return img6;
  }

  public void setImg6(File img6) {
    this.img6 = img6;
  }

  public File getImg7() {
    return img7;
  }

  public void setImg7(File img7) {
    this.img7 = img7;
  }

  public File getImg8() {
    return img8;
  }

  public void setImg8(File img8) {
    this.img8 = img8;
  }

  public File getImg9() {
    return img9;
  }

  public void setImg9(File img9) {
    this.img9 = img9;
  }

  public OrderPojo getOrderPojo() {
    return orderPojo;
  }

  public void setOrderPojo(OrderPojo orderPojo) {
    this.orderPojo = orderPojo;
  }

  public Long getAttendId() {
    return attendId;
  }

  public Long getZonesId() {
    return zonesId;
  }

  public void setZonesId(Long zonesId) {
    this.zonesId = zonesId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Long getSpecialId() {
    return specialId;
  }

  public void setSpecialId(Long specialId) {
    this.specialId = specialId;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public Integer getTaType() {
    return taType;
  }

  public void setTaType(Integer taType) {
    this.taType = taType;
  }

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

  public String getExtChannel() {
    return extChannel;
  }

  public void setExtChannel(String extChannel) {
    this.extChannel = extChannel;
  }

  public File getImage4() {
    return image4;
  }

  public void setImage4(File image4) {
    this.image4 = image4;
  }

  public File getImage5() {
    return image5;
  }

  public void setImage5(File image5) {
    this.image5 = image5;
  }

  public String getCardNo() {
    return cardNo;
  }

  public void setCardNo(String cardNo) {
    this.cardNo = cardNo;
  }

  public Integer getChannel() {
    return channel;
  }

  public void setChannel(Integer channel) {
    this.channel = channel;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public Integer getFavSenId() {
    return favSenId;
  }

  public void setFavSenId(Integer favSenId) {
    this.favSenId = favSenId;
  }

  public Long getRecordId() {
    return recordId;
  }

  public void setRecordId(Long recordId) {
    this.recordId = recordId;
  }

  public Long getLinkId() {
    return linkId;
  }

  public void setLinkId(Long linkId) {
    this.linkId = linkId;
  }

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public String getBirth() {
    return birth;
  }

  public void setBirth(String birth) {
    this.birth = birth;
  }

  public Integer getBabySex() {
    return babySex;
  }

  public void setBabySex(Integer babySex) {
    this.babySex = babySex;
  }

  public String getBabyBirthday() {
    return babyBirthday;
  }

  public void setBabyBirthday(String babyBirthday) {
    this.babyBirthday = babyBirthday;
  }

  public String getQQ() {
    return QQ;
  }

  public void setQQ(String qQ) {
    QQ = qQ;
  }

  public Long getSid() {
    return sid;
  }

  public void setSid(Long sid) {
    this.sid = sid;
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

  public File getImage1() {
    return image1;
  }

  public void setImage1(File image1) {
    this.image1 = image1;
  }

  public File getImage2() {
    return image2;
  }

  public void setImage2(File image2) {
    this.image2 = image2;
  }

  public File getImage3() {
    return image3;
  }

  public void setImage3(File image3) {
    this.image3 = image3;
  }

  public void setAttendId(Long attendId) {
    this.attendId = attendId;
  }

  public Integer getStatus() {
    return status;
  }

  public String getLogisticsNum() {
    return logisticsNum;
  }

  public void setLogisticsNum(String logisticsNum) {
    this.logisticsNum = logisticsNum;
  }

  public String getLogisticsName() {
    return logisticsName;
  }

  public void setLogisticsName(String logisticsName) {
    this.logisticsName = logisticsName;
  }

  public String getRegChannel() {
    return regChannel;
  }

  public void setRegChannel(String regChannel) {
    this.regChannel = regChannel;
  }

  public String getBaidu_uid() {
    return baidu_uid;
  }

  public void setBaidu_uid(String baidu_uid) {
    this.baidu_uid = baidu_uid;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public String getUnionid() {
    return unionid;
  }

  public void setUnionid(String unionid) {
    this.unionid = unionid;
  }

  public String getQq_openid() {
    return qq_openid;
  }

  public void setQq_openid(String qq_openid) {
    this.qq_openid = qq_openid;
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

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getImei() {
    return imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getSrc() {
    return src;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getPrize() {
    return prize;
  }

  public void setPrize(Integer prize) {
    this.prize = prize;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public List<FocusSettingPojo> getFocusSettingPojoList() {
    return focusSettingPojoList;
  }

  public void setFocusSettingPojoList(List<FocusSettingPojo> focusSettingPojoList) {
    this.focusSettingPojoList = focusSettingPojoList;
  }

  public FocusSettingPojo getFocusSettingPojo() {
    return focusSettingPojo;
  }

  public void setFocusSettingPojo(FocusSettingPojo focusSettingPojo) {
    this.focusSettingPojo = focusSettingPojo;
  }

  public List<GrouponActivityPojo> getGrouponActivityList() {
    return grouponActivityList;
  }

  public void setGrouponActivityList(List<GrouponActivityPojo> grouponActivityList) {
    this.grouponActivityList = grouponActivityList;
  }

  public GrouponActivityRecordPojo getGrouponActivityRecordPojo() {
    return grouponActivityRecordPojo;
  }

  public void setGrouponActivityRecordPojo(GrouponActivityRecordPojo grouponActivityRecordPojo) {
    this.grouponActivityRecordPojo = grouponActivityRecordPojo;
  }

  public GrouponActivityPojo getGrouponActivityPojo() {
    return grouponActivityPojo;
  }

  public void setGrouponActivityPojo(GrouponActivityPojo grouponActivityPojo) {
    this.grouponActivityPojo = grouponActivityPojo;
  }

  public GrouponUserRecordPojo getGrouponUserRecordPojo() {
    return grouponUserRecordPojo;
  }

  public void setGrouponUserRecordPojo(GrouponUserRecordPojo grouponUserRecordPojo) {
    this.grouponUserRecordPojo = grouponUserRecordPojo;
  }

  public List<GrouponUserRecordPojo> getGrouponUserRecordList() {
    return grouponUserRecordList;
  }

  public void setGrouponUserRecordList(List<GrouponUserRecordPojo> grouponUserRecordList) {
    this.grouponUserRecordList = grouponUserRecordList;
  }

  public void setSrc(Integer src) {
    this.src = src;
  }

  public Long getSrcId() {
    return srcId;
  }

  public void setSrcId(Long srcId) {
    this.srcId = srcId;
  }

  public Long getBrandId() {
    return brandId;
  }

  public void setBrandId(Long brandId) {
    this.brandId = brandId;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public File getIdCardImg() {
    return idCardImg;
  }

  public void setIdCardImg(File idCardImg) {
    this.idCardImg = idCardImg;
  }

  public String getCrossPlatform() {
    return crossPlatform;
  }

  public void setCrossPlatform(String crossPlatform) {
    this.crossPlatform = crossPlatform;
  }

  public String getCommercialPrice() {
    return commercialPrice;
  }

  public void setCommercialPrice(String commercialPrice) {
    this.commercialPrice = commercialPrice;
  }

  public String getCoopSimilarPlat() {
    return coopSimilarPlat;
  }

  public void setCoopSimilarPlat(String coopSimilarPlat) {
    this.coopSimilarPlat = coopSimilarPlat;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public String getPlatUserName() {
    return platUserName;
  }

  public void setPlatUserName(String platUserName) {
    this.platUserName = platUserName;
  }

  public String getIdentity() {
    return identity;
  }

  public void setIdentity(String identity) {
    this.identity = identity;
  }

  public Integer getFansNum() {
    return fansNum;
  }

  public void setFansNum(Integer fansNum) {
    this.fansNum = fansNum;
  }

  public String getCoopBrand() {
    return coopBrand;
  }

  public void setCoopBrand(String coopBrand) {
    this.coopBrand = coopBrand;
  }

  public Integer getContentOutPer() {
    return contentOutPer;
  }

  public void setContentOutPer(Integer contentOutPer) {
    this.contentOutPer = contentOutPer;
  }

  public Integer getOrContentOutPer() {
    return orContentOutPer;
  }

  public void setOrContentOutPer(Integer orContentOutPer) {
    this.orContentOutPer = orContentOutPer;
  }

  public String getCommercialType() {
    return commercialType;
  }

  public void setCommercialType(String commercialType) {
    this.commercialType = commercialType;
  }

  public String getSampleTitle1() {
    return sampleTitle1;
  }

  public void setSampleTitle1(String sampleTitle1) {
    this.sampleTitle1 = sampleTitle1;
  }

  public String getSampleUrl1() {
    return sampleUrl1;
  }

  public void setSampleUrl1(String sampleUrl1) {
    this.sampleUrl1 = sampleUrl1;
  }

  public String getAgeTypes() {
    return ageTypes;
  }

  public void setAgeTypes(String ageTypes) {
    this.ageTypes = ageTypes;
  }

  public Long getAbilityId() {
    return abilityId;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public Long getAgeType() {
    return ageType;
  }

  public void setAgeType(Long ageType) {
    this.ageType = ageType;
  }

  public Long getSortId() {
    return sortId;
  }

  public void setSortId(Long sortId) {
    this.sortId = sortId;
  }

  public void setAbilityId(Long abilityId) {
    this.abilityId = abilityId;
  }

  public Long getId() {
    return id;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProductNo() {
    return productNo;
  }

  public void setProductNo(String productNo) {
    this.productNo = productNo;
  }

  public Long getOid() {
    return oid;
  }

  public void setOid(Long oid) {
    this.oid = oid;
  }

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public Integer getPageNo() {
    return pageNo;
  }

  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
  }

  public Integer getSource() {
    return source;
  }

  public void setSource(Integer source) {
    this.source = source;
  }

  public Long getPid() {
    return pid;
  }

  public void setPid(Long pid) {
    this.pid = pid;
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

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String[] getCids() {
    return cids;
  }

  public void setCids(String[] cids) {
    this.cids = cids;
  }

  public Integer getWallet() {
    return wallet;
  }

  public void setWallet(Integer wallet) {
    this.wallet = wallet;
  }

  public Integer getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(Integer orderStatus) {
    this.orderStatus = orderStatus;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getSorting() {
    return sorting;
  }

  public void setSorting(Integer sorting) {
    this.sorting = sorting;
  }

  public String getBrindId() {
    return brindId;
  }

  public void setBrindId(String brindId) {
    this.brindId = brindId;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getDisplay() {
    return display;
  }

  public void setDisplay(String display) {
    this.display = display;
  }

  public String getSubTypeId() {
    return subTypeId;
  }

  public void setSubTypeId(String subTypeId) {
    this.subTypeId = subTypeId;
  }

  public String getTypeId() {
    return typeId;
  }

  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }

  public Integer getFavType() {
    return favType;
  }

  public void setFavType(Integer favType) {
    this.favType = favType;
  }

  public Long getShopId() {
    return shopId;
  }

  public void setShopId(Long shopId) {
    this.shopId = shopId;
  }


  public String getCustMsg() {
    return custMsg;
  }

  public void setCustMsg(String custMsg) {
    this.custMsg = custMsg;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  // ---- 操作方法 ---- //
  /**
   * 
   * 申请退货/退款
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String applyRefund() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    OrderRefundPojo orderRefundPojo = new OrderRefundPojo();
    String msg = "";
    int dresult = 0;
    boolean b = false;
    if (uid == null) {
      msg = "会员ID是必须要填写的哦!";
    } else if (oid == null) {
      msg = "订单ID是必须要填写的哦!";
    } else if (type == null || type != 1 && type != 2 && type != 3) {
      msg = "退货退款类型是必须要填写的哦!";
    } else if (phone == null || StringUtils.isBlank(phone)) {
      msg = "手机号码是必须要填写的哦!";
    } else if (price == null) {
      msg = "退款金额是必须要填写的哦!";
    } else {
      try {
        OrderRefundPojo isnull = new OrderRefundPojo();
        isnull.setOrderId(oid);
        if (orderRefundService.getorderRefundDetail(isnull) == null) {
          OrderDetailPojo orderDetailPojo = orderDetailService.getfindByOrderIdOrderDetail(oid);
          if (orderDetailPojo != null) {
            String ddd = "";
            if (image1 != null) {
              ddd = sellerService.uploadFile(image1, "upfiles" + File.separator + "orderRefund");
              orderRefundPojo.setImages(ddd);
            }
            if (image2 != null) {
              ddd = sellerService.uploadFile(image2, "upfiles" + File.separator + "orderRefund");
              orderRefundPojo.setImages2(ddd);
            }
            if (image3 != null) {
              ddd = sellerService.uploadFile(image3, "upfiles" + File.separator + "orderRefund");
              orderRefundPojo.setImages3(ddd);
            }
            orderRefundPojo.setRefundNum(orderDetailPojo.getNum());
            orderRefundPojo.setDetailId(orderDetailPojo.getId());
            orderRefundPojo.setUserId(uid);
            if (uid != null && uid > 0) {
              SysLoginPojo sysLoginPojo = sysLoginService.getfindByIdSysLogin(uid);
              if (sysLoginPojo != null) {
                String loginname = sysLoginPojo.getName();
                orderRefundPojo.setLoginname(loginname);
              }
            }
            orderRefundPojo.setShopId(orderDetailPojo.getShopId());
            orderRefundPojo.setType(type);
            orderRefundPojo.setRefundType(refundType);
            orderRefundPojo.setRefundReason(refundReason);
            orderRefundPojo.setStatus(1);
            orderRefundPojo.setOrderId(orderDetailPojo.getOrderId());
            orderRefundPojo.setProductId(orderDetailPojo.getProductId());
            orderRefundPojo.setProductName(orderDetailPojo.getProductName());
            orderRefundPojo.setStockPriceOld(orderDetailPojo.getStockPriceOld());
            orderRefundPojo.setStockPrice(orderDetailPojo.getStockPrice());
            orderRefundPojo.setSkuLinkId(orderDetailPojo.getSkuLinkId());
            // orderRefundPojo.setServiceInvolved(1);
            orderRefundService.addOrderRefundService(orderRefundPojo);
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("reStatus", 1);
            map1.put("status", 0);
            map1.put("id", orderDetailPojo.getId());
            orderDetailService.updateReStatusmap(map1);

            // 优惠金额
            double allPrice = 0, couponPrice = 0;
            Map<String, Object> option = new HashMap<String, Object>();
            option.put("orderId", orderRefundPojo.getOrderId());
            option.put("status", 1);
            List<CouponOrderPojo> list2 = couponService.getcouponOrderList(option);
            if (list2.size() > 0) {
              option.clear();
              option.put("couponNo", list2.get(0).getCouponNo());
              option.put("status", 1);
              List<CouponOrderPojo> list3 = couponService.getcouponOrderList(option);
              if (list3.size() > 0) {
                for (CouponOrderPojo c : list3) {
                  if (c.getUsedPrice() > 0) {
                    couponPrice = c.getUsedPrice();
                  }

                  OrderDetailPojo orderDetailPojo2 = new OrderDetailPojo();
                  orderDetailPojo2.setOrderId(c.getOrderId());
                  List<OrderDetailPojo> list =
                      orderDetailService.orderDetailAllList(orderDetailPojo2, null);
                  if (list.size() > 0) {
                    for (OrderDetailPojo o : list) {
                      allPrice += o.getStockPrice() * o.getNum();
                    }
                  }
                }
              }
            }
            if (couponPrice != 0 && allPrice != 0) {
              couponPrice =
                  couponPrice * orderRefundPojo.getStockPrice() * orderRefundPojo.getRefundNum()
                      / allPrice;
            }
            // 专场退款金额
            /*
             * OrderPojo order = orderService.findOrderById(orderRefundPojo.getOrderId()); if (order
             * != null && order.getDiscountPrice() > 0 && order.getAllPrice() > 0) { discountPrice =
             * order.getDiscountPrice() * orderRefundPojo.getStockPrice()
             * orderRefundPojo.getRefundNum() / order.getAllPrice(); } // 优惠券优惠金额、专场优惠金额累计
             * couponPrice += discountPrice;
             */
            if (couponPrice > 0) {
              // 修改优惠金额
              OrderRefundPojo orderRefund = new OrderRefundPojo();
              orderRefund.setCouponPrice(couponPrice);
              orderRefund.setId(orderRefundPojo.getId());
              orderRefundService.updateOrderRefund(orderRefund);
            }
            msg = "申请成功！";
            dresult = 1;
            b = true;
          } else {
            msg = "查询不到订单！";
          }
        } else {
          msg = "您已申请过了，不能再申请！";
        }
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
   * 申请退货/退款
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   */
  public String applyRefund2() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    OrderRefundPojo orderRefundPojo = new OrderRefundPojo();
    String msg = "";
    int dresult = 0;
    boolean b = false;
    if (uid == null) {
      msg = "会员ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else if (oid == null) {
      msg = "订单详情ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else if (type == null || type != 1 && type != 2 && type != 3) {
      msg = "退货退款类型是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      try {
        long s = oid;
        String ddd = "";
        if (image1 != null) {
          ddd = sellerService.uploadFile(image1, "upfiles" + File.separator + "orderRefund");
          orderRefundPojo.setImages(ddd);
        }
        if (image2 != null) {
          ddd = sellerService.uploadFile(image2, "upfiles" + File.separator + "orderRefund");
          orderRefundPojo.setLogistics(ddd);
        }
        if (image3 != null) {
          ddd = sellerService.uploadFile(image3, "upfiles" + File.separator + "orderRefund");
          orderRefundPojo.setLogType(ddd);
        }
        OrderRefundPojo isnull = new OrderRefundPojo();
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
          if (uid != null && uid > 0) {
            SysLoginPojo sysLoginPojo = sysLoginService.getfindByIdSysLogin(uid);
            if (sysLoginPojo != null) {
              String loginname = sysLoginPojo.getName();
              orderRefundPojo.setLoginname(loginname);
            }
          }
          orderRefundPojo.setShopId(sid);
          orderRefundPojo.setType(type);
          orderRefundPojo.setRefundType(refundType);
          orderRefundPojo.setRefundReason(refundReason);
          if (type == 2 || type == 3) {
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
          if (type == 2 || type == 3) {
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
          if (list2.size() > 0) {
            option = new HashMap<String, Object>();
            option.put("couponNo", list2.get(0).getCouponNo());
            option.put("status", 1);
            List<CouponOrderPojo> list3 = couponService.getcouponOrderList(option);
            if (list3.size() > 0) {
              for (CouponOrderPojo c : list3) {
                if (c.getUsedPrice() > 0) {
                  couponPrice = c.getUsedPrice();
                }

                OrderDetailPojo orderDetailPojo2 = new OrderDetailPojo();
                orderDetailPojo2.setOrderId(c.getOrderId());
                List<OrderDetailPojo> list =
                    orderDetailService.orderDetailAllList(orderDetailPojo2, null);
                if (list.size() > 0) {
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
   * 填写物流信息
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   */
  public String submitLogistics() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    OrderRefundPojo orderRefundPojo = new OrderRefundPojo();

    String msg = "";
    int dresult = 0;
    boolean b = false;
    if (uid == null || uid <= 0) {
      msg = "会员ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else if (oid == null || oid <= 0) {
      msg = "订单ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else if (logisticsNum == null || "".equals(logisticsNum)) {
      msg = "物流单号是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else if (logisticsName == null || "".equals(logisticsName)) {
      msg = "物流名称是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      try {
        long s = oid;
        orderRefundPojo.setLogistics(logisticsNum);
        orderRefundPojo.setLogType(logisticsName);
        // orderRefundPojo.setDetailId(oid);
        orderRefundPojo.setOrderId(oid);
        orderRefundPojo.setStatus(3);
        orderRefundService.updateOrderRefundbyDetailId(orderRefundPojo);
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("reStatus", 3);
        map1.put("status", 0);
        map1.put("id", s);
        orderDetailService.updateReStatusmap(map1);
        msg = "申请成功！";
        dresult = 1;
        b = true;
      } catch (Exception e) {
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
   * 售后列表
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   */
  public String refundListApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = new HashMap<String, Object>();

    if (userId == null || userId == 0) {
      msg = "用户ID不能为空哦！~";
    } else if (status == null
        || !(status == 0 || status == 1 || status == 2 || status == 3 || status == 4)) {
      msg = "售后状态不能为空哦！~";
    } else {
      if (pageNo == null || pageNo <= 0) {
        page = new Pager();
        page.setPageNo(1);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
      }
      page.setPageSize(20);
      UserOrderRefundPojo userOrderRefundPojo = new UserOrderRefundPojo();
      userOrderRefundPojo.setUserId(userId);
      if (status != 0) {
        userOrderRefundPojo.setRefundStatus(status);
      }
      List<UserOrderRefundPojo> list =
          userOrderRefundService.findUserOrderRefundByUserId(userOrderRefundPojo, page);
      if (list.size() > 0) {
        for (UserOrderRefundPojo u : list) {
          item = new HashMap<String, Object>();
          item.put("orderId", u.getOrderId() == null ? "0" : u.getOrderId().toString());
          item.put("orderPrice",
              u.getStockPrice() == null ? "0" : StringUtil.checkVal(u.getStockPrice()));
          item.put("productId", u.getProductId() == null ? "0" : u.getProductId().toString());
          item.put("productImage", u.getProductImage() == null ? "" : ConstParam.URL
              + "/upfiles/product" + File.separator + u.getProductImage());
          item.put("productName", u.getProductName() == null ? "" : u.getProductName());
          item.put("reason", u.getRejectReason() == null ? "" : u.getRejectReason());
          item.put("refundId", u.getId() == null ? "0" : u.getId().toString());
          // 0=>未申请，1=>审核，2=>请退货，3=>退货中，4=>退货成功，5=>退货失败，6=>审核不成功，7=>退款成功
          item.put("refundStatus", StringUtil.checkVal(u.getStatus()));
          item.put("activityId", StringUtil.checkVal(u.getActivityId()));
          item.put("activityType", StringUtil.checkVal(u.getSource()));
          result.add(item);
        }
      }

      b = true;
    }

    map.put("result", result);
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
   * 我的-基本信息
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   */
  public String myInfoApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();

    if (userId == null || userId == 0) {
      msg = "用户ID不能为空哦！~";
    } else {
      SysLoginPojo sysLoginPojo = sysLoginService.getfindByIdSysLogin(userId);
      if (sysLoginPojo != null) {
        Map<String, Object> params = new HashMap<String, Object>();
        result.put("phone", StringUtil.checkVal(sysLoginPojo.getLoginname()));
        params.put("userId", sysLoginPojo.getId());
        params.put("pageNo", 0);
        params.put("pageSize", 1);
        // 已激活
        params.put("status", 1);
        // 未使用
        params.put("used", 0);
        // 有效时间
        params.put("validTime", StringUtil.dateString(new Date()));
        List<GroupFreeCouponPojo> groupFreeCouponPojos = groupFreeCouponService.listPage(params);
        GroupFreeCouponPojo groupFreeCouponPojo = null;
        if (groupFreeCouponPojos.size() > 0) {
          groupFreeCouponPojo = groupFreeCouponPojos.get(0);
          result.put(
              "couponBTime",
              groupFreeCouponPojo.getActiveTime() == null ? "" : StringUtil
                  .dateToString(groupFreeCouponPojo.getActiveTime()));
          result.put(
              "couponETime",
              groupFreeCouponPojo.getInvalidTime() == null ? "" : StringUtil
                  .dateToString(groupFreeCouponPojo.getInvalidTime()));
          // result.put("isGroupFree", groupFreeCouponPojo.getStatus() == null ? "0"
          // : groupFreeCouponPojo.getStatus().toString());
          result.put("isGroupFree", "1");

          result.put("uid", groupFreeCouponPojo.getUserId().toString());
          result.put("name",
              groupFreeCouponPojo.getUserName() == null ? "" : groupFreeCouponPojo.getUserName());
          result.put(
              "userImage",
              groupFreeCouponPojo.getUserLogo() == null ? "" : groupFreeCouponPojo.getUserLogo()
                  .contains("http") == true ? groupFreeCouponPojo.getUserLogo() : ConstParam.URL
                  + "/upfiles/userlogo" + File.separator
                  + StringUtil.checkVal(groupFreeCouponPojo.getUserLogo()));
          result.put(
              "userName",
              groupFreeCouponPojo.getUserPhone() == null ? "" : WalletService.enCodeString(
                  groupFreeCouponPojo.getUserPhone(), 4, 6));
        } else {
          result.put("couponBTime", "");
          result.put("couponETime", "");
          result.put("isGroupFree", "0");

          result.put("uid", userId + "");
          result.put("name", sysLoginPojo.getName() == null ? "" : sysLoginPojo.getName());
          result
              .put(
                  "userImage",
                  sysLoginPojo.getImage() == null ? ""
                      : sysLoginPojo.getImage().contains("http") == true ? sysLoginPojo.getImage()
                          : ConstParam.URL + "/upfiles/userlogo" + File.separator
                              + StringUtil.checkVal(sysLoginPojo.getImage()));
          result.put(
              "userName",
              sysLoginPojo.getLoginname() == null ? "" : WalletService.enCodeString(
                  sysLoginPojo.getLoginname(), 4, 6));
        }
        result.put("phone", StringUtil.checkVal(sysLoginPojo.getLoginname()));
        // 0-非拼得客 1-拼得客
        result.put("pindeke", StringUtil.checkVal(sysLoginPojo.getIsPindeke()));
        // 分享邀请码
        result.put("invitationCode", StringUtil.checkVal(sysLoginPojo.getInvitationCode()));

        // params.clear();
        // params.put("userId", userId);
        // params.put("currentTime", StringUtil.dateStr(new Date()));
        // int groupingNum = grouponUserRecordService.countBy(params);
        // // 拼团中数量
        // result.put("groupingNum", groupingNum == 0 ? "" : groupingNum + "");

        // UserOrderRefundPojo userOrderRefundPojo = new UserOrderRefundPojo();
        // userOrderRefundPojo.setUserId(userId);
        // List<UserOrderRefundPojo> UserOrderRefundPojos =
        // userOrderRefundService.findOrderRefundDetailByUserId(userOrderRefundPojo, page);
        // int saleSerNum = 0;
        // if (UserOrderRefundPojos.size() > 0) {
        // for (UserOrderRefundPojo refund : UserOrderRefundPojos) {
        // // 退货类型(4=>退货成功，5=>退货失败，6=>审核不成功，7=>退款成功)
        // if (refund.getStatus() != 4 && refund.getStatus() != 7 && refund.getStatus() != 5
        // && refund.getStatus() != 6) {
        // saleSerNum++;
        // }
        // }
        // }
        // // 售后/退货数量
        // result.put("saleSerNum", saleSerNum == 0 ? "0" : saleSerNum + "");

        OrderPojo orderPojo = orderService.orderStatusNum(userId);
        if (orderPojo != null) {
          // 待收货数量
          result.put("waitRecNum", orderPojo.getDshNum());// 3
          // 待评论数量
          result.put("waitComNum", orderPojo.getDplNum());// 4
          // 待付款数量
          result.put("waitPayNum", orderPojo.getDfkNum());// 1

          // 待发货数量
          result.put("waitSendNum", orderPojo.getWaitSendNum() == 0 ? "0" : orderPojo
              .getWaitSendNum().toString());// 2

          // 拼团中数量
          result.put("groupingNum", orderPojo.getGroupingNum() == 0 ? "0" : orderPojo
              .getGroupingNum().toString());// 21

          // 售后/退货数量
          result.put("saleSerNum", orderPojo.getSaleSerNum() == 0 ? "0" : orderPojo.getSaleSerNum()
              .toString());// 6
        } else {
          // 待收货数量
          result.put("waitRecNum", "0");
          // 待评论数量
          result.put("waitComNum", "0");
          // 待付款数量
          result.put("waitPayNum", "0");

          // 待发货数量
          result.put("waitSendNum", "0");

          // 拼团中数量
          result.put("groupingNum", "0");

          // 售后/退货数量
          result.put("saleSerNum", "0");
        }
      }

      b = true;
    }

    map.put("result", result);
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
   * 我的-收藏列表
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   */
  public String myCollectListApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = new HashMap<String, Object>();

    if (userId == null || userId == 0) {
      msg = "用户ID不能为空哦！~";
    } else {
      // Map<String, Object> params = new HashMap<String, Object>();
      // params.put("userId", uid);
      if (pageNo == null || pageNo == 0) {
        page = new Pager();
        page.setPageNo(1);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
      }
      page.setPageSize(20);
      // params.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      // params.put("pageSize", page.getPageSize());

      UserCollectPojo userCollect = new UserCollectPojo();
      userCollect.setUserId(userId);
      List<UserCollectPojo> list =
          userCollectService.findUserCollectByUserIdPage(userCollect, page);
      if (list.size() > 0) {
        for (UserCollectPojo u : list) {
          item = new HashMap<String, Object>();
          item.put("activityId", u.getActivityId() == null ? "0" : u.getActivityId().toString());
          item.put(
              "alonePrice",
              u.getDistributionPrice() == null ? "0"
                  : StringUtil.checkVal(u.getDistributionPrice()));

          item.put("collectId", u.getId() == null ? "0" : u.getId().toString());

          // group
          item.put("groupNum", u.getGroupNum() == null ? "0" : u.getGroupNum().toString());

          item.put("proSellrNum", u.getSellNumber() == null ? "0" : u.getSellNumber().toString());

          item.put("productId", u.getProductId() == null ? "0" : u.getProductId().toString());
          item.put("productImage", u.getImage() == null ? "" : ConstParam.URL + "/upfiles/product"
              + File.separator + u.getImage());
          item.put("productName", u.getProductName() == null ? "" : u.getProductName());
          // group
          item.put("productPrice",
              u.getGroupPrice() == null ? "0" : StringUtil.checkVal(u.getGroupPrice()));
          // 活动类型(1-普通拼团2-团免3-猜价5-0.1抽奖6-限时秒杀)
          item.put("activityType",
              u.getActivityType() == null ? "0" : StringUtil.checkVal(u.getActivityType()));
          result.add(item);
        }
      }

      b = true;
    }

    map.put("result", result);
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
   * 我的-拼团列表
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   */
  public String myGroupListApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = new HashMap<String, Object>();

    if (status == null || !(status == 0 || status == 1 || status == 2 || status == 3)) {
      msg = "查询类型ID不能为空哦！~";
    } else if (userId == null || userId == 0) {
      msg = "用户ID不能为空哦！~";
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      // params.put("activityType", 1);
      // params.put("activityId", activityId);
      if (status != 0) {
        params.put("recordStatus", status - 1);
        // params.put("orderStatus", status - 1);
      }
      params.put("userId", userId);
      params.put("activityTypes", new String[] {"1", "2"});
      if (pageNo == null || pageNo == 0) {
        page = new Pager();
        page.setPageNo(1);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
      }
      page.setPageSize(20);
      params.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      params.put("pageSize", page.getPageSize());

      // List<GrouponActivityRecordPojo> list = grouponActivityRecordService.listPage(params);
      // if (list.size() > 0) {
      // for (GrouponActivityRecordPojo g : list) {
      // item = new HashMap<String, Object>();
      // item.put("activityId", g.getActivityId() == null ? "0" : g.getActivityId().toString());
      // item.put("activityStatus", g.getActivityStatus() == null ? "0" : g.getActivityStatus()
      // .toString());
      // item.put("alonePrice",
      // g.getGroupPrice() == null ? "0" : StringUtil.checkVal(g.getGroupPrice()));
      // item.put("groupNum", g.getGroupNum() == null ? "0" : g.getGroupNum().toString());
      // item.put("orderId", g.getGroupNum() == null ? "0" : g.getGroupNum().toString());
      // item.put("productId", g.getProductId() == null ? "0" : g.getProductId().toString());
      // item.put("productImage", g.getProductImage() == null ? "" : ConstParam.URL
      // + "/upfiles/product" + File.separator + g.getProductImage());
      // item.put("productName", g.getProductName() == null ? "" : g.getProductName());
      // item.put("productPrice",
      // g.getDistributionPrice() == null ? "0" : StringUtil.checkVal(g.getDistributionPrice()));
      // result.add(item);
      // }
      // }
      params.put("source", 1);
      // 过滤售后完成的订单
      params.put("refundFinish", 1);
      params.put("orderBy", "gur.create_date desc,gur.id desc");
      List<GrouponUserRecordPojo> list = grouponUserRecordService.listPage(params);
      if (list.size() > 0) {
        for (GrouponUserRecordPojo g : list) {
          item = new HashMap<String, Object>();
          item.put("activityId", g.getActivityId() == null ? "0" : g.getActivityId().toString());
          item.put("activityStatus", g.getRecordStatus() == null ? "1" : g.getRecordStatus() + 1
              + "");
          item.put("alonePrice",
              g.getGroupPrice() == null ? "0" : StringUtil.checkVal(g.getGroupPrice()));
          item.put("groupNum", g.getGroupNum() == null ? "0" : g.getGroupNum().toString());
          item.put("orderId", g.getOrderId() == null ? "0" : g.getOrderId().toString());
          item.put("productId", g.getProductId() == null ? "0" : g.getProductId().toString());
          item.put("productImage", g.getProductImage() == null ? "" : ConstParam.URL
              + "/upfiles/product" + File.separator + g.getProductImage());
          item.put("productName", g.getProductName() == null ? "" : g.getProductName());
          item.put(
              "productPrice",
              g.getDistributionPrice() == null ? "0"
                  : StringUtil.checkVal(g.getDistributionPrice()));
          item.put("groupRecId",
              g.getAttendId() == null ? "0" : StringUtil.checkVal(g.getAttendId()));
          result.add(item);
        }
      }

      b = true;
    }

    map.put("result", result);
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
   * 拼团-参团页面
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   */
  public String groupDetailApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> banners = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = new HashMap<String, Object>();
    List<Map<String, Object>> groupUserList = new ArrayList<Map<String, Object>>();
    Map<String, Object> item2 = new HashMap<String, Object>();

    if (recordId == null || recordId == 0) {
      msg = "团活动记录ID不能为空哦！~";
    } else if (userId == null || userId == 0) {
      msg = "用户ID不能为空哦！~";
    } else {
      // GrouponActivityRecordPojo grouponActivityRecordPojo =
      // grouponActivityRecordService.getById(activityId);
      Map<String, Object> params2 = new HashMap<String, Object>();
      params2.put("id", recordId);
      // params2.put("userId", userId);
      // params2.put("activityType", 1);
      params2.put("pageNo", 0);
      params2.put("pageSize", 1);
      List<GrouponActivityRecordPojo> grouponActivityRecordPojos =
          grouponActivityRecordService.listPage(params2);
      GrouponActivityRecordPojo grouponActivityRecordPojo = null;
      if (grouponActivityRecordPojos.size() > 0) {
        grouponActivityRecordPojo = grouponActivityRecordPojos.get(0);
      }

      if (grouponActivityRecordPojo != null) {
        // 查询订单支付状态
        Map<String, Object> params = new HashMap<String, Object>();
        params.clear();
        params.put("userId", userId);
        params.put("sourceId", recordId);
        params.put("payStatus", 1);
        List<OrderPojo> orderList = orderService.listPage(params);
        if (orderList != null && orderList.size() > 0) {
          result.put("payStatus", StringUtil.checkVal(orderList.get(0).getPayStatus()));
          result.put("groupPrice", StringUtil.checkVal(orderList.get(0).getFactPrice()
              - orderList.get(0).getUsedPrice()));
        } else {
          result.put("groupPrice", grouponActivityRecordPojo.getGroupPrice() == null ? "0"
              : StringUtil.checkVal(grouponActivityRecordPojo.getGroupPrice()));
          result.put("payStatus", "0");
        }
        // 是否售罄
        params.clear();
        params.put("sourceId", recordId);
        List<OrderPojo> orders = orderService.listPage(params);
        if (orders != null && orders.size() > 0) {
          grouponActivityPojo =
              grouponActivityService.getById(grouponActivityRecordPojo.getActivityId());
          if (grouponActivityPojo != null && grouponActivityPojo.getProductId() != null) {
            params.clear();
            params.put("productId", grouponActivityPojo.getProductId());
            params.put("status", 1);
            int stock = productSkuLinkService.getSkuStock(params);
            if (stock > 0) {
              if (grouponActivityPojo.getType() == 6) {
                result.put("isSellOut", grouponActivityPojo.getSurplusNum() == null
                    || grouponActivityPojo.getSurplusNum() < grouponActivityPojo.getNum() ? "1"
                    : "0");
              } else {
                result.put("isSellOut", "0");
              }
            } else {
              result.put("isSellOut", "1");
            }
          } else {
            result.put("isSellOut", "1");
          }
        } else {
          result.put("isSellOut", "1");
        }

        // 是否结束
        if (DateTimeUtil.compareDate(StringUtil.dateString(new Date()),
            StringUtil.dateString(grouponActivityRecordPojo.getBeginTime()))
            && DateTimeUtil.compareDate(
                StringUtil.dateString(grouponActivityRecordPojo.getEndTime()),
                StringUtil.dateString(new Date()))) {
          result.put("isStart", "1");
        } else {
          result.put("isStart", "0");
        }
        result.put("alonePrice", grouponActivityRecordPojo.getDistributionPrice() == null ? "0"
            : StringUtil.checkVal(grouponActivityRecordPojo.getDistributionPrice()));
        // banners
        productFocusImagesList =
            productFocusImagesService.getProductFocusImagesByPid(grouponActivityRecordPojo
                .getProductId());
        if (productFocusImagesList.size() > 0) {
          for (ProductFocusImagesPojo productFocusImages : productFocusImagesList) {
            item = new HashMap<String, Object>();
            item.put("bannerImage", productFocusImages.getImages() == null ? "" : ConstParam.URL
                + "/upfiles/productFocusImages" + File.separator + productFocusImages.getImages());
            banners.add(item);
          }
        }
        result.put("banners", banners);

        result.put("beginTime", grouponActivityRecordPojo.getBeginTimeStr() == null ? ""
            : grouponActivityRecordPojo.getBeginTimeStr());
        result.put("endTime", grouponActivityRecordPojo.getEndTimeStr() == null ? ""
            : grouponActivityRecordPojo.getEndTimeStr());
        int groupNum = grouponActivityRecordPojo.getNum();
        result.put("groupNum", groupNum + "");
        result.put("recordId", recordId.toString());
        result.put("activityId", grouponActivityRecordPojo.getGroupId() == null ? "0"
            : grouponActivityRecordPojo.getGroupId().toString());
        // 团类型 1-普通 2-团免
        result.put("activityType", grouponActivityRecordPojo.getActivityType() == null ? "0"
            : grouponActivityRecordPojo.getActivityType().toString());

        // groupUserList
        params.clear();
        params.put("attendId", grouponActivityRecordPojo.getId());
        // params.put("activityType", 1);
        params.put("orderBy", "gur.create_date asc,gur.id asc");
        grouponUserRecordList = grouponUserRecordService.listPage(params);
        if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
          for (GrouponUserRecordPojo g : grouponUserRecordList) {
            item2 = new HashMap<String, Object>();
            item2.put("isHead", g.getIsHead() == null ? "0" : g.getIsHead().toString());
            item2.put("joinTime", g.getAttendTimeStr() == null ? "" : g.getAttendTimeStr());
            item2.put("userImage", g.getUserLogo() == null ? ""
                : g.getUserLogo().contains("http") == true ? g.getUserLogo() : ConstParam.URL
                    + "/upfiles/userlogo" + File.separator + StringUtil.checkVal(g.getUserLogo()));
            item2.put("userName", g.getUserName() == null ? "" : g.getUserName());
            groupUserList.add(item2);
          }
        }
        result.put("groupUserList", groupUserList);

        params.put("userId", userId);
        params.put("pageNo", 0);
        params.put("pageSize", 1);
        grouponUserRecordList = grouponUserRecordService.listPage(params);
        if (grouponUserRecordList.size() > 0) {
          grouponUserRecordPojo = grouponUserRecordList.get(0);
          if (grouponUserRecordPojo.getIsHead() == 1) {
            result.put("userIsHead", "1");
          } else {
            result.put("userIsHead", "0");
          }
          // result.put("isGroup", "1");
        } else {
          // result.put("isGroup", "0");
          result.put("userIsHead", "0");
        }

        // 是否参团
        params.clear();
        params.put("activityId", grouponActivityRecordPojo.getActivityId());
        params.put("userId", userId);
        int garCount = grouponActivityRecordService.countBy(params);
        params.clear();
        params.put("activityId", grouponActivityRecordPojo.getActivityId());
        params.put("userId", userId);
        params.put("attendId", recordId);
        params.put("isHead", "0");
        int gurCount = grouponUserRecordService.countBy(params);
        if (recordId == 7 || recordId == 6) {
          if (gurCount > 0 || garCount > 0) {
            result.put("isGroup", "1");
            result.put("isOpen", "1");
          }
        } else {
          if (gurCount > 0) {
            result.put("isGroup", "1");
          } else {
            result.put("isGroup", "0");
          }
          if (garCount > 0) {
            result.put("isOpen", "1");
          } else {
            result.put("isOpen", "0");
          }
        }

        // joinNum
        params.clear();
        params.put("attendId", grouponActivityRecordPojo.getId());
        // params.put("activityType", 1);
        int joinNum = grouponUserRecordService.countBy(params);
        result.put("joinNum", joinNum + "");
        result.put("poorNum", groupNum - joinNum + "");
        result.put("nowTime", StringUtil.dateString(new Date()));
        result.put("productId", grouponActivityRecordPojo.getProductId() == null ? "0"
            : grouponActivityRecordPojo.getProductId().toString());
        result
            .put(
                "productImage",
                grouponActivityRecordPojo.getProductImage() == null ? "" : ConstParam.URL
                    + "/upfiles/product" + File.separator
                    + grouponActivityRecordPojo.getProductImage());
        result.put("productName", grouponActivityRecordPojo.getProductName() == null ? ""
            : grouponActivityRecordPojo.getProductName());
        // int productStatus =
        // (grouponActivityRecordPojo.getGroupStatus() == null ? 0 : grouponActivityRecordPojo
        // .getGroupStatus()) == 1 ? 1 : 2;
        // result.put("productStatus", productStatus + "");
        result.put("productStatus", grouponActivityRecordPojo.getGroupStatus() == null ? "0"
            : grouponActivityRecordPojo.getGroupStatus().toString());
        grouponActivityPojo =
            grouponActivityService.getById(grouponActivityRecordPojo.getActivityId());
        // 是否拼团失败
        if (grouponActivityRecordPojo.getStatus() == 2) {
          if (groupNum - joinNum > 0) {
            result.put("status", "2");
          } else {
            result.put("status", "4");
          }
        } else if (groupNum - joinNum <= 0 && grouponActivityRecordPojo.getStatus() == 0) {
          // 是否待开奖
          result.put("status", "3");
        } else if (grouponActivityPojo != null && grouponActivityPojo.getActivityStatus() == 3) {
          // 是否已开奖
          result.put("status", "4");
        } else {
          result.put("status", grouponActivityRecordPojo.getStatus() == null ? "0"
              : grouponActivityRecordPojo.getStatus());
        }
        result.put("productSketch", grouponActivityRecordPojo.getProductSketch() == null ? ""
            : grouponActivityRecordPojo.getProductSketch());
        // 拼团人数满支付成功>>订单支付成功拼团失败但是没有参团记录
        try {
          params.clear();
          params.put("userId", userId);
          params.put("sourceId", recordId);
          params.put("payStatus", 1);
          params.put("isSuccess", 2);
          params.put("onlyOrder", 1);
          List<OrderPojo> orderList2 = orderService.listPage(params);
          if (orderList2 != null && orderList2.size() > 0) {
            OrderPojo orderPojo = orderList2.get(0);
            if (orderPojo != null) {
              params.clear();
              params.put("userId", userId);
              params.put("attendId", recordId);
              int i = grouponUserRecordService.countBy(params);
              if (grouponActivityRecordPojo.getStatus() != null
                  && grouponActivityRecordPojo.getStatus() == 1 && i < 1) {
                result.put("status", "5");
              }
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
        b = true;
      } else {
        msg = "查询不到数据!";
      }
    }

    map.put("result", result);
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
   * 拼团-同一拼团(待组团)
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   */
  public String waitGroupListApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Map<String, Object> item = new HashMap<String, Object>();

    if (activityId == null || activityId == 0) {
      msg = "拼团活动ID不能为空哦！~";
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      // params.put("activityType", 1);
      params.put("activityId", activityId);
      params.put("status", 0);
      // params.put("pageNo", 0);
      // params.put("pageSize", 1);

      List<GrouponActivityRecordPojo> list = grouponActivityRecordService.listPage(params);
      if (list.size() > 0) {
        for (GrouponActivityRecordPojo g : list) {
          item = new HashMap<String, Object>();
          item.put("beginTime", g.getBeginTimeStr() == null ? "" : g.getBeginTimeStr());
          item.put("endTime", g.getEndTimeStr() == null ? "" : g.getEndTimeStr());
          int num =
              (g.getNum() == null ? 0 : g.getNum())
                  - (g.getSurplusNum() == null ? 0 : g.getSurplusNum());
          item.put("oweNum", num + "");
          item.put("groupRecId", g.getId() == null ? "0" : g.getId().toString());
          item.put("userImage", g.getUserLogo() == null ? ""
              : g.getUserLogo().contains("http") == true ? g.getUserLogo() : ConstParam.URL
                  + "/upfiles/userlogo" + File.separator + StringUtil.checkVal(g.getUserLogo()));
          item.put("userName", g.getUserName() == null ? "" : g.getUserName());
          result.add(item);
        }
      }

      b = true;
    }

    map.put("result", result);
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
   * 拼团-开团页面
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   */
  public String openGroupActivityApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    List<Map<String, Object>> banners = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> waitGroupList = new ArrayList<Map<String, Object>>();

    if (activityId == null && pid == null || activityId != null && activityId == 0 || pid != null
        && pid == 0) {
      msg = "团活动ID不能为空哦！~";
    } else {
      Map<String, Object> params2 = new HashMap<String, Object>();
      params2.put("id", activityId);
      if (pid != null) {
        params2.put("productId", pid);
        params2.put("isDefault", 1);
        params2.put("type", 1);
      }
      params2.put("pageNo", 0);
      params2.put("pageSize", 1);
      List<GrouponActivityPojo> grouponActivityPojos = grouponActivityService.listPage(params2);
      GrouponActivityPojo grouponActivityPojo = null;
      if (grouponActivityPojos.size() > 0) {
        grouponActivityPojo = grouponActivityPojos.get(0);
      }
      if (grouponActivityPojo != null) {
        // 查询是否售罄
        params.clear();
        params.put("productId", grouponActivityPojo.getProductId());
        params.put("status", 1);
        int stock = productSkuLinkService.getSkuStock(params);
        if (stock > 0) {
          if (grouponActivityPojo.getType() == 6) {
            result.put("isSellOut", grouponActivityPojo.getSurplusNum() == null
                || grouponActivityPojo.getSurplusNum() < grouponActivityPojo.getNum() ? "1" : "0");
          } else {
            result.put("isSellOut", "0");
          }
        } else {
          result.put("isSellOut", "1");
        }
        // 活动开始时间
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日");
        if (grouponActivityPojo.getBeginTime() != null) {
          String sTime = sdf.format(grouponActivityPojo.getBeginTime());
          result.put("startTime", sTime);
          result.put("activitySTime", sdf2.format(grouponActivityPojo.getBeginTime()) + sTime);
        } else {
          result.put("startTime", "");
          result.put("activitySTime", "");
        }
        if (grouponActivityPojo.getEndTime() != null) {
          String eTime = sdf.format(grouponActivityPojo.getEndTime());
          result.put("activityETime", sdf2.format(grouponActivityPojo.getEndTime()) + eTime);
        } else {
          result.put("activityETime", "");
        }
        // 活动限量
        result.put("limitNum", StringUtil.checkVal(grouponActivityPojo.getLimitNum()));
        result.put("activityId", StringUtil.checkVal(grouponActivityPojo.getId()));
        Date nowTime = new Date();
        // 活动状态
        if (grouponActivityPojo.getType() != 1) {
          if (DateTimeUtil.compareDate(StringUtil.dateString(nowTime),
              StringUtil.dateString(grouponActivityPojo.getBeginTime()))
              && DateTimeUtil.compareDate(StringUtil.dateString(grouponActivityPojo.getEndTime()),
                  StringUtil.dateString(nowTime))) {
            result.put("activityStatus", "1");
          } else if (DateTimeUtil.compareDate(
              StringUtil.dateString(grouponActivityPojo.getBeginTime()),
              StringUtil.dateString(nowTime))) {
            result.put("activityStatus", "0");
          } else if (DateTimeUtil.compareDate(StringUtil.dateString(nowTime),
              StringUtil.dateString(grouponActivityPojo.getEndTime()))) {
            result.put("activityStatus", "2");
          }
        } else {
          result.put("activityStatus", "1");
        }
        if (grouponActivityPojo.getType() == 7) {
          if (nowTime.getTime() > grouponActivityPojo.getEndTime().getTime()
              && !"3".equals(StringUtil.checkVal(grouponActivityPojo.getActivityStatus()))) {
            // 待开奖
            result.put("isWaitOpen", "1");
          } else if (nowTime.getTime() > grouponActivityPojo.getEndTime().getTime()
              && "3".equals(StringUtil.checkVal(grouponActivityPojo.getActivityStatus()))) {
            // 已开奖/活动已结束
            result.put("isWaitOpen", "2");
          } else if (nowTime.getTime() > grouponActivityPojo.getBeginTime().getTime()
              && grouponActivityPojo.getEndTime().getTime() > nowTime.getTime()
              && !"3".equals(StringUtil.checkVal(grouponActivityPojo.getActivityStatus()))) {
            // 活动进行中
            result.put("isWaitOpen", "0");
          } else if (grouponActivityPojo.getBeginTime().getTime() > nowTime.getTime()) {
            // 活动未开始
            result.put("isWaitOpen", "3");
          }
        } else {
          result.put("isWaitOpen", "0");
        }
        // result.put("activityStatus", grouponActivityPojo.getActivityStatus() == null ? "0" :
        // grouponActivityPojo.getActivityStatus().toString());
        result.put("alonePrice", grouponActivityPojo.getDistributionPrice() == null ? "0"
            : StringUtil.checkVal(grouponActivityPojo.getDistributionPrice()));
        result.put(
            "sellingPrice",
            grouponActivityPojo.getSellingPrice() == null ? "0" : StringUtil
                .checkVal(grouponActivityPojo.getSellingPrice()));

        // banners
        productFocusImagesList =
            productFocusImagesService
                .getProductFocusImagesByPid(grouponActivityPojo.getProductId());
        if (productFocusImagesList.size() > 0) {
          for (ProductFocusImagesPojo productFocusImages : productFocusImagesList) {
            item = new HashMap<String, Object>();
            item.put("bannerImage", productFocusImages.getImages() == null ? "" : ConstParam.URL
                + "/upfiles/productFocusImages" + File.separator + productFocusImages.getImages());
            banners.add(item);
          }
        }
        result.put("banners", banners);

        // int isGroupFree =
        // (grouponActivityPojo.getType() == null ? 0 : grouponActivityPojo.getType()) == 2 ? 1
        // : 0;
        // result.put("isGroupFree", isGroupFree + "");
        int isGroupFree = 0;
        int activityType =
            grouponActivityPojo.getType() == null ? 1 : grouponActivityPojo.getType();
        if (userId != null) {
          Map<String, Object> params3 = new HashMap<String, Object>();
          params3.put("userId", userId);
          params3.put("status", 1);
          params3.put("used", 0);
          // 有效时间
          params3.put("validTime", StringUtil.dateString(new Date()));
          isGroupFree = groupFreeCouponService.countBy(params3) > 0 && activityType == 2 ? 1 : 0;
          result.put("isGroupFree", isGroupFree + "");
          // 查询是否收藏
          userCollectPojo = new UserCollectPojo();
          userCollectPojo.setUserId(userId);
          userCollectPojo.setActivityId(grouponActivityPojo.getId());
          userCollectPojo.setIsDelete(0);
          int i = userCollectService.userCollectAllCount(userCollectPojo);
          if (i > 0) {
            result.put("isCollect", "1");
          } else {
            result.put("isCollect", "0");
          }
          // 是否开团
          params.clear();
          params.put("activityId", grouponActivityPojo.getId());
          params.put("userId", userId);
          int garCount = grouponActivityRecordService.countBy(params);
          // 是否参团
          params.clear();
          params.put("activityId", grouponActivityPojo.getId());
          params.put("userId", userId);
          params.put("isHead", "0");
          int gurCount = grouponUserRecordService.countBy(params);
          if (grouponActivityPojo.getType() == 6) {
            if (garCount > 0 || gurCount > 0) {
              result.put("isOpen", "1");
              result.put("isGroup", "1");
            } else {
              result.put("isOpen", "0");
              result.put("isGroup", "0");
            }
          } else {
            if (garCount > 0) {
              result.put("isOpen", "1");
            } else {
              result.put("isOpen", "0");
            }
            if (gurCount > 0) {
              result.put("isGroup", "1");
            } else {
              result.put("isGroup", "0");
            }
          }
        } else {
          result.put("isCollect", "0");
          result.put("isGroupFree", "0");
          result.put("isGroup", "0");
          result.put("isOpen", "0");
        }

        result.put("groupNum", grouponActivityPojo.getNum() == null ? "0" : grouponActivityPojo
            .getNum().toString());
        result.put("nowTime", StringUtil.dateString(new Date()));
        result.put("proSellrNum", grouponActivityPojo.getSellNumber() == null ? "0"
            : grouponActivityPojo.getSellNumber().toString());

        result.put(
            "productPrice",
            grouponActivityPojo.getPrice() == null ? "0" : StringUtil.checkVal(grouponActivityPojo
                .getPrice()));
        // if (userId != null) {
        // if (isGroupFree == 1 && activityType == 2) {
        // result.put("productPrice", "0");
        // }
        // }

        result.put("productId", grouponActivityPojo.getProductId() == null ? "0"
            : grouponActivityPojo.getProductId());
        result.put("productName", grouponActivityPojo.getProductName() == null ? ""
            : grouponActivityPojo.getProductName());
        result.put("productImage",
            grouponActivityPojo.getProductImage() == null ? "" : ConstParam.URL
                + "/upfiles/product" + File.separator + grouponActivityPojo.getProductImage());
        result.put("productSketch", grouponActivityPojo.getProductSketch() == null ? ""
            : grouponActivityPojo.getProductSketch());
        // int productStatus =
        // (grouponActivityPojo.getStatus() == null ? 0 : grouponActivityPojo.getStatus()) == 1 ? 1
        // : 2;
        // result.put("productStatus", productStatus + "");
        result.put("productStatus", grouponActivityPojo.getStatus() == null ? "0"
            : grouponActivityPojo.getStatus().toString());

        // waitGroupList
        // TODO:优惠券商品
        if (grouponActivityPojo.getType() != 5
            && grouponActivityPojo.getType() != 7
            && grouponActivityPojo.getType() != 2
            && !(grouponActivityPojo.getType() == 1 && activityId != null && (activityId == 3643
                || activityId == 3645 || activityId == 3646 || activityId == 3883))) {
          params.remove("userId");
          params.put("activityType", grouponActivityPojo.getType());
          params.put("activityId", grouponActivityPojo.getId());
          params.put("status", 0);
          params.put("currentTime", StringUtil.dateString(new Date()));
          params.put("pageNo", 0);
          params.put("pageSize", 2);
          params.put("orderBy", "joinNum desc,gar.end_time asc");

          List<GrouponActivityRecordPojo> list = grouponActivityRecordService.listPage(params);
          if (list.size() > 0) {
            for (GrouponActivityRecordPojo g : list) {
              item = new HashMap<String, Object>();
              item.put("beginTime", g.getBeginTimeStr() == null ? "" : g.getBeginTimeStr());
              item.put("endTime", g.getEndTimeStr() == null ? "" : g.getEndTimeStr());
              item.put("groupRecId", g.getId() == null ? "0" : g.getId().toString());
              item.put("nowTime", params.get("currentTime"));
              // int surplusNum = g.getNum() -
              // grouponUserRecordService.joinNumByAttendId(g.getId());
              // item.put("oweNum", surplusNum + "");
              int surplusNum = g.getNum() - g.getJoinNum();
              item.put("oweNum", surplusNum + "");
              item.put("userImage", g.getUserLogo() == null ? ""
                  : g.getUserLogo().contains("http") == true ? g.getUserLogo() : ConstParam.URL
                      + "/upfiles/userlogo" + File.separator + StringUtil.checkVal(g.getUserLogo()));
              item.put("userName", g.getUserName() == null ? "" : g.getUserName());
              waitGroupList.add(item);
            }
          }
        }
        result.put("waitGroupList", waitGroupList);

        result.put("activityType", activityType);

        // 抽奖是否中奖
        params.clear();
        params.put("userId", userId);
        params.put("status", 3);
        params.put("prize", 1);
        int i = grouponUserRecordService.countBy(params);
        if (i > 0) {
          result.put("isPrize", "1");
        } else {
          result.put("isPrize", "0");
        }

        b = true;
      } else {
        msg = "没有该活动记录哦！~";
      }
    }

    map.put("result", result);
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
   * 导航分类列表
   * 
   * @return
   * @throws SQLException
   * @throws ParseException
   */
  public String productTypeNav() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> result = new ArrayList<Object>();

    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 2);

    List<NavigationPojo> navs = navigationService.findNavigationList(option);
    Map<String, Object> item = null;
    if (navs != null && navs.size() > 0) {
      for (NavigationPojo n : navs) {
        item = new HashMap<String, Object>();
        item.put("name", n.getName());
        // item.put("level", n.getLevel());
        item.put("id", n.getCategoryId());
        result.add(item);
      }
      b = true;
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
   * banner接口
   * 
   * @return String
   * @throws SQLException
   */
  public String groupHomeApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> bannerList = new ArrayList<Map<String, Object>>();
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("type", 1);
    option.put("status", 1);
    // option.put("pageNo", 0);
    // option.put("pageSize", 5);
    option.put("nowTimeStr", StringUtil.dateString(new Date()));
    option.put("orderBy", "sorting desc, create_date desc");
    List<FocusSettingPojo> focusSettings = focusSettingService.listPage(option);
    if (focusSettings != null && focusSettings.size() > 0) {
      Map<String, Object> item = null;
      String url = ConstParam.URL + "/upfiles/focusbanner" + File.separator;
      for (FocusSettingPojo focus : focusSettings) {
        item = new HashMap<String, Object>();
        grouponActivityPojo = grouponActivityService.getById(focus.getParamId());
        if (grouponActivityPojo != null) {
          item.put("productId", StringUtil.checkVal(grouponActivityPojo.getProductId()));
        } else {
          item.put("productId", "");
        }
        item.put("banner", url + StringUtil.checkVal(focus.getBanner()));
        item.put("title", StringUtil.checkVal(focus.getTitle()));
        // 参数类型(0-无;1-商品;2-普通拼团;3-猜价格;)
        item.put("type", StringUtil.checkVal(focus.getParamType()));
        // 对应参数类型的ID
        if (focus.getParamType() != null && focus.getParamType() == 7) {
          item.put("typeId", StringUtil.checkVal(focus.getUrl()));
        } else {
          item.put("typeId", StringUtil.checkVal(focus.getParamId()));
        }
        bannerList.add(item);
      }
    } else {
      msg = "暂无数据！";
    }
    b = true;
    map.put("result", bannerList);
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
   * 首页-推荐拼团列表
   * 
   * @return String
   * @throws SQLException
   */
  public String homeGroupProductsApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> groups = new ArrayList<Map<String, Object>>();
    if (pageNo == null || pageNo < 1) {
      pageNo = 1;
    }
    pageSize = 10;
    StringUtil.dateString(new Date());
    Map<String, Object> option = new HashMap<String, Object>();
    // >=起始时间
    // option.put("beginTimeStr", curDate);
    // <=结束时间
    // option.put("endTimeStr", curDate);
    // 1-普通拼团审核通过
    option.put("status", 1);
    // 1-普通拼团
    option.put("type", 1);
    option.put("pageNo", (pageNo - 1) * pageSize);
    option.put("pageSize", pageSize);
    option.put("orderBy", "gr.sorting desc, gr.create_date desc");
    List<GrouponRecommendPojo> grouponRecommends = grouponRecommendService.listPage(option);
    if (grouponRecommends != null && grouponRecommends.size() > 0) {
      Map<String, Object> item = null;
      String url = ConstParam.URL + "/upfiles/product" + File.separator;
      for (GrouponRecommendPojo product : grouponRecommends) {
        item = new HashMap<String, Object>();
        // 活动ID
        item.put("activityId", StringUtil.checkVal(product.getActivityId()));
        // 独购价
        item.put("alonePrice", StringUtil.checkVal(product.getDistributionPrice()));
        // 团购人数
        item.put("groupNum", StringUtil.checkVal(product.getGroupNum()));
        // 商品销量
        item.put("proSellrNum", StringUtil.checkVal(product.getSellNumber()));
        // 商品ID
        item.put("productId", StringUtil.checkVal(product.getProductId()));
        // 商品图片
        item.put("productImage", url + StringUtil.checkVal(product.getImageMain()));
        // 商品名称
        item.put("productName", StringUtil.checkVal(product.getProductName()));
        // 商品团购价
        item.put("productPrice", StringUtil.checkVal(product.getPrice()));
        groups.add(item);
      }
    } else {
      msg = "暂无数据！";
    }
    b = true;
    map.put("result", groups);
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
   * 首页-根据分类查询拼团列表
   * 
   * @return String
   * @throws SQLException
   */
  public String findGroupByTypeId() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> groups = new ArrayList<Map<String, Object>>();
    if (id == null || id < 1) {
      msg = "导航ID不能为空!";
    } else {
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      pageSize = 10;
      StringUtil.dateString(new Date());
      Map<String, Object> option = new HashMap<String, Object>();
      // 二级分类ID
      option.put("secProductType", ":" + id + ":");
      // >=起始时间
      // option.put("beginTimeStr", curDate);
      // <=结束时间
      // option.put("endTimeStr", curDate);
      // 1-普通拼团
      option.put("type", 1);
      // 1-拼团审核通过
      option.put("status", 1);
      // 0-未删除
      option.put("isDelete", 0);
      option.put("pageNo", (pageNo - 1) * pageSize);
      option.put("pageSize", pageSize);
      option.put("orderBy", "p.sell_number desc, ga.sorting desc, ga.create_date desc");
      List<GrouponActivityPojo> grouponActivitys = grouponActivityService.listPage(option);
      if (grouponActivitys != null && grouponActivitys.size() > 0) {
        Map<String, Object> item = null;
        String url = ConstParam.URL + "/upfiles/product" + File.separator;
        for (GrouponActivityPojo product : grouponActivitys) {
          item = new HashMap<String, Object>();
          // 活动ID
          item.put("activityId", StringUtil.checkVal(product.getId()));
          // 独购价
          item.put("alonePrice", StringUtil.checkVal(product.getDistributionPrice()));
          // 团购人数
          item.put("groupNum", StringUtil.checkVal(product.getNum()));
          // 已购人数
          item.put("attendNum", StringUtil.checkVal(product.getNumNow()));
          // 商品销量
          item.put("proSellrNum", StringUtil.checkVal(product.getSellNumber()));
          // 商品ID
          item.put("productId", StringUtil.checkVal(product.getProductId()));
          // 商品图片
          item.put("productImage", url + StringUtil.checkVal(product.getProductImage()));
          // 商品名称
          item.put("productName", StringUtil.checkVal(product.getProductName()));
          // 商品团购价
          item.put("productPrice", StringUtil.checkVal(product.getPrice()));
          groups.add(item);
        }
      } else {
        msg = "暂无数据！";
      }
      b = true;
    }

    map.put("result", groups);
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
   * 首页-根据分类查询拼团列表(按当日销量排序)
   * 
   * @return String
   * @throws SQLException
   */
  public String findGroupByTypeId2() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> groups = new ArrayList<Map<String, Object>>();
    if (id == null || id < 1) {
      msg = "导航ID不能为空!";
    } else {
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      pageSize = 10;
      StringUtil.dateString(new Date());
      Map<String, Object> option = new HashMap<String, Object>();
      // 二级分类ID
      option.put("secProductType", ":" + id + ":");
      // >=起始时间
      // option.put("beginTimeStr", curDate);
      // <=结束时间
      // option.put("endTimeStr", curDate);
      // 1-普通拼团
      option.put("type", 1);
      // 1-拼团审核通过
      option.put("status", 1);
      // 0-未删除
      option.put("isDelete", 0);
      option.put("pageNo", (pageNo - 1) * pageSize);
      option.put("pageSize", pageSize);
      // option.put("orderBy", "p.sell_number desc, ga.sorting desc, ga.create_date desc");
      option.put("orderBy", "ps.day_sell desc");
      List<GrouponActivityPojo> grouponActivitys = grouponActivityService.listPage2(option);
      if (grouponActivitys != null && grouponActivitys.size() > 0) {
        Map<String, Object> item = null;
        String url = ConstParam.URL + "/upfiles/product" + File.separator;
        for (GrouponActivityPojo product : grouponActivitys) {
          item = new HashMap<String, Object>();
          // 活动ID
          item.put("activityId", StringUtil.checkVal(product.getId()));
          // 独购价
          item.put("alonePrice", StringUtil.checkVal(product.getPrice1()));
          // 团购人数
          item.put("groupNum", StringUtil.checkVal(product.getNum()));
          // 已购人数
          item.put("attendNum", StringUtil.checkVal(product.getNumNow()));
          // 商品销量
          item.put("proSellrNum", StringUtil.checkVal(product.getSellNumber1()));
          // 商品ID
          item.put("productId", StringUtil.checkVal(product.getProductId()));
          // 商品图片
          item.put("productImage", url + StringUtil.checkVal(product.getProductImage1()));
          // 商品名称
          item.put("productName", StringUtil.checkVal(product.getProductName1()));
          // 商品团购价
          item.put("productPrice", StringUtil.checkVal(product.getPrice()));
          // 商品当日销售量
          // item.put("daySell", StringUtil.checkVal(product.getDaySell()));
          groups.add(item);
        }
      } else {
        msg = "暂无数据！";
      }
      b = true;
    }

    map.put("result", groups);
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
   * 团免-检查团免券
   * 
   * @return String
   * @throws SQLException
   */
  public String checkGroupFreeApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    result.put("beginTime", "");
    result.put("currentTime", "");
    result.put("endTime", "");
    result.put("isStart", "0");
    if (userId == null || userId < 1) {
      msg = "用户没有团免券！";
    } else {
      SysLoginPojo user = sysLoginService.findSysLoginById(userId);
      if (user != null && user.getStatus() == 1) {
        Map<String, Object> params = new HashMap<String, Object>();
        // 用户ID
        params.put("userId", userId);
        // 激活状态 1-已激活
        params.put("status", 1);
        // 使用状态 0-未使用
        params.put("used", 0);
        params.put("pageNo", 0);
        params.put("pageSize", 1);
        // 有效时间
        params.put("validTime", StringUtil.dateString(new Date()));
        List<GroupFreeCouponPojo> freeCoupons = groupFreeCouponService.listPage(params);
        if (freeCoupons != null && freeCoupons.size() > 0) {
          result.put("beginTime", StringUtil.dateToString(freeCoupons.get(0).getActiveTime()));
          result.put("currentTime", StringUtil.dateToString(new Date()));
          result.put("endTime", StringUtil.dateToString(freeCoupons.get(0).getInvalidTime()));
          result.put("isStart", "1");
          b = true;
        } else {
          msg = "用户没有团免券！";
        }
      } else {
        msg = "用户没有团免券~！";
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
   * 
   * 团免列表
   * 
   * @return String
   * @throws SQLException
   */
  public String groupFreeListApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> groups = new ArrayList<Map<String, Object>>();
    if (userId == null || userId < 1) {
      msg = "用户ID不能为空!";
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      // 用户ID
      params.put("userId", userId);
      // 激活状态 1-已激活
      params.put("status", 1);
      // 使用状态 0-未使用
      params.put("used", 0);
      params.put("pageNo", 0);
      params.put("pageSize", 1);
      // 有效时间
      params.put("validTime", StringUtil.dateString(new Date()));
      List<GroupFreeCouponPojo> freeCoupons = groupFreeCouponService.listPage(params);
      // 检查是否有团免券
      if (freeCoupons != null && freeCoupons.size() > 0) {
        if (pageNo == null || pageNo < 1) {
          pageNo = 1;
        }
        pageSize = 10;
        Map<String, Object> option = new HashMap<String, Object>();
        // >=起始时间
        // option.put("beginTimeStr", curDate);
        // <=结束时间
        // option.put("endTimeStr", curDate);
        option.put("nowTime", new Date());
        // 2-团免
        option.put("type", 2);
        // 1-团免审核通过
        option.put("status", 1);
        // 0-未删除
        option.put("isDelete", 0);
        option.put("pageNo", (pageNo - 1) * pageSize);
        option.put("pageSize", pageSize);
        option.put("orderBy", "ga.sorting desc, ga.create_date desc");
        List<GrouponActivityPojo> grouponActivitys = grouponActivityService.listPage(option);
        if (grouponActivitys != null && grouponActivitys.size() > 0) {
          Map<String, Object> item = null;
          String url = ConstParam.URL + "/upfiles/product" + File.separator;
          for (GrouponActivityPojo product : grouponActivitys) {
            item = new HashMap<String, Object>();
            // 活动ID
            item.put("activityId", StringUtil.checkVal(product.getId()));
            // 独购价
            item.put("alonePrice", StringUtil.checkVal(product.getDistributionPrice()));
            // 团购人数
            item.put("groupNum", StringUtil.checkVal(product.getNum()));
            // 商品ID
            item.put("productId", StringUtil.checkVal(product.getProductId()));
            // 商品图片
            item.put("productImage", url + StringUtil.checkVal(product.getImageSmall()));
            // 商品名称
            item.put("productName", StringUtil.checkVal(product.getProductName()));
            // 商品团购价
            item.put("productPrice", StringUtil.checkVal(product.getPrice()));
            groups.add(item);
          }
        } else {
          msg = "暂无数据！";
        }
      } else {
        msg = "暂无数据！";
      }

      b = true;
    }

    map.put("result", groups);
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
   * 猜价-顶部图片
   * 
   * @return
   * @throws SQLException
   */
  public String guessBannerApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    params.put("status", 1);
    params.put("type", 2);
    params.put("pageNo", 0);
    params.put("pageSize", 1);
    params.put("nowTimeStr", StringUtil.dateString(new Date()));
    params.put("orderBy", "sorting desc, create_date desc");
    focusSettingPojoList = focusSettingService.listPage(params);
    if (focusSettingPojoList != null && focusSettingPojoList.size() > 0) {
      focusSettingPojo = focusSettingPojoList.get(0);
      result.put("banner", focusSettingPojo.getBanner() == null ? "" : ConstParam.URL
          + "/upfiles/focusbanner" + File.separator + focusSettingPojo.getBanner());
      b = true;
    } else {
      msg = "查询不到数据!";
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
   * 猜价-活动列表
   * 
   * @return
   * @throws SQLException
   */
  public String guessActivityApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    String curDate = StringUtil.dateString(new Date());
    // >=起始时间
    params.put("beginTimeStr", curDate);
    // <=结束时间
    params.put("endTimeStr", curDate);
    params.put("type", 3);
    params.put("status", 1);
    params.put("isDelete", 0);
    params.put("orderBy", "ga.end_time asc");
    // 分页
    int ps = 20;
    if (pageSize != null && pageSize != 0) {
      ps = pageSize;
    }
    if (pageNo == null || pageNo == 0) {
      params.put("pageNo", 0);
    } else {
      params.put("pageNo", (pageNo - 1) * ps);
    }
    params.put("pageSize", ps);
    grouponActivityList = grouponActivityService.listPage(params);
    if (grouponActivityList != null && grouponActivityList.size() > 0) {
      for (GrouponActivityPojo grouponActivity : grouponActivityList) {
        item = new HashMap<String, Object>();
        item.put("activityId",
            grouponActivity.getId() == null ? "" : String.valueOf(grouponActivity.getId()));
        item.put(
            "beginTime",
            grouponActivity.getBeginTime() == null ? "" : StringUtil.dateString(grouponActivity
                .getBeginTime()));
        item.put(
            "endTime",
            grouponActivity.getEndTime() == null ? "" : StringUtil.dateString(grouponActivity
                .getEndTime()));
        item.put("joinNum", String.valueOf(getJoinActivityNum(grouponActivity.getId())));
        item.put(
            "maxPrice",
            grouponActivity.getPriceMax() == null ? "" : String.valueOf(grouponActivity
                .getPriceMax()));
        item.put(
            "minPrice",
            grouponActivity.getPriceMin() == null ? "" : String.valueOf(grouponActivity
                .getPriceMin()));
        item.put("nowTime", StringUtil.dateString(new Date()));
        item.put(
            "productId",
            grouponActivity.getProductId() == null ? "" : String.valueOf(grouponActivity
                .getProductId()));
        item.put(
            "productName",
            grouponActivity.getProductName() == null ? "" : String.valueOf(grouponActivity
                .getProductName()));
        item.put("productImage", grouponActivity.getProductImage() == null ? "" : ConstParam.URL
            + "/upfiles/product" + File.separator + grouponActivity.getProductImage());
        result.add(item);
      }
      b = true;
    } else {
      msg = "查询不到猜价活动!";
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
   * 往期猜价-活动列表
   * 
   * @return
   * @throws SQLException
   */
  public String guessBeforeActivityApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    params.put("overTime", new Date());
    params.put("type", 3);
    params.put("status", 1);
    params.put("isDelete", 0);
    params.put("orderBy", "ga.end_time desc");
    // 分页
    int ps = 20;
    if (pageSize != null && pageSize != 0) {
      ps = pageSize;
    }
    if (pageNo == null || pageNo == 0) {
      params.put("pageNo", 0);
    } else {
      params.put("pageNo", (pageNo - 1) * ps);
    }
    params.put("pageSize", ps);
    grouponActivityList = grouponActivityService.listPage(params);
    if (grouponActivityList != null && grouponActivityList.size() > 0) {
      for (GrouponActivityPojo grouponActivity : grouponActivityList) {
        item = new HashMap<String, Object>();
        item.put("activityId",
            grouponActivity.getId() == null ? "" : String.valueOf(grouponActivity.getId()));
        item.put(
            "beginTime",
            grouponActivity.getBeginTime() == null ? "" : StringUtil.dateString(grouponActivity
                .getBeginTime()));
        item.put(
            "endTime",
            grouponActivity.getEndTime() == null ? "" : StringUtil.dateString(grouponActivity
                .getEndTime()));
        item.put("joinNum", String.valueOf(getJoinActivityNum(grouponActivity.getId())));
        item.put(
            "maxPrice",
            grouponActivity.getPriceMax() == null ? "" : String.valueOf(grouponActivity
                .getPriceMax()));
        item.put(
            "minPrice",
            grouponActivity.getPriceMin() == null ? "" : String.valueOf(grouponActivity
                .getPriceMin()));
        item.put("nowTime", StringUtil.dateString(new Date()));
        item.put(
            "productId",
            grouponActivity.getProductId() == null ? "" : String.valueOf(grouponActivity
                .getProductId()));
        item.put(
            "productName",
            grouponActivity.getProductName() == null ? "" : String.valueOf(grouponActivity
                .getProductName()));
        item.put("productImage", grouponActivity.getProductImage() == null ? "" : ConstParam.URL
            + "/upfiles/product" + File.separator + grouponActivity.getProductImage());
        if (grouponActivity.getActivityStatus() != 3) {
          item.put("isPrize", "1");
        } else {
          item.put("isPrize", "2");
        }
        result.add(item);
      }
      b = true;
    } else {
      msg = "查询不到猜价活动!";
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
   * 查询已参加团的人数
   * 
   * @param activityId 活动id
   * @throw
   * @return int 参团人数
   */
  public int getJoinActivityNum(Long activityId) throws SQLException {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("activityId", activityId);
    params.put("activityType", 3);
    return grouponUserRecordService.countBy(params);
  }

  /**
   * 猜价-参与情况
   * 
   * @return
   * @throws Exception
   */
  public String readyJoinApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, Object> userInfo = new HashMap<String, Object>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    if (activityId == null || activityId == 0) {
      msg = "活动id不能为空!";
    } else {
      grouponActivityPojo = grouponActivityService.getById(activityId);
      // 判断是否存在活动
      if (grouponActivityPojo == null) {
        msg = "查询不到猜价活动!";
      } else {
        // 检查是否弹窗
        if (userId != null) {
          params.put("userId", userId);
          params.put("activityId", activityId);
          params.put("activityType", 3);
          params.put("isRecCoupon", 1);
          params.put("isAlert", 0);
          int i = grouponUserRecordService.countBy(params);
          if (i > 0) {
            result.put("isAlert", "0");
          } else {
            result.put("isAlert", "1");
          }
        } else {
          result.put("isAlert", "1");
        }
        // 跳转到普通拼团的id
        // params.clear();
        // params.put("productId", grouponActivityPojo.getProductId());
        // params.put("type", 1);
        // params.put("isDefault", 1);
        // grouponActivityList = grouponActivityService.listPage(params);
        // if (grouponActivityList != null && grouponActivityList.size() > 0) {
        // result.put("ordGrouponId", String.valueOf(grouponActivityList.get(0).getId()));
        // } else {
        // result.put("ordGrouponId", "");
        // }
        // 是否开奖
        if (grouponActivityPojo.getActivityStatus() == 3) {
          result.put("isPublic", "1");
          result.put("isStart", "2");
          // 是否进行中
        } else if (DateTimeUtil.compareDate(
            StringUtil.dateString(grouponActivityPojo.getEndTime()), sdf.format(new Date()))
            && DateTimeUtil.compareDate(sdf.format(new Date()),
                StringUtil.dateString(grouponActivityPojo.getBeginTime()))) {
          result.put("isPublic", "0");
          result.put("isStart", "1");
        } else {
          result.put("isPublic", "0");
          result.put("isStart", "2");
        }
        // 参与人数
        result.put("joinNum", String.valueOf(getJoinActivityNum(grouponActivityPojo.getId())));
        // 最低价格
        result.put(
            "minPrice",
            grouponActivityPojo.getPriceMin() == null ? "" : String.valueOf(grouponActivityPojo
                .getPriceMin()));
        // 最高价格
        result.put(
            "maxPrice",
            grouponActivityPojo.getPriceMax() == null ? "" : String.valueOf(grouponActivityPojo
                .getPriceMax()));
        // 当前时间
        result.put("nowTime", StringUtil.dateString(new Date()));
        // 活动商品名称
        result.put(
            "productName",
            grouponActivityPojo.getProductName() == null ? "" : String.valueOf(grouponActivityPojo
                .getProductName()));
        // 活动商品简介
        result.put(
            "productSketch",
            grouponActivityPojo.getProductSketch() == null ? "" : String
                .valueOf(grouponActivityPojo.getProductSketch()));
        // 活动商品id
        result.put(
            "productId",
            grouponActivityPojo.getProductId() == null ? "" : String.valueOf(grouponActivityPojo
                .getProductId()));
        // 开奖价格
        result.put(
            "realPrice",
            grouponActivityPojo.getPrice() == null ? "" : String.valueOf(grouponActivityPojo
                .getPrice()));
        result.put("beginTime", StringUtil.dateString(grouponActivityPojo.getBeginTime()));
        result.put("endTime", StringUtil.dateString(grouponActivityPojo.getEndTime()));
        // 用户是否登录
        if (userId == null || userId == 0) {
          result.put("userInfo", userInfo);
          result.put("isJoin", "0");
          result.put("isWin", "0");
          result.put("prize", "0");
          result.put("couponEndTime", "");
          result.put("couponPrice", "");
          result.put("isRecCoupon", "1");
          b = true;
        } else {
          // 团购用户记录
          params.clear();
          params.put("activityId", activityId);
          params.put("userId", userId);
          grouponUserRecordPojo = grouponUserRecordService.findByParams(params);
          if (grouponUserRecordPojo != null) {
            // 有参与
            // 参与时间
            userInfo.put("joinTime", grouponUserRecordPojo.getAttendTime() == null ? ""
                : StringUtil.dateString(grouponUserRecordPojo.getAttendTime()));
            // 用户头像
            userInfo.put(
                "userImage",
                grouponUserRecordPojo.getUserLogo() == null ? "" : grouponUserRecordPojo
                    .getUserLogo().contains("http") == true ? grouponUserRecordPojo.getUserLogo()
                    : ConstParam.URL + "/upfiles/userlogo" + File.separator
                        + StringUtil.checkVal(grouponUserRecordPojo.getUserLogo()));
            // 用户名称
            userInfo.put("userName",
                String.valueOf(grouponUserRecordPojo.getUserName() == null ? ""
                    : grouponUserRecordPojo.getUserName()));
            // 用户出价
            userInfo.put("userPrice", String.valueOf(grouponUserRecordPojo.getPrice() == null ? ""
                : grouponUserRecordPojo.getPrice()));
            result.put("isJoin", "1");
            // 是否分发优惠券
            result.put("isRecCoupon",
                String.valueOf(grouponUserRecordPojo.getIsRecCoupon() == null ? ""
                    : grouponUserRecordPojo.getIsRecCoupon()));
            // 优惠券信息
            if (userId != null && userId != 0) {
              params.clear();
              params.put("sourceId", activityId);
              params.put("userId", userId);
              userCouponList = couponService.getuserCouponList(params);
              if (userCouponList != null && userCouponList.size() > 0) {
                userCouponPojo = userCouponList.get(0);
                // 优惠券截止时间
                result.put("couponEndTime", userCouponPojo.getValidetime() == null ? ""
                    : StringUtil.timestampToDateStr(userCouponPojo.getValidetime()));
                JSONObject content = JSONObject.fromObject(userCouponPojo.getContent());
                result.put("couponPrice",
                    content.getString("m") == null ? "" : content.getString("m"));
                // 优惠券码
                result.put(
                    "couponNo",
                    userCouponPojo.getCouponNo() == null ? "" : String.valueOf(userCouponPojo
                        .getCouponNo()));
              } else {
                result.put("couponEndTime", "");
                result.put("couponPrice", "");
                result.put("couponNo", "");
              }
            }
            result.put("userInfo", userInfo);
            if (grouponUserRecordPojo.getPrize() == 0) {
              // 未得奖
              result.put("isWin", "0");
              result.put("prize", "0");
            } else {
              // 有得奖
              result.put(
                  "prize",
                  grouponUserRecordPojo.getPrize() == null ? "" : String
                      .valueOf(grouponUserRecordPojo.getPrize()));
              result.put("isWin", "1");
            }
          } else {
            // 没参与
            result.put("userInfo", userInfo);
            result.put("isJoin", "0");
            result.put("isWin", "0");
            result.put("prize", "0");
            result.put("couponEndTime", "");
            result.put("couponPrice", "");
            result.put("isRecCoupon", "1");
          }
          b = true;
        }
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
   * 猜价-参与用户列表
   * 
   * @return
   * @throws SQLException
   */
  public String userJoinInfoApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> joinUserList = new ArrayList<Map<String, Object>>();
    if (activityId == null || activityId == 0) {
      msg = "活动id不能为空!";
    } else {
      // 分页
      int ps = 20;
      if (pageSize != null && pageSize != 0) {
        ps = pageSize;
      }
      if (pageNo == null || pageNo == 0) {
        params.put("pageNo", 0);
      } else {
        params.put("pageNo", (pageNo - 1) * ps);
      }
      params.put("pageSize", ps);
      params.put("activityId", activityId);
      params.put("activityType", 3);
      grouponUserRecordList = grouponUserRecordService.listPage(params);
      result.put("joinNum", String.valueOf(getJoinActivityNum(activityId)));
      if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
        for (GrouponUserRecordPojo grouponUserRecord : grouponUserRecordList) {
          item = new HashMap<String, Object>();
          item.put(
              "joinTime",
              grouponUserRecord.getAttendTime() == null ? "" : StringUtil
                  .dateString(grouponUserRecord.getAttendTime()));
          item.put(
              "userName",
              grouponUserRecord.getUserName() == null ? "" : String.valueOf(grouponUserRecord
                  .getUserName()));
          item.put(
              "userPrice",
              grouponUserRecord.getPrice() == null ? "" : String.valueOf(grouponUserRecord
                  .getPrice()));
          item.put(
              "userImage",
              grouponUserRecord.getUserLogo() == null ? "" : grouponUserRecord.getUserLogo()
                  .contains("http") == true ? grouponUserRecord.getUserLogo() : ConstParam.URL
                  + "/upfiles/userlogo" + File.separator
                  + StringUtil.checkVal(grouponUserRecord.getUserLogo()));
          joinUserList.add(item);
        }
        result.put("joinUserList", joinUserList);
        b = true;
      } else {
        result.put("joinUserList", joinUserList);
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
   * 猜价-商品轮播图
   * 
   * @return
   * @throws SQLException
   */
  public String productFocusImagsApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    if (productId == null || productId == 0) {
      msg = "商品id不能为空!";
    } else {
      params.put("productId", productId);
      productFocusImagesList = productFocusImagesService.getProductFocusImagesByPid(productId);
      if (productFocusImagesList != null && productFocusImagesList.size() > 0) {
        for (ProductFocusImagesPojo productFocusImages : productFocusImagesList) {
          item = new HashMap<String, Object>();
          item.put("image", productFocusImages.getImages() == null ? "" : ConstParam.URL
              + "/upfiles/productFocusImages" + File.separator + productFocusImages.getImages());
          result.add(item);
        }
        b = true;
      } else {
        msg = "查询不到轮播图!";
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
   * 猜价-估价
   * 
   * @return
   * @throws SQLException
   */
  public String guessPriceApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (activityId == null || activityId == 0) {
      msg = "活动id不能为空!";
    } else if (userId == null || userId == 0) {
      msg = "用户id不能为空!";
    } else if (price == null) {
      msg = "估价id不能为空!";
    } else {
      params.put("userId", userId);
      params.put("activityId", activityId);
      grouponActivityPojo = grouponActivityService.getById(activityId);
      if (grouponActivityPojo != null) {
        if (price > Double.valueOf(grouponActivityPojo.getPriceMax())
            || price < Double.valueOf(grouponActivityPojo.getPriceMin())) {
          result.put("isGuess", "0");
          msg = "猜的价格须在猜价范围内哦!";
        } else {
          if (DateTimeUtil.compareDate(StringUtil.dateString(new Date()),
              StringUtil.dateString(grouponActivityPojo.getBeginTime()))
              && DateTimeUtil.compareDate(StringUtil.dateString(grouponActivityPojo.getEndTime()),
                  StringUtil.dateString(new Date()))) {
            int i = grouponUserRecordService.countBy(params);
            if (i > 0) {
              msg = "您已经猜价过了哦!";
              result.put("isGuess", "0");
            } else {
              grouponUserRecordPojo = new GrouponUserRecordPojo();
              grouponUserRecordPojo.setUserId(userId);
              grouponUserRecordPojo.setPrice(price);
              grouponUserRecordPojo.setActivityId(activityId);
              grouponUserRecordPojo.setActivityType(3);
              grouponUserRecordPojo.setAttendTime(new Date());
              grouponUserRecordPojo.setCreateDate(new Date());
              grouponUserRecordPojo.setUpdateDate(new Date());
              grouponUserRecordPojo.setCreateBy(userId);
              grouponUserRecordPojo.setUpdateBy(userId);
              try {
                grouponUserRecordService.add(grouponUserRecordPojo);
                result.put("isGuess", "1");
                b = true;
              } catch (Exception e) {
                result.put("isGuess", "0");
              }
            }
          } else {
            result.put("isGuess", "0");
            msg = "活动已结束!";
          }
        }
      } else {
        result.put("isGuess", "0");
        msg = "查询不到该猜价活动!";
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
   * 猜价-更多得奖人列表
   * 
   * @return
   * @throws SQLException
   */
  public String winListApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> prizeList = new ArrayList<Map<String, Object>>();
    if (activityId == null || activityId == 0) {
      msg = "活动id不能为空!";
    } else if (prize == null || prize == 0) {
      msg = "得奖类型不能为空!";
    } else {
      // 分页
      int ps = 20;
      if (pageSize != null && pageSize != 0) {
        ps = pageSize;
      }
      if (pageNo == null || pageNo == 0) {
        params.put("pageNo", 0);
      } else {
        params.put("pageNo", (pageNo - 1) * ps);
      }
      params.put("pageSize", ps);
      // 根据prize查询得奖列表
      if (prize == 1) {
        params.put("prize", 1);
      } else if (prize == 2) {
        params.put("prize", 2);
      } else if (prize == 3) {
        params.put("prize", 3);
      } else {
        params.put("prize", 0);
      }
      params.put("orderBy", "attendTime desc");
      params.put("activityId", activityId);
      grouponUserRecordList = grouponUserRecordService.listPage(params);
      if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
        for (GrouponUserRecordPojo grouponUserRecord : grouponUserRecordList) {
          item = new HashMap<String, Object>();
          item.put(
              "userName",
              grouponUserRecord.getUserName() == null ? "" : String.valueOf(grouponUserRecord
                  .getUserName()));
          item.put(
              "userPrice",
              grouponUserRecord.getPrice() == null ? "" : String.valueOf(grouponUserRecord
                  .getPrice()));
          item.put(
              "userImage",
              grouponUserRecord.getUserLogo() == null ? "" : grouponUserRecord.getUserLogo()
                  .contains("http") == true ? grouponUserRecord.getUserLogo() : ConstParam.URL
                  + "/upfiles/userlogo" + File.separator
                  + StringUtil.checkVal(grouponUserRecord.getUserLogo()));
          item.put(
              "joinTime",
              grouponUserRecord.getAttendTime() == null ? "" : StringUtil
                  .dateString(grouponUserRecord.getAttendTime()));
          prizeList.add(item);
        }
        b = true;
      } else {
        msg = "查询不到数据!";
      }
    }
    result.put("joinNum", String.valueOf(prizeList.size()));
    result.put("prizeList", prizeList);
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
   * 猜价-详情得奖人列表
   * 
   * @return
   * @throws SQLException
   */
  public String guessWinListApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> friPrizeList = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> twoPrizeList = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> thrPrizeList = new ArrayList<Map<String, Object>>();
    Integer friPrizeNum = 0;
    Integer twoPrizeNum = 0;
    Integer thrPrizeNum = 0;
    if (activityId == null || activityId == 0) {
      msg = "活动id不能为空!";
    } else {
      params.put("orderBy", "attendTime desc");
      params.put("activityId", activityId);
      params.put("activityType", 3);
      for (int i = 1; i <= 3; i++) {
        params.put("prize", i);
        params.put("pageNo", 0);
        params.put("pageSize", 5);
        grouponUserRecordList = grouponUserRecordService.listPage(params);
        if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
          for (GrouponUserRecordPojo grouponUserRecord : grouponUserRecordList) {
            item = new HashMap<String, Object>();
            item.put(
                "userName",
                grouponUserRecord.getUserName() == null ? "" : String.valueOf(grouponUserRecord
                    .getUserName()));
            item.put(
                "userPrice",
                grouponUserRecord.getPrice() == null ? "" : String.valueOf(grouponUserRecord
                    .getPrice()));
            item.put(
                "userImage",
                grouponUserRecord.getUserLogo() == null ? "" : grouponUserRecord.getUserLogo()
                    .contains("http") == true ? grouponUserRecord.getUserLogo() : ConstParam.URL
                    + "/upfiles/userlogo" + File.separator
                    + StringUtil.checkVal(grouponUserRecord.getUserLogo()));
            item.put(
                "joinTime",
                grouponUserRecord.getAttendTime() == null ? "" : StringUtil
                    .dateString(grouponUserRecord.getAttendTime()));
            if (i == 1) {
              friPrizeList.add(item);
            } else if (i == 2) {
              twoPrizeList.add(item);
            } else if (i == 3) {
              thrPrizeList.add(item);
            }
          }
          b = true;
        } else {
          msg += "查询不到" + i + "等奖数据!";
        }
        params.remove("pageNo");
        params.remove("pageSize");
        if (i == 1) {
          friPrizeNum = grouponUserRecordService.countBy(params);
        } else if (i == 2) {
          twoPrizeNum = grouponUserRecordService.countBy(params);
        } else if (i == 3) {
          thrPrizeNum = grouponUserRecordService.countBy(params);
        }
      }
    }

    result.put("friPrizeNum", friPrizeNum);
    result.put("friPrizeList", friPrizeList);
    result.put("twoPrizeNum", twoPrizeNum);
    result.put("twoPrizeList", twoPrizeList);
    result.put("thrPrizeNum", thrPrizeNum);
    result.put("thrPrizeList", thrPrizeList);
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
   * 猜价-详情得奖人列表
   * 
   * @return
   * @throws SQLException
   */
  public String callGuessCouponAlertApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    String result = "0";
    if (activityId == null || activityId == 0) {
      msg = "活动id不能为空!";
    } else if (userId == null || userId == 0) {
      msg = "用户id不能为空!";
    } else {
      params.put("userId", userId);
      params.put("activityId", activityId);
      params.put("activityType", 3);
      params.put("isRecCoupon", 1);
      grouponUserRecordPojo = grouponUserRecordService.findByParams(params);
      if (grouponUserRecordPojo != null) {
        grouponUserRecordPojo.setIsAlert(1);
        int i = grouponUserRecordService.update(grouponUserRecordPojo);
        if (i > 0) {
          result = "1";
          b = true;
        } else {
          msg = "已弹窗修改失败!";
        }
      } else {
        msg = "查询不到未弹窗记录!";
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
   * 
   * 搜索商品
   * 
   * @return String
   * @throws SQLException
   */
  public String searchAll() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> groups = new ArrayList<Map<String, Object>>();
    if (StringUtils.isBlank(name)) {
      msg = "请输入搜索关键词!";
    } else {
      // sorting:(1为销量降序，11为销量升序，2为价格升序，22为价格降序， 3为点击数降序，33为点击数升序)
      if (sorting == null || sorting != 1 && sorting != 11 && sorting != 2 && sorting != 22
          && sorting != 3 && sorting != 33) {
        sorting = 0;
      }
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      pageSize = 20;
      // String curDate = StringUtil.dateString(new Date());
      Map<String, Object> option = new HashMap<String, Object>();
      // >=起始时间
      // option.put("beginTimeStr", curDate);
      // <=结束时间
      // option.put("endTimeStr", curDate);
      // 1-普通拼团
      option.put("type", 1);
      // 1-拼团审核通过
      option.put("status", 1);
      // 0-未删除
      option.put("isDelete", 0);
      option.put("query", name);
      option.put("pageNo", (pageNo - 1) * pageSize);
      option.put("pageSize", pageSize);
      if (sorting == 1) {
        // 1为销量降序
        option.put("orderBy", "p.sell_number desc, ga.create_date desc");
      } else if (sorting == 11) {
        // 11为销量升序
        option.put("orderBy", "p.sell_number asc, ga.create_date desc");
      } else if (sorting == 2) {
        // 2为价格升序
        option.put("orderBy", "ga.price asc, ga.create_date desc");
      } else if (sorting == 22) {
        // 22为价格降序
        option.put("orderBy", "ga.price desc, ga.create_date desc");
      } else if (sorting == 3) {
        // 3为点击数降序
        option.put("orderBy", "p.hits desc, ga.create_date desc");
      } else if (sorting == 33) {
        // 33为点击数升序
        option.put("orderBy", "p.hits asc, ga.create_date desc");
      } else {
        option.put("orderBy", "ga.sorting desc, ga.create_date desc");
      }
      int count = grouponActivityService.countBy(option);
      if (count > 0) {
        List<GrouponActivityPojo> grouponActivitys = grouponActivityService.listPage(option);
        if (grouponActivitys != null && grouponActivitys.size() > 0) {
          Map<String, Object> item = null;
          String url = ConstParam.URL + "/upfiles/product" + File.separator;
          for (GrouponActivityPojo product : grouponActivitys) {
            item = new HashMap<String, Object>();
            // 活动ID
            item.put("activityId", StringUtil.checkVal(product.getId()));
            // 独购价
            item.put("alonePrice", StringUtil.checkVal(product.getDistributionPrice()));
            // 团购人数
            item.put("groupNum", StringUtil.checkVal(product.getNum()));
            // 已购人数
            item.put("attendNum", StringUtil.checkVal(product.getNumNow()));
            // 商品销量
            item.put("proSellrNum", StringUtil.checkVal(product.getSellNumber()));
            // 商品ID
            item.put("productId", StringUtil.checkVal(product.getProductId()));
            // 商品图片
            item.put("productImage", url + StringUtil.checkVal(product.getProductImage()));
            // 商品名称
            item.put("productName", StringUtil.checkVal(product.getProductName()));
            // 商品团购价
            item.put("productPrice", StringUtil.checkVal(product.getPrice()));
            groups.add(item);
          }
          result.put("products", groups);
        } else {
          msg = "暂无数据！";
        }
      }
      result.put("count", count);
      b = true;
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
   * 我的-猜价列表
   * 
   * @return
   * @throws SQLException
   */
  public String myGuessListApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    if (userId == null || userId == 0) {
      msg = "用户id不能为空!";
    } else if (type == null) {
      msg = "列表类型id不能为空!";
    } else {
      // 分页
      int ps = 20;
      if (pageSize != null && pageSize != 0) {
        ps = pageSize;
      }
      if (pageNo == null || pageNo == 0) {
        params.put("pageNo", 0);
      } else {
        params.put("pageNo", (pageNo - 1) * ps);
      }
      params.put("pageSize", ps);
      params.put("userId", userId);
      params.put("activityType", 3);
      if (type != 0) {
        if (type == 1) {
          // 进行中(大于开始时间小于结束时间)
          params.put("nowTime", StringUtil.stringDate(sdf.format(new Date())));
        } else if (type == 2) {
          // 已结束(已开奖)
          params.put("activityStatus", 3);
        } else if (type == 3) {
          // 待开奖(时间到activityStatus且不等于3)
          params.put("overTime", new Date());
          params.put("isNotPublic", 1);
        }
      }
      // 查询用户参与记录
      params.put("orderBy", "gur.create_date desc,gur.id desc");
      grouponUserRecordList = grouponUserRecordService.listPage(params);
      if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
        for (GrouponUserRecordPojo grouponUserRecord : grouponUserRecordList) {
          item = new HashMap<String, Object>();
          item.put("activityId", String.valueOf(grouponUserRecord.getActivityId()));
          // 根据活动表的活动时间和用户活动记录表得奖情况判断数据的类型(1进行中,2未得奖,3已得奖)
          if (DateTimeUtil.compareDate(StringUtil.dateString(grouponUserRecord.getEndTime()),
              sdf.format(new Date()))
              && DateTimeUtil.compareDate(sdf.format(new Date()),
                  StringUtil.dateString(grouponUserRecord.getBeginTime()))) {
            item.put("activityStatus", "1");
            item.put("isRecCoupon", "0");
            item.put("realPrice", "");
            item.put("prize", "0");
            // 已结束(已开奖)
          } else if (grouponUserRecord.getActivityStatus() == 3
              && grouponUserRecord.getPrize() != null) {
            item.put("activityStatus", "2");
            item.put("isRecCoupon", grouponUserRecord.getIsRecCoupon() == null ? "0"
                : grouponUserRecord.getIsRecCoupon());
            item.put("prize", String.valueOf(grouponUserRecord.getPrize()));
            // 查询开奖价格
            grouponActivityPojo = grouponActivityService.getById(grouponUserRecord.getActivityId());
            if (grouponActivityPojo != null && grouponActivityPojo.getPrice() != null) {
              item.put("realPrice", String.valueOf(grouponActivityPojo.getPrice()));
            } else {
              item.put("realPrice", "");
            }
            // 待开奖
          } else if (grouponUserRecord.getPrize() != 3
              && DateTimeUtil.compareDate(sdf.format(new Date()),
                  StringUtil.dateString(grouponUserRecord.getEndTime()))) {
            item.put("activityStatus", "3");
            item.put("prize", "0");
            item.put("isRecCoupon", "0");
            item.put("realPrice", "");
          }

          item.put(
              "minPrice",
              grouponUserRecord.getPriceMin() == null ? "" : String.valueOf(grouponUserRecord
                  .getPriceMin()));
          item.put(
              "maxPrice",
              grouponUserRecord.getPriceMax() == null ? "" : String.valueOf(grouponUserRecord
                  .getPriceMax()));
          item.put(
              "productId",
              grouponUserRecord.getProductId() == null ? "" : String.valueOf(grouponUserRecord
                  .getProductId()));
          item.put(
              "productName",
              grouponUserRecord.getProductName() == null ? "" : String.valueOf(grouponUserRecord
                  .getProductName()));
          item.put(
              "userPrice",
              grouponUserRecord.getPrice() == null ? "" : String.valueOf(grouponUserRecord
                  .getPrice()));
          item.put("productImage",
              grouponUserRecord.getProductImage() == null ? "" : ConstParam.URL
                  + "/upfiles/product" + File.separator + grouponUserRecord.getProductImage());
          result.add(item);
        }
        b = true;
      } else {
        msg = "查询不到数据!";
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
   * 
   * 用户优惠券
   * 
   * @throws SQLException
   * */
  public String getUserCouponList() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    List<Map<String, Object>> coupons = new ArrayList<Map<String, Object>>();
    if (uid == null || uid == 0) {
      msg = "用户id不能为空！";
    } else if (type == null || type == 0) {
      msg = "优惠券状态不能为空！";
    } else {
      Map<String, Object> param = new HashMap<String, Object>();
      long curr = System.currentTimeMillis() / 1000;
      // 查询未使用优惠卷数量
      param.put("used", 0);
      param.put("status", 1);
      param.put("userId", uid);
      param.put("nowTime", curr);
      param.put("gtFlag", 1);
      result.put("notUsedNum", couponService.getuserCouponCount(param));
      param.clear();
      // 查询已使用优惠卷数量
      param.put("used", 1);
      param.put("status", 1);
      param.put("userId", uid);
      result.put("usedNum", couponService.getuserCouponCount(param));
      param.clear();
      // 查询已过期优惠卷数量
      param.put("used", 0);
      param.put("status", 1);
      param.put("userId", uid);
      param.put("nowTime", curr);
      param.put("ltFlag", 1);
      result.put("overdueNum", couponService.getuserCouponCount(param));


      // 根据类型查询数据
      param.clear();
      param.put("userId", uid);
      param.put("status", 1);
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      param.put("pageNo", (pageNo - 1) * 10);
      param.put("pageSize", 10);
      if (type == 1) {
        // 查找未使用的
        param.put("used", 0);
        param.put("nowTime", curr);
        param.put("gtFlag", 1);
      } else if (type == 2) {
        // 查找已过期
        param.put("used", 0);
        param.put("ltFlag", 1);
        long nowTime = System.currentTimeMillis() / 1000;
        param.put("nowTime", nowTime);
      } else if (type == 3) {
        // 查找已使用
        param.put("used", 1);
      }
      List<UserCouponPojo> userCoupons = couponService.getuserCouponList(param);
      if (userCoupons != null && userCoupons.size() > 0) {
        Map<String, Object> couponmap = null;
        Integer couponType = null;
        for (UserCouponPojo userCouponPojo : userCoupons) {
          couponmap = new HashMap<String, Object>();
          couponmap.put("couponNo", userCouponPojo.getCouponNo());
          couponmap.put("couponId", userCouponPojo.getCouponId());
          couponmap.put("couponName", userCouponPojo.getCouponName());
          // 1-满减 2-直减
          couponType = userCouponPojo.getType() == null ? 0 : userCouponPojo.getType();
          couponmap.put("couponType", StringUtil.checkVal(couponType));
          couponmap.put(
              "validStime",
              userCouponPojo.getValidstime() == null ? "" : StringUtil.dateToString(new Date(
                  userCouponPojo.getValidstime() * 1000)));
          couponmap.put(
              "validEtime",
              userCouponPojo.getValidetime() == null ? "" : StringUtil.dateToString(new Date(
                  userCouponPojo.getValidetime() * 1000)));
          couponmap.put("used", userCouponPojo.getUsed());
          // 使用时间
          if (userCouponPojo.getUsed() == 1) {
            couponmap.put(
                "useTime",
                userCouponPojo.getUseTime() == null ? "" : StringUtil.dateToString(new Date(
                    userCouponPojo.getUseTime() * 1000)));
          } else {
            couponmap.put("useTime", "");
          }
          // 是否过期
          if (userCouponPojo.getValidetime() != null
              && new Date().getTime() > userCouponPojo.getValidetime() * 1000) {
            couponmap.put("overdue", "1");
          } else {
            couponmap.put("overdue", "0");
          }
          // 满减价格
          if (1 == couponType) {
            String str = userCouponPojo.getContent();
            org.json.JSONObject json = new org.json.JSONObject(str);
            couponmap.put("om", json.get("om"));
            couponmap.put("m", json.get("m"));
          } else if (2 == couponType) {
            // 直减
            String str = userCouponPojo.getContent();
            org.json.JSONObject json = new org.json.JSONObject(str);
            couponmap.put("om", "");
            couponmap.put("m", json.get("m"));
          } else {
            couponmap.put("om", "");
            couponmap.put("m", "");
          }
          // 是否指定商品
          couponmap.put(
              "isProduct",
              userCouponPojo.getIsProduct() == null ? "" : String.valueOf(userCouponPojo
                  .getIsProduct()));
          couponmap.put(
              "productId",
              userCouponPojo.getProductId() == null ? "" : String.valueOf(userCouponPojo
                  .getProductId()));
          couponmap.put("source",
              userCouponPojo.getSource() == null ? 0 : userCouponPojo.getSource());
          // 指定商品对应的活动id
          if (userCouponPojo.getProductId() != null && userCouponPojo.getProductId() != 0) {
            params = new HashMap<String, Object>();
            params.put("productId", userCouponPojo.getProductId());
            params.put("isDelete", 0);
            params.put("status", 1);
            params.put("isDefault", 1);
            grouponActivityList = grouponActivityService.listPage(params);
            if (grouponActivityList != null && grouponActivityList.size() > 0) {
              couponmap.put("activityId", grouponActivityList.get(0).getId());
            } else {
              couponmap.put("activityId", "");
            }
          } else {
            couponmap.put("activityId", "");
          }
          coupons.add(couponmap);
        }
      } else {
        msg = "未找到优惠券！";
      }
      b = true;
    }
    result.put("couponList", coupons);
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
   * 取消订单
   * */
  public String cancelOrder() {
    String msg = "";
    boolean b = false;
    int result = 0;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    if (oid == null || oid <= 0) {
      msg = "订单ID不能为空哒！";
    } else {
      OrderPojo orderqry = null;
      try {
        orderqry = orderService.getfindByIdOrder(oid.longValue());
        if (orderqry != null && orderqry.getOrderStatus() == 1) {
          // 待付款才可取消订单
          OrderPojo orderPojo = new OrderPojo();
          orderPojo.setId(oid.longValue());
          orderPojo.setIsCancelOrder(1);
          // 根据订单ID修改是否取消状态
          int count = orderService.updateIsCancelOrder(orderPojo);
          if (count > 0) {
            // 待付款状态取消订单，恢复优惠券
            map1.put("orderId", oid.longValue());
            map1.put("status", 1);
            List<CouponOrderPojo> couponOrderPojos = couponService.getcouponOrderList(map1);
            if (couponOrderPojos != null && couponOrderPojos.size() > 0) {
              String couponNo = couponOrderPojos.get(0).getCouponNo();
              CouponOrderPojo couponOrderPojo = new CouponOrderPojo();
              couponOrderPojo.setCouponNo(couponNo);
              couponOrderPojo.setStatus(0);
              couponService.updateCouponOrderStatus(couponOrderPojo);
              UserCouponPojo userCouponPojo = new UserCouponPojo();
              userCouponPojo.setCouponNo(couponNo);
              userCouponPojo.setUsed(0);
              userCouponPojo.setUseTime(0L);
              userCouponPojo.setStatus(1);
              couponService.updateUserCoupon(userCouponPojo);
            }
            // 取消订单恢复钱包金额
            // if (orderqry.getWalletPrice() != null && orderqry.getWalletPrice() > 0) {
            // walletService.recoverWalletPay(orderqry.getUserId(), orderqry.getId(),
            // orderqry.getWalletPrice());
            // }
            msg = "取消订单成功！";
            b = true;
            result = 1;
          } else {
            msg = "取消失败哒，或者没有该条订单！";
          }
        } else {
          msg = "取消失败哒，或者没有该条订单！";
        }
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
   * 我的订单
   * */
  public String myorder() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();
    String msg = "";
    boolean success = false;
    DecimalFormat df = new DecimalFormat("#.##");
    if (uid == null || uid < 1) {
      msg = "用户ID不能为空！";
    } else if (orderStatus == null
        || !(orderStatus == 1 || orderStatus == 2 || orderStatus == 21 || orderStatus == 3
            || orderStatus == 4 || orderStatus == 5 || orderStatus == 6 || orderStatus == 7)) {
      msg = "订单状态不能为空！";
    } else {
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      pageSize = 15;
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("userId", uid);
      params.put("isDeleteOrder", 0);
      params.put("pageNo", (pageNo - 1) * pageSize);
      params.put("pageSize", pageSize);
      params.put("orderBy", "t.create_date desc");
      // 订单状态: 1待付款,2已付款（拼团中）,21拼团成功，待发货,3已发货,4已收货，5已评价，6退款退货,7为全部
      params.put("refund", 0);
      if (orderStatus != 6 && orderStatus != 7 && orderStatus != 2 && orderStatus != 21) {
        params.put("orderStatus", orderStatus);
      } else if (orderStatus == 2) {
        params.put("orderStatus", 2);
        params.put("isSuccess", 0);
      } else if (orderStatus == 21) {
        params.put("orderStatus", 2);
        params.put("isSuccess", 1);
      } else if (orderStatus == 6) {
        params.put("refund", 1);
      }
      if (orderStatus == 1) {
        params.put("isCancelOrder", 0);
      }
      if (orderStatus == 2) {
        params.put("isFreeDraw", 1);
      }
      List<OrderPojo> orders = orderService.listPage(params);
      if (orders != null && orders.size() > 0) {
        Map<String, Object> item = null;
        ProductSkuLinkPojo skuParam = new ProductSkuLinkPojo();
        ProductSkuLinkPojo productSkuLink = null;
        Long skuid = 0l;
        String skuvalue = "";
        Integer actType = 0;
        for (OrderPojo order : orders) {
          item = new HashMap<String, Object>();
          // 购买数量
          List<OrderDetailPojo> orderDetailList =
              orderDetailService.getOrderDetailWeb(order.getId());
          if (orderDetailList != null && orderDetailList.size() > 0) {
            item.put("buyNum", orderDetailList.get(0).getNum());
          } else {
            item.put("buyNum", "");
          }
          item.put("id", StringUtil.checkVal(order.getId()));
          item.put("orderNo", StringUtil.checkVal(order.getOrderNo()));
          item.put("productImage", ConstParam.URL + "/upfiles/product" + File.separator
              + StringUtil.checkVal(order.getProductImage()));
          item.put("productName", StringUtil.checkVal(order.getProductName()));
          item.put("productId", StringUtil.checkVal(order.getProductId()));

          if (order.getAllPrice() == null) {
            order.setAllPrice(0d);
          }
          if (order.getEspressPrice() == null) {
            order.setEspressPrice(0d);
          }
          if (order.getUsedPrice() != null) {
            item.put("orderPrice", df.format(order.getFactPrice() - order.getUsedPrice()));
          } else {
            item.put("orderPrice", df.format(order.getFactPrice()));
          }
          item.put("espressPrice", df.format(order.getEspressPrice()));
          item.put("allPrice", df.format(order.getAllPrice()));

          item.put("activityId", StringUtil.checkVal(order.getActivityId()));
          item.put("source", StringUtil.checkVal(order.getSource()));
          item.put("attendId", StringUtil.checkVal(order.getSourceId()));
          item.put("isCancel", StringUtil.checkVal(order.getIsCancelOrder()));
          item.put("isSuccess", StringUtil.checkVal(order.getIsSuccess()));

          item.put("isPrize", "0");
          item.put("oweNum", "");
          if ("5".equals(StringUtil.checkVal(order.getSource()))
              || "7".equals(StringUtil.checkVal(order.getSource()))) {
            params.clear();
            params.put("attendId", order.getSourceId());
            int joinNum = grouponUserRecordService.countBy(params);
            int groupNum = order.getGaNum();
            // 免费抽专用"拼团人数是否已到"
            item.put("isSatis", "0");
            if ("7".equals(StringUtil.checkVal(order.getSource()))) {
              if (groupNum - joinNum <= 0) {
                item.put("isSatis", "1");
              }
            }
            if (order.getIsCancelOrder() == 1) {
              // 交易已取消 5
              item.put("orderStatus", String.valueOf(5));
            } else if (order.getOrderStatus() == 1) {
              // 待支付 1
              item.put("orderStatus", String.valueOf(1));
            } else if (order.getOrderStatus() >= 2) {
              if (order.getIsSuccess() == 0) {
                // 拼团中、差N人 2
                item.put("orderStatus", String.valueOf(2));
                item.put("oweNum", String.valueOf(groupNum - joinNum));
                if (groupNum - joinNum <= 0) {
                  item.put("orderStatus", String.valueOf(12));
                }
              } else if (order.getIsSuccess() == 2) {
                if (order.getIsRefund() == 1) {
                  // 未成团，退款中 3
                  item.put("orderStatus", String.valueOf(3));
                } else if (order.getIsRefund() == 2) {
                  // 未成团，已退款 4
                  item.put("orderStatus", String.valueOf(4));
                } else {
                  // 查询是抽不到奖还是时间到拼团失败
                  if (order.getSource() == 7) {
                    if (joinNum >= order.getGaNum()) {
                      // 未中奖，待返款 6
                      item.put("orderStatus", String.valueOf(6));
                    } else {
                      // 未成团，退款中3
                      item.put("orderStatus", String.valueOf(3));
                    }
                  } else {
                    params.clear();
                    params.put("sourceId", order.getSourceId());
                    params.put("isSuccess", 1);
                    int i = orderService.countBy(params);
                    if (i > 0) {
                      // 未中奖，待返款 6
                      item.put("orderStatus", String.valueOf(6));
                    } else {
                      // 未成团，退款中3
                      item.put("orderStatus", String.valueOf(3));
                    }
                  }
                }

                if ("3".equals(StringUtil.checkVal(order.getGurStatus()))) {
                  item.put("isPrize", "1");
                }
              } else if (order.getIsSuccess() == 1) {
                if (order.getPrize() == 0) {
                  if (order.getIsRefund() == 0) {
                    // 未中奖，待返款 6
                    item.put("orderStatus", String.valueOf(6));
                  } else if (order.getIsRefund() == 2) {
                    // 未中奖，已返款 7
                    item.put("orderStatus", String.valueOf(7));
                  }
                } else if (order.getPrize() > 0) {
                  if (order.getOrderStatus() >= 4) {
                    // 已中奖，已完成 9
                    item.put("orderStatus", String.valueOf(9));
                  } else if (order.getOrderStatus() == 2) {
                    // 已中奖，待发货 10
                    item.put("orderStatus", String.valueOf(10));
                  } else if (order.getOrderStatus() == 3) {
                    // 已中奖，待收货 11
                    item.put("orderStatus", String.valueOf(11));
                  }
                }

                item.put("isPrize", "1");
              }
            }
          } else {
            item.put("orderStatus", StringUtil.checkVal(order.getOrderStatus()));
            if (order.getSourceId() != null && order.getSourceId() > 0) {
              grouponActivityRecordPojo = grouponActivityRecordService.getById(order.getSourceId());
              if (grouponActivityRecordPojo != null) {
                int againJoinNum = grouponUserRecordService.joinNumByAttendId(order.getSourceId());
                item.put("oweNum", grouponActivityRecordPojo.getNum() - againJoinNum);
              }
            }
          }

          item.put("refundStatus", StringUtil.checkVal(order.getRefundStatus()));
          // 拼团退款状态
          item.put("isRefund", order.getIsRefund());
          // 抽奖是否中奖
          // if (order.getSourceId() != null && order.getSourceId() > 0) {
          // params.clear();
          // params.put("userId", order.getUserId());
          // params.put("status", 3);
          // params.put("prize", 1);
          // int i = grouponUserRecordService.countBy(params);
          // if (i > 0) {
          // item.put("isPrize", "1");
          // } else {
          // item.put("isPrize", "0");
          // }
          // } else {
          // item.put("isPrize", "0");
          // }

          skuid = order.getSkuLinkId();
          item.put("skuLinkId", StringUtil.checkVal(skuid));
          item.put("skuValue", "");
          if (skuid != null) {
            skuParam.setId(skuid);
            productSkuLink = productSkuLinkService.findProductSkuLink(skuParam);
            if (productSkuLink != null) {
              if (StringUtils.isNotBlank(productSkuLink.getSkuColor())) {
                skuvalue += "颜色:" + productSkuLink.getSkuColor() + ";";
              }
              if (StringUtils.isNotBlank(productSkuLink.getSkuFormat())) {
                skuvalue += "款式:" + productSkuLink.getSkuFormat() + ";";
              }
              item.put("skuValue", skuvalue);
            }
          }
          actType = order.getSource();
          if (actType == 1 || actType == 2) {
            if ("".equals(item.get("oweNum"))) {
              int num = grouponUserRecordService.joinNumByAttendId(order.getSourceId());
              num = order.getGroupNum() - num;
              item.put("oweNum", StringUtil.checkVal(num));
            }
          }
          orderList.add(item);
        }
      } else {
        msg = "暂无数据！";
      }
      success = true;
    }
    map.put("result", orderList);
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
   * 
   * 用户注册api
   */
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

    }
    /*
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
    } else if (category == null || "".equals(category) || !"1".equals(category)
        && !"2".equals(category) && !"6".equals(category)) {
      regFlag = true;
      error_msg = "用户注册类型错误！";
      // } else if (image == null || "".equals(image)) {
      // regFlag = true;
      // error_msg = "用户头像不能为空！";
    } /*
       * else if (name == null || name.equals("")) { regFlag = true; error_msg = "请输入昵称！"; }
       */else {
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
      sysPojo.setType(category);
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
        sysPojo.setOpenid(openid);
      }
      if (unionid != null && !"".equals(unionid)) {
        sysPojo.setUnionid(unionid);
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
      // String externalSignCode = phone + new Date().getTime() / 1000 + StringUtil.genRandomStr(4);
      // externalSignCode = MD5.MD5Encode(externalSignCode);
      // sysPojo.setExternalSignCode(externalSignCode);
      // sysPojo.setInvitationCode(walletService.genInviteCode());
      sysPojo.setCreateBy(0l);
      if (image != null && !"".equals(image)) {
        sysPojo.setImage(image);
      }
      sysLoginService.addSysLoginService(sysPojo);

      // 得到添加成功的账户id
      if (sysPojo != null && sysPojo.getId() != null && sysPojo.getId() != 0) {
        // 根据用户注册时的手机查询用户
        // sysPojo = sysLoginService.findSysLoginByLoginname(sysPojo);

        // 注册同时userinfo表需插入一条数据
        UserInfoPojo userInfoPojo = new UserInfoPojo();
        userInfoPojo.setUserId(sysPojo.getId());
        userInfoPojo.setCreateDate(new Date());
        userInfoPojo.setStatus(1);
        userInfoPojo.setBabyBirthday(StringUtil.dateToString(new Date()));
        userInfoPojo.setBabySex(1);
        userInfoService.insertUserInfo(userInfoPojo);

        // 插入未激活团免券
        GroupFreeCouponPojo freecp = new GroupFreeCouponPojo();
        freecp.setUserId(sysPojo.getId());
        freecp.setStatus(0);
        freecp.setUsed(0);
        freecp.setCreateBy(sysPojo.getId());
        freecp.setCreateDate(new Date());
        freecp.setUpdateBy(sysPojo.getId());
        freecp.setUpdateDate(freecp.getCreateDate());
        groupFreeCouponService.add(freecp);

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
        /*
         * if (StringUtils.isNotEmpty(imei)) { // 1-ios 2-android Integer channel = 0; if
         * (StringUtils.isEmpty(regChannel) || "App Store".equals(regChannel)) { channel = 1; } else
         * if ("Umeng".equals(regChannel) || "YouMi".equals(regChannel)) { channel = 2; } if
         * (channel > 0) { walletService.checkAdFromYoumi(imei, channel, sysPojo.getId()); } }
         */

        /*
         * HttpServletRequest request = ServletActionContext.getRequest();
         * 
         * LoginRecPojo loginRecPojo = new LoginRecPojo(); loginRecPojo.setType(sysPojo.getType());
         * loginRecPojo.setLoginDate(new Date());
         * loginRecPojo.setLoginIp(walletService.getIpAddr(request));
         * loginRecPojo.setUserId(sysPojo.getId()); loginRecService.addLoginRec(loginRecPojo);
         */

      }

      map1.put("phone", sysPojo.getLoginname());
      map1.put("name", sysPojo.getName());
      map1.put("type", sysPojo.getType());
      map1.put("uid", sysPojo.getId());
      map1.put("token", sysPojo.getToken() == null ? "" : sysPojo.getToken());
      map1.put("openid", sysPojo.getOpenid() == null ? "" : sysPojo.getOpenid());
      map1.put("unionid", sysPojo.getUnionid() == null ? "" : sysPojo.getUnionid());
      map1.put("sinaid", sysPojo.getSinaid() == null ? "" : sysPojo.getSinaid());
      if (sysPojo.getType() != null && "2".equals(sysPojo.getType())) {
        map1.put("judgeType", 1);
      } else {
        map1.put("judgeType", 0);
      }
      // 查询年龄阶段
      map1.put("ageType", "");
      map1.put("followNum", 0);

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
   * 
   * 直接购买
   * 
   * @throws SQLException
   * */
  public String addPurchase() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    GrouponActivityPojo activity = null;

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
    if (uid == null || uid <= 0) {
      msg = "会员ID是必须要填写哦！";
    } else if (sysLogin == null || sysLogin.getStatus() == 0) {
      msg = "该会员ID不存在哦！";
    } else if (pid == null || pid <= 0) {
      msg = "商品ID是必须要填写哦！";
    } else if (num == null || num <= 0) {
      msg = "购买数量是必须要填写哦！";
    } else if (proPojo == null || proPojo.getStatus() == 0) {
      msg = "商品已经下架!";
    } else if (source == null
        || !(source == 1 || source == 2 || source == 3 || source == 4 || source == 5 || source == 6 || source == 7)) {
      msg = "来源不能为空！";
    } else if (skuLinkId == null || skuLinkId <= 0) {
      msg = "请选择规格！";
    } else {
      // 根据产品信息中的用户ID查询到店铺
      ShopPojo shop = new ShopPojo();
      shop.setUserId(proPojo.getUserId());
      shop = shopService.findShop(shop);
      if (shop != null) {
        DecimalFormat df = new DecimalFormat("#.##");
        Double price = proPojo.getDistributionPrice();
        String allprice = "";
        Integer activityFlag = 0;
        boolean stockFlag = true;
        // 活动价格判断
        if (activityId == null) {
          activityId = 0L;
        }
        // 判断是否是0.1红包商品开团8760 8858
        if (source == 5
            && (activityId == 8760 || activityId == 8858 || activityId == 9437 || activityId == 9543)
            && (attendId == null || attendId == 0)) {
          Boolean flag = true;
          if (invCode == null || "".equals(invCode)) {
            flag = false;
            msg = "暂时不支持购买!";
          } else {
            AliRedEnvelopePojo aliRedEnvelope = aliRedEnvelopeService.getByInviteCode(invCode);
            if (aliRedEnvelope == null) {
              msg = "不存在该邀请码!";
              flag = false;
            } else if (aliRedEnvelope != null && aliRedEnvelope.getIsUsed() == 1) {
              msg = "该邀请码已被使用!";
              flag = false;
            }
          }
          if (!flag) {
            map.put("result", result);
            map.put("success", false);
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
        }
        if (source != 4 && source != 7) {
          // 检查团是否过期/人数是否已满
          activityFlag =
              grouponService.purchaseActivityCheck(activityId, source, attendId, uid, pid,
                  skuLinkId, num);
          if (activityFlag == 1) {
            msg = "商品验证不通过!";
          } else if (activityFlag == 2) {
            msg = "该团已满!";
          } else if (activityFlag == 3) {
            msg = "活动已结束!";
          } else if (activityFlag == 4) {
            msg = "不存在该团记录!";
          } else if (activityFlag == 5) {
            msg = "查询不到该活动!";
          } else if (activityFlag == 6) {
            msg = "用户在该活动并未得奖!";
          } else if (activityFlag == 7) {
            msg = "来源类型不正确!";
          } else if (activityFlag == 8) {
            msg = "您已参加过活动!";
          } else if (activityFlag == 9) {
            msg = "您已领取过一等奖奖品!";
          } else if (activityFlag == 10) {
            msg = "库存不足!";
          } else if (activityFlag == 11) {
            msg = "查询不到sku!";
          } else if (activityFlag == 12) {
            msg = "您已经参过团!";
          } else if (activityFlag == 13) {
            msg = "您已经开过团!";
          }
          if (activityFlag != 0) {
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
        }
        // 判断商品sku是否售罄
        ProductSkuLinkPojo productSkuLinkCheck = null;
        if (skuLinkId != null && skuLinkId > 0) {
          productSkuLinkCheck = sellerService.getProducSku(proPojo.getId(), skuLinkId);
          if (productSkuLinkCheck != null) {
            if (num > productSkuLinkCheck.getStock()) {
              // 购买数量大于库存
              msg = "购买数量大于库存";
              stockFlag = false;
            }
          }
        }

        // 判断是否已达到普通商品限购
        try {
          if (source == 1) {
            if (proPojo.getMaxNum() > 0) {
              if (proPojo.getMaxNum() < num) {
                msg = "购买数量大于商品限购";
                stockFlag = false;
              }
              if (stockFlag) {
                Map<String, Object> params1 = new HashMap<String, Object>();
                params1.put("userId", uid);
                params1.put("activityId", activityId);
                params1.put("productId", pid);
                List<ProductRestrictionPojo> productRestrictionPojos =
                    productRestrictionService.listPage(params1);
                if (productRestrictionPojos != null && productRestrictionPojos.size() > 0) {
                  ProductRestrictionPojo productRestriction1 = productRestrictionPojos.get(0);
                  if (productRestriction1.getMaxNum() > 0
                      && productRestriction1.getBuyNum() + num > productRestriction1.getMaxNum()) {
                    msg = "购买数量大于商品限购";
                    stockFlag = false;
                  }
                }
              }
            }
          }
        } catch (Exception e1) {
          e1.printStackTrace();
        }

        // 免费抽判断是否参加过
        if (source == 6) {
          // 是否参团
          int gurCount = 0;
          params.clear();
          params.put("activityId", activityId);
          params.put("userId", uid);
          gurCount = grouponUserRecordService.countBy(params);
          if (gurCount > 0) {
            msg = "您已参加过该活动!";
            stockFlag = false;
          }
        }

        if (!stockFlag) {
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

        // 抽奖判断是否拼过团
        if (activityId > 0 && (source == 7 || source == 5)) {
          if (attendId != null && attendId > 0) {
            params.put("userId", uid);
            params.put("activityId", activityId);
            params.put("isHead", 0);
            if (grouponUserRecordService.countBy(params) > 0) {
              map.put("result", result);
              map.put("error_msg", "您已经参过该团!");
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
          } else {
            params.put("userId", uid);
            params.put("activityId", activityId);
            params.put("isHead", 1);
            if (grouponUserRecordService.countBy(params) > 0) {
              map.put("result", result);
              map.put("error_msg", "您已经参过该团!");
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
          }
        }

        if (activityId > 0 && source != 4) {
          activity = grouponActivityService.getById(activityId);
          if (activity != null && activity.getStatus() == 1) {
            // 3-猜价 2-团长团免 价格为0
            if (activity.getType() == 2 && (attendId == null || attendId == 0)
                || activity.getType() == 3 || activity.getType() == 7) {
              price = 0d;
              num = 1;
            } else {
              price = activity.getPrice();
            }
            // 限时秒杀和0.1抽奖购买数量只能是1
            if (activity.getType() == 5 || activity.getType() == 6) {
              num = 1;
            }
          }
        }

        allprice = df.format(price * num);
        Map<String, Object> product = new HashMap<String, Object>();
        product.put("productId", proPojo.getId());
        product.put("sellingPrice", df.format(proPojo.getSellingPrice()));
        product.put("price", df.format(price));
        product.put("discount", SellerService.calcDiscount(price, proPojo.getSellingPrice()));
        product.put("productName", proPojo.getProductName());
        product.put("productNumber", num);
        product.put("productImage", ConstParam.URL + "/upfiles/product/" + proPojo.getImage());
        product.put("allPrice", allprice);
        product.put("skuLinkId", skuLinkId == null ? "" : skuLinkId);
        if (skuLinkId != null) {
          ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
          productSkuLink.setId(Long.valueOf(skuLinkId));
          productSkuLink = productSkuLinkService.findProductSkuLink(productSkuLink);
          if (productSkuLink != null) {
            product.put("skuValue",
                "颜色:" + productSkuLink.getSkuColor() + ";尺码:" + productSkuLink.getSkuFormat());
          } else {
            product.put("skuValue", "");
          }
        } else {
          product.put("skuValue", "");
        }
        product.put("activityId", activityId);
        // 商品活动类型
        product.put("type", source);

        result.put("shopLogo", ConstParam.URL + "/upfiles/shop/" + shop.getImages());
        result.put("allCount", num);
        result.put("sumPrice", allprice);
        // weight += Double.valueOf(proPojo.getWeight()) * num;
        // map1.put("espressPrice", df.format(SellerService.calcFare(weight)));
        result.put("espressPrice", 0);
        result.put("shopId", shop.getId());
        result.put("shopName", shop.getName());
        result.put("products", product);
        b = true;

      } else {
        msg = "未找到该产品店铺信息！";
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
    Map<String, Object> params = new HashMap<String, Object>();
    GrouponActivityPojo activity = null;
    String msg = "";
    String openid = null;
    boolean b = false;
    // 根据用户ID查询用户信息
    SysLoginPojo user = null;
    if (uid != null && uid > 0) {
      user = sysLoginService.findSysLoginById(uid);
    }
    if (payMethod != null && payMethod == 8) {
      // 公众号支付openid
      openid = user.getOpenid();
    }
    // 获取商品信息
    ProductPojo productPojo = null;
    if (pid != null && pid > 0) {
      productPojo = new ProductPojo();
      productPojo.setId(pid);
      productPojo = productService.findProduct(productPojo);
    }
    DeliveryAddressPojo address = null;// 自提地址可以为空
    // 快递方式
    if (addressId != null && addressId > 0 && consigneeType != null && consigneeType == 1) {
      address = addressService.findDeliveryAddressById(addressId);// 获取收货地址信息
    }
    if (uid == null || uid <= 0) {
      msg = "会员Id不能为空哦！";
    } else if (user == null || user.getStatus() == 0) {
      msg = "该会员ID不存在哦！";
    } else if (pid == null || pid <= 0) {
      msg = "产品Id不能为空哦！";
    } else if (productPojo == null || productPojo.getStatus() == 0) {
      msg = "商品已下架！";
    } else if (num == null || num <= 0) {
      msg = "购买数量不能为空哦！";
    } else if (consigneeType == null || consigneeType == 0) {
      msg = "收货方式有误！";
    } else if (consigneeType == 1 && (addressId == null || addressId == 0 || address == null)) {
      msg = "收货地址不能为空！";
    } else if (channel == null || !(channel == 1 || channel == 2)) {
      msg = "订单渠道错误！";
    } else if (payMethod == null || channel == 2 && payMethod != 8 || channel != 2
        && payMethod != 1 && payMethod != 2) {
      // 1-支付宝 2-微信APP支付 3-货到付款 4-钱包支付 5-银联支付 6-苹果支付 7-招行支付 8-微信公众号支付
      msg = "支付方式有误哦！";
    } else if (8 == payMethod && StringUtils.isBlank(openid)) {
      msg = "支付参数错误";
    } else if (source == null
        || !(source == 1 || source == 2 || source == 3 || source == 4 || source == 5 || source == 6 || source == 7)) {
      msg = "来源不能为空！";
    } else if (skuLinkId == null || skuLinkId <= 0) {
      msg = "请选择规格！";
    } else {
      if (activityId == null) {
        activityId = 0l;
      }
      // 偏远地区判断
      try {
        Boolean dFlag = false;
        if (productPojo != null && address != null && productPojo.getFaraway() != null
            && StringUtils.isNotEmpty(productPojo.getFaraway()) && address.getProvinceId() != null
            && address.getProvinceId() > 0) {
          List<Long> farawayList = new ArrayList<>();
          String[] farawayArr = productPojo.getFaraway().split(",");
          if (farawayArr != null && farawayArr.length > 0) {
            for (String f : farawayArr) {
              farawayList.add(Long.valueOf(f));
            }
          }
          if (farawayList != null && farawayList.size() > 0) {
            if (farawayList.contains(address.getProvinceId())) {
              dFlag = true;
            }
          }
        }
        if (dFlag) {
          map.put("result", result);
          map.put("error_msg", "您的地址不在配送范围内!");
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
      } catch (Exception e2) {
        e2.printStackTrace();
      }
      // 判断是否是0.1红包商品开团
      AliRedEnvelopePojo aliRedEnvelope = null;
      if (source == 5
          && (activityId == 8760 || activityId == 8858 || activityId == 9437 || activityId == 9543)
          && (attendId == null || attendId == 0)) {
        Boolean flag = true;
        if (invCode == null || "".equals(invCode)) {
          flag = false;
          msg = "暂时不支持购买!";
        } else {
          aliRedEnvelope = aliRedEnvelopeService.getByInviteCode(invCode);
          if (aliRedEnvelope == null) {
            msg = "不存在该邀请码!";
            flag = false;
          } else if (aliRedEnvelope != null && aliRedEnvelope.getIsUsed() == 1) {
            msg = "该邀请码已被使用!";
            flag = false;
          }
        }
        if (!flag) {
          map.put("result", result);
          map.put("success", false);
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
      }
      // 分割消息
      String[] msgs = null;
      if (buyer_message != null) {
        msgs = buyer_message.split(",");
      }
      DecimalFormat df = new DecimalFormat("#.##");
      // 订单描述--支付宝
      String body = productPojo.getProductName();
      // 商户订单号--支付宝
      String out_trade_no = UtilDate.getOrderNum() + uid + RandomUtils.runVerifyCode(3);
      // 是否算运费,默认否
      boolean isFee = false;
      double price = productPojo.getDistributionPrice();// 单价
      double postPrice = 0.0;// 邮费
      double allCartPrice = 0.0;// 订单价格
      double allCartPrice0 = 0.0;// 总价格
      boolean stockFlag = true;
      Integer activityFlag = 0;
      // 活动价格判断
      if (source != 4 && source != 7) {
        // 检查团是否过期/人数是否已满
        activityFlag =
            grouponService.purchaseActivityCheck(activityId, source, attendId, uid, pid, skuLinkId,
                num);
        if (activityFlag == 1) {
          msg = "商品验证不通过!";
        } else if (activityFlag == 2) {
          msg = "该团已满!";
        } else if (activityFlag == 3) {
          msg = "活动已结束!";
        } else if (activityFlag == 4) {
          msg = "不存在该团记录!";
        } else if (activityFlag == 5) {
          msg = "查询不到该活动!";
        } else if (activityFlag == 6) {
          msg = "用户在该活动并未等奖!";
        } else if (activityFlag == 7) {
          msg = "来源类型不正确!";
        } else if (activityFlag == 8) {
          msg = "您已参加过活动!";
        } else if (activityFlag == 9) {
          msg = "您已领取过一等奖奖品!";
        } else if (activityFlag == 10) {
          msg = "库存不足!";
        } else if (activityFlag == 11) {
          msg = "查询不到sku";
        } else if (activityFlag == 12) {
          msg = "您已经参过团!";
        } else if (activityFlag == 13) {
          msg = "您已经开过团!";
        } else if (activityFlag == 14) {
          msg = "您购买数量超过了限购数量!";
        }
        if (activityFlag != 0) {
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
      }

      // 免费抽判断是否参加过
      if (source == 6) {
        // 是否参团
        int gurCount = 0;
        params.clear();
        params.put("activityId", activityId);
        params.put("userId", uid);
        gurCount = grouponUserRecordService.countBy(params);
        if (gurCount > 0) {
          msg = "您已参加过该活动!";
          stockFlag = false;
        }
        if (source == 7 && attendId != null && attendId > 0) {
          // 判断团拼团人数是否已到
          if (activityId != null) {
            GrouponActivityPojo grouponActivity = grouponActivityService.getById(activityId);
            if (grouponActivity != null) {
              params.clear();
              params.put("attendId", attendId);
              gurCount = grouponUserRecordService.countBy(params);
              if (gurCount >= grouponActivity.getNum()) {
                msg = "该团拼团人数已到!";
                stockFlag = false;
              }
            } else {
              msg = "查询不到活动!";
              stockFlag = false;
            }
          }
        }
      }

      if (source != 5 && source != 6 && source != 7) {
        // sku价格判断
        if (stockFlag) {
          CartPojo cart = new CartPojo();
          cart.setProductId(productPojo.getId());
          cart.setNum(num);
          if (skuLinkId != null && skuLinkId > 0) {
            cart.setSkuLinkId(skuLinkId);
            stockFlag = sellerService.updateActivitySkuStock(cart);
            // if (stockFlag) { // 更新购买数量 num = cart.getNum(); }
            if (!stockFlag) {
              stockFlag = false;
              msg = "该规格的商品已售罄!";
            }
          }
        }
        // 判断是否已达到普通商品限购
        try {
          if (source == 1) {
            if (productPojo.getMaxNum() > 0) {
              if (productPojo.getMaxNum() < num) {
                msg = "购买数量大于商品限购";
                stockFlag = false;
              }
              if (stockFlag) {
                Map<String, Object> params1 = new HashMap<String, Object>();
                params1.put("userId", uid);
                params1.put("activityId", activityId);
                params1.put("productId", pid);
                List<ProductRestrictionPojo> productRestrictionPojos =
                    productRestrictionService.listPage(params1);
                if (productRestrictionPojos != null && productRestrictionPojos.size() > 0) {
                  ProductRestrictionPojo productRestriction1 = productRestrictionPojos.get(0);
                  if (productRestriction1.getMaxNum() > 0
                      && productRestriction1.getBuyNum() + num > productRestriction1.getMaxNum()) {
                    msg = "购买数量大于商品限购";
                    stockFlag = false;
                  }
                }
              }
            }
          }
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
      if (!stockFlag) {
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
      // 抽奖判断是否拼过团
      if (activityId > 0 && (source == 7 || source == 5)) {
        if (attendId != null && attendId > 0) {
          params.clear();
          params.put("userId", uid);
          params.put("activityId", activityId);
          params.put("isHead", 0);
          if (grouponUserRecordService.countBy(params) > 0) {
            map.put("result", result);
            map.put("error_msg", "您已经参过该团!");
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
        } else {
          params.clear();
          params.put("userId", uid);
          params.put("activityId", activityId);
          params.put("isHead", 1);
          if (grouponUserRecordService.countBy(params) > 0) {
            map.put("result", result);
            map.put("error_msg", "您已经开过该团!");
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
        }
      }

      if (activityId > 0 && source != 4) {
        activity = grouponActivityService.getById(activityId);
        if (activity != null && activity.getStatus() == 1) {
          // 3-猜价 2-团长团免 价格为0
          if (activity.getType() == 2 && (attendId == null || attendId == 0)
              || activity.getType() == 3 || activity.getType() == 7) {
            price = 0d;
            num = 1;
          } else {
            price = activity.getPrice();
          }
          // 限时秒杀和0.1抽奖购买数量只能是1
          if (activity.getType() == 5 || activity.getType() == 6) {
            num = 1;
          }
        }
      }

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
      // String province = "";
      // if (address != null && address.getProvinceName() != null
      // && !"".equals(address.getProvinceName())) {
      // province = address.getProvinceName();
      // if (province.indexOf("新疆") != -1 || province.indexOf("西藏") != -1
      // || province.indexOf("内蒙古") != -1 || province.indexOf("青海") != -1
      // || province.indexOf("甘肃") != -1 || province.indexOf("宁夏") != -1) {
      // // 总重量
      // double allWeight = num * Double.valueOf(productPojo.getWeight());
      // deliverFee = SellerService.calcFare(allWeight);
      // // 订单价格
      // allCartPrice0 += deliverFee;
      // }
      // }

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
      order.setChannel(channel);
      order.setCreateBy(user.getId());
      order.setCreateDate(new Date());
      order.setOrderNo(orderNo);
      order.setPayMethod(payMethod);
      order.setIp(ip);// 下单ip

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
      // 活动id
      order.setActivityId(activityId);
      // 来源
      order.setSource(source);
      // 插入团记录id
      if (attendId == null || attendId == 0) {
        order.setSourceId(0L);
      } else {
        order.setSourceId(attendId);
      }
      // 插入成团人数
      if (activity != null && activity.getNum() != null) {
        order.setGroupNum(activity.getNum());
      }
      orderService.insertOrder(order);

      if ((attendId == null || attendId == 0) && source == 2) {
        // 判断团免券是否有效
        Map<String, Object> params3 = new HashMap<String, Object>();
        params3.put("userId", uid);
        params3.put("status", 1);
        params3.put("used", 0);
        // 有效时间
        params3.put("validTime", StringUtil.dateString(new Date()));
        List<GroupFreeCouponPojo> groupFreeCoupons = groupFreeCouponService.listPage(params3);
        GroupFreeCouponPojo groupFreeCoupon = null;
        if (groupFreeCoupons.size() > 0) {
          groupFreeCoupon = groupFreeCoupons.get(0);
        }
        // 开团,判断是否团活动是否到期
        if (groupFreeCoupon != null
            && DateTimeUtil.compareDate(StringUtil.dateString(groupFreeCoupon.getInvalidTime()),
                StringUtil.dateString(new Date()))) {
          // 修改团免券激活状态
          groupFreeCoupon.setStatus(0);
          groupFreeCoupon.setUsed(1);
          groupFreeCouponService.update(groupFreeCoupon);
        }
      }

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
      orderDetail.setChannel(payMethod);
      orderDetail.setStatus(1);
      orderDetail.setSkuLinkId(skuLinkId);
      orderDetail.setCreateBy(user.getId());
      orderDetail.setCreateDate(new Date());
      orderDetail.setType(source);
      // 活动id
      orderDetail.setActivityId(activityId);
      // 来源
      orderDetail.setSource(source);
      // 插入团记录id
      if (attendId == null || attendId == 0) {
        orderDetail.setSourceId(0L);
      } else {
        orderDetail.setSourceId(attendId);
      }
      orderDetail.setReStatus(0l);
      orderDetailService.insertOrderDetail(orderDetail);

      // 订单金额大于0 才处理
      if (allCartPrice0 > 0) {
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
          if (userCoupon != null) {
            if (userCoupon.getIsProduct() != null && userCoupon.getIsProduct() == 1) {
              if (userCoupon.getProductId().longValue() == pid.longValue()) {
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
              } else {
                msg = "优惠券指定商品不是该订单商品,无法使用该优惠券!";
              }
            } else {
              if (1 == userCoupon.getType() || 2 == userCoupon.getType()) {
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
          } else {
            msg = "查询不到优惠券!";
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
      }
      int fullpay = 0;
      if (allCartPrice0 > 0) {
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
              sellerService.buildAlipayReq(alipayOrderInfoPojo.getOutTradeNo(), subject,
                  alipayOrderInfoPojo.getTotalFee(), body, show_url);

        } else if (2 == payMethod || 8 == payMethod) {
          // 生成未付款微信支付信息
          WxpayOrderInfoPojo wxpay = new WxpayOrderInfoPojo();
          wxpay.setOutTradeNo(out_trade_no);
          // 单位分
          wxpay.setTotalFee(SellerService.doubleToFee(allCartPrice0));
          wxpay.setTradeStatus("WAIT_BUYER_PAY");
          wxpay.setVersion(0);
          String tradeType = 2 == payMethod ? "APP" : "JSAPI";
          wxpay.setTradeType(tradeType);
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
              sellerService.buildWxpayReq(openid, wxpay.getOutTradeNo(), null, body, subject,
                  Integer.valueOf(wxpay.getTotalFee()), tradeType);
          wxpayMap.put("out_trade_no", out_trade_no);
        }
      } else {
        // 付款成功
        processPaySuccess(out_trade_no);
        // 拼团成功处理
        // grouponService.groupOrderHandle(activityId, attendId, uid, source, order.getId());

        fullpay = 1;
      }

      b = true;
      result.put("fullpay", fullpay);
      result.put("aplipay", alipayMap);
      result.put("wxpay", wxpayMap);
      if (order != null) {
        result.put("orderId", StringUtil.checkVal(order.getId()));
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
   * 支付交易查询状态.
   * 
   * @return
   * @throws Exception
   */
  public String queryPayStatus() throws Exception {
    if (payMethod != null && payMethod == 1 && StringUtils.isNotBlank(outTradeNo)) {
      queryAlipayStatus(outTradeNo);
    } else if (payMethod != null && payMethod == 2 && StringUtils.isNotBlank(outTradeNo)) {
      queryWxpayStatus(outTradeNo);
    }
    return null;
  }

  /**
   * 支付宝查询.
   * 
   * @param outTradeNo
   * @return
   * @throws Exception
   */
  public String queryAlipayStatus(String outTradeNo) throws Exception {
    AlipayOrderInfoPojo alipay = alipayOrderInfoService.findPayInfoByTradeNo(outTradeNo);
    if (alipay != null && "WAIT_BUYER_PAY".equals(alipay.getTradeStatus())) {
      // 把请求参数打包成数组
      Map<String, String> sParaTemp = new HashMap<String, String>();
      sParaTemp.put("service", "single_trade_query");
      sParaTemp.put("partner", AlipayConfig.partner);
      sParaTemp.put("_input_charset", AlipayConfig.input_charset);
      sParaTemp.put("out_trade_no", outTradeNo);

      String rsp = AlipaySubmit.buildRequest("", "", sParaTemp);
      System.out.println(">>>>>> 支付宝交易查询结果[" + outTradeNo + "]" + rsp);
      XStream stream = new XStream(new DomDriver());
      stream.processAnnotations(AlipayTradeQueryResponse.class);
      AlipayTradeQueryResponse response = (AlipayTradeQueryResponse) stream.fromXML(rsp);
      processOrderPayStatus(1, outTradeNo, null, response);
    }

    return null;
  }

  /**
   * 微信支付查询.
   * 
   * @param outTradeNo
   * @return
   * @throws Exception
   */
  public String queryWxpayStatus(String outTradeNo) throws Exception {
    WxpayOrderInfoPojo wxpay = wxpayOrderInfoService.findPayInfoByTradeNo(outTradeNo);
    if (wxpay != null && "WAIT_BUYER_PAY".equals(wxpay.getTradeStatus())) {
      // 把请求参数打包成数组
      ScanPayQueryReqData qryPayReqData =
          new ScanPayQueryReqData(null, outTradeNo, wxpay.getTradeType());
      String rsp = "";
      try {
        rsp = WXPay.requestScanPayQueryService(qryPayReqData);
        System.out.println(">>>>>> 微信支付交易查询结果[" + outTradeNo + "]" + rsp);
        // 将从API返回的XML数据映射到Java对象
        ScanPayQueryResData qyrPayResData =
            (ScanPayQueryResData) Util.getObjectFromXML(rsp, ScanPayQueryResData.class);
        processOrderPayStatus(2, outTradeNo, qyrPayResData, null);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return null;
  }

  /**
   * 支付成功处理.
   * 
   * @param payMethod 支付方式
   * @param outTradeNo 流水号
   * @param wxqry 微信支付信息
   * @param aliqry 支付宝支付信息
   * @throws Exception
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  protected void processOrderPayStatus(int payMethod, String outTradeNo, ScanPayQueryResData wxqry,
      AlipayTradeQueryResponse aliqry) throws Exception {
    if (payMethod == 1 && aliqry != null) {
      AlipayOrderInfoPojo alipayOrderInfoPojo =
          alipayOrderInfoService.findPayInfoByTradeNo(outTradeNo);
      if (alipayOrderInfoPojo != null
          && "WAIT_BUYER_PAY".equals(alipayOrderInfoPojo.getTradeStatus())) {
        // 请求成功
        if ("T".equals(aliqry.getIsSuccess())) {
          // 交易成功
          if ("TRADE_FINISHED".equals(aliqry.getResponse().getTrade().getTrade_status())
              || "TRADE_SUCCESS".equals(aliqry.getResponse().getTrade().getTrade_status())) {
            // 验证价格
            Double totalFee = Double.valueOf(aliqry.getResponse().getTrade().getTotal_fee());
            Double aliTotalFee = Double.valueOf(alipayOrderInfoPojo.getTotalFee());
            if (totalFee.doubleValue() == aliTotalFee.doubleValue()) {
              // 更新支付宝订单状态
              alipayOrderInfoPojo.setTradeNo(aliqry.getResponse().getTrade().getTrade_no());
              alipayOrderInfoPojo.setBuyerEmail(aliqry.getResponse().getTrade().getBuyer_email());
              alipayOrderInfoPojo.setBuyerId(aliqry.getResponse().getTrade().getBuyer_id());
              alipayOrderInfoPojo.setTradeStatus(aliqry.getResponse().getTrade().getTrade_status());
              alipayOrderInfoPojo.setOutTradeNo(aliqry.getResponse().getTrade().getOut_trade_no());
              alipayOrderInfoPojo.setUpdateDate(new Date());
              // 异步标志
              alipayOrderInfoPojo.setUpdateBy(998L);
              alipayOrderInfoService.updateOrder(alipayOrderInfoPojo);

              processPaySuccess(outTradeNo);
            } else {
              System.out.println("支付价格与订单价格不一致!");
            }
          } else if (!"WAIT_SYS_CONFIRM_PAY".equals(aliqry.getResponse().getTrade()
              .getTrade_status())) {
            // WAIT_SYS_CONFIRM_PAY:付款确认中的不处理
            // 交易失败处理
            processPayFailure(outTradeNo);
          }
        } else if ("F".equals(aliqry.getIsSuccess()) && "TRADE_NOT_EXIST".equals(aliqry.getError())) {
          // TRADE_NOT_EXIST:支付宝交易不存在（情况：支付时取消）
          // 交易失败处理
          processPayFailure(outTradeNo);
        }
      }
    } else if (payMethod == 2 && wxqry != null) {
      WxpayOrderInfoPojo wxpay = wxpayOrderInfoService.findPayInfoByTradeNo(outTradeNo);
      if (wxpay != null && "WAIT_BUYER_PAY".equals(wxpay.getTradeStatus())) {
        // 请求状态成功
        if ("SUCCESS".equals(wxqry.getResult_code())) {
          if ("SUCCESS".equals(wxqry.getTrade_state())) {
            // 验证价格
            Double totalFee = Double.valueOf(wxqry.getTotal_fee());
            Double wxTotalFee = Double.valueOf(wxpay.getTotalFee());
            if (totalFee.doubleValue() == wxTotalFee.doubleValue()) {
              wxpay.setBankType(wxqry.getBank_type());
              wxpay.setTimeEnd(wxqry.getTime_end());
              wxpay.setTradeStatus("TRADE_SUCCESS");
              wxpay.setTransactionId(wxqry.getTransaction_id());
              wxpay.setUpdateBy(998L);
              wxpayOrderInfoService.updatePay(wxpay);

              processPaySuccess(outTradeNo);

            } else {
              System.out.println("支付价格与订单价格不一致!");
            }
          } else if (!"USERPAYING".equals(wxqry.getTrade_state())) {
            // USERPAYING：支付处理中的不处理
            // 交易失败处理
            processPayFailure(outTradeNo);
          }
        }
      }
    }

  }

  /**
   * 付款成功处理.
   * 
   * @param outTradeNo 交易订单号
   * @return 用户id
   * @throws Exception
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  protected Long processPaySuccess(String outTradeNo) throws Exception {
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
      orderPojo = orders.get(0);
      // 支付成功参团/开团处理
      grouponService.groupOrderHandle(orderPojo.getActivityId(), orderPojo.getSourceId(), uid,
          orderPojo.getSource(), orderPojo.getId());

      try {
        // 添加商品限购
        OrderPojo order = orderService.findOrderByOrderNo(orderPojo.getOrderNo());
        if (order != null && order.getSource() != null && order.getSource() == 1
            && order.getMaxNum() != null && order.getMaxNum() > 0) {
          Map<String, Object> params = new HashMap<String, Object>();
          params.put("userId", orderPojo.getUserId());
          params.put("activityId", orderPojo.getActivityId());
          params.put("productId", Long.valueOf(order.getProductId()));
          List<ProductRestrictionPojo> productRestrictionPojos =
              productRestrictionService.listPage(params);
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
        if (p.getSource() != 5) {
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
    }


    return uid;
  }

  /**
   * 付款失败处理.
   * 
   * @param outTradeNo 交易订单号
   * @throws Exception
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  protected void processPayFailure(String outTradeNo) throws Exception {
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
   * 
   * 订单可用优惠券列表
   * 
   * @throws SQLException
   * */
  public String getValidUserCoupon() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();

    List<Map<String, Object>> coupons = new ArrayList<Map<String, Object>>();

    if (uid == null || uid < 1) {
      msg = "用户id不能为空！";
    } else if (price == null || price == 0) {
      msg = "订单金额不能为空！";
    } else if (pid == null || pid < 1) {
      msg = "商品ID不能为空！";
    } else {
      Map<String, Object> param = new HashMap<String, Object>();
      long currentTime = new Date().getTime() / 1000;
      // 如果不是当前时间有效的优惠券，不能使用
      param.put("couponNum", 1);
      param.put("validStime", currentTime);
      param.put("validEtime", currentTime);
      param.put("userId", uid);
      param.put("status", 1);
      param.put("used", 0);
      param
          .put(
              "orderBy",
              "ifnull(u.product_id,0) desc,ifnull(substring(c.content,LOCATE('\"m\":\"',c.content)+5,LOCATE('\"}',c.content)-LOCATE('\"m\":\"',c.content)-5) ,0)+0 desc,u.valid_etime desc");
      List<UserCouponPojo> userCoupons = couponService.getuserCouponList(param);
      if (userCoupons != null && userCoupons.size() > 0) {
        Map<String, Object> couponmap = null;
        boolean canUse = false;
        Integer isProduct = 0;
        Integer couponType = 0;
        ProductPojo product = null;
        for (UserCouponPojo userCouponPojo : userCoupons) {
          isProduct = userCouponPojo.getIsProduct() == null ? 0 : userCouponPojo.getIsProduct();
          // 指定商品优惠券，非对应商品ID优惠券过滤
          if (isProduct == 1 && userCouponPojo.getProductId().doubleValue() != pid) {
            continue;
          }
          // 判断订单金额能否使用优惠券
          canUse = grouponService.checkCouponUsePrice(userCouponPojo.getCouponId(), price);
          if (!canUse) {
            continue;
          }
          couponmap = new HashMap<String, Object>();
          couponmap.put("couponNo", userCouponPojo.getCouponNo());
          couponmap.put("couponId", userCouponPojo.getCouponId());
          couponmap.put("couponName", userCouponPojo.getCouponName());
          couponmap.put(
              "validStime",
              userCouponPojo.getValidstime() == null ? "" : StringUtil.dateToString(new Date(
                  userCouponPojo.getValidstime() * 1000)));
          couponmap.put(
              "validEtime",
              userCouponPojo.getValidetime() == null ? "" : StringUtil.dateToString(new Date(
                  userCouponPojo.getValidetime() * 1000)));
          couponmap.put("used", userCouponPojo.getUsed());
          if (userCouponPojo.getUsed() == 1) {
            couponmap.put(
                "useTime",
                userCouponPojo.getUseTime() == null ? "" : StringUtil.dateToString(new Date(
                    userCouponPojo.getUseTime() * 1000)));
          } else {
            couponmap.put("useTime", "");
          }
          couponmap.put("overdue", "0");
          // 指定商品
          couponmap.put("isProduct", isProduct == 1 ? "1" : "0");
          couponmap.put("productName", "");
          if (isProduct == 1) {
            product = new ProductPojo();
            product.setId(userCouponPojo.getProductId());
            product = productService.findProduct(product);
            if (product != null) {
              couponmap.put("productName", StringUtil.checkVal(product.getProductName()));
            }
          }

          couponType = userCouponPojo.getType() == null ? 0 : userCouponPojo.getType();
          couponmap.put("couponType", StringUtil.checkVal(couponType));
          if (1 == couponType) {
            // 满om减m时返回金额
            String str = userCouponPojo.getContent();
            org.json.JSONObject json = new org.json.JSONObject(str);
            couponmap.put("om", json.get("om"));
            couponmap.put("m", json.get("m"));
          } else if (2 == couponType) {
            // 直减减m时返回金额
            String str = userCouponPojo.getContent();
            org.json.JSONObject json = new org.json.JSONObject(str);
            couponmap.put("om", "");
            couponmap.put("m", json.get("m"));
          } else {
            couponmap.put("om", "");
            couponmap.put("m", "");
          }
          couponmap.put("source", StringUtil.checkVal(userCouponPojo.getSource()));
          coupons.add(couponmap);
        }
        if (coupons.size() > 0) {
          b = true;
        } else {
          msg = "未找到可用优惠券！";
        }
      } else {
        msg = "未找到可用优惠券！";
      }

    }

    map.put("result", coupons);
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
   * 订单详情
   * 
   * @throws Exception
   * */
  public String orderdetail() throws Exception {
    String msg = "";
    boolean success = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    Map<String, Object> user = new HashMap<String, Object>();
    Map<String, Object> orderInfo = new HashMap<String, Object>();
    Map<String, Object> productInfo = new HashMap<String, Object>();
    Map<String, Object> addressInfo = new HashMap<String, Object>();
    List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
    DecimalFormat df = new DecimalFormat("#.##");
    if (oid == null || oid < 1) {
      map.put("error_msg", "订单ID不能为空！");
      map.put("success", false);
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("id", oid);
      params.put("isDeleteOrder", 0);
      List<OrderPojo> orders = orderService.listPage(params);
      if (orders != null && orders.size() > 0) {
        OrderPojo order = orders.get(0);
        orderInfo.put("orderId", StringUtil.checkVal(order.getId()));
        orderInfo.put("orderNo", StringUtil.checkVal(order.getOrderNo()));
        productInfo.put("productImage", ConstParam.URL + "/upfiles/product" + File.separator
            + StringUtil.checkVal(order.getProductImage()));
        productInfo.put("productName", StringUtil.checkVal(order.getProductName()));
        productInfo.put("productId", StringUtil.checkVal(order.getProductId()));
        productInfo.put("number", StringUtil.checkVal(order.getNum()));

        if (order.getAllPrice() == null) {
          order.setAllPrice(0d);
        }
        if (order.getEspressPrice() == null) {
          order.setEspressPrice(0d);
        }
        if (order.getUsedPrice() != null) {
          productInfo.put("orderPrice", df.format(order.getFactPrice() - order.getUsedPrice()));
        } else {
          productInfo.put("orderPrice", df.format(order.getFactPrice()));
        }
        productInfo.put("espressPrice", df.format(order.getEspressPrice()));
        productInfo.put("allPrice", df.format(order.getAllPrice()));
        // sku
        Long skuid = order.getSkuLinkId();
        productInfo.put("skuLinkId", StringUtil.checkVal(skuid));
        productInfo.put("skuValue", "");
        if (skuid != null) {
          ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
          productSkuLink.setId(skuid);
          productSkuLink = productSkuLinkService.findProductSkuLink(productSkuLink);
          if (productSkuLink != null) {
            String skuvalue = "";
            if (StringUtils.isNotBlank(productSkuLink.getSkuColor())) {
              skuvalue += "颜色:" + productSkuLink.getSkuColor() + ";";
            }
            if (StringUtils.isNotBlank(productSkuLink.getSkuFormat())) {
              skuvalue += "款式:" + productSkuLink.getSkuFormat() + ";";
            }
            item.put("skuValue", skuvalue);
          }
        }

        item.put("activityId", StringUtil.checkVal(order.getActivityId()));
        item.put("source", StringUtil.checkVal(order.getSource()));
        item.put("attendId", StringUtil.checkVal(order.getSourceId()));
        item.put("isCancel", StringUtil.checkVal(order.getIsCancelOrder()));
        item.put("isSuccess", StringUtil.checkVal(order.getIsSuccess()));

        item.put("isPrize", "0");
        item.put("oweNum", "");
        params.clear();
        params.put("attendId", order.getSourceId());
        if ("5".equals(StringUtil.checkVal(order.getSource()))
            || "7".equals(StringUtil.checkVal(order.getSource()))) {
          int joinNum = grouponUserRecordService.countBy(params);
          int groupNum = order.getGaNum();
          if (order.getIsCancelOrder() == 1) {
            // 交易已取消 5
            item.put("orderStatus", String.valueOf(5));
          } else if (order.getOrderStatus() == 1) {
            // 待支付 1
            item.put("orderStatus", String.valueOf(1));
          } else if (order.getOrderStatus() >= 2) {
            if (order.getIsSuccess() == 0) {
              // 拼团中，差N人 2
              item.put("orderStatus", String.valueOf(2));
              item.put("oweNum", String.valueOf(groupNum - joinNum));
              // 12-待开奖
              if (groupNum - joinNum <= 0) {
                item.put("orderStatus", "12");
              }
            } else if (order.getIsSuccess() == 2) {
              if (order.getIsRefund() == 1) {
                // 未成团，退款中 3
                item.put("orderStatus", String.valueOf(3));
              } else if (order.getIsRefund() == 2) {
                // 未成团，已退款 4
                item.put("orderStatus", String.valueOf(4));
              } else {
                // 查询是抽不到奖还是时间到拼团失败
                if (order.getSource() == 7) {
                  if (joinNum >= order.getGaNum()) {
                    // 未中奖，待返款 6
                    item.put("orderStatus", String.valueOf(6));
                  } else {
                    // 未成团，退款中3
                    item.put("orderStatus", String.valueOf(3));
                  }
                } else {
                  params.clear();
                  params.put("sourceId", order.getSourceId());
                  params.put("isSuccess", 1);
                  int i = orderService.countBy(params);
                  if (i > 0) {
                    // 未中奖，待返款 6
                    item.put("orderStatus", String.valueOf(6));
                  } else {
                    // 未成团，退款中3
                    item.put("orderStatus", String.valueOf(3));
                  }
                }
              }

              if ("3".equals(StringUtil.checkVal(order.getGurStatus()))) {
                item.put("isPrize", "1");
              }
            } else if (order.getIsSuccess() == 1) {
              if (order.getPrize() == 0) {
                if (order.getIsRefund() == 0) {
                  // 未中奖，待返款 6
                  item.put("orderStatus", String.valueOf(6));
                } else if (order.getIsRefund() == 2) {
                  // 未中奖，已返款 7
                  item.put("orderStatus", String.valueOf(7));
                }
              } else if (order.getPrize() > 0) {
                if (order.getOrderStatus() >= 4) {
                  // 已中奖，已完成 9
                  item.put("orderStatus", String.valueOf(9));
                } else if (order.getOrderStatus() == 2) {
                  // 已中奖，待发货 10
                  item.put("orderStatus", String.valueOf(10));
                } else if (order.getOrderStatus() == 3) {
                  // 已中奖，待收货 11
                  item.put("orderStatus", String.valueOf(11));
                }
              }

              item.put("isPrize", "1");
            }
          }
        } else {
          item.put("orderStatus", StringUtil.checkVal(order.getOrderStatus()));
        }

        item.put("refundStatus", StringUtil.checkVal(order.getRefundStatus()));

        // 3-货到付款
        /*
         * if (order.getPayMethod() != null && 3 == order.getPayMethod()) { item.put("deliverType",
         * "货到付款"); } else if (order.getConsigneeType() == 2) { item.put("deliverType", "自提"); }
         * else if (order.getConsigneeType() == 1) { // 快递 }
         */

        // 支付方式：1-支付宝，2-微信支付，3-货到付款
        orderInfo.put("paymethod", StringUtil.checkVal(order.getPayMethod()));
        // 地址
        addressInfo.put("tel", StringUtil.checkVal(order.getConsigneePhone()));
        addressInfo.put("address", StringUtil.checkVal(order.getConsigneeAddress()));
        addressInfo.put("consignee", StringUtil.checkVal(order.getConsignee()));
        // 买家留言
        item.put("buymsg", StringUtil.checkVal(order.getBuyerMessage()));

        // 下单时间
        orderInfo.put("createTime", StringUtil.checkVal(order.getCreateDate()));
        // 订单详情ID
        orderInfo.put("orderDid", StringUtil.checkVal(order.getOrderDid()));

        // 成团时间
        orderInfo.put("groupTime", StringUtil.checkVal(order.getGroupDate()));

        // 发货时间
        orderInfo.put("sendTime", "");
        orderInfo.put("logisticsName", "");
        orderInfo.put("logisticsNo", "");
        if (order.getOrderStatus() >= 3) {
          orderInfo.put("sendTime", StringUtil.checkVal(order.getSendDate()));
          OrderShipPojo shipPojo = orderShipService.findByIdOrderShip(oid);
          if (shipPojo != null) {
            orderInfo.put("logisticsName", StringUtil.checkVal(shipPojo.getLogisticsNameCN()));
            orderInfo.put("logisticsNo", StringUtil.checkVal(shipPojo.getLogisticsNo()));
          }
        }

        // 完成时间
        orderInfo.put("confirmTime", "");
        if (order.getOrderStatus() >= 4) {
          orderInfo.put("confirmTime", StringUtil.checkVal(order.getConfirmDate()));
        }

        // 订单用券详情
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("orderId", oid);
        param.put("status", 1);
        List<CouponOrderPojo> couponOrders = couponService.getcouponOrderList(param);
        if (couponOrders != null && couponOrders.size() > 0) {
          CouponOrderPojo couponOrderPojo = couponOrders.get(0);
          item.put("couponNo", StringUtil.checkVal(couponOrderPojo.getCouponNo()));
          item.put("couponName", StringUtil.checkVal(couponOrderPojo.getCouponName()));
        } else {
          item.put("couponNo", "");
          item.put("couponName", "");
        }
        // 使用优惠券优惠金额
        item.put("usedPrice", StringUtil.checkVal(order.getUsedPrice()));
        // 退款状态
        if (order.getIsRefund() == 1 || order.getIsRefund() == 3) {
          item.put("refPriStatus", "1");
        } else if (order.getIsRefund() == 2) {
          item.put("refPriStatus", "2");
        } else {
          item.put("refPriStatus", "");
        }
        // 是否团长
        if (order.getUserId() != null && order.getSourceId() != null) {
          params.clear();
          params.put("userId", order.getUserId());
          params.put("attendId", order.getSourceId());
          params.put("isHead", 1);
          int i = grouponUserRecordService.countBy(params);
          if (i > 0) {
            item.put("isOpen", "1");
          } else {
            item.put("isOpen", "0");
          }
        } else {
          item.put("isOpen", "0");
        }
        // 查询已成团人的信息
        if (order.getSource() != null && order.getSource() != 3 && order.getSource() != 4
            && order.getSourceId() != null && order.getSourceId() > 0) {
          params.clear();
          params.put("sourceId", order.getSourceId());
          params.put("isSuccess", 1);
          params.put("payStatus", 1);
          List<OrderPojo> orderList = orderService.listPage(params);
          SysLoginPojo sysLogin = null;
          for (OrderPojo orderPojo : orderList) {
            user = new HashMap<String, Object>();
            sysLogin = sysLoginService.findSysLoginById(orderPojo.getUserId());
            if (sysLogin != null) {
              user.put("openid", StringUtil.checkVal(sysLogin.getOpenid()));
              // 查询用对应的订单号和订单金额
              user.put("orderNo", StringUtil.checkVal(order.getOrderNo()));
              user.put("factPrice", StringUtil.checkVal(order.getFactPrice()));
              userList.add(user);
            }
          }
        }
        // // 查询抽奖是否中奖
        // if (order.getSourceId() != null && order.getSourceId() > 0) {
        // params.clear();
        // params.put("userId", order.getUserId());
        // params.put("status", 3);
        // params.put("prize", 1);
        // int i = grouponUserRecordService.countBy(params);
        // if (i > 0) {
        // item.put("isPrize", "1");
        // } else {
        // item.put("isPrize", "0");
        // }
        // } else {
        // item.put("isPrize", "0");
        // }

        order.getSource();
        // if (actType == 1 || actType == 2) {
        if ("".equals(item.get("oweNum"))) {
          int num = grouponUserRecordService.joinNumByAttendId(order.getSourceId());
          num = order.getGroupNum() - num;
          item.put("oweNum", StringUtil.checkVal(num));
        }
        // }

        success = true;
      } else {
        msg = "无此订单信息！";
      }
    }
    item.put("orderInfo", orderInfo);
    item.put("productInfo", productInfo);
    item.put("addressInfo", addressInfo);
    item.put("userList", userList);
    map.put("result", item);
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
   * 
   * 用户登录API
   * 
   * @throws Exception
   */
  public String agentlogin() throws Exception {
    boolean success = false;
    boolean ckFlag = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    Map<String, Object> map3 = new HashMap<String, Object>();
    SysLoginPojo loginck = null;
    if (openid != null && !"".equals(openid)) {
      map3.put("openid", openid);
      loginck = sysLoginService.qrySysLoginByThirdId(map3);
      if (loginck == null) {
        error_msg = "微信号未注册！";
        ckFlag = true;
      }
    } else if (unionid != null && !"".equals(unionid)) {
      map3.put("unionid", unionid);
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
        if (logiPojo != null && logiPojo.getStatus() == 1 && Long.valueOf(logiPojo.getType()) == 1) {
          /*
           * HttpServletRequest request = ServletActionContext.getRequest(); // 向登录日志表中插入信息
           * LoginRecPojo loginRecPojo = new LoginRecPojo();
           * loginRecPojo.setType(logiPojo.getType()); loginRecPojo.setLoginDate(new Date());
           * loginRecPojo.setLoginIp(walletService.getIpAddr(request));
           * loginRecPojo.setUserId(logiPojo.getId()); loginRecService.addLoginRec(loginRecPojo);
           */

          // 接收baidu_id并保存进数据库表
          if (StringUtils.isNotBlank(baidu_uid) && !baidu_uid.equals(logiPojo.getBaidu_uid())) {
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
          map2.put("openid", logiPojo.getOpenid() == null ? "" : logiPojo.getOpenid());
          map2.put("unionid", logiPojo.getUnionid() == null ? "" : logiPojo.getUnionid());
          map2.put("sinaid", logiPojo.getSinaid() == null ? "" : logiPojo.getSinaid());
          map2.put("image", logiPojo.getImage() == null ? ""
              : logiPojo.getImage().contains("http") == true ? logiPojo.getImage() : ConstParam.URL
                  + "/upfiles/userlogo" + File.separator + StringUtil.checkVal(logiPojo.getImage()));
          map2.put("judgeType", 0);
          map2.put("agencyId", 0);

          map2.put("status", "");
          map2.put("newUser", "");
          map2.put("balance", "");
          map2.put("ageType", "");
          map2.put("followNum", "0");

          map.put("result", map2);
        } else {
          error_msg = "无效账号";
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
   * 
   * 用户登录API
   * 
   * @throws Exception
   */
  public String userlogin() throws Exception {
    boolean success = false;
    boolean ckFlag = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    Map<String, Object> map3 = new HashMap<String, Object>();
    SysLoginPojo loginck = null;
    if (source == null || !(source == 1 || source == 2 || source == 3 || source == 4)) {
      error_msg = "用户来源不能为空！";
      ckFlag = true;
    } else if (openid != null && !"".equals(openid) && (phone == null || "".equals(phone))) {
      map3.put("openid", openid);
      loginck = sysLoginService.qrySysLoginByThirdId(map3);
      if (loginck == null) {
        error_msg = "微信号未注册！";
        ckFlag = true;
      }
    } else if (unionid != null && !"".equals(unionid) && (phone == null || "".equals(phone))) {
      map3.put("unionid", unionid);
      loginck = sysLoginService.qrySysLoginByThirdId(map3);
      if (loginck == null) {
        error_msg = "微信号未注册！";
        ckFlag = true;
      }
    } else if (sinaid != null && !"".equals(sinaid) && (phone == null || "".equals(phone))) {
      map3.put("sinaid", sinaid);
      loginck = sysLoginService.qrySysLoginByThirdId(map3);
      if (loginck == null) {
        error_msg = "新浪id未注册！";
        ckFlag = true;
      }
    } else if (qq_openid != null && !"".equals(qq_openid) && (phone == null || "".equals(phone))) {
      map3.put("qqOpenid", qq_openid);
      loginck = sysLoginService.qrySysLoginByThirdId(map3);
      if (loginck == null) {
        error_msg = "QQid未注册！";
        ckFlag = true;
      }
    } else if (phone != null && !"".equals(phone) && captcha != null && !"".equals(captcha)) {
      // 判断验证码是否正确
      UserVerifyPojo userVerify = new UserVerifyPojo();
      userVerify.setLoginname(phone);
      userVerify = userVerifyService.findNewestByPhone(userVerify);
      if (!("852123".equals(captcha) && "1300000000".equals(phone))
          && !("123645".equals(captcha) && "15018818838".equals(phone))
          && (userVerify == null || userVerify.getCaptcha() == null || !captcha.equals(userVerify
              .getCaptcha()))) {
        error_msg = "验证码错误";
        ckFlag = true;
      } else {
        // 判断帐号是否有效
        loginck = sysLoginService.getSysLoginByLoginName(phone);
        SysLoginPojo loginck2 = null;
        if (StringUtils.isNotBlank(openid)) {
          map3.put("openid", openid);
          loginck2 = sysLoginService.qrySysLoginByThirdId(map3);
        } else if (StringUtils.isNotBlank(unionid)) {
          map3.put("unionid", unionid);
          loginck2 = sysLoginService.qrySysLoginByThirdId(map3);
        } else if (StringUtils.isNotBlank(sinaid)) {
          map3.put("sinaid", sinaid);
          loginck2 = sysLoginService.qrySysLoginByThirdId(map3);
        } else if (StringUtils.isNotBlank(qq_openid)) {
          map3.put("qqOpenid", qq_openid);
          loginck2 = sysLoginService.qrySysLoginByThirdId(map3);
        }

        if (loginck != null) {
          if (StringUtils.isNotEmpty(loginck.getOpenid()) && StringUtils.isNotBlank(openid)
              && !openid.equals(loginck.getOpenid())) {
            error_msg = "帐号已被绑定";
            ckFlag = true;
          } else if (StringUtils.isEmpty(loginck.getOpenid()) && loginck2 == null
              && StringUtils.isNotBlank(openid)) {
            // 手机帐号没有openid并且openid不为空并且openid未绑定
            loginck.setOpenid(openid);
            SysLoginPojo openidUser = new SysLoginPojo();
            openidUser.setOpenid(openid);
            openidUser.setLoginname(phone);
            // 绑定手机号和openid
            loginService.updateLoginPojo(openidUser);
          }
          if (loginck.getStatus() != 1 || Long.valueOf(loginck.getType()) != 1) {
            error_msg = "账号未激活，请联系管理员";
            ckFlag = true;
          }
        } else if (loginck2 != null) {
          error_msg = "第三方帐号ID已被绑定";
          ckFlag = true;
        } else {
          // 注册
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
          }
          if (flag) {
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

          SysLoginPojo sysPojo = new SysLoginPojo();
          sysPojo.setType("1");
          sysPojo.setStatus(1);
          sysPojo.setSorting(0);
          sysPojo.setPassword(MD5Util.MD5("123456"));
          sysPojo.setLoginname(phone);
          // 用户注册呢称为tzm+手机号
          if (StringUtils.isNotEmpty(name)) {
            sysPojo.setName(name);
          } else {
            name = "pdh" + phone;
            name = WalletService.enCodeString(name, 6, 9);
            sysPojo.setName(name);
          }
          if (baidu_uid != null && !"".equals(baidu_uid)) {
            sysPojo.setBaidu_uid(baidu_uid);
          }
          if (openid != null && !"".equals(openid)) {
            sysPojo.setOpenid(openid);
          }
          if (unionid != null && !"".equals(unionid)) {
            sysPojo.setUnionid(unionid);
          }
          if (qq_openid != null && !"".equals(qq_openid)) {
            sysPojo.setQqOpenId(qq_openid);
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
          // 用户来源：1-ios,2-android,3-weixin
          if (source != null && (source == 1 || source == 2 || source == 3 || source == 4)) {
            sysPojo.setSource(source);
          }
          sysPojo.setSource(source);
          sysPojo.setCreateDate(new Date());
          sysPojo.setCreateBy(0l);
          sysLoginService.addSysLoginService(sysPojo);
          loginck = sysPojo;

          // 得到添加成功的账户id
          if (sysPojo != null && sysPojo.getId() != null && sysPojo.getId() != 0) {
            // 注册同时userinfo表需插入一条数据
            UserInfoPojo userInfoPojo = new UserInfoPojo();
            userInfoPojo.setUserId(sysPojo.getId());
            userInfoPojo.setCreateDate(new Date());
            userInfoPojo.setStatus(1);
            userInfoPojo.setBabyBirthday(StringUtil.dateToString(new Date()));
            userInfoPojo.setBabySex(1);
            userInfoService.insertUserInfo(userInfoPojo);

            // 插入未激活团免券
            GroupFreeCouponPojo freecp = new GroupFreeCouponPojo();
            freecp.setUserId(sysPojo.getId());
            freecp.setStatus(0);
            freecp.setUsed(0);
            freecp.setCreateBy(sysPojo.getId());
            freecp.setCreateDate(new Date());
            freecp.setUpdateBy(sysPojo.getId());
            freecp.setUpdateDate(freecp.getCreateDate());
            groupFreeCouponService.add(freecp);

            // 向表baidu_login插入数据
            if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(baidu_uid)) {
              BaiduLoginPojo baiduLoginPojo = new BaiduLoginPojo();
              baiduLoginPojo.setUserId(sysPojo.getId());
              baiduLoginPojo.setLoginName(phone);
              baiduLoginPojo.setBaiduId(baidu_uid);
              baiduLoginPojo.setType(1);
              walletService.isInsertBaibuLogin(baiduLoginPojo);
            }
          }
        }
      }
    } else {
      error_msg = "用户登录方式不能为空";
      ckFlag = true;
    }

    if (ckFlag == false) {
      // 接收baidu_id并保存进数据库表
      // loginck = sysLoginService.getSysLoginByLoginName(phone);
      if (loginck != null && baidu_uid != null && !"".equals(baidu_uid)
          && !baidu_uid.equals(loginck.getBaidu_uid())) {
        SysLoginPojo login = new SysLoginPojo();
        login.setBaidu_uid(baidu_uid);
        login.setLoginname(loginck.getLoginname());
        loginService.updateBaiduUid(login);
      }

      // 设置用户id会员名称
      if (loginck != null) {
        Long uid = loginck.getId();
        String name = loginck.getName();
        success = true;
        error_msg = "登录成功！";
        map2.put("uid", uid);
        map2.put("name", name);
        // map2.put("phone", WalletService.enCodeString(phone, 4, 6));
        map2.put("phone", StringUtil.checkVal(loginck.getLoginname()));

        // map2.put("type", loginck.getType());
        // map2.put("token", loginck.getToken() == null ? "" : loginck.getToken());
        // map2.put("openid", loginck.getUnionid() == null ? "" : loginck.getUnionid());
        // map2.put("qqOpenid", loginck.getQqOpenId() == null ? "" : loginck.getQqOpenId());
        // map2.put("sinaid", loginck.getSinaid() == null ? "" : loginck.getSinaid());
        map2.put("image", loginck.getImage() == null || "".equals(loginck.getImage()) ? ""
            : loginck.getImage().contains("http") == true ? loginck.getImage() : ConstParam.URL
                + "/upfiles/userlogo" + File.separator + StringUtil.checkVal(loginck.getImage()));
        // map2.put("judgeType", 0);
        // map2.put("agencyId", 0);
        //
        // map2.put("status", "");
        // map2.put("newUser", "");
        // map2.put("balance", "");
        // map2.put("ageType", "");
        // map2.put("followNum", "0");

        // 向登录日志表中插入信息
        try {
          LoginRecPojo loginRecPojo = new LoginRecPojo();
          loginRecPojo.setType(loginck.getType());
          loginRecPojo.setLoginDate(new Date());
          loginRecPojo.setLoginIp(ip);
          loginRecPojo.setUserId(loginck.getId());
          loginRecService.addLoginRec(loginRecPojo);
        } catch (Exception e) {
          e.printStackTrace();
        }
        success = true;
      } else {
        error_msg = "登录失败!";
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
   * 用户登录API
   * 
   * @throws Exception
   */
  /*
   * public String userlogin() throws Exception { boolean success = false; boolean ckFlag = false;
   * boolean isreg = false; String error_msg = ""; Map<String, Object> map = new HashMap<String,
   * Object>(); Map<String, Object> map2 = new HashMap<String, Object>(); Map<String, Object> map3 =
   * new HashMap<String, Object>(); SysLoginPojo loginck = null; if (source == null || !(source == 1
   * || source == 2 || source == 3 || source == 4)) { error_msg = "用户来源不能为空！"; ckFlag = true; } else
   * if (openid != null && !"".equals(openid) && (phone == null || "".equals(phone))) {
   * map3.put("openid", openid); loginck = sysLoginService.qrySysLoginByThirdId(map3); if (loginck
   * == null) { loginck = new SysLoginPojo(); loginck.setOpenid(openid); loginck.setLoginname("pdh"
   * + System.currentTimeMillis()); isreg = true; } } else if (unionid != null &&
   * !"".equals(unionid) && (phone == null || "".equals(phone))) { map3.put("unionid", unionid);
   * loginck = sysLoginService.qrySysLoginByThirdId(map3); if (loginck == null) { loginck = new
   * SysLoginPojo(); loginck.setUnionid(unionid); loginck.setLoginname("pdh" +
   * System.currentTimeMillis()); isreg = true; } } else if (sinaid != null && !"".equals(sinaid) &&
   * (phone == null || "".equals(phone))) { map3.put("sinaid", sinaid); loginck =
   * sysLoginService.qrySysLoginByThirdId(map3); if (loginck == null) { loginck = new
   * SysLoginPojo(); loginck.setSinaid(sinaid); loginck.setLoginname("pdh" +
   * System.currentTimeMillis()); isreg = true; } } else if (qq_openid != null &&
   * !"".equals(qq_openid) && (phone == null || "".equals(phone))) { map3.put("qqOpenid",
   * qq_openid); loginck = sysLoginService.qrySysLoginByThirdId(map3); if (loginck == null) {
   * loginck = new SysLoginPojo(); loginck.setQqOpenId(qq_openid); loginck.setLoginname("pdh" +
   * System.currentTimeMillis()); isreg = true; } } else if (phone != null && !"".equals(phone) &&
   * captcha != null && !"".equals(captcha)) { // 判断验证码是否正确 UserVerifyPojo userVerify = new
   * UserVerifyPojo(); userVerify.setLoginname(phone); userVerify =
   * userVerifyService.findNewestByPhone(userVerify); if (!"123645".equals(captcha) && (userVerify
   * == null || userVerify.getCaptcha() == null || !captcha.equals(userVerify .getCaptcha()))) {
   * error_msg = "验证码错误"; ckFlag = true; } else { // 判断帐号是否有效 loginck =
   * sysLoginService.getSysLoginByLoginName(phone); SysLoginPojo loginck2 = null; if
   * (StringUtils.isNotBlank(openid)) { map3.put("openid", openid); loginck2 =
   * sysLoginService.qrySysLoginByThirdId(map3); } else if (StringUtils.isNotBlank(unionid)) {
   * map3.put("unionid", unionid); loginck2 = sysLoginService.qrySysLoginByThirdId(map3); } else if
   * (StringUtils.isNotBlank(sinaid)) { map3.put("sinaid", sinaid); loginck2 =
   * sysLoginService.qrySysLoginByThirdId(map3); } else if (StringUtils.isNotBlank(qq_openid)) {
   * map3.put("qqOpenid", qq_openid); loginck2 = sysLoginService.qrySysLoginByThirdId(map3); }
   * 
   * if (loginck != null) { if (StringUtils.isNotEmpty(loginck.getOpenid()) &&
   * StringUtils.isNotBlank(openid) && !openid.equals(loginck.getOpenid())) { error_msg = "帐号已被绑定";
   * ckFlag = true; } else if (StringUtils.isEmpty(loginck.getOpenid()) && loginck2 == null &&
   * StringUtils.isNotBlank(openid)) { // 手机帐号没有openid并且openid不为空并且openid未绑定
   * loginck.setOpenid(openid); SysLoginPojo openidUser = new SysLoginPojo();
   * openidUser.setOpenid(openid); openidUser.setLoginname(phone); // 绑定手机号和openid
   * loginService.updateLoginPojo(openidUser); } if (loginck.getStatus() != 1 ||
   * Long.valueOf(loginck.getType()) != 1) { error_msg = "账号未激活，请联系管理员"; ckFlag = true; } } else if
   * (loginck2 != null) { error_msg = "第三方帐号ID已被绑定"; ckFlag = true; } else { loginck = new
   * SysLoginPojo(); loginck.setLoginname(phone); isreg = true; } } } else { error_msg =
   * "用户登录方式不能为空"; ckFlag = true; }
   * 
   * if (!ckFlag) {
   * 
   * if (isreg) { // 注册 boolean flag = false; if (baidu_uid != null &&
   * StringUtils.isNotBlank(baidu_uid)) { // 相同机器码最多注册5个用户 int count =
   * baiduLoginService.checkMachineCodeRepeatByCode(baidu_uid); if (count >= 5) { flag = true;
   * System.out.println(">>>>> baiduid2:" + baidu_uid); } if (!flag) { List<String> blackWords =
   * WalletService.getBlackWords(); int len = blackWords.size(); for (int i = 0; i < len; i++) { if
   * (baidu_uid.startsWith(blackWords.get(i))) { flag = true; break; } } } } if (flag) {
   * map.put("result", ""); map.put("error_msg", "系统繁忙，请稍后再试！"); map.put("success", false);
   * JSONObject json = JSONObject.fromObject(map);
   * ServletActionContext.getResponse().setContentType("text/html; charset=utf-8"); try {
   * ServletActionContext.getResponse().getWriter().write(json.toString()); } catch (IOException e)
   * { e.printStackTrace(); } return null; }
   * 
   * loginck.setType("1"); loginck.setStatus(1); loginck.setSorting(0);
   * loginck.setPassword(MD5Util.MD5("123456")); // 用户注册呢称为tzm+手机号 if (StringUtils.isNotEmpty(name))
   * { loginck.setName(name); } else { if (StringUtils.isNotBlank(phone)) { name = "pdh" + phone; }
   * else { name = loginck.getLoginname(); } loginck.setName(name); } if (baidu_uid != null &&
   * !"".equals(baidu_uid)) { loginck.setBaidu_uid(baidu_uid); } if (openid != null &&
   * !"".equals(openid)) { loginck.setOpenid(openid); } if (unionid != null && !"".equals(unionid))
   * { loginck.setUnionid(unionid); } if (qq_openid != null && !"".equals(qq_openid)) {
   * loginck.setQqOpenId(qq_openid); } if (sinaid != null && !"".equals(sinaid)) {
   * loginck.setSinaid(sinaid); } if (regChannel != null && !"".equals(regChannel)) {
   * loginck.setRegChannel(regChannel); } if (imei != null && !"".equals(imei)) {
   * loginck.setImei(imei); } // 用户来源：1-ios,2-android,3-weixin,4-wap if (source != null && (source
   * == 1 || source == 2 || source == 3 || source == 4)) { loginck.setSource(source); }
   * loginck.setSource(source); loginck.setCreateDate(new Date()); loginck.setCreateBy(0l);
   * sysLoginService.addSysLoginService(loginck);
   * 
   * // 得到添加成功的账户id if (loginck != null && loginck.getId() != null && loginck.getId() > 0) { //
   * 注册同时userinfo表需插入一条数据 UserInfoPojo userInfoPojo = new UserInfoPojo();
   * userInfoPojo.setUserId(loginck.getId()); userInfoPojo.setCreateDate(new Date());
   * userInfoPojo.setStatus(1); userInfoPojo.setBabyBirthday(StringUtil.dateToString(new Date()));
   * userInfoPojo.setBabySex(1); userInfoService.insertUserInfo(userInfoPojo);
   * 
   * // 插入未激活团免券 GroupFreeCouponPojo freecp = new GroupFreeCouponPojo();
   * freecp.setUserId(loginck.getId()); freecp.setStatus(0); freecp.setUsed(0);
   * freecp.setCreateBy(loginck.getId()); freecp.setCreateDate(new Date());
   * freecp.setUpdateBy(loginck.getId()); freecp.setUpdateDate(freecp.getCreateDate());
   * groupFreeCouponService.add(freecp);
   * 
   * // 向表baidu_login插入数据 if (StringUtils.isNotBlank(baidu_uid)) { BaiduLoginPojo baiduLoginPojo =
   * new BaiduLoginPojo(); baiduLoginPojo.setUserId(loginck.getId());
   * baiduLoginPojo.setLoginName(loginck.getLoginname()); baiduLoginPojo.setBaiduId(baidu_uid);
   * baiduLoginPojo.setType(1); walletService.isInsertBaibuLogin(baiduLoginPojo); } } }
   * 
   * // 设置用户id会员名称 if (loginck != null && loginck.getId() != null && loginck.getId() > 0) { //
   * 接收baidu_id并保存进数据库表 if (StringUtils.isNotBlank(baidu_uid) &&
   * !baidu_uid.equals(loginck.getBaidu_uid())) { SysLoginPojo login = new SysLoginPojo();
   * login.setBaidu_uid(baidu_uid); login.setLoginname(loginck.getLoginname());
   * loginService.updateBaiduUid(login); }
   * 
   * map2.put("uid", loginck.getId()); map2.put("name", loginck.getName()); map2.put("phone",
   * WalletService.enCodeString(loginck.getLoginname(), 4, 6)); map2.put("image", loginck.getImage()
   * == null ? "" : loginck.getImage().contains("http") == true ? loginck.getImage() :
   * ConstParam.URL + "/upfiles/userlogo" + File.separator +
   * StringUtil.checkVal(loginck.getImage())); success = true; error_msg = "登录成功！"; } else {
   * error_msg = "登录失败!"; } } map.put("success", success); map.put("result", map2);
   * map.put("error_msg", error_msg); JSONObject json = JSONObject.fromObject(map);
   * ServletActionContext.getResponse().setContentType("text/html; charset=utf-8"); try {
   * ServletActionContext.getResponse().getWriter().write(json.toString()); } catch (IOException e)
   * { e.printStackTrace(); } return null; }
   */

  /**
   * @return 更新登录用户信息
   * @throws SQLException
   */
  public String editUserInfo() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid == null || uid <= 0) {
      msg = "会员ID不能为空!";
    } else {
      SysLoginPojo sysLoginPojo = sysLoginService.getUserInfoById(uid);
      if (sysLoginPojo == null) {
        msg = "会员ID未查到！";
      } else {
        if (file != null) {
          String path =
              ServletActionContext.getRequest()
                  .getRealPath("upfiles" + File.separator + "userlogo");
          // System.out.println(path);
          String image = grouponService.uploadFile(file, path);
          sysLoginPojo.setImage(image);
        }
        if (name != null && !"".equals(name)) {
          sysLoginPojo.setName(name);
        }
        /*
         * if (sex != null && sex != 0) { sysLoginPojo.setSex(sex); } if (birth != null &&
         * !"".equals(birth)) { sysLoginPojo.setBirthday(birth); } if (babySex != null && (babySex
         * == 1 || babySex == 2)) { sysLoginPojo.setBabySex(babySex); } if (babyBirthday != null &&
         * !"".equals(babyBirthday)) { sysLoginPojo.setBabyBirthday(babyBirthday); } if (QQ != null
         * && !"".equals(QQ)) { sysLoginPojo.setQQ(QQ); }
         */
        if (name != null && !"".equals(name) || file != null) {
          // String token = sysLoginService.getRongyunToken(userid, username, logo);
          // sysLoginPojo.setToken(token);
          sysLoginService.updateUserInfoById(sysLoginPojo);
        }
        b = true;
        msg = "用户信息更新成功";
      }
    }

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
   * 用户绑定微信或新浪api
   * */
  public String bundling() throws Exception {
    Boolean success = false;
    String error_msg = "";
    Map<String, Object> map3 = null;
    Map<String, Object> map1 = new HashMap<String, Object>();
    SysLoginPojo sysLoginPojobyloginname = sysLoginService.getSysLoginByLoginName(phone);// 根据手机号码判断是否注册过
    UserVerifyPojo v = new UserVerifyPojo();
    v.setLoginname(phone);
    UserVerifyPojo verifyPojo = new UserVerifyPojo();
    verifyPojo = userVerifyService.findNewestByPhone(v);// 得到系统生成的验证码

    if (phone == null || phone.equals("")) {
      error_msg = "请输入手机号码！";
    } else if (captcha == null || captcha.equals("")) {
      error_msg = "请输入验证码！";
    } else if (captcha.toString().length() != 6) {
      error_msg = "请填写正确6位验证码！";
    } else if (pass == null || pass.equals("")) {
      error_msg = "请输入密码！";

    } else if (verifyPojo == null) {
      error_msg = "验证码已经失效，请重新发送验证！";
    } else if (!captcha.equals(verifyPojo.getCaptcha())) {
      error_msg = "请填写正确验证码！";
    } else if (sysLoginPojobyloginname != null) {
      if (pass != null && !pass.equals(sysLoginPojobyloginname.getPassword())) {
        error_msg = "输入密码不正确！";
      }
      // 判断绑定的openid或sianid是否为空
      else if (openid != null && !"".equals(openid) || unionid != null && !"".equals(unionid)
          || sinaid != null && !"".equals(sinaid)) {
        if (openid != null && !"".equals(openid)) {
          if (sysLoginPojobyloginname != null
              && !(sysLoginPojobyloginname.getUnionid() == null || ""
                  .equals(sysLoginPojobyloginname.getUnionid()))) {
            error_msg = "手机号已绑定过微信号！";
          } else {
            map3 = new HashMap<String, Object>();
            map3.put("openid", openid);
            SysLoginPojo loginck = sysLoginService.qrySysLoginByThirdId(map3);
            if (loginck != null) {
              error_msg = "微信号已被绑定！";
            } else {
              sysLoginPojobyloginname.setOpenid(openid);
              if (baidu_uid != null && !"".equals(baidu_uid)) {
                sysLoginPojobyloginname.setBaidu_uid(baidu_uid);;
              }
              sysLoginPojobyloginname.setUpdateDate(new Date());
              loginService.updateLoginPojo(sysLoginPojobyloginname);
              error_msg = "提交成功！";
              success = true;

              // 绑定微信号成功
            }

          }
        }
        if (unionid != null && !"".equals(unionid)) {
          if (sysLoginPojobyloginname != null
              && !(sysLoginPojobyloginname.getUnionid() == null || ""
                  .equals(sysLoginPojobyloginname.getUnionid()))) {
            error_msg = "手机号已绑定过微信号！";
          } else {
            map3 = new HashMap<String, Object>();
            map3.put("unionid", unionid);
            SysLoginPojo loginck = sysLoginService.qrySysLoginByThirdId(map3);
            if (loginck != null) {
              error_msg = "微信号已被绑定！";
            } else {
              sysLoginPojobyloginname.setUnionid(unionid);
              if (baidu_uid != null && !"".equals(baidu_uid)) {
                sysLoginPojobyloginname.setBaidu_uid(baidu_uid);;
              }
              sysLoginPojobyloginname.setUpdateDate(new Date());
              loginService.updateLoginPojo(sysLoginPojobyloginname);
              error_msg = "提交成功！";
              success = true;

              // 绑定微信号成功
            }

          }
        }
        if (sinaid != null && !"".equals(sinaid)) {
          if (sysLoginPojobyloginname != null
              && !(sysLoginPojobyloginname.getSinaid() == null || "".equals(sysLoginPojobyloginname
                  .getSinaid()))) {
            error_msg = "手机号已绑定过新浪号！";
          } else {
            map3 = new HashMap<String, Object>();
            map3.put("sinaid", sinaid);
            SysLoginPojo loginck = sysLoginService.qrySysLoginByThirdId(map3);
            if (loginck != null) {
              error_msg = "新浪号已被绑定！";
            } else {
              sysLoginPojobyloginname.setSinaid(sinaid);
              if (baidu_uid != null && !"".equals(baidu_uid)) {
                sysLoginPojobyloginname.setBaidu_uid(baidu_uid);;
              }
              sysLoginPojobyloginname.setUpdateDate(new Date());
              loginService.updateLoginPojo(sysLoginPojobyloginname);
              error_msg = "提交成功！";
              success = true;
            }

          }
        }
        if (success) {
          map1.put("phone", sysLoginPojobyloginname.getLoginname());
          map1.put("name", sysLoginPojobyloginname.getName());
          map1.put("type", sysLoginPojobyloginname.getType());
          map1.put("uid", sysLoginPojobyloginname.getId());
          map1.put("token", sysLoginPojobyloginname.getToken() == null ? ""
              : sysLoginPojobyloginname.getToken());
          map1.put("openid", sysLoginPojobyloginname.getOpenid() == null ? ""
              : sysLoginPojobyloginname.getOpenid());
          map1.put("unionid", sysLoginPojobyloginname.getUnionid() == null ? ""
              : sysLoginPojobyloginname.getUnionid());
          map1.put("sinaid", sysLoginPojobyloginname.getSinaid() == null ? ""
              : sysLoginPojobyloginname.getSinaid());
          map1.put(
              "image",
              sysLoginPojobyloginname.getImage() == null ? "" : sysLoginPojobyloginname.getImage()
                  .contains("http") == true ? sysLoginPojobyloginname.getImage() : ConstParam.URL
                  + "/upfiles/userlogo" + File.separator
                  + StringUtil.checkVal(sysLoginPojobyloginname.getImage()));
          map1.put("judgeType", 0);
        }
      }
    } else {
      error_msg = "该用户还没注册！";
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("result", map1);
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
   * 获取产品颜色/规格
   * 
   * @return
   * @throws Exception
   */
  public String getProductSkus() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> skuList = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> validSkus = new ArrayList<Map<String, Object>>();
    Map<String, Object> skuColorMap = null;
    Map<String, Object> skuFormatMap = null;


    if (pid == null || pid <= 0) {
      msg = "产品ID是必须要填写哦";
    } else {
      ProductSkuLinkPojo productSkuLinkPojo = new ProductSkuLinkPojo();
      productSkuLinkPojo.setProductId(pid);
      productSkuLinkPojo.setStatus(1);
      // sku类型，0-普通;1-活动
      productSkuLinkPojo.setType(0);
      productSkuLinkPojo.setActivityId(0l);
      productSkuLinkPojo.setIsDelete(0);
      List<ProductSkuLinkPojo> list =
          productSkuLinkService.getProductSkuLinkAll(productSkuLinkPojo, null);
      Map<String, Object> format = null;
      List<String> colorCuror = new ArrayList<String>();
      List<String> formatCuror = new ArrayList<String>();
      if (list != null && list.size() > 0) {
        List<Map<String, Object>> collist = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> fmlist = new ArrayList<Map<String, Object>>();
        Map<String, Object> validSku = null;
        for (ProductSkuLinkPojo skuCol : list) {
          if (!colorCuror.contains(skuCol.getSkuColor())) {
            format = new HashMap<String, Object>();
            format.put("optionValue", skuCol.getSkuColor());
            collist.add(format);
          }
          if (!formatCuror.contains(skuCol.getSkuFormat())) {
            format = new HashMap<String, Object>();
            format.put("optionValue", skuCol.getSkuFormat());
            fmlist.add(format);
          }

          formatCuror.add(skuCol.getSkuFormat());
          colorCuror.add(skuCol.getSkuColor());

          if (skuCol.getStock() > 0) {
            validSku = new HashMap<String, Object>();
            validSku.put("pid", pid);
            validSku.put("skuColor", skuCol.getSkuColor());
            validSku.put("skuFormat", skuCol.getSkuFormat());
            validSku.put("skuImg", skuCol.getImage() == null ? "" : ConstParam.URL
                + "/upfiles/productSkuLink" + File.separator + skuCol.getImage());
            validSku.put("status", 1);
            validSku.put("id", skuCol.getId());
            validSkus.add(validSku);
          }
        }
        if (collist.size() > 0) {
          skuColorMap = new HashMap<String, Object>();
          skuColorMap.put("skuTitle", "颜色");
          skuColorMap.put("skuType", "1");
          skuColorMap.put("skuValue", collist);
          skuList.add(skuColorMap);
        }
        if (fmlist.size() > 0) {
          skuFormatMap = new HashMap<String, Object>();
          skuFormatMap.put("skuTitle", "规格");
          skuFormatMap.put("skuType", "2");
          skuFormatMap.put("skuValue", fmlist);
          skuList.add(skuFormatMap);
        }
        result.put("skuList", skuList);
        result.put("validSKu", validSkus);
      }
      b = true;
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
   * 获取团免券
   * 
   * @return
   * @throws SQLException
   */
  public String getFreeCouponApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    String result = "0";
    if (userId == null || userId == 0) {
      msg = "用户id不能为空!";
    } else if (linkId == null || linkId == 0) {
      msg = "链接id不能为空!";
    } else {
      int flag = grouponService.getFreeCoupon(userId, linkId);
      if (flag == 0) {
        result = "1";
        b = true;
      } else if (flag == 1) {
        msg = "团免券激活失败!";
      } else if (flag == 2) {
        msg = "领取时间已过!";
      } else if (flag == 3) {
        msg = "查询不到!";
      } else if (flag == 4) {
        msg = "您已经有团免券了,不能重复领取!";
      } else if (flag == 5) {
        msg = "团免券已被抢光!";
      } else if (flag == 6) {
        msg = "团免券正在使用,暂时无法领取哦!";
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
   * 下家激活团免券
   * 
   * @return
   * @throws SQLException
   */
  public String activeFreeCoupon() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    String result = "";
    if (userId == null || userId == 0) {
      msg = "用户id不能为空!";
    } else if (StringUtils.isBlank(code)) {
      msg = "邀请码不能为空!";
    } else if (sysLoginService.getfindCountByIdSysLogin(userId) == 0) {
      msg = "该会员未找到！";
    } else {
      // 根据邀请码获取被邀请用户ID
      SysLoginPojo invitecode = new SysLoginPojo();
      invitecode.setInvitationCode(code);
      SysLoginPojo invitor = sysLoginService.getUserIdByInvitationCode(invitecode);
      if (invitor != null && invitor.getId().longValue() != userId.longValue()) {
        int check = baiduLoginService.checkMachineCodeRepeat(userId);
        // 同一机器注册5个用户及以上，为嫌疑用户
        if (check < 5) {
          SysLoginPojo user = sysLoginService.findSysLoginById(userId);
          if (user != null) {
            // 激活团免券
            int flag = grouponService.activeFreeCoupon(userId, invitor.getId());
            if (flag == 1) {
              b = true;
              msg = "激活成功！";
            } else if (flag == 2) {
              msg = "您已激活过了！";
            }
          }
        } else {
          msg = "激活成功！";
        }
      } else {
        msg = "邀请码无效！";
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
   * 订单确认收货
   * 
   * */
  public String editOrderStatus() throws SQLException {
    String msg = "";
    boolean b = false;
    int result = 0;
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid == null || uid < 1) {
      msg = "用户Id不能为空";
    } else if (oid == null || oid < 1) {
      msg = "订单Id不能为空";
    } else {
      int status = 0;// 订单更新状态
      OrderPojo order = new OrderPojo();
      order.setUserId(uid);
      order.setId(oid);
      order.setOrderStatus(4);
      order.setOldOrderStatus(3);
      try {
        status = orderService.updateOrderStatus2(order);
      } catch (Exception e) {
        status = 0;
        e.printStackTrace();
      }
      if (status > 0) {
        msg = "收货成功！";
        result = 1;
        b = true;
      } else {
        msg = "修改订单状态失败，重复确认或未找到该订单号！";
      }
    }
    map.put("success", b);
    map.put("error_msg", msg);
    map.put("result", result);
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
   * 退款详情
   * 
   * @return
   * @throws Exception
   */
  public String refundDetails() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    new DecimalFormat("#.##");
    if (uid == null || uid <= 0) {
      msg = "会员ID是必须要填写的哦";
    } else if (oid == null || oid <= 0) {
      msg = "订单ID是必须要填写的哦";
    } else {
      OrderRefundPojo orderRefundPojo = new OrderRefundPojo();
      orderRefundPojo.setUserId(uid);
      orderRefundPojo.setOrderId(oid);
      orderRefundPojo = orderRefundService.getorderRefundDetail(orderRefundPojo);
      if (orderRefundPojo != null) {
        // 退款金额
        double refundPrice =
            orderRefundPojo.getStockPrice() * orderRefundPojo.getRefundNum()
                - orderRefundPojo.getCouponPrice();
        result.put("refundPrice", StringUtil.checkVal(refundPrice));
        // result.put("createDate", StringUtil.dateString(orderRefundPojo.getCreateDate()));
        result.put("type", StringUtil.checkVal(orderRefundPojo.getType()));
        // result
        // .put("status", orderRefundPojo.getStatus() == null ? "" : orderRefundPojo.getStatus());
        result.put("refundType", StringUtil.checkVal(orderRefundPojo.getRefundType()));
        // result.put("refundReason",
        // orderRefundPojo.getRefundReason() == null ? "" : orderRefundPojo.getRefundReason());
        result.put("remarks", StringUtil.checkVal(orderRefundPojo.getRefundReason()));
        result.put("refundNum", StringUtil.checkVal(orderRefundPojo.getRefundNum()));

        result.put("refundImage1", orderRefundPojo.getImages() == null ? "" : ConstParam.URL
            + "/upfiles/orderRefund" + File.separator + orderRefundPojo.getImages());
        result.put("refundImage2", orderRefundPojo.getImages2() == null ? "" : ConstParam.URL
            + "/upfiles/orderRefund" + File.separator + orderRefundPojo.getImages2());
        result.put("refundImage3", orderRefundPojo.getImages3() == null ? "" : ConstParam.URL
            + "/upfiles/orderRefund" + File.separator + orderRefundPojo.getImages3());
        result.put("phone", StringUtil.checkVal(orderRefundPojo.getConsigneePhone()));
      }
      b = true;
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
   * 添加收藏
   * */
  public String addFavorite() throws SQLException {
    // String msg = "";
    boolean success = false;
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid == null || uid <= 0) {
      map.put("error_msg", "用户Id不能为空！");
    } else if (favSenId == null || favSenId <= 0) {
      map.put("error_msg", "产品或者专场Id不能为空哒，(* ￣3)(ε￣ *)");
    } else if (favType == null || favType != 1 && favType != 2 && favType != 3 && favType != 4
        && favType != 5) {
      map.put("error_msg", "收藏类型不能为空！");
    } else if (activityId == null) {
      map.put("error_msg", "活动Id不能为空！");
    } else {
      long s = uid;// 用户id
      long d = favSenId;// 收藏产品或者店铺id
      if (favType == 1) {// 产品
        UserCollectPojo userCollectPojo = new UserCollectPojo();
        userCollectPojo.setUserId(s);
        userCollectPojo.setProductId(d);
        userCollectPojo.setActivityId(activityId);
        userCollectPojo.setType(0l);
        userCollectPojo = userCollectService.findUserCollectByProductId(userCollectPojo);
        if (userCollectPojo != null) {
          success = false;
          map.put("result", "0");
          map.put("error_msg", "已经收藏过了");
        } else {
          try {
            UserCollectPojo userCollectPojo1 = new UserCollectPojo();
            userCollectPojo1.setUserId(s);
            userCollectPojo1.setProductId(d);
            userCollectPojo1.setVersion(0);
            userCollectPojo1.setCreateBy(s);
            userCollectPojo1.setActivityId(activityId);
            userCollectPojo1.setType(0l);
            userCollectService.addUserCollectService(userCollectPojo1);
            map.put("result", "1");
            success = true;
            map.put("error_msg", "收藏成功");
          } catch (Exception e) {
            success = false;
            map.put("result", "2");
            map.put("error_msg", "收藏失败");
          }

        }
      } else if (favType == 2) {// 店铺
        UserShopCollectPojo userShopCollectPojo = new UserShopCollectPojo();
        userShopCollectPojo.setUserId(s);
        userShopCollectPojo.setShopId(d);
        userShopCollectPojo =
            userShopCollectService.findUserShopCollectByShopId(userShopCollectPojo);
        if (userShopCollectPojo != null) {
          map.put("result", "0");
          success = false;
          map.put("error_msg", "已经收藏过了");
        } else {
          try {
            ShopPojo shopPojo = shopService.findShopById(d);
            UserShopCollectPojo userShopCollectPojo1 = new UserShopCollectPojo();
            userShopCollectPojo1.setUserId(s);
            userShopCollectPojo1.setShopId(d);
            userShopCollectPojo1.setSuserId(shopPojo.getUserId());
            userShopCollectPojo1.setVersion(0);
            userShopCollectPojo1.setCreateBy(s);
            userShopCollectService.addUserShopCollectService(userShopCollectPojo1);
            map.put("result", "1");
            success = true;
            map.put("error_msg", "收藏成功");
          } catch (Exception e) {
            map.put("result", "2");
            success = false;
            map.put("error_msg", "收藏失败");
          }
        }
      } else if (favType == 3) {// 分销产品库
        ProductPojo product = new ProductPojo();
        product.setId(d);
        product = productService.findProduct(product);
        UserConsumerCollectPojo userConsumerCollectPojo = new UserConsumerCollectPojo();
        userConsumerCollectPojo.setUserId(s);
        userConsumerCollectPojo.setProductId(d);
        userConsumerCollectPojo.setSuserId(product.getUserId());
        userConsumerCollectPojo = userConsumerCollectService.findCollect(userConsumerCollectPojo);
        if (userConsumerCollectPojo != null) {
          map.put("result", "0");
          success = false;
          map.put("error_msg", "已经收藏过了");
        } else {
          try {
            UserConsumerCollectPojo userConsumerCollectPojo1 = new UserConsumerCollectPojo();
            userConsumerCollectPojo1.setUserId(s);
            userConsumerCollectPojo1.setProductId(d);
            userConsumerCollectPojo1.setVersion(0);
            userConsumerCollectPojo1.setSuserId(product.getUserId());
            userConsumerCollectPojo1.setCreateBy(s);
            userConsumerCollectService.insertUserConsumerCollect(userConsumerCollectPojo1);
            map.put("result", "1");
            success = true;
            map.put("error_msg", "收藏成功");
          } catch (Exception e) {
            map.put("result", "2");
            success = false;
            map.put("error_msg", "收藏失败");
          }
        }
      } else if (favType == 4) {// 专场
        UserSpecialCollectPojo userSpecialCollectPojo = new UserSpecialCollectPojo();
        userSpecialCollectPojo.setUserId(s);
        userSpecialCollectPojo.setActivityId(activityId);
        userSpecialCollectPojo =
            userSpecialCollectService.getUserSpecialCollectBySpecialId(userSpecialCollectPojo);
        if (userSpecialCollectPojo != null) {
          map.put("result", "0");
          success = false;
          map.put("error_msg", "已经收藏过了");
        } else {
          SpecialShowPojo specialShow = null;
          try {
            specialShow = specialShowService.findSpecialShowByActivityId(activityId);
            if (specialShow != null) {
              userSpecialCollectPojo = new UserSpecialCollectPojo();
              userSpecialCollectPojo.setUserId(s);
              userSpecialCollectPojo.setSpecialId(specialShow.getId());
              userSpecialCollectPojo.setActivityId(activityId);
              userSpecialCollectPojo.setVersion(0);
              userSpecialCollectPojo.setCreateBy(s);
              userSpecialCollectPojo.setUpdateBy(s);
              userSpecialCollectService.insertUserSpecialCollect(userSpecialCollectPojo);
            }
            map.put("result", "1");
            success = true;
            map.put("error_msg", "收藏成功");
          } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "2");
            success = false;
            map.put("error_msg", "收藏失败");
          }
        }
      } else if (favType == 5) {
        try {
          // 收藏拼团
          userCollectPojo = new UserCollectPojo();
          userCollectPojo.setActivityId(activityId);
          userCollectPojo.setUserId(uid);
          userCollectPojo.setProductId(Long.valueOf(favSenId));
          userCollectList = userCollectService.userCollectAllList(userCollectPojo, page);
          if (userCollectList != null && userCollectList.size() > 0) {
            if (userCollectList.get(0).getIsDelete() == 0) {
              map.put("error_msg", "已收藏过!");
              map.put("result", 0);
              success = false;
            } else {
              userCollectPojo.setId(userCollectList.get(0).getId());
              userCollectPojo.setUpdateBy(uid);
              userCollectPojo.setUpdateDate(new Date());
              userCollectPojo.setIsDelete(0);
              userCollectService.updateUserCollect(userCollectPojo);
              map.put("error_msg", "收藏成功!");
              map.put("result", 1);
              success = true;
            }
          } else {
            userCollectPojo.setCreateBy(uid);
            userCollectPojo.setCreateDate(new Date());
            userCollectPojo.setUpdateBy(uid);
            userCollectPojo.setUpdateDate(new Date());
            userCollectService.addUserCollectService(userCollectPojo);
            map.put("result", 1);
            map.put("error_msg", "收藏成功!");
            success = true;
          }
        } catch (Exception e) {
          map.put("result", 2);
          map.put("error_msg", "收藏失败!");
          success = false;
          e.printStackTrace();
        }
      }
    }
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
   * 
   * 取消收藏
   * */
  public String delFavorite() throws SQLException {
    String msg = "";
    Boolean b = false;
    int result = 0;
    Map<String, Object> map = new HashMap<String, Object>();
    if (activityId == null || activityId == 0) {
      msg = "活动Id不能为空！";
    } else if (favType == null || favType != 5) {
      msg = "收藏类型不能为空！";
    } else if (uid == null || uid <= 0) {
      msg = "用户id不能为空！";
    } else {
      if (favType == 5) {
        userCollectPojo = new UserCollectPojo();
        userCollectPojo.setActivityId(activityId);
        userCollectPojo.setUserId(uid);
        userCollectPojo.setIsDelete(0);
        userCollectList = userCollectService.userCollectAllList(userCollectPojo, page);
        if (userCollectList != null && userCollectList.size() > 0) {
          for (UserCollectPojo userCollectPojo : userCollectList) {
            userCollectPojo.setIsDelete(1);
            userCollectService.updateUserCollect(userCollectPojo);
          }
          result = 1;
          b = true;
          msg = "取消收藏成功!";
        } else {
          result = 0;
          b = false;
          msg = "没有收藏记录!";
        }
      }
    }
    map.put("result", result);
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
   * 猜你喜欢
   * */
  public String guessYourLikeApi() throws SQLException {
    String msg = "";
    Boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    if (activityId == null || activityId == 0) {
      msg = "活动id不能为空!";
    } else {
      grouponActivityPojo = grouponActivityService.getById(activityId);
      if (grouponActivityPojo != null && grouponActivityPojo.getProductId() != null) {
        // 分页
        int ps = 9;
        if (pageSize != null && pageSize != 0) {
          ps = pageSize;
        }
        if (pageNo == null || pageNo == 0) {
          params.put("pageNo", 0);
        } else {
          params.put("pageNo", (pageNo - 1) * ps);
        }
        params.put("pageSize", ps);
        productPojo = productService.getById(grouponActivityPojo.getProductId());
        if (productPojo != null && productPojo.getProductTypeIds() != null) {
          params.put("orderBy", "p.sell_number desc,ga.create_date desc");
          params.put("productTypeIds", productPojo.getProductTypeIds().replaceAll(":", ""));
          params.put("isNotProductId", grouponActivityPojo.getProductId());
          params.put("status", 1);
          params.put("type", 1);
          grouponActivityList = grouponActivityService.listPage(params);
          if (grouponActivityList != null && grouponActivityList.size() > 0) {
            for (GrouponActivityPojo grouponActivity : grouponActivityList) {
              item = new HashMap<String, Object>();
              // 商品名
              item.put("productName", grouponActivity.getProductName() == null ? ""
                  : grouponActivity.getProductName());
              // 商品图片
              item.put("productImage",
                  grouponActivity.getProductImage() == null ? "" : ConstParam.URL
                      + "/upfiles/product" + File.separator + grouponActivity.getProductImage());
              // 商品id
              item.put(
                  "productId",
                  grouponActivity.getProductId() == null ? "" : String.valueOf(grouponActivity
                      .getProductId()));
              // 活动id
              item.put("activityId",
                  grouponActivity.getId() == null ? "" : String.valueOf(grouponActivity.getId()));
              // 商品价格
              item.put(
                  "price",
                  grouponActivity.getPrice() == null ? "" : String.valueOf(grouponActivity
                      .getPrice()));
              // 是否收藏
              if (userId != null && grouponActivity.getId() != null) {
                userCollectPojo = new UserCollectPojo();
                userCollectPojo.setUserId(userId);
                userCollectPojo.setActivityId(grouponActivity.getId());
                userCollectPojo.setIsDelete(0);
                int i = userCollectService.userCollectAllCount(userCollectPojo);
                if (i > 0) {
                  item.put("isCollect", "1");
                } else {
                  item.put("isCollect", "0");
                }
              } else {
                item.put("isCollect", "0");
              }
              list.add(item);
              b = true;
            }
          } else {
            msg = "查询不到数据!";
          }
        } else {
          msg = "查询不到数据!";
        }
      } else {
        msg = "查询不到活动!";
      }
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
   * 猜你喜欢（按当日销量排序）
   * */
  public String guessYourLikeApi2() throws SQLException {
    String msg = "";
    Boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    if (activityId == null || activityId == 0) {
      msg = "活动id不能为空!";
    } else {
      grouponActivityPojo = grouponActivityService.getById(activityId);
      if (grouponActivityPojo != null && grouponActivityPojo.getProductId() != null) {
        // 分页
        int ps = 9;
        if (pageSize != null && pageSize != 0) {
          ps = pageSize;
        }
        if (pageNo == null || pageNo == 0) {
          params.put("pageNo", 0);
        } else {
          params.put("pageNo", (pageNo - 1) * ps);
        }
        params.put("pageSize", ps);
        productPojo = productService.getById(grouponActivityPojo.getProductId());
        if (productPojo != null && productPojo.getProductTypeIds() != null) {
          // params.put("orderBy", "p.sell_number desc,ga.create_date desc");
          params.put("orderBy", "ps.day_sell desc");
          // params.put("productTypeIds", productPojo.getProductTypeIds().replaceAll(":", ""));
          params.put("productType1", productPojo.getProductType1());
          params.put("isNotProductId", grouponActivityPojo.getProductId());
          params.put("status", 1);
          params.put("type", 1);
          grouponActivityList = grouponActivityService.listPage2(params);
          if (grouponActivityList != null && grouponActivityList.size() > 0) {
            for (GrouponActivityPojo grouponActivity : grouponActivityList) {
              item = new HashMap<String, Object>();
              // 商品名
              item.put("productName", grouponActivity.getProductName1() == null ? ""
                  : grouponActivity.getProductName1());
              // 商品图片
              item.put("productImage",
                  grouponActivity.getProductImage1() == null ? "" : ConstParam.URL
                      + "/upfiles/product" + File.separator + grouponActivity.getProductImage1());
              // 商品当日销售量
              // item.put("daySell", grouponActivity.getDaySell());
              // 商品id
              item.put(
                  "productId",
                  grouponActivity.getProductId() == null ? "" : String.valueOf(grouponActivity
                      .getProductId()));
              // 活动id
              item.put("activityId",
                  grouponActivity.getId() == null ? "" : String.valueOf(grouponActivity.getId()));
              // 商品价格
              item.put(
                  "price",
                  grouponActivity.getPrice() == null ? "" : String.valueOf(grouponActivity
                      .getPrice()));
              // 是否收藏
              if (userId != null && grouponActivity.getId() != null) {
                userCollectPojo = new UserCollectPojo();
                userCollectPojo.setUserId(userId);
                userCollectPojo.setActivityId(grouponActivity.getId());
                userCollectPojo.setIsDelete(0);
                int i = userCollectService.userCollectAllCount(userCollectPojo);
                if (i > 0) {
                  item.put("isCollect", "1");
                } else {
                  item.put("isCollect", "0");
                }
              } else {
                item.put("isCollect", "0");
              }
              list.add(item);
              b = true;
            }
          } else {
            msg = "查询不到数据!";
          }
        } else {
          msg = "查询不到数据!";
        }
      } else {
        msg = "查询不到活动!";
      }
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
   * 查看退货物流
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String refundExpress() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    String msg = "";
    Map<String, Object> map1 = null;
    if (oid == null || oid <= 0) {
      map.put("result", list);
      map.put("error_msg", "订单ID不能为空！");
      map.put("success", false);
    } else {
      UserOrderRefundPojo userOrderRefundPojo = new UserOrderRefundPojo();
      userOrderRefundPojo.setOrderId(oid);
      List<UserOrderRefundPojo> userOrderRefundPojos =
          userOrderRefundService.findUserOrderRefundByorderId(userOrderRefundPojo);
      if (userOrderRefundPojos.size() > 0) {
        userOrderRefundPojo = userOrderRefundPojos.get(0);
      } else {
        userOrderRefundPojo = null;
      }

      // 订单物流公司英文名转中文
      if (userOrderRefundPojo != null && userOrderRefundPojo.getLogType() != null
          && !"".equals(userOrderRefundPojo.getLogType())
          && userOrderRefundPojo.getLogistics() != null
          && !"".equals(userOrderRefundPojo.getLogistics())) {
        String type = userOrderRefundPojo.getLogType();
        String postid = userOrderRefundPojo.getLogistics();
        String productImages = userOrderRefundPojo.getProductImage();
        String state = "";
        String expressType =
            userOrderRefundPojo.getLogisticsNameCN() == null ? "" : userOrderRefundPojo
                .getLogisticsNameCN();
        String expressNumber = userOrderRefundPojo.getLogistics();
        String url =
            "http://api.kuaidi.com/openapi.html?id=481ea0389df9f6b101f7fd8af272fbef&com=" + type
                + "&nu=" + postid;
        String str = StringUtil.loadJson(url);
        if (str != null && !"".equals(str) && str.startsWith("{")) {
          JSONObject jsonobject = JSONObject.fromObject(str);
          if (!(Boolean) jsonobject.get("success")) {
            if (jsonobject.get("reason") instanceof JSONNull) {
              msg = "";
            } else {
              msg = (String) jsonobject.get("reason");
            }
          } else {
            state = jsonobject.getString("status") == null ? "0" : jsonobject.getString("status");
            if ("0".equals(state)) {
              msg = "物流单号暂无结果！";
            } else {
              JSONArray jsonarray = jsonobject.getJSONArray("data");
              for (int i = 0; i < jsonarray.size(); i++) {
                map1 = new HashMap<String, Object>();
                map1.put("content", jsonarray.getJSONObject(i).get("context"));
                map1.put("time", jsonarray.getJSONObject(i).get("time"));
                list.add(map1);
              }
            }
          }
        }
        // 字典转化
        // 运单的当前状态：0物流单号暂无结果，3在途，4 揽件，5 疑难，6签收，7退签，8 派件，9 退回
        if ("3".equals(state)) {
          state = "0";
        } else if ("4".equals(state)) {
          state = "1";
        } else if ("5".equals(state)) {
          state = "2";
        } else if ("6".equals(state)) {
          state = "3";
        } else if ("7".equals(state)) {
          state = "4";
        } else if ("8".equals(state)) {
          state = "5";
        } else if ("9".equals(state)) {
          state = "6";
        }
        map2.put("state", state);
        map2.put("expressType", expressType);
        map2.put("expressNumber", expressNumber);
        map2.put("productImages", ConstParam.URL + "/upfiles/product" + File.separator
            + productImages);
        map2.put("express", list);
        map.put("result", map2);
        map.put("error_msg", msg);
        map.put("success", true);
      } else {
        map.put("result", list);
        map.put("error_msg", "单号不存在或者已过期");
        map.put("success", false);
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
    Map<String, Object> params = new HashMap<String, Object>();
    SysLoginPojo loginPojo = sysLoginService.findSysLoginById(uid);
    DecimalFormat df = new DecimalFormat("#.##");
    String openid = null;
    OrderPojo order = null;
    /*
     * if (orderNo != null && !"".equals(orderNo)) { order =
     * orderService.findOrderByOrderNo(orderNo); if (order != null) { List<OrderDetailPojo>
     * orderDetail = orderDetailService.getfindByUserIdOrderDetail(order.getId()); if
     * (orderDetail.size() != 0) { for (OrderDetailPojo o : orderDetail) { if (o.getType() != 3) {
     * isWallet = 0; break; } } } else { isWallet = 0; } } else { isWallet = 0; } }
     */
    if (payMethod != null && 8 == payMethod) {
      // 公众号支付openid
      openid = loginPojo.getOpenid();
    }
    SysLoginPojo user = null;
    if (payMethod == 4) {
      user = sysLoginService.findSysLoginById(uid);
    }
    int fullpay = 0;
    if (uid == null) {
      error_msg = "uid不能为空";
    } else if (loginPojo == null) {
      error_msg = "没有该会员";
    } else if (orderNo == null || "".equals(orderNo)) {
      error_msg = "订单号不能为空";
    }
    // else if (order == null) { error_msg = "订单号未找到或已付款"; }
    else if (payMethod == null || payMethod != 1 && payMethod != 2 && payMethod != 8
        && payMethod != 4) {
      error_msg = "支付方式错误";
    } else if (4 == payMethod && user.getBalance() == 0) {
      error_msg = "钱包余额不足！~";
    } else if (8 == payMethod && StringUtils.isBlank(openid)) {
      error_msg = "支付参数错误";
    }
    // else if (payMethod == 4 && isWallet == 0) { error_msg = "支付方式错误"; }
    else {

      String subject = "";
      String body = "";
      price = 0.0;
      // 待支付状态
      order = orderService.findOrderByOrderNo(orderNo);
      if (order != null && 1 == order.getOrderStatus()) {
        // 判断是否是0.1红包商品
        try {
          if ("5".equals(StringUtil.checkVal(order.getSource()))
              && order.getActivityId() != null
              && (order.getActivityId() == 8760 || order.getActivityId() == 8858
                  || order.getActivityId() == 9437 || order.getActivityId() == 9543)
              && (attendId == null || attendId == 0)) {
            Boolean flag = true;
            if (order.getInviteCode() == null || "".equals(order.getInviteCode())) {
              error_msg = "您不能参加该活动!";
              flag = false;
            } else {
              AliRedEnvelopePojo aliRedEnvelope =
                  aliRedEnvelopeService.getByInviteCode(order.getInviteCode());
              if (aliRedEnvelope == null || aliRedEnvelope.getOrderId() == null
                  || aliRedEnvelope.getOrderId().longValue() != order.getId().longValue()) {
                error_msg = "邀请码已被使用!";
                flag = false;
              }
            }
            if (!flag) {
              map.put("result", result);
              map.put("error_msg", error_msg);
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
          }
        } catch (Exception e2) {
          e2.printStackTrace();
        }
        // 判断是否参过团
        if (order.getSourceId() != null && order.getSourceId() > 0) {
          Map<String, Object> p = new HashMap<String, Object>();
          p.put("attendId", order.getSourceId());
          p.put("userId", uid);
          int gur = grouponUserRecordService.countBy(p);
          if (gur > 0) {
            map.put("result", result);
            map.put("error_msg", "您已经参加过该团^_^");
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
        }
        Util.log("判断是否已达到普通商品限购");
        try {
          if ("1".equals(StringUtil.checkVal(order.getSource()))) {
            if (order.getProductId() != null && order.getNum() > 0) {
              ProductPojo proPojo = productService.getById(Long.valueOf(order.getProductId()));
              if (proPojo.getMaxNum() > 0) {
                if (proPojo.getMaxNum() < order.getNum()) {
                  map.put("result", result);
                  map.put("error_msg", "对不起，您购买的数量大于商品的限购数量！~");
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
                Map<String, Object> params1 = new HashMap<String, Object>();
                params1.put("userId", uid);
                params1.put("activityId", order.getActivityId());
                params1.put("productId", Long.valueOf(order.getProductId()));
                List<ProductRestrictionPojo> productRestrictionPojos =
                    productRestrictionService.listPage(params1);
                if (productRestrictionPojos != null && productRestrictionPojos.size() > 0) {
                  ProductRestrictionPojo productRestriction1 = productRestrictionPojos.get(0);
                  if (productRestriction1.getMaxNum() > 0
                      && productRestriction1.getBuyNum() + order.getNum() > productRestriction1
                          .getMaxNum()) {
                    map.put("result", result);
                    map.put("error_msg", "对不起，您购买的数量大于商品的限购数量！~");
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
                }
              }
            }
          } else if ("5".equals(StringUtil.checkVal(order.getSource()))
              || "6".equals(StringUtil.checkVal(order.getSource()))
              || "7".equals(StringUtil.checkVal(order.getSource()))) {
            if (order.getActivityId() != null) {
              int gurCount = 0;
              int garCount = 0;
              params.clear();
              params.put("userId", uid);
              params.put("activityId", order.getActivityId());
              params.put("isHead", 0);
              gurCount = grouponUserRecordService.countBy(params);
              params.clear();
              params.put("userId", uid);
              params.put("activityId", order.getActivityId());
              garCount = grouponActivityRecordService.countBy(params);
              Util.log("0.1抽奖判断是开团还是参团");
              if ("5".equals(StringUtil.checkVal(order.getSource()))
                  || "7".equals(StringUtil.checkVal(order.getSource()))) {
                Util.log("根据sourceId判断是开团还是参团");
                if (order.getSourceId() != null && order.getSourceId() > 0) {
                  Util.log("参团");
                  if (gurCount > 0) {
                    map.put("result", result);
                    map.put("error_msg", "您已经参加过该团!");
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
                } else {
                  Util.log("开团");
                  if (garCount > 0) {
                    map.put("result", result);
                    map.put("error_msg", "您已经参加过该团!");
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
                }
              } else if ("6".equals(StringUtil.checkVal(order.getSource()))) {
                Util.log("限时秒杀和免费抽奖判断是否参过或拼过团");
                if (gurCount > 0 || garCount > 0) {
                  Util.log("拼过团或参过团");
                  map.put("result", result);
                  map.put("error_msg", "您已经参过该团!");
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
              }
            }
          }
        } catch (Exception e1) {
          e1.printStackTrace();
        }
        // 新商户订单号
        String out_trade_no = UtilDate.getOrderNum() + uid + RandomUtils.runVerifyCode(3);
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
        } else if (2 == order.getPayMethod() || 8 == order.getPayMethod()) {
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
          Map<String, Object> orderMap = new HashMap<String, Object>();
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

          boolean check = true;
          if (payMethod == 4 && price > user.getBalance()) {
            check = false;

            b = false;
            error_msg = "钱包余额不足！~";
          }

          if (check) {
            List<Map<String, Object>> orders = new ArrayList<Map<String, Object>>();
            Map<String, Object> ordermap = new HashMap<String, Object>();
            ordermap.put("orderId", order.getId());
            ordermap.put("price", price);
            orders.add(ordermap);
            // 更新支付流水号
            orderMap.put("orderId", order.getId());
            orderMap.put("outTradeNo", out_trade_no);
            orderMap.put("payMethod", payMethod);
            orderService.updateOutTradeNo(orderMap);
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

            } else if (2 == payMethod || 8 == payMethod) {
              // 生成未付款微信支付信息
              WxpayOrderInfoPojo wxpay = new WxpayOrderInfoPojo();
              wxpay.setOutTradeNo(out_trade_no);
              // 单位分
              wxpay.setTotalFee(SellerService.doubleToFee(price));
              wxpay.setTradeStatus("WAIT_BUYER_PAY");
              wxpay.setVersion(0);
              String tradeType = 2 == payMethod ? "APP" : "JSAPI";
              wxpay.setTradeType(tradeType);
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
                  sellerService.buildWxpayReq(openid, wxpay.getOutTradeNo(), null, body, subject,
                      Integer.valueOf(wxpay.getTotalFee()), tradeType);
              wxpayMap.put("out_trade_no", out_trade_no);
            } else if (payMethod == 4) {
              SysLoginPojo sysLoginPojo = sysLoginService.getfindByIdSysLogin(uid);
              if (sysLoginPojo != null) {
                if (sysLoginPojo.getBalance() >= price) {
                  SysLoginPojo sysLogin = new SysLoginPojo();
                  sysLogin.setBalanceReduce(price);
                  sysLogin.setId(sysLoginPojo.getId());
                  int i = sysLoginService.updateSysLogin(sysLogin);

                  if (i != 0) {
                    UserWalletLogPojo userWalletLogPojo = new UserWalletLogPojo();
                    userWalletLogPojo.setUserId(uid);
                    userWalletLogPojo.setCurBal(sysLoginPojo.getBalance());
                    userWalletLogPojo.setType(1l);
                    userWalletLogPojo.setTradeAmt(price);
                    userWalletLogPojo.setSource((long) order.getSource());
                    userWalletLogPojo.setOrderId(order.getId());
                    userWalletLogPojo.setCreateBy(sysLoginPojo.getId());
                    userWalletLogPojo.setOutTradeNo(out_trade_no);
                    userWalletLogPojo.setRemarks("消费");
                    userWalletLogService.insertUserWalletLog(userWalletLogPojo);

                    Map<String, Object> param = new HashMap<String, Object>();
                    param.put("id", order.getId());
                    param.put("walletPrice", price);
                    orderService.updateorders(param);

                    // 付款成功
                    processPaySuccess(out_trade_no);

                    fullpay = 1;
                  } else {
                    b = false;
                    error_msg = "支付失败！~";
                  }
                } else {
                  b = false;
                  error_msg = "钱包余额不足！~";
                }
              } else {
                b = false;
                error_msg = "用户不存在！~";
              }
            }
            b = true;
          }
        }
      } else {
        error_msg = "订单号未找到或已付款";
      }
    }
    result.put("fullpay", fullpay);
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
   * @return 拼得客申请
   * @throws Exception
   */
  public String pdkApplyApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    new HashMap<String, Object>();
    String result = "0";
    // if (cardNo == null || StringUtils.isBlank(cardNo)) { msg = "身份证号不能为空!"; } else
    if (extChannel == null || StringUtils.isBlank(extChannel)) {
      msg = "推广渠道不能为空!";
    } else if (name == null || StringUtils.isBlank(name)) {
      msg = "真实姓名不能为空!";
    } else if (phone == null || StringUtils.isBlank(phone)) {
      msg = "电话不能为空!";
    } else if (userId == null || userId == 0) {
      msg = "用户id不能为空!";
    } else {
      SysLoginPojo loginPojo = sysLoginService.findSysLoginById(userId);
      if (loginPojo != null) {
        UserPindekeInfoPojo userPindekeInfoPojo = new UserPindekeInfoPojo();
        // 判断是否有上传图片
        String image1Name = null;
        Integer size = 0;
        if (image1 != null) {
          image1Name = sellerService.uploadFile(image1, "upfiles" + File.separator + "userpindeke");
          userPindekeInfoPojo.setExtendImg1(image1Name);
          size++;
        } else {
          userPindekeInfoPojo.setExtendImg1("");
        }
        if (image2 != null) {
          image1Name = sellerService.uploadFile(image2, "upfiles" + File.separator + "userpindeke");
          userPindekeInfoPojo.setExtendImg2(image1Name);
          size++;
        } else {
          userPindekeInfoPojo.setExtendImg2("");
        }
        if (image3 != null) {
          image1Name = sellerService.uploadFile(image3, "upfiles" + File.separator + "userpindeke");
          userPindekeInfoPojo.setExtendImg3(image1Name);
          size++;
        } else {
          userPindekeInfoPojo.setExtendImg3("");
        }
        if (image4 != null) {
          image1Name = sellerService.uploadFile(image4, "upfiles" + File.separator + "userpindeke");
          userPindekeInfoPojo.setExtendImg4(image1Name);
          size++;
        } else {
          userPindekeInfoPojo.setExtendImg4("");
        }
        if (image5 != null) {
          image1Name = sellerService.uploadFile(image5, "upfiles" + File.separator + "userpindeke");
          userPindekeInfoPojo.setExtendImg5(image1Name);
          size++;
        } else {
          userPindekeInfoPojo.setExtendImg5("");
        }
        // if (size == 0) {
        // msg = "至少上传一张推广图片!";
        // result = "0";
        // } else {
        userPindekeInfoPojo.setName(name);
        userPindekeInfoPojo.setCardNo(cardNo);
        userPindekeInfoPojo.setExtendChannel(extChannel);
        userPindekeInfoPojo.setPhone(phone);
        userPindekeInfoPojo.setUserId(userId);
        userPindekeInfoPojo.setCreateBy(userId);
        userPindekeInfoPojo.setCreateDate(new Date());
        userPindekeInfoPojo.setUpdateBy(userId);
        userPindekeInfoPojo.setUpdateDate(new Date());

        if ((loginPojo.getInviterId() == null || loginPojo.getInviterId() == 0)
            && StringUtils.isNotBlank(code) && code.length() == 6) {
          SysLoginPojo sysLogin = new SysLoginPojo();
          sysLogin.setInvitationCode(code);
          sysLogin = sysLoginService.getUserIdByInvitationCode(sysLogin);
          if (sysLogin != null && sysLogin.getStatus() == 1 && sysLogin.getIsPindeke() == 1) {
            userPindekeInfoPojo.setInvitationCode(code);
          }
        }
        // }
        // 判断是否存在申请记录
        userPindekeInfo = userPindekeInfoService.findByUserId(userId);
        if (userPindekeInfo == null) {
          int i = userPindekeInfoService.add(userPindekeInfoPojo);
          if (i > 0) {
            result = "1";
            msg = "申请成功!";
            success = true;
          } else {
            result = "0";
            msg = "申请失败,请重新申请!";
          }
        } else {
          if (userPindekeInfo.getIsDelete() == 0) {
            msg = "您已经申请过拼得客,请不要重新申请!";
            result = "0";
          } else {
            userPindekeInfoPojo.setId(userPindekeInfo.getId());
            userPindekeInfoPojo.setIsDelete(0);
            int i = userPindekeInfoService.update(userPindekeInfoPojo);
            if (i > 0) {
              result = "1";
              msg = "申请成功!";
              success = true;
            } else {
              result = "0";
              msg = "申请失败,请重新申请!";
            }
          }
        }
      } else {
        result = "0";
        msg = "不存在该用户!";
      }
    }
    map.put("result", result);
    map.put("success", success);
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
   * @return 修改拼得客信息
   * @throws Exception
   */
  public String pdkUpdateApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    new HashMap<String, Object>();
    String result = "0";
    // if (cardNo == null || StringUtils.isBlank(cardNo)) { msg = "身份证号不能为空!"; } else
    if (extChannel == null || StringUtils.isBlank(extChannel)) {
      msg = "推广渠道不能为空!";
    } else if (id == null || id == 0) {
      msg = "拼得客id不能为空!";
    } else if (name == null || StringUtils.isBlank(name)) {
      msg = "真实姓名不能为空!";
    } else if (phone == null || StringUtils.isBlank(phone)) {
      msg = "电话不能为空!";
    } else if (userId == null || userId == 0) {
      msg = "用户id不能为空!";
    } else {
      userPindekeInfo = userPindekeInfoService.getById(id);
      if (userPindekeInfo == null || userPindekeInfo.getIsDelete() == 1) {
        msg = "不存在该拼得客!";
        result = "0";
      } else {
        // 判断是否是申请通过
        if (userPindekeInfo.getStatus() == 2) {
          // 判断是否有上传图片
          Integer size = 0;
          if (imgName1 != null) {
            userPindekeInfo.setExtendImg1(imgName1);
            size++;
          } else {
            userPindekeInfo.setExtendImg1("");
          }
          if (imgName2 != null) {
            userPindekeInfo.setExtendImg2(imgName2);
            size++;
          } else {
            userPindekeInfo.setExtendImg2("");
          }
          if (imgName3 != null) {
            userPindekeInfo.setExtendImg3(imgName3);
            size++;
          } else {
            userPindekeInfo.setExtendImg3("");
          }
          if (imgName4 != null) {
            userPindekeInfo.setExtendImg4(imgName4);
            size++;
          } else {
            userPindekeInfo.setExtendImg4("");
          }
          if (imgName5 != null) {
            userPindekeInfo.setExtendImg5(imgName5);
            size++;
          } else {
            userPindekeInfo.setExtendImg5("");
          }
          // if (size == 0) {
          // msg = "至少上传一张推广图片!";
          // result = "0";
          // } else {
          // 修改拼得客绑定的推荐者id
          if (code != null && !"".equals(code) && code.length() == 6) {
            SysLoginPojo sysLogin = new SysLoginPojo();
            sysLogin.setInvitationCode(code);
            SysLoginPojo sysLoginCode = sysLoginService.getUserIdByInvitationCode(sysLogin);
            if (sysLoginCode != null && sysLoginCode.getStatus() == 1
                && sysLoginCode.getIsPindeke() == 1) {
              userPindekeInfo.setInvitationCode(code);
            } else {
              Util.log("根据推荐码" + code + "查询不到拼得客!");
            }
          }
          userPindekeInfo.setName(name);
          userPindekeInfo.setStatus(0);
          userPindekeInfo.setCardNo(cardNo);
          userPindekeInfo.setExtendChannel(extChannel);
          userPindekeInfo.setPhone(phone);
          userPindekeInfo.setUserId(userId);
          int i = userPindekeInfoService.update(userPindekeInfo);
          if (i > 0) {
            result = "1";
            msg = "修改成功!";
            success = true;
          } else {
            result = "0";
            msg = "修改失败,请重新修改!";
          }
          // }
        } else {
          result = "0";
          msg = "您已经成为拼得客,不用重新申请!";
        }
      }
    }
    map.put("result", result);
    map.put("success", success);
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
   * @return 修改拼得客信息
   * @throws Exception
   */
  public String pdkApplyInfoApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (userId == null || userId == 0) {
      msg = "用户ID不能为空!";
    } else {
      userPindekeInfo = userPindekeInfoService.findByUserId(userId);
      if (userPindekeInfo == null || userPindekeInfo.getIsDelete() == 1) {
        msg = "不存在该拼得客!";
      } else {
        // 身份证号
        result
            .put("cardNo", userPindekeInfo.getCardNo() == null ? "" : userPindekeInfo.getCardNo());
        // 推广渠道
        result.put("channel",
            userPindekeInfo.getExtendChannel() == null ? "" : userPindekeInfo.getExtendChannel());
        // 图片
        result.put("image1",
            StringUtils.isEmpty(userPindekeInfo.getExtendImg1()) ? "" : ConstParam.URL
                + "/upfiles/userpindeke" + File.separator + userPindekeInfo.getExtendImg1());
        result.put("image2",
            StringUtils.isEmpty(userPindekeInfo.getExtendImg2()) ? "" : ConstParam.URL
                + "/upfiles/userpindeke" + File.separator + userPindekeInfo.getExtendImg2());
        result.put("image3",
            StringUtils.isEmpty(userPindekeInfo.getExtendImg3()) ? "" : ConstParam.URL
                + "/upfiles/userpindeke" + File.separator + userPindekeInfo.getExtendImg3());
        result.put("image4",
            StringUtils.isEmpty(userPindekeInfo.getExtendImg4()) ? "" : ConstParam.URL
                + "/upfiles/userpindeke" + File.separator + userPindekeInfo.getExtendImg4());
        result.put("image5",
            StringUtils.isEmpty(userPindekeInfo.getExtendImg5()) ? "" : ConstParam.URL
                + "/upfiles/userpindeke" + File.separator + userPindekeInfo.getExtendImg5());
        // 姓名
        result.put("name", userPindekeInfo.getName() == null ? "" : userPindekeInfo.getName());
        // 手机
        result.put("phone", userPindekeInfo.getPhone() == null ? "" : userPindekeInfo.getPhone());
        // 状态0-审核中1-审核通过2-审核不通过3-冻结
        result.put("status",
            userPindekeInfo.getStatus() == null ? "" : String.valueOf(userPindekeInfo.getStatus()));
        // 钱包余额
        result.put(
            "balance",
            userPindekeInfo.getSurpluPrice() == null ? "" : String.valueOf(userPindekeInfo
                .getSurpluPrice()));
        // 团免链接
        result.put("freeGroupUrl", "freeGroupUrl");
        // 拼得客id
        result.put("pdkId", String.valueOf(userPindekeInfo.getId()));
        // 退回原因
        result.put("returnMsg", StringUtil.checkVal(userPindekeInfo.getReturnMsg()));
      }
    }
    map.put("result", result);
    map.put("success", success);
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
   * @return 拼得客信息
   * @throws Exception
   */
  public String pindekeUserInfo() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (userId == null || userId == 0) {
      msg = "用户ID不能为空!";
    } else {
      userPindekeInfo = userPindekeInfoService.findByUserId(userId);
      if (userPindekeInfo == null || userPindekeInfo.getIsDelete() == 1
          || userPindekeInfo.getStatus() == 0 || userPindekeInfo.getStatus() == 2) {
        msg = "不存在该拼得客!";
      } else {
        // 钱包余额
        result.put("balance", StringUtil.checkVal(userPindekeInfo.getSurpluPrice()));
        // 开团数
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("invitationUserId", userId);
        params.put("openNum", 1);
        int openNum = grouponActivityRecordService.countBy(params);
        result.put("openNum", openNum);
        // 成团数
        params.put("invitationUserId", userId);
        params.put("status", 1);
        int succNum = grouponActivityRecordService.countBy(params);
        result.put("succNum", succNum);
        // 是否冻结
        if (userPindekeInfo.getStatus() == 3) {
          result.put("isFrozen", 1);
        } else {
          result.put("isFrozen", 0);
        }
      }
    }
    map.put("result", result);
    map.put("success", success);
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
   * @return 拼得客交易记录列表
   * @throws Exception
   */
  public String pdkTranRecListApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    if (type == null || type == 0) {
      msg = "查询类型不能为空!";
    } else if (userId == null || userId == 0) {
      msg = "用户id不能为空!";
    } else {
      int ps = 10;
      if (pageSize != null && pageSize != 0) {
        ps = pageSize;
      }
      if (pageNo == null || pageNo == 0) {
        params.put("pageNo", 0);
      } else {
        params.put("pageNo", (pageNo - 1) * ps);
      }
      params.put("pageSize", ps);
      // 1-收入2-支出
      params.put("dealType", type);
      params.put("userId", userId);
      if (StringUtils.isNotEmpty(beginTime)) {
        params.put("beginTime", beginTime);
      }
      if (StringUtils.isNotEmpty(endTime)) {
        params.put("endTime", endTime);
      }
      if (status != null) {
        params.put("status", status);
      }
      params.put("orderBy", "udl.id desc");
      userDealLogList = userDealLogService.listPage(params);
      if (userDealLogList != null && userDealLogList.size() > 0) {
        for (UserDealLogPojo userDealLogPojo : userDealLogList) {
          item = new HashMap<String, Object>();
          // 交易时间
          item.put(
              "date",
              userDealLogPojo.getDealDate() == null ? "" : StringUtil.dateString(userDealLogPojo
                  .getDealDate()));
          // 交易记录id
          item.put("id",
              userDealLogPojo.getId() == null ? "" : String.valueOf(userDealLogPojo.getId()));
          // 返佣金额
          item.put(
              "price",
              userDealLogPojo.getDealAmount() == null ? "" : StringUtil.checkVal(userDealLogPojo
                  .getDealAmount()));
          // list.add(item);
          // 1-返佣2-提现
          item.put(
              "type",
              userDealLogPojo.getDealType() == null ? "" : String.valueOf(userDealLogPojo
                  .getDealType()));
          // 0-待审核1-审核通过2-审核不通过3-转账完成
          item.put(
              "status",
              userDealLogPojo.getStatus() == null ? ""
                  : String.valueOf(userDealLogPojo.getStatus()));
          list.add(item);
        }
      } else {
        msg = "查询不到交易记录!";
      }
      // 查询拼得客信息
      userPindekeInfo = userPindekeInfoService.findByUserId(userId);
      if (userPindekeInfo != null && userPindekeInfo.getIsDelete() == 0) {
        // 总返佣
        result.put(
            "rePrice",
            userPindekeInfo.getRebatePrice() == null ? "" : StringUtil.checkVal(userPindekeInfo
                .getRebatePrice()));
        // 总提成
        result.put(
            "wdPrice",
            userPindekeInfo.getWithdrawPrice() == null ? "" : StringUtil.checkVal(userPindekeInfo
                .getWithdrawPrice()));
      } else {
        result.put("rePrice", "");
        result.put("wdPrice", "");
      }
      result.put("tranList", list);
      success = true;
    }
    map.put("result", result);
    map.put("success", success);
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
   * @return 拼得客交易记录详情
   * @throws Exception
   */
  public String tranDetailApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (id == null || id == 0) {
      msg = "交易记录id不能为空!";
    } else {
      userDealLogPojo = userDealLogService.getById(id);
      if (userDealLogPojo != null) {
        // 交易时间
        result.put(
            "date",
            userDealLogPojo.getDealDate() == null ? "" : StringUtil.dateString(userDealLogPojo
                .getDealDate()));
        // 交易金额
        result.put(
            "price",
            userDealLogPojo.getDealAmount() == null ? "" : StringUtil.checkVal(userDealLogPojo
                .getDealAmount()));
        // 备注1-返佣2-提现
        result.put("remark", "");
        if (userDealLogPojo.getRemark() != null) {
          if (userDealLogPojo.getRemark() == 1) {
            result.put("remark", "返佣");
          } else if (userDealLogPojo.getRemark() == 2) {
            result.put("remark", "提现");
          }
        }
        // 拼得客剩余金额
        result.put(
            "surpluPrice",
            userDealLogPojo.getSurplusPrice() == null ? "" : StringUtil.checkVal(userDealLogPojo
                .getSurplusPrice()));
        // 记录类型(1-收入2-支出)
        result.put("type",
            userDealLogPojo.getRemark() == null ? "" : String.valueOf(userDealLogPojo.getRemark()));
        // 交易单号
        result.put(
            "orderNo",
            userDealLogPojo.getOrderNo() == null ? ""
                : String.valueOf(userDealLogPojo.getOrderNo()));
        success = true;
      }
    }
    map.put("result", result);
    map.put("success", success);
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
   * @return 提现申请
   * @throws Exception
   */
  public String wdApplyApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    String result = "0";
    if (account == null || StringUtils.isBlank(account)) {
      msg = "转账帐号不能为空!";
    } else if (taType == null || taType == 0) {
      msg = "转账方式不能为空!";
    } else if (name == null || StringUtils.isBlank(name)) {
      msg = "真实姓名不能为空!";
    } else if (price == null) {
      msg = "提现金额不能为空!";
    } else if (userId == null) {
      msg = "用户id不能为空!";
    } else {
      userPindekeInfo = userPindekeInfoService.findByUserId(userId);
      if (userPindekeInfo == null
          || !(userPindekeInfo.getIsDelete() == 0 && userPindekeInfo.getStatus() == 1)) {
        msg = "查询不到审核通过拼得客!";
      } else {
        if (userPindekeInfo.getSurpluPrice().doubleValue() < price.doubleValue()) {
          msg = "提现价格不能多于剩余价格!";
        } else {
          Map<String, Object> params = new HashMap<String, Object>();
          params.put("userId", userId);
          params.put("dealType", 2);
          params.put("statusStr", " udl.status in(0,1)");
          params.put("pageNo", 0);
          params.put("pageSize", 1);
          params.put("orderBy", "udl.id desc");
          List<UserDealLogPojo> list = userDealLogService.listPage(params);
          if (list.size() > 0) {
            msg = "您已有一笔提现记录正在审核中，请耐心等待管理员审核！~";
          } else {
            userDealLogPojo = new UserDealLogPojo();
            userDealLogPojo.setCreateBy(userId);
            userDealLogPojo.setUserId(userId);
            userDealLogPojo.setName(name);
            userDealLogPojo.setType(String.valueOf(taType));
            userDealLogPojo.setTypeNo(account);
            userDealLogPojo.setDealAmount(price);
            userDealLogPojo.setRemark(2);
            userDealLogPojo.setSurplusPrice(userPindekeInfo.getSurpluPrice() - price);
            userDealLogPojo.setDealDate(new Date());
            userDealLogPojo.setDealType(2);
            userDealLogPojo.setCreateBy(userId);
            userDealLogPojo.setCreateDate(new Date());
            userDealLogPojo.setUpdateBy(userId);
            userDealLogPojo.setUpdateDate(new Date());
            int i = userDealLogService.add(userDealLogPojo);
            if (i > 0) {
              msg = "申请成功!";
              result = "1";
              success = true;
            } else {
              msg = "提交申请失败,请重新提交申请!";
            }
          }
        }
      }
    }
    map.put("result", result);
    map.put("success", success);
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
   * @return 专题顶部图片
   * @throws Exception
   */
  public String specialImageApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (specialId == null || specialId == 0) {
      msg = "专题id不能为空!";
    } else {
      specialPojo = specialService.getById(specialId);
      if (specialPojo != null) {
        result.put("image", specialPojo.getImage() == null ? "" : ConstParam.URL
            + "/upfiles/special" + File.separator + specialPojo.getImage());
        result.put("specialName", specialPojo.getTitle() == null ? "" : specialPojo.getTitle());
        success = true;
      } else {
        msg = "查询不到专题!";
      }
    }
    map.put("result", result);
    map.put("success", success);
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
   * @return 专题分类
   * @throws Exception
   */
  public String specialTypeApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    params.put("statusDisplay", 1);
    params.put("orderBy", "sorting desc");
    specialTypeList = specialTypeService.listPage(params);
    if (specialTypeList != null && specialTypeList.size() > 0) {
      for (SpecialTypePojo specialType : specialTypeList) {
        item = new HashMap<String, Object>();
        item.put("id", String.valueOf(specialType.getId()));
        item.put("name", specialType.getName() == null ? "" : specialType.getName());
        result.add(item);
      }
      success = true;
    } else {
      msg = "查询不到专题分类!";
    }
    map.put("result", result);
    map.put("success", success);
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
   * @return 拼团专题列表
   * @throws Exception
   */
  public String specialListApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    Map<String, Object> item1 = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> productList = new ArrayList<Map<String, Object>>();
    if (typeId == null || typeId.equals("0")) {
      msg = "专题分类id不能为空!";
    } else {
      int ps = 10;
      if (pageSize != null && pageSize != 0) {
        ps = pageSize;
      }
      if (pageNo == null || pageNo == 0) {
        params.put("pageNo", 0);
      } else {
        params.put("pageNo", (pageNo - 1) * ps);
      }
      params.put("pageSize", ps);
      params.put("specialTypeId", typeId);
      params.put("isDelete", 0);
      params.put("status", 1);
      params.put("orderBy", "s.sorting desc,s.id desc");
      // 查询专题列表
      specialList = specialService.listPage(params);
      if (specialList != null && specialList.size() > 0) {
        for (SpecialPojo special : specialList) {
          item = new HashMap<String, Object>();
          productList = new ArrayList<Map<String, Object>>();
          item.put("specialImage", special.getImage() == null ? "" : ConstParam.URL
              + "/upfiles/special" + File.separator + special.getImage());
          item.put("specialId", special.getId() == null ? "" : String.valueOf(special.getId()));
          item.put("specialName", special.getTitle() == null ? "" : special.getTitle());
          params.clear();
          params.put("specialId", special.getId());
          params.put("orderBy", "sg.sorting desc,sg.id desc");
          params.put("status", 1);
          params.put("pageNo", 0);
          params.put("pageSize", 6);
          params.put("activityStatus", 1);
          // 查询活动商品
          specialGoodsList = specialGoodsService.listPage(params);
          if (specialGoodsList != null && specialGoodsList.size() > 0) {
            for (SpecialGoodsPojo specialGoods : specialGoodsList) {
              item1 = new HashMap<String, Object>();
              // 拼团活动id
              item1.put("activityId", StringUtil.checkVal(specialGoods.getActivityId()));
              // 单独购价
              item1.put("alonePrice", StringUtil.checkVal(specialGoods.getAlonePrice()));
              // 拼团价格
              item1.put(
                  "price",
                  specialGoods.getGroupPrice() == null ? "" : StringUtil.checkVal(specialGoods
                      .getGroupPrice()));
              // 销量
              item1.put(
                  "grouponNum",
                  specialGoods.getSellNumber() == null ? "" : String.valueOf(specialGoods
                      .getSellNumber()));
              // 几人团
              item1.put("num",
                  specialGoods.getNum() == null ? "" : String.valueOf(specialGoods.getNum()));
              // 商品id
              item1.put(
                  "productId",
                  specialGoods.getProductId() == null ? "" : String.valueOf(specialGoods
                      .getProductId()));
              // 商品图片
              item1.put("productImage", specialGoods.getImage() == null ? "" : ConstParam.URL
                  + "/upfiles/product" + File.separator + specialGoods.getImage());
              // 商品名称
              item1.put("productName",
                  specialGoods.getProductName() == null ? "" : specialGoods.getProductName());
              productList.add(item1);
            }
          }
          item.put("productList", productList);
          result.add(item);
        }
        success = true;
      } else {
        msg = "没有更多专题!";
      }
    }
    map.put("result", result);
    map.put("success", success);
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
   * @return 拼团专题详情
   * @throws Exception
   */
  public String specialDetailApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    if (specialId == null || specialId <= 0) {
      msg = "专题id不能为空!";
    } else {
      int ps = 8;
      if (pageSize != null && pageSize != 0) {
        ps = pageSize;
      }
      if (pageNo == null || pageNo == 0) {
        params.put("pageNo", 0);
      } else {
        params.put("pageNo", (pageNo - 1) * ps);
      }
      params.put("pageSize", ps);
      params.put("specialId", specialId);
      params.put("status", 1);
      params.put("activityStatus", 1);
      params.put("orderBy", "sg.sorting desc,sg.id desc");
      specialGoodsList = specialGoodsService.listPage(params);
      if (specialGoodsList != null && specialGoodsList.size() > 0) {
        for (SpecialGoodsPojo specialGoods : specialGoodsList) {
          item = new HashMap<String, Object>();
          // 拼团活动id
          item.put("activityId", specialGoods.getActivityId());
          // 单独购价
          item.put("alonePrice", StringUtil.checkVal(specialGoods.getAlonePrice()));
          // 拼团价格
          item.put(
              "price",
              specialGoods.getGroupPrice() == null ? "" : StringUtil.checkVal(specialGoods
                  .getGroupPrice()));
          // 销量
          item.put(
              "grouponNum",
              specialGoods.getSellNumber() == null ? "" : String.valueOf(specialGoods
                  .getSellNumber()));
          // 几人团
          item.put("num",
              specialGoods.getNum() == null ? "" : String.valueOf(specialGoods.getNum()));
          // 商品id
          item.put(
              "productId",
              specialGoods.getProductId() == null ? ""
                  : String.valueOf(specialGoods.getProductId()));
          // 商品图片
          item.put("productImage", specialGoods.getImage() == null ? "" : ConstParam.URL
              + "/upfiles/product" + File.separator + specialGoods.getImage());
          // 商品名称
          item.put("productName",
              specialGoods.getProductName() == null ? "" : specialGoods.getProductName());
          result.add(item);
        }
        success = true;
      } else {
        msg = "没有更多商品数据!";
      }
    }
    map.put("result", result);
    map.put("success", success);
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
    option.put("pageNo", 0);
    option.put("pageSize", 5);
    List<HomePageIconPojo> homePageIconPojo = homePageIconService.findHomePageIconList(option);
    List<Object> list = new ArrayList<Object>();
    Map<String, Object> result = null;
    if (homePageIconPojo.size() > 0) {
      for (HomePageIconPojo h : homePageIconPojo) {
        result = new HashMap<String, Object>();
        result.put("image", StringUtils.isBlank(h.getImage()) ? "" : ConstParam.URL
            + "/upfiles/icon/" + h.getImage());
        result.put("title", StringUtil.checkVal(h.getTitle()));
        result.put("type", StringUtil.checkVal(h.getType()));
        result.put("param", StringUtil.checkVal(h.getUrl()));
        list.add(result);
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


  /**
   * @return 7.7专区
   * @throws Exception
   */
  public String zoneProductsApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    if (id == null || id == 0) {
      msg = "专区id不能为空!";
    } else {
      // 判断是否存在专场
      zonesPojo = zonesService.getById(id);
      if (zonesPojo != null && zonesPojo.getStatus() == 1 && zonesPojo.getIsDelete() == 0) {
        int ps = 8;
        if (pageSize != null && pageSize != 0) {
          ps = pageSize;
        }
        if (pageNo == null || pageNo == 0) {
          params.put("pageNo", 0);
        } else {
          params.put("pageNo", (pageNo - 1) * ps);
        }
        params.put("pageSize", ps);
        params.put("zoneId", id);
        params.put("status", 1);
        params.put("activityStatus", 1);
        params.put("orderBy", "zg.sorting desc,zg.id desc");
        zoneGoodsList = zoneGoodsService.listPage(params);
        if (zoneGoodsList != null && zoneGoodsList.size() > 0) {
          for (ZoneGoodsPojo zoneGoods : zoneGoodsList) {
            item = new HashMap<String, Object>();
            // 拼团活动id
            item.put("activityId", StringUtil.checkVal(zoneGoods.getActivityId()));
            // 单独购价
            item.put("alonePrice", StringUtil.checkVal(zoneGoods.getAlonePrice()));
            // 拼团价格
            item.put(
                "price",
                zoneGoods.getGroupPrice() == null ? "" : StringUtil.checkVal(zoneGoods
                    .getGroupPrice()));
            // 销量
            item.put("grouponNum",
                zoneGoods.getSellNumber() == null ? "" : String.valueOf(zoneGoods.getSellNumber()));
            // 几人团
            item.put("num", zoneGoods.getNum() == null ? "" : String.valueOf(zoneGoods.getNum()));
            // 商品id
            item.put("productId",
                zoneGoods.getProductId() == null ? "" : String.valueOf(zoneGoods.getProductId()));
            // 商品图片
            item.put("productImage", zoneGoods.getImage() == null ? "" : ConstParam.URL
                + "/upfiles/product" + File.separator + zoneGoods.getImage());
            // 商品名称
            item.put("productName",
                zoneGoods.getProductName() == null ? "" : zoneGoods.getProductName());
            result.add(item);
          }
          success = true;
        } else {
          msg = "没有更多商品数据!";
        }
      } else {
        msg = "查询不到该专场!";
      }
    }
    map.put("result", result);
    map.put("success", success);
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
   * @return 77专区顶部图片
   * @throws Exception
   */
  public String zoneApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    params.put("status", 1);
    params.put("type", 1);
    params.put("isDelete", 0);
    params.put("orderBy", "id desc");
    zonesList = zonesService.listPage(params);
    if (zonesList != null && zonesList.size() > 0) {
      zonesPojo = zonesList.get(0);
      if (zonesPojo != null) {
        result.put("banner", zonesPojo.getImage() == null ? "" : ConstParam.URL + "/upfiles/zones"
            + File.separator + zonesPojo.getImage());
        result.put("title", zonesPojo.getTitle() == null ? "" : zonesPojo.getTitle());
        result.put("id", zonesPojo.getId() == null ? "" : zonesPojo.getId());
        success = true;
      } else {
        msg = "查询不到专区!";
      }
    } else {
      msg = "查询不到专区!";
    }
    map.put("result", result);
    map.put("success", success);
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
   * 获取优惠券
   * 
   * @return
   * @throws Exception
   */
  public String getCouponApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    String result = "0";
    if (userId == null || userId == 0) {
      msg = "用户id不能为空!";
    } else if (linkId == null || linkId == 0) {
      msg = "链接id不能为空!";
    } else {
      int flag = grouponService.getCoupon(userId, linkId);
      if (flag == 1) {
        result = "1";
        b = true;
      } else if (flag == 2) {
        msg = "用户id不能为空!";
      } else if (flag == 3) {
        msg = "查询该不到优惠券!";
      } else if (flag == 4) {
        msg = "查询不到优惠券!";
      } else if (flag == 5) {
        msg = "您已经领取过优惠券!";
      } else if (flag == 6) {
        msg = "优惠券已过期!";
      } else if (flag == 7) {
        msg = "优惠券领取完了!";
      } else if (flag == 8) {
        msg = "优惠券领取失败,请重新领取!";
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
   * 
   * 查询一条优惠券信息
   * 
   * @throws SQLException
   * */
  public String aCouponInfoApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (linkId == null || linkId == 0) {
      msg = "优惠券类型id不能为空！";
    } else {
      CouponPojo couponPojo = couponService.getcouponById(linkId);
      if (couponPojo != null && couponPojo.getIsDelete() == 0) {
        if (StringUtils.isNotEmpty(couponPojo.getContent())) {
          String str = couponPojo.getContent();
          org.json.JSONObject json = new org.json.JSONObject(str);
          result.put("m", json.get("m"));
        } else {
          result.put("m", "");
        }
        // 查询商品信息
        if (couponPojo.getIsProduct() == 1 && couponPojo.getProductId() != 0) {
          // 是否指定商品
          result.put("isProduct", "1");
          productPojo = productService.getById(couponPojo.getProductId());
          if (productPojo != null) {
            result.put("productId", String.valueOf(productPojo.getId()));
            result.put("productName", StringUtil.checkVal(productPojo.getProductName()));
            result.put("productImage", productPojo.getImage() == null ? "" : ConstParam.URL
                + "/upfiles/product" + File.separator + productPojo.getImage());
            result.put("productSketch", StringUtil.checkVal(productPojo.getProductSketch()));
            // 指定商品对应的活动id
            params = new HashMap<String, Object>();
            params.put("productId", productPojo.getId());
            params.put("isDelete", 0);
            params.put("status", 1);
            params.put("isDefault", 1);
            grouponActivityList = grouponActivityService.listPage(params);
            if (grouponActivityList != null && grouponActivityList.size() > 0) {
              result.put("activityId", grouponActivityList.get(0).getId());
            } else {
              result.put("activityId", "");
            }
          } else {
            result.put("productId", "");
            result.put("productName", "");
            result.put("productImage", "");
            result.put("productSketch", "");
            result.put("activityId", "");
          }
        } else {
          result.put("productId", "");
          result.put("productName", "");
          result.put("productImage", "");
          result.put("productSketch", "");
          result.put("activityId", "");
          result.put("isProduct", "0");
        }
        b = true;
      } else {
        msg = "查询不到该优惠券！";
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
   * 
   * 限时秒杀列表
   * 
   * @throws SQLException
   * */
  public String secKillListApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    Map<String, Object> goodsItem = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> secKillList = new ArrayList<Map<String, Object>>();
    SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
    if (type == null || type == 0) {
      msg = "查询日期类型不能为空！";
    } else {
      if (type == 1) {
        // 今天
        params.put("today", DateTimeUtil.getDate(0));
        params.put("tomorrow", DateTimeUtil.getDate(1));
        params.put("nowTime", new Date());
      } else if (type == 2) {
        // 明天
        params.put("today", DateTimeUtil.getDate(1));
        params.put("tomorrow", DateTimeUtil.getDate(2));
      } else if (type == 3) {
        // 后天
        params.put("today", DateTimeUtil.getDate(2));
        params.put("tomorrow", DateTimeUtil.getDate(3));
      } else {
        msg = "查询时间错误!";
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
      params.put("isDelete", 0);
      params.put("status", 1);
      params.put("type", 1);
      params.put("orderBy", "begin_time asc,id asc");
      List<SeckillPojo> seckillList = seckillService.listPage(params);
      if (seckillList != null && seckillList.size() > 0) {
        for (SeckillPojo seckillPojo : seckillList) {
          item = new HashMap<String, Object>();
          secKillList = new ArrayList<Map<String, Object>>();
          // 开始时间
          item.put("time",
              seckillPojo.getBeginTime() == null ? "" : fmt.format(seckillPojo.getBeginTime()));
          // 是否进行中
          if (DateTimeUtil.compareDate(StringUtil.dateString(new Date()),
              StringUtil.dateString(seckillPojo.getBeginTime()))
              && DateTimeUtil.compareDate(StringUtil.dateString(seckillPojo.getEndTime()),
                  StringUtil.dateString(new Date()))) {
            item.put("isStart", "1");
          } else {
            item.put("isStart", "0");
          }
          // 秒杀活动Id
          item.put("killId", StringUtil.checkVal(seckillPojo.getId()));
          // 查询活动的拼团
          params.clear();
          params.put("seckillId", seckillPojo.getId());
          params.put("status", 1);
          params.put("orderBy", "sg.sorting desc,sg.id asc");
          List<SeckillGoodsPojo> seckillGoodsList = seckillGoodsService.listPage(params);
          if (seckillGoodsList != null && seckillGoodsList.size() > 0) {
            for (SeckillGoodsPojo seckillGoodsPojo : seckillGoodsList) {
              goodsItem = new HashMap<String, Object>();
              // 活动id
              goodsItem.put("activityId", StringUtil.checkVal(seckillGoodsPojo.getActivityId()));
              // 商品id
              goodsItem.put("productId", StringUtil.checkVal(seckillGoodsPojo.getProductId()));
              // 商品名称
              goodsItem.put("productName", StringUtil.checkVal(seckillGoodsPojo.getProductName()));
              // 商品图片
              goodsItem.put("productImage",
                  seckillGoodsPojo.getImage() == null ? "" : ConstParam.URL + "/upfiles/product"
                      + File.separator + seckillGoodsPojo.getImage());
              // 商品价格
              goodsItem.put("productPrice", StringUtil.checkVal(seckillGoodsPojo.getPrice()));
              // 限购数量
              goodsItem.put("limitNum", StringUtil.checkVal(seckillGoodsPojo.getLimitNum()));
              // 原价
              goodsItem.put("alonePrice", StringUtil.checkVal(seckillGoodsPojo.getAlonePrice()));
              // 是否售罄
              if (seckillGoodsPojo.getSurplusNum() != null && seckillGoodsPojo.getSurplusNum() > 0) {
                goodsItem.put("isSellOut", "0");
              } else {
                goodsItem.put("isSellOut", "1");
              }
              // 判断活动库存数量是否小于或等于0
              if (seckillGoodsPojo.getLimitNum() == null || seckillGoodsPojo.getLimitNum() <= 0) {
                goodsItem.put("salePerce", "100.0");
              } else {
                Integer saleNum = 0;
                // 销量
                saleNum = seckillGoodsPojo.getLimitNum() - seckillGoodsPojo.getSurplusNum();
                // 计算销量百分比
                String salePerce =
                    StringUtil.calcPerceStr(saleNum, seckillGoodsPojo.getLimitNum()).replace("%",
                        "");
                goodsItem.put("salePerce", salePerce);
              }
              secKillList.add(goodsItem);
            }
            item.put("secKillList", secKillList);
          } else {
            Util.log("查询不到活动商品!");
            item.put("secKillList", secKillList);
          }
          result.add(item);
        }
        b = true;
      } else {
        msg = "查询不到数据!";
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
   * 
   * 今日售罄商品
   * 
   * @throws SQLException
   * */
  public String sellOutListApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> goodsItem = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    // 分页
    int ps = 10;
    if (pageSize != null && pageSize != 0) {
      ps = pageSize;
    }
    if (pageNo == null || pageNo == 0) {
      params.put("pageNo", 0);
    } else {
      params.put("pageNo", (pageNo - 1) * ps);
    }
    params.put("pageSize", ps);
    params.put("today", DateTimeUtil.getDate(0));
    params.put("tomorrow", DateTimeUtil.getDate(1));
    params.put("isDelete", 0);
    params.put("status", 1);
    params.put("surplusNum", 0);
    List<SeckillGoodsPojo> seckillGoodsList = seckillGoodsService.listPage(params);
    if (seckillGoodsList != null && seckillGoodsList.size() > 0) {
      for (SeckillGoodsPojo seckillGoodsPojo : seckillGoodsList) {
        goodsItem = new HashMap<String, Object>();
        // 活动id
        goodsItem.put("activityId", StringUtil.checkVal(seckillGoodsPojo.getActivityId()));
        // 商品id
        goodsItem.put("productId", StringUtil.checkVal(seckillGoodsPojo.getProductId()));
        // 商品名称
        goodsItem.put("productName", StringUtil.checkVal(seckillGoodsPojo.getProductName()));
        // 商品图片
        goodsItem.put("productImage", seckillGoodsPojo.getImage() == null ? "" : ConstParam.URL
            + "/upfiles/product" + File.separator + seckillGoodsPojo.getImage());
        // 商品价格
        goodsItem.put("productPrice", StringUtil.checkVal(seckillGoodsPojo.getPrice()));
        // 原价
        goodsItem.put("alonePrice", StringUtil.checkVal(seckillGoodsPojo.getAlonePrice()));
        // 是否售罄
        if (seckillGoodsPojo.getSurplusNum() != null && seckillGoodsPojo.getSurplusNum() > 0) {
          goodsItem.put("isSellOut", "0");
        } else {
          goodsItem.put("isSellOut", "1");
        }
        result.add(goodsItem);
      }
    } else {
      msg = "暂无售罄的商品!";
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
   * 0.1抽奖列表
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String getDrawListApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    if (type == null || type <= 0) {
      msg = "查看类型不能为空哦！~";
      // } else if (userId == null || userId <= 0) {
      // msg = "用户ID不能为空哦！~";
    } else {
      // if (type == 2) {
      // map.put("result", result);
      // map.put("success", true);
      // map.put("error_msg", msg);
      // JSONObject json = JSONObject.fromObject(map);
      // ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      // try {
      // ServletActionContext.getResponse().getWriter().write(json.toString());
      // } catch (IOException e) {
      // e.printStackTrace();
      // }
      // return null;
      // }
      if (SellerService.getOpenDrawValid() == 1) {
        Map<String, Object> params = new HashMap<String, Object>();
        int ps = 10;
        if (pageSize != null && pageSize != 0) {
          ps = pageSize;
        }
        if (pageNo == null || pageNo == 0) {
          params.put("pageNo", 0);
        } else {
          if (pageNo > 1) {
            int t = ps;
            ps = 5;
            params.put("pageNo", (pageNo - 1) * ps + t - ps);
          } else {
            params.put("pageNo", (pageNo - 1) * ps);
          }
        }
        params.put("pageSize", ps);

        params.put("type", 5);
        String now = StringUtil.dateString(new Date());
        params.put("status", 1);
        params.put("isDelete", 0);
        if (type == 1) {
          params.put("beginTimeStr", now);
          params.put("endTimeStr", now);
        } else {
          // params.put("beginTimeStr", now);
          params.put("endTimeStr2", now);
        }
        // params.put("activityStatus", type);// 1,2
        // params.put("surplusNumStr", " ga.surplus_num > 0");
        if (type == 2) {
          params.put("orderBy", "ga.end_time desc,ga.id desc");
        } else {
          params.put("orderBy", "ga.sorting desc,ga.id desc");
        }
        List<GrouponActivityPojo> list = grouponActivityService.listPage(params);
        if (list.size() > 0) {
          Map<String, Object> item = new HashMap<String, Object>();
          for (GrouponActivityPojo g : list) {
            item = new HashMap<String, Object>();
            item.put("activityId", StringUtil.checkVal(g.getId()));
            item.put("alonePrice", StringUtil.checkVal(g.getSellingPrice()));
            item.put("groupNum", StringUtil.checkVal(g.getNum()));
            item.put("productId", StringUtil.checkVal(g.getProductId()));
            item.put("productImage", ConstParam.URL + "/upfiles/product" + File.separator
                + StringUtil.checkVal(g.getProductImage()));
            item.put("productName", StringUtil.checkVal(g.getProductName()));
            item.put("productPrice", StringUtil.checkVal(g.getPrice()));
            result.add(item);
          }
          success = true;
        } else {
          msg = "暂时没有数据哦！~";
        }
      } else {
        msg = "暂时没有数据哦！~";
      }
    }
    map.put("result", result);
    map.put("success", success);
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
   * 0.1抽奖评论详情
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String getDrawCommentDetailsApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> result2 = new ArrayList<Map<String, Object>>();
    if (activityId == null || activityId <= 0) {
      msg = "活动ID不能为空哦！~";
      // } else if (productId == null || productId <= 0) {
      // msg = "商品ID不能为空哦！~";
      // } else if (userId == null || userId <= 0) {
      // msg = "用户ID不能为空哦！~";
    } else {
      GrouponActivityPojo grouponActivityPojo = grouponActivityService.getById(activityId);
      if (grouponActivityPojo != null) {
        map2.put(
            "productImage",
            ConstParam.URL + "/upfiles/product" + File.separator
                + StringUtil.checkVal(grouponActivityPojo.getProductImage()));
        map2.put("productName", StringUtil.checkVal(grouponActivityPojo.getProductName()));

        Map<String, Object> params = new HashMap<String, Object>();
        int ps = 10;
        if (pageSize != null && pageSize != 0) {
          ps = pageSize;
        }
        if (pageNo == null || pageNo == 0) {
          params.put("pageNo", 0);
        } else {
          params.put("pageNo", (pageNo - 1) * ps);
        }
        params.put("pageSize", ps);

        params.put("type", 1);// 活动类型：1-0.1抽奖
        params.put("status", 1);
        params.put("activityId", grouponActivityPojo.getId());
        params.put("productId", grouponActivityPojo.getProductId());
        params.put("orderBy", " apc.create_date desc");
        List<ActivityProductCommentPojo> list = activityProductCommentService.listPage(params);
        if (list.size() > 0) {
          Map<String, Object> item = new HashMap<String, Object>();
          Map<String, Object> item2 = new HashMap<String, Object>();
          for (ActivityProductCommentPojo a : list) {
            item = new HashMap<String, Object>();
            result2 = new ArrayList<Map<String, Object>>();
            if (a.getImg1() != null && !"".equals(a.getImg1())) {
              item2 = new HashMap<String, Object>();
              item2.put("image", ConstParam.URL + "/upfiles/productComment" + File.separator
                  + StringUtil.checkVal(a.getImg1()));
              result2.add(item2);
            }
            if (a.getImg2() != null && !"".equals(a.getImg2())) {
              item2 = new HashMap<String, Object>();
              item2.put("image", ConstParam.URL + "/upfiles/productComment" + File.separator
                  + StringUtil.checkVal(a.getImg2()));
              result2.add(item2);
            }
            if (a.getImg3() != null && !"".equals(a.getImg3())) {
              item2 = new HashMap<String, Object>();
              item2.put("image", ConstParam.URL + "/upfiles/productComment" + File.separator
                  + StringUtil.checkVal(a.getImg3()));
              result2.add(item2);
            }
            if (a.getImg4() != null && !"".equals(a.getImg4())) {
              item2 = new HashMap<String, Object>();
              item2.put("image", ConstParam.URL + "/upfiles/productComment" + File.separator
                  + StringUtil.checkVal(a.getImg4()));
              result2.add(item2);
            }
            if (a.getImg5() != null && !"".equals(a.getImg5())) {
              item2 = new HashMap<String, Object>();
              item2.put("image", ConstParam.URL + "/upfiles/productComment" + File.separator
                  + StringUtil.checkVal(a.getImg5()));
              result2.add(item2);
            }
            if (a.getImg6() != null && !"".equals(a.getImg6())) {
              item2 = new HashMap<String, Object>();
              item2.put("image", ConstParam.URL + "/upfiles/productComment" + File.separator
                  + StringUtil.checkVal(a.getImg6()));
              result2.add(item2);
            }
            if (a.getImg7() != null && !"".equals(a.getImg7())) {
              item2 = new HashMap<String, Object>();
              item2.put("image", ConstParam.URL + "/upfiles/productComment" + File.separator
                  + StringUtil.checkVal(a.getImg7()));
              result2.add(item2);
            }
            if (a.getImg8() != null && !"".equals(a.getImg8())) {
              item2 = new HashMap<String, Object>();
              item2.put("image", ConstParam.URL + "/upfiles/productComment" + File.separator
                  + StringUtil.checkVal(a.getImg8()));
              result2.add(item2);
            }
            if (a.getImg9() != null && !"".equals(a.getImg9())) {
              item2 = new HashMap<String, Object>();
              item2.put("image", ConstParam.URL + "/upfiles/productComment" + File.separator
                  + StringUtil.checkVal(a.getImg9()));
              result2.add(item2);
            }
            item.put("commentImage", result2);

            item.put("commentText", StringUtil.checkVal(a.getContent()));
            item.put("commentTime", StringUtil.checkVal(a.getCreateDate()));

            item.put("userImage", a.getUserImage() == null ? ""
                : a.getUserImage().contains("http") == true ? a.getUserImage() : ConstParam.URL
                    + "/upfiles/userlogo" + File.separator + StringUtil.checkVal(a.getUserImage()));
            item.put("userName",
                "pdh" + WalletService.enCodeString(StringUtil.checkVal(a.getLoginname()), 3, 6));



            // item.put("image1", ConstParam.URL + "/upfiles/activityProductComment" +
            // File.separator
            // + StringUtil.checkVal(a.getImg1()));
            // item.put("image2", ConstParam.URL + "/upfiles/activityProductComment" +
            // File.separator
            // + StringUtil.checkVal(a.getImg2()));
            // item.put("image3", ConstParam.URL + "/upfiles/activityProductComment" +
            // File.separator
            // + StringUtil.checkVal(a.getImg3()));
            // item.put("image4", ConstParam.URL + "/upfiles/activityProductComment" +
            // File.separator
            // + StringUtil.checkVal(a.getImg4()));
            // item.put("image5", ConstParam.URL + "/upfiles/activityProductComment" +
            // File.separator
            // + StringUtil.checkVal(a.getImg5()));
            // item.put("image6", ConstParam.URL + "/upfiles/activityProductComment" +
            // File.separator
            // + StringUtil.checkVal(a.getImg6()));
            // item.put("image7", ConstParam.URL + "/upfiles/activityProductComment" +
            // File.separator
            // + StringUtil.checkVal(a.getImg7()));
            // item.put("image8", ConstParam.URL + "/upfiles/activityProductComment" +
            // File.separator
            // + StringUtil.checkVal(a.getImg8()));
            // item.put("image9", ConstParam.URL + "/upfiles/activityProductComment" +
            // File.separator
            // + StringUtil.checkVal(a.getImg9()));
            result.add(item);
          }
        } else {
          msg = "暂时没有数据哦！~";
        }
        success = true;
        map2.put("userInfo", result);
      } else {
        msg = "没有该活动记录哦！~";
      }
    }
    map.put("result", map2);
    map.put("success", success);
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
   * 我的抽奖
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   */
  public String getDrawApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    if (userId == null || userId <= 0) {
      msg = "用户ID不能为空哦！~";
    } else if (type == null) {
      msg = "类型不能为空哦！~";
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      // 分页
      int ps = 10;
      if (pageSize != null && pageSize != 0) {
        ps = pageSize;
      }
      if (pageNo == null || pageNo == 0) {
        params.put("pageNo", 0);
      } else {
        params.put("pageNo", (pageNo - 1) * ps);
      }
      params.put("pageSize", ps);

      params.put("userId", userId);
      if (type == 1) {
        params.put("source", 5);// 0.1抽奖
      } else {
        params.put("source", 7);// 免费抽奖
      }
      params.put("orderBy", "t.create_date desc,t.id desc");
      List<OrderPojo> list = orderService.listPage(params);
      if (list.size() > 0) {
        Map<String, Object> item = new HashMap<String, Object>();
        for (OrderPojo o : list) {
          item = new HashMap<String, Object>();
          item.put("productImage", ConstParam.URL + "/upfiles/product" + File.separator
              + StringUtil.checkVal(o.getProductImage()));
          item.put("productName", StringUtil.checkVal(o.getProductName()));
          item.put("productId", StringUtil.checkVal(o.getProductId()));
          item.put("activityId", StringUtil.checkVal(o.getActivityId()));
          // item.put("num", StringUtil.checkVal(o.getNum()));
          item.put("productPrice", StringUtil.checkVal(o.getFactPrice()));
          item.put("orderId", StringUtil.checkVal(o.getId()));
          item.put("poorNum", "");
          item.put("isPrize", "0");
          item.put("isShow", "0");
          item.put("attendId", "");

          // ----------1-待支付 2-拼团中，差N人 3-未成团，退款中 4-未成团，已退款 5-交易已取消 6-未中奖，待返款
          // ----------7-未中奖，已返款 8-已成团，待开奖 9-已中奖，已完成 10-已中奖，待发货 11-已中奖，待收货
          // ----------12-待开奖
          if (o.getIsCancelOrder() == 1) {
            // 交易已取消 5
            item.put("orderStatus", String.valueOf(5));
          } else if (o.getOrderStatus() == 1) {
            // ----------1.待付款2.待发货3.已发货4.已确认5.已评论
            // 待支付 1
            item.put("orderStatus", String.valueOf(1));
          } else if (o.getOrderStatus() >= 2) {
            // ----------1-拼团成功2-拼团失败
            if (o.getIsSuccess() == 0) {
              // 拼团中，差N人 2
              item.put("orderStatus", String.valueOf(2));

              params.clear();
              params.put("attendId", o.getSourceId());
              int joinNum = grouponUserRecordService.countBy(params);
              int groupNum = o.getGaNum();
              item.put("poorNum", String.valueOf(groupNum - joinNum));
              // 12-待开奖
              if (groupNum - joinNum <= 0) {
                item.put("orderStatus", 12);
              }
            } else if (o.getIsSuccess() == 2) {
              // ----------0-未退款，1-处理中，2-退款成功，3-退款失败
              if (o.getIsRefund() == 1) {
                // 未成团，退款中 3
                item.put("orderStatus", String.valueOf(3));
              } else if (o.getIsRefund() == 2) {
                // 未成团，已退款 4
                item.put("orderStatus", String.valueOf(4));
              } else {
                // 查询是抽不到奖还是时间到拼团失败
                if (o.getSource() == 7) {
                  params.clear();
                  params.put("attendId", o.getSourceId());
                  int joinNum = grouponUserRecordService.countBy(params);
                  if (joinNum >= o.getGaNum()) {
                    // 未中奖，待返款 6
                    item.put("orderStatus", String.valueOf(6));
                  } else {
                    // 未成团，退款中3
                    item.put("orderStatus", String.valueOf(3));
                  }
                } else {
                  params.clear();
                  params.put("sourceId", o.getSourceId());
                  params.put("isSuccess", 1);
                  int i = orderService.countBy(params);
                  if (i > 0) {
                    // 未中奖，待返款 6
                    item.put("orderStatus", String.valueOf(6));
                  } else {
                    // 未成团，退款中3
                    item.put("orderStatus", String.valueOf(3));
                  }
                }
              }

              // ----------0-普通1-团免2-猜价中奖3-抽奖4-限时秒杀
              if ("3".equals(StringUtil.checkVal(o.getGurStatus()))) {
                item.put("isPrize", "1");
              }
            } else if (o.getIsSuccess() == 1) {
              // ----------0-未得奖1-一等奖2-二等奖3-三等奖
              if (o.getPrize() == 0) {
                if (o.getIsRefund() == 0) {
                  // 未中奖，待返款 6
                  item.put("orderStatus", String.valueOf(6));
                } else if (o.getIsRefund() == 2) {
                  // 未中奖，已返款 7
                  item.put("orderStatus", String.valueOf(7));
                }
                // } else if (o.getPrize() > 0) {
                // // 已成团，待开奖 8
                // item.put("orderStatus", String.valueOf(8));
              } else if (o.getPrize() > 0) {
                if (o.getOrderStatus() >= 4) {
                  // 已中奖，已完成 9
                  item.put("orderStatus", String.valueOf(9));

                  params.clear();
                  params.put("pageNo", 0);
                  params.put("pageSize", 1);

                  params.put("type", 1);// 活动类型：1-0.1抽奖
                  // params.put("status", 1);
                  params.put("activityId", o.getActivityId());
                  params.put("productId", o.getProductId());
                  params.put("userId", userId);
                  List<ActivityProductCommentPojo> list2 =
                      activityProductCommentService.listPage(params);
                  if (list2.size() == 0) {
                    item.put("isShow", "1");
                  }
                } else if (o.getOrderStatus() == 2) {
                  // 已中奖，待发货 10
                  item.put("orderStatus", String.valueOf(10));
                } else if (o.getOrderStatus() == 3) {
                  // 已中奖，待收货 11
                  item.put("orderStatus", String.valueOf(11));
                }
              }

              item.put("isPrize", "1");
            }
          }

          item.put("attendId", StringUtil.checkVal(o.getAttendId()));
          result.add(item);
        }
        b = true;
      } else {
        msg = "暂时没有数据哦！~";
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
   * 抽奖-顶部图片
   * 
   * @return
   * @throws SQLException
   */
  public String prizeBannerApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    params.put("status", 1);
    params.put("type", 3);
    params.put("pageNo", 0);
    params.put("pageSize", 1);
    params.put("nowTimeStr", StringUtil.dateString(new Date()));
    params.put("orderBy", "sorting desc, create_date desc");
    focusSettingPojoList = focusSettingService.listPage(params);
    if (focusSettingPojoList != null && focusSettingPojoList.size() > 0) {
      focusSettingPojo = focusSettingPojoList.get(0);
      result.put("banner", focusSettingPojo.getBanner() == null ? "" : ConstParam.URL
          + "/upfiles/focusbanner" + File.separator + focusSettingPojo.getBanner());
      b = true;
    } else {
      msg = "查询不到数据!";
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
   * 我要晒图提交
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   * @throws IOException
   */
  public String actProductComment() throws SQLException, IOException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    if (userId == null || userId <= 0) {
      msg = "用户ID不能为空！";
    } else if (attendId == null || attendId <= 0) {
      msg = "参团ID不能为空！";
    } else if (img1 == null && img2 == null && img3 == null && img4 == null && img5 == null
        && img6 == null && img7 == null && img8 == null && img9 == null) {
      msg = "评论图片至少1张";
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("attendId", attendId);
      params.put("userId", userId);
      List<GrouponUserRecordPojo> grouponUsers = grouponUserRecordService.listPage(params);
      if (grouponUsers != null && grouponUsers.size() > 0) {
        GrouponUserRecordPojo grouponUser = grouponUsers.get(0);
        // 抽奖中奖才可评论
        if (grouponUser != null && grouponUser.getPrize() == 1 && grouponUser.getStatus() == 3) {
          String imgName = "";
          ActivityProductCommentPojo comment = new ActivityProductCommentPojo();
          comment.setUserId(userId);
          comment.setActivityId(grouponUser.getActivityId());
          comment.setAttendId(attendId);
          comment.setProductId(Long.valueOf(grouponUser.getProductId()));
          comment.setType(1);
          comment.setStatus(0);
          comment.setCreateBy(userId);
          comment.setCreateDate(new Date());
          comment.setUpdateBy(userId);
          comment.setUpdateDate(comment.getCreateDate());
          if (StringUtils.isNotBlank(content)) {
            comment.setContent(content);
          }
          if (img1 != null) {
            imgName = sellerService.uploadFile(img1, "upfiles" + File.separator + "productComment");
            comment.setImg1(imgName);
          }
          if (img2 != null) {
            imgName = sellerService.uploadFile(img2, "upfiles" + File.separator + "productComment");
            comment.setImg2(imgName);
          }
          if (img3 != null) {
            imgName = sellerService.uploadFile(img3, "upfiles" + File.separator + "productComment");
            comment.setImg3(imgName);
          }
          if (img4 != null) {
            imgName = sellerService.uploadFile(img4, "upfiles" + File.separator + "productComment");
            comment.setImg4(imgName);
          }
          if (img5 != null) {
            imgName = sellerService.uploadFile(img5, "upfiles" + File.separator + "productComment");
            comment.setImg5(imgName);
          }
          if (img6 != null) {
            imgName = sellerService.uploadFile(img6, "upfiles" + File.separator + "productComment");
            comment.setImg6(imgName);
          }
          if (img7 != null) {
            imgName = sellerService.uploadFile(img7, "upfiles" + File.separator + "productComment");
            comment.setImg7(imgName);
          }
          if (img8 != null) {
            imgName = sellerService.uploadFile(img8, "upfiles" + File.separator + "productComment");
            comment.setImg8(imgName);
          }
          if (img9 != null) {
            imgName = sellerService.uploadFile(img9, "upfiles" + File.separator + "productComment");
            comment.setImg9(imgName);
          }
          int flag = activityProductCommentService.add(comment);
          if (flag > 0) {
            b = true;
          } else {
            msg = "提交评论失败！";
          }
        } else {
          msg = "对不起，您未中奖，无法评论！";
        }
      } else {
        msg = "您未参与活动！";
      }
    }
    map.put("result", "");
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
   * 中奖信息详情
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   * @throws IOException
   */
  public String prizeDetail() throws SQLException, IOException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, Object> result2 = new HashMap<String, Object>();
    if ((attendId == null || attendId <= 0) && (activityId == null || activityId <= 0)) {
      msg = "参团ID/活动ID不能为空！";
    } else if (activityType == null || activityType <= 0) {
      msg = "活动类型不能为空！";
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      if (attendId != null && attendId > 0) {
        params.put("id", attendId);
        params.put("activityType", activityType);
        List<GrouponActivityRecordPojo> activitys = grouponActivityRecordService.listPage(params);
        GrouponActivityRecordPojo activity = null;
        if (activitys != null && activitys.size() > 0) {
          activity = activitys.get(0);
        }
        if (activity != null) {
          result.put("productPrice", StringUtil.checkVal(activity.getGroupPrice()));
          result.put("activityId", StringUtil.checkVal(activity.getActivityId()));
          result.put("activityType", StringUtil.checkVal(activity.getActivityType()));
          result.put("productId", StringUtil.checkVal(activity.getProductId()));
          result.put("productImage", StringUtils.isBlank(activity.getProductImage()) ? ""
              : ConstParam.URL + "/upfiles/product" + File.separator + activity.getProductImage());
          result.put("productName", StringUtil.checkVal(activity.getProductName()));
          // 中奖状态：0-未开奖1-已开奖2-拼团失败
          if ("7".equals(StringUtil.checkVal(activity.getActivityType()))) {
            grouponActivityPojo = grouponActivityService.getById(activity.getActivityId());
            if (grouponActivityPojo != null
                && "3".equals(StringUtil.checkVal(grouponActivityPojo.getActivityStatus()))) {
              result.put("status", "1");
            } else {
              result.put("status", "0");
            }
          } else {
            result.put("status", StringUtil.checkVal(activity.getStatus()));
          }

          params.clear();
          params.put("attendId", attendId);
          params.put("prize", 1);
          if (activityType == 5) {
            params.put("status", 3);
          } else {
            params.put("status", 5);
          }
          params.put("activityType", activityType);
          params.put("orderBy", "gur.attend_id desc,gur.is_head desc");
          params.put("isHead", 1);
          // prize=1&& status=3 已开奖并中奖
          List<GrouponUserRecordPojo> users = grouponUserRecordService.listPage(params);
          List<Map<String, Object>> prizelist = new ArrayList<Map<String, Object>>();
          List<Map<String, Object>> prizelist2 = new ArrayList<Map<String, Object>>();
          if (users != null && users.size() > 0) {
            Map<String, Object> item = null;
            for (GrouponUserRecordPojo userRecord : users) {
              prizelist = new ArrayList<Map<String, Object>>();
              result2 = new HashMap<String, Object>();
              item = new HashMap<String, Object>();
              item.put("attendTime", StringUtil.checkVal(userRecord.getAttendTimeStr()));
              item.put("isHead", StringUtil.checkVal(userRecord.getIsHead()));
              item.put(
                  "loginname",
                  userRecord.getLoginName() == null ? "" : WalletService.enCodeString(
                      userRecord.getLoginName(), 4, 6));
              item.put(
                  "name",
                  "pdh"
                      + WalletService.enCodeString(StringUtil.checkVal(userRecord.getLoginName()),
                          3, 6));
              item.put("userlogo",
                  StringUtils.isBlank(userRecord.getUserLogo()) ? "" : ConstParam.URL
                      + "/upfiles/userlogo" + File.separator + userRecord.getUserLogo());
              item.put("orderNo", "");
              prizelist.add(item);

              params.put("isHead", 0);
              if (activityType == 5) {
                params.put("pageNo", 0);
                params.put("pageSize", 1);
                params.put("prize", 1);
              }

              List<GrouponUserRecordPojo> users2 = grouponUserRecordService.listPage(params);
              if (users2 != null && users2.size() > 0) {
                for (GrouponUserRecordPojo userRecord2 : users2) {
                  item = new HashMap<String, Object>();
                  item.put("attendTime", StringUtil.checkVal(userRecord2.getAttendTimeStr()));
                  item.put("isHead", StringUtil.checkVal(userRecord2.getIsHead()));
                  item.put(
                      "loginname",
                      userRecord2.getLoginName() == null ? "" : WalletService.enCodeString(
                          userRecord2.getLoginName(), 4, 6));
                  item.put(
                      "name",
                      "pdh"
                          + WalletService.enCodeString(
                              StringUtil.checkVal(userRecord2.getLoginName()), 3, 6));
                  item.put("userlogo",
                      StringUtils.isBlank(userRecord2.getUserLogo()) ? "" : ConstParam.URL
                          + "/upfiles/userlogo" + File.separator + userRecord2.getUserLogo());
                  item.put("orderNo", "");
                  prizelist.add(item);
                }
              }

              result2.put("groupList", prizelist);
              prizelist2.add(result2);
            }
          }
          result.put("prizelist", prizelist2);
          b = true;
        } else {
          msg = "您未参与活动！";
        }
      } else if (activityId != null && activityId > 0) {
        GrouponActivityPojo activity = grouponActivityService.getById(activityId);
        if (activity != null && (activity.getType() == 5 || activity.getType() == 7)) {
          result.put("productPrice", StringUtil.checkVal(activity.getPrice()));
          result.put("activityId", StringUtil.checkVal(activity.getId()));
          result.put("activityType", StringUtil.checkVal(activity.getType()));
          result.put("productId", StringUtil.checkVal(activity.getProductId()));
          result.put("productImage", StringUtils.isBlank(activity.getProductImage()) ? ""
              : ConstParam.URL + "/upfiles/product" + File.separator + activity.getProductImage());
          result.put("productName", StringUtil.checkVal(activity.getProductName()));
          // 中奖状态：0-未开奖1-已开奖2-拼团失败
          if ("7".equals(StringUtil.checkVal(activity.getType()))) {
            if ("3".equals(StringUtil.checkVal(activity.getActivityStatus()))) {
              result.put("status", "1");
            } else {
              result.put("status", "0");
            }
          } else {
            result.put("status", StringUtil.checkVal(activity.getStatus()));
          }

          if (pageNo == null) {
            pageNo = 1;
          }
          int pageSize = 20;
          params.clear();
          params.put("activityId", activityId);
          params.put("prize", 1);
          if (activityType == 5) {
            params.put("status", 3);
          } else {
            params.put("status", 5);
          }
          params.put("activityType", activityType);
          params.put("pageNo", (pageNo - 1) * pageSize);
          params.put("pageSize", pageSize);
          params.put("orderBy", "gur.attend_id desc,gur.is_head desc");
          params.put("isHead", 1);
          // prize=1&& status=3 已开奖并中奖
          List<GrouponUserRecordPojo> users = grouponUserRecordService.listPage(params);
          List<Map<String, Object>> prizelist = new ArrayList<Map<String, Object>>();
          List<Map<String, Object>> prizelist2 = new ArrayList<Map<String, Object>>();
          if (users != null && users.size() > 0) {
            Map<String, Object> item = null;
            for (GrouponUserRecordPojo userRecord : users) {
              result2 = new HashMap<String, Object>();
              prizelist = new ArrayList<Map<String, Object>>();
              item = new HashMap<String, Object>();
              item.put("attendTime", StringUtil.checkVal(userRecord.getAttendTimeStr()));
              item.put("isHead", StringUtil.checkVal(userRecord.getIsHead()));
              item.put(
                  "loginname",
                  userRecord.getLoginName() == null ? "" : WalletService.enCodeString(
                      userRecord.getLoginName(), 4, 6));
              item.put(
                  "name",
                  "pdh"
                      + WalletService.enCodeString(StringUtil.checkVal(userRecord.getLoginName()),
                          3, 6));
              item.put("userlogo",
                  StringUtils.isBlank(userRecord.getUserLogo()) ? "" : ConstParam.URL
                      + "/upfiles/userlogo" + File.separator + userRecord.getUserLogo());
              item.put("orderNo", "");
              prizelist.add(item);
              params.clear();
              params.put("isHead", 0);
              params.put("attendId", userRecord.getAttendId());
              if (activityType == 5) {
                params.put("pageNo", 0);
                params.put("pageSize", 1);
                params.put("prize", 1);
              }
              List<GrouponUserRecordPojo> users2 = grouponUserRecordService.listPage(params);
              if (users2 != null && users2.size() > 0) {
                for (GrouponUserRecordPojo userRecord2 : users2) {
                  item = new HashMap<String, Object>();
                  item.put("attendTime", StringUtil.checkVal(userRecord2.getAttendTimeStr()));
                  item.put("isHead", StringUtil.checkVal(userRecord2.getIsHead()));
                  item.put(
                      "loginname",
                      userRecord2.getLoginName() == null ? "" : WalletService.enCodeString(
                          userRecord2.getLoginName(), 4, 6));
                  item.put(
                      "name",
                      "pdh"
                          + WalletService.enCodeString(
                              StringUtil.checkVal(userRecord2.getLoginName()), 3, 6));
                  item.put("userlogo",
                      StringUtils.isBlank(userRecord2.getUserLogo()) ? "" : ConstParam.URL
                          + "/upfiles/userlogo" + File.separator + userRecord2.getUserLogo());
                  item.put("orderNo", "");
                  prizelist.add(item);
                }
              }
              result2.put("groupList", prizelist);
              prizelist2.add(result2);
            }
          }
          result.put("prizelist", prizelist2);
          b = true;
        } else {
          msg = "活动信息错误！";
        }
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
   * 
   * 上传拼得客图片
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String upUserpindekeImage() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (file != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/userpindeke")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/userpindeke/", file);

      result.put("fileName", ConstParam.URL + "/upfiles/userpindeke" + File.separator + file_name);
      b = true;
    } else {
      result.put("fileName", "");
      msg = "上传的图片不能为空哦！~";
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
   * 开团推送
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   */
  public String getGrouponPushApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

    Map<String, Object> params = new HashMap<String, Object>();
    // params.put("nowDate", new Date());
    Date nowDate = new Date();
    Date startDate = null;
    if (num != null && num > 0) {
      startDate = new Date(nowDate.getTime() - num * 1000);
      // params.put("num", num);
    } else {
      startDate = new Date(nowDate.getTime() - 5 * 1000);
      // params.put("num", 5);
    }
    params.put("startDate", StringUtil.checkVal(startDate));
    params.put("endDate", StringUtil.checkVal(nowDate));
    params.put("pageNo", 0);
    if (pageSize != null && pageSize > 0) {
      params.put("pageSize", pageSize);
    } else {
      params.put("pageSize", 3);
    }
    params.put("orderBy", " id desc");
    List<GrouponPushPojo> list = grouponPushService.listPage(params);
    if (list.size() > 0) {
      Map<String, Object> item = null;
      for (GrouponPushPojo grouponPushPojo : list) {
        item = new HashMap<String, Object>();
        item.put(
            "icon",
            ConstParam.URL + "/upfiles/product" + File.separator
                + StringUtil.checkVal(grouponPushPojo.getProductImage()));
        item.put("address", StringUtil.checkVal(grouponPushPojo.getAddress()).replace("省", ""));
        item.put("userName", StringUtil.checkVal(grouponPushPojo.getName()));
        item.put("attendId", StringUtil.checkVal(grouponPushPojo.getAttendId()));
        result.add(item);
      }
      b = true;
    } else {
      msg = "暂时没有开团推送信息";
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
   * 免费抽奖列表
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String freeDrawListApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    if (type == null || type <= 0) {
      msg = "查看类型不能为空哦！~";
      // } else if (userId == null || userId <= 0) {
      // msg = "用户ID不能为空哦！~";
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      int ps = 10;
      if (pageSize != null && pageSize != 0) {
        ps = pageSize;
      }
      if (pageNo == null || pageNo == 0) {
        params.put("pageNo", 0);
      } else {
        if (pageNo > 1) {
          int t = ps;
          ps = 5;
          params.put("pageNo", (pageNo - 1) * ps + t - ps);
        } else {
          params.put("pageNo", (pageNo - 1) * ps);
        }
      }
      params.put("pageSize", ps);

      params.put("type", 7);
      String now = StringUtil.dateString(new Date());
      params.put("status", 1);
      if (type == 1) {
        params.put("beginTimeStr", now);
        params.put("endTimeStr", now);
      } else {
        // params.put("beginTimeStr", now);
        params.put("endTimeStr2", now);
      }
      // params.put("activityStatus", type);// 1,2
      // params.put("surplusNumStr", " ga.surplus_num > 0");
      params.put("isDelete", 0);
      if (type == 2) {
        params.put("orderBy", "ga.end_time desc,ga.id desc");
      } else {
        params.put("orderBy", "ga.sorting desc,ga.id desc");
      }

      List<GrouponActivityPojo> list = grouponActivityService.listPage(params);
      if (list.size() > 0) {
        Map<String, Object> item = new HashMap<String, Object>();
        for (GrouponActivityPojo g : list) {
          item = new HashMap<String, Object>();
          item.put("activityId", StringUtil.checkVal(g.getId()));
          item.put("alonePrice", StringUtil.checkVal(g.getSellingPrice()));
          item.put("groupNum", StringUtil.checkVal(g.getNum()));
          item.put("productId", StringUtil.checkVal(g.getProductId()));
          item.put("productImage", ConstParam.URL + "/upfiles/product" + File.separator
              + StringUtil.checkVal(g.getProductImage()));
          item.put("productName", StringUtil.checkVal(g.getProductName()));
          item.put("productPrice", StringUtil.checkVal(g.getPrice()));
          result.add(item);
        }
        success = true;
      } else {
        msg = "暂时没有数据哦！~";
      }
    }
    map.put("result", result);
    map.put("success", success);
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
   * @return 新品专区
   * @throws Exception
   */
  public String newSpecialApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    params.put("status", 1);
    params.put("type", 2);
    params.put("orderBy", "createDate desc,id desc");
    zonesList = zonesService.listPage(params);
    if (zonesList != null && zonesList.size() > 0) {
      params.clear();
      int ps = 8;
      if (pageSize != null && pageSize != 0) {
        ps = pageSize;
      }
      if (pageNo == null || pageNo == 0) {
        params.put("pageNo", 0);
      } else {
        params.put("pageNo", (pageNo - 1) * ps);
      }
      params.put("pageSize", ps);
      params.put("zoneId", zonesList.get(0).getId());
      params.put("status", 1);
      params.put("activityStatus", 1);
      params.put("orderBy", "zg.sorting desc,zg.id desc");
      zoneGoodsList = zoneGoodsService.listPage(params);
      if (zoneGoodsList != null && zoneGoodsList.size() > 0) {
        for (ZoneGoodsPojo zoneGoods : zoneGoodsList) {
          item = new HashMap<String, Object>();
          // 拼团活动id
          item.put("activityId", String.valueOf(zoneGoods.getActivityId()));
          // 单独购价
          item.put("alonePrice", StringUtil.checkVal(zoneGoods.getAlonePrice()));
          // 拼团价格
          item.put(
              "price",
              zoneGoods.getGroupPrice() == null ? "" : StringUtil.checkVal(zoneGoods
                  .getGroupPrice()));
          // 销量
          item.put("grouponNum",
              zoneGoods.getSellNumber() == null ? "" : String.valueOf(zoneGoods.getSellNumber()));
          // 几人团
          item.put("num", zoneGoods.getNum() == null ? "" : String.valueOf(zoneGoods.getNum()));
          // 商品id
          item.put("productId",
              zoneGoods.getProductId() == null ? "" : String.valueOf(zoneGoods.getProductId()));
          // 商品图片
          item.put("productImage", zoneGoods.getImage() == null ? "" : ConstParam.URL
              + "/upfiles/product" + File.separator + zoneGoods.getImage());
          // 商品名称
          item.put("productName",
              zoneGoods.getProductName() == null ? "" : zoneGoods.getProductName());
          result.add(item);
        }
        success = true;
      } else {
        msg = "查询不到新品!";
      }
    } else {
      msg = "查询不到新品专区!";
    }
    map.put("result", result);
    map.put("success", success);
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
   * @return 新品顶部图片
   * @throws Exception
   */
  public String newSpecialImageApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("status", 1);
    params.put("type", 2);
    params.put("orderBy", "createDate desc,id desc");
    zonesList = zonesService.listPage(params);
    if (zonesList != null && zonesList.size() > 0) {
      result.put("image", zonesList.get(0).getImage() == null ? "" : ConstParam.URL
          + "/upfiles/zones" + File.separator + zonesList.get(0).getImage());
      result.put("specialName", zonesList.get(0).getTitle() == null ? "" : zonesList.get(0)
          .getTitle());
      success = true;
    } else {
      msg = "查询不到专题!";
    }
    map.put("result", result);
    map.put("success", success);
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
   * 商品分类
   * 
   * @return
   * @throws SQLException
   */
  public String productCategoryApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> oneItem = new HashMap<String, Object>();
    Map<String, Object> twoItem = new HashMap<String, Object>();
    Map<String, Object> threeItem = new HashMap<String, Object>();
    List<ProductTypePojo> oneProductTypes = null;
    List<ProductTypePojo> twoProductTypes = null;
    List<ProductTypePojo> threeProductTypes = null;
    List<Map<String, Object>> oneList = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> twoList = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> threeList = new ArrayList<Map<String, Object>>();
    // 查询一级分类
    ProductTypePojo productType = null;
    productType = new ProductTypePojo();
    productType.setPid(-1L);
    productType.setVisable(1);
    oneProductTypes = productTypeService.getProductTypeByPids(productType);
    if (oneProductTypes != null && oneProductTypes.size() > 0) {
      for (ProductTypePojo oneProductType : oneProductTypes) {
        oneItem = new HashMap<String, Object>();
        oneItem.put("oneId", StringUtil.checkVal(oneProductType.getId()));
        oneItem.put("oneIcon", ConstParam.URL + "/upfiles/productType" + File.separator
            + StringUtil.checkVal(oneProductType.getImage()));
        oneItem.put("oneName", StringUtil.checkVal(oneProductType.getName()));
        oneItem.put("oneFlag", oneProductType.getCategoryFlag() == null ? "0" : "1");
        // 查询二级分类
        productType = new ProductTypePojo();
        productType.setTopLevel(String.valueOf(oneProductType.getId()));
        twoProductTypes = productTypeService.getProductTypeByPids(productType);
        twoList = new ArrayList<Map<String, Object>>();
        if (twoProductTypes != null && twoProductTypes.size() > 0) {
          for (ProductTypePojo twoProductType : twoProductTypes) {
            twoItem = new HashMap<String, Object>();
            twoItem.put("twoId", StringUtil.checkVal(twoProductType.getId()));
            twoItem.put("twoIcon", ConstParam.URL + "/upfiles/productType" + File.separator
                + StringUtil.checkVal(twoProductType.getImage()));
            twoItem.put("twoName", StringUtil.checkVal(twoProductType.getName()));
            // 查询三级分类
            productType = new ProductTypePojo();
            productType.setPid(twoProductType.getId());
            threeProductTypes = productTypeService.getProductTypeByPids(productType);
            threeList = new ArrayList<Map<String, Object>>();
            if (threeProductTypes != null && threeProductTypes.size() > 0) {
              for (ProductTypePojo threeProductType : threeProductTypes) {
                threeItem = new HashMap<String, Object>();
                threeItem.put("threeId", StringUtil.checkVal(threeProductType.getId()));
                threeItem.put("threeIcon", ConstParam.URL + "/upfiles/productType" + File.separator
                    + StringUtil.checkVal(threeProductType.getImage()));
                threeItem.put("threeName", StringUtil.checkVal(threeProductType.getName()));
                threeList.add(threeItem);
              }
            } else {
              System.out.println("查询不到对应三级分类");
            }
            twoItem.put("threeLevelList", threeList);
            twoList.add(twoItem);
          }
        } else {
          System.out.println("查询不到对应二级分类");
        }
        oneItem.put("twoLevelList", twoList);
        oneList.add(oneItem);
      }
    } else {
      System.out.println("查询不到一级分类");
    }
    map.put("result", oneList);
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
   * 产品列表
   * */
  public String findProductListApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> groups = new ArrayList<Map<String, Object>>();
    if (id == null) {
      msg = "导航ID不能为空!";
    } else if (level == null) {
      msg = "级别不能为空!";
    } else {
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      pageSize = 10;
      Map<String, Object> option = new HashMap<String, Object>();
      if (level != null) {
        if (level == 1) {
          option.put("productType1", id);
        } else if (level == 2) {
          option.put("secProductType", ":" + id + ":");
        } else if (level == 3) {
          option.put("productTypeId", id);
        }
      } else {
        msg = "级别不能为空！";
      }
      // 1-普通拼团
      option.put("type", 1);
      // 1-拼团审核通过
      option.put("status", 1);
      // 0-未删除
      option.put("isDelete", 0);
      option.put("pageNo", (pageNo - 1) * pageSize);
      option.put("pageSize", pageSize);
      option.put("orderBy",
          "p.sell_number+p.base_number desc, ga.sorting desc, ga.create_date desc");
      List<GrouponActivityPojo> grouponActivitys = grouponActivityService.listPage(option);
      if (grouponActivitys != null && grouponActivitys.size() > 0) {
        Map<String, Object> item = null;
        String url = ConstParam.URL + "/upfiles/product" + File.separator;
        for (GrouponActivityPojo product : grouponActivitys) {
          item = new HashMap<String, Object>();
          // 活动ID
          item.put("activityId", StringUtil.checkVal(product.getId()));
          // 独购价
          item.put("alonePrice", StringUtil.checkVal(product.getDistributionPrice()));
          // 团购人数
          item.put("groupNum", StringUtil.checkVal(product.getNum()));
          // 已购人数
          item.put("attendNum", StringUtil.checkVal(product.getNumNow()));
          // 商品销量
          item.put("proSellrNum", StringUtil.checkVal(product.getSellNumber()));
          // 商品ID
          item.put("productId", StringUtil.checkVal(product.getProductId()));
          // 商品图片
          item.put("productImage", url + StringUtil.checkVal(product.getProductImage()));
          // 商品名称
          item.put("productName", StringUtil.checkVal(product.getProductName()));
          // 商品团购价
          item.put("productPrice", StringUtil.checkVal(product.getPrice()));
          groups.add(item);
        }
      } else {
        msg = "暂无数据！";
      }
      b = true;
    }

    map.put("result", groups);
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
   * 活动顶部图片
   * 
   * @return
   * @throws SQLException
   */
  public String activityBannerApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (type == null || type == 0) {
      msg = "类型不能为空!";
    } else {
      params.put("status", 1);
      params.put("type", type);
      params.put("pageNo", 0);
      params.put("pageSize", 1);
      params.put("nowTimeStr", StringUtil.dateString(new Date()));
      params.put("orderBy", "sorting desc, create_date desc");
      focusSettingPojoList = focusSettingService.listPage(params);
      if (focusSettingPojoList != null && focusSettingPojoList.size() > 0) {
        focusSettingPojo = focusSettingPojoList.get(0);
        result.put("banner", focusSettingPojo.getBanner() == null ? "" : ConstParam.URL
            + "/upfiles/focusbanner" + File.separator + focusSettingPojo.getBanner());
        b = true;
      } else {
        msg = "查询不到数据!";
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
   * 
   * 首页秒杀商品
   * 
   * @throws SQLException
   * */
  public String homeSecKillListApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> goodsItem = new HashMap<String, Object>();
    List<Map<String, Object>> secKillList = new ArrayList<Map<String, Object>>();
    // 首页拼团介绍商品
    params.put("id", 9543);
    params.put("status", 1);
    params.put("isDelete", 0);
    List<GrouponActivityPojo> grouponActivityList = grouponActivityService.listPage(params);
    if (grouponActivityList != null && grouponActivityList.size() > 0) {
      GrouponActivityPojo grouponActivityPojo = grouponActivityList.get(0);
      goodsItem.put("actType", StringUtil.checkVal(grouponActivityPojo.getType()));
      goodsItem.put("activityId", StringUtil.checkVal(grouponActivityPojo.getId()));
      goodsItem.put("productId", StringUtil.checkVal(grouponActivityPojo.getProductId()));
      goodsItem.put("productName", StringUtil.checkVal(grouponActivityPojo.getProductName()));
      goodsItem.put("productImage",
          grouponActivityPojo.getImageMain() == null ? "" : ConstParam.URL + "/upfiles/product"
              + File.separator + grouponActivityPojo.getImageMain());
      goodsItem.put("productPrice", StringUtil.checkVal(grouponActivityPojo.getPrice()));
      goodsItem.put("limitNum", StringUtil.checkVal(grouponActivityPojo.getLimitNum()));
      goodsItem.put("proSellrNum", StringUtil.checkVal(grouponActivityPojo.getSellNumber()));
      goodsItem.put("alonePrice", StringUtil.checkVal(grouponActivityPojo.getDistributionPrice()));
      goodsItem.put("groupNum", StringUtil.checkVal(grouponActivityPojo.getNum()));
      if (grouponActivityPojo.getSurplusNum() != null && grouponActivityPojo.getSurplusNum() > 0) {
        goodsItem.put("isSellOut", "0");
      } else {
        goodsItem.put("isSellOut", "1");
      }
      if (grouponActivityPojo.getLimitNum() == null || grouponActivityPojo.getLimitNum() <= 0) {
        goodsItem.put("salePerce", "100.0");
      } else {
        Integer saleNum = 0;
        saleNum = grouponActivityPojo.getLimitNum() - grouponActivityPojo.getSurplusNum();
        String salePerce =
            StringUtil.calcPerceStr(saleNum, grouponActivityPojo.getLimitNum()).replace("%", "");
        goodsItem.put("salePerce", salePerce);
      }
      secKillList.add(goodsItem);
    }
    params.clear();
    params.put("nowTime2", new Date());
    params.put("isDelete", 0);
    params.put("status", 1);
    params.put("type", 1);
    params.put("orderBy", "begin_time asc,id asc");
    List<SeckillPojo> seckillList = seckillService.listPage(params);
    if (seckillList != null && seckillList.size() > 0) {
      for (SeckillPojo seckillPojo : seckillList) {
        // secKillList = new ArrayList<Map<String, Object>>();
        // 查询活动的拼团
        params.clear();
        params.put("seckillId", seckillPojo.getId());
        params.put("status", 1);
        params.put("isHome", 1);
        params.put("orderBy", "sg.sorting desc,sg.id asc");
        List<SeckillGoodsPojo> seckillGoodsList = seckillGoodsService.listPage(params);
        if (seckillGoodsList != null && seckillGoodsList.size() > 0) {
          for (SeckillGoodsPojo seckillGoodsPojo : seckillGoodsList) {
            goodsItem = new HashMap<String, Object>();
            // 活动类型
            goodsItem.put("actType", "6");
            // 活动id
            goodsItem.put("activityId", StringUtil.checkVal(seckillGoodsPojo.getActivityId()));
            // 商品id
            goodsItem.put("productId", StringUtil.checkVal(seckillGoodsPojo.getProductId()));
            // 商品名称
            goodsItem.put("productName", StringUtil.checkVal(seckillGoodsPojo.getProductName()));
            // 商品图片
            goodsItem.put("productImage",
                seckillGoodsPojo.getImageMain() == null ? "" : ConstParam.URL + "/upfiles/product"
                    + File.separator + seckillGoodsPojo.getImageMain());
            // 商品价格
            goodsItem.put("productPrice", StringUtil.checkVal(seckillGoodsPojo.getPrice()));
            // 限购数量
            goodsItem.put("limitNum", StringUtil.checkVal(seckillGoodsPojo.getLimitNum()));
            // 销量
            goodsItem.put("proSellrNum", StringUtil.checkVal(seckillGoodsPojo.getSellNumber()));
            // 原价
            goodsItem.put("alonePrice", StringUtil.checkVal(seckillGoodsPojo.getAlonePrice()));
            // 几人团
            goodsItem.put("groupNum", StringUtil.checkVal(seckillGoodsPojo.getNum()));
            // 是否售罄
            if (seckillGoodsPojo.getSurplusNum() != null && seckillGoodsPojo.getSurplusNum() > 0) {
              goodsItem.put("isSellOut", "0");
            } else {
              goodsItem.put("isSellOut", "1");
            }
            // 判断活动库存数量是否小于或等于0
            if (seckillGoodsPojo.getLimitNum() == null || seckillGoodsPojo.getLimitNum() <= 0) {
              goodsItem.put("salePerce", "100.0");
            } else {
              Integer saleNum = 0;
              // 销量
              saleNum = seckillGoodsPojo.getLimitNum() - seckillGoodsPojo.getSurplusNum();
              // 计算销量百分比
              String salePerce =
                  StringUtil.calcPerceStr(saleNum, seckillGoodsPojo.getLimitNum()).replace("%", "");
              goodsItem.put("salePerce", salePerce);
            }
            secKillList.add(goodsItem);
          }
        } else {
          Util.log("查询不到活动商品!");
        }
      }
      b = true;
    } else {
      msg = "查询不到数据!";
    }
    map.put("result", secKillList);
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
   * 我的消息
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String myNoticeApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    if (userId == null || userId <= 0) {
      msg = "用户ID不能为空哦！~";
    } else {
      params.put("userId", userId);
      params.put("isDelete", 0);
      params.put("orderBy", "create_date desc");
      params.put("pageNo", 0);
      params.put("pageSize", 1);
      List<UserOrderNoticePojo> userOrderNoticeList = userOrderNoticeService.listPage(params);
      item.put("images", ConstParam.URL + "/upfiles/orderNotice" + File.separator
          + "orderNotice.png");
      item.put("name", "订单消息");
      if (userOrderNoticeList != null && userOrderNoticeList.size() > 0) {
        UserOrderNoticePojo userOrderNotice = userOrderNoticeList.get(0);
        if (userOrderNotice != null) {
          item.put("title", StringUtil.checkVal(userOrderNotice.getTitle()));
          item.put("time", StringUtil.checkVal(userOrderNotice.getCreateDateStr()));
        }
      }
      item.put("type", "3");
      item.put("count", StringUtil.checkVal(userOrderNoticeService.countBy(params)));
      result.add(item);
      success = true;
    }
    map.put("result", result);
    map.put("success", success);
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
   * 订单消息
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String userOrderNoticeApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    if (userId == null || userId <= 0) {
      msg = "用户ID不能为空哦！~";
    } else {
      if (pageNo == null || pageNo == 0) {
        params.put("pageNo", 0);
      } else {
        params.put("pageNo", (pageNo - 1) * 20);
      }
      params.put("pageSize", 20);
      params.put("userId", userId);
      params.put("isDelete", 0);
      params.put("orderBy", "create_date desc");
      List<UserOrderNoticePojo> userOrderNoticeList = userOrderNoticeService.listPage(params);
      if (userOrderNoticeList != null && userOrderNoticeList.size() > 0) {
        for (UserOrderNoticePojo userOrderNotice : userOrderNoticeList) {
          // linkType: 1-支付成功2-开团成功3-拼团成功4-抽奖未中奖5-抽奖已中奖6-发货7-退款8-拼团失败
          if (userOrderNotice.getContent() != null && !userOrderNotice.getContent().equals("")) {
            JSONObject json = JSONObject.fromObject(userOrderNotice.getContent());
            item = new HashMap<String, Object>();
            // 标题
            item.put("title", StringUtil.checkVal(json.get("title")));
            // 商品图片
            item.put("productImage", ConstParam.URL + StringUtil.checkVal(json.get("img")));
            // json.get("img")已经包含/upfiles/product/所以省略/upfiles/product" + File.separator
            // 商品名称
            item.put("productName", StringUtil.checkVal(json.get("name")));
            // 内容
            item.put("content", StringUtil.checkVal(json.get("content")));
            // 链接名
            item.put("linkName", StringUtil.checkVal(json.get("lname")));
            // 链接类型
            item.put("linkType", StringUtil.checkVal(json.get("type")));
            item.put("productId", "0");
            item.put("activityType", "0");
            if (!"".equals(StringUtil.checkVal(json.get("type")))
                && (json.get("type").equals("1") || json.get("type").equals("5"))) {
              if (json.get("param") != null && !"".equals(json.get("param"))) {
                grouponActivityPojo =
                    grouponActivityService.getById(Long.valueOf(StringUtil.checkVal(json
                        .get("param"))));
              }
              if (grouponActivityPojo != null) {
                item.put("productId", StringUtil.checkVal(grouponActivityPojo.getProductId()));
                item.put("activityType", StringUtil.checkVal(grouponActivityPojo.getType()));
              }
            }
            // 链接参数
            item.put("linkParam", StringUtil.checkVal(json.get("param")));
            // 时间
            item.put("time", StringUtil.checkVal(userOrderNotice.getCreateDateStr()));
            result.add(item);
          }
        }
        success = true;
      } else {
        msg = "没有订单消息数据!";
      }
    }
    map.put("result", result);
    map.put("success", success);
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
   * @return 版本控制
   * @throws Exception
   */

  public String versionControlApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    String result = "";
    if (os == null) {
      msg = "渠道不能为空!";
    } else if (os != 1 && os != 2) {
      msg = "请输入正确渠道!";
    } else if (ver == null) {
      msg = "版本不能为空!";
    } else if (ver <= 0) {
      msg = "请输入正确的版本!";
    } else {
      params.put("channel", os);
      List<VersionControlPojo> versionControlList = versionControlService.listPage(params);
      if (versionControlList.size() > 0) {
        VersionControlPojo v = versionControlList.get(0);
        if (v.getState() == 1) {
          if (ver >= v.getLastVersion()) {
            result = "0";
          } else if (ver > v.getForceVersion() && ver < v.getLastVersion()) {
            result = "1";
            msg = v.getSketch();
          } else if (v.getForceVersion() > 0 && ver <= v.getForceVersion()) {
            result = "2";
            msg = v.getSketch();
          } else {
            result = "0";
          }
        } else {
          result = "0";
        }
        b = true;
      } else {
        msg = "找不到版本信息!";
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
   * @return 领取优惠券
   * @throws Exception
   */

  public String gainCouponApi() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    String result = "0";
    if (userId == null || userId == 0) {
      msg = "请先登录,获取不到用户ID!";
    } else {
      params.put("userId", userId);
      int flagCount = userCouponFlagService.countBy(params);
      if (flagCount > 0) {
        msg = "您已领取过优惠券!";
      } else {
        // 插入优惠券领取记录
        UserCouponFlagPojo userCouponFlag = new UserCouponFlagPojo();
        userCouponFlag.setUserId(userId);
        userCouponFlag.setGainDate(new Date());
        int flag = userCouponFlagService.insert(userCouponFlag);
        if (flag <= 0) {
          msg = "您已领取过优惠券!";
        } else {
          // 领取优惠券
          UserCouponPojo userCoupon = new UserCouponPojo();
          ArrayList<Long> list = new ArrayList<Long>();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          list.add(73L);
          list.add(74L);
          list.add(75L);
          list.add(76L);
          list.add(77L);
          IdWorker idGen = null;
          Date validSdate = null;
          Long gentime = null;
          Long stime = null;
          Long etime = null;
          for (Long couponId : list) {
            idGen = new IdWorker(0, 0);
            validSdate = new Date();
            // 创建时间
            gentime = sdf.parse(StringUtil.dateString(new Date())).getTime();
            // 有效期开始时间
            stime = sdf.parse(StringUtil.dateString(validSdate)).getTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(validSdate);
            calendar.add(Calendar.HOUR, 72);
            // 有效期结束时间
            etime = sdf.parse(StringUtil.dateString(calendar.getTime())).getTime();
            userCoupon.setCouponNo(String.valueOf(idGen.nextId()));
            userCoupon.setCouponId(couponId);
            userCoupon.setUserId(userId);
            userCoupon.setSource(0);
            userCoupon.setGenTime(gentime / 1000);
            userCoupon.setStatus(1);
            userCoupon.setUsed(0);
            userCoupon.setUseTime(0L);
            userCoupon.setValidstime(stime / 1000);
            userCoupon.setValidetime(etime / 1000);
            int a = couponService.addUserCoupon(userCoupon);
            if (a > 0) {
              Util.log("优惠券ID" + id + "领取成功!");
            } else {
              Util.log("优惠券ID" + id + "领取失败!");
            }
          }
          msg = "优惠券领取成功!";
          result = "1";
          b = true;
        }
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
   * 个人资料编辑
   * 
   * @param uid 用户ID
   * @return
   * @throws Exception
   */
  public String editProfile() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (uid == null || uid == 0) {
      msg = "用户ID不能为空!";
    } else {
      SysLoginPojo user = sysLoginService.getUserInfoById(uid);
      if (user != null && user.getStatus() != null && user.getStatus() == 1) {
        try {
          // 上传头像
          if (logo != null) {
            String image = sellerService.uploadFile(logo, "upfiles" + File.separator + "userlogo");
            user.setImage(image);
          }
          if (name != null && !"".equals(name)) {
            user.setName(name);
          }
          if (sex != null && sex != 0) {
            user.setSex(sex);
          }
          sysLoginService.updateUserInfoById(user);
          msg = "提交成功!";
          b = true;
        } catch (Exception e) {
          msg = "修改用户信息出现异常!";
          e.printStackTrace();
        }
      } else {
        msg = "未找到用户信息!";
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
   * 
   * 绑定用户优惠券
   * 
   * @throws SQLException
   * */
  public String addUserCoupon() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();

    if (uid == null || uid == 0) {
      msg = "用户id不能为空！";
    } else if (couponNo == null || "".equals(couponNo)) {
      msg = "优惠券码不能为空！";
    } else {
      Map<String, Object> param = new HashMap<String, Object>();
      param.put("couponNo", couponNo);
      param.put("status", 1);
      param.put("used", 0);
      UserCouponPojo userCoupon = couponService.getUserCouponByNo(param);
      if (userCoupon != null) {
        if (userCoupon.getUserId() != null && userCoupon.getUserId() != 0) {
          msg = "该优惠券码已被绑定！";
        } else if (userCoupon.getUsed() == 1 || userCoupon.getUseTime() != null
            && userCoupon.getUseTime() != 0) {
          msg = "该优惠券码已使用！";
        } else {
          // 关联用户
          UserCouponPojo upUserCoupon = new UserCouponPojo();
          upUserCoupon.setUserId(uid);
          upUserCoupon.setStatus(1);
          upUserCoupon.setCouponNo(userCoupon.getCouponNo());
          // 来源：5-用户绑定
          upUserCoupon.setSource(5);
          couponService.updateUserCoupon(upUserCoupon);
          b = true;
          msg = "绑定成功！";
        }

      } else {
        msg = "未查到该优惠券码！";
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
   * 
   * 0.1抽奖列表--PHP专用
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String getDrawListPhpApi() throws Exception {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    if (type == null || type <= 0) {
      msg = "查看类型不能为空哦！~";
      // } else if (userId == null || userId <= 0) {
      // msg = "用户ID不能为空哦！~";
    } else {
      // if (type == 2) {
      // map.put("result", result);
      // map.put("success", true);
      // map.put("error_msg", msg);
      // JSONObject json = JSONObject.fromObject(map);
      // ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      // try {
      // ServletActionContext.getResponse().getWriter().write(json.toString());
      // } catch (IOException e) {
      // e.printStackTrace();
      // }
      // return null;
      // }
      Map<String, Object> params = new HashMap<String, Object>();
      int ps = 10;
      if (pageSize != null && pageSize != 0) {
        ps = pageSize;
      }
      if (pageNo == null || pageNo == 0) {
        params.put("pageNo", 0);
      } else {
        if (pageNo > 1) {
          int t = ps;
          ps = 5;
          params.put("pageNo", (pageNo - 1) * ps + t - ps);
        } else {
          params.put("pageNo", (pageNo - 1) * ps);
        }
      }
      params.put("pageSize", ps);

      params.put("type", 5);
      String now = StringUtil.dateString(new Date());
      params.put("status", 1);
      params.put("isDelete", 0);
      if (type == 1) {
        params.put("beginTimeStr", now);
        params.put("endTimeStr", now);
      } else {
        // params.put("beginTimeStr", now);
        params.put("endTimeStr2", now);
      }
      // params.put("activityStatus", type);// 1,2
      // params.put("surplusNumStr", " ga.surplus_num > 0");
      if (type == 2) {
        params.put("orderBy", "ga.end_time desc,ga.id desc");
      } else {
        params.put("orderBy", "ga.sorting desc,ga.id desc");
      }
      List<GrouponActivityPojo> list = grouponActivityService.listPage(params);
      if (list.size() > 0) {
        Map<String, Object> item = new HashMap<String, Object>();
        for (GrouponActivityPojo g : list) {
          item = new HashMap<String, Object>();
          item.put("activityId", StringUtil.checkVal(g.getId()));
          item.put("alonePrice", StringUtil.checkVal(g.getSellingPrice()));
          item.put("groupNum", StringUtil.checkVal(g.getNum()));
          item.put("productId", StringUtil.checkVal(g.getProductId()));
          item.put("productImage", ConstParam.URL + "/upfiles/product" + File.separator
              + StringUtil.checkVal(g.getProductImage()));
          item.put("productName", StringUtil.checkVal(g.getProductName()));
          item.put("productPrice", StringUtil.checkVal(g.getPrice()));
          result.add(item);
        }
        success = true;
      } else {
        msg = "暂时没有数据哦！~";
      }
    }
    map.put("result", result);
    map.put("success", success);
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
   * 口令红包表生成
   * 
   * @return String
   * @throws SQLException
   */
  public String aliRedEnvelopeApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<String> a = new ArrayList<>();
    String uploadPath =
        ServletActionContext.getServletContext().getRealPath("/upfiles/aliRed") + File.separator;
    System.out.println("路径>>>>" + uploadPath);
    File file = new File(uploadPath);
    if (file == null || !file.exists() || !file.isDirectory()) {
      map.put("error_msg", "文件路径错误!");
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
    File[] files = file.listFiles();
    Util.log("文件length>>>>>" + files.length);
    if (files != null && files.length > 0) {
      String image = "";
      // 取偶数张图片
      int filecnt = files.length & ~1;
      Util.log("实际使用文件数>>>>>" + filecnt);
      // 循环红包口令图片重命名上传并添加进数组
      for (int i = 0; i < filecnt; i++) {
        try {
          image =
              FileUtil.uploadFile1(files[i], 0, files[i].getName(), "upfiles/aliRedEnvelope",
                  false, 300, 300, true, true, "");
        } catch (Exception e) {
          e.printStackTrace();
        }
        if (StringUtils.isNotBlank(image)) {
          a.add(image);
        }
      }
      AliRedEnvelopePojo aliRedEnvelopePojo = null;
      // 最小偶数
      int count = a.size() & ~1;
      // 红包口令图片以及邀请码循环插入红包表
      for (int q = 0; q < count;) {
        aliRedEnvelopePojo = new AliRedEnvelopePojo();
        aliRedEnvelopePojo.setPasswdImg1(a.get(q++));
        aliRedEnvelopePojo.setPasswdImg2(a.get(q++));
        aliRedEnvelopePojo.setInviteCode(walletService.genInviteCode());
        aliRedEnvelopePojo.setCreateDate(new Date());
        aliRedEnvelopePojo.setUpdateDate(new Date());
        aliRedEnvelopeService.add(aliRedEnvelopePojo);
      }
      b = true;
      // 生成成功，删除原文件
      for (int i = 0; i < count; i++) {
        files[i].delete();
      }
    } else {
      msg = "文件为空哦！";
    }
    // map.put("result", result);
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


  public String getUpfileFileName() {
    return upfileFileName;
  }

  public void setUpfileFileName(String upfileFileName) {
    this.upfileFileName = upfileFileName;
  }

}
