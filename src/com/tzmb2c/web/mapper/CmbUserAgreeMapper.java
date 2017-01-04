/*
 * 文 件 名:  CmbUserAgreeMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-07-18
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.CmbUserAgreePojo;

public interface CmbUserAgreeMapper {
    
    CmbUserAgreePojo getById(Long id);
    
    int countBy(Map<String, Object> params);

    List<CmbUserAgreePojo> listPage(Map<String, Object> params);
    
    int insert(CmbUserAgreePojo cmbUserAgree);
    
    int update(CmbUserAgreePojo cmbUserAgree);
    
    int deleteById(Long id);
    
    public CmbUserAgreePojo getByUserId(Long userId);
}