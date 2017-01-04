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
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.SysDictService;

/**
 * @author EricChen
 */
public class ProductTypeAction extends SuperAction {
  private static final long serialVersionUID = 1L;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private ProductTypeService productTypeService;
  private String msg;
  private Long pid;
  private List<ProductTypePojo> productTypeList, productTypePojos;
  private ProductTypePojo productTypePojo;
  private String result;
  private String[] tids;
  private String productTypes;
  private File upfile2;
  private Integer level;// 级别
  private String panduan;
  private String categoryId;
  private String typeName;
  private File upfile;
  private String upfileFileName;

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getPanduan() {
    return panduan;
  }

  public void setPanduan(String panduan) {
    this.panduan = panduan;
  }

  public File getUpfile2() {
    return upfile2;
  }

  public void setUpfile2(File upfile2) {
    this.upfile2 = upfile2;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Long getPid() {
    return pid;
  }

  public void setPid(Long pid) {
    this.pid = pid;
  }

  public List<ProductTypePojo> getProductTypeList() {
    return productTypeList;
  }

  public void setProductTypeList(List<ProductTypePojo> productTypeList) {
    this.productTypeList = productTypeList;
  }

  public ProductTypePojo getProductTypePojo() {
    return productTypePojo;
  }

  public void setProductTypePojo(ProductTypePojo productTypePojo) {
    this.productTypePojo = productTypePojo;
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

  public void setProductTypes(String productTypes) {
    this.productTypes = productTypes;
  }

  public String getProductTypes() {
    return productTypes;
  }

  public String getCountAllProductType() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    productTypePojo = new ProductTypePojo();
    if (pid == null) {
      productTypePojo.setPid((long) -1);
    } else {
      productTypePojo.setPid(pid);
    }

    page.setRowCount(productTypeService.getCount(productTypePojo));
    return SUCCESS;
  }

  public String getAllProductTypeByPid() throws Exception {
    productTypePojo = new ProductTypePojo();
    if (pid == null) {
      productTypePojo.setPid(-1l);
    } else {
      productTypePojo.setPid(pid);
    }
    // productTypeList = productTypeService.getProductTypeByPid(productTypePojo);
    JSONArray json = JSONArray.fromObject(productTypeService.getProductTypeByPid(productTypePojo));
    if (page == null) {
      page = new Pager();
    }
    page.setResult(json.toString());
    // ActionContext ac = ActionContext.getContext();
    // ac.put("pid",pid);
    return SUCCESS;

  }

  public String getCountProductType() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    productTypePojo = new ProductTypePojo();
    if (pid == null) {
      productTypePojo.setPid(0l);
    } else {
      if (level != null && level != 0) {
        productTypePojo.setTopLevel(pid.toString());
      } else {
        productTypePojo.setPid(pid);
      }
    }

    page.setRowCount(productTypeService.getCount(productTypePojo));
    return SUCCESS;
  }

  public String getProductTypeByPid() throws Exception {

    productTypePojo = new ProductTypePojo();
    if (pid == null) {
      productTypePojo.setPid(0l);
    } else {
      if (level != null && level != 0) {
        productTypePojo.setTopLevel(pid.toString());
      } else {
        productTypePojo.setPid(pid);
      }
    }
    // productTypeList = productTypeService.getProductTypeByPid(productTypePojo);
    JSONArray json = JSONArray.fromObject(productTypeService.getProductTypeByPid(productTypePojo));
    if (page == null) {
      page = new Pager();
    }

    page.setResult(json.toString());
    // ActionContext ac = ActionContext.getContext();
    // ac.put("pid",pid);
    return SUCCESS;

  }

  public String goproductTypeAdd() {
    return SUCCESS;
  }

