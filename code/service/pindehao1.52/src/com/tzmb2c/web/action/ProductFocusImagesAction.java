package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ProductFocusImagesPojo;
import com.tzmb2c.web.service.ProductFocusImagesService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.SysRoleService;

public class ProductFocusImagesAction extends SuperAction {

  @Autowired
  private ProductFocusImagesService productFocusImagesService;
  @Autowired
  private SysRoleService sysRoleService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private SysDictService sysDictService;

  private ProductFocusImagesPojo productFocusImages, productFocusImages2, productFocusImages3,
      productFocusImages4, productFocusImages5;
  private String result;
  private String[] tids;
  private File upfile, upfile2, upfile3, upfile4, upfile5;

  public ProductFocusImagesPojo getProductFocusImages2() {
    return productFocusImages2;
  }

  public void setProductFocusImages2(ProductFocusImagesPojo productFocusImages2) {
    this.productFocusImages2 = productFocusImages2;
  }

  public ProductFocusImagesPojo getProductFocusImages3() {
    return productFocusImages3;
  }

  public void setProductFocusImages3(ProductFocusImagesPojo productFocusImages3) {
    this.productFocusImages3 = productFocusImages3;
  }

  public ProductFocusImagesPojo getProductFocusImages4() {
    return productFocusImages4;
  }

  public void setProductFocusImages4(ProductFocusImagesPojo productFocusImages4) {
    this.productFocusImages4 = productFocusImages4;
  }

  public ProductFocusImagesPojo getProductFocusImages5() {
    return productFocusImages5;
  }

  public void setProductFocusImages5(ProductFocusImagesPojo productFocusImages5) {
    this.productFocusImages5 = productFocusImages5;
  }

  public File getUpfile2() {
    return upfile2;
  }

  public void setUpfile2(File upfile2) {
    this.upfile2 = upfile2;
  }

  public File getUpfile3() {
    return upfile3;
  }

  public void setUpfile3(File upfile3) {
    this.upfile3 = upfile3;
  }

  public File getUpfile4() {
    return upfile4;
  }

  public void setUpfile4(File upfile4) {
    this.upfile4 = upfile4;
  }

  public File getUpfile5() {
    return upfile5;
  }

  public void setUpfile5(File upfile5) {
    this.upfile5 = upfile5;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public ProductFocusImagesPojo getProductFocusImages() {
    return productFocusImages;
  }

  public void setProductFocusImages(ProductFocusImagesPojo productFocusImages) {
    this.productFocusImages = productFocusImages;
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

  public String getProductFocusImagesCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(productFocusImagesService.productFocusImagesAllCount(productFocusImages));
    ActionContext ac = ActionContext.getContext();
    ac.put("productFocusImagesPojo", productFocusImages);
    return SUCCESS;
  }

  public String productFocusImagesAllList() {
    JSONArray json =
        JSONArray.fromObject(productFocusImagesService.productFocusImagesAllList(
            productFocusImages, page));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String productFocusImagesDeleteId() {
    if (tids != null) {
      productFocusImagesService.productFocusImagesDeleteId(tids);
      FileUtil.alertMessageBySkip("删除成功！",
          "productFocusImages.do?productFocusImages.productId=" + productFocusImages.getProductId()
              + "&productFocusImages.userId=" + productFocusImages.getUserId());
    } else {
      FileUtil.alertMessageBySkip("删除失败！",
          "productFocusImages.do?productFocusImages.productId=" + productFocusImages.getProductId()
              + "&productFocusImages.userId=" + productFocusImages.getUserId());
    }

    return null;
  }

  public String deleProductFocusImages() throws SQLException {
    try {
      productFocusImagesService.delProductFocusImages(productFocusImages.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String goFindProductFocusImages() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("userId", productFocusImages.getUserId());
    ac.put("productFocusImagesPojo",
        productFocusImagesService.findProductFocusImagesById(productFocusImages.getId()));
    ac.put("status", sysDictService.getSysDictListByType("status"));
    return SUCCESS;
  }

  public String updateProductFocusImages() throws Throwable {
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/", upfile);
      productFocusImages.setImages(file_name);
    }
    productFocusImagesService.updateProductFocusImages(productFocusImages);
    FileUtil.alertMessageBySkip("修改成功！",
        "productFocusImages.do?productFocusImages.productId=" + productFocusImages.getProductId()
            + "&productFocusImages.userId=" + productFocusImages.getUserId());

    return null;
  }

  public String addProductFocusImages() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("productFocusImagesPojo", productFocusImages);
    return SUCCESS;
  }

  public String insertProductFocusImages() throws Throwable {
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/", upfile);
      productFocusImages.setImages(file_name);
    }
    productFocusImagesService.insertProductFocusImages(productFocusImages);
    if (upfile2 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/", upfile2);
      productFocusImages2.setImages(file_name);
      productFocusImagesService.insertProductFocusImages(productFocusImages2);
    }
    if (upfile3 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/", upfile3);
      productFocusImages3.setImages(file_name);
      productFocusImagesService.insertProductFocusImages(productFocusImages3);
    }
    if (upfile4 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/", upfile4);
      productFocusImages4.setImages(file_name);
      productFocusImagesService.insertProductFocusImages(productFocusImages4);
    }
    if (upfile5 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/", upfile5);
      productFocusImages5.setImages(file_name);
      productFocusImagesService.insertProductFocusImages(productFocusImages5);
    }
    FileUtil.alertMessageBySkip("新增成功！",
        "productFocusImages.do?productFocusImages.productId=" + productFocusImages.getProductId()
            + "&productFocusImages.userId=" + productFocusImages.getUserId());

    return null;
  }
}
