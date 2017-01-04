package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.EleorderPojo;

public interface EleorderMapper {

  public List<EleorderPojo> findEleorderAll(Map<String, Object> map);

  public List<EleorderPojo> findEleorderByorderNo(Map<String, Object> map);

  public EleorderPojo findEleorderByorderNos(Map<String, Object> map);

  public int EleorderAllCount(Map<String, Object> map);

  public void insertEleorder(Map<String, Object> map);

  public int sellSumNum(Long productId) throws SQLException;
}
