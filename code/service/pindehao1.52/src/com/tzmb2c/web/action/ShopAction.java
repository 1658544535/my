package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.MD5Util;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.ManufacturerPojo;
import com.tzmb2c.web.pojo.PagePushPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductSellPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.SearchKeyPojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SysAreaPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserInfoPojo;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.PagePushService;
import com.tzmb2c.web.service.ProductSellService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.SearchKeyService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SysAreaService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserInfoService;

public class ShopAction extends SuperAction {
  private static final long serialVersionUID = 1L;

  @Autowired
  private ManufacturerService manufacturerService;
  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private ShopService shopService, shopServiceWeb;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private ProductService productService;
  @Autowired
  private SysAreaService sysAreaService;
  @Autowired
  private ProductTypeService productTypeService;
  @Autowired
  private PagePushService pagePushService;
  @Autowired
  private SearchKeyService searchKeyService;
  @Autowired
  private SysLoginAction sysLoginAction;
  @Autowired
  private ProductSellService productSellService;
  private SysLoginPojo sysLogin, sysLogin1;
  private ShopPojo shop, shopWeb;
  private SysAreaPojo sysAreaPojo;
  private String result;
  private String[] tids;
  private File upfile, upfile2;
  private List<ProductPojo> productList, productListYes;
  private List<ShopPojo> shoplist;
  private String jsonStr;
  private List<PagePushPojo> pagePushPojos;
  private int pages;
  private int type;
  private long id;
  private String os;
  private ManufacturerPojo manufacturer;
  private List<ProductTypePojo> productTypePojos;

  public List<ProductTypePojo> getProductTypePojos() {
    return productTypePojos;
  }

  public void setProductTypePojos(List<ProductTypePojo> productTypePojos) {
    this.productTypePojos = productTypePojos;
  }

  public SysLoginPojo getSysLogin() {
    return sysLogin;
  }

  public void setSysLogin(SysLoginPojo sysLogin) {
    this.sysLogin = sysLogin;
  }

  public SysLoginPojo getSysLogin1() {
    return sysLogin1;
  }

