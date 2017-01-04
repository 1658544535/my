package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.UserOrderDetailDao;
import com.tzmb2c.web.pojo.UserOrderDetailPojo;
import com.tzmb2c.web.service.UserOrderDetailService;

public class UserOrderDetailServiceImpl implements UserOrderDetailService {

  @Autowired
  private UserOrderDetailDao userOrderDetailDao;

  @Override
  public int userOrderDetailAllCount(UserOrderDetailPojo userOrderDetailPojo) {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (userOrderDetailPojo != null) {
      map.put("loginName", userOrderDetailPojo.getLoginName());// 用户名称
      map.put("productName", userOrderDetailPojo.getProductName());// 产品名称
      map.put("shopName", userOrderDetailPojo.getShopName());//
      map.put("productNum", userOrderDetailPojo.getProductNum());//
      map.put("beganday", userOrderDetailPojo.getBeganday());
      map.put("endday", userOrderDetailPojo.getEndday());
      map.put("notuserIds", userOrderDetailPojo.getNotuserIds());
    }
    return userOrderDetailDao.userOrderDetailAllCount(map);
  }

  @Override
  public List<UserOrderDetailPojo> userOrderDetailAllList(UserOrderDetailPojo userOrderDetailPojo,
      Pager page) {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (userOrderDetailPojo != null) {
      map.put("loginName", userOrderDetailPojo.getLoginName());// 用户名称
      map.put("productName", userOrderDetailPojo.getProductName());// 产品名称
      map.put("shopName", userOrderDetailPojo.getShopName());//
      map.put("productNum", userOrderDetailPojo.getProductNum());//
      map.put("beganday", userOrderDetailPojo.getBeganday());
      map.put("endday", userOrderDetailPojo.getEndday());
      map.put("notuserIds", userOrderDetailPojo.getNotuserIds());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    List<UserOrderDetailPojo> list = userOrderDetailDao.userOrderDetailAllList(map);
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
  public void addUserOrderDetail(UserOrderDetailPojo userOrderDetailPojo) throws SQLException {
    // TODO Auto-generated method stub
    userOrderDetailDao.addUserOrderDetail(userOrderDetailPojo);
  }

  @Override
  public void delUserOrderDetail(UserOrderDetailPojo userOrderDetailPojo) throws SQLException {
    // TODO Auto-generated method stub
    userOrderDetailDao.delUserOrderDetail(userOrderDetailPojo);
  }

  @Override
  public void delAllUserOrderDetailById(String[] tids) {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        userOrderDetailDao.delAllUserOrderDetailById(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public UserOrderDetailPojo findUserOrderDetailById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return userOrderDetailDao.findUserOrderDetailById(id);
  }

  @Override
  public void updateUserOrderDetail(UserOrderDetailPojo userOrderDetailPojo) throws SQLException {
    // TODO Auto-generated method stub
    userOrderDetailDao.updateUserOrderDetail(userOrderDetailPojo);
  }

  @Override
  public void checkUserOrderDetail(UserOrderDetailPojo userOrderDetailPojo) throws SQLException {
    // TODO Auto-generated method stub
    userOrderDetailDao.checkUserOrderDetail(userOrderDetailPojo);
  }

  @Override
  public void checkAllUserOrderDetailById(String[] tids) {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        userOrderDetailDao.checkAllUserOrderDetailById(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public int productSaleCountAllCount(UserOrderDetailPojo userOrderDetailPojo) {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (userOrderDetailPojo != null) {
      map.put("loginName", userOrderDetailPojo.getLoginName());// 用户名称
      map.put("productName", userOrderDetailPojo.getProductName());// 产品名称
    }
    return userOrderDetailDao.productSaleCountAllCount(map);
  }

  @Override
  public List<UserOrderDetailPojo> productSaleCountAllList(UserOrderDetailPojo userOrderDetailPojo,
      Pager page) {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (userOrderDetailPojo != null) {
      map.put("loginName", userOrderDetailPojo.getLoginName());// 用户名称
      map.put("productName", userOrderDetailPojo.getProductName());// 产品名称
      map.put("product_num", userOrderDetailPojo.getProduct_num());// 产品货号
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    List<UserOrderDetailPojo> list = userOrderDetailDao.productSaleCountAllList(map);
    if (list != null) {
      // 判断信息是否被审核
    }
    return list;
  }

  @Override
  public int shopSaleListCount(UserOrderDetailPojo orderDetailPojo, String beganday, String endday) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (orderDetailPojo != null) {
      map.put("shopId", orderDetailPojo.getShopId());
      // map.put("loginName", orderDetailPojo.getLoginName());// 用户名称
      // map.put("productName", orderDetailPojo.getProductName());// 产品名称
    }
    if (beganday != null) {
      map.put("beganday", beganday);
    }
    if (endday != null) {
      map.put("endday", endday);
    }
    return userOrderDetailDao.shopSaleListCount(map);
  }

  @Override
  public List<UserOrderDetailPojo> shopSaleList(UserOrderDetailPojo userOrderDetailPojo,
      Pager page, String beganday, String endday) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (userOrderDetailPojo != null) {
      map.put("shopId", userOrderDetailPojo.getShopId());
      // map.put("shopName", userOrderDetailPojo.getShopName());
    }
    if (beganday != null) {
      map.put("beganday", beganday);
    }
    if (endday != null) {
      map.put("endday", endday);
    }
    // if(orderDaoPojo!=null){
    // map.put("orderNo", orderDaoPojo.getOrderNo());
    // map.put("orderStatus", orderDaoPojo.getOrderStatus());
    // map.put("consignee", orderDaoPojo.getConsignee());
    // map.put("payStatus", orderDaoPojo.getPayStatus());
    // }
    // if(os!=null&!os.equals(""))
    // map.put("orderStatus", Integer.parseInt(os));

    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return userOrderDetailDao.shopSaleList(map);
  }

  @Override
  public int sellSumNum(Long productId) throws SQLException {
    return userOrderDetailDao.sellSumNum(productId);
  }

}
