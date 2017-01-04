package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.DeliveryAddressDao;
import com.tzmb2c.web.pojo.DeliveryAddressPojo;
import com.tzmb2c.web.service.DeliveryAddressService;

public class DeliveryAddressServiceImpl implements DeliveryAddressService {

  @Autowired
  private DeliveryAddressDao deliveryAddressDao;

  @Override
  public void addDeliveryAddress(DeliveryAddressPojo deliveryAddressPojo) throws SQLException {

    deliveryAddressDao.addDeliveryAddress(deliveryAddressPojo);
  }

  @Override
  public DeliveryAddressPojo findDeliveryAddressById(Long id) throws SQLException {

    return deliveryAddressDao.findDeliveryAddressById(id);
  }

  @Override
  public void updateDeliveryAddress(DeliveryAddressPojo deliveryAddressPojo) throws SQLException {

    deliveryAddressDao.updateDeliveryAddress(deliveryAddressPojo);
  }

  @Override
  public void delDeliveryAddress(DeliveryAddressPojo deliveryAddressPojo) throws SQLException {

    deliveryAddressDao.delDeliveryAddress(deliveryAddressPojo);
  }

  @Override
  public void delAllDeliveryAddressById(String[] tids) {

    for (String tid : tids) {
      try {
        deliveryAddressDao.delAllDeliveryAddressById(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public List<DeliveryAddressPojo> deliveryAddressAllList(DeliveryAddressPojo deliveryAddressPojo,
      Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (deliveryAddressPojo != null) {
      map.put("userId", deliveryAddressPojo.getUserId());// 用户编号
      map.put("userName", deliveryAddressPojo.getUserName());// 用户昵称
      map.put("consignee", deliveryAddressPojo.getConsignee());// 收货人
      map.put("address", deliveryAddressPojo.getAddress());// 收货地址
      map.put("isDefault", deliveryAddressPojo.getIsDefault());
      map.put("status", deliveryAddressPojo.getStatus());
    }
    // map.put("type", type);
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    List<DeliveryAddressPojo> list = deliveryAddressDao.deliveryAddressAllList(map);
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
  public int deliveryAddressAllCount(DeliveryAddressPojo deliveryAddressPojo) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (deliveryAddressPojo != null) {
      map.put("userId", deliveryAddressPojo.getUserId());// 用户编号
      map.put("userName", deliveryAddressPojo.getUserName());// 用户昵称
      map.put("consignee", deliveryAddressPojo.getConsignee());// 收货人
      map.put("address", deliveryAddressPojo.getAddress());
      map.put("status", deliveryAddressPojo.getStatus());
    }
    return deliveryAddressDao.deliveryAddressAllCount(map);
  }

  @Override
  public void verifyDeliveryAddress(DeliveryAddressPojo deliveryAddressPojo) throws SQLException {

    deliveryAddressDao.verifyDeliveryAddress(deliveryAddressPojo);
  }

  @Override
  public void checkAllDeliveryAddressById(String[] tids) {

    for (String tid : tids) {
      try {
        deliveryAddressDao.checkAllDeliveryAddressById(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public List<DeliveryAddressPojo> getAddressByUser(Long userId) throws SQLException {
    return deliveryAddressDao.getAddressByUser(userId);
  }

  @Override
  public void updateAddressToDefault(DeliveryAddressPojo deliveryAddressPojo) throws SQLException {

    deliveryAddressDao.updateAddressToDefault(deliveryAddressPojo);
  }

  @Override
  public void updateAddressToNotDefault(DeliveryAddressPojo deliveryAddressPojo)
      throws SQLException {

    deliveryAddressDao.updateAddressToNotDefault(deliveryAddressPojo);
  }

  @Override
  public DeliveryAddressPojo getAddress(DeliveryAddressPojo deliveryAddressPojo)
      throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (deliveryAddressPojo != null) {
      map.put("userId", deliveryAddressPojo.getUserId());// 用户编号
      map.put("id", deliveryAddressPojo.getId());// 用户昵称
    }
    return deliveryAddressDao.getAddress(map);
  }

  @Override
  public void delDeliveryAddressWeb(Long id) {
    deliveryAddressDao.delDeliveryAddressWeb(id);
  }

  @Override
  public void updateAddressToDefaultWeb(Long id) {
    deliveryAddressDao.updateAddressToDefaultWeb(id);
  }

  @Override
  public void updateAddressToNotDefaultWeb(Long id) {
    deliveryAddressDao.updateAddressToNotDefaultWeb(id);
  }
}
