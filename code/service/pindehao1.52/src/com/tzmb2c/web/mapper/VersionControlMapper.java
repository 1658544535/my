/*
 * 文 件 名:  VersionControlMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-12-01
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.VersionControlPojo;

public interface VersionControlMapper {
    
    VersionControlPojo getById(Long id) throws SQLException;
    
    int countBy(Map<String, Object> params) throws SQLException;

    List<VersionControlPojo> listPage(Map<String, Object> params) throws SQLException;
    
    int insert(VersionControlPojo versionControl) throws SQLException;
    
    int update(VersionControlPojo versionControl) throws SQLException;
    
    int deleteById(Long id) throws SQLException;
}