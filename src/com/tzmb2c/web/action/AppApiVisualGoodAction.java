package com.tzmb2c.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import com.tzmb2c.web.pojo.ChildrenChannelHistoryPojo;
import com.tzmb2c.web.pojo.ChildrenStoryPojo;
import com.tzmb2c.web.pojo.HistoryPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.SpecialProductPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserCollectPojo;
import com.tzmb2c.web.pojo.UserConsumerCollectPojo;
import com.tzmb2c.web.pojo.UserShopCollectPojo;
import com.tzmb2c.web.pojo.UserSpecialCollectPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.AlipayOrderInfoService;
import com.tzmb2c.web.service.BrandDicService;
import com.tzmb2c.web.service.CartService;
import com.tzmb2c.web.service.ChildrenChannelHistoryService;
import com.tzmb2c.web.service.ChildrenChannelService;
import com.tzmb2c.web.service.ChildrenStoryService;
import com.tzmb2c.web.service.CouponService;
import com.tzmb2c.web.service.DeliveryAddressService;
import com.tzmb2c.web.service.HomePageIconService;
import com.tzmb2c.web.service.LoginService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.ProductCommentService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SpecialShowService;
import com.tzmb2c.web.service.SysAreaService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserBrandService;
import com.tzmb2c.web.service.UserCollectService;
import com.tzmb2c.web.service.UserConsumerCollectService;
import com.tzmb2c.web.service.UserShopCollectService;
import com.tzmb2c.web.service.UserSpecialCollectService;
import com.tzmb2c.web.service.UserWalletService;
import com.tzmb2c.web.service.VisualGoodSetingService;
import com.tzmb2c.web.service.WxpayOrderInfoService;

public class AppApiVisualGoodAction extends SuperAction {
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

  @Autowired
  private UserCollectService userCollectService;
  @Autowired
  private UserShopCollectService userShopCollectService;
  @Autowired
  private UserConsumerCollectService userConsumerCollectService;
  @Autowired
  private UserSpecialCollectService userSpecialCollectService;

  @Autowired
  private LoginService loginService;
  @Autowired
  private ProductSkuLinkService productSkuLinkService;
  @Autowired
  private ShopService shopService;
  @Autowired
  private BrandDicService brandDicService;
  @Autowired
  private ProductCommentService productCommentService;


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
  private Integer favType;// 收藏类型
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
  private String fileName;
  private Integer sex;// 性别
  private String birth;// 生日
  private Integer babySex;// baby性别
  private String babyBirthday;// baby生日
  private String QQ;// 联系人QQ
  // sku
  private ProductSkuLinkPojo productSkuLinkPojo;


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

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
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

  public String getChargeId() {
    return chargeId;
  }

  public void setChargeId(String chargeId) {
    this.chargeId = chargeId;
  }

