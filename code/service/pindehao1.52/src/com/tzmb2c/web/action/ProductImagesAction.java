package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.CompressPicture;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ProductImagesPojo;
import com.tzmb2c.web.service.ProductImagesService;

/**
 * @author EricChen
 */
public class ProductImagesAction extends SuperAction {
  private static final long serialVersionUID = 1L;

  @Autowired
  private ProductImagesService productImagesService;
  private List<ProductImagesPojo> productImagesList;
  private ProductImagesPojo productImagesPojo, productImagesPojo2, productImagesPojo3,
      productImagesPojo4, productImagesPojo5, productImagesPojo6;
  private File upfile, upfile2, upfile3, upfile4, upfile5, upfile6;
  private String result;
  private String[] tids;

  public String getCountProductImages() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(productImagesService.getCount(productImagesPojo));
    ActionContext ac = ActionContext.getContext();
    ac.put("productImagesPojo2", productImagesPojo);
    return SUCCESS;
  }

  public String getProductImagesAll() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    productImagesList = productImagesService.getProductImagesAll(productImagesPojo, page);
    JSONArray json = JSONArray.fromObject(productImagesList);
    page.setResult(json.toString());
    return SUCCESS;

  }

  public String goproductImagesAdd() {
    ActionContext ac = ActionContext.getContext();
    ac.put("productImagesPojo", productImagesPojo);
    return SUCCESS;
  }

  public String productImagesAdd() throws Throwable {
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
      // 给图片加水印
      // String waterPic =
      // ServletActionContext.getServletContext().getRealPath("/images")+File.separator+"water_logo.png";
      // ImageUtils.pressImageganji(uploadPath+file_name, waterPic, 50, 50, 0.5f,false);
      // ImageUtils.pressImages(waterPic,uploadPath+file_name,4,0.5f);
      // 图片压缩
      CompressPicture cp = new CompressPicture();
      String compressPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
              + File.separator;
      cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
      productImagesPojo.setImages(file_name);
    } else {
      productImagesPojo.setImages("");
    }
    productImagesPojo.setStatus(1);
    productImagesService.addProductImages(productImagesPojo);
    if (upfile2 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile2);
      // 给图片加水印
      // String waterPic =
      // ServletActionContext.getServletContext().getRealPath("/images")+File.separator+"water_logo.png";
      // ImageUtils.pressImageganji(uploadPath+file_name, waterPic, 50, 50, 0.5f,false);
      // ImageUtils.pressImages(waterPic,uploadPath+file_name,4,0.5f);
      // 图片压缩
      CompressPicture cp = new CompressPicture();
      String compressPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
              + File.separator;
      cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
      productImagesPojo2.setImages(file_name);
    }
    productImagesPojo2.setStatus(1);
    productImagesService.addProductImages(productImagesPojo2);
    if (upfile3 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile3);
      // 给图片加水印
      // String waterPic =
      // ServletActionContext.getServletContext().getRealPath("/images")+File.separator+"water_logo.png";
      // ImageUtils.pressImageganji(uploadPath+file_name, waterPic, 50, 50, 0.5f,false);
      // ImageUtils.pressImages(waterPic,uploadPath+file_name,4,0.5f);
      // 图片压缩
      CompressPicture cp = new CompressPicture();
      String compressPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
              + File.separator;
      cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
      productImagesPojo3.setImages(file_name);
    }
    productImagesPojo3.setStatus(1);
    productImagesService.addProductImages(productImagesPojo3);
    if (upfile4 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile4);
      // 给图片加水印
      // String waterPic =
      // ServletActionContext.getServletContext().getRealPath("/images")+File.separator+"water_logo.png";
      // ImageUtils.pressImageganji(uploadPath+file_name, waterPic, 50, 50, 0.5f,false);
      // ImageUtils.pressImages(waterPic,uploadPath+file_name,4,0.5f);
      // 图片压缩
      CompressPicture cp = new CompressPicture();
      String compressPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
              + File.separator;
      cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
      productImagesPojo4.setImages(file_name);
    }
    productImagesPojo4.setStatus(1);
    productImagesService.addProductImages(productImagesPojo4);
    if (upfile5 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile5);
      // 给图片加水印
      // String waterPic =
      // ServletActionContext.getServletContext().getRealPath("/images")+File.separator+"water_logo.png";
      // ImageUtils.pressImageganji(uploadPath+file_name, waterPic, 50, 50, 0.5f,false);
      // ImageUtils.pressImages(waterPic,uploadPath+file_name,4,0.5f);
      // 图片压缩
      CompressPicture cp = new CompressPicture();
      String compressPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
              + File.separator;
      cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
      productImagesPojo5.setImages(file_name);
    }
    productImagesPojo5.setStatus(1);
    productImagesService.addProductImages(productImagesPojo5);
    if (upfile6 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile6);
      // 给图片加水印
      // String waterPic =
      // ServletActionContext.getServletContext().getRealPath("/images")+File.separator+"water_logo.png";
      // ImageUtils.pressImageganji(uploadPath+file_name, waterPic, 50, 50, 0.5f,false);
      // ImageUtils.pressImages(waterPic,uploadPath+file_name,4,0.5f);
      // 图片压缩
      CompressPicture cp = new CompressPicture();
      String compressPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
              + File.separator;
      cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
      productImagesPojo6.setImages(file_name);
    }
    productImagesPojo6.setStatus(1);
    productImagesService.addProductImages(productImagesPojo6);
    return SUCCESS;
  }

  public String goFindProductImages() {
    Long puserId = productImagesPojo.getUserId();
    productImagesPojo = productImagesService.findProductImages(productImagesPojo);
    productImagesPojo.setUserId(puserId);
    return SUCCESS;
  }

  public String productImagesUpdate() throws Throwable {
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
      // 给图片加水印
      // String waterPic =
      // ServletActionContext.getServletContext().getRealPath("/images")+File.separator+"water_logo.png";
      // ImageUtils.pressImageganji(uploadPath+file_name, waterPic, 50, 50, 0.5f,false);
      // ImageUtils.pressImages(waterPic,uploadPath+file_name,4,0.5f);

      // 图片压缩
      CompressPicture cp = new CompressPicture();
      String compressPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
              + File.separator;
      cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
      productImagesPojo.setImages(file_name);
    }
    productImagesService.productImagesUpdate(productImagesPojo);
    return SUCCESS;
  }

  public String deleProductImages() throws SQLException {
    try {
      productImagesService.deleProductImages(productImagesPojo.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String productImagesDeleteId() {
    StringBuffer url = new StringBuffer("productImagesManage.do");
    if (productImagesPojo != null && productImagesPojo.getProductId() != null) {
      url.append("?productImagesPojo.productId=" + productImagesPojo.getProductId()
          + "&productImagesPojo.userId=" + productImagesPojo.getUserId());
    }
    if (tids != null) {
      productImagesService.productImagesDeleteId(tids);
      FileUtil.alertMessageBySkip("删除成功！", url.toString());
    } else {
      FileUtil.alertMessageBySkip("删除失败！", url.toString());
    }

    return null;
  }

  public String productImagescheckAll() {
    StringBuffer url = new StringBuffer("productImagesManage.do");
    if (productImagesPojo != null && productImagesPojo.getProductId() != null) {
      url.append("?productImagesPojo.productId=" + productImagesPojo.getProductId()
          + "&productImagesPojo.userId=" + productImagesPojo.getUserId());
    }
    for (String tid : tids) {
      try {
        productImagesService.checkProductImages(Long.parseLong(tid));
        FileUtil.alertMessageBySkip("审核成功！", url.toString());
      } catch (SQLException e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("审核失败！", url.toString());
      }
    }

    return null;
  }

  public String checkProductImages() throws SQLException {
    try {
      productImagesService.checkProductImages(productImagesPojo.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  // 前端调用
  public String getCountProductImagesWeb() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setPageSize(5);
    page.setRowCount(productImagesService.getCount(productImagesPojo));
    return SUCCESS;
  }

  public String getProductImagesAllWeb() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setPageSize(5);
    productImagesList = productImagesService.getProductImagesAll(productImagesPojo, page);
    JSONArray json = JSONArray.fromObject(productImagesList);
    page.setResult(json.toString());
    return SUCCESS;

  }

  public String goproductImagesAddWeb() {
    return SUCCESS;
  }

  public String productImagesAddWeb() throws Throwable {
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
      // 给图片加水印
      /*
       * String waterPic = ServletActionContext.getServletContext().getRealPath("/images") +
       * File.separator + "water_logo.png"; ImageUtils.pressImageganji(uploadPath + file_name,
       * waterPic, 50, 50, 0.5f, false);
       */
      // 图片压缩
      CompressPicture cp = new CompressPicture();
      String compressPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
              + File.separator;
      cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
      productImagesPojo.setImages(file_name);
    } else {
      productImagesPojo.setImages("");
    }
    productImagesPojo.setStatus(0);
    productImagesService.addProductImages(productImagesPojo);
    return SUCCESS;
  }

  public String deleProductImagesWeb() throws SQLException {
    try {
      productImagesService.deleProductImages(productImagesPojo.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String productImagesDeleteIdWeb() {
    StringBuffer url = new StringBuffer("productImagesManageWeb.do");
    if (productImagesPojo != null && productImagesPojo.getProductId() != null) {
      url.append("?productImagesPojo.productId=" + productImagesPojo.getProductId());
    }
    if (tids != null) {
      productImagesService.productImagesDeleteId(tids);
      FileUtil.alertMessageBySkip("删除成功！", url.toString());
    } else {
      FileUtil.alertMessageBySkip("删除失败！", url.toString());
    }

    return null;
  }

  public List<ProductImagesPojo> getProductImagesList() {
    return productImagesList;
  }

  public void setProductImagesList(List<ProductImagesPojo> productImagesList) {
    this.productImagesList = productImagesList;
  }

  public ProductImagesPojo getProductImagesPojo() {
    return productImagesPojo;
  }

  public void setProductImagesPojo(ProductImagesPojo productImagesPojo) {
    this.productImagesPojo = productImagesPojo;
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

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public ProductImagesPojo getProductImagesPojo2() {
    return productImagesPojo2;
  }

  public void setProductImagesPojo2(ProductImagesPojo productImagesPojo2) {
    this.productImagesPojo2 = productImagesPojo2;
  }

  public ProductImagesPojo getProductImagesPojo3() {
    return productImagesPojo3;
  }

  public void setProductImagesPojo3(ProductImagesPojo productImagesPojo3) {
    this.productImagesPojo3 = productImagesPojo3;
  }

  public ProductImagesPojo getProductImagesPojo4() {
    return productImagesPojo4;
  }

  public void setProductImagesPojo4(ProductImagesPojo productImagesPojo4) {
    this.productImagesPojo4 = productImagesPojo4;
  }

  public ProductImagesPojo getProductImagesPojo5() {
    return productImagesPojo5;
  }

  public void setProductImagesPojo5(ProductImagesPojo productImagesPojo5) {
    this.productImagesPojo5 = productImagesPojo5;
  }

  public ProductImagesPojo getProductImagesPojo6() {
    return productImagesPojo6;
  }

  public void setProductImagesPojo6(ProductImagesPojo productImagesPojo6) {
    this.productImagesPojo6 = productImagesPojo6;
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

  public File getUpfile6() {
    return upfile6;
  }

  public void setUpfile6(File upfile6) {
    this.upfile6 = upfile6;
  }


}
