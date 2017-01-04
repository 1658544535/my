package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductTypePojo;

/**
 * @author EricChen
 */
public interface ProductTypeMapper {



  public List<ProductTypePojo> getProductTypeSecondLevel();

  int getCount(ProductTypePojo productTypePojo);

  List<ProductTypePojo> getProductTypeByPid8(Long pid);

  List<ProductTypePojo> getProductTypeByPid(ProductTypePojo productTypePojo);

  List<ProductTypePojo> getProductTypeByPidName(ProductTypePojo productTypePojo);

  ProductTypePojo findProductType(ProductTypePojo productTypePojo);

  public void addProductType(ProductTypePojo productType);

  public void productTypeUpdate(ProductTypePojo productType);

  public void deleProductType(Long id) throws SQLException;

  void checkProductType(Long id) throws SQLException;

  void visableProductType(Long id) throws SQLException;

  void invisableProductType(Long id) throws SQLException;

  public List<ProductTypePojo> getProductTypeByPids(ProductTypePojo productTypePojo)
      throws SQLException;

  public List<ProductTypePojo> getProductTypeThree(Map<String, Object> map);

  public int getCountProductTypeThree(Map<String, Object> map);
  
  public void checkAllProductTypeById(String id) throws SQLException;

  /**
   * ��ѯ������Ŀ
   * 
   * @return List<ProductTypePojo> ��������б�
   */
  List<ProductTypePojo> findThridProductType();

}
