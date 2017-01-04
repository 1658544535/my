package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductExcelPojo;
import com.tzmb2c.web.pojo.ProductPojo;

/**
 * @author EricChen
 */
public interface ProductMapper {

  int getCount(Map<String, Object> map);

  List<ProductPojo> getProductAll(Map<String, Object> map);

  List<ProductExcelPojo> getProductAll2(Map<String, Object> map);

  List<ProductPojo> getAgencyProductAll(Map<String, Object> map);

  public int getProductStock(ProductPojo productPojo) throws SQLException;

  List<ProductPojo> getProductAllIds(Map<String, Object> map);

  ProductPojo findProduct(ProductPojo product);

  public ProductPojo findProductContent(Long id) throws SQLException;

  public int productContentUpdate(ProductPojo productPojo) throws SQLException;

  public ProductPojo findProductName(Long id) throws SQLException;

  public int productNameUpdate(ProductPojo productPojo) throws SQLException;

  public int productStatusUpdate(ProductPojo productPojo) throws SQLException;

  public Long addProduct(ProductPojo product);

  public void productUpdate(ProductPojo product);

  public void updateProductBaseNumber() throws SQLException;

  public void productUpdateIng(ProductPojo product);// 设置优先值更新 by wen

  public void deleProduct(Long id) throws SQLException;

  void checkProduct(Long id) throws SQLException;

  void uncheckProduct(Long id) throws SQLException;

  List<ProductPojo> productForUser(Map<String, Object> map);

  List<ProductPojo> productForUserYes(Long id);

  List<ProductPojo> productForUserNew(Long id);

  List<ProductPojo> productForUserYes5(Long id);

  List<ProductPojo> productForType(Long id);

  public List<ProductPojo> productAll() throws SQLException;

  List<ProductPojo> productForShopId(Long id);

  List<ProductPojo> productForHot(ProductPojo product);

  List<ProductPojo> productForHotSupplyWeb(ProductPojo product);

  List<ProductPojo> getProductAllByParameter(Map<String, Object> map);

  List<ProductPojo> productSellCountSupplyWeb(ProductPojo product);

  List<ProductPojo> getProductLimit(Map<String, Object> map);

  void updateHits(Long id);

  List<ProductPojo> getProductAllByName(Map<String, Object> map);

  List<ProductPojo> getProductAllStatus(Map<String, Object> map);

  void updateProductStatus(Long userId);

  int getCountStatus(Map<String, Object> map);

  List<ProductPojo> getProductByBrandName(Map<String, Object> map);

  List<ProductPojo> productByUserIdSort(Map<String, Object> map);

  List<ProductPojo> getProductApi(Map<String, Object> map);

  void updateProductsellNumber(Map<String, Object> map);

  List<ProductPojo> getProductByActivity(Map<String, Object> map);

  int getProductByActivityCount(Map<String, Object> map);

  List<ProductPojo> indexProductTopFive();

  void indexProductTopFiveUpdateOld(ProductPojo product);

  void indexProductTopFiveUpdateNew(ProductPojo product);

  List<ProductPojo> indexShowByFloor(String productTypeIds);

  void indexProductFloorUpdateNew(ProductPojo product);

  int getProductCount(Map<String, Object> map);

  int indexProductListsCount(Map<String, Object> map) throws SQLException;

  List<ProductPojo> indexProductLists(Map<String, Object> map);

  List<ProductPojo> productForShopId1(Map<String, Object> map);

  /**
   * 
   * 每日10件
   * 
   * @return
   */
  List<ProductPojo> tenOfEveryDay();

  List<ProductPojo> productCollectAdd(Map<String, Object> map);

  public Integer imagesCount(Long id) throws SQLException;

  public Integer focusImageCount(Long id) throws SQLException;

  public int getAgencyProductAllCount(Map<String, Object> map) throws SQLException;

  public int findSpecialProductByPid(long productId);


  public ProductPojo findActivityIdByPlatformSpecialProductId(Map<String, Object> map);


  /**
   * 商品库存
   */
  public List<ProductPojo> findProductStockList(Long id) throws SQLException;

  public int findProductStockCount(Long id) throws SQLException;

  public int productStockUpdate(Map<String, Object> map) throws SQLException;

  public ProductPojo getById(Long id) throws SQLException;
}
