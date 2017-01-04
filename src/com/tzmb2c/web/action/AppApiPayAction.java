package com.tzmb2c.web.action;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.alipay.util.UtilDate;
import com.alipay.util.httpClient.AlipayTradeQueryResponse;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.PingppException;
import com.pingplusplus.model.Charge;
import com.tencent.WXPay;
import com.tencent.common.Util;
import com.tencent.protocol.pay_query_protocol.ScanPayQueryReqData;
import com.tencent.protocol.pay_query_protocol.ScanPayQueryResData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.business.service.WalletService;
import com.tzmb2c.utils.PropertiesHelper;
import com.tzmb2c.utils.RandomUtils;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityGoodsPojo;
import com.tzmb2c.web.pojo.AgencyPojo;
import com.tzmb2c.web.pojo.AlipayOrderInfoPojo;
import com.tzmb2c.web.pojo.CartPojo;
import com.tzmb2c.web.pojo.CmbUserAgreePojo;
import com.tzmb2c.web.pojo.CouponOrderPojo;
import com.tzmb2c.web.pojo.DeliveryAddressPojo;
import com.tzmb2c.web.pojo.HistoryPojo;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.OrderPayinfoPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductSellPojo;
import com.tzmb2c.web.pojo.SysAreaPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserCollectPojo;
import com.tzmb2c.web.pojo.UserConsumerCollectPojo;
import com.tzmb2c.web.pojo.UserCouponPojo;
import com.tzmb2c.web.pojo.UserWalletPojo;
import com.tzmb2c.web.pojo.WxpayOrderInfoPojo;
import com.tzmb2c.web.service.AlipayOrderInfoService;
import com.tzmb2c.web.service.CartService;
import com.tzmb2c.web.service.CmbUserAgreeService;
import com.tzmb2c.web.service.CouponService;
import com.tzmb2c.web.service.DeliveryAddressService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderPayinfoService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.ProductSellService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.SysAreaService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserInfoService;
import com.tzmb2c.web.service.UserWalletService;
import com.tzmb2c.web.service.WxpayOrderInfoService;

public class AppApiPayAction extends SuperAction {
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
  private CmbUserAgreeService cmbUserAgreeService;
  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private ProductSellService productSellService;

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
  private CmbUserAgreePojo cmbUserAgreePojo;

