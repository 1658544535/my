/*
 * 文 件 名:  PlatformSpecialServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-05-28
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.PlatformSpecialDao;
import com.tzmb2c.web.pojo.PlatformSpecialPojo;
import com.tzmb2c.web.service.PlatformSpecialService;

/**
 * PlatformSpecial Service层
 */
public class PlatformSpecialServiceImpl implements PlatformSpecialService {

  @Autowired
  private PlatformSpecialDao platformSpecialdao;

  @Override
  public int add(PlatformSpecialPojo platformSpecial) {
    if (null == platformSpecial) {
      return 0;
    }
    int rows = platformSpecialdao.add(platformSpecial);
    return rows;
  }

  @Override
  public int update(PlatformSpecialPojo platformSpecial) {
    if (null == platformSpecial) {
      return 0;
    }
    int rows = platformSpecialdao.update(platformSpecial);
    return rows;
  }

  @Override
  public int delete(Integer id) {
    if (null == id) {
      return 0;
    }
    int rows = platformSpecialdao.delete(id);
    return rows;
  }

  @Override
  public PlatformSpecialPojo getById(Integer id) {
    if (null == id) {
      return null;
    }
    PlatformSpecialPojo platformSpecial = platformSpecialdao.getById(id);
    return platformSpecial;
  }

  @Override
  public Integer countBy(Map<String, Object> params) {
    Integer rows = platformSpecialdao.countBy(params);
    return rows;
  }

  @Override
  public List<PlatformSpecialPojo> listPage(Map<String, Object> params) {
    List<PlatformSpecialPojo> lists = platformSpecialdao.listPage(params);
    return lists;
  }



  @Override
  public PlatformSpecialPojo getNewPlatformSpecial(Map<String, Object> params) {
    return platformSpecialdao.getNewPlatformSpecial(params);
  }

  @Override
  public int updateOne(PlatformSpecialPojo platformSpecial) {
    if (null == platformSpecial) {
      return 0;
    }
    int rows = platformSpecialdao.updateOne(platformSpecial);
    return rows;
  }

  @Override
  public PlatformSpecialPojo findSpecialByParams(Map<String, Object> params) {
    return platformSpecialdao.findSpecialByParams(params);
  }

  @Override
  public int increaseCollectsById(Long id, long incrNum) throws SQLException {
    PlatformSpecialPojo platformSpecial = new PlatformSpecialPojo();
    platformSpecial.setId(id);
    platformSpecial.setCollectNum(incrNum);
    return platformSpecialdao.increaseCollectsById(platformSpecial);
  }

  @Override
  public int decreaseCollectsById(Long id, long decrNum) throws SQLException {
    PlatformSpecialPojo platformSpecial = new PlatformSpecialPojo();
    platformSpecial.setId(id);
    platformSpecial.setCollectNum(decrNum);
    return platformSpecialdao.decreaseCollectsById(platformSpecial);
  }
}
