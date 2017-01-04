package com.tzmb2c.web.dao;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductAccessLogPojo;

public interface ProductAccessLogDao {

  int productAccessLogAllCount(Map<String, Object> map);

  List<ProductAccessLogPojo> productAccessLogAllList(Map<String, Object> map);

  List<ProductAccessLogPojo> productAccessLogAllListHot(Map<String, Object> map);

}
