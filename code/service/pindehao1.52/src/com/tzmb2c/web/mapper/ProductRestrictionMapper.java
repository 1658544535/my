/*
 * 文 件 名:  ProductRestrictionMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-12-05
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductRestrictionPojo;

public interface ProductRestrictionMapper {
    
    ProductRestrictionPojo getById(Long id) throws SQLException;
    
    int countBy(Map<String, Object> params) throws SQLException;

    List<ProductRestrictionPojo> listPage(Map<String, Object> params) throws SQLException;
    
    int insert(ProductRestrictionPojo productRestriction) throws SQLException;
    
    int update(ProductRestrictionPojo productRestriction) throws SQLException;
    
    int deleteById(Long id) throws SQLException;
}