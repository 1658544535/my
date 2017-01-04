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
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SystemPropertyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.ActivityGoodsPojo;
import com.tzmb2c.web.pojo.ActivityProductPojo;
import com.tzmb2c.web.pojo.NavigationPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.SpecialProductPojo;
import com.tzmb2c.web.pojo.SpecialShowPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.ActivityProductService;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.NavigationService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.SpecialProductService;
import com.tzmb2c.web.service.SpecialShowService;
import com.tzmb2c.web.service.SyntheticalDictService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;

/**
 * @author EricChen
 */
public class ActivityProductAction extends SuperAction {
  private static final long serialVersionUID = 1L;
  @Autowired
  private ActivityProductService activityProductService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ProductTypeService productTypeService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SyntheticalDictService syntheticalDictService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  @Autowired
  private ActivityTimeService activityTimeService;
  @Autowired
  private SpecialProductService specialProductService;
  @Autowired
  private SpecialShowService specialShowService;
  @Autowired
  private NavigationService navigationService;
  private ActivityProductPojo activityProductPojo;
  private SpecialShowPojo specialShowPojo;
  private List<ActivityProductPojo> activityProductPojos;
  private List<ActivityGoodsPojo> activityGoodsPojo;
  private List<SpecialProductPojo> specialProductPojo;
  private List<SpecialShowPojo> specialShowPojos;
  private SysLoginPojo sysLogin;
  private List<SysDictPojo> statusSysDictList = null;
  private List<NavigationPojo> navigationList = null;
  private String msg;
  private String result;
  private String[] tids;
  private File upfile;
  private String type;
  private String types;
  private String productName;
  private String productNo;
  private Long titleId;// 首页列表活动标题id
  private String title;// 首页列表活动标题
  private Long id;//
  private Long titleIds;

  public SpecialShowPojo getSpecialShowPojo() {
    return specialShowPojo;
  }

  public void setSpecialShowPojo(SpecialShowPojo specialShowPojo) {
    this.specialShowPojo = specialShowPojo;
  }

  public List<SpecialShowPojo> getSpecialShowPojos() {
    return specialShowPojos;
  }

  public void setSpecialShowPojos(List<SpecialShowPojo> specialShowPojos) {
    this.specialShowPojos = specialShowPojos;
  }

  public List<SpecialProductPojo> getSpecialProductPojo() {
    return specialProductPojo;
  }

  public void setSpecialProductPojo(List<SpecialProductPojo> specialProductPojo) {
    this.specialProductPojo = specialProductPojo;
  }

  public List<ActivityGoodsPojo> getActivityGoodsPojo() {
    return activityGoodsPojo;
  }

  public void setActivityGoodsPojo(List<ActivityGoodsPojo> activityGoodsPojo) {
    this.activityGoodsPojo = activityGoodsPojo;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long Id) {
    this.id = Id;
  }

  public List<SysDictPojo> getStatusSysDictList() {
    return statusSysDictList;
  }

  public void setStatusSysDictList(List<SysDictPojo> statusSysDictList) {
    this.statusSysDictList = statusSysDictList;
  }

  public List<NavigationPojo> getNavigationList() {
    return navigationList;
  }

  public void setNavigationList(List<NavigationPojo> navigationList) {
    this.navigationList = navigationList;
  }

  public SysLoginPojo getSysLogin() {
    return sysLogin;
  }

