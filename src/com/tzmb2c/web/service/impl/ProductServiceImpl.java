package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.ProductDao;
import com.tzmb2c.web.pojo.ProductExcelPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.service.ProductService;

/**
 * @author EricChen
 */
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductDao productDao;
  private ProductPojo productPojo;

  @Override
  public int getCount(ProductPojo product, Pager page) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (product != null && product.getProductNameOrId() != null
        && !product.getProductNameOrId().equals("")) {
      map.put("productNameOrId", product.getProductNameOrId());
    }
    if (product != null && product.getActivityStatus() != null
        && !product.getActivityStatus().equals("")) {
      map.put("activityStatus", product.getActivityStatus());
    }
    if (product != null && product.getProductName() != null && !product.getProductName().equals("")) {
      map.put("productName", product.getProductName());
    }
    if (product != null && product.getProductStatus() != null
        && !product.getProductStatus().equals("")) {
      map.put("productStatus", product.getProductStatus());
    }
    if (product != null && product.getProductTypeId() != null
        && !product.getProductTypeId().equals("")) {
      map.put("productTypeId", product.getProductTypeId());
    }
    if (product != null && product.getProductTypeIds() != null
        && !product.getProductTypeIds().equals("")) {
      map.put("productTypeIds", product.getProductTypeIds());
    }
    if (product != null && product.getProductType1() != null
        && !product.getProductType1().equals("")) {
      map.put("productType1", product.getProductType1());
    }
    if (product != null) {
      map.put("productNameEn", product.getProductNameEn());
      map.put("id", product.getId());
      map.put("productNo", product.getProductNo());
      map.put("productNum", product.getProductNum());
      map.put("remarks", product.getRemarks());
      map.put("isIntroduce", product.getIsIntroduce());
      map.put("isNew", product.getIsNew());
      map.put("userId", product.getUserId());
      map.put("brand", product.getBrand());
      map.put("texture", product.getTexture());
      map.put("age", product.getAge());
      map.put("tongji", product.getTongji());
      map.put("name", product.getName());
      map.put("shopName", product.getShopName());
      map.put("userBrandId", product.getUserBrandId());
      map.put("keywords", product.getKeywords());
      map.put("drawkeywords", product.getDrawkeywords());
      map.put("status", product.getStatus());
    }
    return productDao.getCount(map);
  }

  @Override
  public List<ProductPojo> getProductAll(ProductPojo product, Pager page) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (product != null && product.getActivityType() != null
        && !product.getActivityType().equals("")) {
      map.put("activityType", product.getActivityType());
    }
    if (product != null && product.getProductNameOrId() != null
        && !product.getProductNameOrId().equals("")) {
      map.put("productNameOrId", product.getProductNameOrId());
    }
    if (product != null && product.getActivityStatus() != null
        && !product.getActivityStatus().equals("")) {
      map.put("activityStatus", product.getActivityStatus());
    }

    if (product != null && product.getProductName() != null && !product.getProductName().equals("")) {
      map.put("productName", product.getProductName());
    }
    if (product != null && product.getProductStatus() != null
        && !product.getProductStatus().equals("")) {
      map.put("productStatus", product.getProductStatus());
    }
    // else{
    // map.put("productStatus", 1);
    // }
    if (product != null && product.getProductTypeId() != null
        && !product.getProductTypeId().equals("")) {
      map.put("productTypeId", product.getProductTypeId());
    }
    if (product != null && product.getProductTypeIds() != null
        && !product.getProductTypeIds().equals("")) {
      map.put("productTypeIds", product.getProductTypeIds());
    }
    if (product != null && product.getProductType1() != null
        && !product.getProductType1().equals("")) {
      map.put("productType1", product.getProductType1());
    }
    if (product != null && product.getStatus() != null && !product.getStatus().equals("")) {
      map.put("status", product.getStatus());
    }
    if (product != null && product.getUserId() != null && !product.getUserId().equals("")) {
      map.put("userId", product.getUserId());
    }
    if (product != null) {
      if (product.getProductNameEn() != null && product.getProductNameEn().equals("0")) {
        map.put("paixu0", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("1")) {
        map.put("paixu1", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("11")) {
        map.put("paixu11", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("2")) {
        map.put("paixu2", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("22")) {
        map.put("paixu22", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("4")) {
        map.put("paixu4", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("44")) {
        map.put("paixu44", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("5")) {
        map.put("paixu5", product.getProductNameEn());
      } else if (product.getTongji() != null && product.getTongji() == 1) {
        map.put("paixu4", 111);
      } else if (product.getOrderBy() != null && !product.getOrderBy().equals("")) {
        map.put("orderBy", product.getOrderBy());
      } else {
        map.put("paixu3", 111);// 为空的时候，按id排序
      }
      if (product.getTime() != null) {
        map.put("time", product.getTime());
      }
      map.put("uID", product.getUserId());
      map.put("id", product.getId());
      map.put("productNo", product.getProductNo());
      map.put("productNum", product.getProductNum());
      map.put("remarks", product.getRemarks());
      map.put("isIntroduce", product.getIsIntroduce());
      map.put("isNew", product.getIsNew());
      map.put("userId", product.getUserId());
      map.put("brand", product.getBrand());
      map.put("texture", product.getTexture());
      map.put("age", product.getAge());
      map.put("BeginPrice", product.getBeginPrice());
      map.put("EndPrice", product.getEndPrice());
      map.put("tongji", product.getTongji());
      map.put("name", product.getName());
      map.put("shopName", product.getShopName());
      map.put("activityType", product.getActivityType());
      map.put("timeIdIsN", product.getTimeIdIsN());
      map.put("userBrandId", product.getUserBrandId());
      map.put("keywords", product.getKeywords());
      map.put("drawkeywords", product.getDrawkeywords());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return productDao.getProductAll(map);
  }


  @Override
  public List<ProductPojo> getProductAllIds(ProductPojo product) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (product != null && product.getProductName() != null && !product.getProductName().equals("")) {
      map.put("productName", product.getProductName());
    }
    if (product != null && product.getProductStatus() != null
        && !product.getProductStatus().equals("")) {
      map.put("productStatus", product.getProductStatus());
    }
    if (product != null && product.getProductTypeId() != null
        && !product.getProductTypeId().equals("")) {
      map.put("productTypeId", product.getProductTypeId());
    }
    if (product != null && product.getProductTypeIds() != null
        && !product.getProductTypeIds().equals("")) {
      map.put("productTypeIds", product.getProductTypeIds());
    }
    if (product != null && product.getStatus() != null && !product.getStatus().equals("")) {
      map.put("status", product.getStatus());
    }
    if (product != null) {
      if (product.getProductNameEn() != null && product.getProductNameEn().equals("0")) {
        map.put("paixu0", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("1")) {
        map.put("paixu1", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("11")) {
        map.put("paixu11", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("2")) {
        map.put("paixu2", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("22")) {
        map.put("paixu22", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("4")) {
        map.put("paixu4", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("44")) {
        map.put("paixu44", product.getProductNameEn());
      } else {
        map.put("paixu3", 111);// 为空的时候，按id排序
      }
      if (product.getTime() != null) {
        map.put("time", product.getTime());
      }
      map.put("uID", product.getUserId());
      map.put("productNo", product.getProductNo());
      map.put("productNum", product.getProductNum());
      map.put("remarks", product.getRemarks());
      map.put("isIntroduce", product.getIsIntroduce());
      map.put("isNew", product.getIsNew());
      map.put("userId", product.getUserId());
      map.put("brand", product.getBrand());
      map.put("texture", product.getTexture());
      map.put("age", product.getAge());
      map.put("BeginPrice", product.getBeginPrice());
      map.put("EndPrice", product.getEndPrice());
      map.put("keywords", product.getKeywords());
    }

    return productDao.getProductAllIds(map);
  }

  @Override
  public List<ProductPojo> getAgencyProductAll(ProductPojo product, Pager page) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (product != null && product.getFilter() != null && !product.getFilter().equals("")) {
      map.put("filter", product.getFilter());
    }
    if (product != null && product.getProductName() != null && !product.getProductName().equals("")) {
      map.put("productName", product.getProductName());
    }
    if (product != null && product.getProductStatus() != null
        && !product.getProductStatus().equals("")) {
      map.put("productStatus", product.getProductStatus());
    }
    // else{
    // map.put("productStatus", 1);
    // }
    if (product != null && product.getProductTypeId() != null
        && !product.getProductTypeId().equals("")) {
      map.put("productTypeId", product.getProductTypeId());
    }
    if (product != null && product.getProductTypeIds() != null
        && !product.getProductTypeIds().equals("")) {
      map.put("productTypeIds", product.getProductTypeIds());
    }
    if (product != null && product.getStatus() != null && !product.getStatus().equals("")) {
      map.put("status", product.getStatus());
    }
    if (product != null) {
      if (product.getProductNameEn() != null && product.getProductNameEn().equals("0")) {
        map.put("paixu0", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("1")) {
        map.put("paixu1", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("11")) {
        map.put("paixu11", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("2")) {
        map.put("paixu2", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("22")) {
        map.put("paixu22", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("4")) {
        map.put("paixu4", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("44")) {
        map.put("paixu44", product.getProductNameEn());
      } else {
        map.put("paixu3", 111);// 为空的时候，按id排序
      }
      if (product.getTime() != null) {
        map.put("time", product.getTime());
      }
      map.put("uID", product.getUserId());
      map.put("productNo", product.getProductNo());
      map.put("productNum", product.getProductNum());
      map.put("remarks", product.getRemarks());
      map.put("isIntroduce", product.getIsIntroduce());
      map.put("isNew", product.getIsNew());
      map.put("userId", product.getUserId());
      map.put("brand", product.getBrand());
      map.put("texture", product.getTexture());
      map.put("age", product.getAge());
      map.put("BeginPrice", product.getBeginPrice());
      map.put("EndPrice", product.getEndPrice());
      map.put("userBrandId", product.getUserBrandId());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return productDao.getAgencyProductAll(map);
  }

  @Override
  public List<ProductPojo> getProductByActivity(ProductPojo product, Pager page) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (product != null && product.getCreateDate() != null && !product.getCreateDate().equals("")) {
      map.put("createDate", product.getCreateDate());
    }
    if (product != null && product.getSellNumber() != null && !product.getSellNumber().equals("")) {
      map.put("sellNumber", product.getSellNumber());
    }
    if (product != null && product.getRecommend() != null && !product.getRecommend().equals("")) {
      map.put("recommend", product.getRecommend());
    }
    if (product != null && product.getIsNew() != null && !product.getIsNew().equals("")) {
      map.put("isNew", product.getIsNew());
    }
    if (product != null && product.getIsIntroduce() != null && !product.getIsIntroduce().equals("")) {
      map.put("isIntroduce", product.getIsIntroduce());
    }
    if (product != null && product.getHits() != null && !product.getHits().equals("")) {
      map.put("hits", product.getHits());
    }
    if (product != null && product.getActivityType() != null) {
      map.put("activityType", product.getActivityType());
    }
    if (product != null && product.getTimeIdIsN() != null) {
      map.put("timeIdIsN", product.getTimeIdIsN());
    }
    if (product != null && StringUtils.isNotBlank(product.getShopName())) {
      map.put("shopName", product.getShopName());
    }
    if (product != null && StringUtils.isNotBlank(product.getProductName())) {
      map.put("productName", product.getProductName());
    }
    if (product != null && StringUtils.isNotBlank(product.getProductNum())) {
      map.put("productNum", product.getProductNum());
    }
    if (product != null && StringUtils.isNotBlank(product.getProductNo())) {
      map.put("productNo", product.getProductNo());
    }
    if (product != null && product.getStatus() != null) {
      map.put("status", product.getStatus());
    }
    if (product != null && product.getGeekId() != null) {
      map.put("geekId", product.getGeekId());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return productDao.getProductByActivity(map);
  }

  @Override
  public List<ProductPojo> getProductAllByName(ProductPojo product, Pager page) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (product != null && product.getProductName() != null && !product.getProductName().equals("")) {
      String name = product.getProductName().replaceAll(" ", "");
      String regex = "(.{1})";
      name = name.replaceAll(regex, "$1%");
      // replaceall的正则 在每个搜索的字符中加入%，如 贝恩 （贝%恩%）
      map.put("productName", name);
    }
    if (product != null && product.getProductTypeId() != null
        && !product.getProductTypeId().equals("")) {
      map.put("productTypeId", product.getProductTypeId());
    }
    if (product != null && product.getProductTypeIds() != null
        && !product.getProductTypeIds().equals("")) {
      map.put("productTypeIds", product.getProductTypeIds());
    }
    if (product != null) {
      if (product.getProductNameEn() != null && product.getProductNameEn().equals("0")) {
        map.put("paixu0", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("1")) {
        map.put("paixu1", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("2")) {
        map.put("paixu2", product.getProductNameEn());
      } else {
        map.put("paixu3", 111);// 为空的时候，按id排序
      }
      map.put("uID", product.getUserId());
      map.put("productNo", product.getProductNo());
      map.put("productNum", product.getProductNum());
      map.put("remarks", product.getRemarks());
      map.put("isIntroduce", product.getIsIntroduce());
      map.put("isNew", product.getIsNew());
      map.put("userId", product.getUserId());
      map.put("brand", product.getBrand());
      map.put("texture", product.getTexture());
      map.put("age", product.getAge());
      map.put("BeginPrice", product.getBeginPrice());
      map.put("EndPrice", product.getEndPrice());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return productDao.getProductAllByName(map);
  }

  @Override
  public List<ProductPojo> productByUserIdSort(ProductPojo product, Pager page) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (product != null && !product.getUserId().equals("")) {
      map.put("userId", product.getUserId());
    }
    if (product != null && !product.getSorting().equals("")) {
      if (product.getSorting().equals(1)) {
        map.put("sort", 1);
      } else if (product.getSorting().equals(11)) {
        map.put("sort", 11);
      } else if (product.getSorting().equals(2)) {
        map.put("sort", 2);
      } else if (product.getSorting().equals(22)) {
        map.put("sort", 22);
      } else if (product.getSorting().equals(3)) {
        map.put("sort", 3);
      } else if (product.getSorting().equals(33)) {
        map.put("sort", 33);
      }
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    // TODO Auto-generated method stub
    return productDao.productByUserIdSort(map);
  }

  @Override
  public ProductPojo findProduct(ProductPojo product) {
    ProductPojo pojo = productDao.findProduct(product);
    return pojo;
  }

  @Override
  public Long addProduct(ProductPojo product) {
    return productDao.addProduct(product);
  }

  @Override
  public void productUpdate(ProductPojo product) {
    productDao.productUpdate(product);
  }

  @Override
  public void productUpdateIng(ProductPojo product) {
    productDao.productUpdateIng(product);
  }

  @Override
  public void deleProduct(Long id) throws SQLException {
    productDao.deleProduct(id);
  }

  @Override
  public void productDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        productDao.deleProduct(Long.parseLong(tid));
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void checkProduct(Long id) throws SQLException {
    productDao.checkProduct(id);
  }

  @Override
  public List<ProductPojo> productForUser(ProductPojo product, Pager page) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (product != null) {
      map.put("userId", product.getUserId());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return productDao.productForUser(map);
  }

  @Override
  public List<ProductPojo> productForType(Long id) {
    return productDao.productForType(id);
  }

  @Override
  public List<ProductPojo> productForUserNew(Long id) {
    return productDao.productForUserNew(id);
  }

  @Override
  public List<ProductPojo> productForUserYes(Long id) {
    return productDao.productForUserYes(id);
  }

  @Override
  public List<ProductPojo> productForUserYes5(Long id) {
    return productDao.productForUserYes5(id);
  }

  @Override
  public List<ProductPojo> productForShopId(Long id) {
    return productDao.productForShopId(id);
  }

  @Override
  public List<ProductPojo> productForHot(ProductPojo product) {
    return productDao.productForHot(product);
  }

  @Override
  public List<ProductPojo> productAll() throws SQLException {
    return productDao.productAll();
  }

  @Override
  public List<ProductPojo> productForHotSupplyWeb(ProductPojo product) {
    // TODO Auto-generated method stub
    return productDao.productForHotSupplyWeb(product);
  }

  @Override
  public int getAllCount(ProductPojo product) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (product != null && product.getStatus() != null) {
      map.put("status", product.getStatus());
    }
    if (product != null && product.getProductName() != null && !product.getProductName().equals("")) {
      map.put("productName", product.getProductName());
    }
    if (product != null && product.getProductTypeId() != null
        && !product.getProductTypeId().equals("")) {
      map.put("productTypeId", product.getProductTypeId());
    }
    if (product != null && product.getProductTypeIds() != null
        && !product.getProductTypeIds().equals("")) {
      map.put("productTypeIds", product.getProductTypeIds());
    }
    if (product != null) {
      if (product.getProductNameEn() != null && product.getProductNameEn().equals("0")) {
        map.put("paixu0", product.getProductNameEn());
      }
      if (product.getProductNameEn() != null && product.getProductNameEn().equals("1")) {
        map.put("paixu1", product.getProductNameEn());
      }
      if (product.getProductNameEn() != null && product.getProductNameEn().equals("2")) {
        map.put("paixu2", product.getProductNameEn());
      }
      map.put("productNo", product.getProductNo());
      map.put("remarks", product.getRemarks());
      map.put("isIntroduce", product.getIsIntroduce());
      map.put("isNew", product.getIsNew());
      map.put("userId", product.getUserId());
      map.put("brand", product.getBrand());
      map.put("texture", product.getTexture());
      map.put("age", product.getAge());
      map.put("BeginPrice", product.getBeginPrice());
      map.put("EndPrice", product.getEndPrice());
    }
    return productDao.getCount(map);
  }

  @Override
  public List<ProductPojo> getProductAllByParameter(ProductPojo product, Pager page)
      throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (product != null && product.getSortingType() != null && !product.getSortingType().equals("")) {
      map.put("sortingType", product.getSortingType());
    }
    if (product != null && product.getProductTypeId() != null
        && !product.getProductTypeId().equals("")) {
      map.put("productTypeId", product.getProductTypeId());
    }
    if (product != null && product.getProductTypeIds() != null
        && !product.getProductTypeIds().equals("")) {
      map.put("productTypeIds", product.getProductTypeIds());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return productDao.getProductAllByParameter(map);
  }

  @Override
  public List<ProductPojo> getProductLimit(ProductPojo product, Integer limit) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (product != null && product.getIsNew() != null && !product.getIsNew().equals("")) {
      map.put("isNew", product.getIsNew());
    }
    if (product != null && product.getProductTypeIds() != null
        && !product.getProductTypeIds().equals("")) {
      map.put("productTypeIds", product.getProductTypeIds());
    }
    if (limit != null && !limit.equals("")) {
      map.put("limit", limit);
    }
    return productDao.getProductLimit(map);
  }

  @Override
  public List<ProductPojo> productSellCountSupplyWeb(ProductPojo product) {
    // TODO Auto-generated method stub
    return productDao.productSellCountSupplyWeb(product);
  }

  @Override
  public void updateHits(Long id) {
    productDao.updateHits(id);
  }

  @Override
  public void uncheckProduct(Long id) throws SQLException {
    productDao.uncheckProduct(id);

  }

  @Override
  public List<ProductPojo> getProductAllStatus(ProductPojo product, Pager page) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (product != null && product.getProductName() != null && !product.getProductName().equals("")) {
      map.put("productName", product.getProductName());
    }
    if (product != null && product.getProductTypeId() != null
        && !product.getProductTypeId().equals("")) {
      map.put("productTypeId", product.getProductTypeId());
    }
    if (product != null && product.getProductTypeIds() != null
        && !product.getProductTypeIds().equals("")) {
      map.put("productTypeIds", product.getProductTypeIds());
    }
    if (product != null && product.getStatus() != null && !product.getStatus().equals("")) {
      map.put("status", product.getStatus());
    }
    if (product != null) {
      if (product.getProductNameEn() != null && product.getProductNameEn().equals("0")) {
        map.put("paixu0", product.getProductNameEn());// 根据推荐排序热度降序
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("33")) {
        map.put("paixu0", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("1")) {
        map.put("paixu1", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("11")) {
        map.put("paixu1", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("2")) {
        map.put("paixu2", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("22")) {
        map.put("paixu2", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("4")) {
        map.put("paixu4", product.getProductNameEn());
      } else {
        map.put("paixu3", 111);// 为空的时候，按id排序
      }
      map.put("uID", product.getUserId());
      map.put("productNo", product.getProductNo());
      map.put("productNum", product.getProductNum());
      map.put("remarks", product.getRemarks());
      map.put("isIntroduce", product.getIsIntroduce());
      map.put("isNew", product.getIsNew());
      map.put("userId", product.getUserId());
      map.put("brand", product.getBrand());
      map.put("texture", product.getTexture());
      map.put("age", product.getAge());
      map.put("BeginPrice", product.getBeginPrice());
      map.put("EndPrice", product.getEndPrice());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return productDao.getProductAllStatus(map);
  }

  @Override
  public List<ProductPojo> getProductApi(ProductPojo product, Pager page) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (product != null && product.getProductName() != null && !product.getProductName().equals("")) {
      map.put("productName", product.getProductName());
    }
    if (product != null && product.getProductTypeId() != null
        && !product.getProductTypeId().equals("")) {
      map.put("productTypeId", product.getProductTypeId());
    }
    if (product != null && product.getProductTypeIds() != null
        && !product.getProductTypeIds().equals("")) {
      map.put("productTypeIds", product.getProductTypeIds());
    }
    if (product != null && product.getStatus() != null && !product.getStatus().equals("")) {
      map.put("status", product.getStatus());
    }
    if (product != null && product.getUserBrandId() != null && !product.getUserBrandId().equals("")) {
      map.put("userBrandId", product.getUserBrandId());
    }
    if (product != null) {
      if (product.getProductNameEn() != null && product.getProductNameEn().equals("0")) {
        map.put("paixu0", product.getProductNameEn());// 根据推荐排序热度降序
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("33")) {
        map.put("paixu0", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("1")) {
        map.put("paixu1", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("11")) {
        map.put("paixu1", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("2")) {
        map.put("paixu2", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("22")) {
        map.put("paixu2", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("4")) {
        map.put("paixu4", product.getProductNameEn());
      } else {
        map.put("paixu3", 111);// 为空的时候，按id排序
      }
      map.put("uID", product.getUserId());
      map.put("productNo", product.getProductNo());
      map.put("productNum", product.getProductNum());
      map.put("remarks", product.getRemarks());
      map.put("isIntroduce", product.getIsIntroduce());
      map.put("isNew", product.getIsNew());
      map.put("userId", product.getUserId());
      map.put("brand", product.getBrand());
      map.put("texture", product.getTexture());
      map.put("age", product.getAge());
      map.put("BeginPrice", product.getBeginPrice());
      map.put("EndPrice", product.getEndPrice());
      map.put("activityType", product.getActivityType());
      map.put("timeIdIsN", product.getTimeIdIsN());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return productDao.getProductApi(map);
  }

  @Override
  public int getCountStatus(ProductPojo product) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (product != null && product.getStatus() != null) {
      map.put("status", product.getStatus());
    }
    if (product != null && product.getProductName() != null && !product.getProductName().equals("")) {
      map.put("productName", product.getProductName());
    }
    if (product != null && product.getProductTypeId() != null
        && !product.getProductTypeId().equals("")) {
      map.put("productTypeId", product.getProductTypeId());
    }
    if (product != null && product.getProductTypeIds() != null
        && !product.getProductTypeIds().equals("")) {
      map.put("productTypeIds", product.getProductTypeIds());
    }
    if (product != null) {
      if (product.getProductNameEn() != null && product.getProductNameEn().equals("0")) {
        map.put("paixu0", product.getProductNameEn());
      }
      if (product.getProductNameEn() != null && product.getProductNameEn().equals("1")) {
        map.put("paixu1", product.getProductNameEn());
      }
      if (product.getProductNameEn() != null && product.getProductNameEn().equals("2")) {
        map.put("paixu2", product.getProductNameEn());
      }
      map.put("productNo", product.getProductNo());
      map.put("remarks", product.getRemarks());
      map.put("isIntroduce", product.getIsIntroduce());
      map.put("isNew", product.getIsNew());
      map.put("userId", product.getUserId());
      map.put("brand", product.getBrand());
      map.put("texture", product.getTexture());
      map.put("age", product.getAge());
      map.put("BeginPrice", product.getBeginPrice());
      map.put("EndPrice", product.getEndPrice());
      if (product != null && product.getProductNum() != null) {
        map.put("productNum", product.getProductNum());
      }
    }
    return productDao.getCountStatus(map);
  }

  /**
   * 
   * 根据综合字典品牌查询商品
   * */
  @Override
  public List<ProductPojo> getProductByBrandName(String productBrand, Pager page) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("productBrand", productBrand);
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    // TODO Auto-generated method stub
    return productDao.getProductByBrandName(map);
  }

  @Override
  public void updateProductStatus(Long id) throws SQLException {
    productDao.updateProductStatus(id);

  }

  @Override
  public void updateProductsellNumber(Map<String, Object> map) throws SQLException {
    productDao.updateProductsellNumber(map);
  }

  @Override
  public List<ProductPojo> indexProductTopFive() {
    // TODO Auto-generated method stub
    return productDao.indexProductTopFive();
  }

  @Override
  public void indexProductTopFiveUpdate(ProductPojo product) {
    // TODO Auto-generated method stub
    productDao.indexProductTopFiveUpdate(product);
  }

  @Override
  public void indexProductTopFiveUpdateNew(ProductPojo product) {
    // TODO Auto-generated method stub
    productDao.indexProductTopFiveUpdateNew(product);
  }

  @Override
  public List<ProductPojo> indexShowByFloor(String productTypeIds) {
    // TODO Auto-generated method stub
    return productDao.indexShowByFloor(productTypeIds);
  }

  @Override
  public void indexProductFloorUpdateNew(ProductPojo product) {
    // TODO Auto-generated method stub
    productDao.indexProductFloorUpdateNew(product);
  }

  @Override
  public int indexProductListsCount(String productTypeIds) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("productTypeIds", productTypeIds);
    return productDao.indexProductListsCount(map);
  }

  @Override
  public List<ProductPojo> indexProductLists(String productTypeIds, Pager page) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("productTypeIds", productTypeIds);
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return productDao.indexProductLists(map);
  }

  @Override
  public List<ProductPojo> productForShopId1(Map<String, Object> map) {
    return productDao.productForShopId1(map);
  }

  /**
   * 每日10件
   */
  @Override
  public List<ProductPojo> tenOfEveryDay() throws SQLException {

    return productDao.tenOfEveryDay();
  }

  @Override
  public List<ProductPojo> productCollectAdd(Map<String, Object> map) {
    return productDao.productCollectAdd(map);
  }

  @Override
  public void updateProductBaseNumber() throws SQLException {
    productDao.updateProductBaseNumber();
  }

  public ProductPojo getProductPojo() {
    return productPojo;
  }

  public void setProductPojo(ProductPojo productPojo) {
    this.productPojo = productPojo;
  }

  @Override
  public Integer imagesCount(Long id) throws SQLException {
    return productDao.imagesCount(id);
  }

  @Override
  public Integer focusImageCount(Long id) throws SQLException {
    return productDao.focusImageCount(id);
  }

  @Override
  public int getAgencyProductAllCount(ProductPojo product) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (product != null) {
      if (product.getFilter() != null && !product.getFilter().equals("")) {
        map.put("filter", product.getFilter());
      }
      if (product.getProductName() != null && !product.getProductName().equals("")) {
        map.put("productName", product.getProductName());
      }
      if (product.getProductStatus() != null && !product.getProductStatus().equals("")) {
        map.put("productStatus", product.getProductStatus());
      }
      if (product.getProductTypeId() != null && !product.getProductTypeId().equals("")) {
        map.put("productTypeId", product.getProductTypeId());
      }
      if (product.getProductTypeIds() != null && !product.getProductTypeIds().equals("")) {
        map.put("productTypeIds", product.getProductTypeIds());
      }
      if (product.getStatus() != null && !product.getStatus().equals("")) {
        map.put("status", product.getStatus());
      }
      if (product.getTime() != null) {
        map.put("time", product.getTime());
      }
      map.put("uID", product.getUserId());
      map.put("productNo", product.getProductNo());
      map.put("productNum", product.getProductNum());
      map.put("remarks", product.getRemarks());
      map.put("isIntroduce", product.getIsIntroduce());
      map.put("isNew", product.getIsNew());
      map.put("userId", product.getUserId());
      map.put("brand", product.getBrand());
      map.put("texture", product.getTexture());
      map.put("age", product.getAge());
      map.put("BeginPrice", product.getBeginPrice());
      map.put("EndPrice", product.getEndPrice());
      map.put("userBrandId", product.getUserBrandId());
    }
    return productDao.getAgencyProductAllCount(map);
  }

  @Override
  public int findSpecialProductByPid(long productId) throws SQLException {
    return productDao.findSpecialProductByPid(productId);
  }

  @Override
  public int getProductByActivityCount(Map<String, Object> map) {
    return productDao.getProductByActivityCount(map);
  }

  @Override
  public ProductPojo findProductContent(Long id) throws SQLException {
    return productDao.findProductContent(id);
  }

  @Override
  public int productContentUpdate(ProductPojo productPojo) throws SQLException {
    return productDao.productContentUpdate(productPojo);
  }

  @Override
  public ProductPojo findProductName(Long id) throws SQLException {
    return productDao.findProductName(id);
  }

  @Override
  public int productNameUpdate(ProductPojo productPojo) throws SQLException {
    return productDao.productNameUpdate(productPojo);
  }

  @Override
  public int getProductStock(ProductPojo productPojo) throws SQLException {
    return productDao.getProductStock(productPojo);
  }

  @Override
  public int productStatusUpdate(ProductPojo productPojo) throws SQLException {
    return productDao.productStatusUpdate(productPojo);
  }

  @Override
  public List<ProductPojo> findProductStockList(Long id) throws SQLException {
    return productDao.findProductStockList(id);
  }

  @Override
  public int findProductStockCount(Long id) throws SQLException {
    return productDao.findProductStockCount(id);
  }

  @Override
  public int productStockUpdate(String stock, String stockSkuId) throws SQLException {
    return productDao.productStockUpdate(stock, stockSkuId);
  }

  @Override
  public List<ProductExcelPojo> getProductAll2(ProductExcelPojo product, Pager page)
      throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("productStatus", product.getProductStatus());
    if (product != null && product.getProductTypeId() != null
        && !product.getProductTypeId().equals("")) {
      map.put("productTypeId", product.getProductTypeId());
    }
    if (product != null && product.getProductTypeIds() != null
        && !product.getProductTypeIds().equals("")) {
      map.put("productTypeIds", product.getProductTypeIds());
    }
    if (product != null && product.getProductType1() != null
        && !product.getProductType1().equals("")) {
      map.put("productType1", product.getProductType1());
    }
    if (product != null && product.getProductNameOrId() != null
        && !product.getProductNameOrId().equals("")) {
      map.put("productNameOrId", product.getProductNameOrId());
    }
    if (product != null && product.getActivityStatus() != null
        && !product.getActivityStatus().equals("")) {
      map.put("activityStatus", product.getActivityStatus());
    }
    return productDao.getProductAll2(map);
  }

  @Override
  public ProductPojo getById(Long id) throws SQLException {
    return productDao.getById(id);
  }

  @Override
  public List<ProductPojo> getProductAllSeller(ProductPojo product, Pager page) throws SQLException {

    Map<String, Object> map = new HashMap<String, Object>();
    if (product != null && product.getBeginTimeStr() != null
        && !"".equals(product.getBeginTimeStr()) && product.getBeginTimeStr().length() == 10
        && product.getEndTimeStr() != null && !"".equals(product.getEndTimeStr())
        && product.getEndTimeStr().length() == 10) {
      // map.put("beginTimeStr", product.getBeginTimeStr() + " 00:00:00");
      // map.put("endTimeStr", product.getEndTimeStr() + " 23:59:59");
      map.put("beginTimeStr", product.getBeginTimeStr());
      map.put("endTimeStr", product.getEndTimeStr());
    }
    if (product != null && product.getActivityType() != null
        && !product.getActivityType().equals("")) {
      map.put("activityType", product.getActivityType());
    }
    if (product != null && product.getProductNameOrId() != null
        && !product.getProductNameOrId().equals("")) {
      map.put("productNameOrId", product.getProductNameOrId());
    }
    if (product != null && product.getActivityStatus() != null
        && !product.getActivityStatus().equals("")) {
      map.put("activityStatus", product.getActivityStatus());
    }

    if (product != null && product.getProductName() != null && !product.getProductName().equals("")) {
      map.put("productName", product.getProductName());
    }
    if (product != null && product.getProductStatus() != null
        && !product.getProductStatus().equals("")) {
      map.put("productStatus", product.getProductStatus());
    }
    // else{
    // map.put("productStatus", 1);
    // }
    if (product != null && product.getProductTypeId() != null
        && !product.getProductTypeId().equals("")) {
      map.put("productTypeId", product.getProductTypeId());
    }
    if (product != null && product.getProductTypeIds() != null
        && !product.getProductTypeIds().equals("")) {
      map.put("productTypeIds", product.getProductTypeIds());
    }
    if (product != null && product.getProductType1() != null
        && !product.getProductType1().equals("")) {
      map.put("productType1", product.getProductType1());
    }
    if (product != null && product.getStatus() != null && !product.getStatus().equals("")) {
      map.put("status", product.getStatus());
    }
    if (product != null && product.getUserId() != null && !product.getUserId().equals("")) {
      map.put("userId", product.getUserId());
    }
    if (product != null) {
      if (product.getProductNameEn() != null && product.getProductNameEn().equals("0")) {
        map.put("paixu0", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("1")) {
        map.put("paixu1", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("11")) {
        map.put("paixu11", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("2")) {
        map.put("paixu2", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("22")) {
        map.put("paixu22", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("4")) {
        map.put("paixu4", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("44")) {
        map.put("paixu44", product.getProductNameEn());
      } else if (product.getProductNameEn() != null && product.getProductNameEn().equals("5")) {
        map.put("paixu5", product.getProductNameEn());
      } else if (product.getTongji() != null && product.getTongji() == 1) {
        map.put("paixu4", 111);
      } else if (product.getOrderBy() != null && !product.getOrderBy().equals("")) {
        map.put("orderBy", product.getOrderBy());
      } else {
        map.put("paixu3", 111);// 为空的时候，按id排序
      }
      if (product.getTime() != null) {
        map.put("time", product.getTime());
      }
      map.put("uID", product.getUserId());
      map.put("id", product.getId());
      map.put("productNo", product.getProductNo());
      map.put("productNum", product.getProductNum());
      map.put("remarks", product.getRemarks());
      map.put("isIntroduce", product.getIsIntroduce());
      map.put("isNew", product.getIsNew());
      map.put("userId", product.getUserId());
      map.put("brand", product.getBrand());
      map.put("texture", product.getTexture());
      map.put("age", product.getAge());
      map.put("BeginPrice", product.getBeginPrice());
      map.put("EndPrice", product.getEndPrice());
      map.put("tongji", product.getTongji());
      map.put("name", product.getName());
      map.put("shopName", product.getShopName());
      map.put("activityType", product.getActivityType());
      map.put("timeIdIsN", product.getTimeIdIsN());
      map.put("userBrandId", product.getUserBrandId());
      map.put("keywords", product.getKeywords());
      map.put("drawkeywords", product.getDrawkeywords());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return productDao.getProductAllSeller(map);

  }

  @Override
  public Long addProductSeller(ProductPojo product) {
    return productDao.addProductSeller(product);
  }

  @Override
  public void productUpdateSeller(ProductPojo product) {
    productDao.productUpdateSeller(product);
  }

  @Override
  public ProductPojo findProductSeller(ProductPojo product) {
    ProductPojo pojo = productDao.findProductSeller(product);
    return pojo;
  }

  @Override
  public void checkProductSeller(Long id) throws SQLException {
    productDao.checkProductSeller(id);
  }

  @Override
  public void uncheckProductSeller(Long id) throws SQLException {
    productDao.uncheckProductSeller(id);

  }
}
