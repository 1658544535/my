package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.ArrayList;
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
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.CouponService;
import com.tzmb2c.web.service.GrouponActivityRecordService;
import com.tzmb2c.web.service.GrouponActivityService;
import com.tzmb2c.web.service.GrouponUserRecordService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductTypeService;

public class GuessActivityAction extends SuperAction {
  @Autowired
  private GrouponActivityService grouponActivityService;
  @Autowired
  private ProductService productService;
  @Autowired
  private GrouponActivityRecordService grouponActivityRecordService;
  @Autowired
  private GrouponUserRecordService grouponUserRecordService;
  @Autowired
  private CouponService couponService;
  @Autowired
  private GrouponService grouponService;
  @Autowired
  private ProductTypeService productTypeService;

  private GrouponUserRecordPojo grouponUserRecordPojo;
  private GrouponActivityRecordPojo grouponActivityRecordPojo;
  private GrouponActivityPojo grouponActivityPojo;
  private List<GrouponActivityPojo> grouponActivityList;
  private List<GrouponUserRecordPojo> grouponUserRecordList;
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
  /**
   * 二等奖ids
   */
  private List<Long> twoIds;
  /**
   * 三等奖ids
   */
  private List<Long> ThreeIds;
  /**
   * 活动id
   */
  private Long groActId;
  /**
   * 最低价格
   */
  private Double minPrice;
  /**
   * 最高价格
   */
  private Double maxPrice;
  private Integer panduan;
  private Long productId;
  private Long activityId;
  private String typeIdsStr;
  private String typeIdStr;



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

  public Double getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(Double minPrice) {
    this.minPrice = minPrice;
  }

  public Double getMaxPrice() {
    return maxPrice;
  }

