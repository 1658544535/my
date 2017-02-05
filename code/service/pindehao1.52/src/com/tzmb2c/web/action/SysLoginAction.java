package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.business.service.WalletService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.MD5Util;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.utils.export.excel.ExcelProcessor;
import com.tzmb2c.web.pojo.AgencyPojo;
import com.tzmb2c.web.pojo.ConsumerPojo;
import com.tzmb2c.web.pojo.ManufacturerPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.PurchaserAttentionPojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SysAreaPojo;
import com.tzmb2c.web.pojo.SysLoginExcelPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserInfoPojo;
import com.tzmb2c.web.pojo.UserWalletPojo;
import com.tzmb2c.web.service.AgencyService;
import com.tzmb2c.web.service.ConsumerService;
import com.tzmb2c.web.service.HistoryService;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.PurchaserAttentionService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SysAreaService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserInfoService;
import com.tzmb2c.web.service.UserVerifyService;
import com.tzmb2c.web.service.UserWalletService;

public class SysLoginAction extends SuperAction {

  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private ConsumerService consumerService;
  @Autowired
  private ManufacturerService manufacturerService;
  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private ProductService productService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private PurchaserAttentionService purchaserAttentionService;
  @Autowired
  private HistoryService historyService;
  @Autowired
  private UserVerifyService userVerifyService;
  @Autowired
  private AgencyService agencyService;
  @Autowired
  private ShopService shopService;
  @Autowired
  private SysAreaService sysAreaService;
  @Autowired
  private WalletService walletService;
  @Autowired
  private UserWalletService userWalletService;
  @Autowired
  private ProductTypeService productTypeService;

  private PurchaserAttentionPojo purchaserAttentionPojo;
  private SysLoginPojo sysLogin, sysLogin1;
  private String result;
  private String[] tids;
  private String loginId;
  private String loginPd;
  private String newPassword;
  private String os;
  private ConsumerPojo consumer;
  private List<ProductPojo> productList;
  private List<OrderPojo> myWuLiuOrderListWeb;
  private OrderPojo orderPojo;
  private AgencyPojo agency;
  private File upfile, upfile2;
  private ManufacturerPojo manufacturer;
  private ShopPojo shop;
  private UserInfoPojo userInfoPojo;
  private String beganday;
  private List<ProductTypePojo> productTypePojos;
  private String type;



  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<ProductTypePojo> getProductTypePojos() {
    return productTypePojos;
  }

  public void setProductTypePojos(List<ProductTypePojo> productTypePojos) {
    this.productTypePojos = productTypePojos;
  }

  public String getBeganday() {
    return beganday;
  }

  public void setBeganday(String beganday) {
    this.beganday = beganday;
  }

  public UserInfoPojo getUserInfoPojo() {
    return userInfoPojo;
  }

  public void setUserInfoPojo(UserInfoPojo userInfoPojo) {
    this.userInfoPojo = userInfoPojo;
  }

  public OrderPojo getOrderPojo() {
    return orderPojo;
  }

  public void setOrderPojo(OrderPojo orderPojo) {
    this.orderPojo = orderPojo;
  }

  public List<OrderPojo> getMyWuLiuOrderListWeb() {
    return myWuLiuOrderListWeb;
  }

  public void setMyWuLiuOrderListWeb(List<OrderPojo> myWuLiuOrderListWeb) {
    this.myWuLiuOrderListWeb = myWuLiuOrderListWeb;
  }

  public PurchaserAttentionPojo getPurchaserAttentionPojo() {
    return purchaserAttentionPojo;
  }

  public void setPurchaserAttentionPojo(PurchaserAttentionPojo purchaserAttentionPojo) {
    this.purchaserAttentionPojo = purchaserAttentionPojo;
  }

  public List<ProductPojo> getProductList() {
    return productList;
  }

  public void setProductList(List<ProductPojo> productList) {
    this.productList = productList;
  }

  public SysLoginPojo getSysLogin1() {
    return sysLogin1;
  }

  public void setSysLogin1(SysLoginPojo sysLogin1) {
    this.sysLogin1 = sysLogin1;
  }

  public ConsumerPojo getConsumer() {
    return consumer;
  }

