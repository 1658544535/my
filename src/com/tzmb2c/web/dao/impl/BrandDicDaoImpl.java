package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.BrandDicDao;
import com.tzmb2c.web.mapper.BrandDicMapper;
import com.tzmb2c.web.pojo.BrandDicPojo;

public class BrandDicDaoImpl implements BrandDicDao {
  @Autowired
  private BrandDicMapper brandDicMapper;

  @Override
  public List<BrandDicPojo> findBrandDicList(Map<String, Object> map) throws SQLException {
    return brandDicMapper.findBrandDicList(map);
  }

  @Override
  public int findBrandDicCount(Map<String, Object> map) throws SQLException {
    return brandDicMapper.findBrandDicCount(map);
  }

  @Override
  public void insertBrandDic(BrandDicPojo brandDicPojo) throws SQLException {
    brandDicMapper.insertBrandDic(brandDicPojo);
  }

  @Override
  public void delBrandDic(BrandDicPojo brandDicPojo) throws SQLException {
    brandDicMapper.delBrandDic(brandDicPojo);
  }

  @Override
  public BrandDicPojo findBrandDicById(Long id) throws SQLException {
    return brandDicMapper.findBrandDicById(id);
  }

  @Override
  public void updateBrandDic(BrandDicPojo brandDicPojo) throws SQLException {
    brandDicMapper.updateBrandDic(brandDicPojo);
  }

  @Override
  public void checkBrandDic(Long id) throws SQLException {
    brandDicMapper.checkBrandDic(id);
  }

  @Override
  public void uncheckBrandDic(Long id) throws SQLException {
    brandDicMapper.uncheckBrandDic(id);
  }

  @Override
  public List<BrandDicPojo> findBrandActivityList(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return brandDicMapper.findBrandActivityList(map);
  }
}
