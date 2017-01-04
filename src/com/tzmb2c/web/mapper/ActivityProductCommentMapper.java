/*
 * 文 件 名:  ActivityProductCommentMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-10-26
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ActivityProductCommentPojo;

public interface ActivityProductCommentMapper {
    
    ActivityProductCommentPojo getById(Long id) throws SQLException;
    
    int countBy(Map<String, Object> params) throws SQLException;

    List<ActivityProductCommentPojo> listPage(Map<String, Object> params) throws SQLException;
    
    int insert(ActivityProductCommentPojo activityProductComment) throws SQLException;
    
    int update(ActivityProductCommentPojo activityProductComment) throws SQLException;
    
    int deleteById(Long id) throws SQLException;
}