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
import com.tencent.common.Util;
import com.tzmb2c.business.service.GrouponService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.SmsSendUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.GrouponActivityPojo;
import com.tzmb2c.web.pojo.GrouponActivityRecordPojo;
import com.tzmb2c.web.pojo.GrouponUserRecordPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.GrouponActivityRecordService;
import com.tzmb2c.web.service.GrouponActivityService;
import com.tzmb2c.web.service.GrouponUserRecordService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductTypeService;

public class FreeDrawAction extends SuperAction {
  @Autowired
  private GrouponActivityService grouponActivityService;
  @Autowired
  private ProductService productService;
  @Autowired
  private GrouponActivityRecordService grouponActivityRecordService;
  @Autowired
  private GrouponUserRecordService grouponUserRecordService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private ProductTypeService productTypeService;
  @Autowired
  private GrouponService grouponService;



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

  private Long activityId;


  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

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

  public GrouponActivityRecordPojo getGrouponActivityRecordPojo() {
    return grouponActivityRecordPojo;
  }

  public void setGrouponActivityRecordPojo(GrouponActivityRecordPojo grouponActivityRecordPojo) {
    this.grouponActivityRecordPojo = grouponActivityRecordPojo;
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
   * 免费抽奖count
   * 
   * @return
   * @throw
   * @return String
   */
  public String freeDrawCount() {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("type", 7);
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
      if (grouponActivityPojo.getKeywords() != null) {
        map.put("keywords1", grouponActivityPojo.getKeywords());
      }
      if (grouponActivityPojo.getStatus() != null) {
        map.put("status", grouponActivityPojo.getStatus());
      }
    }
    try {
      GrouponActivityPojo grouponActivity = new GrouponActivityPojo();
      Date date = new java.util.Date();
      grouponActivity.setCurrentTime(StringUtil.dateString(date));
      grouponActivity.setActivityStatus(1);
      grouponActivityService.update(grouponActivity);
      grouponActivity = new GrouponActivityPojo();
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
  public String freeDrawList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("type", 7);
    map.put("isDelete", 0);
    map.put("orderBy", "ga.sorting desc");
    if (grouponActivityPojo != null) {
      if (grouponActivityPojo.getBeginTime() != null) {
        map.put("beginTimeStr3", StringUtil.dateString(grouponActivityPojo.getBeginTime()));
      }
      if (grouponActivityPojo.getEndTime() != null) {
        map.put("endTimeStr3", StringUtil.dateString(grouponActivityPojo.getEndTime()));
      }
      if (grouponActivityPojo.getActivityStatus() != null) {
        map.put("activityStatus", grouponActivityPojo.getActivityStatus());
      }
      if (grouponActivityPojo.getKeywords() != null) {
        map.put("keywords1", grouponActivityPojo.getKeywords());
      }
      if (grouponActivityPojo.getStatus() != null) {
        map.put("status", grouponActivityPojo.getStatus());
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
   * 进入新增免费抽奖页面
   */
  public String goAddFreeDraw() {
    return SUCCESS;
  }

  /**
   * 进入新增免费抽奖页面
   */
  public String addFreeDraw() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    if (user != null && grouponActivityPojo != null) {
      grouponActivityPojo.setType(7);
      grouponActivityPojo.setCreateBy(user.getId());
      // grouponActivityPojo.setCreateDate(new Date());
      grouponActivityPojo.setUpdateBy(user.getId());
      // grouponActivityPojo.setUpdateDate(new Date());
      try {
        grouponActivityService.add(grouponActivityPojo);
        FileUtil.alertMessageBySkip("新增成功！", "goFreeDrawList.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "goFreeDrawList.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goFreeDrawList.do");
    }
    return null;
  }

  /**
   * 跳转到选择活动商品count
   */
  public String selectFreeDrawProductCount() {
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
    } catch (Exception e) {
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
  public String selectFreeDrawProductList() throws SQLException {
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
  public String findFreeDrawProductById() throws Exception {
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
   * 跳转免费抽奖修改页面
   * 
   * @throws Exception
   * @return String
   */
  public String goUpdateFreeDraw() throws Exception {
    if (grouponActivityPojo != null && grouponActivityPojo.getId() != null) {
      // 查新免费抽奖数据
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
   * 免费抽奖修改
   * 
   * @return
   * @throw
   * @return String
   */
  public String updateFreeDraw() {
    SysLoginPojo user = UserUtil.getUser();
    if (user != null && grouponActivityPojo != null) {
      grouponActivityPojo.setUpdateBy(user.getId());
      grouponActivityPojo.setUpdateDate(new Date());
      try {
        grouponActivityService.update(grouponActivityPojo);
        FileUtil.alertMessageBySkip("提交成功！", "goFreeDrawList.do");
        System.out.println("执行更新操作员：" + user.getName() + "，账号：" + user.getLoginname());
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！", "goFreeDrawList.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goFreeDrawList.do");
    }
    return null;
  }

  /**
   * 根据id删除
   * 
   * @return
   */
  public String delFreeDrawById() throws Exception {
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
        System.out.println("执行删除操作员：" + user.getName() + "，账号：" + user.getLoginname());
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
  public String delFreeDrawByIds() throws Exception {
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
          System.out.println("执行删除操作员：" + user.getName() + "，账号：" + user.getLoginname());
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
   * 根据ID审核
   * 
   * @return
   */
  public String checkFreeDraw() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && id != null && id > 0) {
      boolean part = false;
      grouponActivityPojo = new GrouponActivityPojo();
      grouponActivityPojo.setStatus(1);
      grouponActivityPojo.setId(id);
      grouponActivityPojo.setUpdateBy(user.getId());
      grouponActivityPojo.setUpdateDate(new Date());
      try {
        grouponActivityService.update(grouponActivityPojo);
        System.out.println("执行审核操作员：" + user.getName() + "，账号：" + user.getLoginname());
      } catch (Exception e) {
        part = true;
        e.printStackTrace();
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
   * 根据ID取消审核
   * 
   * @return
   */
  public String uncheckFreeDraw() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && id != null && id > 0) {
      boolean part = false;
      grouponActivityPojo = new GrouponActivityPojo();
      grouponActivityPojo.setStatus(0);
      grouponActivityPojo.setId(id);
      grouponActivityPojo.setUpdateBy(user.getId());
      grouponActivityPojo.setUpdateDate(new Date());
      try {
        grouponActivityService.update(grouponActivityPojo);
        System.out.println("执行审核操作员：" + user.getName() + "，账号：" + user.getLoginname());
      } catch (Exception e) {
        part = true;
        e.printStackTrace();
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
  public String checkAllFreeDraw() throws Exception {
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
          System.out.println("执行审核操作员：" + user.getName() + "，账号：" + user.getLoginname());
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
  public String uncheckAllFreeDraw() throws Exception {
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
          System.out.println("执行取消审核操作员：" + user.getName() + "，账号：" + user.getLoginname());
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
   * 免费抽奖参团列表 查询全部条数
   */
  public String freeDrawGroRecCount() throws Exception {
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
    map.put("groupStatus1", grouponUserRecordPojo.getGroupStatus());
    map.put("status", grouponUserRecordPojo.getStatus());
    map.put("activityType", 7);
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
   * 免费抽奖团列表 查询全部记录
   */
  public String freeDrawGroRecList() throws Exception {
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
    map.put("groupStatus1", grouponUserRecordPojo.getGroupStatus());
    map.put("status", grouponUserRecordPojo.getStatus());
    map.put("activityType", 7);
    map.put("id", grouponUserRecordPojo.getId());
    map.put("isPrize", grouponUserRecordPojo.getIsPrize());
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
   * 免费抽奖参团人列表 查询全部条数
   */
  public String freeDrawGroUserRecCount() throws Exception {
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
   * 免费抽奖参团人列表 查询全部记录
   */
  public String freeDrawGroUserRecList() throws Exception {
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

  /**
   * 免费抽奖加入中奖设置列表
   * 
   * @throws SQLException
   */
  public String FreeDrawGroPrizeList() throws SQLException {

    if (grouponUserRecordPojo.getIsPrize() != null) {
      Map<String, Object> params = new HashMap<String, Object>();
      Map<String, Object> map = new HashMap<String, Object>();
      grouponActivityPojo = grouponActivityService.getById(grouponUserRecordPojo.getActivityId());
      Integer gaPNum = null;
      Integer garPNum = null;
      if (grouponActivityPojo != null && grouponActivityPojo.getPrizeNum() != null) {
        gaPNum = grouponActivityPojo.getPrizeNum();
      }

      params.put("isPrize", 1);
      params.put("id", grouponUserRecordPojo.getId());
      map.put("isPrize", 1);
      map.put("activityId", grouponUserRecordPojo.getActivityId());
      garPNum = grouponUserRecordService.countBy3(map);

      if (garPNum != null && garPNum >= gaPNum) {
        this.result = "2";

      } else {
        grouponUserRecordService.updateIsPrize(params);
        this.result = "1";
      }
    }

    return SUCCESS;

  }

  /**
   * 根据ID移除中奖列表的团
   */
  public String delFreeDrawGroPrizeById() throws SQLException {

    if (grouponUserRecordPojo.getIsPrize() != null) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("isPrize", 0);
      params.put("id", grouponUserRecordPojo.getId());
      grouponUserRecordService.updateIsPrize(params);
      this.result = "1";
    }
    return SUCCESS;
  }


  /**
   * 免费抽奖开奖处理
   * 
   * @param ids 中奖的团
   * @param activityId 免费抽奖活动id
   * @throws SQLException
   */
  public String openFreeDrawHandle() throws SQLException {
    result = "1";
    SysLoginPojo user = UserUtil.getUser();
    if (user == null) {
      result = "请先登录!";
    } else {
      try {
        GrouponActivityPojo grouponActivity = grouponActivityService.getById(activityId);
        if (grouponActivity == null || grouponActivity.getType() != 7) {
          result = "查询不到抽奖活动!";
        } else {
          Util.log("设置活动为已开奖!");
          GrouponActivityPojo grouponActivityUp = new GrouponActivityPojo();
          grouponActivityUp.setId(grouponActivity.getId());
          grouponActivityUp.setActivityStatus(3);
          grouponActivityService.update(grouponActivityUp);
          Util.log("选中的团设置为中奖!");
          GrouponActivityRecordPojo grouponActivityRecord = null;
          Map<String, Object> params = new HashMap<String, Object>();
          List<OrderPojo> orderListW = null;
          try {
            GrouponActivityRecordPojo grouponActivityRecordUp = null;
            GrouponUserRecordPojo grouponUserRecord = null;
            OrderPojo orderup = null;
            Date nowTime = new Date();
            String text = "【拼得好】恭喜您中 奖啦！您参与的免费试用活动开奖啦！商品在1-3天内打包发出，更多关注http://dwz.cn/4JhfzF";
            for (String id : tids) {
              grouponActivityRecord = grouponActivityRecordService.getById(Long.valueOf(id));
              if (grouponActivityRecord != null && grouponActivityRecord.getActivityType() == 7) {
                Util.log("设置团记录为拼团成功");
                grouponActivityRecordUp = new GrouponActivityRecordPojo();
                grouponActivityRecordUp.setId(Long.valueOf(id));
                grouponActivityRecordUp.setStatus(1);
                grouponActivityRecordUp.setUpdateDate(new Date());
                grouponActivityRecordUp.setUpdateBy(user.getId());
                grouponActivityRecordService.update(grouponActivityRecordUp);
                Util.log("设置订单为拼团成功");
                params.clear();
                params.put("sourceId", id);
                params.put("source", 7);
                params.put("isSuccess", 0);
                orderListW = orderService.listPage(params);
                for (OrderPojo orderPojo : orderListW) {
                  orderup = new OrderPojo();
                  orderup.setIsSuccess(1);
                  orderup.setGroupDate(nowTime);
                  orderup.setUpdateBy(user.getId());
                  orderup.setUpdateDate(nowTime);
                  orderup.setId(orderPojo.getId());
                  try {
                    orderService.updateOrder(orderup);
                    Util.log("免费抽奖中奖短信通知!!");
                    if (orderPojo.getLoginname() != null) {
                      SmsSendUtil.sendSMS(orderPojo.getLoginname(), text);
                    }

                    grouponService.addUserOrderNotice(5, orderPojo.getUserId(), orderPojo.getId());
                  } catch (Exception e) {
                    e.printStackTrace();
                  }
                }
                Util.log("设置用户团记录为中奖");
                params.clear();
                params.put("activityId", activityId);
                params.put("attendId", id);
                List<GrouponUserRecordPojo> grouponUserRecordList =
                    grouponUserRecordService.listPage(params);
                if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
                  for (GrouponUserRecordPojo grouponUserRecordPojo : grouponUserRecordList) {
                    grouponUserRecord = new GrouponUserRecordPojo();
                    grouponUserRecord.setId(grouponUserRecordPojo.getId());
                    grouponUserRecord.setPrize(1);
                    grouponUserRecord.setUpdateDate(nowTime);
                    grouponUserRecord.setUpdateBy(user.getId());
                    grouponUserRecordService.update(grouponUserRecord);
                  }
                }
              } else {
                Util.log("查询不到抽奖活动团id为:" + id + "的团");
              }
            }
          } catch (Exception e) {
            result = "设置成功出现异常";
            e.printStackTrace();
          }
          Util.log("除选中的团其他团设置为未中奖!");
          params.clear();
          params.put("grouponIds", tids);
          params.put("activityId", activityId);
          params.put("activityType", 7);
          List<GrouponActivityRecordPojo> grouponActivityRecordList =
              grouponActivityRecordService.listPage(params);
          if (grouponActivityRecordList != null && grouponActivityRecordList.size() > 0) {
            OrderPojo orderup = null;
            List<OrderPojo> orderListF = null;
            GrouponActivityRecordPojo grouponActivityRecordUp = null;
            for (GrouponActivityRecordPojo grouponActivityRecordPojo : grouponActivityRecordList) {
              Util.log("设置团记录为拼团成功");
              grouponActivityRecordUp = new GrouponActivityRecordPojo();
              grouponActivityRecordUp.setId(grouponActivityRecordPojo.getId());
              grouponActivityRecordUp.setStatus(2);
              grouponActivityRecordService.update(grouponActivityRecordUp);
              Util.log("设置团记录为拼团失败");
              params.clear();
              params.put("sourceId", grouponActivityRecordPojo.getId());
              params.put("source", 7);
              orderListF = orderService.listPage(params);
              Date nowTime = new Date();
              for (OrderPojo orderPojo : orderListF) {
                orderup = new OrderPojo();
                orderup.setId(orderPojo.getId());
                orderup.setIsSuccess(2);
                orderup.setUpdateBy(user.getId());
                orderup.setGroupDate(nowTime);
                orderup.setUpdateDate(nowTime);
                try {
                  orderService.updateOrder(orderup);

                  grouponService.addUserOrderNotice(4, orderPojo.getUserId(), orderPojo.getId());
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            }
          } else {
            Util.log("查询不到未中奖的团");
          }
        }
      } catch (Exception e) {
        result = "出现异常";
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 检查是否已开奖
   * 
   * @throws SQLException
   */
  public String checkOpenWin() throws SQLException {
    result = "0";
    Date nowTime = new Date();
    GrouponActivityPojo grouponActivity = grouponActivityService.getById(activityId);
    if (grouponActivity == null) {
      result = "查询不到活动!";
    } else if (grouponActivity.getEndTime().getTime() > nowTime.getTime()) {
      result = "1";
    } else if (grouponActivity.getActivityStatus() == 3) {
      result = "2";
    }
    return SUCCESS;
  }
}
