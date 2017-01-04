package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductTypePojo;

/**
 * @author EricChen
 */
public interface ProductTypeService {

  public int getCount(ProductTypePojo productTypePojo);

  List<ProductTypePojo> getProductTypeByPid8(Long pid) throws SQLException;// 首页取产品类型的子类型 8个

  public List<ProductTypePojo> getProductTypeByPid(ProductTypePojo productTypePojo)
      throws SQLException;

  public List<ProductTypePojo> getProductTypeByPidName(ProductTypePojo productTypePojo);

  public ProductTypePojo findProductType(ProductTypePojo productTypePojo);

  public void addProductType(ProductTypePojo productType);

  public void productTypeUpdate(ProductTypePojo productType);

  public void deleProductType(Long id) throws SQLException;

  public void checkProductType(Long id) throws SQLException;

  void visableProductType(Long id) throws SQLException;

  void invisableProductType(Long id) throws SQLException;

  public List<ProductTypePojo> getProductTypeSecondLevel() throws SQLException;

  List<ProductTypePojo> getProductTypeByPids(ProductTypePojo productTypePojo) throws SQLException;

  public List<ProductTypePojo> getProductTypeThree(Map<String, Object> map) throws SQLException;

  public int getCountProductTypeThree(Map<String, Object> map);
  
  public void checkAllProductTypeById(String id) throws SQLException;

  /**
   * 查询第三级类目
   * 
   * @return List<ProductTypePojo> 第三级分类列表
   */
  public List<ProductTypePojo> findThridProductType() throws SQLException;

}