  /**
   * 提交订单
   * 
   * @throws Exception
   */
  public String addorder() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, String> alipayMap = new HashMap<String, String>();
    Map<String, Object> wxpayMap = new HashMap<String, Object>();
    String error_msg = "";
    Boolean success = false;
    DeliveryAddressPojo address = null;
    Charge charge = new Charge();
    String custNo = null;

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
    } else if (payMethod == null || payMethod != 1 && payMethod != 2 && payMethod != 4
        && payMethod != 5 && payMethod != 6 && payMethod != 7) {
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
          // 商户订单号--招行
          String cmd_trade_no = StringUtil.generateRandomStr(10);
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
            } else if (5 == payMethod || 6 == payMethod || 7 == payMethod) {
              String channel = null;
              // 超出500长度截掉
              if (body.length() > 250) {
                body = body.substring(0, 250) + "...";
              }
              // 客户端的I P地址
              String ip = "";
              HttpServletRequest request = ServletActionContext.getRequest();
              ip = walletService.getIpAddr(request);
              // 返回charge信息
              if (5 == payMethod) {
                channel = "upacp";
              } else if (6 == payMethod) {
                channel = "applepay_upacp";
              } else if (7 == payMethod) {
                channel = "cmb_wallet";
                Map<String, Object> params = new HashMap<String, Object>();
                // 商户订单号--支付宝
                custNo = UtilDate.getOrderNum() + UtilDate.getThree();
                params.put("userId", uid);
                params.put("status", 1);
                cmbUserAgreePojo = cmbUserAgreeService.getByUserId(uid);
                if (cmbUserAgreePojo == null) {
                  SysLoginPojo sysLogin = sysLoginService.findSysLoginById(uid);
                  if (sysLogin != null) {
                    cmbUserAgreePojo = new CmbUserAgreePojo();
                    cmbUserAgreePojo.setUserId(uid);
                    cmbUserAgreePojo.setCustArgno(custNo);
                    cmbUserAgreePojo.setReqSerial(custNo);
                    cmbUserAgreePojo.setCustNo(custNo);
                    cmbUserAgreePojo.setMobile(sysLogin.getLoginname());
                    cmbUserAgreePojo.setStatus(1);
                    cmbUserAgreePojo.setCreateDate(new Date());
                    cmbUserAgreeService.add(cmbUserAgreePojo);
                  }
                }
              }
              int amount = Integer.valueOf(SellerService.doubleToFee(allOrderPrice));// 总额
              if (7 == payMethod) {
                charge =
                    walletService.pingPayReq(cmd_trade_no, subject, amount, body, ip, channel,
                        cmbUserAgreePojo);
              } else {
                charge =
                    walletService.pingPayReq(out_trade_no, subject, amount, body, ip, channel,
                        cmbUserAgreePojo);
              }
              // System.out.println(charge);
              if (charge != null) {
                // 生成一条未付款的charge信息
                OrderPayinfoPojo orderPayinfoPojo = new OrderPayinfoPojo();
                orderPayinfoPojo.setChannel(charge.getChannel());
                // orderPayinfoPojo.setTimePaid(charge.getTimePaid()!=null?charge.getTimePaid().toString():null);
                if (charge.getChannel().equals("cmb_wallet")) {
                  orderPayinfoPojo.setOutTradeNo(out_trade_no);
                  orderPayinfoPojo.setCmbTradeNo(charge.getOrderNo());
                } else {
                  orderPayinfoPojo.setOutTradeNo(charge.getOrderNo());
                }
                orderPayinfoPojo.setAmount(String.valueOf(charge.getAmount() / 100));
                orderPayinfoPojo.setChargeId(charge.getId());
                orderPayinfoPojo.setFeeType(charge.getCurrency());
                orderPayinfoPojo.setTradeStatus("WAIT_BUYER_PAY");
                orderPayinfoPojo.setFailureCode(charge.getFailureCode());
                orderPayinfoPojo.setFailureMsg(charge.getFailureMsg());
                orderPayinfoPojo.setCreateBy(user.getId());
                orderPayinfoPojo.setCreateDate(new Date());
                orderPayinfoPojo.setUpdateBy(user.getId());
                orderPayinfoPojo.setUpdateDate(new Date());
                orderPayinfoPojo.setRemarks(charge.getSubject());
                orderPayinfoPojo.setTransactionNo(charge.getTransactionNo());
                orderPayinfoPojo.setVersion(0);
                orderPayinfoService.addOrderPayInfo(orderPayinfoPojo);
              }
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
          if (charge.getId() != null) {
            result.put("charge", charge);
          }
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
    Charge charge = new Charge();
    String error_msg = "";
    boolean b = false;
    boolean isPay = false;
    String custNo;
    // 商户订单号--招行
    String cmd_trade_no = StringUtil.generateRandomStr(10);
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    Map<String, String> alipayMap = new HashMap<String, String>();
    Map<String, Object> wxpayMap = new HashMap<String, Object>();
    // 根据用户ID查询用户信息
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
    } else if (payMethod != 1 && payMethod != 2 && payMethod != 4 && payMethod != 5
        && payMethod != 6 && payMethod != 7) {
      error_msg = "支付方式错误";
    } else if (payMethod == 4 && isWallet == 0) {
      error_msg = "支付方式错误";
    } else {
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
        } else if (5 == order.getPayMethod() || 6 == order.getPayMethod()) {
          OrderPayinfoPojo pingpay = orderPayinfoService.findOrderPayInfoByOrderNo(outTradeNoOld);
          if (pingpay != null && "WAIT_BUYER_PAY".equals(pingpay.getTradeStatus())) {
            // error_msg = "等待支付结果返回中";
            // isPay = true;
            pingpay.setTradeStatus("TRADE_FAIL");
            pingpay.setRemarks("cancel:" + outTradeNoOld);
            orderPayinfoService.updateOrderPayInfo(pingpay);
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
            subject = "淘竹马单号:" + order.getOrderNo();
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
            } else if (5 == payMethod || 6 == payMethod || 7 == payMethod) {
              // 总长32，超出29长度截掉
              if (subject.length() > 29) {
                subject = subject.substring(0, 29) + "...";
              }
              String channel = null;
              // 超出500长度截掉
              if (body.length() > 250) {
                body = body.substring(0, 250) + "...";
              }
              // 客户端的I P地址
              String ip = "";
              HttpServletRequest request = ServletActionContext.getRequest();
              ip = walletService.getIpAddr(request);
              // 返回charge信息
              if (5 == payMethod) {
                channel = "upacp";
              } else if (6 == payMethod) {
                channel = "applepay_upacp";
              } else if (7 == payMethod) {
                channel = "cmb_wallet";
                Map<String, Object> params = new HashMap<String, Object>();
                // 商户订单号--支付宝
                custNo = UtilDate.getOrderNum() + UtilDate.getThree();
                params.put("userId", uid);
                params.put("status", 1);
                cmbUserAgreePojo = cmbUserAgreeService.getByUserId(uid);
                if (cmbUserAgreePojo == null) {
                  SysLoginPojo sysLogin = sysLoginService.findSysLoginById(uid);
                  if (sysLogin != null) {
                    cmbUserAgreePojo = new CmbUserAgreePojo();
                    cmbUserAgreePojo.setUserId(uid);
                    cmbUserAgreePojo.setCustArgno(custNo);
                    cmbUserAgreePojo.setReqSerial(custNo);
                    cmbUserAgreePojo.setCustNo(custNo);
                    cmbUserAgreePojo.setMobile(sysLogin.getLoginname());
                    cmbUserAgreePojo.setStatus(1);
                    cmbUserAgreePojo.setCreateDate(new Date());
                    cmbUserAgreeService.add(cmbUserAgreePojo);
                  }
                }
              }
              int amount = Integer.valueOf(SellerService.doubleToFee(price));// 总额
              // 创建charge
              if (7 == payMethod) {
                charge =
                    walletService.pingPayReq(cmd_trade_no, subject, amount, body, ip, channel,
                        cmbUserAgreePojo);
              } else {
                charge =
                    walletService.pingPayReq(out_trade_no, subject, amount, body, ip, channel,
                        cmbUserAgreePojo);
              }
              // 生成未付款ping支付信息
              if (charge != null) {
                // 生成一条未付款的charge信息
                OrderPayinfoPojo orderPayinfoPojo = new OrderPayinfoPojo();
                orderPayinfoPojo.setChannel(charge.getChannel());
                // orderPayinfoPojo.setTimePaid(charge.getTimePaid()!=null?charge.getTimePaid().toString():null);
                if (charge.getChannel().equals("cmb_wallet")) {
                  orderPayinfoPojo.setOutTradeNo(out_trade_no);
                  orderPayinfoPojo.setCmbTradeNo(charge.getOrderNo());
                } else {
                  orderPayinfoPojo.setOutTradeNo(charge.getOrderNo());
                }
                orderPayinfoPojo.setAmount(String.valueOf(charge.getAmount() / 100));
                orderPayinfoPojo.setChargeId(charge.getId());
                orderPayinfoPojo.setFeeType(charge.getCurrency());
                orderPayinfoPojo.setTradeStatus("WAIT_BUYER_PAY");
                orderPayinfoPojo.setFailureCode(charge.getFailureCode());
                orderPayinfoPojo.setFailureMsg(charge.getFailureMsg());
                orderPayinfoPojo.setCreateBy(loginPojo.getId());
                orderPayinfoPojo.setCreateDate(new Date());
                orderPayinfoPojo.setUpdateBy(loginPojo.getId());
                orderPayinfoPojo.setUpdateDate(new Date());
                orderPayinfoPojo.setRemarks(charge.getSubject());
                orderPayinfoPojo.setTransactionNo(charge.getTransactionNo());
                orderPayinfoPojo.setVersion(0);
                orderPayinfoService.addOrderPayInfo(orderPayinfoPojo);
              }
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
    if (charge.getId() != null) {
      result.put("charge", charge);
    }
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
   * 支付交易查询状态.
   * 
   * @return
   * @throws Exception
   */
  public String queryPayStatus() throws Exception {
    Pingpp.apiKey = PropertiesHelper.getValue("Pingpp.apiKey");
    if (payMethod != null && payMethod == 1 && StringUtils.isNotBlank(outTradeNo)) {
      queryAlipayStatus(outTradeNo);
    } else if (payMethod != null && payMethod == 2 && StringUtils.isNotBlank(outTradeNo)) {
      queryWxpayStatus(outTradeNo);
    } else if (payMethod != null && (payMethod == 5 || payMethod == 6 || payMethod == 7)
        && StringUtils.isNotBlank(outTradeNo)) {
      queryPingStatus(outTradeNo);
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
    Thread.sleep(1000);// 延迟查询
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
    Thread.sleep(1000);// 延迟查询
    WxpayOrderInfoPojo wxpay = wxpayOrderInfoService.findPayInfoByTradeNo(outTradeNo);
    if (wxpay != null) {
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

  // ----------------------------同步查询ping++charge_start---------------------------
  /**
   * ping++查询.
   * 
   * @param outTradeNo
   * @return
   * @throws Exception
   */
  public String queryPingStatus(String outTradeNo) throws Exception {
    Thread.sleep(1000);// 延迟查询
    OrderPayinfoPojo orderPayinfoPojo = orderPayinfoService.findOrderPayInfoByOrderNo(outTradeNo);
    if (orderPayinfoPojo != null) {
      chargeId = orderPayinfoPojo.getChargeId();
      try {
        Map<String, Object> param = new HashMap<String, Object>();
        List<String> expande = new ArrayList<String>();
        expande.add("app");
        param.put("expand", expande);
        Charge charge = Charge.retrieve(chargeId, param);
        System.out.println("这是charge：" + charge);
        processOrderPayStatusByPing(charge);
      } catch (PingppException e) {
        e.printStackTrace();
      }
    }

    return null;
  }

  /**
   * ping++支付是否成功处理.
   * 
   * @param charge
   * @throws Exception
   */
  protected void processOrderPayStatusByPing(Charge charge) throws Exception {
    if (charge != null) {
      outTradeNo = charge.getOrderNo();
      OrderPayinfoPojo orderPayinfoPojo = orderPayinfoService.findOrderPayInfoByOrderNo(outTradeNo);
      if (orderPayinfoPojo != null) {
        if (charge.getPaid() == true || orderPayinfoPojo.getTradeStatus().equals("TRADE_SUCCESS")) {
          orderPayinfoPojo.setChannel(charge.getChannel());
          orderPayinfoPojo.setTimePaid(charge.getTimePaid().toString());
          orderPayinfoPojo.setOutTradeNo(charge.getOrderNo());
          orderPayinfoPojo.setAmount(charge.getAmount().toString());
          orderPayinfoPojo.setChargeId(charge.getId());
          orderPayinfoPojo.setFeeType(charge.getCurrency());
          orderPayinfoPojo.setTradeStatus("TRADE_SUCCESS");
          orderPayinfoPojo.setFailureCode(charge.getFailureCode());
          orderPayinfoPojo.setFailureMsg(charge.getFailureMsg());
          orderPayinfoPojo.setCreateBy(orderPayinfoPojo.getCreateBy());
          orderPayinfoPojo.setCreateDate(orderPayinfoPojo.getCreateDate());
          orderPayinfoPojo.setUpdateBy(orderPayinfoPojo.getUpdateBy());
          orderPayinfoPojo.setUpdateDate(new Date());
          orderPayinfoPojo.setRemarks(charge.getSubject());
          orderPayinfoPojo.setTransactionNo(charge.getTransactionNo());
          orderPayinfoPojo.setVersion(0);
          orderPayinfoService.updateOrderPayInfo(orderPayinfoPojo);
          // 交易成功处理
          long uid = processPaySuccess(outTradeNo);
          OrderPayinfoPojo payQry =
              orderPayinfoService.findOrderPayInfoByOrderNo(charge.getOrderNo());
          // 满40元赠送
          if (payQry != null && payQry.getTradeStatus() != null) {
            walletService.giveUserCouponByOrderPayWithDate(payQry.getTradeStatus(), uid,
                Double.valueOf(payQry.getAmount()));
          }
        } else if (charge.getPaid() == false
            || orderPayinfoPojo.getTradeStatus().equals("TRADE_FAIL")) {
          // 交易失败处理
          processPayFailure(outTradeNo);
        }
      }

    }
  }

  // ----------------------------同步查询ping++charge_end---------------------------



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

              // 交易成功处理
              long uid = processPaySuccess(outTradeNo);
              AlipayOrderInfoPojo payQry = alipayOrderInfoService.findPayInfoByTradeNo(outTradeNo);
              /*
               * // 活动期间满60送60 Date today = new Date(); if (payQry != null &&
               * ("TRADE_FINISHED".equals(payQry.getTradeStatus()) ||
               * "TRADE_SUCCESS".equals(payQry.getTradeStatus())) && StringUtil.stringDate(
               * "2015-12-07 00:00:00").compareTo(today) <= 0 && StringUtil.stringDate(
               * "2015-12-14 00:00:00").compareTo(today) >= 0) {
               * giveUserCouponByOrderPay(Double.valueOf(payQry.getTotalFee()), uid); }
               */
              // 满40元赠送
              if (payQry != null && payQry.getTradeStatus() != null) {
                walletService.giveUserCouponByOrderPayWithDate(payQry.getTradeStatus(), uid,
                    Double.valueOf(payQry.getTotalFee()));
              }
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
               * "TRADE_SUCCESS".equals(payQry.getTradeStatus()) && StringUtil.stringDate(
               * "2015-12-07 00:00:00").compareTo(today) <= 0 && StringUtil.stringDate(
               * "2015-12-14 00:00:00").compareTo(today) >= 0) {
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
      uid = orders.get(0).getUserId();
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

  public CmbUserAgreePojo getCmbUserAgreePojo() {
    return cmbUserAgreePojo;
  }

  public void setCmbUserAgreePojo(CmbUserAgreePojo cmbUserAgreePojo) {
    this.cmbUserAgreePojo = cmbUserAgreePojo;
  }
}
