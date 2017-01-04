package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.DeliveryAddressPojo;

public interface DeliveryAddressMapper {

  public List<DeliveryAddressPojo> deliveryAddressAllList(Map<String, Object> map);

  public int deliveryAddressAllCount(Map<String, Object> map);

  public DeliveryAddressPojo findDeliveryAddressById(Long id);

  public void addDeliveryAddress(DeliveryAddressPojo deliveryAddressPojo) throws SQLException;

  public void delAllDeliveryAddressById(String id) throws SQLException;

  public void delDeliveryAddress(DeliveryAddressPojo deliveryAddressPojo) throws SQLException;

  public void updateDeliveryAddress(DeliveryAddressPojo deliveryAddressPojo);

  public void verifyDeliveryAddress(DeliveryAddressPojo deliveryAddressPojo) throws SQLException;

  public void checkAllDeliveryAddressById(String id) throws SQLException;

  public List<DeliveryAddressPojo> getAddressByUser(Long userId) throws SQLException;

  public void updateAddressToDefault(DeliveryAddressPojo deliveryAddressPojo);

  public void updateAddressToNotDefault(DeliveryAddressPojo deliveryAddressPojo);

  public DeliveryAddressPojo getAddress(Map<String, Object> map);

  public void delDeliveryAddressWeb(Long id);

  public void updateAddressToDefaultWeb(Long id);

  public void updateAddressToNotDefaultWeb(Long id);
}
