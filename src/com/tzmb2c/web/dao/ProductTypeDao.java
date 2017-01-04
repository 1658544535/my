package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductTypePojo;

/**
 * @author EricChen
 */
public interface ProductTypeDao {

  int getCount(ProductTypePojo productTypePojo);

  List<ProductTypePojo> getProductTypeByPid8(Long pid);

  List<ProductTypePojo> getProductTypeByPid(ProductTypePojo productTypePojo);

  ProductTypePojo findProductType(ProductTypePojo productTypePojo);

  List<ProductTypePojo> getProductTypeByPidName(ProductTypePojo productTypePojo);

  void addProductType(ProductTypePojo productType);

  void productTypeUpdate(ProductTypePojo productType);

  public void deleProductType(Long id) throws SQLException;

  void checkProductType(Long id) throws SQLException;

  void visableProductType(Long id) throws SQLException;

  void invisableProductType(Long id) throws SQLException;

  public List<ProductTypePojo> getProductTypeSecondLevel() throws SQLException;

  List<ProductTypePojo> getProductTypeByPids(ProductTypePojo productTypePojo) throws SQLException;

  List<ProductTypePojo> getProductTypeThree(Map<String, Object> map);

  int getCountProductTypeThree(Map<String, Object> map);
  
  public void checkAllProductTypeById(String id) throws SQLException;

  /**
   * ��ѯ������Ŀ
   * 
   * @return List<ProductTypePojo> ��������б�
   */
  public List<ProductTypePojo> findThridProductType() throws SQLException;
}
