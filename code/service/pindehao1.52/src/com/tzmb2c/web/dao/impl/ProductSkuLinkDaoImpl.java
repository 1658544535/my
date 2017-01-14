package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ProductSkuLinkDao;
import com.tzmb2c.web.mapper.ProductSkuLinkMapper;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;

/**
 * @author EricChen
 */
public class ProductSkuLinkDaoImpl implements ProductSkuLinkDao {

  @Autowired
  private ProductSkuLinkMapper productSkuLinkMapper;

  @Override
  public int getProductSkuLinkCount(Map<String, Object> map) {
    return productSkuLinkMapper.getProductSkuLinkCount(map);
  }

  @Override
  public List<ProductSkuLinkPojo> getProductSkuLinkAll(Map<String, Object> map) {
    return productSkuLinkMapper.getProductSkuLinkAll(map);
  }

  @Override
  public ProductSkuLinkPojo findProductSkuLink(ProductSkuLinkPojo productSkuLink) {
    return productSkuLinkMapper.findProductSkuLink(productSkuLink);
  }

  @Override
  public void productSkuLinkUpdate(ProductSkuLinkPojo productSkuLink) {
    productSkuLinkMapper.productSkuLinkUpdate(productSkuLink);
  }

  @Override
  public ProductSkuLinkPojo findSkuLinkByProductId(ProductSkuLinkPojo productSkuLink) {
    return productSkuLinkMapper.findSkuLinkByProductId(productSkuLink);
  }

  @Override
  public List<ProductSkuLinkPojo> findSkuColorByProductId(ProductSkuLinkPojo productSkuLink) {
    return productSkuLinkMapper.findSkuColorByProductId(productSkuLink);
  }

  @Override
  public List<ProductSkuLinkPojo> findSkuFormatByProductId(ProductSkuLinkPojo productSkuLink) {
    return productSkuLinkMapper.findSkuFormatByProductId(productSkuLink);
  }

  @Override
  public Long addSkuLinkByProductId(ProductSkuLinkPojo productSkuLink) throws SQLException {
    return productSkuLinkMapper.addSkuLinkByProductId(productSkuLink);
  }

  @Override
  public void deleProductSkuLink(Long id) throws SQLException {
    productSkuLinkMapper.deleProductSkuLink(id);
  }

  @Override
  public void checkProductSkuLink(Long id) throws SQLException {
    productSkuLinkMapper.checkProductSkuLink(id);
  }

  @Override
  public ProductSkuLinkPojo findProductSkuPrice(ProductSkuLinkPojo productSkuLink) {
    return productSkuLinkMapper.findProductSkuPrice(productSkuLink);
  }

  @Override
  public void productSkuPriceUpdate(ProductSkuLinkPojo productSkuLink) throws SQLException {
    productSkuLinkMapper.productSkuPriceUpdate(productSkuLink);
  }

  @Override
  public void uncheckProductSkuLink(Long id) throws SQLException {
    productSkuLinkMapper.uncheckProductSkuLink(id);
  }

  @Override
  public int findSkuLinkByOtherSku(Map<String, Object> map) {
    return productSkuLinkMapper.findSkuLinkByOtherSku(map);
  }

  @Override
  public int updateProductSkuStock(ProductSkuLinkPojo productSkuLink) throws SQLException {
    return productSkuLinkMapper.updateProductSkuStock(productSkuLink);
  }

  @Override
  public List<ProductSkuLinkPojo> listPage(Map<String, Object> params) throws SQLException {
    return productSkuLinkMapper.listPage(params);
  }

  @Override
  public int update(ProductSkuLinkPojo productSkuLink) throws SQLException {
    return productSkuLinkMapper.update(productSkuLink);
  }

  @Override
  public ProductSkuLinkPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    ProductSkuLinkPojo productSkuLink = productSkuLinkMapper.getById(id);
    return productSkuLink;
  }

  @Override
  public int updateActivityProductSkuStock(ProductSkuLinkPojo productSkuLink) throws SQLException {
    return productSkuLinkMapper.updateActivityProductSkuStock(productSkuLink);
  }

  @Override
  public int getSkuStock(Map<String, Object> map) throws SQLException {
    return productSkuLinkMapper.getSkuStock(map);
  }

  @Override
  public Long addSkuLinkByProductIdSeller(ProductSkuLinkPojo productSkuLink) throws SQLException {
    return productSkuLinkMapper.addSkuLinkByProductIdSeller(productSkuLink);
  }

  @Override
  public ProductSkuLinkPojo findProductSkuLinkSeller(ProductSkuLinkPojo productSkuLink) {
    return productSkuLinkMapper.findProductSkuLinkSeller(productSkuLink);
  }

  @Override
  public void productSkuLinkUpdateSeller(ProductSkuLinkPojo productSkuLink) throws SQLException {
    productSkuLinkMapper.productSkuLinkUpdateSeller(productSkuLink);
  }


}
