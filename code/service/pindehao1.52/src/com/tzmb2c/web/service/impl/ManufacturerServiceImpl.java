package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.ManufacturerDao;
import com.tzmb2c.web.dao.ProductDao;
import com.tzmb2c.web.dao.SysLoginDao;
import com.tzmb2c.web.dao.UserInfoDao;
import com.tzmb2c.web.pojo.ManufacturerPojo;
import com.tzmb2c.web.service.ManufacturerService;

public class ManufacturerServiceImpl implements ManufacturerService {
  @Autowired
  private ManufacturerDao manufacturerDao;
  @Autowired
  private ProductDao productDao;
  @Autowired
  private SysLoginDao sysLoginDao;
  @Autowired
  private UserInfoDao userInfoDao;

  public void setManufacturerDao(ManufacturerDao manufacturerDao) {
    this.manufacturerDao = manufacturerDao;
  }

  @Override
  public void insertManufacturer(ManufacturerPojo manufacturerPojo) throws SQLException {
    manufacturerDao.insertManufacturer(manufacturerPojo);

  }

  @Override
  public List<ManufacturerPojo> manufacturerAllService() throws SQLException {
    return manufacturerDao.getManufacturerAll();
  }

  @Override
  public void updateManufacturer(ManufacturerPojo manufacturerPojo) throws SQLException {
    manufacturerDao.updateManufacturer(manufacturerPojo);

  }

  @Override
  public ManufacturerPojo getfindByIdManufacturer(Long id) throws SQLException {
    return manufacturerDao.getfindByIdManufacturer(id);

  }

  @Override
  public int manufacturerAllCount(ManufacturerPojo manufacturerDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (manufacturerDaoPojo != null) {
      map.put("company", manufacturerDaoPojo.getCompany());
      map.put("address", manufacturerDaoPojo.getAddress());
      map.put("mainProduct", manufacturerDaoPojo.getMainProduct());
      map.put("mainCategory", manufacturerDaoPojo.getMainCategory());
      map.put("salesArea", manufacturerDaoPojo.getSalesArea());
      map.put("phone", manufacturerDaoPojo.getPhone());
      map.put("userId", manufacturerDaoPojo.getUserId());
      map.put("loginname", manufacturerDaoPojo.getLoginname());
      map.put("name", manufacturerDaoPojo.getName());
      map.put("createDateStr", manufacturerDaoPojo.getCreateDateStr());
      map.put("status", manufacturerDaoPojo.getStatus());
    }
    return manufacturerDao.manufacturerAllCount(map);
  }

  @Override
  public List<ManufacturerPojo> manufacturerAllList(ManufacturerPojo manufacturerDaoPojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (manufacturerDaoPojo != null) {
      map.put("company", manufacturerDaoPojo.getCompany());
      map.put("address", manufacturerDaoPojo.getAddress());
      map.put("mainProduct", manufacturerDaoPojo.getMainProduct());
      map.put("mainCategory", manufacturerDaoPojo.getMainCategory());
      map.put("salesArea", manufacturerDaoPojo.getSalesArea());
      map.put("phone", manufacturerDaoPojo.getPhone());
      map.put("userId", manufacturerDaoPojo.getUserId());
      map.put("loginname", manufacturerDaoPojo.getLoginname());
      map.put("name", manufacturerDaoPojo.getName());
      map.put("createDateStr", manufacturerDaoPojo.getCreateDateStr());
      map.put("status", manufacturerDaoPojo.getStatus());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<ManufacturerPojo> list = manufacturerDao.manufacturerAllList(map);

    return list;
  }

  @Override
  public void manufacturerChecks(String[] tids) {
    for (String tid : tids) {
      try {
        manufacturerDao.checkManufacturer(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void checkManufacturer(Long id) throws SQLException {
    manufacturerDao.checkManufacturer(id);
  }

  @Override
  public void uncheckManufacturer(Long id) throws SQLException {
    manufacturerDao.uncheckManufacturer(id);
  }

  @Override
  public void delManufacturer(Long id) throws SQLException {
    ManufacturerPojo pojo = manufacturerDao.getfindByIdManufacturer(id);
    if (pojo != null) {
      productDao.updateProductStatus(pojo.getUserId());
      sysLoginDao.deleteSysLogin(pojo.getUserId());
      userInfoDao.delUserInfobyUserId(pojo.getUserId());
    }


    manufacturerDao.delManufacturer(id);
  }

  @Override
  public void deleteManufacturer(Long id) throws SQLException {
    manufacturerDao.deleteManufacturer(id);
    productDao.updateProductStatus(id);
  }

  @Override
  public ManufacturerPojo findManufacturerById(Long id) throws SQLException {

    return manufacturerDao.findManufacturerById(id);

  }

  @Override
  public ManufacturerPojo findManufacturerByUserId(Long id) throws SQLException {

    return manufacturerDao.findManufacturerByUserId(id);

  }

  @Override
  public void updateManufacturerWeb(ManufacturerPojo manufacturerPojo) throws SQLException {
    // TODO Auto-generated method stub
    manufacturerDao.updateManufacturerWeb(manufacturerPojo);
  }

}
