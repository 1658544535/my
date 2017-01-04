package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.List;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.ProductStockPojo;
import com.tzmb2c.web.service.ProductStockService;

/**
 * @author EricChen
 */
public class ProductStockAction extends SuperAction {
  private static final long serialVersionUID = 1L;

  @Autowired
  private ProductStockService productStockService;
  private List<ProductStockPojo> productStockList;
  private ProductStockPojo productStockPojo;
  private String result;
  private String[] tids;

  public String getCountProductStock() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(productStockService.getCount(productStockPojo));
    return SUCCESS;
  }

  public String getProductStockAll() throws Exception {
    productStockList = productStockService.getProductStockAll(productStockPojo, page);
    JSONArray json = JSONArray.fromObject(productStockList);
    page.setResult(json.toString());
    return SUCCESS;

  }

  public String goproductStockAdd() {
    return SUCCESS;
  }

  public String productStockAdd() throws Exception {
    productStockPojo.setStatus(0);
    productStockService.addProductStock(productStockPojo);
    return SUCCESS;
  }

  public String goFindProductStock() {
    productStockPojo = productStockService.findProductStock(productStockPojo);
    return SUCCESS;
  }

  public String productStockUpdate() throws Exception {
    productStockService.productStockUpdate(productStockPojo);
    return SUCCESS;
  }

  public String deleProductStock() throws SQLException {
    try {
      productStockService.deleProductStock(productStockPojo.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String productStockDeleteId() {
    if (tids != null) {
      productStockService.productStockDeleteId(tids);
      FileUtil.alertMessageBySkip("删除成功！", "productStockManage.do?productStockPojo.productId="
          + productStockPojo.getProductId());
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "productStockManage.do?productStockPojo.productId="
          + productStockPojo.getProductId());
    }

    return null;
  }

  public String checkProductStock() throws SQLException {
    try {
      productStockService.checkProductStock(productStockPojo.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public List<ProductStockPojo> getProductStockList() {
    return productStockList;
  }

  public void setProductStockList(List<ProductStockPojo> productStockList) {
    this.productStockList = productStockList;
  }

  public ProductStockPojo getProductStockPojo() {
    return productStockPojo;
  }

  public void setProductStockPojo(ProductStockPojo productStockPojo) {
    this.productStockPojo = productStockPojo;
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
}
