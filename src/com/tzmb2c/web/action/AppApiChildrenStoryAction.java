package com.tzmb2c.web.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.business.service.WalletService;
import com.tzmb2c.web.pojo.AgencyPojo;
import com.tzmb2c.web.pojo.ChildrenStoryPojo;
import com.tzmb2c.web.pojo.HistoryPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.UserCollectPojo;
import com.tzmb2c.web.pojo.UserConsumerCollectPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.AlipayOrderInfoService;
import com.tzmb2c.web.service.CartService;
import com.tzmb2c.web.service.ChildrenChannelHistoryService;
import com.tzmb2c.web.service.ChildrenChannelService;
import com.tzmb2c.web.service.ChildrenStoryService;
import com.tzmb2c.web.service.CouponService;
import com.tzmb2c.web.service.DeliveryAddressService;
import com.tzmb2c.web.service.HomePageIconService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderPayinfoService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.SpecialShowService;
import com.tzmb2c.web.service.SysAreaService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserBrandService;
import com.tzmb2c.web.service.UserWalletService;
import com.tzmb2c.web.service.VisualGoodSetingService;
import com.tzmb2c.web.service.WxpayOrderInfoService;

public class AppApiChildrenStoryAction extends SuperAction {
  private static final long serialVersionUID = 1L;

  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private CartService cartService;
  @Autowired
  private SellerService sellerService;
  @Autowired
  private DeliveryAddressService addressService;
  @Autowired
  private SysAreaService sysAreaService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private OrderDetailService orderDetailService;
  @Autowired
  private CouponService couponService;
  @Autowired
  private AlipayOrderInfoService alipayOrderInfoService;
  @Autowired
  private WxpayOrderInfoService wxpayOrderInfoService;
  @Autowired
  private WalletService walletService;
  @Autowired
  private UserWalletService userWalletService;
  @Autowired
  private OrderPayinfoService orderPayinfoService;
  @Autowired
  private ProductService productService;
  @Autowired
  private SpecialShowService specialShowService;
  @Autowired
  private ChildrenChannelService childrenChannelService;
  @Autowired
  private ChildrenChannelHistoryService childrenChannelHistoryService;
  @Autowired
  private HomePageIconService homePageIconService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  @Autowired
  private UserBrandService userBrandService;
  @Autowired
  private VisualGoodSetingService visualGoodSetingService;
  @Autowired
  private ChildrenStoryService childrenStoryService;

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
  // ping++
  private String chargeId;

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

  public String getChargeId() {
    return chargeId;
  }

  public void setChargeId(String chargeId) {
    this.chargeId = chargeId;
  }

  /**
   * 儿童故事列表
   * 
   * @return
   * @throws SQLException
   */
  public String childrenStoryListApi() throws SQLException {
    String msg = "";
    boolean success = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> result = new ArrayList<Object>();

    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 1);
    // 分页
    int pageSize = 10;
    if (pageNo == null || pageNo == 0) {
      option.put("pageNo", 0);
      option.put("pageSize", pageSize);
    } else {
      option.put("pageNo", (pageNo - 1) * pageSize);
      option.put("pageSize", pageSize);
    }
    List<ChildrenStoryPojo> list = childrenStoryService.findChildrenStoryList(option);
    if (list.size() != 0) {
      Map<String, Object> item = null;
      for (ChildrenStoryPojo c : list) {
        item = new HashMap<String, Object>();
        // item.put("audioUrl", c.getAudioUrl());
        // item.put("type", c.getType());
        // item.put("content", c.getContent());
        item.put("sorting", c.getSorting());
        item.put("title", c.getTitle());
        item.put("id", c.getId());
        result.add(item);
      }
      success = true;
    } else {
      msg = "亲，暂时还没有儿童故事哦~";
    }

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
}
