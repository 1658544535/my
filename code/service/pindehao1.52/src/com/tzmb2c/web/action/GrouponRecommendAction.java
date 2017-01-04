/*
 * 文 件 名: GrouponRecommendAction.java 创 建 人: admin 创建时间: 2016-09-26
 */
package com.tzmb2c.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.GrouponActivityPojo;
import com.tzmb2c.web.pojo.GrouponRecommendPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.SeckillGoodsPojo;
import com.tzmb2c.web.pojo.SpecialGoodsPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.ZoneGoodsPojo;
import com.tzmb2c.web.service.GrouponActivityService;
import com.tzmb2c.web.service.GrouponRecommendService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.SpecialGoodsService;

public class GrouponRecommendAction extends SuperAction {
  @Autowired
  private GrouponRecommendService grouponRecommendService;
  @Autowired
  private GrouponActivityService grouponActivityService;
  @Autowired
  private ProductTypeService productTypeService;
  @Autowired
  private SpecialGoodsService specialGoodsService;
  private List<ProductTypePojo> productType1List;
  private List<ProductTypePojo> productTypeIdsList;
  private ProductPojo productPojo;
  private Integer special;
  private SpecialGoodsPojo specialGoodsPojo;
  private Integer zones;
  private ZoneGoodsPojo zoneGoodsPojo;
  private Integer seckill;
  private SeckillGoodsPojo seckillGoodsPojo;

  public Integer getSeckill() {
    return seckill;
  }

  public void setSeckill(Integer seckill) {
    this.seckill = seckill;
  }

  public Integer getZones() {
    return zones;
  }

  public void setZones(Integer zones) {
    this.zones = zones;
  }

  public SpecialGoodsPojo getSpecialGoodsPojo() {
    return specialGoodsPojo;
  }

  public void setSpecialGoodsPojo(SpecialGoodsPojo specialGoodsPojo) {
    this.specialGoodsPojo = specialGoodsPojo;
  }

  public ProductPojo getProductPojo() {
    return productPojo;
  }

  public void setProductPojo(ProductPojo productPojo) {
    this.productPojo = productPojo;
  }

  public List<ProductTypePojo> getProductType1List() {
    return productType1List;
  }

  public void setProductType1List(List<ProductTypePojo> productType1List) {
    this.productType1List = productType1List;
  }

  public List<ProductTypePojo> getProductTypeIdsList() {
    return productTypeIdsList;
  }

  public void setProductTypeIdsList(List<ProductTypePojo> productTypeIdsList) {
    this.productTypeIdsList = productTypeIdsList;
  }

  private GrouponRecommendPojo grouponRecommendPojo;
  private Long id;
  private String[] tids;
  private String result;
  private String query;
  private String keywords;
  private Long productTypeIds;



  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public Long getProductTypeIds() {
    return productTypeIds;
  }

