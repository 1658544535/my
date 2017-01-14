package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.ProductExcelPojo;
import com.tzmb2c.web.pojo.ProductPojo;

/**
 * @author EricChen
 */
public interface ProductService {
  public int getAllCount(ProductPojo productPojo);

  public int getCount(ProductPojo product, Pager page);

  public List<ProductPojo> getProductAll(ProductPojo product, Pager page) throws SQLException;

  public List<ProductPojo> getProductAllSeller(ProductPojo product, Pager page) throws SQLException;

  public List<ProductExcelPojo> getProductAll2(ProductExcelPojo product, Pager page)
      throws SQLException;

  public List<ProductPojo> getAgencyProductAll(ProductPojo product, Pager page) throws SQLException;

  public int getProductStock(ProductPojo productPojo) throws SQLException;

  public List<ProductPojo> getProductAllIds(ProductPojo product) throws SQLException;

  public ProductPojo findProduct(ProductPojo product);

  public ProductPojo findProductSeller(ProductPojo product);

  public Long addProduct(ProductPojo product);

  public Long addProductSeller(ProductPojo product);

  void productUpdateIng(ProductPojo product);

  public void productUpdate(ProductPojo product);

  public void productUpdateSeller(ProductPojo product);

  public ProductPojo findProductContent(Long id) throws SQLException;

  public int productContentUpdate(ProductPojo productPojo) throws SQLException;

  public ProductPojo findProductName(Long id) throws SQLException;

  public int productNameUpdate(ProductPojo productPojo) throws SQLException;

  public int productStatusUpdate(ProductPojo productPojo) throws SQLException;

  public void deleProduct(Long id) throws SQLException;

  public void productDeleteId(String[] tids);

  public void checkProduct(Long id) throws SQLException;

  List<ProductPojo> productForUserNew(Long id);

  List<ProductPojo> productForUserYes(Long id);

  List<ProductPojo> productForShopId(Long id);

  List<ProductPojo> productForShopId1(Map<String, Object> map);

  List<ProductPojo> productForHot(ProductPojo product);

  List<ProductPojo> productAll() throws SQLException;

  List<ProductPojo> productForHotSupplyWeb(ProductPojo product);

  List<ProductPojo> productForType(Long id);

  public List<ProductPojo> getProductAllByParameter(ProductPojo product, Pager page)
      throws SQLException;

  List<ProductPojo> productSellCountSupplyWeb(ProductPojo product);

  /**
   * 根据限制数获取产品列表（by EricChen）
   */
  List<ProductPojo> getProductLimit(ProductPojo productPojo, Integer limit);

  List<ProductPojo> productForUser(ProductPojo product, Pager page) throws SQLException;

  List<ProductPojo> productForUserYes5(Long id);

  void updateHits(Long id);

  void uncheckProduct(Long id) throws SQLException;

  List<ProductPojo> getProductAllByName(ProductPojo product, Pager page) throws SQLException;

  public List<ProductPojo> getProductAllStatus(ProductPojo product, Pager page) throws SQLException;

  public int getCountStatus(ProductPojo productPojo);

  List<ProductPojo> getProductByBrandName(String productBrand, Pager page);

  void updateProductStatus(Long id) throws SQLException;

  List<ProductPojo> productByUserIdSort(ProductPojo product, Pager page);

  void updateProductsellNumber(Map<String, Object> map) throws SQLException;

  List<ProductPojo> getProductApi(ProductPojo product, Pager page);

  List<ProductPojo> getProductByActivity(ProductPojo product, Pager page);

  int getProductByActivityCount(Map<String, Object> map);

  List<ProductPojo> indexProductTopFive();

  void indexProductTopFiveUpdate(ProductPojo product);

  void indexProductTopFiveUpdateNew(ProductPojo product);

  List<ProductPojo> indexShowByFloor(String productTypeIds);

  void indexProductFloorUpdateNew(ProductPojo product);

  int indexProductListsCount(String productTypeIds) throws SQLException;

  List<ProductPojo> indexProductLists(String productTypeIds, Pager page);

  /**
   * 每日10件
   * 
   * @return
   * @throws SQLException
   */
  List<ProductPojo> tenOfEveryDay() throws SQLException;

  List<ProductPojo> productCollectAdd(Map<String, Object> map);

  public void updateProductBaseNumber() throws SQLException;

  public Integer imagesCount(Long id) throws SQLException;

  public Integer focusImageCount(Long id) throws SQLException;

  public int getAgencyProductAllCount(ProductPojo product) throws SQLException;

  /**
   * 查询商品所属专场是否为排期完成
   * 
   * @return
   */
  public int findSpecialProductByPid(long productId) throws SQLException;

  /**
   * 商品库存
   */
  public List<ProductPojo> findProductStockList(Long id) throws SQLException;

  public int findProductStockCount(Long id) throws SQLException;

  public int productStockUpdate(String stock, String stockSkuId) throws SQLException;

  public ProductPojo getById(Long id) throws SQLException;
}
