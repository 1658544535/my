package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.BrandDicPojo;

public interface BrandDicMapper {
  List<BrandDicPojo> findBrandDicList(Map<String, Object> map) throws SQLException;

  List<BrandDicPojo> findBrandActivityList(Map<String, Object> map) throws SQLException;

  int findBrandDicCount(Map<String, Object> map) throws SQLException;

  void insertBrandDic(BrandDicPojo brandDicPojo) throws SQLException;

  void delBrandDic(BrandDicPojo brandDicPojo) throws SQLException;

  BrandDicPojo findBrandDicById(Long id) throws SQLException;

  void updateBrandDic(BrandDicPojo brandDicPojo) throws SQLException;

  void checkBrandDic(Long id) throws SQLException;

  void uncheckBrandDic(Long id) throws SQLException;
}
