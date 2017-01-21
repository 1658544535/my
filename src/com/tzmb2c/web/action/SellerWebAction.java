package com.tzmb2c.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.CompressPicture;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.MD5Util;
import com.tzmb2c.utils.RandomUtils;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.BrandDicPojo;
import com.tzmb2c.web.pojo.HelpPojo;
import com.tzmb2c.web.pojo.HelpTypePojo;
import com.tzmb2c.web.pojo.LoginRecPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.ProductFocusImagesPojo;
import com.tzmb2c.web.pojo.ProductImagesPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductSellPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.SkuAttributePojo;
import com.tzmb2c.web.pojo.SpecialShowPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserBrandPojo;
import com.tzmb2c.web.pojo.UserOrderRefundPojo;
import com.tzmb2c.web.service.HelpTypeService;
import com.tzmb2c.web.service.LoginRecService;
import com.tzmb2c.web.service.LoginService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.ProductFocusImagesService;
import com.tzmb2c.web.service.ProductImagesService;
import com.tzmb2c.web.service.ProductSellService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.SkuAttributeService;
import com.tzmb2c.web.service.SpecialShowService;
import com.tzmb2c.web.service.SyntheticalDictService;
import com.tzmb2c.web.service.UserBrandService;
import com.tzmb2c.web.service.UserOrderRefundService;

public class SellerWebAction extends SuperAction {

  @Autowired
  private LoginService loginService;
  @Autowired
  private LoginRecService loginRecService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ProductSkuLinkService productSkuLinkService;
  @Autowired
  private SkuAttributeService skuAttributeService;
  @Autowired
  private UserBrandService userBrandService;
  @Autowired
  private SyntheticalDictService syntheticalDictService;
  @Autowired
  private ProductTypeService productTypeService;
  @Autowired
  private ProductImagesService productImagesService;
  @Autowired
  private ProductFocusImagesService productFocusImagesService;
  @Autowired
  private HelpTypeService helpTypeService;
  @Autowired
  private SellerService sellerService;
  @Autowired
  private UserOrderRefundService userOrderRefundService;
  @Autowired
  private OrderDetailService orderDetailService;
  @Autowired
  private SpecialShowService specialShowService;
  @Autowired
  private ProductSellService productSellService;
  @Autowired
  private OrderService orderService;

  private List<ProductImagesPojo> productImagesList;
  private List<ProductFocusImagesPojo> productFocusImagesList;
  private ProductImagesPojo productImagesPojo, productImagesPojo2, productImagesPojo3,
      productImagesPojo4, productImagesPojo5, productImagesPojo6, productImagesPojo7,
      productImagesPojo8, productImagesPojo9, productImagesPojo10;
  private ProductFocusImagesPojo productFocusImages, productFocusImages2, productFocusImages3,
      productFocusImages4, productFocusImages5;
  private File upfile2, upfile3, upfile4, upfile5, upfile6, upfile7, upfile8, upfile9, upfile10;
  private File[] upfiles, upfiles2;
  private ProductPojo productPojo;
  private ProductSkuLinkPojo productSkuLinkPojo;
  private SkuAttributePojo skuAttributePojo, skuAttributePojo2;
  private UserBrandPojo userBrandPojo;
  private List<UserBrandPojo> userBrandPojos;
  private BrandDicPojo brandDicPojo;
  private List<BrandDicPojo> brandDicPojos;
  private List<ProductPojo> productList;
  private List<SpecialShowPojo> specialShowList;
  private Logger logger = Logger.getLogger(SellerWebAction.class);
  private String username;// 用户名称
  private String passwd;// 用户密码
  private String usernameMsg;// 用户名称提示
  private String passwdMsg;// 用户密码提示
  private String checkcode;// 验证码
  private String rememberme;
  private String msg;// 提示
  private String url;// 前端传的返回连接
  private File upfile;
  private String typeStr;
  private String typeIdsStr;
  private String typeIdStr;
  private String[] tids;
  private String[] colors;
  private String[] formats;
  private String[] upfileSkus;// 原有的SKU图片
  private String[] upfileSku;// 新加的SKU图片
  private String upfileSku1;// 新加的SKU图片
  private String[] images;
  private String[] stocks;
  private String[] prices;
  private String[] businessCodes;
  private String[] status;
  private String result;
  private String skuImagesUrl;
  private HelpTypePojo helpTypePojo;
  private List<HelpTypePojo> helpTypePojos;
  private HelpPojo helpPojo;
  private List<HelpPojo> helpPojos;
  private ProductTypePojo productTypePojo;
  private Double deductScoreAll;// 总的扣减分数
  private List<ProductSkuLinkPojo> productSkuLinkPojos;
  private String is;

  private UserOrderRefundPojo userOrderRefundPojo;
  private List<UserOrderRefundPojo> userOrderRefundPojos;
  private long orderId;
  private int uorStatus;
  private int serviceInvolved;
  private String type;
  private String postid;
  private long productId;
  private long[] proImgId;
  private long proImgId1;
  private Integer[] isUpFiles;

  public Integer[] getIsUpFiles() {
    return isUpFiles;
  }

  public void setIsUpFiles(Integer[] isUpFiles) {
    this.isUpFiles = isUpFiles;
  }

  public long getProImgId1() {
    return proImgId1;
  }

  public void setProImgId1(long proImgId1) {
    this.proImgId1 = proImgId1;
  }

  public long[] getProImgId() {
    return proImgId;
  }

  public void setProImgId(long proImgId[]) {
    this.proImgId = proImgId;
  }

  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getPostid() {
    return postid;
  }

  public void setPostid(String postid) {
    this.postid = postid;
  }

  public int getUorStatus() {
    return uorStatus;
  }

  public void setUorStatus(int uorStatus) {
    this.uorStatus = uorStatus;
  }

  public int getServiceInvolved() {
    return serviceInvolved;
  }

  public void setServiceInvolved(int serviceInvolved) {
    this.serviceInvolved = serviceInvolved;
  }

  public String getSkuImagesUrl() {
    return skuImagesUrl;
  }

  public void setSkuImagesUrl1(String skuImagesUrl) {
    this.skuImagesUrl = skuImagesUrl;
  }

  public String getUpfileSku1() {
    return upfileSku1;
  }

  public void setUpfileSku1(String upfileSku1) {
    this.upfileSku1 = upfileSku1;
  }

  public String getTypeIdsStr() {
    return typeIdsStr;
  }

  public void setTypeIdsStr(String typeIdsStr) {
    this.typeIdsStr = typeIdsStr;
  }

  public String getTypeIdStr() {
    return typeIdStr;
  }

  public void setTypeIdStr(String typeIdStr) {
    this.typeIdStr = typeIdStr;
  }

  public String getTypeStr() {
    return typeStr;
  }

  public void setTypeStr(String typeStr) {
    this.typeStr = typeStr;
  }

  public List<ProductPojo> getProductList() {
    return productList;
  }

  public void setProductList(List<ProductPojo> productList) {
    this.productList = productList;
  }

  public ProductPojo getProductPojo() {
    return productPojo;
  }