  public void setConsumer(ConsumerPojo consumer) {
    this.consumer = consumer;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public SysLoginPojo getSysLogin() {
    return sysLogin;
  }

  public void setSysLogin(SysLoginPojo sysLogin) {
    this.sysLogin = sysLogin;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public File getUpfile2() {
    return upfile2;
  }

  public void setUpfile2(File upfile2) {
    this.upfile2 = upfile2;
  }

  public ManufacturerPojo getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(ManufacturerPojo manufacturer) {
    this.manufacturer = manufacturer;
  }

  public ShopPojo getShop() {
    return shop;
  }

  public void setShop(ShopPojo shop) {
    this.shop = shop;
  }

  // TODO 传递数据供应商/采购商
  public String getSysLoginCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    int count = sysLoginService.sysLoginAllCount(sysLogin, os);
    page.setRowCount(count);
    return SUCCESS;
  }

  public String userInfoAllCount2() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (userInfoPojo != null) {
      map.put("loginName", userInfoPojo.getLoginName());
      map.put("userName", userInfoPojo.getUserName());
      map.put("QQ", userInfoPojo.getQQ());
    }
    map.put("type", "1");
    page.setRowCount(userInfoService.userInfoAllCount2(map));
    return SUCCESS;
  }

  public String userInfoAllList2() {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      map.put("pageNo", 0);
      map.put("pageSize", 10);
    } else {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    if (userInfoPojo != null) {
      map.put("loginName", userInfoPojo.getLoginName());
      map.put("userName", userInfoPojo.getUserName());
      map.put("QQ", userInfoPojo.getQQ());
    }
    map.put("type", "1");
    JSONArray json = JSONArray.fromObject(userInfoService.userInfoAllList2(map));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String checkUserInfo() throws SQLException {
    try {
      userInfoService.checkUserInfo(userInfoPojo.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  /**
   * 审核全部
   * 
   * @return
   * @throws SQLException
   * @throws NumberFormatException
   */
  public String userInfocheckAll() throws NumberFormatException, SQLException {
    if (tids != null) {
      for (String tid : tids) {
        userInfoService.checkUserInfo(Long.parseLong(tid));
      }
      FileUtil.alertMessageBySkip("审核成功！", "normalUserInfo.do");
    } else {
      FileUtil.alertMessageBySkip("审核失败！", "normalUserInfo.do");
    }
    return null;
  }

  /***
   * 查找单条
   * 
   * @return
   * @throws Exception
   */
  public String goFindUserInfo() throws Exception {
    ActionContext ac = ActionContext.getContext();
    userInfoPojo = userInfoService.getUserInfoById(userInfoPojo.getId());
    ac.put("sex", sysDictService.getSysDictListByType("sex"));
    ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("scale", sysDictService.getSysDictListByType("scale"));
    ac.put("channel", sysDictService.getSysDictListByType("channel"));
    ac.put("isread", sysDictService.getSysDictListByType("isread"));
    return SUCCESS;
  }

  public String userInfoUpdate() throws Throwable {
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/userlogo")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/userlogo/", upfile);
      userInfoPojo.setImage(file_name);
    }
    if (upfile2 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/userInfo")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/userInfo/", upfile2);
      userInfoPojo.setQrCode(file_name);
    }
    if (beganday != null) {
      userInfoPojo.setBirthday(beganday);
    }
    userInfoService.updateUserInfo2(userInfoPojo);
    FileUtil.alertMessageBySkip("修改成功！", "normalUserInfo.do");
    return null;
  }

  // 批发商
  public String getSysLoginWS() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(agencyService.getSysLoginAG(agency, os));
    return SUCCESS;
  }


