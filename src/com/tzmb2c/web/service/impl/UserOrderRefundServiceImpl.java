package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.UserOrderRefundDao;
import com.tzmb2c.web.pojo.UserOrderRefundPojo;
import com.tzmb2c.web.service.UserOrderRefundService;

public class UserOrderRefundServiceImpl implements UserOrderRefundService {

  @Autowired
  private UserOrderRefundDao userOrderRefundDao;

  @Override
  public int userOrderRefundAllCount(UserOrderRefundPojo userOrderRefundPojo, String refundStatus) {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (userOrderRefundPojo != null) {
      map.put("orderId", userOrderRefundPojo.getOrderId());// 订单ID
      map.put("orderNo", userOrderRefundPojo.getOrderNo());// 订单号
      map.put("loginname1", userOrderRefundPojo.getLoginname1());// 用户名
      map.put("name", userOrderRefundPojo.getName());// 用户昵称
      map.put("productName", userOrderRefundPojo.getProductName());// 产品名称
      map.put("createDateStartStr", userOrderRefundPojo.getCreateDateStartStr());
      map.put("createDateEndStr", userOrderRefundPojo.getCreateDateEndStr());
      map.put("auditStatus", userOrderRefundPojo.getAuditStatus());
      map.put("productNum", userOrderRefundPojo.getProductNum());
    }
    map.put("refundStatus", refundStatus);// 退货状态
    return userOrderRefundDao.userOrderRefundAllCount(map);
  }