  public void setSysLogin(SysLoginPojo sysLogin) {
    this.sysLogin = sysLogin;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
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

  public ActivityProductPojo getActivityProductPojo() {
    return activityProductPojo;
  }

  public void setActivityProductPojo(ActivityProductPojo activityProductPojo) {
    this.activityProductPojo = activityProductPojo;
  }

  public List<ActivityProductPojo> getActivityProductPojos() {
    return activityProductPojos;
  }

  public void setActivityProductPojos(List<ActivityProductPojo> activityProductPojos) {
    this.activityProductPojos = activityProductPojos;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductNo() {
    return productNo;
  }

  public void setProductNo(String productNo) {
    this.productNo = productNo;
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


  /***
   * 查找数据字典
   */
  private void getDictList() {
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 2);
    try {
      statusSysDictList = sysDictService.getSysDictListByType("status");
      navigationList = navigationService.findNavigationList(option);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }



  public String getActivityProductById() throws SQLException, DocumentException {
    getDictList();
    Map<String, Object> map = new HashMap<String, Object>();
    if (activityProductPojo != null) {
      map.put("productId", activityProductPojo.getProductId());
      map.put("type", activityProductPojo.getType());
      map.put("remarks", titleId);
    }

    ActivityProductPojo activityProduct = activityProductService.getActivityProductById(map);
    if (activityProduct != null) {
      activityProductPojo = activityProduct;
    }
    ProductPojo product = new ProductPojo();
    product.setId(activityProductPojo.getProductId());
    product = productService.findProduct(product);
    if (product != null) {
      activityProductPojo.setProductName(product.getProductName());
      activityProductPojo.setImage(product.getImage());
    }
    // JSONArray json = JSONArray.fromObject(activityProductPojo);
    // page.setResult(json.toString());
    return SUCCESS;
  }

  public String addActivityProduct() throws SQLException {
    SysLoginPojo sysLoginPojo = findSessionUser();
    if (activityProductPojo.getId() == null || "".equals(activityProductPojo.getId())
        || 0 == activityProductPojo.getId()) {
      activityProductPojo.setCreateBy(sysLoginPojo.getId());
      activityProductService.insertActivityProduct(activityProductPojo);
    } else {
      activityProductPojo.setUpdateBy(sysLoginPojo.getId());
      activityProductService.updateActivityProduct(activityProductPojo);
    }

    FileUtil.alertMessageBySkip("提交成功！", "productManage.do");
    return null;
  }

  public String addActivityProduct1() throws SQLException {
    SysLoginPojo sysLoginPojo = findSessionUser();
    if (activityProductPojo.getId() == null || "".equals(activityProductPojo.getId())
        || 0 == activityProductPojo.getId()) {
      activityProductPojo.setCreateBy(sysLoginPojo.getId());
      activityProductPojo.setTitleId(titleId);
      activityProductService.insertActivityProduct(activityProductPojo);
    } else {
      activityProductPojo.setUpdateBy(sysLoginPojo.getId());
      activityProductService.updateActivityProduct(activityProductPojo);
    }
    if (activityProductPojo.getRemarks() != null && !"".equals(activityProductPojo.getRemarks())) {
      FileUtil.alertMessageBySkip("提交成功！", "activityTitleManage.do");
    } else {
      FileUtil.alertMessageBySkip("提交成功！",
          "activityProductManage.do?type=" + activityProductPojo.getType());
    }
    return null;
  }

  public String getActivityProductList() throws SQLException, DocumentException {
    getDictList();
    getActivityProductCount();
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("remarks", titleId);
    if (activityProductPojo != null) {
      map.put("userName", activityProductPojo.getUserName());
      map.put("productNum", activityProductPojo.getProductNum());
      map.put("productName", activityProductPojo.getProductName());
      map.put("status", activityProductPojo.getStatus());
      map.put("proStatus", activityProductPojo.getProStatus());
      map.put("type", activityProductPojo.getType());
      map.put("productId", activityProductPojo.getProductId());
      map.put("name", activityProductPojo.getName());
    }
    if (type != null && !"".equals(type)) {
      map.put("type", type);
    }
    if (titleIds != null) {
      map.put("titleId", titleIds);
    }
    List<ActivityProductPojo> list = activityProductService.getActivityProductList(map);
    if (list != null && list.size() > 0) {
      for (ActivityProductPojo p : list) {
        p.setImage(p.getImage());
      }
    }
    JSONArray json = JSONArray.fromObject(list);
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String getActivityProductCount() throws SQLException {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("remarks", titleId);
    if (activityProductPojo != null) {
      map.put("userName", activityProductPojo.getUserName());
      map.put("productNum", activityProductPojo.getProductNum());
      map.put("productName", activityProductPojo.getProductName());
      map.put("status", activityProductPojo.getStatus());
      map.put("type", activityProductPojo.getType());
      map.put("proStatus", activityProductPojo.getProStatus());
      map.put("productId", activityProductPojo.getProductId());
      map.put("name", activityProductPojo.getName());
    } else if (type != null && !"".equals(type)) {
      map.put("type", type);
    }

    int i = activityProductService.getActivityProductCount(map);
    page.setRowCount(i);
    return SUCCESS;
  }

  /**
   * @return 审核通过
   * @throws SQLException
   */
  public String checkActivityProduct() throws SQLException {
    try {
      activityProductService.checkActivityProduct(activityProductPojo.getId());
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  /**
   * @return 取消审核
   * @throws SQLException
   */
  public String uncheckActivityProduct() throws SQLException {
    try {
      activityProductService.uncheckActivityProduct(activityProductPojo.getId());
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  /**
   * @return 删除活动
   * @throws SQLException
   */
  public String delActivityProduct() throws SQLException {
    try {
      activityProductService.delActivityProduct(activityProductPojo.getId());
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  /**
   * @return 设置活动
   * @throws SQLException
   */
  public String setActivityProduct() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      SysLoginPojo sysLoginPojo = findSessionUser();
      if (activityProductPojo != null) {
        map.put("productId", activityProductPojo.getProductId());
        map.put("type", activityProductPojo.getType());
        map.put("remarks", activityProductPojo.getRemarks());
      }

      ActivityProductPojo activityProduct = activityProductService.getActivityProductById(map);
      if (activityProduct == null) {
        activityProductPojo.setStatus(0);
        activityProductPojo.setCreateBy(sysLoginPojo.getId());
        activityProductService.insertActivityProduct(activityProductPojo);
        if (activityProductPojo.getRemarks() != null) {
          FileUtil.alertMessageBySkip("添加成功！",
              "activityTitleManage.do?type=" + activityProductPojo.getType());
        } else {
          this.result = "1";
          return SUCCESS;
        }
      } else {
        activityProduct.setUpdateBy(sysLoginPojo.getId());
        activityProductService.updateActivityProduct(activityProduct);
        if (activityProductPojo.getRemarks() != null) {
          FileUtil.alertMessageBySkip("产品已经在活动中，添加失败！", "activityTitleManage.do?type="
              + activityProductPojo.getType());
        } else {
          this.result = "0";
          return SUCCESS;
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public String setActivityProductAll() throws Exception {
    if (tids != null) {
      for (String tid : tids) {
        try {
          Map<String, Object> map = new HashMap<String, Object>();
          SysLoginPojo sysLoginPojo = findSessionUser();
          if (activityProductPojo != null) {
            map.put("productId", Long.valueOf(tid));
            map.put("type", "4");
            map.put("remarks", activityProductPojo.getRemarks());
          }
          ActivityProductPojo activityProduct = activityProductService.getActivityProductById(map);
          if (activityProduct == null) {
            activityProductPojo.setStatus(0);
            activityProductPojo.setType("4");
            activityProductPojo.setCreateBy(sysLoginPojo.getId());
            activityProductPojo.setProductId(Long.valueOf(tid));
            activityProductService.insertActivityProduct(activityProductPojo);
          } else {
            activityProduct.setUpdateBy(sysLoginPojo.getId());
            activityProductService.updateActivityProduct(activityProduct);
          }
        } catch (Exception e) {
          e.printStackTrace();
          // FileUtil.alertMessageBySkip("添加失败！", "activityTitleManage.do");
        }
      }
      FileUtil.alertMessageBySkip("添加成功！",
          "activityTitleManage.do?type=" + activityProductPojo.getType());
    } else {
      FileUtil.alertMessageBySkip("请先勾选！",
          "activityTitleManage.do?type=" + activityProductPojo.getType());
    }

    return null;
  }

  public String delActivityProductAll() {
    StringBuffer url = new StringBuffer("activityProductManage.do?type=" + type);
    if (tids != null && tids.length > 0) {
      try {
        for (String tid : tids) {
          activityProductService.delActivityProduct(Long.parseLong(tid));
        }
        if (titleId != null) {
          FileUtil.alertMessageBySkip("删除成功！", "activityProductManage.do?titleId=" + titleId
              + "&title=" + title);
        } else {
          FileUtil.alertMessageBySkip("删除成功！", url.toString());
        }
      } catch (SQLException e) {
        e.printStackTrace();
        if (titleId != null) {
          FileUtil.alertMessageBySkip("删除失败！", "activityProductManage.do?titleId=" + titleId
              + "&title=" + title);
        } else {
          FileUtil.alertMessageBySkip("删除失败！", url.toString());
        }
      }
    } else {
      if (titleId != null) {
        FileUtil.alertMessageBySkip("没有勾选！", "activityProductManage.do?titleId=" + titleId
            + "&title=" + title);
      } else {
        FileUtil.alertMessageBySkip("没有勾选！", url.toString());
      }
    }
    return null;
  }

  public String checkActivityProductAll() {
    StringBuffer url = new StringBuffer("activityProductManage.do?type=" + type);
    if (tids != null && tids.length > 0) {
      try {
        for (String tid : tids) {
          activityProductService.checkActivityProduct(Long.parseLong(tid));
        }
        if (titleId != null) {
          FileUtil.alertMessageBySkip("审核成功！", "activityProductManage.do?titleId=" + titleId
              + "&title=" + title);
        } else {
          FileUtil.alertMessageBySkip("审核成功！", url.toString());
        }
      } catch (SQLException e) {
        e.printStackTrace();
        if (titleId != null) {
          FileUtil.alertMessageBySkip("审核失败！", "activityProductManage.do?titleId=" + titleId
              + "&title=" + title);
        } else {
          FileUtil.alertMessageBySkip("审核失败！", url.toString());
        }
      }
    } else {
      if (titleId != null) {
        FileUtil.alertMessageBySkip("没有勾选！", "activityProductManage.do?titleId=" + titleId
            + "&title=" + title);
      } else {
        FileUtil.alertMessageBySkip("没有勾选！", url.toString());
      }
    }
    return null;
  }

  // web活动页批量删除商品
  public String delActivityPlaceAll() throws SQLException {
    try {
      for (String id : tids) {
        activityProductService.delActivityProduct(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部删除成功！", "activityPlaceList.do?type=" + type + "&titleId="
          + titleId);
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部删除失败！", "activityPlaceList.do?type=" + type + "&titleId="
          + titleId);
    }
    return null;
  }

  // web活动页批量审核商品
  public String checkActivityPlaceAll() {
    try {
      for (String id : tids) {
        activityProductService.checkActivityProduct(Long.valueOf(id));
      }
      if ("6".equals(type)) {
        FileUtil.alertMessageBySkip("全部通过审核成功！",
            "goSettingPlatformSpecialPorduct.do?types=6&titleId=" + titleId);
        return null;
      }
      FileUtil.alertMessageBySkip("全部通过审核成功！", "activityPlaceList.do?type=" + type + "&titleId="
          + titleId);
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部通过审核失败！", "activityPlaceList.do?type=" + type + "&titleId="
          + titleId);
    }
    return null;
  }


  // web活动页里添加页面所用到的方法
  public String activityPlaceAdd() throws Exception {
    getDictList();
    ActionContext ac = ActionContext.getContext();
    ac.put("type", type);

    return SUCCESS;
  }

  // web活动页里编辑页面所用到的方法
  public String activityPlaceById() throws SQLException {
    getDictList();
    ActionContext ac = ActionContext.getContext();
    ac.put("type", type);
    ac.put("id", id);
    Map<String, Object> map = new HashMap<String, Object>();
    if (activityProductPojo == null) {
      activityProductPojo = new ActivityProductPojo();
      map.put("id", id);
    } else {
      map.put("id", id);
    }
    activityProductPojo = activityProductService.getActivityProductById(map);
    return SUCCESS;
  }

  // web活动页里商品编辑
  public String updateActivityProduct() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("id", activityProductPojo.getSpecialId());
    map.put("isdelete", "0");
    map.put("houtai", 1);
    map.put("status", 4);
    specialShowPojos = specialShowService.findSpecialShowList(map);
    if (specialShowPojos.size() == 0) {
      FileUtil.alertMessageBySkip("无此专场！", "activityPlaceById.do?type=" + type + "&titleId="
          + titleId + "&id=" + id);
      return null;
    }
    Map<String, Object> map1 = new HashMap<String, Object>();
    map1.put("productId", activityProductPojo.getProductId());
    map1.put("specialId", activityProductPojo.getSpecialId());
    specialProductPojo = specialProductService.findSpecialProductList(map1);
    if (specialProductPojo.size() == 0) {
      FileUtil.alertMessageBySkip("该专场无此商品！", "activityPlaceById.do?type=" + type + "&titleId="
          + titleId + "&id=" + id);
      return null;
    }

    Map<String, Object> map3 = new HashMap<String, Object>();
    map3.put("id", specialShowPojos.get(0).getActivityId());
    map3.put("type", 3);
    int i = activityTimeService.findActivityTimeCount(map3);
    if (i == 0) {
      FileUtil.alertMessageBySkip("该活动不是专场活动！", "activityPlaceById.do?type=" + type + "&titleId="
          + titleId + "&id=" + id);
      return null;
    }
    Map<String, Object> map2 = new HashMap<String, Object>();
    map2.put("type", type);
    map2.put("titleId", titleId);
    map2.put("productType", activityProductPojo.getProductType());
    map2.put("productId", activityProductPojo.getProductId());
    int t = activityProductService.getActivityPlaceCount(map2);
    if (t != 0) {
      map2.put("id", id);
      int n = activityProductService.getActivityPlaceCount(map2);
      if (n == 0) {
        FileUtil.alertMessageBySkip("在该展位此商品已添加！", "activityPlaceById.do?type=" + type
            + "&titleId=" + titleId + "&id=" + id);
        return null;
      }
    }
    if (activityProductPojo != null) {
      activityProductPojo.setId(id);
      activityProductPojo.setActivityId(specialShowPojos.get(0).getActivityId());
      activityProductService.updateActivityProduct(activityProductPojo);
      FileUtil.alertMessageBySkip("修改成功！", "activityPlaceList.do?type=" + type + "&titleId="
          + titleId);
    }
    return null;

  }

  // web活动页里商品列表
  public String getActivityPlaceList() throws SQLException, DocumentException {
    // getDictList();
    // getActivityProductCount();
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("type", type);
    map.put("titleId", titleId);
    List<ActivityProductPojo> list = activityProductService.getActivityPlaceList(map);
    JSONArray json = JSONArray.fromObject(list);
    page.setRowCount(activityProductService.getActivityPlaceCount(map));
    page.setResult(json.toString());
    return SUCCESS;
  }

  // web活动页里商品条数
  public String getActivityPlaceCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (type != null) {
        map.put("type", type);
      }
      if (types != null) {
        map.put("types", types);
      }
      map.put("titleId", titleId);
      if (activityProductPojo != null) {
        map.put("productName", activityProductPojo.getProductName());
      }
      int i = activityProductService.getActivityPlaceCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  // web活动页里新增活动商品
  public String insertActivityProduct() throws Exception {
    getDictList();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("id", activityProductPojo.getSpecialId());
    map.put("isdelete", "0");
    map.put("houtai", 1);
    map.put("status", 4);
    specialShowPojos = specialShowService.findSpecialShowList(map);
    if (specialShowPojos.size() == 0) {
      FileUtil.alertMessageBySkip("无此专场！", "activityPlaceAdd.do?type=" + type + "&titleId="
          + titleId);
      return null;
    }
    Map<String, Object> map1 = new HashMap<String, Object>();
    map1.put("productId", activityProductPojo.getProductId());
    map1.put("specialId", activityProductPojo.getSpecialId());
    specialProductPojo = specialProductService.findSpecialProductList(map1);
    if (specialProductPojo.size() == 0) {
      FileUtil.alertMessageBySkip("该专场无此商品！", "activityPlaceAdd.do?type=" + type + "&titleId="
          + titleId);
      return null;
    }
    Map<String, Object> map3 = new HashMap<String, Object>();
    map3.put("id", specialShowPojos.get(0).getActivityId());
    map3.put("type", 3);
    int i = activityTimeService.findActivityTimeCount(map3);
    if (i == 0) {
      FileUtil.alertMessageBySkip("该活动不是专场活动！", "activityPlaceAdd.do?type=" + type + "&titleId="
          + titleId);
      return null;
    }
    Map<String, Object> map2 = new HashMap<String, Object>();
    map2.put("type", type);
    map2.put("titleId", titleId);
    map2.put("productType", activityProductPojo.getProductType());
    map2.put("productId", activityProductPojo.getProductId());
    int t = activityProductService.getActivityPlaceCount(map2);
    if (t != 0) {
      FileUtil.alertMessageBySkip("在该展位此商品已添加！", "activityPlaceAdd.do?type=" + type + "&titleId="
          + titleId);
      return null;
    }

    if (activityProductPojo != null) {
      activityProductPojo.setType(type);
      activityProductPojo.setTitleId(titleId);
      activityProductPojo.setActivityId(specialShowPojos.get(0).getActivityId());
    }
    activityProductService.insertActivityProduct(activityProductPojo);
    FileUtil.alertMessageBySkip("添加成功！", "activityPlaceList.do?type=" + type + "&titleId="
        + titleId);
    return null;
  }

  public String getActivitySetCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (type == null) {
        type = "21";
      }
      map.put("type", type);
      if (activityProductPojo != null) {
        if (StringUtils.isNotBlank(activityProductPojo.getProductName())) {
          map.put("productName", activityProductPojo.getProductName());
        }
        if (activityProductPojo.getProductId() != null) {
          map.put("productId", activityProductPojo.getProductId());
        }
        if (StringUtils.isNotBlank(activityProductPojo.getProductType())) {
          map.put("productType", activityProductPojo.getProductType());
        }
      }
      int i = activityProductService.getActivitySetCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public String getActivitySetList() throws SQLException {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    if (type == null) {
      type = "21";
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("type", type);
    if (activityProductPojo != null) {
      if (StringUtils.isNotBlank(activityProductPojo.getProductName())) {
        map.put("productName", activityProductPojo.getProductName());
      }
      if (activityProductPojo.getProductId() != null) {
        map.put("productId", activityProductPojo.getProductId());
      }
      if (StringUtils.isNotBlank(activityProductPojo.getProductType())) {
        map.put("productType", activityProductPojo.getProductType());
      }
    }
    List<ActivityProductPojo> list = activityProductService.getActivitySetList(map);
    JSONArray json = JSONArray.fromObject(list);
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String activitySetAdd() throws Exception {
    getDictList();
    // ActionContext ac = ActionContext.getContext();
    // ac.put("type", type);

    return SUCCESS;
  }

  public String addActivitySetProduct() throws Exception {
    getDictList();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("id", activityProductPojo.getSpecialId());
    map.put("isdelete", "0");
    map.put("houtai", 1);
    map.put("status", 4);
    specialShowPojos = specialShowService.findSpecialShowList(map);
    if (specialShowPojos.size() == 0) {
      FileUtil.alertMessageBySkip("无此专场！", "activitySetAdd.do?type=" + type);
      return null;
    }
    Map<String, Object> map1 = new HashMap<String, Object>();
    map1.put("productId", activityProductPojo.getProductId());
    map1.put("specialId", activityProductPojo.getSpecialId());
    specialProductPojo = specialProductService.findSpecialProductList(map1);
    if (specialProductPojo.size() == 0) {
      FileUtil.alertMessageBySkip("该专场无此商品！", "activitySetAdd.do?type=" + type);
      return null;
    }
    Map<String, Object> map3 = new HashMap<String, Object>();
    map3.put("id", specialShowPojos.get(0).getActivityId());
    map3.put("type", 3);
    int i = activityTimeService.findActivityTimeCount(map3);
    if (i == 0) {
      FileUtil.alertMessageBySkip("该活动不是专场活动！", "activitySetAdd.do?type=" + type);
      return null;
    }
    Map<String, Object> map2 = new HashMap<String, Object>();
    if (activityProductPojo != null && activityProductPojo.getCategoryId() != null
        && "1".equals(activityProductPojo.getProductType())) {
      map2.put("categoryId", activityProductPojo.getCategoryId());
    }
    map2.put("type", type);
    map2.put("productType", activityProductPojo.getProductType());
    map2.put("productId", activityProductPojo.getProductId());
    int t = activityProductService.getActivitySetCount(map2);
    if (t != 0) {
      FileUtil.alertMessageBySkip("此商品在该展位已添加！", "activitySetAdd.do?type=" + type);
      return null;
    }

    if (activityProductPojo != null) {
      activityProductPojo.setType(type);
      activityProductPojo.setActivityId(specialShowPojos.get(0).getActivityId());
      activityProductPojo.setTitleId(0l);
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
        activityProductPojo.setImage(file_name);
      } else {
        activityProductPojo.setImage("");
      }
    }
    activityProductService.insertActivityProduct(activityProductPojo);
    FileUtil.alertMessageBySkip("添加成功！", "activitySetManage.do?type=" + type);
    return null;
  }

  public String delActivitySetAll() throws SQLException {
    if (tids == null || tids.length == 0) {
      FileUtil.alertMessageBySkip("请先勾选！", "activitySetManage.do?type=" + type);
    }
    try {
      for (String id : tids) {
        activityProductService.delActivityProduct(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部删除成功！", "activitySetManage.do?type=" + type);
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部删除失败！", "activitySetManage.do?type=" + type);
    }
    return null;
  }

  public String checkActivitySetAll() {
    if (tids == null || tids.length == 0) {
      FileUtil.alertMessageBySkip("请先勾选！", "activitySetManage.do?type=" + type);
    }
    try {
      for (String id : tids) {
        activityProductService.checkActivityProduct(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部审核成功！", "activitySetManage.do?type=" + type);
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部审核失败！", "activitySetManage.do?type=" + type);
    }
    return null;
  }

  public String editActivitySetById() throws SQLException {
    getDictList();
    // ActionContext ac = ActionContext.getContext();
    // ac.put("type", type);
    // ac.put("id", id);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("id", id);
    activityProductPojo = activityProductService.getActivityProductById(map);
    return SUCCESS;
  }

  public String updateActivitySetProduct() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("id", activityProductPojo.getSpecialId());
    map.put("isdelete", "0");
    map.put("houtai", 1);
    map.put("status", 4);
    specialShowPojos = specialShowService.findSpecialShowList(map);
    if (specialShowPojos.size() == 0) {
      FileUtil.alertMessageBySkip("无此专场！", "editActivitySetById.do?type=" + type + "&id=" + id);
      return null;
    }
    Map<String, Object> map1 = new HashMap<String, Object>();
    map1.put("productId", activityProductPojo.getProductId());
    map1.put("specialId", activityProductPojo.getSpecialId());
    specialProductPojo = specialProductService.findSpecialProductList(map1);
    if (specialProductPojo.size() == 0) {
      FileUtil.alertMessageBySkip("该专场无此商品！", "editActivitySetById.do?type=" + type + "&id=" + id);
      return null;
    }

    Map<String, Object> map3 = new HashMap<String, Object>();
    map3.put("id", specialShowPojos.get(0).getActivityId());
    map3.put("type", 3);
    int i = activityTimeService.findActivityTimeCount(map3);
    if (i == 0) {
      FileUtil
          .alertMessageBySkip("该活动不是专场活动！", "editActivitySetById.do?type=" + type + "&id=" + id);
      return null;
    }
    Map<String, Object> map2 = new HashMap<String, Object>();
    if (activityProductPojo != null && activityProductPojo.getCategoryId() != null
        && "1".equals(activityProductPojo.getProductType())) {
      map2.put("categoryId", activityProductPojo.getCategoryId());
    }
    map2.put("type", type);
    map2.put("productType", activityProductPojo.getProductType());
    map2.put("productId", activityProductPojo.getProductId());
    int t = activityProductService.getActivitySetCount(map2);
    if (t > 0) {
      map2.put("id", id);
      int n = activityProductService.getActivitySetCount(map2);
      if (n == 0) {
        FileUtil.alertMessageBySkip("在该展位此商品已添加！", "editActivitySetById.do?type=" + type + "&id="
            + id);
        return null;
      }
    }
    if (activityProductPojo != null) {
      activityProductPojo.setId(id);
      activityProductPojo.setActivityId(specialShowPojos.get(0).getActivityId());
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
        activityProductPojo.setImage(file_name);
      }
      activityProductService.updateActivityProduct(activityProductPojo);
      FileUtil.alertMessageBySkip("修改成功！", "activitySetManage.do?type=" + type);
    }
    return null;

  }
  /**
   * 
   * 跳转到单品复制页面
   * @return
   * @throw
   * @return String
   */
  public String goCopyActivityProduct() {
    getDictList();
    return SUCCESS;
  }
  
  /**
   * 
   * 跳转到单品复制页面
   * @return
   * @throw
   * @return String
   */
  public String copyActivityProduct() throws Exception{
    if (activityProductPojo != null && StringUtils.isNotBlank(activityProductPojo.getCategoryId())) {
      String categoryId = activityProductPojo.getCategoryId();
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("id", activityProductPojo.getId());
      ActivityProductPojo activityProductPojo = activityProductService.getActivityProductById(params);
      if (activityProductPojo != null && "21".equals(activityProductPojo.getType())){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", activityProductPojo.getSpecialId());
        map.put("isdelete", "0");
        map.put("houtai", 1);
        map.put("status", 4);
        specialShowPojos = specialShowService.findSpecialShowList(map);
        if (specialShowPojos.size() == 0) {
          FileUtil.alertMessageBySkip("无此专场！", "activitySetManage.do");
          return null;
        }
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("productId", activityProductPojo.getProductId());
        map1.put("specialId", activityProductPojo.getSpecialId());
        specialProductPojo = specialProductService.findSpecialProductList(map1);
        if (specialProductPojo.size() == 0) {
          FileUtil.alertMessageBySkip("该专场无此商品！", "activitySetManage.do");
          return null;
        }
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("id", specialShowPojos.get(0).getActivityId());
        map3.put("type", 3);
        int i = activityTimeService.findActivityTimeCount(map3);
        if (i == 0) {
          FileUtil.alertMessageBySkip("该活动不是专场活动！", "activitySetManage.do");
          return null;
        }
        Map<String, Object> map2 = new HashMap<String, Object>();
        if (activityProductPojo != null && activityProductPojo.getCategoryId() != null
            && "1".equals(activityProductPojo.getProductType())) {
          map2.put("categoryId", categoryId);
        }
        map2.put("type", activityProductPojo.getType());
        map2.put("productType", activityProductPojo.getProductType());
        map2.put("productId", activityProductPojo.getProductId());
        map2.put("createDate", new Date());
        map2.put("updateDate", new Date());
        int t = activityProductService.getActivitySetCount(map2);
        if (t != 0) {
          FileUtil.alertMessageBySkip("此商品在该展位已添加！", "activitySetManage.do");
          return null;
        }
        activityProductPojo.setId(null);
        activityProductPojo.setCategoryId(categoryId);
        activityProductService.insertActivityProduct(activityProductPojo);
      } else {
        FileUtil.alertMessageBySkip("传输失败！", "activitySetManage.do");
      }
      FileUtil.alertMessageBySkip("传输成功！", "activitySetManage.do");
    } else {
      FileUtil.alertMessageBySkip("传输失败！", "activitySetManage.do");
    }
    return null;
  }
  

  public Long getTitleIds() {
    return titleIds;
  }

  public void setTitleIds(Long titleIds) {
    this.titleIds = titleIds;
  }

  public String getTypes() {
    return types;
  }

  public void setTypes(String types) {
    this.types = types;
  }

}