  public String productTypeAdd() throws Exception {
    /*
     * if(productTypePojo.getId()==null) productTypePojo.setId(0l);
     */
    // 图片上传
    if (upfile != null) {
      String file_name = getUpfileFileName();
      String image =
          FileUtil.uploadFile(upfile, 0, file_name, "upfiles/productType", false, 300, 300, true,
              true, "");
      productTypePojo.setImage(file_name);
      if (image != null) {
        productTypePojo.setImage(image);
      }
    }
    pid = productTypePojo.getPid();
    level = productTypePojo.getLevel();
    if (productTypePojo.getTopLevel() != null && productTypePojo.getTopLevel() != "") {
      productTypePojo.setPid(0l);
    }
    // productTypePojo.setStatus(0);
    productTypePojo.setIsRed("0");

    if (upfile2 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/productType")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/productType/", upfile2);
      productTypePojo.setImage(file_name);
    }
    productTypePojo.setVisable(1);
    productTypeService.addProductType(productTypePojo);
    if (pid != -1) {
      FileUtil.skipHistoryAction();
      return null;
    }
    return SUCCESS;
  }

  public String goFindProductType() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("isRed", sysDictService.getSysDictListByType("yes_no"));
    ac.put("status", sysDictService.getSysDictListByType("status"));
    productTypePojo = productTypeService.findProductType(productTypePojo);
    return SUCCESS;
  }

  public String productTypeUpdate() throws Throwable {
    if (upfile2 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/productType")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/productType/", upfile2);
      productTypePojo.setImage(file_name);
    }
    productTypeService.productTypeUpdate(productTypePojo);
    pid = productTypePojo.getPid();
    level = productTypePojo.getLevel();
    return SUCCESS;
  }

  public String goFindProductType1() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("isRed", sysDictService.getSysDictListByType("yes_no"));
    ac.put("status", sysDictService.getSysDictListByType("status"));
    productTypePojo = productTypeService.findProductType(productTypePojo);
    return SUCCESS;
  }

  public String productTypeUpdate1() throws Throwable {
    if (upfile2 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/productType")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/productType/", upfile2);
      productTypePojo.setImage(file_name);
    }
    productTypeService.productTypeUpdate(productTypePojo);
    // pid = productTypePojo.getPid();
    // level = productTypePojo.getLevel();
    return SUCCESS;
  }

  public String deleProductType() throws SQLException {
    try {
      productTypeService.deleProductType(productTypePojo.getId());
      // 删除子类
      if (productTypePojo.getId() != null) {
        ProductTypePojo productType = new ProductTypePojo();
        productType.setPid(productTypePojo.getId());
        productTypeList = productTypeService.getProductTypeByPid(productType);
        for (int i = 0; i < productTypeList.size(); i++) {
          productTypeService.deleProductType(productTypeList.get(i).getId());
        }
      }

      result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }


  public String checkProductType() throws SQLException {
    try {
      productTypeService.checkProductType(productTypePojo.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  /**
   * 根据id批量审核
   * 
   * @return
   */
  public String checkAllProductTypeById() {
    try {
      for (String id : tids) {
        productTypeService.checkAllProductTypeById(id);
      }
      result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  /**
   * 根据id批量删除
   * 
   * @return
   */
  public String delAllProductTypeById() {
    try {
      for (String id : tids) {
        productTypeService.deleProductType(Long.parseLong(id));
      }
      result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  public String visableProductType() throws SQLException {
    try {
      productTypeService.visableProductType(productTypePojo.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  public String invisableProductType() throws SQLException {
    try {
      productTypeService.invisableProductType(productTypePojo.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  public String getAllProductTypes() throws SQLException {
    List<ProductTypePojo> types = productTypeService.getProductTypeByPid(productTypePojo);
    JSONArray json = JSONArray.fromObject(types);
    productTypes = json.toString();
    return SUCCESS;
  }

  // ajax 返回 type 产品类型名称
  public String typeName() {
    System.out.println(productTypes);
    ProductTypePojo productTypePojo = new ProductTypePojo();
    productTypePojo.setId(Long.parseLong(productTypes));
    productTypePojo = productTypeService.findProductType(productTypePojo);
    result = productTypePojo.getName();
    return SUCCESS;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }


  /***
   * 获取三级类目
   * 
   * @return
   */
  public String getProductTypeThree() {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (productTypePojo != null) {
      map.put("pid", productTypePojo.getId());
    }
    JSONArray json;
    try {
      json = JSONArray.fromObject(productTypeService.getProductTypeThree(map));
      page.setResult(json.toString());
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return SUCCESS;

  }

  /*
   * 获取三级类目
   */
  public String getCountProductTypeThree() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("panduan", panduan);
    ac.put("categoryId", categoryId);
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (productTypePojo != null) {
      map.put("pid", productTypePojo.getId());
    } else {
      productTypePojo = new ProductTypePojo();
    }
    productTypePojo.setPid(-1l);
    productTypePojo.setStatus(1);
    productTypePojo.setVisable(1);
    productTypeList = productTypeService.getProductTypeByPid(productTypePojo);
    page.setRowCount(productTypeService.getCountProductTypeThree(map));
    return SUCCESS;
  }

  /*
   * 获取二级类目
   */
  public String getProductTypeBySecond() throws Exception {
    productTypePojo.setPid(0L);
    productTypePojo.setStatus(1);
    productTypePojo.setVisable(1);
    productTypePojo.setTopLevel(String.valueOf(productTypePojo.getId()));
    JSONArray json =
        JSONArray.fromObject(productTypePojos =
            productTypeService.getProductTypeByPid(productTypePojo));
    if (page == null) {
      page = new Pager();
    }
    page.setResult(json.toString());
    return SUCCESS;
  }

  public List<ProductTypePojo> getProductTypePojos() {
    return productTypePojos;
  }

  public void setProductTypePojos(List<ProductTypePojo> productTypePojos) {
    this.productTypePojos = productTypePojos;
  }

  public String getUpfileFileName() {
    return upfileFileName;
  }

  public void setUpfileFileName(String upfileFileName) {
    this.upfileFileName = upfileFileName;
  }



}
