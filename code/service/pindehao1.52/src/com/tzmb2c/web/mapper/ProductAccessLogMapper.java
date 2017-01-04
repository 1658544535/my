package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductAccessLogPojo;

public interface ProductAccessLogMapper {

  public int productAccessLogAllCount(Map<String, Object> map);

  public List<ProductAccessLogPojo> productAccessLogAllList(Map<String, Object> map);

  public List<ProductAccessLogPojo> productAccessLogAllListHot(Map<String, Object> map);

}
