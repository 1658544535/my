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

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.util.UtilDate;
import com.tzmb2c.business.service.ConstParam;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.business.service.WalletService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.RandomUtils;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityGoodsPojo;
import com.tzmb2c.web.pojo.ActivityTimePojo;
import com.tzmb2c.web.pojo.AlipayOrderInfoPojo;
import com.tzmb2c.web.pojo.BrandDicPojo;
import com.tzmb2c.web.pojo.CartPojo;
import com.tzmb2c.web.pojo.CouponOrderPojo;
import com.tzmb2c.web.pojo.DeliveryAddressPojo;
import com.tzmb2c.web.pojo.HistoryPojo;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.OrderShipPojo;
import com.tzmb2c.web.pojo.ProductCommentPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SpecialDiscountPojo;
import com.tzmb2c.web.pojo.SpecialShowPojo;
import com.tzmb2c.web.pojo.SysAreaPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserBrandPojo;
import com.tzmb2c.web.pojo.UserCollectPojo;
import com.tzmb2c.web.pojo.UserCouponPojo;
import com.tzmb2c.web.pojo.UserOrderRefundPojo;
import com.tzmb2c.web.pojo.UserShopCollectPojo;
import com.tzmb2c.web.pojo.UserSpecialCollectPojo;
import com.tzmb2c.web.pojo.UserWalletPojo;
import com.tzmb2c.web.pojo.WxpayOrderInfoPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.AgeSkillLinkService;
import com.tzmb2c.web.service.AlipayOrderInfoService;
import com.tzmb2c.web.service.BrandDicService;
import com.tzmb2c.web.service.CartService;
import com.tzmb2c.web.service.CouponService;
import com.tzmb2c.web.service.DeliveryAddressService;
import com.tzmb2c.web.service.HistoryService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.OrderShipService;
import com.tzmb2c.web.service.ProductCommentService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SpecialShowService;
import com.tzmb2c.web.service.SysAreaService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserBabyService;
import com.tzmb2c.web.service.UserBrandService;
import com.tzmb2c.web.service.UserCircleFollowService;
import com.tzmb2c.web.service.UserCirclePostService;
import com.tzmb2c.web.service.UserCollectService;
import com.tzmb2c.web.service.UserGrowthLineService;
import com.tzmb2c.web.service.UserInfoService;
import com.tzmb2c.web.service.UserMakerShopService;
import com.tzmb2c.web.service.UserOrderRefundService;
import com.tzmb2c.web.service.UserPostCollectService;
import com.tzmb2c.web.service.UserShopCollectService;
import com.tzmb2c.web.service.UserSpecialCollectService;
import com.tzmb2c.web.service.UserTalentAuthService;
import com.tzmb2c.web.service.UserWalletService;
import com.tzmb2c.web.service.WxpayOrderInfoService;

public class AppApiNormalShopAction extends SuperAction {
  private static final long serialVersionUID = 1L;
  // ---- spring 注入 ---- //
  @Autowired
  private ProductTypeService productTypeService;
  @Autowired
  private ProductService productService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private UserBrandService userBrandService;
  @Autowired
  private SellerService sellerService;
  @Autowired
  private ShopService shopService;
  @Autowired
  private CartService cartService;
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
  private ProductSkuLinkService productSkuLinkService;
  @Autowired
  private ProductCommentService productCommentService;
  @Autowired
  private SpecialShowService specialShowService;
  @Autowired
  private BrandDicService brandDicService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  @Autowired
  private ActivityTimeService activityTimeService;
  @Autowired
  private WalletService walletService;
  @Autowired
  private UserWalletService userWalletService;
  @Autowired
  private AlipayOrderInfoService alipayOrderInfoService;
  @Autowired
  private WxpayOrderInfoService wxpayOrderInfoService;
  @Autowired
  private HistoryService historyService;
  @Autowired
  private UserCollectService userCollectService;
  @Autowired
  private UserShopCollectService userShopCollectService;
  @Autowired
  private UserSpecialCollectService userSpecialCollectService;
  @Autowired
  private UserCirclePostService userCirclePostService;
  @Autowired
  private UserPostCollectService userPostCollectService;
  @Autowired
  private UserCircleFollowService userCircleFollowService;
  @Autowired
  private UserBabyService userBabyService;
  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private UserGrowthLineService userGrowthLineService;
  @Autowired
  private UserMakerShopService userMakerShopService;
  @Autowired
  private AgeSkillLinkService ageSkillLinkService;
  @Autowired
  private UserTalentAuthService userTalentAuthService;