  public void setMaxPrice(Double maxPrice) {
    this.maxPrice = maxPrice;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public List<Long> getTwoIds() {
    return twoIds;
  }

  public void setTwoIds(List<Long> twoIds) {
    this.twoIds = twoIds;
  }

  public List<Long> getThreeIds() {
    return ThreeIds;
  }

  public void setThreeIds(List<Long> threeIds) {
    ThreeIds = threeIds;
  }

  public Integer getPanduan() {
    return panduan;
  }

  public void setPanduan(Integer panduan) {
    this.panduan = panduan;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public List<GrouponUserRecordPojo> getGrouponUserRecordList() {
    return grouponUserRecordList;
  }

  public void setGrouponUserRecordList(List<GrouponUserRecordPojo> grouponUserRecordList) {
    this.grouponUserRecordList = grouponUserRecordList;
  }

  public GrouponUserRecordPojo getGrouponUserRecordPojo() {
    return grouponUserRecordPojo;
  }

  public void setGrouponUserRecordPojo(GrouponUserRecordPojo grouponUserRecordPojo) {
    this.grouponUserRecordPojo = grouponUserRecordPojo;
  }

  public ProductTypePojo getProductTypePojo() {
    return productTypePojo;
  }

  public void setProductTypePojo(ProductTypePojo productTypePojo) {
    this.productTypePojo = productTypePojo;
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
   * 猜价活动count
   * 
   * @return
   * @throw
   * @return String
   */
  public String guessActivityCount() {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("type", 3);
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
  public String guessActivityList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("type", 3);
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
   * 进入新增猜价活动页面
   */
  public String goAddGuessActivity() {
    return SUCCESS;
  }

  /**
   * 新增猜价活动
   */
  public String addGuessActivity() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    if (user != null && grouponActivityPojo != null) {
      grouponActivityPojo.setType(3);
      // grouponActivityPojo.setIsDefault(1);
      grouponActivityPojo.setCreateBy(user.getId());
      grouponActivityPojo.setCreateDate(new Date());
      grouponActivityPojo.setUpdateBy(user.getId());
      grouponActivityPojo.setUpdateDate(new Date());
      try {
        grouponActivityService.add(grouponActivityPojo);
        FileUtil.alertMessageBySkip("新增成功！", "goGuessActivityList.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "goGuessActivityList.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goGuessActivityList.do");
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
   * 
   * @throws SQLException
   */
  public String selectGuessProductCount() throws SQLException {
    if (page == null) {
      page = new Pager();
    }
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
    // 查询下架的默认拼团
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("isDefault", 1);
    params.put("status", 0);
    params.put("isDelete", 0);
    params.put("keywords", keywords);
    if (productPojo != null) {
      params.put("productTypeIds", getProductPojo().getProductTypeIds());
      params.put("productTypeId", getProductPojo().getProductTypeId());
      params.put("productType1", getProductPojo().getProductType1());
    }
    page.setRowCount(grouponActivityService.countBy(params));
    return SUCCESS;
  }

  /**
   * 跳转到选择活动商品List
   * 
   * @throws SQLException
   */
  public String selectGuessProductList() throws SQLException {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    // 查询下架的默认拼团
    if (productPojo == null) {
      productPojo = new ProductPojo();
    }
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("isDefault", 1);
    params.put("status", 0);
    params.put("keywords", keywords);
    params.put("isDelete", 0);
    params.put("pageSize", page.getPageSize());
    params.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (productPojo != null) {
      params.put("productTypeIds", getProductPojo().getProductTypeIds());
      params.put("productTypeId", getProductPojo().getProductTypeId());
      params.put("productType1", getProductPojo().getProductType1());
    }
    page.setRowCount(grouponActivityService.countBy(params));
    try {
      grouponActivityList = grouponActivityService.listPage(params);
      JSONArray json = JSONArray.fromObject(grouponActivityList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 根据商品id查找替换商品
   */
  public String findGuessProductById() throws Exception {
    if (productPojo != null && productPojo.getId() != null && productPojo.getId() != 0) {
      dataMap = new HashMap<String, Object>();
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
   * 跳转猜价活动修改页面
   * 
   * @throws Exception
   * @return String
   */
  public String goUpdateGuessActivity() throws Exception {
    if (grouponActivityPojo != null && grouponActivityPojo.getId() != null) {
      // 查新猜价活动数据
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
   * 猜价活动修改
   * 
   * @return
   * @throw
   * @return String
   */
  public String updateGuessActivity() {
    SysLoginPojo user = UserUtil.getUser();
    if (user != null && grouponActivityPojo != null) {
      grouponActivityPojo.setUpdateBy(user.getId());
      grouponActivityPojo.setUpdateDate(new Date());
      try {
        grouponActivityService.update(grouponActivityPojo);
        FileUtil.alertMessageBySkip("提交成功！", "goGuessActivityList.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！", "goGuessActivityList.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goGuessActivityList.do");
    }
    return null;
  }

  /**
   * 根据id删除
   * 
   * @return
   */
  public String delGuessActivityById() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && id != null && id > 0) {
      try {
        grouponActivityPojo = new GrouponActivityPojo();
        grouponActivityPojo.setId(id);
        grouponActivityPojo.setIsDelete(1);
        grouponActivityService.update(grouponActivityPojo);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 开奖
   * 
   * @return
   */
  public String openWin() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && id != null && id > 0) {
      try {
        grouponActivityPojo = grouponActivityService.getById(id);
        Double realPrice = grouponActivityPojo.getPrice();
        // 开奖处理
        grouponUserRecordPojo = new GrouponUserRecordPojo();
        grouponUserRecordPojo.setRealPrice(realPrice);
        grouponUserRecordPojo.setActivityId(id);
        grouponUserRecordService.openWinHandle(grouponUserRecordPojo);
        // 修改活动状态且活动上架
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", 3);
        params.put("productId", productId);
        grouponActivityList = grouponActivityService.listPage(params);
        if (grouponActivityList != null && grouponActivityList.size() > 0) {
          params.clear();
          params.put("productId", grouponActivityList.get(0).getProductId());
          params.put("isDefault", 1);
          grouponActivityService.listPage(params);

          grouponActivityPojo = new GrouponActivityPojo();
          grouponActivityPojo.setStatus(1);
          grouponActivityService.update(grouponActivityPojo);
          result = "1";
        } else {
          System.out.println("查询不到该猜价活动");
        }
      } catch (Exception e) {
        e.printStackTrace();
        result = "0";
      }
    }
    return SUCCESS;
  }

  /**
   * 删除选中
   * 
   * @return
   */
  public String delGuessActivityByIds() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      try {
        for (String tid : tids) {
          if (tid != null) {
            grouponActivityPojo = new GrouponActivityPojo();
            grouponActivityPojo.setId(Long.valueOf(tid));
            grouponActivityPojo.setIsDelete(1);
            grouponActivityService.update(grouponActivityPojo);
          } else {
            part = true;
          }
        }
      } catch (Exception e) {
        part = true;
        e.printStackTrace();
      }
      if (part) {
        result = "2";
      } else {
        result = "1";
      }
    }
    return SUCCESS;
  }

  /**
   * 审核选中
   * 
   * @return
   */
  public String checkAllGuessActivity() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
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
        result = "2";
      } else {
        result = "1";
      }
    }
    return SUCCESS;
  }

  /**
   * 选中取消审核
   * 
   * @return
   */
  public String uncheckAllGuessActivity() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
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
        result = "2";
      } else {
        result = "1";
      }
    }
    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String guessGroUserRecCount() throws Exception {
    if (panduan != null && page == null) {
      SysLoginPojo user = UserUtil.getUser();
      if (user != null && id != null && id > 0) {
        grouponActivityPojo = grouponActivityService.getById(id);
        Double realPrice = grouponActivityPojo.getPrice();
        // 开奖处理
        grouponUserRecordPojo = new GrouponUserRecordPojo();
        grouponUserRecordPojo.setRealPrice(realPrice);
        grouponUserRecordPojo.setActivityId(id);
        grouponUserRecordService.openWinHandle(grouponUserRecordPojo);
        // 修改活动状态
        grouponActivityPojo = grouponActivityService.getById(id);
        if (grouponActivityPojo != null && grouponActivityPojo.getType() == 3) {
          grouponActivityPojo.setActivityStatus(3);
          grouponActivityService.update(grouponActivityPojo);
        } else {
          System.out.println("查询不到该猜价活动");
        }
        if (productId != null && productId != 0) {
          // 活动上架
          Map<String, Object> params = new HashMap<String, Object>();
          params.put("type", 1);
          params.put("isDefault", 1);
          params.put("productId", productId);
          grouponActivityList = grouponActivityService.listPage(params);
          if (grouponActivityList != null && grouponActivityList.size() > 0) {
            grouponActivityList.get(0).setStatus(1);
            grouponActivityService.update(grouponActivityList.get(0));
          } else {
            System.out.println("查询不到该猜价活动");
          }
        }
        Util.log("猜价微信一等奖通知!/activityId=" + id);
        grouponService.guessWXNotice(1, id);
        try {
          Util.log("发送猜价中奖短信!");
          String text = "恭喜您中 奖啦！您参与的猜价格/0.1夺宝活动开奖啦！商品在1-3天内打包发出，更多关注http://dwz.cn/4JhfzF";
          Map<String, Object> params = new HashMap<String, Object>();
          new HashMap<String, Object>();
          params.put("activityId", id);
          params.put("activityType", 3);
          params.put("prize", 1);
          List<GrouponUserRecordPojo> grouponUserRecordList =
              grouponUserRecordService.listPage(params);
          if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
            for (GrouponUserRecordPojo grouponUserRecordPojo : grouponUserRecordList) {
              if (grouponUserRecordPojo.getLoginName() != null) {
                SmsSendUtil.sendSMS(grouponUserRecordPojo.getLoginName(), text);
              }
            }
          } else {
            Util.log("查询不到一等奖用户!");
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    if (page == null) {
      page = new Pager();
    }
    System.out.println(productId);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("activityId", id);
    map.put("activityType", 3);
    map.put("keywords", keywords);
    map.put("minPrice", minPrice);
    map.put("maxPrice", maxPrice);
    try {
      int i = grouponUserRecordService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String guessGroUserRecList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("activityId", groActId);
    map.put("activityType", 3);
    map.put("keywords", keywords);
    map.put("minPrice", minPrice);
    map.put("maxPrice", maxPrice);
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
   * 发放优惠券
   * 
   * @return
   * @throws Exception
   */
  public void grantCoupon() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    if (user == null) {
      FileUtil.alertMessageBySkip("请先登录!", "loginin.do");
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      int flag = 1;
      // 二等奖处理
      if (StringUtil.checkIsNotNull(productId) && StringUtil.checkIsNotNull(activityId)) {
        try {
          // 获取二等奖用户id
          params.put("activityId", activityId);
          params.put("activityType", 3);
          params.put("prize", 2);
          params.put("isRecCoupon", 0);
          grouponUserRecordList = grouponUserRecordService.listPage(params);
          if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
            twoIds = new ArrayList<Long>();
            for (GrouponUserRecordPojo grouponUserRecord : grouponUserRecordList) {
              if (grouponUserRecord.getUserId() != null && grouponUserRecord.getUserId() > 0) {
                twoIds.add(grouponUserRecord.getUserId());
              }
            }
            if (twoIds != null && twoIds.size() > 0) {
              // 修改用户id修改二等奖用户团记录为获取优惠券
              params.clear();
              params.put("userIds", twoIds);
              params.put("activityId", activityId);
              grouponUserRecordService.updateIsRecCoupon(params);
              // 根据用户id发放二等奖优惠券
              grouponService.gussesPriceGrantCoupon(twoIds, 2, productId, activityId);
              grouponService.guessWXNotice(2, activityId);
            } else {
              System.out.println("查询不到二等奖用户!");
            }
          } else {
            System.out.println("查询不到二等奖用户!");
          }
        } catch (Exception e) {
          flag = 2;
          System.out.println("发放二等奖报错!");
          e.printStackTrace();
        }
      }
      // 三等奖处理
      if (StringUtil.checkIsNotNull(productId) && StringUtil.checkIsNotNull(activityId)) {
        try {
          // 获取三等奖用户id
          params.put("activityId", activityId);
          params.put("activityType", 3);
          params.put("prize", 3);
          params.put("isRecCoupon", 0);
          grouponUserRecordList = grouponUserRecordService.listPage(params);
          if (grouponUserRecordList != null && grouponUserRecordList.size() > 0) {
            ThreeIds = new ArrayList<Long>();
            for (GrouponUserRecordPojo grouponUserRecord : grouponUserRecordList) {
              if (grouponUserRecord.getUserId() != null && grouponUserRecord.getUserId() > 0) {
                ThreeIds.add(grouponUserRecord.getUserId());
              }
            }
            if (ThreeIds != null && ThreeIds.size() > 0) {
              // 修改用户id修改三等奖用户团记录为获取优惠券
              params.clear();
              params.put("userIds", ThreeIds);
              params.put("activityId", activityId);
              grouponUserRecordService.updateIsRecCoupon(params);
              // 根据用户id发放三等奖优惠券
              grouponService.gussesPriceGrantCoupon(ThreeIds, 3, productId, activityId);
              grouponService.guessWXNotice(3, activityId);
            } else {
              System.out.println("查询不到三等奖用户!");
            }
          } else {
            System.out.println("查询不到三等奖用户!");
          }
        } catch (Exception e) {
          flag = 3;
          System.out.println("发放三等奖报错!");
          e.printStackTrace();
        }
      }
      if (flag == 1) {
        FileUtil.alertMessageBySkip("发放优惠券成功!", "goGuessActivityList.do");
      } else if (flag == 2) {
        FileUtil.alertMessageBySkip("发放二等奖优惠券过程出现错误!", "goGuessActivityList.do");
      } else if (flag == 3) {
        FileUtil.alertMessageBySkip("发放三等奖优惠券过程出现错误!", "goGuessActivityList.do");
      }
    }
  }
}
