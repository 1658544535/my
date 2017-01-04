/*
 * 文 件 名:  UserRedeemCodeMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-12-27
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserRedeemCodePojo;

public interface UserRedeemCodeMapper {
    
    UserRedeemCodePojo getByCode(String code) throws SQLException;
    
    int countBy(Map<String, Object> params) throws SQLException;

    List<UserRedeemCodePojo> listPage(Map<String, Object> params) throws SQLException;
    
    int insert(UserRedeemCodePojo userRedeemCode) throws SQLException;
    
    int update(UserRedeemCodePojo userRedeemCode) throws SQLException;
    
    int deleteByCode(String code) throws SQLException;
}