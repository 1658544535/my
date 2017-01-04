/*
 * 文 件 名:  GrouponPushMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-11-14
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.GrouponPushPojo;

public interface GrouponPushMapper {
    
    GrouponPushPojo getById(Long id) throws SQLException;
    
    int countBy(Map<String, Object> params) throws SQLException;

    List<GrouponPushPojo> listPage(Map<String, Object> params) throws SQLException;
    
    int insert(GrouponPushPojo grouponPush) throws SQLException;
    
    int update(GrouponPushPojo grouponPush) throws SQLException;
    
    int deleteById(Long id) throws SQLException;
}