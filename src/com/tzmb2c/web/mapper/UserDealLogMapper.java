/*
 * 文 件 名:  UserDealLogMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-15
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserDealLogPojo;

public interface UserDealLogMapper {
    
    UserDealLogPojo getById(Long id) throws SQLException;
    
    int countBy(Map<String, Object> params) throws SQLException;

    List<UserDealLogPojo> listPage(Map<String, Object> params) throws SQLException;
    
    int insert(UserDealLogPojo userDealLog) throws SQLException;
    
    int update(UserDealLogPojo userDealLog) throws SQLException;
    
    int deleteById(Long id) throws SQLException;
}