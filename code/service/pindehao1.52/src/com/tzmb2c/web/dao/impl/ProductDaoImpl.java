package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ProductDao;
import com.tzmb2c.web.mapper.ProductMapper;
import com.tzmb2c.web.pojo.ProductExcelPojo;
import com.tzmb2c.web.pojo.ProductPojo;

/**
 * @author EricChen
 */
public class ProductDaoImpl implements ProductDao {

  @Autowired
  private ProductMapper productMapper;

  @Override
  public List<ProductPojo> productAll() throws SQLException {
    return productMapper.productAll();
  }

  @Override
  public int getCount(Map<String, Object> map) {
    return productMapper.getCount(map);
  }

  @Override
  public List<ProductPojo> getProductAll(Map<String, Object> map) {
    return productMapper.getProductAll(map);
  }

  @Override
  public List<ProductPojo> getAgencyProductAll(Map<String, Object> map) {
    return productMapper.getAgencyProductAll(map);
  }

  @Override
  public List<ProductPojo> getProductAllIds(Map<String, Object> map) {
    return productMapper.getProductAllIds(map);
  }

  @Override
  public List<ProductPojo> getProductAllByName(Map<String, Object> map) {
    return productMapper.getProductAllByName(map);
  }

  @Override
  public ProductPojo findProduct(ProductPojo product) {
    return productMapper.findProduct(product);
  }

  @Override
  public Long addProduct(ProductPojo product) {
    return productMapper.addProduct(product);
  }

  @Override
  public void productUpdate(ProductPojo product) {
    productMapper.productUpdate(product);
  }

  @Override
  public void productUpdateIng(ProductPojo product) {
    productMapper.productUpdateIng(product);
  }

  @Override
  public void deleProduct(Long id) throws SQLException {
    productMapper.deleProduct(id);
  }

  @Override
  public void checkProduct(Long id) throws SQLException {

    productMapper.checkProduct(id);
  }

  @Override
  public List<ProductPojo> productForUser(Map<String, Object> map) {
    return productMapper.productForUser(map);
  }

  @Override
  public List<ProductPojo> productForUserNew(Long id) {
    return productMapper.productForUserNew(id);
  }

  @Override
  public List<ProductPojo> productForUserYes(Long id) {
    return productMapper.productForUserYes(id);
  }

  @Override
  public List<ProductPojo> productForUserYes5(Long id) {
    return productMapper.productForUserYes5(id);
  }

  @Override
  public List<ProductPojo> productForType(Long id) {
    return productMapper.productForType(id);
  }

  @Override
  public List<ProductPojo> productForShopId(Long id) {
    return productMapper.productForShopId(id);
  }

  @Override
  public List<ProductPojo> productForHot(ProductPojo product) {
    return productMapper.productForHot(product);
  }

  @Override
  public List<ProductPojo> productForHotSupplyWeb(ProductPojo product) {
    return productMapper.productForHotSupplyWeb(product);
  }

  @Override
  public List<ProductPojo> productSellCountSupplyWeb(ProductPojo product) {

    return productMapper.productSellCountSupplyWeb(product);
  }

  @Override
  public List<ProductPojo> getProductAllByParameter(Map<String, Object> map) {
    return productMapper.getProductAllByParameter(map);
  }

  @Override
  public List<ProductPojo> getProductLimit(Map<String, Object> map) {
    return productMapper.getProductLimit(map);
  }

  @Override
  public void updateHits(Long id) {
    productMapper.updateHits(id);
  }

  @Override
  public void uncheckProduct(Long id) throws SQLException {
    productMapper.uncheckProduct(id);

  }

  @Override
  public List<ProductPojo> getProductAllStatus(Map<String, Object> map) {
    return productMapper.getProductAllStatus(map);
  }

  @Override
  public void updateProductStatus(Long userId) {
    productMapper.updateProductStatus(userId);

  }

  @Override
  public int getCountStatus(Map<String, Object> map) {
    return productMapper.getCountStatus(map);
  }

  @Override
  public List<ProductPojo> getProductByBrandName(Map<String, Object> map) {

    return productMapper.getProductByBrandName(map);
  }

  @Override
  public List<ProductPojo> productByUserIdSort(Map<String, Object> map) {

    return productMapper.productByUserIdSort(map);
  }


  @Override
  public List<ProductPojo> getProductApi(Map<String, Object> map) {

    return productMapper.getProductApi(map);
  }

  @Override
  public void updateProductsellNumber(Map<String, Object> map) {

    productMapper.updateProductsellNumber(map);
  }

  @Override
  public List<ProductPojo> getProductByActivity(Map<String, Object> map) {

    return productMapper.getProductByActivity(map);
  }

