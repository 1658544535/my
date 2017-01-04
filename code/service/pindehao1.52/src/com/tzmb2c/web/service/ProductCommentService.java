package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.ProductCommentPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.UserBrandPojo;

public interface ProductCommentService {

  public List<ProductPojo> productNameAllList(Map<String, Object> map);

  public List<ProductCommentPojo> productCommentAllList(ProductCommentPojo productCommentPojo,
      Pager page, String proName);

  public int productCommentAllCount(ProductCommentPojo productCommentPojo);

  public void delComment(ProductCommentPojo productCommentPojo) throws SQLException;

  public void delAllCommentByIds(String[] ids) throws SQLException;

  List<ProductCommentPojo> productCommentAllListWeb(ProductCommentPojo productCommentPojo,
      Pager page);

  public ProductCommentPojo findProductCommentById(Long id);

  public void checkProductComment(Long id) throws SQLException;

  public void checkAllProductCommentByIds(String[] ids) throws SQLException;

  public void uncheckProductComment(Long id) throws SQLException;

  public void uncheckAllProductCommentByIds(String[] ids) throws SQLException;

  public List<ProductCommentPojo> productCommentAllListWe(ProductCommentPojo productCommentPojo,
      Pager page);

  public int productCommentAllCountWe(ProductCommentPojo productCommentPojo);

  public List<ProductCommentPojo> productCommentAllListHg(ProductCommentPojo productCommentPojo,
      Pager page, String beganday, String endday);

  public int productCommentAllCountHg(ProductCommentPojo productCommentPojo, String beganday,
      String endday);

  public void addUserComment(ProductCommentPojo productCommentPojo) throws SQLException;

  public List<ProductCommentPojo> userCommentAllListWeb(Map<String, Object> map);

  public int productCommentCountByUid(Map<String, Object> map) throws SQLException;

  public List<ProductCommentPojo> productCommentListByUid(String productName, Long userId,
      Pager page) throws SQLException;

  public UserBrandPojo returnproductCommtByBid(Long id) throws SQLException;


}
