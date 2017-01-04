package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.BrandDicDao;
import com.tzmb2c.web.pojo.BrandDicPojo;
import com.tzmb2c.web.service.BrandDicService;

public class BrandDicServiceImpl implements BrandDicService {
  @Autowired
  private BrandDicDao brandDicDao;

  @Override
  public List<BrandDicPojo> findBrandDicList(Map<String, Object> map) throws SQLException {
    return brandDicDao.findBrandDicList(map);
  }

  @Override
  public int findBrandDicCount(Map<String, Object> map) throws SQLException {
    return brandDicDao.findBrandDicCount(map);
  }

  @Override
  public void insertBrandDic(BrandDicPojo brandDicPojo) throws SQLException {
    brandDicDao.insertBrandDic(brandDicPojo);
  }

  @Override
  public void delBrandDic(BrandDicPojo brandDicPojo) throws SQLException {
    brandDicDao.delBrandDic(brandDicPojo);
  }

  @Override
  public BrandDicPojo findBrandDicById(Long id) throws SQLException {
    return brandDicDao.findBrandDicById(id);
  }

  @Override
  public void updateBrandDic(BrandDicPojo brandDicPojo) throws SQLException {
    brandDicDao.updateBrandDic(brandDicPojo);
  }

  @Override
  public void checkBrandDic(Long id) throws SQLException {
    brandDicDao.checkBrandDic(id);
  }

  @Override
  public void uncheckBrandDic(Long id) throws SQLException {
    brandDicDao.uncheckBrandDic(id);
  }

  @Override
  public List<BrandDicPojo> findBrandActivityList(Map<String, Object> map) throws SQLException {
    return brandDicDao.findBrandActivityList(map);
  }
}
