package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.GrouponActivityPojo;
import com.tzmb2c.web.pojo.GrouponActivityRecordPojo;
import com.tzmb2c.web.pojo.GrouponUserRecordPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.GrouponActivityRecordService;
import com.tzmb2c.web.service.GrouponActivityService;
import com.tzmb2c.web.service.GrouponUserRecordService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductTypeService;

public class FreeGroupActivityAction extends SuperAction {
  @Autowired
  private GrouponActivityService grouponActivityService;
  @Autowired
  private ProductService productService;
  @Autowired
  private GrouponActivityRecordService grouponActivityRecordService;
  @Autowired
  private GrouponUserRecordService grouponUserRecordService;
  @Autowired
  private ProductTypeService productTypeService;

  private GrouponUserRecordPojo grouponUserRecordPojo;
  private GrouponActivityRecordPojo grouponActivityRecordPojo;
  private GrouponActivityPojo grouponActivityPojo;
  private List<GrouponActivityPojo> grouponActivityList;
  private List<ProductTypePojo> productTypePojos;
  private ProductTypePojo productTypePojo;
  private List<ProductPojo> productList;
  private ProductPojo productPojo;
  private String timeId;
  private String keywords;
  private String result;
  private Map<String, Object> dataMap;
  private Long id;
  private String[] tids;
  private String typeIdsStr;
  private String typeIdStr;
  private Date beginTime;
  private Date endTime;
  /**
   * 活动id
   */
  private Long groActId;
  /**
   * 最低价格
   */
  private Integer minPrice;
  /**
   * 最高价格
   */
  private Integer maxPrice;



  public Integer getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(Integer minPrice) {
    this.minPrice = minPrice;
  }

  public Integer getMaxPrice() {
    return maxPrice;
  }

  public void setMaxPrice(Integer maxPrice) {
    this.maxPrice = maxPrice;
  }

  public GrouponUserRecordPojo getGrouponUserRecordPojo() {
    return grouponUserRecordPojo;
  }

  public void setGrouponUserRecordPojo(GrouponUserRecordPojo grouponUserRecordPojo) {
    this.grouponUserRecordPojo = grouponUserRecordPojo;
  }

  public Long getGroActId() {
    return groActId;
  }

  public void setGroActId(Long groActId) {
    this.groActId = groActId;
  }

  public ProductTypePojo getProductTypePojo() {
    return productTypePojo;
  }

