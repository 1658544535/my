package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.ProductCommentDao;
import com.tzmb2c.web.pojo.ProductCommentPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.UserBrandPojo;
import com.tzmb2c.web.service.ProductCommentService;

public class ProductCommentServiceImpl implements ProductCommentService {

  @Autowired
  private ProductCommentDao productCommentDao;

  @Override
  public void delComment(ProductCommentPojo productCommentPojo) throws SQLException {

    productCommentDao.delComment(productCommentPojo);
  }

  @Override
  public List<ProductCommentPojo> productCommentAllList(ProductCommentPojo productCommentPojo,
      Pager page, String proName) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (productCommentPojo != null) {
      map.put("userId", productCommentPojo.getUserId());// 用户ID
      map.put("userName", productCommentPojo.getUserName());// 用户昵称
      map.put("productId", productCommentPojo.getProductId());// 产品类型
      map.put("productName", productCommentPojo.getProductName());// 产品名称
      map.put("status", productCommentPojo.getStatus());// 评论状态
      map.put("orderNo", productCommentPojo.getOrderNo());// 订单号
      map.put("beginTimeStr", productCommentPojo.getBeginTimeStr());// 评价开始时间
      map.put("endTimeStr", productCommentPojo.getEndTimeStr());// 评价结束时间
    }
    map.put("proName", proName);// page产品名称
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<ProductCommentPojo> list = productCommentDao.productCommentAllList(map);
    if (list != null) {
      // 判断信息是否被审核
      // for(PagePushPojo no:list){
      // BusinessDictPojo sysDictPojo = new BusinessDictPojo();
      // sysDictPojo.setBusinessDictId(m.getNoticeType());
      // BusinessDictPojo
      // syddsffDictPojo=businessDictDao.sysDictById(sysDictPojo);
      // m.setNoticeType(syddsffDictPojo.getBusinessDictName());
      // if(m.getNoticeState().equals("0")){
      // no.setNoticeState("未审核");
      // }else{
      // no.setNoticeState("已审核");
      // }
      // }
    }
    return list;
  }

  @Override
  public int productCommentAllCount(ProductCommentPojo productCommentPojo) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (productCommentPojo != null) {
      map.put("userId", productCommentPojo.getUserId());// 用户ID
      map.put("userName", productCommentPojo.getUserName());// 用户昵称
      map.put("productId", productCommentPojo.getProductId());// 产品类型
      map.put("productName", productCommentPojo.getProductName());// 产品名称
      map.put("status", productCommentPojo.getStatus());// 评论状态
      map.put("orderNo", productCommentPojo.getOrderNo());// 订单号
      map.put("beginTimeStr", productCommentPojo.getBeginTimeStr());// 评价开始时间
      map.put("endTimeStr", productCommentPojo.getEndTimeStr());// 评价结束时间
    }
    return productCommentDao.productCommentAllCount(map);
  }

  @Override
  public void delAllCommentByIds(String[] ids) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("tids", ids);
    productCommentDao.delAllCommentByIds(map);

  }

  @Override
  public List<ProductPojo> productNameAllList(Map<String, Object> map) {

    List<ProductPojo> list = productCommentDao.productNameAllList(map);
    return list;

  }

  // //////////////////////////////////分割线////////////////////////////////////
  @Override
  public int productCommentAllCountWe(ProductCommentPojo productCommentPojo) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (productCommentPojo != null) {
      map.put("userName", productCommentPojo.getUserName());// 用户昵称
      map.put("orderNo", productCommentPojo.getOrderNo());//
      map.put("productId", productCommentPojo.getProductId());// 产品类型
    }
    return productCommentDao.productCommentAllCountWe(map);
  }

  @Override
  public List<ProductCommentPojo> productCommentAllListWe(ProductCommentPojo productCommentPojo,
      Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (productCommentPojo != null) {
      map.put("userName", productCommentPojo.getUserName());// 用户昵称
      map.put("orderNo", productCommentPojo.getOrderNo());//
      map.put("productId", productCommentPojo.getProductId());// 产品类型
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<ProductCommentPojo> list = productCommentDao.productCommentAllListWe(map);
    return list;
  }

  @Override
  public List<ProductCommentPojo> productCommentAllListWeb(ProductCommentPojo productCommentPojo,
      Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (productCommentPojo != null) {
      map.put("userName", productCommentPojo.getUserName());// 用户昵称
      map.put("orderNo", productCommentPojo.getOrderNo());//
      map.put("productId", productCommentPojo.getProductId());// 产品类型
      map.put("status", productCommentPojo.getStatus());// 评价状态
      map.put("userId", productCommentPojo.getUserId());// 用户ID
      map.put("score", productCommentPojo.getScore());// 评价状态
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<ProductCommentPojo> list = productCommentDao.productCommentAllListWeb(map);
    return list;
  }

  @Override
  public int productCommentAllCountHg(ProductCommentPojo productCommentPojo, String beganday,
      String endday) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (productCommentPojo != null) {
      map.put("userName", productCommentPojo.getUserName());// 用户昵称
      map.put("orderNo", productCommentPojo.getOrderNo());//
    }
    if (beganday != null && !beganday.equals("")) {
      map.put("beganday", beganday);
    }
    if (endday != null && !endday.equals("")) {
      map.put("endday", endday);
    }
    return productCommentDao.productCommentAllCountHg(map);
  }

  @Override
  public List<ProductCommentPojo> productCommentAllListHg(ProductCommentPojo productCommentPojo,
      Pager page, String beganday, String endday) {

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
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<ProductCommentPojo> list = productCommentDao.productCommentAllListHg(map);
    return list;
  }

  @Override
  public ProductCommentPojo findProductCommentById(Long id) {

    return productCommentDao.findProductCommentById(id);
  }

  @Override
  public void checkProductComment(Long id) throws SQLException {

    productCommentDao.checkProductComment(id);
  }

  @Override
  public void checkAllProductCommentByIds(String[] ids) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("tids", ids);
    productCommentDao.checkAllProductCommentByIds(map);
  }

  @Override
  public void uncheckProductComment(Long id) throws SQLException {

    productCommentDao.uncheckProductComment(id);
  }

  @Override
  public void uncheckAllProductCommentByIds(String[] ids) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("tids", ids);
    productCommentDao.uncheckAllProductCommentByIds(map);
  }

  @Override
  public void addUserComment(ProductCommentPojo productCommentPojo) throws SQLException {
    this.productCommentDao.addUserComment(productCommentPojo);
  }

  @Override
  public List<ProductCommentPojo> userCommentAllListWeb(Map<String, Object> map) {
    List<ProductCommentPojo> list = productCommentDao.userCommentAllListWeb(map);
    return list;
  }

  @Override
  public int productCommentCountByUid(Map<String, Object> map) throws SQLException {
    return productCommentDao.productCommentCountByUid(map);
  }

  @Override
  public List<ProductCommentPojo> productCommentListByUid(String productName, Long userId,
      Pager page) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("userId", userId);
    if (productName != null && !productName.equals("")) {
      map.put("productName", productName);
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return productCommentDao.productCommentListByUid(map);
  }

  @Override
  public UserBrandPojo returnproductCommtByBid(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return productCommentDao.returnproductCommtByBid(id);
  }
}