  public String sysLoginAllList() {

    JSONArray json = null;
    try {
      json = JSONArray.fromObject(sysLoginService.sysLoginAllList(sysLogin, page, os));
    } catch (SQLException e) {

      e.printStackTrace();
    }
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String sysLoginAGAllList() {

    JSONArray json = JSONArray.fromObject(agencyService.sysLoginAGAllList(agency, page, os));
    page.setResult(json.toString());

    return SUCCESS;
  }

  // TODO 传递数据到代理商
  /**
   * @return
   * @throws Exception
   */

  public String sysLoginDeleteId() {
    if (tids != null) {
      sysLoginService.sysLoginDeleteId(tids);
      // FileUtil.alertMessageBySkip("删除成功", "gomanageTicketRule.do");
      if (os.equals("2")) {
        FileUtil.alertMessageBySkip("审核成功！", "sysLoginMF.do?os=" + os);
      } else if (os.equals("3")) {
        FileUtil.alertMessageBySkip("审核成功！", "sysLoginCS.do?os=" + os);
      } else if (os.equals("6")) {
        FileUtil.alertMessageBySkip("审核成功！", "sysLoginAG.do?os=" + os);
      } else {
        FileUtil.alertMessageBySkip("审核成功！", "sysLogin.do?os=" + os);
      }

    } else {
      if (os.equals("2")) {
        FileUtil.alertMessageBySkip("审核失败！", "sysLoginMF.do?os=" + os);
      } else if (os.equals("3")) {
        FileUtil.alertMessageBySkip("审核失败！", "sysLoginCS.do?os=" + os);
      } else if (os.equals("6")) {
        FileUtil.alertMessageBySkip("审核失败！", "sysLoginAG.do?os=" + os);
      } else {
        FileUtil.alertMessageBySkip("审核失败！", "sysLogin.do?os=" + os);
      }
    }

    return null;
  }

  public String deleSysLogin() throws SQLException {
    try {
      sysLoginService.delSysLogin(sysLogin.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  public String goFindSysLogin() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("sysLoginPojo", sysLoginService.findSysLoginById(sysLogin.getId()));
    ac.put("type", sysDictService.getSysDictListByType("sys_login_type"));
    ac.put("status", sysDictService.getSysDictListByType("status"));
    return SUCCESS;
  }

  public String updateSysLogin() throws Exception {

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      sysLogin.preUpdate(loginPojo);
    }
    sysLoginService.updateSysLogin(sysLogin);
    if (os.equals("2")) {
      FileUtil.alertMessageBySkip("修改成功！", "sysLoginMF.do?os=" + os);
    } else if (os.equals("3")) {
      FileUtil.alertMessageBySkip("修改成功！", "sysLoginCS.do?os=" + os);
    } else if (os.equals("6")) {
      FileUtil.alertMessageBySkip("修改成功！", "sysLoginAG.do?os=" + os);
    } else {
      FileUtil.alertMessageBySkip("修改成功！", "sysLogin.do?os=" + os);
    }

    return null;
  }

  public String updatePasswordlogin() throws Exception {
    try {
      sysLogin.setPassword(MD5Util.MD5("123456"));
      sysLoginService.updatePassword(sysLogin);
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  public String updateStatus() throws Exception {
    if (sysLogin.getStatus() == 0) {
      sysLogin.setStatus(1);
      sysLoginService.updateStatus(sysLogin);
      if (os.equals("2")) {
        FileUtil.alertMessageBySkip("解冻成功！", "sysLoginMF.do?os=" + os);
      } else if (os.equals("3")) {
        FileUtil.alertMessageBySkip("解冻成功！", "sysLoginCS.do?os=" + os);
      } else if (os.equals("6")) {
        FileUtil.alertMessageBySkip("解冻成功！", "sysLoginAG.do?os=" + os);
      } else {
        FileUtil.alertMessageBySkip("解冻成功！", "sysLogin.do?os=" + os);
      }
    } else {
      sysLogin.setStatus(0);
      sysLoginService.updateStatus(sysLogin);
      if (os.equals("2")) {
        FileUtil.alertMessageBySkip("冻结成功！", "sysLoginMF.do?os=" + os);
      } else if (os.equals("3")) {
        FileUtil.alertMessageBySkip("冻结成功！", "sysLoginCS.do?os=" + os);
      } else if (os.equals("6")) {
        FileUtil.alertMessageBySkip("冻结成功！", "sysLoginAG.do?os=" + os);
      } else {
        FileUtil.alertMessageBySkip("冻结成功！", "sysLogin.do?os=" + os);
      }
    }


    return null;
  }

  public String delSysLogin() throws Exception {
    sysLoginService.deleteSysLogin(sysLogin.getId());
    userInfoService.delUserInfobyUserId(sysLogin.getId());
    if (os.equals("2")) {
      manufacturerService.deleteManufacturer(sysLogin.getId());
      FileUtil.alertMessageBySkip("删除成功！", "sysLoginMF.do?os=" + os);
    } else if (os.equals("3")) {
      consumerService.deleteConsumer(sysLogin.getId());
      FileUtil.alertMessageBySkip("删除成功！", "sysLoginCS.do?os=" + os);
    } else if (os.equals("6")) {
      agencyService.deleteAgency(sysLogin.getId());
      FileUtil.alertMessageBySkip("删除成功！", "sysLoginAG.do?os=" + os);
    } else {
      FileUtil.alertMessageBySkip("删除成功！", "sysLogin.do?os=" + os);
    }
    return null;
  }

  public String addSysLogin() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("type", sysDictService.getSysDictListByType("sys_login_type"));
    ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("scale", sysDictService.getSysDictListByType("scale"));
    ac.put("channel", sysDictService.getSysDictListByType("channel"));
    ac.put("isread", sysDictService.getSysDictListByType("isread"));
    // ac.put("mainCategory", sysDictService.getSysDictListByType("main_category"));
    ProductTypePojo productTypePojo = new ProductTypePojo();
    productTypePojo.setPid(-1l);
    productTypePojo.setStatus(1);
    productTypePojos = productTypeService.getProductTypeByPid(productTypePojo);
    for (int i = 0; i < productTypePojos.size(); i++) {
      if (productTypePojos.get(i).getName().equals("0-3岁婴幼儿玩具")
          || productTypePojos.get(i).getName().equals("3-6岁儿童玩具")
          || productTypePojos.get(i).getName().equals("6岁以上玩具")) {
        productTypePojos.remove(i);
      }
    }
    ac.put("mainCategory", productTypePojos);
    ac.put("isNew", sysDictService.getSysDictListByType("yes_no"));
    return SUCCESS;
  }

  public String insertSysLogin() throws Throwable {
    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      sysLogin.prePersist(loginPojo);
    }
    sysLogin.setPassword(MD5Util.MD5(sysLogin.getPassword()));
    sysLogin.setType(os);
    sysLogin.setStatus(1);
    String externalCode = sysLoginService.genExternalSignCode(sysLogin.getLoginname());
    sysLogin.setExternalSignCode(externalCode);
    sysLogin.setInvitationCode(walletService.genInviteCode());
    sysLoginService.insertSysLogin(sysLogin);
    sysLogin1 = sysLoginService.sysLoginFindId(sysLogin);
    // 添加用户信息
    UserInfoPojo userInfo = new UserInfoPojo();
    userInfo.setUserId(sysLogin1.getId());
    userInfo.setCreateDate(new Date());
    userInfo.setUpdateDate(new Date());
    userInfo.setCreateBy(1l);
    userInfo.setUpdateBy(1l);
    userInfoService.insertUserInfo(userInfo);

    // 用户钱包表插入记录
    UserWalletPojo userWalletPojo = new UserWalletPojo();
    userWalletPojo.setUserId(sysLogin1.getId());
    userWalletPojo.setBalance(0.00);
    userWalletPojo.setTotalBalance(0.00);
    userWalletPojo.setCreateBy(sysLogin1.getId());
    userWalletPojo.setUpdateBy(sysLogin1.getId());
    userWalletService.insertUserWallet(userWalletPojo);

    // 采购商/供应商/批发商添加数据
    if (os.equals("3")) {
      consumer = new ConsumerPojo();
      consumer.setUserId(sysLogin1.getId());
      consumerService.insertConsumer(consumer);
      FileUtil.alertMessageBySkip("新增成功！", "sysLoginCS.do?os=" + os);
    } else if (os.equals("2")) {
      // ManufacturerPojo manufacturer = new ManufacturerPojo();
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/manufacturer")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/manufacturer/", upfile);
        manufacturer.setLogo(file_name);
      }

      manufacturer.setUserId(sysLogin1.getId());
      manufacturer.setCreateBy(sysLogin1.getId());
      SysAreaPojo sysArea = sysAreaService.getNameById(shop.getProvince());
      String province = sysArea == null ? "" : sysArea.getName();
      sysArea = sysAreaService.getNameById(shop.getCity());
      String city = sysArea == null ? "" : sysArea.getName();
      sysArea = sysAreaService.getNameById(shop.getArea());
      String area = sysArea == null ? "" : sysArea.getName();
      manufacturer.setAddress(province + city + area + shop.getAddress());
      manufacturerService.insertManufacturer(manufacturer);

      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + "1.jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/shop") + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/shop/", upfile);
        shop.setImages(file_name);
      }

