package com.tzmb2c.web.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.CouponPojo;
import com.tzmb2c.web.pojo.MicroPagePojo;
import com.tzmb2c.web.pojo.NavigationPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.TemplatePageDataPojo;
import com.tzmb2c.web.pojo.UserMakerThemePojo;
import com.tzmb2c.web.service.CouponService;
import com.tzmb2c.web.service.MicroPageService;
import com.tzmb2c.web.service.NavigationService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.TemplatePageDataService;
import com.tzmb2c.web.service.UserMakerThemeService;

public class MicroPageAction extends SuperAction {
  @Autowired
  private MicroPageService microPageService;
  @Autowired
  private TemplatePageDataService templatePageDataService;
  @Autowired
  private ProductService productService;
  @Autowired
  private CouponService couponService;
  @Autowired
  private UserMakerThemeService userMakerThemeService;
  
  private TemplatePageDataPojo templatePageDataPojo;
  private MicroPagePojo microPagePojo;
  private List<MicroPagePojo> microPageList;
  private String[] tids;
  private String s;
  //自定义专题详情json
  private String saveData;
  private String result;
  private Long dataId;
  private File files;
  private Map<String, Object> dataMap;
  private Map<String, Object> productData;
  private String filePath;
  //商品列表
  private ProductPojo productPojo;
  private List<ProductPojo> productList;
  //优惠劵列表
  private List<CouponPojo> couponPojos;
  private CouponPojo couponPojo;
  private Long microPageId;
  private Long id;

  
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TemplatePageDataPojo getTemplatePageDataPojo() {
    return templatePageDataPojo;
  }

  public void setTemplatePageDataPojo(TemplatePageDataPojo templatePageDataPojo) {
    this.templatePageDataPojo = templatePageDataPojo;
  }

  public String getSaveData() {
    return saveData;
  }

