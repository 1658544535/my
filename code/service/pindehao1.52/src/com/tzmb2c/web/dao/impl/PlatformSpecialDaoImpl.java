/*
 * 文 件 名:  PlatformSpecialDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-05-28
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.PlatformSpecialDao;
import com.tzmb2c.web.mapper.PlatformSpecialMapper;
import com.tzmb2c.web.pojo.PlatformSpecialPojo;

/**
 * PlatformSpecial Dao层
 */
public class PlatformSpecialDaoImpl implements PlatformSpecialDao {

  @Autowired
  private PlatformSpecialMapper platformSpecialMapper;

  @Override
  public int add(PlatformSpecialPojo platformSpecial) {
    if (null == platformSpecial) {
      return 0;
    }
    int rows = platformSpecialMapper.insert(platformSpecial);
    return rows;
  }

  @Override
  public int update(PlatformSpecialPojo platformSpecial) {
    if (null == platformSpecial) {
      return 0;
    }
    int rows = platformSpecialMapper.update(platformSpecial);
    return rows;
  }

  @Override
  public int delete(Integer id) {
    if (null == id) {
      return 0;
    }
    int rows = platformSpecialMapper.deleteById(id);
    return rows;
  }

  @Override
  public PlatformSpecialPojo getById(Integer id) {
    if (null == id) {
      return null;
    }
    PlatformSpecialPojo platformSpecial = platformSpecialMapper.getById(id);
    //
    return platformSpecial;
  }

  @Override
  public Integer countBy(Map<String, Object> params) {
    Integer rows = platformSpecialMapper.countBy(params);
    return rows;
  }

  @Override
  public List<PlatformSpecialPojo> listPage(Map<String, Object> params) {
    List<PlatformSpecialPojo> lists = platformSpecialMapper.listPage(params);
    return lists;
  }

  @Override
  public int updateOne(PlatformSpecialPojo platformSpecial) {
    if (null == platformSpecial) {
      return 0;
    }
    int rows = platformSpecialMapper.updateOne(platformSpecial);
    return rows;
  }

  @Override
  public PlatformSpecialPojo getNewPlatformSpecial(Map<String, Object> params) {
    return platformSpecialMapper.getNewPlatformSpecial(params);
  }

  @Override
  public PlatformSpecialPojo findSpecialByParams(Map<String, Object> params) {
    return platformSpecialMapper.findSpecialByParams(params);
  }

  @Override
  public int increaseCollectsById(PlatformSpecialPojo platformSpecial) throws SQLException {
    return platformSpecialMapper.increaseCollectsById(platformSpecial);
  }

  @Override
  public int decreaseCollectsById(PlatformSpecialPojo platformSpecial) throws SQLException {
    return platformSpecialMapper.decreaseCollectsById(platformSpecial);
  }
}