  /**
   * 获取产品颜色/规格
   * 
   * @return
   * @throws Exception
   */
  public String getValidProductSkuList() throws Exception {
    String msg = "";
    boolean b = false;
    DecimalFormat df = new DecimalFormat("#.##");
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    List<Map<String, Object>> skuList = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> vdSkuList = new ArrayList<Map<String, Object>>();
    Map<String, Object> skuColorMap = null;
    Map<String, Object> skuFormatMap = null;
    Map<String, Object> skuMap = null;
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

      // 返回库存不为0的sku
      productSkuLinkPojo.setStock(1);
      list = productSkuLinkService.getProductSkuLinkAll(productSkuLinkPojo, null);
      // 查询产品原价
      ProductPojo product = new ProductPojo();
      product.setId(pid);
      product = productService.findProduct(product);
      Double sellprice = 0.0;
      if (product != null) {
        sellprice = product.getSellingPrice();
      }

      if (list != null && list.size() > 0) {
        for (ProductSkuLinkPojo sku : list) {
          skuMap = new HashMap<String, Object>();
          skuMap.put("pid", pid);
          skuMap.put("skuColorId", sku.getSkuColorId());
          skuMap.put("colorValue", sku.getColorValue());
          skuMap.put("skuFormatId", sku.getSkuFormatId());
          skuMap.put("formatValue", sku.getFormatValue());
          skuMap.put("price", sku.getPrice() == null ? "" : df.format(sku.getPrice()));
          skuMap.put("discount", SellerService.calcDiscount(sku.getPrice(), sellprice) + "折");
          skuMap.put("status", 1);
          skuMap.put("id", sku.getId());
          vdSkuList.add(skuMap);
        }
      }
      result.put("skuList", skuList);
      result.put("vdSkuList", vdSkuList);
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
   * 获取融云token
   * 
   * @return
   * @throws Exception
   */
  public String getRongyunTokenApi() throws Exception {
    String msg = "";
    boolean success = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    // token
    String token = "";

    if (uid == null || uid == 0) {
      msg = "请先登录哦！~";
    } else {
      SysLoginPojo sysLoginPojo = sysLoginService.findSysLoginById(uid);
      if (sysLoginPojo != null) {
        if (sysLoginPojo.getToken() == null || "".equals(sysLoginPojo.getToken())) {
          // 用户id
          String userid = String.valueOf(uid);
          // 用户名
          String username = sysLoginPojo.getName();
          // 头像
          String logo = sysLoginPojo.getImage();
          token = sysLoginService.getRongyunToken(userid, username, logo);

          sysLoginPojo.setToken(token);
          loginService.updateLoginPojo(sysLoginPojo);
        } else {
          token = sysLoginPojo.getToken();
        }
        result.put("token", token);
      } else {
        msg = "该用户信息不存在哦！~";
      }
      success = true;
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
            map1.put("activityId", p.getActivityId());
            map1.put("favId", p.getId());
            map1.put("favoriteId", p.getProductId());
            ProductPojo product = new ProductPojo();
            product.setId(p.getProductId());
            product = productService.findProduct(product);
            if (product != null) {
              map1.put("favName", product.getProductName());
              ActivityGoodsPojo activityGoodsPojo =
                  sellerService.findActivityGoodsByProductId(product.getId(), p.getActivityId());
              map1.put(
                  "favPrice",
                  activityGoodsPojo == null ? df.format(product.getDistributionPrice()) : df
                      .format(activityGoodsPojo.getActivePrice()));
              map1.put("favImage", ConstParam.URL + "/upfiles/product/small" + File.separator
                  + product.getImage());
              map1.put("favItem", product.getProductNum());
              map1.put(
                  "status",
                  activityGoodsPojo == null ? 2 : sellerService
                      .checkActivityGoodStatus(activityGoodsPojo));
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
            map1.put("activityId", "");
            map1.put("favId", p.getId());
            map1.put("favoriteId", p.getProductId());
            ProductPojo product = new ProductPojo();
            product.setId(p.getProductId());
            product = productService.findProduct(product);
            if (product != null) {
              map1.put("favName", product.getProductName());
              map1.put(
                  "favPrice",
                  product.getDistributionPrice() == null ? "" : df.format(product
                      .getDistributionPrice()));
              map1.put("favImage", ConstParam.URL + "/upfiles/product/small" + File.separator
                  + product.getImage());
              map1.put("favItem", product.getProductNum());
              map1.put("status", product.getStatus());
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
            // map1.put("beginTime",
            // StringUtil.dateString(p.getBeginTime()));
            // map1.put("endTime",
            // StringUtil.dateString(p.getEndTime()));
            map1.put("status", sellerService.isActivityStart(p.getBeginTime(), p.getEndTime()));
            map1.put("favPrice", "");
            map1.put("favItem", "");
            list1.add(map1);
          }
        }
        success = true;
      } else if (favType == 5) {// 儿童产品收藏
        userCollect = new UserCollectPojo();
        userCollect.setUserId(s);
        long type = 1;
        userCollect.setType(type);
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
              ActivityGoodsPojo activityGoodsPojo =
                  sellerService.findActivityGoodsByProductId(product.getId(), p.getActivityId());
              map1.put(
                  "favPrice",
                  activityGoodsPojo == null ? df.format(product.getDistributionPrice()) : df
                      .format(activityGoodsPojo.getActivePrice()));
              map1.put("favImage", ConstParam.URL + "/upfiles/product/small" + File.separator
                  + product.getImage());
              map1.put("favItem", product.getProductNum());
              map1.put(
                  "status",
                  activityGoodsPojo == null ? 2 : sellerService
                      .checkActivityGoodStatus(activityGoodsPojo));
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
   * 视觉商品列表
   * 
   * @return
   * @throws SQLException
   */
  public String visualGoodListApi() throws SQLException {
    String msg = "";
    boolean success = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> result = new ArrayList<Object>();

    Map<String, Object> option = new HashMap<String, Object>();
    option.put("visualGoods", 1);
    option.put("proStatus", 1);
    option.put("status", 1);
    // option.put("specialStatus", 4);
    // 分页
    int pageSize = 10;
    if (pageNo == null || pageNo == 0) {
      option.put("pageNo", 0);
      option.put("pageSize", pageSize);
    } else {
      option.put("pageNo", (pageNo - 1) * pageSize);
      option.put("pageSize", pageSize);
    }
    List<SpecialProductPojo> list = visualGoodSetingService.findVisualGoodSetingList(option);
    if (list.size() != 0) {
      Map<String, Object> item = null;
      Double factPrice = 0.0;
      DecimalFormat df = new DecimalFormat("#.##");
      for (SpecialProductPojo s : list) {
        item = new HashMap<String, Object>();
        item.put("id", s.getProductId());
        item.put("name", s.getProductName());
        item.put("image", ConstParam.URL + "/upfiles/product/small" + File.separator + s.getImage());
        // 活动价格判断
        if (s.getActivePrice() != null && !"".equals(s.getActivePrice())) {
          factPrice = s.getActivePrice();
        } else {
          factPrice = s.getDistributionPrice() == null ? 0.0 : s.getDistributionPrice();
        }
        item.put("price", df.format(factPrice));
        item.put("sellingPrice", df.format(s.getSellingPrice()));
        item.put("discount", SellerService.calcDiscount(factPrice, s.getSellingPrice()));
        item.put("sellNumber", s.getSellNumber() + s.getBaseNumber());
        item.put("subTypeId", s.getProductTypeName());
        item.put("activityId", s.getActivityId());
        result.add(item);
      }
      success = true;
    } else {
      msg = "暂时还没有视觉商品哦！~";
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

        // 融云token
        // 用户id
        String userid = String.valueOf(sysLoginPojo.getId());
        // 用户名
        String username = sysLoginPojo.getName();
        // 头像
        String logo = "";
        // token
        if (sysLoginPojo.getImage() != null) {
          logo = ConstParam.URL + "/upfiles/userlogo/" + sysLoginPojo.getImage();
        }
        if (name != null && !"".equals(name) || file != null) {
          String token = sysLoginService.getRongyunToken(userid, username, logo);
          sysLoginPojo.setToken(token);
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
   * 视觉商品浏览记录
   * 
   * @return
   * @throws SQLException
   */
  public void visualGoodHistoryApi() throws SQLException {
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("type", 5);
    if (uid == null || uid == 0) {
      uid = 0l;
    }
    option.put("userId", uid);
    if (activityId == null || activityId == 0) {
      activityId = 0l;
    }
    option.put("activityId", activityId);
    if (productId == null || productId == 0) {
      productId = 0l;
    }
    option.put("businessId", productId);
    option.put("createDate", StringUtil.dateToString(new Date()));
    List<ChildrenChannelHistoryPojo> historys =
        childrenChannelHistoryService.findChildrenChannelHistoryList(option);
    ChildrenChannelHistoryPojo childrenChannelHistory = null;
    if (historys.size() != 0) {
      childrenChannelHistory = new ChildrenChannelHistoryPojo();
      childrenChannelHistory.setId(historys.get(0).getId());

      childrenChannelHistory.setHid(historys.get(0).getHid() + 1);
      childrenChannelHistoryService.updateChildrenChannelHistory(childrenChannelHistory);
    } else {
      childrenChannelHistory = new ChildrenChannelHistoryPojo();
      childrenChannelHistory.setUserId(uid);
      childrenChannelHistory.setType(5);

      childrenChannelHistory.setBusinessId(productId);
      childrenChannelHistory.setHid(1);
      childrenChannelHistory.setActivityId(activityId);
      childrenChannelHistoryService.insertChildrenChannelHistory(childrenChannelHistory);
    }

  }

  /**
   * 
   * 新商品判断收藏
   * */
  public String isFavoriteNew() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid == null || uid.equals("")) {
      map.put("error_msg", "用户Id不能为空！");
    } else if (productId == null || productId.equals("")) {
      map.put("error_msg", "产品Id不能为空！");
    } else if (favType == null || favType != 5) {
      map.put("error_msg", "收藏类型不能为空或者类型错误！");
    } else if (activityId == null) {
      map.put("error_msg", "活动Id不能为空！");
    } else {
      long s = uid;// 用户id
      long d = productId;// 收藏id
      UserCollectPojo userCollectPojo = new UserCollectPojo();
      userCollectPojo.setUserId(s);
      userCollectPojo.setProductId(d);
      userCollectPojo.setActivityId(activityId);
      userCollectPojo.setType(1L);
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
   * 添加新商品收藏
   * */
  public String addFavoriteNew() throws SQLException {
    boolean success = false;
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid == null || uid.equals("")) {
      map.put("error_msg", "用户Id不能为空！");
    } else if (productId == null || productId.equals("")) {
      map.put("error_msg", "产品Id不能为空！");
    } else if (favType == null || favType != 5) {
      map.put("error_msg", "收藏类型不能为空或者类型错误！");
    } else if (activityId == null) {
      map.put("error_msg", "活动Id不能为空！");
    } else {
      long s = uid;// 用户id
      long d = productId;// 收藏产品id
      UserCollectPojo userCollectPojo = new UserCollectPojo();
      userCollectPojo.setUserId(s);
      userCollectPojo.setProductId(d);
      userCollectPojo.setActivityId(activityId);
      userCollectPojo.setType(1L);
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
          userCollectPojo1.setType(1L);
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
        item.put("audioUrl", c.getAudioUrl());
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