  public void setProductPojo(ProductPojo productPojo) {
    this.productPojo = productPojo;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getCheckcode() {
    return checkcode;
  }

  public void setCheckcode(String checkcode) {
    this.checkcode = checkcode;
  }

  public String getRememberme() {
    return rememberme;
  }

  public void setRememberme(String rememberme) {
    this.rememberme = rememberme;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public ProductSkuLinkPojo getProductSkuLinkPojo() {
    return productSkuLinkPojo;
  }

  public void setProductSkuLinkPojo(ProductSkuLinkPojo productSkuLinkPojo) {
    this.productSkuLinkPojo = productSkuLinkPojo;
  }

  public SkuAttributePojo getSkuAttributePojo2() {
    return skuAttributePojo2;
  }

  public void setSkuAttributePojo2(SkuAttributePojo skuAttributePojo2) {
    this.skuAttributePojo2 = skuAttributePojo2;
  }

  public UserBrandPojo getUserBrandPojo() {
    return userBrandPojo;
  }

  public void setUserBrandPojo(UserBrandPojo userBrandPojo) {
    this.userBrandPojo = userBrandPojo;
  }

  public List<UserBrandPojo> getUserBrandPojos() {
    return userBrandPojos;
  }

  public void setUserBrandPojos(List<UserBrandPojo> userBrandPojos) {
    this.userBrandPojos = userBrandPojos;
  }

  public BrandDicPojo getBrandDicPojo() {
    return brandDicPojo;
  }

  public void setBrandDicPojo(BrandDicPojo brandDicPojo) {
    this.brandDicPojo = brandDicPojo;
  }

  public List<BrandDicPojo> getBrandDicPojos() {
    return brandDicPojos;
  }

  public void setBrandDicPojos(List<BrandDicPojo> brandDicPojos) {
    this.brandDicPojos = brandDicPojos;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String[] getColors() {
    return colors;
  }

  public void setColors(String[] colors) {
    this.colors = colors;
  }

  public String[] getFormats() {
    return formats;
  }

  public void setFormats(String[] formats) {
    this.formats = formats;
  }

  public String[] getUpfileSkus() {
    return upfileSkus;
  }

  public void setUpfileSkus(String[] upfileSkus) {
    this.upfileSkus = upfileSkus;
  }

  public String[] getUpfileSku() {
    return upfileSku;
  }

  public void setUpfileSku(String[] upfileSku) {
    this.upfileSku = upfileSku;
  }

  public String[] getImages() {
    return images;
  }

  public void setImages(String[] images) {
    this.images = images;
  }

  public String[] getStocks() {
    return stocks;
  }

  public void setStocks(String[] stocks) {
    this.stocks = stocks;
  }

  public String[] getPrices() {
    return prices;
  }

  public void setPrices(String[] prices) {
    this.prices = prices;
  }

  public String[] getBusinessCodes() {
    return businessCodes;
  }

  public void setBusinessCodes(String[] businessCodes) {
    this.businessCodes = businessCodes;
  }

  public String[] getStatus() {
    return status;
  }

  public void setStatus(String[] status) {
    this.status = status;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public List<ProductImagesPojo> getProductImagesList() {
    return productImagesList;
  }

  public void setProductImagesList(List<ProductImagesPojo> productImagesList) {
    this.productImagesList = productImagesList;
  }

  public List<ProductFocusImagesPojo> getProductFocusImagesList() {
    return productFocusImagesList;
  }

  public void setProductFocusImagesList(List<ProductFocusImagesPojo> productFocusImagesList) {
    this.productFocusImagesList = productFocusImagesList;
  }

  public ProductImagesPojo getProductImagesPojo() {
    return productImagesPojo;
  }

  public void setProductImagesPojo(ProductImagesPojo productImagesPojo) {
    this.productImagesPojo = productImagesPojo;
  }

  public ProductImagesPojo getProductImagesPojo2() {
    return productImagesPojo2;
  }

  public void setProductImagesPojo2(ProductImagesPojo productImagesPojo2) {
    this.productImagesPojo2 = productImagesPojo2;
  }

  public ProductImagesPojo getProductImagesPojo3() {
    return productImagesPojo3;
  }

  public void setProductImagesPojo3(ProductImagesPojo productImagesPojo3) {
    this.productImagesPojo3 = productImagesPojo3;
  }

  public ProductImagesPojo getProductImagesPojo4() {
    return productImagesPojo4;
  }

  public void setProductImagesPojo4(ProductImagesPojo productImagesPojo4) {
    this.productImagesPojo4 = productImagesPojo4;
  }

  public ProductImagesPojo getProductImagesPojo5() {
    return productImagesPojo5;
  }

  public void setProductImagesPojo5(ProductImagesPojo productImagesPojo5) {
    this.productImagesPojo5 = productImagesPojo5;
  }

  public ProductImagesPojo getProductImagesPojo6() {
    return productImagesPojo6;
  }

  public void setProductImagesPojo6(ProductImagesPojo productImagesPojo6) {
    this.productImagesPojo6 = productImagesPojo6;
  }

  public ProductImagesPojo getProductImagesPojo7() {
    return productImagesPojo7;
  }

  public void setProductImagesPojo7(ProductImagesPojo productImagesPojo7) {
    this.productImagesPojo7 = productImagesPojo7;
  }

  public ProductImagesPojo getProductImagesPojo8() {
    return productImagesPojo8;
  }

  public void setProductImagesPojo8(ProductImagesPojo productImagesPojo8) {
    this.productImagesPojo8 = productImagesPojo8;
  }

  public ProductImagesPojo getProductImagesPojo9() {
    return productImagesPojo9;
  }

  public void setProductImagesPojo9(ProductImagesPojo productImagesPojo9) {
    this.productImagesPojo9 = productImagesPojo9;
  }

  public ProductImagesPojo getProductImagesPojo10() {
    return productImagesPojo10;
  }

  public void setProductImagesPojo10(ProductImagesPojo productImagesPojo10) {
    this.productImagesPojo10 = productImagesPojo10;
  }

  public File[] getUpfiles() {
    return upfiles;
  }

  public void setUpfiles(File[] upfiles) {
    this.upfiles = upfiles;
  }

  public File[] getUpfiles2() {
    return upfiles2;
  }

  public void setUpfiles2(File[] upfiles2) {
    this.upfiles2 = upfiles2;
  }

  public File getUpfile7() {
    return upfile7;
  }

  public void setUpfile7(File upfile7) {
    this.upfile7 = upfile7;
  }

  public File getUpfile8() {
    return upfile8;
  }

  public void setUpfile8(File upfile8) {
    this.upfile8 = upfile8;
  }

  public File getUpfile9() {
    return upfile9;
  }

  public void setUpfile9(File upfile9) {
    this.upfile9 = upfile9;
  }

  public File getUpfile10() {
    return upfile10;
  }

  public void setUpfile10(File upfile10) {
    this.upfile10 = upfile10;
  }

  public File getUpfile2() {
    return upfile2;
  }

  public void setUpfile2(File upfile2) {
    this.upfile2 = upfile2;
  }

  public File getUpfile3() {
    return upfile3;
  }

  public void setUpfile3(File upfile3) {
    this.upfile3 = upfile3;
  }

  public File getUpfile4() {
    return upfile4;
  }

  public void setUpfile4(File upfile4) {
    this.upfile4 = upfile4;
  }

  public File getUpfile5() {
    return upfile5;
  }

  public void setUpfile5(File upfile5) {
    this.upfile5 = upfile5;
  }

  public File getUpfile6() {
    return upfile6;
  }

  public void setUpfile6(File upfile6) {
    this.upfile6 = upfile6;
  }

  public String getUsernameMsg() {
    return usernameMsg;
  }

  public void setUsernameMsg(String usernameMsg) {
    this.usernameMsg = usernameMsg;
  }

  public String getPasswdMsg() {
    return passwdMsg;
  }

  public void setPasswdMsg(String passwdMsg) {
    this.passwdMsg = passwdMsg;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public ProductFocusImagesPojo getProductFocusImages() {
    return productFocusImages;
  }

  public void setProductFocusImages(ProductFocusImagesPojo productFocusImages) {
    this.productFocusImages = productFocusImages;
  }

  public ProductFocusImagesPojo getProductFocusImages2() {
    return productFocusImages2;
  }

  public void setProductFocusImages2(ProductFocusImagesPojo productFocusImages2) {
    this.productFocusImages2 = productFocusImages2;
  }

  public ProductFocusImagesPojo getProductFocusImages3() {
    return productFocusImages3;
  }

  public void setProductFocusImages3(ProductFocusImagesPojo productFocusImages3) {
    this.productFocusImages3 = productFocusImages3;
  }

  public ProductFocusImagesPojo getProductFocusImages4() {
    return productFocusImages4;
  }

  public void setProductFocusImages4(ProductFocusImagesPojo productFocusImages4) {
    this.productFocusImages4 = productFocusImages4;
  }

  public ProductFocusImagesPojo getProductFocusImages5() {
    return productFocusImages5;
  }

  public void setProductFocusImages5(ProductFocusImagesPojo productFocusImages5) {
    this.productFocusImages5 = productFocusImages5;
  }

  public ProductTypePojo getProductTypePojo() {
    return productTypePojo;
  }

  public void setProductTypePojo(ProductTypePojo productTypePojo) {
    this.productTypePojo = productTypePojo;
  }

  public UserOrderRefundPojo getUserOrderRefundPojo() {
    return userOrderRefundPojo;
  }

  public void setUserOrderRefundPojo(UserOrderRefundPojo userOrderRefundPojo) {
    this.userOrderRefundPojo = userOrderRefundPojo;
  }

  public List<UserOrderRefundPojo> getUserOrderRefundPojos() {
    return userOrderRefundPojos;
  }

  public void setUserOrderRefundPojos(List<UserOrderRefundPojo> userOrderRefundPojos) {
    this.userOrderRefundPojos = userOrderRefundPojos;
  }

  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }

  public String getIs() {
    return is;
  }

  public void setIs(String is) {
    this.is = is;
  }

  public List<SpecialShowPojo> getSpecialShowList() {
    return specialShowList;
  }

  public void setSpecialShowList(List<SpecialShowPojo> specialShowList) {
    this.specialShowList = specialShowList;
  }

  /**
   * 商家登录前端显示
   * 
   * @return
   */
  public String getLoginWeb() {

    return SUCCESS;
  }

  /**
   * 商家注册前端显示
   * 
   * @return
   */
  public String getRegWeb() {
    return SUCCESS;
  }

  public String getLoginValidate() throws Exception {
    ActionContext actionContext = ActionContext.getContext();

    if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(passwd)) {

      SysLoginPojo loginPojo = new SysLoginPojo();
      loginPojo.setLoginname(username);

      loginPojo.setPassword(MD5Util.MD5(passwd));

      loginPojo.setUserType("2");
      if (loginService.loginCheckWeb(loginPojo)) {
        SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("wuser");
        if (logiPojo != null && logiPojo.getStatus() == 1) {
          HttpServletRequest request = ServletActionContext.getRequest();

          LoginRecPojo loginRecPojo = new LoginRecPojo();
          loginRecPojo.setType(logiPojo.getType());
          loginRecPojo.setLoginDate(new Date());
          loginRecPojo.setLoginIp(getIpAddr(request));
          loginRecPojo.setUserId(logiPojo.getId());
          loginRecService.addLoginRec(loginRecPojo);
          if (url != null && !url.equals("")) {
            FileUtil.alertMessageBySkip("登录成功", url);
            return null;
          } else {
            return SUCCESS;
          }

        } else {

          setMsg("您的帐号暂时不能登录，请联系管理员！");
          actionContext.put("loop", "false");
          return "loginweb";
        }
      }
      setMsg("用户名或密码错误！");
    }
    // setMsg("用户名或密码错误！");
    actionContext.put("loop", "false");
    return "loginweb";
  }

  /**
   * 商家退出
   * 
   * @return
   */
  public String doSellerLoginOutWeb() {
    Map<String, Object> session = ActionContext.getContext().getSession();
    session.clear();
    return "loginweb";
  }

  /**
   * 商家中心首页
   * 
   * @return
   * @throws SQLException
   */
  public String goSellerIndex() throws SQLException {
    // helpTypePojo = new HelpTypePojo();
    // // helpTypePojo.setId(26l);
    // helpTypePojo.setPname("商家帮助中心");
    // helpTypePojo.setName("商家须知");
    // helpTypePojo.setStatus(1);
    // helpTypePojo = helpTypeService.findHelpType(helpTypePojo);
    // if (helpTypePojo != null) {
    // HelpTypePojo ht = new HelpTypePojo();
    // ht.setPid(helpTypePojo.getId());
    // ht.setStatus(1);
    // helpTypePojos = helpTypeService.getHelpTypeByPidAndStatus(ht);
    // if (helpTypePojos.size() != 0) {
    // helpTypePojo.setHelpTypeChildPojoList(helpTypePojos);
    // }
    // }
    //
    // SysLoginPojo loginPojo = UserUtil.getWebUser();
    // BigDecimal bd = new BigDecimal(sellerService.returnDeductScoreAllByUid(loginPojo.getId()));
    // deductScoreAll = bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    //
    // setSpecialShowList(specialShowService.findSpecialShowByStatus(loginPojo.getId()));
    // for (SpecialShowPojo specialShowPojo : specialShowList) {
    // if (specialShowPojo.getDiscountContext() != null
    // && !"".equals(specialShowPojo.getDiscountContext())) {
    // specialShowPojo.setDiscountContext(sellerService.transJsonToDiscountStr(
    // specialShowPojo.getDiscountType(), specialShowPojo.getDiscountContext()));
    // } else {
    // specialShowPojo.setDiscountContext("无");
    // }
    // }

    SysLoginPojo loginPojo = UserUtil.getWebUser();
    if (loginPojo == null) {
      FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
      return null;
    } else {
      OrderPojo orderPojo = orderService.orderStatusNum(loginPojo.getId());
      ActionContext result = ActionContext.getContext();
      if (orderPojo != null) {
        // 待发货数量
        result.put("waitSendNum", orderPojo.getWaitSendNum() == null ? "0" : orderPojo
            .getWaitSendNum().toString());
        // 待收货数量
        result.put("waitRecNum", orderPojo.getDshNum() == null ? "0" : orderPojo.getDshNum());
        // 退款申请待处理
        result.put("saleApplyNum", orderPojo.getSaleApplyNum() == null ? "0" : orderPojo
            .getSaleSerNum().toString());
        // 已退货待处理
        result.put("saleOverNum", orderPojo.getSaleOverNum() == null ? "0" : orderPojo
            .getSaleOverNum().toString());
      } else {
        // 待发货数量
        result.put("waitSendNum", "0");
        // 待收货数量
        result.put("waitRecNum", "0");
        // 退款申请待处理
        result.put("saleApplyNum", "0");
        // 已退货待处理
        result.put("saleOverNum", "0");
      }
    }
    return SUCCESS;
  }

  public HelpTypePojo getHelpTypePojo() {
    return helpTypePojo;
  }

  public void setHelpTypePojo(HelpTypePojo helpTypePojo) {
    this.helpTypePojo = helpTypePojo;
  }

  public List<HelpTypePojo> getHelpTypePojos() {
    return helpTypePojos;
  }

  public void setHelpTypePojos(List<HelpTypePojo> helpTypePojos) {
    this.helpTypePojos = helpTypePojos;
  }

  public HelpPojo getHelpPojo() {
    return helpPojo;
  }

  public void setHelpPojo(HelpPojo helpPojo) {
    this.helpPojo = helpPojo;
  }

  public List<HelpPojo> getHelpPojos() {
    return helpPojos;
  }

  public void setHelpPojos(List<HelpPojo> helpPojos) {
    this.helpPojos = helpPojos;
  }

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

  /***
   * 商品库商品总数
   * 
   * @return
   * @throws Exception
   */
  public String productAllCountWeb() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    ActionContext ac = ActionContext.getContext();
    // getProductType(0l);
    ProductTypePojo productTypePojo = new ProductTypePojo();
    productTypePojo.setPid(-1L);
    ac.put("productType1List", productTypeService.getProductTypeByPids(productTypePojo));
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "doLoginWeb.do");
      return null;
    }
    Long uID = sysLogin.getId();
    map.put("userId", uID);
    ac.put("brandList", userBrandService.findUserBrandByUserId(map));
    if (page == null) {
      page = new Pager();
    }
    if (productPojo == null) {
      productPojo = new ProductPojo();
    }
    productPojo.setUserId(uID);
    productList = productService.getProductAllSeller(productPojo, null);
    page.setRowCount(productList.size());
    return SUCCESS;
  }

