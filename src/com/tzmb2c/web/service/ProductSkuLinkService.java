package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;

/**
 * @author EricChen
 */
public interface ProductSkuLinkService {

  public int getProductSkuLinkCount(ProductSkuLinkPojo productSkuLinkPojo);

  public int findSkuLinkByOtherSku(Map<String, Object> map);

  public List<ProductSkuLinkPojo> getProductSkuLinkAll(ProductSkuLinkPojo productSkuLinkPojo,
      Pager page) throws SQLException;

  public ProductSkuLinkPojo findProductSkuLink(ProductSkuLinkPojo productSkuLink);

  public ProductSkuLinkPojo findSkuLinkByProductId(ProductSkuLinkPojo productSkuLinkPojo);

  public Long addSkuLinkByProductId(ProductSkuLinkPojo productSkuLink) throws SQLException;

  public void productSkuLinkUpdate(ProductSkuLinkPojo productSkuLink) throws SQLException;

  public void deleProductSkuLink(Long id) throws SQLException;

  public void productSkuLinkDeleteId(String[] tids);

  public void productSkuLinkCheckId(String[] tids);

  public void checkProductSkuLink(Long id) throws SQLException;

  public void uncheckProductSkuLink(Long id) throws SQLException;

  public ProductSkuLinkPojo findProductSkuPrice(ProductSkuLinkPojo productSkuLinkPojo);

  public void productSkuPriceUpdate(ProductSkuLinkPojo productSkuLinkPojo) throws SQLException;

  public List<ProductSkuLinkPojo> findSkuColorByProductId(ProductSkuLinkPojo productSkuLink);

  public List<ProductSkuLinkPojo> findSkuFormatByProductId(ProductSkuLinkPojo productSkuLink);

  public int updateProductSkuStock(ProductSkuLinkPojo productSkuLink) throws SQLException;

  /**
   * 限时秒杀控制库存
   * 
   * @param productSkuLink 传对应活动activityId控制
   * @return
   * @throws SQLException
   * @throw
   * @return int
   */
  public int updateActivityProductSkuStock(ProductSkuLinkPojo productSkuLink) throws SQLException;

  List<ProductSkuLinkPojo> listPage(Map<String, Object> params) throws SQLException;

  int update(ProductSkuLinkPojo productSkuLink) throws SQLException;

  public ProductSkuLinkPojo getById(Long id) throws SQLException;

  /**
   * 查询库存
   * 
   * @param map
   * @return
   * @throws SQLException
   */
  public int getSkuStock(Map<String, Object> map) throws SQLException;
}