  public void setProductTypePojo(ProductTypePojo productTypePojo) {
    this.productTypePojo = productTypePojo;
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

  public GrouponActivityRecordPojo getGrouponActivityRecordPojo() {
    return grouponActivityRecordPojo;
  }

  public void setGrouponActivityRecordPojo(GrouponActivityRecordPojo grouponActivityRecordPojo) {
    this.grouponActivityRecordPojo = grouponActivityRecordPojo;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Map<String, Object> getDataMap() {
    return dataMap;
  }

  public void setDataMap(Map<String, Object> dataMap) {
    this.dataMap = dataMap;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public List<ProductTypePojo> getProductTypePojos() {
    return productTypePojos;
  }

  public void setProductTypePojos(List<ProductTypePojo> productTypePojos) {
    this.productTypePojos = productTypePojos;
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

  public String getTimeId() {
    return timeId;
  }

  public void setTimeId(String timeId) {
    this.timeId = timeId;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public Date getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(Date beginTime) {
    this.beginTime = beginTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public GrouponActivityPojo getGrouponActivityPojo() {
    return grouponActivityPojo;
  }

  public void setGrouponActivityPojo(GrouponActivityPojo grouponActivityPojo) {
    this.grouponActivityPojo = grouponActivityPojo;
  }

  public List<GrouponActivityPojo> getGrouponActivityList() {
    return grouponActivityList;
  }

  public void setGrouponActivityList(List<GrouponActivityPojo> grouponActivityList) {
    this.grouponActivityList = grouponActivityList;
  }

  /**
   * 团免活动count
   * 
   * @return
   * @throw
   * @return String
   */
  public String freeGroupActivityCount() {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("type", 2);
    map.put("isDelete", 0);
    if (grouponActivityPojo != null) {
      if (grouponActivityPojo.getBeginTime() != null) {
        map.put("beginTimeStr", StringUtil.dateString(grouponActivityPojo.getBeginTime()));
      }
      if (grouponActivityPojo.getEndTime() != null) {
        map.put("endTimeStr", StringUtil.dateString(grouponActivityPojo.getEndTime()));
      }
      if (grouponActivityPojo.getActivityStatus() != null) {
        map.put("activityStatus", grouponActivityPojo.getActivityStatus());
      }
    }
    try {
      GrouponActivityPojo grouponActivity = new GrouponActivityPojo();
      Date date = new java.util.Date();
      grouponActivity.setCurrentTime(StringUtil.dateString(date));
      grouponActivity.setActivityStatus(1);
      grouponActivityService.update(grouponActivity);
      grouponActivity.setCurrentTime2(StringUtil.dateString(date));
      grouponActivity.setActivityStatus(2);
      grouponActivityService.update(grouponActivity);

      int i = grouponActivityService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String freeGroupActivityList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("type", 2);
    map.put("isDelete", 0);
    if (grouponActivityPojo != null) {
      if (grouponActivityPojo.getBeginTime() != null) {
        map.put("beginTimeStr", StringUtil.dateString(grouponActivityPojo.getBeginTime()));
      }
      if (grouponActivityPojo.getEndTime() != null) {
        map.put("endTimeStr", StringUtil.dateString(grouponActivityPojo.getEndTime()));
      }
      if (grouponActivityPojo.getActivityStatus() != null) {
        map.put("activityStatus", grouponActivityPojo.getActivityStatus());
      }
    }
    List<GrouponActivityPojo> grouponActivityList = null;
    try {
      grouponActivityList = grouponActivityService.listPage(map);
      JSONArray json = JSONArray.fromObject(grouponActivityList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 进入新增团免活动页面
   */
  public String goAddFreeGroupActivity() {
    return SUCCESS;
  }

  /**
   * 进入新增团免活动页面
   */
  public String addFreeGroupActivity() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    if (user != null && grouponActivityPojo != null) {
      grouponActivityPojo.setType(2);
      grouponActivityPojo.setCreateBy(user.getId());
      // grouponActivityPojo.setCreateDate(new Date());
      grouponActivityPojo.setUpdateBy(user.getId());
      // grouponActivityPojo.setUpdateDate(new Date());
      try {
        grouponActivityService.add(grouponActivityPojo);
        FileUtil.alertMessageBySkip("新增成功！", "goFreeGroupActivityList.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "goFreeGroupActivityList.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goFreeGroupActivityList.do");
    }
    return null;
  }
  
  /***
   * 获取二级类目
   * 
   * @return
   */
  public String getProductType2() throws Exception {
    typeIdsStr = "";
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

  /**
   * 跳转到选择活动商品count
   */
  public String selectFreeGroupProductCount() {
    if (page == null) {
      page = new Pager();
    }
    // 查询下架的商品
    if (productPojo == null) {
      productPojo = new ProductPojo();
    }
    try {
      ProductTypePojo productTypePojo = new ProductTypePojo();
      productTypePojo.setPid(-1L);
      ActionContext ac = ActionContext.getContext();
      ac.put("productType1List", productTypeService.getProductTypeByPids(productTypePojo));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    productPojo.setStatus(1);
    productPojo.setKeywords(keywords);
    page.setRowCount(productService.getCount(productPojo, page));
    return SUCCESS;
  }

  /**
   * 跳转到选择活动商品List
   * 
   * @throws SQLException
   */
  public String selectFreeGroupProductList() throws SQLException {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    // 查询下架的商品
    if (productPojo == null) {
      productPojo = new ProductPojo();
    }
    productPojo.setStatus(1);
    productPojo.setKeywords(keywords);
    try {
      productList = productService.getProductAll(productPojo, page);
      JSONArray json = JSONArray.fromObject(productList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 根据商品id查找替换商品
   */
  public String findFreeGroupProductById() throws Exception {
    if (productPojo != null && productPojo.getId() != null && productPojo.getId() != 0) {
      dataMap = new HashMap<String, Object>();
      // productPojo.setStatus(1);
      productPojo = productService.findProduct(productPojo);
      if (productPojo != null) {
        dataMap.put("result", productPojo);
      } else {
        dataMap.put("result", null);
      }
    }
    return SUCCESS;
  }

  /**
   * 
   * 跳转团免活动修改页面
   * 
   * @throws Exception
   * @return String
   */
  public String goUpdateFreeGroupActivity() throws Exception {
    if (grouponActivityPojo != null && grouponActivityPojo.getId() != null) {
      // 查新团免活动数据
      grouponActivityPojo = grouponActivityService.getById(grouponActivityPojo.getId());
      if (grouponActivityPojo != null) {
        if (grouponActivityPojo.getBeginTime() != null) {
          grouponActivityPojo.setBeginTimeStr(StringUtil.dateString(grouponActivityPojo
              .getBeginTime()));
        }
        if (grouponActivityPojo.getEndTime() != null) {
          grouponActivityPojo.setBeginTimeStr(StringUtil.dateString(grouponActivityPojo
              .getEndTime()));
        }
      }
    }
    return SUCCESS;
  }

  /**
   * 
   * 团免活动修改
   * 
   * @return
   * @throw
   * @return String
   */
  public String updateFreeGroupActivity() {
    SysLoginPojo user = UserUtil.getUser();
    if (user != null && grouponActivityPojo != null) {
      grouponActivityPojo.setUpdateBy(user.getId());
      grouponActivityPojo.setUpdateDate(new Date());
      try {
        grouponActivityService.update(grouponActivityPojo);
        FileUtil.alertMessageBySkip("提交成功！", "goFreeGroupActivityList.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！", "goFreeGroupActivityList.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goFreeGroupActivityList.do");
    }
    return null;
  }

  /**
   * 根据id删除
   * 
   * @return
   */
  public String delFreeGroupActivityById() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && id != null && id > 0) {
      grouponActivityPojo = new GrouponActivityPojo();
      grouponActivityPojo.setId(id);
      grouponActivityPojo.setUpdateBy(user.getId());
      // grouponActivityPojo.setUpdateDate(new Date());
      grouponActivityPojo.setIsDelete(1);
      try {
        grouponActivityService.update(grouponActivityPojo);
        this.result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 删除选中
   * 
   * @return
   */
  public String delFreeGroupActivityByIds() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      grouponActivityPojo = new GrouponActivityPojo();
      for (String tid : tids) {
        grouponActivityPojo.setId(Long.valueOf(tid));
        grouponActivityPojo.setUpdateBy(user.getId());
        // grouponActivityPojo.setUpdateDate(new Date());
        grouponActivityPojo.setIsDelete(1);
        try {
          grouponActivityService.update(grouponActivityPojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        this.result = "2";
      } else {
        this.result = "1";
      }
    }
    return SUCCESS;
  }

  /**
   * 审核选中
   * 
   * @return
   */
  public String checkAllFreeGroupActivity() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      grouponActivityPojo = new GrouponActivityPojo();
      grouponActivityPojo.setStatus(1);
      for (String tid : tids) {
        grouponActivityPojo.setId(Long.valueOf(tid));
        grouponActivityPojo.setUpdateBy(user.getId());
        grouponActivityPojo.setUpdateDate(new Date());
        try {
          grouponActivityService.update(grouponActivityPojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        this.result = "2";
      } else {
        this.result = "1";
      }
    }
    return SUCCESS;
  }

  /**
   * 选中取消审核
   * 
   * @return
   */
  public String uncheckAllFreeGroupActivity() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      grouponActivityPojo = new GrouponActivityPojo();
      grouponActivityPojo.setStatus(0);
      for (String tid : tids) {
        grouponActivityPojo.setId(Long.valueOf(tid));
        grouponActivityPojo.setUpdateBy(user.getId());
        grouponActivityPojo.setUpdateDate(new Date());
        try {
          grouponActivityService.update(grouponActivityPojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        this.result = "2";
      } else {
        this.result = "1";
      }
    }
    return SUCCESS;
  }
  
  /**
   * 团免活动参团列表
   * 查询全部条数
   */
  public String freeGroupGroRecCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (beginTime != null) {
      map.put("beginTimeStr", StringUtil.dateString(beginTime));
    }
    if (endTime != null) {
      map.put("endTimeStr", StringUtil.dateString(endTime));
    }
      map.put("keywords", grouponUserRecordPojo.getKeywords());
      map.put("groupStatus", grouponUserRecordPojo.getGroupStatus());
      map.put("activityType", 2);
      map.put("id", grouponUserRecordPojo.getId());
    try {
      int i = grouponUserRecordService.countBy3(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 团免活动团列表
   * 查询全部记录
   */
  public String freeGroupGroRecList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (beginTime != null) {
      map.put("beginTimeStr", StringUtil.dateString(beginTime));
    }
    if (endTime != null) {
      map.put("endTimeStr", StringUtil.dateString(endTime));
    }
    map.put("keywords", grouponUserRecordPojo.getKeywords());
    map.put("groupStatus", grouponUserRecordPojo.getGroupStatus());
    map.put("activityType", 2);
    map.put("id", grouponUserRecordPojo.getId());
    List<GrouponUserRecordPojo> grouponRecordList = null;
    try {
      grouponRecordList = grouponUserRecordService.listPage3(map);
      JSONArray json = JSONArray.fromObject(grouponRecordList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 团免活动参团人列表
   * 查询全部条数
   */
  public String freeGroupGroUserRecCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("attendId", grouponUserRecordPojo.getAttendId());
    map.put("keywords", keywords);
    map.put("source", 2);
    try {
      int i = grouponUserRecordService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 团免活动参团人列表
   * 查询全部记录
   */
  public String freeGroupGroUserRecList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("attendId", grouponUserRecordPojo.getAttendId());
    map.put("keywords", keywords);
    map.put("source", 2);
    map.put("orderBy", " gur.is_head desc");
    List<GrouponUserRecordPojo> grouponUserRecordList = null;
    try {
      grouponUserRecordList = grouponUserRecordService.listPage(map);
      JSONArray json = JSONArray.fromObject(grouponUserRecordList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }
}