  /***
   * 商品库商品列表
   * 
   * @return
   * @throws Exception
   */
  public String productAllListWeb() throws Exception {
    if (productPojo != null) {
      SysLoginPojo sysLogin = UserUtil.getWebUser();
      Long uID = sysLogin.getId();
      page.setPageSize(10);
      productPojo.setUserId(uID);
      productList = productService.getProductAllSeller(productPojo, page);
    }
    JSONArray json = JSONArray.fromObject(productList);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /***
   * 跳到添加商品
   * 
   * @return
   * @throws Exception
   */
  public String goProductAddWeb() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    // getProductType(0l);
    ActionContext ac = ActionContext.getContext();
    map.put("type", "unit");
    ac.put("unit", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("type", "brand");
    ac.put("brand", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("type", "texture");
    ac.put("texture", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("type", "age");
    ac.put("age", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("type", "product_function");
    ac.put("productFunction", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("type", "is_power");
    ac.put("isPower", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("type", "pack");
    ac.put("pack", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("type", "is_hotsale");
    ac.put("isHotsale", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("type", "is_introduce");
    ac.put("isIntroduce", syntheticalDictService.getSyntheticalDictListByType(map));
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    Long uID = sysLogin.getId();
    map.put("userId", uID);
    ac.put("brandList", userBrandService.findUserBrandByUserId(map));
    ProductTypePojo productTypePojo = new ProductTypePojo();
    productTypePojo.setPid(-1L);
    ac.put("productType1List", productTypeService.getProductTypeByPids(productTypePojo));
    return SUCCESS;
  }

  public void getProductType(Long pid) throws Exception {
    ProductTypePojo fatherType = new ProductTypePojo();
    fatherType.setId(pid);
    fatherType = productTypeService.findProductType(fatherType);
    ProductTypePojo pojo = new ProductTypePojo();
    if (productTypePojo != null && productTypePojo.getLevel() != null) {
      pojo.setTopLevel(productTypePojo.getTopLevel());
      productTypePojo.setLevel(null);
    } else {
      pojo.setPid(pid);
    }
    List<ProductTypePojo> childList = productTypeService.getProductTypeByPids(pojo);

    String typeTemp = fatherType == null ? "" : fatherType.getName() + "--";

    if (!childList.isEmpty()) {
      for (ProductTypePojo child : childList) {
        typeStr = typeStr + "<option value='" + child.getId() + "' ";
        if (productPojo != null && productPojo.getProductTypeId() != null
            && productPojo.getProductTypeId().equals(String.valueOf(child.getId()))) {
          typeStr = typeStr + " selected='selected'";
        }
        typeStr = typeStr + ">" + typeTemp + child.getName() + "</option>";
        getProductType(child.getId());
      }
    }
  }

  /***
   * 添加产品信息
   * 
   * @return
   * @throws Throwable
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String productAddWeb() throws Throwable {
    try {
      SysLoginPojo sysLogin = UserUtil.getWebUser();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
        return null;
      }
      productPojo.setUserId(sysLogin.getId());
      productPojo.setCreateBy(sysLogin.getId());
      productPojo.setUpdateBy(sysLogin.getId());
      long uid = sysLogin.getId();
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
        // 给图片加水印
        // String waterPic =
        // ServletActionContext.getServletContext().getRealPath("/images")+File.separator+"water_logo.png";
        // ImageUtils.pressImageganji(uploadPath+file_name, waterPic, 50,
        // 50, 0.5f,false);
        // ImageUtils.pressImages(waterPic,uploadPath+file_name,4,0.5f);
        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
        productPojo.setImage(file_name);
      }
      productPojo.setIsNew("0");
      productPojo.setRecommend(0);
      StringBuffer sbf = new StringBuffer(RandomUtils.runVerifyCode(6));
      // sbf.append(new Date().getTime());
      // >>>qrcode<<<
      // 生成二维码
      // String content = "F***";
      // String path =
      // ServletActionContext.getServletContext().getRealPath("/upfiles/qrcode") + File.separator;
      // File creapath = new File(path);
      // if (!creapath.isDirectory()) {
      // creapath.mkdirs();
      // }
      // MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
      // Hashtable hints = new Hashtable();
      // hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
      // BitMatrix bitMatrix =
      // multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
      // SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
      // String img = df.format(new Date());
      // File file1 = new File(path, img + ".jpg");
      // MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
      // productPojo.setQrcode(img + ".jpg");
      productPojo.setStatus(0);
      productPojo.setProductNo(sbf.toString());
      productPojo.setLadderPrice("[]");
      /*
       * ProductTypePojo type = new ProductTypePojo();
       * type.setId(Long.parseLong(productPojo.getProductTypeId())); type =
       * productTypeService.findProductType(type);
       * //productPojo.setProductTypeIds(String.valueOf(type.getPid())); String typeIdStr =
       * ":"+String.valueOf(type.getPid())+":"; String value = ""; ProductTypePojo type2 = new
       * ProductTypePojo(); type2.setPid(0L); List<ProductTypePojo> productTypes = null; if
       * ("1".equals(productPojo.getAgeType())) { type2.setName("0-3岁婴幼儿玩具"); productTypes =
       * productTypeService.getProductTypeByPidName(type2); } else if
       * ("2".equals(productPojo.getAgeType())) { type2.setName("3-6岁儿童玩具"); productTypes =
       * productTypeService.getProductTypeByPidName(type2);
       * 
       * } else if ("3".equals(productPojo.getAgeType())) { type2.setName("6岁以上玩具"); productTypes =
       * productTypeService.getProductTypeByPidName(type2); } if (productTypes!=null &&
       * productTypes.size()>0) { value = String.valueOf(productTypes.get(0).getId()); }
       * if(!"".equals(value)){ typeIdStr += value +":"; } productPojo.setProductTypeIds(typeIdStr);
       */
      String typeIds = ":" + String.valueOf(productPojo.getProductTypeIds()) + ":";
      productPojo.setProductTypeIds(typeIds);
      productPojo.setBaseNumber(0);
      productPojo.setVersion(1);
      productService.addProductSeller(productPojo);// 商品插入
      // >>>product_sell<<<
      ProductSellPojo productSellPojo = new ProductSellPojo();
      productSellPojo.setProductId(productPojo.getId());
      productSellPojo.setProductName(productPojo.getProductName());
      productSellPojo.setProductImage(productPojo.getImage());
      productSellPojo.setPrice(productPojo.getDistributionPrice());
      productSellPojo.setSellNumber(productPojo.getSellNumber());
      productSellPojo.setCreateDate(new Date());
      productSellPojo.setCreateBy(productPojo.getCreateBy());
      productSellPojo.setUpdateDate(new Date());
      productSellPojo.setUpdateBy(productPojo.getUpdateBy());
      productSellPojo.setProductType1(productPojo.getProductType1());
      productSellPojo.setProductTypeIds(productPojo.getProductTypeIds());
      productSellPojo.setStatus(productPojo.getStatus());
      productSellService.addSeller(productSellPojo);
      if (upfile != null) {
        // 商品图片1张
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
        productImagesPojo = new ProductImagesPojo();
        productImagesPojo.setProductId(productPojo.getId());
        productImagesPojo.setImages(file_name);
        productImagesPojo.setUserId(uid);
        productImagesPojo.setCreateBy(uid);
        productImagesPojo.setUpdateBy(uid);
        productImagesPojo.setStatus(1);
        productImagesPojo.setSorting(1);
        productImagesService.addProductImagesSeller(productImagesPojo);
      } else {
        productPojo.setImage("");
      }
      // 焦点图片9张
      if (upfiles != null && upfiles.length > 0) {
        for (int i = 0; i < upfiles.length; i++) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/", upfiles[i]);
          // 图片压缩
          CompressPicture cp = new CompressPicture();
          String compressPath =
              ServletActionContext.getServletContext().getRealPath(
                  "/upfiles/productFocusImages/small")
                  + File.separator;
          cp.compressPic(upfiles[i], compressPath, "upfiles/productFocusImages/small/", file_name,
              300, 300, true);
          productFocusImages = new ProductFocusImagesPojo();
          productFocusImages.setProductId(productPojo.getId());
          productFocusImages.setImages(file_name);
          productFocusImages.setStatus(1);
          productFocusImages.setSorting(i + 1);
          productFocusImages.setUserId(uid);
          productFocusImages.setCreateBy(uid);
          productFocusImages.setUpdateBy(uid);
          productFocusImagesService.insertProductFocusImagesSeller(productFocusImages);
        }
      }
      // ProductPojo product = new ProductPojo();
      // product.setProductNameEn("5");
      // List<ProductPojo> productPojos = productService.getProductAll(product, page);
      // SKU插入
      // String message = "新增商品成功";
      result = "1" + productPojo.getId();
      Map<String, Object> map = new HashMap<String, Object>();
      Map<String, Object> map2 = new HashMap<String, Object>();
      if (productSkuLinkPojo == null) {
        productSkuLinkPojo = new ProductSkuLinkPojo();
      }
      if (skuAttributePojo == null) {
        skuAttributePojo = new SkuAttributePojo();
      }
      if (skuAttributePojo2 == null) {
        skuAttributePojo2 = new SkuAttributePojo();
      }
      if (tids != null) {
        for (int i = 0; i < tids.length; i++) {
          if (businessCodes != null && !"".equals(businessCodes[i])) {
            productSkuLinkPojo.setBusinessCode(businessCodes[i]);
          } else {
            // message = "新增商品成功，但sku商家编码不能为空！";
            result = "2" + productPojo.getId();
            continue;
          }
          if (stocks != null) {
            if ("".equals(stocks[i])) {
              // message = "新增商品成功，但sku库存不能为空！";
              result = "3" + productPojo.getId();
              continue;
            } else if (Integer.parseInt(stocks[i]) < 0) {
              // message = "新增商品成功，但sku库存不能小于0！";
              result = "4" + productPojo.getId();
              continue;
            } else {
              productSkuLinkPojo.setStock(Integer.parseInt(stocks[i]));
              productSkuLinkPojo.setStockNum(Integer.parseInt(stocks[i]));
            }
          }
          if (prices != null && !"".equals(prices[i])) {
            productSkuLinkPojo.setPrice(Double.parseDouble(prices[i]));
          } else {
            // message = "新增商品成功，但sku价格不能为空！";
            result = "5" + productPojo.getId();
            continue;
          }
          /*
           * if (status != null && !"".equals(status[i])) {
           * productSkuLinkPojo.setStatus(Integer.parseInt(status[i])); } else { // message =
           * "修改失败，sku审核状态不能为空！"; this.result = "6"; continue; }
           */
          if (formats != null && !"".equals(formats[i])) {
            map2.put("value", formats[i]);
            map2.put("productId", productPojo.getId());
            map2.put("attribute", "规格");
            List<SkuAttributePojo> SkuAttributePojos2 =
                skuAttributeService.getSkuAttributeSeller(map2);
            if (SkuAttributePojos2.size() != 0) {
              productSkuLinkPojo.setSkuFormatId(SkuAttributePojos2.get(0).getId());
            } else {
              skuAttributePojo2.setValue(formats[i]);
              skuAttributePojo2.setAttribute("规格");
              skuAttributePojo2.setProductId(productPojo.getId());
              skuAttributePojo2.setStatus(1);
              skuAttributePojo2.setCreateDate(new Date());
              skuAttributePojo2.setUpdateDate(new Date());
              skuAttributeService.insertSkuAttributeSeller(skuAttributePojo2);// 插入sku属性表
              productSkuLinkPojo.setSkuFormatId(skuAttributePojo2.getId());
            }
          } else {
            // message = "新增商品成功，但sku规格不能为空！";
            result = "7" + productPojo.getId();
            continue;
          }
          if (colors != null && !"".equals(colors[i])) {
            if (upfileSku != null && !"".equals(upfileSku[i])) {
              String file_name = StringUtil.getCurrentDateStr() + ".jpg";
              String uploadPath =
                  ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                      + File.separator;
              FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
              // 图片压缩
              CompressPicture cp = new CompressPicture();
              String compressPath =
                  ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                      + File.separator;
              cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 150, 150,
                  true);
              skuAttributePojo.setImage(file_name);
            }
            // else {
            // skuAttributePojo.setImage("");
            // message = "新增商品成功，但sku图片不能为空！";
            // continue;
            // }
            map.put("value", colors[i]);
            map.put("productId", productPojo.getId());
            map.put("attribute", "颜色");
            List<SkuAttributePojo> SkuAttributePojos =
                skuAttributeService.getSkuAttributeSeller(map);
            if (SkuAttributePojos.size() != 0) {
              productSkuLinkPojo.setSkuColorId(SkuAttributePojos.get(0).getId());
            } else {
              skuAttributePojo.setValue(colors[i]);
              skuAttributePojo.setAttribute("颜色");
              skuAttributePojo.setProductId(productPojo.getId());
              skuAttributePojo.setStatus(1);
              skuAttributePojo.setCreateDate(new Date());
              skuAttributePojo.setUpdateDate(new Date());
              skuAttributeService.insertSkuAttributeSeller(skuAttributePojo);// 插入sku属性表
              productSkuLinkPojo.setSkuColorId(skuAttributePojo.getId());
            }
            productSkuLinkPojo.setProductId(productPojo.getId());
            // productSkuLinkPojo.setStatus(1);
            productSkuLinkPojo.setType(0);
            productSkuLinkPojo.setActivityId(0L);
            ProductSkuLinkPojo productSkuLink =
                productSkuLinkService.findProductSkuLinkSeller(productSkuLinkPojo);
            if (productSkuLink != null) {
              productSkuLinkPojo.setId(productSkuLink.getId());
              productSkuLinkService.productSkuLinkUpdateSeller(productSkuLinkPojo);// 修改产品sku表
              // message = "新增商品成功！";
              result = "1" + productPojo.getId();
            } else {
              productSkuLinkService.addSkuLinkByProductIdSeller(productSkuLinkPojo);// 插入产品sku表
              // message = "新增商品成功！";
              result = "1" + productPojo.getId();
            }
          } else {
            // message = "新增商品成功，但sku颜色不能为空！";
            result = "8" + productPojo.getId();
            continue;
          }
        }
      }
      // FileUtil.alertMessageBySkip(message, "productManageSellerWeb.do");
      // return null;
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  /***
   * 获取二级类目
   * 
   * @return
   */
  public String getProductType2() throws Exception {
    /*
     * typeStr = ""; getProductType(0l); System.out.println(typeStr); Map<String, String> map = new
     * HashMap<String,String>(); map.put("data", typeStr); JSONArray json =
     * JSONArray.fromObject(map); result=json.toString();
     */
    typeIdsStr = "";
    if (productPojo != null && productPojo.getProductType1() != null) {
      productTypePojo = new ProductTypePojo();
      productTypePojo.setLevel(1);
      productTypePojo.setTopLevel(productPojo.getProductType1());
    }
    List<ProductTypePojo> productTypeIdsList =
        productTypeService.getProductTypeByPids(productTypePojo);
    for (ProductTypePojo productTypeIds : productTypeIdsList) {
      typeIdsStr = typeIdsStr + "<option value='" + productTypeIds.getId() + "' ";
      if (productPojo != null && productPojo.getProductTypeIds() != null
          && productPojo.getProductTypeIds().equals(String.valueOf(productTypeIds.getId()))) {
        typeIdsStr = typeIdsStr + " selected='selected'";
      }
      typeIdsStr = typeIdsStr + ">" + productTypeIds.getName() + "</option>";
    }
    Map<String, String> map = new HashMap<String, String>();
    map.put("data", typeIdsStr);
    JSONArray json = JSONArray.fromObject(map);
    result = json.toString();
    return SUCCESS;
  }

  /***
   * 获取三级类目
   * 
   * @return
   */
  public String getProductType3() throws Exception {
    typeIdStr = "";
    if (productPojo != null && productPojo.getProductTypeIds() != null) {
      productTypePojo = new ProductTypePojo();
      productTypePojo.setPid(Long.parseLong(productPojo.getProductTypeIds()));
    }
    List<ProductTypePojo> productTypeIdList =
        productTypeService.getProductTypeByPids(productTypePojo);
    for (ProductTypePojo productTypeId : productTypeIdList) {
      typeIdStr = typeIdStr + "<option value='" + productTypeId.getId() + "' ";
      if (productPojo != null && productPojo.getProductTypeId() != null
          && productPojo.getProductTypeId().equals(String.valueOf(productTypeId.getId()))) {
        typeIdStr = typeIdStr + " selected='selected'";
      }
      typeIdStr = typeIdStr + ">" + productTypeId.getName() + "</option>";
    }
    Map<String, String> map = new HashMap<String, String>();
    map.put("data", typeIdStr);
    JSONArray json = JSONArray.fromObject(map);
    result = json.toString();
    return SUCCESS;
  }

  /***
   * 跳转到修改产品
   * 
   * @return
   */
  public String goFindProduct() throws Exception {
    if (productPojo != null) {
      productPojo = productService.findProductSeller(productPojo);
      if (productPojo != null) {
        Map<String, Object> map = new HashMap<String, Object>();
        // getProductType(0l);
        ActionContext ac = ActionContext.getContext();
        map.put("type", "unit");
        ac.put("unit", syntheticalDictService.getSyntheticalDictListByType(map));
        map.put("type", "brand");
        ac.put("brand", syntheticalDictService.getSyntheticalDictListByType(map));
        map.put("type", "texture");
        ac.put("texture", syntheticalDictService.getSyntheticalDictListByType(map));
        map.put("type", "age");
        ac.put("age", syntheticalDictService.getSyntheticalDictListByType(map));
        map.put("type", "product_function");
        ac.put("productFunction", syntheticalDictService.getSyntheticalDictListByType(map));
        map.put("type", "is_power");
        ac.put("isPower", syntheticalDictService.getSyntheticalDictListByType(map));
        map.put("type", "pack");
        ac.put("pack", syntheticalDictService.getSyntheticalDictListByType(map));
        map.put("type", "is_hotsale");
        ac.put("isHotsale", syntheticalDictService.getSyntheticalDictListByType(map));
        map.put("type", "is_introduce");
        ac.put("isIntroduce", syntheticalDictService.getSyntheticalDictListByType(map));
        SysLoginPojo sysLogin = UserUtil.getWebUser();
        Long uID = sysLogin.getId();
        map.put("userId", uID);
        ac.put("brandList", userBrandService.findUserBrandByUserId(map));
        ProductTypePojo productTypePojo = new ProductTypePojo();
        productTypePojo.setPid(-1L);
        ac.put("productType1List", productTypeService.getProductTypeByPids(productTypePojo));
        if (productPojo != null && productPojo.getProductType1() != null) {
          getProductType2();
          getProductType3();
        }
        if (productSkuLinkPojo == null) {
          productSkuLinkPojo = new ProductSkuLinkPojo();
        }
        productSkuLinkPojo.setProductId(productPojo.getId());
        productSkuLinkPojo.setType(0);
        List<ProductSkuLinkPojo> productSkuLinkList =
            productSkuLinkService.getProductSkuLinkAll(productSkuLinkPojo, null);
        JSONArray json = JSONArray.fromObject(productSkuLinkList);
        productSkuLinkPojo.setLadderSku(json.toString());

        // 商品图片1张
        proImgId = new long[10];
        images = new String[10];
        productImagesPojo = new ProductImagesPojo();
        productImagesPojo.setProductId(productPojo.getId());
        productImagesList = productImagesService.getProductImagesAll(productImagesPojo, null);
        if (productImagesList.size() > 0) {
          proImgId[0] = productImagesList.get(0).getId();
        }

        // 焦点图片9张
        productFocusImages = new ProductFocusImagesPojo();
        productFocusImages.setProductId(productPojo.getId());
        productFocusImagesList =
            productFocusImagesService.productFocusImagesAllList(productFocusImages, null);
        if (productFocusImagesList.size() > 0) {
          int n = 1;
          for (ProductFocusImagesPojo p : productFocusImagesList) {
            if (n <= 9) {
              // ac.put("image" + n, p.getImages());
              images[n] = p.getImages();
              proImgId[n] = p.getId();
              n++;
            }
          }

          // List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
          // Map<String, Object> item = new HashMap<String, Object>();
          // item.put("images", images);
          // item.put("proImgId", proImgId);
          // list.add(item);

          // List<Object> list = new ArrayList<Object>();
          // list.add(images);
          // list.add(proImgId);
          // ac.put("proImages", list);

          ac.put("images", images);
        }
      }
    }
    return SUCCESS;
  }

  /***
   * 删除
   * 
   * @return
   */
  public String deleProduct() throws SQLException {
    try {
      productService.deleProduct(productPojo.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  /***
   * 批量删除
   * 
   * @return
   */
  public String productDeleteIdWeb() {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "doLoginWeb.do");
      return null;
    }
    if (productPojo == null) {
      productPojo = new ProductPojo();
    }
    productPojo.setUserId(sysLogin.getId());
    if (tids != null) {
      productService.productDeleteId(tids);
      FileUtil.alertMessageBySkip("删除成功！", "productManageSellerWeb.do");
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "productManageSellerWeb.do");
    }
    return null;
  }

  /***
   * 审核
   * 
   * @return
   */
  public String checkProduct() throws SQLException {
    try {
      productService.checkProductSeller(productPojo.getId());
      ProductSellPojo productSell = new ProductSellPojo();
      productSell.setProductId(productPojo.getId());
      productSell.setStatus(1);
      productSellService.updateSeller(productSell);
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  /**
   * 取消审核
   */
  public String uncheckProduct() throws SQLException {
    try {
      productService.uncheckProductSeller(productPojo.getId());
      ProductSellPojo productSell = new ProductSellPojo();
      productSell.setProductId(productPojo.getId());
      productSell.setStatus(0);
      productSellService.updateSeller(productSell);
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  /***
   * 批量审核
   * 
   * @return
   */
  public String productCheckWeb() {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "doLoginWeb.do");
      return null;
    }
    if (productPojo == null) {
      productPojo = new ProductPojo();
    }
    productPojo.setUserId(sysLogin.getId());
    ProductSellPojo productSell = new ProductSellPojo();
    if (tids != null) {
      for (String tid : tids) {
        try {
          productService.checkProduct(Long.parseLong(tid));
          productSell.setProductId(Long.parseLong(tid));
          productSell.setStatus(1);
          productSellService.update(productSell);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      FileUtil.alertMessageBySkip("批量售卖成功！", "productManageSellerWeb.do");
    } else {
      FileUtil.alertMessageBySkip("批量售卖失败！", "productManageSellerWeb.do");
    }
    return null;
  }

  /***
   * 批量取消审核
   * 
   * @return
   */
  public String productUncheckWeb() {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "doLoginWeb.do");
      return null;
    }
    if (productPojo == null) {
      productPojo = new ProductPojo();
    }
    productPojo.setUserId(sysLogin.getId());
    if (tids != null) {
      ProductSellPojo productSell = new ProductSellPojo();
      for (String tid : tids) {
        try {
          productService.uncheckProduct(Long.parseLong(tid));
          productSell.setProductId(Long.parseLong(tid));
          productSell.setStatus(0);
          productSellService.update(productSell);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      FileUtil.alertMessageBySkip("批量取消售卖成功！", "productManageSellerWeb.do");
    } else {
      FileUtil.alertMessageBySkip("批量取消售卖失败！", "productManageSellerWeb.do");
    }
    return null;
  }

  /***
   * 修改商品
   * 
   * @return
   * @throws Throwable
   */
  public String productUpdate() throws Throwable {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null || productPojo == null) {
      FileUtil.alertMessageBySkip("请先登录", "doLoginWeb.do");
      return null;
    }
    // if (productPojo != null) {
    productPojo.setUserId(sysLogin.getId());
    productPojo.setUpdateBy(sysLogin.getId());
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
      // 图片压缩
      CompressPicture cp = new CompressPicture();
      String compressPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
              + File.separator;
      cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
      productPojo.setImage(file_name);

      // 商品图片1张
      file_name = StringUtil.getCurrentDateStr() + ".jpg";
      uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
      productImagesPojo = new ProductImagesPojo();
      productImagesPojo.setImages(file_name);
      productImagesPojo.setId(proImgId1);
      productImagesService.productImagesUpdate(productImagesPojo);
    } else {
      productPojo.setImage("");
    }

    String typeIds = ":" + String.valueOf(productPojo.getProductTypeIds()) + ":";
    productPojo.setProductTypeIds(typeIds);
    // 商品修改
    productService.productUpdate(productPojo);
    ProductSellPojo productSellPojo = new ProductSellPojo();
    productSellPojo.setProductId(productPojo.getId());
    productSellPojo.setProductName(productPojo.getProductName());
    productSellPojo.setProductImage(productPojo.getImage());
    productSellPojo.setPrice(productPojo.getDistributionPrice());
    productSellPojo.setSellNumber(productPojo.getSellNumber());
    productSellPojo.setUpdateDate(new Date());
    productSellPojo.setProductType1(productPojo.getProductType1());
    productSellPojo.setProductTypeIds(productPojo.getProductTypeIds());
    productSellPojo.setStatus(productPojo.getStatus());
    productSellService.update(productSellPojo);

    // 焦点图片9张
    // 删除
    if (proImgId != null && proImgId.length > 0 && proImgId[0] != 0) {
      productFocusImages = new ProductFocusImagesPojo();
      productFocusImages.setProductId(productPojo.getId() == null ? 0l : productPojo.getId());
      productFocusImages.setCids(proImgId);
      productFocusImagesList =
          productFocusImagesService.productFocusImagesAllList(productFocusImages, null);
      if (productFocusImagesList.size() > 0) {
        for (ProductFocusImagesPojo p : productFocusImagesList) {
          productFocusImagesService.deleteProductFocusImages(p.getId());
        }
      }
    }
    // 修改
    if (proImgId != null && proImgId.length > 0) {
      int sorting = 0;
      int fileCount = 0;
      for (int i = 0; i < proImgId.length; i++) {
        if (upfiles2 != null && upfiles2.length > 0 && upfiles2[0].length() > 0) {
          if (isUpFiles != null && isUpFiles[i] == 1) {
            String file_name = StringUtil.getCurrentDateStr() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/",
                upfiles2[fileCount]);
            // 图片压缩
            CompressPicture cp = new CompressPicture();
            String compressPath =
                ServletActionContext.getServletContext().getRealPath(
                    "/upfiles/productFocusImages/small")
                    + File.separator;
            cp.compressPic(upfiles2[fileCount], compressPath, "upfiles/productFocusImages/small/",
                file_name, 300, 300, true);
            productFocusImages = new ProductFocusImagesPojo();
            productFocusImages.setId(proImgId[i]);
            productFocusImages.setImages(file_name);
            sorting++;
            productFocusImages.setSorting(sorting);
            productFocusImagesService.updateProductFocusImages(productFocusImages);
            fileCount++;
          }
        }
      }
    }
    // 添加
    if (upfiles != null && upfiles.length > 0) {
      long uid = sysLogin.getId();
      int sorting = 0;
      if (upfiles2 != null) {
        sorting = upfiles2.length;
      }
      for (int i = 0; i < upfiles.length; i++) {
        if (upfiles[i].length() > 0) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/", upfiles[i]);
          // 图片压缩
          CompressPicture cp = new CompressPicture();
          String compressPath =
              ServletActionContext.getServletContext().getRealPath(
                  "/upfiles/productFocusImages/small")
                  + File.separator;
          cp.compressPic(upfiles[i], compressPath, "upfiles/productFocusImages/small/", file_name,
              300, 300, true);
          productFocusImages = new ProductFocusImagesPojo();
          productFocusImages.setProductId(productPojo.getId());
          productFocusImages.setImages(file_name);
          productFocusImages.setUserId(uid);
          productFocusImages.setCreateBy(uid);
          productFocusImages.setUpdateBy(uid);
          productFocusImages.setStatus(1);
          sorting++;
          productFocusImages.setSorting(sorting);
          productFocusImagesService.addProductFocusImagesService(productFocusImages);
        }
      }
    }

    // SKU修改
    String message = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    if (productSkuLinkPojo == null) {
      productSkuLinkPojo = new ProductSkuLinkPojo();
    }
    if (skuAttributePojo == null) {
      skuAttributePojo = new SkuAttributePojo();
    }
    if (skuAttributePojo2 == null) {
      skuAttributePojo2 = new SkuAttributePojo();
    }
    if (tids != null) {
      for (int i = 0; i < tids.length; i++) {
        if (!tids[i].equals("on")) {
          productSkuLinkPojo.setId(Long.parseLong(tids[i]));
        } else {
          productSkuLinkPojo.setId(null);
        }
        if (businessCodes != null && !"".equals(businessCodes[i])) {
          productSkuLinkPojo.setBusinessCode(businessCodes[i]);
        } else {
          message = "修改失败，sku商家编码不能为空！";
          continue;
        }
        if (stocks != null) {
          if ("".equals(stocks[i])) {
            message = "修改失败，sku库存不能为空！";
            continue;
          } else if (Integer.parseInt(stocks[i]) < 0) {
            message = "修改失败，sku库存不能小于0！";
            continue;
          } else {
            productSkuLinkPojo.setStock(Integer.parseInt(stocks[i]));
            productSkuLinkPojo.setStockNum(Integer.parseInt(stocks[i]));
          }
        }
        if (prices != null && !"".equals(prices[i])) {
          productSkuLinkPojo.setPrice(Double.parseDouble(prices[i]));
        } else {
          message = "修改失败，sku价格不能为空！";
          continue;
        }
        if (status != null && !"".equals(status[i])) {
          productSkuLinkPojo.setStatus(Integer.parseInt(status[i]));
        } else {
          message = "修改失败，sku审核状态不能为空！";
          continue;
        }
        if (formats != null && formats[i] != "") {
          skuAttributePojo2.setValue(formats[i]);
          map2.put("value", formats[i]);
          map2.put("productId", productPojo.getId());
          map2.put("attribute", "规格");
          List<SkuAttributePojo> SkuAttributePojos2 = skuAttributeService.getSkuAttribute(map2);// 查询sku属性表规格
          if (SkuAttributePojos2.size() != 0) {
            productSkuLinkPojo.setSkuFormatId(SkuAttributePojos2.get(0).getId());
            // skuAttributePojo2.setId(SkuAttributePojos2.get(0).getId());
            // skuAttributeService.skuAttributeUpdateById(skuAttributePojo2);//修改sku属性表规格
          } else {
            skuAttributePojo2.setValue(formats[i]);
            skuAttributePojo2.setAttribute("规格");
            skuAttributePojo2.setProductId(productPojo.getId());
            skuAttributePojo2.setStatus(1);
            skuAttributePojo2.setCreateDate(new Date());
            skuAttributePojo2.setUpdateDate(new Date());
            skuAttributeService.insertSkuAttribute(skuAttributePojo2);// 插入sku属性表
            productSkuLinkPojo.setSkuFormatId(skuAttributePojo2.getId());
          }
        } else {
          message = "修改失败，sku规格不能为空！";
          continue;
        }
        if (colors != null && !"".equals(colors[i])) {
          skuAttributePojo.setValue(colors[i]);
          SkuAttributePojo skuAttributePojo3 = new SkuAttributePojo();
          if (images != null && !"".equals(images[i])) {
            skuAttributePojo.setImage(images[i]);
            skuAttributePojo3.setImage(images[i]);
          }
          // else {
          // skuAttributePojo.setImage("");
          // skuAttributePojo3.setImage("");
          // message = "修改失败，sku图片不能为空！";
          // continue;
          // }
          /*
           * if(images != null ){ if( !"".equals(images[i])){ if(upfileSkus != null){ for (int s=i ;
           * s <upfileSkus.length; s++) { if (upfileSkus[s]!=null && ""!=upfileSkus[s]) {
           * FileInputStream fin = new FileInputStream(upfileSkus[i]); String file_name =
           * StringUtil.getCurrentDateStr() + ".jpg"; String uploadPath =
           * ServletActionContext.getServletContext().getRealPath( "/upfiles/product") +
           * File.separator; File dirfile = new File(uploadPath); // 是否存在目录 如果不存在创建文件 if
           * (!dirfile.exists() || null != dirfile) { dirfile.mkdirs(); } FileUtil.copyFile(fin,
           * uploadPath + file_name); // 图片压缩 CompressPicture cp = new CompressPicture(); String
           * compressPath = ServletActionContext.getServletContext().getRealPath(
           * "/upfiles/product/small") + File.separator; cp.compressPic(uploadPath, compressPath,
           * file_name, file_name, 150, 150, true); skuAttributePojo.setImage(file_name);
           * skuAttributePojo3.setImage(file_name); } else { skuAttributePojo.setImage("");
           * skuAttributePojo3.setImage(images[i]); } break; } } }else{ if(upfileSku != null){ for
           * (int t = d; t <upfileSku.length; t++) { if (upfileSku[t]!=null &&
           * !"".equals(upfileSku[t]) ) { FileInputStream fin = new FileInputStream(upfileSku[t]);
           * String file_name = StringUtil.getCurrentDateStr() + ".jpg"; String uploadPath =
           * ServletActionContext.getServletContext().getRealPath( "/upfiles/product") +
           * File.separator; File dirfile = new File(uploadPath); // 是否存在目录 如果不存在创建文件 if
           * (!dirfile.exists() || null != dirfile) { dirfile.mkdirs(); } FileUtil.copyFile(fin,
           * uploadPath + file_name); // 图片压缩 CompressPicture cp = new CompressPicture(); String
           * compressPath = ServletActionContext.getServletContext().getRealPath(
           * "/upfiles/product/small") + File.separator; cp.compressPic(uploadPath, compressPath,
           * file_name, file_name, 150, 150, true); skuAttributePojo.setImage(file_name);
           * skuAttributePojo3.setImage(file_name); } else { skuAttributePojo.setImage("");
           * skuAttributePojo3.setImage(""); } d++; break; }
           * if("".equals(skuAttributePojo3.getImage())){ message="修改失败，新加sku图片不能为空！"; continue; } }
           * } }
           */
          map.put("value", colors[i]);
          map.put("productId", productPojo.getId());
          map.put("attribute", "颜色");
          List<SkuAttributePojo> SkuAttributePojos = skuAttributeService.getSkuAttribute(map);// 查询sku属性表颜色
          if (SkuAttributePojos.size() != 0) {
            productSkuLinkPojo.setSkuColorId(SkuAttributePojos.get(0).getId());
            skuAttributePojo3.setId(SkuAttributePojos.get(0).getId());
            skuAttributeService.skuAttributeUpdateById(skuAttributePojo3);// 修改sku属性表颜色
          } else {
            skuAttributePojo.setValue(colors[i]);
            skuAttributePojo.setAttribute("颜色");
            skuAttributePojo.setProductId(productPojo.getId());
            skuAttributePojo.setStatus(1);
            skuAttributePojo.setCreateDate(new Date());
            skuAttributePojo.setUpdateDate(new Date());
            skuAttributeService.insertSkuAttribute(skuAttributePojo);// 插入sku属性表
            productSkuLinkPojo.setSkuColorId(skuAttributePojo.getId());
          }
          if (productSkuLinkPojo.getId() != null) {
            productSkuLinkService.productSkuLinkUpdate(productSkuLinkPojo);// 修改产品sku表
            message = "修改成功！";
          } else {
            productSkuLinkPojo.setProductId(productPojo.getId());
            // productSkuLinkPojo.setStatus(1);
            productSkuLinkPojo.setType(0);
            productSkuLinkPojo.setActivityId(0L);
            ProductSkuLinkPojo productSkuLink =
                productSkuLinkService.findProductSkuLink(productSkuLinkPojo);
            if (productSkuLink == null) {
              if (stocks != null && !"".equals(stocks[i])) {
                productSkuLinkPojo.setStockNum(Integer.parseInt(stocks[i]));
              }
              productSkuLinkService.addSkuLinkByProductId(productSkuLinkPojo);// 插入产品sku表
              message = "修改成功！";
            }
          }
        } else {
          message = "修改失败，sku颜色不能为空！";
          continue;
        }
      }
    }
    FileUtil.alertMessageBySkip(message, "productManageSellerWeb.do");
    // } else {
    // FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
    // return null;
    // }
    return null;
  }

  /***
   * 上传SKU图片
   * 
   * @return
   * @throws Throwable
   */
  public String uploadSkuImages() throws Throwable {
    try {
      if (upfileSku1 != null) {
        File fin = new File(upfileSku1);
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", fin);
        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 150, 150, true);
        // skuImagesUrl=compressPath+file_name;
        skuImagesUrl = file_name;
      } else {
        skuImagesUrl = "";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /***
   * 删除商品
   * 
   * @return
   */
  public String deleProductSkuLink() throws SQLException {
    ProductSkuLinkPojo productSku = null;
    try {
      productSku = delCheckSkuColor(productSkuLinkPojo.getId());
      productSkuLinkService.deleProductSkuLink(productSkuLinkPojo.getId());
      if (productSku != null && productSku.getSkuColorId() != null) {
        skuAttributeService.deleSkuAttribute(productSku.getSkuColorId());
      }
      if (productSku != null && productSku.getSkuFormatId() != null) {
        skuAttributeService.deleSkuAttribute(productSku.getSkuFormatId());
      }
      result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  /**
   * 删除SKU时，查询颜色/规格是否有其他SKU关联
   * 
   * @param id
   * @return
   * @throws SQLException
   */
  public ProductSkuLinkPojo delCheckSkuColor(long id) throws SQLException {
    ProductSkuLinkPojo productSku = new ProductSkuLinkPojo();
    productSku.setId(id);
    productSku = productSkuLinkService.findProductSkuLink(productSku);
    if (productSku != null) {
      Map<String, Object> param = new HashMap<String, Object>();
      param.put("id", productSku.getId());
      param.put("skuColorId", productSku.getSkuColorId());
      int count = productSkuLinkService.findSkuLinkByOtherSku(param);
      if (count > 0) {
        productSku.setSkuColorId(null);
      }
      param.clear();
      param.put("id", productSku.getId());
      param.put("skuFormatId", productSku.getSkuFormatId());
      count = productSkuLinkService.findSkuLinkByOtherSku(param);
      if (count > 0) {
        productSku.setSkuFormatId(null);
      }
    }
    return productSku;
  }

  // 产品图片集列表
  public String getCountProductImages() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(productImagesService.getCount(productImagesPojo));
    // ActionContext ac = ActionContext.getContext();
    // ac.put("productImagesPojo2", productImagesPojo);
    return SUCCESS;
  }

  // 产品图片集列表
  public String getProductImagesAll() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    productImagesList = productImagesService.getProductImagesAll(productImagesPojo, page);
    JSONArray json = JSONArray.fromObject(productImagesList);
    page.setResult(json.toString());
    return SUCCESS;

  }

  // 产品图片集审核
  public String checkProductImages() throws SQLException {
    try {
      productImagesService.checkProductImages(productImagesPojo.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  // 产品图片集取消审核
  public String unCheckProductImages() throws SQLException {
    try {
      productImagesService.unCheckProductImages(productImagesPojo.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  // 产品图片集删除
  public String deleProductImages() throws SQLException {
    try {
      productImagesService.deleProductImages(productImagesPojo.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  // 产品图片集批量删除
  public String productImagesDeleteId() {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "doLoginWeb.do");
      return null;
    }
    productImagesPojo.setUserId(sysLogin.getId());
    StringBuffer url = new StringBuffer("productImagesManageSellerWeb.do");
    if (productImagesPojo != null && productImagesPojo.getProductId() != null) {
      url.append("?productImagesPojo.productId=" + productImagesPojo.getProductId()
          + "&productImagesPojo.userId=" + productImagesPojo.getUserId());
    }
    if (tids != null) {
      productImagesService.productImagesDeleteId(tids);
      FileUtil.alertMessageBySkip("删除成功！", url.toString());
    } else {
      FileUtil.alertMessageBySkip("删除失败！", url.toString());
    }
    return null;
  }

  // 产品图片集批量审核
  public String productImagescheckAll() {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "doLoginWeb.do");
      return null;
    }
    productImagesPojo.setUserId(sysLogin.getId());
    StringBuffer url = new StringBuffer("productImagesManageSellerWeb.do");
    if (productImagesPojo != null && productImagesPojo.getProductId() != null) {
      url.append("?productImagesPojo.productId=" + productImagesPojo.getProductId()
          + "&productImagesPojo.userId=" + productImagesPojo.getUserId());
    }
    for (String tid : tids) {
      try {
        productImagesService.checkProductImages(Long.parseLong(tid));
        FileUtil.alertMessageBySkip("审核成功！", url.toString());
      } catch (SQLException e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("审核失败！", url.toString());
      }
    }
    return null;
  }

  // 跳转到产品图片集修改图片
  public String goFindProductImages() {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "doLoginWeb.do");
      return null;
    }
    Long puserId = sysLogin.getId();
    productImagesPojo.setUserId(puserId);
    productImagesPojo = productImagesService.findProductImages(productImagesPojo);
    return SUCCESS;
  }

  // 产品图片集修改图片
  public String productImagesUpdate() throws Throwable {
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
      // 给图片加水印
      // String waterPic =
      // ServletActionContext.getServletContext().getRealPath("/images")+File.separator+"water_logo.png";
      // ImageUtils.pressImageganji(uploadPath+file_name, waterPic, 50,
      // 50, 0.5f,false);
      // ImageUtils.pressImages(waterPic,uploadPath+file_name,4,0.5f);

      // 图片压缩
      CompressPicture cp = new CompressPicture();
      String compressPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
              + File.separator;
      cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
      productImagesPojo.setImages(file_name);
      // productImagesPojo.setStatus(0);
    }
    productImagesService.productImagesUpdate(productImagesPojo);
    return SUCCESS;
  }

  // 跳转到产品图片集添加图片
  public String goproductImagesAddWeb() {
    return SUCCESS;
  }

  // 产品图片集添加图片
  public String productImagesAddWeb() throws Throwable {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "doLoginWeb.do");
      return null;
    }
    if (productImagesPojo != null && !"".equals(productImagesPojo.getProductId())) {
      long uid = sysLogin.getId();
      int max = 0;
      List<ProductImagesPojo> productImagesPojos =
          productImagesService.getProductImagesAll(productImagesPojo, null);
      if (productImagesPojos.size() > 0) {
        max = productImagesPojos.get(productImagesPojos.size() - 1).getSorting();
      }
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
        // 给图片加水印
        // String waterPic =
        // ServletActionContext.getServletContext().getRealPath("/images")+File.separator+"water_logo.png";
        // ImageUtils.pressImageganji(uploadPath+file_name, waterPic,
        // 50, 50, 0.5f,false);
        // ImageUtils.pressImages(waterPic,uploadPath+file_name,4,0.5f);
        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
        productImagesPojo.setImages(file_name);
        productImagesPojo.setUserId(uid);
        productImagesPojo.setCreateBy(uid);
        productImagesPojo.setUpdateBy(uid);
        productImagesPojo.setStatus(1);
        productImagesPojo.setSorting(max + 1);
        productImagesService.addProductImages(productImagesPojo);
      }
      if (upfile2 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile2);

        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile2, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
        productImagesPojo2.setImages(file_name);
        productImagesPojo2.setUserId(uid);
        productImagesPojo2.setCreateBy(uid);
        productImagesPojo2.setUpdateBy(uid);
        productImagesPojo2.setStatus(1);
        productImagesPojo2.setSorting(max + 2);
        productImagesService.addProductImages(productImagesPojo2);
      }
      if (upfile3 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile3);

        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile3, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
        productImagesPojo3.setImages(file_name);
        productImagesPojo3.setUserId(uid);
        productImagesPojo3.setCreateBy(uid);
        productImagesPojo3.setUpdateBy(uid);
        productImagesPojo3.setStatus(1);
        productImagesPojo3.setSorting(max + 3);
        productImagesService.addProductImages(productImagesPojo3);
      }
      if (upfile4 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile4);
        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile4, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
        productImagesPojo4.setImages(file_name);
        productImagesPojo4.setUserId(uid);
        productImagesPojo4.setCreateBy(uid);
        productImagesPojo4.setUpdateBy(uid);
        productImagesPojo4.setStatus(1);
        productImagesPojo4.setSorting(max + 4);
        productImagesService.addProductImages(productImagesPojo4);
      }
      if (upfile5 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile5);
        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile5, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
        productImagesPojo5.setImages(file_name);
        productImagesPojo5.setUserId(uid);
        productImagesPojo5.setCreateBy(uid);
        productImagesPojo5.setUpdateBy(uid);
        productImagesPojo5.setStatus(1);
        productImagesPojo5.setSorting(max + 5);
        productImagesService.addProductImages(productImagesPojo5);
      }
      if (upfile6 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile6);
        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile6, compressPath, "upfiles/product/small/", file_name, 300, 300, true);

        productImagesPojo6.setImages(file_name);
        productImagesPojo6.setUserId(uid);
        productImagesPojo6.setCreateBy(uid);
        productImagesPojo6.setUpdateBy(uid);
        productImagesPojo6.setStatus(1);
        productImagesPojo6.setSorting(max + 6);
        productImagesService.addProductImages(productImagesPojo6);
      }
      if (upfile7 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile7);

        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile7, compressPath, "upfiles/product/small/", file_name, 300, 300, true);

        productImagesPojo7.setImages(file_name);
        productImagesPojo7.setUserId(uid);
        productImagesPojo7.setCreateBy(uid);
        productImagesPojo7.setUpdateBy(uid);
        productImagesPojo7.setStatus(1);
        productImagesPojo7.setSorting(max + 7);
        productImagesService.addProductImages(productImagesPojo7);
      }

      if (upfile8 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile8);
        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile8, compressPath, "upfiles/product/small/", file_name, 300, 300, true);

