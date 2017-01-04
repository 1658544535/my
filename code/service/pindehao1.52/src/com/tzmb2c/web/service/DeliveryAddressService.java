package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.DeliveryAddressPojo;

public interface DeliveryAddressService {

  public List<DeliveryAddressPojo> deliveryAddressAllList(DeliveryAddressPojo deliveryAddressPojo,
      Pager page);

  public int deliveryAddressAllCount(DeliveryAddressPojo deliveryAddressPojo);

  public DeliveryAddressPojo findDeliveryAddressById(Long id) throws SQLException;

  public DeliveryAddressPojo getAddress(DeliveryAddressPojo deliveryAddressPojo)
      throws SQLException;

  public void addDeliveryAddress(DeliveryAddressPojo deliveryAddressPojo) throws SQLException;

  public void delDeliveryAddress(DeliveryAddressPojo deliveryAddressPojo) throws SQLException;

  public void delAllDeliveryAddressById(String[] tids);

  public void updateDeliveryAddress(DeliveryAddressPojo deliveryAddressPojo) throws SQLException;

  public void updateAddressToDefault(DeliveryAddressPojo deliveryAddressPojo) throws SQLException;

  public void updateAddressToNotDefault(DeliveryAddressPojo deliveryAddressPojo)
      throws SQLException;

  public void verifyDeliveryAddress(DeliveryAddressPojo deliveryAddressPojo) throws SQLException;

  public void checkAllDeliveryAddressById(String[] tids);

  public List<DeliveryAddressPojo> getAddressByUser(Long userId) throws SQLException;

  public void delDeliveryAddressWeb(Long id);

  public void updateAddressToDefaultWeb(Long id);

  public void updateAddressToNotDefaultWeb(Long id);
}
