package com.tzmb2c.web.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.datetime.DateTimeUtil;
import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.util.UtilDate;
import com.tencent.common.Util;
import com.tzmb2c.business.service.ConstParam;
import com.tzmb2c.business.service.GrouponService;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.business.service.WalletService;
import com.tzmb2c.utils.RandomUtils;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.AlipayOrderInfoPojo;
import com.tzmb2c.web.pojo.CartPojo;
import com.tzmb2c.web.pojo.CouponOrderPojo;
import com.tzmb2c.web.pojo.DeliveryAddressPojo;
import com.tzmb2c.web.pojo.FocusSettingPojo;
import com.tzmb2c.web.pojo.GroupFreeCouponPojo;
import com.tzmb2c.web.pojo.GrouponActivityPojo;
import com.tzmb2c.web.pojo.GrouponActivityRecordPojo;
import com.tzmb2c.web.pojo.GrouponUserRecordPojo;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.OrderShipPojo;
import com.tzmb2c.web.pojo.ProductFocusImagesPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductRestrictionPojo;
import com.tzmb2c.web.pojo.ProductSellPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SysAreaPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserCollectPojo;
import com.tzmb2c.web.pojo.UserCouponPojo;
import com.tzmb2c.web.pojo.UserDealLogPojo;
import com.tzmb2c.web.pojo.UserPindekeInfoPojo;
import com.tzmb2c.web.pojo.UserRedeemCodePojo;
import com.tzmb2c.web.pojo.UserWalletLogPojo;
import com.tzmb2c.web.pojo.WxpayOrderInfoPojo;
import com.tzmb2c.web.service.ActivityProductCommentService;
import com.tzmb2c.web.service.ActivityTimeService;
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
import com.tzmb2c.web.service.UserRedeemCodeService;
import com.tzmb2c.web.service.UserShopCollectService;
import com.tzmb2c.web.service.UserSpecialCollectService;
import com.tzmb2c.web.service.UserVerifyService;
import com.tzmb2c.web.service.UserWalletLogService;
import com.tzmb2c.web.service.UserWalletService;
import com.tzmb2c.web.service.VersionControlService;
import com.tzmb2c.web.service.WxpayOrderInfoService;
import com.tzmb2c.web.service.ZoneGoodsService;
import com.tzmb2c.web.service.ZonesService;

public class AppApiPinDeKeAction extends SuperAction {
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
  private UserRedeemCodeService userRedeemCodeService;

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
   * 三级分类ID
   */
  private String type1, type2, type3;


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
  /**
   * 产品/店铺/专场ID
   */
  private Integer favSenId;
  private String orderNo;// 订单号
  private Integer channel;// 订单渠道

  private String cardNo;
  private String extChannel;
  private String beginTime;
  private String endTime;
  private String account;// 账户
  private Integer taType;// 支付方式
  private Long specialId;// 专题id
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
  private Long pdkUid;

  // ---- getter and setter ---- //

  public String getIp() {
    return ip;
  }

  public Long getPdkUid() {
    return pdkUid;
  }