      if (upfile2 != null) {
        String file_name = StringUtil.getCurrentDateStr() + "2.jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/shop") + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/shop/", upfile2);
        shop.setTopImage(file_name);
      }
      shop.setUserId(sysLogin1.getId());
      shop.setCreateBy(sysLogin1.getId());
      shop.setCreateDate(new Date());
      shop.setRecommendType(0l);
      shop.setLocationCertification("1");
      shop.setIdentityCertification("1");
      shop.setSorting(1000);
      shop.setMainCategory(manufacturer.getMainCategory());
      shop.setSalesArea(manufacturer.getSalesArea());
      shop.setMainProduct(manufacturer.getMainProduct());
      shop.setStatus(manufacturer.getStatus());
      shop.setName(manufacturer.getCompany());
      shop.setContent(manufacturer.getContent());
      shop.setPhone(manufacturer.getPhone());
      shopService.insertShop(shop);

      FileUtil.alertMessageBySkip("新增成功！", "sysLoginMF.do?os=" + os);
    } else if (os.equals("6")) {
      AgencyPojo agencyPojo = new AgencyPojo();
      agencyPojo.setUserId(sysLogin1.getId());
      agencyService.insertAgency(agencyPojo);
      FileUtil.alertMessageBySkip("新增成功！", "sysLoginAG.do?os=" + os);
    } else {
      FileUtil.alertMessageBySkip("新增成功！", "sysLogin.do?os=" + os);
    }


    return null;
  }

  /**
   * 生成标识码
   * 
   * @return
   * @throws SQLException
   */
  public String genExternalCode() throws SQLException {
    List<SysLoginPojo> sysLoginPojos = sysLoginService.getExternalCodeNullList();
    if (sysLoginPojos != null && sysLoginPojos.size() > 0) {
      for (SysLoginPojo sysLoginPojo : sysLoginPojos) {
        sysLoginService.updateSysLoginForExternalCode(sysLoginPojo);
      }
    }
    if ("1".equals(os)) {
      FileUtil.alertMessageBySkip("更新完成！", "sysLogin.do?os=" + os);
    } else if ("2".equals(os)) {
      FileUtil.alertMessageBySkip("更新完成！", "sysLoginMF.do?os=" + os);
    } else if ("6".equals(os)) {
      FileUtil.alertMessageBySkip("更新完成！", "sysLoginAG.do?os=" + os);
    } else {
      FileUtil.alertMessageBySkip("更新完成！", "sysLogin.do?os=" + os);
    }

    return null;
  }

  public String toUpdatePassword() throws Exception {
    return SUCCESS;
  }

  public String updatePassword() throws Exception {

    SysLoginPojo loginPojo = new SysLoginPojo();
    loginPojo.setLoginname(loginId);
    loginPojo.setPassword(MD5Util.MD5(loginPd));
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setCharacterEncoding("UTF-8");
    if (sysLoginService.loginCheckService(loginPojo)) {
      loginPojo = sysLoginService.findSysLoginByLoginname(loginPojo);
      loginPojo.setPassword(MD5Util.MD5(newPassword));
      sysLoginService.updatePassword(loginPojo);
      response.getWriter().print("{\"flag\":1}");
    } else {
      response.getWriter().print("{\"flag\":0}");
    }
    return null;
  }

  public String getLoginId() {
    return loginId;
  }

  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }

  public String getLoginPd() {
    return loginPd;
  }

  public void setLoginPd(String loginPd) {
    this.loginPd = loginPd;
  }

  public String myConsumerWeb() throws Exception {
    ActionContext actionContext = ActionContext.getContext();
    SysLoginPojo sysLogin = (SysLoginPojo) actionContext.getSession().get("wuser");
    // sysLogin = UserUtil.getWebUser();
    purchaserAttentionPojo = purchaserAttentionService.attentionByUserId(sysLogin.getId());
    List<ProductPojo> productAnList = new ArrayList<ProductPojo>();
    if (purchaserAttentionPojo != null) {
      productAnList = productService.productForType(purchaserAttentionPojo.getProductTypeId());
    } else {
      ProductPojo productPojo = new ProductPojo();
      productPojo.setIsNew("1");
      productAnList = productService.getProductLimit(productPojo, 4);
    }
    OrderPojo orderPojo3 = new OrderPojo();
    orderPojo3 = orderService.orderStatusNum(sysLogin.getId());
    // 我的物流
    orderPojo = new OrderPojo();
    orderPojo.setUserId(sysLogin.getId());
    myWuLiuOrderListWeb = orderService.myWuLiuWeb(orderPojo);
    ActionContext ac = ActionContext.getContext();
    ac.put("sysLoginPojo", sysLoginService.findSysLoginById(sysLogin.getId()));
    ac.put("productAnList", productAnList);
    ac.put("productHaList", historyService.historyUserList(sysLogin.getId()));
    ac.put("orderPojo", orderPojo3);
    ac.put("myWuLiuOrderListWeb", myWuLiuOrderListWeb);
    return SUCCESS;
  }

  /**
   * 注册校验
   * 
   * @return
   * @throws Exception
   */
  public String validLoginRegiste() throws Exception {
    try {
      if (sysLogin != null) {
        if (sysLogin.getLoginname() != null && !"".equals(sysLogin.getLoginname())) {
          // 判断会员帐号是否注册过
          SysLoginPojo sysLoginPojobyloginname =
              sysLoginService.getSysLoginByLoginName(sysLogin.getLoginname());
          if (sysLoginPojobyloginname != null) {
            result = "0";
          } else {
            result = "1";
          }
        } else if (sysLogin.getName() != null && !"".equals(sysLogin.getName())) {
          // 判断会员名称是否注册过
          List<SysLoginPojo> sysLoginPojobuname =
              sysLoginService.getSysLoginByName(sysLogin.getName());
          if (sysLoginPojobuname != null && sysLoginPojobuname.size() > 0) {
            result = "0";
          } else {
            result = "1";
          }
        }
      } else {
        result = "0";
      }
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  /**
   * 生成分享邀请码
   * 
   * @return
   * @throws SQLException
   */
  public String genInvitationCode() throws SQLException {
    List<SysLoginPojo> sysLoginPojos = sysLoginService.getInvitationCodeNullList();
    if (sysLoginPojos != null && sysLoginPojos.size() > 0) {
      for (SysLoginPojo sysLoginPojo : sysLoginPojos) {
        sysLoginService.updateSysloginForInvitationCode(sysLoginPojo);
      }
    }
    if ("1".equals(os)) {
      FileUtil.alertMessageBySkip("更新完成！", "sysLogin.do?os=" + os);
    } else if ("2".equals(os)) {
      FileUtil.alertMessageBySkip("更新完成！", "sysLoginMF.do?os=" + os);
    } else if ("6".equals(os)) {
      FileUtil.alertMessageBySkip("更新完成！", "sysLoginAG.do?os=" + os);
    } else {
      FileUtil.alertMessageBySkip("更新完成！", "sysLogin.do?os=" + os);
    }
    return null;
  }

  /**
   * 导出excel
   * 
   * @return
   * @throws SQLException
   */
  public String getSysLoginExcel() throws Exception {
    List<SysLoginExcelPojo> sysLoginList;
    try {
      SysLoginExcelPojo sysLoginExcelPojo = new SysLoginExcelPojo();
      if (sysLogin != null) {
        if (sysLogin.getName() != null && sysLogin.getName() != "") {
          sysLoginExcelPojo.setName(sysLogin.getName());
        }
        if (sysLogin.getLoginname() != null && sysLogin.getLoginname() != "") {
          sysLoginExcelPojo.setLoginname(sysLogin.getLoginname());
        }
        if (sysLogin.getCreateDateStartStr() != null && sysLogin.getCreateDateStartStr() != "") {
          sysLoginExcelPojo.setCreateDateStartStr(sysLogin.getCreateDateStartStr());
        }
        if (sysLogin.getCreateDateEndStr() != null && sysLogin.getCreateDateEndStr() != "") {
          sysLoginExcelPojo.setCreateDateEndStr(sysLogin.getCreateDateEndStr());
        }
        if (sysLogin.getJudgeSource() != null) {
          sysLoginExcelPojo.setJudgeSource(sysLogin.getJudgeSource());
        }

      }
      sysLoginList = sysLoginService.getSysLoginAll2(sysLoginExcelPojo, page, os);
      downloadFileName = "普通用户.xls";
      ExcelProcessor<SysLoginExcelPojo> e =
          new ExcelProcessor<SysLoginExcelPojo>(sysLoginList, SysLoginExcelPojo.class, "普通用户");
      inputStream = e.generateExcelSteam();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public String updateRemarks() throws Exception {
    // remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
    sysLoginService.updateSysLogin(sysLogin);
    return SUCCESS;
  }

  public AgencyPojo getAgency() {
    return agency;
  }

  public void setAgency(AgencyPojo agency) {
    this.agency = agency;
  }

}