        productImagesPojo8.setImages(file_name);
        productImagesPojo8.setUserId(uid);
        productImagesPojo8.setCreateBy(uid);
        productImagesPojo8.setUpdateBy(uid);
        productImagesPojo8.setStatus(1);
        productImagesPojo8.setSorting(max + 8);
        productImagesService.addProductImages(productImagesPojo8);
      }

      if (upfile9 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile9);

        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile9, compressPath, "upfiles/product/small/", file_name, 300, 300, true);

        productImagesPojo9.setImages(file_name);
        productImagesPojo9.setUserId(uid);
        productImagesPojo9.setCreateBy(uid);
        productImagesPojo9.setUpdateBy(uid);
        productImagesPojo9.setStatus(1);
        productImagesPojo9.setSorting(max + 9);
        productImagesService.addProductImages(productImagesPojo9);
      }

      if (upfile10 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile10);

        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile10, compressPath, "upfiles/product/small/", file_name, 300, 300, true);

        productImagesPojo10.setImages(file_name);
        productImagesPojo10.setUserId(uid);
        productImagesPojo10.setCreateBy(uid);
        productImagesPojo10.setUpdateBy(uid);
        productImagesPojo10.setStatus(1);
        productImagesPojo10.setSorting(max + 10);
        productImagesService.addProductImages(productImagesPojo10);
      }

    }
    return SUCCESS;
  }

  /**
   * 商品图片集批量添加.
   * 
   * @return
   * @throws Throwable
   */
  public String productImgBatchAddWeb() throws Throwable {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "doLoginWeb.do");
      return null;
    }
    if (productImagesPojo != null && !"".equals(productImagesPojo.getProductId())) {
      synchronized (SellerWebAction.class) {
        long uid = sysLogin.getId();
        int maxCount = productImagesService.getCount(productImagesPojo);
        if (upfile != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
          // 图片压缩
          CompressPicture cp = new CompressPicture();
          String compressPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                  + File.separator;
          cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
          productImagesPojo.setImages(file_name);
          productImagesPojo.setUserId(uid);
          productImagesPojo.setCreateBy(uid);
          productImagesPojo.setUpdateBy(uid);
          productImagesPojo.setStatus(1);
          productImagesPojo.setSorting(maxCount + 1);
          productImagesService.addProductImages(productImagesPojo);
        }
      }

    }
    return SUCCESS;
  }

  // 商品焦点图片集总数
  public String getProductFocusImagesCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(productFocusImagesService.productFocusImagesAllCount(productFocusImages));
    ActionContext ac = ActionContext.getContext();
    ac.put("productFocusImagesPojo", productFocusImages);
    return SUCCESS;
  }

  // 商品焦点图片集列表
  public String productFocusImagesAllList() {
    JSONArray json =
        JSONArray.fromObject(productFocusImagesService.productFocusImagesAllList(
            productFocusImages, page));
    page.setResult(json.toString());

    return SUCCESS;
  }

  // 商品焦点图片删除
  public String deleProductFocusImages() throws SQLException {
    try {
      productFocusImagesService.delProductFocusImages(productFocusImages.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  // 商品焦点图片批量删除
  public String productFocusImagesDeleteId() {
    if (tids != null) {
      productFocusImagesService.productFocusImagesDeleteId(tids);
      FileUtil.alertMessageBySkip(
          "删除成功！",
          "productFocusImagesSellerWeb.do?productFocusImages.productId="
              + productFocusImages.getProductId());
    } else {
      FileUtil.alertMessageBySkip(
          "删除失败！",
          "productFocusImagesSellerWeb.do?productFocusImages.productId="
              + productFocusImages.getProductId());
    }
    return null;
  }

  // 跳转到商品焦点图片更新
  public String goFindProductFocusImages() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("productFocusImagesPojo",
        productFocusImagesService.findProductFocusImagesById(productFocusImages.getId()));
    return SUCCESS;
  }

  // 商品焦点图片更新
  public String updateProductFocusImages() throws Throwable {
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/", upfile);
      productFocusImages.setImages(file_name);
    }
    productFocusImagesService.updateProductFocusImages(productFocusImages);
    FileUtil.alertMessageBySkip(
        "修改成功！",
        "productFocusImagesSellerWeb.do?productFocusImages.productId="
            + productFocusImages.getProductId());
    return null;
  }

  // 跳转到商品焦点图片添加
  public String addProductFocusImages() throws Exception {
    return SUCCESS;
  }

  // 商品焦点图片添加
  public String insertProductFocusImages() throws Throwable {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "doLoginWeb.do");
      return null;
    }
    if (productFocusImages != null && !"".equals(productFocusImages.getProductId())) {
      long uid = sysLogin.getId();
      int max = 0;
      List<ProductFocusImagesPojo> productFocusImagePojos =
          productFocusImagesService.productFocusImagesAllList(productFocusImages, null);
      if (productFocusImagePojos.size() > 0) {
        max = productFocusImagePojos.get(productFocusImagePojos.size() - 1).getSorting();
      }
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/", upfile);
        productFocusImages.setImages(file_name);
        productFocusImages.setStatus(1);
        productFocusImages.setSorting(max + 1);
        productFocusImages.setUserId(uid);
        productFocusImages.setCreateBy(uid);
        productFocusImages.setUpdateBy(uid);
        productFocusImagesService.insertProductFocusImages(productFocusImages);
      }
      if (upfile2 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/", upfile2);
        productFocusImages2.setImages(file_name);
        productFocusImages2.setStatus(1);
        productFocusImages2.setSorting(max + 2);
        productFocusImages2.setUserId(uid);
        productFocusImages2.setCreateBy(uid);
        productFocusImages2.setUpdateBy(uid);
        productFocusImagesService.insertProductFocusImages(productFocusImages2);
      }

      if (upfile3 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/", upfile3);
        productFocusImages3.setImages(file_name);
        productFocusImages3.setStatus(1);
        productFocusImages3.setSorting(max + 3);
        productFocusImages3.setUserId(uid);
        productFocusImages3.setCreateBy(uid);
        productFocusImages3.setUpdateBy(uid);
        productFocusImagesService.insertProductFocusImages(productFocusImages3);
      }
      if (upfile4 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/", upfile4);
        productFocusImages4.setImages(file_name);
        productFocusImages4.setStatus(1);
        productFocusImages4.setSorting(max + 4);
        productFocusImages4.setUserId(uid);
        productFocusImages4.setCreateBy(uid);
        productFocusImages4.setUpdateBy(uid);
        productFocusImagesService.insertProductFocusImages(productFocusImages4);
      }

      if (upfile5 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/", upfile5);
        productFocusImages5.setImages(file_name);
        productFocusImages5.setStatus(1);
        productFocusImages5.setSorting(max + 5);
        productFocusImages5.setUserId(uid);
        productFocusImages5.setCreateBy(uid);
        productFocusImages5.setUpdateBy(uid);
        productFocusImagesService.insertProductFocusImages(productFocusImages5);
      }

      FileUtil.alertMessageBySkip(
          "新增成功！",
          "productFocusImagesSellerWeb.do?productFocusImages.productId="
              + productFocusImages.getProductId());
    }
    return null;
  }

  public Double getDeductScoreAll() {
    return deductScoreAll;
  }

  public void setDeductScoreAll(Double deductScoreAll) {
    this.deductScoreAll = deductScoreAll;
  }

  /**
   * 商品预览页面
   * 
   * @return
   * @throws Exception
   */
  public String goPreviewWeb() throws Exception {
    if (productPojo != null) {
      productList = productService.getProductAll(productPojo, page);
      if (productList.size() != 0) {
        productPojo = productList.get(0);
        productImagesPojo = new ProductImagesPojo();
        productImagesPojo.setProductId(productPojo.getId());
        productImagesList = productImagesService.getProductImagesAll(productImagesPojo, page);
        productFocusImagesList =
            productFocusImagesService.getProductFocusImagesByPid(productPojo.getId());
        productSkuLinkPojo = new ProductSkuLinkPojo();
        productSkuLinkPojo.setType(0);
        productSkuLinkPojo.setProductId(productPojo.getId());
        productSkuLinkPojo.setStatus(1);
        productSkuLinkPojos = productSkuLinkService.getProductSkuLinkAll(productSkuLinkPojo, page);
        List<ProductSkuLinkPojo> skuColorList =
            productSkuLinkService.findSkuColorByProductId(productSkuLinkPojo);
        List<ProductSkuLinkPojo> skuFormatList =
            productSkuLinkService.findSkuFormatByProductId(productSkuLinkPojo);
        userBrandPojo = userBrandService.findUserBrandById(productPojo.getUserBrandId());
        List<ProductSkuLinkPojo> pslList = productSkuLinkPojos;
        Map<Long, SkuAttributePojo> colorMap = new LinkedHashMap<Long, SkuAttributePojo>();
        Map<Long, SkuAttributePojo> fromatMap = new LinkedHashMap<Long, SkuAttributePojo>();
        for (int i = 0; i < pslList.size(); i++) {
          SkuAttributePojo colors =
              skuAttributeService.getSkuAttributeById(pslList.get(i).getSkuColorId());
          SkuAttributePojo formats =
              skuAttributeService.getSkuAttributeById(pslList.get(i).getSkuFormatId());
          pslList.get(i).setColorAttr(colors);
          pslList.get(i).setFormatAttr(formats);
          colorMap.put(pslList.get(i).getSkuColorId(), colors);
          fromatMap.put(pslList.get(i).getSkuFormatId(), formats);
        }
        JSONArray json = JSONArray.fromObject(pslList);
        String pslListJson = json.toString();

        Map skuStockMap = new LinkedHashMap<>();
        Map skuPriceMap = new LinkedHashMap<>();
        Map skuIdMap = new LinkedHashMap<>();

        for (ProductSkuLinkPojo temp : pslList) {
          skuStockMap.put(temp.getSkuColorId() + "_" + temp.getSkuFormatId(), temp.getStock());
          skuPriceMap.put(temp.getSkuColorId() + "_" + temp.getSkuFormatId(), temp.getPrice());
          skuIdMap.put(temp.getSkuColorId() + "_" + temp.getSkuFormatId(), temp.getId());
        }
        JSONArray skuStockJson = JSONArray.fromObject(skuStockMap);
        String skuStockStr = skuStockJson.toString();

        JSONArray skuPriceJson = JSONArray.fromObject(skuPriceMap);
        String skuPriceStr = skuPriceJson.toString();

        JSONArray skuIdJson = JSONArray.fromObject(skuIdMap);
        String skuIdStr = skuIdJson.toString();

        Comparator<SkuAttributePojo> comparator = new Comparator<SkuAttributePojo>() {
          @Override
          public int compare(SkuAttributePojo s1, SkuAttributePojo s2) {
            return s2.getSorting() - s1.getSorting();
          }
        };

        ActionContext ac = ActionContext.getContext();
        ac.put("skuStockStr", skuStockStr);
        ac.put("skuPriceStr", skuPriceStr);
        ac.put("skuIdStr", skuIdStr);
        ac.put("productImagesList", productImagesList);
        ac.put("productFocusImagesList", productFocusImagesList);
        ac.put("pslListJson", pslListJson);
        ac.put("pslList", pslList);
        List<SkuAttributePojo> skuColors = new ArrayList<SkuAttributePojo>(colorMap.values());
        List<SkuAttributePojo> skuFormats = new ArrayList<SkuAttributePojo>(fromatMap.values());
        Collections.sort(skuColors, comparator);
        Collections.sort(skuFormats, comparator);
        ac.put("skuColors", skuColors);
        ac.put("skuFormats", skuFormats);
        ac.put("skuColorList", skuColorList);
        ac.put("skuFormatList", skuFormatList);
      }
    }
    return SUCCESS;
  }

  public List<ProductSkuLinkPojo> getProductSkuLinkPojos() {
    return productSkuLinkPojos;
  }

  public void setProductSkuLinkPojos(List<ProductSkuLinkPojo> productSkuLinkPojos) {
    this.productSkuLinkPojos = productSkuLinkPojos;
  }

  /**
   * 售后管理页面
   * 
   * @return
   * @throws Exception
   */
  public String goRefundWeb() throws Exception {
    SysLoginPojo logiPojo = UserUtil.getWebUser();
    if (logiPojo == null) {
      FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
      return null;
    }
    Map<String, Object> map = new HashMap<String, Object>();
    int count = 0;
    if (logiPojo != null) {
      /*
       * ShopPojo shopPojo = new ShopPojo(); shopPojo.setUserId(logiPojo.getId()); shopPojo =
       * shopService.findShop(shopPojo); if (shopPojo != null) { map.put("shopId",
       * shopPojo.getId()); }else { map.put("shopId", -1); }
       */
      map.put("userId", logiPojo.getId());
      if (userOrderRefundPojo != null) {
        map.put("status", userOrderRefundPojo.getStatus());
        map.put("orderNo", userOrderRefundPojo.getOrderNo());
        // map.put("serviceInvolved",
        // userOrderRefundPojo.getServiceInvolved());
      }
      count = userOrderRefundService.userOrderRefundAllCount2(map);
    }
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(count);
    return SUCCESS;
  }

  /**
   * 售后管理页面数据
   * 
   * @return
   * @throws Exception
   */
  public String getUserOrderRefundList() throws Exception {
    SysLoginPojo logiPojo = UserUtil.getWebUser();
    if (page == null) {
      page = new Pager();
    }
    if (logiPojo != null) {
      userOrderRefundPojo.setUserId(logiPojo.getId());
      /*
       * ShopPojo shopPojo = new ShopPojo(); shopPojo.setUserId(logiPojo.getId()); shopPojo =
       * shopService.findShop(shopPojo); if (shopPojo != null) {
       * userOrderRefundPojo.setShopId(shopPojo.getId()); }else {
       * userOrderRefundPojo.setShopId(-1l); }
       */
      if (userOrderRefundPojo != null) {
        userOrderRefundPojo.setStatus(userOrderRefundPojo.getStatus());
        userOrderRefundPojo.setOrderNo(userOrderRefundPojo.getOrderNo());
        // userOrderRefundPojo.setServiceInvolved(userOrderRefundPojo.getServiceInvolved());
      }
      page.setPageSize(10);
      userOrderRefundPojos =
          userOrderRefundService.findUserOrderRefundByUserId2(userOrderRefundPojo, page);
    }
    JSONArray json = JSONArray.fromObject(userOrderRefundPojos);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 根据订单id查询退货商品
   */

  public String findUserOrderRefundListByorderId() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    SysLoginPojo logiPojo = UserUtil.getWebUser();
    if (logiPojo != null) {
      UserOrderRefundPojo userOrderRefundPojo = new UserOrderRefundPojo();
      userOrderRefundPojo.setOrderId(orderId);
      if (uorStatus != 0) {
        userOrderRefundPojo.setStatus(uorStatus);
      }
      userOrderRefundPojo.setServiceInvolved(serviceInvolved);

      userOrderRefundPojos =
          userOrderRefundService.findUserOrderRefundListByorderId(userOrderRefundPojo);
    }
    JSONArray json = JSONArray.fromObject(userOrderRefundPojos);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /***
   * 商家中心退货审核页面
   * 
   * @return
   * @throws Exception
   */
  public String checkOrderRefundWeb() throws Exception {
    // 填写退货信息
    if (is.equals("3")) {
      userOrderRefundPojo.setStatus(3);
      userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
      orderDetailService.updateReStatus(userOrderRefundPojo.getDetailId(), 3);
      FileUtil.alertMessageBySkip("操作成功！", "goRefundWeb.do");
    }
    // 审核申请成功
    else if (is.equals("2")) {
      userOrderRefundPojo.setStatus(2);
      userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("id", userOrderRefundPojo.getDetailId());
      map.put("reStatus", 2);
      map.put("status", 0);
      orderDetailService.updateReStatusmap(map);
      FileUtil.alertMessageBySkip("操作成功！", "goRefundWeb.do");
    }
    // 退货成功
    else if (is.equals("4")) {
      userOrderRefundPojo.setStatus(4);
      userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("id", userOrderRefundPojo.getDetailId());
      map.put("reStatus", 4);
      map.put("status", "0");
      orderDetailService.updateReStatusmap(map);
      FileUtil.alertMessageBySkip("操作成功！", "goRefundWeb.do");
    }
    // 退货失败
    else if (is.equals("5")) {
      userOrderRefundPojo.setStatus(5);
      userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
      orderDetailService.updateReStatus(userOrderRefundPojo.getDetailId(), 5);
      FileUtil.alertMessageBySkip("操作成功！", "goRefundWeb.do");
    }
    // 审核失败
    else if (is.equals("6")) {
      userOrderRefundPojo.setStatus(6);
      userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
      orderDetailService.updateReStatus(userOrderRefundPojo.getDetailId(), 6);
      FileUtil.alertMessageBySkip("操作成功！", "goRefundWeb.do");
    }
    // 退款成功
    else if (is.equals("7")) {
      userOrderRefundPojo.setStatus(7);
      userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("id", userOrderRefundPojo.getDetailId());
      map.put("reStatus", 7);
      map.put("status", "0");
      orderDetailService.updateReStatusmap(map);
      FileUtil.alertMessageBySkip("操作成功！", "goRefundWeb.do");
    }
    return null;
  }

  /***
   * 商家中心售后管理申请客服介入
   * 
   * @return
   * @throws Exception
   */
  public String updateServiceInvolvedById() throws Exception {
    if (userOrderRefundPojo != null && !"".equals(userOrderRefundPojo.getId())) {
      userOrderRefundPojo.setServiceInvolved(2);
      userOrderRefundService.checkUserOrderRefund(userOrderRefundPojo);
    }
    FileUtil.alertMessageBySkip("申请成功！", "goRefundWeb.do");
    return null;
  }

  /**
   * 查询物流
   * 
   * @return
   * @throws Exception
   */
  public String goRefundExpressSearch() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map1 = null;
    List list = new ArrayList();
    String url =
        "http://api.kuaidi.com/openapi.html?id=481ea0389df9f6b101f7fd8af272fbef&com=" + type
            + "&nu=" + postid;
    String str = loadJson(url);
    JSONObject jsonobject = JSONObject.fromObject(str);
    if (!jsonobject.get("success").equals("false") && !jsonobject.get("status").equals("")) {
      map.put("expressType", type);
      map.put("expressNumber", postid);
      map.put("status", jsonobject.getString("status"));
      map.put("reason", jsonobject.getString("reason"));
      JSONArray jsonarray = jsonobject.getJSONArray("data");
      for (int i = 0; i < jsonarray.size(); i++) {
        map1 = new HashMap<String, Object>();
        map1.put("context", jsonarray.getJSONObject(i).get("context"));
        map1.put("time", jsonarray.getJSONObject(i).get("time"));
        list.add(map1);
      }
      map.put("data", list);
    } else {
      map.put("status", jsonobject.getString("status"));
      map.put("reason", jsonobject.getString("reason"));
      // map.put("message","单号不存在或者已经过期");
      map.put("result", 1);
    }
    JSONObject json = JSONObject.fromObject(map);
    ActionContext actionContext = ActionContext.getContext();
    actionContext.put("result", json);
    return SUCCESS;
  }

  // 将地址接收到的json转成str
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

  // 查询商品所属专场是否为排期完成
  public String findSpecialProductByPid() throws SQLException {
    int pn = productService.findSpecialProductByPid(productId);
    ActionContext actionContext = ActionContext.getContext();
    actionContext.put("result", pn);
    return SUCCESS;
  }

  /***
   * 添加产品信息
   * 
   * @return
   * @throws Throwable
   */
  public String productAddContent() throws Throwable {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
      return null;
    }
    try {
      // productPojo.setVersion(1);
      productService.productUpdateSeller(productPojo);
      FileUtil.alertMessageBySkip("编辑成功", "productManageSellerWeb.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("编辑失败", "goProductAddSellerWeb.do");
    }
    return null;
  }
}
