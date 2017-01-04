package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.AgencyCollectPojo;
import com.tzmb2c.web.pojo.AgencyPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.AgencyCollectService;
import com.tzmb2c.web.service.AgencyService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;

public class AgencyAction extends SuperAction {

  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private AgencyService agencyService;
  @Autowired
  private ShopService shopService;
  @Autowired
  private AgencyCollectService agencyCollectService;

  private AgencyPojo agency;
  private AgencyCollectPojo agencyCollectPojo;
  private String result;
  private String[] tids;
  private File upfile;
  private String id;
  private String stock;
  private Long timeId;
  private String activityTime;
  private String activityDate;
  private Long titleId;
  private String title;
  private String type;
  private Integer t;
  private String typeName;


  public String getActivityDate() {
    return activityDate;
  }

  public void setActivityDate(String activityDate) {
    this.activityDate = activityDate;
  }

  public Long getTitleId() {
    return titleId;
  }

  public void setTitleId(Long titleId) {
    this.titleId = titleId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getTimeId() {
    return timeId;
  }

  public void setTimeId(Long timeId) {
    this.timeId = timeId;
  }

  public String getActivityTime() {
    return activityTime;
  }

  public void setActivityTime(String activityTime) {
    this.activityTime = activityTime;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getStock() {
    return stock;
  }

  public void setStock(String stock) {
    this.stock = stock;
  }

  public AgencyCollectPojo getAgencyCollectPojo() {
    return agencyCollectPojo;
  }

  public void setAgencyCollectPojo(AgencyCollectPojo agencyCollectPojo) {
    this.agencyCollectPojo = agencyCollectPojo;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public void setAgency(AgencyPojo agency) {
    this.agency = agency;
  }

  public AgencyPojo getAgency() {
    return agency;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String agencyAllList() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (agency != null) {
      map.put("company", agency.getCompany());
      map.put("addressCompany", agency.getAddressCompany());
      map.put("phone", agency.getPhone());
      map.put("loginname", agency.getLoginname());
      map.put("name", agency.getName());
      map.put("QQ", agency.getQQ());
      map.put("userId", agency.getUserId());
    }
    List<AgencyPojo> list = agencyService.agencyAllList(map);
    JSONArray json = JSONArray.fromObject(list);
    page.setRowCount(agencyService.agencyCount(map));
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String agencyCount() throws SQLException {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (agency != null) {
      map.put("company", agency.getCompany());
      map.put("addressCompany", agency.getAddressCompany());
      map.put("phone", agency.getPhone());
      map.put("loginname", agency.getLoginname());
      map.put("name", agency.getName());
      map.put("QQ", agency.getQQ());
      map.put("userId", agency.getUserId());
    }
    int i = agencyService.agencyCount(map);
    page.setRowCount(i);
    return SUCCESS;
  }



  public String goFindAgency() throws Exception {

    ActionContext ac = ActionContext.getContext();
    ac.put("agencyPojo", agencyService.getfindByUserIdAgency(agency.getAgencyId()));
    ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("channel", sysDictService.getSysDictListByType("channel"));
    ac.put("isread", sysDictService.getSysDictListByType("isread"));
    ac.put("mainCategory", sysDictService.getSysDictListByType("main_category"));
    ac.put("shop", shopService.FindshopAll());
    return SUCCESS;
  }

  public String agencyCollectCount() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    if (agencyCollectPojo != null) {
      map.put("uid", agencyCollectPojo.getUid());
      map.put("agency_id", agencyCollectPojo.getAgency_id());
      map.put("productNum", agencyCollectPojo.getProductNum());
      map.put("productName", agencyCollectPojo.getProductName());
    }
    page.setRowCount(agencyCollectService.agencyCollectCount(map));
    return SUCCESS;
  }

  public String agencyCollectList() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (agencyCollectPojo != null) {
      map.put("uid", agencyCollectPojo.getUid());
      map.put("agency_id", agencyCollectPojo.getAgency_id());
      map.put("productNum", agencyCollectPojo.getProductNum());
      map.put("productName", agencyCollectPojo.getProductName());
    }
    List<AgencyCollectPojo> list = agencyCollectService.agencyCollectList(map);
    JSONArray json = JSONArray.fromObject(list);
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String delAgencyCollect() throws SQLException {
    try {
      if (agencyCollectPojo != null) {
        agencyCollectService.delAgencyCollectById(agencyCollectPojo.getId());
      }
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  public String delAgencyCollectAll() throws Exception {
    if (tids != null) {
      for (String tid : tids) {
        try {
          agencyCollectService.delAgencyCollectById(Long.valueOf(tid));
        } catch (Exception e) {
          e.printStackTrace();
          FileUtil.alertMessageBySkip(
              "删除失败！",
              "goFindAgencyCollect.do?agencyCollectPojo.agency_id="
                  + agencyCollectPojo.getAgency_id());
        }
      }
      FileUtil.alertMessageBySkip("删除成功！", "goFindAgencyCollect.do?agencyCollectPojo.agency_id="
          + agencyCollectPojo.getAgency_id());
    } else {
      FileUtil.alertMessageBySkip("请先勾选！", "goFindAgencyCollect.do?agencyCollectPojo.agency_id="
          + agencyCollectPojo.getAgency_id());
    }

    return null;
  }

  public String updAgencyCollectStock() throws Exception {
    Long stockNum = 0L;
    if (stock == null || "".equals(stock)) {
      this.result = "0";
    } else {
      AgencyCollectPojo collectPojo = new AgencyCollectPojo();
      try {
        stockNum = Long.parseLong(stock);
        collectPojo.setAgency_stock(stockNum);
        collectPojo.setId(Long.valueOf(id));
        agencyCollectService.updatAagencyCollect(collectPojo);
        this.result = "1";
      } catch (NumberFormatException e) {
        e.printStackTrace();
        this.result = "0";
      } catch (Exception e) {
        e.printStackTrace();
        this.result = "0";
      }
    }
    return SUCCESS;
  }

  public String updateAgency() throws Throwable {
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + "1.jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/agency") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/agency/", upfile);
      agency.setBusLicence(file_name);
    }
    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      agency.preUpdate(loginPojo);
    }
    // 修改代理品牌时，删除已设置代理商品
    AgencyPojo agencyOld = agencyService.getfindByAgencyId(agency.getAgencyId());
    AgencyCollectPojo collectPojo = null;
    if (agencyOld != null) {
      if (agency.getManufacturerId() != 0
          && agencyOld.getManufacturerId() != agency.getManufacturerId()) {
        collectPojo = new AgencyCollectPojo();
        collectPojo.setUid(agency.getUserId());
        collectPojo.setAgency_id(agency.getAgencyId());
        List<AgencyCollectPojo> collects =
            agencyCollectService.findagencyCollectByUid(collectPojo, null);
        for (AgencyCollectPojo collect : collects) {
          collectPojo.setProduct_id(collect.getProduct_id());
          agencyCollectService.delCheckProduct(collectPojo);
        }
      }
    }

    agencyService.updateAgency(agency);

    FileUtil.alertMessageBySkip("修改成功！", "agency.do");

    return null;
  }

  public String delAgemcy() throws Exception {

    agencyService.deleteAgency(agency.getAgencyId());
    FileUtil.alertMessageBySkip("删除成功！", "agency.do");
    return null;
  }

  public String checkAgency() throws SQLException {
    try {
      agencyService.checkAgency(agency.getAgencyId());
      AgencyPojo agencyPojo = agencyService.getfindByIdAgency(agency.getAgencyId());
      SysLoginPojo sysLoginPojo = new SysLoginPojo();
      SysLoginPojo loginPojo = UserUtil.getUser();
      if (UserUtil.getUser() == null) {
        return "loginpage";
      } else {
        sysLoginPojo.preUpdate(loginPojo);
      }
      sysLoginPojo.setType("6");
      sysLoginPojo.setId(agencyPojo.getUserId());
      sysLoginService.updateType(sysLoginPojo);
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String delAgency() throws Exception {

    agencyService.delAgency(agency.getAgencyId());
    FileUtil.alertMessageBySkip("删除成功！", "agency.do");

    return null;
  }

  public String agencyChecks() throws SQLException {
    if (tids != null) {
      agencyService.agencyChecks(tids);
      for (String tid : tids) {
        try {
          AgencyPojo agencyPojo = agencyService.getfindByIdAgency(Long.parseLong(tid));
          SysLoginPojo sysLoginPojo = new SysLoginPojo();
          SysLoginPojo loginPojo = UserUtil.getUser();
          if (UserUtil.getUser() == null) {
            return "loginpage";
          } else {
            sysLoginPojo.preUpdate(loginPojo);
          }
          sysLoginPojo.setId(agencyPojo.getUserId());
          sysLoginPojo.setType("6");
          sysLoginService.updateType(sysLoginPojo);
        } catch (SQLException e) {

          e.printStackTrace();
        }
      }
      FileUtil.alertMessageBySkip("审核成功！", "agency.do");
    } else {
      FileUtil.alertMessageBySkip("审核失败！", "agency.do");
    }

    return null;
  }

  private ProductPojo productPojo;
  private List<ProductPojo> productList;
  private ShopPojo shopPojo;
  @Autowired
  private ProductService productService;

  public ProductPojo getProductPojo() {
    return productPojo;
  }

  public void setProductPojo(ProductPojo productPojo) {
    this.productPojo = productPojo;
  }

  public ShopPojo getShopPojo() {
    return shopPojo;
  }

  public void setShopPojo(ShopPojo shopPojo) {
    this.shopPojo = shopPojo;
  }

  public List<ProductPojo> getProductList() {
    return productList;
  }

  public void setProductList(List<ProductPojo> productList) {
    this.productList = productList;
  }

  public String agencyCollectAdd() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("agencyId", agency.getAgencyId());
    map.put("id", productPojo.getId());
    try {
      Long c = agencyCollectService.findagencyCollectByProductId(map);
      if (c == 0) {
        agencyCollectPojo = new AgencyCollectPojo();
        AgencyPojo agencyPojo = agencyService.getfindByAgencyId(agency.getAgencyId());
        agencyCollectPojo.setUid(agencyPojo.getUserId());
        agencyCollectPojo.setAgency_id(agency.getAgencyId());
        agencyCollectPojo.setAgency_stock(0L);
        agencyCollectPojo.setProduct_id(productPojo.getId());
        agencyCollectService.insertAgencyCollect(agencyCollectPojo);
        FileUtil.alertMessageBySkip("代理成功！",
            "addAgencyCollect.do?agency.agencyId=" + agency.getAgencyId()
                + "&agency.manufacturerId=" + agency.getManufacturerId());
      } else {
        FileUtil.alertMessageBySkip("商品已代理过！",
            "addAgencyCollect.do?agency.agencyId=" + agency.getAgencyId()
                + "&agency.manufacturerId=" + agency.getManufacturerId());
      }

    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("代理失败！",
          "addAgencyCollect.do?agency.agencyId=" + agency.getAgencyId() + "&agency.manufacturerId="
              + agency.getManufacturerId());
    }
    return null;
  }

  public String agencyCollectAddAll() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("agencyId", agency.getAgencyId());
    AgencyPojo agencyPojo = null;
    try {
      for (String id : tids) {
        map.put("id", Long.valueOf(id));
        Long c = agencyCollectService.findagencyCollectByProductId(map);
        if (c == 0) {
          agencyCollectPojo = new AgencyCollectPojo();
          agencyPojo = agencyService.getfindByAgencyId(agency.getAgencyId());
          agencyCollectPojo.setUid(agencyPojo == null ? null : agencyPojo.getUserId());
          agencyCollectPojo.setAgency_id(agency.getAgencyId());
          agencyCollectPojo.setProduct_id(Long.valueOf(id));
          agencyCollectPojo.setAgency_stock(0L);
          agencyCollectService.insertAgencyCollect(agencyCollectPojo);
        }
      }
      FileUtil.alertMessageBySkip("代理成功！",
          "addAgencyCollect.do?agency.agencyId=" + agency.getAgencyId() + "&agency.manufacturerId="
              + agency.getManufacturerId());
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("代理失败！",
          "addAgencyCollect.do?agency.agencyId=" + agency.getAgencyId() + "&agency.manufacturerId="
              + agency.getManufacturerId());
    }
    return null;
  }

  public String addAgencyCollect() throws SQLException {
    try {

      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      // map.put("pageSize",10);
      // map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (agency != null && agency.getManufacturerId() != null) {
        shopPojo = shopService.getfindByIdShop(agency.getManufacturerId());
        map.put("userId", shopPojo == null ? 0 : shopPojo.getUserId());
      }
      if (productPojo != null) {
        map.put("id", productPojo.getId());
        map.put("shopName", productPojo.getShopName());
        map.put("productName", productPojo.getProductName().trim());
      }
      List<ProductPojo> productList = productService.productCollectAdd(map);
      page.setRowCount(productList.size());
      // JSONArray json = JSONArray.fromObject(productList);
      // page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  public String agencyCollectAddManage() throws SQLException {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (agency != null && agency.getAgencyId() != null && shopPojo != null) {
        map.put("userId", shopPojo.getUserId() == null ? 0 : shopPojo.getUserId());
      }
      if (productPojo != null) {
        map.put("id", productPojo.getId());
        map.put("shopName", productPojo.getShopName());
        map.put("productName", productPojo.getProductName().trim());
      }
      List<ProductPojo> productList = productService.productCollectAdd(map);
      JSONArray json = JSONArray.fromObject(productList);
      // page.setRowCount(productList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public Integer getT() {
    return t;
  }

  public void setT(Integer t) {
    this.t = t;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }
}
