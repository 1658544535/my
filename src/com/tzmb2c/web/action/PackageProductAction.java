package com.tzmb2c.web.action;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.ActivityGoodsPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ScenePojo;
import com.tzmb2c.web.pojo.SceneProductPojo;
import com.tzmb2c.web.pojo.SpecialProductPojo;
import com.tzmb2c.web.pojo.SpecialShowPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.SceneProductService;
import com.tzmb2c.web.service.SceneService;
import com.tzmb2c.web.service.SpecialProductService;
import com.tzmb2c.web.service.SpecialShowService;

public class PackageProductAction extends SuperAction {
  @Autowired
  private SceneProductService sceneProductService;
  private SceneProductPojo sceneProductPojo;
  private List<SceneProductPojo> sceneProductPojos;
  @Autowired
  private SpecialProductService specialProductService;
  private SpecialProductPojo specialProductPojo;
  private List<SpecialProductPojo> specialProductPojos;

  private Integer t;// 操作类型
  private File upfile;// 上传图片
  private long sceneId;
  private long specialId;
  private String[] tids;
  private String result;
  private Integer type;// 1为场景，2为套餐，3为专场
  @Autowired
  private ProductService productService;
  private ProductPojo productPojo;
  private List<ProductPojo> productPojos;
  @Autowired
  private SceneService sceneService;
  @Autowired
  private SpecialShowService specialShowService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  private ActivityGoodsPojo activityGoodsPojo;
  private ScenePojo scenePojo;
  private SpecialShowPojo specialShowPojo;
  private List<ScenePojo> scenePojos;
  private List<SpecialShowPojo> specialShowPojos;
  private Long id;
  private Long activityId;

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public List<ScenePojo> getScenePojos() {
    return scenePojos;
  }

  public void setScenePojos(List<ScenePojo> scenePojos) {
    this.scenePojos = scenePojos;
  }

  public ScenePojo getScenePojo() {
    return scenePojo;
  }

