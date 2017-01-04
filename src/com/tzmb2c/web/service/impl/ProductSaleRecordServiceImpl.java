package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.ProductSaleRecordDao;
import com.tzmb2c.web.pojo.ProductSaleRecordPojo;
import com.tzmb2c.web.service.ProductSaleRecordService;

public class ProductSaleRecordServiceImpl implements ProductSaleRecordService {

  @Autowired
  private ProductSaleRecordDao productSaleRecordDao;

  @Override
  public int productSaleRecordAllCount(ProductSaleRecordPojo productSaleRecordPojo) {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (productSaleRecordPojo != null) {
      map.put("orderNo", productSaleRecordPojo.getOrderNo());// 订单号
      map.put("payStatus", productSaleRecordPojo.getPayStatus());// 支付状态
      map.put("consignee", productSaleRecordPojo.getConsignee());// 收货人
      map.put("orderStatus", productSaleRecordPojo.getOrderStatus());// 订单状态
    }
    return productSaleRecordDao.productSaleRecordAllCount(map);
  }

  @Override
  public List<ProductSaleRecordPojo> productSaleRecordAllList(
      ProductSaleRecordPojo productSaleRecordPojo, Pager page) {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (productSaleRecordPojo != null) {
      map.put("orderNo", productSaleRecordPojo.getOrderNo());// 订单号
      map.put("payStatus", productSaleRecordPojo.getPayStatus());// 支付状态
      map.put("consignee", productSaleRecordPojo.getConsignee());// 收货人
      map.put("orderStatus", productSaleRecordPojo.getOrderStatus());// 订单状态
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<ProductSaleRecordPojo> list = productSaleRecordDao.productSaleRecordAllList(map);
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
  public void addProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo) throws SQLException {
    // TODO Auto-generated method stub
    productSaleRecordDao.addProductSaleRecord(productSaleRecordPojo);
  }

  @Override
  public void delProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo) throws SQLException {
    // TODO Auto-generated method stub
    productSaleRecordDao.delProductSaleRecord(productSaleRecordPojo);
  }

  @Override
  public void delAllProductSaleRecordById(String[] tids) {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        productSaleRecordDao.delAllProductSaleRecordById(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public ProductSaleRecordPojo findProductSaleRecordById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return productSaleRecordDao.findProductSaleRecordById(id);
  }

  @Override
  public void updateProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo)
      throws SQLException {
    // TODO Auto-generated method stub
    productSaleRecordDao.updateProductSaleRecord(productSaleRecordPojo);
  }

  @Override
  public void checkProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo)
      throws SQLException {
    // TODO Auto-generated method stub
    productSaleRecordDao.checkProductSaleRecord(productSaleRecordPojo);
  }

  @Override
  public void checkAllProductSaleRecordById(String[] tids) {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        productSaleRecordDao.checkAllProductSaleRecordById(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

}
