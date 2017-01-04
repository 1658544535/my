package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ProductCommentDao;
import com.tzmb2c.web.mapper.ProductCommentMapper;
import com.tzmb2c.web.pojo.ProductCommentPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.UserBrandPojo;

public class ProductCommentDaoImpl implements ProductCommentDao {

  @Autowired
  private ProductCommentMapper productCommentMapper;

  @Override
  public void delComment(ProductCommentPojo productCommentPojo) throws SQLException {
    productCommentMapper.delComment(productCommentPojo);
  }

  @Override
  public void delAllCommentByIds(Map<String, Object> map) throws SQLException {
    productCommentMapper.delAllCommentByIds(map);
  }

  @Override
  public List<ProductCommentPojo> productCommentAllList(Map<String, Object> map) {
    return productCommentMapper.productCommentAllList(map);
  }

  @Override
  public int productCommentAllCount(Map<String, Object> map) {
    return productCommentMapper.productCommentAllCount(map);
  }

  @Override
  public List<ProductPojo> productNameAllList(Map<String, Object> map) {
    return productCommentMapper.productNameAllList(map);
  }

  @Override
  public List<ProductCommentPojo> productCommentAllListWe(Map<String, Object> map) {
    return productCommentMapper.productCommentAllListWe(map);
  }

  @Override
  public int productCommentAllCountWe(Map<String, Object> map) {
    return productCommentMapper.productCommentAllCountWe(map);
  }

  @Override
  public List<ProductCommentPojo> productCommentAllListHg(Map<String, Object> map) {
    return productCommentMapper.productCommentAllListHg(map);
  }

  @Override
  public int productCommentAllCountHg(Map<String, Object> map) {
    return productCommentMapper.productCommentAllCountHg(map);
  }

  @Override
  public ProductCommentPojo findProductCommentById(Long id) {
    return productCommentMapper.findProductCommentById(id);
  }

  @Override
  public void checkProductComment(Long id) throws SQLException {

    productCommentMapper.checkProductComment(id);
  }

  @Override
  public void checkAllProductCommentByIds(Map<String, Object> map) throws SQLException {

    productCommentMapper.checkAllProductCommentByIds(map);
  }

  @Override
  public void uncheckProductComment(Long id) throws SQLException {

    productCommentMapper.uncheckProductComment(id);
  }

  @Override
  public void uncheckAllProductCommentByIds(Map<String, Object> map) throws SQLException {

    productCommentMapper.uncheckAllProductCommentByIds(map);
  }

  @Override
  public void addUserComment(ProductCommentPojo productCommentPojo) throws SQLException {

    productCommentMapper.addUserComment(productCommentPojo);
  }

  @Override
  public List<ProductCommentPojo> productCommentAllListWeb(Map<String, Object> map) {

    return productCommentMapper.productCommentAllListWeb(map);
  }

  @Override
  public List<ProductCommentPojo> userCommentAllListWeb(Map<String, Object> map) {

    return productCommentMapper.userCommentAllListWeb(map);
  }

  @Override
  public int productCommentCountByUid(Map<String, Object> map) throws SQLException {
    return productCommentMapper.productCommentCountByUid(map);
  }

  @Override
  public List<ProductCommentPojo> productCommentListByUid(Map<String, Object> map)
      throws SQLException {
    return productCommentMapper.productCommentListByUid(map);
  }

  @Override
  public UserBrandPojo returnproductCommtByBid(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return productCommentMapper.returnproductCommtByBid(id);
  }
}
