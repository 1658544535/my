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
import com.tzmb2c.web.pojo.ProductPrimaryImagesPojo;
import com.tzmb2c.web.service.ProductPrimaryImagesService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.SysRoleService;

public class ProductPrimaryImagesAction extends SuperAction {

  @Autowired
  private ProductPrimaryImagesService productPrimaryImagesService;
  @Autowired
  private SysRoleService sysRoleService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private SysDictService sysDictService;

  private ProductPrimaryImagesPojo productPrimaryImages;
  private String result;
  private String[] tids;
  private File upfile;


  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public ProductPrimaryImagesPojo getProductPrimaryImages() {
    return productPrimaryImages;
  }

  public void setProductPrimaryImages(ProductPrimaryImagesPojo productPrimaryImages) {
    this.productPrimaryImages = productPrimaryImages;
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

  public String getProductPrimaryImagesCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(productPrimaryImagesService.productPrimaryImagesAllCount(productPrimaryImages));
    ActionContext ac = ActionContext.getContext();
    ac.put("productPrimaryImagesPojo", productPrimaryImages);
    return SUCCESS;
  }

  public String productPrimaryImagesAllList() {
    JSONArray json =
        JSONArray.fromObject(productPrimaryImagesService.productPrimaryImagesAllList(
            productPrimaryImages, page));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String productPrimaryImagesDeleteId() {
    if (tids != null) {
      productPrimaryImagesService.productPrimaryImagesDeleteId(tids);
      FileUtil.alertMessageBySkip(
          "删除成功！",
          "productPrimaryImages.do?productPrimaryImages.productId="
              + productPrimaryImages.getProductId() + "&productPrimaryImages.userId="
              + productPrimaryImages.getUserId());
    } else {
      FileUtil.alertMessageBySkip(
          "删除失败！",
          "productPrimaryImages.do?productPrimaryImages.productId="
              + productPrimaryImages.getProductId() + "&productPrimaryImages.userId="
              + productPrimaryImages.getUserId());
    }

    return null;
  }

  public String deleProductPrimaryImages() throws SQLException {
    try {
      productPrimaryImagesService.delProductPrimaryImages(productPrimaryImages.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String goFindProductPrimaryImages() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("userId", productPrimaryImages.getUserId());
    ac.put("productPrimaryImagesPojo",
        productPrimaryImagesService.findProductPrimaryImagesById(productPrimaryImages.getId()));
    ac.put("status", sysDictService.getSysDictListByType("status"));
    return SUCCESS;
  }

  public String updateProductPrimaryImages() throws Throwable {
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/productPrimaryImages")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/productPrimaryImages/", upfile);
      productPrimaryImages.setImages(file_name);
    }
    productPrimaryImagesService.updateProductPrimaryImages(productPrimaryImages);
    FileUtil.alertMessageBySkip("修改成功！", "productPrimaryImages.do?productPrimaryImages.productId="
        + productPrimaryImages.getProductId() + "&productPrimaryImages.userId="
        + productPrimaryImages.getUserId());

    return null;
  }

  public String addProductPrimaryImages() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("productPrimaryImagesPojo", productPrimaryImages);
    return SUCCESS;
  }

  public String insertProductPrimaryImages() throws Throwable {
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/productPrimaryImages")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/productPrimaryImages/", upfile);
      productPrimaryImages.setImages(file_name);
    }
    productPrimaryImagesService.insertProductPrimaryImages(productPrimaryImages);
    FileUtil.alertMessageBySkip("新增成功！", "productPrimaryImages.do?productPrimaryImages.productId="
        + productPrimaryImages.getProductId() + "&productPrimaryImages.userId="
        + productPrimaryImages.getUserId());

    return null;
  }

}
