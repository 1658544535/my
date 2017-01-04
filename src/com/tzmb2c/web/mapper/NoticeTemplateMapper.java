/*
 * 文 件 名:  NoticeTemplateMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-12-01
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.NoticeTemplatePojo;

public interface NoticeTemplateMapper {
    
    NoticeTemplatePojo getById(Long id) throws SQLException;
    
    int countBy(Map<String, Object> params) throws SQLException;

    List<NoticeTemplatePojo> listPage(Map<String, Object> params) throws SQLException;
    
    int insert(NoticeTemplatePojo noticeTemplate) throws SQLException;
    
    int update(NoticeTemplatePojo noticeTemplate) throws SQLException;
    
    int deleteById(Long id) throws SQLException;
}