package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.DeliveryAddressDao;
import com.tzmb2c.web.mapper.DeliveryAddressMapper;
import com.tzmb2c.web.pojo.DeliveryAddressPojo;

public class DeliveryAddressDaoImpl implements DeliveryAddressDao {

  @Autowired
  private DeliveryAddressMapper deliveryAddressMapper;

  @Override
  public void addDeliveryAddress(DeliveryAddressPojo deliveryAddressPojo) throws SQLException {
    // TODO Auto-generated method stub
    deliveryAddressMapper.addDeliveryAddress(deliveryAddressPojo);
  }

  @Override
  public DeliveryAddressPojo findDeliveryAddressById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return deliveryAddressMapper.findDeliveryAddressById(id);
  }

  @Override
  public void updateDeliveryAddress(DeliveryAddressPojo deliveryAddressPojo) throws SQLException {
    // TODO Auto-generated method stub
    deliveryAddressMapper.updateDeliveryAddress(deliveryAddressPojo);
  }

  @Override
  public void delDeliveryAddress(DeliveryAddressPojo deliveryAddressPojo) throws SQLException {
    // TODO Auto-generated method stub
    deliveryAddressMapper.delDeliveryAddress(deliveryAddressPojo);
  }

  @Override
  public void delAllDeliveryAddressById(String id) throws SQLException {
    // TODO Auto-generated method stub
    deliveryAddressMapper.delAllDeliveryAddressById(id);
  }

  @Override
  public List<DeliveryAddressPojo> deliveryAddressAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return deliveryAddressMapper.deliveryAddressAllList(map);
  }

  @Override
  public int deliveryAddressAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return deliveryAddressMapper.deliveryAddressAllCount(map);
  }

  @Override
  public void verifyDeliveryAddress(DeliveryAddressPojo deliveryAddressPojo) throws SQLException {
    // TODO Auto-generated method stub
    deliveryAddressMapper.verifyDeliveryAddress(deliveryAddressPojo);
  }

  @Override
  public void checkAllDeliveryAddressById(String id) throws SQLException {
    // TODO Auto-generated method stub
    deliveryAddressMapper.checkAllDeliveryAddressById(id);
  }

  @Override
  public List<DeliveryAddressPojo> getAddressByUser(Long userId) throws SQLException {
    return deliveryAddressMapper.getAddressByUser(userId);
  }

  @Override
  public void updateAddressToDefault(DeliveryAddressPojo deliveryAddressPojo) throws SQLException {
    // TODO Auto-generated method stub
    deliveryAddressMapper.updateAddressToDefault(deliveryAddressPojo);
  }

  @Override
  public void updateAddressToNotDefault(DeliveryAddressPojo deliveryAddressPojo)
      throws SQLException {
    // TODO Auto-generated method stub
    deliveryAddressMapper.updateAddressToNotDefault(deliveryAddressPojo);
  }

  @Override
  public DeliveryAddressPojo getAddress(Map<String, Object> map) {
    return deliveryAddressMapper.getAddress(map);
  }

  @Override
  public void delDeliveryAddressWeb(Long id) {
    deliveryAddressMapper.delDeliveryAddressWeb(id);
  }

  @Override
  public void updateAddressToDefaultWeb(Long id) {
    deliveryAddressMapper.updateAddressToDefaultWeb(id);
  }

  @Override
  public void updateAddressToNotDefaultWeb(Long id) {
    deliveryAddressMapper.updateAddressToNotDefaultWeb(id);
  }


}
