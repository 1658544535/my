/*
 * 文 件 名:  PlatformSpecialMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-05-28
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.PlatformSpecialPojo;

public interface PlatformSpecialMapper {

  PlatformSpecialPojo getById(Integer id);

  int countBy(Map<String, Object> params);

  List<PlatformSpecialPojo> listPage(Map<String, Object> params);

  int insert(PlatformSpecialPojo platformSpecial);

  int update(PlatformSpecialPojo platformSpecial);

  int deleteById(Integer id);

  int updateOne(PlatformSpecialPojo platformSpecial);

  PlatformSpecialPojo getNewPlatformSpecial(Map<String, Object> params);

  public PlatformSpecialPojo findSpecialByParams(Map<String, Object> params);

  public int increaseCollectsById(PlatformSpecialPojo platformSpecial) throws SQLException;

  public int decreaseCollectsById(PlatformSpecialPojo platformSpecial) throws SQLException;

}
