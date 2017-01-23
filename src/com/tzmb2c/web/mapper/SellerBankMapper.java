/*
 * 文 件 名:  SellerBankMapper.java
 * 创 建 人:  admin
 * 创建时间:  2017-01-17
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SellerBankPojo;

public interface SellerBankMapper {
    
    SellerBankPojo getById(Long id) throws SQLException;
    
    int countBy(Map<String, Object> params) throws SQLException;

    List<SellerBankPojo> listPage(Map<String, Object> params) throws SQLException;
    
    int insert(SellerBankPojo sellerBank) throws SQLException;
    
    int update(SellerBankPojo sellerBank) throws SQLException;
    
    int deleteById(Long id) throws SQLException;
}