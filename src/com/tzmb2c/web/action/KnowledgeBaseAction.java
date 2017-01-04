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

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityTimePojo;
import com.tzmb2c.web.pojo.KnowledgeBasePojo;
import com.tzmb2c.web.pojo.LabelPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.SpecialProductPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.UserCirclePostPojo;
import com.tzmb2c.web.pojo.YourFavouritesDetailPojo;
import com.tzmb2c.web.pojo.YourFavouritesPojo;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.KnowledgeBaseService;
import com.tzmb2c.web.service.LabelService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.YourFavouritesDetailService;
import com.tzmb2c.web.service.YourFavouritesService;

public class KnowledgeBaseAction extends SuperAction {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Autowired
  private ActivityTimeService activityTimeService;
  @Autowired
  private KnowledgeBaseService knowledgeBaseService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ProductSkuLinkService productSkuLinkService;
  @Autowired
  private LabelService labelService;
  @Autowired
  private YourFavouritesService yourFavouritesService;
  @Autowired
  private YourFavouritesDetailService favouritesDetailService;
  
  private SpecialProductPojo specialProductPojo;
  private KnowledgeBasePojo knowledgeBasePojo, knowledgeBase;
  private ActivityTimePojo activityTimePojo;
  private List<SysDictPojo> statusSysDictList = null;
  private Long timeId;
  private Long productId;
  private String productName;
  private double sellPrice;
  private String result;
  private String[] tids;
  private String activityTime;
  private Integer t;
  private List<ProductSkuLinkPojo> productSkuLinkPojos;
  private ProductSkuLinkPojo productSkuLinkPojo;
  private LabelPojo labelPojo;
  private Integer op;
  private String typeName;
  private String activityDate;
  private List<ActivityTimePojo> activityTimeList;
  private File upfile;
  private String upfileFileName;
  private List<LabelPojo> labelPojoList;
  private Long userId;
  private Long ageType;
  private Long skillType;
  private Long id;
  private YourFavouritesPojo yourFavourites;
  /**
   * 返回json数据用
   */
  private Map<String, Object> dataMap;

  public Map<String, Object> getDataMap() {
    return dataMap;
  }

  public void setDataMap(Map<String, Object> dataMap) {
    this.dataMap = dataMap;
  }

  public YourFavouritesPojo getYourFavourites() {
    return yourFavourites;
  }

