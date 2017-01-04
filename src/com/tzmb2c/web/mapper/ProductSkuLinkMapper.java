package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductSkuLinkPojo;

/**
 * @author EricChen
 */
public interface ProductSkuLinkMapper {

  int getProductSkuLinkCount(Map<String, Object> map);

  int findSkuLinkByOtherSku(Map<String, Object> map);

  List<ProductSkuLinkPojo> getProductSkuLinkAll(Map<String, Object> map);

  ProductSkuLinkPojo findProductSkuLink(ProductSkuLinkPojo productSkuLink);

  public void productSkuLinkUpdate(ProductSkuLinkPojo productSkuLink);

  ProductSkuLinkPojo findSkuLinkByProductId(ProductSkuLinkPojo productSkuLink);

  public Long addSkuLinkByProductId(ProductSkuLinkPojo productSkuLink) throws SQLException;

  public void deleProductSkuLink(Long id) throws SQLException;

  void checkProductSkuLink(Long id) throws SQLException;

  void uncheckProductSkuLink(Long id) throws SQLException;

  ProductSkuLinkPojo findProductSkuPrice(ProductSkuLinkPojo productSkuLink);

  void productSkuPriceUpdate(ProductSkuLinkPojo productSkuLink) throws SQLException;

  public List<ProductSkuLinkPojo> findSkuColorByProductId(ProductSkuLinkPojo productSkuLink);

  public List<ProductSkuLinkPojo> findSkuFormatByProductId(ProductSkuLinkPojo productSkuLink);

  public int updateProductSkuStock(ProductSkuLinkPojo productSkuLink) throws SQLException;

  public int updateActivityProductSkuStock(ProductSkuLinkPojo productSkuLink) throws SQLException;

  List<ProductSkuLinkPojo> listPage(Map<String, Object> params) throws SQLException;

  int update(ProductSkuLinkPojo productSkuLink) throws SQLException;

  public ProductSkuLinkPojo getById(Long id) throws SQLException;

  public int getSkuStock(Map<String, Object> map) throws SQLException;
}
