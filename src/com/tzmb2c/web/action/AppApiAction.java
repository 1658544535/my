package com.tzmb2c.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.alipay.util.UtilDate;
import com.alipay.util.httpClient.AlipayTradeQueryResponse;
import com.geetest.sdk.GeetestUtil;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.tencent.WXPay;
import com.tencent.common.MD5;
import com.tencent.common.Signature;
import com.tencent.common.Util;
import com.tencent.protocol.pay_query_protocol.ScanPayQueryReqData;
import com.tencent.protocol.pay_query_protocol.ScanPayQueryResData;
import com.tencent.protocol.pre_pay_protocol.PrePayReqData;
import com.tencent.protocol.pre_pay_protocol.PrePayResData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.tzmb2c.business.service.ConstParam;
import com.tzmb2c.business.service.GrouponService;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.business.service.WalletService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.MD5Util;
import com.tzmb2c.utils.PropertiesHelper;
import com.tzmb2c.utils.RandomUtils;
import com.tzmb2c.utils.SmsSendUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityGoodsPojo;
import com.tzmb2c.web.pojo.ActivityPojo;
import com.tzmb2c.web.pojo.ActivityProductPojo;
import com.tzmb2c.web.pojo.ActivityTimePojo;
import com.tzmb2c.web.pojo.ActivityTitlePojo;
import com.tzmb2c.web.pojo.AgencyApplyPojo;
import com.tzmb2c.web.pojo.AgencyCollectPojo;
import com.tzmb2c.web.pojo.AgencyPojo;
import com.tzmb2c.web.pojo.AgencyPushPojo;
import com.tzmb2c.web.pojo.AlipayOrderInfoPojo;
import com.tzmb2c.web.pojo.BaiduLoginPojo;
import com.tzmb2c.web.pojo.CartPojo;
import com.tzmb2c.web.pojo.ConsumerPojo;
import com.tzmb2c.web.pojo.CouponOrderPojo;
import com.tzmb2c.web.pojo.CouponPojo;
import com.tzmb2c.web.pojo.DeliveryAddressPojo;
import com.tzmb2c.web.pojo.FeedBackPojo;
import com.tzmb2c.web.pojo.HelpPojo;
import com.tzmb2c.web.pojo.HelpTypePojo;
import com.tzmb2c.web.pojo.HistoryPojo;
import com.tzmb2c.web.pojo.InfoPojo;
import com.tzmb2c.web.pojo.LoginRecPojo;
import com.tzmb2c.web.pojo.ManufacturerPojo;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.OrderRefundPojo;
import com.tzmb2c.web.pojo.OrderShipPojo;
import com.tzmb2c.web.pojo.PagePushPojo;
import com.tzmb2c.web.pojo.PopupPojo;
import com.tzmb2c.web.pojo.ProductCommentPojo;
import com.tzmb2c.web.pojo.ProductFocusImagesPojo;
import com.tzmb2c.web.pojo.ProductImagesPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductSellPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.PushHomePagePojo;
import com.tzmb2c.web.pojo.ScenePojo;
import com.tzmb2c.web.pojo.SceneProductPojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SkuAttributePojo;
import com.tzmb2c.web.pojo.SysAreaPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserAttestationPojo;
import com.tzmb2c.web.pojo.UserCollectPojo;
import com.tzmb2c.web.pojo.UserConsumerCollectPojo;
import com.tzmb2c.web.pojo.UserCouponPojo;
import com.tzmb2c.web.pojo.UserInfoPojo;
import com.tzmb2c.web.pojo.UserOrderRefundPojo;
import com.tzmb2c.web.pojo.UserScoreLogPojo;
import com.tzmb2c.web.pojo.UserScorePojo;
import com.tzmb2c.web.pojo.UserShopCollectPojo;
import com.tzmb2c.web.pojo.UserVerifyPojo;
import com.tzmb2c.web.pojo.UserVisitPojo;
import com.tzmb2c.web.pojo.WxpayOrderInfoPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.ActivityProductService;
import com.tzmb2c.web.service.ActivityService;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.ActivityTitleService;
import com.tzmb2c.web.service.AgencyApplyService;
import com.tzmb2c.web.service.AgencyAuthenticationService;
import com.tzmb2c.web.service.AgencyCollectService;
import com.tzmb2c.web.service.AgencyPushService;
import com.tzmb2c.web.service.AgencyService;
import com.tzmb2c.web.service.AlipayOrderInfoService;
import com.tzmb2c.web.service.BaiduLoginService;
import com.tzmb2c.web.service.CartService;
import com.tzmb2c.web.service.ConsumerService;
import com.tzmb2c.web.service.CouponService;
import com.tzmb2c.web.service.DeliveryAddressService;
import com.tzmb2c.web.service.FeedbackService;
import com.tzmb2c.web.service.HelpService;
import com.tzmb2c.web.service.HelpTypeService;
import com.tzmb2c.web.service.HistoryService;
import com.tzmb2c.web.service.InfoService;
import com.tzmb2c.web.service.LoginRecService;
import com.tzmb2c.web.service.LoginService;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderRefundService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.OrderShipService;
import com.tzmb2c.web.service.PagePushService;
import com.tzmb2c.web.service.PopupService;
import com.tzmb2c.web.service.ProductCommentService;
import com.tzmb2c.web.service.ProductFocusImagesService;
import com.tzmb2c.web.service.ProductImagesService;
import com.tzmb2c.web.service.ProductSellService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.ProductStockService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.PushHomePageService;
import com.tzmb2c.web.service.SceneProductService;
import com.tzmb2c.web.service.SceneService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SkuAttributeService;
import com.tzmb2c.web.service.SysAreaService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserAttestationService;
import com.tzmb2c.web.service.UserCollectService;
import com.tzmb2c.web.service.UserConsumerCollectService;
import com.tzmb2c.web.service.UserInfoService;
import com.tzmb2c.web.service.UserOrderRefundService;
import com.tzmb2c.web.service.UserScoreLogService;
import com.tzmb2c.web.service.UserScoreService;
import com.tzmb2c.web.service.UserShopCollectService;
import com.tzmb2c.web.service.UserVerifyService;
import com.tzmb2c.web.service.UserVisitService;
import com.tzmb2c.web.service.WxpayOrderInfoService;

public class AppApiAction extends SuperAction {
  private static final long serialVersionUID = 1L;
  @Autowired
  private AgencyPushService agencyPushService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ShopService shopService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private HistoryService historyService;
  @Autowired
  private ProductImagesService productImagesService;
  @Autowired
  private ProductFocusImagesService productFocusImagesService;
  @Autowired
  private UserCollectService userCollectService;
  @Autowired
  private ProductCommentService productCommentService;
  @Autowired
  private OrderDetailService orderDetailService;
  @Autowired
  private SysAreaService sysAreaService;
  @Autowired
  private ProductTypeService productTypeService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private UserVerifyService userVerifyService;
  @Autowired
  private LoginService loginService;
  @Autowired
  private LoginRecService loginRecService;
  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private ConsumerService consumerService;
  @Autowired
  private ManufacturerService manufacturerService;
  @Autowired
  private UserAttestationService userAttestationService;
  @Autowired
  private CartService cartService;
  @Autowired
  private DeliveryAddressService addressService;
  @Autowired
  private AlipayOrderInfoService alipayOrderInfoService;
  @Autowired
  private InfoService infoService;
  @Autowired
  private UserShopCollectService userShopCollectService;
  @Autowired
  private UserConsumerCollectService userConsumerCollectService;
  @Autowired
  private FeedbackService feedbackService;
  @Autowired
  private DeliveryAddressService deliveryAddressService;
  @Autowired
  private OrderShipService orderShipService;
  @Autowired
  private HelpService helpService;
  @Autowired
  private HelpTypeService helpTypeService;
  @Autowired
  private PagePushService pagePushService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private ProductStockService productStockService;
  @Autowired
  private UserVisitService userVisitService;
  @Autowired
  private UserOrderRefundService userOrderRefundService;
  @Autowired
  private OrderRefundService orderRefundService;
  @Autowired
  private ActivityService activityService;
  @Autowired
  private AgencyService agencyService;
  @Autowired
  private AgencyCollectService agencyCollectService;
  @Autowired
  private AgencyAuthenticationService agencyAuthenticationService;
  @Autowired
  private PushHomePageService pushHomePageService;
  @Autowired
  private ActivityProductService activityProductService;
  @Autowired
  private AgencyApplyService agencyApplyService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  @Autowired
  private ActivityTimeService activityTimeService;
  @Autowired
  private ActivityTitleService activityTitleService;
  @Autowired
  private WxpayOrderInfoService wxpayOrderInfoService;
  @Autowired
  private CouponService couponService;
  @Autowired
  private ProductSkuLinkService productSkuLinkService;
  @Autowired
  private SkuAttributeService skuAttributeService;
  @Autowired
  private UserScoreService userScoreService;
  @Autowired
  private UserScoreLogService userScoreLogService;
  @Autowired
  private SceneService sceneService;
  @Autowired
  private SceneProductService sceneProductService;
  @Autowired
  private BaiduLoginService baiduLoginService;
  @Autowired
  private PopupService popupService;
  @Autowired
  private WalletService walletService;
  @Autowired
  private GrouponService grouponService;
  @Autowired
  private ProductSellService productSellService;

  private String[] pidInventorys;// id和库存的字符串
  private Long agencyId;// 批发商ID
  private Long agencyUserId;// 批发商用户ID
  private String searchKey;// 搜索关键字
  private Long idLong;// 1=注册2=修改密码
  private Integer source;// 1=注册2=修改密码
  private String phone;// 手机号码
  private String captcha;// 验证码
  private String pass;// 密码
  private String passWork;// 确认密码
  private String type;// 选择注册类型1为分销2为供应||分销商注册1为网店2为实体店
  private Long uid;// 会员id
  private Long sid;// 店铺id
  private String subTypeId;// （0为所有；1为遥控/电动玩具；2为早教/音乐玩具
  // ；3为过家家玩具；4为童车玩具；5为益智玩具；6为其他玩具）
  private String typeId;// 小分类
  private String brindId;// 品牌id
  private String sorting;// (1为热门，2为最新，3为价格从高到低，4为价格从低到高,7为销量)
  private String display;// 是否新品：0=》否；1=》是
  private String key;// 搜索关键字（产品名称或产品型号）
  private String name;// 搜索内容
  private String productNo;// 产品货号
  private Long id;
  private Long userId;// 用户ID
  private Pager page2;
  private Long orderId;
  private Long productId;
  private String comment;
  private String companyName;// 店铺名称
  private Integer mainCategory;// 主营类目（1为遥控/电动玩具；2为早教/音乐玩具
  // ；3为过家家玩具；4为童车玩具；5为益智玩具；6为其他玩具）
  private Integer groups;// 运营总人数（1为1~5人；2为6~20人；3为21~50人；4为51~100人；5为100人以上）
  private String platform;// 销售平台
  private String salesArea;// 销售地区
  private String contact;// 联系人
  private String duty;// 联系人职务
  private String email;// 联系人邮箱
  private String QQ;// 联系人QQ
  private String tel;// 联系电话
  private String fax;// 传真号码
  private String webSite;// 网店
  private String address;// 公司地址
  private String brand;// 自营品牌
  private String mainProduct;// 主营产品
  private String content;// 公司介绍
  private String user3cImage;// CCC认证
  private String attestationImage;// 营业执照
  private Long pid;
  private Integer num;
  private Double price;
  private String[] cids;
  private String pids;
  private String[] orderIds;
  private Long addressId;
  private Integer payMethod;
  private Integer orderStatus;
  private Integer oid;
  private String buyer_message;
  private Integer consigneeType;// 送货方式
  private String outTradeNo;
  private OrderPojo order, orderPojo;
  private InfoPojo infoPojo;
  private List<ShopPojo> shoplist;
  private UserCollectPojo userCollect;
  private Integer favType;
  private UserConsumerCollectPojo userConsumerCollectPojo;
  private Integer favSenId;
  private FeedBackPojo feedBackPojo;
  private DeliveryAddressPojo deliveryAddressPojo;
  private HistoryPojo historyPojo;
  private AgencyApplyPojo agencyApplyPojo;
  private Integer province;// 省
  private Integer city;// 市
  private Integer area;// 区
  private String postCode;// 邮政编码
  private Long addId;// 地址ID
  private Integer pageNo;
  private Integer status;
  private Long orderDetailId;// 订单详情Id
  private Integer refundType;
  private String image;
  private String refundReason;
  private String logisticsNum;
  private String logisticsName;
  private String path;
  private int product_type_ids;
  private Long agency_id;
  private Long user_id;
  private String addressHome;
  private String addressCompany;
  private String corporation;
  private Long proxy_market;
  private String lat;// 横坐标
  private String lng;// 纵坐标
  private String manufacturerId;// 代理厂家ID
  private String company;
  private String main_category;
  private String main_product;
  private int credit_level;
  private int channel;
  private int isread;
  private int create_by;
  private Date create_date;
  private int update_by;
  private Date update_date;
  private String remarks;
  private Integer version;
  private String proxy_product;// 代理批发产品编号，半角逗号分隔，格式：proxy_product=产品a的编号,产品b的编号......
  private Long agency_stock;// 批发商库存量（批发商该产品的库存量）
  private String is_export;// 是否导出
  private String realName;// 实名
  private String id_num;// 身份证号
  private String bus_licence;// 营业执照
  private Long auto_userId;// 认证人编号（外键关联（sys_login.id））
  private String id_front_photo;// 身份证正面照
  private String id_back_photo;// 身份证反面照
  private String id_handle_photo;// 手持身份证照
  private Integer auth_status;// 认证状态（取业务字典：0待认证1认证通过 2 认证未通过）
  private String auth_licence;// 授权证书
  private Long auto_user_id;// 认证人编号
  private Date createDate;// 创建日期
  private String baidu_uid;// 百度id
  private String messages;// 店铺留言

  private File file, file1, file2, file3, file4, file5;
  private String fileName;
  private Integer adType;// 广告类型
  private AgencyPojo agencyPojo;
  private Integer sex;// 性别
  private String birth;// 生日
  private ActivityGoodsPojo activityGoodsPojo;
  private ActivityTimePojo activityTimePojo;
  private Integer isDefault;// 是否默认
  private String openid;// 微信
  private String sinaid;// 新浪
  private String authCode;// 微信支付授权号
  private String deviceInfo;// 设备信息
  private String goodsTag;// 微信商品标记
  private String orderNo;// 订单号
  private Long timeId;// 秒杀时间id
  private String couponNo;// 优惠券码
  private Integer couponNum;// 未使用的代金券数量
  private ProductSkuLinkPojo productSkuLinkPojo;
  private SkuAttributePojo skuAttributePojo;
  private Integer babySex;// baby性别
  private String babyBirthday;// baby生日
  private ScenePojo scenePojo;// 场景活动
  private SceneProductPojo sceneProductPojo;// 场景产品活动
  // sku关联id
  private Integer skuLinkId;
  // 活动类型
  private Long activityId;
  // 套餐ID
  private Long pkgId;
  private BaiduLoginPojo baiduLoginPojo;
  // 注册渠道
  private String regChannel;
  // 极验
  private String geetest_challenge, geetest_seccode, geetest_validate;
  private String regisType;// 1-苹果；2 安卓

  public String getGeetest_challenge() {
    return geetest_challenge;
  }

  public void setGeetest_challenge(String geetest_challenge) {
    this.geetest_challenge = geetest_challenge;
  }

  public String getGeetest_seccode() {
    return geetest_seccode;
  }

  public void setGeetest_seccode(String geetest_seccode) {
    this.geetest_seccode = geetest_seccode;
  }

  public String getGeetest_validate() {
    return geetest_validate;
  }

  public void setGeetest_validate(String geetest_validate) {
    this.geetest_validate = geetest_validate;
  }

  public String getRegChannel() {
    return regChannel;
  }

  public void setRegChannel(String regChannel) {
    this.regChannel = regChannel;
  }

  public BaiduLoginPojo getBaiduLoginPojo() {
    return baiduLoginPojo;
  }

  public void setBaiduLoginPojo(BaiduLoginPojo baiduLoginPojo) {
    this.baiduLoginPojo = baiduLoginPojo;
  }

  public Long getPkgId() {
    return pkgId;
  }

  public void setPkgId(Long pkgId) {
    this.pkgId = pkgId;
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

  public ProductSkuLinkPojo getProductSkuLinkPojo() {
    return productSkuLinkPojo;
  }

  public void setProductSkuLinkPojo(ProductSkuLinkPojo productSkuLinkPojo) {
    this.productSkuLinkPojo = productSkuLinkPojo;
  }

  public String getCouponNo() {
    return couponNo;
  }

  public void setCouponNo(String couponNo) {
    this.couponNo = couponNo;
  }

  public Long getTimeId() {
    return timeId;
  }

  public void setTimeId(Long timeId) {
    this.timeId = timeId;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
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

  public Integer getIsDefault() {
    return isDefault;
  }

  public void setIsDefault(Integer isDefault) {
    this.isDefault = isDefault;
  }

  public ActivityGoodsPojo getActivityGoodsPojo() {
    return activityGoodsPojo;
  }

  public void setActivityGoodsPojo(ActivityGoodsPojo activityGoodsPojo) {
    this.activityGoodsPojo = activityGoodsPojo;
  }

  public ActivityTimePojo getActivityTimePojo() {
    return activityTimePojo;
  }

  public void setActivityTimePojo(ActivityTimePojo activityTimePojo) {
    this.activityTimePojo = activityTimePojo;
  }

  public String getMessages() {
    return messages;
  }

  public void setMessages(String messages) {
    this.messages = messages;
  }

  public String getBaidu_uid() {
    return baidu_uid;
  }

  public void setBaidu_uid(String baidu_uid) {
    this.baidu_uid = baidu_uid;
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

  public File getFile3() {
    return file3;
  }

  public void setFile3(File file3) {
    this.file3 = file3;
  }

  public File getFile4() {
    return file4;
  }

  public void setFile4(File file4) {
    this.file4 = file4;
  }

  public File getFile5() {
    return file5;
  }

  public void setFile5(File file5) {
    this.file5 = file5;
  }

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Long getSid() {
    return sid;
  }

  public void setSid(Long sid) {
    this.sid = sid;
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

  public Integer getRefundType() {
    return refundType;
  }

  public void setRefundType(Integer refundType) {
    this.refundType = refundType;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getRefundReason() {
    return refundReason;
  }

  public void setRefundReason(String refundReason) {
    this.refundReason = refundReason;
  }

  public Long getOrderDetailId() {
    return orderDetailId;
  }

  public void setOrderDetailId(Long orderDetailId) {
    this.orderDetailId = orderDetailId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getPageNo() {
    return pageNo;
  }

  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
  }

  public Long getAddId() {
    return addId;
  }

  public void setAddId(Long addId) {
    this.addId = addId;
  }

  public Integer getProvince() {
    return province;
  }

  public void setProvince(Integer province) {
    this.province = province;
  }

  public Integer getCity() {
    return city;
  }

  public void setCity(Integer city) {
    this.city = city;
  }

  public Integer getArea() {
    return area;
  }

  public void setArea(Integer area) {
    this.area = area;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public HistoryPojo getHistoryPojo() {
    return historyPojo;
  }

  public void setHistoryPojo(HistoryPojo historyPojo) {
    this.historyPojo = historyPojo;
  }

  public DeliveryAddressPojo getDeliveryAddressPojo() {
    return deliveryAddressPojo;
  }

  public void setDeliveryAddressPojo(DeliveryAddressPojo deliveryAddressPojo) {
    this.deliveryAddressPojo = deliveryAddressPojo;
  }

  public FeedBackPojo getFeedBackPojo() {
    return feedBackPojo;
  }

  public void setFeedBackPojo(FeedBackPojo feedBackPojo) {
    this.feedBackPojo = feedBackPojo;
  }

  public UserInfoService getUserInfoService() {
    return userInfoService;
  }

  public void setUserInfoService(UserInfoService userInfoService) {
    this.userInfoService = userInfoService;
  }

  public Integer getSource() {
    return source;
  }

  public void setSource(Integer source) {
    this.source = source;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
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

  public String getPassWork() {
    return passWork;
  }

  public void setPassWork(String passWork) {
    this.passWork = passWork;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
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

  public String getBrindId() {
    return brindId;
  }

  public void setBrindId(String brindId) {
    this.brindId = brindId;
  }

  public String getSorting() {
    return sorting;
  }

  public void setSorting(String sorting) {
    this.sorting = sorting;
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Pager getPage2() {
    return page2;
  }

  public void setPage2(Pager page2) {
    this.page2 = page2;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public Integer getMainCategory() {
    return mainCategory;
  }

  public void setMainCategory(Integer mainCategory) {
    this.mainCategory = mainCategory;
  }

  public Integer getGroups() {
    return groups;
  }

  public void setGroups(Integer groups) {
    this.groups = groups;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public String getSalesArea() {
    return salesArea;
  }

  public void setSalesArea(String salesArea) {
    this.salesArea = salesArea;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getDuty() {
    return duty;
  }

  public void setDuty(String duty) {
    this.duty = duty;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getQQ() {
    return QQ;
  }

  public void setQQ(String qQ) {
    QQ = qQ;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getWebSite() {
    return webSite;
  }

  public void setWebSite(String webSite) {
    this.webSite = webSite;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getMainProduct() {
    return mainProduct;
  }

  public void setMainProduct(String mainProduct) {
    this.mainProduct = mainProduct;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getUser3cImage() {
    return user3cImage;
  }

  public void setUser3cImage(String user3cImage) {
    this.user3cImage = user3cImage;
  }

  public String getAttestationImage() {
    return attestationImage;
  }

  public void setAttestationImage(String attestationImage) {
    this.attestationImage = attestationImage;
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

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String[] getOrderIds() {
    return orderIds;
  }

  public void setOrderIds(String[] orderIds) {
    this.orderIds = orderIds;
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

  public Integer getPayMethod() {
    return payMethod;
  }

  public void setPayMethod(Integer payMethod) {
    this.payMethod = payMethod;
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

  public InfoPojo getInfoPojo() {
    return infoPojo;
  }

  public void setInfoPojo(InfoPojo infoPojo) {
    this.infoPojo = infoPojo;
  }

  public List<ShopPojo> getShoplist() {
    return shoplist;
  }

  public void setShoplist(List<ShopPojo> shoplist) {
    this.shoplist = shoplist;
  }

  public UserCollectPojo getUserCollect() {
    return userCollect;
  }

  public void setUserCollect(UserCollectPojo userCollect) {
    this.userCollect = userCollect;
  }

  public Integer getFavType() {
    return favType;
  }

  public void setFavType(Integer favType) {
    this.favType = favType;
  }

  public UserConsumerCollectPojo getUserConsumerCollectPojo() {
    return userConsumerCollectPojo;
  }

  public void setUserConsumerCollectPojo(UserConsumerCollectPojo userConsumerCollectPojo) {
    this.userConsumerCollectPojo = userConsumerCollectPojo;
  }

  public Integer getFavSenId() {
    return favSenId;
  }

  public void setFavSenId(Integer favSenId) {
    this.favSenId = favSenId;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public int getProduct_type_ids() {
    return product_type_ids;
  }

  public void setProduct_type_ids(int product_type_ids) {
    this.product_type_ids = product_type_ids;
  }

  /**
   * @return the agency_id
   */
  public Long getAgency_id() {
    return agency_id;
  }

  /**
   * @param agency_id the agency_id to set
   */
  public void setAgency_id(Long agency_id) {
    this.agency_id = agency_id;
  }

  /**
   * @return the user_id
   */
  public Long getUser_id() {
    return user_id;
  }

  /**
   * @param user_id the user_id to set
   */
  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  /**
   * @return the address_home
   */
  public String getAddressHome() {
    return addressHome;
  }

  /**
   * @param address_home the address_home to set
   */
  public void setAddressHome(String addressHome) {
    this.addressHome = addressHome;
  }

  /**
   * @return the address_company
   */
  public String getAddressCompany() {
    return addressCompany;
  }

  /**
   * @param address_company the address_company to set
   */
  public void setAddressCompany(String addressCompany) {
    this.addressCompany = addressCompany;
  }

  /**
   * @return the corporation
   */
  public String getCorporation() {
    return corporation;
  }

  /**
   * @param corporation the corporation to set
   */
  public void setCorporation(String corporation) {
    this.corporation = corporation;
  }

  /**
   * @return the proxy_market
   */
  public Long getProxy_market() {
    return proxy_market;
  }

  /**
   * @param proxy_market the proxy_market to set
   */
  public void setProxy_market(Long proxy_market) {
    this.proxy_market = proxy_market;
  }

  /**
   * @return the company
   */
  public String getCompany() {
    return company;
  }

  /**
   * @param company the company to set
   */
  public void setCompany(String company) {
    this.company = company;
  }

  /**
   * @return the main_category
   */
  public String getMain_category() {
    return main_category;
  }

  /**
   * @param main_category the main_category to set
   */
  public void setMain_category(String main_category) {
    this.main_category = main_category;
  }

  /**
   * @return the main_product
   */
  public String getMain_product() {
    return main_product;
  }

  /**
   * @param main_product the main_product to set
   */
  public void setMain_product(String main_product) {
    this.main_product = main_product;
  }

  /**
   * @return the credit_level
   */
  public int getCredit_level() {
    return credit_level;
  }

  /**
   * @param credit_level the credit_level to set
   */
  public void setCredit_level(int credit_level) {
    this.credit_level = credit_level;
  }

  /**
   * @return the channel
   */
  public int getChannel() {
    return channel;
  }

  /**
   * @param channel the channel to set
   */
  public void setChannel(int channel) {
    this.channel = channel;
  }

  /**
   * @return the isread
   */
  public int getIsread() {
    return isread;
  }

  /**
   * @param isread the isread to set
   */
  public void setIsread(int isread) {
    this.isread = isread;
  }

  /**
   * @return the create_by
   */
  public int getCreate_by() {
    return create_by;
  }

  /**
   * @param create_by the create_by to set
   */
  public void setCreate_by(int create_by) {
    this.create_by = create_by;
  }

  /**
   * @return the create_date
   */
  public Date getCreate_date() {
    return create_date;
  }

  /**
   * @param create_date the create_date to set
   */
  public void setCreate_date(Date create_date) {
    this.create_date = create_date;
  }

  /**
   * @return the update_by
   */
  public int getUpdate_by() {
    return update_by;
  }

  /**
   * @param update_by the update_by to set
   */
  public void setUpdate_by(int update_by) {
    this.update_by = update_by;
  }

  /**
   * @return the update_date
   */
  public Date getUpdate_date() {
    return update_date;
  }

  /**
   * @param update_date the update_date to set
   */
  public void setUpdate_date(Date update_date) {
    this.update_date = update_date;
  }

  /**
   * @return the remarks
   */
  public String getRemarks() {
    return remarks;
  }

  /**
   * @param remarks the remarks to set
   */
  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  /**
   * @return the version
   */
  public Integer getVersion() {
    return version;
  }

  /**
   * @param version the version to set
   */
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * @return the proxy_product
   */
  public String getProxy_product() {
    return proxy_product;
  }

  /**
   * @param proxy_product the proxy_product to set
   */
  public void setProxy_product(String proxy_product) {
    this.proxy_product = proxy_product;
  }

  /**
   * @return the agency_stock
   */
  public Long getAgency_stock() {
    return agency_stock;
  }

  /**
   * @param agency_stock the agency_stock to set
   */
  public void setAgency_stock(Long agency_stock) {
    this.agency_stock = agency_stock;
  }

  /**
   * @return the is_export
   */
  public String getIs_export() {
    return is_export;
  }

  /**
   * @param is_export the is_export to set
   */
  public void setIs_export(String is_export) {
    this.is_export = is_export;
  }

  /**
   * @return the realName
   */
  public String getRealName() {
    return realName;
  }

  /**
   * @param realName the realName to set
   */
  public void setRealName(String realName) {
    this.realName = realName;
  }

  /**
   * @return the id_num
   */
  public String getId_num() {
    return id_num;
  }

  /**
   * @param id_num the id_num to set
   */
  public void setId_num(String id_num) {
    this.id_num = id_num;
  }

  /**
   * @return the bus_licence
   */
  public String getBus_licence() {
    return bus_licence;
  }

  /**
   * @param bus_licence the bus_licence to set
   */
  public void setBus_licence(String bus_licence) {
    this.bus_licence = bus_licence;
  }

  /**
   * @return the auto_userId
   */
  public Long getAuto_userId() {
    return auto_userId;
  }

  /**
   * @param auto_userId the auto_userId to set
   */
  public void setAuto_userId(Long auto_userId) {
    this.auto_userId = auto_userId;
  }

  /**
   * @return the id_front_photo
   */
  public String getId_front_photo() {
    return id_front_photo;
  }

  /**
   * @param id_front_photo the id_front_photo to set
   */
  public void setId_front_photo(String id_front_photo) {
    this.id_front_photo = id_front_photo;
  }

  /**
   * @return the id_back_photo
   */
  public String getId_back_photo() {
    return id_back_photo;
  }

  /**
   * @param id_back_photo the id_back_photo to set
   */
  public void setId_back_photo(String id_back_photo) {
    this.id_back_photo = id_back_photo;
  }

  /**
   * @return the id_handle_photo
   */
  public String getId_handle_photo() {
    return id_handle_photo;
  }

  /**
   * @param id_handle_photo the id_handle_photo to set
   */
  public void setId_handle_photo(String id_handle_photo) {
    this.id_handle_photo = id_handle_photo;
  }

  /**
   * @return the auth_status
   */
  public Integer getAuth_status() {
    return auth_status;
  }

  /**
   * @param auth_status the auth_status to set
   */
  public void setAuth_status(Integer auth_status) {
    this.auth_status = auth_status;
  }

  /**
   * @return the createDate
   */
  public Date getCreateDate() {
    return createDate;
  }

  /**
   * @param createDate the createDate to set
   */
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  /**
   * @return the auth_licence
   */
  public String getAuth_licence() {
    return auth_licence;
  }

  /**
   * @param auth_licence the auth_licence to set
   */
  public void setAuth_licence(String auth_licence) {
    this.auth_licence = auth_licence;
  }

  /**
   * @return the auto_user_id
   */
  public Long getAuto_user_id() {
    return auto_user_id;
  }

  /**
   * @param auto_user_id the auto_user_id to set
   */
  public void setAuto_user_id(Long auto_user_id) {
    this.auto_user_id = auto_user_id;
  }

  /**
   * @return the lat
   */
  public String getLat() {
    return lat;
  }

  /**
   * @param lat the lat to set
   */
  public void setLat(String lat) {
    this.lat = lat;
  }

  /**
   * @return the lng
   */
  public String getLng() {
    return lng;
  }

  /**
   * @param lng the lng to set
   */
  public void setLng(String lng) {
    this.lng = lng;
  }

  public Integer getAdType() {
    return adType;
  }

  public void setAdType(Integer adType) {
    this.adType = adType;
  }

  public AgencyApplyPojo getAgencyApplyPojo() {
    return agencyApplyPojo;
  }

  public void setAgencyApplyPojo(AgencyApplyPojo agencyApplyPojo) {
    this.agencyApplyPojo = agencyApplyPojo;
  }

  /**
   * 
   * 用户注册和修改密码接收短信api
   * 
   * @throws SQLException
   * */
  public String captcha() throws Exception {
    String msg = "";
    // ip限制
    HttpServletRequest request = ServletActionContext.getRequest();
    String ip = getIpAddr(request);
    if (!"120.76.212.54".equals(ip) && !sendFlag(request)) {
      return null;
    }

    // 极验验证码处理
    if (SellerService.getOpenGeekValid() == 1) {
      boolean flag = true;
      if (StringUtils.isEmpty(geetest_challenge) || StringUtils.isEmpty(geetest_seccode)
          || StringUtils.isEmpty(geetest_validate)) {
        flag = false;
        msg = "当前版本过旧，请更新！";
      } else {
        System.out.println(">>>>" + geetest_challenge + "," + geetest_seccode + ","
            + geetest_validate);
        flag = GeetestUtil.validGeetestCaptcha(request);
        msg = flag ? "" : "验证码获取错误";
      }
      if (!flag) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        map.put("result", "");
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

    Boolean success = false;
    String error_msg = "";
    String code = "";
    Map<String, Object> map = new HashMap<String, Object>();
    SysLoginPojo s = new SysLoginPojo();
    s.setLoginname(phone);
    if (source == null || source != 1 && source != 2 && source != 3 && source != 4) {
      error_msg = "您的操作有误！";
    } else if (phone == null || phone.equals("") || phone.length() != 11) {
      error_msg = "手机号码有误！";
    } else if (source == 1 && sysLoginService.findSysLoginCountByLoginname(s) != 0) {
      error_msg = "手机号码已注册！";
    } else if (source == 2 && sysLoginService.findSysLoginCountByLoginname(s) == 0) {
      error_msg = "手机号码未注册！";
    } else {
      // 随机生成6位数字验证码
      Random rd = new Random();
      int getNum;
      do {
        getNum = Math.abs(rd.nextInt()) % 10 + 48;// 产生数字0-9的随机数
        // getNum = Math.abs(rd.nextInt())%26 + 97;//产生字母a-z的随机数
        char num1 = (char) getNum;
        String dn = Character.toString(num1);
        code += dn;
      } while (code.length() < 6);
      String content = "";
      if (source == 1) {
        content = "【拼得好】您申请了 拼得好 会员注册，验证码为：" + code + "，两分钟内有效。请在注册页面中输入验证码完成注册，淘竹马将竭诚为您服务。";
      } else if (source == 2) {
        content = "【拼得好】您申请了 拼得好 会员密码修改，验证码为：" + code + "，两分钟内有效。淘竹马将竭诚为您服务。";
      } else if (source == 3) {
        content = "【拼得好】您申请了绑定第三方账号，验证码为：" + code + "，两分钟内有效，淘竹马将竭诚为您服务。";
      } else {
        content = "【拼得好】亲，您此次登录的验证码是" + code + "，有效期15分钟。如非本人操作，请勿透露！";
      }
      // 调用短信发送接口
      // MxtongSMSCaptchaUtil.sendMxtongSMS(phone, content);
      SmsSendUtil.sendSMS(phone, content);
      // 将验证码和手机号码保存到数据库中
      UserVerifyPojo pojo = new UserVerifyPojo();
      pojo.setCaptcha(code);
      pojo.setLoginname(phone);
      pojo.setCreateDate(new Date());
      userVerifyService.userVerifyAdd(pojo);
      // 登录记录
      LoginRecPojo loginRecPojo = new LoginRecPojo();
      loginRecPojo.setType("0");
      loginRecPojo.setLoginDate(new Date());
      loginRecPojo.setLoginIp(ip);
      loginRecPojo.setUserId(0l);
      loginRecService.addLoginRec(loginRecPojo);
      success = true;
      error_msg = "验证码已发送";
    }
    Map<String, Object> map1 = new HashMap<String, Object>();
    map.put("success", success);
    map1.put("phone", phone);
    map1.put("captcha", code);
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
   * 用户注册api
   * */
  public String register() throws Exception {
    boolean flag = false;
    if (baidu_uid != null && StringUtils.isNotBlank(baidu_uid)) {
      // 相同机器码最多注册5个用户
      int count = baiduLoginService.checkMachineCodeRepeatByCode(baidu_uid);
      if (count >= 5) {
        flag = true;
        System.out.println(">>>>> baiduid1:" + baidu_uid);
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

    } else {
      flag = true;
    }
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
    SysLoginPojo sysLoginPojobyloginname = sysLoginService.getSysLoginByLoginName(phone);
    // 判断绑定的openid或sianid是否为空
    if (openid != null && !"".equals(openid) || sinaid != null && !"".equals(sinaid)) {
      if (openid != null && !"".equals(openid)) {
        if (sysLoginPojobyloginname != null
            && !(sysLoginPojobyloginname.getUnionid() == null || "".equals(sysLoginPojobyloginname
                .getUnionid()))) {
          regFlag = true;
          error_msg = "已绑定过微信号！";
        }
      }
      if (sinaid != null && !"".equals(sinaid)) {
        if (sysLoginPojobyloginname != null
            && !(sysLoginPojobyloginname.getSinaid() == null || "".equals(sysLoginPojobyloginname
                .getSinaid()))) {
          regFlag = true;
          error_msg = "已绑定过新浪号！";
        }
      }
    } else {
      // 判断会员名称是否注册过
      int sysLoginPojobuname = sysLoginService.getSysLoginCountByName(name);
      if (sysLoginPojobyloginname != null) {
        regFlag = true;
        error_msg = "手机号已注册过！";
      }
      if (sysLoginPojobuname != 0 && sysLoginPojobuname > 0) {
        regFlag = true;
        error_msg = "会员名称已经存在！";
      }
    }

    Map<String, Object> map1 = new HashMap<String, Object>();
    UserVerifyPojo v = new UserVerifyPojo();
    v.setLoginname(phone);
    UserVerifyPojo verifyPojo = new UserVerifyPojo();
    // 得到系统生成的验证码
    verifyPojo = userVerifyService.findNewestByPhone(v);
    Map<String, Object> map = new HashMap<String, Object>();
    if (phone == null || phone.equals("")) {
      error_msg = "请输入手机号码！";
    } else if (captcha == null || captcha.equals("")) {
      error_msg = "请输入验证码！";
    } else if (captcha.toString().length() != 6) {
      error_msg = "请填写正确6位验证码！";
    } else if (name == null || name.equals("")) {
      error_msg = "请输入会员名称！";
    } else if (pass == null || pass.equals("")) {
      error_msg = "请输入密码！";
    }/*
      * else if (StringUtil.CheckSecurity(pass) < 2) { error_msg = "密码强度弱，请换一个！"; }
      */else if (pass.length() < 6) {
      error_msg = "密码强度弱（至少6位），请换一个！";
    } else if (regFlag) {
      // error_msg = "手机号码已经存在！";
    } /*
       * else if (sysLoginPojobuname.size() != 0) { error_msg = "会员名称已经存在！"; }
       */else if (verifyPojo == null) {
      error_msg = "验证码已经失效，请重新发送验证！";
    } else if (!captcha.equals(verifyPojo.getCaptcha())) {
      error_msg = "请填写正确验证码！";
    } else {
      SysLoginPojo sysPojo = new SysLoginPojo();
      if (sysLoginPojobyloginname != null) {
        if (openid != null && !"".equals(openid)) {
          // sysLoginPojobyloginname.setOpenid(openid);
          sysLoginPojobyloginname.setUnionid(openid);
        }
        if (sinaid != null && !"".equals(sinaid)) {
          sysLoginPojobyloginname.setSinaid(sinaid);
        }
        if (sysLoginPojobyloginname.getToken() == null
            || "".equals(sysLoginPojobyloginname.getToken())) {
          // 融云token
          // 用户id
          String userid = String.valueOf(sysLoginPojobyloginname.getId());
          // 用户名
          String username = sysLoginPojobyloginname.getName();
          // 头像
          String logo = "";
          // token
          String token = sysLoginService.getRongyunToken(userid, username, logo);
          sysLoginPojobyloginname.setToken(token);
        }
        loginService.updateLoginPojo(sysLoginPojobyloginname);

        // 登录记录
        HttpServletRequest request = ServletActionContext.getRequest();

        LoginRecPojo loginRecPojo = new LoginRecPojo();
        loginRecPojo.setType(sysLoginPojobyloginname.getType());
        loginRecPojo.setLoginDate(new Date());
        loginRecPojo.setLoginIp(getIpAddr(request));
        loginRecPojo.setUserId(sysLoginPojobyloginname.getId());
        loginRecService.addLoginRec(loginRecPojo);
        sysPojo = sysLoginPojobyloginname;
      } else {
        if (type == null) {
          type = "0";
        }
        sysPojo = new SysLoginPojo();
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
        sysPojo.setCreateDate(new Date());
        String externalSignCode = phone + new Date().getTime() / 1000 + StringUtil.genRandomStr(4);
        externalSignCode = MD5.MD5Encode(externalSignCode);
        sysPojo.setExternalSignCode(externalSignCode);
        sysPojo.setCreateBy(0l);
        sysLoginService.addSysLoginService(sysPojo);

        // 得到添加成功的账户id
        if (loginService.loginCheckWeb(sysPojo)) {
          // 根据用户注册时的手机查询用户
          sysPojo = sysLoginService.findSysLoginByLoginname(sysPojo);

          // 向表baidu_login插入数据
          if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(baidu_uid)) {
            BaiduLoginPojo baiduLoginPojo = new BaiduLoginPojo();
            baiduLoginPojo.setUserId(sysPojo.getId());
            baiduLoginPojo.setLoginName(phone);
            baiduLoginPojo.setBaiduId(baidu_uid);
            baiduLoginPojo.setType(1);
            isInsertBaibuLogin(baiduLoginPojo);
          }

          /*
           * Date today = new Date(); if
           * (StringUtil.stringDate("2015-11-20 00:00:00").compareTo(today) <= 0) {
           */
          // 首次注册送优惠券
          long uid = sysPojo.getId();
          Map<String, Object> param = new HashMap<String, Object>();
          // 满10减5
          param.put("om", "10");
          param.put("m", "5");
          JSONObject json = JSONObject.fromObject(param);
          Date current = new Date();
          giveCoupon(uid, 1, 1, json.toString(), current, 30);
          // 满30减5
          param.put("om", "30");
          param.put("m", "5");
          json = JSONObject.fromObject(param);
          walletService.giveCoupon(uid, 1, 1, json.toString(), current, 30);
          // 满99减10
          param.put("om", "99");
          param.put("m", "10");
          json = JSONObject.fromObject(param);
          walletService.giveCoupon(uid, 1, 1, json.toString(), current, 30);
          // 满169减20=2张
          param.put("om", "169");
          param.put("m", "20");
          json = JSONObject.fromObject(param);
          walletService.giveCoupon(uid, 1, 1, json.toString(), current, 30);
          walletService.giveCoupon(uid, 1, 1, json.toString(), current, 30);
          // 满399减40
          param.put("om", "399");
          param.put("m", "40");
          json = JSONObject.fromObject(param);
          walletService.giveCoupon(uid, 1, 1, json.toString(), current, 30);
          /*
           * } else { // 首次注册送100元优惠券 long uid = sysPojo.getId(); Map<String, Object> param = new
           * HashMap<String, Object>(); param.put("om", "68"); param.put("m", "10"); JSONObject json
           * = JSONObject.fromObject(param); Date current = new Date(); // 满68减10 giveCoupon(uid, 1,
           * 1, json.toString(), current, 30); // 满128减20=2张 param.put("om", "128"); param.put("m",
           * "20"); json = JSONObject.fromObject(param); giveCoupon(uid, 1, 1, json.toString(),
           * current, 30); giveCoupon(uid, 1, 1, json.toString(), current, 30); // 满268减50
           * param.put("om", "268"); param.put("m", "50"); json = JSONObject.fromObject(param);
           * giveCoupon(uid, 1, 1, json.toString(), current, 30); // 满10减10 param.put("om", "10");
           * param.put("m", "10"); json = JSONObject.fromObject(param); giveCoupon(uid, 1, 1,
           * json.toString(), current, 30); // error_msg += "赠送100元优惠券！"; }
           */

          // 融云token
          // 用户id
          String userid = String.valueOf(sysPojo.getId());
          // 用户名
          String username = sysPojo.getName();
          // 头像
          String logo = "";
          // token
          String token = sysLoginService.getRongyunToken(userid, username, logo);
          sysPojo.setToken(token);
          loginService.updateLoginPojo(sysPojo);
          HttpServletRequest request = ServletActionContext.getRequest();

          LoginRecPojo loginRecPojo = new LoginRecPojo();
          loginRecPojo.setType(sysPojo.getType());
          loginRecPojo.setLoginDate(new Date());
          loginRecPojo.setLoginIp(getIpAddr(request));
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
          UserScorePojo userScorePojo = new UserScorePojo();
          userScorePojo.setUserId(sysPojo.getId());
          userScorePojo.setName(sysPojo.getName());
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
          userScorePojo.setShakeDateStr(sdf.format(new Date()));
          userScorePojo.setScore(0L);
          userScorePojo.setShakeDate(StringUtil.stringToDate(sdf.format(new Date())));
          userScorePojo.setShakeNum(3);
          userScorePojo.setBunding(0);
          userScorePojo.setUpload(0);
          userScorePojo.setImprove(0);
          userScoreService.insertUserScore(userScorePojo);
        }
      }

      map1.put("phone", sysPojo.getLoginname());
      map1.put("name", sysPojo.getName());
      map1.put("type", sysPojo.getType());
      map1.put("uid", sysPojo.getId());
      map1.put("token", sysPojo.getToken() == null ? "" : sysPojo.getToken());
      map1.put("openid", sysPojo.getUnionid() == null ? "" : sysPojo.getUnionid());
      map1.put("sinaid", sysPojo.getSinaid() == null ? "" : sysPojo.getSinaid());
      if (manufacturerService.findManufacturerByUserId(sysPojo.getId()) != null) {
        map1.put("judgeType", 1);
      } else if (consumerService.findConsumerByUserId(sysPojo.getId()) != null) {
        map1.put("judgeType", 2);
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
   * 
   * 用户绑定微信或新浪api
   * */
  public String bundling() throws Exception {
    Boolean success = false;
    // Boolean regFlag = false;
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
      // if (pass != null && !MD5Util.MD5(pass).equals(sysLoginPojobyloginname.getPassword())) {
      if (pass != null && !pass.equals(sysLoginPojobyloginname.getPassword())) {
        error_msg = "输入密码不正确！";
      }
      // 判断绑定的openid或sianid是否为空
      else if (openid != null && !"".equals(openid) || sinaid != null && !"".equals(sinaid)) {
        if (openid != null && !"".equals(openid)) {
          if (sysLoginPojobyloginname != null
              && !(sysLoginPojobyloginname.getUnionid() == null || ""
                  .equals(sysLoginPojobyloginname.getUnionid()))) {
            error_msg = "手机号已绑定过微信号！";
          } else {
            map3 = new HashMap<String, Object>();
            // map3.put("openid", openid);
            map3.put("unionid", openid);
            SysLoginPojo loginck = sysLoginService.qrySysLoginByThirdId(map3);
            if (loginck != null) {
              error_msg = "微信号已被绑定！";
            } else {
              // sysLoginPojobyloginname.setOpenid(openid);
              sysLoginPojobyloginname.setUnionid(openid);
              if (baidu_uid != null && !"".equals(baidu_uid)) {
                sysLoginPojobyloginname.setBaidu_uid(baidu_uid);;
              }
              sysLoginPojobyloginname.setUpdateDate(new Date());
              loginService.updateLoginPojo(sysLoginPojobyloginname);
              error_msg = "提交成功！";
              success = true;

              // 绑定微信号成功
              /*
               * UserScorePojo userScorePojo = userScoreService.findUserScoreByUid(uid); if
               * (userScorePojo != null && userScorePojo.getBunding() != null &&
               * userScorePojo.getBunding() == 0) { UserScorePojo userScorePojo2 = new
               * UserScorePojo(); userScorePojo2.setUserId(uid); userScorePojo2.setBunding(1);
               * userScorePojo2.setScore(userScorePojo.getScore() + 5);
               * userScoreService.updateUserScoreByUid(userScorePojo2);
               * 
               * UserScoreLogPojo userScoreLogPojo = new UserScoreLogPojo();
               * userScoreLogPojo.setUserId(uid); userScoreLogPojo.setName(userScorePojo.getName());
               * userScoreLogPojo.setCurScore(userScorePojo.getScore());
               * userScoreLogPojo.setTradeScore((long) 5); userScoreLogPojo.setTradeSource(2);
               * userScoreLogPojo.setRemark("绑定微信号");
               * userScoreLogService.insertUserScoreLog(userScoreLogPojo); }
               */
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
          map1.put("openid", sysLoginPojobyloginname.getUnionid() == null ? ""
              : sysLoginPojobyloginname.getUnionid());
          map1.put("sinaid", sysLoginPojobyloginname.getSinaid() == null ? ""
              : sysLoginPojobyloginname.getSinaid());
          if (sysLoginPojobyloginname.getImage() == null
              || "".equals(sysLoginPojobyloginname.getImage())) {
            map1.put("image", "");
          } else {
            map1.put("image",
                ConstParam.URL + "/upfiles/userlogo/" + sysLoginPojobyloginname.getImage());
          }
          if (manufacturerService.findManufacturerByUserId(sysLoginPojobyloginname.getId()) != null) {
            map1.put("judgeType", 1);
          } else if (consumerService.findConsumerByUserId(sysLoginPojobyloginname.getId()) != null) {
            map1.put("judgeType", 2);
          } else {
            map1.put("judgeType", 0);
          }
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
   * 
   * 用户第三方绑定检查
   * */
  public String checkRegister() throws Exception {
    Boolean success = false;
    Boolean regFlag = false;
    String error_msg = "";
    Map<String, Object> map3 = null;
    // 根据手机号码判断是否注册过
    SysLoginPojo sysLoginPojobyloginname = sysLoginService.getSysLoginByLoginName(phone);
    // 判断绑定的openid或sianid是否为空
    if (openid != null && !"".equals(openid) || sinaid != null && !"".equals(sinaid)) {
      if (openid != null && !"".equals(openid)) {
        if (sysLoginPojobyloginname != null
            && !(sysLoginPojobyloginname.getUnionid() == null || "".equals(sysLoginPojobyloginname
                .getUnionid()))) {
          regFlag = true;
          error_msg = "手机号已绑定过微信号！";
        } else {
          map3 = new HashMap<String, Object>();
          // map3.put("openid", openid);
          map3.put("unionid", openid);
          SysLoginPojo loginck = sysLoginService.qrySysLoginByThirdId(map3);
          if (loginck != null) {
            regFlag = true;
            error_msg = "微信号已被绑定！";
          }
        }
      }
      if (sinaid != null && !"".equals(sinaid)) {
        if (sysLoginPojobyloginname != null
            && !(sysLoginPojobyloginname.getSinaid() == null || "".equals(sysLoginPojobyloginname
                .getSinaid()))) {
          regFlag = true;
          error_msg = "手机号已绑定过新浪号！";
        } else {
          map3 = new HashMap<String, Object>();
          map3.put("sinaid", sinaid);
          SysLoginPojo loginck = sysLoginService.qrySysLoginByThirdId(map3);
          if (loginck != null) {
            regFlag = true;
            error_msg = "新浪号已被绑定！";
          }
        }
      }
    } else {
      if (sysLoginPojobyloginname != null) {
        regFlag = true;
        error_msg = "手机号已注册过！";
      }
    }
    if (!regFlag) {
      success = true;
    }

    Map<String, Object> map = new HashMap<String, Object>();
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
   * 
   * 删除百度id
   * */
  public String deleteBaiduUid() throws Exception {
    Boolean success = false;
    String error_msg = "";
    // 根据手机号码判断是否注册过
    SysLoginPojo loginname = sysLoginService.getSysLoginByLoginName(phone);
    if (phone == null || "".equals(phone)) {
      error_msg = "手机号必输！";
    } else if (loginname == null) {
      error_msg = "手机号未注册！";
    } else {
      loginname.setBaidu_uid("");
      loginService.updateBaiduUid(loginname);
      success = true;
    }

    Map<String, Object> map = new HashMap<String, Object>();
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
   * 
   * 用户登录API
   * 
   * @throws Exception
   * */
  public String login() throws Exception {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    if (phone == null || phone.equals("")) {
      error_msg = "用户登录名不能为空！";
    } else if (pass == null || pass.equals("")) {
      error_msg = "用户密码不能为空！";
    } else {
      // 验证用户名和密码是否正确
      SysLoginPojo loginPojo = new SysLoginPojo();
      loginPojo.setLoginname(phone);
      loginPojo.setPassword(MD5Util.MD5(pass));
      // ActionContext actionContext = ActionContext.getContext();
      if (loginService.loginCheckWeb(loginPojo)) {
        // 成功
        SysLoginPojo logiPojo = sysLoginService.getSysLoginByLoginName(phone);
        if (logiPojo != null && logiPojo.getStatus() == 1) {
          HttpServletRequest request = ServletActionContext.getRequest();
          // 向登录日志表中插入信息
          LoginRecPojo loginRecPojo = new LoginRecPojo();
          loginRecPojo.setType(logiPojo.getType());
          loginRecPojo.setLoginDate(new Date());
          loginRecPojo.setLoginIp(getIpAddr(request));
          loginRecPojo.setUserId(logiPojo.getId());
          loginRecService.addLoginRec(loginRecPojo);
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
          if (manufacturerService.findManufacturerByUserId(uid) != null) {
            map2.put("judgeType", 1);
          } else if (consumerService.findConsumerByUserId(uid) != null) {
            map2.put("judgeType", 2);
          } else {
            map2.put("judgeType", 0);
          }
          map.put("result", map2);
          // JSONObject json = JSONObject.fromObject(map);
          // actionContext.put("result", json.toString());
          // return SUCCESS;
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
   * */
  public String myInfo() throws Exception {
    boolean success = false;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    if (uid == null || uid.equals("")) {
      error_msg = "用户id不能为空！";
    } else {
      // 验证用户名和密码是否正确
      // SysLoginPojo loginPojo = new SysLoginPojo();
      int status;
      // ActionContext actionContext = ActionContext.getContext();
      SysLoginPojo logiPojo = sysLoginService.getfindByIdSysLogin(uid);
      try {
        agencyPojo = agencyService.findagencyByUserId(uid);
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

        // status=agencyPojo.getStatus();
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

        // map2.put("type", 1);

        int num = userCollectService.myProductCollectAllCount(uid);
        map2.put("proFavorites", num);
        int num2 = userShopCollectService.myShopCollectAllCount(uid);
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
          birth = getAgeString(StringUtil.stringToDate(birth));
          map2.put("age", birth);

        } else {
          map2.put("age", "");
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
      // ActionContext actionContext = ActionContext.getContext();
      if (loginService.loginCheckWeb(loginPojo)) {
        // 成功
        SysLoginPojo logiPojo = sysLoginService.getSysLoginByLoginName(phone);
        if (logiPojo != null && logiPojo.getStatus() == 1
            && (Long.valueOf(logiPojo.getType()) == 6 || Long.valueOf(logiPojo.getType()) == 1)) {
          HttpServletRequest request = ServletActionContext.getRequest();
          // 向登录日志表中插入信息
          LoginRecPojo loginRecPojo = new LoginRecPojo();
          loginRecPojo.setType(logiPojo.getType());
          loginRecPojo.setLoginDate(new Date());
          loginRecPojo.setLoginIp(getIpAddr(request));
          loginRecPojo.setUserId(logiPojo.getId());
          loginRecService.addLoginRec(loginRecPojo);

          // 接收baidu_id并保存进数据库表
          SysLoginPojo login = new SysLoginPojo();
          login.setBaidu_uid(baidu_uid);
          login.setLoginname(phone);
          if (logiPojo.getBaidu_uid() == null || "".equals(logiPojo.getBaidu_uid())) {
            loginService.updateBaiduUid(login);
          } else if (logiPojo.getBaidu_uid() != baidu_uid) {
            loginService.updateBaiduUid(login);
          }

          // 向表baidu_login插入数据
          if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(baidu_uid)) {
            BaiduLoginPojo baiduLoginPojo = new BaiduLoginPojo();
            baiduLoginPojo.setUserId(logiPojo.getId());
            baiduLoginPojo.setLoginName(phone);
            baiduLoginPojo.setBaiduId(baidu_uid);
            baiduLoginPojo.setType(2);
            isInsertBaibuLogin(baiduLoginPojo);
          }

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
          } else if (consumerService.findConsumerByUserId(uid) != null) {
            map2.put("judgeType", 2);
          } else if (manufacturerService.findManufacturerByUserId(uid) != null) {
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
   * 搜索整合（商品，店铺，没货号）
   * 
   * @throws SQLException
   **/
  public String searchAll() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> mapall = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    if (pageNo == null || pageNo.equals("")) {
      page = new Pager();
      page.setPageNo(1);
    } else {
      page = new Pager();
      page.setPageNo(pageNo);
    }
    List<ProductPojo> productList = new ArrayList<ProductPojo>();
    // 根据传入的参数不同搜索商品信息
    ProductPojo ppojo = new ProductPojo();
    ppojo.setProductNum(productNo);
    ppojo.setProductName(name);
    ppojo.setProductStatus("1");
    ppojo.setProductNameEn(sorting);
    page.setPageSize(10);
    page.setRowCount(productService.getAllCount(ppojo));
    productList = productService.getProductAll(ppojo, page);
    List<Object> list = new ArrayList<Object>();// 商品list
    List<Object> list1 = new ArrayList<Object>();// 店铺list
    if (productList.size() == 0) {
      msg = "没有查询到商品数据！";
    } else {
      // ActivityGoodsPojo activityGoods = null;
      Double factPrice = 0.0;
      for (ProductPojo p : productList) {
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("pId", p.getId());
        map2.put("productName", p.getProductName());
        map2.put("productImage",
            ConstParam.URL + "/upfiles/product/small" + File.separator + p.getImage());
        // 活动价格判断
        factPrice = p.getDistributionPrice();
        // 活动价由活动页面进入
        /*
         * activityGoods = isProductActivity(p.getId()); if (activityGoods != null &&
         * activityGoods.getActivePrice() != null && activityGoods.getActivePrice().doubleValue() !=
         * 0) { factPrice = activityGoods.getActivePrice(); }
         */
        map2.put("distributionPrice", df.format(factPrice));
        map2.put("sellingPrice", df.format(p.getSellingPrice()));
        map2.put("discount", calcDiscount(factPrice, p.getSellingPrice()));
        map2.put("sellNumber", p.getSellNumber() + p.getBaseNumber());
        map2.put("status", p.getStatus());
        list.add(map2);

      }
      b = true;
    }
    if (name != null && !name.equals("")) {
      if (page.getPageNo() < 2) {
        List<ShopPojo> shoplist = new ArrayList<ShopPojo>();
        ShopPojo shopPojo = new ShopPojo();
        shopPojo.setName(name);
        shopPojo.setStatus(1);
        shoplist = shopService.shopAllList(shopPojo, null);
        if (shoplist.size() == 0) {
          msg = "没有查询到店铺数据！";
        } else {
          for (ShopPojo s : shoplist) {
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("sId", s.getId());
            map2.put("shopName", s.getName());
            map2.put("mainCategory", s.getMainCategoryName());
            map2.put("address", s.getAddress());
            map2.put("shopImage", ConstParam.URL + "/upfiles/shop" + File.separator + s.getImages());
            list1.add(map2);
            b = true;
          }
        }
      }
    }
    mapall.put("shop", list1);
    mapall.put("product", list);
    map.put("result", mapall);
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
   * 搜索商品
   * 
   * @throws SQLException
   **/
  public String searchProduct() throws SQLException {
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    if (pageNo == null || pageNo.equals("")) {
      page = new Pager();
      page.setPageNo(1);
    } else {
      page = new Pager();
      page.setPageNo(pageNo);
    }
    List<ProductPojo> productList = new ArrayList<ProductPojo>();
    // 根据传入的参数不同搜索商品信息
    ProductPojo ppojo = new ProductPojo();
    ppojo.setProductNum(productNo);
    ppojo.setProductName(name);
    ppojo.setProductNameEn(sorting);
    page.setPageSize(10);
    page.setRowCount(productService.getAllCount(ppojo));
    productList = productService.getProductAll(ppojo, page);
    Map<String, Object> map2 = null;
    List<Object> list = new ArrayList<Object>();
    if (productList.size() == 0) {
      msg = "没有查询到数据！";
    }
    // ActivityGoodsPojo activityGoods = null;
    Double factPrice = 0.0;
    for (ProductPojo p : productList) {
      map2 = new HashMap<String, Object>();
      map2.put("pId", p.getId());
      map2.put("productName", p.getProductName());
      map2.put("productImage",
          ConstParam.URL + "/upfiles/product/small" + File.separator + p.getImage());
      // 活动价格判断
      factPrice = p.getDistributionPrice();
      /*
       * activityGoods = isProductActivity(p.getId()); if (activityGoods != null &&
       * activityGoods.getActivePrice() != null && activityGoods.getActivePrice().doubleValue() !=
       * 0) { factPrice = activityGoods.getActivePrice(); }
       */
      map2.put("distributionPrice", df.format(factPrice));
      map2.put("sellingPrice", df.format(p.getSellingPrice()));
      map2.put("discount", calcDiscount(factPrice, p.getSellingPrice()));
      map2.put("sellNumber", p.getSellNumber() + p.getBaseNumber());
      list.add(map2);
    }
    map.put("result", list);
    map.put("success", true);
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
   * 根据品牌名称查询商品
   * 
   * @throws SQLException
   * */
  public String searchBrand() throws SQLException {
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    // ActivityGoodsPojo activityGoods = null;
    DecimalFormat df = new DecimalFormat("#.##");
    if (name == null || name.equals("")) {
      // 查询全部商品
      if (pageNo == null || pageNo.equals("")) {
        page = new Pager();
        page.setPageNo(1);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
      }
      List<ProductPojo> productList = new ArrayList<ProductPojo>();
      // 搜索全部商品信息
      page.setPageSize(10);
      page.setRowCount(productService.getAllCount(new ProductPojo()));
      productList = productService.getProductAll(new ProductPojo(), page);
      if (productList.size() == 0) {
        msg = "没有搜索到数据！";
      }
      Map<String, Object> map2 = null;
      List<Object> list = new ArrayList<Object>();
      Double factPrice = 0.0;
      for (ProductPojo p : productList) {
        map2 = new HashMap<String, Object>();
        map2.put("pId", p.getId());
        map2.put("productName", p.getProductName());
        map2.put("productImage",
            ConstParam.URL + "/upfiles/product/small" + File.separator + p.getImage());
        // 活动价格判断
        factPrice = p.getDistributionPrice();
        /*
         * activityGoods = isProductActivity(p.getId()); if (activityGoods != null &&
         * activityGoods.getActivePrice() != null && activityGoods.getActivePrice().doubleValue() !=
         * 0) { factPrice = activityGoods.getActivePrice(); }
         */
        map2.put("distributionPrice", df.format(factPrice));
        map2.put("sellingPrice", df.format(p.getSellingPrice()));
        map2.put("discount", calcDiscount(factPrice, p.getSellingPrice()));
        map2.put("sellNumber", p.getSellNumber() + p.getBaseNumber());
        list.add(map2);
      }
      map.put("result", list);
      map.put("success", true);
      map.put("error_msg", msg);
      JSONObject json = JSONObject.fromObject(map);
      ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      try {
        ServletActionContext.getResponse().getWriter().write(json.toString());
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    } else {
      // 根据品牌名称查询商品
      if (pageNo == null || pageNo.equals("")) {
        page = new Pager();
        page.setPageNo(1);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
      }
      page.setPageSize(10);
      List<ProductPojo> productPojos = new ArrayList<ProductPojo>();
      productPojos = productService.getProductByBrandName(name, page);
      if (productPojos.size() < 1) {
        msg = "没有搜索到数据！";
      }
      Map<String, Object> map2 = null;
      List<Object> list = new ArrayList<Object>();
      Double factPrice = 0.0;
      for (ProductPojo p : productPojos) {
        map2 = new HashMap<String, Object>();
        map2.put("pId", p.getId());
        map2.put("productName", p.getProductName());
        map2.put("productImage",
            ConstParam.URL + "/upfiles/product/small" + File.separator + p.getImage());
        // map2.put("distributionPrice", p.getDistributionPrice());
        // 活动价格判断
        factPrice = p.getDistributionPrice();
        /*
         * activityGoods = isProductActivity(p.getId()); if (activityGoods != null &&
         * activityGoods.getActivePrice() != null && activityGoods.getActivePrice().doubleValue() !=
         * 0) { factPrice = activityGoods.getActivePrice(); }
         */
        map2.put("distributionPrice", df.format(factPrice));
        map2.put("sellingPrice", df.format(p.getSellingPrice()));
        map2.put("discount", calcDiscount(factPrice, p.getSellingPrice()));
        map2.put("sellNumber", p.getSellNumber() + p.getBaseNumber());
        list.add(map2);
      }
      map.put("result", list);
      map.put("success", true);
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

  /**
   * 
   * 搜索店铺
   * */
  public String searchShop() {
    Map<String, Object> map = new HashMap<String, Object>();
    String msg = "";
    if (name == null || name.equals("")) {
      // 搜索全部店铺
      if (pageNo == null || pageNo.equals("")) {
        page = new Pager();
        page.setPageNo(1);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
      }
      page.setPageSize(10);
      // page.setRowCount(shopService.shopAllCount(new ShopPojo()));
      List<ShopPojo> shoplist = new ArrayList<ShopPojo>();
      ShopPojo shopPojo = new ShopPojo();
      shopPojo.setStatus(1);
      shoplist = shopService.shopAllList(shopPojo, page);
      if (shoplist == null || shoplist.size() == 0) {
        msg = "没有查询到数据！";
      }
      Map<String, Object> map2 = null;
      List<Object> list = new ArrayList<Object>();
      for (ShopPojo s : shoplist) {
        map2 = new HashMap<String, Object>();
        map2.put("sId", s.getId());
        map2.put("shopName", s.getName());
        map2.put("shopImage", ConstParam.URL + "/upfiles/shop" + File.separator + s.getImages());
        list.add(map2);
      }
      map.put("result", list);
      map.put("success", true);
      map.put("error_msg", msg);
      JSONObject json = JSONObject.fromObject(map);
      ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      try {
        ServletActionContext.getResponse().getWriter().write(json.toString());
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    } else {
      // 根据名称搜索店铺
      if (pageNo == null || pageNo.equals("")) {
        page = new Pager();
        page.setPageNo(1);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
      }
      ShopPojo shopPojo = new ShopPojo();
      shopPojo.setName(name);
      page.setPageSize(10);
      // page.setRowCount(shopService.shopAllCount(shopPojo));
      List<ShopPojo> shoplist = shopService.shopAllListByName(shopPojo, page);
      if (shoplist.size() == 0) {
        msg = "没有查询到数据！";
      }
      List<Object> list = new ArrayList<Object>();
      Map<String, Object> map2 = null;
      for (ShopPojo s : shoplist) {
        map2 = new HashMap<String, Object>();
        map2.put("sId", s.getId());
        map2.put("shopName", s.getName());
        map2.put("shopImage", ConstParam.URL + "/upfiles/shop" + File.separator + s.getImages());
        list.add(map2);
      }
      map.put("result", list);
      map.put("success", true);
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

  /**
   * 
   * 产品列表
   * */
  public String product() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    if (pageNo == null || pageNo.equals("")) {
      page = new Pager();
      page.setPageNo(1);
    } else {
      page = new Pager();
      page.setPageNo(pageNo);
    }
    page.setPageSize(20);
    ProductPojo productPojo = new ProductPojo();
    productPojo.setUserId(uid);
    productPojo.setBrand(brindId);
    productPojo.setProductNameEn(sorting);// 排序方式
    productPojo.setProductName(key);// 根据产品名称
    productPojo.setIsNew(display);// 设置是否新品

    List<ProductTypePojo> typeList = null;
    ProductTypePojo productTypeqry = new ProductTypePojo();
    productTypeqry.setPid(0L);
    if ("0".equals(subTypeId)) {

    } else if ("7".equals(subTypeId)) {
      // 0-3岁
      productTypeqry.setName("0-3岁婴幼儿玩具");
      typeList = productTypeService.getProductTypeByPidName(productTypeqry);
      if (typeList != null && typeList.size() > 0) {
        productPojo.setProductTypeIds(String.valueOf(typeList.get(0).getId()));
      } else {
        productPojo.setProductTypeIds(subTypeId);// 设置大分类
      }
    } else if ("8".equals(subTypeId)) {
      // 3-6岁
      productTypeqry.setName("3-6岁儿童玩具");
      typeList = productTypeService.getProductTypeByPidName(productTypeqry);
      if (typeList != null && typeList.size() > 0) {
        productPojo.setProductTypeIds(String.valueOf(typeList.get(0).getId()));
      } else {
        productPojo.setProductTypeIds(subTypeId);// 设置大分类
      }
    } else if ("9".equals(subTypeId)) {
      // 6岁以上
      productTypeqry.setName("6岁以上玩具");
      typeList = productTypeService.getProductTypeByPidName(productTypeqry);
      if (typeList != null && typeList.size() > 0) {
        productPojo.setProductTypeIds(String.valueOf(typeList.get(0).getId()));
      } else {
        productPojo.setProductTypeIds(subTypeId);// 设置大分类
      }
    } else {
      productPojo.setProductTypeIds(subTypeId);// 设置大分类
    }
    productPojo.setProductTypeId(typeId);// 设置小分类
    page.setRowCount(productService.getCountStatus(productPojo));
    List<ProductPojo> productList = productService.getProductApi(productPojo, page);
    if (productList.size() < 1) {
      map.put("error_msg", "没有搜索到数据！");
    }
    List<Object> list = new ArrayList<Object>();
    Map<String, Object> map2;
    // ActivityGoodsPojo activityGoods = null;
    Double factPrice = 0.0;
    for (ProductPojo p : productList) {
      map2 = new HashMap<String, Object>();
      map2.put("id", p.getId());
      map2.put("name", p.getProductName());
      map2.put("image", ConstParam.URL + "/upfiles/product/small" + File.separator + p.getImage());
      // 活动价格判断
      factPrice = p.getDistributionPrice();
      /*
       * activityGoods = isProductActivity(p.getId()); if (activityGoods != null &&
       * activityGoods.getActivePrice() != null && activityGoods.getActivePrice().doubleValue() !=
       * 0) { factPrice = activityGoods.getActivePrice(); }
       */
      map2.put("price", df.format(factPrice));
      map2.put("sellingPrice", df.format(p.getSellingPrice()));
      map2.put("discount", calcDiscount(factPrice, p.getSellingPrice()));
      map2.put("sellNumber", p.getSellNumber() + p.getBaseNumber());
      map2.put("subTypeId", p.getProductTypeName());
      map.put("error_msg", "");
      list.add(map2);
    }
    map.put("result", list);
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
        // 是否包邮
        /*
         * if (productPojo.getPostageType() == 1) { productPojo.setPostageTypeName("是"); } else if
         * (productPojo.getPostageType() == 0) { productPojo.setPostageTypeName("否"); }
         */
        if (loginPojo != 0) {
          // 用户存在，添加浏览记录
          historyed(id, uid);
          // loginPojotype = loginPojo.getType();
        }
        // 根据产品用户ID查询所属店铺
        ShopPojo shopPojo = new ShopPojo();
        shopPojo.setUserId(productPojo.getUserId());
        shopPojo = shopService.findShop(shopPojo);
        if (shopPojo == null) {
          shopPojo = new ShopPojo();
        }
        // 得到当前产品图片列表
        // List<ProductImagesPojo> productImagesList =
        // productImagesService.productForId(productPojo.getId());
        mapresult.put("name", productPojo.getProductName());// 产品名称
        mapresult.put("description", productPojo.getContent());// 产品详情
        mapresult.put("sid", shopPojo.getId());// 店铺ID
        mapresult.put("shopName", shopPojo.getName());// 店铺名
        mapresult.put("shopImage",
            ConstParam.URL + "/upfiles/shop" + File.separator + shopPojo.getImages());// 店铺Logo
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
        mapresult.put("productCmmt",
            shopPojo.getProductCommt() == null ? "" : shopPojo.getProductCommt());
        mapresult.put("deliverCmmt",
            shopPojo.getDeliverCommt() == null ? "" : shopPojo.getDeliverCommt());
        mapresult.put("logisticsCmmt",
            shopPojo.getLogisticsCommt() == null ? "" : shopPojo.getLogisticsCommt());
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

        if (activityId == null) {
          activityId = 0L;
        }
        // 是否有SKU
        int hasSku = queryProductSkuCount(id, activityId);
        mapresult.put("skuFlag", hasSku > 0 ? 1 : 0);

        // 活动价格判断
        Double factPrice = productPojo.getDistributionPrice();
        // 活动类型
        int acType = 0;
        // 活动标题
        String acTitle = "";
        // 活动进行标志
        int isActive = 0;
        // 活动结束时间
        String endTime = "";
        // 活动产品判断
        if (activityId > 0) {
          ActivityGoodsPojo activityGoods = isProductActivity(productPojo.getId(), activityId);
          if (activityGoods != null) {
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
              // 活动无设置sku
              if (hasSku == 0) {
                if (activityGoods.getActivityStock() != null
                    && activityGoods.getActivityStock() > 0) {
                  isActive = 1;
                  endTime = activityGoods.getEndTime();
                }
              } else {
                isActive = 1;
                endTime = activityGoods.getEndTime();
              }
            }
          }
        }
        mapresult.put("acType", acType);
        mapresult.put("acTitle", acTitle);
        mapresult.put("isActive", isActive);
        mapresult.put("endTime", endTime);

        mapresult.put("ladderPrice", df.format(factPrice));
        mapresult.put("discount", calcDiscount(factPrice, productPojo.getSellingPrice()));

        // mapresult.put("ladderPrice",
        // String.valueOf(productPojo.getDistributionPrice()));// 产品价格

        // JSONObject object0 =
        // JSONObject.fromObject(json.get(json.size()-1));//获取最后一个阶梯价格
        // String price=(String) object0.get("price");
        // mapresult.put("ladderPrice", price);// 价格

        // List<ProductPojo> shopProductList =
        // productService.productForShopId(productPojo.getUserId());
        // productPojo.setId(productPojo.getId());
        // productPojo.setUserId(productPojo.getUserId());
        // productPojo.setProductTypeId(productPojo.getProductTypeId());
        List<ProductPojo> hotProductList;
        Map<String, Object> productmap = new HashMap<String, Object>();
        // productmap.put("userId",productPojo.getUserId());
        productmap.put("productTypeId", productPojo.getProductTypeId());
        productmap.put("productId", productPojo.getId());
        hotProductList = productService.productForShopId1(productmap);

        if (hotProductList == null || hotProductList.size() == 0) {
          msg = "没有推荐商品！";
        }
        Map<String, Object> mapHotProduct = null;
        List<Object> listHot = new ArrayList<Object>();
        Double factPrice2 = 0.0;
        for (ProductPojo productPojo2 : hotProductList) {
          mapHotProduct = new HashMap<String, Object>();
          // 活动价格判断
          factPrice2 = productPojo2.getDistributionPrice();
          // activityGoods = isProductActivity(productPojo2.getId(),0L);
          /*
           * if (activityGoods != null && activityGoods.getActivePrice() != null &&
           * activityGoods.getActivePrice().doubleValue() != 0) { factPrice2 =
           * activityGoods.getActivePrice(); }
           */
          mapHotProduct.put("price", df.format(factPrice2));
          mapHotProduct.put("sellingPrice", df.format(productPojo2.getSellingPrice()));
          mapHotProduct.put("discount", calcDiscount(factPrice2, productPojo2.getSellingPrice()));
          // mapHotProduct.put("price",
          // String.valueOf(productPojo2.getDistributionPrice()));//
          // 推荐商品价格
          mapHotProduct.put("image", ConstParam.URL + "/upfiles/product/small" + File.separator
              + productPojo2.getImage());// 推荐商品图片
          mapHotProduct.put("name", productPojo2.getProductName());// 推荐商品名
          mapHotProduct.put("productId", productPojo2.getId());// 推荐商品ID
          listHot.add(mapHotProduct);
        }
        mapresult.put("attribute", listHot);
        // UserCollectPojo userCollectPojo = new UserCollectPojo();
        // userCollectPojo.setProductId(productPojo.getId());
        // int fcount =
        // userCollectService.productCollectAllCount(userCollectPojo);
        /*
         * if (pageNo == null || pageNo.equals("")) { page = new Pager(); page.setPageNo(1); } else
         * { page = new Pager(); page.setPageNo(pageNo); } page.setPageSize(18); OrderDetailPojo
         * orderDetailPojo = new OrderDetailPojo();
         * orderDetailPojo.setProductId(productPojo.getId()); List<OrderDetailPojo> odlist =
         * orderDetailService.orderDetailAllList(orderDetailPojo, page);
         */
        /*
         * SysAreaPojo sysAreaPojo = new SysAreaPojo(); sysAreaPojo.setPid(0l); List<SysAreaPojo>
         * sysAreas = sysAreaService.getSysAreaByPid(sysAreaPojo); Double pweight =
         * productPojo.getWeight() == null ? 0 : Double.valueOf(productPojo.getWeight()); int
         * intPweight = (int) Math.ceil(pweight); for (SysAreaPojo sysArea : sysAreas) { Double fare
         * = sysArea.getPostage() + sysArea.getAddPostage() * (intPweight - 1); Double fare2 =
         * sysArea.getPostage2() + sysArea.getAddPostage2() * (intPweight - 1);
         * sysArea.setFare(fare); sysArea.setFare2(fare2); }
         */
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

  /**
   * 
   * 猜你喜欢
   * 
   * @throws SQLException
   * */
  public String guessFavoriteApi() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> mapresult = new HashMap<String, Object>();
    Map<String, Object> productmap = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    String msg = "";
    boolean success = false;
    if (uid == null) {
      uid = 0l;
    }
    if (id != null && !"".equals(id)) {
      // 根据产品Id查询商品
      ProductPojo productPojo = new ProductPojo();
      productPojo.setId(id);
      productPojo = productService.findProduct(productPojo);
      // 判断商品是否下架
      if (productPojo == null || productPojo.getStatus() == 0) {
        msg = "商品已经下架！";
      } else {
        // 设置点击次数+1
        // productService.updateHits(id);
        productmap.put("productId", productPojo.getId());
        uid = productPojo.getUserId();
        // 根据用户Id查询到当前用户所有信息
        /*
         * SysLoginPojo loginPojo = sysLoginService.findSysLoginById(uid); if (loginPojo != null) {
         * // 用户存在，添加浏览记录 historyed(id, uid); }
         */
      }
    }

    List<ProductPojo> hotProductList;

    productmap.put("userId", uid);
    hotProductList = productService.productForShopId1(productmap);

    if (hotProductList == null || hotProductList.size() == 0) {
      msg = "没有推荐商品！";
    } else {
      Map<String, Object> mapHotProduct = null;
      List<Object> listHot = new ArrayList<Object>();
      Double factPrice = 0.0;
      // ActivityGoodsPojo activityGoods = null;
      for (ProductPojo productPojo2 : hotProductList) {
        mapHotProduct = new HashMap<String, Object>();
        // 活动价格判断
        factPrice = productPojo2.getDistributionPrice();
        /*
         * activityGoods = isProductActivity(productPojo2.getId(),0L); if (activityGoods != null &&
         * activityGoods.getActivePrice() != null && activityGoods.getActivePrice().doubleValue() !=
         * 0) { factPrice = activityGoods.getActivePrice(); }
         */
        mapHotProduct.put("price", df.format(factPrice));// 推荐商品价格
        mapHotProduct.put("sellingPrice", df.format(productPojo2.getSellingPrice()));// 推荐商品原价
        mapHotProduct.put("discount", calcDiscount(factPrice, productPojo2.getSellingPrice()));// 折扣
        mapHotProduct.put("image", ConstParam.URL + "/upfiles/product/small" + File.separator
            + productPojo2.getImage());// 推荐商品图片
        mapHotProduct.put("name", productPojo2.getProductName());// 推荐商品名
        mapHotProduct.put("productId", productPojo2.getId());// 推荐商品ID
        listHot.add(mapHotProduct);
      }
      mapresult.put("attribute", listHot);
      success = true;
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

  /**
   * 
   * 产品详情评价
   * */
  public String productDetailComment() {
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
          // mapComment.put("userName", productCommentPojo2.getUserName());// 评价人
          mapComment.put("userName", productCommentPojo2.getLoginName() == null ? ""
              : WalletService.enCodeString(productCommentPojo2.getLoginName(), 4, 6));// 评价人
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
   * 
   * 产品详情焦点图
   * */
  public String productDetailFocusImg() {
    boolean b = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    if (id == null || id.equals("")) {
      msg = "产品Id不能为空！";
    } else {
      // 根据产品Id查询商品
      ProductPojo productPojo = new ProductPojo();
      productPojo.setId(id);
      productPojo = productService.findProduct(productPojo);
      if (productPojo != null && productPojo.getStatus() == 1) {
        // 根据商品Id查询焦点图
        List<ProductFocusImagesPojo> list =
            productFocusImagesService.getProductFocusImagesByPid(productPojo.getId());
        List<Object> list2 = new ArrayList<Object>();
        if (list.size() > 0) {
          Map<String, Object> map1 = null;
          for (ProductFocusImagesPojo productFocusImagesPojo : list) {
            map1 = new HashMap<String, Object>();
            map1.put("image", ConstParam.URL + "/upfiles/productFocusImages/"
                + productFocusImagesPojo.getImages());
            list2.add(map1);
          }
        }
        map.put("result", list2);
      } else {
        msg = "没有该商品信息，或者已经下架！";
      }
      b = true;
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
   * 产品描述图
   * */
  public String productDetailImg() {
    boolean b = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    if (id == null || id.equals("")) {
      msg = "产品Id不能为空！";
    } else {
      // 根据产品Id查询商品
      ProductPojo productPojo = new ProductPojo();
      productPojo.setId(id);
      productPojo = productService.findProduct(productPojo);
      if (productPojo != null && productPojo.getStatus() == 1) {
        // 根据商品Id查询焦点图
        List<ProductImagesPojo> list = productImagesService.productForId(productPojo.getId());
        List<Object> list2 = new ArrayList<Object>();
        if (list.size() > 0) {
          Map<String, Object> map1 = null;
          for (ProductImagesPojo productImagesPojo : list) {
            map1 = new HashMap<String, Object>();
            map1.put("image", ConstParam.URL + "/upfiles/product/" + productImagesPojo.getImages());
            list2.add(map1);
          }
        }
        map.put("result", list2);
      } else {
        msg = "没有该商品信息，或者已经下架！";
      }
      b = true;
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
    Map<String, Object> map2 = null;
    List<Object> list = null;
    List<Object> falist1 = new ArrayList<Object>();
    List<Object> falist2 = new ArrayList<Object>();
    String url = ConstParam.URL + "/upfiles/productType/";
    for (ProductTypePojo fatherPojo : fatherList) {
      map2 = new HashMap<String, Object>();
      list = new ArrayList<Object>();
      map2.put("pid", fatherPojo.getId());
      map2.put("name", fatherPojo.getName());
      map2.put("image", url + fatherPojo.getImage());
      for (ProductTypePojo productTypePojo : childList) {
        map1 = new HashMap<String, Object>();
        if ("0-3岁婴幼儿玩具".equals(fatherPojo.getName()) && "1".equals(productTypePojo.getAgeType())
            || "3-6岁儿童玩具".equals(fatherPojo.getName()) && "2".equals(productTypePojo.getAgeType())
            || "6岁以上玩具".equals(fatherPojo.getName()) && "3".equals(productTypePojo.getAgeType())
            || productTypePojo.getPid() == fatherPojo.getId()) {
          map1.put("id", productTypePojo.getId());
          map1.put("name", productTypePojo.getName());
          map1.put("image", StringUtils.isEmpty(productTypePojo.getImage()) ? "" : url
              + productTypePojo.getImage());
          list.add(map1);
        }
      }
      map2.put("type", list);
      if ("0-3岁婴幼儿玩具".equals(fatherPojo.getName()) || "3-6岁儿童玩具".equals(fatherPojo.getName())
          || "6岁以上玩具".equals(fatherPojo.getName())) {
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

  /**
   * 
   * 产品一二级分类
   * 
   * @throws SQLException
   * */
  public String productTypeList() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    ProductTypePojo pojo = new ProductTypePojo();
    pojo.setPid(idLong);
    List<ProductTypePojo> fatherList = productTypeService.getProductTypeByPid(pojo);

    Map<String, Object> map2 = null;

    List<Object> falist = new ArrayList<Object>();
    for (ProductTypePojo fatherPojo : fatherList) {
      map2 = new HashMap<String, Object>();

      map2.put("pid", fatherPojo.getId());
      map2.put("name", fatherPojo.getName());
      map2.put("image", fatherPojo.getImage());
      falist.add(map2);
    }
    map.put("result", falist);
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
      map.put("error_msg", "详情id与评价内容不能为空！");
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
            productCommentPojo.setScoreProduct(5);
            productCommentPojo.setScoreService(5);
            productCommentPojo.setScoreSspeed(5);
            productCommentPojo.setScore(5);
            productCommentService.addUserComment(productCommentPojo);
            OrderPojo orderPojo = new OrderPojo();
            orderPojo.setOrderStatus(5);
            orderPojo.setId((long) oid);
            orderService.updateOrderStatus(orderPojo);// 修改订单状态为已评价
          }
          map.put("result", 1);
          map.put("success", true);
          map.put("error_msg", "评价成功");

        } catch (Exception e) {
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
   * 批发商的供应商的商产品列表
   * 
   * @throws SQLException
   * */
  public String agencyProductIds() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> list = new ArrayList<Object>();
    SysLoginPojo sysLoginPojo = sysLoginService.findSysLoginById(uid);
    if (uid == null || uid.equals("")) {
      map.put("error_msg", "供应商ID和批发商ID不能为空！");
      map.put("success", false);
    } else if (sysLoginPojo.getType() == "2" || sysLoginPojo.getType().equals("2")) {// 判断是否是供应商
      ProductPojo product = new ProductPojo();
      product.setUserId(uid);
      List<ProductPojo> pList = productService.getProductAllIds(product);

      if (pList.size() == 0) {
        map.put("error_msg", "没有数据了！");
        map.put("success", false);
      }
      Map<String, Object> map1 = null;
      String ids = "";
      int i = 0;
      for (ProductPojo productPojo : pList) {
        i++;
        if (i == 1) {
          ids = productPojo.getId().toString();
        } else {
          ids = ids + "," + productPojo.getId();
        }

      }
      map1 = new HashMap<String, Object>();
      map1.put("ids", ids);

      map.put("result", map1);
      map.put("error_msg", "");
      map.put("success", true);
    } else {
      map.put("error_msg", "该用户不是供应商！");
      map.put("result", list);
      map.put("success", false);
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
   * 批发商的供应商的商产品列表
   * 
   * @throws SQLException
   * */
  public String agencyProductLists() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> list = new ArrayList<Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    SysLoginPojo sysagencyPojo = sysLoginService.findSysLoginById(uid);
    // SysLoginPojo sysLoginPojo = sysLoginService.findSysLoginById(uid);

    if (uid == null || uid.equals("")) {
      map.put("error_msg", "批发商ID不能为空！");
      map.put("success", false);
    } else if (sysagencyPojo == null) {
      map.put("error_msg", "批发商不存在！");
      map.put("success", false);
    } else if (sysagencyPojo.getType() != "6" && !sysagencyPojo.getType().equals("6")) {
      map.put("error_msg", "批发商ID错误！");
      map.put("result", list);
      map.put("success", false);
    } else {// 判断是否是供应商
      AgencyPojo agencyPojo = agencyService.findagencyByUserId(uid);
      // ManufacturerPojo
      // manufacturerPojo=manufacturerService.getfindByIdManufacturer(agencyPojo.getManufacturer_id());
      ShopPojo shopPojo = shopService.getfindByIdShop(agencyPojo.getManufacturerId());
      if (pageNo == null || pageNo.equals("")) {
        page = new Pager();
        page.setPageNo(1);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
      }
      page.setPageSize(20);// 修改返回数量
      ProductPojo product = new ProductPojo();
      product.setUserId(shopPojo.getUserId());
      List<ProductPojo> pList = productService.getAgencyProductAll(product, page);

      if (pList.size() == 0) {
        map.put("error_msg", "没有数据了！");
        map.put("success", false);
      }
      Map<String, Object> map1 = null;

      for (ProductPojo productPojo : pList) {
        int checked = 0;
        AgencyCollectPojo acPojo = new AgencyCollectPojo();
        acPojo.setUid(uid);
        acPojo.setProduct_id(productPojo.getId());
        AgencyCollectPojo agencyproduct = agencyCollectService.findagencyCollectById(acPojo);
        if (agencyproduct != null) {
          checked = 1;
        }
        map1 = new HashMap<String, Object>();
        map1.put("id", productPojo.getId());
        map1.put("image",
            ConstParam.URL + "/upfiles/product/small" + File.separator + productPojo.getImage());
        map1.put("name", productPojo.getProductName());
        map1.put("price", df.format(productPojo.getDistributionPrice()));
        map1.put("sellingPrice", df.format(productPojo.getSellingPrice()));
        map1.put("discount",
            calcDiscount(productPojo.getDistributionPrice(), productPojo.getSellingPrice()));
        map1.put("checked", checked);
        map1.put("agencyUserId", agencyUserId);
        map1.put("agencyId", agencyPojo.getAgencyId());
        map1.put("manufacturerUid", shopPojo.getUserId());
        map1.put("manufacturerName", shopPojo.getName());
        list.add(map1);
      }
      map.put("result", list);
      map.put("error_msg", "");
      map.put("success", true);
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
   * 设置代理商品
   * 
   * @return
   * @throws SQLException
   */
  public String toSetAgencyProduct() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (agencyId == null || agencyId.equals("")) {
      map.put("error_msg", "批发商ID不能为空！");
      map.put("success", false);
      map.put("result", 2);
    } else if (pids == null || pids.equals("")) {
      map.put("error_msg", "批发商产品ID不能为空！");
      map.put("success", false);
      map.put("result", 2);
    } else {
      String[] sourceStrArray = pids.split(",");
      for (int i = 0; i < sourceStrArray.length; i++) {
        long idsplit;
        long status;
        // 分解字符串分别得到购物车ID和数量
        String[] strings = sourceStrArray[i].split(":");
        AgencyCollectPojo agencyCollectPojo = null;
        AgencyCollectPojo agencyCollect = null;
        AgencyPojo aPojo = null;
        for (int k = 0; k < strings.length - 1; k++) {
          idsplit = Integer.parseInt(strings[0]);
          status = Integer.parseInt(strings[1]);
          agencyCollectPojo = new AgencyCollectPojo();
          agencyCollectPojo.setProduct_id(idsplit);
          agencyCollectPojo.setAgency_id(agencyId);
          aPojo = agencyService.getfindByAgencyId(agencyId);
          agencyCollectPojo.setUid(aPojo == null ? 0 : aPojo.getUserId());
          agencyCollectPojo.setAgency_stock(1L);
          agencyCollect = agencyCollectService.findagencyCollectById(agencyCollectPojo);
          if (status == 0 && null != agencyCollect) {
            // 根据id删除相对应的数据（有可能数据库表中没有这个数据）
            if (agencyCollect.getAgency_id() != null && agencyCollect.getProduct_id() != null) {
              agencyCollectService.delCheckProduct(agencyCollect);
            }
          } else if (status == 1 && null == agencyCollect) {
            // 根据id添加相对应的数据
            agencyCollectService.insertAgencyCollect(agencyCollectPojo);
          }
        }
      }
      map.put("result", 1);
      map.put("error_msg", "设置成功！");
      map.put("success", true);
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
   * 修改批发商商品的数量
   * 
   * @throws SQLException
   * */
  public String updateAgencyInventory() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    boolean b = false;
    if (uid == null || uid.equals("")) {
      map.put("error_msg", "用户ID不能为空！");
      map.put("result", 0);
    } else if (pidInventorys == null || pidInventorys.equals("")) {
      map.put("error_msg", "pidInventorys不能为空！");
      map.put("result", 0);
    } else {
      String[] sourceStrArray = pidInventorys[0].split(",");
      for (int i = 0; i < sourceStrArray.length; i++) {
        // 分解字符串分别得到购物车ID和数量
        String[] strings = sourceStrArray[i].split(":");
        for (int k = 0; k < strings.length; k++) {
          long idsplit = Integer.parseInt(strings[0]);
          long numsplit = Integer.parseInt(strings[1]);
          AgencyCollectPojo acPojo = new AgencyCollectPojo();
          acPojo.setUid(uid);
          acPojo.setId(idsplit);
          acPojo.setAgency_stock(numsplit);
          agencyCollectService.updatAagencyNumWeb(acPojo);
          b = true;
          map.put("result", 1);
          map.put("error_msg", "修改成功！");
        }
      }
    }
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
   * 批发商的供应商的商产品列表
   * 
   * @throws SQLException
   * */
  public String agencyProducts() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> list = new ArrayList<Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    SysLoginPojo sysagencyPojo = sysLoginService.findSysLoginById(uid);
    if (uid == null || uid.equals("")) {
      map.put("error_msg", "批发商ID不能为空！");
      map.put("success", false);
    } else if (sysagencyPojo.getType() == "6" || sysagencyPojo.getType().equals("6")) {// 判断是否是供应商
      if (pageNo == null || pageNo.equals("")) {
        page = new Pager();
        page.setPageNo(1);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
      }
      page.setPageSize(10);
      AgencyCollectPojo acPojo = new AgencyCollectPojo();
      acPojo.setUid(uid);
      List<AgencyCollectPojo> pList = agencyCollectService.findagencyCollectByUid(acPojo, page);

      if (pList.size() == 0) {
        map.put("error_msg", "没有数据了！");
        map.put("success", false);
      }
      Map<String, Object> map1 = null;

      for (AgencyCollectPojo agencyCollectPojo : pList) {
        map1 = new HashMap<String, Object>();
        map1.put("id", agencyCollectPojo.getProduct_id());
        map1.put("image", ConstParam.URL + "/upfiles/product/small" + File.separator
            + agencyCollectPojo.getImage());
        map1.put("name", agencyCollectPojo.getProductName());
        map1.put("price", df.format(agencyCollectPojo.getSellingPrice()));
        map1.put("sellingPrice", df.format(agencyCollectPojo.getOriginalPrice()));
        map1.put("discount",
            calcDiscount(agencyCollectPojo.getSellingPrice(), agencyCollectPojo.getOriginalPrice()));
        map1.put("agencyUserId", uid);
        map1.put("AgencyStock", agencyCollectPojo.getAgency_stock());
        list.add(map1);
      }
      map.put("result", list);
      map.put("error_msg", "");
      map.put("success", true);
    } else {
      map.put("error_msg", "该用户不是供应商！");
      map.put("result", list);
      map.put("success", false);
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
   * 供应商产品列表
   * 
   * @throws SQLException
   * */
  public String productLists() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> list = new ArrayList<Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    SysLoginPojo sysLoginPojo = sysLoginService.findSysLoginById(uid);
    if (uid == null || uid.equals("")) {
      map.put("error_msg", "供应商ID不能为空！");
      map.put("success", false);
    } else if (sysLoginPojo.getType() == "2" || sysLoginPojo.getType().equals("2")) {// 判断是否是供应商
      if (pageNo == null || pageNo.equals("")) {
        page = new Pager();
        page.setPageNo(1);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
      }
      page.setPageSize(10);
      ProductPojo product = new ProductPojo();
      product.setUserId(uid);
      List<ProductPojo> pList = productService.getProductAll(product, page);
      if (pList.size() == 0) {
        map.put("error_msg", "没有数据了！");
        map.put("success", false);
      }
      Map<String, Object> map1 = null;
      // ActivityGoodsPojo activityGoods = null;
      for (ProductPojo productPojo : pList) {
        map1 = new HashMap<String, Object>();
        map1.put("id", productPojo.getId());
        map1.put("image",
            ConstParam.URL + "/upfiles/product/small" + File.separator + productPojo.getImage());
        map1.put("name", productPojo.getProductName());
        map1.put("price", df.format(productPojo.getDistributionPrice()));
        // 活动价格判断
        /*
         * activityGoods = isProductActivity(productPojo.getId()); if(activityGoods!=null &&
         * activityGoods.getActivePrice() != null && activityGoods.getActivePrice().doubleValue() !=
         * 0){ map1.put("price", activityGoods.getActivePrice()); }else{ map1.put("price",
         * productPojo.getDistributionPrice()); }
         */
        map1.put("sellingPrice", df.format(productPojo.getSellingPrice()));
        map1.put("discount",
            calcDiscount(productPojo.getDistributionPrice(), productPojo.getSellingPrice()));
        list.add(map1);
      }
      map.put("result", list);
      map.put("error_msg", "");
      map.put("success", true);
    } else {
      map.put("error_msg", "该用户不是供应商！");
      map.put("result", list);
      map.put("success", false);
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
   * 
   * 分销商注册
   * */
  public String applyConsumerApi() throws Exception {
    Boolean success = false;
    String error_msg = null;
    int result = 2;
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid == null || uid.equals("")) {
      error_msg = "会员id不能为空！";
    } else if (companyName == null || companyName.equals("")) {
      error_msg = "店铺名称不能为空！";
    } else if (mainCategory != 1 && mainCategory != 2 && mainCategory != 3 && mainCategory != 4
        && mainCategory != 5 && mainCategory != 6) {
      error_msg = "主营类目有误！";
    } else if (groups != 1 && groups != 2 && groups != 3 && groups != 4 && groups != 5) {
      error_msg = "运营人数有误！";
    } else if (platform == null || platform.equals("")) {
      error_msg = "销售平台不能为空！";
    } else if (salesArea == null || salesArea.equals("")) {
      error_msg = "销售地区不能为空！";
    } else if (contact == null || contact.equals("")) {
      error_msg = "联系人不能为空！";
    } else if (email == null || email.equals("")) {
      error_msg = "邮箱不能为空！";
    } else if (QQ == null || QQ.equals("")) {
      error_msg = "QQ不能为空！";
    } else if (phone == null || phone.equals("")) {
      error_msg = "手机号码不能为空！";
    } else if (content == null || content.equals("")) {
      error_msg = "网店地址或者实体店地址不能为空！";
    } else if (!type.equals("1") && !type.equals("2")) {
      error_msg = "请选择您的店铺类型！";
    } else if (manufacturerService.findManufacturerByUserId(uid) != null) {
      error_msg = "您已经提交过供应商申请，请不要重复提交！";
    } else if (consumerService.findConsumerByUserId(uid) != null) {
      error_msg = "您已经提交过申请，我们的工作人员正在审核中，请您耐心等候！";
    } else {
      ConsumerPojo c = new ConsumerPojo();
      c.setUserId(uid);
      c.setCompany(companyName);
      c.setMainCategory(mainCategory.toString());
      c.setGroups(groups);
      c.setPlatform(platform);
      c.setSalesArea(salesArea);
      c.setContact(contact);
      c.setDuty(duty);
      c.setEmail(email);
      c.setQQ(QQ);
      c.setTel(tel);
      c.setPhone(phone);
      c.setFax(fax);
      if (type == "1" || type.equals("1")) {
        c.setWebSite(content);
      } else {
        c.setAddress(content);
      }
      c.setCreateDate(new Date());
      c.setCreateBy(uid);
      consumerService.insertConsumer(c);
      result = 1;
      error_msg = "申请已提交，您的申请我们的工作人员将会在3个工作日内审核！";
      success = true;
    }
    map.put("success", success);
    map.put("result", result);
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
   * 供应商注册Api
   * */
  public String applyManufacturerApi() throws Exception {
    boolean success = false;
    int result = 2;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    if (companyName == null || companyName.equals("")) {
      error_msg = "公司名称不能为空！";
    } else if (uid.toString() == null || uid.toString().equals("")) {
      error_msg = "会员ID不能为空！";
    } else if (sysLoginService.getfindByIdSysLogin(uid) == null) {// 判断会员ID是否存在
      error_msg = "该会员没有注册本网站！";
    } else if (mainCategory != 1 && mainCategory != 2 && mainCategory != 3 && mainCategory != 4
        && mainCategory != 5 && mainCategory != 6) {
      error_msg = "主营类目有误！";
    } else if (brand == null || brand.equals("")) {
      error_msg = "自营品牌不能为空！";
    } else if (groups != 1 && groups != 2 && groups != 3 && groups != 4 && groups != 5) {
      error_msg = "运营总人数有误！";
    } else if (salesArea == null || salesArea.equals("")) {
      error_msg = "销售地区不能为空！";
    } else if (contact == null || contact.equals("")) {
      error_msg = "联系人不能为空！";
    } else if (email == null || email.equals("")) {
      error_msg = "邮箱地址不能为空！";
    } else if (QQ == null || QQ.equals("")) {
      error_msg = "联系QQ不能为空！";
    } else if (phone == null || phone.equals("")) {
      error_msg = "联系手机号码不能为空！";
    } else if (mainProduct == null || mainProduct.equals("")) {
      error_msg = "主营产品不能为空！";
    } else if (content == null || content.equals("")) {
      error_msg = "公司简介不能为空！";
    } else if (address == null || address.equals("")) {
      error_msg = "公司地址不能为空！";
    } else if (manufacturerService.findManufacturerByUserId(uid) != null) {
      error_msg = "您已经提交过供应商申请，请不要重复申请！";
    } else if (consumerService.findConsumerByUserId(uid) != null) {
      error_msg = "您已经提交过分销商申请，请不要重复申请！";
    } else {
      ManufacturerPojo mPojo = new ManufacturerPojo();
      mPojo.setUserId(uid);
      mPojo.setCompany(companyName);
      mPojo.setMainCategory(mainCategory.toString());
      mPojo.setBrand(brand);
      mPojo.setScale(groups);
      mPojo.setSalesArea(salesArea);
      mPojo.setContact(contact);
      mPojo.setDuty(duty);
      mPojo.setEmail(email);
      mPojo.setQQ(QQ);
      mPojo.setPhone(phone);
      mPojo.setFax(fax);
      mPojo.setWebSite(webSite);
      mPojo.setMainProduct(mainProduct);
      mPojo.setContent(content);
      mPojo.setAddress(address);
      UserAttestationPojo attestationPojo = new UserAttestationPojo();
      Date createDate = new Date();
      try {
        if (file1 != null) {
          String ddd = uploadFile(file1, "upfiles" + File.separator + "attestation");
          attestationPojo.setUser3cImage(ddd);
        }
        if (file2 != null) {
          String ddd = uploadFile(file2, "upfiles" + File.separator + "attestation");
          attestationPojo.setAttestationImage(ddd);
        }
        attestationPojo.setUserId(uid);
        attestationPojo.setType(2);
        attestationPojo.setStatus(0);
        attestationPojo.setCreateDate(createDate);
        attestationPojo.setCreateBy(uid);
        userAttestationService.addAttestationInfo(attestationPojo);
      } catch (Exception e) {
        e.printStackTrace();
      }
      mPojo.setCreateDate(createDate);
      mPojo.setCreateBy(uid);
      mPojo.setChannel(1);
      mPojo.setStatus(0);
      mPojo.setIsread(1);
      manufacturerService.insertManufacturer(mPojo);
      result = 1;
      success = true;
      error_msg = "提交成功，您的申请我们的工作人员将在三个工作日内审核！";
    }
    map.put("success", success);
    map.put("result", result);
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
   * 用户重置密码API
   * 
   * @throws SQLException
   * */
  public String password() throws SQLException {
    int result = 0;
    boolean success = false;
    String error_msg = null;
    Map<String, Object> map = new HashMap<String, Object>();
    UserVerifyPojo userVerifyPojo = new UserVerifyPojo();
    SysLoginPojo s = new SysLoginPojo();
    s.setLoginname(phone);
    userVerifyPojo.setLoginname(phone);
    UserVerifyPojo verifyPojo = new UserVerifyPojo();
    verifyPojo = userVerifyService.findNewestByPhone(userVerifyPojo);
    if (phone == null || phone.equals("")) {
      error_msg = "手机号码不能为空！";
      result = 4;
    } else if (sysLoginService.findSysLoginByLoginname(s) == null) {
      error_msg = "手机号码不存在！";
      result = 5;
    } else if (pass == null || pass.equals("")) {
      error_msg = "密码不能为空！";
      result = 6;
    }/*
      * else if (StringUtil.CheckSecurity(pass) < 2) { error_msg = "密码强度弱，请换一个！"; result = 7; }
      */else if (pass.length() < 6) {
      error_msg = "密码强度弱（至少6位），请换一个！";
      result = 7;
    } else if (captcha == null || captcha.equals("")) {
      error_msg = "验证码不能为空！";
      result = 3;
    } else if (!captcha.equals(verifyPojo.getCaptcha())) {
      error_msg = "验证码错误！";
      result = 2;
    } else {
      SysLoginPojo sPojo = sysLoginService.findSysLoginByLoginname(s);
      // sPojo.setPassword(MD5Util.MD5(pass));
      sPojo.setPassword(pass);
      sPojo.setUpdateDate(new Date());
      sysLoginService.updatePassword(sPojo);
      success = true;
      result = 1;
      error_msg = "密码重置成功！";
    }
    map.put("success", success);
    map.put("result", result);
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
   * 添加购物车
   * 
   * @throws SQLException
   * */
  /*
   * public String addcart() throws SQLException { String msg = ""; boolean b = false; int result =
   * 0; Map<String, Object> map = new HashMap<String, Object>(); // 根据商品Id得到商品信息 ProductPojo proPojo
   * = new ProductPojo(); proPojo.setId(pid); proPojo = productService.findProduct(proPojo);
   * SysLoginPojo sysLogin = sysLoginService.findSysLoginById(uid); if (uid == null ||
   * uid.equals("")) { msg = "会员Id不能为空！"; result = 3; } else if (pid == null || pid.equals("")) {
   * msg = "商品Id不能为空！"; result = 3; } else if (num == 0 || num.equals(0)) { msg = "商品数量不能为零！";
   * result = 1; } else if (proPojo == null || proPojo.getStatus() == 0) { msg = "商品已经下架！"; result =
   * 1; } else if (sysLogin == null) { msg = "没有该用户！"; result = 1; } else { // 根据产品信息中的用户ID查询到店铺
   * ShopPojo shop = new ShopPojo(); shop.setUserId(proPojo.getUserId()); shop =
   * shopService.findShop(shop);
   * 
   * CartPojo cartPojo = new CartPojo(); cartPojo.setUserId(sysLogin.getId());
   * cartPojo.setProductId(proPojo.getId()); CartPojo productCart =
   * cartService.findCartByProductId(cartPojo);
   * 
   * // 判断活动价 Double factPrice = proPojo.getDistributionPrice(); // 秒杀活动产品不加入购物车 //
   * ActivityGoodsPojo activityGoods = // isProductActivity(proPojo.getId());
   * 
   * if (productCart == null) { cartPojo.prePersist(sysLogin);
   * 
   * String prices = proPojo.getLadderPrice(); JSONArray json = JSONArray.fromObject(prices); int
   * min, max = 0; for (int i = 0; i < json.size(); i++) { JSONObject object0 =
   * JSONObject.fromObject(json.get(i)); String strMinNum = (String) object0.get("min"); min =
   * Integer.valueOf(strMinNum).intValue(); String strMaxNum = (String) object0.get("max"); max =
   * Integer.valueOf(strMaxNum).intValue(); if (min <= num && max >= num) { String bPrice = (String)
   * object0.get("price"); price = Double.valueOf(bPrice); } else if (min <= num && (max == 0)) {
   * String bPrice = (String) object0.get("price"); price = Double.valueOf(bPrice); } else if (num >
   * max && (max > 0)) { String bPrice = (String) object0.get("price"); price =
   * Double.valueOf(bPrice); } }
   * 
   * long StockId = 0; cartPojo.setUserId(sysLogin.getId()); cartPojo.setShopId(shop.getId());
   * cartPojo.setProductId(proPojo.getId()); cartPojo.setProductName(proPojo.getProductName());
   * cartPojo.setProductImage(proPojo.getImage()); cartPojo.setWeight(proPojo.getWeight());
   * cartPojo.setStockId(StockId); // cartPojo.setStockPriceOld(proPojo.getDistributionPrice()); //
   * cartPojo.setStockPrice(price); // 没有阶梯价格 //
   * cartPojo.setStockPrice(proPojo.getDistributionPrice()); // 产品原价
   * cartPojo.setStockPriceOld(proPojo.getSellingPrice());
   * 
   * 
   * if (activityGoods != null && activityGoods.getActivePrice() != null &&
   * activityGoods.getActivePrice().doubleValue() != 0) { factPrice =
   * activityGoods.getActivePrice(); // 购买数量大于库存时，只能购买库存数量 if (activityGoods.getActivityStock() !=
   * null && num > activityGoods.getActivityStock().intValue()) { num =
   * activityGoods.getActivityStock().intValue(); } }
   * 
   * cartPojo.setStockPrice(factPrice); cartPojo.setNum(num); cartPojo.setType(0);
   * cartPojo.setChannel(1); cartPojo.setStatus(0); cartPojo.setUserName(sysLogin.getName());
   * cartPojo.setShopName(shop.getName()); cartPojo.setPostageType(proPojo.getPostageType());
   * cartService.insertCart(cartPojo); msg = "恭喜，已增加至购物车！"; b = true; result = 0; } else { int old,
   * newNo, min, max = 0; old = productCart.getNum(); newNo = old + num.intValue();
   * 
   * String prices = proPojo.getLadderPrice(); JSONArray json = JSONArray.fromObject(prices); for
   * (int i = 0; i < json.size(); i++) { JSONObject object0 = JSONObject.fromObject(json.get(i));
   * String strMinNum = (String) object0.get("min"); min = Integer.valueOf(strMinNum).intValue();
   * String strMaxNum = (String) object0.get("max"); max = Integer.valueOf(strMaxNum).intValue(); if
   * (min <= newNo && max >= newNo) { String bPrice = (String) object0.get("price"); price =
   * Double.valueOf(bPrice); } else if (min <= newNo && (max == 0)) { String bPrice = (String)
   * object0.get("price"); price = Double.valueOf(bPrice); } else if (num.intValue() > max && (max >
   * 0)) { String bPrice = (String) object0.get("price"); price = Double.valueOf(bPrice); } }
   * 
   * productCart.preUpdate(sysLogin);
   * 
   * 
   * if (price != null) { productCart.setStockPrice(price); } else {
   * productCart.setStockPrice(productCart.getStockPrice()); }
   * 
   * // productCart.setStockPrice(proPojo.getDistributionPrice());
   * 
   * if (activityGoods != null && activityGoods.getActivePrice() != null &&
   * activityGoods.getActivePrice().doubleValue() != 0) { factPrice =
   * activityGoods.getActivePrice(); // 购买数量大于库存时，只能购买库存数量 if (activityGoods.getActivityStock() !=
   * null && newNo > activityGoods.getActivityStock().intValue()) { newNo =
   * activityGoods.getActivityStock().intValue(); } }
   * 
   * productCart.setStockPrice(factPrice); productCart.setNum(newNo);
   * cartService.updateCart(productCart); msg = "您增加购物车好辛苦，休息一下！"; b = true; result = 0; }
   * 
   * } map.put("success", b); map.put("error_msg", msg); map.put("result", result); JSONObject json
   * = JSONObject.fromObject(map);
   * ServletActionContext.getResponse().setContentType("text/html; charset=utf-8"); try {
   * ServletActionContext.getResponse().getWriter().write(json.toString()); } catch (IOException e)
   * { e.printStackTrace(); } return null; }
   */
  public String addcart() throws SQLException {
    String msg = "";
    boolean b = false;
    int flag = 0;
    int result = 0;
    Map<String, Object> map = new HashMap<String, Object>();
    // 根据商品Id得到商品信息
    ProductPojo proPojo = new ProductPojo();
    proPojo.setId(pid);
    proPojo = productService.findProduct(proPojo);
    SysLoginPojo sysLogin = sysLoginService.findSysLoginById(uid);
    if (uid == null || uid.equals("")) {
      msg = "会员Id不能为空！";
      result = 3;
    } else if (pid == null || pid.equals("")) {
      msg = "商品Id不能为空！";
      result = 3;
    } else if (num == 0 || num.equals(0)) {
      msg = "商品数量不能为零！";
      result = 1;
    } else if (proPojo == null || proPojo.getStatus() == 0) {
      msg = "商品已经下架！";
      result = 1;
    } else if (sysLogin == null) {
      msg = "没有该用户！";
      result = 1;
    } else if (activityId == null || activityId == 0) {
      msg = "活动已结束，请选择其他商品！";
      result = 1;
    } else {
      // 根据产品信息中的用户ID查询到店铺
      ShopPojo shop = new ShopPojo();
      shop.setUserId(proPojo.getUserId());
      shop = shopService.findShop(shop);
      if (activityId == null) {
        activityId = 0L;
      }
      // 秒杀处理 暂时处理
      /*
       * if (activityId == null || activityId == 0L) { Map<String, Object> activity = new
       * HashMap<String, Object>(); activity.put("productId", pid); ActivityGoodsPojo activityGood =
       * activityGoodsService.findActivityGoodsByPidTmp(activity); if (activityGood != null &&
       * activityGood.getActivePrice() != null && activityGood.getActivePrice().doubleValue() != 0 )
       * { activityId = activityGood.getTimeId(); } else { activityId = 0L; } }
       */
      // 活动判断
      // 活动产品
      ActivityGoodsPojo activityGoods = null;
      if (activityId > 0) {
        activityGoods = isProductActivity(proPojo.getId(), activityId);
        if (activityGoods == null) {
          // 活动已结束或未开始，提示活动库存不足.
          flag = 2;
        }
      }
      CartPojo productCart = null;
      Double price = 0.00;
      if (flag == 0) {
        CartPojo cartPojo = new CartPojo();
        cartPojo.setUserId(sysLogin.getId());
        cartPojo.setProductId(proPojo.getId());
        cartPojo.setActivityId(activityId);
        cartPojo.setSkuLinkId(skuLinkId);
        productCart = cartService.findCartByProductId(cartPojo);
        // 购物车购买数量
        num = productCart == null ? num : productCart.getNum() + num;

        // sku判断
        price = proPojo.getDistributionPrice();
        ProductSkuLinkPojo productSkuLinkCheck = null;
        if (skuLinkId != null && skuLinkId > 0) {
          productSkuLinkCheck = getProducSku(proPojo.getId(), skuLinkId);
          if (productSkuLinkCheck != null) {
            if (productSkuLinkCheck.getPrice() != null && productSkuLinkCheck.getPrice() != 0) {
              price = productSkuLinkCheck.getPrice();
            }
            if (num > productSkuLinkCheck.getStock()) {
              // 购买数量大于库存时
              flag = 2;
            }
          } else {
            // 找不到sku信息
            flag = 2;
          }
        }


        // 活动判断
        else if (activityGoods != null) {
          if (activityGoods.getActivePrice() != null
              && activityGoods.getActivePrice().doubleValue() != 0) {
            price = activityGoods.getActivePrice();
          }

          if (activityGoods.getActivityType() != null && activityGoods.getActivityType() == 0
              && num > 3) {
            // 秒杀活动限购数量为3
            flag = 1;
          } else if (activityGoods.getActivityStock() == null
              || num > activityGoods.getActivityStock().intValue()) {
            // 购买数量大于库存时，只能购买库存数量
            flag = 2;
          }
        }
      }


      if (flag == 1) {
        msg = "对不起，您购买的数量已超过限购数量哦！";
        result = 1;
      } else if (flag == 2) {
        if (productCart == null) {
          msg = "对不起，您购买的商品库存已完结，请选择其他商品购买！";
        } else {
          msg = "对不起，您购买的数量已超过剩余库存！";
        }
        result = 1;
      } else {
        if (productCart == null) {
          CartPojo cartPojo = new CartPojo();
          cartPojo.prePersist(sysLogin);

          long StockId = 0;
          cartPojo.setUserId(sysLogin.getId());
          cartPojo.setShopId(shop.getId());
          cartPojo.setProductId(proPojo.getId());
          cartPojo.setProductName(proPojo.getProductName());
          cartPojo.setProductImage(proPojo.getImage());
          cartPojo.setWeight(proPojo.getWeight());
          cartPojo.setStockId(StockId);
          // 产品原价
          cartPojo.setStockPriceOld(proPojo.getSellingPrice());
          cartPojo.setStockPrice(price);
          cartPojo.setNum(num);
          cartPojo.setType(0);
          cartPojo.setChannel(1);
          cartPojo.setStatus(0);
          cartPojo.setUserName(sysLogin.getName());
          cartPojo.setShopName(shop.getName());
          cartPojo.setPostageType(proPojo.getPostageType());
          cartPojo.setSkuLinkId(skuLinkId);
          cartPojo.setActivityId(activityId);
          cartService.insertCart(cartPojo);
          msg = "恭喜，已增加至购物车！";
        } else {
          // int old, newNo;
          // old = productCart.getNum();
          // newNo = old + num.intValue();
          productCart.preUpdate(sysLogin);

          // 活动判断
          /*
           * if (activityGoods != null && activityGoods.getActivePrice() != null &&
           * activityGoods.getActivePrice().doubleValue() != 0) { price =
           * activityGoods.getActivePrice(); // 购买数量大于库存时，只能购买库存数量 if
           * (activityGoods.getActivityStock() != null && newNo >
           * activityGoods.getActivityStock().intValue()) { newNo =
           * activityGoods.getActivityStock().intValue(); } }
           */

          productCart.setNum(num);
          productCart.setStockPrice(price);
          cartService.updateCart(productCart);
          msg = "您增加购物车好辛苦，休息一下！";
        }
        b = true;
        result = 0;
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
   * 添加购物车
   * 
   * @throws SQLException
   * */
  /*
   * public String toAddcart() throws SQLException { String msg = ""; boolean b = false; int flag =
   * 0; int result = 0; Map<String, Object> map = new HashMap<String, Object>(); // 根据商品Id得到商品信息
   * ProductPojo proPojo = new ProductPojo(); proPojo.setId(pid); proPojo =
   * productService.findProduct(proPojo); SysLoginPojo sysLogin =
   * sysLoginService.findSysLoginById(uid); if (uid == null || uid.equals("")) { msg = "会员Id不能为空！";
   * result = 3; } else if (pid == null || pid.equals("")) { msg = "商品Id不能为空！"; result = 3; } else
   * if (num == 0 || num.equals(0)) { msg = "商品数量不能为零！"; result = 1; } else if (proPojo == null ||
   * proPojo.getStatus() == 0) { msg = "商品已经下架！"; result = 1; } else if (sysLogin == null) { msg =
   * "没有该用户！"; result = 1; } else { // 根据产品信息中的用户ID查询到店铺 ShopPojo shop = new ShopPojo();
   * shop.setUserId(proPojo.getUserId()); shop = shopService.findShop(shop); // 暂时处理 if (activityId
   * == null) { activityId = 0L; } // 秒杀处理 if (activityId == null || activityId == 0L) { Map<String,
   * Object> activity = new HashMap<String, Object>(); activity.put("productId", pid);
   * ActivityGoodsPojo activityGood = activityGoodsService.findActivityGoodsByPidTmp(activity); if
   * (activityGood != null && activityGood.getActivePrice() != null &&
   * activityGood.getActivePrice().doubleValue() != 0 ) { activityId = activityGood.getTimeId(); }
   * else { activityId = 0L; } }
   * 
   * CartPojo productCart = new CartPojo(); CartPojo cartPojo = new CartPojo();
   * cartPojo.setUserId(sysLogin.getId()); cartPojo.setProductId(proPojo.getId());
   * cartPojo.setActivityId(activityId); cartPojo.setSkuLinkId(skuLinkId); productCart =
   * cartService.findCartByProductId(cartPojo); // 活动价格判断 Double price =
   * proPojo.getDistributionPrice(); ProductSkuLinkPojo productSkuLinkCheck = null; if (skuLinkId !=
   * null && skuLinkId > 0) { productSkuLinkCheck = getProducSku(proPojo.getId(), skuLinkId); if
   * (productSkuLinkCheck != null && productSkuLinkCheck.getPrice() != null &&
   * productSkuLinkCheck.getPrice() != 0) { price = productSkuLinkCheck.getPrice(); } } // 活动产品
   * ActivityGoodsPojo activityGoods = isProductActivity(proPojo.getId(), activityId);
   * 
   * num = productCart == null ? num : productCart.getNum() + num;
   * 
   * // 活动判断
   * 
   * if (activityGoods != null && activityGoods.getActivePrice() != null &&
   * activityGoods.getActivePrice().doubleValue() != 0) { if (skuLinkId != null && skuLinkId > 0 &&
   * productSkuLinkCheck != null) { // productSkuLinkCheck = getProducSku(proPojo.getId(),
   * skuLinkId); //if (productSkuLinkCheck != null && productSkuLinkCheck.getPrice() != null &&
   * productSkuLinkCheck.getPrice() != 0) { // price = productSkuLinkCheck.getPrice(); // } price =
   * productSkuLinkCheck.getPrice(); } else { price = activityGoods.getActivePrice(); }
   * 
   * if (activityGoods.getActivityType() != null && activityGoods.getActivityType() == 0 && num > 3)
   * { // 秒杀活动限购数量为3 flag = 1; } else if (skuLinkId != null && skuLinkId > 0 && productSkuLinkCheck
   * != null && num > productSkuLinkCheck.getStock()) { // 购买数量大于库存时，只能购买库存数量 flag = 2; } else if
   * (activityGoods.getActivityStock() != null && num > activityGoods.getActivityStock().intValue())
   * { // 购买数量大于库存时，只能购买库存数量 flag = 2; } }
   * 
   * if (flag == 1) { msg = "对不起，您购买的数量已超过限购数量哦！"; result = 1; } else if(flag == 2){ msg =
   * "对不起，您购买的数量已超过活动剩余库存！"; result = 1; } else { if (productCart == null) {
   * cartPojo.prePersist(sysLogin);
   * 
   * long StockId = 0; cartPojo.setUserId(sysLogin.getId()); cartPojo.setShopId(shop.getId());
   * cartPojo.setProductId(proPojo.getId()); cartPojo.setProductName(proPojo.getProductName());
   * cartPojo.setProductImage(proPojo.getImage()); cartPojo.setWeight(proPojo.getWeight());
   * cartPojo.setStockId(StockId); // 产品原价 cartPojo.setStockPriceOld(proPojo.getSellingPrice());
   * cartPojo.setStockPrice(price); cartPojo.setNum(num); cartPojo.setType(0);
   * cartPojo.setChannel(1); cartPojo.setStatus(0); cartPojo.setUserName(sysLogin.getName());
   * cartPojo.setShopName(shop.getName()); cartPojo.setPostageType(proPojo.getPostageType());
   * cartPojo.setSkuLinkId(skuLinkId); cartPojo.setActivityId(activityId);
   * cartService.insertCart(cartPojo); msg = "恭喜，已增加至购物车！"; } else { // int old, newNo; // old =
   * productCart.getNum(); // newNo = old + num.intValue(); productCart.preUpdate(sysLogin);
   * 
   * // 活动判断 if (activityGoods != null && activityGoods.getActivePrice() != null &&
   * activityGoods.getActivePrice().doubleValue() != 0) { price = activityGoods.getActivePrice(); //
   * 购买数量大于库存时，只能购买库存数量 if (activityGoods.getActivityStock() != null && newNo >
   * activityGoods.getActivityStock().intValue()) { newNo =
   * activityGoods.getActivityStock().intValue(); } }
   * 
   * productCart.setNum(num); productCart.setStockPrice(price); cartService.updateCart(productCart);
   * msg = "您增加购物车好辛苦，休息一下！"; } b = true; result = 0; } }
   * 
   * map.put("success", b); map.put("error_msg", msg); map.put("result", result); JSONObject json =
   * JSONObject.fromObject(map);
   * ServletActionContext.getResponse().setContentType("text/html; charset=utf-8"); try {
   * ServletActionContext.getResponse().getWriter().write(json.toString()); } catch (IOException e)
   * { e.printStackTrace(); } return null; }
   */

  public String toAddcart() throws SQLException {
    String msg = "";
    boolean b = false;
    int flag = 0;
    int result = 0;
    Map<String, Object> map = new HashMap<String, Object>();
    // 根据商品Id得到商品信息
    ProductPojo proPojo = new ProductPojo();
    proPojo.setId(pid);
    proPojo = productService.findProduct(proPojo);
    SysLoginPojo sysLogin = sysLoginService.findSysLoginById(uid);
    if (uid == null || uid.equals("")) {
      msg = "会员Id不能为空！";
      result = 3;
    } else if (pid == null || pid.equals("")) {
      msg = "商品Id不能为空！";
      result = 3;
    } else if (num == 0 || num.equals(0)) {
      msg = "商品数量不能为零！";
      result = 1;
    } else if (proPojo == null || proPojo.getStatus() == 0) {
      msg = "商品已经下架！";
      result = 1;
    } else if (sysLogin == null) {
      msg = "没有该用户！";
      result = 1;
    } else if (activityId == null || activityId == 0) {
      msg = "活动已结束，请选择其他商品！";
      result = 1;
    } else {
      // 根据产品信息中的用户ID查询到店铺
      ShopPojo shop = new ShopPojo();
      shop.setUserId(proPojo.getUserId());
      shop = shopService.findShop(shop);
      if (activityId == null) {
        activityId = 0L;
      }
      // 秒杀处理 暂时处理
      /*
       * if (activityId == null || activityId == 0L) { Map<String, Object> activity = new
       * HashMap<String, Object>(); activity.put("productId", pid); ActivityGoodsPojo activityGood =
       * activityGoodsService.findActivityGoodsByPidTmp(activity); if (activityGood != null &&
       * activityGood.getActivePrice() != null && activityGood.getActivePrice().doubleValue() != 0 )
       * { activityId = activityGood.getTimeId(); } else { activityId = 0L; } }
       */
      // 活动判断
      // 活动产品
      ActivityGoodsPojo activityGoods = null;
      if (activityId > 0) {
        activityGoods = isProductActivity(proPojo.getId(), activityId);
        if (activityGoods == null) {
          // 活动已结束或未开始，提示活动库存不足.
          flag = 2;
        }
      }
      CartPojo productCart = null;
      Double price = 0.00;
      if (flag == 0) {
        CartPojo cartPojo = new CartPojo();
        cartPojo.setUserId(sysLogin.getId());
        cartPojo.setProductId(proPojo.getId());
        cartPojo.setActivityId(activityId);
        cartPojo.setSkuLinkId(skuLinkId);
        productCart = cartService.findCartByProductId(cartPojo);
        // 购物车购买数量
        num = productCart == null ? num : productCart.getNum() + num;

        // sku判断
        price = proPojo.getDistributionPrice();
        ProductSkuLinkPojo productSkuLinkCheck = null;
        if (skuLinkId != null && skuLinkId > 0) {
          productSkuLinkCheck = getProducSku(proPojo.getId(), skuLinkId);
          if (productSkuLinkCheck != null) {
            if (productSkuLinkCheck.getPrice() != null && productSkuLinkCheck.getPrice() != 0) {
              price = productSkuLinkCheck.getPrice();
            }
            if (num > productSkuLinkCheck.getStock()) {
              // 购买数量大于库存时
              flag = 2;
            }
          } else {
            // 找不到sku信息
            flag = 2;
          }
        }


        // 活动判断
        else if (activityGoods != null) {
          if (activityGoods.getActivePrice() != null
              && activityGoods.getActivePrice().doubleValue() != 0) {
            price = activityGoods.getActivePrice();
          }

          if (activityGoods.getActivityType() != null && activityGoods.getActivityType() == 0
              && num > 3) {
            // 秒杀活动限购数量为3
            flag = 1;
          } else if (activityGoods.getActivityStock() == null
              || num > activityGoods.getActivityStock().intValue()) {
            // 购买数量大于库存时，只能购买库存数量
            flag = 2;
          }
        }
      }


      if (flag == 1) {
        msg = "对不起，您购买的数量已超过限购数量哦！";
        result = 1;
      } else if (flag == 2) {
        if (productCart == null) {
          msg = "对不起，您购买的商品库存已完结，请选择其他商品购买！";
        } else {
          msg = "对不起，您购买的数量已超过剩余库存！";
        }
        result = 1;
      } else {
        if (productCart == null) {
          CartPojo cartPojo = new CartPojo();
          cartPojo.prePersist(sysLogin);

          long StockId = 0;
          cartPojo.setUserId(sysLogin.getId());
          cartPojo.setShopId(shop.getId());
          cartPojo.setProductId(proPojo.getId());
          cartPojo.setProductName(proPojo.getProductName());
          cartPojo.setProductImage(proPojo.getImage());
          cartPojo.setWeight(proPojo.getWeight());
          cartPojo.setStockId(StockId);
          // 产品原价
          cartPojo.setStockPriceOld(proPojo.getSellingPrice());
          cartPojo.setStockPrice(price);
          cartPojo.setNum(num);
          cartPojo.setType(0);
          cartPojo.setChannel(1);
          cartPojo.setStatus(0);
          cartPojo.setUserName(sysLogin.getName());
          cartPojo.setShopName(shop.getName());
          cartPojo.setPostageType(proPojo.getPostageType());
          cartPojo.setSkuLinkId(skuLinkId);
          cartPojo.setActivityId(activityId);
          cartService.insertCart(cartPojo);
          msg = "恭喜，已增加至购物车！";
        } else {
          // int old, newNo;
          // old = productCart.getNum();
          // newNo = old + num.intValue();
          productCart.preUpdate(sysLogin);

          // 活动判断
          /*
           * if (activityGoods != null && activityGoods.getActivePrice() != null &&
           * activityGoods.getActivePrice().doubleValue() != 0) { price =
           * activityGoods.getActivePrice(); // 购买数量大于库存时，只能购买库存数量 if
           * (activityGoods.getActivityStock() != null && newNo >
           * activityGoods.getActivityStock().intValue()) { newNo =
           * activityGoods.getActivityStock().intValue(); } }
           */

          productCart.setNum(num);
          productCart.setStockPrice(price);
          cartService.updateCart(productCart);
          msg = "您增加购物车好辛苦，休息一下！";
        }
        b = true;
        result = 0;
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
   * 我的购物车
   * 
   * @throws SQLException
   * */
  public String mycart() throws SQLException {
    SysLoginPojo sysLogin = sysLoginService.findSysLoginById(uid);
    List<Object> list = new ArrayList<Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid == null || uid.equals("")) {
      map.put("error_msg", "用户ID不能为空！");
      map.put("result", list);
      map.put("success", b);
    } else if (sysLogin == null) {
      map.put("error_msg", "没有该用户！");
      map.put("success", b);
      map.put("result", list);
    } else {
      List<CartPojo> cartList = cartService.findCartByUserId(sysLogin.getId());
      // 秒杀活动产品不加入购物车，直接购买
      // ActivityGoodsPojo activityGoods = null;
      ProductPojo product = new ProductPojo();
      int isActive = 0;
      for (CartPojo cartPojo : cartList) {
        product.setId(cartPojo.getProductId());
        product = productService.findProduct(product);
        // 查看时更新购物车商品价格
        /*
         * activityGoods = isProductActivity(cartPojo.getProductId()); if (activityGoods != null &&
         * activityGoods.getActivePrice() != null && activityGoods.getActivePrice().doubleValue() !=
         * 0) { cartPojo.setStockPrice(activityGoods.getActivePrice()); if
         * (activityGoods.getActivityStock() != null && cartPojo.getNum().intValue() >
         * activityGoods.getActivityStock().intValue()) {
         * cartPojo.setNum(activityGoods.getActivityStock().intValue()); } isActive = 1;
         * cartService.updateCart(cartPojo); } else { isActive = 0; if (product != null &&
         * cartPojo.getStockPrice(). compareTo(product.getDistributionPrice()) != 0) {
         * cartPojo.setStockPrice(product.getDistributionPrice()); cartService.updateCart(cartPojo);
         * } }
         */
        Map<String, Object> mapShop = new HashMap<String, Object>();
        mapShop.put("shopId", cartPojo.getShopId());
        mapShop.put("shopName", cartPojo.getShopName());
        mapShop.put("cid", cartPojo.getId().toString());
        mapShop.put("productId", cartPojo.getProductId().toString());
        mapShop.put("isActive", isActive);
        mapShop.put("name", cartPojo.getProductName().toString());
        mapShop.put("price", df.format(cartPojo.getStockPrice()));
        mapShop.put("number", cartPojo.getNum().toString());
        mapShop.put("image",
            ConstParam.URL + "/upfiles/product/small/" + cartPojo.getProductImage());
        // 根据商品ID查询该商品的阶梯价格
        // ProductPojo product = new ProductPojo();
        // product.setId(cartPojo.getProductId());
        // product = productService.findProduct(product);
        mapShop.put("status", product.getStatus());
        String string = product.getLadderPrice();
        JSONArray json = JSONArray.fromObject(string);
        if (json.size() == 1) {
          String min = (String) JSONObject.fromObject(json.get(0)).get("max");
          String minprices = (String) JSONObject.fromObject(json.get(0)).get("price");
          mapShop.put("min", min);
          mapShop.put("minprices", minprices);
        } else if (json.size() == 2) {
          String min = (String) JSONObject.fromObject(json.get(0)).get("max");
          String mid = (String) JSONObject.fromObject(json.get(1)).get("max");
          String minprices = (String) JSONObject.fromObject(json.get(0)).get("price");
          String midprices = (String) JSONObject.fromObject(json.get(1)).get("price");
          mapShop.put("min", min);
          mapShop.put("mid", mid);
          mapShop.put("minprices", minprices);
          mapShop.put("midprices", midprices);
        } else {
          String min = (String) JSONObject.fromObject(json.get(0)).get("max");
          String mid = (String) JSONObject.fromObject(json.get(1)).get("max");
          String max = (String) JSONObject.fromObject(json.get(2)).get("max");
          String minprices = (String) JSONObject.fromObject(json.get(0)).get("price");
          String midprices = (String) JSONObject.fromObject(json.get(1)).get("price");
          String maxprices = (String) JSONObject.fromObject(json.get(2)).get("price");
          mapShop.put("max", max);
          mapShop.put("min", min);
          mapShop.put("mid", mid);
          mapShop.put("minprices", minprices);
          mapShop.put("midprices", midprices);
          mapShop.put("maxprices", maxprices);
        }
        list.add(mapShop);
      }
      b = true;
      if (list.size() == 0) {
        map.put("result", list);
        map.put("error_msg", "亲，您的购物车里没有东西哦，赶紧去选购吧！");
      } else {
        map.put("result", list);
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
   * b2c我的购物车
   * 
   * @throws SQLException
   * */
  public String tomycart() throws SQLException {
    SysLoginPojo sysLogin = sysLoginService.findSysLoginById(uid);
    List<Object> list = new ArrayList<Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid == null || uid.equals("")) {
      map.put("error_msg", "用户ID不能为空！");
      map.put("result", list);
      map.put("success", b);
    } else if (sysLogin == null) {
      map.put("error_msg", "没有该用户！");
      map.put("success", b);
      map.put("result", list);
    } else {
      long shopId = 0;
      // List<CartPojo> cartList =
      // cartService.findCartByShopUserId(sysLogin.getId());
      List<CartPojo> cartList = cartService.findCartShopByUserId(sysLogin.getId());
      // 活动产品
      ActivityGoodsPojo activityGoods = null;
      // 0-没有活动，1-活动进行中，2-活动结束
      int isActive = 0;
      ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
      ProductSkuLinkPojo productSkuLinkPojo = null;
      for (CartPojo cartPojo : cartList) {
        if (shopId != cartPojo.getShopId()) {
          List<Object> clist = new ArrayList<Object>();
          CartPojo cPojo = new CartPojo();
          cPojo.setUserId(uid);
          cPojo.setShopId(cartPojo.getShopId());
          List<CartPojo> shopCartList = cartService.findCartByUserIdAndShopId(cPojo);
          Map<String, Object> mapShop = new HashMap<String, Object>();
          ActivityTimePojo activityTimePojo = null;
          ProductSkuLinkPojo productSkuLinkCheck = null;
          for (CartPojo sCartPojo : shopCartList) {
            // 根据商品ID查询该商品的阶梯价格
            ProductPojo product = new ProductPojo();
            product.setId(sCartPojo.getProductId());
            product = productService.findProduct(product);

            // 商品sku
            Integer skuid = sCartPojo.getSkuLinkId();
            // 活动id
            Long activityId = sCartPojo.getActivityId();
            if (activityId == null) {
              activityId = 0L;
            }
            // 判断活动是否结束
            isActive = 0;
            // 活动判断
            if (activityId > 0) {
              activityGoods = isProductActivity(sCartPojo.getProductId(), activityId);
            }
            // 有sku
            if (skuid != null && skuid > 0) {
              productSkuLinkCheck = getProducSku(product.getId(), skuid);
              if (productSkuLinkCheck != null) {
                if (sCartPojo.getNum() > productSkuLinkCheck.getStock()) {
                  // 购买数量大于库存时
                  isActive = 2;
                } else if (activityId > 0 && activityGoods != null) {
                  // 活动进行中
                  isActive = 1;
                } else if (activityId > 0 && activityGoods == null) {
                  // 活动结束状态
                  isActive = 2;
                }
              } else {
                isActive = 2;
              }
            } else if (activityId > 0) {
              // 有活动，判断活动是否正常进行
              if (activityGoods != null && activityGoods.getActivePrice() != null
                  && activityGoods.getActivePrice().doubleValue() != 0
                  && activityGoods.getActivityStock() != null
                  && activityGoods.getActivityStock() >= sCartPojo.getNum()) {
                isActive = 1;
              } else {
                isActive = 2;
              }
            }

            Map<String, Object> mapCart = new HashMap<String, Object>();
            mapCart.put("shopId", sCartPojo.getShopId());
            mapCart.put("shopName", sCartPojo.getShopName());
            mapCart.put("cid", sCartPojo.getId().toString());
            mapCart.put("productId", sCartPojo.getProductId().toString());
            mapCart.put("isActive", isActive);
            mapCart.put("name", sCartPojo.getProductName().toString());
            mapCart.put("price", df.format(sCartPojo.getStockPrice()));
            mapCart.put("sellingPrice", df.format(sCartPojo.getStockPriceOld()));
            mapCart.put("discount",
                calcDiscount(sCartPojo.getStockPrice(), sCartPojo.getStockPriceOld()));
            mapCart.put("number", sCartPojo.getNum().toString());
            mapCart.put("image",
                ConstParam.URL + "/upfiles/product/small/" + sCartPojo.getProductImage());

            mapCart.put("status", product.getStatus());
            // mapCart.put("type", sCartPojo.getType());
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
            if (sCartPojo.getActivityId() == null || sCartPojo.getActivityId() == 0) {
              // 普通商品
              mapCart.put("type", 0);
            } else {
              activityTimePojo =
                  activityTimeService.findActivityTimeById(sCartPojo.getActivityId());
              if (activityTimePojo != null && activityTimePojo.getType() == 0) {
                // 秒杀商品
                mapCart.put("type", 1);
              } else if (activityTimePojo != null && activityTimePojo.getType() == 1) {
                // 专题商品
                mapCart.put("type", 2);
              } else {
                // 活动商品
                mapCart.put("type", 3);
              }

            }
            clist.add(mapCart);
          }
          mapShop.put("shopId", cartPojo.getShopId());
          mapShop.put("shopName", cartPojo.getShopName());
          mapShop.put("carts", clist);
          list.add(mapShop);
        }
        shopId = cartPojo.getShopId();
      }
      b = true;
      if (list.size() == 0) {
        map.put("result", list);
        map.put("error_msg", "亲，您的购物车里没有东西哦，赶紧去选购吧！");
      } else {
        map.put("result", list);
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
   * 
   * 修改B2c购物车数量
   * 
   * @throws SQLException
   * */
  public String toEditCart() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    boolean b = false;
    String msg = "";
    if (uid == null || "".equals(uid)) {
      map.put("error_msg", "用户ID不能为空！");
      map.put("result", 0);
    } else if (cids == null || cids.equals("")) {
      map.put("error_msg", "购物车ID不能为空！");
      map.put("result", 0);
    } else {
      String[] sourceStrArray = cids[0].split(",");
      ProductPojo productPojo = null;
      // 活动产品
      ActivityGoodsPojo activityGoods = null;
      ProductSkuLinkPojo productSkuLinkCheck = null;
      CartPojo productCart = null;
      for (int i = 0; i < sourceStrArray.length; i++) {
        // 分解字符串分别得到购物车ID和数量
        String[] strings = sourceStrArray[i].split(":");

        long idsplit = Long.parseLong(strings[0]);
        int numsplit = Integer.parseInt(strings[1]);
        if (0 == numsplit) {
          numsplit = 1;
        }
        productCart = cartService.findCartById(idsplit);
        if (productCart != null) {
          productPojo = new ProductPojo();
          productPojo.setId(productCart.getProductId());
          productPojo = productService.findProduct(productPojo);
          // Double price = productPojo.getSellingPrice();
          // Double price = productPojo.getDistributionPrice();
          // 活动价格判断
          Integer skuid = productCart.getSkuLinkId();
          Long activityId = productCart.getActivityId();
          if (activityId == null) {
            activityId = 0L;
          }
          if (activityId > 0) {
            activityGoods = isProductActivity(productCart.getProductId(), activityId);
          }
          if (skuid != null && skuid > 0) {
            productSkuLinkCheck = getProducSku(productPojo.getId(), skuid);
            if (productSkuLinkCheck != null) {
              if (numsplit > productSkuLinkCheck.getStock()) {
                // 购买数量大于库存时
                msg += productPojo.getProductName() + ",";
                continue;
              } else if (activityId > 0 && activityGoods == null) {
                // 活动信息不全
                msg += productPojo.getProductName() + ",";
                continue;
              }
            } else {
              msg += productPojo.getProductName() + ",";
              continue;
            }
          } else if (activityId > 0) {
            // 活动信息不全或没有库存
            if (!(activityGoods != null && activityGoods.getActivePrice() != null
                && activityGoods.getActivePrice().doubleValue() != 0
                && activityGoods.getActivityStock() != null && numsplit <= activityGoods
                .getActivityStock().intValue())) {
              msg += productPojo.getProductName() + ",";
              continue;
            }
          }

          // productCart.setStockPrice(price);
          productCart.setNum(numsplit);
          cartService.updateNumCartWeb(productCart);
        }
      }
      if (msg.length() > 0) {
        if (msg.length() >= 21) {
          msg = msg.substring(0, 20) + "...";
        } else {
          // 截掉最后一个逗号
          msg = msg.substring(0, msg.length() - 1);
        }
        msg = "您购买的商品[" + msg + "]数量大于剩余库存！";
        map.put("result", 0);
        b = false;
      } else {
        msg = "修改成功！";
        map.put("result", 1);
        b = true;
      }

      map.put("error_msg", msg);
    }
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
   * 修改购物车数量
   * 
   * @throws SQLException
   * */
  public String editCart() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    boolean b = false;
    if (uid == null || uid.equals("")) {
      map.put("error_msg", "用户ID不能为空！");
      map.put("result", 0);
    } else if (cids == null || cids.equals("")) {
      map.put("error_msg", "购物车ID不能为空！");
      map.put("result", 0);
    } else {
      String[] sourceStrArray = cids[0].split(",");
      for (int i = 0; i < sourceStrArray.length; i++) {
        // 分解字符串分别得到购物车ID和数量
        String[] strings = sourceStrArray[i].split(":");
        for (int k = 0; k < strings.length; k++) {
          String idsplit = strings[0];
          int numsplit = Integer.parseInt(strings[1]);
          CartPojo productCart = cartService.findCartById(Long.parseLong(idsplit));
          ProductPojo productPojo = new ProductPojo();
          productPojo.setId(productCart.getProductId());
          productPojo = productService.findProduct(productPojo);
          String prices = productPojo.getLadderPrice();
          JSONArray json = JSONArray.fromObject(prices);
          int minNum, maxNum = 0;
          Double buyPrice = 0.0;
          for (int j = 0; j < json.size(); j++) {
            JSONObject object0 = JSONObject.fromObject(json.get(j));
            String strMinNum = (String) object0.get("min");
            minNum = Integer.valueOf(strMinNum).intValue();
            String strMaxNum = (String) object0.get("max");
            maxNum = Integer.valueOf(strMaxNum).intValue();
            if (minNum <= numsplit && maxNum >= numsplit || minNum <= numsplit && maxNum == 0) {
              String bPrice = (String) object0.get("price");
              buyPrice = Double.valueOf(bPrice);
            } else if (numsplit > maxNum && maxNum > 0) {
              String bPrice = (String) object0.get("price");
              buyPrice = Double.valueOf(bPrice);
            }
          }
          productCart.setStockPrice(buyPrice);
          productCart.setNum(numsplit);
          cartService.updateNumCartWeb(productCart);
          b = true;
          map.put("result", 1);
          map.put("error_msg", "修改成功！");
        }
      }
    }
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
   * 删除购物车
   * 
   * @throws SQLException
   * */
  public String delCart() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    boolean b = false;
    if (cids == null || cids.equals("")) {
      map.put("error_msg", "购物车Id不能为空！");
      map.put("result", 2);
      map.put("success", b);
    } else if (uid == null || uid.equals("")) {
      map.put("error_msg", "用户Id不能为空！");
      map.put("result", 2);
      map.put("success", b);
    } else if (sysLoginService.getfindCountByIdSysLogin(uid) == 0) {
      map.put("error_msg", "用户不存在！");
      map.put("result", 2);
      map.put("success", b);
    } else {
      cartService.deleteCarts(cids);
      map.put("result", 0);
      map.put("success", true);
      map.put("error_msg", "删除成功！");
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
   * 选中提交购物车
   * 
   * @throws SQLException
   * */
  public String orderProduct() throws SQLException {
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
      long shopId = 0;
      // List<CartPojo> cartList = cartService.findCartByShopUserId(sysLogin.getId());
      CartPojo cPojo = new CartPojo();
      cPojo.setUserId(sysLogin.getId());
      List<CartPojo> cartList = cartService.findCartByUserIdAndShopId(cPojo);
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
        if (tip) {
          msg = "存在下架产品，请您重新选择！！！";
        } else {
          // CartPojo cPojo = new CartPojo();
          List<Object> clist = null;
          List<CartPojo> shopCartList = null;
          // 活动产品
          ActivityGoodsPojo activityGoods = null;
          ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
          ProductSkuLinkPojo productSkuLinkPojo = null;
          ActivityTimePojo activityTimePojo = null;
          for (CartPojo cartPojo : cartListNew) {
            if (shopId != cartPojo.getShopId()) {
              clist = new ArrayList<Object>();

              cPojo.setUserId(uid);
              cPojo.setShopId(cartPojo.getShopId());
              shopCartList = cartService.findCartByUserIdAndShopId(cPojo);

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
              String shopLogo = null;
              Double sum = 0.0;
              Double weight = 0.0;
              int count = 0;
              ProductSkuLinkPojo productSkuLinkCheck = null;

              for (CartPojo sCartPojo : cartListShow) {
                // 活动价格判断
                Integer skuid = sCartPojo.getSkuLinkId();
                Long activityId = sCartPojo.getActivityId();
                // 更新购物车活动价格
                if (activityId == null) {
                  activityId = 0L;
                }
                if (activityId > 0) {
                  activityGoods = isProductActivity(sCartPojo.getProductId(), activityId);
                }
                // 有sku
                if (skuid != null && skuid > 0) {
                  productSkuLinkCheck = getProducSku(sCartPojo.getProductId(), skuid);
                  if (productSkuLinkCheck != null) {
                    // 购买数量大于库存
                    if (sCartPojo.getNum().intValue() > productSkuLinkCheck.getStock()) {
                      acFlag = 1;
                      // msg += sCartPojo.getProductName() + ",";
                      continue;
                    } else if (activityId > 0 && activityGoods == null) {
                      // 活动已过期
                      acFlag = 2;
                      // msg += sCartPojo.getProductName() + ",";
                      continue;
                    }
                  } else {
                    acFlag = 1;
                    // msg += sCartPojo.getProductName() + ",";
                    continue;
                  }
                } else if (activityId > 0) {
                  // 有活动，活动无库存或设置不全
                  if (!(activityGoods != null && activityGoods.getActivePrice() != null
                      && activityGoods.getActivePrice().doubleValue() != 0
                      && activityGoods.getActivityStock() != null && sCartPojo.getNum().intValue() <= activityGoods
                      .getActivityStock().intValue())) {
                    acFlag = 2;
                    // msg += sCartPojo.getProductName() + ",";
                    // 删除活动已过期商品
                    // cartService.delCart(sCartPojo.getId());
                    continue;
                  }
                }

                name = sCartPojo.getShopName();
                sId = sCartPojo.getShopId();
                shopLogo = shopService.findShopById(sCartPojo.getShopId()).getImages();
                Map<String, Object> mapCart = new HashMap<String, Object>();
                mapCart.put("cid", sCartPojo.getId());
                mapCart.put("productId", sCartPojo.getProductId());
                mapCart.put("sellingPrice", df.format(sCartPojo.getStockPriceOld()));
                mapCart.put("price", df.format(sCartPojo.getStockPrice()));
                mapCart.put("discount",
                    calcDiscount(sCartPojo.getStockPrice(), sCartPojo.getStockPriceOld()));
                mapCart.put("productName", sCartPojo.getProductName());
                mapCart.put("productNumber", sCartPojo.getNum());
                mapCart.put("productImage",
                    ConstParam.URL + "/upfiles/product/small/" + sCartPojo.getProductImage());
                mapCart.put("allPrice", df.format(sCartPojo.getNum() * sCartPojo.getStockPrice()));
                // 商品sku
                // Integer skuid = sCartPojo.getSkuLinkId();
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
                if (sCartPojo.getActivityId() == null || sCartPojo.getActivityId() == 0) {
                  // 普通商品
                  mapCart.put("type", 0);
                } else {
                  activityTimePojo =
                      activityTimeService.findActivityTimeById(sCartPojo.getActivityId());
                  if (activityTimePojo != null && activityTimePojo.getType() == 0) {
                    // 秒杀商品
                    mapCart.put("type", 1);
                  } else if (activityTimePojo != null && activityTimePojo.getType() == 1) {
                    // 专题商品
                    mapCart.put("type", 2);
                  } else {
                    // 活动商品
                    mapCart.put("type", 3);
                  }

                }
                clist.add(mapCart);
                sum += sCartPojo.getNum() * sCartPojo.getStockPrice();
                count += sCartPojo.getNum();
                weight += Double.valueOf(sCartPojo.getWeight()) * sCartPojo.getNum();
              }

              cartListShow.clear();
              mapShop.put("shopLogo", ConstParam.URL + "/upfiles/shop/" + shopLogo);
              mapShop.put("allCount", count);
              mapShop.put("sumPrice", df.format(sum));
              mapShop.put("shopId", sId);
              mapShop.put("shopName", name);
              mapShop.put("espressPrice", df.format(calcFare(weight)));

              mapShop.put("products", clist);

              list.add(mapShop);

            }
            shopId = cartPojo.getShopId();
          }
        }
      }

      b = true;
      if (acFlag == 1 || acFlag == 2) {
        /*
         * if (msg.length() >= 21) { msg = msg.substring(0,20) + "..."; } else { // 截掉最后一个逗号 msg =
         * msg.substring(0, msg.length() - 1); }
         */
        msg = "对不起，您购买的商品数量大于剩余库存！";
      }
      /*
       * if (acFlag == 2) { msg = "存在活动过期的商品！" + msg; } if (acFlag == 1 ||acFlag == 2) { msg +=
       * "请您重新选择！！！"; // 清除选择的购买商品 list.clear(); }
       */
      if (list.size() == 0) {
        map.put("result", list);
        map.put("error_msg", msg);
      } else {
        map.put("result", list);
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
            notifyUrl, spBillCreateIP, "APP", "APP");
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
      List<CartPojo> cartList = cartService.getCartsByIdsSummit(cartIds);// 获取商品信息
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
        int flag = cartService.updateStatusCartWeb(cartIds);
        if (flag == 0) {
          error_msg = "订单商品已提交，请不要重复提交哦！";
          success = false;
        } else {

          DecimalFormat df = new DecimalFormat("#.##");
          // 订单描述--支付宝
          String body = "";
          // 订单号
          String subject = "";
          // 商户订单号--支付宝
          String out_trade_no = UtilDate.getOrderNum() + UtilDate.getThree();
          long shopId = 0L;
          // 订单商品数
          int cartCnt = 0;
          // 订单数
          int allOrderCnt = 0;
          for (CartPojo cart : cartList) {

            if (shopId != cart.getShopId()) {
              cartCnt = 0;
              Map<String, Object> map1 = new HashMap<String, Object>();
              map1.put("userId", user.getId());
              map1.put("shopId", cart.getShopId());
              List<CartPojo> cartListByShopId = cartService.getCartAllByShopId(map1);
              // 邮费
              Double farestatus = 0.0;// farestatus=1初始值
              // 重量
              Double pweight = 0.0;
              Double orderWeight = 0.0;
              // 订单总价
              allCartPrice = 0.0;
              // 活动产品判断
              ActivityGoodsPojo activityGoods = null;
              boolean stockFlag = false;
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
                  activityGoods = isProductActivity(cartByShopId.getProductId(), activityId);
                  if (activityGoods == null) {
                    continue;
                  }
                }
                // sku库存判断
                if (skuid != null && skuid > 0) {
                  // 同步检查活动库存并更新
                  stockFlag = updateActivitySkuStock(cartByShopId);
                  if (!stockFlag) {
                    continue;
                  }
                } else if (activityId > 0) {
                  // 同步检查活动库存并更新
                  stockFlag = updateActivityStock(cartByShopId);
                  if (!stockFlag) {
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
                // 包邮
                /*
                 * if (cartByShopId.getPostageType() == 1) { farestatus = 0.0; }
                 */
              }
              // 订单无商品
              if (cartCnt == 0) {
                shopId = cart.getShopId();// 再次初始化ShopId的值
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
                  deliverFee = calcFare(orderWeight);
                  // 订单价格
                  allCartPrice0 += deliverFee;
                }
              }
              // 生成一张新的订单的订单号
              String orderNo = new Date().getTime() + RandomUtils.runVerifyCode(6);

              // 生成订单
              OrderPojo order = new OrderPojo();
              order.setUserId(user.getId());
              order.setSuserId(cart.getShopUserid());// 店铺id作为订单的suerid
              order.setAllNum("" + cart.getNum());
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
              orderService.insertOrder(order);
              subject += order.getOrderNo() + "\n";
              // 生成订单结束
              // 生成订单详情
              OrderPojo insert_order = orderService.findOrderByOrderNo(orderNo);
              ActivityTimePojo activityTimePojo = null;
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
                orderDetail.setChannel(1);
                orderDetail.setStatus(1);
                orderDetail.setSkuLinkId(cartDetail.getSkuLinkId());
                orderDetail.setCreateBy(user.getId());
                orderDetail.setCreateDate(new Date());
                // 商品活动类型
                if (cartDetail.getActivityId() == 0) {
                  // 普通商品
                  orderDetail.setType(0);
                } else {
                  activityTimePojo =
                      activityTimeService.findActivityTimeById(cartDetail.getActivityId());
                  if (activityTimePojo != null && activityTimePojo.getType() == 0) {
                    // 秒杀商品
                    orderDetail.setType(1);
                  } else if (activityTimePojo != null && activityTimePojo.getType() == 1) {
                    // 专题商品
                    orderDetail.setType(2);
                  } else {
                    // 活动商品
                    orderDetail.setType(3);
                  }

                }
                orderDetailService.insertOrderDetail(orderDetail);
                cartService.deleteCart(cartDetail.getId());
              }
              allOrderCnt++;
            }
            shopId = cart.getShopId();// 再次初始化ShopId的值
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
          for (OrderPojo outOrder : outOrderList) {
            allOrderPrice = allOrderPrice + outOrder.getFactPrice();
            if (m > 0.0) {
              // 添加订单用券记录
              couponOrder = new CouponOrderPojo();
              if (maxOrderNo.equals(outOrder.getOrderNo())) {
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
          }

          // 支付金额减去代金券金额
          allOrderPrice = allOrderPrice - m;
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
                buildAlipayReq(alipayOrderInfoPojo.getOutTradeNo(), subject,
                    alipayOrderInfoPojo.getTotalFee(), body, show_url);

          } else if (2 == payMethod) {
            // 生成未付款微信支付信息
            WxpayOrderInfoPojo wxpay = new WxpayOrderInfoPojo();
            wxpay.setOutTradeNo(out_trade_no);
            // 单位分
            wxpay.setTotalFee(doubleToFee(allOrderPrice));
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
                buildWxpayReq(wxpay.getOutTradeNo(), null, body, subject,
                    Integer.valueOf(wxpay.getTotalFee()));
            wxpayMap.put("out_trade_no", out_trade_no);
          }
          success = true;
          // map.put("result", 1);
          result.put("aplipay", alipayMap);
          result.put("wxpay", wxpayMap);
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
   * 
   * 供应商订单详情
   * */
  public String shopOrder() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = null;
    Map<String, Object> map2 = null;
    Map<String, Object> map3 = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    List list3 = new ArrayList();
    String error_msg = "";
    if (uid == null || uid.equals("")) {
      map.put("error_msg", "供应商Id不能为空！");
      map.put("success", false);
    } else {
      try {
        map3.put("suerID", uid);
        map3.put("orderStatus", orderStatus);
        if (page == null) {
          page = new Pager();
        }
        page.setPageSize(10);
        map3.put("pageSize", page.getPageSize());
        map3.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
        List<OrderPojo> list1 = orderService.supplyOrderListWeb(map3);
        if (list1 != null && list1.size() != 0) {
          for (OrderPojo p : list1) {
            List list = new ArrayList();
            map1 = new HashMap<String, Object>();
            map1.put("orderId", p.getId());
            map1.put("orderNumber", p.getOrderNo());
            map1.put("orderPrice", df.format(p.getAllPrice()));
            map1.put("espressPrice",
                p.getEspressPrice() == null ? "" : df.format(p.getEspressPrice()));
            map1.put("num", p.getAllNum());
            map1.put("orderStatus", p.getOrderStatus());
            map1.put("shopId", p.getShopId());
            map1.put("shopName", p.getShopName());
            // 3-货到付款
            if (p.getPayMethod() != null && 3 == p.getPayMethod()) {
              map1.put("consigneeType", "货到付款");
            } else {
              map1.put("consigneeType", p.getConsigneeTypeName());
            }
            List<OrderDetailPojo> orderlist =
                orderDetailService.getfindByUserIdOrderDetail(p.getId());
            for (OrderDetailPojo orderDetailPojo : orderlist) {
              map2 = new HashMap<String, Object>();
              map2.put("productId", orderDetailPojo.getProductId());
              map2.put("productName", orderDetailPojo.getProductName());
              map2.put("productNumber", orderDetailPojo.getNum());
              map2.put("productImage", ConstParam.URL + "/upfiles/product/small" + File.separator
                  + orderDetailPojo.getProductImages());
              map2.put("Price", df.format(orderDetailPojo.getStockPrice()));
              list.add(map2);
            }
            map1.put("carts", list);
            list3.add(map1);
          }
        } else {
          error_msg = "没有数据";
        }
        map.put("success", true);
      } catch (Exception e) {
        map.put("success", false);
      }
    }
    map.put("error_msg", error_msg);
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
   * 批发商订单
   * */
  public String agentOrder() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = null;
    Map<String, Object> map2 = null;
    Map<String, Object> map3 = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    List list3 = new ArrayList();
    String error_msg = "";
    if (uid == null || uid.equals("")) {
      map.put("error_msg", "批发商Id不能为空！");
      map.put("success", false);
    } else {
      try {
        map3.put("suerID", uid);
        map3.put("orderStatus", orderStatus);

        if (pageNo == null || pageNo.equals("")) {
          page = new Pager();
          page.setPageNo(1);
        } else {
          page = new Pager();
          page.setPageNo(pageNo);
        }

        page.setPageSize(10);
        map3.put("pageSize", page.getPageSize());
        map3.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
        List<OrderPojo> list1 = orderService.agentOrderListWeb(map3);
        UserOrderRefundPojo userOrderRefundPojo = new UserOrderRefundPojo();
        List<UserOrderRefundPojo> userOrderRefundlist = null;
        ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
        ProductSkuLinkPojo productSkuLinkPojo = null;
        if (list1 != null && list1.size() != 0) {
          for (OrderPojo p : list1) {
            ShopPojo shopdetail = shopService.findShopById(p.getShopId());
            List list = new ArrayList();
            map1 = new HashMap<String, Object>();
            map1.put("orderId", p.getId());
            map1.put("orderNumber", p.getOrderNo());
            map1.put("orderPrice", df.format(p.getAllPrice()));
            map1.put("espressPrice",
                p.getEspressPrice() == null ? "" : df.format(p.getEspressPrice()));
            // 判断订单物流是否在45天前
            int isExpired = orderShipService.isOrderShipExpiredByOrderId(p.getId());
            map1.put("isExpired", isExpired);

            map1.put("orderStatus", p.getOrderStatus());
            map1.put("shopId", p.getShopId());
            map1.put("shopName", p.getShopName());
            map1.put("shopLogo",
                ConstParam.URL + "/upfiles/shop" + File.separator + shopdetail.getImages());
            // 3-货到付款
            if (p.getPayMethod() != null && 3 == p.getPayMethod()) {
              map1.put("consigneeType", "货到付款");
            } else {
              map1.put("consigneeType", p.getConsigneeTypeName());
            }
            List<OrderDetailPojo> orderlist =
                orderDetailService.getfindByUserIdOrderDetail(p.getId());
            userOrderRefundPojo.setOrderId(p.getId());
            userOrderRefundlist =
                userOrderRefundService.findUserOrderRefundByorderId(userOrderRefundPojo);
            if (orderlist.size() == userOrderRefundlist.size()) {
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
              if (orderStatus != null) {
                if (num2 == size || 4 == orderStatus && num3 == size) {
                  continue;
                }
              }
            } else {
              map1.put("refundId", 0);
            }

            int count = 0;
            for (OrderDetailPojo orderDetailPojo : orderlist) {
              count += orderDetailPojo.getNum();
              map2 = new HashMap<String, Object>();
              map2.put("productId", orderDetailPojo.getProductId());
              map2.put("productName", orderDetailPojo.getProductName());
              map2.put("productNumber", orderDetailPojo.getNum());
              map2.put("productImage", ConstParam.URL + "/upfiles/product/small" + File.separator
                  + orderDetailPojo.getProductImages());
              map2.put("Price", df.format(orderDetailPojo.getStockPrice()));
              // 商品sku显示
              Integer skuid = orderDetailPojo.getSkuLinkId();
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
              // 商品活动类型 0-普通商品 1-秒杀商品 2-专题商品 3-活动商品
              map2.put("type", orderDetailPojo.getType() == null ? "" : orderDetailPojo.getType());
              list.add(map2);
            }
            map1.put("num", count);
            map1.put("carts", list);
            list3.add(map1);
          }
        } else {
          error_msg = "没有数据";
        }
        map.put("success", true);
      } catch (Exception e) {
        map.put("success", false);
      }
    }
    map.put("error_msg", error_msg);
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
   * 订单确认收货
   * 
   * */
  public String editOrderStatus() throws SQLException {
    String msg = "";
    boolean b = false;
    int result = 0;
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid == null || uid.equals("")) {
      msg = "用户Id不能为空";
    } else if (oid == null || oid.equals("")) {
      msg = "订单Id不能为空";
    } else {
      long s = uid; // 用户id
      long d = oid;// 订单id
      int status = 0;// 订单更新状态
      OrderPojo order = orderService.getfindByIdOrderWeb(d);
      if (order != null) {
        orderPojo = new OrderPojo();
        orderPojo.setUserId(s);
        orderPojo.setId(d);
        orderPojo.setOrderStatus(4);
        orderPojo.setOldOrderStatus(3);
        try {
          status = orderService.updateOrderStatus2(orderPojo);
        } catch (Exception e) {
          status = 0;
          e.printStackTrace();
        }
        if (status > 0) {
          msg = "成功修改订单状态！";
          result = 1;

          // 收货确认，首单满80元赠送80元优惠券
          Date today = new Date();
          if (status > 0 && StringUtil.stringDate("2015-12-07 00:00:00").compareTo(today) > 0) {
            // int firstFlag = orderService.isUserFirstOrder(d);
            if (order.getFirstFlag() == 1) {
              // 用户首单礼包

              // 判断订单金额
              String outTradeNo = order.getOutTradeNo();
              List<OrderPojo> orders = orderService.getOrderByoutTradeNo(outTradeNo);
              double allPrice = 0.00;
              boolean confirmFlag = true;
              double m = 0.00;
              try {
                if (orders != null && orders.size() > 0) {
                  Map<String, Object> param = new HashMap<String, Object>();
                  List<CouponOrderPojo> couponOrders = null;
                  CouponOrderPojo couponOrder = null;
                  UserCouponPojo userCouponPojo = null;
                  for (OrderPojo orderPojo : orders) {
                    if (orderPojo.getOrderStatus() < 4) {
                      confirmFlag = false;
                      break;
                    }
                    allPrice += orderPojo.getFactPrice();

                    // 判断优惠券使用
                    param.clear();
                    param.put("orderId", orderPojo.getId());
                    param.put("status", 1);
                    couponOrders = couponService.getcouponOrderList(param);
                    if (couponOrders != null && couponOrders.size() > 0) {
                      couponOrder = couponOrders.get(0);
                      param.clear();
                      param.put("couponNo", couponOrder.getCouponNo());
                      userCouponPojo = couponService.getUserCouponByNo(param);
                      if (userCouponPojo != null) {
                        org.json.JSONObject json =
                            new org.json.JSONObject(userCouponPojo.getContent());
                        if (userCouponPojo.getType() == 1 && json.get("om") != null) {
                          double orderPay = json.getDouble("om");
                          if (json.get("m") != null && orderPay <= order.getFactPrice()) {
                            m += json.getDouble("m");
                          }
                        } else if (userCouponPojo.getType() == 2 && json.get("m") != null) {
                          m += json.getDouble("m");
                        }
                      }
                    }
                  }
                }
                allPrice = allPrice - m;
                // 满80元赠送
                if (confirmFlag && allPrice >= 80) {
                  Map<String, Object> param = new HashMap<String, Object>();
                  param.put("om", "68");
                  param.put("m", "10");
                  JSONObject json = JSONObject.fromObject(param);
                  Date current = new Date();
                  // 满25减10
                  giveCoupon(uid, 2, 1, json.toString(), current, 30);
                  // 满48减20
                  param.put("om", "128");
                  param.put("m", "20");
                  json = JSONObject.fromObject(param);
                  giveCoupon(uid, 2, 1, json.toString(), current, 30);
                  // 满120减50
                  param.put("om", "268");
                  param.put("m", "50");
                  json = JSONObject.fromObject(param);
                  giveCoupon(uid, 2, 1, json.toString(), current, 30);
                  msg += "首单满80元送80元优惠券！";
                }
              } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("赠送优惠券失败.");
              }
            }
          }
          b = true;
        } else {
          msg = "修改订单状态失败！";
        }
      } else {
        msg = "未找到该订单号！";
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
   * 
   * 订单详情
   * 
   * @throws Exception
   * */
  public String orderdetail() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = null;
    DecimalFormat df = new DecimalFormat("#.##");
    List list = new ArrayList();
    if (oid == null || oid.equals("")) {
      map.put("error_msg", "订单ID不能为空！");
      map.put("success", false);
    } else {
      long d = oid;
      OrderPojo orderPojo = orderService.findOrderById(d);
      if (orderPojo != null) {
        map2 = new HashMap<String, Object>();
        map2.put("orderId", orderPojo.getId());
        // 判断订单物流是否在45天前
        int isExpired = orderShipService.isOrderShipExpiredByOrderId(orderPojo.getId());
        map2.put("isExpired", isExpired);
        map2.put("orderNumber", orderPojo.getOrderNo());
        if (orderPojo.getAllPrice() == null) {
          orderPojo.setAllPrice(0d);
        }
        if (orderPojo.getEspressPrice() == null) {
          orderPojo.setEspressPrice(0d);
        }
        if (orderPojo.getUsedPrice() != null) {
          map2.put(
              "orderPrice",
              df.format(orderPojo.getAllPrice() + orderPojo.getEspressPrice()
                  - orderPojo.getUsedPrice()));
        } else {
          map2.put("orderPrice", df.format(orderPojo.getAllPrice() + orderPojo.getEspressPrice()));
        }

        map2.put("espressPrice", df.format(orderPojo.getEspressPrice()));
        // 3-货到付款
        if (orderPojo.getPayMethod() != null && 3 == orderPojo.getPayMethod()) {
          map2.put("deliverType", "货到付款");
        } else {
          map2.put("deliverType", orderPojo.getConsigneeTypeName());
        }

        map2.put("orderStatus", orderPojo.getOrderStatus());
        map2.put("shopId", orderPojo.getShopId());
        ShopPojo shop = shopService.findShopById(orderPojo.getShopId());
        map2.put("shopLogo",
            ConstParam.URL + "/upfiles/shop/" + (shop == null ? "" : shop.getImages()));
        map2.put("name", orderPojo.getConsignee());
        map2.put("shopName", orderPojo.getShopName());
        map2.put("tel", orderPojo.getConsigneePhone());
        map2.put("address", orderPojo.getConsigneeAddress());
        map2.put("addtime", orderPojo.getCreateDateString());
        if (orderPojo.getBuyerMessage() == null || "".equals(orderPojo.getBuyerMessage())) {
          map2.put("remark", "");
        } else {
          map2.put("remark", orderPojo.getBuyerMessage());
        }

        map2.put("comtime", "");
        // 判断是否有退货退款申请
        List<OrderDetailPojo> orderDetaillist = orderDetailService.getfindByUserIdOrderDetail(d);
        UserOrderRefundPojo userOrderRefundPojo = new UserOrderRefundPojo();
        userOrderRefundPojo.setOrderId(d);
        List<UserOrderRefundPojo> userOrderRefundlist =
            userOrderRefundService.findUserOrderRefundByorderId(userOrderRefundPojo);
        if (orderDetaillist != null && userOrderRefundlist != null
            && orderDetaillist.size() == userOrderRefundlist.size()) {
          // map2.put("refundId", 1);
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
            map2.put("refundId", 2);
          } else if (num2 == size) {
            // 全部成功
            map2.put("refundId", 3);
          } else if (num3 == size) {
            // 全部失败
            map2.put("refundId", 4);
          } else {
            // 审核中
            map2.put("refundId", 1);
          }
        } else {
          map2.put("refundId", 0);
        }
        // 订单用券详情
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("orderId", d);
        param.put("status", 1);
        List<CouponOrderPojo> couponOrders = couponService.getcouponOrderList(param);
        if (couponOrders != null && couponOrders.size() > 0) {
          CouponOrderPojo couponOrderPojo = couponOrders.get(0);
          map2.put("couponNo", couponOrderPojo.getCouponNo());
          map2.put("couponName", couponOrderPojo.getCouponName());
          map2.put("usedPrice",
              couponOrderPojo.getUsedPrice() == null || couponOrderPojo.getUsedPrice() == 0L ? ""
                  : df.format(couponOrderPojo.getUsedPrice()));
        } else {
          map2.put("couponNo", "");
          map2.put("couponName", "");
          map2.put("usedPrice", "");
        }

        OrderShipPojo shipPojo = orderShipService.findByIdOrderShip(d);
        if (shipPojo != null) {
          map2.put("fahuotime", shipPojo.getUpdateDate());
        }
        List<OrderDetailPojo> orderDetailList =
            orderDetailService.getOrderDetail(orderPojo.getId());
        List<Object> list2 = new ArrayList<Object>();
        if (orderDetailList != null) {
          Map<String, Object> map3 = null;
          ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
          ProductSkuLinkPojo productSkuLinkPojo = null;
          for (OrderDetailPojo orderDetailPojo2 : orderDetailList) {
            map3 = new HashMap<String, Object>();
            map3.put("productId", orderDetailPojo2.getProductId());
            map3.put("productName", orderDetailPojo2.getProductName());
            map3.put("productNumber", orderDetailPojo2.getNum());
            map3.put("orderDetailId", orderDetailPojo2.getId().toString());
            map3.put("reStatus", orderDetailPojo2.getReStatus().toString());
            map3.put("productImage", ConstParam.URL + "/upfiles/product/small" + File.separator
                + orderDetailPojo2.getProductImages());
            map3.put("price", df.format(orderDetailPojo2.getStockPrice()));
            map3.put("productStatus", orderDetailPojo2.getProductStatus() == null ? 0
                : orderDetailPojo2.getProductStatus());
            // 商品sku显示
            Integer skuid = orderDetailPojo2.getSkuLinkId();
            map3.put("skuLinkId", skuid == null ? "" : skuid);
            if (skuid != null) {
              productSkuLink.setId(Long.valueOf(skuid));
              productSkuLinkPojo = productSkuLinkService.findProductSkuLink(productSkuLink);
              if (productSkuLinkPojo != null) {
                map3.put("skuValue", "颜色:" + productSkuLinkPojo.getColorValue() + ";尺码:"
                    + productSkuLinkPojo.getFormatValue());
              } else {
                map3.put("skuValue", "");
              }
            } else {
              map3.put("skuValue", "");
            }
            // 商品活动类型 0-普通商品 1-秒杀商品 2-专题商品 3-活动商品
            map3.put("type", orderDetailPojo2.getType() == null ? "" : orderDetailPojo2.getType());
            list2.add(map3);
          }
        }
        map2.put("product", list2);
        list.add(map2);
        map.put("result", map2);
      }
      map.put("error_msg", "");
      map.put("success", true);
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
   * 订单详情
   * */
  public String orderdetailForAgency() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = null;
    DecimalFormat df = new DecimalFormat("#.##");
    List list = new ArrayList();
    if (oid == null || oid.equals("")) {
      map.put("error_msg", "订单ID不能为空！");
      map.put("success", false);
    } else if (agencyId == null || "".equals(agencyId)) {
      map.put("error_msg", "批发商ID不能为空！");
      map.put("success", false);
    } else {
      long d = oid;
      OrderPojo orderPojo = orderService.findOrderById(d);
      if (orderPojo != null) {
        map2 = new HashMap<String, Object>();
        map2.put("orderId", orderPojo.getId());
        // 判断订单物流是否在45天前
        int isExpired = orderShipService.isOrderShipExpiredByOrderId(orderPojo.getId());
        map2.put("isExpired", isExpired);
        map2.put("orderNumber", orderPojo.getOrderNo());
        if (orderPojo.getAllPrice() == null) {
          orderPojo.setAllPrice(0d);
        }
        if (orderPojo.getEspressPrice() == null) {
          orderPojo.setEspressPrice(0d);
        }
        map2.put("orderPrice", df.format(orderPojo.getAllPrice() + orderPojo.getEspressPrice()));
        map2.put("espressPrice", df.format(orderPojo.getEspressPrice()));
        map2.put("deliverType", orderPojo.getConsigneeTypeName());
        map2.put("orderStatus", orderPojo.getOrderStatus());
        map2.put("shopId", orderPojo.getShopId());
        ShopPojo shop = shopService.findShopById(orderPojo.getShopId());
        map2.put("shopLogo",
            ConstParam.URL + "/upfiles/shop/" + (shop == null ? "" : shop.getImages()));
        map2.put("name", orderPojo.getConsignee());
        map2.put("shopName", orderPojo.getShopName());
        map2.put("tel", orderPojo.getConsigneePhone());
        map2.put("address", orderPojo.getConsigneeAddress());
        map2.put("addtime", orderPojo.getCreateDateString());
        if (orderPojo.getBuyerMessage() == null || "".equals(orderPojo.getBuyerMessage())) {
          map2.put("remark", "");
        } else {
          map2.put("remark", orderPojo.getBuyerMessage());
        }

        map2.put("comtime", "");

        // 根据产品信息中的用户ID查询到推送状态
        AgencyPushPojo agencyPush = new AgencyPushPojo();
        agencyPush.setOrderId(d);
        agencyPush.setAgencyId(agencyId);
        AgencyPushPojo agencyPushdetail = agencyPushService.findAgencyPush(agencyPush);
        // 订单推送状态
        if (agencyPushdetail == null) {
          map2.put("pushStatus", "");
        } else {
          map2.put("pushStatus", agencyPushdetail.getStatus());
        }
        // 判断是否有退货退款申请
        List<OrderDetailPojo> orderDetaillist = orderDetailService.getfindByUserIdOrderDetail(d);
        UserOrderRefundPojo userOrderRefundPojo = new UserOrderRefundPojo();
        userOrderRefundPojo.setOrderId(d);
        List<UserOrderRefundPojo> userOrderRefundlist =
            userOrderRefundService.findUserOrderRefundByorderId(userOrderRefundPojo);
        if (orderDetaillist != null && userOrderRefundlist != null
            && orderDetaillist.size() == userOrderRefundlist.size()) {
          map2.put("refundId", 1);
        } else {
          map2.put("refundId", 0);
        }
        OrderShipPojo shipPojo = orderShipService.findByIdOrderShip(d);
        if (shipPojo != null) {
          map2.put("fahuotime", shipPojo.getUpdateDate());
        }
        List<OrderDetailPojo> orderDetailList =
            orderDetailService.getOrderDetail(orderPojo.getId());
        List<Object> list2 = new ArrayList<Object>();
        if (orderDetailList != null) {
          Map<String, Object> map3 = null;
          ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
          ProductSkuLinkPojo productSkuLinkPojo = null;
          for (OrderDetailPojo orderDetailPojo2 : orderDetailList) {
            map3 = new HashMap<String, Object>();
            map3.put("productId", orderDetailPojo2.getProductId());
            map3.put("productName", orderDetailPojo2.getProductName());
            map3.put("productNumber", orderDetailPojo2.getNum());
            map3.put("orderDetailId", orderDetailPojo2.getId().toString());
            map3.put("reStatus", orderDetailPojo2.getReStatus().toString());
            map3.put("productImage", ConstParam.URL + "/upfiles/product/small" + File.separator
                + orderDetailPojo2.getProductImages());
            map3.put("price", df.format(orderDetailPojo2.getStockPrice()));

            // 商品sku显示
            Integer skuid = orderDetailPojo2.getSkuLinkId();
            map3.put("skuLinkId", skuid == null ? "" : skuid);
            if (skuid != null) {
              productSkuLink.setId(Long.valueOf(skuid));
              productSkuLinkPojo = productSkuLinkService.findProductSkuLink(productSkuLink);
              if (productSkuLinkPojo != null) {
                map3.put("skuValue", "颜色:" + productSkuLinkPojo.getColorValue() + ";尺码:"
                    + productSkuLinkPojo.getFormatValue());
              } else {
                map3.put("skuValue", "");
              }
            } else {
              map3.put("skuValue", "");
            }
            // 商品活动类型 0-普通商品 1-秒杀商品 2-专题商品 3-活动商品
            map3.put("type", orderDetailPojo2.getType() == null ? "" : orderDetailPojo2.getType());
            list2.add(map3);
          }
        }
        map2.put("product", list2);
        list.add(map2);
        map.put("result", map2);
      }
      map.put("error_msg", "");
      map.put("success", true);
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
        if (list1 != null && list1.size() > 0) {
          for (OrderPojo p : list1) {
            List list = new ArrayList();
            int num = 0;
            map1 = new HashMap<String, Object>();
            List<OrderDetailPojo> orderDetaillist =
                orderDetailService.getfindByUserIdOrderDetail(p.getId());
            UserOrderRefundPojo userOrderRefundPojo = new UserOrderRefundPojo();
            for (int i = 0; i < orderDetaillist.size(); i++) {
              num += orderDetaillist.get(i).getNum();
            }
            map1.put("num", num);
            userOrderRefundPojo.setOrderId(p.getId());
            List<UserOrderRefundPojo> userOrderRefundlist =
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
            if (p.getUsedPrice() != null) {
              map1.put("orderPrice",
                  df.format(p.getAllPrice() + p.getEspressPrice() - p.getUsedPrice()));
            } else {
              map1.put("orderPrice", df.format(p.getAllPrice() + p.getEspressPrice()));
            }
            map1.put("espressPrice", df.format(p.getEspressPrice()));
            // 判断订单物流是否在45天前
            int isExpired = orderShipService.isOrderShipExpiredByOrderId(p.getId());
            map1.put("isExpired", isExpired);
            // map1.put("num", p.getAllNum());
            map1.put("orderStatus", p.getOrderStatus());
            map1.put("shopId", p.getShopId());
            map1.put("shopLogo",
                ConstParam.URL + "/upfiles/shop/"
                    + shopService.findShopById(p.getShopId()).getImages());
            map1.put("shopName", p.getShopName());
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
                // 商品活动类型 0-普通商品 1-秒杀商品 2-专题商品 3-活动商品
                map2.put("type",
                    orderDetailPojo2.getType() == null ? "" : orderDetailPojo2.getType());
                list.add(map2);
              }

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
   * 行业资讯
   * */
  public String newsList() throws SQLException {
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = null;
    if (pageNo == null || pageNo.equals("")) {
      page = new Pager();
      page.setPageNo(1);
    } else {
      page = new Pager();
      page.setPageNo(pageNo);
    }
    page.setPageSize(10);
    List list = new ArrayList();
    InfoPojo infoPojo = new InfoPojo();
    infoPojo.setType(1);
    List<InfoPojo> infolist = infoService.infoAllList(infoPojo, page, 1);
    if (infolist.size() == 0) {
      msg = "没有搜索到数据！";
    }
    for (InfoPojo info : infolist) {
      map1 = new HashMap<String, Object>();
      map1.put("id", info.getId());
      map1.put("title", info.getTitle());
      String[] strs = StringUtil.getImagesSrc(info.getContent());
      // List<Object> listImg = new ArrayList<Object>();
      // for (int i = 0; i < strs.length; i++) {
      // map2 = new HashMap<String, Object>();
      // map2.put("image", "http://b2c.taozhuma.com/" + strs[i]);
      // listImg.add(map2);
      // }
      if (strs.length > 0) {
        map1.put("image", ConstParam.URL + "/" + strs[0]);
      } else {
        map1.put("image", "");
      }
      String str = "";
      if (StringUtil.delHTMLTag(info.getContent()).length() < 50) {
        str = StringUtil.delHTMLTag(info.getContent());
      } else {
        str = StringUtil.delHTMLTag(info.getContent()).substring(0, 50);
      }
      map1.put("content", str);
      map1.put("addTime", info.getCreateDateStr());
      list.add(map1);
    }
    map.put("result", list);
    map.put("success", true);
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
   * 行业资讯详情
   * */
  public String newsDetail() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    String msg = "";
    boolean success = false;
    List<Object> list = new ArrayList<Object>();
    if (id == null || id.equals("")) {
      msg = "资讯Id不能为空！";
    } else {
      InfoPojo info = infoService.findInfoById(id);
      if (info != null) {
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("id", info.getId());
        map2.put("title", info.getTitle());
        // map2.put("content",
        // StringUtil.delHTMLTag(info.getContent()));
        // 字符转换成html格式
        String s = info.getContent();
        s = s.replaceAll("src=\"", "src=\"" + ConstParam.URL);
        // map2.put("content", StringEscapeUtils.escapeHtml(s));
        s = s.replaceAll("img", "img width='100%'");
        map2.put("content", s.toString());
        map2.put("author", info.getAuthor());
        map2.put("addTime", info.getCreateDateStr());
        Map<String, Object> map1 = null;
        String[] strs = StringUtil.getImagesSrc(info.getContent());
        List<Object> listImg = new ArrayList<Object>();
        for (int i = 0; i < strs.length; i++) {
          map1 = new HashMap<String, Object>();
          map1.put("image", ConstParam.URL + "/" + strs[i]);
          listImg.add(map1);
        }
        map2.put("image", listImg);
        list.add(map2);
        map.put("result", list);
        success = true;
      } else {
        msg = "没有找到该条资讯";
      }
    }
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
   * 店铺街
   * */
  public String shopList() throws SQLException {
    String msg = "";
    boolean success = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = null;
    List list = new ArrayList();
    ShopPojo shop = new ShopPojo();
    if (type == null || type.equals("")) {
      msg = "店铺类型不能为空！";
    } else if (type.equals("0")) {// 类型为0则查询全部店铺
      if (pageNo == null || pageNo.equals("")) {
        page = new Pager();
        page.setPageNo(1);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
      }
      page.setPageSize(10);
      shop.setStatus(1);
      shop.setName(searchKey);
      shoplist = shopService.shopAllList(shop, page);
      if (shoplist.size() == 0) {
        msg = "没有搜索到数据！";
      }
      for (ShopPojo shop1 : shoplist) {
        map1 = new HashMap<String, Object>();
        map1.put("id", shop1.getId());
        map1.put("name", shop1.getName());
        map1.put("image", ConstParam.URL + "/upfiles/shop" + File.separator + shop1.getImages());
        map1.put("mainCategory", shop1.getMainCategoryName());
        map1.put("address", shop1.getAddress());
        list.add(map1);
      }
      map.put("result", list);
      success = true;
    } else {
      if (pageNo == null || pageNo.equals("")) {
        page = new Pager();
        page.setPageNo(1);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
      }
      page.setPageSize(10);
      shop.setMainCategory(String.valueOf(type));
      shop.setName(searchKey);
      shoplist = shopService.shopAllService(shop, page);
      if (shoplist.size() == 0) {
        msg = "没有搜索到数据！";
      }
      for (ShopPojo shop1 : shoplist) {
        map1 = new HashMap<String, Object>();
        map1.put("id", shop1.getId());
        map1.put("name", shop1.getName());
        map1.put("image", ConstParam.URL + "/upfiles/shop" + File.separator + shop1.getImages());
        map1.put("mainCategory", shop1.getMainCategoryName());
        map1.put("address", shop1.getAddress());
        list.add(map1);
      }
      map.put("result", list);
      success = true;
    }
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
   * 店铺详情
   * */
  public String shopDetail() throws SQLException {
    String msg = "";
    boolean success = false;
    DecimalFormat df = new DecimalFormat("#.##");
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = null;
    ShopPojo shop = new ShopPojo();
    if (id == null || id.equals("")) {
      msg = "店铺Id不能为空！";
    } else if (shopService.findShopById(id) == null) {
      msg = "该店铺不存在！";
    } else {
      if (pageNo == null || pageNo.equals("")) {
        page = new Pager();
        page.setPageNo(1);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
      }
      page.setPageSize(10);
      if (source == null || source.equals("")) {
        source = 2;// 设置默认排序为价格降序
      }
      shop = shopService.findShopById(id);
      ProductPojo product = new ProductPojo();
      List<Object> list2 = new ArrayList<Object>();
      if (shop.getUserId() != null) {
        product.setUserId(shop.getUserId());
        product.setSorting(source);// 设置排序1销量降序、11销量升序、2价格降序、22价格升序、3点击率降序、33点击率升序
        List<ProductPojo> productlist = productService.productByUserIdSort(product, page);
        if (productlist.size() == 0) {
          msg = "没有搜索到商品！";
        }
        for (ProductPojo p : productlist) {
          map1 = new HashMap<String, Object>();
          map1.put("productId", p.getId());
          map1.put("name", p.getProductName());
          map1.put("image",
              ConstParam.URL + "/upfiles/product/small" + File.separator + p.getImage());
          // 活动价格判断
          /*
           * activityGoods = isProductActivity(p.getId()); if (activityGoods != null &&
           * activityGoods.getActivePrice() != null && activityGoods.getActivePrice().doubleValue()
           * != 0) { map1.put("price", activityGoods.getActivePrice()); } else { map1.put("price",
           * p.getDistributionPrice()); }
           */
          map1.put("price", df.format(p.getDistributionPrice()));
          map1.put("sellingPrice", df.format(p.getSellingPrice()));
          map1.put("sellNumber", p.getSellNumber() + p.getBaseNumber());
          list2.add(map1);
        }
      }
      map.put("result", list2);
      success = true;
    }
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
   * 每日新品
   * */
  public String activity() throws SQLException {
    String msg = "";
    boolean success = false;
    DecimalFormat df = new DecimalFormat("#.##");
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = null;
    List<ProductPojo> list = null;
    List list1 = new ArrayList();
    if (type == null || type.equals("")) {
      msg = "类型不能为空！";
    } else {
      if (pageNo == null || pageNo.equals("")) {
        page = new Pager();
        page.setPageNo(1);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
      }
      page.setPageSize(10);
      if (type.equals("1")) {// 每日新品（三天内上架的商品，根据创建时间查询）
        ProductPojo product = new ProductPojo();
        product.setCreateDate(new Date());
        list = productService.getProductByActivity(product, page);
        if (list.size() == 0) {
          msg = "没有搜索到商品！";
        }
        success = true;
      } else if (type.equals("2")) {// 热销产品
        ProductPojo product = new ProductPojo();
        product.setSellNumber(1);
        list = productService.getProductByActivity(product, page);
        if (list.size() == 0) {
          msg = "没有搜索到商品！";
        }
        success = true;
      } else if (type.equals("3")) {// 竹马推荐
        ProductPojo product = new ProductPojo();
        product.setIsIntroduce(1 + "");
        list = productService.getProductByActivity(product, page);
        if (list.size() == 0) {
          msg = "没有搜索到商品！";
        }
        success = true;
      } else if (type.equals("4")) {// 新品快订
        ProductPojo product = new ProductPojo();
        product.setIsNew("1");
        list = productService.getProductByActivity(product, page);
        if (list.size() == 0) {
          msg = "没有搜索到商品！";
        }
        success = true;
      } else if (type.equals("5")) {// 家有好货
        ProductPojo product = new ProductPojo();
        product.setHits("1");
        list = productService.getProductByActivity(product, page);
        if (list.size() == 0) {
          msg = "没有搜索到商品！";
        }
        success = true;
      } else if (type.equals("6")) {// 每日10件
        list = productService.tenOfEveryDay();
        System.out.println(list.get(0).getProductTypeIds());
        if (list.size() == 0) {
          msg = "没有搜索到商品！";
        }
        success = true;
      } else if (type.equals("7")) {// top排行
        ProductPojo product = new ProductPojo();
        product.setSellNumber(1);
        list = productService.getProductByActivity(product, page);
        if (list.size() == 0) {
          msg = "没有搜索到商品！";
        }
        success = true;
      }
      int count = 1;
      if (list != null) {
        // ActivityGoodsPojo activityGoods = null;
        Double factPrice = 0.0;
        for (ProductPojo p : list) {
          map1 = new HashMap<String, Object>();
          map1.put("id", p.getId());
          map1.put("image",
              ConstParam.URL + "/upfiles/product/small" + File.separator + p.getImage());
          map1.put("name", p.getProductName());
          /*
           * if(type.equals("6")){ map1.put("price", p.getSellingPrice()); }else{ map1.put("price",
           * p.getDistributionPrice()); }
           */
          // 活动价格判断
          factPrice = p.getDistributionPrice();
          /*
           * activityGoods = isProductActivity(p.getId()); if (activityGoods != null &&
           * activityGoods.getActivePrice() != null && activityGoods.getActivePrice().doubleValue()
           * != 0) { factPrice = activityGoods.getActivePrice(); }
           */
          map1.put("price", df.format(factPrice));
          map1.put("sellingPrice", df.format(p.getSellingPrice()));
          map1.put("discount", calcDiscount(factPrice, p.getSellingPrice()));

          String fatherString = "其它玩具";
          if (p.getProductTypeIds().equals("1")) {
            fatherString = "遥控/电动玩具";
          } else if (p.getProductTypeIds().equals("2")) {
            fatherString = "早教/音乐玩具";
          } else if (p.getProductTypeIds().equals("3")) {
            fatherString = "过家家玩具";
          } else if (p.getProductTypeIds().equals("4")) {
            fatherString = "童车玩具";
          } else if (p.getProductTypeIds().equals("5")) {
            fatherString = "益智玩具";
          }
          map1.put("subTypeId", fatherString);
          map1.put("sellNumber", p.getSellNumber() + p.getBaseNumber());
          if ("7".equals(type)) {
            // TOP排行返回序号
            map1.put("rowid", count + (page.getPageNo() - 1) * page.getPageSize());
            count++;
          }
          list1.add(map1);
        }
        success = true;
      }
    }
    map.put("result", list1);
    map.put("success", success);
    map.put("error_msg", msg);
    JSONObject json = JSONObject.fromObject(map);
    // ActionContext actionContext = ActionContext.getContext();
    // actionContext.put("result", json.toString());
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
   * 我收藏的产品列表
   * */
  public String favorites() throws SQLException {
    String msg = "";
    boolean success = false;
    Map<String, Object> map = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    Map<String, Object> map1 = null;
    List list1 = new ArrayList();
    if (uid == null || uid.equals("")) {
      msg = "用户Id不能为空！";
    } else if (favType == null || favType.equals("")) {
      msg = "查看收藏类型不能为空！";
    } else {
      long s = uid;
      if (pageNo == null || pageNo.equals("")) {
        page = new Pager();
        page.setPageNo(1);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
      }
      page.setPageSize(20);
      if (favType == 1) {// 产品
        userCollect = new UserCollectPojo();
        userCollect.setUserId(s);
        List<UserCollectPojo> list = userCollectService.UserCollectByUserId(userCollect, page);
        if (list.size() == 0) {
          msg = "没有数据了！";
        }
        if (list != null) {
          for (UserCollectPojo p : list) {
            map1 = new HashMap<String, Object>();
            map1.put("favId", p.getId());
            map1.put("favoriteId", p.getProductId());
            ProductPojo product = new ProductPojo();
            product.setId(p.getProductId());
            product = productService.findProduct(product);
            if (product != null) {
              map1.put("favName", product.getProductName());
              map1.put("favPrice", df.format(product.getDistributionPrice()));
              map1.put("favImage", ConstParam.URL + "/upfiles/product/small" + File.separator
                  + product.getImage());
              map1.put("favItem", product.getProductNum());
              map1.put("status", product.getStatus());
            } else {
              map1.put("favName", "");
              map1.put("favPrice", "");
              map1.put("favImage", "");
              map1.put("favItem", "");
            }
            list1.add(map1);
          }
        }
        success = true;
      } else if (favType == 2) {// 店铺
        UserShopCollectPojo userShopCollectPojo = new UserShopCollectPojo();
        userShopCollectPojo.setUserId(uid);
        List<UserShopCollectPojo> list =
            userShopCollectService.userShopCollectByPage(userShopCollectPojo, page);
        if (list.size() == 0) {
          msg = "没有数据了！";
        }
        if (list != null) {
          for (UserShopCollectPojo p : list) {
            map1 = new HashMap<String, Object>();
            map1.put("favId", p.getId());
            map1.put("favoriteId", p.getShopId());
            map1.put("favName", p.getShopName());
            map1.put("favPrice", "");
            map1.put("favImage",
                ConstParam.URL + "/upfiles/shop" + File.separator + p.getShopImages());
            map1.put("favItem", "");
            map1.put("status", p.getStatus());
            list1.add(map1);
          }
        }
        success = true;
      } else if (favType == 3) {// 分销产品库
        userConsumerCollectPojo = new UserConsumerCollectPojo();
        userConsumerCollectPojo.setUserId(s);
        List<UserConsumerCollectPojo> list =
            userConsumerCollectService.findCollect2(userConsumerCollectPojo, page);
        if (list.size() == 0) {
          msg = "没有数据了！";
        }
        if (list != null) {
          for (UserConsumerCollectPojo p : list) {
            map1 = new HashMap<String, Object>();
            map1.put("favId", p.getId());
            map1.put("favoriteId", p.getProductId());
            ProductPojo product = new ProductPojo();
            product.setId(p.getProductId());
            product = productService.findProduct(product);
            if (product != null) {
              map1.put("favName", product.getProductName());
              map1.put("favPrice", df.format(product.getDistributionPrice()));
              map1.put("favImage", ConstParam.URL + "/upfiles/product/small" + File.separator
                  + product.getImage());
              map1.put("favItem", product.getProductNum());
              map1.put("status", product.getStatus());
            } else {
              map1.put("favName", "");
              map1.put("favPrice", "");
              map1.put("favImage", "");
              map1.put("favItem", "");
            }
            list1.add(map1);
          }
        }
        success = true;
      }
    }
    map.put("success", success);
    map.put("error_msg", msg);
    map.put("result", list1);
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
    if (uid == null || uid.equals("")) {
      map.put("error_msg", "用户Id不能为空！");
    } else if (favSenId == null || favSenId.equals("")) {
      map.put("error_msg", "产品或者店铺Id不能为空哒，(* ￣3)(ε￣ *)");
    } else if (favType == null || favType.equals("")) {
      map.put("error_msg", "收藏类型不能为空！");
    } else {
      long s = uid;// 用户id
      long d = favSenId;// 收藏产品或者店铺id
      if (favType == 1) {// 产品
        UserCollectPojo userCollectPojo = new UserCollectPojo();
        userCollectPojo.setUserId(s);
        userCollectPojo.setProductId(d);

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
   * 判断收藏
   * */
  public String isFavorite() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid == null || uid.equals("")) {
      map.put("error_msg", "用户Id不能为空！");
    } else if (favSenId == null || favSenId.equals("")) {
      map.put("error_msg", "产品或者店铺Id不能为空！");
    } else if (favType == null || favType.equals("")) {
      map.put("error_msg", "收藏类型不能为空！");
    } else {
      long s = uid;// 用户id
      long d = favSenId;// 收藏id
      if (favType == 1) {// 产品
        UserCollectPojo userCollectPojo = new UserCollectPojo();
        userCollectPojo.setUserId(s);
        userCollectPojo.setProductId(d);
        userCollectPojo = userCollectService.findUserCollectByProductId(userCollectPojo);
        if (userCollectPojo != null) {
          map.put("result", 0);
          map.put("success", true);
          map.put("error_msg", "已经收藏过");
        } else {
          map.put("result", 1);
          map.put("success", true);
          map.put("error_msg", "还没有收藏");
        }
      } else if (favType == 2) {// 店铺
        UserShopCollectPojo userShopCollectPojo = new UserShopCollectPojo();
        userShopCollectPojo.setUserId(s);
        userShopCollectPojo.setShopId(d);
        userShopCollectPojo =
            userShopCollectService.findUserShopCollectByShopId(userShopCollectPojo);
        if (userShopCollectPojo != null) {
          map.put("result", 0);
          map.put("success", true);
          map.put("error_msg", "已经收藏过");
        } else {
          map.put("result", 1);
          map.put("success", true);
          map.put("error_msg", "还没有收藏");
        }
      } else if (favType == 3) {// 分销产品库
        UserConsumerCollectPojo userConsumerCollectPojo = new UserConsumerCollectPojo();
        userConsumerCollectPojo.setUserId(s);
        userConsumerCollectPojo.setProductId(d);
        userConsumerCollectPojo = userConsumerCollectService.findCollect(userConsumerCollectPojo);
        if (userConsumerCollectPojo != null) {
          map.put("result", 0);
          map.put("success", true);
          map.put("error_msg", "已经收藏过");
        } else {
          map.put("result", 1);
          map.put("success", true);
          map.put("error_msg", "还没有收藏");
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
   * 取消收藏
   * */
  public String delFavorite() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid == null || uid.equals("")) {
      map.put("error_msg", "用户Id不能为空！");
    } else if (cids == null || cids.equals("")) {
      map.put("error_msg", "产品或者店铺Id不能为空！");
    } else if (favType == null || favType.equals("")) {
      map.put("error_msg", "收藏类型不能为空！");
    } else {
      long s = uid;// 用户id
      // 循环遍历得到收藏ID
      String[] strings = cids[0].split(",");
      if (favType == 1) {// 产品
        for (int i = 0; i < strings.length; i++) {
          Map<String, Object> map1 = new HashMap<String, Object>();
          long d = Long.parseLong(strings[i]);// 收藏id
          map1.put("userId", s);
          map1.put("productId", d);
          try {
            userCollectService.deleteUserCollect(map1);
            map.put("result", 1);
            map.put("success", true);
            map.put("error_msg", "取消收藏成功");
          } catch (Exception e) {
            map.put("result", 0);
            map.put("success", true);
            map.put("error_msg", "取消收藏失败");
          }
        }
      } else if (favType == 2) {// 店铺
        for (int i = 0; i < strings.length; i++) {
          Map<String, Object> map1 = new HashMap<String, Object>();
          long d = Long.parseLong(strings[i]);// 收藏id
          map1.put("userId", s);
          map1.put("shopId", d);
          try {
            userShopCollectService.deleteUserShopCollect(map1);
            map.put("result", 1);
            map.put("success", true);
            map.put("error_msg", "取消收藏成功");
          } catch (Exception e) {
            map.put("result", 0);
            map.put("success", true);
            map.put("error_msg", "取消收藏失败");
          }
        }
      } else if (favType == 3) {// 分销产品库
        for (int i = 0; i < strings.length; i++) {
          long d = Long.parseLong(strings[i]);// 收藏id
          UserConsumerCollectPojo userConsumerCollectPojo = new UserConsumerCollectPojo();
          userConsumerCollectPojo.setUserId(s);
          userConsumerCollectPojo.setProductId(d);
          userConsumerCollectPojo = userConsumerCollectService.findCollect(userConsumerCollectPojo);
          if (userConsumerCollectPojo != null) {
            userConsumerCollectService.delUserConsumerCollect(userConsumerCollectPojo.getId());
            map.put("result", 1);
            map.put("success", true);
            map.put("error_msg", "取消收藏成功");
          } else {
            map.put("result", 0);
            map.put("success", true);
            map.put("error_msg", "取消收藏失败");
          }
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
   * 问题反馈
   * */
  public String feedback() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    String msg = "";
    boolean success = false;
    /*
     * if (uid == null || uid.equals("")) { msg = "用户Id不能为空！"; } else
     */if (type == null || type.equals("")) {
      msg = "反馈类型有误！";
    } else if (email == null || email.equals("")) {
      msg = "亲，留下您的邮箱吧！";
    } else if (phone == null || phone.equals("")) {
      msg = "亲，告诉您的手机号码呗，以便以后常联系啊！";
    } else {
      if (content != null) {
        try {
          feedBackPojo = new FeedBackPojo();
          if (uid != null) {
            feedBackPojo.setUserId(uid);
          }
          feedBackPojo.setType(Integer.parseInt(type));
          feedBackPojo.setEmail(email);
          feedBackPojo.setContent(content);
          feedBackPojo.setTelephone(phone);
          feedbackService.feedBackAdd(feedBackPojo);
          map.put("result", "1");
          msg = "反馈成功";
          success = true;
        } catch (Exception e) {
          map.put("result", "2");
          msg = "反馈失败";
        }
      } else {
        map.put("result", "3");
        msg = "请填写内容";
      }
    }
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
   * 访问记录
   * */
  public String footprint() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    List list = new ArrayList();
    Map<String, Object> map1 = null;
    String msg = "";
    if (uid == null || uid.equals("")) {
      msg = "用户ID不能为空！";
    } else {
      long s1 = uid;
      if (pageNo == null || pageNo.equals("")) {
        page = new Pager();
        page.setPageNo(1);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
      }
      page.setPageSize(10);
      historyPojo = new HistoryPojo();
      historyPojo.setUserId(s1);
      List<HistoryPojo> historylist = historyService.historyUserList2(historyPojo, page);
      for (HistoryPojo h : historylist) {
        map1 = new HashMap<String, Object>();
        map1.put("id", h.getId());
        map1.put("productId", h.getBusinessId());
        map1.put("name", h.getProductName());
        map1.put("price", df.format(h.getDistributionPrice()));
        map1.put("image", ConstParam.URL + "/upfiles/product/small" + File.separator + h.getImage());
        list.add(map1);
      }
    }
    map.put("error_msg", msg);
    map.put("result", list);
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
   * api 添加地址
   **/
  public String addAddress() throws SQLException {
    String msg = "";
    boolean success = false;
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid == null || uid.equals("")) {
      msg = "用户ID不能为空！";

    } else if (province == null || province.equals("")) {
      msg = "请选择省！";
    } else if (city == null || city.equals("")) {
      msg = "请选择市！";
    } else if (area == null || area.equals("")) {
      msg = "请选择区！";
    } else if (tel == null || tel.equals("")) {
      msg = "请填写收货人电话！";
    } else if (name == null || name.equals("")) {
      msg = "请填写收货人姓名！";
    } else if (address == null || address.equals("")) {
      msg = "请填写收货人详细地址！";
    }
    /*
     * else if (postCode == null || postCode.equals("")) { msg = "请填写邮编！"; }
     */
    else {
      long s1 = uid;
      DeliveryAddressPojo deliveryAddressPojo = new DeliveryAddressPojo();
      deliveryAddressPojo.setAddress(address);
      deliveryAddressPojo.setUserId(s1);
      deliveryAddressPojo.setProvince(province);
      deliveryAddressPojo.setArea(area);
      deliveryAddressPojo.setCity(city);

      if (isDefault != null && isDefault == 1) {
        deliveryAddressPojo.setIsDefault(1);
      } else {
        deliveryAddressPojo.setIsDefault(0);
      }
      deliveryAddressPojo.setSorting(0);
      deliveryAddressPojo.setStatus(0);
      deliveryAddressPojo.setConsigneePhone(tel);
      deliveryAddressPojo.setConsignee(name);
      // deliveryAddressPojo.setPostcode(postCode);
      try {
        deliveryAddressService.addDeliveryAddress(deliveryAddressPojo);
        if (isDefault != null && isDefault == 1) {
          // 将其他id的地址设置成非默认状态
          DeliveryAddressPojo deliveryAddress = new DeliveryAddressPojo();
          deliveryAddress.setUserId(uid);
          deliveryAddress.setId(deliveryAddressPojo.getId());
          deliveryAddress.setIsDefault(0);
          deliveryAddressService.updateAddressToNotDefault(deliveryAddress);
        }
        map.put("result", "1");
        success = true;
        msg = "添加成功！";
      } catch (Exception e) {
        map.put("result", "2");
        msg = "添加失败！";
      }
    }
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
   * 设置默认地址
   * */
  public String selectAddress() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    String msgString = "";
    boolean success = false;
    if (uid == null || uid.equals("")) {
      msgString = "用户ID不能为空！";
    } else if (addId == null || addId.equals("")) {
      msgString = "地址Id不能为空！";
    } else {
      long s1 = uid;
      long s2 = addId;
      try {
        DeliveryAddressPojo deliveryAddressPojo = new DeliveryAddressPojo();
        deliveryAddressPojo.setUserId(s1);
        deliveryAddressPojo.setId(s2);
        deliveryAddressPojo.setIsDefault(1);
        deliveryAddressService.updateAddressToDefault(deliveryAddressPojo);// 设置为默认地址
        // 将其他id的地址设置成非默认状态
        deliveryAddressPojo.setUserId(s1);
        deliveryAddressPojo.setId(s2);
        deliveryAddressPojo.setIsDefault(0);
        deliveryAddressService.updateAddressToNotDefault(deliveryAddressPojo);
        map.put("result", "0");
        success = true;
        msgString = "设置成功!";
      } catch (Exception e) {
        map.put("result", "1");
        msgString = "设置失败!";
      }
    }
    map.put("error_msg", msgString);
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
   * 地址详情
   * */
  public String addressDetail() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    String msgString = "";
    boolean success = false;
    if (uid == null || uid.equals("")) {
      msgString = "用户ID不能为空！";
    } else if (addId == null || addId.equals("")) {
      msgString = "地址Id不能为空！";
    } else {
      long s1 = uid;
      long s2 = addId;
      DeliveryAddressPojo deliveryAddressPojo = new DeliveryAddressPojo();
      deliveryAddressPojo.setUserId(s1);
      deliveryAddressPojo.setId(s2);
      deliveryAddressPojo = deliveryAddressService.getAddress(deliveryAddressPojo);
      if (deliveryAddressPojo != null) {
        map2.put("addId", deliveryAddressPojo.getId());
        map2.put("uid", deliveryAddressPojo.getUserId());
        map2.put("province", deliveryAddressPojo.getProvince());
        map2.put("provinceName", deliveryAddressPojo.getProvinceName());
        map2.put("city", deliveryAddressPojo.getCity());
        map2.put("cityName", deliveryAddressPojo.getCityName());
        map2.put("area", deliveryAddressPojo.getArea());
        map2.put("areaName", deliveryAddressPojo.getAreaName());
        map2.put("address", deliveryAddressPojo.getAddress());
        map2.put("name", deliveryAddressPojo.getConsignee());
        map2.put("tel", deliveryAddressPojo.getConsigneePhone());
        map2.put("postcodes", deliveryAddressPojo.getPostcode());
        map2.put("defaultStatus", deliveryAddressPojo.getIsDefault());
      }
      success = true;
    }
    map.put("result", map2);
    map.put("error_msg", msgString);
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
   * 修改地址
   * */
  public String eidtAddress() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    String msg = "";
    boolean success = false;
    if (uid == null || uid.equals("")) {
      msg = "用户ID不能为空！";

    } else if (addId == null || addId.equals("")) {
      msg = "地址Id不能为空！";
    } else if (province == null || province.equals("")) {
      msg = "请选择省！";
    } else if (city == null || city.equals("")) {
      msg = "请选择市！";
    } else if (area == null || area.equals("")) {
      msg = "请选择区！";
    } else if (tel == null || tel.equals("")) {
      msg = "请填写收货人电话！";
    } else if (name == null || name.equals("")) {
      msg = "请填写收货人姓名！";
    } else if (address == null || address.equals("")) {
      msg = "请填写收货人详细地址！";
    }
    /*
     * else if (postCode == null || postCode.equals("")) { msg = "请填写邮编！"; }
     */
    else {
      DeliveryAddressPojo deliveryAddressPojo = new DeliveryAddressPojo();
      deliveryAddressPojo.setId(addId);
      deliveryAddressPojo.setUserId(uid);
      deliveryAddressPojo.setAddress(address);
      deliveryAddressPojo.setProvince(province);
      deliveryAddressPojo.setArea(area);
      deliveryAddressPojo.setCity(city);
      deliveryAddressPojo.setConsigneePhone(tel);
      deliveryAddressPojo.setConsignee(name);
      // deliveryAddressPojo.setPostcode(postCode);
      if (isDefault != null && isDefault == 1) {
        deliveryAddressPojo.setIsDefault(1);
        // 将其他id的地址设置成非默认状态
        DeliveryAddressPojo deliveryAddress = new DeliveryAddressPojo();
        deliveryAddress.setUserId(uid);
        deliveryAddress.setId(addId);
        deliveryAddress.setIsDefault(0);
        deliveryAddressService.updateAddressToNotDefault(deliveryAddress);
      } else if (isDefault != null && isDefault == 0) {
        deliveryAddressPojo.setIsDefault(0);
      }
      try {
        deliveryAddressService.updateDeliveryAddress(deliveryAddressPojo);
        map.put("result", "0");
        msg = "修改成功!";
        success = true;
      } catch (Exception e) {
        map.put("result", "1");
        msg = "修改失败!";
      }
    }
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
   * 删除地址
   * */
  public String deleteAddress() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    String msg = "";
    boolean success = false;
    if (uid == null || uid.equals("")) {
      msg = "用户ID不能为空！";
    } else if (addId == null || addId.equals("")) {
      msg = "地址Id不能为空！";
    } else {
      long s1 = uid;
      long s2 = addId;
      try {
        deliveryAddressPojo = new DeliveryAddressPojo();
        deliveryAddressPojo.setUserId(s1);
        deliveryAddressPojo.setId(s2);
        deliveryAddressService.delDeliveryAddress(deliveryAddressPojo);
        map.put("result", "1");
        success = true;
        msg = "删除成功!";
      } catch (Exception e) {
        map.put("result", "2");
        msg = "删除失败!";
      }
    }
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
   * 查看物流
   * */
  /*
   * public String express() throws Exception { Map<String, Object> map = new HashMap<String,
   * Object>(); Map<String, Object> map2 = new HashMap<String, Object>(); List list = new
   * ArrayList(); String msg = ""; Map<String, Object> map1 = null; if (orderId == null ||
   * orderId.equals("")) { map.put("result", list); map.put("error_msg", "订单ID不能为空！");
   * map.put("success", false); } else { long s = orderId; OrderShipPojo orderShipPojo =
   * orderShipService.findByIdOrderShip(s); // 订单物流公司英文名转中文 if (orderShipPojo != null &&
   * (orderShipPojo.getLogisticsName() != null && !"".equals(orderShipPojo.getLogisticsName())) &&
   * (orderShipPojo.getLogisticsNo() != null && !"".equals(orderShipPojo.getLogisticsNo()))) {
   * String type = orderShipPojo.getLogisticsName(); String postid = orderShipPojo.getLogisticsNo();
   * String productImages=orderShipPojo.getProductImages(); String state = ""; String expressType =
   * ""; String expressNumber = ""; String url = "http://www.kuaidi100.com/query?type=" + type +
   * "&postid=" + postid; String str = loadJson(url); if (str != null && !"".equals(str) &&
   * str.startsWith("{")) { JSONObject jsonobject = JSONObject.fromObject(str); if
   * (jsonobject.get("status").equals("403")) { msg = (String)jsonobject.get("message"); } else if
   * (jsonobject.get("status").equals("201")) { msg = "单号不存在或者已过期"; } else if
   * (!jsonobject.get("status").equals("400")) { state = jsonobject.getString("state"); expressType
   * = orderShipPojo.getLogisticsNameCN(); expressNumber = jsonobject.getString("nu"); JSONArray
   * jsonarray = jsonobject.getJSONArray("data"); for (int i = 0; i < jsonarray.size(); i++) { map1
   * = new HashMap<String, Object>(); map1.put("content",
   * jsonarray.getJSONObject(i).get("context")); map1.put("time",
   * jsonarray.getJSONObject(i).get("time")); list.add(map1); } } } map2.put("state", state);
   * map2.put("expressType", expressType); map2.put("expressNumber", expressNumber);
   * map2.put("productImages", "http://b2c.taozhuma.com/upfiles/product/small" + File.separator +
   * productImages); map2.put("express", list); map.put("result", map2); map.put("error_msg", msg);
   * map.put("success", true); } else { map.put("result", list); map.put("error_msg", "单号不存在或者已过期");
   * map.put("success", false); } } JSONObject json = JSONObject.fromObject(map);
   * ServletActionContext.getResponse().setContentType("text/html; charset=utf-8"); try {
   * ServletActionContext.getResponse().getWriter().write(json.toString()); } catch (IOException e)
   * { e.printStackTrace(); } return null; }
   */

  /**
   * 
   * 查看物流
   * */
  public String express() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    List list = new ArrayList();
    String msg = "";
    Map<String, Object> map1 = null;
    if (orderId == null || orderId.equals("")) {
      map.put("result", list);
      map.put("error_msg", "订单ID不能为空！");
      map.put("success", false);
    } else {
      long s = orderId;
      OrderShipPojo orderShipPojo = orderShipService.findByIdOrderShip(s);
      // 订单物流公司英文名转中文
      if (orderShipPojo != null && orderShipPojo.getLogisticsName() != null
          && !"".equals(orderShipPojo.getLogisticsName()) && orderShipPojo.getLogisticsNo() != null
          && !"".equals(orderShipPojo.getLogisticsNo())) {
        String type = orderShipPojo.getLogisticsName().trim();
        String postid = orderShipPojo.getLogisticsNo().trim();
        String productImages = orderShipPojo.getProductImages();
        String expressType =
            orderShipPojo.getLogisticsNameCN() == null ? "" : orderShipPojo.getLogisticsNameCN();
        String expressNumber = orderShipPojo.getLogisticsNo();
        // double ran = Math.random();
        double ran = 1;
        String url = "";
        String str = "";
        String state = "0";
        String status = "";
        if (ran < 0.5) {
          url = "http://api.kuaidi100.com/api?id=046b4e493f2ff874&com=" + type + "&nu=" + postid;
          str = loadJson(url);
          if (str != null && !"".equals(str) && str.startsWith("{")) {
            JSONObject jsonobject = JSONObject.fromObject(str);
            status = jsonobject.getString("status");
            if ("1".equals(status)) {
              JSONArray jsonarray = jsonobject.getJSONArray("data");
              for (int i = 0; i < jsonarray.size(); i++) {
                map1 = new HashMap<String, Object>();
                map1.put("content", jsonarray.getJSONObject(i).get("context"));
                map1.put("time", jsonarray.getJSONObject(i).get("time"));
                list.add(map1);
              }
              state = jsonobject.getString("state");
            } else {
              state = "0";
              msg = "物流单号暂无结果！";
            }
          }
        } else {
          url = "http://poll.kuaidi100.com/poll/query.do";
          String param = "{\"com\":\"" + type + "\",\"num\":\"" + postid + "\"}";
          String customer = "E22EF3066638CA9DFA3B7B36B7268C67";
          String key = "MuIevzgP7153";
          String sign = MD5Util.MD5(param + key + customer);
          param = "param=" + param + "&sign=" + sign + "&customer=" + customer;
          str = StringUtil.loadJson(url, param, "post");
          if (str != null && !"".equals(str) && str.startsWith("{")) {
            JSONObject jsonobject = JSONObject.fromObject(str);
            status = (String) jsonobject.get("status");
            if (status != null && "200".equals(status)) {
              JSONArray jsonarray = jsonobject.getJSONArray("data");
              for (int i = 0; i < jsonarray.size(); i++) {
                map1 = new HashMap<String, Object>();
                map1.put("content", jsonarray.getJSONObject(i).get("context"));
                map1.put("time", jsonarray.getJSONObject(i).get("time"));
                list.add(map1);
              }
              state = jsonobject.getString("state");
            } else {
              state = "0";
              msg = "物流单号暂无结果！";
            }
          }
        }
        // 运单的当前状态：0物流单号暂无结果，3在途，4 揽件，5 疑难，6签收，7退签，8 派件，9 退回
        map2.put("state", state);
        map2.put("express", list);
        map2.put("expressType", expressType);
        map2.put("expressNumber", expressNumber);
        map2.put("productImages", ConstParam.URL + "/upfiles/product/small" + File.separator
            + productImages);
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
   * 
   * 查看退货物流
   * */
  /*
   * public String refundExpress() throws Exception { Map<String, Object> map = new HashMap<String,
   * Object>(); Map<String, Object> map2 = new HashMap<String, Object>(); List list = new
   * ArrayList(); String msg = ""; Map<String, Object> map1 = null; if (oid == null ||
   * oid.equals("")) { map.put("result", list); map.put("error_msg", "订单详情ID不能为空！");
   * map.put("success", false); } else { long s = oid; UserOrderRefundPojo
   * userOrderRefundPojo=userOrderRefundService.findUserOrderRefundByOid(s); // 订单物流公司英文名转中文 if
   * (userOrderRefundPojo != null && (userOrderRefundPojo.getLogType() != null &&
   * !"".equals(userOrderRefundPojo.getLogType())) && (userOrderRefundPojo.getLogistics()!= null &&
   * !"".equals(userOrderRefundPojo.getLogistics()))) { String type =
   * userOrderRefundPojo.getLogType(); String postid = userOrderRefundPojo.getLogistics(); String
   * productImages=userOrderRefundPojo.getProductImage(); String state = ""; String expressType =
   * ""; String expressNumber = ""; String url = "http://www.kuaidi100.com/query?type=" + type +
   * "&postid=" + postid; String str = loadJson(url); if (str != null && !"".equals(str) &&
   * str.startsWith("{")) { JSONObject jsonobject = JSONObject.fromObject(str); if
   * (jsonobject.get("status").equals("403")) { msg = (String)jsonobject.get("message"); } else if
   * (jsonobject.get("status").equals("201")) { msg = "单号不存在或者已过期"; } else if
   * (!jsonobject.get("status").equals("400")) { state = jsonobject.getString("state"); expressType
   * = userOrderRefundPojo.getLogisticsNameCN(); expressNumber = jsonobject.getString("nu");
   * JSONArray jsonarray = jsonobject.getJSONArray("data"); for (int i = 0; i < jsonarray.size();
   * i++) { map1 = new HashMap<String, Object>(); map1.put("content",
   * jsonarray.getJSONObject(i).get("context")); map1.put("time",
   * jsonarray.getJSONObject(i).get("time")); list.add(map1); } } } map2.put("state", state);
   * map2.put("expressType", expressType); map2.put("expressNumber", expressNumber);
   * map2.put("productImages", "http://b2c.taozhuma.com/upfiles/product" + File.separator +
   * productImages); map2.put("express", list); map.put("result", map2); map.put("error_msg", msg);
   * map.put("success", true); } else { map.put("result", list); map.put("error_msg", "单号不存在或者已过期");
   * map.put("success", false); } } JSONObject json = JSONObject.fromObject(map);
   * ServletActionContext.getResponse().setContentType("text/html; charset=utf-8"); try {
   * ServletActionContext.getResponse().getWriter().write(json.toString()); } catch (IOException e)
   * { e.printStackTrace(); } return null; }
   */
  public String refundExpress() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    List list = new ArrayList();
    String msg = "";
    Map<String, Object> map1 = null;
    if (oid == null || oid.equals("")) {
      map.put("result", list);
      map.put("error_msg", "订单详情ID不能为空！");
      map.put("success", false);
    } else {
      long s = oid;
      UserOrderRefundPojo userOrderRefundPojo = userOrderRefundService.findUserOrderRefundByOid(s);
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
        String str = loadJson(url);
        if (str != null && !"".equals(str) && str.startsWith("{")) {
          JSONObject jsonobject = JSONObject.fromObject(str);
          if (!(Boolean) jsonobject.get("success")) {
            msg = (String) jsonobject.get("reason");
          } else {
            state = jsonobject.getString("status");
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
   * 
   * 将地址接收到的json转成str
   * */
  public static String loadJson(String url) {
    StringBuilder json = new StringBuilder();
    try {
      URL urlObject = new URL(url);
      URLConnection uc = urlObject.openConnection();
      BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
      String inputLine = null;
      while ((inputLine = in.readLine()) != null) {
        json.append(inputLine);
      }
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return json.toString();
  }

  /**
   * 
   * 我的地址列表
   * 
   * @throws SQLException
   * */
  public String myaddress() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> list = new ArrayList<Object>();
    String msg = "";
    boolean b = false;
    if (uid == null || uid.equals("")) {
      msg = "用户Id不能为空哦！";
    } else {
      if (pageNo == null || pageNo.equals("")) {
        page = new Pager();
        page.setPageNo(1);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
      }
      page.setPageSize(8);
      Map<String, Object> map1 = null;
      // 根据用户ID查询收货地址列表
      deliveryAddressPojo = new DeliveryAddressPojo();
      deliveryAddressPojo.setUserId(uid);
      List<DeliveryAddressPojo> listAddress =
          deliveryAddressService.deliveryAddressAllList(deliveryAddressPojo, page);
      if (listAddress.size() > 0) {
        for (DeliveryAddressPojo d : listAddress) {
          map1 = new HashMap<String, Object>();
          map1.put("addId", d.getId());
          map1.put("address",
              d.getProvinceName() + d.getCityName() + d.getAreaName() + d.getAddress());
          map1.put("postcode", d.getPostcode());
          map1.put("name", d.getConsignee());
          map1.put("tel", d.getConsigneePhone());
          map1.put("isDefault", d.getIsDefault());
          map1.put("province", d.getProvince());
          map1.put("city", d.getCity());
          map1.put("area", d.getArea());
          // 偏远地区标志
          map1.put("isRemote", "0");
          if (pid != null && pid > 0) {
            ProductPojo product = productService.getById(pid);
            if (product != null && product.getFaraway() != null
                && StringUtils.isNotEmpty(product.getFaraway()) && d.getProvinceId() != null
                && d.getProvinceId() > 0) {
              List<Long> farawayList = new ArrayList<>();
              String[] farawayArr = product.getFaraway().split(",");
              if (farawayArr != null && farawayArr.length > 0) {
                for (String f : farawayArr) {
                  farawayList.add(Long.valueOf(f));
                }
              }
              if (farawayList != null && farawayList.size() > 0) {
                if (farawayList.contains(d.getProvinceId())) {
                  map1.put("isRemote", 1);
                } else {
                  map1.put("isRemote", 0);
                }
              }
            }
          } else {
            String province = "";
            if (d != null && d.getProvinceName() != null && !"".equals(d.getProvinceName())) {
              province = d.getProvinceName();
              if (province.indexOf("新疆") != -1 || province.indexOf("西藏") != -1
                  || province.indexOf("内蒙古") != -1 || province.indexOf("青海") != -1
                  || province.indexOf("甘肃") != -1 || province.indexOf("宁夏") != -1) {
                map1.put("isRemote", 1);
              } else {
                map1.put("isRemote", 0);
              }
            }
          }

          list.add(map1);
        }
      } else {
        if (pageNo > 1) {
          msg = "查询不到地址!";
        } else {
          msg = "亲,您还没有添加地址哦!";
        }
      }
      b = true;
    }
    map.put("error_msg", msg);
    map.put("success", b);
    map.put("result", list);
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
   * 帮助中心--帮助类型
   * 
   * @throws SQLException
   * */
  public String helpCenter() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> list = new ArrayList<Object>();
    List<HelpTypePojo> helpTypePojoList = helpTypeService.getHelpTypeByPid(12L);
    for (HelpTypePojo helpTypePojo : helpTypePojoList) {
      Map<String, Object> mapFather = new HashMap<String, Object>();
      List<Object> listSon = new ArrayList<Object>();
      List<HelpTypePojo> helpUsualList = helpTypeService.getHelpTypeByPid(helpTypePojo.getId());
      for (HelpTypePojo HelpPojo : helpUsualList) {
        Map<String, Object> mapSon = new HashMap<String, Object>();
        mapSon.put("sonId", HelpPojo.getId().toString());
        mapSon.put("sonName", HelpPojo.getName());
        listSon.add(mapSon);
      }
      mapFather.put("son", listSon);
      mapFather.put("fatherId", helpTypePojo.getId().toString());
      mapFather.put("fatherName", helpTypePojo.getName());
      list.add(mapFather);
    }
    map.put("result", list);
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

  /**
   * 
   * 帮助中心--帮助类型
   * 
   * @throws SQLException
   * */
  public String helpCenterPid() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> childMap = null;
    List<Object> list = new ArrayList<Object>();
    String msg = "";
    boolean b = false;
    if (pid == null) {
      msg = "pid不能为空";
    } else {
      List<HelpTypePojo> helpTypePojoList = helpTypeService.getHelpTypeByPid(pid);
      if (helpTypePojoList != null && helpTypePojoList.size() > 0) {
        for (HelpTypePojo helpTypePojo : helpTypePojoList) {
          childMap = new HashMap<String, Object>();
          childMap.put("id", helpTypePojo.getId().toString());
          childMap.put("name", helpTypePojo.getName());
          list.add(childMap);
        }
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
   * 
   * 帮助中心--常见问题列表
   * 
   * @throws SQLException
   * @throws NumberFormatException
   * */
  public String helpCenterList() throws NumberFormatException, SQLException {
    boolean b = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> list = new ArrayList<Object>();
    if (typeId == null || typeId.equals("")) {
      msg = "亲，类型ID不能为空哦，(づ￣ 3￣)づ";
    } else {
      // 根据类型id详细查询内容列表
      List<HelpPojo> listHelpPojos = helpService.getListByTypeid(Long.parseLong(typeId));
      for (HelpPojo helpPojo : listHelpPojos) {
        Map<String, Object> mapList = new HashMap<String, Object>();
        mapList.put("id", helpPojo.getId().toString());
        mapList.put("title", helpPojo.getTitle());
        list.add(mapList);
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
   * 
   * 帮助中心--问题详情
   * */
  public String helpCenterDetail() {
    boolean b = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> list = new ArrayList<Object>();
    if (id == null || id.equals("")) {
      msg = "亲，问题ID不能为空哦(づ￣ 3￣)づ";
    } else {
      HelpPojo helpPojo = new HelpPojo();
      helpPojo.setId(id);
      helpPojo = helpService.goHelpUpdate(helpPojo);
      if (helpPojo == null) {
        msg = "亲，没有搜索到数据哦(* ￣3)(ε￣ *)";
      } else {
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("id", helpPojo.getId().toString());
        map1.put("title", helpPojo.getTitle());
        String s = helpPojo.getContent().toString();
        s = s.replaceAll("src=\"", "src=\"" + ConstParam.URL);
        s = s.replaceAll("img", "img width='100%'");
        if (id == 238) {
          String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
          Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
          Matcher m_style = p_style.matcher(s);
          s = m_style.replaceAll(""); // 过滤style标签
        }
        map1.put("content", s);
        list.add(map1);
        b = true;
      }
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
    String title = "";
    Long id = 0L;
    for (PagePushPojo pagePushPojo : list2) {
      Map<String, Object> map1 = new HashMap<String, Object>();
      map1.put("image", ConstParam.URL + "/upfiles/notice/" + pagePushPojo.getImages());
      map1.put("id", pagePushPojo.getTitle());
      map1.put("subType", pagePushPojo.getUrl());
      type = pagePushPojo.getUrl();
      // 1-产品，2-店铺，3-列表，4-文字
      if ("2".equals(type)) {
        id = Long.valueOf(pagePushPojo.getTitle());
        ActivityTimePojo activity = activityTimeService.findActivityTimeById(id);
        if (activity != null && StringUtils.isNotBlank(activity.getTitle())) {
          title = activity.getTitle();
        }
      } else if ("3".equals(type)) {
        id = Long.valueOf(pagePushPojo.getTitle());
        ActivityTitlePojo activity = activityTitleService.findActivityTitleById(id);
        if (activity != null && StringUtils.isNotBlank(activity.getTitle())) {
          title = activity.getTitle();
        }
      } else if ("4".equals(type)) {
        id = Long.valueOf(pagePushPojo.getTitle());
        InfoPojo info = infoService.findInfoById(id);
        if (info != null && StringUtils.isNotBlank(info.getTitle())) {
          title = info.getTitle();
        }
      }
      map1.put("title", title);
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

  /**
   * 
   * 消息
   * */
  public String clientInfo() {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> list = new ArrayList<Object>();
    // 得到消息列表
    InfoPojo iPojo = new InfoPojo();
    iPojo.setType(3);
    if (pageNo == null || pageNo.equals("")) {
      page = new Pager();
      page.setPageNo(1);
    } else {
      page = new Pager();
      page.setPageNo(pageNo);
    }
    page.setPageSize(10);
    List<InfoPojo> list1 = infoService.infoAllList(iPojo, page, 1);
    for (InfoPojo infoPojo : list1) {
      Map<String, Object> map1 = new HashMap<String, Object>();
      map1.put("id", infoPojo.getId());
      map1.put("author", infoPojo.getAuthor());
      map1.put("title", infoPojo.getTitle());
      map1.put("createDate", infoPojo.getCreate_date());
      list.add(map1);
    }
    map.put("result", list);
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

  /**
   * 
   * 消息详情
   * 
   * @throws SQLException
   * */
  public String clientInfoDetail() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    List<Object> list = new ArrayList<Object>();
    String msg = "";
    boolean b = false;
    if (id == null || id.equals("")) {
      msg = "亲，输入消息ID才能看哟(づ￣ 3￣)づ";
    } else {
      InfoPojo infoPo = infoService.findInfoById(id);
      map1.put("id", infoPo.getId().toString());
      map1.put("title", infoPo.getTitle());
      map1.put("author", infoPo.getAuthor());
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String date = simpleDateFormat.format(infoPo.getCreateDate());
      map1.put("createDate", date);
      // map1.put("content", StringUtil.delHTMLTag(infoPo.getContent()));
      String s = infoPo.getContent();
      s = s.replaceAll("src=\"", "src=\"" + ConstParam.URL);
      s = s.replaceAll("img", "img width='100%'");
      // map1.put("content", StringEscapeUtils.escapeHtml(s));
      map1.put("content", s.toString());
      b = true;
      list.add(map1);
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
   * 
   * 直接购买
   * 
   * @throws SQLException
   * */
  public String addPurchase() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    List result = new ArrayList();

    // 根据商品Id得到商品信息
    ProductPojo proPojo = new ProductPojo();
    proPojo.setId(pid);
    proPojo = productService.findProduct(proPojo);
    SysLoginPojo sysLogin = sysLoginService.findSysLoginById(uid);
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
              map2.put("type", 1);
            } else if (activityTimePojo != null && activityTimePojo.getType() == 1) {
              // 专题商品
              map2.put("type", 2);
            } else {
              // 活动商品
              map2.put("type", 3);
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
   * 套餐选中购买.
   * 
   * @return
   * @throws SQLException
   */
  public String addPkgPurchase() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    List result = new ArrayList();
    // 查询用户
    SysLoginPojo sysLogin = sysLoginService.findSysLoginById(uid);
    // 根据套餐Id得到商品信息
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("id", pkgId);
    option.put("type", 2);
    option.put("status", 1);
    // 未删除的
    option.put("isdelete", "0");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    option.put("currentTimeStr", sdf.format(new Date()));
    List<ScenePojo> scenePojos = sceneService.findSceneList(option);
    // 套餐价格
    Double pkgPrices = 0.0;
    // 购买数量
    int num = 0;
    // 套餐产品
    List<SceneProductPojo> sceneProductPojos = null;
    ScenePojo scenePojo = null;
    if (scenePojos != null && scenePojos.size() > 0) {
      scenePojo = scenePojos.get(0);
      pkgPrices = scenePojo.getPackagePrice();

      option.clear();
      option.put("sceneId", pkgId);
      // option.put("status", 1);
      sceneProductPojos = sceneProductService.findSceneProductList(option);
    }
    String msg = "";
    boolean b = false;
    if (uid == null || uid.equals("")) {
      msg = "会员ID是必须要填写哦！";
    } else if (sysLogin == null || sysLogin.getStatus() == 0) {
      msg = "该会员ID不存在哦！";
    } else if (pkgId == null || pkgId.equals("")) {
      msg = "套餐ID是必须要填写哦！";
    } else if (sceneProductPojos == null || sceneProductPojos.size() == 0) {
      msg = "套餐活动已结束！";
    } else if (scenePojo == null || !(scenePojo.getStock() != null && scenePojo.getStock() > 0)) {
      msg = "套餐活动库存不足！";
    } else {
      Long pid = 0L;
      Long userId = 0L;
      Double weight = 0.0;
      ProductPojo product = null;
      Map<String, Object> map2 = null;
      List list = new ArrayList();
      for (SceneProductPojo u : sceneProductPojos) {
        pid = u.getProductId();
        product = new ProductPojo();
        product.setId(pid);
        product = productService.findProduct(product);
        if (product != null) {
          map2 = new HashMap<String, Object>();
          map2.put("productId", product.getId());
          map2.put("sellingPrice", df.format(product.getSellingPrice()));
          map2.put("price", df.format(product.getDistributionPrice()));
          map2.put("discount",
              calcDiscount(product.getDistributionPrice(), product.getSellingPrice()));
          map2.put("productName", product.getProductName());
          map2.put("productNumber", 1);
          map2.put("productImage", ConstParam.URL + "/upfiles/product/small/" + product.getImage());
          map2.put("allPrice", df.format(product.getDistributionPrice()));
          map2.put("skuLinkId", skuLinkId == null ? "" : skuLinkId);
          map2.put("skuValue", "");
          map2.put("activityId", 0);
          map2.put("type", 0);
          userId = product.getUserId();
          weight += Double.valueOf(product.getWeight());
          num++;
          list.add(map2);
        }
      }

      // 根据产品信息中的用户ID查询到店铺
      ShopPojo shop = new ShopPojo();
      shop.setUserId(userId);
      shop = shopService.findShop(shop);
      if (shop != null) {
        map1.put("shopLogo", ConstParam.URL + "/upfiles/shop/" + shop.getImages());
        map1.put("allCount", num);
        map1.put("sumPrice", df.format(pkgPrices));
        map1.put("espressPrice", df.format(calcFare(weight)));
        map1.put("shopId", shop.getId());
        map1.put("shopName", shop.getName());
        map1.put("products", list);
        result.add(map1);
        b = true;
      } else {
        msg = "未找到该套餐信息！";
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
    String msg = "";
    boolean b = false;
    // 根据用户ID查询用户信息
    SysLoginPojo user = sysLoginService.findSysLoginById(uid);
    // 获取商品信息
    ProductPojo productPojo = new ProductPojo();
    productPojo.setId(pid);
    productPojo = productService.findProduct(productPojo);
    if (uid == null || uid.equals("")) {
      msg = "会员Id不能为空哦！";
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
    } else if (payMethod == null || payMethod != 1 && payMethod != 2) {
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
          stockFlag = updateActivitySkuStock(cart);
          if (stockFlag) {
            price = cart.getStockPrice();
            // 更新购买数量
            num = cart.getNum();
          }
        } else if (activityId > 0) {
          cart.setActivityId(activityId);
          stockFlag = updateActivityStock(cart);
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

      for (int i = 0; i < msgs.length; i++) {
        // 分解字符串分别得到店铺ID和评价
        String[] strings = msgs[i].split(":");
        if (strings.length >= 2) {
          for (int k = 0; k < strings.length; k++) {
            String idsplit = strings[0];
            if (shop != null && shop.getId() == Long.parseLong(idsplit)) {
              buyer_message = strings[1];
            }
          }
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
      OrderPojo insert_order = orderService.findOrderByOrderNo(orderNo);
      OrderDetailPojo orderDetail = new OrderDetailPojo();
      orderDetail.setOrderId(insert_order.getId());
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
        activityTimePojo = activityTimeService.findActivityTimeById(activityId);
        if (activityTimePojo != null && activityTimePojo.getType() == 0) {
          // 秒杀商品
          orderDetail.setType(1);
        } else if (activityTimePojo != null && activityTimePojo.getType() == 1) {
          // 专题商品
          orderDetail.setType(2);
        } else {
          // 活动商品
          orderDetail.setType(3);
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
        couponOrder.setOrderId(insert_order.getId());
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
   * 
   * 套餐直接购买提交订单.
   * 
   * @throws Exception
   * */
  public String addPkgOrderByPurchase() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, String> alipayMap = new HashMap<String, String>();
    Map<String, Object> wxpayMap = new HashMap<String, Object>();
    String msg = "";
    boolean b = false;
    // 根据用户ID查询用户信息
    SysLoginPojo user = sysLoginService.findSysLoginById(uid);
    // 根据套餐Id得到商品信息
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("id", pkgId);
    option.put("type", 2);
    option.put("status", 1);
    // 未删除的
    option.put("isdelete", "0");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    option.put("currentTimeStr", sdf.format(new Date()));
    List<ScenePojo> scenePojos = sceneService.findSceneList(option);
    // 套餐价格
    Double pkgPrices = 0.0;
    // 购买数量
    int num = 0;
    // 套餐产品
    List<SceneProductPojo> sceneProductPojos = null;
    ScenePojo scenePojo = null;
    if (scenePojos != null && scenePojos.size() > 0) {
      scenePojo = scenePojos.get(0);
      pkgPrices = scenePojo.getPackagePrice();

      option.clear();
      option.put("sceneId", pkgId);
      // option.put("status", 1);
      sceneProductPojos = sceneProductService.findSceneProductList(option);
    }
    if (uid == null || uid.equals("")) {
      msg = "会员Id不能为空哦！";
    } else if (user == null || user.getStatus() == 0) {
      msg = "该会员ID不存在哦！";
    } else if (pkgId == null || pkgId == 0) {
      msg = "套餐Id不能为空哦！";
    } else if (sceneProductPojos == null || sceneProductPojos.size() == 0) {
      msg = "套餐活动已结束！";
    } else if (scenePojo == null || !(scenePojo.getStock() != null && scenePojo.getStock() > 0)) {
      msg = "套餐活动库存不足！";
    } else if (consigneeType == null || consigneeType == 0) {
      msg = "收货方式有误！";
    } else if (consigneeType == 1 && (addressId == null || addressId == 0)) {
      msg = "收货地址不能为空！";
    } else if (payMethod == null || payMethod != 1 && payMethod != 2) {
      msg = "支付方式有误哦！";
    } else {
      // 更新套餐库存
      int flag = updatePackageStock(pkgId);
      // 提示套餐库存不足
      if (flag == 0) {
        msg = "套餐活动库存不足！";
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
      // 分割消息
      String[] msgs = buyer_message.split(",");
      DeliveryAddressPojo address = null;// 自提地址可以为空
      // 快递方式
      if (addressId != null && consigneeType == 1) {
        address = addressService.findDeliveryAddressById(addressId);// 获取收货地址信息
      }

      DecimalFormat df = new DecimalFormat("#.##");
      // 订单描述--支付宝
      String body = "";
      // 商户订单号--支付宝
      String out_trade_no = UtilDate.getOrderNum() + UtilDate.getThree();

      Long pid = 0L;
      Long userId = 0L;
      double allCartPrice = 0.0;// 订单价格
      double allCartPrice0 = 0.0;// 总价格
      double weight = 0.0;// 总重量

      ProductPojo product = null;
      List<OrderDetailPojo> orderDetails = new ArrayList<OrderDetailPojo>();
      OrderDetailPojo orderDetail = null;
      for (SceneProductPojo u : sceneProductPojos) {
        pid = u.getProductId();
        product = new ProductPojo();
        product.setId(pid);
        product = productService.findProduct(product);
        if (product != null) {
          // 支付描述
          body += product.getProductName() + ";";

          orderDetail = new OrderDetailPojo();
          orderDetail.setUserId(user.getId());
          orderDetail.setLoginname(user.getLoginname());
          orderDetail.setProductId(product.getId());
          orderDetail.setProductName(product.getProductName());
          orderDetail.setProductImage(product.getImage());
          orderDetail.setWeight(product.getWeight());
          orderDetail.setStockId(0l);
          orderDetail.setStockPriceOld(product.getSellingPrice());
          orderDetail.setStockPrice(product.getDistributionPrice());
          orderDetail.setNum(1);
          orderDetail.setPostageType(product.getPostageType());
          orderDetail.setChannel(1);
          orderDetail.setStatus(1);
          // orderDetail.setSkuLinkId(skuLinkId);
          orderDetail.setCreateBy(user.getId());
          orderDetail.setCreateDate(new Date());
          orderDetail.setType(0);

          orderDetails.add(orderDetail);

          userId = product.getUserId();
          weight += Double.valueOf(product.getWeight());
          num++;
        }
      }

      // 根据产品信息中的用户ID查询到店铺
      ShopPojo shop = new ShopPojo();
      shop.setUserId(userId);
      shop = shopService.findShop(shop);

      // 偏远地区：新疆、西藏、内蒙古、青海、甘肃、宁夏——加运费10元
      Double deliverFee = 0.00;
      String province = "";
      // 订单价格 = 套餐价格
      allCartPrice = pkgPrices;
      allCartPrice0 = pkgPrices;
      if (address != null && address.getProvinceName() != null
          && !"".equals(address.getProvinceName())) {
        province = address.getProvinceName();
        if (province.indexOf("新疆") != -1 || province.indexOf("西藏") != -1
            || province.indexOf("内蒙古") != -1 || province.indexOf("青海") != -1
            || province.indexOf("甘肃") != -1 || province.indexOf("宁夏") != -1) {
          // 总重量
          deliverFee = calcFare(weight);
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
      order.setEspressPrice(deliverFee);
      // 快递方式
      if (address != null) {
        order.setConsignee(address.getConsignee());
        order.setConsigneeAddress(address.getProvinceName() + address.getCityName()
            + address.getAreaName() + address.getAddress());
        order.setConsigneePhone(address.getConsigneePhone());
        order.setProvince(address.getProvince() != null ? address.getProvince().toString() : "0");
        order.setCity(address.getCity() != null ? address.getCity().toString() : "0");
        order.setArea(address.getArea() != null ? address.getArea().toString() : "0");
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

      for (int i = 0; i < msgs.length; i++) {
        // 分解字符串分别得到店铺ID和评价
        String[] strings = msgs[i].split(":");
        if (strings.length >= 2) {
          for (int k = 0; k < strings.length; k++) {
            String idsplit = strings[0];
            if (shop != null && shop.getId() == Long.parseLong(idsplit)) {
              buyer_message = strings[1];
            }
          }
        }
      }
      // 买家留言
      if (buyer_message != null && !"".equals(buyer_message)) {
        order.setBuyerMessage(buyer_message);
      }
      // 支付宝订单号
      order.setOutTradeNo(out_trade_no);
      orderService.insertOrder(order);

      String subject = order.getOrderNo();
      // 用户首单标志更新
      int hadFirstOrder = orderService.checkUserFirstOrder(uid);
      if (hadFirstOrder == 0) {
        // 首单更新
        orderService.updateFirstOrder(out_trade_no);
      }

      // 生成订单详情
      OrderPojo insert_order = orderService.findOrderByOrderNo(orderNo);
      for (OrderDetailPojo orderDetailPojo : orderDetails) {
        orderDetailPojo.setOrderId(insert_order.getId());
        orderDetailPojo.setShopId(shop.getId());
        orderDetailService.insertOrderDetail(orderDetailPojo);
      }

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
        couponOrder.setOrderId(insert_order.getId());
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
   * 更新套餐库存.1-成功，0-库存不足
   * 
   * @param pkgId 套餐id
   * @return
   * @throws SQLException
   */
  public int updatePackageStock(Long pkgId) throws SQLException {
    int result = 0;
    synchronized (AppApiAction.class) {
      Map<String, Object> option = new HashMap<String, Object>();
      option.put("id", pkgId);
      option.put("type", 2);
      option.put("status", 1);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      option.put("currentTimeStr", sdf.format(new Date()));
      List<ScenePojo> scenePojos = sceneService.findSceneList(option);
      ScenePojo scenePojo = null;
      if (scenePojos != null && scenePojos.size() > 0 && scenePojos.get(0).getStock() != null
          && scenePojos.get(0).getStock() > 0) {
        scenePojo = new ScenePojo();
        scenePojo.setStock(scenePojos.get(0).getStock() - 1);
        scenePojo.setId(scenePojos.get(0).getId());
        sceneService.updateSceneById(scenePojo);
        result = 1;
      }
    }

    return result;
  }

  /**
   * 
   * 淘竹马注册用户数量
   * */
  public String allUserNum() {
    Map<String, Object> map = new HashMap<String, Object>();
    // SysLoginPojo sysLoginPojo = new SysLoginPojo();
    // sysLoginPojo.setCreateDateStartStr(StringUtil.dateToString(new Date())+" 00:00:00");
    SysLoginPojo sysLoginPojo = new SysLoginPojo();
    sysLoginPojo.setForm("allUserNum");
    int num = sysLoginService.sysLoginAllCount(sysLoginPojo, "1");
    // num += userInfoService.jinwanhaoAllCount(null);
    map.put("num", num);
    map.put("error_msg", "");
    map.put("success", true);
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
   * 今日成交量
   * 
   * @throws SQLException
   * */
  public String todayBargainNum() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    int num = orderService.todayBargainNum();
    map.put("num", num);
    map.put("error_msg", "");
    map.put("success", true);
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
   * 今日订单量
   * 
   * @throws SQLException
   * */
  public String todayOrderNum() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    int num = orderService.todayBargainNum();
    map.put("num", num);
    map.put("error_msg", "");
    map.put("success", true);
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
   * 
   * 查询今日访问IP列表
   * 
   * @throws SQLException
   * */
  public String todaysVisitList() throws SQLException {
    String msg = "";
    boolean b = true;
    Map<String, Object> map = new HashMap<String, Object>();
    List<UserVisitPojo> userVisitPojos = userVisitService.getTodayVisitPojos();
    Map<String, Object> mapVisit = null;
    List<Object> list = new ArrayList<Object>();
    for (UserVisitPojo userVisitPojo : userVisitPojos) {
      mapVisit = new HashMap<String, Object>();
      mapVisit.put("id", userVisitPojo.getId());
      mapVisit.put("visitTime", userVisitPojo.getVisitTime());
      mapVisit.put("ip", userVisitPojo.getIp());
      mapVisit.put("ipDesc", userVisitPojo.getIpDesc());
      list.add(mapVisit);
    }
    map.put("result", list);
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
   * 今日IP访问总数
   * 
   * @throws SQLException
   * 
   * */
  public String todayVisitNum() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    int num = userVisitService.getTodayVisitNum();
    map.put("result", num);
    map.put("error_msg", "");
    map.put("success", true);
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
   * 总成交额
   * 
   * @throws SQLException
   * */
  public String transactionPriceSum() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    double money = orderService.transactionPriceSum();
    map.put("result", df.format(money));
    map.put("error_msg", "");
    map.put("success", true);
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
   * 查询省市区名称
   * */
  public String province() {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> mapArea = null;
    List<Object> list = new ArrayList<Object>();
    SysAreaPojo sysAreaPojo = new SysAreaPojo();
    sysAreaPojo.setPid(pid);
    List<SysAreaPojo> listAreaPojos = sysAreaService.getSysAreaByPid(sysAreaPojo);
    for (SysAreaPojo sysAreaPojo2 : listAreaPojos) {
      mapArea = new HashMap<String, Object>();
      mapArea.put("id", sysAreaPojo2.getId().toString());
      mapArea.put("name", sysAreaPojo2.getName());
      list.add(mapArea);
    }
    map.put("result", list);
    map.put("error_msg", "");
    map.put("success", true);
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
   * 查询所有物流公司表
   * */
  public String logistics() {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> list = new ArrayList<Object>();
    Map<String, Object> mapDictPojo = null;
    String msg = "";
    boolean b = true;
    SysDictPojo sysDictPojo = new SysDictPojo();
    sysDictPojo.setType("logistics_type");
    // 根据快递英文查询快递
    List<SysDictPojo> listDictPojos = sysDictService.sysDictAllList(sysDictPojo, page);
    if (listDictPojos.size() < 1) {
      msg = "没有搜索到数据！";
    } else {
      for (SysDictPojo sysDictPojo2 : listDictPojos) {
        mapDictPojo = new HashMap<String, Object>();
        mapDictPojo.put("id", sysDictPojo2.getId());
        mapDictPojo.put("name", sysDictPojo2.getName());
        mapDictPojo.put("nameEn", sysDictPojo2.getNameEn());
        list.add(mapDictPojo);
      }
    }
    map.put("result", list);
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
   * 
   * 订单单个产品详情
   * 
   * @throws SQLException
   * */
  public String orderDetailInfo() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> mapOrderDetailPojo = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    String msg = "";
    boolean b = false;
    if (orderDetailId == null || orderDetailId.equals("")) {
      msg = "订单详情Id不能为空！";
    } else {
      OrderDetailPojo orderDetailPojo = orderDetailService.findOrderDetailById(orderDetailId);
      if (orderDetailPojo == null) {
        msg = "没有该订单详情信息！";
      } else {
        mapOrderDetailPojo.put("productId", orderDetailPojo.getProductId());
        mapOrderDetailPojo.put("productName", orderDetailPojo.getProductName());
        mapOrderDetailPojo.put("productNumber", orderDetailPojo.getNum().toString());
        mapOrderDetailPojo.put("productImage", ConstParam.URL + "/upfiles/product/small"
            + File.separator + orderDetailPojo.getProductImage());
        mapOrderDetailPojo.put("reStatus", orderDetailPojo.getReStatus().toString());
        mapOrderDetailPojo.put("price", df.format(orderDetailPojo.getStockPrice()));
        mapOrderDetailPojo.put("shopId", orderDetailPojo.getShopId().toString());
        // 商品sku显示
        Integer skuid = orderDetailPojo.getSkuLinkId();
        mapOrderDetailPojo.put("skuLinkId", skuid == null ? "" : skuid);
        ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
        ProductSkuLinkPojo productSkuLinkPojo = null;
        if (skuid != null) {
          productSkuLink.setId(Long.valueOf(skuid));
          productSkuLinkPojo = productSkuLinkService.findProductSkuLink(productSkuLink);
          if (productSkuLinkPojo != null) {
            mapOrderDetailPojo.put("skuValue", "颜色:" + productSkuLinkPojo.getColorValue() + ";尺码:"
                + productSkuLinkPojo.getFormatValue());
          } else {
            mapOrderDetailPojo.put("skuValue", "");
          }
        } else {
          mapOrderDetailPojo.put("skuValue", "");
        }
        // 商品活动类型 0-普通商品 1-秒杀商品 2-专题商品 3-活动商品
        mapOrderDetailPojo.put("type",
            orderDetailPojo.getType() == null ? "" : orderDetailPojo.getType());
      }
      b = true;
    }
    map.put("result", mapOrderDetailPojo);
    map.put("success", b);
    map.put("error_msg", msg);
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
   * 搜索帮助中心
   * 
   * @throws SQLException
   * */
  public String searchHelp() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> list = new ArrayList<Object>();
    Map<String, Object> mapHelpPojo = null;
    String msg = "";
    boolean b = true;
    List<HelpPojo> helpPojos = helpService.helpSearch(name);
    if (helpPojos.size() < 1) {
      msg = "没有搜索到数据！";
    } else {
      for (HelpPojo helpPojo : helpPojos) {
        mapHelpPojo = new HashMap<String, Object>();;
        mapHelpPojo.put("id", helpPojo.getId().toString());
        mapHelpPojo.put("title", helpPojo.getTitle());
        list.add(mapHelpPojo);
      }
    }
    map.put("result", list);
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
   * 
   * 查询省市区名称
   * */
  public String provinceInfo() {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> mapSysa = new HashMap<String, Object>();
    String msg = "";
    boolean b = false;
    if (id == null || id.equals("")) {
      msg = "请输入省市区ID！";
    } else {
      SysAreaPojo sysAreaPojo = sysAreaService.getNameById(id.intValue());
      if (sysAreaPojo == null) {
        msg = "没有搜索到数据！";
      } else {
        mapSysa.put("id", sysAreaPojo.getId());
        mapSysa.put("name", sysAreaPojo.getName());
      }
      b = true;
    }
    map.put("result", mapSysa);
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
   * 
   * 修改密码
   * 
   * @throws SQLException
   * */
  public String reSetPassWord() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    String msg = "";
    boolean b = false;
    int result = 0;
    if (phone == null || phone.equals("")) {
      msg = "手机号码不能为空！";
    } else if (pass == null || pass.equals("")) {
      msg = "旧密码不能为空！";
    } else if (passWork == null || passWork.equals("")) {
      msg = "新密码不能为空！";
    } /*
       * else if (StringUtil.CheckSecurity(passWork) < 2) { msg = "新密码强度弱，请换一个！"; }
       */else if (passWork.length() < 6) {
      msg = "新密码强度弱（至少6位），请换一个！";
    } else {
      // 根据用户手机号码查询当前用户
      SysLoginPojo sysLoginPojo = sysLoginService.getSysLoginByLoginName(phone);
      // System.out.println(MD5Util.MD5(pass));
      if (sysLoginPojo == null) {
        msg = "没有该用户！";
        // } else if (sysLoginPojo.getPassword().equals(MD5Util.MD5(pass))) {
      } else if (sysLoginPojo.getPassword().equals(pass)) {
        sysLoginPojo.setPassword(passWork);
        sysLoginService.updatePassword(sysLoginPojo);
        result = 1;
        b = true;
        msg = "修改密码成功！";
      } else {
        msg = "旧密码输入错误！";
      }
    }
    map.put("error_msg", msg);
    map.put("result", result);
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
   * 退款维权列表
   * 
   * @throws SQLException
   * */
  public String myRefund() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = null;
    Map<String, Object> map2 = null;
    Map<String, Object> map3 = null;
    DecimalFormat df = new DecimalFormat("#.##");
    List<Object> list = new ArrayList<Object>();

    String msg = "";
    boolean b = false;
    if (uid == null || uid.equals("")) {
      msg = "会员ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      if (pageNo == null || pageNo.equals("")) {
        page = new Pager();
        page.setPageNo(1);
        page.setPageSize(10);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
        page.setPageSize(10);
      }
      UserOrderRefundPojo userOrderRefundPojo = new UserOrderRefundPojo();
      userOrderRefundPojo.setUserId(uid);
      List<UserOrderRefundPojo> orderlist =
          userOrderRefundService.findUserOrderRefundByUserId(userOrderRefundPojo, page);
      if (orderlist != null && orderlist.size() > 0) {
        OrderPojo order = null;
        List products = null;
        int num;
        List<OrderDetailPojo> orderDetaillist = null;
        List<UserOrderRefundPojo> userOrderRefundlist = null;
        List<OrderDetailPojo> orderDetailList = null;
        for (UserOrderRefundPojo p : orderlist) {
          order = orderService.findOrderById(p.getOrderId());
          if (order != null) {
            num = 0;
            map1 = new HashMap<String, Object>();
            orderDetaillist = orderDetailService.getfindByUserIdOrderDetail(order.getId());
            if (orderDetaillist != null && orderDetaillist.size() > 0) {
              for (int i = 0; i < orderDetaillist.size(); i++) {
                num += orderDetaillist.get(i).getNum();
              }
            }
            map1.put("num", num);
            userOrderRefundPojo = new UserOrderRefundPojo();
            userOrderRefundPojo.setOrderId(order.getId());
            userOrderRefundlist =
                userOrderRefundService.findUserOrderRefundByorderId(userOrderRefundPojo);
            if (orderDetaillist != null && userOrderRefundlist != null
                && orderDetaillist.size() == userOrderRefundlist.size()) {
              map1.put("refundId", 1);
            } else {
              map1.put("refundId", 0);
            }
            map1.put("orderId", order.getId());
            map1.put("orderNumber", order.getOrderNo());
            if (order.getAllPrice() == null) {
              order.setAllPrice(0d);
            }
            if (order.getEspressPrice() == null) {
              order.setEspressPrice(0d);
            }
            map1.put("orderPrice", df.format(order.getAllPrice() + order.getEspressPrice()));
            map1.put("espressPrice", df.format(order.getEspressPrice()));
            // 判断订单物流是否在45天前
            int isExpired = orderShipService.isOrderShipExpiredByOrderId(order.getId());
            map1.put("isExpired", isExpired);
            map1.put("orderStatus", order.getOrderStatus());
            map1.put("shopId", order.getShopId());
            ShopPojo shopPojo = shopService.findShopById(order.getShopId());
            map1.put("shopLogo", ConstParam.URL + "/upfiles/shop/"
                + (shopPojo == null ? "" : shopPojo.getImages()));
            map1.put("shopName", order.getShopName());
            map1.put("consigneeType", order.getConsigneeTypeName());
            orderDetailList = orderDetailService.getOrderDetail(order.getId());
            if (orderDetailList != null && orderDetailList.size() > 0) {
              ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
              ProductSkuLinkPojo productSkuLinkPojo = null;
              for (OrderDetailPojo orderDetailPojo2 : orderDetailList) {
                if (orderDetailPojo2.getReStatus() == null || orderDetailPojo2.getReStatus() == 0) {
                  continue;
                }

                products = new ArrayList();
                map2 = new HashMap<String, Object>();
                map2.put("detailId", orderDetailPojo2.getId());
                map2.put("productId", orderDetailPojo2.getProductId());
                map2.put("productName", orderDetailPojo2.getProductName());
                // map2.put("productNumber", orderDetailPojo2.getNum());
                map2.put("productNumber", orderDetailPojo2.getRefundNum());
                map2.put("productImage", ConstParam.URL + "/upfiles/product/small" + File.separator
                    + orderDetailPojo2.getProductImages());
                map2.put("Price", df.format(orderDetailPojo2.getStockPrice()));

                // 商品sku
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
                // 商品活动类型 0-普通商品 1-秒杀商品 2-专题商品 3-活动商品
                map2.put("type",
                    orderDetailPojo2.getType() == null ? "" : orderDetailPojo2.getType());
                products.add(map2);
                map1.put("reStatus", orderDetailPojo2.getReStatus());
                map3 = new HashMap<String, Object>();
                map3.putAll(map1);
                map3.put("carts", products);
                list.add(map3);
              }
            } else {
              map1.put("reStatus", "");
              map1.put("carts", "");
              list.add(map1);
              msg = "cars没有数据";
            }
            // if (products == null ||products.size() == 0) {
            // map1.put("carts", "");
            // } else {
            // map1.put("carts", products);
            // }
            // list.add(map1);
          }
        }
      } else {
        msg = "亲，您没有退货订单！";
      }
      b = true;
    }
    map.put("error_msg", msg);
    map.put("result", list);
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
        if (file != null) {
          String ddd = uploadFile(file, "upfiles" + File.separator + "orderRefund");
          orderRefundPojo.setImages(ddd);
        }
        if (file1 != null) {
          String ddd = uploadFile(file1, "upfiles" + File.separator + "orderRefund");
          orderRefundPojo.setImages2(ddd);
        }
        if (file2 != null) {
          String ddd = uploadFile(file2, "upfiles" + File.separator + "orderRefund");
          orderRefundPojo.setImages3(ddd);
        }
        OrderRefundPojo isnull = new OrderRefundPojo();
        isnull.setUserId(uid);
        isnull.setDetailId(s);
        if (orderRefundService.getorderRefundDetail(isnull) == null) {
          orderRefundPojo.setRefundNum(num);
          orderRefundPojo.setDetailId(s);
          orderRefundPojo.setUserId(uid);
          orderRefundPojo.setShopId(sid);
          orderRefundPojo.setType(Integer.valueOf(type));
          orderRefundPojo.setRefundType(refundType);
          orderRefundPojo.setRefundReason(refundReason);
          orderRefundPojo.setStatus(1);
          OrderDetailPojo orderDetailPojo = orderDetailService.getfindByIdOrderDetail(s);
          orderRefundPojo.setOrderId(orderDetailPojo.getOrderId());
          orderRefundPojo.setProductId(orderDetailPojo.getProductId());
          orderRefundPojo.setProductName(orderDetailPojo.getProductName());
          orderRefundPojo.setStockPrice(orderDetailPojo.getStockPrice());
          orderRefundPojo.setSkuLinkId(orderDetailPojo.getSkuLinkId());
          orderRefundService.addOrderRefundService(orderRefundPojo);
          Map<String, Object> map1 = new HashMap<String, Object>();
          map1.put("reStatus", 1);
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
          double allPrice = 0, couponPrice = 0;
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
        // 判断订单restatus是否都有
        // Map<String, Object> map2=new HashMap<String,Object>();
        // map2.put("orderId",orderDetailPojo.getOrderId());
        // map2.put("userId", orderDetailPojo.getUserId());
        // int d=orderRefundService.getNOrefundDetail(map2);
        // if(d==0){
        // OrderPojo o=new OrderPojo();
        // o.setIsCancelOrder(1);
        // o.setId(orderDetailPojo.getOrderId());
        // orderService.updateIsCancelOrder(o);
        // }
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
   * @throws SQLException
   **/
  public String SubmitLogistics() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    OrderRefundPojo orderRefundPojo = new OrderRefundPojo();

    String msg = "";
    int dresult = 0;
    boolean b = false;
    if (uid == null) {
      msg = "会员ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else if (oid == null) {
      msg = "订单详情ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else if (logisticsNum == null) {
      msg = "物流单号是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else if (logisticsName == null) {
      msg = "物流名称是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      try {
        long s = oid;
        orderRefundPojo.setLogistics(logisticsNum);
        orderRefundPojo.setLogType(logisticsName);
        orderRefundPojo.setDetailId(Long.valueOf(oid));
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
   * 未申请退货退款售后订单详情数据
   * 
   * @throws SQLException
   **/
  public String evaluation() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> list = new ArrayList<Object>();
    long s = oid;
    String msg = "";
    boolean b = false;
    if (uid == null) {
      msg = "会员ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else if (oid == null) {
      msg = "订单ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      try {
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("userId", uid);
        map2.put("orderId", s);
        List<OrderDetailPojo> orderlist = orderDetailService.getOrderDetailAllbyrestatus(map2);
        if (orderlist.size() == 0) {
          msg = "该订单已经申请退货退款信息，亲(づ￣3￣)づ╭❤～";
        } else {
          for (OrderDetailPojo p : orderlist) {
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("pid", p.getProductId());
            map1.put("productName", p.getProductName());
            map1.put("productImage",
                ConstParam.URL + "/upfiles/product/small/" + p.getProductImage());
            map1.put("detailId", p.getId());
            list.add(map1);
          }
        }
        b = true;

      } catch (Exception e) {
        e.printStackTrace();
        msg = "查询报错！！！亲(づ￣3￣)づ╭❤～";
      }
    }
    map.put("error_msg", msg);
    map.put("result", list);
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
   * 查询购物车产品类型数量
   * 
   * @throws SQLException
   **/
  public String myCartNum() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    String msg = "";
    boolean b = false;
    if (uid == null) {
      msg = "会员ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      try {
        String s = cartService.findCartByUserIdCount(uid) + "";
        map1.put("num", s);
        b = true;
      } catch (Exception e) {
        msg = "查询报错哦，亲(づ￣3￣)づ╭❤～";
      }

    }
    map.put("error_msg", msg);
    map.put("result", map1);
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
   * 返回默认地址
   * 
   * @throws SQLException
   **/
  public String defaultAddress() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    // List<Object> listarray=new ArrayList<Object>();
    Map<String, Object> map1 = null;
    String msg = "";
    boolean b = false;
    if (uid == null) {
      msg = "会员ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      try {
        DeliveryAddressPojo deliveryAddressPojo = new DeliveryAddressPojo();
        Pager pager = new Pager();
        pager.setPageNo(1);
        pager.setPageSize(1);
        deliveryAddressPojo.setUserId(uid);
        // deliveryAddressPojo.setIsDefault(1);
        List<DeliveryAddressPojo> list =
            deliveryAddressService.deliveryAddressAllList(deliveryAddressPojo, pager);
        if (list != null && list.size() > 0) {
          DeliveryAddressPojo p = list.get(0);
          map1 = new HashMap<String, Object>();
          map1.put("addId", p.getId());
          map1.put("address",
              p.getProvinceName() + p.getCityName() + p.getAreaName() + p.getAddress());
          map1.put("name", p.getConsignee());
          map1.put("postcode", p.getPostcode());
          map1.put("tel", p.getConsigneePhone());
          map1.put("isDefault", p.getIsDefault());
          map1.put("province", p.getProvince());
          // 偏远地区标志
          map1.put("isRemote", "0");
          if (pid != null && pid > 0) {
            ProductPojo product = productService.getById(pid);
            if (product != null && product.getFaraway() != null
                && StringUtils.isNotEmpty(product.getFaraway()) && p.getProvinceId() != null
                && p.getProvinceId() > 0) {
              List<Long> farawayList = new ArrayList<>();
              String[] farawayArr = product.getFaraway().split(",");
              if (farawayArr != null && farawayArr.length > 0) {
                for (String f : farawayArr) {
                  farawayList.add(Long.valueOf(f));
                }
              }
              if (farawayList != null && farawayList.size() > 0) {
                if (farawayList.contains(p.getProvinceId())) {
                  map1.put("isRemote", 1);
                } else {
                  map1.put("isRemote", 0);
                }
              }
            }
          } else {
            String province = "";
            if (p != null && p.getProvinceName() != null && !"".equals(p.getProvinceName())) {
              province = p.getProvinceName();
              if (province.indexOf("新疆") != -1 || province.indexOf("西藏") != -1
                  || province.indexOf("内蒙古") != -1 || province.indexOf("青海") != -1
                  || province.indexOf("甘肃") != -1 || province.indexOf("宁夏") != -1) {
                map1.put("isRemote", 1);
              } else {
                map1.put("isRemote", 0);
              }
            }
          }
          // listarray.add(map1);
          b = true;
        } else {
          msg = "请先添加地址！";
        }
      } catch (Exception e) {
        msg = "查询报错哦，亲(づ￣3￣)づ╭❤～";
      }

    }
    map.put("error_msg", msg);
    map.put("result", map1);
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

  public String uploadFile(File file, String s) {
    System.out.println("-----------------");
    System.out.println(fileName + "------------------" + file.length());

    try {
      FileInputStream fis = new FileInputStream(file);
      String path = ServletActionContext.getRequest().getRealPath(s);
      System.out.println(path);
      Random random = new Random();
      int i = random.nextInt(1000);
      fileName = StringUtil.getCurrentDateStrByfu() + i + ".jpg";
      File fs = new File(path, fileName);
      FileOutputStream fos = new FileOutputStream(fs);
      int len = 0;
      byte[] buffer = new byte[1024];

      while ((len = fis.read(buffer)) != -1) {
        fos.write(buffer, 0, len);
      }
      fos.flush();
      fos.close();
      fis.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return fileName;
  }

  /**
   * @return 品牌推送图
   * @throws SQLException
   */
  public String appActivityList() throws SQLException {
    String msg = "";
    boolean b = false;
    List<Object> list = new ArrayList<Object>();
    Map<String, Object> map = new HashMap<String, Object>();
    if (type == null || "".equals(type)) {
      msg = "产品类型不能为空哦！";
    } else {
      Map<String, Object> activitymap = new HashMap<String, Object>();
      if (pageNo == null || "".equals(pageNo)) {
        pageNo = 1;
      }
      activitymap.put("pageNo", (pageNo - 1) * 10);
      activitymap.put("pageSize", 10);
      activitymap.put("mainCategory", type);
      List<ActivityPojo> activtyPojoList = activityService.findAll(activitymap);
      if (activtyPojoList == null || activtyPojoList.size() == 0) {
        msg = "没有查询到商品数据！";
      } else {
        Map<String, Object> activiMap = null;
        String url = ConstParam.URL + "/upfiles/activity/";
        ShopPojo shopPojo = null;
        for (ActivityPojo activity : activtyPojoList) {
          activiMap = new HashMap<String, Object>();
          activiMap.put("type", activity.getType());
          activiMap.put("type_id", activity.getTypeId());
          activiMap.put("image", url + activity.getImage());
          activiMap.put("title", activity.getTitle());
          activiMap.put("shopName", "");
          // 1-店铺类型
          if ("1".equals(activity.getType())) {
            shopPojo =
                shopService.findShopById(activity.getTypeId() == null
                    || "".equals(activity.getTypeId()) ? 0 : Long.valueOf(activity.getTypeId()));
            if (shopPojo != null) {
              activiMap.put("shopName", shopPojo.getName());
            }
          }
          list.add(activiMap);
          b = true;
        }
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

  public String manufacturerCommentList() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = null;
    List<Object> list = null;
    String msg = "";
    boolean b = false;
    if (uid == null) {
      msg = "会员ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      if (page == null) {
        page = new Pager();
        page.setPageNo(1);

      }
      page.setPageNo(pageNo);
      page.setPageSize(10);
      List<ProductCommentPojo> productCommentlist =
          productCommentService.productCommentListByUid("", uid, page);
      list = new ArrayList<Object>();
      for (ProductCommentPojo p : productCommentlist) {
        map1 = new HashMap<String, Object>();
        map1.put("id", p.getId());
        map1.put("orderId", p.getOrderId());
        map1.put("orderNo", p.getOrderNo());
        map1.put("productId", p.getProductId());
        map1.put("productImage",
            ConstParam.URL + "/upfiles/product/small" + File.separator + p.getProductImage());
        map1.put("productName", p.getProductName());
        map1.put("comment", p.getComment());
        map1.put("create_date", p.getCreateDateStr());
        map1.put("name", p.getUserName());
        list.add(map1);
        b = true;
      }
    }
    map.put("result", list);
    map.put("success", b);
    map.put("error_msg", msg);
    JSONObject json1 = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json1.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String manufacturerShop() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = null;
    String msg = "";
    boolean b = false;
    ShopPojo shopPojo = new ShopPojo();
    if (uid == null) {
      msg = "会员ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      shopPojo.setUserId(uid);
      shopPojo = shopService.findShop(shopPojo);
      if (shopPojo != null) {
        map1 = new HashMap<String, Object>();
        map1.put("id", shopPojo.getId());
        map1.put("userId", shopPojo.getUserId());
        map1.put("name", shopPojo.getName());
        map1.put("image", ConstParam.URL + "/upfiles/shop" + File.separator + shopPojo.getImages());
        map1.put("address", shopPojo.getAddress());
        map1.put("content", shopPojo.getContent());
        map1.put("main_category", shopPojo.getMainCategoryName());
        map1.put("main_product", shopPojo.getMainProduct());
        map1.put("main_category_id", shopPojo.getMainCategory());
        map1.put("sales_area", shopPojo.getSalesArea());
        map1.put("phone", shopPojo.getPhone());
        b = true;
      }
    }
    map.put("result", map1);
    map.put("success", b);
    map.put("error_msg", msg);
    JSONObject json1 = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json1.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String updateShopInfo() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    String msg = "";
    int s = 0;
    boolean b = false;

    if (id == null || id.equals("")) {
      msg = "店铺ID不能为空！";
    } else if (uid == null || uid.equals("")) {
      msg = "用户ID不能为空！";
    } else {
      if (file != null) {
        String images = uploadFile(file, "upfiles" + File.separator + "shop");
        map1.put("images", images);
      }
      map1.put("id", id);
      map1.put("userId", uid);
      map1.put("name", name);

      map1.put("address", address);
      map1.put("content", content);
      map1.put("main_category", ":" + mainCategory + ":");
      map1.put("main_product", mainProduct);
      map1.put("sales_area", salesArea);
      map1.put("phone", phone);
      // shopService.selectShopInfo(map1);
      try {
        shopService.updateShopInfo(map1);
        b = true;
        s = 1;
      } catch (Exception e) {
        msg = "查询报错！";
      }

    }
    map.put("result", s);
    map.put("success", b);
    map.put("error_msg", msg);
    JSONObject json1 = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json1.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String productDetailHtml() throws SQLException {
    String str = "";
    if (id == null) {
      str = "产品ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      ProductPojo productPojo = new ProductPojo();
      productPojo.setId(id);
      productPojo = productService.findProduct(productPojo);
      if (productPojo != null && productPojo.getStatus() == 1) {
        str =
            str
                + "<html lang='en'><title>picture</title><head><meta charset='UTF-8'><style>.picWrap {min-width:280px;margin: 0 auto;}.picWrap img {width:100%;}@media screen and (min-width:800px) {.picWrap {width: 749px;}}</style></head>";
        // 根据商品Id查询焦点图
        str = str + "<body><div class='picWrap'>";
        List<ProductImagesPojo> list = productImagesService.productForId(productPojo.getId());
        if (list.size() > 0) {
          for (ProductImagesPojo productImagesPojo : list) {
            str =
                str + "<img src='http://b2c.taozhuma.com/upfiles/product/"
                    + productImagesPojo.getImages() + "'>";
          }
          str = str + "<img src='http://b2c.taozhuma.com/images/guarantee.jpg'>";
        }
        str = str + "</div></body></html>";
      } else {
        str = "没有该产品图片数据";
      }

    }
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(str);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String productDetailApp() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> list2 = new ArrayList<Object>();
    String msg = "";
    boolean b = false;
    if (id == null) {
      msg = "产品ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      Map<String, Object> map1 = null;
      ProductPojo productPojo = new ProductPojo();
      productPojo.setId(id);
      productPojo = productService.findProduct(productPojo);
      if (productPojo != null && productPojo.getStatus() == 1) {
        List<ProductImagesPojo> list = productImagesService.productForId(productPojo.getId());
        if (list != null && list.size() > 0) {
          String image = "";
          Image img = null;
          for (ProductImagesPojo productImagesPojo : list) {
            map1 = new HashMap<String, Object>();

            image = ConstParam.URL + "/upfiles/product/" + productImagesPojo.getImages();
            map1.put("image", image);

            try {
              img = Image.getInstance(new URL(image));
              map1.put("width", img.getWidth());
              map1.put("height", img.getHeight());
            } catch (BadElementException e) {
              e.printStackTrace();
              map1.put("width", 0);
              map1.put("height", 0);
            } catch (MalformedURLException e) {
              e.printStackTrace();
              map1.put("width", 0);
              map1.put("height", 0);
            } catch (IOException e) {
              e.printStackTrace();
              map1.put("width", 0);
              map1.put("height", 0);
            }
            list2.add(map1);
          }
          if (list2.size() > 0) {
            image = ConstParam.URL + "/images/guarantee.jpg";
            map1 = new HashMap<String, Object>();
            map1.put("image", image);
            try {
              img = Image.getInstance(new URL(image));
              map1.put("width", img.getWidth());
              map1.put("height", img.getHeight());
            } catch (BadElementException e) {
              e.printStackTrace();
              map1.put("width", 0);
              map1.put("height", 0);
            } catch (MalformedURLException e) {
              e.printStackTrace();
              map1.put("width", 0);
              map1.put("height", 0);
            } catch (IOException e) {
              e.printStackTrace();
              map1.put("width", 0);
              map1.put("height", 0);
            }
            list2.add(map1);
          }
          b = true;
        } else {
          msg = "该产品没有图片数据";
        }
      } else {
        msg = "没有该产品图片数据";
      }
    }
    map.put("result", list2);
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

  public String foot() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = null;
    DecimalFormat df = new DecimalFormat("#.##");
    List<Object> list = new ArrayList<Object>();
    String msg = "";
    if (pageNo == null || pageNo.equals("")) {
      page = new Pager();
      page.setPageNo(1);
    } else {
      page = new Pager();
      page.setPageNo(pageNo);
    }
    page.setPageSize(10);
    boolean b = false;
    if (uid == null) {
      msg = "用户ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      if (historyPojo == null) {
        historyPojo = new HistoryPojo();
      }
      historyPojo.setUserId(uid);
      List<HistoryPojo> historyPojolist = historyService.historyUserList2(historyPojo, page);
      for (HistoryPojo h : historyPojolist) {
        map2 = new HashMap<String, Object>();
        map2.put("productId", h.getBusinessId());
        map2.put("sellNumber", Integer.valueOf(h.getSellNumber()) + h.getBaseNumber());
        map2.put("productName", h.getProductName());
        map2.put("shopName", h.getShopName());
        map2.put("distributionPrice", df.format(h.getDistributionPrice()));
        map2.put("sellingPrice", df.format(h.getSellingPrice()));
        map2.put("lowestPrice", h.getLowestPrice() == null ? "" : df.format(h.getLowestPrice()));
        map2.put("image", ConstParam.URL + "/upfiles/product/" + h.getImage());
        map2.put("type", h.getType());
        map2.put("createDate", h.getCreateDateString());
        list.add(map2);
        b = true;
      }
    }
    map.put("result", list);
    map.put("success", b);
    map.put("error_msg", msg);
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
   * b2c我的足迹
   * */
  public String footNew() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = null;
    Map<String, Object> map3 = null;
    List<Object> list = new ArrayList<Object>();
    List<Object> list1 = new ArrayList<Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    String msg = "";
    boolean b = false;
    if (uid == null) {
      msg = "用户ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      if (historyPojo == null) {
        historyPojo = new HistoryPojo();
      }
      historyPojo.setUserId(uid);
      List<HistoryPojo> historyPojolist = historyService.historyUserList2(historyPojo, page);
      int len = historyPojolist == null ? 0 : historyPojolist.size();
      HistoryPojo h = null;
      HistoryPojo hn = null;
      // ActivityGoodsPojo activityGoods = null;
      Double factPrice = 0.0;
      for (int i = 0; i < len; i++) {
        h = historyPojolist.get(i);
        map2 = new HashMap<String, Object>();
        map2.put("productId", h.getBusinessId());
        map2.put("sellNumber", Integer.valueOf(h.getSellNumber()) + h.getBaseNumber());
        map2.put("productName", h.getProductName());
        map2.put("shopName", h.getShopName());
        // map2.put("distributionPrice", h.getDistributionPrice());
        // 活动价格判断
        factPrice = h.getDistributionPrice();
        /*
         * activityGoods = isProductActivity(h.getBusinessId()); if (activityGoods != null &&
         * activityGoods.getActivePrice() != null && activityGoods.getActivePrice().doubleValue() !=
         * 0) { factPrice = activityGoods.getActivePrice(); }
         */
        map2.put("distributionPrice", df.format(factPrice));

        map2.put("sellingPrice", df.format(h.getSellingPrice()));
        map2.put("discount", calcDiscount(factPrice, h.getSellingPrice()));
        map2.put("lowestPrice", h.getLowestPrice() == null ? "" : df.format(h.getLowestPrice()));
        map2.put("image", ConstParam.URL + "/upfiles/product/" + h.getImage());
        map2.put("type", h.getType());
        map2.put("createDate", h.getCreateDateString());

        if (h.getProStatus() != null && "1".equals(h.getProStatus())) {
          // 产品有效
          map2.put("proStatus", 1);
        } else {
          // 产品下架
          map2.put("proStatus", 0);
        }

        list.add(map2);
        if (i + 1 < len) {
          hn = historyPojolist.get(i + 1);
        } else {
          hn = null;
        }
        if (hn == null || !h.getCreateDateString().equals(hn.getCreateDateString())) {
          map3 = new HashMap<String, Object>();
          map3.put("viewTime", h.getCreateDateString());
          map3.put("products", list);
          map3.put("num", list.size());
          list1.add(map3);
          // 限制返回3个日期的足迹
          if (list1 != null && list1.size() >= 3) {
            break;
          }
          if (hn != null) {
            list = new ArrayList<Object>();
          }
        }
      }
      b = true;
    }
    map.put("result", list1);
    map.put("success", b);
    map.put("error_msg", msg);
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
   * 代理商注册Api
   * */
  @SuppressWarnings("unused")
  public String applyAgencyApi() throws Exception {
    boolean success = false;
    int result = 2;
    String error_msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    AgencyPojo aPojo = new AgencyPojo();

    if (company == null || company.equals("")) {
      error_msg = "公司名称不能为空！";
    } else if (uid == null) {
      error_msg = "会员ID不能为空！";
    } else if (sysLoginService.getfindByIdSysLogin(uid) == null) {// 判断会员ID是否存在
      error_msg = "该会员没有注册本网站！";
    }
    // else if (mainCategory<1 && mainCategory>6) {
    // error_msg = "主营类目有误！";
    // }
    else if (contact == null || contact.equals("")) {
      error_msg = "联系人不能为空！";
    } else if (email == null || email.equals("")) {
      error_msg = "邮箱地址不能为空！";
    } else if (QQ == null || QQ.equals("")) {
      error_msg = "联系QQ不能为空！";
    } else if (phone == null || phone.equals("")) {
      error_msg = "联系手机号码不能为空！";
    } else if (addressCompany == null || addressCompany.equals("")) {
      error_msg = "公司地址不能为空！";
    } else if (corporation == null || corporation.equals("")) {
      error_msg = "法人代表不能为空！";
    } else if (province == null || province.equals("")) {
      error_msg = "省份不能为空！";
    } else if (city == null || city.equals("")) {
      error_msg = "城市不能为空！";
    } else if (manufacturerId == null || manufacturerId.equals("")) {
      error_msg = "代理厂家ID不能为空！";
    } else if (sysAreaService.getNameById(province.intValue()) == null) {// 判断省份是否存在
      error_msg = "省份不在所属范围内！";
    } else if (sysAreaService.getNameById(city.intValue()) == null) {// 判断城市是否存在
      error_msg = "城市不在所属范围内！";
    } else if (sysAreaService.getNameById(area.intValue()) == null) {// 判断地区是否存在
      error_msg = "地区不在所属范围内！";
    } else if (shopService.getfindByIdShop(Long.valueOf(manufacturerId)) == null) {// 判断代理厂商ID是否存在
      error_msg = "代理厂商ID不存在！";

    } else if (agencyService.getfindByUserIdAgency(uid) != null) {
      error_msg = "您已经提交过代理商申请，请不要重复申请！";

    } else {
      aPojo.setUserId(uid);
      aPojo.setCompany(company);
      aPojo.setMainCategory("0");
      aPojo.setContact(contact);
      aPojo.setTel(tel);
      aPojo.setEmail(email);
      aPojo.setQQ(QQ);
      aPojo.setPhone(phone);
      aPojo.setFax(fax);
      aPojo.setAddressHome(addressHome);
      aPojo.setProvince(Long.valueOf(province));
      aPojo.setCity(Long.valueOf(city));
      aPojo.setArea(Long.valueOf(area));
      aPojo.setLat(lat);
      aPojo.setLng(lng);
      aPojo.setManufacturerId(Long.valueOf(manufacturerId));
      aPojo.setAddressCompany(addressCompany);
      aPojo.setCorporation(corporation);
      aPojo.setCreateBy(uid);
      aPojo.setCreateDate(new Date());

      try {
        if (file1 != null) {
          String d = uploadFile(file1, "upfiles" + File.separator + "authentication");
          aPojo.setBusLicence(d);
        }
        // if (file2 != null) {
        // String dd = uploadFile(file2, "upfiles" + File.separator
        // + "authentication");
        // aaPojo.setId_back_photo(dd);
        // }
        // if (file3 != null) {
        // String ddd = uploadFile(file3, "upfiles" + File.separator
        // + "authentication");
        // aaPojo.setId_handle_photo(ddd);
        // }

      } catch (Exception e) {
        e.printStackTrace();
      }

      agencyService.insertAgency(aPojo);

      // aPojo = agencyService.getfindByUserIdAgency(uid);
      // aaPojo.setAgency_id(aPojo.getAgency_id());
      // aaPojo.setUser_id(aPojo.getUser_id());
      // aaPojo.setAuth_status(0);
      // agencyAuthenticationService.insertAgencyAuthentication(aaPojo);
      //
      // if (proxy_product != null) {
      //
      // String proxyProduct[] = proxy_product.split(",");
      // for (int i = 0; i < proxyProduct.length; i++) {
      // acPojo.setAgency_id(aPojo.getAgency_id());
      // acPojo.setProducrt_id(Long.valueOf(proxyProduct[i].trim()));
      // ProductPojo pPojo = new ProductPojo();
      // pPojo.setId(Long.valueOf(proxyProduct[i].trim()));
      // if (productService.findProduct(pPojo) != null) {
      // agencyCollectService.insertAgencyCollect(acPojo);
      // }
      // }
      // }

      result = 1;
      success = true;
      error_msg = "提交成功，您的申请我们的工作人员将在三个工作日内审核！";

    }
    map.put("success", success);
    map.put("result", result);
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
   * 
   * b2c批发商接收的百度推送
   * */
  public String agencyPush() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = null;
    Map<String, Object> map2 = null;
    DecimalFormat df = new DecimalFormat("#.##");
    List list3 = new ArrayList();
    if (uid == null || uid.equals("")) {
      map.put("error_msg", "用户ID不能为空");
      map.put("success", false);
    } else {
      long s = uid;
      String error_msg = "";
      try {
        orderPojo = new OrderPojo();
        orderPojo.setPushAgencyUid(s);

        if (pageNo == null || pageNo.equals("")) {
          page = new Pager();
          page.setPageNo(1);
        } else {
          page = new Pager();
          page.setPageNo(pageNo);
        }
        page.setPageSize(10);
        List<OrderPojo> list1 = orderService.getPushByUidOrder(orderPojo, page);
        if (list1 != null && list1.size() != 0) {
          for (OrderPojo p : list1) {
            List list = new ArrayList();
            int num = 0;
            map1 = new HashMap<String, Object>();
            List<OrderDetailPojo> orderDetaillist =
                orderDetailService.getfindByUserIdOrderDetail(p.getId());
            UserOrderRefundPojo userOrderRefundPojo = new UserOrderRefundPojo();
            for (int i = 0; i < orderDetaillist.size(); i++) {
              num += orderDetaillist.get(i).getNum();
            }
            map1.put("num", num);
            userOrderRefundPojo.setOrderId(p.getId());
            List<UserOrderRefundPojo> userOrderRefundlist =
                userOrderRefundService.findUserOrderRefundByorderId(userOrderRefundPojo);
            if (orderDetaillist.size() == userOrderRefundlist.size()) {
              map1.put("refundId", 1);
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
            map1.put("orderPrice", df.format(p.getAllPrice() + p.getEspressPrice()));
            map1.put("espressPrice", df.format(p.getEspressPrice()));
            // map1.put("num", p.getAllNum());
            map1.put("orderStatus", p.getOrderStatus());
            map1.put("shopId", p.getShopId());
            map1.put("shopName", p.getShopName());
            map1.put("agencyId", p.getAgencyId());
            map1.put("agencyUid", p.getAgencyUid());
            map1.put("pushStatus", p.getPushStatus());
            // 3-货到付款
            if (p.getPayMethod() != null && 3 == p.getPayMethod()) {
              map1.put("consigneeType", "货到付款");
            } else {
              map1.put("consigneeType", p.getConsigneeTypeName());
            }
            List<OrderDetailPojo> orderDetailList = orderDetailService.getOrderDetail(p.getId());
            ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
            ProductSkuLinkPojo productSkuLinkPojo = null;
            if (orderDetailList != null) {
              for (OrderDetailPojo orderDetailPojo2 : orderDetailList) {
                map2 = new HashMap<String, Object>();
                map2.put("productId", orderDetailPojo2.getProductId());
                map2.put("productName", orderDetailPojo2.getProductName());
                map2.put("productNumber", orderDetailPojo2.getNum());
                map2.put("productImage", ConstParam.URL + "/upfiles/product/small" + File.separator
                    + orderDetailPojo2.getProductImages());
                map2.put("price", df.format(orderDetailPojo2.getStockPrice()));

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
                // 商品活动类型 0-普通商品 1-秒杀商品 2-专题商品 3-活动商品
                map2.put("type",
                    orderDetailPojo2.getType() == null ? "" : orderDetailPojo2.getType());
                list.add(map2);
              }

            } else {
              error_msg = "carts为空";
            }
            if (list.size() == 0) {
              map1.put("carts", "");
            } else {
              map1.put("carts", list);
            }
            list3.add(map1);
          }
        } else {
          error_msg = "没有数据";
        }
        map.put("error_msg", error_msg);
        map.put("success", true);
      } catch (Exception e) {
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
   * b2c批发商确认百度推送
   * */

  public String setAgencyPush() throws SQLException {
    String msg = "";
    boolean b = false;
    int result = 0;
    Map<String, Object> map = new HashMap<String, Object>();

    if (orderId == null || orderId.equals("")) {
      msg = "订单id不能为空";
      result = 3;
    } else if (agencyId == null || agencyId.equals("")) {
      msg = "批发商ID不能为空";
      result = 3;
    } else {
      OrderPojo orderPojo = orderService.findOrderById(orderId);
      // 根据产品信息中的用户ID查询到店铺
      AgencyPushPojo agencyPush = new AgencyPushPojo();
      agencyPush.setOrderId(orderId);
      agencyPush.setAgencyId(agencyId);
      AgencyPushPojo agencyPushdetail = agencyPushService.findAgencyPush(agencyPush);

      if (agencyPushdetail.getStatus() == 0
          && (orderPojo.getAgencyUserId() == null || orderPojo.getAgencyUserId() == 0)) {
        AgencyPushPojo agencyPush0 = new AgencyPushPojo();
        agencyPush0.setId(agencyPushdetail.getId());
        agencyPush0.setStatus(1);
        agencyPushService.updateAgencyPushStatus(agencyPush0);

        AgencyPushPojo agencyPush10 = new AgencyPushPojo();
        agencyPush10.setId(agencyPushdetail.getId());
        agencyPush10.setOrderId(orderId);
        agencyPush10.setStatus(2);
        agencyPushService.updateAgencyPushOtherStatus(agencyPush10);

        int aid = new Long(agencyPushdetail.getUserId()).intValue();
        OrderPojo Order = new OrderPojo();
        Order.setId(orderId);
        Order.setAgencyUserId(aid);
        orderService.updateOrderPush(Order);
        b = true;
        msg = "抢单成功";
        result = 0;
      } else if (orderPojo.getAgencyUserId() > 0) {
        AgencyPushPojo agencyPush0 = new AgencyPushPojo();
        agencyPush0.setId(agencyPushdetail.getId());
        agencyPush0.setStatus(2);
        agencyPushService.findAgencyPush(agencyPush0);
        msg = "订单已被抢先确认";
        result = 1;
      } else if (agencyPush.getStatus() == 1) {
        msg = "该订单您已经成功抢单过了，无需再抢了";
        result = 2;
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
   * 
   * b2c批发商发货填写物流信息
   * */
  public String delivery() throws SQLException {
    String msg = "";
    boolean b = false;
    int result = 0;
    Map<String, Object> map = new HashMap<String, Object>();

    if (oid == null || "".equals(oid)) {
      msg = "订单id不能为空";
      result = 0;
    } else if (uid == null || "".equals(uid)) {
      msg = "批发商ID不能为空";
      result = 0;
    } else if (logisticsNum == null || "".equals(logisticsNum)) {
      msg = "物流单号不能为空";
      result = 0;
    } else if (logisticsName == null || "".equals(logisticsName)) {
      msg = "物流名称不能为空";
      result = 0;
    } else {
      // 发货人(批发商)信息查询
      AgencyPojo agency = agencyService.findagencyByUserId(uid);
      // 收货人信息查询
      OrderPojo order = orderService.findOrderById(new Long(oid));
      // 已付款时
      if (agency != null && order != null && 2 == order.getOrderStatus()) {
        // 发货人、收货人信息不为空
        // 新建订单物流信息
        OrderShipPojo orderShip = new OrderShipPojo();
        orderShip.setOrderId(new Long(oid));
        orderShip.setUserId(uid);
        orderShip.setLogisticsNo(logisticsNum);
        orderShip.setLogisticsName(logisticsName);
        orderShip.setConsignor(agency.getContact());
        orderShip.setConsignorAddress(agency.getAddressCompany());
        orderShip.setShipPhone(agency.getPhone());
        // 更新订单状态2-已付款 -> 3-已发货
        order.setOrderStatus(3);
        order.setSendDate(new Date());
        order.setUpdateBy(uid);
        order.setUpdateDate(new Date());
        orderService.updateOrder(order);
        // 订单已发货自动发送“发货短信”
        if (order != null) {
          if (order.getOrderStatus() == 3 && order.getConsigneePhone() != null) {
            String content = "【拼得好】客官您好，感谢您支持淘竹马，你的包裹已寄出，请注意查收，客服电话/QQ：4001503677，登陆APP邀请好友分享快乐。";
            /*
             * try { MxtongSMSCaptchaUtil.sendMxtongSMS(order.getConsigneePhone(), content); } catch
             * (UnsupportedEncodingException e) { e.printStackTrace(); }
             */
            SmsSendUtil.sendSMS(order.getConsigneePhone(), content);
          }
        }
        orderShip.setConsignee(order.getConsignee());
        orderShip.setConsigneeAddress(order.getConsigneeAddress());
        orderShip.setConsigneePhone(order.getConsigneePhone());
        orderShip.setConsigneeType(order.getConsigneeType());
        orderShip.setBuyerMessage(order.getBuyerMessage());
        orderShip.setOrderStatus(order.getOrderStatus());
        orderShip.setOrderNo(order.getOrderNo());
        // status:0-待审核;1-已审核
        orderShip.setStatus(0);
        orderShip.setCreateBy(uid);
        orderShip.setCreateDate(new Date());

        orderShipService.insertOrderShip(orderShip);
        b = true;
        msg = "提交成功！";
        result = 1;

      } else {
        msg = "提交失败！";
        result = 0;
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
   * 
   * 获取店铺主推产品图片
   * */
  public String getCommentPic() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();

    if (id == null || "".equals(id)) {
      map.put("error_msg", "店铺id不能为空");
      map.put("result", 0);
      map.put("success", false);
    } else {
      Map<String, Object> result = new HashMap<String, Object>();
      // 根据店铺ID查询商店
      ShopPojo shop = shopService.findShopById(id);
      if (shop != null) {
        String url = ConstParam.URL + "/upfiles/shop/";
        // 查询供应商公司信息
        ManufacturerPojo manufacturer =
            manufacturerService.findManufacturerByUserId(shop.getUserId());
        // 主推图片
        result.put("commentPic", url + shop.getTopImage());
        // 公司简介
        result.put("introduce", manufacturer.getContent());
      }
      map.put("success", true);
      map.put("error_msg", "");
      map.put("result", result);
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
      String url = ConstParam.URL + "/upfiles/pushhomepg/";
      for (PushHomePagePojo pushHomePagePojo : pushHomePagePojoList) {
        activiMap = new HashMap<String, Object>();
        activiMap.put("type", pushHomePagePojo.getType());
        activiMap.put("typeId", pushHomePagePojo.getParam());
        activiMap.put("image", url + pushHomePagePojo.getImage());
        activiMap.put("title", pushHomePagePojo.getTitle());
        activiMap.put("sorting", pushHomePagePojo.getSorting());
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
   * @return 首页分类推送图
   * @throws SQLException
   */
  public String homePageFloorList() throws SQLException {
    String msg = "";
    boolean b = false;
    List<Object> list = new ArrayList<Object>();
    List<Object> list1 = new ArrayList<Object>();
    List<Object> list2 = new ArrayList<Object>();
    List<Object> list3 = new ArrayList<Object>();
    List<Object> list4 = new ArrayList<Object>();
    List<Object> list5 = new ArrayList<Object>();
    List<Object> list6 = new ArrayList<Object>();
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    Map<String, Object> map3 = new HashMap<String, Object>();
    Map<String, Object> map4 = new HashMap<String, Object>();
    Map<String, Object> map5 = new HashMap<String, Object>();
    Map<String, Object> map6 = new HashMap<String, Object>();
    Map<String, Object> activitymap = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    activitymap.put("status", "1");
    List<PushHomePagePojo> pushHomePagePojoList = pushHomePageService.findAll(activitymap);
    if (pushHomePagePojoList == null || pushHomePagePojoList.size() == 0) {
      msg = "没有查询到活动数据！";
    } else {
      Map<String, Object> activiMap = null;
      String url = ConstParam.URL + "/upfiles/pushhomepg/";
      ProductPojo product = null;
      Double price = 0.00;
      // ActivityGoodsPojo activityGoods = null;
      for (PushHomePagePojo pushHomePagePojo : pushHomePagePojoList) {
        activiMap = new HashMap<String, Object>();
        activiMap.put("typeId", pushHomePagePojo.getParam());
        activiMap.put("image", url + pushHomePagePojo.getImage());
        activiMap.put("title", pushHomePagePojo.getTitle());
        activiMap.put("sorting", pushHomePagePojo.getSorting());
        // 查询产品价格
        if (pushHomePagePojo.getParam() != null && !"".equals(pushHomePagePojo.getParam())) {
          product = new ProductPojo();
          product.setId(Long.valueOf(pushHomePagePojo.getParam().trim()));
          product = productService.findProduct(product);
          price = product == null ? 0 : product.getDistributionPrice();
          // 活动价格判断
          /*
           * if (product != null) { activityGoods = isProductActivity(product.getId()); if
           * (activityGoods != null && activityGoods.getActivePrice() != null &&
           * activityGoods.getActivePrice().doubleValue() != 0) { price =
           * activityGoods.getActivePrice(); } }
           */
          activiMap.put("price", df.format(price));
          activiMap.put("sellingPrice", product == null ? 0 : df.format(product.getSellingPrice()));
        }

        if ("1".equals(pushHomePagePojo.getType())) {
          list1.add(activiMap);
        } else if ("2".equals(pushHomePagePojo.getType())) {
          list2.add(activiMap);
        } else if ("3".equals(pushHomePagePojo.getType())) {
          list3.add(activiMap);
        } else if ("4".equals(pushHomePagePojo.getType())) {
          list4.add(activiMap);
        } else if ("5".equals(pushHomePagePojo.getType())) {
          list5.add(activiMap);
        } else if ("6".equals(pushHomePagePojo.getType())) {
          list6.add(activiMap);
        }
      }
      b = true;
      map1.put("type", "1");
      map1.put("arr", list1);
      map2.put("type", "2");
      map2.put("arr", list2);
      map3.put("type", "3");
      map3.put("arr", list3);
      map4.put("type", "4");
      map4.put("arr", list4);
      map5.put("type", "5");
      map5.put("arr", list5);
      map6.put("type", "6");
      map6.put("arr", list6);
      list.add(map2);
      list.add(map5);
      list.add(map3);
      list.add(map1);
      list.add(map4);
      list.add(map6);
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
   * @return 活动产品获取
   * @throws SQLException
   */
  public String getActivityProductByType() throws SQLException {
    String msg = "";
    boolean b = false;
    List<Object> list = new ArrayList<Object>();
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> activitymap = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    if (type == null || "".equals(type)) {
      msg = "活动类型不能为空!";
    } else {
      if (pageNo == null || "".equals(pageNo)) {
        pageNo = 1;
      }
      activitymap.put("pageNo", (pageNo - 1) * 20);
      activitymap.put("pageSize", 20);
      activitymap.put("type", type);
      activitymap.put("status", "1");
      activitymap.put("proStatus", "1");
      List<ActivityProductPojo> activityProductPojoList =
          activityProductService.getActivityProductList(activitymap);
      if (activityProductPojoList == null || activityProductPojoList.size() == 0) {
        msg = "没有查询到活动产品数据！";
      } else {
        Map<String, Object> activiMap = null;
        String url = ConstParam.URL + "/upfiles/product/";
        int count = 0;
        // ActivityGoodsPojo activityGoods = null;
        Double factPrice = 0.0;
        for (ActivityProductPojo activityProductPojo : activityProductPojoList) {
          // 1-每日10件只返回10件
          if ("1".equals(type) && count >= 10) {
            break;
          }
          activiMap = new HashMap<String, Object>();
          activiMap.put("type", activityProductPojo.getType());
          activiMap.put("productId", activityProductPojo.getProductId());
          activiMap.put("image", url + activityProductPojo.getImage());
          activiMap.put("productName", activityProductPojo.getProductName());
          activiMap.put("sorting", activityProductPojo.getSorting());
          activiMap.put("recommendation", StringUtils.isBlank(activityProductPojo
              .getRecommendation()) ? "" : activityProductPojo.getRecommendation());
          // activiMap.put("price",
          // activityProductPojo.getDistributionPrice());
          // 活动价格判断
          factPrice = activityProductPojo.getDistributionPrice();
          /*
           * activityGoods = isProductActivity(activityProductPojo.getProductId()); if
           * (activityGoods != null && activityGoods.getActivePrice() != null &&
           * activityGoods.getActivePrice().doubleValue() != 0) { factPrice =
           * activityGoods.getActivePrice(); }
           */
          activiMap.put("price", df.format(factPrice));
          activiMap.put("sellingPrice", df.format(activityProductPojo.getSellingPrice()));
          activiMap.put("sellNumber",
              activityProductPojo.getSellNumber() + activityProductPojo.getBaseNumber());
          activiMap.put("discount", calcDiscount(factPrice, activityProductPojo.getSellingPrice()));

          list.add(activiMap);
          count++;
        }
        b = true;
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
   * @return 查询登录用户信息
   * @throws SQLException
   */
  public String getUserInfo() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (uid == null || "".equals(uid)) {
      msg = "会员ID不能为空!";
    } else {
      SysLoginPojo sysLoginPojo = sysLoginService.getUserInfoById(uid);
      if (sysLoginPojo == null) {
        msg = "会员ID未查到！";
      } else {
        String url = ConstParam.URL + "/upfiles/userlogo/";
        if (sysLoginPojo.getImage() == null || "".equals(sysLoginPojo.getImage())) {
          url = "";
        } else {
          url = url + sysLoginPojo.getImage();
        }
        result.put("image", url);
        if (sysLoginPojo.getName() == null || "".equals(sysLoginPojo.getName())) {
          result.put("name", "");
        } else {
          result.put("name", sysLoginPojo.getName());
        }
        if (sysLoginPojo.getSex() == null || sysLoginPojo.getSex() == 0) {
          result.put("sex", 1);
        } else {
          result.put("sex", sysLoginPojo.getSex());
        }
        if (sysLoginPojo.getBirthday() == null || "".equals(sysLoginPojo.getBirthday())) {
          result.put("birth", "");
        } else {
          result.put("birth", sysLoginPojo.getBirthday());
        }
        if (sysLoginPojo.getBabySex() == null || "".equals(sysLoginPojo.getBabySex())) {
          result.put("babySex", "");
        } else {
          result.put("babySex", sysLoginPojo.getBabySex());
        }
        if (sysLoginPojo.getBabySexName() == null || "".equals(sysLoginPojo.getBabySexName())) {
          result.put("babySexName", "");
        } else {
          result.put("babySexName", sysLoginPojo.getBabySexName());
        }
        if (sysLoginPojo.getBabyBirthday() == null || "".equals(sysLoginPojo.getBabyBirthday())) {
          result.put("babyBirthday", "");
        } else {
          result.put("babyBirthday", sysLoginPojo.getBabyBirthday());
        }
        if (sysLoginPojo.getQQ() == null || "".equals(sysLoginPojo.getQQ())) {
          result.put("QQ", "");
        } else {
          result.put("QQ", sysLoginPojo.getQQ());
        }
        b = true;
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
   * @return 更新登录用户信息
   * @throws SQLException
   */
  public String editUserInfo() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid == null || "".equals(uid)) {
      msg = "会员ID不能为空!";
    } else {
      SysLoginPojo sysLoginPojo = sysLoginService.getUserInfoById(uid);
      if (sysLoginPojo == null) {
        msg = "会员ID未查到！";
      } else {
        if (file != null) {
          String image = uploadFile(file, "upfiles" + File.separator + "userlogo");
          sysLoginPojo.setImage(image);
          // 上传头像成功
          /*
           * UserScorePojo userScorePojo = userScoreService.findUserScoreByUid(uid); if
           * (userScorePojo != null && userScorePojo.getUpload() != null &&
           * userScorePojo.getUpload() == 0) { UserScorePojo userScorePojo2 = new UserScorePojo();
           * userScorePojo2.setUserId(uid); userScorePojo2.setUpload(1);
           * userScorePojo2.setScore(userScorePojo.getScore() + 5);
           * userScoreService.updateUserScoreByUid(userScorePojo2);
           * 
           * UserScoreLogPojo userScoreLogPojo = new UserScoreLogPojo();
           * userScoreLogPojo.setUserId(uid); userScoreLogPojo.setName(userScorePojo.getName());
           * userScoreLogPojo.setCurScore(userScorePojo.getScore());
           * userScoreLogPojo.setTradeScore((long) 5); userScoreLogPojo.setTradeSource(3);
           * userScoreLogPojo.setRemark("上传头像");
           * userScoreLogService.insertUserScoreLog(userScoreLogPojo); }
           */
        }
        if (name != null && !"".equals(name)) {
          sysLoginPojo.setName(name);
        }
        if (sex != null && sex != 0) {
          sysLoginPojo.setSex(sex);
        }
        if (birth != null && !"".equals(birth)) {
          sysLoginPojo.setBirthday(birth);
        }
        if (babySex != null && !"".equals(babySex)) {
          sysLoginPojo.setBabySex(babySex);
        }
        if (babyBirthday != null && !"".equals(babyBirthday)) {
          sysLoginPojo.setBabyBirthday(babyBirthday);
        }
        if (QQ != null && !"".equals(QQ)) {
          sysLoginPojo.setQQ(QQ);
        }
        // 完善资料成功
        /*
         * UserScorePojo userScorePojo = userScoreService.findUserScoreByUid(uid); if (userScorePojo
         * != null && userScorePojo.getImprove() != null && userScorePojo.getImprove() == 0) {
         * SysLoginPojo sysLoginPojo2 = sysLoginService.getUserInfoById(uid); if (sysLoginPojo2 !=
         * null && sysLoginPojo2.getName() != null && sysLoginPojo2.getSex() != null &&
         * sysLoginPojo2.getBirthday() != null && sysLoginPojo2.getBabySex() != null &&
         * sysLoginPojo2.getBabyBirthday() != null && sysLoginPojo2.getQQ() != null &&
         * !sysLoginPojo2.getName().equals("") && !sysLoginPojo2.getSex().equals("") &&
         * !sysLoginPojo2.getBirthday().equals("") && !sysLoginPojo2.getBabySex().equals("") &&
         * !sysLoginPojo2.getBabyBirthday().equals("") && !"".equals(sysLoginPojo2.getQQ())) {
         * 
         * UserScorePojo userScorePojo2 = new UserScorePojo(); userScorePojo2.setUserId(uid);
         * userScorePojo2.setImprove(1); userScorePojo2.setScore(userScorePojo.getScore() + 5);
         * userScoreService.updateUserScoreByUid(userScorePojo2);
         * 
         * UserScoreLogPojo userScoreLogPojo = new UserScoreLogPojo();
         * userScoreLogPojo.setUserId(uid); userScoreLogPojo.setName(userScorePojo.getName());
         * userScoreLogPojo.setCurScore(userScorePojo.getScore());
         * userScoreLogPojo.setTradeScore((long) 5); userScoreLogPojo.setTradeSource(4);
         * userScoreLogPojo.setRemark("完善个人资料");
         * userScoreLogService.insertUserScoreLog(userScoreLogPojo); } }
         */

        // 融云token
        // 用户id
        /*
         * String userid = String.valueOf(sysLoginPojo.getId()); // 用户名 String username =
         * sysLoginPojo.getName(); // 头像 String logo = ""; // token if (sysLoginPojo.getImage() !=
         * null) { logo = ConstParam.URL + "/upfiles/userlogo/" + sysLoginPojo.getImage(); } String
         * token = sysLoginService.getRongyunToken(userid, username, logo);
         * sysLoginPojo.setToken(token);
         */
        sysLoginService.updateUserInfoById(sysLoginPojo);
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
   * @return 批发商申请
   * @throws SQLException
   */
  public String agencyApply() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    if (contact == null || "".equals(contact)) {
      msg = "联系人不能为空!";
    } else if (province == null || province == 0) {
      msg = "请选择省！";
    } else if (city == null || city == 0) {
      msg = "请选择市！";
    } else if (area == null || area == 0) {
      msg = "请选择区！";
    } /*
       * else if (address == null || "".equals(address)) { msg = "请输入完整地址！"; }
       */else if (mainProduct == null || "".equals(mainProduct)) {
      msg = "请输入主营产品！";
    } else {
      if (id == null || id == 0) {
        agencyApplyPojo = new AgencyApplyPojo();
        agencyApplyPojo.setContact(contact);
        agencyApplyPojo.setTel(tel);
        agencyApplyPojo.setPhone(phone);
        agencyApplyPojo.setFax(fax);
        agencyApplyPojo.setQQ(QQ);
        agencyApplyPojo.setProvinceId(province);
        agencyApplyPojo.setCityId(city);
        agencyApplyPojo.setAreaId(area);
        agencyApplyPojo.setAddress(address);
        agencyApplyPojo.setMainProduct(mainProduct);
        agencyApplyPojo.setStatus(0);
        agencyApplyService.insertAgencyApply(agencyApplyPojo);
        b = true;
        msg = "申请成功！";
      } else {
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("id", id);
        agencyApplyPojo = agencyApplyService.getAgencyApplyById(map1);
        if (agencyApplyPojo == null) {
          msg = "未找到id信息！";
        } else {
          agencyApplyPojo.setContact(contact);
          if (tel != null && !"".equals(tel)) {
            agencyApplyPojo.setTel(tel);
          }
          if (phone != null && !"".equals(phone)) {
            agencyApplyPojo.setPhone(phone);
          }
          if (fax != null && !"".equals(fax)) {
            agencyApplyPojo.setFax(fax);
          }
          if (QQ != null && !"".equals(QQ)) {
            agencyApplyPojo.setQQ(QQ);
          }
          if (mainProduct != null && !"".equals(mainProduct)) {
            agencyApplyPojo.setMainProduct(mainProduct);
          }
          if (address != null && !"".equals(address)) {
            agencyApplyPojo.setAddress(address);
          }

          agencyApplyPojo.setProvinceId(province);
          agencyApplyPojo.setCityId(city);
          agencyApplyPojo.setAreaId(area);
          agencyApplyPojo.setAddress(address);
          agencyApplyService.updateAgencyApply(agencyApplyPojo);
          b = true;
          msg = "更新成功！";
        }
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
   * 地区列表
   * 
   * @throws SQLException
   * */
  public String getAreaInfo() throws SQLException {
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = null;
    Map<String, Object> map2 = null;
    Map<String, Object> map3 = null;
    List provinclt = new ArrayList();
    List citylt = null;
    List arealt = null;
    SysAreaPojo sysAreaPojo = new SysAreaPojo();
    sysAreaPojo.setPid(0L);
    List<SysAreaPojo> provs = sysAreaService.getSysAreaByPid(sysAreaPojo);
    List<SysAreaPojo> citys = null;
    List<SysAreaPojo> areas = null;
    if (provs != null && provs.size() > 0) {
      for (SysAreaPojo prov : provs) {
        map1 = new HashMap<String, Object>();
        map1.put("provincState", prov.getName());
        map1.put("provinceId", prov.getId());
        sysAreaPojo.setPid(prov.getId());
        citys = sysAreaService.getSysAreaByPid(sysAreaPojo);
        citylt = new ArrayList();
        if (citys != null && citys.size() > 0) {
          for (SysAreaPojo city : citys) {
            map2 = new HashMap<String, Object>();
            map2.put("cityState", city.getName());
            map2.put("cityId", city.getId());
            sysAreaPojo.setPid(city.getId());
            areas = sysAreaService.getSysAreaByPid(sysAreaPojo);
            arealt = new ArrayList();
            if (areas != null && areas.size() > 0) {
              for (SysAreaPojo area : areas) {
                map3 = new HashMap<String, Object>();
                map3.put("areaState", area.getName());
                map3.put("areaId", area.getId());
                arealt.add(map3);
              }
            }
            map2.put("areas", arealt);
            citylt.add(map2);
          }
        }
        map1.put("cities", citylt);
        provinclt.add(map1);
      }
    } else {
      msg = "无区域数据";
    }

    map.put("result", provinclt);
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

  /**
   * 根据产品id判断当前是否作活动中(库存>0)
   * 
   * @param productId
   * @return
   * @throws SQLException
   */
  public ActivityGoodsPojo isProductActivity(Long productId, Long activityId, Integer stockStatus)
      throws SQLException {
    ActivityGoodsPojo activityGoodsPojo =
        activityGoodsService.findActivityGoodsByPid(productId, activityId, stockStatus, 1);
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
      String url = ConstParam.URL + "/upfiles/activity/";
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
        String url = ConstParam.URL + "/upfiles/product/";
        String url2 = ConstParam.URL + "/upfiles/activity/";
        if (goods != null && goods.size() > 0) {
          int flag = 0;
          map1.put("activityDate", StringUtil.dateToString(activityTime.getActivityDate()));
          map1.put("beginTime", activityTime.getBeginTime());
          map1.put("endTime", activityTime.getEndTime());
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
   * 
   * 限时秒杀产品列表库存返回
   * 
   * @throws SQLException
   * */
  public String activityGoodsStock() throws SQLException {
    boolean b = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = null;
    List list1 = new ArrayList();
    List<ActivityGoodsPojo> goods = null;
    int[] stock = null;
    if (timeId == null) {
      msg = "timeId不能为空";
    } else {
      Map<String, Object> param = new HashMap<String, Object>();
      param.put("status", 1);
      param.put("timeId", timeId);
      param.put("proStatus", 1);
      goods = activityGoodsService.findActivityGoodsList(param);

      if (goods != null && goods.size() > 0) {
        for (ActivityGoodsPojo good : goods) {
          map1 = new HashMap<String, Object>();
          // 判断是否有活动sku
          stock = getActivitySkuStock(timeId, good.getProductId());
          map1.put("productId", good.getProductId());
          if (stock != null) {
            map1.put("activityStock", stock[1]);
          } else {
            map1.put("activityStock", good.getActivityStock() == null ? 0 : good.getActivityStock());
          }
          list1.add(map1);
        }
        b = true;
      } else {
        msg = "无活动产品数据！";
      }

    }

    map.put("result", list1);
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
   * @return 首页列表活动产品获取
   * @throws SQLException
   */
  public String getFocusActivityProduct() throws SQLException {
    String msg = "";
    boolean b = false;
    List<Object> list = new ArrayList<Object>();
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    if (id == null || id == 0) {
      msg = "活动编号id不能为空!";
    } else {
      ActivityTitlePojo activity = activityTitleService.findActivityTitleById(id);

      if (activity != null && activity.getStatus() != 0) {
        if (pageNo == null || "".equals(pageNo)) {
          pageNo = 1;
        }

        result.put("id", activity.getId());
        result.put("title", activity.getTitle());
        result.put("banner", activity.getBanner() == null ? "" : ConstParam.URL
            + "/upfiles/notice/" + activity.getBanner());

        Map<String, Object> activitymap = new HashMap<String, Object>();
        activitymap.put("pageNo", (pageNo - 1) * 20);
        activitymap.put("pageSize", 20);
        // 4-默认首页列表活动类型
        activitymap.put("type", 4);
        activitymap.put("status", "1");
        // 借用Product_activity中remark字段存储活动对应id
        activitymap.put("remarks", String.valueOf(id));
        activitymap.put("sorting", sorting);
        List<ActivityProductPojo> activityProductPojoList =
            activityProductService.getActivityProductList(activitymap);
        if (activityProductPojoList == null || activityProductPojoList.size() == 0) {
          msg = "没有查询到活动产品数据！";
        } else {
          Map<String, Object> activiMap = null;
          String url = ConstParam.URL + "/upfiles/product/";
          // ActivityGoodsPojo activityGoods = null;
          for (ActivityProductPojo activityProductPojo : activityProductPojoList) {
            activiMap = new HashMap<String, Object>();
            activiMap.put("type", activityProductPojo.getType());
            activiMap.put("productId", activityProductPojo.getProductId());
            activiMap.put("image", url + activityProductPojo.getImage());
            activiMap.put("productName", activityProductPojo.getProductName());
            activiMap.put("sorting", activityProductPojo.getSorting() == null ? ""
                : activityProductPojo.getSorting());
            // activiMap.put("price",
            // activityProductPojo.getDistributionPrice());
            // 活动价格判断
            /*
             * activityGoods = isProductActivity(activityProductPojo.getProductId()); if
             * (activityGoods != null && activityGoods.getActivePrice() != null &&
             * activityGoods.getActivePrice().doubleValue() != 0) { activiMap.put("price",
             * df.format(activityGoods.getActivePrice())); } else { activiMap.put("price",
             * df.format(activityProductPojo.getDistributionPrice())); }
             */
            activiMap.put("price", df.format(activityProductPojo.getDistributionPrice()));
            activiMap.put("sellingPrice", df.format(activityProductPojo.getSellingPrice()));
            activiMap.put("sellNumber",
                activityProductPojo.getSellNumber() + activityProductPojo.getBaseNumber());
            list.add(activiMap);
          }
        }
        b = true;
        result.put("products", list);
      } else {
        msg = "该活动已过期！";
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
   * @return 微信支付
   * @throws Exception
   */
  public String wexinPay() throws Exception {
    String error_msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> sParaTemp = new HashMap<String, Object>();
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
    String body = "玩具总动员";
    // attach 支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回
    // outTradeNo 商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
    // totalFee 订单总金额，单位为“分”，只能整数
    int totalFee = new Double(price * 100l).intValue();
    // deviceInfo 商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
    // spBillCreateIP 订单生成的机器IP
    String spBillCreateIP = "";
    // goodsTag 商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
    String notifyUrl = PropertiesHelper.getValue("wx_notify_url");
    String key = PropertiesHelper.getValue("wx_key");
    WXPay.initSDKConfiguration(key, appID, mchID, sdbMchID, certLocalPath, certPassword);
    PrePayReqData prePayReqData =
        new PrePayReqData(null, "WEB", null, name, body, outTradeNo, totalFee, notifyUrl,
            spBillCreateIP, "APP", "APP");
    prePayReqData.setOtherNull();
    long costTimeStart = System.currentTimeMillis();
    String rsp = WXPay.requestPrePayService(prePayReqData);
    System.out.println(rsp);
    long costTimeEnd = System.currentTimeMillis();
    long totalTimeCost = costTimeEnd - costTimeStart;
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
      b = true;
    }
    map.put("result", sParaTemp);
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
   * @return 支付宝支付
   * @throws Exception
   */
  public String aliPay() throws Exception {
    String error_msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, String> alipayMap = new HashMap<String, String>();
    // 组装支付宝支付串
    String subject = "拼得好-订单号：" + outTradeNo;
    String show_url = "http://www.alipay.com/";
    String body = "玩具总动员";
    alipayMap = buildAlipayReq(outTradeNo, subject, "1", body, show_url);
    b = true;
    map.put("result", alipayMap);
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
    int loginPojo = sysLoginService.getfindCountByIdSysLogin(uid);
    if (uid == null) {
      error_msg = "uid不能为空";
    } else if (loginPojo == 0) {
      error_msg = "没有该会员";
    } else if (orderNo == null || "".equals(orderNo)) {
      error_msg = "订单号不能为空";
    } else if (payMethod != 1 && payMethod != 2) {
      error_msg = "支付方式错误";
    } else {
      OrderPojo order = orderService.findOrderByOrderNo(orderNo);

      String subject = "";
      String body = "";
      DecimalFormat df = new DecimalFormat("#.##");
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
                buildAlipayReq(alipayOrderInfoPojo.getOutTradeNo(), subject,
                    alipayOrderInfoPojo.getTotalFee(), body, show_url);

          } else if (2 == payMethod) {
            // 生成未付款微信支付信息
            WxpayOrderInfoPojo wxpay = new WxpayOrderInfoPojo();
            wxpay.setOutTradeNo(out_trade_no);
            // 单位分
            wxpay.setTotalFee(doubleToFee(price));
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
                buildWxpayReq(wxpay.getOutTradeNo(), null, body, subject,
                    Integer.valueOf(wxpay.getTotalFee()));
            wxpayMap.put("out_trade_no", out_trade_no);
          }
          b = true;
        }
      } else {
        error_msg = "订单号未找到或已付款";
      }
    }

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
   * 单个产品活动时间
   * 
   * @throws SQLException
   * */
  public String getProductActivityTime() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    if (productId == null) {
      msg = "产品id不能为空！";
    } else {
      /*
       * if (activityId == null) { activityId = 0L; } ActivityGoodsPojo activityGoodsPojo =
       * isProductActivity(productId, skuLinkId, activityId);
       */
      ActivityGoodsPojo activityGoodsPojo =
          activityGoodsService.getActivityGoodsTimeByPid(productId);
      if (activityGoodsPojo != null) {
        map1.put("beginTime", activityGoodsPojo.getBeginTime());
        map1.put("endTime", activityGoodsPojo.getEndTime());
        map1.put("activityDate", StringUtil.dateToString(activityGoodsPojo.getActivityDate()));
        b = true;
      } else {
        msg = "产品未参加活动或结束！";
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
   * 
   * 用户优惠券
   * 
   * @throws SQLException
   * */
  public String getUserCouponList() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();

    List coupons = new ArrayList();

    if (uid == null || uid == 0) {
      msg = "用户id不能为空！";
    } else {
      Map<String, Object> param = new HashMap<String, Object>();
      param.put("userId", uid);
      param.put("status", 1);
      if (pageNo == null || pageNo < 1) {
        pageNo = 1;
      }
      param.put("pageNo", (pageNo - 1) * 10);
      param.put("pageSize", 10);
      List<UserCouponPojo> userCoupons = couponService.getuserCouponList(param);
      if (userCoupons != null && userCoupons.size() > 0) {
        Map<String, Object> couponmap = null;
        for (UserCouponPojo userCouponPojo : userCoupons) {
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
          if (userCouponPojo.getValidetime() != null
              && new Date().getTime() > userCouponPojo.getValidetime() * 1000) {
            couponmap.put("overdue", "1");
          } else {
            couponmap.put("overdue", "0");
          }
          if (userCouponPojo.getType() != null && 1 == userCouponPojo.getType()) {
            String str = userCouponPojo.getContent();
            org.json.JSONObject json = new org.json.JSONObject(str);
            couponmap.put("om", json.get("om"));
            couponmap.put("m", json.get("m"));
          } else {
            couponmap.put("om", "");
            couponmap.put("m", "");
          }
          couponmap.put("source",
              userCouponPojo.getSource() == null ? 0 : userCouponPojo.getSource());
          coupons.add(couponmap);
        }
        b = true;
      } else {
        msg = "未找到优惠券！";
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
   * 订单可用优惠券列表
   * 
   * @throws SQLException
   * */
  public String getValidUserCoupon() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();

    List coupons = new ArrayList();

    if (uid == null) {
      msg = "用户id不能为空！";
    } else if (price == null || price == 0) {
      msg = "订单金额不能为空！";
    } else {
      Map<String, Object> param = new HashMap<String, Object>();
      param.put("userId", uid);
      param.put("status", 1);
      param.put("used", 0);
      List<UserCouponPojo> userCoupons = couponService.getuserCouponList(param);
      if (userCoupons != null && userCoupons.size() > 0) {
        Map<String, Object> couponmap = null;
        boolean canUse = false;
        long currentTime = new Date().getTime() / 1000;
        for (UserCouponPojo userCouponPojo : userCoupons) {
          // 如果不是当前时间有效的优惠券，不能使用
          if (!(userCouponPojo.getValidetime() != null && userCouponPojo.getValidstime() != null
              && currentTime >= userCouponPojo.getValidstime() && currentTime <= userCouponPojo
              .getValidetime())) {
            continue;
          }
          // 判断订单金额能否使用优惠券
          canUse = checkCouponUsePrice(userCouponPojo.getCouponId(), price);
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
          // 满om减m时返回金额
          if (userCouponPojo.getType() != null && 1 == userCouponPojo.getType()) {
            String str = userCouponPojo.getContent();
            org.json.JSONObject json = new org.json.JSONObject(str);
            couponmap.put("om", json.get("om"));
            couponmap.put("m", json.get("m"));
          } else {
            couponmap.put("om", "");
            couponmap.put("m", "");
          }
          couponmap
              .put("source", userCouponPojo.getSource() == 0 ? "" : userCouponPojo.getSource());
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
   * 根据订单金额判断能否使用优惠券
   * 
   * @param id 优惠券id
   * @param price 订单金额
   * @return
   * @throws Exception
   */
  private boolean checkCouponUsePrice(Long id, Double price) throws Exception {
    boolean flag = false;

    // 查询优惠券金额
    CouponPojo coupon = couponService.getcouponById(id);
    if (coupon != null) {
      int type = coupon.getType();
      String contentStr = coupon.getContent();
      double m = 0.0;
      org.json.JSONObject json = new org.json.JSONObject(contentStr);
      if (1 == type) {
        // 满减
        m = json.getDouble("om");
        if (price.doubleValue() >= m) {
          flag = true;
        }

      } else if (2 == type) {
        // 直减
        m = json.getDouble("m");
        if (price.doubleValue() >= m) {
          flag = true;
        }
      }
    }

    return flag;
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
   * 更新产品销售基数
   * 
   * @throws SQLException
   * */
  public String addBaseNumber() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    productService.updateProductBaseNumber();
    b = true;
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
   * 获取用户购物车数量
   * 
   * @return
   * @throws Exception
   */
  public String getUserCartNum() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    if (uid != null) {
      b = true;
      int n = cartService.findCartByUserIdCount(uid);
      map1.put("num", n == 0 ? "" : n);
    } else {
      msg = "会员ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
      map1.put("num", "");
    }
    map.put("result", map1);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      b = false;
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 获取商品SKU信息
   * 
   * @return
   * @throws Exception
   */
  public String getProductSkuLink() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> fmlist = new ArrayList<Map<String, Object>>();
    DecimalFormat df = new DecimalFormat("#.##");
    // sku类型，0-普通;1-活动
    int type = 0;
    if (pid == null || pid.equals("")) {
      msg = "产品ID是必须要填写哦";
    } else {
      if (activityId == null) {
        activityId = 0L;
      }
      if (activityId > 0) {
        type = 1;
      }
      productSkuLinkPojo = new ProductSkuLinkPojo();
      productSkuLinkPojo.setProductId(pid);
      productSkuLinkPojo.setStatus(1);
      productSkuLinkPojo.setType(type);
      productSkuLinkPojo.setActivityId(activityId);
      // 返回库存不为0的sku
      productSkuLinkPojo.setStock(1);
      List<ProductSkuLinkPojo> list =
          productSkuLinkService.getProductSkuLinkAll(productSkuLinkPojo, null);

      // 查询产品原价
      ProductPojo product = new ProductPojo();
      product.setId(pid);
      product = productService.findProduct(product);
      Double sellprice = 0.0;
      if (product != null) {
        sellprice = product.getSellingPrice();
      }
      Map<String, Object> format = null;
      if (list != null && list.size() > 0) {
        for (ProductSkuLinkPojo sku : list) {
          format = new HashMap<String, Object>();
          format.put("pid", pid);
          format.put("skuColorId", sku.getSkuColorId());
          format.put("colorValue", sku.getColorValue());
          format.put("skuFormatId", sku.getSkuFormatId());
          format.put("formatValue", sku.getFormatValue());
          format.put("price", sku.getPrice() == null ? "" : df.format(sku.getPrice()));
          format.put("discount", calcDiscount(sku.getPrice(), sellprice) + "折");
          format.put("status", 1);
          format.put("id", sku.getId());
          fmlist.add(format);
        }
      }
      b = true;
    }
    map.put("result", fmlist);
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
   * 获取产品颜色/规格
   * 
   * @return
   * @throws Exception
   */
  public String getProductSkuList() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> skuList = new ArrayList<Map<String, Object>>();
    Map<String, Object> skuColorMap = null;
    Map<String, Object> skuFormatMap = null;
    // sku类型，0-普通;1-活动
    int type = 0;

    if (pid == null || pid.equals("")) {
      msg = "产品ID是必须要填写哦";
    } else {
      if (activityId == null) {
        activityId = 0L;
      }
      if (activityId > 0) {
        type = 1;
      }

      productSkuLinkPojo = new ProductSkuLinkPojo();
      productSkuLinkPojo.setProductId(pid);
      productSkuLinkPojo.setStatus(1);
      productSkuLinkPojo.setType(type);
      productSkuLinkPojo.setActivityId(activityId);
      List<ProductSkuLinkPojo> list =
          productSkuLinkService.getProductSkuLinkAll(productSkuLinkPojo, null);
      Map<String, Object> format = null;
      List<Long> colorCuror = new ArrayList<Long>();
      List<Long> formatCuror = new ArrayList<Long>();
      if (list != null && list.size() > 0) {
        List<Map<String, Object>> collist = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> fmlist = new ArrayList<Map<String, Object>>();
        for (ProductSkuLinkPojo skuCol : list) {
          if (!colorCuror.contains(skuCol.getSkuColorId())) {
            format = new HashMap<String, Object>();
            format.put("optionId", skuCol.getSkuColorId());
            format.put("optionValue", skuCol.getColorValue());
            collist.add(format);
          }
          if (!formatCuror.contains(skuCol.getSkuFormatId())) {
            format = new HashMap<String, Object>();
            format.put("optionId", skuCol.getSkuFormatId());
            format.put("optionValue", skuCol.getFormatValue());
            fmlist.add(format);
          }

          formatCuror.add(skuCol.getSkuFormatId());
          colorCuror.add(skuCol.getSkuColorId());
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

      }
      b = true;
    }
    map.put("result", skuList);
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
   * 微信摇一摇活动
   * 
   * @return
   * @throws SQLException
   * @throws ParseException
   */
  public String shakeActivity() throws SQLException, ParseException {
    String msg = "";
    boolean b = false;
    DecimalFormat df = new DecimalFormat("#.##");
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    List<ProductPojo> list = new ArrayList<ProductPojo>();
    List<ProductPojo> list2 = new ArrayList<ProductPojo>();
    UserScorePojo userScorePojo = new UserScorePojo();
    UserScorePojo userScorePojo2 = new UserScorePojo();
    Random r = new Random();
    if (uid != null) {
      userScorePojo = userScoreService.findUserScoreByUid(uid);
      if (userScorePojo != null) {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (sdf.parse(sdf.format(dt)).getTime() > userScorePojo.getShakeDate().getTime()) {
          userScorePojo2.setUserId(uid);
          userScorePojo2.setShakeDateStr(sdf.format(dt));
          userScorePojo2.setShakeNum(3);
          userScoreService.updateUserScoreByUid(userScorePojo2);

          userScorePojo = userScoreService.findUserScoreByUid(uid);
        }
        if (sdf.parse(sdf.format(dt)).getTime() == userScorePojo.getShakeDate().getTime()
            && userScorePojo.getShakeNum() > 0) {
          Map<String, Object> option = new HashMap<String, Object>();
          option.put("userId", uid);
          option.put("createDate", sdf.format(dt));
          option.put("tradeSource", 1);
          int count = userScoreLogService.findUserScoreLogCount(option);
          if (count == 0) {
            result.put("type", 1);// Type1
            int score = r.nextInt(5) + 1;
            result.put("score", score);
            result.put("productId", "");
            result.put("productName", "");
            result.put("price", "");
            result.put("sellingprice", "");
            result.put("discount", "");
            result.put("productImage", "");

            userScorePojo2 = new UserScorePojo();
            userScorePojo2.setUserId(uid);
            userScorePojo2.setScore(userScorePojo.getScore() + score);// Score++
            userScorePojo2.setShakeNum(userScorePojo.getShakeNum() - 1);// ShakeNum-1
            userScoreService.updateUserScoreByUid(userScorePojo2);
            // +ScoreLog
            UserScoreLogPojo userScoreLogPojo = new UserScoreLogPojo();
            userScoreLogPojo.setUserId(uid);
            userScoreLogPojo.setName(userScorePojo.getName());
            userScoreLogPojo.setCurScore(userScorePojo.getScore());;
            userScoreLogPojo.setTradeScore((long) score);
            userScoreLogPojo.setTradeSource(1);
            userScoreLogPojo.setRemark("摇一摇");
            userScoreLogService.insertUserScoreLog(userScoreLogPojo);
            b = true;
          } else {
            ProductPojo productPojo = new ProductPojo();
            productPojo.setStatus(1);
            list = productService.getProductAll(productPojo, null);
            if (list.size() > 0) {
              for (ProductPojo p : list) {
                if (p.getSellNumber() > 0) {
                  list2.add(p);
                }
              }
            } else {
              // 没有产品

            }
            if (list2.size() == 0) {
              // 没有热销产品
              list2 = list;
            }
            if (list2.size() > 0) {
              productPojo = new ProductPojo();
              productPojo = list2.get(r.nextInt(list2.size()));

              result.put("type", 2);// Type2
              result.put("score", "");
              result.put("productId", productPojo.getId());
              result.put("productName", productPojo.getProductName());
              result.put("price", df.format(productPojo.getDistributionPrice()));
              result.put("sellingprice", df.format(productPojo.getSellingPrice()));
              result.put("discount",
                  calcDiscount(productPojo.getDistributionPrice(), productPojo.getSellingPrice()));
              result.put("productImage",
                  ConstParam.URL + "/upfiles/product/small/" + productPojo.getImage());

              userScorePojo2 = new UserScorePojo();
              userScorePojo2.setUserId(uid);
              userScorePojo2.setShakeNum(userScorePojo.getShakeNum() - 1);// ShakeNum-1
              userScoreService.updateUserScoreByUid(userScorePojo2);
              b = true;
            }
          }
        } else {
          msg = "机会用光";
        }
      } else {
        // 用户积分信息必须添加

      }
    } else {
      msg = "会员ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    }
    map.put("result", result);
    map.put("msg", msg);
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
   * 用户积分任务列表
   * 
   * @return
   * @throws SQLException
   */
  public String userScoreTasksList() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    HashMap<String, Object> tack = new HashMap<String, Object>();
    List<HashMap<String, Object>> tacks = new ArrayList<HashMap<String, Object>>();
    UserScorePojo userScorePojo = new UserScorePojo();
    if (uid != null) {
      userScorePojo = userScoreService.findUserScoreByUid(uid);
      if (userScorePojo != null) {
        result.put("userScore", userScorePojo.getScore());

        tack.put("type", 1);
        tack.put("name", "摇一摇");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (userScorePojo.getShakeDateStr().equals(sdf.format(new Date()))) {
          tack.put("num", userScorePojo.getShakeNum());
        } else {
          tack.put("num", 3);
        }
        tack.put("score", userScorePojo.getScore());
        tacks.add(tack);
        if (userScorePojo.getBunding().equals(0)) {
          tack = new HashMap<String, Object>();
          tack.put("type", 2);
          tack.put("name", "绑定微信号");
          tack.put("num", 1);
          tack.put("score", 5);
          tacks.add(tack);
        }
        if (userScorePojo.getUpload().equals(0)) {
          tack = new HashMap<String, Object>();
          tack.put("type", 3);
          tack.put("name", "上传头像");
          tack.put("num", 1);
          tack.put("score", 5);
          tacks.add(tack);
        }
        if (userScorePojo.getImprove().equals(0)) {
          tack = new HashMap<String, Object>();
          tack.put("type", 4);
          tack.put("name", "完善个人资料");
          tack.put("num", 1);
          tack.put("score", 5);
          tacks.add(tack);
        }
        result.put("tasks", tacks);
        b = true;
      } else {

      }
    } else {
      msg = "会员ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    }
    map.put("result", result);
    map.put("msg", msg);
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
   * 用户积分明细查询
   * 
   * @return
   * @throws SQLException
   */
  public String userScoreDetailed() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    // Map<String, Object> result = new HashMap<String, Object>();
    HashMap<String, Object> detail = new HashMap<String, Object>();
    List<HashMap<String, Object>> details = new ArrayList<HashMap<String, Object>>();
    List<UserScoreLogPojo> userScoreLogPojo = new ArrayList<UserScoreLogPojo>();
    if (uid != null) {
      if (pageNo == null || pageNo.equals("")) {
        page = new Pager();
        page.setPageNo(1);
      } else {
        page = new Pager();
        page.setPageNo(pageNo);
      }

      Map<String, Object> option = new HashMap<String, Object>();
      option.put("userId", uid);
      option.put("pageSize", 10);
      option.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      userScoreLogPojo = userScoreLogService.findUserScoreLogList(option);

      if (userScoreLogPojo.size() != 0) {
        for (UserScoreLogPojo u : userScoreLogPojo) {
          detail = new HashMap<String, Object>();
          detail.put("name", u.getName());
          detail.put("createDate", u.getCreateDateStr());
          detail.put("lastScore", u.getCurScore());
          detail.put("tradeScore", u.getTradeScore());
          detail.put("tradeSource", u.getTradeSource());
          details.add(detail);
        }
        b = true;
      } else {

      }
    } else {
      msg = "会员ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    }
    map.put("result", details);
    map.put("msg", msg);
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
   * 场景/套餐活动查询
   * 
   * @return
   * @throws SQLException
   * @throws ParseException
   */
  public String scenesList() throws SQLException, ParseException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List results = new ArrayList();
    Map<String, Object> scene = null;
    Map<String, Object> detail = null;
    List details = null;
    List<ScenePojo> scenePojo = new ArrayList<ScenePojo>();
    List<ScenePojo> scenePojo2 = new ArrayList<ScenePojo>();

    Map<String, Object> option = new HashMap<String, Object>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    option.put("type", 1);
    option.put("status", 1);
    // 未删除的
    option.put("isdelete", "0");
    option.put("currentTimeStr", sdf.format(new Date()));
    scenePojo = sceneService.findSceneList(option);

    option.put("type", 2);
    scenePojo2 = sceneService.findSceneList(option);
    String url = ConstParam.URL + "/upfiles/scene/";
    if (scenePojo.size() != 0 && scenePojo2.size() != 0) {
      details = new ArrayList();
      scene = new HashMap<String, Object>();
      for (ScenePojo u : scenePojo) {
        detail = new HashMap<String, Object>();
        detail.put("sceneId", u.getId() == null ? "" : u.getId());
        detail.put("name", u.getName());
        detail.put("image", url + u.getImage());
        // detail.put("introduction", u.getIntroduction());
        // detail.put("beginTime", u.getBeginTime());
        // detail.put("beginTimeStr", u.getBeginTimeStr());
        // detail.put("endTime", u.getEndTime());
        // detail.put("endTimeStr", u.getEndTimeStr());
        // detail.put("sorting", u.getSorting());
        // detail.put("status", u.getStatus());
        // detail.put("preview", u.getPreview());
        // detail.put("type", u.getType());
        details.add(detail);
      }

      scene.put("type", 1);
      scene.put("scenes", details);
      results.add(scene);

      details = new ArrayList();
      scene = new HashMap<String, Object>();
      url = ConstParam.URL + "/upfiles/homePackage/";
      for (ScenePojo u : scenePojo2) {
        detail = new HashMap<String, Object>();
        detail.put("sceneId", u.getId() == null ? "" : u.getId());
        detail.put("name", u.getName());
        detail.put("image", url + u.getImage());
        // detail.put("introduction", u.getIntroduction());
        // detail.put("beginTime", u.getBeginTime());
        // detail.put("beginTimeStr", u.getBeginTimeStr());
        // detail.put("endTime", u.getEndTime());
        // detail.put("endTimeStr", u.getEndTimeStr());
        // detail.put("sorting", u.getSorting());
        // detail.put("status", u.getStatus());
        // detail.put("preview", u.getPreview());
        // detail.put("type", u.getType());
        // detail.put("packagePrice", u.getPackagePrice());
        // detail.put("psellPrice", u.getpSellPrice());
        details.add(detail);
      }
      scene.put("type", 2);
      scene.put("scenes", details);
      results.add(scene);
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
   * 套餐活动产品查询
   * 
   * @return
   * @throws SQLException
   * @throws ParseException
   */
  public String packageProductDetailed() throws SQLException, ParseException {
    String msg = "";
    boolean b = false;
    DecimalFormat df = new DecimalFormat("#.##");
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    HashMap<String, Object> detail = new HashMap<String, Object>();
    List<HashMap<String, Object>> details = new ArrayList<HashMap<String, Object>>();
    List<SceneProductPojo> sceneProductPojo = new ArrayList<SceneProductPojo>();
    if (id != null) {
      // if (pageNo == null || pageNo.equals("")) {
      // page = new Pager();
      // page.setPageNo(1);
      // } else {
      // page = new Pager();
      // page.setPageNo(pageNo);
      // }

      Map<String, Object> option = new HashMap<String, Object>();
      option.put("id", id);
      option.put("type", 2);
      option.put("status", 1);
      // 未删除的
      option.put("isdelete", "0");
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      option.put("currentTimeStr", sdf.format(new Date()));
      List<ScenePojo> scenePojo = new ArrayList<ScenePojo>();
      scenePojo = sceneService.findSceneList(option);
      if (scenePojo.size() != 0) {
        result.put("introduction", scenePojo.get(0).getIntroduction());
        result.put("image", scenePojo.get(0).getImage());
        result.put("packagePrice", df.format(scenePojo.get(0).getPackagePrice()));
        result.put("psellPrice", df.format(scenePojo.get(0).getPsellPrice()));

        option = new HashMap<String, Object>();
        // option.put("userId", uid);
        // option.put("pageSize", 10);
        // option.put("pageNo", (page.getPageNo() - 1) *
        // page.getPageSize());
        option.put("sceneId", id);
        // option.put("status", 1);
        sceneProductPojo = sceneProductService.findSceneProductList(option);

        if (sceneProductPojo.size() != 0) {
          for (SceneProductPojo u : sceneProductPojo) {
            detail = new HashMap<String, Object>();
            // detail.put("id", u.getId());
            // detail.put("title", u.getTitle());
            detail.put("image", u.getImage());
            // detail.put("introduction", u.getIntroduction());
            detail.put("productId", u.getProductId());
            // detail.put("productName", u.getProductName());
            // detail.put("sellPrice", u.getSellPrice());
            // detail.put("scenePrice", u.getScenePrice());
            // detail.put("sceneNum", u.getSceneNum());
            // detail.put("sceneStock", u.getSceneStock());
            // detail.put("skuLinkId", u.getSkuLinkId());
            // detail.put("sorting", u.getSorting());
            // detail.put("status", u.getStatus());
            details.add(detail);
          }
          result.put("details", details);
          b = true;
        } else {

        }
      } else {

      }
    } else {
      msg = "套餐ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    }
    map.put("result", result);
    map.put("msg", msg);
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
   * 场景产品明细查询
   * 
   * @return
   * @throws SQLException
   */
  public String sceneProductDetailed() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    HashMap<String, Object> detail = new HashMap<String, Object>();
    List<HashMap<String, Object>> details = new ArrayList<HashMap<String, Object>>();
    if (pageNo == null || pageNo.equals("")) {
      page = new Pager();
      page.setPageNo(1);
    } else {
      page = new Pager();
      page.setPageNo(pageNo);
    }
    Date dt = new Date();
    SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    List<ScenePojo> scenePojos = new ArrayList<ScenePojo>();
    Map<String, Object> options = new HashMap<String, Object>();
    options.put("id", id);
    options.put("type", 1);
    options.put("status", 1);
    // 未删除的
    options.put("isdelete", "0");
    options.put("currentTimeStr", matter1.format(dt));
    scenePojos = sceneService.findSceneList(options);
    if (scenePojos.size() != 0) {
      map1.put("sceneName", scenePojos.get(0).getName());
      map1.put("sceneImage", scenePojos.get(0).getImage());
      map1.put("sceneItroduction", scenePojos.get(0).getIntroduction());
      List<SceneProductPojo> sceneProductPojos = new ArrayList<SceneProductPojo>();
      Map<String, Object> option = new HashMap<String, Object>();
      option.put("sceneId", id);
      option.put("status", 1);
      option.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      sceneProductPojos = sceneProductService.findSceneProductList(option);

      if (sceneProductPojos.size() != 0) {
        for (SceneProductPojo u : sceneProductPojos) {
          detail = new HashMap<String, Object>();
          // detail.put("id", u.getId());
          detail.put("title", u.getTitle());
          detail.put("ProductImage", u.getImage());
          detail.put("introduction", u.getIntroduction());
          detail.put("productId", u.getProductId());
          detail.put("productName", u.getProductName());
          // detail.put("sellPrice", u.getSellPrice());
          // detail.put("scenePrice", u.getScenePrice());
          details.add(detail);
        }
        map1.put("details", details);
        b = true;
      } else {
        msg = "该活动产品已下架，下次早点来哦，么么哒！";
      }
    } else {
      msg = "该活动已结束，敬请关注下次活动，么么哒！";
    }
    map.put("result", map1);
    map.put("msg", msg);
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
   * 查询用户最近浏览的4只产品
   * 
   * @return
   * @throws SQLException
   */
  public String getUserFootByProduct() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = null;
    List<Object> list = new ArrayList<Object>();

    // if (pageNo == null || pageNo.equals("")) {
    // page = new Pager();
    // page.setPageNo(1);
    // } else {
    // page = new Pager();
    // page.setPageNo(pageNo);
    // }
    // page.setPageSize(10);

    if (uid == null) {
      msg = "用户ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      if (historyPojo == null) {
        historyPojo = new HistoryPojo();
      }
      historyPojo.setUserId(uid);

      if (page == null) {
        page = new Pager();
      }
      page.setPageNo(1);
      page.setPageSize(4);

      List<HistoryPojo> historyPojolist = historyService.historyUserList2(historyPojo, page);
      DecimalFormat df = new DecimalFormat("#.##");

      for (HistoryPojo h : historyPojolist) {
        result = new HashMap<String, Object>();
        result.put("productId", h.getBusinessId());
        result.put("sellNumber", Integer.valueOf(h.getSellNumber()) + h.getBaseNumber());
        result.put("productName", h.getProductName());
        result.put("shopName", h.getShopName());
        result.put("distributionPrice", df.format(h.getDistributionPrice()));
        result.put("sellingPrice", df.format(h.getSellingPrice()));
        result.put("lowestPrice", h.getLowestPrice() == null ? "" : df.format(h.getLowestPrice()));
        result.put("image", ConstParam.URL + "/upfiles/product/" + h.getImage());
        result.put("type", h.getType());
        result.put("createDate", h.getCreateDateString());
        list.add(result);
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
    DecimalFormat df = new DecimalFormat("#.##");
    if (uid == null || "".equals(uid)) {
      msg = "会员ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else if (oid == null || "".equals(oid)) {
      msg = "订单详情ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      OrderRefundPojo orderRefundPojo = new OrderRefundPojo();
      orderRefundPojo.setUserId(uid);
      orderRefundPojo.setDetailId(Long.valueOf(oid));
      orderRefundPojo = orderRefundService.getorderRefundDetail(orderRefundPojo);
      if (orderRefundPojo != null) {
        /*
         * // 退款金额 // OrderDetailPojo orderDetailPojo = new OrderDetailPojo(); //
         * orderDetailPojo.setOrderId(orderRefundPojo.getOrderId()); // List<OrderDetailPojo> list =
         * // orderDetailService.orderDetailAllList(orderDetailPojo, null); double allPrice = 0,
         * refundPrice = 0, couponPrice = 0; // if (list.size() != 0) { for (OrderDetailPojo o :
         * list) { // allPrice += o.getStockPrice() * o.getNum(); } } Map<String, Object> option =
         * new HashMap<String, Object>(); option.put("orderId", orderRefundPojo.getOrderId());
         * option.put("status", 1); List<CouponOrderPojo> list2 =
         * couponService.getcouponOrderList(option); if (list2.size() != 0) { // option = new
         * HashMap<String, Object>(); // option.put("couponNo", list2.get(0).getCouponNo()); //
         * option.put("status", 1); // option.put("used", 1); // UserCouponPojo userCouponPojo = //
         * couponService.getUserCouponByNo(option); // if (userCouponPojo != null) { // String
         * jsonString = userCouponPojo.getContent(); // JSONObject jsons =
         * JSONObject.fromObject(jsonString); // couponPrice = //
         * Double.parseDouble(jsons.get("m").toString());
         * 
         * option = new HashMap<String, Object>(); // option.put("couponNo",
         * userCouponPojo.getCouponNo()); option.put("couponNo", list2.get(0).getCouponNo());
         * option.put("status", 1); List<CouponOrderPojo> list3 =
         * couponService.getcouponOrderList(option); if (list3.size() != 0) { for (CouponOrderPojo c
         * : list3) { if (c.getUsedPrice() != 0) { couponPrice = c.getUsedPrice(); }
         * 
         * OrderDetailPojo orderDetailPojo = new OrderDetailPojo();
         * orderDetailPojo.setOrderId(c.getOrderId()); List<OrderDetailPojo> list =
         * orderDetailService.orderDetailAllList(orderDetailPojo, null); if (list.size() != 0) { for
         * (OrderDetailPojo o : list) { allPrice += o.getStockPrice() * o.getNum(); } } } } // } }
         * if (couponPrice != 0 && allPrice != 0) { couponPrice = couponPrice *
         * orderRefundPojo.getStockPrice() * orderRefundPojo.getRefundNum() / allPrice; refundPrice
         * = orderRefundPojo.getStockPrice() * orderRefundPojo.getRefundNum() - couponPrice; //
         * 修改优惠金额 OrderRefundPojo orderRefund = new OrderRefundPojo();
         * orderRefund.setCouponPrice(couponPrice); orderRefund.setId(orderRefundPojo.getId());
         * orderRefundService.updateOrderRefund(orderRefund); } else { refundPrice =
         * orderRefundPojo.getStockPrice() * orderRefundPojo.getRefundNum(); }
         */

        // 退款金额
        double refundPrice =
            orderRefundPojo.getStockPrice() * orderRefundPojo.getRefundNum()
                - orderRefundPojo.getCouponPrice();
        result.put("refundPrice", df.format(refundPrice));
        result.put("createDate", StringUtil.dateString(orderRefundPojo.getCreateDate()));
        result.put("type",
            Long.valueOf(orderRefundPojo.getType()) == null ? "" : orderRefundPojo.getType());
        result
            .put("status", orderRefundPojo.getStatus() == null ? "" : orderRefundPojo.getStatus());
        result.put("refundType",
            orderRefundPojo.getRefundType() == null ? "" : orderRefundPojo.getRefundType());
        result.put("refundReason",
            orderRefundPojo.getRefundReason() == null ? "" : orderRefundPojo.getRefundReason());
        result.put("remarks",
            orderRefundPojo.getRemarks() == null ? "" : orderRefundPojo.getRemarks());
        result.put("refundNum",
            orderRefundPojo.getRefundNum() == null ? "" : orderRefundPojo.getRefundNum());
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
   * 退货单个商品详情
   * 
   * @return
   * @throws Exception
   */
  public String refundOneDetails() throws Exception {
    boolean b = false;
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    if (oid == null || "".equals(oid)) {
      msg = "订单详情ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      OrderRefundPojo orderRefund = new OrderRefundPojo();
      orderRefund.setDetailId(Long.valueOf(oid));
      OrderRefundPojo orderRefundPojo = orderRefundService.getorderRefundDetail(orderRefund);
      if (orderRefundPojo == null) {
        msg = "没有该订单的退货详情信息哦，亲(づ￣3￣)づ╭❤～";
      } else {
        result.put("productId", orderRefundPojo.getProductId().toString());
        result.put("productName", orderRefundPojo.getProductName());
        result.put("refundNumber", orderRefundPojo.getRefundNum().toString());
        OrderDetailPojo orderDetailPojo = orderDetailService.findOrderDetailById((long) oid);
        result.put("productImage", ConstParam.URL + "/upfiles/product/small" + File.separator
            + orderDetailPojo.getProductImage());
        result.put("status", orderRefundPojo.getStatus().toString());
        result.put("price", df.format(orderRefundPojo.getStockPrice()));
        result.put("shopId", orderRefundPojo.getShopId().toString());
        // 商品sku显示
        Integer skuid = orderRefundPojo.getSkuLinkId();
        result.put("skuLinkId", skuid == null ? "" : skuid);
        ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
        ProductSkuLinkPojo productSkuLinkPojo = null;
        if (skuid != null) {
          productSkuLink.setId(Long.valueOf(skuid));
          productSkuLinkPojo = productSkuLinkService.findProductSkuLink(productSkuLink);
          if (productSkuLinkPojo != null) {
            result.put("skuValue", "颜色:" + productSkuLinkPojo.getColorValue() + ";尺码:"
                + productSkuLinkPojo.getFormatValue());
          } else {
            result.put("skuValue", "");
          }
        } else {
          result.put("skuValue", "");
        }
        // 商品活动类型 0-普通商品 1-秒杀商品 2-专题商品 3-活动商品
        result.put("type", orderDetailPojo.getType() == null ? "" : orderDetailPojo.getType());
        // 类型1：退款 2：退货 3：售后服务
        result.put("rtype",
            Long.valueOf(orderRefundPojo.getType()) == null ? "" : orderRefundPojo.getType());
        result.put("discountPrice", df.format(orderRefundPojo.getCouponPrice()));
        result.put(
            "refundPrice",
            df.format(orderRefundPojo.getStockPrice() * orderRefundPojo.getRefundNum()
                - orderRefundPojo.getCouponPrice()));
      }
      b = true;
    }
    map.put("result", result);
    map.put("success", b);
    map.put("error_msg", msg);
    JSONObject json1 = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json1.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Long getIdLong() {
    return idLong;
  }

  public void setIdLong(Long idLong) {
    this.idLong = idLong;
  }

  public String getSearchKey() {
    return searchKey;
  }

  public void setSearchKey(String searchKey) {
    this.searchKey = searchKey;
  }

  public String getManufacturerId() {
    return manufacturerId;
  }

  public void setManufacturerId(String manufacturerId) {
    this.manufacturerId = manufacturerId;
  }

  public Long getAgencyUserId() {
    return agencyUserId;
  }

  public void setAgencyUserId(Long agencyUserId) {
    this.agencyUserId = agencyUserId;
  }

  public String[] getPidInventorys() {
    return pidInventorys;
  }

  public void setPidInventorys(String[] pidInventorys) {
    this.pidInventorys = pidInventorys;
  }

  public Long getAgencyId() {
    return agencyId;
  }

  public void setAgencyId(Long agencyId) {
    this.agencyId = agencyId;
  }

  public String getPids() {
    return pids;
  }

  public void setPids(String pids) {
    this.pids = pids;
  }

  public AgencyPojo getAgencyPojo() {
    return agencyPojo;
  }

  public void setAgencyPojo(AgencyPojo agencyPojo) {
    this.agencyPojo = agencyPojo;
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

  public String getAuthCode() {
    return authCode;
  }

  public void setAuthCode(String authCode) {
    this.authCode = authCode;
  }

  public String getDeviceInfo() {
    return deviceInfo;
  }

  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  public String getGoodsTag() {
    return goodsTag;
  }

  public void setGoodsTag(String goodsTag) {
    this.goodsTag = goodsTag;
  }

  public Integer getCouponNum() {
    return couponNum;
  }

  public void setCouponNum(Integer couponNum) {
    this.couponNum = couponNum;
  }

  public SkuAttributePojo getSkuAttributePojo() {
    return skuAttributePojo;
  }

  public void setSkuAttributePojo(SkuAttributePojo skuAttributePojo) {
    this.skuAttributePojo = skuAttributePojo;
  }

  public ScenePojo getScenePojo() {
    return scenePojo;
  }

  public void setScenePojo(ScenePojo scenePojo) {
    this.scenePojo = scenePojo;
  }

  public SceneProductPojo getSceneProductPojo() {
    return sceneProductPojo;
  }

  public void setSceneProductPojo(SceneProductPojo sceneProductPojo) {
    this.sceneProductPojo = sceneProductPojo;
  }

  public void genCouponTest() throws Exception {
    int count = num;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    UserCouponPojo userCouponPojo = null;
    for (int i = 0; i < count; i++) {
      long gentime = sdf.parse(StringUtil.dateString(new Date())).getTime();
      // 优惠券码规则：时间秒+5位数字随机
      userCouponPojo = new UserCouponPojo();
      userCouponPojo.setCouponNo(String.valueOf(gentime / 1000) + StringUtil.genRandomStr(5));
      Date sd = sdf.parse(babyBirthday);
      Date ed = sdf.parse(birth);
      userCouponPojo.setValidstime(sd.getTime() / 1000);
      userCouponPojo.setValidetime(ed.getTime() / 1000);
      // 用户
      userCouponPojo.setUserId(0L);
      userCouponPojo.setStatus(1);
      userCouponPojo.setCouponId(id);
      userCouponPojo.setGenTime(gentime / 1000);
      userCouponPojo.setUsed(0);
      userCouponPojo.setUseTime(0L);
      userCouponPojo.setSource(3);
      couponService.addUserCoupon(userCouponPojo);
    }
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
    if (wxpay != null) {
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
              // alipayOrderInfoService.findPayInfoByTradeNo(outTradeNo);
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

              // 交易成功处理
              long uid = processPaySuccess(outTradeNo);

              WxpayOrderInfoPojo payQry = wxpayOrderInfoService.findPayInfoByTradeNo(outTradeNo);
              /*
               * // 活动期间满60送60 Date today = new Date(); if (payQry != null &&
               * "TRADE_SUCCESS".equals(payQry.getTradeStatus()) &&
               * StringUtil.stringDate("2015-12-07 00:00:00").compareTo(today) <= 0 &&
               * StringUtil.stringDate("2015-12-14 00:00:00").compareTo(today) >= 0) {
               * giveUserCouponByOrderPay(Double.valueOf(payQry.getTotalFee())/100, uid); }
               */
              // 满40元赠送
              walletService.giveUserCouponByOrderPayWithDate(payQry.getTradeStatus(), uid,
                  Double.valueOf(payQry.getTotalFee()) / 100);
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
      orderPojo = orders.get(0);
      uid = orderPojo.getUserId();
      // 支付成功参团/开团处理
      grouponService.groupOrderHandle(orderPojo.getActivityId(), orderPojo.getSourceId(),
          orderPojo.getUserId(), orderPojo.getSource(), orderPojo.getId());
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
   * 订单支付金额满xx送xx
   * 
   * @param orderPirce 支付金额
   * @throws Exception
   */
  public void giveUserCouponByOrderPay(double orderPirce, Long uid) throws Exception {
    // 满60元赠送
    if (orderPirce >= 60.0) {
      Map<String, Object> param = new HashMap<String, Object>();
      Date start = StringUtil.stringDate("2015-12-07 00:00:00");
      // 满99减20
      param.put("om", "99");
      param.put("m", "20");
      JSONObject json = JSONObject.fromObject(param);
      giveCoupon(uid, 2, 1, json.toString(), start, 7);
      // 满199减40
      param.put("om", "159");
      param.put("m", "40");
      json = JSONObject.fromObject(param);
      giveCoupon(uid, 2, 1, json.toString(), start, 7);
    }
  }

  /**
   * 获取弹窗设置信息
   * 
   * @return
   * @throws SQLException
   */
  public String getPopupManageInfo() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, Object> option = new HashMap<String, Object>();
    String date = StringUtil.dateString(new Date());
    option.put("status", 1);
    option.put("startTimeStr", date);
    option.put("endTimeStr", date);
    option.put("option", 1);
    List<PopupPojo> popupPojos = popupService.findPopupList(option);
    if (popupPojos.size() != 0) {
      result.put("popupPic", "http://testb2c.taozhuma.com/upfiles/popup" + File.separator
          + popupPojos.get(0).getPopupPic());
      result.put("popupType", popupPojos.get(0).getPopupSize());
      result.put("parameterType", popupPojos.get(0).getParameterSize());
      result.put("parameter", popupPojos.get(0).getParameter());
      result.put("title", popupPojos.get(0).getTitle() == null ? "" : popupPojos.get(0).getTitle());
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
   * ip限制.
   * 
   * @param request
   * @return
   */
  public boolean sendFlag(HttpServletRequest request) {
    String ip = getIpAddr(request);
    Long second = loginRecService.queryTDLoinRecCount(ip);
    if (second < 30) {
      return false;
    }
    return true;
  }

  public boolean appleFlag() {
    HttpServletRequest request = ServletActionContext.getRequest();
    String agent = request.getHeader("User-Agent");
    if (agent.contains("MailWorldClient")) {
      return true;
    }
    return false;
  }

  /**
   * 昨日有米注册数量
   */
  public String youmiRegisNum() {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    int num = 0;
    if (regisType != null && !"".equals(regisType)) {
      if (regisType.equals("1")) {
        Map<String, Object> option = new HashMap<String, Object>();
        option.put("channel", 1);
        num = sysLoginService.sysLoginAllCountByYoumi(option);

        map.put("num", num);
        b = true;
      } else if (regisType.equals("2")) {
        Map<String, Object> option = new HashMap<String, Object>();
        option.put("channel", 2);
        num = sysLoginService.sysLoginAllCountByYoumi(option);

        map.put("num", num);
        b = true;
      } else {
        msg = "您输入的类型有误！";
      }
    } else {
      msg = "类型不能为空哦！";
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
   * 极验开关
   */
  public String getGeekValid() {
    String msg = "";
    boolean b = true;
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("result", SellerService.getOpenGeekValid());
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
   * 极验开关
   */
  public String setGeekValid() {
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    if (status == null || !(status == 1 || status == 0)) {
      status = 1;
    }
    SellerService.setOpenGeekValid(status);
    map.put("result", status);
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
   * 极验开关--0.1抽奖
   */
  public String setDrawValid() {
    String msg = "";
    Map<String, Object> map = new HashMap<String, Object>();
    if (status == null || !(status == 1 || status == 0)) {
      status = 1;
    }
    SellerService.setOpenDrawValid(status);
    map.put("result", status);
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

  public String getRegisType() {
    return regisType;
  }

  public void setRegisType(String regisType) {
    this.regisType = regisType;
  }
}