  @Override
  public List<UserOrderRefundPojo> userOrderRefundAllList(UserOrderRefundPojo userOrderRefundPojo,
      Pager page, String refundStatus) {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (userOrderRefundPojo != null) {
      map.put("orderId", userOrderRefundPojo.getOrderId());// 订单ID
      map.put("orderNo", userOrderRefundPojo.getOrderNo());// 订单号
      map.put("loginname1", userOrderRefundPojo.getLoginname1());// 用户名
      map.put("name", userOrderRefundPojo.getName());// 用户昵称
      map.put("productName", userOrderRefundPojo.getProductName());// 产品名称
      map.put("createDateStartStr", userOrderRefundPojo.getCreateDateStartStr());
      map.put("createDateEndStr", userOrderRefundPojo.getCreateDateEndStr());
      map.put("auditStatus", userOrderRefundPojo.getAuditStatus());
      map.put("productNum", userOrderRefundPojo.getProductNum());

    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    map.put("refundStatus", refundStatus);// 退货状态
    List<UserOrderRefundPojo> list = userOrderRefundDao.userOrderRefundAllList(map);
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
  public void addUserOrderRefund(UserOrderRefundPojo userOrderRefundPojo) throws SQLException {
    // TODO Auto-generated method stub
    userOrderRefundDao.addUserOrderRefund(userOrderRefundPojo);
  }

  @Override
  public void delUserOrderRefund(UserOrderRefundPojo userOrderRefundPojo) throws SQLException {
    // TODO Auto-generated method stub
    userOrderRefundDao.delUserOrderRefund(userOrderRefundPojo);
  }

  @Override
  public void delAllUserOrderRefundById(String[] tids) {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        userOrderRefundDao.delAllUserOrderRefundById(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public UserOrderRefundPojo findUserOrderRefundById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return userOrderRefundDao.findUserOrderRefundById(id);
  }

  @Override
  public UserOrderRefundPojo findUserOrderRefundByOid(Long oid) throws SQLException {
    // TODO Auto-generated method stub
    return userOrderRefundDao.findUserOrderRefundByOid(oid);
  }


  @Override
  public void updateUserOrderRefund(UserOrderRefundPojo userOrderRefundPojo) throws SQLException {
    // TODO Auto-generated method stub
    userOrderRefundDao.updateUserOrderRefund(userOrderRefundPojo);
  }

  @Override
  public void checkUserOrderRefund(UserOrderRefundPojo userOrderRefundPojo) throws SQLException {
    // TODO Auto-generated method stub
    userOrderRefundDao.checkUserOrderRefund(userOrderRefundPojo);
  }

  @Override
  public void checkAllUserOrderRefundById(String[] tids, UserOrderRefundPojo userOrderRefundPojo) {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        userOrderRefundPojo.setId(Long.parseLong(tid));
        userOrderRefundDao.checkAllUserOrderRefundById(userOrderRefundPojo);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void checkOrder(UserOrderRefundPojo userOrderRefundPojo) throws SQLException {
    // TODO Auto-generated method stub
    userOrderRefundDao.checkOrder(userOrderRefundPojo);
  }

  @Override
  public List<UserOrderRefundPojo> findUserOrderRefundByUserId(
      UserOrderRefundPojo userOrderRefundPojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (userOrderRefundPojo != null) {
      map.put("userId", userOrderRefundPojo.getUserId());
      map.put("shopId", userOrderRefundPojo.getShopId());
      map.put("status", userOrderRefundPojo.getStatus());
      map.put("refundStatus", userOrderRefundPojo.getRefundStatus());
      map.put("orderNo", userOrderRefundPojo.getOrderNo());
      // map.put("serviceInvolved", userOrderRefundPojo.getServiceInvolved());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return userOrderRefundDao.findUserOrderRefundByUserId(map);

  }

  @Override
  public List<UserOrderRefundPojo> findUserOrderRefundByorderId(
      UserOrderRefundPojo userOrderRefundPojo) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (userOrderRefundPojo != null) {
      map.put("orderId", userOrderRefundPojo.getOrderId());
    }
    return userOrderRefundDao.findUserOrderRefundByorderId(map);

  }

  @Override
  public List<UserOrderRefundPojo> findOrderRefundDetailByUserId(
      UserOrderRefundPojo userOrderRefundPojo, Pager page) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (userOrderRefundPojo != null) {
      map.put("userId", userOrderRefundPojo.getUserId());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return userOrderRefundDao.findOrderRefundDetailByUserId(map);
  }

  @Override
  public void updateOrderRefundRemarks(UserOrderRefundPojo userOrderRefundPojo) throws SQLException {
    userOrderRefundDao.updateOrderRefundRemarks(userOrderRefundPojo);
  }

  @Override
  public int userOrderRefundAllCount2(Map<String, Object> map) throws SQLException {
    return userOrderRefundDao.userOrderRefundAllCount2(map);
  }

  @Override
  public List<UserOrderRefundPojo> findUserOrderRefundListByorderId(
      UserOrderRefundPojo userOrderRefundPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (userOrderRefundPojo != null) {
      map.put("orderId", userOrderRefundPojo.getOrderId());
      map.put("status", userOrderRefundPojo.getStatus());
      map.put("serviceInvolved", userOrderRefundPojo.getServiceInvolved());
    }
    return userOrderRefundDao.findUserOrderRefundListByorderId(map);
  }

  @Override
  public List<UserOrderRefundPojo> findUserOrderRefundByUserId2(
      UserOrderRefundPojo userOrderRefundPojo, Pager page) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (userOrderRefundPojo != null) {
      map.put("userId", userOrderRefundPojo.getUserId());
      map.put("status", userOrderRefundPojo.getStatus());
      map.put("reStatus", userOrderRefundPojo.getReStatus());
      map.put("productNum", userOrderRefundPojo.getProductNum());
      map.put("consigneePhone", userOrderRefundPojo.getConsigneePhone());
      map.put("id", userOrderRefundPojo.getId());
      map.put("type", userOrderRefundPojo.getType());
      map.put("orderNo", userOrderRefundPojo.getOrderNo());
      map.put("serviceInvolved", userOrderRefundPojo.getServiceInvolved());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return userOrderRefundDao.findUserOrderRefundByUserId2(map);
  }


  @Override
  public void updateStatusOfUserOrderRefundById(UserOrderRefundPojo userOrderRefundPojo) {
    userOrderRefundDao.updateStatusOfUserOrderRefundById(userOrderRefundPojo);

  }

  @Override
  public UserOrderRefundPojo getByOutRefundNo(String outRefundNo) throws SQLException {
    // TODO Auto-generated method stub
    return userOrderRefundDao.getByOutRefundNo(outRefundNo);
  }
}
