/*
 * 文 件 名:  UserOrderNoticeMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-11-30
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserOrderNoticePojo;

public interface UserOrderNoticeMapper {
    
    UserOrderNoticePojo getById(Long id) throws SQLException;
    
    int countBy(Map<String, Object> params) throws SQLException;

    List<UserOrderNoticePojo> listPage(Map<String, Object> params) throws SQLException;
    
    int insert(UserOrderNoticePojo userOrderNotice) throws SQLException;
    
    int update(UserOrderNoticePojo userOrderNotice) throws SQLException;
    
    int deleteById(Long id) throws SQLException;
}