  public void setSaveData(String saveData) {
    this.saveData = saveData;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public Long getDataId() {
    return dataId;
  }

  public void setDataId(Long dataId) {
    this.dataId = dataId;
  }

  public File getFiles() {
    return files;
  }

  public void setFiles(File files) {
    this.files = files;
  }

  public Map<String, Object> getDataMap() {
    return dataMap;
  }

  public void setDataMap(Map<String, Object> dataMap) {
    this.dataMap = dataMap;
  }

  public Map<String, Object> getProductData() {
    return productData;
  }

  public void setProductData(Map<String, Object> productData) {
    this.productData = productData;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public ProductPojo getProductPojo() {
    return productPojo;
  }

  public void setProductPojo(ProductPojo productPojo) {
    this.productPojo = productPojo;
  }

  public List<ProductPojo> getProductList() {
    return productList;
  }

  public void setProductList(List<ProductPojo> productList) {
    this.productList = productList;
  }

  public List<CouponPojo> getCouponPojos() {
    return couponPojos;
  }

  public void setCouponPojos(List<CouponPojo> couponPojos) {
    this.couponPojos = couponPojos;
  }

  public CouponPojo getCouponPojo() {
    return couponPojo;
  }

  public void setCouponPojo(CouponPojo couponPojo) {
    this.couponPojo = couponPojo;
  }

  public Long getMicroPageId() {
    return microPageId;
  }

  public void setMicroPageId(Long microPageId) {
    this.microPageId = microPageId;
  }

  public MicroPagePojo getMicroPagePojo() {
    return microPagePojo;
  }

  public void setMicroPagePojo(MicroPagePojo microPagePojo) {
    this.microPagePojo = microPagePojo;
  }

  public List<MicroPagePojo> getMicroPageList() {
    return microPageList;
  }

  public void setMicroPageList(List<MicroPagePojo> microPageList) {
    this.microPageList = microPageList;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getS() {
    return s;
  }

  public void setS(String s) {
    this.s = s;
  }

  /**
   * 查询全部条数
   */
  public String microPageListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (microPagePojo != null) {
      }
      int i = microPageService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String microPageListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (microPagePojo != null) {
      }
      map.put("orderBy", "updateDate desc,createDate desc");
      microPageList = microPageService.listPage(map);
      JSONArray json = JSONArray.fromObject(microPageList);
      page.setRowCount(microPageList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }
  /**
   * 删除微页面
   */
  public String delMicroPage() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getUser();
    if(sysLogin == null){
      FileUtil.alertMessageBySkip("请先登录!", "#");
      return null;
    }
    try {
      if (microPagePojo != null && microPagePojo.getId() != null){
        try {
          microPageService.delete(microPagePojo.getId());
          //删除自定义模板
          Map<String, Object> params = new HashMap<String, Object>();
          params.put("pageId", microPagePojo.getId());
          params.put("type", 4);
          templatePageDataPojo = templatePageDataService.findByParams(params);
          if (templatePageDataPojo != null){
            templatePageDataService.delete(templatePageDataPojo.getId());
          }
          FileUtil.alertMessageBySkip("删除成功!", "microPageList.do");
        } catch (Exception e) {
          FileUtil.alertMessageBySkip("删除失败!", "microPageList.do");
          e.printStackTrace();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 跳转微页面修改
   * @return
   * @throw
   * @return String
   */
  public String goUpdateMicroPage() throws Exception{
    Map<String, Object> params = new HashMap<String, Object>();
    try {
      if(microPagePojo != null && microPagePojo.getId() != null){
        microPagePojo = microPageService.getById(microPagePojo.getId());
        params.put("pageId", microPagePojo.getId());
        params.put("type", 4);
        templatePageDataPojo = templatePageDataService.findByParams(params);
        if(templatePageDataPojo == null){
          templatePageDataPojo = new TemplatePageDataPojo();
          templatePageDataPojo.setData("{}");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }
  /**
   * 跳转微页面新增
   * @return
   * @throw
   * @return String
   */
  public String goAddMicroPage() throws Exception{
    return SUCCESS;
  }
  
  /**
   * 微页面新增
   * @return
   * @throw
   * @return String
   */
  public String addMicroPage() throws Exception{
    SysLoginPojo sysLogin = UserUtil.getUser();
    if(sysLogin == null){
      this.result = String.valueOf(0);
    } else {
      if (microPagePojo != null && StringUtils.isNotBlank(microPagePojo.getTitle())){
        try {
          microPagePojo.setCreateBy(sysLogin.getId());
          microPagePojo.setCreateDate(new Date());
          microPagePojo.setUpdateBy(sysLogin.getId());
          microPagePojo.setUpdateDate(new Date());
          microPageService.add(microPagePojo);
          this.result = String.valueOf(microPagePojo.getId());
        } catch (Exception e) {
          this.result = null;
          e.printStackTrace();
        }
      } else {
        this.result = null;
      }
    }
    return SUCCESS;
  }
  /**
   * 微页面修改
   * @return
   * @throw
   * @return String
   */
  public String updateMicroPage() throws Exception{
    SysLoginPojo sysLogin = UserUtil.getUser();
    if(sysLogin == null){
      this.result = String.valueOf(0);
    } else {
      if(microPagePojo != null && microPagePojo.getId() != null){
        microPageService.update(microPagePojo);
        this.result = String.valueOf(microPagePojo.getId());
      } else {
        this.result = null;
      }
    }
    return SUCCESS;
  }
  
  /**
   * 
   * 添加自定义详情
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String addMicroPageTemplatePageData() throws Exception{
    SysLoginPojo sysLogin = UserUtil.getUser();
    if(sysLogin == null){
      this.result = String.valueOf(0);
    } else {
      if(saveData != null && microPageId != null){
        templatePageDataPojo = new TemplatePageDataPojo();
        templatePageDataPojo.setData(saveData);
        templatePageDataPojo.setType(4);
        templatePageDataPojo.setPageId(microPageId);
        templatePageDataPojo.setCreateBy(sysLogin.getId());
        templatePageDataPojo.setCreateDate(new Date());
        templatePageDataPojo.setUpdateBy(sysLogin.getId());
        templatePageDataPojo.setUpdateDate(new Date());
        templatePageDataService.add(templatePageDataPojo);
        this.result = String.valueOf(templatePageDataPojo.getId());
      } else {
        this.result = null;
      }
    }
    return SUCCESS;
  }
  
  /**
   * 
   * 修改自定义专题详情
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String updateMicroPageTemplatePageData() throws Exception{
    SysLoginPojo sysLogin = UserUtil.getUser();
    if(sysLogin == null){
      this.result = String.valueOf(0);
    } else {
      if(saveData != null && microPageId != null && dataId != null){
        templatePageDataPojo = new TemplatePageDataPojo();
        templatePageDataPojo.setId(dataId);
        templatePageDataPojo.setData(saveData);
        templatePageDataPojo.setPageId(microPageId);
        templatePageDataPojo.setUpdateBy(sysLogin.getId());
        templatePageDataPojo.setUpdateDate(new Date());
        templatePageDataService.update(templatePageDataPojo);
        this.result = String.valueOf(templatePageDataPojo.getId());
      } else {
        //第一次添加微页面时没保存执行添加
        if (dataId == null && microPageId != null) {
          templatePageDataPojo = new TemplatePageDataPojo();
          templatePageDataPojo.setData(saveData);
          templatePageDataPojo.setType(4);
          templatePageDataPojo.setPageId(microPageId);
          templatePageDataPojo.setUpdateBy(sysLogin.getId());
          templatePageDataPojo.setUpdateDate(new Date());
          templatePageDataService.add(templatePageDataPojo);
          this.result = String.valueOf(templatePageDataPojo.getId());
        } else {
          this.result = null;
        }
      }
    }
    return SUCCESS;
  }
  
  /**
   * 上传图片
   * 
   * @return
   * @throws Exception 
   */
  public String microPageImgUpfile() throws Exception {
    try {
      dataMap = new HashMap<String, Object>();
      if (files != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/microPage")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/microPage/", files);
        dataMap.put("src", "/upfiles/microPage/"+file_name);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }
  
  /**
   * 
   * 微页面可视化web页面
   * @return
   * @throw
   * @return String
   */
  public String getMicroPageVisApi(){
    Map<String, Object> params = new HashMap<String, Object>();
    if(id == null || id == 0){
      System.out.println("微页面id不能为空!");
    } else {
      params.put("id", id);
      params.put("status", 1);
      microPagePojo = microPageService.getById(id);
      if(microPagePojo == null){
        System.out.println("查询不到该微页面!");
      } else {
        return SUCCESS;
      }
    }
    return null;
  }
  
}
