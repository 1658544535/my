package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.WalletService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.export.excel.ExcelProcessor;
import com.tzmb2c.web.pojo.AgencyPojo;
import com.tzmb2c.web.pojo.ConsumerPojo;
import com.tzmb2c.web.pojo.GrouponExcelPojo;
import com.tzmb2c.web.pojo.GrouponUserRecordPojo;
import com.tzmb2c.web.pojo.ManufacturerPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.PurchaserAttentionPojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserInfoPojo;
import com.tzmb2c.web.service.AgencyService;
import com.tzmb2c.web.service.ConsumerService;
import com.tzmb2c.web.service.GrouponUserRecordService;
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

public class GrouponExcelAction extends SuperAction {

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
  @Autowired
  private GrouponUserRecordService grouponUserRecordService;
  private PurchaserAttentionPojo purchaserAttentionPojo;
  private SysLoginPojo sysLogin, sysLogin1;
  private String result;
  private String[] tids;
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
  private GrouponUserRecordPojo grouponUserRecordPojo;


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

  public GrouponUserRecordPojo getGrouponUserRecordPojo() {
    return grouponUserRecordPojo;
  }

  public void setGrouponUserRecordPojo(GrouponUserRecordPojo grouponUserRecordPojo) {
    this.grouponUserRecordPojo = grouponUserRecordPojo;
  }


  // 跳转到拼团信息导出页面
  public String goGrouponExcel() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    try {
      page.setRowCount(0);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }


  public String grouponExcelCount() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      map.put("pageNo", 0);
      map.put("pageSize", 10);
    } else {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    int count = 0;
    if (grouponUserRecordPojo != null && grouponUserRecordPojo.getAttendTimeStartStr() != ""
        && grouponUserRecordPojo.getAttendTimeEndStr() != ""
        && grouponUserRecordPojo.getOpenGroup() != null) {
      map.put("attendTimeStartStr", grouponUserRecordPojo.getAttendTimeStartStr());
      map.put("attendTimeEndStr", grouponUserRecordPojo.getAttendTimeEndStr());
      map.put("openGroup", grouponUserRecordPojo.getOpenGroup());
      count = grouponUserRecordService.countBy(map);
    }
    page.setRowCount(count);
    return SUCCESS;
  }

  public String grouponExcelList() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();

    if (page == null) {
      map.put("pageNo", 0);
      map.put("pageSize", 10);
    } else {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    List<GrouponUserRecordPojo> grouponUserRecordList = null;
    if (grouponUserRecordPojo != null && grouponUserRecordPojo.getAttendTimeStartStr() != ""
        && grouponUserRecordPojo.getAttendTimeEndStr() != ""
        && grouponUserRecordPojo.getOpenGroup() != null) {
      map.put("attendTimeStartStr", grouponUserRecordPojo.getAttendTimeStartStr());
      map.put("attendTimeEndStr", grouponUserRecordPojo.getAttendTimeEndStr());
      map.put("openGroup", grouponUserRecordPojo.getOpenGroup());
      grouponUserRecordList = grouponUserRecordService.listPage(map);
    }
    JSONArray json = null;
    if (grouponUserRecordList == null) {
      json = JSONArray.fromObject("[]");
    } else {
      json = JSONArray.fromObject(grouponUserRecordList);
    }
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 导出excel
   * 
   * @return
   * @throws SQLException
   */
  public String getGrouponExcel() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (grouponUserRecordPojo != null && grouponUserRecordPojo.getAttendTimeStartStr() != ""
          && grouponUserRecordPojo.getAttendTimeEndStr() != ""
          && grouponUserRecordPojo.getOpenGroup() != null) {
        map.put("attendTimeStartStr", grouponUserRecordPojo.getAttendTimeStartStr());
        map.put("attendTimeEndStr", grouponUserRecordPojo.getAttendTimeEndStr());
        map.put("openGroup", grouponUserRecordPojo.getOpenGroup());
      }
      List<GrouponExcelPojo> grouponUserRecordList = grouponUserRecordService.listPage4(map);
      downloadFileName = "拼团名单.xls";
      ExcelProcessor<GrouponExcelPojo> e =
          new ExcelProcessor<GrouponExcelPojo>(grouponUserRecordList, GrouponExcelPojo.class,
              "拼团名单");
      inputStream = e.generateExcelSteam();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public AgencyPojo getAgency() {
    return agency;
  }

  public void setAgency(AgencyPojo agency) {
    this.agency = agency;
  }

}