  public void setSysLogin1(SysLoginPojo sysLogin1) {
    this.sysLogin1 = sysLogin1;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public File getUpfile2() {
    return upfile2;
  }

  public void setUpfile2(File upfile2) {
    this.upfile2 = upfile2;
  }

  public List<ShopPojo> getShoplist() {
    return shoplist;
  }

  public void setShoplist(List<ShopPojo> shoplist) {
    this.shoplist = shoplist;
  }

  public ShopPojo getShopWeb() {
    return shopWeb;
  }

  public void setShopWeb(ShopPojo shopWeb) {
    this.shopWeb = shopWeb;
  }

  public SysAreaPojo getSysAreaPojo() {
    return sysAreaPojo;
  }

  public void setSysAreaPojo(SysAreaPojo sysAreaPojo) {
    this.sysAreaPojo = sysAreaPojo;
  }

  public List<ProductPojo> getProductList() {
    return productList;
  }

  public void setProductList(List<ProductPojo> productList) {
    this.productList = productList;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public ShopPojo getShop() {
    return shop;
  }

  public void setShop(ShopPojo shop) {
    this.shop = shop;
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

  public String getJsonStr() {
    return jsonStr;
  }

  public void setJsonStr(String jsonStr) {
    this.jsonStr = jsonStr;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }

  public String searchShopWeb() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    shoplist = shopService.shopAllList(shop, page);
    ActionContext ac = ActionContext.getContext();
    ac.put("mainCategory", sysDictService.getSysDictListByType("main_category"));
    ac.put("shoplist", shoplist);
    ac.put("shop", shop);
    ac.put("num", shopService.shopAllCount(shop));
    return SUCCESS;

  }

  // 前端“店铺”搜索方法
  public String searchShopWebByName() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    if (shop.getName() == "搜索 店铺") {
      shop.setName(null);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("type", 2);
    map.put("keyword", shop.getName());

    if (searchKeyService.selectKeyword(map).isEmpty() != true) {
      List<SearchKeyPojo> searchKeyPojos = searchKeyService.selectKeyword(map);
      map.put("hits", searchKeyPojos.get(0).getHits());
      searchKeyService.updateKeyword(map);
    } else {
      map.put("hits", 1);
      searchKeyService.insertKeyword(map);
    }

    shoplist = shopService.shopAllListByName(shop, page);
    ActionContext ac = ActionContext.getContext();
    ac.put("mainCategory", sysDictService.getSysDictListByType("main_category"));
    ac.put("shoplist", shoplist);
    ac.put("shop", shop);
    ac.put("num", shopService.shopAllCountStatus(shop));
    return SUCCESS;



  }

  public String getShopCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    ActionContext ac = ActionContext.getContext();
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
    page.setRowCount(shopService.shopAllCount(shop));
    return SUCCESS;
  }

  public String shopAllList() throws SQLException {
    List<ShopPojo> shopPojos = shopService.shopAllList(shop, page);

    JSONArray json = JSONArray.fromObject(shopPojos);
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String shopChecks() {
    if (tids != null) {
      shopService.shopChecks(tids);
      FileUtil.alertMessageBySkip("审核成功！", "shop.do");
    } else {
      FileUtil.alertMessageBySkip("审核失败！", "shop.do");
    }

    return null;
  }

  public String checkShop() throws SQLException {
    try {
      shopService.checkShop(shop.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  public String addShop() throws Exception {
    if (shopService.findShop(shop) != null) {
      FileUtil.alertMessageBySkip("该供应商已有店铺了！", "shop.do");
      return null;
    } else {
      ActionContext ac = ActionContext.getContext();
      ac.put("shopPojo", shop);
      ac.put("status", sysDictService.getSysDictListByType("status"));
      ac.put("isNew", sysDictService.getSysDictListByType("yes_no"));
      ac.put("mainCategory", sysDictService.getSysDictListByType("main_category"));
      return SUCCESS;
    }
  }

  public String insertShop() throws Throwable {

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      sysLogin.prePersist(loginPojo);
    }
    sysLogin.setPassword(MD5Util.MD5(sysLogin.getPassword()));
    sysLogin.setType(os);
    sysLogin.setStatus(1);
    sysLoginService.insertSysLogin(sysLogin);
    sysLogin1 = sysLoginService.sysLoginFindId(sysLogin);
    // 添加用户信息
    UserInfoPojo userInfo = new UserInfoPojo();
    userInfo.setUserId(sysLogin1.getId());
    userInfoService.insertUserInfo(userInfo);
    // 供应商添加数据
    ManufacturerPojo manufacturer = new ManufacturerPojo();
    manufacturer.setUserId(sysLogin1.getId());
    manufacturerService.insertManufacturer(manufacturer);
    Long i = sysLogin1.getId();
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
    shop.setRecommendType(0l);
    shop.setLocationCertification("1");
    shop.setIdentityCertification("1");
    shop.setSorting(1000);
    shop.setUserId(i);
    shopService.insertShop(shop);
    FileUtil.alertMessageBySkip("新增成功！", "shop.do");

    return null;
  }

  public String goFindShop() throws Exception {
    ActionContext ac = ActionContext.getContext();
    // ac.put("shopPojo", shopService.findShopById(shop.getId()));
    ac.put("shopPojo", shopService.getfindByIdShop2(shop.getId()));
    ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("isNew", sysDictService.getSysDictListByType("yes_no"));
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
    ProductTypePojo pojo = new ProductTypePojo();
    pojo.setPid(0l);
    ac.put("recommendType", productTypeService.getProductTypeByPid(pojo));
    return SUCCESS;
  }

  public String updateShop() throws Throwable {
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
    String category = shop.getMainCategory();// 1,7
    String[] catg = null;
    if (category != null) {
      catg = category.split(",");
    }

    // 遍历整个主营品类
    String categoryStr = "";
    String categStr = "";
    for (Integer i = 0; i < catg.length; i++) {
      categoryStr = StringUtils.trim(catg[i]) + ":";
      categStr = categStr + categoryStr;

    }
    if (categStr.length() > 0) {
      categStr = ":" + categStr;
    }
    shop.setMainCategory(categStr);

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      shop.preUpdate(loginPojo);
    }
    manufacturer = new ManufacturerPojo();
    manufacturer.setUserId(shop.getUserId());
    manufacturer.setCompany(shop.getName());
    shopService.updateShop(shop);
    manufacturerService.updateManufacturer(manufacturer);
    FileUtil.alertMessageBySkip("修改成功！", "shop.do");

    return null;
  }

  public String delShop() throws Exception {

    shopService.delShop(shop.getId());
    FileUtil.alertMessageBySkip("删除成功！", "shop.do");

    return null;
  }

  public String goshopCount() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(12);
    page.setRowCount(shopService.shopAllCount(shop));
    shoplist = shopService.shopAllService(shop, page);
    ActionContext ac = ActionContext.getContext();
    ac.put("shopPojo", shop);
    ac.put("shoplist", shoplist);

    // 店铺街广告图//
    pagePushPojos = pagePushService.findAdByType(3);
    for (int i = 0; i < pagePushPojos.size(); i++) {
      ac.put("adimages" + i, pagePushPojos.get(i).getImages());
      ac.put("backgroundColor" + i, pagePushPojos.get(i).getBackgroundColor());
    }

    return SUCCESS;
  }

  public String shopDetailsWeb() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(15);
    ShopPojo shop33 = shopService.findShopById(shop.getId());
    productListYes = productService.productForUserYes(shop33.getUserId());
    ProductPojo productPojo = new ProductPojo();
    productPojo.setStatus(1);
    productPojo.setUserId(shop33.getUserId());
    page.setRowCount(productService.getAllCount(productPojo));
    List<ProductPojo> productlist = productService.productForUser(productPojo, page);
    ActionContext ac = ActionContext.getContext();
    ac.put("shopPojo", shop33);
    ac.put("productListYes", productListYes);
    ac.put("productlist", productlist);
    return SUCCESS;
  }

  public String goFindShopWeb() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "doLoginWeb.do");
    }
    shopWeb = new ShopPojo();
    shopWeb.setUserId(sysLogin.getId());
    ActionContext ac = ActionContext.getContext();
    shopWeb = shopServiceWeb.getfindByIdShopWeb(shopWeb.getUserId());
    // ac.put("shopWeb", shopServiceWeb.getfindByIdShopWeb(shopWeb.getUserId()));
    ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("mainCategory", sysDictService.getSysDictListByType("main_category"));
    return SUCCESS;
  }

  public String updateShopWeb() throws Throwable {
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/shop") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/shop/", upfile);
      shopWeb.setImages(file_name);
    }
    SysLoginPojo loginPojo = UserUtil.getWebUser();
    Long UID = loginPojo.getId();
    if (UserUtil.getWebUser() == null) {
      return "doLoginWeb";
    } else {
      shopWeb.preUpdate(loginPojo);
    }
    if (shopService.findShop(shopWeb) != null) {
      shopWeb.setSorting(1);
      shopWeb.setStatus(0);
      shopWeb.setCreateBy(loginPojo.getId());
      // shopWeb.setCreateDate();
      // shopWeb.setUserId(loginPojo.getId());
      shopService.updateShopWeb(shopWeb);
      FileUtil.alertMessageBySkip("设置成功！", "goFindShopWeb.do?shopWeb.userId=" + UID);
      return null;
    } else {
      shopWeb.setUserId(UID);;
      shopWeb.setSorting(1);
      shopWeb.setStatus(0);
      shopWeb.setCreateBy(loginPojo.getId());
      shopService.insertShop(shopWeb);
      FileUtil.alertMessageBySkip("设置成功！", "goFindShopWeb.do?shopWeb.userId=" + UID);
      return null;
    }


  }

  public String getSysAreaByPid() {
    List<SysAreaPojo> sysAreaList = sysAreaService.getSysAreaByPid(sysAreaPojo);
    JSONArray json = JSONArray.fromObject(sysAreaList);
    jsonStr = json.toString();
    return SUCCESS;
  }

  public String undercarriageShop() throws Exception {
    shop.setStatus(0);
    shopService.updateShopStatus(shop);
    productService.updateProductStatus(shop.getUserId());
    ProductPojo productPojo = new ProductPojo();
    productPojo.setUserId(shop.getUserId());
    List<ProductPojo> productPojos = productService.getProductAll(productPojo, null);
    if (productPojos.size() > 0) {
      ProductSellPojo productSell = new ProductSellPojo();
      for (ProductPojo Product : productPojos) {
        productSell.setProductId(Product.getId());
        productSell.setStatus(0);
        productSellService.update(productSell);
      }
    }
    FileUtil.alertMessageBySkip("下架成功！", "shop.do");
    return null;
  }

  /*
   * 设置为自营
   */
  public String setSelfSupport() throws SQLException {
    try {
      shop.setSelfSupport(1);
      shop.setUpdateDate(new Date());
      shopService.updateShop(shop);
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  /*
   * 取消自营
   */
  public String setUnSelfSupport() throws SQLException {
    try {
      shop.setSelfSupport(0);
      shop.setUpdateDate(new Date());
      shopService.updateShop(shop);
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

}
