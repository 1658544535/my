package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductCommentPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.UserBrandPojo;

public interface ProductCommentMapper {

  public List<ProductPojo> productNameAllList(Map<String, Object> map);

  public List<ProductCommentPojo> productCommentAllList(Map<String, Object> map);

  public int productCommentAllCount(Map<String, Object> map);

  public ProductCommentPojo findProductCommentById(Long id);

  public void delComment(ProductCommentPojo productCommentPojo) throws SQLException;

  public void delAllCommentByIds(Map<String, Object> map) throws SQLException;

  public void checkProductComment(Long id) throws SQLException;

  public void checkAllProductCommentByIds(Map<String, Object> map) throws SQLException;

  public void uncheckProductComment(Long id) throws SQLException;

  public void uncheckAllProductCommentByIds(Map<String, Object> map) throws SQLException;

  public List<ProductCommentPojo> productCommentAllListWe(Map<String, Object> map);

  public List<ProductCommentPojo> productCommentAllListWeb(Map<String, Object> map);

  public int productCommentAllCountWe(Map<String, Object> map);

  List<ProductCommentPojo> productCommentAllListHg(Map<String, Object> map);

  int productCommentAllCountHg(Map<String, Object> map);

  public void addUserComment(ProductCommentPojo productCommentPojo) throws SQLException;

  public List<ProductCommentPojo> userCommentAllListWeb(Map<String, Object> map);

  public int productCommentCountByUid(Map<String, Object> map) throws SQLException;

  public List<ProductCommentPojo> productCommentListByUid(Map<String, Object> map)
      throws SQLException;

  public UserBrandPojo returnproductCommtByBid(Long id) throws SQLException;


}