  public void setProductTypeIds(Long productTypeIds) {
    this.productTypeIds = productTypeIds;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public GrouponRecommendPojo getGrouponRecommendPojo() {
    return grouponRecommendPojo;
  }

  public void setGrouponRecommendPojo(GrouponRecommendPojo grouponRecommendPojo) {
    this.grouponRecommendPojo = grouponRecommendPojo;
  }

  /**
   * 查询全部条数
   */
  public String goGrouponRecommend() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    try {
      int i = grouponRecommendService.countBy(null);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String grouponRecommendRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (keywords != null && StringUtils.isNotBlank(keywords)) {
      map.put("keywords", keywords);
    }
    try {
      int i = grouponRecommendService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String grouponRecommendList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    if (keywords != null && StringUtils.isNotBlank(keywords)) {
      map.put("keywords", keywords);
    }
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("orderBy", "gr.sorting desc,gr.product_id desc");
    List<GrouponRecommendPojo> grouponRecommendList = null;
    try {
      grouponRecommendList = grouponRecommendService.listPage(map);
      JSONArray json = JSONArray.fromObject(grouponRecommendList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddGrouponRecommend() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增提交
   * 
   * @return
   * @throws Throwable
   */
  public String add() throws Throwable {
    SysLoginPojo user = UserUtil.getUser();
    // 添加专题商品
    if (special != null && special == 1) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("activityId", specialGoodsPojo.getActivityId());
      int count = specialGoodsService.countBy(map);
      if (count > 0) {
        FileUtil.alertMessageBySkip("该商品已添加！", "goGrouponActivityProduct.do?special=1");
        return null;
      }
      if (user != null && specialGoodsPojo != null) {
        specialGoodsPojo.setCreateBy(user.getId());
        specialGoodsPojo.setCreateDate(new Date());
        specialGoodsPojo.setUpdateBy(user.getId());
        specialGoodsPojo.setUpdateDate(new Date());
        specialGoodsPojo.setUpdateDate(new Date());
        specialGoodsPojo.setSorting(0);
        try {
          specialGoodsService.add(specialGoodsPojo);
          FileUtil.alertMessageBySkip("新增成功！", "goAddSpecial.do");
        } catch (Exception e) {
          e.printStackTrace();
          FileUtil.alertMessageBySkip("新增失败！", "goGrouponActivityProduct.do?special=1");
        }
      } else {
        FileUtil.alertMessageBySkip("操作失败！", "goAddSpecial.do");
      }
    } else {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("activityId", grouponRecommendPojo.getActivityId());
      int count = grouponRecommendService.countBy(map);
      if (count > 0) {
        FileUtil.alertMessageBySkip("该商品已推荐！", "goGrouponActivityProduct.do");
        return null;
      }
      if (user != null && grouponRecommendPojo != null) {
        grouponRecommendPojo.setCreateBy(user.getId());
        grouponRecommendPojo.setCreateDate(new Date());
        grouponRecommendPojo.setUpdateBy(user.getId());
        grouponRecommendPojo.setUpdateDate(new Date());
        grouponRecommendPojo.setUpdateDate(new Date());
        grouponRecommendPojo.setSorting(0);
        try {
          grouponRecommendService.add(grouponRecommendPojo);
          FileUtil.alertMessageBySkip("新增成功！", "goGrouponRecommend.do");
        } catch (Exception e) {
          e.printStackTrace();
          FileUtil.alertMessageBySkip("新增失败！", "goGrouponActivityProduct.do");
        }
      } else {
        FileUtil.alertMessageBySkip("操作失败！", "goGrouponRecommend.do");
      }
    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditGrouponRecommend() throws Exception {
    if (id != null && id > 0) {
      grouponRecommendPojo = grouponRecommendService.getById(id);
    }
    return SUCCESS;
  }

  /**
   * 编辑提交
   * 
   * @return
   * @throws Throwable
   */
  public String update() throws Throwable {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && grouponRecommendPojo != null) {
      grouponRecommendPojo.setUpdateBy(user.getId());
      grouponRecommendPojo.setUpdateDate(new Date());
      try {
        grouponRecommendService.update(grouponRecommendPojo);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
        result = "0";
      }
    } else {
      result = "0";
    }

    return SUCCESS;
  }

  /**
   * 根据id审核
   * 
   * @return
   */
  public String check() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && id != null && id > 0) {
      grouponRecommendPojo = new GrouponRecommendPojo();
      grouponRecommendPojo.setId(id);
      grouponRecommendPojo.setUpdateBy(user.getId());
      grouponRecommendPojo.setUpdateDate(new Date());
      try {
        grouponRecommendService.update(grouponRecommendPojo);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 根据id取审
   * 
   * @return
   */
  public String uncheck() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && id != null && id > 0) {
      grouponRecommendPojo = new GrouponRecommendPojo();
      grouponRecommendPojo.setId(id);
      grouponRecommendPojo.setUpdateBy(user.getId());
      grouponRecommendPojo.setUpdateDate(new Date());
      try {
        grouponRecommendService.update(grouponRecommendPojo);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 根据id删除
   * 
   * @return
   */
  public String delete() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && id != null && id > 0) {
      try {
        grouponRecommendService.delete(id);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 审核选中
   * 
   * @return
   */
  public String checkAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      grouponRecommendPojo = new GrouponRecommendPojo();
      for (String tid : tids) {
        grouponRecommendPojo.setId(Long.valueOf(tid));
        grouponRecommendPojo.setUpdateBy(user.getId());
        grouponRecommendPojo.setUpdateDate(new Date());
        try {
          grouponRecommendService.update(grouponRecommendPojo);
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
  public String uncheckAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      grouponRecommendPojo = new GrouponRecommendPojo();
      for (String tid : tids) {
        grouponRecommendPojo.setId(Long.valueOf(tid));
        grouponRecommendPojo.setUpdateBy(user.getId());
        grouponRecommendPojo.setUpdateDate(new Date());
        try {
          grouponRecommendService.update(grouponRecommendPojo);
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
   * 删除选中
   * 
   * @return
   */
  public String deleteAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      for (String tid : tids) {
        try {
          grouponRecommendService.delete(Long.valueOf(tid));
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
  public String goGrouponActivityProduct() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    ProductTypePojo productTypePojo = new ProductTypePojo();
    productTypePojo.setPid(-1L);
    productType1List = productTypeService.getProductTypeByPids(productTypePojo);
    Map<String, Object> map = new HashMap<String, Object>();
    ActionContext ac = ActionContext.getContext();
    productTypePojo.setTopLevel1(-7L);
    ac.put("productTypeIdsList", productTypeService.getProductTypeByPids(productTypePojo));
    try {
      map.put("type", 1);
      map.put("status", 1);
      map.put("isDefault", 1);
      map.put("isDelete", 0);
      if (query != null) {
        map.put("query", query);
      }
      // if (productTypeIds != null) {
      // map.put("productTypeIds", productTypeIds);
      // }
      if (productPojo != null) {
        map.put("productType1", productPojo.getProductType1());
        map.put("productTypeIds", productPojo.getProductTypeIds());
        map.put("productTypeId", productPojo.getProductTypeId());
      }

      if (special != null) {
        ac.put("special", special);
      }
      if (zones != null) {
        ac.put("zones", zones);
      }
      if (seckill != null) {
        ac.put("seckill", seckill);
      }
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
  public String grouponActivityProductList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("type", 1);
    map.put("status", 1);
    map.put("isDefault", 1);
    map.put("isDelete", 0);
    if (query != null) {
      map.put("query", query);
    }
    // if (productTypeIds != null) {
    // map.put("productTypeIds", productTypeIds);
    // }
    if (productPojo != null) {
      map.put("productType1", productPojo.getProductType1());
      map.put("productTypeIds", productPojo.getProductTypeIds());
      map.put("productTypeId", productPojo.getProductTypeId());
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

  public Integer getSpecial() {
    return special;
  }

  public void setSpecial(Integer special) {
    this.special = special;
  }

  public ZoneGoodsPojo getZoneGoodsPojo() {
    return zoneGoodsPojo;
  }

  public void setZoneGoodsPojo(ZoneGoodsPojo zoneGoodsPojo) {
    this.zoneGoodsPojo = zoneGoodsPojo;
  }

  public SeckillGoodsPojo getSeckillGoodsPojo() {
    return seckillGoodsPojo;
  }

  public void setSeckillGoodsPojo(SeckillGoodsPojo seckillGoodsPojo) {
    this.seckillGoodsPojo = seckillGoodsPojo;
  }

}