  public void setScenePojo(ScenePojo scenePojo) {
    this.scenePojo = scenePojo;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public long getSceneId() {
    return sceneId;
  }

  public void setSpecialId(long specialId) {
    this.specialId = specialId;
  }

  public long getSpecialId() {
    return specialId;
  }

  public void setSceneId(long sceneId) {
    this.sceneId = sceneId;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  /**
   * 设置套餐产品
   * 
   * @return
   * @throws SQLException
   * @throws ParseException
   */
  public String packageProductManage() throws SQLException, ParseException {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (sceneProductPojo != null) {
      map.put("title", sceneProductPojo.getTitle().trim());
      map.put("productName", sceneProductPojo.getProductName().trim());
      // map.put("status", sceneProductPojo.getStatus());
    }
    map.put("sceneId", sceneId);
    int i = sceneProductService.findSceneProductCount(map);
    page.setRowCount(i);
    return SUCCESS;
  }

  /**
   * 套餐产品列表
   * 
   * @return
   * @throws SQLException
   */
  public String packageProductAllList() throws SQLException {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (sceneProductPojo != null) {
      map.put("title", sceneProductPojo.getTitle().trim());
      map.put("productName", sceneProductPojo.getProductName().trim());
      // map.put("status", sceneProductPojo.getStatus());
    }
    map.put("sceneId", sceneId);
    sceneProductPojos = sceneProductService.findSceneProductList(map);
    JSONArray json = JSONArray.fromObject(sceneProductPojos);
    page.setRowCount(sceneProductPojos.size());
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 批量删除场套餐产品
   * 
   * @return
   */
  public String deletePackageProductAll() {
    try {
      for (String id : tids) {
        sceneProductService.delSceneProductById(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("删除成功！", "packageProductManage.do?sceneId=" + sceneId);
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("删除失败！", "packageProductManage.do?sceneId=" + sceneId);
    }
    return null;
  }

  /**
   * 删除套餐产品
   * 
   * @return
   */
  public String deletePackageProduct() {
    try {
      if (sceneProductPojo != null) {
        sceneProductService.delSceneProductById(sceneProductPojo.getId());
      }
      setResult("1");
    } catch (Exception e) {
      e.printStackTrace();
      setResult("0");
    }
    return SUCCESS;
  }

  /**
   * 批量审核场套餐产品
   * 
   * @return
   */
  public String checkPackageProductAll() {
    try {
      for (String id : tids) {
        sceneProductService.checkSceneProductById(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("审核成功！", "packageProductManage.do?sceneId=" + sceneId);
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("审核失败！", "packageProductManage.do?sceneId=" + sceneId);
    }
    return null;
  }

  /**
   * 取消审核套餐产品
   * 
   * @return
   */
  public String checkPackageProduct() {
    try {
      if (sceneProductPojo != null) {
        sceneProductService.checkSceneProductById(sceneProductPojo.getId());
      }
      setResult("1");
    } catch (Exception e) {
      e.printStackTrace();
      setResult("0");
    }
    return SUCCESS;
  }

  /**
   * 审核套餐产品
   * 
   * @return
   */
  public String uncheckPackageProduct() {
    try {
      if (sceneProductPojo != null) {
        sceneProductService.uncheckSceneProductById(sceneProductPojo.getId());
      }
      setResult("1");
    } catch (Exception e) {
      e.printStackTrace();
      setResult("0");
    }
    return SUCCESS;
  }

  /**
   * 添加/修改套餐产品信息
   * 
   * @return
   * @throws SQLException
   */
  public String packageProductAddInfo() throws SQLException {
    if (page == null) {
      page = new Pager();
    }
    int i = productService.getCount(productPojo, page);
    page.setRowCount(i);
    return SUCCESS;
  }

  // /**
  // * 添加/修改套餐产品信息
  // * @return
  // * @throws SQLException
  // */
  // public String packageProductAddInfoList() throws SQLException{
  // if(page==null){
  // page=new Pager();
  // }
  // productPojos = productService.getProductAll(productPojo, page);
  // JSONArray json = JSONArray.fromObject(sceneProductPojos);
  // page.setRowCount(productPojos.size());
  // page.setResult(json.toString());
  // return SUCCESS;
  // }

  /**
   * 添加/修改套餐产品
   * 
   * @return
   * @throws SQLException
   */
  public String packageProductAdd() throws SQLException {
    if (t == 1) {
      if (productPojo != null) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("sceneId", sceneId);
        map.put("productId", productPojo.getId());
        List<SceneProductPojo> list = sceneProductService.findSceneProductList(map);
        // for (SceneProductPojo s : list) {
        // if (s.getProductId().equals(productPojo.getId())){
        if (list.size() > 0) {
          FileUtil.alertMessageBySkip("该产品已经添加过！", "packageProductAddInfo.do?type=" + type
              + "&sceneId=" + sceneId + "&productPojo.productStatus=1");
          return null;
        }
        // }
        // }
        ProductPojo productPojoAdd = new ProductPojo();
        productPojoAdd = productService.findProduct(productPojo);
        if (productPojoAdd != null) {
          sceneProductPojo = new SceneProductPojo();
          sceneProductPojo.setProductId(productPojoAdd.getId());
          sceneProductPojo.setSellPrice(productPojoAdd.getSellingPrice());
          sceneProductPojo.setScenePrice(productPojoAdd.getDistributionPrice());
          sceneProductPojo.setSceneId(sceneId);
          sceneProductPojo.setProductName(productPojoAdd.getProductName());
        }
      }
    } else {
      if (sceneProductPojo != null) {
        sceneProductPojo = sceneProductService.findSceneProductById(sceneProductPojo.getId());

      }
    }
    return SUCCESS;
  }

  /**
   * 添加/修改套餐产品提交
   * 
   * @return
   */
  public String packageProductAddOk() {
    if (t == 1) {
      try {
        if (sceneProductPojo != null) {
          // if(upfile != null){
          // FileInputStream fin = new FileInputStream(upfile);
          // String file_name = StringUtil.getCurrentDateStr()+".jpg";
          // if(type!= null){
          // String uploadPath =
          // ServletActionContext.getServletContext().getRealPath("/upfiles/scene") +
          // File.separator;
          // File dirfile = new File(uploadPath);
          // //是否存在目录 如果不存在创建文件
          // if (!dirfile.exists() || dirfile != null) {
          // dirfile.mkdirs();
          // }
          // FileUtil.copyFile(fin, uploadPath+file_name);
          // }else{
          // String uploadPath =
          // ServletActionContext.getServletContext().getRealPath("/upfiles/homePackage/packageProduct")
          // + File.separator;
          // File dirfile = new File(uploadPath);
          // //是否存在目录 如果不存在创建文件
          // if (!dirfile.exists() || dirfile != null) {
          // dirfile.mkdirs();
          // }
          // FileUtil.copyFile(fin, uploadPath+file_name);
          // }
          // sceneProductPojo.setImage(file_name);
          // }
          if (type != null) {
            activityGoodsPojo = new ActivityGoodsPojo();
            Date time = new Date();
            scenePojo = sceneService.findSceneById(sceneProductPojo.getSceneId());
            activityGoodsPojo.setTimeId(scenePojo.getActivityId());
            activityGoodsPojo.setProductId(sceneProductPojo.getProductId());
            activityGoodsPojo.setSorting(sceneProductPojo.getSorting());
            activityGoodsPojo.setSellPrice(sceneProductPojo.getSellPrice());
            activityGoodsPojo.setActivePrice(sceneProductPojo.getScenePrice());
            activityGoodsPojo.setStatus(sceneProductPojo.getStatus());
            activityGoodsPojo.setCreateDate(time);
            activityGoodsPojo.setUpdateDate(time);
            activityGoodsPojo.setActivityStock(sceneProductPojo.getSceneStock());
            activityGoodsPojo.setActivityNum(sceneProductPojo.getSceneNum());
            activityGoodsService.insertActivityGoods(activityGoodsPojo);
            Long t = activityGoodsPojo.getId();
            sceneProductPojo.setGoodId(t);
          }
          sceneProductPojo.setPreviewPro(0);
          sceneProductService.insertSceneProduct(sceneProductPojo);
          if (type != null) {
            FileUtil.alertMessageBySkip("添加成功！", "sceneProductList.do?sceneProductPojo.sceneId="
                + sceneProductPojo.getSceneId());
          } else {
            FileUtil.alertMessageBySkip("添加成功！", "packageProductManage.do?sceneId="
                + sceneProductPojo.getSceneId());
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        if (type != null) {
          FileUtil.alertMessageBySkip("添加失败！", "sceneProductList.do?sceneProductPojo.sceneId="
              + sceneProductPojo.getSceneId());
        } else {
          FileUtil.alertMessageBySkip("添加失败！", "packageProductManage.do?sceneId="
              + sceneProductPojo.getSceneId());
        }
      }
    } else {
      try {
        if (sceneProductPojo != null) {
          // if(upfile != null){
          // FileInputStream fin = new FileInputStream(upfile);
          // String file_name = StringUtil.getCurrentDateStr()+".jpg";
          // String uploadPath =
          // ServletActionContext.getServletContext().getRealPath("/upfiles/homePackage/packageProduct")
          // + File.separator;
          // File dirfile = new File(uploadPath);
          //
          // //是否存在目录 如果不存在创建文件
          // if (!dirfile.exists() || dirfile != null) {
          // dirfile.mkdirs();
          // }
          //
          // FileUtil.copyFile(fin, uploadPath+file_name);
          // sceneProductPojo.setImage(file_name);
          // }
          sceneProductService.updateSceneProductById(sceneProductPojo);
          FileUtil.alertMessageBySkip("编辑成功！", "packageProductManage.do?sceneId="
              + sceneProductPojo.getSceneId());
        }
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("编辑失败！",
            "packageProductManage.do?sceneId=" + sceneProductPojo.getSceneId());
      }
    }
    return null;
  }

  /**
   * 套餐产品前端显示
   * 
   * @return
   * @throws SQLException
   */
  public String packageProductWeb() throws SQLException {
    try {
      Map<String, Object> option = new HashMap<String, Object>();
      if (id == null || id.equals("")) {
        // 提示
      } else {
        option.put("id", id);
        option.put("type", 2);
        option.put("status", 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        option.put("currentTimeStr", sdf.format(new Date()));
        scenePojos = sceneService.findSceneList(option);
        // HttpSession session = ServletActionContext.getRequest().getSession();
        if (scenePojos.size() != 0) {
          scenePojo = scenePojos.get(0);
          option = new HashMap<String, Object>();
          option.put("sceneId", scenePojo.getId());
          // option.put("status", 1);
          sceneProductPojos = sceneProductService.findSceneProductList(option);

          // ActionContext.getContext().getSession().put("sceneProductPojoList", sceneProductPojos);
          // session.setAttribute("sceneProductPojoList", sceneProductPojos);
        } else {
          // session.setAttribute("sceneProductPojoList", null);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public Integer getT() {
    return t;
  }

  public void setT(Integer t) {
    this.t = t;
  }

  public SceneProductPojo getSceneProductPojo() {
    return sceneProductPojo;
  }

  public void setSceneProductPojo(SceneProductPojo sceneProductPojo) {
    this.sceneProductPojo = sceneProductPojo;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public ProductPojo getProductPojo() {
    return productPojo;
  }

  public void setProductPojo(ProductPojo productPojo) {
    this.productPojo = productPojo;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<SceneProductPojo> getSceneProductPojos() {
    return sceneProductPojos;
  }

  public void setSceneProductPojos(List<SceneProductPojo> sceneProductPojos) {
    this.sceneProductPojos = sceneProductPojos;
  }

  public SpecialProductPojo getSpecialProductPojo() {
    return specialProductPojo;
  }

  public void setSpecialProductPojo(SpecialProductPojo specialProductPojo) {
    this.specialProductPojo = specialProductPojo;
  }

  /**
   * 可添加专场的产品的信息
   * 
   * @return
   * @throws SQLException
   */
  public String specialProductAddInfo() throws SQLException {
    if (page == null) {
      page = new Pager();
    }
    int i = productService.getCount(productPojo, page);
    page.setRowCount(i);
    return SUCCESS;
  }

  // /**
  // * 添加/修改套餐产品信息
  // * @return
  // * @throws SQLException
  // */
  // public String packageProductAddInfoList() throws SQLException{
  // if(page==null){
  // page=new Pager();
  // }
  // productPojos = productService.getProductAll(productPojo, page);
  // JSONArray json = JSONArray.fromObject(sceneProductPojos);
  // page.setRowCount(productPojos.size());
  // page.setResult(json.toString());
  // return SUCCESS;
  // }
  /**
   * 添加专场产品
   * 
   * @return
   * @throws SQLException
   */
  public String specialProductAdd() throws SQLException {
    if (productPojo != null) {
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("specialId", specialId);
      map.put("productId", productPojo.getId());
      List<SpecialProductPojo> list = specialProductService.findSpecialProductList(map);
      if (list.size() > 0) {
        FileUtil.alertMessageBySkip(
            "该产品已经添加过！",
            "specialProductAddInfo.do?activityId=" + activityId + "&specialId=" + specialId
                + "&productPojo.productStatus=1&productPojo.userBrandId="
                + productPojo.getUserBrandId());
        return null;
      }
      ProductPojo productPojoAdd = new ProductPojo();
      productPojoAdd = productService.findProduct(productPojo);
      if (productPojoAdd != null) {
        specialProductPojo = new SpecialProductPojo();
        specialProductPojo.setProductId(productPojoAdd.getId());
        specialProductPojo.setSellPrice(productPojoAdd.getSellingPrice());
        specialProductPojo.setSpecialPrice(productPojoAdd.getDistributionPrice());
        BigDecimal fact = new BigDecimal(String.valueOf(specialProductPojo.getSpecialPrice()));
        BigDecimal sell = new BigDecimal(String.valueOf(specialProductPojo.getSellPrice()));
        BigDecimal tips =
            fact.divide(sell, 3, RoundingMode.HALF_UP).multiply(new BigDecimal(10))
                .setScale(1, RoundingMode.HALF_UP);
        if (tips.doubleValue() < 10) {
          specialProductPojo.setTips(tips.toPlainString() + "折");
        } else {
          specialProductPojo.setTips("无折扣");
        }
        specialProductPojo.setSpecialId(specialId);
        specialProductPojo.setProductName(productPojoAdd.getProductName());
        specialProductPojo.setUserBrandId(productPojo.getUserBrandId());
      }
    }
    return SUCCESS;
  }

  /**
   * 添加的专场产品提交
   * 
   * @return
   */
  public String specialProductAddOk() {
    try {
      if (specialProductPojo != null) {
        // if(upfile != null){
        // FileInputStream fin = new FileInputStream(upfile);
        // String file_name = StringUtil.getCurrentDateStr()+".jpg";
        // if(type!= null){
        // String uploadPath =
        // ServletActionContext.getServletContext().getRealPath("/upfiles/scene") + File.separator;
        // File dirfile = new File(uploadPath);
        // //是否存在目录 如果不存在创建文件
        // if (!dirfile.exists() || dirfile != null) {
        // dirfile.mkdirs();
        // }
        // FileUtil.copyFile(fin, uploadPath+file_name);
        // }else{
        // String uploadPath =
        // ServletActionContext.getServletContext().getRealPath("/upfiles/homePackage/packageProduct")
        // + File.separator;
        // File dirfile = new File(uploadPath);
        // //是否存在目录 如果不存在创建文件
        // if (!dirfile.exists() || dirfile != null) {
        // dirfile.mkdirs();
        // }
        // FileUtil.copyFile(fin, uploadPath+file_name);
        // }
        // sceneProductPojo.setImage(file_name);
        // }
        activityGoodsPojo = new ActivityGoodsPojo();
        Date time = new Date();
        specialShowPojo = specialShowService.findSpecialShowById(specialProductPojo.getSpecialId());
        activityGoodsPojo.setTimeId(specialShowPojo.getActivityId());
        activityGoodsPojo.setProductId(specialProductPojo.getProductId());
        activityGoodsPojo.setSorting(specialProductPojo.getSorting());
        activityGoodsPojo.setSellPrice(specialProductPojo.getSellPrice());
        activityGoodsPojo.setActivePrice(specialProductPojo.getSpecialPrice());
        BigDecimal fact = new BigDecimal(String.valueOf(specialProductPojo.getSpecialPrice()));
        BigDecimal sell = new BigDecimal(String.valueOf(specialProductPojo.getSellPrice()));
        BigDecimal tips =
            fact.divide(sell, 3, RoundingMode.HALF_UP).multiply(new BigDecimal(10))
                .setScale(1, RoundingMode.HALF_UP);
        if (tips.doubleValue() < 10) {
          activityGoodsPojo.setTips(tips.toPlainString() + "折");
        } else {
          activityGoodsPojo.setTips("无折扣");
        }
        activityGoodsPojo.setStatus(specialProductPojo.getStatus());
        activityGoodsPojo.setCreateDate(time);
        activityGoodsPojo.setUpdateDate(time);
        activityGoodsPojo.setActivityStock(specialProductPojo.getSpecialStock());
        activityGoodsPojo.setActivityNum(specialProductPojo.getSpecialNum());
        activityGoodsService.insertActivityGoods(activityGoodsPojo);
        Long t = activityGoodsPojo.getId();
        specialProductPojo.setGoodId(t);
        specialProductService.insertSpecialProduct(specialProductPojo);
        FileUtil.alertMessageBySkip("添加成功！", "specialProductList.do?activityId=" + activityId
            + "&specialProductPojo.userBrandId=" + specialProductPojo.getUserBrandId()
            + "&specialProductPojo.specialId=" + specialProductPojo.getSpecialId());
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("添加失败！", "specialProductList.do?activityId=" + activityId
          + "&specialProductPojo.userBrandId=" + specialProductPojo.getUserBrandId()
          + "&specialProductPojo.specialId=" + specialProductPojo.getSpecialId());
    }
    return null;
  }

  public List<ProductPojo> getProductPojos() {
    return productPojos;
  }

  public void setProductPojos(List<ProductPojo> productPojos) {
    this.productPojos = productPojos;
  }

  public List<SpecialProductPojo> getSpecialProductPojos() {
    return specialProductPojos;
  }

  public void setSpecialProductPojos(List<SpecialProductPojo> specialProductPojos) {
    this.specialProductPojos = specialProductPojos;
  }

  public List<SpecialShowPojo> getSpecialShowPojos() {
    return specialShowPojos;
  }

  public void setSpecialShowPojos(List<SpecialShowPojo> specialShowPojos) {
    this.specialShowPojos = specialShowPojos;
  }
}
