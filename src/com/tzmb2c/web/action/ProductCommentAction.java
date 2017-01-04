package com.tzmb2c.web.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.dao.ProductCommentDao;
import com.tzmb2c.web.pojo.ProductCommentPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductSellPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserBrandPojo;
import com.tzmb2c.web.service.ProductCommentService;
import com.tzmb2c.web.service.ProductSellService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.UserBrandService;

/**
 * 产品评价管理Action 2014-12-15
 * 
 * @author creazylee
 */
public class ProductCommentAction extends SuperAction {

  private String[] tids;
  private String result;
  @Autowired
  private ProductCommentService productCommentService;
  @Autowired
  private ProductCommentDao productCommentDao;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SellerService sellerService;
  @Autowired
  private ProductService productService;
  @Autowired
  private UserBrandService userBrandService;
  @Autowired
  private ProductSellService productSellService;
  private ProductCommentPojo productCommentPojo;
  private List<ProductCommentPojo> productCommentList = null, productCommentListWeb;
  private List<SysDictPojo> statusDic = null;
  private List<ProductPojo> productNameList = null;
  private String beganday = null, endday = null;
  private String proName = null, name = null;

  public List<SysDictPojo> getStatusDic() {
    return statusDic;
  }

  public void setStatusDic(List<SysDictPojo> statusDic) {
    this.statusDic = statusDic;
  }

  public List<ProductCommentPojo> getProductCommentListWeb() {
    return productCommentListWeb;
  }

  public void setProductCommentListWeb(List<ProductCommentPojo> productCommentListWeb) {
    this.productCommentListWeb = productCommentListWeb;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getProName() {
    return proName;
  }

  public void setProName(String proName) {
    this.proName = proName;
  }

  public String getBeganday() {
    return beganday;
  }

  public void setBeganday(String beganday) {
    this.beganday = beganday;
  }

  public String getEndday() {
    return endday;
  }

  public void setEndday(String endday) {
    this.endday = endday;
  }

  public List<ProductPojo> getProductNameList() {
    return productNameList;
  }

  public void setProductNameList(List<ProductPojo> productNameList) {
    this.productNameList = productNameList;
  }

  public ProductCommentPojo getProductCommentPojo() {
    return productCommentPojo;
  }

  public void setProductCommentPojo(ProductCommentPojo productCommentPojo) {
    this.productCommentPojo = productCommentPojo;
  }

  public ProductCommentService getProductCommentService() {
    return productCommentService;
  }

  public void setProductCommentService(ProductCommentService productCommentService) {
    this.productCommentService = productCommentService;
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

  // get产品类型
  private void getProductName() {
    Map<String, Object> map = new HashMap<String, Object>();
    productNameList = productCommentService.productNameAllList(map);
  }

  public String ProductCommentAllList() {
    getProductName();
    productCommentList =
        productCommentService.productCommentAllList(productCommentPojo, page, proName);
    JSONArray json = JSONArray.fromObject(productCommentList);
    page.setResult(json.toString());
    return SUCCESS;

  }

  // Action入口
  public String getProductCommentCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    getProductName();
    statusDic = sysDictService.getSysDictListByType("status");
    page.setRowCount(productCommentService.productCommentAllCount(productCommentPojo));
    return SUCCESS;
  }

  public String ProductCommentAllListWeb() throws SQLException {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    getProductName();
    page.setPageSize(5);
    // productCommentPojo = new ProductCommentPojo();
    proName = name;
    // productCommentList = productCommentService.productCommentAllList(
    // productCommentPojo, page,proName);
    productCommentList =
        productCommentService.productCommentListByUid(proName, sysLogin.getId(), page);
    JSONArray json = JSONArray.fromObject(productCommentList);
    page.setResult(json.toString());
    return SUCCESS;

  }

  public String getProductCommentCountWeb() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (page == null) {
      page = new Pager();
    }
    page.setPageSize(5);
    getProductName();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("userId", sysLogin.getId());
    page.setRowCount(productCommentService.productCommentCountByUid(map));
    return SUCCESS;
  }