  public void setYourFavourites(YourFavouritesPojo yourFavourites) {
    this.yourFavourites = yourFavourites;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getAgeType() {
    return ageType;
  }

  public void setAgeType(Long ageType) {
    this.ageType = ageType;
  }

  public Long getSkillType() {
    return skillType;
  }

  public void setSkillType(Long skillType) {
    this.skillType = skillType;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public List<LabelPojo> getLabelPojoList() {
    return labelPojoList;
  }

  public void setLabelPojoList(List<LabelPojo> labelPojoList) {
    this.labelPojoList = labelPojoList;
  }

  public List<ActivityTimePojo> getactivityTimeList() {
    return activityTimeList;
  }

  public void setActivityTimeList(List<ActivityTimePojo> activityTimeList) {
    this.activityTimeList = activityTimeList;
  }

  public LabelPojo getLabelPojo() {
    return labelPojo;
  }

  public void setLabelPojo(LabelPojo labelPojo) {
    this.labelPojo = labelPojo;
  }

  public ProductSkuLinkPojo getProductSkuLinkPojo() {
    return productSkuLinkPojo;
  }

  public void setProductSkuLinkPojo(ProductSkuLinkPojo productSkuLinkPojo) {
    this.productSkuLinkPojo = productSkuLinkPojo;
  }

  public List<ProductSkuLinkPojo> getProductSkuLinkPojos() {
    return productSkuLinkPojos;
  }

  public void setProductSkuLinkPojos(List<ProductSkuLinkPojo> productSkuLinkPojos) {
    this.productSkuLinkPojos = productSkuLinkPojos;
  }

  public String getActivityTime() {
    return activityTime;
  }

  public void setActivityTime(String activityTime) {
    this.activityTime = activityTime;
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



  public List<SysDictPojo> getStatusSysDictList() {
    return statusSysDictList;
  }

  public void setStatusSysDictList(List<SysDictPojo> statusSysDictList) {
    this.statusSysDictList = statusSysDictList;
  }

  public KnowledgeBasePojo getKnowledgeBasePojo() {
    return knowledgeBasePojo;
  }

  public void setKnowledgeBasePojo(KnowledgeBasePojo knowledgeBasePojo) {
    this.knowledgeBasePojo = knowledgeBasePojo;
  }

  public KnowledgeBasePojo getKnowledgeBase() {
    return knowledgeBase;
  }

  public void setKnowledgeBase(KnowledgeBasePojo knowledgeBase) {
    this.knowledgeBase = knowledgeBase;
  }

  public SpecialProductPojo getSpecialProductPojo() {
    return specialProductPojo;
  }

  public void setSpecialProductPojo(SpecialProductPojo specialProductPojo) {
    this.specialProductPojo = specialProductPojo;
  }

  public ActivityTimePojo getActivityTimePojo() {
    return activityTimePojo;
  }

  public void setActivityTimePojo(ActivityTimePojo activityTimePojo) {
    this.activityTimePojo = activityTimePojo;
  }

  public Long getTimeId() {
    return timeId;
  }

  public void setTimeId(Long timeId) {
    this.timeId = timeId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public double getSellPrice() {
    return sellPrice;
  }

  public void setSellPrice(double sellPrice) {
    this.sellPrice = sellPrice;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  private void getDictList() {
    try {
      statusSysDictList = sysDictService.getSysDictListByType("status");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String findKnowledgeBaseList() throws SQLException {
    getDictList();
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (knowledgeBasePojo != null) {
      map.put("title", knowledgeBasePojo.getTitle());
      map.put("status", knowledgeBasePojo.getStatus());
      map.put("ageType", knowledgeBasePojo.getAgeType());
      map.put("skillType", knowledgeBasePojo.getSkillType());
      map.put("secSkillType", knowledgeBasePojo.getSecSkillType());
      map.put("optionType", knowledgeBasePojo.getOptionType());
      map.put("productType", knowledgeBasePojo.getProductType());
    }
    List<KnowledgeBasePojo> list = knowledgeBaseService.listPage(map);
    JSONArray json = JSONArray.fromObject(list);
    page.setRowCount(knowledgeBaseService.countBy(map));
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String findKnowledgeBaseCount() throws SQLException {
    getDictList();
    Map<String, Object> map1 = new HashMap<String, Object>();
    labelPojoList = labelService.findLabelList(map1);
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (knowledgeBasePojo != null) {
      map.put("title", knowledgeBasePojo.getTitle());
      map.put("status", knowledgeBasePojo.getStatus());
      map.put("ageType", knowledgeBasePojo.getAgeType());
      map.put("skillType", knowledgeBasePojo.getSkillType());
      map.put("secSkillType", knowledgeBasePojo.getSecSkillType());
      map.put("optionType", knowledgeBasePojo.getOptionType());
      map.put("productType", knowledgeBasePojo.getProductType());
    }
    int i = knowledgeBaseService.countBy(map);
    page.setRowCount(i);
    return SUCCESS;
  }

  public String findKnowledgeBaseById() throws SQLException {
    getDictList();
    knowledgeBasePojo = knowledgeBaseService.getById(knowledgeBasePojo.getId());
    return SUCCESS;
  }

  public String updateKnowledgeBaseList() throws SQLException {
    getDictList();
    knowledgeBasePojo = knowledgeBaseService.getById(knowledgeBasePojo.getId());
    Map<String, Object> map = new HashMap<String, Object>();
    labelPojoList = labelService.findLabelList(map);
    return SUCCESS;
  }

  public void updateKnowledgeBase() throws Throwable {
    getDictList();
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/knowledgeBase")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/knowledgeBase/", upfile);
      knowledgeBasePojo.setSmallIcon(file_name);
    }
    knowledgeBaseService.update(knowledgeBasePojo);
    FileUtil.alertMessageBySkip("修改成功！", "knowledgeBase.do");
  }



  public String checkKnowledgeBase() throws SQLException {
    try {
      knowledgeBaseService.checkKnowledgeBase(knowledgeBasePojo.getId());
      result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  public String uncheckKnowledgeBase() throws SQLException {
    try {
      knowledgeBaseService.uncheckKnowledgeBase(knowledgeBasePojo.getId());
      result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  public String checkKnowledgeBaseAll() {
    StringBuffer url = new StringBuffer("knowledgeBase.do");
    if (tids != null && tids.length > 0) {

      for (String tid : tids) {
        knowledgeBaseService.checkKnowledgeBase(Long.parseLong(tid));
      }
      FileUtil.alertMessageBySkip("审核成功！", url.toString());


    } else {
      FileUtil.alertMessageBySkip("没有勾选！", url.toString());
    }
    return null;
  }

  public String delKnowledgeBase() throws SQLException {
    try {
      knowledgeBaseService.delete(knowledgeBasePojo.getId());
      result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  public String delKnowledgeBaseAll() {
    StringBuffer url = new StringBuffer("knowledgeBase.do");
    if (tids != null && tids.length > 0) {
      for (String tid : tids) {
        knowledgeBaseService.delete(Long.parseLong(tid));
      }
      FileUtil.alertMessageBySkip("删除成功！", url.toString());
    } else {
      FileUtil.alertMessageBySkip("没有勾选！", url.toString());
    }
    return null;
  }

  // 跳转至新增页面
  public String addKnowledgeBase() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    labelPojoList = labelService.findLabelList(map);
    return SUCCESS;
  }

  // 新增提交
  public String insertKnowledgeBase() throws Throwable {
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/knowledgeBase")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/knowledgeBase/", upfile);
      knowledgeBasePojo.setSmallIcon(file_name);
    }

    knowledgeBaseService.add(knowledgeBasePojo);
    FileUtil.alertMessageBySkip("添加成功！", "knowledgeBase.do");

    return null;
  }

  /**
   * 知识库webview
   * 
   * @throws SQLException
   * @return String
   */
  public String goKnowledgeBaseView() throws SQLException {
    if (ageType == null || ageType == 0) {
      System.out.println(">>>>>> ageType为空!");
    } else if (skillType == null || skillType == 0) {
      System.out.println(">>>>>> skillType为空!");
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("ageType", ageType);
      params.put("skillType", skillType);
      params.put("status", 1);
      params.put("orderBy", "update_date desc,create_date desc");
      knowledgeBasePojo = knowledgeBaseService.findKnowledgeByParams(params);
      if (knowledgeBasePojo == null) {
        System.out.println(">>>>>>" + ageType + "和" + skillType + "查不到知识库!");
      }
    }
    return SUCCESS;
  }

  /**
   * 查询知识库webview有你喜欢按钮
   * @return
   * @throw
   * @return String
   */
  public String getYourFavouritesInfo(){
    Map<String, Object> option = new HashMap<String, Object>();
    if(id != null && id != 0){
      // 查询有你喜欢
      yourFavourites = yourFavouritesService.getById(id);
      if(yourFavourites != null){
        dataMap = new HashMap<String, Object>();
        dataMap.put("yourId", yourFavourites.getId());
        dataMap.put("yourName", yourFavourites.getName() == null ? "" : yourFavourites.getName());
        dataMap.put("sort", yourFavourites.getContentType() == null ? "" : yourFavourites.getContentType());
        if (yourFavourites.getContentType() == 1) {
          // 单个商品 序号最大
          option.clear();
          option.put("favId", yourFavourites.getId());
          option.put("type", 1);
          // 活动商品审核通过
          option.put("activity", 1);
          option.put("agStatus", 1);
          // 商品审核通过
          option.put("proStatus", 1);
          option.put("orderBy", "yfd.sorting desc, yfd.create_date desc");
          option.put("pageNo", 0);
          option.put("pageSize", 1);
          List<ProductPojo> products = favouritesDetailService.getProductFavouritesList(option);
          if (products != null && products.size() > 0) {
            ProductPojo product = products.get(0);
            dataMap.put("pid", StringUtil.checkVal(product.getId()));
            dataMap.put("activityId", StringUtil.checkVal(product.getActivityId()));
          }
        } else if (yourFavourites.getContentType() == 2) {
          // 单个笔记 序号最大
          option.clear();
          option.put("favId", yourFavourites.getId());
          option.put("type", 2);
          option.put("status", 1);
          option.put("orderBy", "yfd.sorting desc, yfd.create_date desc");
          option.put("pageNo", 0);
          option.put("pageSize", 1);
          List<UserCirclePostPojo> posts =
              favouritesDetailService.getUserCirclePostFavouritesList(option);
          if (posts != null && posts.size() > 0) {
            UserCirclePostPojo post = posts.get(0);
            dataMap.put("postId", StringUtil.checkVal(post.getId()));
            dataMap.put("userId", StringUtil.checkVal(post.getUserId()));
          }
        } else if (yourFavourites.getContentType() == 3) {
          option.clear();
          option.put("favId", yourFavourites.getId());
          option.put("type", 3);
          option.put("pageNo", 0);
          option.put("pageSize", 1);
          option.put("orderBy", "yfd.sorting desc, yfd.create_date desc");
          List<YourFavouritesDetailPojo> yfds = favouritesDetailService.listPage(option);
          if (yfds != null && yfds.size() > 0) {
            YourFavouritesDetailPojo yfd = yfds.get(0);
            dataMap.put("productTypeId", StringUtil.checkVal(yfd.getProductTypeId()));
          }
        }
      }
    } else {
      System.out.println("有你喜欢id不能为空!");
    }
    return SUCCESS;
  }
  
  /**
   * 根据ID查知识库webview
   * 
   * @throws SQLException
   * @return String
   */
  public String getKnowledgeView() throws SQLException {
    if (id == null || id == 0) {
      System.out.println(">>>>>> 知识ID为空!");
    } else {
      knowledgeBasePojo = knowledgeBaseService.getById(id);
      if (knowledgeBasePojo == null) {
        System.out.println(">>>>>> 未找到ID：" + id + "的知识库!");
      }
    }
    return SUCCESS;
  }

  public Integer getT() {
    return t;
  }

  public void setT(Integer t) {
    this.t = t;
  }

  public Integer getOp() {
    return op;
  }

  public void setOp(Integer op) {
    this.op = op;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public String getActivityDate() {
    return activityDate;
  }

  public void setActivityDate(String activityDate) {
    this.activityDate = activityDate;
  }

  public String getUpfileFileName() {
    return upfileFileName;
  }

  public void setUpfileFileName(String upfileFileName) {
    this.upfileFileName = upfileFileName;
  }

}