  @Override
  public List<ProductPojo> indexProductTopFive() {

    return productMapper.indexProductTopFive();
  }

  @Override
  public void indexProductTopFiveUpdate(ProductPojo product) {

    productMapper.indexProductTopFiveUpdateOld(product);
  }

  @Override
  public void indexProductTopFiveUpdateNew(ProductPojo product) {

    productMapper.indexProductTopFiveUpdateNew(product);
  }

  @Override
  public List<ProductPojo> indexShowByFloor(String productTypeIds) {

    return productMapper.indexShowByFloor(productTypeIds);
  }

  @Override
  public void indexProductFloorUpdateNew(ProductPojo product) {

    productMapper.indexProductFloorUpdateNew(product);
  }

  @Override
  public int indexProductListsCount(Map<String, Object> map) throws SQLException {

    return productMapper.indexProductListsCount(map);
  }

  @Override
  public List<ProductPojo> indexProductLists(Map<String, Object> map) {

    return productMapper.indexProductLists(map);
  }

  @Override
  public List<ProductPojo> productForShopId1(Map<String, Object> map) {

    return productMapper.productForShopId1(map);
  }


  /**
   * 每日10件
   */
  @Override
  public List<ProductPojo> tenOfEveryDay() {

    return productMapper.tenOfEveryDay();
  }

  @Override
  public List<ProductPojo> productCollectAdd(Map<String, Object> map) {
    return productMapper.productCollectAdd(map);
  }

  @Override
  public void updateProductBaseNumber() throws SQLException {
    productMapper.updateProductBaseNumber();
  }

  @Override
  public Integer imagesCount(Long id) throws SQLException {
    return productMapper.imagesCount(id);
  }

  @Override
  public Integer focusImageCount(Long id) throws SQLException {
    return productMapper.focusImageCount(id);
  }

  @Override
  public int getAgencyProductAllCount(Map<String, Object> map) throws SQLException {

    return productMapper.getAgencyProductAllCount(map);
  }

  @Override
  public int findSpecialProductByPid(long productId) throws SQLException {
    return productMapper.findSpecialProductByPid(productId);
  }

  @Override
  public int getProductByActivityCount(Map<String, Object> map) {
    return productMapper.getProductByActivityCount(map);
  }

  @Override
  public ProductPojo findProductContent(Long id) throws SQLException {

    return productMapper.findProductContent(id);
  }

  @Override
  public int productContentUpdate(ProductPojo productPojo) throws SQLException {

    return productMapper.productContentUpdate(productPojo);
  }

  @Override
  public ProductPojo findProductName(Long id) throws SQLException {

    return productMapper.findProductName(id);
  }

  @Override
  public int productNameUpdate(ProductPojo productPojo) throws SQLException {

    return productMapper.productNameUpdate(productPojo);
  }

  @Override
  public int getProductStock(ProductPojo productPojo) throws SQLException {
    return productMapper.getProductStock(productPojo);
  }

  @Override
  public int productStatusUpdate(ProductPojo productPojo) throws SQLException {
    return productMapper.productStatusUpdate(productPojo);
  }

  @Override
  public List<ProductPojo> findProductStockList(Long id) throws SQLException {
    return productMapper.findProductStockList(id);
  }

  @Override
  public int findProductStockCount(Long id) throws SQLException {
    return productMapper.findProductStockCount(id);
  }

  @Override
  public int productStockUpdate(String stock, String stockSkuId) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("stock", stock);
    map.put("stockSkuId", stockSkuId);
    return productMapper.productStockUpdate(map);
  }

  @Override
  public List<ProductExcelPojo> getProductAll2(Map<String, Object> map) {
    return productMapper.getProductAll2(map);
  }

  @Override
  public ProductPojo getById(Long id) throws SQLException {
    return productMapper.getById(id);
  }

  @Override
  public List<ProductPojo> getProductAllSeller(Map<String, Object> map) {
    return productMapper.getProductAllSeller(map);
  }

  @Override
  public Long addProductSeller(ProductPojo product) {
    return productMapper.addProductSeller(product);
  }

  @Override
  public void productUpdateSeller(ProductPojo product) {
    productMapper.productUpdateSeller(product);
  }

  @Override
  public ProductPojo findProductSeller(ProductPojo product) {
    return productMapper.findProductSeller(product);
  }

  @Override
  public void checkProductSeller(Long id) throws SQLException {

    productMapper.checkProductSeller(id);
  }

  @Override
  public void uncheckProductSeller(Long id) throws SQLException {
    productMapper.uncheckProductSeller(id);

  }
}
