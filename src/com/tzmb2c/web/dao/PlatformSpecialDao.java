/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.PlatformSpecialPojo;

/**
 * @version 1.0
 * @author 
 */
public interface PlatformSpecialDao {
  /**
   * 
   * 增加一条专题记录
   * @param platformSpecial  专题Pojo
   * @return
   * @throw
   * @return int
   */
  public int add(PlatformSpecialPojo platformSpecial);

  /**
   * 
   * 更新一条专题记录
   * @param platformSpecial  专题Pojo
   * @return
   * @throw
   * @return int
   */
  public int update(PlatformSpecialPojo platformSpecial);

  /**
   * 
   * 取消专题审核
   * @param id   专题ID
   * @return
   * @throw
   * @return int
   */
  public int delete(Integer id);

  /**
   * 
   * 通过ID查询相应的专题记录
   * @param id   专题ID
   * @return
   * @throw
   * @return PlatformSpecialPojo
   */
  public PlatformSpecialPojo getById(Integer id);

  /**
   * 
   * 查询专题记录条数
   * @param params   map参数
   * @return
   * @throw
   * @return Integer
   */
  public Integer countBy(Map<String, Object> params);

  /**
   * 
   * 查询所有专题记录
   * @param params   map参数
   * @return
   * @throw
   * @return List<PlatformSpecialPojo>
   */
  public List<PlatformSpecialPojo> listPage(Map<String, Object> params);

  /**
   * 
   * 专题状态操作
   * @param platformSpecial   专题POJO
   * @return
   * @throw
   * @return int
   */
  public int updateOne(PlatformSpecialPojo platformSpecial);

  /**
   * 查询最新的一条平台专题
   * @return
   */
  public PlatformSpecialPojo getNewPlatformSpecial(Map<String, Object> params);

  public PlatformSpecialPojo findSpecialByParams(Map<String, Object> params);

  /**
     * 增加收藏数
     * @return
     */
  public int increaseCollectsById(PlatformSpecialPojo platformSpecial) throws SQLException;

  /**
   * 减少收藏数
   * @return
   */
  public int decreaseCollectsById(PlatformSpecialPojo platformSpecial) throws SQLException;

}
