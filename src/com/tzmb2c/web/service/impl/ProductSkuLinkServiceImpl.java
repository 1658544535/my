package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.ProductSkuLinkDao;
import com.tzmb2c.web.dao.SkuAttributeDao;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.service.ProductSkuLinkService;

/**
 * @author EricChen
 */
public class ProductSkuLinkServiceImpl implements ProductSkuLinkService {

  @Autowired
  private ProductSkuLinkDao productSkuLinkDao;
  @Autowired
  private SkuAttributeDao skuAttributeDao;

  @Override
  public int getProductSkuLinkCount(ProductSkuLinkPojo productSkuLink) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (productSkuLink != null) {
      if (productSkuLink.getProductId() != null && !productSkuLink.getProductId().equals("")) {
        map.put("productId", productSkuLink.getProductId());
      }
      if (productSkuLink.getSkuColorId() != null) {
        map.put("skuColorId", productSkuLink.getSkuColorId());
      }
      if (productSkuLink.getSkuFormatId() != null) {
        map.put("skuFormatId", productSkuLink.getSkuFormatId());
      }
      if (productSkuLink.getId() != null) {
        map.put("id", productSkuLink.getId());
      }
      if (productSkuLink.getStatus() != null) {
        map.put("status", productSkuLink.getStatus());
      }
      if (productSkuLink.getType() != null) {
        map.put("type", productSkuLink.getType());
      }
      if (productSkuLink.getActivityId() != null) {
        map.put("activityId", productSkuLink.getActivityId());
      }
      if (productSkuLink.getStock() > 0) {
        map.put("stockStatus", 1);
      }
    }

    return productSkuLinkDao.getProductSkuLinkCount(map);
  }

  @Override
  public List<ProductSkuLinkPojo> getProductSkuLinkAll(ProductSkuLinkPojo productSkuLink, Pager page)
      throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (productSkuLink != null) {
      if (productSkuLink.getProductId() != null && !productSkuLink.getProductId().equals("")) {
        map.put("productId", productSkuLink.getProductId());
      }
      if (productSkuLink.getId() != null) {
        map.put("id", productSkuLink.getId());
      }
      if (productSkuLink.getStatus() != null) {
        map.put("status", productSkuLink.getStatus());
      }
      if (productSkuLink.getType() != null) {
        map.put("type", productSkuLink.getType());
      }
      if (productSkuLink.getActivityId() != null) {
        map.put("activityId", productSkuLink.getActivityId());
      }
      if (productSkuLink.getStock() > 0) {
        map.put("stockStatus", 1);
      }
      if (productSkuLink.getIsDelete() != null) {
        map.put("isDelete", 0);
      }
    }

    if (page != null) {
      map.put("pageSize", page.getPageNo() * page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return productSkuLinkDao.getProductSkuLinkAll(map);
  }

  @Override
  public ProductSkuLinkPojo findProductSkuLink(ProductSkuLinkPojo productSkuLink) {
    return productSkuLinkDao.findProductSkuLink(productSkuLink);
  }

  @Override
  public void productSkuLinkUpdate(ProductSkuLinkPojo productSkuLink) throws SQLException {
    productSkuLinkDao.productSkuLinkUpdate(productSkuLink);
  }

  @Override
  public ProductSkuLinkPojo findSkuLinkByProductId(ProductSkuLinkPojo productSkuLink) {
    return productSkuLinkDao.findSkuLinkByProductId(productSkuLink);
  }

  @Override
  public List<ProductSkuLinkPojo> findSkuColorByProductId(ProductSkuLinkPojo productSkuLink) {
    return productSkuLinkDao.findSkuColorByProductId(productSkuLink);
  }

  @Override
  public List<ProductSkuLinkPojo> findSkuFormatByProductId(ProductSkuLinkPojo productSkuLink) {
    return productSkuLinkDao.findSkuFormatByProductId(productSkuLink);
  }

  @Override
  public Long addSkuLinkByProductId(ProductSkuLinkPojo productSkuLink) throws SQLException {
    return productSkuLinkDao.addSkuLinkByProductId(productSkuLink);
  }


  @Override
  public void deleProductSkuLink(Long id) throws SQLException {
    productSkuLinkDao.deleProductSkuLink(id);
  }

  @Override
  public void productSkuLinkDeleteId(String[] tids) {
    ProductSkuLinkPojo productSkuLinkPojo = new ProductSkuLinkPojo();
    for (String tid : tids) {
      try {
        productSkuLinkPojo.setId(Long.parseLong(tid));
        productSkuLinkPojo = productSkuLinkDao.findProductSkuLink(productSkuLinkPojo);
        skuAttributeDao.deleSkuAttribute(productSkuLinkPojo.getSkuColorId());
        skuAttributeDao.deleSkuAttribute(productSkuLinkPojo.getSkuFormatId());
        productSkuLinkDao.deleProductSkuLink(Long.parseLong(tid));
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void productSkuLinkCheckId(String[] tids) {
    for (String tid : tids) {
      try {
        productSkuLinkDao.checkProductSkuLink(Long.parseLong(tid));
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void checkProductSkuLink(Long id) throws SQLException {
    productSkuLinkDao.checkProductSkuLink(id);
  }

  @Override
  public ProductSkuLinkPojo findProductSkuPrice(ProductSkuLinkPojo productSkuLink) {
    ProductSkuLinkPojo pojo = productSkuLinkDao.findProductSkuPrice(productSkuLink);
    return pojo;
  }

  @Override
  public void productSkuPriceUpdate(ProductSkuLinkPojo productSkuLink) throws SQLException {
    productSkuLinkDao.productSkuPriceUpdate(productSkuLink);
  }

  @Override
  public void uncheckProductSkuLink(Long id) throws SQLException {
    productSkuLinkDao.uncheckProductSkuLink(id);
  }

  @Override
  public int findSkuLinkByOtherSku(Map<String, Object> map) {
    return productSkuLinkDao.findSkuLinkByOtherSku(map);
  }

  @Override
  public int updateProductSkuStock(ProductSkuLinkPojo productSkuLink) throws SQLException {
    return productSkuLinkDao.updateProductSkuStock(productSkuLink);
  }

  @Override
  public List<ProductSkuLinkPojo> listPage(Map<String, Object> params) throws SQLException {
    return productSkuLinkDao.listPage(params);
  }

  @Override
  public int update(ProductSkuLinkPojo productSkuLink) throws SQLException {
    return productSkuLinkDao.update(productSkuLink);
  }

  @Override
  public ProductSkuLinkPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    ProductSkuLinkPojo productSkuLink = productSkuLinkDao.getById(id);
    return productSkuLink;
  }

  @Override
  public int updateActivityProductSkuStock(ProductSkuLinkPojo productSkuLink) throws SQLException {
    return productSkuLinkDao.updateActivityProductSkuStock(productSkuLink);
  }

  @Override
  public int getSkuStock(Map<String, Object> map) throws SQLException {
    return productSkuLinkDao.getSkuStock(map);
  }

  @Override
  public Long addSkuLinkByProductIdSeller(ProductSkuLinkPojo productSkuLink) throws SQLException {
    return productSkuLinkDao.addSkuLinkByProductIdSeller(productSkuLink);
  }

  @Override
  public ProductSkuLinkPojo findProductSkuLinkSeller(ProductSkuLinkPojo productSkuLink) {
    return productSkuLinkDao.findProductSkuLinkSeller(productSkuLink);
  }

  @Override
  public void productSkuLinkUpdateSeller(ProductSkuLinkPojo productSkuLink) throws SQLException {
    productSkuLinkDao.productSkuLinkUpdateSeller(productSkuLink);
  }

}