  public void setPdkUid(Long pdkUid) {
    this.pdkUid = pdkUid;
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

  public String getType1() {
    return type1;
  }

  public void setType1(String type1) {
    this.type1 = type1;
  }

  public String getType2() {
    return type2;
  }

  public void setType2(String type2) {
    this.type2 = type2;
  }

  public String getType3() {
    return type3;
  }

  public void setType3(String type3) {
    this.type3 = type3;
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
        result.put("alonePrice", grouponActivityPojo.getDistributionPrice() == null ? "0"
            : StringUtil.checkVal(grouponActivityPojo.getDistributionPrice()));
        result.put(
            "sellingPrice",
            grouponActivityPojo.getSellingPrice() == null ? "0" : StringUtil
                .checkVal(grouponActivityPojo.getSellingPrice()));

        // banners
        List<ProductFocusImagesPojo> productFocusImagesList =
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
        int isGroupFree = 0;
        int activityType =
            grouponActivityPojo.getType() == null ? 1 : grouponActivityPojo.getType();

        result.put("isCollect", "0");
        result.put("isGroupFree", "0");
        result.put("isGroup", "0");
        result.put("isOpen", "0");
        result.put("isPdk", "0");
        if (userId != null) {
          // 判断是否是拼得客
          SysLoginPojo syslogin = sysLoginService.getfindByIdSysLogin(userId);
          if (syslogin != null && syslogin.getIsPindeke() == 1) {
            result.put("isPdk", "1");
          }
          Map<String, Object> params3 = new HashMap<String, Object>();
          params3.put("userId", userId);
          params3.put("status", 1);
          params3.put("used", 0);
          // 有效时间
          params3.put("validTime", StringUtil.dateString(new Date()));
          isGroupFree = groupFreeCouponService.countBy(params3) > 0 && activityType == 2 ? 1 : 0;
          result.put("isGroupFree", isGroupFree + "");
          // 查询是否收藏
          UserCollectPojo userCollectPojo = new UserCollectPojo();
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
          if (grouponActivityPojo.getType() == 7 || grouponActivityPojo.getType() == 6) {
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
        result.put("productId", grouponActivityPojo.getProductId() == null ? "0"
            : grouponActivityPojo.getProductId());
        result.put("productName", grouponActivityPojo.getProductName() == null ? ""
            : grouponActivityPojo.getProductName());
        result.put("productImage",
            grouponActivityPojo.getProductImage() == null ? "" : ConstParam.URL
                + "/upfiles/product" + File.separator + grouponActivityPojo.getProductImage());
        result.put("productSketch", grouponActivityPojo.getProductSketch() == null ? ""
            : grouponActivityPojo.getProductSketch());
        result.put("productStatus", grouponActivityPojo.getStatus() == null ? "0"
            : grouponActivityPojo.getStatus().toString());
        // waitGroupList
        if (grouponActivityPojo.getType() != 2
            && grouponActivityPojo.getType() != 5
            && grouponActivityPojo.getType() != 7
            && grouponActivityPojo.getType() != 8
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
      ProductPojo product = productService.getById(pid);
      if (product == null) {
        msg = "查询不到商品!";
      } else {
        if (product.getNormType() == 1) {
          result.put("spceType", "1");
        } else if (product.getNormType() == 2) {
          result.put("spceType", "2");
        } else {
          result.put(msg, "规格有误");
        }
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
            if (product.getNormType() == 2) {
              if (!formatCuror.contains(skuCol.getSkuFormat())) {
                format = new HashMap<String, Object>();
                format.put("optionValue", skuCol.getSkuFormat());
                fmlist.add(format);
              }
              formatCuror.add(skuCol.getSkuFormat());
            }
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

          // 规格一名称
          SysDictPojo sysDict = new SysDictPojo();
          sysDict.setValue(String.valueOf(list.get(0).getColorValue()));
          sysDict.setType("sku_attr_type");
          SysDictPojo colorDict = sysDictService.getSysDictByTypeAndValue(sysDict);
          String colorStr = "规格";
          if (colorDict != null && colorDict.getName() != null) {
            colorStr = colorDict.getName();
          }
          // 规格二名称
          String formatStr = "";
          if (product.getNormType() == 2) {
            sysDict = new SysDictPojo();
            sysDict.setValue(String.valueOf(list.get(0).getFormatValue()));
            sysDict.setType("sku_attr_type");
            SysDictPojo formatDict = sysDictService.getSysDictByTypeAndValue(sysDict);
            formatStr = "套餐类型";
            if (formatDict != null && formatDict.getName() != null) {
              formatStr = formatDict.getName();
            }
          }
          if (collist.size() > 0) {
            skuColorMap = new HashMap<String, Object>();
            skuColorMap.put("skuTitle", colorStr);
            skuColorMap.put("skuType", "1");
            skuColorMap.put("skuValue", collist);
            skuList.add(skuColorMap);
          }
          if (product.getNormType() == 2) {
            if (fmlist.size() > 0) {
              skuFormatMap = new HashMap<String, Object>();
              skuFormatMap.put("skuTitle", formatStr);
              skuFormatMap.put("skuType", "2");
              skuFormatMap.put("skuValue", fmlist);
              skuList.add(skuFormatMap);
            }
          }
          result.put("skuList", skuList);
          result.put("validSKu", validSkus);
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
   * 
   * 拼得客任务清单
   * 
   * @return String
   * @throws SQLException
   */
  public String userGroupTaskApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> groups = new ArrayList<Map<String, Object>>();
    if (userId == null || userId < 1) {
      msg = "用户ID不能为空!";
    } else {
      SysLoginPojo sysLogin = sysLoginService.findSysLoginById(userId);
      if (sysLogin == null || sysLogin.getStatus() != 1) {
        msg = "该用户不存在！";
      } else if (sysLogin.getIsPindeke() != 1) {
        msg = "该用户不是拼得客哦！";
      } else {
        if (pageNo == null || pageNo < 1) {
          pageNo = 1;
        }
        pageSize = 10;
        Map<String, Object> params = new HashMap<String, Object>();
        // 8为拼得客任务
        params.put("type", 8);
        // 1-拼团审核通过
        params.put("status", 1);
        // 0-未删除
        params.put("isDelete", 0);
        params.put("nowTime", new Date());
        params.put("pageNo", (pageNo - 1) * pageSize);
        params.put("pageSize", pageSize);

        params.put("productName", name);
        if (source == null) {
          params.put("orderBy", "ga.sorting desc, ga.create_date desc");
        } else if (source == 1) {
          params.put("orderBy", "p.sell_number + p.base_number desc");
        } else if (source == 11) {
          params.put("orderBy", "p.sell_number + p.base_number asc");
        } else if (source == 2) {
          params.put("orderBy", "ga.price desc");
        } else if (source == 22) {
          params.put("orderBy", "ga.price asc");
        }

        if (type1 != null && type2 == null && type3 == null) {
          params.put("productType1", type1);
        } else if (type1 != null && type2 != null && type3 == null) {
          params.put("productTypeIds", type2);
        } else if (type1 != null && type2 != null && type3 != null) {
          params.put("productTypeId", type3);
        }

        List<GrouponActivityPojo> grouponActivitys = grouponActivityService.listPage(params);
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
            // 返佣金额
            Double price = 0.0;
            if (product.getNum() != null && product.getRebateRatio() != null
                && product.getPrice() != null) {
              price = product.getPrice() * product.getRebateRatio() / 100 * (product.getNum() - 1);
            }
            item.put("rebatePrice", StringUtil.checkVal(price));
            groups.add(item);
          }
        } else {
          msg = "暂无数据！";
        }
        b = true;
      }
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
        || !(source == 1 || source == 2 || source == 3 || source == 4 || source == 5 || source == 6
            || source == 7 || source == 8)) {
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
        // 判断用户是否是拼得客
        if (source == 8 && (pdkUid == null || pdkUid == 0)) {
          if ((attendId == null || attendId == 0) && sysLogin.getIsPindeke() != 1) {
            map.put("result", result);
            map.put("error_msg", "您无法参与此活动!");
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
            // 商品总量限购
            if (proPojo.getLimitNum() > 0 && (attendId == null || attendId == 0)) {
              if (proPojo.getSurplusNum() < 1) {
                msg = "商品开团数量已达到上限!";
                stockFlag = false;
              }
            }
            // 限购数量
            if (proPojo.getMaxNum() > 0) {
              if (proPojo.getMaxNum() < num) {
                msg = "购买数量大于商品限购~";
                stockFlag = false;
              }
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
        } catch (Exception e1) {
          e1.printStackTrace();
        }

        // 免费抽判断是否参加过
        if (source == 7 || source == 6) {
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
        if (source == 5 && activityId > 0) {
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
            } else if (activity.getType() == 8 && sysLogin.getIsPindeke() == 1
                && (pdkUid == null || pdkUid == 0 || pdkUid.longValue() == uid.longValue())
                && (attendId == null || attendId == 0)) {
              price = 0d;
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
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
    } else if (payMethod == null || channel == 2 && payMethod != 8 && payMethod != 4
        || channel != 2 && payMethod != 1 && payMethod != 2 && payMethod != 4) {
      // 1-支付宝 2-微信APP支付 3-货到付款 4-钱包支付 5-银联支付 6-苹果支付 7-招行支付 8-微信公众号支付
      msg = "支付方式有误哦！";
    } else if (4 == payMethod && user.getBalance() == 0) {
      msg = "钱包余额不足！~";
    } else if (8 == payMethod && StringUtils.isBlank(openid)) {
      msg = "支付参数错误";
    } else if (source == null
        || !(source == 1 || source == 2 || source == 3 || source == 4 || source == 5 || source == 6
            || source == 7 || source == 8)) {
      msg = "来源不能为空！";
    } else if (skuLinkId == null || skuLinkId <= 0) {
      msg = "请选择规格！";
    } else {
      // 判断用户是否是拼得客
      if (source == 8 && (pdkUid == null || pdkUid == 0)) {
        if ((attendId == null || attendId == 0) && user.getIsPindeke() != 1) {
          map.put("result", result);
          map.put("error_msg", "您无法参与此活动!");
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
      if (activityId == null) {
        activityId = 0l;
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
      if (source == 7 || source == 6) {
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
          if (activityId != null && (pdkUid == null || pdkUid == 0)) {
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
            // 商品总量限购
            if (productPojo.getLimitNum() > 0 && (attendId == null || attendId == 0)) {
              if (productPojo.getSurplusNum() < 1) {
                msg = "商品开团数量已达到上限!";
                stockFlag = false;
              }
            }
            // 限购数量
            if (productPojo.getMaxNum() > 0) {
              if (productPojo.getMaxNum() < num) {
                msg = "购买数量大于商品限购~";
                stockFlag = false;
              }
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
      if (source == 5 && activityId > 0) {
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
          } else if (activity.getType() == 8 && user.getIsPindeke() == 1
              && (pdkUid == null || pdkUid == 0 || pdkUid.longValue() == uid.longValue())
              && (attendId == null || attendId == 0)) {
            price = 0d;
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

      // 订单金额大于0 才处理
      double m = 0.0;
      long time = new Date().getTime() / 1000;
      if (allCartPrice0 > 0) {
        // 使用优惠券金额
        UserCouponPojo userCoupon = null;
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



        // 支付金额减去代金券金额
        allCartPrice0 = allCartPrice0 - m;
      }

      boolean check = true;
      int fullpay = 0;
      if (payMethod == 4 && allCartPrice0 > user.getBalance()) {
        check = false;

        b = false;
        msg = "钱包余额不足！~";
      }

      // 判断是否是拼得客订单
      if (pdkUid != null && pdkUid > 0) {
        order.setPdkUid(pdkUid);
      } else {
        if (source != null && source == 8) {
          if (attendId == null || attendId == 0) {
            // 开团
            if (user.getIsPindeke() != null && user.getIsPindeke() > 0) {
              order.setPdkUid(uid);
            }
          } else {
            // 参团
            GrouponActivityRecordPojo grouponActivityRecord =
                grouponActivityRecordService.getById(attendId);
            if (grouponActivityRecord != null && grouponActivityRecord.getUserId() != null) {
              order.setPdkUid(grouponActivityRecord.getUserId());
            }
          }
        }
      }
      orderService.insertOrder(order);

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



      if (check) {
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
          } else if (4 == payMethod) {
            SysLoginPojo sysLoginPojo = sysLoginService.getfindByIdSysLogin(uid);
            if (sysLoginPojo != null) {
              if (sysLoginPojo.getBalance() >= allCartPrice0) {
                SysLoginPojo sysLogin = new SysLoginPojo();
                sysLogin.setBalanceReduce(allCartPrice0);
                sysLogin.setId(sysLoginPojo.getId());
                int i = sysLoginService.updateSysLogin(sysLogin);

                if (i != 0) {
                  UserWalletLogPojo userWalletLogPojo = new UserWalletLogPojo();
                  userWalletLogPojo.setUserId(uid);
                  userWalletLogPojo.setCurBal(sysLoginPojo.getBalance());
                  userWalletLogPojo.setType(1l);
                  userWalletLogPojo.setTradeAmt(allCartPrice0);
                  userWalletLogPojo.setSource((long) source);
                  userWalletLogPojo.setOrderId(order.getId());
                  userWalletLogPojo.setCreateBy(sysLoginPojo.getId());
                  userWalletLogPojo.setOutTradeNo(out_trade_no);
                  userWalletLogPojo.setRemarks("消费");
                  userWalletLogService.insertUserWalletLog(userWalletLogPojo);

                  Map<String, Object> param = new HashMap<String, Object>();
                  param.put("id", order.getId());
                  param.put("walletPrice", allCartPrice0);
                  orderService.updateorders(param);

                  // 付款成功
                  processPaySuccess(out_trade_no);

                  fullpay = 1;
                } else {
                  b = false;
                  msg = "支付失败！~";
                }
              } else {
                b = false;
                msg = "钱包余额不足！~";
              }
            } else {
              b = false;
              msg = "用户不存在！~";
            }
          }
        } else {
          // 付款成功
          processPaySuccess(out_trade_no);
          // 拼团成功处理
          // grouponService.groupOrderHandle(activityId, attendId, uid, source, order.getId());

          fullpay = 1;
        }

        b = true;
      }
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
        if (p.getSource() != 5 && p.getSource() == 7) {
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
            if (productSell != null) {
              productSellPojo.setDaySell(productSell.getDaySell() + p.getNum());
            }
            productSellPojo.setUpdateDate(new Date());
            productSellService.update(productSellPojo);
          }
        }
      }
    }
    return uid;
  }

  /**
   * 订单确认收货
   * 
   * */
  public synchronized String editOrderStatus() throws SQLException {
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
        try {
          Util.log("判断订单类型是不是拼得客!");
          OrderPojo orderPojo = orderService.getfindByIdOrder(oid);
          if (orderPojo != null && orderPojo.getActivityId() != null && orderPojo.getSource() == 8
              && orderPojo.getPdkUid() != null && orderPojo.getPdkUid() > 0
              && orderPojo.getIsRebate() != null && orderPojo.getIsRebate() == 0) {
            Util.log("判断订单是否是0元");
            if (orderPojo.getFactPrice().doubleValue() == 0.0) {
              Util.log("0元不用返佣!");
            } else {
              GrouponActivityPojo grouponActivity =
                  grouponActivityService.getById(orderPojo.getActivityId());
              if (grouponActivity != null && grouponActivity.getRebateRatio() > 0.0) {
                Util.log("计算返佣金额!");
                Double price =
                    grouponActivity.getRebateRatio() / 100
                        * (orderPojo.getFactPrice() + orderPojo.getUsedPrice());
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
                  orderUp.setRebatePrice(price);
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
            }
          }
        } catch (Exception e) {
          Util.log("拼得客返佣出现异常!");
          e.printStackTrace();
        }
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
      UserPindekeInfoPojo userPindekeInfo = userPindekeInfoService.findByUserId(userId);
      if (userPindekeInfo == null || userPindekeInfo.getIsDelete() == 1
          || userPindekeInfo.getStatus() == 0 || userPindekeInfo.getStatus() == 2) {
        msg = "不存在该拼得客!";
      } else {
        // 钱包余额
        result.put("balance", StringUtil.checkVal(userPindekeInfo.getSurpluPrice()));
        // 冻结金额
        result.put("fzPrice", StringUtil.checkVal(userPindekeInfo.getFreezingPrice()));
        // 开团数
        Map<String, Object> params = new HashMap<String, Object>();
        // params.put("invitationUserId", userId);
        params.put("pdkUid", userId);
        params.put("activityType", 8);
        int openNum = grouponActivityRecordService.countBy(params);
        result.put("openNum", openNum);
        // 成团数
        // params.put("invitationUserId", userId);
        params.put("pdkUid", userId);
        params.put("activityType", 8);
        params.put("status", 1);
        int succNum = grouponActivityRecordService.countBy(params);
        result.put("succNum", succNum);
        // 是否冻结
        if (userPindekeInfo.getStatus() == 3) {
          result.put("isFrozen", 1);
        } else {
          result.put("isFrozen", 0);
        }
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
    int fullpay = 0;
    SysLoginPojo sysLogin = null;
    if (uid != null || payMethod == 4) {
      sysLogin = sysLoginService.findSysLoginById(uid);
    }
    if (uid == null) {
      error_msg = "uid不能为空";
    } else if (sysLogin == null || sysLogin.getStatus() == 0) {
      error_msg = "该会员ID不存在哦！";
    } else if (loginPojo == null) {
      error_msg = "没有该会员";
    } else if (orderNo == null || "".equals(orderNo)) {
      error_msg = "订单号不能为空";
    } /*
       * else if (order == null) { error_msg = "订单号未找到或已付款"; }
       */else if (payMethod == null || payMethod != 1 && payMethod != 2 && payMethod != 8
        && payMethod != 4) {
      error_msg = "支付方式错误";
    } else if (4 == payMethod && sysLogin.getBalance() == 0) {
      error_msg = "钱包余额不足！~";
    } else if (8 == payMethod && StringUtils.isBlank(openid)) {
      error_msg = "支付参数错误";
    } /*
       * else if (payMethod == 4 && isWallet == 0) { error_msg = "支付方式错误"; }
       */else {

      String subject = "";
      String body = "";
      price = 0.0;
      // 待支付状态
      order = orderService.findOrderByOrderNo(orderNo);
      if (order != null && 1 == order.getOrderStatus()) {
        // 判断用户是否是拼得客
        try {
          if (order.getSourceId() != null && order.getSourceId() > 0) {
            // 参团
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
          if ("1".equals(StringUtil.checkVal(order.getSource()))) {
            if (order.getProductId() != null && order.getNum() > 0) {
              Boolean stockFlag = true;
              ProductPojo proPojo = productService.getById(Long.valueOf(order.getProductId()));
              // 商品总量限购
              if (proPojo.getLimitNum() > 0) {
                if (proPojo.getSurplusNum() < order.getNum()) {
                  stockFlag = false;
                }
              }
              // 单笔最大购买限购
              if (proPojo.getMaxNum() > 0) {
                if (proPojo.getMaxNum() < order.getNum()) {
                  stockFlag = false;
                }
              }
              // 活动购买限购
              if (stockFlag) {
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
                    stockFlag = false;
                  }
                }
              }
              if (!stockFlag) {
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
          } else if ("5".equals(StringUtil.checkVal(order.getSource()))
              || "6".equals(StringUtil.checkVal(order.getSource()))
              || "7".equals(StringUtil.checkVal(order.getSource()))) {
            Boolean flag = true;
            String msg = "";
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
              if ("5".equals(StringUtil.checkVal(order.getSource()))) {
                Util.log("根据sourceId判断是开团还是参团");
                if (order.getSourceId() != null && order.getSourceId() > 0) {
                  Util.log("参团");
                  if (gurCount > 0) {
                    msg = "您已经参加过该团!";
                    flag = false;
                  }
                } else {
                  Util.log("开团");
                  if (garCount > 0) {
                    flag = false;
                    msg = "您已经参加过该团!";
                  }
                }
              } else if ("7".equals(StringUtil.checkVal(order.getSource()))
                  || "6".equals(StringUtil.checkVal(order.getSource()))) {
                Util.log("限时秒杀和免费抽奖判断是否参过或拼过团");
                if (gurCount > 0 || garCount > 0) {
                  Util.log("拼过团或参过团");
                  msg = "您已经参加过该团!";
                  flag = false;
                }
              }
            } else {
              msg = "查询不到活动!";
              flag = false;
            }
            if (!flag) {
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
        } catch (Exception e1) {
          Util.log("拼团验证出现异常");
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

          boolean check = true;
          if (payMethod == 4 && price > sysLogin.getBalance()) {
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
                  SysLoginPojo sysLogin2 = new SysLoginPojo();
                  sysLogin2.setBalanceReduce(price);
                  sysLogin2.setId(sysLoginPojo.getId());
                  int i = sysLoginService.updateSysLogin(sysLogin2);

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
   * 
   * 充值券兑换
   * 
   * @return
   * @throw
   * @return String
   * @throws SQLException
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String userCodeRedeemApi() throws SQLException {
    Boolean success = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (userId == null || userId == 0) {
      msg = "用户ID不能为空哦！~";
    } else if (code == null || "".equals(code)) {
      msg = "兑换码不能为空哦！~";
    } else {
      UserRedeemCodePojo userRedeemCodePojo = userRedeemCodeService.getByCode(code);
      if (userRedeemCodePojo != null) {
        Date date = new Date();
        if (date.before(userRedeemCodePojo.getValidStime())) {
          msg = "兑换券还未到有效期！~";
        } else if (userRedeemCodePojo.getValidEtime().before(date)) {
          msg = "兑换券已经过了有效期！~";
        } else {
          if (userRedeemCodePojo.getUsed() == 0 && userRedeemCodePojo.getStatus() == 1) {
            UserRedeemCodePojo userRedeemCode = new UserRedeemCodePojo();
            userRedeemCode.setUserId(userId);
            userRedeemCode.setUsed(1);
            userRedeemCode.setUseTime(date);
            userRedeemCode.setCode(code);
            userRedeemCodeService.update(userRedeemCode);

            SysLoginPojo sysLogin = new SysLoginPojo();
            sysLogin.setBalanceAdd(userRedeemCodePojo.getPrice());
            sysLogin.setTotalBalanceAdd(userRedeemCodePojo.getPrice());
            sysLogin.setId(userId);
            sysLoginService.updateSysLogin(sysLogin);

            SysLoginPojo user = sysLoginService.findSysLoginById(userId);
            if (user != null) {
              UserWalletLogPojo userWalletLogPojo = new UserWalletLogPojo();
              userWalletLogPojo.setUserId(userId);
              userWalletLogPojo.setCurBal(user.getTotalBalance());
              userWalletLogPojo.setType(0l);
              userWalletLogPojo.setTradeAmt(userRedeemCodePojo.getPrice());
              // userWalletLogPojo.setSource(0);
              // userWalletLogPojo.setOrderId(0);
              userWalletLogPojo.setCreateBy(userId);
              // userWalletLogPojo.setOutTradeNo("");
              userWalletLogPojo.setRemarks("领取兑换券");
              userWalletLogService.insertUserWalletLog(userWalletLogPojo);

              success = true;
            }
          } else {
            msg = "兑换券已经使用过！~";
          }
        }
      } else {
        msg = "兑换券不存在！~";
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
   * 用户钱包余额
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   */
  public String userWelletBalance() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("0.0");
    if (uid != null) {
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
      b = true;
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
      UserDealLogPojo userDealLogPojo = userDealLogService.getById(id);
      if (userDealLogPojo != null) {
        // 交易时间
        result.put("date", StringUtil.checkVal(userDealLogPojo.getDealDate()));
        // 交易金额
        result.put("price", StringUtil.checkVal(userDealLogPojo.getDealAmount()));
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
        result.put("surpluPrice", StringUtil.checkVal(userDealLogPojo.getSurplusPrice()));
        // 记录类型(1-收入2-支出)
        result.put("type", StringUtil.checkVal(userDealLogPojo.getRemark()));
        // 交易单号
        result.put("orderNo", StringUtil.checkVal(userDealLogPojo.getOrderNo()));
        // 审核状态
        result.put("status", StringUtil.checkVal(userDealLogPojo.getStatus()));
        // 转账完成时间
        result.put("overTime", StringUtil.checkVal(userDealLogPojo.getOverTime()));
        // 审核不通过时间
        result.put("falseDate", StringUtil.checkVal(userDealLogPojo.getFalseDate()));
        // 不通过原因
        result.put("reason", StringUtil.checkVal(userDealLogPojo.getReturnMsg()));
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
        // 拼得客id
        orderInfo.put("pdkUid", StringUtil.checkVal(order.getPdkUid()));
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
          item.put("pdkUid", StringUtil.checkVal(order.getPdkUid()));
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

}