  // ---- 变量定义 ---- //
  private Long id;
  /**
   * 会员id
   */
  private Long uid;
  /**
   * 活动类型
   */
  private Long activityId;

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
  private Integer oid;

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
  private String sorting;

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

  // ---- getter and setter ---- //

  public Integer getSrc() {
    return src;
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

  public Integer getOid() {
    return oid;
  }

  public void setOid(Integer oid) {
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

  public String getSorting() {
    return sorting;
  }

  public void setSorting(String sorting) {
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

  // ---- 操作方法 ---- //
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
        if (activityId > 0) {
          UserBrandPojo ub = userBrandService.findUserBrandById(productPojo.getUserBrandId());
          mapresult.put("shopName", ub.getBrandName());// 品牌名
          mapresult.put("shopImage", ConstParam.URL + "/upfiles/businessCenter/brandDic"
              + File.separator + ub.getLogo());// 品牌Logo

          // 品牌评分
          mapresult.put("productCmmt", ub.getProductCommt() == null ? "" : ub.getProductCommt());
          mapresult.put("deliverCmmt", ub.getDeliverCommt() == null ? "" : ub.getDeliverCommt());
          mapresult.put("logisticsCmmt",
              ub.getLogisticsCommt() == null ? "" : ub.getLogisticsCommt());
        } else {
          mapresult.put("shopName", shopPojo.getName());// 店铺名
          mapresult.put("shopImage",
              ConstParam.URL + "/upfiles/shop" + File.separator + shopPojo.getImages());// 店铺Logo

          // 店铺评分
          mapresult.put("productCmmt",
              shopPojo.getProductCommt() == null ? "" : shopPojo.getProductCommt());
          mapresult.put("deliverCmmt",
              shopPojo.getDeliverCommt() == null ? "" : shopPojo.getDeliverCommt());
          mapresult.put("logisticsCmmt",
              shopPojo.getLogisticsCommt() == null ? "" : shopPojo.getLogisticsCommt());
        }
        mapresult.put("pCmmt",
            productPojo.getProductCommt() == null ? "" : productPojo.getProductCommt());

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
        if (activityId > 0) {
          mapresult.put("isActive", isActive);
        } else {
          // 普通商品
          mapresult.put("isActive", 1);
        }
        mapresult.put("endTime", endTime);

        mapresult.put("ladderPrice", df.format(factPrice));
        mapresult.put("discount",
            SellerService.calcDiscount(factPrice, productPojo.getSellingPrice()));

        Map<String, Object> mapHotProduct = null;
        List<Object> listHot = new ArrayList<Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        if (activityId > 0) {
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
        } else {
          param.put("productTypeId", productPojo.getProductTypeId());
          param.put("productId", productPojo.getId());
          List<ProductPojo> hotProductList = productService.productForShopId1(param);

          if (hotProductList == null || hotProductList.size() == 0) {
            msg = "没有推荐商品！";
          }
          int count = 0;
          for (ProductPojo productPojo2 : hotProductList) {
            if (count >= 4) {
              break;
            }
            mapHotProduct = new HashMap<String, Object>();
            mapHotProduct.put("price", df.format(productPojo2.getDistributionPrice()));
            mapHotProduct.put("sellingPrice", df.format(productPojo2.getSellingPrice()));
            mapHotProduct.put(
                "discount",
                SellerService.calcDiscount(productPojo2.getDistributionPrice(),
                    productPojo2.getSellingPrice()));
            // 推荐商品价格
            mapHotProduct.put("image", ConstParam.URL + "/upfiles/product/small" + File.separator
                + productPojo2.getImage());// 推荐商品图片
            mapHotProduct.put("name", productPojo2.getProductName());// 推荐商品名
            mapHotProduct.put("productId", productPojo2.getId());// 推荐商品ID
            mapHotProduct.put("activityId", 0);// 推荐活动ID
            listHot.add(mapHotProduct);
          }
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
   * 加入购物车.
   * 
   * @return
   * @throws SQLException
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
    } else {
      if (src == null) {
        src = 0;
      }
      if (srcId == null) {
        srcId = 0l;
      }
      // 根据产品信息中的用户ID查询到店铺
      ShopPojo shop = new ShopPojo();
      shop.setUserId(proPojo.getUserId());
      shop = shopService.findShop(shop);
      if (activityId == null) {
        activityId = 0L;
      }
      // 活动产品判断
      ActivityGoodsPojo activityGoods = null;
      if (activityId > 0) {
        activityGoods = sellerService.isProductActivity(proPojo.getId(), activityId);
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
          productSkuLinkCheck = sellerService.getProducSku(proPojo.getId(), skuLinkId);
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

          if (activityGoods != null && activityGoods.getActivityType() != null
              && activityGoods.getActivityType() == 0 && num > 3) {
            // 秒杀活动限购数量为3
            flag = 1;
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
        // 活动类型,0普通1活动2秒杀3专题4场景5专场
        int type = 0;
        // 活动名称
        String activityName = "";
        if (activityId > 0) {
          ActivityTimePojo activity = activityTimeService.findActivityTimeById(activityId);
          type = activity != null ? activity.getType() + 2 : 1;
          activityName = activity.getTitle();

          if (type != 5 && proPojo.getUserBrandId() != null) {
            // 秒杀,名称某品牌特惠
            UserBrandPojo userBrand = userBrandService.findUserBrandById(proPojo.getUserBrandId());
            if (userBrand != null) {
              activityName = userBrand.getBrandName() + "特惠";
            } else {
              activityName += "特惠";
            }
          }
        } else {
          // 普通商品，活动名称写为店铺名称
          activityName = shop.getName();
        }
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
          cartPojo.setType(type);
          cartPojo.setChannel(1);
          cartPojo.setStatus(0);
          cartPojo.setUserName(sysLogin.getName());
          cartPojo.setShopName(shop.getName());
          cartPojo.setPostageType(proPojo.getPostageType());
          cartPojo.setSkuLinkId(skuLinkId);
          cartPojo.setActivityId(activityId);
          cartPojo.setActivityName(activityName);
          cartPojo.setSource(src);
          cartPojo.setSourceId(srcId);
          cartService.insertCart(cartPojo);
          msg = "恭喜，已增加至购物车！";
        } else {
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
   * 查看购物车.
   * 
   * @return
   * @throws SQLException
   */
  public String tomycart() throws SQLException {
    int sysLogin = sysLoginService.getfindCountByIdSysLogin(uid);
    List<Object> list = new ArrayList<Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid == null || uid.equals("")) {
      map.put("error_msg", "用户ID不能为空！");
      map.put("result", list);
      map.put("success", b);
    } else if (sysLogin == 0) {
      map.put("error_msg", "没有该用户！");
      map.put("success", b);
      map.put("result", list);
    } else {
      List<CartPojo> cartList = cartService.groupCartBrandByUserId(uid);
      // 活动产品
      ActivityGoodsPojo activityGoods = null;
      // 0-没有活动，1-活动进行中，2-活动结束
      int isActive = 0;
      ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
      ProductSkuLinkPojo productSkuLinkPojo = null;
      SpecialShowPojo specialShowPojo = null;
      for (CartPojo cartPojo : cartList) {
        List<Object> clist = new ArrayList<Object>();
        CartPojo cPojo = new CartPojo();
        cPojo.setUserId(uid);
        cPojo.setActivityId(cartPojo.getActivityId());
        cPojo.setActivityName(cartPojo.getActivityName());
        List<CartPojo> shopCartList = cartService.findCartByUserIdAndBrand(cPojo);
        Map<String, Object> mapShop = new HashMap<String, Object>();
        ProductSkuLinkPojo productSkuLinkCheck = null;
        String shopName = "";
        long shopId = 0l;
        for (CartPojo sCartPojo : shopCartList) {
          shopName = sCartPojo.getShopName() == null ? "" : sCartPojo.getShopName();
          shopId = sCartPojo.getShopId() == null ? 0l : sCartPojo.getShopId();
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
            activityGoods = sellerService.isProductActivity(sCartPojo.getProductId(), activityId);
          }
          // 有sku
          if (skuid != null && skuid > 0) {
            productSkuLinkCheck = sellerService.getProducSku(product.getId(), skuid);
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
          if (activityId > 0) {
            mapCart.put("shopId", sCartPojo.getActivityId());
            mapCart.put("shopName", StringUtils.isEmpty(sCartPojo.getActivityName()) ? shopName
                : sCartPojo.getActivityName());
          } else {
            mapCart.put("shopId", StringUtil.checkLong(sCartPojo.getShopId()));
            mapCart.put("shopName", StringUtil.checkString(sCartPojo.getShopName()));
          }

          mapCart.put("cid", sCartPojo.getId().toString());
          mapCart.put("productId", sCartPojo.getProductId().toString());
          if (activityId > 0) {
            mapCart.put("isActive", isActive);
          } else {
            mapCart.put("isActive", 1);
          }
          mapCart.put("name", sCartPojo.getProductName().toString());
          mapCart.put("price", df.format(sCartPojo.getStockPrice()));
          mapCart.put("sellingPrice", df.format(sCartPojo.getStockPriceOld()));
          mapCart.put("discount",
              SellerService.calcDiscount(sCartPojo.getStockPrice(), sCartPojo.getStockPriceOld()));
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
          // 活动类型,0普通,1活动,2秒杀,3专题,4场景,5专场
          mapCart.put("type", sCartPojo.getType());
          clist.add(mapCart);
        }
        if (cartPojo.getActivityId() != null && cartPojo.getActivityId() > 0) {
          mapShop.put("shopId", cartPojo.getActivityId());
          mapShop.put("shopName", StringUtils.isEmpty(cartPojo.getActivityName()) ? shopName
              : cartPojo.getActivityName());
          // 专场
          specialShowPojo =
              specialShowService.findSpecialShowByActivityId(cartPojo.getActivityId());
          if (specialShowPojo != null
              && StringUtils.isNotEmpty(specialShowPojo.getDiscountContext())) {
            mapShop.put("discountType", specialShowPojo.getDiscountType() == null ? 0
                : specialShowPojo.getDiscountType());
            List<SpecialDiscountPojo> discountLt =
                sellerService.transJsonToDiscount(specialShowPojo.getDiscountContext());
            mapShop.put("discountText",
                discountLt == null || discountLt.size() == 0 ? new ArrayList<SpecialDiscountPojo>()
                    : discountLt);
            mapShop.put("discountTextStr", sellerService.transJsonToDiscountStr(
                specialShowPojo.getDiscountType(), specialShowPojo.getDiscountContext()));
          } else {
            mapShop.put("discountType", 0);
            mapShop.put("discountText", new ArrayList<SpecialDiscountPojo>());
            mapShop.put("discountTextStr", "");
          }
        } else {
          // 普通商品
          mapShop.put("shopId", shopId);
          mapShop.put("shopName", shopName);
          // 没有专场信息
          mapShop.put("discountType", 0);
          mapShop.put("discountText", new ArrayList<SpecialDiscountPojo>());
          mapShop.put("discountTextStr", "");
        }

        mapShop.put("carts", clist);
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
                if (activityId > 0) {
                  name =
                      StringUtils.isEmpty(sCartPojo.getActivityName()) ? sCartPojo.getShopName()
                          : sCartPojo.getActivityName();
                  sId = sCartPojo.getActivityId();
                } else {
                  name = sCartPojo.getShopName();
                  sId = sCartPojo.getShopId();
                }
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
              if (cartPojo.getActivityId() > 0) {
                orderParam =
                    sellerService.specialDiscountProcess(sum, count, sum, cartPojo.getActivityId());
                sum = orderParam == null ? sum : orderParam.getFactPrice();
                specialShowPojo = specialShowService.findSpecialShowByActivityId(sId);
                String banner = "";
                if (specialShowPojo != null && specialShowPojo.getBanner() != null) {
                  banner = specialShowPojo.getBanner();
                }
                mapShop.put("shopLogo", ConstParam.URL + "/upfiles/specialShow/" + banner);
              } else {
                mapShop.put("shopLogo", ConstParam.URL + "/upfiles/shop" + File.separator
                    + cartPojo.getShopImage());
              }

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
              if (cart.getActivityId() > 0) {
                orderParam =
                    sellerService.specialDiscountProcess(allCartPrice, cartNum, allCartPrice0,
                        cart.getActivityId());
                allCartPrice0 = orderParam == null ? allCartPrice0 : orderParam.getFactPrice();
              }
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
      OrderPojo orderPojo = new OrderPojo();
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

      List<OrderPojo> list1 = null;
      List list = null;
      List<OrderDetailPojo> orderDetaillist = null;
      List<UserOrderRefundPojo> userOrderRefundlist = null;
      UserOrderRefundPojo userOrderRefundPojo = new UserOrderRefundPojo();
      List<OrderDetailPojo> orderDetailList = null;
      ProductSkuLinkPojo productSkuLink = null;
      ProductSkuLinkPojo productSkuLinkPojo = null;
      try {
        list1 = orderService.userIdOrderByPage(orderPojo, page);
        if (list1 != null && list1.size() > 0) {
          for (OrderPojo p : list1) {
            list = new ArrayList();
            map1 = new HashMap<String, Object>();
            int num = 0;
            orderDetaillist = orderDetailService.getfindByUserIdOrderDetail(p.getId());
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
            if (p.getActivityId() != null && p.getActivityId() > 0) {
              map1.put("shopId", p.getActivityId());
              if (StringUtils.isEmpty(p.getActivityName())) {
                map1.put("shopName", p.getShopName());
              } else {
                map1.put("shopName", p.getActivityName());
              }
            } else {
              map1.put("shopId", p.getShopId());
              map1.put("shopName", p.getShopName());
            }

            // map1.put("consigneeType", p.getConsigneeTypeName());
            // 3-货到付款
            if (p.getPayMethod() != null && 3 == p.getPayMethod()) {
              map1.put("consigneeType", "货到付款");
            } else {
              map1.put("consigneeType", p.getConsigneeTypeName());
            }
            orderDetailList = orderDetailService.getOrderDetail(p.getId());
            if (orderDetailList != null) {
              productSkuLink = new ProductSkuLinkPojo();
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
        map2.put("orderPrice", df.format(orderPojo.getFactPrice()));

        map2.put("espressPrice", df.format(orderPojo.getEspressPrice()));
        // 3-货到付款
        if (orderPojo.getPayMethod() != null && 3 == orderPojo.getPayMethod()) {
          map2.put("deliverType", "货到付款");
        } else {
          map2.put("deliverType", orderPojo.getConsigneeTypeName());
        }

        map2.put("orderStatus", orderPojo.getOrderStatus());

        ShopPojo shop = shopService.findShopById(orderPojo.getShopId());
        map2.put("shopLogo",
            ConstParam.URL + "/upfiles/shop/" + (shop == null ? "" : shop.getImages()));
        map2.put("name", orderPojo.getConsignee());
        if (orderPojo.getActivityId() != null && orderPojo.getActivityId() > 0) {
          map2.put("shopId", orderPojo.getActivityId());
          if (StringUtils.isEmpty(orderPojo.getActivityName())) {
            map2.put("shopName", orderPojo.getShopName());
          } else {
            map2.put("shopName", orderPojo.getActivityName());
          }
        } else {
          // 普通商品显示店铺信息
          map2.put("shopId", orderPojo.getShopId());
          map2.put("shopName", shop.getName());
        }

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
        double discountPrice = orderPojo.getDiscountPrice();
        double couponPrice = 0.00;
        // 订单用券详情
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("orderId", d);
        param.put("status", 1);
        List<CouponOrderPojo> couponOrders = couponService.getcouponOrderList(param);
        if (couponOrders != null && couponOrders.size() > 0) {
          CouponOrderPojo couponOrderPojo = couponOrders.get(0);
          map2.put("couponNo", couponOrderPojo.getCouponNo());
          map2.put("couponName", couponOrderPojo.getCouponName());
          couponPrice =
              couponOrderPojo.getUsedPrice() == null ? 0.00 : couponOrderPojo.getUsedPrice();
        } else {
          map2.put("couponNo", "");
          map2.put("couponName", "");
        }
        discountPrice += couponPrice;
        map2.put("usedPrice", discountPrice == 0L ? "" : df.format(discountPrice));


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
            map2.put("activityId", orderDetailPojo2.getActivityId());
            map2.put("activityName", orderDetailPojo2.getActivityName() == null ? ""
                : orderDetailPojo2.getActivityName());
            // 商品活动类型 0-普通商品 1-活动商品 2-秒杀商品 3-专题商品 4-场景 5-专场
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
          activityGoods = sellerService.isProductActivity(proPojo.getId(), activityId);
          if (activityGoods == null) {
            stockFlag = false;
          }
        }
        if (stockFlag) {
          // sku 价格
          if (skuLinkId != null && skuLinkId > 0) {
            productSkuLinkCheck = sellerService.getProducSku(proPojo.getId(), skuLinkId);
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
          map2.put("discount", SellerService.calcDiscount(price, proPojo.getSellingPrice()));
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
              map2.put("skuValue",
                  "颜色:" + productSkuLink.getColorValue() + ";尺码:" + productSkuLink.getFormatValue());
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
          map1.put("espressPrice", df.format(SellerService.calcFare(weight)));
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
    ActivityTimePojo activityTimePojo = null;
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
    } else if (payMethod == 4 && activityTimePojo != null
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
        activityGoods = sellerService.isProductActivity(productPojo.getId(), activityId);
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
          deliverFee = SellerService.calcFare(allWeight);
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
            sellerService.buildAlipayReq(alipayOrderInfoPojo.getOutTradeNo(), subject,
                alipayOrderInfoPojo.getTotalFee(), body, show_url);

      } else if (2 == payMethod) {
        // 生成未付款微信支付信息
        WxpayOrderInfoPojo wxpay = new WxpayOrderInfoPojo();
        wxpay.setOutTradeNo(out_trade_no);
        // 单位分
        wxpay.setTotalFee(SellerService.doubleToFee(allCartPrice0));
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
            sellerService.buildWxpayReq(null, wxpay.getOutTradeNo(), null, body, subject,
                Integer.valueOf(wxpay.getTotalFee()), "");
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
   * 搜索整合（商品，没货号）
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
    // ppojo.setActivityType("3");
    // ppojo.setTimeIdIsN("1");
    page.setPageSize(10);
    productList = productService.getProductAll(ppojo, page);
    List<Object> list = new ArrayList<Object>();// 商品list
    List<Object> list1 = new ArrayList<Object>();// 店铺list
    if (productList.size() == 0) {
      msg = "没有查询到商品数据！";
    } else {
      Double factPrice = 0.0;
      for (ProductPojo p : productList) {
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("pId", p.getId());
        map2.put("productName", p.getProductName());
        map2.put("productImage",
            ConstParam.URL + "/upfiles/product/small" + File.separator + p.getImage());
        // 活动价格判断
        if (p.getActivePrice() != null && !p.getActivePrice().equals("")) {
          factPrice = p.getActivePrice();
        } else {
          factPrice = p.getDistributionPrice();
        }
        map2.put("activityId", StringUtil.checkLong(p.getActivityId()));
        map2.put("distributionPrice", df.format(factPrice));
        map2.put("sellingPrice", df.format(p.getSellingPrice()));
        map2.put("discount", SellerService.calcDiscount(factPrice, p.getSellingPrice()));
        map2.put("sellNumber", p.getSellNumber() + p.getBaseNumber());
        map2.put("status", p.getStatus());
        list.add(map2);
      }
      b = true;
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
   * 产品列表
   * */
  public String product() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    DecimalFormat df = new DecimalFormat("#.##");
    page = new Pager();
    if (pageNo == null || pageNo.equals("")) {
      pageNo = 1;
    }
    page.setPageNo(pageNo);
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
    productPojo.setUserBrandId(brandId);
    // productPojo.setActivityType("3");
    // productPojo.setTimeIdIsN("1");
    List<ProductPojo> productList = productService.getProductApi(productPojo, page);
    if (productList.size() < 1) {
      map.put("error_msg", "没有搜索到数据！");
    }
    List<Object> list = new ArrayList<Object>();
    Map<String, Object> map2;
    Double factPrice = 0.0;
    for (ProductPojo p : productList) {
      map2 = new HashMap<String, Object>();
      map2.put("id", p.getId());
      map2.put("name", p.getProductName());
      map2.put("image", ConstParam.URL + "/upfiles/product/small" + File.separator + p.getImage());
      // 活动价格判断
      if (p.getActivePrice() != null && !"".equals(p.getActivePrice())) {
        factPrice = p.getActivePrice();
      } else {
        factPrice = p.getDistributionPrice();
      }
      map2.put("price", df.format(factPrice));
      map2.put("sellingPrice", df.format(p.getSellingPrice()));
      map2.put("discount", SellerService.calcDiscount(factPrice, p.getSellingPrice()));
      map2.put("sellNumber", p.getSellNumber() + p.getBaseNumber());
      map2.put("subTypeId", p.getProductTypeName());
      map2.put("activityId", StringUtil.checkLong(p.getActivityId()));
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
      HistoryPojo historyPojo = new HistoryPojo();
      historyPojo.setUserId(uid);
      List<HistoryPojo> historyPojolist = historyService.historyUserList2(historyPojo, page);
      int len = historyPojolist == null ? 0 : historyPojolist.size();
      HistoryPojo h = null;
      HistoryPojo hn = null;
      Double factPrice = 0.0;
      for (int i = 0; i < len; i++) {
        h = historyPojolist.get(i);
        map2 = new HashMap<String, Object>();
        map2.put("activityId", h.getActivityId());
        map2.put("productId", h.getBusinessId());
        map2.put("sellNumber", Integer.valueOf(h.getSellNumber()) + h.getBaseNumber());
        map2.put("productName", h.getProductName());
        map2.put("shopName", h.getShopName());
        if (h.getActivityId() != null && h.getActivityId() > 0) {
          // 活动价格判断
          ActivityGoodsPojo activityGoodsPojo =
              sellerService.findActivityGoodsByProductId(h.getBusinessId(), h.getActivityId());
          factPrice =
              activityGoodsPojo == null ? h.getDistributionPrice() : activityGoodsPojo
                  .getActivePrice();
          map2.put("distributionPrice", df.format(factPrice));
          map2.put(
              "proStatus",
              activityGoodsPojo == null ? 2 : sellerService
                  .checkActivityGoodStatus(activityGoodsPojo));
        } else {
          map2.put("distributionPrice", df.format(h.getDistributionPrice()));
          if (h.getProStatus() != null && "1".equals(h.getProStatus())) {
            // 产品有效
            map2.put("proStatus", 1);
          } else {
            // 产品下架
            map2.put("proStatus", 2);
          }
        }

        map2.put("sellingPrice", df.format(h.getSellingPrice()));
        map2.put("discount", SellerService.calcDiscount(factPrice, h.getSellingPrice()));
        map2.put("lowestPrice", h.getLowestPrice() == null ? "" : df.format(h.getLowestPrice()));
        map2.put("image", ConstParam.URL + "/upfiles/product/small/" + h.getImage());
        map2.put("type", h.getType());
        map2.put("createDate", h.getCreateDateString());

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
        UserCollectPojo userCollect = new UserCollectPojo();
        userCollect.setUserId(s);
        List<UserCollectPojo> list = userCollectService.UserCollectByUserId(userCollect, page);
        if (list.size() == 0) {
          msg = "没有数据了！";
        }
        if (list != null) {
          for (UserCollectPojo p : list) {
            map1 = new HashMap<String, Object>();
            map1.put("activityId", p.getActivityId());
            map1.put("favId", p.getId());
            map1.put("favoriteId", p.getProductId());
            ProductPojo product = new ProductPojo();
            product.setId(p.getProductId());
            product = productService.findProduct(product);
            if (product != null) {
              map1.put("favName", product.getProductName());
              if (p.getActivityId() != null && p.getActivityId() > 0) {
                ActivityGoodsPojo activityGoodsPojo =
                    sellerService.findActivityGoodsByProductId(product.getId(), p.getActivityId());
                map1.put(
                    "favPrice",
                    activityGoodsPojo == null ? df.format(product.getDistributionPrice()) : df
                        .format(activityGoodsPojo.getActivePrice()));
                map1.put(
                    "status",
                    activityGoodsPojo == null ? 2 : sellerService
                        .checkActivityGoodStatus(activityGoodsPojo));
              } else {
                map1.put("favPrice", df.format(product.getDistributionPrice()));
                if (product.getStatus() != null && "1".equals(product.getStatus())) {
                  // 产品有效
                  map1.put("status", 1);
                } else {
                  // 产品下架
                  map1.put("status", 2);
                }
              }

              map1.put("favImage", ConstParam.URL + "/upfiles/product/small" + File.separator
                  + product.getImage());
              map1.put("favItem", product.getProductNum());
            } else {
              map1.put("favName", "");
              map1.put("favPrice", "");
              map1.put("favImage", "");
              map1.put("favItem", "");
              map1.put("status", "");
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
            map1.put("activityId", "");
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
      } else if (favType == 4) {// 专场
        Map<String, Object> option = new HashMap<String, Object>();
        option.put("userId", s);
        option.put("pageSize", page.getPageSize());
        option.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
        List<UserSpecialCollectPojo> list =
            userSpecialCollectService.getUserSpecialCollectByUserId(option);
        if (list.size() == 0) {
          msg = "没有数据了！";
        }
        if (list != null) {
          for (UserSpecialCollectPojo p : list) {
            map1 = new HashMap<String, Object>();
            map1.put("activityId", p.getActivityId());
            map1.put("favId", p.getId());
            map1.put("favoriteId", p.getActivityId());
            map1.put("favName", p.getSpecialName());
            map1.put("favImage",
                ConstParam.URL + "/upfiles/specialShow" + File.separator + p.getBanner());
            map1.put("status", sellerService.isActivityStart(p.getBeginTime(), p.getEndTime()));
            map1.put("favPrice", "");
            map1.put("favItem", "");
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

    if (uid == null) {
      msg = "用户ID是必须要填写的哦，亲(づ￣3￣)づ╭❤～";
    } else {
      HistoryPojo historyPojo = new HistoryPojo();
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
        result.put("activityId", h.getActivityId());
        result.put("productId", h.getBusinessId());
        result.put("sellNumber", Integer.valueOf(h.getSellNumber()) + h.getBaseNumber());
        result.put("productName", h.getProductName());
        result.put("shopName", h.getShopName());
        if (h.getActivityId() != null && h.getActivityId() > 0) {
          ActivityGoodsPojo activityGoodsPojo =
              sellerService.findActivityGoodsByProductId(h.getBusinessId(), h.getActivityId());
          result.put(
              "distributionPrice",
              activityGoodsPojo == null ? df.format(h.getDistributionPrice()) : df
                  .format(activityGoodsPojo.getActivePrice()));
          result.put(
              "proStatus",
              activityGoodsPojo == null ? 2 : sellerService
                  .checkActivityGoodStatus(activityGoodsPojo));
        } else {
          result.put("distributionPrice", df.format(h.getDistributionPrice()));
          if (h.getProStatus() != null && "1".equals(h.getProStatus())) {
            // 产品有效
            result.put("proStatus", 1);
          } else {
            // 产品下架
            result.put("proStatus", 2);
          }
        }
        result.put("sellingPrice", df.format(h.getSellingPrice()));
        result.put("lowestPrice", h.getLowestPrice() == null ? "" : df.format(h.getLowestPrice()));
        result.put("image", ConstParam.URL + "/upfiles/product/small/" + h.getImage());
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

}