  public String delProductComment() throws SQLException {
    try {
      productCommentService.delComment(productCommentPojo);
      result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  public String checkProductComment() throws SQLException {
    Long id = 0L;
    if (productCommentPojo != null) {
      id = productCommentPojo.getId();
    }
    try {
      productCommentService.checkProductComment(id);

      // product添加product_commt
      ProductPojo userBrandId = null;
      String productCommt =
          sellerService.returnproductCommtByPid(productCommentPojo.getProductId());
      if (!"".equals(productCommt)) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setId(productCommentPojo.getProductId());
        productPojo.setProductCommt(productCommt);
        productService.productUpdate(productPojo);
        userBrandId = productService.findProduct(productPojo);
      }

      // user_brand添加product_commt,seller_commt/deliver_commt,logistics_commt
      if (userBrandId != null) {
        UserBrandPojo userBrand =
            sellerService.returnproductCommtByBid(userBrandId.getUserBrandId());
        if (userBrand != null) {
          UserBrandPojo userBrandPojo = new UserBrandPojo();
          userBrandPojo.setId(userBrandId.getUserBrandId());
          userBrandPojo.setProductCommt(userBrand.getProductCommt() + "");
          userBrandPojo.setDeliverCommt(userBrand.getDeliverCommt() + "");
          userBrandPojo.setLogisticsCommt(userBrand.getLogisticsCommt() + "");
          userBrandService.updateUserBrand(userBrandPojo);
        }
      }

      result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  public String uncheckProductComment() throws SQLException {
    Long id = 0L;
    if (productCommentPojo != null) {
      id = productCommentPojo.getId();
    }
    try {
      productCommentService.uncheckProductComment(id);

      // product添加product_commt
      ProductPojo userBrandId = null;
      String productCommt =
          sellerService.returnproductCommtByPid(productCommentPojo.getProductId());
      if (!"".equals(productCommt)) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setId(productCommentPojo.getProductId());
        productPojo.setProductCommt(productCommt);
        productService.productUpdate(productPojo);

        userBrandId = productService.findProduct(productPojo);
      }

      // user_brand添加product_commt,seller_commt/deliver_commt,logistics_commt
      if (userBrandId != null) {
        UserBrandPojo userBrand =
            sellerService.returnproductCommtByBid(userBrandId.getUserBrandId());
        if (userBrand != null) {
          UserBrandPojo userBrandPojo = new UserBrandPojo();
          userBrandPojo.setId(userBrandId.getUserBrandId());
          userBrandPojo.setProductCommt(userBrand.getProductCommt() + "");
          userBrandPojo.setDeliverCommt(userBrand.getDeliverCommt() + "");
          userBrandPojo.setLogisticsCommt(userBrand.getLogisticsCommt() + "");
          userBrandService.updateUserBrand(userBrandPojo);
        }
      }

      result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  public String delAllProductCommentByIds() throws SQLException {
    if (tids != null && tids.length > 0) {
      productCommentService.delAllCommentByIds(tids);
      FileUtil.alertMessageBySkip("删除成功！", "productCommentManage.do");
    } else {
      FileUtil.alertMessageBySkip("请先勾选！", "productCommentManage.do");
    }
    return null;
  }

  public String checkAllProductCommentByIds() throws SQLException {
    if (tids != null && tids.length > 0) {
      productCommentService.checkAllProductCommentByIds(tids);

      for (String tid : tids) {
        productCommentPojo = new ProductCommentPojo();
        productCommentPojo.setId(Long.parseLong(tid));
        productCommentPojo =
            productCommentService.findProductCommentById(productCommentPojo.getId());

        if (productCommentPojo != null) {
          // product添加product_commt
          ProductPojo userBrandId = null;
          String productCommt =
              sellerService.returnproductCommtByPid(productCommentPojo.getProductId());
          if (!"".equals(productCommt)) {
            ProductPojo productPojo = new ProductPojo();
            productPojo.setId(productCommentPojo.getProductId());
            productPojo.setProductCommt(productCommt);
            productService.productUpdate(productPojo);

            userBrandId = productService.findProduct(productPojo);
          }

          // user_brand添加product_commt,seller_commt/deliver_commt,logistics_commt
          if (userBrandId != null) {
            UserBrandPojo userBrand =
                sellerService.returnproductCommtByBid(userBrandId.getUserBrandId());
            if (userBrand != null) {
              UserBrandPojo userBrandPojo = new UserBrandPojo();
              userBrandPojo.setId(userBrandId.getUserBrandId());
              userBrandPojo.setProductCommt(userBrand.getProductCommt() + "");
              userBrandPojo.setDeliverCommt(userBrand.getDeliverCommt() + "");
              userBrandPojo.setLogisticsCommt(userBrand.getLogisticsCommt() + "");
              userBrandService.updateUserBrand(userBrandPojo);
            }
          }
        }
      }

      FileUtil.alertMessageBySkip("审核成功！", "productCommentManage.do");
    } else {
      FileUtil.alertMessageBySkip("请先勾选！", "productCommentManage.do");
    }
    return null;
  }

  // /////////////////////////////分割线////////////////////////////////////

  public String ProductCommentAllListWe() {
    productCommentList = productCommentService.productCommentAllListWe(productCommentPojo, page);
    JSONArray json = JSONArray.fromObject(productCommentList);
    page.setResult(json.toString());
    return SUCCESS;

  }

  public String getProductCommentCountWe() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(productCommentService.productCommentAllCountWe(productCommentPojo));
    return SUCCESS;
  }

  public String ProductCommentAllListHg() {
    productCommentList =
        productCommentService.productCommentAllListHg(productCommentPojo, page, beganday, endday);
    JSONArray json = JSONArray.fromObject(productCommentList);
    page.setResult(json.toString());
    return SUCCESS;

  }

  public String getProductCommentCountHg() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (productCommentPojo != null) {
      map.put("userName", productCommentPojo.getUserName());// 借用户昵称传值，用于产品搜索
      map.put("orderNo", productCommentPojo.getOrderNo());//
    }
    if (beganday != null && !beganday.equals("")) {
      map.put("beganday", beganday);
    }
    if (endday != null && !endday.equals("")) {
      map.put("endday", endday);
    }

    List<ProductCommentPojo> list = productCommentDao.productCommentAllListHg(map);
    page.setRowCount(list.size());
    return SUCCESS;
  }

  /**
   * 添加商品评价 前台
   * 
   * @return
   * @throws IOException
   */
  public String addUserComment() throws IOException {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    productCommentPojo.setUserId(sysLogin.getId());// 设置用户id
    productCommentPojo.setUserName(sysLogin.getName());// 设置用户昵称user_name
    productCommentPojo.setCreateDate(new Date());// 设置评论时间
    productCommentPojo.setCreateBy(sysLogin.getId());// 设置评论者
    Long orderID = productCommentPojo.getOrderId();
    try {
      productCommentService.addUserComment(productCommentPojo);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    // 添加成功,并修改订单的状态为已评论
    FileUtil.alertMessageBySkip("添加成功！",
        "updateOrderStatus.do?orderPojo.orderStatus=5&orderPojo.id=" + orderID);
    // FileUtil.alertMessageBySkip("添加成功！", "goUserCommentWeb.do?orderPojo.id="+orderID);
    return null;
  }

  /***
   * 根据用户ID查询所有该用户的商品评价
   * 
   * @return
   */
  public String userCommentAllListWeb() {
    Map<String, Object> map = new HashMap<String, Object>();
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    Long userId = sysLogin.getId();
    map.put("userId", userId);
    productCommentListWeb = productCommentService.userCommentAllListWeb(map);
    return SUCCESS;

  }

}
